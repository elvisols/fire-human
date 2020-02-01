CREATE SEQUENCE human_db.s_person_id START WITH 1;

CREATE TABLE human_db.persons (
  id INT AUTO_INCREMENT NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  age INT NOT NULL,
  color VARCHAR(50) check(color in ('red', 'orange', 'yellow', 'green', 'blue', 'indigo', 'violet', 'others')),
  created DATETIME,
	modified DATETIME,

  CONSTRAINT pk_person PRIMARY KEY (id)
);

CREATE SEQUENCE human_db.s_hobby_id START WITH 1;

CREATE TABLE human_db.hobbies (
  keyword VARCHAR(50) NOT NULL,
  person_id INT NOT NULL,
  description VARCHAR(50),

  CONSTRAINT pk_hobby PRIMARY KEY (KEYWORD),
  CONSTRAINT fk_hobby_person_id FOREIGN KEY (person_id) REFERENCES human_db.persons(id)
);