FROM eclipse-temurin:17.0.4.1_1-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "application.jar"]