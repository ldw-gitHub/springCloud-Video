-- 创建 schema
create database if not exists shop default charset utf8 collate utf8_general_ci;

use shop;

-- 创建用户表，表字段以后做扩充
create table if not exists userinfo (
    id int unsigned auto_increment,
    username varchar(30) not null,
    password varchar(30) not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

-- 插入测试数据
insert into userinfo (username,password) values ('ldw','123456');

select * from userinfo;

-- 创建视频信息表
create table if not exists videoinfo(
   id int unsigned auto_increment,
   title varchar(50) not null,
   description varchar(100),
   createuserid int(100),
   videoType varchar(10),
   imgpath varchar(50),
   videopath varchar(100),
   createtime date,
   updatetime date,
   primary key (id)
) engine=InnoDB default charset=utf8;

select * from videoinfo;

-- 增加某一列
alter table videoinfo add column videopath varchar(100);

-- 修改某一列的列名
alter table videoinfo change column imgname imgpath varchar(50);

-- 删除所有表中的数据，有事务
delete from videoinfo;

-- 直接删除表中的所有数据
truncate table videoinfo;