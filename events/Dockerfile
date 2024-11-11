FROM openjdk:21

COPY target/events-*.jar /app/musicando-events.jar

WORKDIR /app

EXPOSE 7777

CMD ["java", "-jar", "-Dspring.profiles.active=hml", "musicando-events.jar"]