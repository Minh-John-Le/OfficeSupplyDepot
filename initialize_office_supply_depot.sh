#!/bin/bash

# Initialize MySQL database.
# ADD this file into the container via Dockerfile.
# Assuming you specify a VOLUME ["/var/lib/mysql"] or `-v /var/lib/mysql` on the `docker run` command…
# Once built, do e.g. `docker run your_image /path/to/docker-mysql-initialize.sh`
# Again, make sure MySQL is persisting data outside the container for this to have any effect.

set -e
set -x

DB_PROPERTIES_FILE=/tmp/db.properties


function set_db_env_variables() {
    # Read the db.properties file and extract the values
    db_driver=$(grep '^db.driver=' ${DB_PROPERTIES_FILE} | cut -d '=' -f 2)
    db_url=$(grep '^db.url=' ${DB_PROPERTIES_FILE} | cut -d '=' -f 2)
    db_username=$(grep '^db.username=' ${DB_PROPERTIES_FILE} | cut -d '=' -f 2)
    db_password=$(grep '^db.password=' ${DB_PROPERTIES_FILE} | cut -d '=' -f 2)
    # Set variables

    # Set the environmental variables
    export DB_DRIVER=$db_driver
    export DB_URL=$db_url
    export DB_USERNAME=$db_username
    export DB_PASSWORD=$db_password
}
set_db_env_variables


SCHEMA_FILE=/tmp/schema.sql


mysql_install_db

# Start the MySQL daemon in the background.
/usr/sbin/mysqld &
mysql_pid=$!

# Wait uintil the mysql admin is started
until mysqladmin ping >/dev/null 2>&1; do
  echo -n "."; sleep 0.2
done

# Permit root login with username and password from outside container.
mysql -uroot -p${DB_PASSWORD} -e "GRANT ALL ON *.* TO '${DB_USERNAME}'@'%' IDENTIFIED BY '${DB_PASSWORD}' WITH GRANT OPTION"


# create the default database from the ADDed file.
mysql < $SCHEMA_FILE

# Tell the MySQL daemon to shutdown.
mysqladmin shutdown

# Wait for the MySQL daemon to exit.
wait $mysql_pid

# create a tar file with the database as it currently exists
tar czvf default_mysql.tar.gz /var/	lib/mysql

# the tarfile contains the initialized state of the database.
# when the container is started, if the database is empty (/var/lib/mysql)
# then it is unpacked from default_mysql.tar.gz from
# the ENTRYPOINT /tmp/run_db script
