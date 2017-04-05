# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  category_id                   bigint auto_increment not null,
  name                          varchar(255),
  status                        tinyint,
  sort_order                    bigint,
  parent_id                     bigint,
  constraint pk_category primary key (category_id)
);

create table company (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_company primary key (id)
);

create table computer (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  introduced                    datetime(6),
  discontinued                  datetime(6),
  company_id                    bigint,
  constraint pk_computer primary key (id)
);

create table product (
  product_id                    bigint auto_increment not null,
  name                          varchar(255),
  image                         varchar(255),
  status                        tinyint,
  date_available                datetime(6),
  date_added                    datetime(6),
  date_modified                 datetime(6),
  quantity                      integer,
  constraint pk_product primary key (product_id)
);

create table product_to_category (
  product_id                    bigint auto_increment not null,
  category_id                   integer,
  constraint pk_product_to_category primary key (product_id)
);

create table oc_customer (
  customer_id                   bigint auto_increment not null,
  email                         varchar(255),
  password                      varchar(255),
  constraint pk_oc_customer primary key (customer_id)
);

alter table computer add constraint fk_computer_company_id foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_computer_company_id on computer (company_id);


# --- !Downs

alter table computer drop foreign key fk_computer_company_id;
drop index ix_computer_company_id on computer;

drop table if exists category;

drop table if exists company;

drop table if exists computer;

drop table if exists product;

drop table if exists product_to_category;

drop table if exists oc_customer;

