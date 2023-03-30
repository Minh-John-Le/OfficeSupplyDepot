#!/bin/bash

# Initialize MySQL database.
# ADD this file into the container via Dockerfile.

# Make sure if any errors, exit immediately
set -e
# Make sure to show any errors that do occur in depth.
set -x

CONTAINER_NAME="office_supply_depot"
IMAGE_NAME="mysql:latest"
MYSQL_DATABASE="OfficeSupplyDepotDatabase"
DOCKER_VOLUME="$(pwd)/mysql_docker"
SCHEMA_FILE="$(pwd)/schema.sql"

DB_PROPERTIES_FILE=$(pwd)/OfficeSupplyDepotServlet/src/main/webapp/WEB-INF/classes/db.properties

# initialze the mysql database container. Runs in detatched mode, this way it runs in the background and can close terminal.
docker run --name $CONTAINER_NAME \
-e MYSQL_ROOT_PASSWORD=$DB_PASSWORD \
-e MYSQL_USER=$DB_USERNAME \
-e MYSQL_PASSWORD=$DB_PASSWO	RD \
-e MYSQL_DATABASE=$MYSQL_DATABASE \
-v $(pwd)/mysql_docker:/var/lib/mysql \
-d mysql:latest &

wait
# copy over the schema file to the container var/lib/mysql folder
docker cp $(pwd)/schema.sql
#docker cp $SCHEMA_FILE $CONTAINER_NAME:/var/lib/mysql/

docker exec office_supply_depot test -f /var/lib/mysql/schema.sql
if [ $? -eq 0 ]; then
    echo "File /var/lib/mysql/schema.sql exists in container office_supply_depot"
else
    echo "File /var/lib/mysql/schema.sql does not exist in container office_supply_depot"
fi



# Grant all privileges to username user from any host with the specified username and password
docker exec -d $CONTAINER_NAME mysql -uroot -p${DB_PASSWORD} -e "GRANT ALL ON *.* TO '${DB_USERNAME}'@'%' IDENTIFIED BY '${DB_PASSWORD}' WITH GRANT OPTION"

# Create the mysql database from the added schema file.
docker exec -i $CONTAINER_NAME mysql -uroot -p$MYSQL_ROOT_PASSWORD < /var/lib/mysql/schema.sql

