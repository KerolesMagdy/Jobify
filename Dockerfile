FROM openjdk:17
#EXPOSE 8045:8080
ADD target/jobify.jar jobify.jar
ENTRYPOINT ["java","-jar","jobify.jar"]