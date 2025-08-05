FROM openjdk:11

ARG XMX=500m
ARG PROFILE=production

ENV XMX=$XMX
ENV PROFILE=$PROFILE

VOLUME /tmp

EXPOSE 8015

ADD ./target/sistema-examenes-backend-0.0.1-SNAPSHOT.jar sistema-examenes-backend.jar

ENTRYPOINT ["java", "-Xmx500m", "-jar", "/sistema-examenes-backend.jar", "--spring.profiles.active=production"]
