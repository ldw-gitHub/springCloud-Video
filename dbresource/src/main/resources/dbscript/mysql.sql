-- 创建 schema
create database if not exists shop default charset utf8 collate utf8_general_ci;

use shop;

-- 用户表，表字段以后做扩充
create table if not exists userinfo (
    id int unsigned auto_increment,
    username varchar(30) not null,
    password varchar(30) not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

-- 插入测试数据
insert into userinfo (username,password) values ('ldw','123456');

select * from userinfo;

------------------ 视频信息表---------------------------
create table if not exists videoinfo(
   id int unsigned auto_increment,
   title varchar(50) not null,
   description varchar(100),
   createuserid int(100),
   click int(255),
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
alter table videoinfo add column isown varchar(1) DEFAULT '1';
alter table videoinfo add column click int(255) DEFAULT 0;
-- 修改某一列的列名
alter table videoinfo change column imgname imgpath varchar(50);
alter table videoinfo modify column imgpath varchar(100) ;

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
   msg varchar(100),
   commentusername varchar(30),
   createtime TIMESTAMP,
   primary key (id)
) engine=InnoDB default charset=utf8;


--------------------------日志表-------------------------
create table if not exists loginfo(
   id int unsigned auto_increment,
   oprateModule int(8) not null,
   oprateuserid int(8),
   createtime TIMESTAMP,
   primary key (id)
)engine=InnoDB default charset=utf8;


--------------------------- 文件信息表---------------------
create table if not exists fileinfo(
   id int unsigned auto_increment,
   filename varchar(50) not null,
   filesize decimal(30),
   createuserid int(100),
   download int(1) DEFAULT 1,
   downloadNumber int(100) DEFAULT 0,
   filepath varchar(100),
   createtime TIMESTAMP,
   updatetime TIMESTAMP,
   primary key (id)
) engine=InnoDB default charset=utf8;

























