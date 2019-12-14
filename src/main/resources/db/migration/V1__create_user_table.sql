create table github_user
(
    userId       bigint unsigned auto_increment not null primary key,
    userName     nvarchar(10)     not null,
    token        varchar(100)    not null,
    gmt_create   bigint unsigned not null,
    gmt_modified bigint unsigned not null
)engine = InnoDB,default charset = utf8;