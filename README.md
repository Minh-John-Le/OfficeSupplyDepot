# OfficeSupplyDepot
 
## Docker container use

The `setup.sh` file sets the environmental variables from the `.env` file.

The `schema.sql` file is inside the `mysql_database_init_files` folder which is 
mapped to the docker container folder `/docker-entrypoint-initdb.d/` which is where sql files get automatically executed when the mysql container is initialized.

### HOW TO:

- If you are using windows, too bad
  - type in `download-git.cmd` into the command prompt in this directory. It will open up the install once it is done downloading and continue with the install.
- First make the `setup.sh` file executable
  - `chmod +x setup.sh`
- Run the setup file
  - `./setup.sh`
- To build the docker container
  - `docker compose -f docker-compose.yml -d`

The database takes about 10-15 seconds to finish initialization. If you want to know when it is done, just do `docker logs osd`.
The name of the container is `osd`.
To see what docker containers are doing(running or stopped(exited)) do this
run: `docker ps -a`. 

- To stop the docker container:
  - `docker stop osd`
- To restart the docker container:
  - `docker start osd`
- To delete the docker container:
  - first make sure the container is stopped
  - Then: `docker rm osd`
- When you change schema.sql file in the OfficeSupplyDepot directory
  - delete the schema.sql file from mysql_database_init_files and then copy the new schema.sql file to the mysql_database_init_files
  - DO NOT change the name of schema.sql to anything else

TODO:
1. The mysql_data directory will be used later to store data as long as the schema.sql is the same as before. The mysql_database_init_files will check if the schema.sql file has changed and if so, recreate the database.
