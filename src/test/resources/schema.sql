drop table if exists attendance;
drop table if exists teams;
drop table if exists events;
drop table if exists authorities;
drop table if exists groups;
drop table if exists users;

CREATE TABLE if not exists authorities (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  PRIMARY KEY (username,authority));

CREATE TABLE if not exists  groups (
  groupId int(11) NOT NULL AUTO_INCREMENT,
  groupName varchar(50) NOT NULL,
  PRIMARY KEY (groupId)) ;

CREATE TABLE if not exists users (
  userId int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) DEFAULT NULL,
  password varchar(70) NOT NULL,
  firstName varchar(30) NOT NULL,
  lastName varchar(30) NOT NULL,
  enabled tinyint(4) NOT NULL,
  PRIMARY KEY (userId)) ;

CREATE TABLE if not exists  events (
  eventId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  eventName varchar(50) NOT NULL,
  eventDesc varchar(500) NOT NULL,
  eventLocSt1 varchar(100) NOT NULL,
  eventLocSt2 varchar(100) DEFAULT NULL,
  eventLocCity varchar(50) NOT NULL,
  eventLocPost varchar(10) NOT NULL,
  eventStartDate date NOT NULL,
  eventEndDate date NOT NULL,
  eventStartTime time NOT NULL,
  eventEndTime time NOT NULL,
  eventOrganiser int(11) NOT NULL,
  dateCreated date NOT NULL,
  maxTeamSize int(11) DEFAULT NULL,
  eventColour varchar(20) NOT NULL,
  FOREIGN KEY (eventOrganiser) REFERENCES users(userId));

CREATE TABLE if not exists teams (
  teamId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  eventId int(11) DEFAULT NULL,
  teamName varchar(50) NOT NULL,
  FOREIGN KEY (eventId) REFERENCES events(eventId));

CREATE TABLE if not exists  attendance (
  attendanceId int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  userId int(11) DEFAULT NULL,
  eventId int(11) DEFAULT NULL,
  response smallint(6) NOT NULL,
  teamId int(11) DEFAULT NULL,
  FOREIGN KEY (userId) REFERENCES users(userId),
  FOREIGN KEY (eventId) REFERENCES events(eventId),
  FOREIGN KEY (teamId) REFERENCES teams(teamId) ON DELETE CASCADE);

