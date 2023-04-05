FROM mysql:latest

LABEL maintainer="David Warshawsky <davidawarshawsky@gmail.com>"

# RUN chown -R mysql:root /var/lib/mysql/

ARG MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD

ENV MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD

WORKDIR /app

# COPY run_sql.sh /app/
# RUN chmod +x /app/run_sql.sh


# Copy over properties extract script
# COPY mysql_database_init_files/schema.sql /docker-entrypoint-initdb.d/

# CMD ["sh", "/app/run_sql.sh"]

EXPOSE $MYSQL_PORT