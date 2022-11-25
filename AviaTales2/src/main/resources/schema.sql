drop table if exists at_user;
drop table if exists ticket_cart;
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
    airline varchar(30),
    from_date date,
    to_date date,
    from_departure_time time,
    from_landing_time time,
    to_departure_time time,
    to_landing_time time,
    seat_place int,
    airport_name varchar(30),
    from_city_name varchar(30),
    to_city_name varchar(40),
    baggage bool
--     foreign key (airport_name) references airport(name),
--     foreign key (from_city_name) references city(name),
--     foreiab key (to_city_name) references city(name)
);

create table airport (
    name varchar(30) primary key,
    city varchar(40),
    country varchar(40),
    foreign key (city, country) references city(name, country_name)
);

create table city (
    name varchar(40),
    country_name varchar(40),
    foreign key (country_name) references country(name),
    constraint city_country primary key (name, country_name)
);

create table country (
    name varchar(40) primary key
);

create table ticket_cart (
    id bigserial primary key,
    username_id bigint,
    ticket_id bigint,
    foreign key(username_id) references at_user(id),
    foreign key (ticket_id) references ticket(id)
);

insert into at_user values (1, 'admin', 'admin@mail.ru', 'looool', null, true);

truncate table ticket;