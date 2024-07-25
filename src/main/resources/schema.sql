create table if not exists users(
    id integer primary key autoincrement,
    name varchar(255) not null,
    username varchar(255) not null unique,
    password varchar(255) not null
);

create table if not exists tasks(
    id integer primary key autoincrement,
    title varchar(255) not null
);