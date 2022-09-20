FROM openjdk:11
ADD target/duchv1_java_students-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]