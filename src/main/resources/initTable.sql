DROP TABLE if exists stud;
DROP SEQUENCE if exists stud_seq;

CREATE sequence stud_seq START 10;
CREATE TABLE stud (
  id INTEGER PRIMARY KEY default nextval('stud_seq'),
  firstname VARCHAR(20) NOT NULL ,
  lastname varchar(20) NOT NULL ,
  date DATE default NULL,
  email VARCHAR(20)
);
