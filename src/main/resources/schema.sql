create table brand (
    brand_entity_id bigint not null,
    name varchar(255),
    primary key (brand_entity_id));
create table product (
    product_entity_id bigint not null,
    name varchar(255),
    primary key (product_entity_id));
create table price (
    id bigint not null,
    currency tinyint check (currency between 0 and 0),
    end_date timestamp(6),
    price_list integer,
    priority smallint,
    start_date timestamp(6),
    "value" numeric(38,2),
    brand_product_id bigint,
    product_entity_id bigint,
    primary key (id));
create sequence brand_seq start with 1 increment by 50;
create sequence price_seq start with 1 increment by 1;
create sequence product_seq start with 1 increment by 50;

alter table if exists price add constraint FK2trd35jxfxje6ju9dioq5e2gi foreign key (brand_product_id) references brand;
alter table if exists price add constraint FKb7doix19k3k3tgs9auoa71fe3 foreign key (product_entity_id) references product;