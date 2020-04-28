FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER campus@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /campus-eureka

WORKDIR /campus-eureka

EXPOSE 8761

ADD ./campus-eureka/target/campus-eureka.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar campus-eureka.jar
