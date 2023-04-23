create table if not exists student (
    id identity,
    jmbag varchar(10) not null unique,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    ects_points number not null,
    date_of_birth date not null
);
