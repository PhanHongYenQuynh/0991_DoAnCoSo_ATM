create database atmsimulatorsystem;
show databases;
use atmsimulatorsystem;
Drop table signup;
Drop table signuptwo;
Drop table signupthree;
Drop table login;
Drop table atm;
Drop table bank_account;

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
	cardnumber varbinary(1024), 
	pin varbinary(1024), 
	facility varchar(100)
);

create table login(
	formno varchar(20), 
	cardnumber varbinary(1024), 
	pin varbinary(1024),
	wrong_attempts INT DEFAULT 0,
	locked_until TIMESTAMP DEFAULT NULL
);


create table atm(
	pin varbinary(1024), 
	date varchar(50), 
	type varchar(20), 
	amount nvarchar(120)
);

CREATE TABLE bank_account (
    acc_no  varbinary(1024),
    pin varbinary(1024),
    balance INT
);



show tables;

select * from signup;

select * from signuptwo;

select * from signupthree;

select * from login;

select * from bank_account;

select * from atm;

ALTER TABLE login MODIFY COLUMN cardnumber varbinary(1024);
ALTER TABLE login MODIFY COLUMN pin varbinary(1024);

ALTER TABLE signupthree MODIFY COLUMN cardnumber varbinary(1024);
ALTER TABLE signupthree MODIFY COLUMN pin varbinary(1024);

ALTER TABLE bank_account MODIFY COLUMN acc_no varbinary(1024);
ALTER TABLE bank_account MODIFY COLUMN pin varbinary(1024);

ALTER TABLE atm MODIFY COLUMN pin varbinary(1024);
ALTER TABLE atm MODIFY COLUMN pin varchar(500);


ALTER TABLE login MODIFY COLUMN cardnumber varchar(500);
ALTER TABLE login MODIFY COLUMN pin varchar(500);

DELETE FROM login WHERE formno = 6477;
DELETE FROM signupthree WHERE formno = 6477;

SET SQL_SAFE_UPDATES = 1;

DELETE FROM login WHERE cardnumber = 'value1' 
AND pin = 'value2' LIMIT 1;








