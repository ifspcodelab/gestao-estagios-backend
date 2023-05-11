/* Campus */
INSERT INTO campuses VALUES ('f81663cf-6e3b-41c5-9138-3177c3411895', 'São Paulo', 'SPO', 'SP', '01109010', 'Pedro Vicente', 'Canindé', '9d7abb88-5ed6-4dec-af41-f06f976d3874', '625', 'Sem Complemento', '1127637520', 'social@ifsp.edu.br', 'https://spo.ifsp.edu.br/', 'ENABLED');
INSERT INTO campuses VALUES ('4f79f04f-1c6a-4855-b4c9-88d39a21f8ee', 'Campinas', 'CMP', 'CP', '13059581', 'Heitor Lacerda Guedes', 'Cidade Satélite Íris', 'da508625-1545-48b9-8bd6-ca583d0fddfa', '1000 ', 'Sem Complemento', '1137754501', 'campinas@ifsp.edu.br', 'https://portal.cmp.ifsp.edu.br/', 'ENABLED');

/* Departamento */
INSERT INTO departments VALUES ('f3eba3a2-157a-4703-9fd7-ec295c763b59', 'Informática e Turismo', 'DIT', 'ENABLED', 'f81663cf-6e3b-41c5-9138-3177c3411895');
INSERT INTO departments VALUES ('6d67af90-0e67-42a7-b0af-7c4c088901be', 'Construção Civil', 'DCC', 'ENABLED', 'f81663cf-6e3b-41c5-9138-3177c3411895');
INSERT INTO departments VALUES ('e5efdff3-0adc-43a5-8f66-d951196175cf', 'Ciências e Matemática', 'DCM', 'ENABLED', 'f81663cf-6e3b-41c5-9138-3177c3411895');
INSERT INTO departments VALUES ('d104ec78-af6f-4d94-9c23-b93de6cffb70', 'Elétrica', 'DEL', 'ENABLED', 'f81663cf-6e3b-41c5-9138-3177c3411895');
INSERT INTO departments VALUES ('f8810f1c-49a8-44ad-8371-0e485f07e712', 'Humanidades', 'DHU', 'ENABLED', 'f81663cf-6e3b-41c5-9138-3177c3411895');
INSERT INTO departments VALUES ('6486b9c8-f546-4dfc-89a3-3a2eeee289dd', 'Mecânica', 'DME', 'ENABLED', 'f81663cf-6e3b-41c5-9138-3177c3411895');

/* Curso */
INSERT INTO courses VALUES ('86ef07e5-ef05-4b9f-a1dc-7e3c502b78b0', 'Superior de Tecnologia em Gestão de Turismo', 'TUR', 6, 'ENABLED', 'f3eba3a2-157a-4703-9fd7-ec295c763b59');
INSERT INTO courses VALUES ('5e08889e-e14a-4158-927d-f849aa838ba3', 'Superior de Tecnologia em Análise e Desenvolvimento de Sistemas', 'TADS', 6, 'ENABLED', 'f3eba3a2-157a-4703-9fd7-ec295c763b59');
INSERT INTO courses VALUES ('2b496bcf-99db-4fa7-ab78-1f48f38ecd0f', 'Técnico em Desenvolvimento de Sistemas Integrado ao Ensino Médio', 'TCADS', 8, 'ENABLED', 'f3eba3a2-157a-4703-9fd7-ec295c763b59');
INSERT INTO courses VALUES ('116c2ce3-e082-49e7-b2c8-3d9c4cb73003', 'Técnico em Informática Integrado ao Ensino Médio', 'TI', 8, 'ENABLED', 'f3eba3a2-157a-4703-9fd7-ec295c763b59');

