FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER campus@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /campus-config

WORKDIR /campus-config

EXPOSE 8888

ADD ./campus-config/target/campus-config.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar campus-config.jar
