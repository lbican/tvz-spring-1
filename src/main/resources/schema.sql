create table if not exists student (
    id identity(4,1),
    jmbag varchar(10) not null unique,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    ects_points numeric not null,
    date_of_birth date not null
);

create table if not exists course (
    id identity,
    name varchar(100) not null,
    ects_points numeric not null
);

create table if not exists student_course (
    id identity,
    student_id bigint,
    course_id bigint,
    constraint fk_student foreign key (student_id) references student(id),
    constraint fk_course foreign key (course_id) references course(id)
);

create table if not exists app_user (
    id identity,
    username varchar(100) not null,
    password varchar(100) not null,
    first_name varchar(100) not null,
    last_name varchar(100) not null
);

create table if not exists authority(
    id identity,
    name varchar(100)
);

create table if not exists user_authority (
    id identity,
    user_id bigint,
    authority_id bigint,
    constraint fk_user foreign key (user_id) references app_user(id),
    constraint fk_authority foreign key (authority_id) references authority(id)
);
