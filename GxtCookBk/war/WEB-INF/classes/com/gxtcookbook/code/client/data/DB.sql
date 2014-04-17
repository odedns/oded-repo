
DROP DATABASE IF EXISTS `gxtcookbook`;
CREATE DATABASE `gxtcookbook`;
USE `gxtcookbook`;

SET FOREIGN_KEY_CHECKS=0;

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

DROP TABLE IF EXISTS `courseofstudy`;
CREATE TABLE `courseofstudy` (
  `id` int(11) NOT NULL,
  `student` int(11) NOT NULL,
  `course` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(50) NOT NULL,
  `code` varchar(5) NOT NULL,
  `weight` tinyint(2) NOT NULL,
  `department` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1; 

DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `code` varchar(5) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

INSERT INTO `departments` (`id`, `name`, `code`) VALUES 
(1, 'Mathematics', 'MTH'),
(2, 'Computer Science', 'CSC');

DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` int(11) NOT NULL auto_increment,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `department` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

INSERT INTO `students` (`id`, `fname`, `lname`, `email`, `address`, `department`) VALUES 
(1, 'Charles', 'Odili', 'chalu@lol.com', 'Ikoyi, Lagos Nigeria', 2),
(2, 'Chiedu', 'Mokogwu', 'chie@lol.com', 'DDPA, Warri, Nigeria', 2),
(12, 'Jite', 'Okoh', 'jioko@lol.com', 'NNPC Housing Estate, Warri.', 2);

SET FOREIGN_KEY_CHECKS=1;
