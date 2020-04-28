FROM mysql:5.7

MAINTAINER campus(campus@gmail.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./doc/db/1schema.sql /docker-entrypoint-initdb.d

COPY ./doc/db/2hhsep.sql /docker-entrypoint-initdb.d