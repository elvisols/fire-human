-- insert sample persons
INSERT INTO human_db.persons VALUES (next value for human_db.s_person_id, 'John', 'Smith', 'jsmith@fire.com', '$2a$10$39xn.XDgm3xjh1qmoWonCeZKJc4TS1M9c3GYwv/vI9suGBAc0eygq', 29, 'red', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );
INSERT INTO human_db.persons VALUES (next value for human_db.s_person_id, 'Sarah', 'Connor', 'sconnor@fire.com', '$2a$10$39xn.XDgm3xjh1qmoWonCeZKJc4TS1M9c3GYwv/vI9suGBAc0eygq', 54, 'blue', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

-- insert sample hobbies
INSERT INTO human_db.hobbies VALUES ('shopping', 'loves shopping for red apples');
INSERT INTO human_db.hobbies VALUES ('football', 'loves watching football with teams in red');
INSERT INTO human_db.hobbies VALUES ('chess', 'loves playing chess on a blue board');

-- insert person_hubby relation
INSERT INTO human_db.person_hobby VALUES (1, 'shopping');
INSERT INTO human_db.person_hobby VALUES (1, 'football');
INSERT INTO human_db.person_hobby VALUES (2, 'chess');
