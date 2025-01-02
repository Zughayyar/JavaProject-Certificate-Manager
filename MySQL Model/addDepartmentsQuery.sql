USE `JavaProject_Cert_Manager`;
SHOW CREATE TABLE departments;
insert into departments (id,department_name,department_description) values (1,'Adminstration', 'Admin users can add and edit teachers and accountants');
insert into departments (id,department_name,department_description) values (2,'Teachers Department', 'Teachers as part of the academy to approve grades for students');
insert into departments (id,department_name,department_description) values (3,'Financial Department', 'Accountants as part of the Finacial to approve accounts for students');
SELECT * FROM JavaProject_Cert_Manager.departments;
