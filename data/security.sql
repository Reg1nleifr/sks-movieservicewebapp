USE moviedb;

DROP TABLE IF EXISTS sec_user_role;
DROP TABLE IF EXISTS sec_user;
DROP TABLE IF EXISTS sec_role;

CREATE TABLE sec_user (
  userid   INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(25)  NOT NULL,
  password VARCHAR(128) NOT NULL
);

CREATE TABLE sec_role (
  roleid   INT AUTO_INCREMENT PRIMARY KEY,
  rolename VARCHAR(25) NOT NULL
);

CREATE TABLE sec_user_role (
  userroleid INT AUTO_INCREMENT PRIMARY KEY,
  userid     INT NOT NULL,
  roleid     INT NOT NULL,
  FOREIGN KEY (userid) REFERENCES sec_user (userid),
  FOREIGN KEY (roleid) REFERENCES sec_role (roleid)
);

INSERT INTO sec_user (userid, username, password) VALUES (1, 'reader', SHA2('123', 512));
INSERT INTO sec_user (userid, username, password) VALUES (2, 'writer', SHA2('123', 512));
INSERT INTO sec_user (userid, username, password) VALUES (3, 'other', SHA2('123', 512));
INSERT INTO sec_role (roleid, rolename) VALUES (1, 'MSRead');
INSERT INTO sec_role (roleid, rolename) VALUES (2, 'MSWrite');
INSERT INTO sec_role (roleid, rolename) VALUES (3, 'MSOther');
INSERT INTO sec_user_role (userid, roleid) VALUES (1, 1);
INSERT INTO sec_user_role (userid, roleid) VALUES (2, 1);
INSERT INTO sec_user_role (userid, roleid) VALUES (2, 2);
INSERT INTO sec_user_role (userid, roleid) VALUES (3, 3);