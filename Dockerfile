FROM eclipse-temurin:25-jre
LABEL authors="robin"
LABEL service="teggr/articluate:${project.version}"

COPY target/articluate-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]