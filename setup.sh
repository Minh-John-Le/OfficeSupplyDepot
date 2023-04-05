#!/bin/bash

# check if mysql_data directory exists in the current working directory
if [ -d "$(pwd)/mysql_data" ]; then
    # if the directory exists, delete all its contents
    echo "mysql_data directory exists, deleting contents..."
    rm -rf $(pwd)/mysql_data/*
else
    # if the directory doesn't exist, create it
    echo "mysql_data directory doesn't exist, creating it..."
    mkdir "$(pwd)/mysql_data"
fi

# imports environmental variables 
# Load variables from .env file
if [ -f .env ]; then
  set -o allexport
  source .env
  set +o allexport
fi

# Replace ${PWD} with the current directory in docker-compose.yml
cp docker-compose-setup.yml docker-compose.yml

envsubst < docker-compose.yml > docker-compose-updated.yml

mv docker-compose-updated.yml docker-compose.yml



