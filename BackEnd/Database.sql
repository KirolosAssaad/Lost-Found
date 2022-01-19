-- INSERT INTO USERS(SID, username, password, phonenum,name) VALUES ('900193220','melsaqa',SHA2('12345',512),'01001143450', 'mariam' );

FLUSH PRIVILEGES;
ALTER USER "root"@"localhost" IDENTIFIED BY "password";
Create database FinalProject;
Use FinalProject;

DROP TABLE USERS;
Create table USERS(
    SID INT NOT NULL UNIQUE,
    username VARCHAR(30) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    phonenum VARCHAR(11) NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY(SID)
);
DROP TABLE LOSTOBJS;
Create table LOSTOBJS(
    objID INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100) NOT NULL,
    image TEXT NOT NULL,
    SID INT NOT NULL,
    category TEXT NOT NULL,
    PRIMARY KEY(objID)
);
DROP TABLE OBJQS;
Create table OBJQS(
    objID INT NOT NULL AUTO_INCREMENT,
    answer1 TEXT NOT NULL,
    answer2 TEXT NOT NULL,
    answer3 TEXT NOT NULL,
    PRIMARY KEY(objID)
);

DROP TABLE FOUNDOBJS;
Create table FOUNDOBJS(
    objID INT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(30) NOT NULL,
    description VARCHAR(100) NOT NULL,
    image TEXT NOT NULL,
    SID INT NOT NULL,
    category TEXT NOT NULL,
    PRIMARY KEY(objID)
);

DROP TABLE NOTIFICATIONS;
CREATE TABLE NOTIFICATIONS(
    phonenum VARCHAR(11) NOT NULL,
    category VARCHAR(20) NOT NULL,
    PRIMARY KEY (phonenum, category)
);

