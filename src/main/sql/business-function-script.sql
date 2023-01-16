create function get_id_user_by_email(user_email VARCHAR(100))
    RETURNS INT reads sql data
begin
return (select id_user from `user` where email = user_email);
end

create function get_id_planet_by_name(planet_name VARCHAR(45))
    RETURNS INT reads sql data
begin
return (select id_planet from `planet` where `name` = planet_name);
end

create function get_id_user_status_by_name(user_status_name VARCHAR(45))
    RETURNS INT reads sql data
begin
return (select id_user_status from `user_status` where `name` = user_status_name);
end

create function get_id_order_status_by_name(order_status_name VARCHAR(45))
    RETURNS INT reads sql data
begin
return (select id_order_status from `order_status` where `name` = order_status_name);
end


create function get_id_trip_status_by_name(trip_status_name VARCHAR(45))
    RETURNS INT reads sql data
begin
return (select id_trip_status from `trip_status` where `name` = trip_status_name);
end


create function get_trip_status_by_id(id int)
    RETURNS VARCHAR(45) reads sql data
begin
return (select `name` from `trip_status` where id_trip_status = id);
end

create function get_id_role_by_name(role_name VARCHAR(45))
    RETURNS INT reads sql data
begin
return (select id_role from `role` where `name` = role_name);
end


create function get_user_status_by_id(id int)
    RETURNS VARCHAR(45) reads sql data
begin
return (select `name` from `user_status` where id_user_status = id);
end


create function get_order_status_by_id(id int)
    RETURNS VARCHAR(45) reads sql data
begin
return (select `name` from `order_status` where id_order_status = id);
end


create function get_planet_by_id(id int)
    RETURNS VARCHAR(45) reads sql data
begin
return (select `name` from `planet` where id_planet = id);
end


create function get_role_by_id(id int)
    RETURNS VARCHAR(45) reads sql data
begin
return (select `name` from `role` where id_role = id);
end




create procedure add_trip(in trip_started timestamp,
                          in trip_price float,
                          in count_places int,
                          in operator_email varchar(100),
                          in planet_name varchar(45),
                          in trip_status varchar(45))
begin
insert into `trip`(created, started, price, count_vacancies, fk_operator, fk_planet, fk_trip_status)
    value (NOW(), trip_started, trip_price, count_places, get_id_user_by_email(operator_email),
    get_id_planet_by_name(planet_name), get_id_trip_status_by_name(trip_status));
end


create procedure add_order(in seats int,
                           in payment float,
                           in trip_id int,
                           in user_email varchar(100))
begin
insert into `order`(ordered_seats, payment_amount, fk_trip, fk_order_status, fk_user)
    value (seats, payment, trip_id, get_id_order_status_by_name("ORDERED"), get_id_user_by_email(user_email));
end


create procedure registration(in lastname varchar(45),
                              in firstname varchar(45),
                              in email varchar(100),
                              in phone varchar(13),
                              in user_password varchar(256),
                              in role_name varchar(45))
begin
insert into `user`(lastname, firstname, email, phone, `password`, fk_role, fk_user_status)
    value (lastname, firstname, email, phone, user_password,
    get_id_role_by_name(role_name), get_id_user_status_by_name("ACTIVE"));
end


create procedure set_order_status(in id int, in status_name varchar(45))
begin
update `order` set fk_order_status = get_id_order_status_by_name(status_name) where id_order = id;
end


create procedure set_trip_status(in id int, in status_name varchar(45))
begin
update `trip` set fk_trip_status = get_id_trip_status_by_name(status_name) where id_trip = id;
end


create procedure set_user_status(in user_email varchar(100), in status_name varchar(45))
begin
update `user` set fk_user_status = get_id_user_status_by_name(status_name) where email = user_email;
end


create procedure get_operator_trip_history(in operator_email varchar(100))
begin
select `trip`.id_trip, `trip`.started, `planet`.`name` as planet_name, trip_status.`name` as trip_status from trip
        inner join planet on `trip`.fk_planet = `planet`.id_planet
        inner join trip_status on `trip`.fk_trip_status = `trip_status`.id_trip_status
where fk_operator = get_id_user_by_email(operator_email);
end


create view user_view as
select `user`.lastname, `user`.firstname, `user`.email,
       `user`.phone, `user`.`password`, user_status.`name` as user_status_name, `role`.`name` as role_name from `user`
        inner join `role` on `user`.fk_role = `role`.id_role
        inner join `user_status` on `user`.fk_user_status = `user_status`.id_user_status;



create view order_view as
select
    `order`.ordered_seats, `order`.payment_amount, `order`.processed, `order_status`.`name` as 'order_status',
        `trip`.id_trip, `trip`.started, `planet`.`name` as planet_name, `trip_status`.`name` as 'trip_status',
        `client`.lastname as client_lastname, `client`.firstname as client_firstname, `client`.email as client_email,
        `client`.phone as client_phone
from `order`
         inner join `user` as `client` on `client`.id_user = `order`.fk_user
         inner join `order_status` on `order_status`.id_order_status = `order`.fk_order_status
         inner join `trip` on `trip`.id_trip = `order`.fk_trip
         inner join `planet` on `planet`.id_planet = `trip`.fk_planet
         inner join `trip_status` on `trip_status`.id_trip_status = `trip`.fk_trip_status;

create view trip_view as
select
    `trip`.id_trip, `trip`.started, `planet`.`name` as planet_name,
    `trip`.created, `trip`.price, `trip`.count_vacancies, `trip_status`.`name` as `trip_status`,
    `operator`.lastname as operator_lastname, `operator`.firstname as operator_firstname,
    `operator`.email as operator_email, `operator`.phone as operator_phone
from `trip`
         inner join `planet` on `planet`.id_planet = `trip`.fk_planet
         inner join `trip_status` on `trip_status`.id_trip_status = `trip`.fk_trip_status
         inner join `user` as operator on `operator`.id_user = `trip`.fk_operator;

CREATE TRIGGER order_controller_after_delete
    AFTER DELETE ON `order`
    FOR EACH ROW
BEGIN
    update trip set count_vacancies = count_vacancies + old.ordered_seats where id_trip = old.fk_trip;
END;

CREATE TRIGGER order_controller_before_insert
    BEFORE INSERT ON `order`
    FOR EACH ROW
BEGIN
    DECLARE error_message TEXT;
    DECLARE current_seats INT;
	set current_seats = (select count_vacancies from `trip` where id_trip = new.fk_trip);

    if new.ordered_seats > current_seats then
		SET error_message = CONCAT("Doesn't enough seats on trip");
		SIGNAL SQLSTATE '45000' SET MYSQL_ERRNO = 30001, MESSAGE_TEXT = error_message;
    else
    update trip set count_vacancies = current_seats - new.ordered_seats where id_trip = new.fk_trip;
end if;
END;

CREATE TRIGGER order_controller_before_update
    BEFORE UPDATE ON `order`
    FOR EACH ROW
BEGIN
    DECLARE current_seats INT;
	set current_seats = (select count_vacancies from `trip` where id_trip = new.fk_trip);

    if (new.fk_order_status = (select id_order_status from order_status where `name` = 'DENIED')) then
    update trip set count_vacancies = current_seats + old.ordered_seats where id_trip = old.fk_trip;
end if;
END;
