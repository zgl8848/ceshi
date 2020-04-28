FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER campus@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /campus-gateway

WORKDIR /campus-gateway

EXPOSE 9999

ADD ./campus-gateway/target/campus-gateway.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar campus-gateway.jar
