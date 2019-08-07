
# Comment
RUN echo 'Starting with Postgress'
FROM postgres:latest

RUN sh ./create_database.sh
RUN echo 'Finishing with Postgress'
# docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres
