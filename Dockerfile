FROM openjdk:11

ARG XMX=500m
ARG PROFILE=production

ENV XMX=$XMX
ENV PROFILE=$PROFILE

VOLUME /tmp

EXPOSE 8015

ADD ./target/sistema-examenes-0.0.1-SNAPSHOT.jar sistema-examenes.jar

ENTRYPOINT java -Xmx$XMX -jar /sistema-examenes.jar --spring.profiles.active=$PROFILE