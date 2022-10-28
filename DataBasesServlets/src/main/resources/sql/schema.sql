drop table if exists product_to_store;
drop table if exists product;
drop table if exists store;

create table product(
    id bigserial primary key,
    name varchar(30)
);

create table store(
    id bigserial primary key,
    name varchar(30)
);

create table product_to_store(
    id bigserial primary key,
    product_id bigint,
    store_id bigint,
    amount int,
    foreign key (product_id) references product(id),
    foreign key (store_id) references store(id)
);


