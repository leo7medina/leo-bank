FROM openjdk:8-alpine

EXPOSE 8080

WORKDIR /usr/share/
COPY target/*.jar /usr/share/app.jar

RUN ls /usr/share/

ENTRYPOINT exec java -jar app.jar
#ENTRYPOINT exec java -Dspring.profiles.active=${SPRING_PROFILE} -Xms64m -Xmx256m -jar app.jar
#ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/app.jar"]