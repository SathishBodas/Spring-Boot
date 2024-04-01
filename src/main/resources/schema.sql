create table if not exists courses(
courseId int PRIMARY KEY AUTO_INCREMENT,
courseName varchar(255),
courseFee int
);

CREATE TABLE IF NOT EXISTS student(
id int PRIMARY KEY AUTO_INCREMENT,
studentname VARCHAR(255),
standard VARCHAR(255),
age int);

create table if not exists student_courses(
stu_id int,
courseid int,
PRIMARY KEY(stu_id,courseId),
FOREIGN KEY(stu_id) REFERENCES student(id),
FOREIGN KEY(courseid) REFERENCES courses(courseId)
);