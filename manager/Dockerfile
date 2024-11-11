FROM openjdk:21

COPY target/manager-*.jar /app/musicando-manager.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=hml", "musicando-manager.jar"]