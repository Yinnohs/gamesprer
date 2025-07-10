# 1.- First User an official OPENJDK base image from docker hub
FROM  openjdk:17-jdk-alpine AS base
FROM base AS builder
# 2.- Set the working directory inside the container
WORKDIR /app
# 3.- Copy the spring boot jar file into the container
COPY target/gamesprer-[0-9].[0-9].[0-9].jar /app/gamesprer.jar
FROM base AS runner
# 4.- Expose the port
EXPOSE 5052

# 5.- Run the jar file
CMD ["java", "-jar", "/app/gamesprer.jar"]