CREATE TABLE USER (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  FIRST_NAME varchar(20),
  LAST_NAME varchar(20),
  USERNAME varchar(15),
  EMAIL varchar(25),
  password varchar(20)
);

CREATE TABLE TypeofTime (
  id INT AUTO_INCREMENT PRIMARY KEY,
  type varchar(30)
);

CREATE TABLE TimeRegistration (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id int,
  date timestamp,
  time double,
  typeOfWork varchar(50),
  comments varchar(150),
  createdAt timestamp,
  UpdatedAt timestamp,
    FOREIGN KEY (user_id)
      REFERENCES USER(id)
);