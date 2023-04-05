# OfficeSupplyDepot
 
## Docker container use

The `setup.sh` file sets the environmental variables from the `.env` file.

The `schema.sql` file is inside the `mysql_database_init_files` folder which is 
mapped to the docker container folder `/docker-entrypoint-initdb.d/` which is where sql files get automatically executed when the mysql container is initialized.

### HOW TO:
-Make sure you have the most recent main branch updated from origin.

- If you are using windows, too bad https://www.youtube.com/watch?v=loDOloG8doY&ab_channel=TechHut
  - Open up the directory that holds the `OfficeSupplyDepot` folder.
  - Open up command prompt
  - Type in `wsl.exe --install` and press enter.
  - Restart the computer
  - Open up command prompt
  - Type in `wsl` and press enter.
  - The default will be ubuntu and it will ask you to set a unix username and password. Do so, just set it to the same as your `User` account on Windowsand use the same password. 
  - To get the directory of your drive open up a bew command prompt and type `wsl pwd` and press enter. 
  - That can then be used to change to the directory you want on the drive to run the next commands below.
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
- When you change `schema.sql` file in the `OfficeSupplyDepot` directory
  - delete the `schema.sql` file from mysql_database_init_files and then copy the new schema.sql file to the `mysql_database_init_files`
  - DO NOT change the name of `schema.sql` to anything else

TODO:
1. The `mysql_data` directory will be used later to store data as long as the schema.sql is the same as before. The mysql_database_init_files will check if the `schema.sql` file has changed and if so, recreate the database.
