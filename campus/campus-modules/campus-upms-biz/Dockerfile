FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER campus@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /campus-upms

WORKDIR /campus-upms

EXPOSE 4000

ADD ./campus-modules/campus-upms-biz/target/campus-upms-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar campus-upms-biz.jar
