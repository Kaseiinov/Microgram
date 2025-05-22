insert into users(email, password, enabled, first_name, last_name)
values
    ('izlax6002@gmail.com', '$2a$10$2a.SIAPUpKTp5Cz6OA5VOOY.mtPQCzy2GaCYhf6lxchUmWSIWIl7m', true, 'Islam', 'Kaseiinov'), --password: qwe
    ('izlax1@gmail.com', '$2a$10$2a.SIAPUpKTp5Cz6OA5VOOY.mtPQCzy2GaCYhf6lxchUmWSIWIl7m', true, 'Sanjar', 'Kaseiinov'),--password: qwe
    ('izlax2@gmail.com', '$2a$10$2a.SIAPUpKTp5Cz6OA5VOOY.mtPQCzy2GaCYhf6lxchUmWSIWIl7m', true, 'Anvar', 'Kaseiinov'),--password: qwe
    ('izlax3@gmail.com', '$2a$10$2a.SIAPUpKTp5Cz6OA5VOOY.mtPQCzy2GaCYhf6lxchUmWSIWIl7m', true, 'Tynchtyk', 'Kaseiinov');--password: qwe


insert into authorities(authority)
values
    ('FULL'),
    ('READ_POSTS'),
    ('WRITE_POSTS'),
    ('READ_COMMENTS'),
    ('WRITE_COMMENTS'),
    ('EDIT_COMMENTS'),
    ('DELETE_COMMENTS');

insert into roles(role)
values
    ('ADMIN'),
    ('USER');

insert into roles_authorities(role_id, authority_id)
values
    ((select id from roles where role = 'ADMIN'), (select id from authorities where authority = 'FULL')),
    ((select id from roles where role = 'USER'), (select id from authorities where authority = 'READ_POSTS')),
    ((select id from roles where role = 'USER'), (select id from authorities where authority = 'WRITE_POSTS')),
    ((select id from roles where role = 'USER'), (select id from authorities where authority = 'READ_COMMENTS')),
    ((select id from roles where role = 'USER'), (select id from authorities where authority = 'WRITE_COMMENTS')),
    ((select id from roles where role = 'USER'), (select id from authorities where authority = 'EDIT_COMMENTS')),
    ((select id from roles where role = 'USER'), (select id from authorities where authority = 'DELETE_COMMENTS'));

insert into usr_roles(usr_id, role_id)
values
    ((select id from users where email = 'izlax6002@gmail.com'), (select id from roles where role = 'ADMIN')),
    ((select id from users where email = 'izlax1@gmail.com'), (select id from roles where role = 'USER')),
    ((select id from users where email = 'izlax2@gmail.com'), (select id from roles where role = 'USER')),
    ((select id from users where email = 'izlax3@gmail.com'), (select id from roles where role = 'USER'));


