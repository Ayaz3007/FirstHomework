drop table if exists at_user;
drop table flight_history;
drop table if exists ticket;
drop table if exists airport;
drop table if exists city;
drop table if exists country;
drop type if exists access_level;


create table at_user (
    id bigserial primary key,
    username varchar(30),
    email varchar(30),
    password varchar(1000),
    phone varchar(13),
    is_admin bool default false
);

create table ticket (
    id bigserial primary key,
    price int,
    from_date date,
    to_date date,
    seat_place int,
    from_airport_id bigint,
    to_airport_id bigint,
    from_time time,
    to_time time,
    from_plane bigint,
    to_plane bigint,
    from_city varchar(40),
    to_city varchar(40),
    foreign key (from_airport_id, from_time) references flight_times(airport_id, flight_time),
    foreign key (to_airport_id, to_time) references flight_times(airport_id, flight_time),
    foreign key (from_airport_id, from_plane) references plane_to_airport(airport_id, plane_id),
    foreign key (to_airport_id, to_plane) references plane_to_airport(airport_id, plane_id),
    foreign key (from_airport_id, from_city) references airport(id, city),
    foreign key (to_airport_id, to_city) references airport(id, city)
);

create table airport (
    id bigserial primary key,
    name varchar(30),
    city varchar(40),
    foreign key (city) references city(name),
    constraint cities unique (id, city)
);

create table city (
    name varchar(40) primary key
);

drop table if exists flight_times;
create table flight_times (
    id bigserial primary key,
    airport_id bigint,
    flight_time time,
    foreign key (airport_id) references airport(id),
    constraint time unique (airport_id, flight_time)
);

create table plane (
    id bigserial primary key,
    name varchar(40),
    place_amount int
);
drop table if exists plane_to_airport;
create table plane_to_airport (
    id bigserial primary key,
    airport_id bigint,
    plane_id bigint,
    foreign key (airport_id) references airport(id),
    foreign key (plane_id) references plane(id),
    constraint planes unique (airport_id, plane_id)
);

create table flight_history (
    id bigserial primary key,
    username_id bigint,
    ticket_id bigint,
    foreign key(username_id) references at_user(id),
    foreign key (ticket_id) references ticket(id)
);

