FROM mysql:8.0.29

ENV MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD}
ENV MYSQL_DATABASE=${MYSQL_DATABASE}
ENV MYSQL_USER=${MYSQL_USER}
ENV MYSQL_PASSWORD=${MYSQL_PASSWORD}

EXPOSE 3306

ADD set-slave.sh /docker-entrypoint-initdb.d/set-slave.sh
RUN chmod 755 /docker-entrypoint-initdb.d/set-slave.sh
