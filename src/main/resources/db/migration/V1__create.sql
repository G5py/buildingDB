create database if not exists BUILDING_DB;
use BUILDING_DB;

create table ARCHITECTS(
	id bigint primary key auto_increment,
	name varchar(20) NOT null unique ,
    korean_name varchar(20) unique
);

create table BUILDINGS(
	id bigint primary key auto_increment,
	name varchar(40) not null unique,
    korean_name varchar(40) unique,
	coordinates point,
	completed_date date,
	architect_id bigint,
	foreign key(architect_id) references ARCHITECTS(id)
);

create table TAGS(
	id bigint primary key,
	name varchar(20) not null unique
);

create table CATEGORIES(
	id bigint primary key,
	building_id bigint not null,
	tag_id bigint not null,
	foreign key(building_id) references BUILDINGS(id)
		on delete cascade
        on update cascade,
	foreign key(tag_id) references TAGS(id)
		on delete cascade
        on update cascade
);

delimiter $$

create trigger reject_insert_of_existing_category
    before insert on CATEGORIES
    for each row
    begin
        if exists (
            select 1 from CATEGORIES where
            building_id = NEW.building_id
            and tag_id = NEW.tag_id
        ) then
            signal sqlstate '45000'
            set message_text = 'Already existing category.';
        end if;
    end$$

delimiter ;