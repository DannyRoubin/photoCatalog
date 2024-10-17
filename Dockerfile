# Use the latest OpenJDK base image
FROM openjdk:23-jdk-oracle AS base

# Set the working directory inside the container
WORKDIR /app

# Expose the port your application will run on
EXPOSE 8080

FROM gradle:8.10.2-jdk23 AS build
WORKDIR /src
COPY . /src
RUN cd /src
RUN gradle clean build

FROM base AS final
WORKDIR /app
COPY --from=build /src/build/libs/*.jar /app


# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/photoCatalog-0.0.1-SNAPSHOT.jar"]


