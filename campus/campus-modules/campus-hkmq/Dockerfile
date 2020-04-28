FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER campus@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /campus-hkmq

WORKDIR /campus-hkmq

EXPOSE 9002

ADD ./campus-modules/campus-hkmq/target/campus-hkmq.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar campus-hkmq.jar
