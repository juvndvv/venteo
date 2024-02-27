FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY target/*.war app.war
ENTRYPOINT ["java", "-jar", "app.war"]
