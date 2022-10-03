
/* creating accounts for teachers */
insert into account(name, role, email, password) values ("Min Lwin", "Teacher", "minlwin@gmail.com", "pass");
insert into account(name, role, email, password) values ("Sayar Kyaw", "Teacher", "kyaw@gmail.com", "pass");
insert into account(name, role, email, password) values ("Ko Pyae", "Teacher", "pyae@gmail.com", "pass");


/* creating accounts for students */
insert into account(name, role, email, password) values ("Maung Maung", "Student", "maung@gmail.com", "pass");
insert into account(name, role, email, password) values ("Aung Aung", "Student", "aung@gmail.com", "pass");
insert into account(name, role, email, password) values ("Thidar", "Student", "thidar@gmail.com", "pass");

/* creating teacher */
insert into teacher values(1, "09778571600", "2022-09-16");
insert into teacher values(2, "09787820722", "2022-09-17");
insert into teacher values(3, "0943207152", "2022-09-18");

/* creating student */
insert into student values(4, "09778551419", "Under Graduated");
insert into student values(5, "09778571600", "High School");
insert into student values(6, "09789885564", "Graduated");

/* creating classes with teacher */
insert  into classes(teacher_id, start_date, months, description) values(1, "2022-09-16", 3, "Java One Stop");
insert  into classes(teacher_id, start_date, months, description) values(1, "2022-09-17", 4, "Flutter CodeLab");
insert  into classes(teacher_id, start_date, months, description) values(2, "2022-09-18", 5, "Spring Framework");


/* creating registration with teachers and students */
insert into registration values (1, 4, "2022-09-20");
insert into registration values (1, 5, "2022-09-21");
insert into registration values (1, 6, "2022-09-22");
insert into registration values (2, 6, "2022-09-20");
insert into registration values (3, 4, "2022-09-20");