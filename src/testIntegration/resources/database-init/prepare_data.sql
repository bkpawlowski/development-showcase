insert into devshowcase.t_number(id, value) values(10, 1);
insert into devshowcase.t_number(id, value) values(11, 2);
insert into devshowcase.t_number(id, value) values(12, 3);
insert into devshowcase.t_number(id, value) values(13, 4);
insert into devshowcase.t_number(id, value) values(14, 5);
insert into devshowcase.t_number(id, value) values(15, 6);
insert into devshowcase.t_number(id, value) values(16, 7);
insert into devshowcase.t_number(id, value) values(17, 8);
insert into devshowcase.t_number(id, value) values(18, 9);
insert into devshowcase.t_number(id, value) values(19, 10);

--update is required as we provide identifiers manually and thus we bypass sequence usage
-- SELECT setval('devshowcase.t_number_id_seq', (SELECT MAX(id) FROM devshowcase.t_number));

insert into devshowcase.t_generated_number(id, value)
values
    (110, 2137),
    (111, 1),
    (112, 5),
    (113, 4),
    (114, 77644);

--update is required as we provide identifiers manually and thus we bypass sequence usage
-- SELECT setval('devshowcase.t_generated_number_id_seq', (SELECT MAX(id) FROM devshowcase.t_generated_number));

insert into devshowcase.t_generated_number_generation_time(epoch_time, type, generated_number_id)
values
    (227473976738349, 'STARTED_AT', 110),
    (227474104629420, 'FINISHED_AT', 110),
    (127891071, 'ELAPSED_TIME', 110),
    (227473976738349, 'STARTED_AT', 111),
    (227474104629420, 'FINISHED_AT', 111),
    (127891071, 'ELAPSED_TIME', 111),
    (227473976738349, 'STARTED_AT', 112),
    (227474104629420, 'FINISHED_AT', 112),
    (127891071, 'ELAPSED_TIME', 112),
    (227473976738349, 'STARTED_AT', 113),
    (227474104629420, 'FINISHED_AT', 113),
    (127891071, 'ELAPSED_TIME', 113),
    (227473976738349, 'STARTED_AT', 114),
    (227474104629420, 'FINISHED_AT', 114),
    (127891071, 'ELAPSED_TIME', 114);
