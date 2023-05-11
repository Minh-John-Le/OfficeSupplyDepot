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


envsubst < docker-compose.yml > compose-updated.yml

mv compose-updated.yml docker-compose.yml



