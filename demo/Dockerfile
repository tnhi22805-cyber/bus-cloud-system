# Bước 1: Build dự án bằng Maven
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Bước 2: Chạy ứng dụng bằng Java (Dùng bản eclipse-temurin thay cho openjdk-slim)
FROM eclipse-temurin:17-jdk
COPY --from=build /target/*.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]