FROM openjdk:latest
ADD target/TheBookStore.jar TheBookStore.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "TheBookStore.jar"]