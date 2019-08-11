# Event Backend
====
## Content
* Database
* Webserver

## Database
Postgress

docker run --name postgress-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=test -p 5432:5432 -d postgres
 
## WebServer and ProxyReverse
Nginx



docker exec -it nginx-ws bash

docker rm -f nginx-ws
