/* Populate tables */
INSERT INTO teachers (dni, name, lastname, activo) VALUES(28564135, 'Agustin', 'Haye', 1);
INSERT INTO teachers (dni, name, lastname, activo) VALUES(15611399, 'Hugo', 'Vaque', 1);
INSERT INTO teachers (dni, name, lastname, activo) VALUES(17956895, 'Guillermo', 'Haye', 1);
INSERT INTO teachers (dni, name, lastname, activo) VALUES(17956232, 'Laura', 'Souto', 1);

INSERT INTO subjects (name, descripcion, horario, teacher_id, cup_alumn) VALUES('Lengua', 'lengua castellana', '09:30', 1, 1);
INSERT INTO subjects (name, descripcion, horario, teacher_id, cup_alumn) VALUES('Matemática', 'Ciencia que estudia las propiedades de los números y las relaciones que se establecen entre ellos. Te va a servir para toda la vida', '09:30', 2, 5);
INSERT INTO subjects (name, descripcion, horario, teacher_id, cup_alumn) VALUES('Geografia', 'Ciencia que estudia y describe la superficie de la Tierra en su aspecto físico, actual y natural, o como lugar habitado por la humanidad.', '10:00', 3, 5);
INSERT INTO subjects (name, descripcion, horario, teacher_id, cup_alumn) VALUES('Educación Física', 'Normalmente, no se hace nada.', '10:30', 4, 5);
INSERT INTO subjects (name, descripcion, horario, teacher_id, cup_alumn) VALUES('Logaritmos y estructura de Datos', 'Pseudo código', '11:00', 1, 0);
INSERT INTO subjects (name, descripcion, horario, teacher_id, cup_alumn) VALUES('Computación', 'The Best', '11:30', 2, 5);

INSERT INTO students (username, password, name, lastname) VALUES(37093729, '$2a$10$TJonI70sBcr86RoikcVvz.EqdAb88Ygg91enmqLYArIHd1vFHsfKC', 'David', 'Haye');
INSERT INTO students (username, password, name, lastname) VALUES(29790127, '$2a$10$PxePQOSSxqFSxxOvGmT3muboLHDD1yFI4giQyV2.291iPLYfiYDHW', 'Marina', 'Vaque');

INSERT INTO authorities (student_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (student_id, authority) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities (student_id, authority) VALUES (2, 'ROLE_USER');

INSERT INTO student_subject (student_id, subject_id) VALUES (1, 1);
INSERT INTO student_subject (student_id, subject_id) VALUES (1, 2);
INSERT INTO student_subject (student_id, subject_id) VALUES (1, 3);
INSERT INTO student_subject (student_id, subject_id) VALUES (1, 4);
INSERT INTO student_subject (student_id, subject_id) VALUES (2, 5);
INSERT INTO student_subject (student_id, subject_id) VALUES (1, 6);
INSERT INTO student_subject (student_id, subject_id) VALUES (2, 1);
INSERT INTO student_subject (student_id, subject_id) VALUES (2, 3);