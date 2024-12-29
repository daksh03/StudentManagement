FROM openjdk:17

WORKDIR /app

COPY /target/student-0.0.1.jar app.jar

EXPOSE 6687

ENTRYPOINT [ "java","-jar","app.jar" ]