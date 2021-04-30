create database IF NOT EXISTS Hodge_Bank;
use Hodge_Bank;

drop table if exists `attendance`;
drop table if exists `teams`;
drop table if exists `events`;
drop table if exists `authorities`;
drop table if exists `groups`;
drop table if exists `users`;



CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `groups` (
  `groupId` int(11) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(50) NOT NULL,
  PRIMARY KEY (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(70) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


CREATE TABLE `events` (
  `eventId` int(11) NOT NULL AUTO_INCREMENT,
  `eventName` varchar(50) NOT NULL,
  `eventDesc` varchar(500) NOT NULL,
  `eventLocSt1` varchar(100) NOT NULL,
  `eventLocSt2` varchar(100) DEFAULT NULL,
  `eventLocCity` varchar(50) NOT NULL,
  `eventLocPost` varchar(10) NOT NULL,
  `eventStartDate` date NOT NULL,
  `eventEndDate` date NOT NULL,
  `eventStartTime` time NOT NULL,
  `eventEndTime` time NOT NULL,
  `eventOrganiser` int(11) NOT NULL,
  `dateCreated` date NOT NULL,
  `maxTeamSize` int(11) DEFAULT NULL,
  `eventColour` varchar(20) NOT NULL,
  PRIMARY KEY (`eventId`),
   KEY `eventOrganiser` (`eventOrganiser`),
   CONSTRAINT `events_ibfk_1` FOREIGN KEY (`eventOrganiser`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;



CREATE TABLE `teams` (
  `teamId` int(11) NOT NULL AUTO_INCREMENT,
  `eventId` int(11) DEFAULT NULL,
  `teamName` varchar(50) NOT NULL,
  PRIMARY KEY (`teamId`),
  KEY `eventId` (`eventId`),
  CONSTRAINT `teams_ibfk_1` FOREIGN KEY (`eventId`) REFERENCES `events` (`eventId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `attendance` (
  `attendanceId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `eventId` int(11) DEFAULT NULL,
  `response` smallint(6) NOT NULL,
  `teamId` int(11) DEFAULT NULL,
  PRIMARY KEY (`attendanceId`),
  KEY `userId` (`userId`),
  KEY `eventId` (`eventId`),
  KEY `teamId` (`teamId`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`eventId`) REFERENCES `events` (`eventId`),
  CONSTRAINT `attendance_ibfk_3` FOREIGN KEY (`teamId`) REFERENCES `teams` (`teamId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

