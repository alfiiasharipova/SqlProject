FROM openjdk:8-slim
WORKDIR /opt/app
COPY . .
CMD ["java", "-jar", "app-deadline.jar"]
EXPOSE 9999