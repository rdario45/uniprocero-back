# --- Creation of events table

# --- !Ups
CREATE TABLE public.events (
	id serial NOT NULL,
	name varchar(20) NOT NULL,
	type varchar(20) NULL,
	place varchar(50) NULL,
	address varchar(50) NULL,
	start_date timestamp NULL,
	finish_date timestamp NULL
);

# --- !Downs
drop table public.events;