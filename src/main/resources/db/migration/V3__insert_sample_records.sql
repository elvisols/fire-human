-- insert sample persons
INSERT INTO human_db.persons VALUES (next value for human_db.s_person_id, 'John', 'Smith', 29, 'red', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );
INSERT INTO human_db.persons VALUES (next value for human_db.s_person_id, 'Sarah', 'Connor', 54, 'blue', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

-- insert sample hobbies
INSERT INTO human_db.hobbies VALUES ('shopping', 1, 'loves shopping for red apples');
INSERT INTO human_db.hobbies VALUES ('football', 1, 'loves watching football with teams in red');
INSERT INTO human_db.hobbies VALUES ('chess', 2, 'loves playing chess on a blue board');
