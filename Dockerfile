FROM openjdk:8
EXPOSE 8080
ADD	target/devopsproject.jar devopsproject.jar
ENTRYPONT ["java","-jar", "/devopsproject.jar"]
