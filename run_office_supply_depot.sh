# start db

set -e
set -x

# Define cleanup procedure
cleanup() {
    echo "Container stopped, performing cleanup..."
    tar czvf /tmp/default_mysql.tar.gz /var/lib/mysql
    exit 0
}

# Trap SIGTERM
trap 'cleanup' SIGTERM

if [ -z "$1" ]; then
  echo "Usage: run_db.sh [path_to_sql_file]"
  exit 1
fi

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





# Check if the provided SQL file is different from the existing schema.sql file.
if ! mysqldiff /tmp/schema.sql "$1" >/dev/null; then
  echo "New database schema detected. Dropping all databases..."

  # Drop all databases with the root user and password.
  # Get a list of all databases except the system databases
  DBS=$(mysql -uroot -p"$DB_PASSWORD" -e "SHOW DATABASES WHERE \`Database\` NOT IN ('information_schema', 'mysql', 'performance_schema')")
  # Loop through the list of databases and drop each one
  for db in $DBS; do
	  mysql -uroot -p"$DB_PASSWORD" -e "DROP DATABASE IF EXISTS \`$db\`"
  done
  
  # Replace the existing schema.sql file with the new one.
  cp "$1" /tmp/schema.sql
  
  mysql -uroot -p"$DB_PASSWORD" < /tmp/schema.sql
    
else
  echo "Using existing database schema."
  echo "Unpacking schema from tar file..."

  # Unpack the existing schema from the tar file.
  tar xzf /tmp/default_mysql.tar.gz -C /var/lib/mysql/
fi

# Start the MySQL daemon in the foreground.
/usr/sbin/mysqld --bind-address=0.0.0.0 --init-file=/tmp/mysql-init.sql