/* Matriz */
INSERT INTO curriculums VALUES ('32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f', '1433', 2370, 360, 'Obter aprovação em todas as disciplinas do primeiro período', 'Práticas voltadas à programação', 'ENABLED', '5e08889e-e14a-4158-927d-f849aa838ba3');
INSERT INTO curriculums VALUES ('30472217-b2eb-43dd-9d0c-1a2c536bb56d', '1434', 3837, 360, 'Obter aprovação em todas as disciplinas do primeiro período', 'Práticas voltadas à programação', 'ENABLED', '116c2ce3-e082-49e7-b2c8-3d9c4cb73003');
INSERT INTO curriculums VALUES ('3bcbabf1-d86b-4fab-b991-8b2e6b3041c8', '1435', 2400, 360, 'Obter aprovação em todas as disciplinas do primeiro período', 'Práticas voltadas à programação', 'ENABLED', '5e08889e-e14a-4158-927d-f849aa838ba3');


/* Usuários Estudantes */
INSERT INTO users VALUES ('e5f71e1c-82fb-44ae-a28f-a14abe6be0c1', 'SP1112223', 'Machado de Assis', '$2a$10$2R3uzCtleSuZT/li5dVeGOUY7u4WvlQ8SMYKNyIo1yx.YfM.HpYGa', 'machado.assis@aluno.ifsp.edu.br', 'ENABLED');
INSERT INTO users_roles VALUES ('e5f71e1c-82fb-44ae-a28f-a14abe6be0c1', 'ROLE_STUDENT');
INSERT INTO students VALUES ('2011364e-0b51-49ab-8729-b44580c9e68a', 'e5f71e1c-82fb-44ae-a28f-a14abe6be0c1', '32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f');


INSERT INTO users VALUES ('12cdfcf4-4f59-4575-91fe-85fb1eb400b2', 'SP2223334', 'Clarisse Lispector', '$2a$10$2R3uzCtleSuZT/li5dVeGOUY7u4WvlQ8SMYKNyIo1yx.YfM.HpYGa', 'clarisse.lispector@aluno.ifsp.edu.br', 'ENABLED');
INSERT INTO users_roles VALUES ('12cdfcf4-4f59-4575-91fe-85fb1eb400b2', 'ROLE_STUDENT');
INSERT INTO students VALUES ('700196f0-a727-4484-99aa-51baaf45f31f', '12cdfcf4-4f59-4575-91fe-85fb1eb400b2', '30472217-b2eb-43dd-9d0c-1a2c536bb56d');

INSERT INTO users VALUES ('53afbbd7-fb6b-4bc9-bf7e-006c9560289a', 'SP3334445', 'Guimarães Rosa', '$2a$10$2R3uzCtleSuZT/li5dVeGOUY7u4WvlQ8SMYKNyIo1yx.YfM.HpYGa', 'guimaraes.rosa@aluno.ifsp.edu.br', 'ENABLED');
INSERT INTO users_roles VALUES ('53afbbd7-fb6b-4bc9-bf7e-006c9560289a', 'ROLE_STUDENT');
INSERT INTO students VALUES ('1cf551d9-8652-4085-96fe-09cf1fbfe04d', '53afbbd7-fb6b-4bc9-bf7e-006c9560289a', '32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f');

/* Usuários Orientadores */
INSERT INTO users VALUES ('e1ecabc6-2ba8-4599-924f-62f1747df896', 'SP4445556', 'Xavier De Maistre', '$2a$10$2R3uzCtleSuZT/li5dVeGOUY7u4WvlQ8SMYKNyIo1yx.YfM.HpYGa', 'xavier.maistre@ifsp.edu.br', 'ENABLED');
INSERT INTO users_roles VALUES ('e1ecabc6-2ba8-4599-924f-62f1747df896', 'ROLE_ADVISOR');
INSERT INTO advisors VALUES ('df8abb04-9c8f-40a3-ae99-557e20215d07', 'e1ecabc6-2ba8-4599-924f-62f1747df896');
INSERT INTO advisors_courses VALUES ('df8abb04-9c8f-40a3-ae99-557e20215d07', '5e08889e-e14a-4158-927d-f849aa838ba3');
INSERT INTO advisors_courses VALUES ('df8abb04-9c8f-40a3-ae99-557e20215d07', '116c2ce3-e082-49e7-b2c8-3d9c4cb73003');

