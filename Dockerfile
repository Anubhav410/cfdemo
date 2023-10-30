FROM maven:3.8.3-openjdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Use AdoptOpenJDK for the runtime image.
FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8111
WORKDIR /app
COPY --from=build /home/app/target/cfdemo-1.0-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["", "", ""]
