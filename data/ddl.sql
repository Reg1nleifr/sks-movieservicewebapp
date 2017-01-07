CREATE TABLE Actor
(
  ID        INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  BIRTHDATE DATE,
  FIRSTNAME VARCHAR(50),
  LASTNAME  VARCHAR(50),
  SEX       VARCHAR(6)
);
CREATE TABLE Actor_Movie
(
  actors_ID INT(11) NOT NULL,
  movies_ID INT(11) NOT NULL
);
CREATE TABLE Movie
(
  ID          INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  DESCRIPTION LONGTEXT,
  GENRE       VARCHAR(50),
  LENGTH      INT(11),
  RELEASEYEAR INT(11)             NOT NULL,
  TITLE       VARCHAR(50),
  studio_ID   INT(11)
);
CREATE TABLE Studio
(
  ID          INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  COUNTRYCODE VARCHAR(3),
  NAME        VARCHAR(50),
  POSTCODE    VARCHAR(11)
);
CREATE TABLE sec_role
(
  roleid   INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  rolename VARCHAR(25)         NOT NULL
);
CREATE TABLE sec_user
(
  userid   INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(25)         NOT NULL,
  password VARCHAR(128)        NOT NULL
);
CREATE TABLE sec_user_role
(
  userroleid INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  userid     INT(11)             NOT NULL,
  roleid     INT(11)             NOT NULL
);
ALTER TABLE Actor_Movie
  ADD FOREIGN KEY (actors_ID) REFERENCES Actor (ID);
ALTER TABLE Actor_Movie
  ADD FOREIGN KEY (movies_ID) REFERENCES Movie (ID);
CREATE INDEX FKfqsqvu35v83y3kr0p3ovhpcbl
  ON Actor_Movie (movies_ID);
CREATE INDEX FKgpn6dlywrjk7iwxmedyyfsymr
  ON Actor_Movie (actors_ID);
ALTER TABLE Movie
  ADD FOREIGN KEY (studio_ID) REFERENCES Studio (ID);
CREATE INDEX FKto9l1krlpwfuidn9hmhegsx7t
  ON Movie (studio_ID);
ALTER TABLE sec_user_role
  ADD FOREIGN KEY (userid) REFERENCES sec_user (userid);
ALTER TABLE sec_user_role
  ADD FOREIGN KEY (roleid) REFERENCES sec_role (roleid);
CREATE INDEX roleid
  ON sec_user_role (roleid);
CREATE INDEX userid
  ON sec_user_role (userid);