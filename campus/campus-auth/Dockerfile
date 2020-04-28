FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER campus@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /campus-auth

WORKDIR /campus-auth

EXPOSE 3000

ADD ./campus-auth/target/campus-auth.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar campus-auth.jar
