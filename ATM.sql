create database atmsimulatorsystem;
show databases;
use atmsimulatorsystem;

Drop table login;

create table signup(
formno varchar(20), 
name nvarchar(120), 
father_name nvarchar(120), 
mother_name nvarchar(120), 
dob varchar(20), 
gender varchar(20), 
email varchar(30),
marital_status nvarchar(20),
address nvarchar(120),
city nvarchar(120),
pincode varchar(20), 
state nvarchar(20));

create table signuptwo(
formno varchar(20), 
religion varchar(80), 
nationality varchar(100), 
income varchar(100), 
education varchar(20), 
occupation varchar(30),
pan nvarchar(20),
cic nvarchar(120),
seniorcitizen nvarchar(120),
exisitingaccount varchar(20));

create table signupthree(
formno varchar(20), 
accountType varchar(40), 
cardnumber varchar(25), 
pin varchar(10), 
facility varchar(100));

create table login(
formno varchar(20), 
cardnumber varchar(25), 
pin varchar(10));

show tables;

select * from signup;

select * from signuptwo;

select * from signupthree;

select * from login;




