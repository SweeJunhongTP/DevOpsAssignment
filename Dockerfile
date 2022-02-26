FROM openjdk:8
EXPOSE 8080
ADD	target/DevOpsProject.jar DevOpsProject.jar
ENTRYPONT ["java","-jar", "/DevOpsProject.jar"]
