create database STRUCTURE_DB;
use STRUCTURE_DB;

create table ARCHITECTS(
	id int primary key auto_increment,
	name varchar(20) NOT null,
    korean_name varchar(20)
);

create table BUILDINGS(
	id int primary key auto_increment,
	name varchar(40) not null,
    korean_name varchar(40),
	coordinates point,
	completed_date date,
	architect_id int,
	foreign key(architect_id) references ARCHITECTS(id)
);

create table TAGS(
	id int primary key,
	name varchar(20) not null
);

create table BUILDINGS_AND_TAGS(
	id int primary key,
	building_id int not null,
	tag_id int not null,
	foreign key(building_id) references BUILDINGS(id)
		on delete cascade
        on update cascade,
	foreign key(tag_id) references TAGS(id)
		on delete cascade
        on update cascade
);