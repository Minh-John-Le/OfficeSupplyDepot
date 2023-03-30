#!/bin/bash
docker stop office_supply_depot
docker rm office_supply_depot

echo $(pwd)
DB_PROPERTIES_FILE=$(pwd)/OfficeSupplyDepotServlet/src/main/webapp/WEB-INF/classes/db.properties

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


CONTAINER_NAME="office_supply_depot"
IMAGE_NAME="mysql:latest"
MYSQL_DATABASE="OfficeSupplyDepotDatabase"
DOCKER_VOLUME="$(pwd)/mysql_docker"
SCHEMA_FILE="$(pwd)/schema.sql"

MYSQL_USER=$DB_USERNAME
MYSQL_PASSWORD=$DB_PASSWORD
MYSQL_ROOT_PASSWORD=$DB_PASSWORD

export CONTAINER_NAME
export IMAGE_NAME
export MYSQL_DATABASE
export DOCKER_VOLUME
export SCHEMA_FILE

export MYSQL_USER
export MYSQL_PASSWORD
export MYSQL_ROOT_PASSWORD



# Check if the container exists
if [ $(docker ps -q -f name=$CONTAINER_NAME) ]; then
    echo "The container $CONTAINER_NAME exists."
    # Check if the container is not running
	if [ -z "$(docker ps -q -f name=$CONTAINER_NAME)"]; then
		docker start $CONTAINER_NAME
		echo "Started the docker container $CONTAINER_NAME."
	fi
	# Check if the schema file has changed.
    if ! docker exec office_supply_depot mysqldiff /var/lib/mysql/schema.sql $(pwd)/schema.sql; then
		docker exec -i $CONTAINER_NAME mysql -uroot -p$DB_PASSWORD -e "SHOW DATABASES;" | grep -v Database | grep -v mysql | grep -v information_schema | grep -v performance_schema | xargs -I {} echo "DROP DATABASE IF EXISTS {};" | docker exec -i $CONTAINER_NAME mysql -uroot -p$DB_PASSWORD
	else
		docker start $CONTAINER_NAME
	fi
else
	echo "The container $CONTAINER_NAME does not exist."
    #Initialize the MySQL database container
    ./init_db.sh
fi



