create table tb_user(
    id bigint auto_increment not null,
    username varchar(200) not null,
    password varchar(200) not null,
    primary key (id)
)