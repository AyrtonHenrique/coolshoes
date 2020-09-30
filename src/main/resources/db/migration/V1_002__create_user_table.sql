create table tb_user(
    id bigint auto_increment not null,
    username varchar(20) not null,
    password varchar(20) not null,
    primary key (id)
)