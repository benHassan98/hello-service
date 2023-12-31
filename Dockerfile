FROM eclipse-temurin:17-jdk-alpine

COPY ./*.jar app.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]
