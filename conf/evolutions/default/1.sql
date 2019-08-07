# --- Creation of events table

# --- !Ups
create table events (
  "id" SERIAL,
  "name" TEXT NOT NULL
);

# --- !Downs
drop table events;