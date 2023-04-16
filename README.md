# OfficeSupplyDepot Group 10 CS 160 Spring 2023 Frank Butt

OfficeSupplyDepot is a dynamic web project built using Java Servlets, JSPs, and MySQL. This project allows customers to buy office supplies online.

## Authors

- Minh 
- Kaleigh 
- Nate 
- Nathan 
- Khush 
- David 

## Prerequisites

- Java SE 1.7
- Eclipse JEE
- Tomcat 8.0.53
- Docker 
- Github

## Getting Started

To get started, you need to have Docker and Docker Compose installed on your machine. Once you have them installed, clone the repository and navigate to the root directory of the project. 


### Setting Up Tomcat

1. Go to Window->Show View->Servers and click on it.
2. Right-click in the box and hover over New->Server and press it.
3. Press the arrow(>) dropdown to the left of Apache and press Tomcat v8.0 server.
4. Press add if Apache Tomcat is not already downloaded on your system and you can't put it in the Server Runtime Environment option.
5. Press Download and Install, accept the license, put it in the OfficeSupplyDepot folder, and specify the necessary Java SE. Then click next and press on OfficeSupplyDepot and try to add it to configured.
6. Once added, press Finish.
7. Right-click on OfficeSupplyDepot in the Project Explorer(View it by pressing Window->Show View->Project Explorer).
8. Press on Build Path->Configure Build Path.
9. Press on "Add Library..." at the right, press "Server Runtime", then press next, and then press on the server you created and then press next, then press finish. 
10. Press "Apply and Close" and see the errors in the Java files in beans, controller, dao, and utilities go away as well as the JSP files.


### Setting Up Environment Variables

The `setup.sh` bash script is used to set the variables of `docker-compose-setup.yml` file which becomes the  `docker-compose.yml` file 
based on what operating system you are using so it is not path specific. The `.env` file is used by `setup.sh` to set the variables in `docker-compose.yml`.

1. Run `chmod +x setup.sh` to be able to run the `setup.sh` script. 
2. Run `./setup.sh` to set up the environmental variables and set up `docker-compose.yml`.

### Creating Docker Container
3. Run `docker compose -f docker-compose.yml up` to create the docker container and start it up. 
4. Wait until you get the message `ready for connections. Version: '8.0.32'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server - GPL.` before you start using the Tomcat Server.


### Accessing the Website

To access the website locally, navigate to `http://localhost:8080/OfficeSupplyDepot/` in your web browser.

To access the website deployment, navigate to `https://officesupplydepot.org/MainPage.jsp`  in your web browser.

## Architecture

The project is built using Java Servlets, JSPs, and JDBC for the backend, and HTML, CSS, and JavaScript for the frontend. The MySQL database is used to store user and order data.

### Beans, Controller, and DAO

The `src/main/java/` directory contains the `beans`, `controller`, and `dao` packages. The `beans` package contains classes that represent objects that are used in the `controller` and `dao` packages. The `dao` package contains classes that handle sending updates to the object data in the MySQL database. The `controller` package handles incoming requests from the frontend and interacts with the `dao` package to retrieve or modify data in the MySQL database.

### JSPs and CSS

The `src/main/webapp` directory contains the JSP and CSS files that are used for the frontend of the website.

## Important Notes

- The Docker container uses the MySQL default port of `3306` and connects to the host machine's `3306` port, so if you have MySQL running, make sure it is either turned off or that any database using port `3306` is deleted or changed.
- There is a volume mapping `./mysql_database_init_files/:/docker-entrypoint-initdb.d/` that should not be changed under any circumstances. This allows the Docker container to be initialized with the `schema.sql` file that is in it.
- Any time the `schema.sql` file is changed, the `./mysql_database_init_files/` folder should have the `schema.sql` file replaced with the new one.
- The `setup.sh` file is used to set up the Docker container based on the variables in `.env` and create the `docker-compose.yml` file.
- The `setup.sh` file and Docker are only required on the development branch locally on your machine.
- The `docker-compose-setup.yml` file is necessary in case someone is using something other than Linux or macOS so that it can work for Windows computers running Git Bash or WSL.


## Acknowledgments

- [Registration Form using JSP + Servlet + JDBC + MySQL Database Example](https://www.youtube.com/watch?v=DzYyzmP4m5)
