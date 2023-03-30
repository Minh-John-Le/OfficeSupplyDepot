# Use the docker image for MySQL in the docker container
FROM mysql

#Copy over the schema.sql file to the temporary docker directory(gets deleted once the docker is closed)
ADD schema.sql /tmp/

# Run a bash script to start up the docker container
RUN init_db
