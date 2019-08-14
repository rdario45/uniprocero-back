FROM openjdk:8
WORKDIR /myapp
COPY /backend-1.0 /myapp
RUN ./bin/backend