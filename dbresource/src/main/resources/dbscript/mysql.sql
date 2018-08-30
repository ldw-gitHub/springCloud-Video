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
   createtime TIMESTAMP,
   updatetime TIMESTAMP,
   primary key (id)
) engine=InnoDB default charset=utf8;

select * from videoinfo;

-- 增加某一列
alter table videoinfo add column videopath varchar(100);
alter table videoinfo add column isown varchar(1) DEFAULT '1' COMMENT;
-- 修改某一列的列名
alter table videoinfo change column imgname imgpath varchar(50);

-- 删除所有表中的数据，有事务
delete from videoinfo;

-- 直接删除表中的所有数据
truncate table videoinfo;



----------------------登入信息表---------------------
select @@GLOBAL.sql_mode;
set @@GLOBAL.sql_mode = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


create table if not exists usersession(
   sessiontoken varchar(50) not null,
   userid int(10),
   createtime TIMESTAMP,
   updatetime TIMESTAMP,
   remenberme int(1),
   isonline int(1),
   primary key (sessiontoken)
) engine=InnoDB default charset=utf8;



----------------------评论表--------------------------
create table if not exists videocomment(
   id int unsigned auto_increment,
   commentuserid int(10) not null,
   videoid int(10) not null,
   createuserid int(100),
   msg varchar(100),
   commentusername varchar(30),
   createtime TIMESTAMP,
   primary key (id)
) engine=InnoDB default charset=utf8;






