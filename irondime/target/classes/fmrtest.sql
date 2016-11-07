
drop table users;
create table users ( id serial not null primary key,
  userid varchar(15),
  password varchar(50),
  name varchar(50),
  phone varchar(50),
  email varchar(50)
);
insert into users (userid,password,name,phone,email) values('johnd','***','John Doe','054-9250209','a@aa.com');
insert into users (userid,password,name,phone,email) values('bobs','***','Bob Smith','054-9250209','a@aa.com');
insert into users (userid,password,name,phone,email) values('bjones','***','Brian Jones','054-9250209','a@aa.com');  