INSERT INTO users VALUES ('feb8f579-9f3f-4870-9d64-f2af3fe04fcd', 'SP5556667', 'William Shakespeare', '$2a$10$2R3uzCtleSuZT/li5dVeGOUY7u4WvlQ8SMYKNyIo1yx.YfM.HpYGa', 'william.shakespeare@ifsp.edu.br', 'ENABLED');
INSERT INTO users_roles VALUES ('feb8f579-9f3f-4870-9d64-f2af3fe04fcd', 'ROLE_ADVISOR');
INSERT INTO advisors VALUES ('534a68ca-7775-4b0c-93e9-8253671d24a0', 'feb8f579-9f3f-4870-9d64-f2af3fe04fcd');
INSERT INTO advisors_courses VALUES ('534a68ca-7775-4b0c-93e9-8253671d24a0', '5e08889e-e14a-4158-927d-f849aa838ba3');
INSERT INTO advisors_courses VALUES ('534a68ca-7775-4b0c-93e9-8253671d24a0', '116c2ce3-e082-49e7-b2c8-3d9c4cb73003');

INSERT INTO users VALUES ('e92e17c7-e7a8-431f-83fd-971390a79d68', 'SP7778889', 'Johann Von Goethe', '$2a$10$2R3uzCtleSuZT/li5dVeGOUY7u4WvlQ8SMYKNyIo1yx.YfM.HpYGa', 'johann.goethe@ifsp.edu.br@ifsp.edu.br', 'ENABLED');
INSERT INTO users_roles VALUES ('e92e17c7-e7a8-431f-83fd-971390a79d68', 'ROLE_ADVISOR');
INSERT INTO advisors VALUES ('ca9552f3-6db8-41d4-8b30-dde0b0a93953', 'e92e17c7-e7a8-431f-83fd-971390a79d68');
INSERT INTO advisors_courses VALUES ('ca9552f3-6db8-41d4-8b30-dde0b0a93953', '5e08889e-e14a-4158-927d-f849aa838ba3');
INSERT INTO advisors_courses VALUES ('ca9552f3-6db8-41d4-8b30-dde0b0a93953', '116c2ce3-e082-49e7-b2c8-3d9c4cb73003');

/* Pedidos de Orientação */
INSERT INTO advisor_requests VALUES ('f0421bd8-6672-4be5-9d60-7ba525315937', '2021-11-10 00:00:00.000000', '2021-11-15 00:00:00.000000', 'REQUIRED_OR_NOT', 'Programação', 'ACCEPTED', '2011364e-0b51-49ab-8729-b44580c9e68a', '32c4dcbe-9769-4cc5-a1fb-2bcc9d38e12f', 'df8abb04-9c8f-40a3-ae99-557e20215d07');

/* Avaliação de pedidos de orientação */
INSERT INTO advisor_request_appraisals VALUES ('8ad5225e-5348-41f0-96a0-3c3a4fb6b1d2', '2021-11-11 00:00:00.000000', 'Aprovado.', true, '2021-11-12 13:30:00.000000', 'f0421bd8-6672-4be5-9d60-7ba525315937');

/* Estágios */
INSERT INTO internships VALUES ('708af3e2-f02e-4f2e-b01b-0757a7ac5728', 'REQUIRED', 'IN_PROGRESS', 'f0421bd8-6672-4be5-9d60-7ba525315937');

/* Plano de Atividades */
INSERT INTO activity_plans VALUES ('524bda2e-41c2-446b-9261-230531e7c387', 'Super Sistemas', '2021-11-15 00:00:00.000000', '2022-10-15 00:00:00.000000', '2021-11-11 00:00:00.000000', '2021-11-20 00:00:00.000000', 'https://res.cloudinary.com/gestaoestagios/image/upload/v1638900497/Teste/00_Plano_de_atividades_Inicial_t62zd3.pdf', 'ACCEPTED', 'Tudo certo', '708af3e2-f02e-4f2e-b01b-0757a7ac5728');

