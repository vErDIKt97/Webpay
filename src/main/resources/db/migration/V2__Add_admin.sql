insert into usr (id, username, password, active, name, surname)
    values (1,'admin','123',true,'test','test');

insert into user_role (user_id, roles)
    values (1,'USER'), (1, 'ADMIN');