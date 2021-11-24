FROM openjdk:17-slim AS builder
WORKDIR /tmp

COPY . .
RUN ./mvnw package -B -DskipTests

#re-create a clean docker image with only the executable.
FROM openjdk:17-slim
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
WORKDIR /app
COPY --from=builder /tmp/target/ingredients-0.0.1-SNAPSHOT.jar /app/app.jar
 
