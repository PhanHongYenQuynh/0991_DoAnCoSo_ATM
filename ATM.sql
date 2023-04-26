create database atmsimulatorsystem;
show databases;
use atmsimulatorsystem;
Drop table signup;
Drop table signuptwo;
Drop table signupthree;
Drop table login;
Drop table atm;

create table signup(
	formno varchar(20), 
	name nvarchar(120), 
	phone char(20),
	father_name nvarchar(120), 
	mother_name nvarchar(120), 
	dob varchar(20), 
	gender varchar(20), 
	email varchar(30),
	marital_status nvarchar(20),
	address nvarchar(120),
	ward nvarchar(120),
	pincode varchar(20), 
	city nvarchar(120)
);

create table signuptwo(
	formno varchar(20), 
	religion varchar(80), 
	nationality varchar(100), 
	income varchar(100), 
	education varchar(20), 
	occupation varchar(30),
	tax nvarchar(20),
	cic nvarchar(120),
	datecic varchar(50),
	wherecic nvarchar(200),
	seniorcitizen nvarchar(120),
	exisitingaccount varchar(20)
);

create table signupthree(
	formno varchar(20), 
	accountType varchar(40), 
	cardnumber varchar(25), 
	pin varchar(10), 
	facility varchar(100
));

create table login(
	formno varchar(20), 
	cardnumber varchar(25), 
	pin varchar(10),
	wrong_attempt INT DEFAULT 0,
	locked_util TIMESTAMP DEFAULT NULL
);


create table atm(
	pin varchar(10), 
	date varchar(50), 
	type varchar(20), 
	amount nvarchar(120)
);

CREATE TABLE bank_account (
    acc_no  varchar(25),
    pin VARCHAR(10),
    balance INT
);



show tables;

select * from signup;

select * from signuptwo;

select * from signupthree;

select * from login;

select * from atm;

select * from bank_account