/* Relatórios Mensais */
INSERT INTO monthly_reports VALUES ('e5216566-d290-41ac-8c21-c7017929666a', '2021-11-01', 'true', '2021-12-01', '2021-11-01', '2021-11-30', null, 80, 'https://res.cloudinary.com/gestaoestagios/image/upload/v1638900674/Teste/19_11_Relat%C3%B3rio_evkswa.pdf', 'FINAL_ACCEPTED', '524bda2e-41c2-446b-9261-230531e7c387', '708af3e2-f02e-4f2e-b01b-0757a7ac5728');
INSERT INTO monthly_reports VALUES ('9530a895-5431-4c81-9094-f6f67bee6a9c', '2021-12-01', 'true', '2022-01-01', '2021-12-01', '2021-12-30', null, 80, 'https://res.cloudinary.com/gestaoestagios/image/upload/v1638900674/Teste/20_01_Relat%C3%B3rio_edmjwc.pdf', 'FINAL_ACCEPTED', '524bda2e-41c2-446b-9261-230531e7c387', '708af3e2-f02e-4f2e-b01b-0757a7ac5728');
INSERT INTO monthly_reports VALUES ('5fefd71d-c095-43f5-9228-1b9bb5584004', '2022-01-01', 'true', '2022-02-01', '2022-01-01', '2022-01-30', null, 80, 'https://res.cloudinary.com/gestaoestagios/image/upload/v1638900675/Teste/20_02_Relat%C3%B3rio_dplynx.pdf', 'FINAL_ACCEPTED', '524bda2e-41c2-446b-9261-230531e7c387', '708af3e2-f02e-4f2e-b01b-0757a7ac5728');
INSERT INTO monthly_reports VALUES ('c2a9d632-50bd-4f90-ae52-6bb67720a153', '2022-02-01', 'true', '2022-03-01', '2022-02-01', '2022-02-28', null, 80, 'https://res.cloudinary.com/gestaoestagios/image/upload/v1638900674/Teste/20_03_Relat%C3%B3rio_wjxqj9.pdf', 'FINAL_ACCEPTED', '524bda2e-41c2-446b-9261-230531e7c387', '708af3e2-f02e-4f2e-b01b-0757a7ac5728');
INSERT INTO monthly_reports VALUES ('8fef81cb-1093-48e4-b000-3df6a2dc610f', '2022-03-01', 'true', '2022-04-01', '2022-03-01', '2022-03-30', null, 80, 'https://res.cloudinary.com/gestaoestagios/image/upload/v1638900675/Teste/20_04_Relat%C3%B3rio_xihk6c.pdf', 'FINAL_ACCEPTED', '524bda2e-41c2-446b-9261-230531e7c387', '708af3e2-f02e-4f2e-b01b-0757a7ac5728');
INSERT INTO monthly_reports VALUES ('79387e76-26d0-43cd-bef5-85534f09bba8', '2022-04-01', 'true', '2022-05-01', '2022-04-01', '2022-04-30', null, 80, 'https://res.cloudinary.com/gestaoestagios/image/upload/v1638900675/Teste/20_04_Relat%C3%B3rio_xihk6c.pdf', 'FINAL_ACCEPTED', '524bda2e-41c2-446b-9261-230531e7c387', '708af3e2-f02e-4f2e-b01b-0757a7ac5728');

/* Termos de Realização */
INSERT INTO realization_terms VALUES ('a8f388de-f8a7-4a22-ab99-9cd83a9a86d8', '2022-05-15 00:00:00.000000', '2021-11-15', '2022-10-15', 'https://res.cloudinary.com/gestaoestagios/image/upload/v1638900674/Teste/19_11_Relat%C3%B3rio_evkswa.pdf', 'ACCEPTED', 'Muito bom', '708af3e2-f02e-4f2e-b01b-0757a7ac5728');