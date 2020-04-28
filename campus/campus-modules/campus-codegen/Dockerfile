FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER campus@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /campus-codegen

WORKDIR /campus-codegen

EXPOSE 5003

ADD ./campus-modules/campus-codegen/target/campus-codegen.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar campus-codegen.jar
