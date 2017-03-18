# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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

create table oc_product (
  product_id                    bigint auto_increment not null,
  sku                           varchar(255),
  model                         varchar(255),
  quantity                      integer,
  constraint pk_oc_product primary key (product_id)
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

drop table if exists company;

drop table if exists computer;

drop table if exists oc_product;

drop table if exists oc_customer;

