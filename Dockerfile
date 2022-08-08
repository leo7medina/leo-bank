FROM openjdk:8-alpine

EXPOSE 8080

WORKDIR /api
COPY target/*.jar /api/app.jar

RUN ls /api/

#RUN java version

#ENTRYPOINT exec java -jar app.jar
#ENTRYPOINT exec java -Dspring.profiles.active=${SPRING_PROFILE} -Xms64m -Xmx256m -jar app.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/api/app.jar"]