FROM phusion/baseimage 
WORKDIR /opt/eventos
COPY ./eventos-1.0 /opt/eventos
RUN ls