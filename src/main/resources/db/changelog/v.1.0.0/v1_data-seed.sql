insert into user_entity
values ('00be9459-5bac-4fcd-8654-0a93306c9159', 'user', '$2a$12$4s72CFT/tUDXAJJcsbQmyOF/SOzGzsY7qqfe3zmfdj309zTJI.NAm', 'ROLE_USER'),
       ('88dcd688-5234-423b-a1fa-bb4ee2402377', 'user1', '$2a$12$4s72CFT/tUDXAJJcsbQmyOF/SOzGzsY7qqfe3zmfdj309zTJI.NAm', 'ROLE_ADMIN');

insert into topic_entity
values ('9495aa27-4f72-44b3-85e8-2eccf2686126', 'test','2024-04-09T22:57:20.865');

insert into message_entity
values
    ('f28c628b-4866-4dce-b7be-df428b22140d', 'test1','2024-04-09T22:57:20.865','9495aa27-4f72-44b3-85e8-2eccf2686126', '00be9459-5bac-4fcd-8654-0a93306c9159'),
    ('6c93454a-5b9b-484b-91fe-3ac5528f2e1b', 'test2','2024-04-09T22:57:20.866','9495aa27-4f72-44b3-85e8-2eccf2686126', '00be9459-5bac-4fcd-8654-0a93306c9159'),
    ('ded190a1-5651-41ac-9fec-8b54d4c7cad7', 'test3','2024-04-09T22:57:20.867','9495aa27-4f72-44b3-85e8-2eccf2686126', '00be9459-5bac-4fcd-8654-0a93306c9159'),
    ('002c1b6d-0bcf-45e3-82d0-1f2553d585f8', 'test4','2024-04-09T22:57:20.865','9495aa27-4f72-44b3-85e8-2eccf2686126', '88dcd688-5234-423b-a1fa-bb4ee2402377'),
    ('9c48cc94-1e29-446f-871e-3bda87d77454', 'test5','2024-04-09T22:57:20.866','9495aa27-4f72-44b3-85e8-2eccf2686126', '88dcd688-5234-423b-a1fa-bb4ee2402377'),
    ('907fd357-7729-4130-9fdb-c770119b9f71', 'test6','2024-04-09T22:57:20.867','9495aa27-4f72-44b3-85e8-2eccf2686126', '88dcd688-5234-423b-a1fa-bb4ee2402377');