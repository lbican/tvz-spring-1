insert into student (first_name, last_name, jmbag, ects_points, date_of_birth)
values ('Luka', 'Bićan', '0246096016', 154, NOW());
insert into student (first_name, last_name, jmbag, ects_points, date_of_birth)
values ('Petar', 'Bićan', '0246053435', 120, NOW());
insert into student (first_name, last_name, jmbag, ects_points, date_of_birth)
values ('Ivo', 'Ivić', '0246053232', 120, NOW());

insert into course (name, ects_points)
values ('Web aplikacije u Javi', 6);
insert into course (name, ects_points)
values ('Programiranje u jeziku Java', 5);

insert into QUESTION(QUESTION_TEXT) values ( 'Koliko je 2+2?' );
insert into ANSWER(ANSWER_TEXT, IS_CORRECT, QUESTION_ID) VALUES ( '1', false, 1 );
insert into ANSWER(ANSWER_TEXT, IS_CORRECT, QUESTION_ID) VALUES ( '2', false, 1 );
insert into ANSWER(ANSWER_TEXT, IS_CORRECT, QUESTION_ID) VALUES ( '3', false, 1 );
insert into ANSWER(ANSWER_TEXT, IS_CORRECT, QUESTION_ID) VALUES ( '4', true , 1 );

insert into student_course (student_id, course_id)
values (1, 1);
insert into student_course (student_id, course_id)
values (2, 1);
insert into student_course (student_id, course_id)
values (2, 2);

insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_USER');

insert into APP_USER (username, password, first_name, last_name) values ('admin', '$2a$12$bJ303jrOdjoTDdFN4MIJle6G34EK11QD9ze1xemTIR4hF0jjhF9Xm', 'Admin', 'User');
insert into APP_USER (username, password, first_name, last_name) values ('user', '$2a$12$AtNrCLC5HtMTvTyBoMLl1O7J2NPfMvo6BjaHvpmrodsfPnlK1iB/e', 'Regular', 'User');

insert into user_authority (user_id, authority_id) values (1, 1);
insert into user_authority (user_id, authority_id) values (2, 2);

