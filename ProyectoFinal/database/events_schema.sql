create database events;

use events;

drop table if exists user;
create table user(
    user_id int auto_increment primary key unique
    ,username varchar(50) unique
    ,is_admin boolean
    ,password varchar(224)
);

# user stored procedures

drop procedure create_user;
delimiter //
create  procedure create_user(username_in varchar(50),is_admin_in boolean,password_in varchar(224))
begin
    insert into user(username,is_admin, password) values(username_in,is_admin_in,sha2(password_in,224));
    select last_insert_id();
end //
delimiter ;

delimiter //
create procedure delete_user(user_id_in int)
begin
    delete
        from user
        where user_id = user_id_in;
end //
delimiter ;
drop procedure if exists edit_user;
delimiter //
create procedure edit_user(user_id_in int,username_in varchar(50),is_admin_in boolean,password_in varchar(50))
begin
    update user
        set
            username = username_in
            ,is_admin = is_admin_in
            ,password = sha2(password_in,224)
        where
            user_id = user_id_in;
end //
delimiter ;
delimiter //
create procedure get_all_users()
begin
    select * from user;
end //
delimiter ;

# user stored procedures testing

call create_user('Mike',1,'Hello');
call create_user('Jesús',1,'Bye');
call create_user('Fulano',0,'Password');

call get_all_users();

call edit_user(3,'Mike2',0,'Yes');

call delete_user(5);

drop  table if exists event;
create table event(
    event_id int auto_increment primary key unique
    ,title varchar(100)
    ,description text
    ,category varchar(20)
    ,begin_date timestamp
    ,end_date timestamp
    ,price float
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


drop table if exists event_image;
create table event_image(
    event_id int unique,
    `name` varchar(100),
    type varchar(100),
    size double,
    content mediumblob,
    foreign key (event_id) references event(event_id)
);

drop procedure  if exists create_event;
delimiter //
create procedure create_event(
    title_in varchar(100)
    ,description_in text
    ,category_in varchar(20)
    ,begin_date_in timestamp
    ,end_date_in timestamp
    ,price_in float
)
begin
    insert into event(
        title
        ,description
        ,category
        ,begin_date
        ,end_date
        ,price
    )
    values(
        title_in
        ,description_in
        ,category_in
        ,begin_date_in
        ,end_date_in
        ,price_in
    );
    select last_insert_id();
end //
delimiter ;

drop procedure if exists create_event_image;
delimiter //
create procedure create_event_image(
    event_id_in int
    ,image_name_in varchar(100)
    ,image_type_in varchar(100)
    ,image_size_in double
    ,image_content_in mediumblob
)
begin
    insert into event_image(
        event_id
        ,name
        ,type
        ,size
        ,content
    ) values(
        event_id_in
        ,image_name_in
        ,image_type_in
        ,image_size_in
        ,image_content_in
    );
end //
delimiter ;
delimiter //
drop procedure if exists create_event_attendance_limit;
create procedure create_event_attendance_limit(
    event_id_in int
    ,attendance_limit_in int
)
begin
    insert into event_limit(
        event_id
        ,attendance_limit)
    values (
        event_id_in
        ,attendance_limit_in
    );
end //
delimiter ;

#test create_event_with_image stored procedured

call create_event('title','description','category',timestamp ('2021-06-06 00:00:00'),timestamp('2021-06-06 00:00:00'),100);
call create_event_image(2,'name','type',32,x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082');
call create_event_attendance_limit(2,3);

#stored procedures of attendance

delimiter //
create procedure attendance_change(user_id_in int,event_id_in int,register boolean)
begin
    if register
    then
        insert into
            attendance(
                    user_id
                    ,event_id
            )
            values(
                   user_id_in
                   ,event_id_in
            );
    else
        delete
            from attendance
            where
                  user_id = user_id_in
                  and event_id = event_id_in
        ;
    end if;
end //
delimiter ;

call attendance_change(3,1,1);
call attendance_change(3,1,0);
/*drop procedure if exists create_event_with_image_and_attendance_limit;

delimiter //
create procedure create_event_with_image_and_attendance_limit(
    title_in varchar(100)
    ,description_in text
    ,category_in varchar(20)
    ,begin_date_in timestamp
    ,end_date_in timestamp
    ,price_in float
    ,attendance_limit_in int
    ,image_name_in varchar(100)
    ,image_type_in varchar(100)
    ,image_size_in double
    ,image_content_in mediumblob
)
begin
    start transaction;
    insert into
        event_limit(event_id, attendance_limit) values(
            (create_event_with_image(
                    title_in
                    ,description_in
                    ,category_in
                    ,begin_date_in
                    ,end_date_in
                    ,price_in
                    ,image_name_in
                    ,image_type_in
                    ,image_size_in
                    ,image_content_in)
            )
            ,attendance_limit_in);

    commit ;
end //
delimiter ;*/