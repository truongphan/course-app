create sequence hibernate_sequence start 1 increment 1
create table course (id  bigserial not null, description varchar(255), name varchar(255), topic_id int8, primary key (id))
create table topic (id  bigserial not null, description varchar(255), name varchar(255), primary key (id))
create table user_login (id int8 not null, password varchar(255), username varchar(255) not null, primary key (id))
alter table user_login add constraint UK_i7tt0xma046ma8ohj9utt63gw unique (username)
alter table course add constraint FKokaxyfpv8p583w8yspapfb2ar foreign key (topic_id) references topic
