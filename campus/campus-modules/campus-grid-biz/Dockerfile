FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER campus@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /campus-grid

WORKDIR /campus-grid

EXPOSE 4201

ADD ./campus-modules/campus-grid-biz/target/campus-grid-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar campus-grid-biz.jar
