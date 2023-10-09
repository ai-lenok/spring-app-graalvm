FROM registry.access.redhat.com/ubi9/openjdk-17-runtime
USER 185
WORKDIR /app
RUN mkdir /app/config
COPY --chown=185 target/*.jar /app/app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar", "/app/app.jar"]