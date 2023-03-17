CREATE TABLE USER (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  FIRST_NAME varchar(20),
  LAST_NAME varchar(20),
  USERNAME varchar(15),
  EMAIL varchar(25),
  password varchar(20)
);


CREATE TABLE Time_Registration (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id int NOT NULL,
  date timestamp NOT NULL,
  time numeric NOT NULL,
  type_of_time varchar(20) NOT NULL,
  comment varchar(150),
  created timestamp default (CURTIME()),
  updated timestamp,
    FOREIGN KEY (user_id)
      REFERENCES USER(id)
);