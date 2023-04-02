FROM mysql
LABEL maintainer="David Warshawsky <davidawarshawsky@gmail.com>"


# Set the Docker volume to $(pwd)/mysql_docker
VOLUME ["$(pwd)/mysql_docker"]

# Copy the database schema and run and init scripts to the /tmp/ directory
ADD run_db.sh init_db.sh schema.sql OfficeSupplyDepotServlet/src/main/webapp/WEB-INF/classes/db.properties /tmp/

# init_db will create the default
# database from schema.sql, then
# stop mysqld, and finally copy the /var/lib/mysql directory
# to default_mysql_db.tar.gz
RUN /tmp/init_db.sh

# run_db starts mysqld, but first it checks
# to see if the /var/lib/mysql directory is empty, if
# it is it is seeded with default_mysql_db.tar.gz before
# the mysql is fired up


# Set the entrypoint to run the initialization script
ENTRYPOINT ["/bin/bash", "/tmp/run_db.sh"]
