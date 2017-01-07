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

INSERT INTO sec_user (userid, username, password) VALUES (1, 'myadmin', SHA2('topsecret', 512));
INSERT INTO sec_user (userid, username, password) VALUES (2, 'myuser', SHA2('topsecret', 512));

INSERT INTO sec_role (roleid, rolename) VALUES (1, 'ADMIN');
INSERT INTO sec_role (roleid, rolename) VALUES (2, 'END_USER');
INSERT INTO sec_role (roleid, rolename) VALUES (3, 'SUPER_USER');

INSERT INTO sec_user_role (userid, roleid) VALUES (1, 1);
INSERT INTO sec_user_role (userid, roleid) VALUES (2, 2);