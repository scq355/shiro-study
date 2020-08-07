
create table t_perms
(
    id int auto_increment primary key,
    name varchar(32) null,
    url varchar(128) null
) comment '权限表';

create table t_role
(
    id int auto_increment primary key,
    name varchar(64) null
) comment '角色表';

create table t_role_perms
(
    id int auto_increment primary key,
    roleid int not null,
    permsid int not null
) comment '角色权限表';

create table t_user
(
    id int auto_increment primary key,
    username varchar(64) null,
    password varchar(128) null,
    salt varchar(256) null
) comment '用户表';

create table t_user_role

(
    id int auto_increment
        primary key,
    userid int not null,
    roleid int not null
) comment '用户角色表';







