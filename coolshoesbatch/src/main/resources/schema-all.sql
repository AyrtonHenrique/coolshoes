drop table if exists TB_USER;

create table TB_USER(
    id bigint auto_increment primary key,
    nome varchar(200),
    cpf varchar(14)
)