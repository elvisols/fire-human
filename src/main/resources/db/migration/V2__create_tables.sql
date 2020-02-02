CREATE SEQUENCE human_db.s_person_id START WITH 1;

CREATE TABLE human_db.persons (
  id INT AUTO_INCREMENT NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR (50) NOT NULL,
  password CHAR (60) NOT NULL,
  age INT NOT NULL,
  color VARCHAR(50) check(color in ('red', 'orange', 'yellow', 'green', 'blue', 'indigo', 'violet', 'others')),
  created DATETIME,
	modified DATETIME,

  CONSTRAINT pk_person PRIMARY KEY (id)
);

CREATE SEQUENCE human_db.s_hobby_id START WITH 1;

CREATE TABLE human_db.hobbies (
  keyword VARCHAR(50) NOT NULL,
  description VARCHAR(150),

  CONSTRAINT pk_hobby PRIMARY KEY (KEYWORD)
);

CREATE TABLE human_db.person_hobby (
  person_id INT NOT NULL,
  hobby_id VARCHAR(50) NOT NULL,

  CONSTRAINT fk_person_hobby_person_id FOREIGN KEY (person_id) REFERENCES human_db.persons(id),
  CONSTRAINT fk_person_hobby_hobby_id FOREIGN KEY (hobby_id) REFERENCES human_db.hobbies(keyword)
);