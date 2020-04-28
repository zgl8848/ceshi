FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER campus@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /campus-message

WORKDIR /campus-message

EXPOSE 9000

ADD ./campus-modules/campus-message/target/campus-message.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar campus-message.jar
