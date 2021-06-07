create database events;

use events;

create table user(
    user_id int auto_increment primary key unique
    ,username varchar(50) unique
    ,password varchar(50)
);


drop  table if exists event;
create table event(
    event_id int auto_increment primary key unique
    ,title varchar(100)
    ,description text
    ,category varchar(20)
    ,begin_date timestamp
    ,end_date timestamp
    ,price float
    ,event_image_id int
    ,foreign key (event_image_id) references event_image(event_image_id)
);

drop table if exists event_limit;
create table event_limit(
    event_id int unique
    ,attendance_limit int
    ,foreign key (event_id) references event(event_id)
);

drop table if exists attendance;
create table attendance(
    user_id int
    ,event_id int
    ,foreign key (user_id) references user(user_id)
    ,foreign key (event_id) references event(event_id)
);

create table event_image(
    event_image_id int auto_increment unique,
    `name` varchar(100),
    type varchar(100),
    size double,
    content mediumblob,
    primary key(event_image_id)
)