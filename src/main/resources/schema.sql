DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS users CASCADE;

create table users (
    seq int8 not null,
    create_at DEFAULT CURRENT_TIMESTAMP() not null,
    email varchar(50) not null,
    last_login_at timestamp,
    login_count integer default 0 not null,
    name varchar(30) not null,
    passwd varchar(80) not null,
    primary key (seq),
);
alter table if exists users drop constraint if exists UK_6dotkott2kjsp8vw4d0m25fb7;
alter table if exists users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
