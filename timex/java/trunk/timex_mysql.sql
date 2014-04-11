-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.16-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema timex
--

CREATE DATABASE IF NOT EXISTS timex;
USE timex;

--
-- Definition of table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `departmentCode` char(2) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`departmentCode`),
  UNIQUE KEY `DepartmentCodeIndex` (`departmentCode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`departmentCode`,`name`) VALUES 
 ('AC','Accounting'),
 ('CS','Customer Support'),
 ('HR','Human Resources'),
 ('IT','Information Technology');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


--
-- Definition of table `departments`
--

DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `state` varchar(2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departments`
--

/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` (`id`,`name`,`state`) VALUES 
 (1,'Accounting','FL'),
 (2,'Customer Support','GA'),
 (3,'Human Resources','FL'),
 (4,'Information Technology','FL');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;


--
-- Definition of table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employeeId` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `employeeCode` char(1) NOT NULL,
  `password` varchar(10) NOT NULL,
  `managerEmployeeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`employeeId`),
  UNIQUE KEY `EmployeeIdIndex` (`employeeId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`employeeId`,`name`,`email`,`employeeCode`,`password`,`managerEmployeeId`) VALUES 
 (1,'Mike Dover','cjacob@nova.edu','H','rapidjava',3),
 (2,'Ajay Kumar','sbutcher@nova.edu','H','visualpatt',3),
 (3,'Teresa Walker','cw769@nova.edu','M','agilestuff',4),
 (4,'Tom Brady','ealvaro@nova.edu','E','superbowl',4),
 (5,'Alvaro E. Escobar','alvaroescobar@live.com','A','123456',4);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


--
-- Definition of table `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(45) NOT NULL,
  `employeeType` char(1) NOT NULL,
  `password` varchar(40) NOT NULL,
  `employee_id` int(10) NOT NULL,
  `username` varchar(31) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(2) NOT NULL,
  `zipcode` varchar(10) DEFAULT NULL,
  `payrate` double unsigned NOT NULL,
  `taxrate` double unsigned NOT NULL,
  `registrationDate` datetime NOT NULL,
  `pictureFilename` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_employees_1` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` (`id`,`name`,`email`,`employeeType`,`password`,`employee_id`,`username`,`address`,`city`,`state`,`zipcode`,`payrate`,`taxrate`,`registrationDate`,`pictureFilename`) VALUES 
 (1,'Mike Dover','ealvaro@nova.edu','H','8cb2237d0679ca88db6464eac60da96345513964',3,'123-45-6789','123 Main St','Davie','FL','33314',35.68,20,'2006-07-23 15:00:00','2013-04-03 19.05.17.1.jpg'),
 (2,'Ajay Kumar','ealvaro@nova.edu','H','8cb2237d0679ca88db6464eac60da96345513964',3,'123-67-1234','234 Main St','Davie','FL','33314',35.68,18,'2006-07-28 19:00:00','2013-04-03 19.09.35.616.png'),
 (3,'Teresa Walker','ealvaro@nova.edu','M','8cb2237d0679ca88db6464eac60da96345513964',4,'123-89-4321','567 Main St','Davie','FL','33314',120000,16,'2007-01-02 16:00:00','2013-04-03 19.09.35.616.png'),
 (4,'Tom Brady','ealvaro@nova.edu','E','8cb2237d0679ca88db6464eac60da96345513964',4,'123-09-3456','989 Main St','Davie','FL','33314',275000,21,'2007-04-01 08:00:00','2013-04-03 19.09.35.616.png'),
 (5,'Alvaro E. Escobar','ealvaro@nova.edu','A','8cb2237d0679ca88db6464eac60da96345513964',4,'123-12-7654','999 Main St','Davie','FL','33314',90000,23,'2010-10-27 08:00:00','2013-04-03 19.04.12.116.JPG'),
 (9,'John Smith','ealvaro@nova.edu','H','8cb2237d0679ca88db6464eac60da96345513964',3,'209-56-4854','','','FL','',0,0,'2012-10-27 12:31:42','2013-04-03 19.09.35.616.png'),
 (11,'Jane Smith','ealvaro@nova.edu','M','8cb2237d0679ca88db6464eac60da96345513964',4,'209-45-8356','','','GA','',0,0,'2012-10-29 20:15:28','2013-04-03 19.09.35.616.png'),
 (12,'Alvarito Escobar','ealvaro@nova.edu','H','8cb2237d0679ca88db6464eac60da96345513964',3,'123-45-6999','123 Main St','Davie','FL','33314',50,30,'2012-12-04 11:00:00','2013-04-03 19.09.35.616.png'),
 (13,'John Doe','ealvaro@nova.edu','H','8cb2237d0679ca88db6464eac60da96345513964',3,'123-45-8899','123 Main St','Davie','FL','33314',48,30,'2012-12-05 18:21:00','2013-04-03 19.09.35.616.png'),
 (14,'Jane Doe','ealvaro@nova.edu','H','8cb2237d0679ca88db6464eac60da96345513964',3,'janedoe','123 Main St','Davie','FL','33314',38,22,'2012-12-05 18:58:00','2013-04-03 19.09.35.616.png'),
 (16,'Leilani Escobar','leilani@nova.edu','\0','7c222fb2927d828af22f592134e8932480637c0d',5,'123-45-6788','','','FL','',15,15,'2013-04-03 10:47:14','2013-04-03 10.47.14.921.png'),
 (17,'Joe Adams','joe@acme.com','\0','8cb2237d0679ca88db6464eac60da96345513964',3,'987-75-3456','123 Main St','Davie','FL','33314',15,15,'2013-04-03 20:29:19','2013-04-03 20.29.19.678.jpg');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;


--
-- Definition of table `payments`
--

DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regularRate` double NOT NULL,
  `overtimeRate` double NOT NULL,
  `taxPercent` double NOT NULL,
  `netPay` double NOT NULL,
  `timesheet_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_payments_1` (`timesheet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payments`
--

/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` (`id`,`regularRate`,`overtimeRate`,`taxPercent`,`netPay`,`timesheet_id`) VALUES 
 (1,35.68,53.52,20,1084.67,1),
 (2,35.68,53.52,18,1143.32,2),
 (3,57.69,57.69,16,1938.46,6),
 (4,132.21,132.21,21,4177.88,10),
 (5,43.27,43.27,23,1332.69,19);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;


--
-- Definition of table `timesheet`
--

DROP TABLE IF EXISTS `timesheet`;
CREATE TABLE `timesheet` (
  `timesheetid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employeeid` int(10) unsigned NOT NULL,
  `statuscode` char(1) NOT NULL,
  `periodEndingDate` date NOT NULL,
  `departmentCode` char(4) NOT NULL,
  `minutesMon` int(11) DEFAULT NULL,
  `minutesTue` int(11) DEFAULT NULL,
  `minutesWed` int(11) DEFAULT NULL,
  `minutesThu` int(11) DEFAULT NULL,
  `minutesFri` int(11) DEFAULT NULL,
  `minutesSat` int(11) DEFAULT NULL,
  `minutesSun` int(11) DEFAULT NULL,
  PRIMARY KEY (`timesheetid`),
  UNIQUE KEY `TimesheetIndex` (`employeeid`,`periodEndingDate`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timesheet`
--

/*!40000 ALTER TABLE `timesheet` DISABLE KEYS */;
INSERT INTO `timesheet` (`timesheetid`,`employeeid`,`statuscode`,`periodEndingDate`,`departmentCode`,`minutesMon`,`minutesTue`,`minutesWed`,`minutesThu`,`minutesFri`,`minutesSat`,`minutesSun`) VALUES 
 (1,2,'P','2006-08-19','IT',480,480,360,480,480,0,0),
 (2,1,'C','2006-08-19','HR',480,480,480,480,480,0,0),
 (3,1,'C','2010-01-31','CS',480,480,480,600,600,0,0),
 (4,1,'A','2010-02-21','AC',480,480,480,240,240,0,0),
 (5,1,'A','2010-02-28','IT',420,360,480,360,360,420,0),
 (6,3,'A','2010-03-21','CS',480,480,480,480,480,0,0),
 (7,1,'A','2010-03-21','IT',480,480,480,240,420,120,0),
 (8,1,'A','2010-03-28','IT',480,480,480,480,480,0,0),
 (9,2,'A','2010-03-28','AC',480,480,48,480,480,0,0),
 (10,4,'P','2010-05-02','CS',240,240,240,0,0,0,0),
 (11,3,'A','2010-05-02','CS',300,300,300,300,300,0,0),
 (18,1,'P','2012-02-26','IT',360,360,240,0,0,0,0),
 (19,5,'P','2012-03-25','IT',480,480,480,480,480,240,0);
/*!40000 ALTER TABLE `timesheet` ENABLE KEYS */;


--
-- Definition of table `timesheets`
--

DROP TABLE IF EXISTS `timesheets`;
CREATE TABLE `timesheets` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` int(10) NOT NULL,
  `statusCode` char(1) NOT NULL,
  `periodEndingDate` date NOT NULL,
  `department_id` int(10) unsigned NOT NULL,
  `minutesMon` int(10) unsigned DEFAULT NULL,
  `minutesTue` int(10) unsigned DEFAULT NULL,
  `minutesWed` int(10) unsigned DEFAULT NULL,
  `minutesThu` int(10) unsigned DEFAULT NULL,
  `minutesFri` int(10) unsigned DEFAULT NULL,
  `minutesSat` int(10) unsigned DEFAULT NULL,
  `minutesSun` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_timesheets_1` (`employee_id`),
  KEY `FK_timesheets_2` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timesheets`
--

/*!40000 ALTER TABLE `timesheets` DISABLE KEYS */;
INSERT INTO `timesheets` (`id`,`employee_id`,`statusCode`,`periodEndingDate`,`department_id`,`minutesMon`,`minutesTue`,`minutesWed`,`minutesThu`,`minutesFri`,`minutesSat`,`minutesSun`) VALUES 
 (1,2,'C','2006-08-19',4,480,480,360,480,480,0,0),
 (2,1,'C','2006-08-19',3,480,480,480,480,480,0,0),
 (3,1,'A','2010-01-31',2,480,480,480,600,600,0,0),
 (4,1,'A','2010-02-21',1,480,480,480,240,240,0,0),
 (5,1,'A','2010-02-28',4,420,360,480,360,360,420,0),
 (6,3,'C','2010-03-21',2,480,480,480,480,480,0,0),
 (7,1,'A','2010-03-21',4,480,480,480,240,420,120,0),
 (8,1,'D','2010-03-28',4,480,480,480,480,480,0,0),
 (9,2,'D','2010-03-28',1,480,480,48,480,480,0,0),
 (10,4,'C','2010-05-02',2,480,480,480,480,480,0,0),
 (11,3,'A','2010-05-02',2,300,300,300,300,300,0,0),
 (18,1,'A','2012-02-26',4,360,360,240,360,0,0,0),
 (19,5,'C','2012-03-25',4,480,480,480,480,480,240,0),
 (20,14,'A','2012-04-08',4,240,240,540,540,0,0,0),
 (37,3,'S','2012-04-08',3,420,480,480,420,0,0,0),
 (38,2,'A','2012-04-08',2,420,360,300,240,0,0,0),
 (39,1,'P','2013-03-24',2,480,480,480,480,480,0,0),
 (52,1,'P','2013-03-24',2,420,420,420,0,0,0,0),
 (53,1,'P','2013-03-24',4,480,480,480,0,0,0,0),
 (54,3,'P','2013-03-24',1,480,480,480,240,0,0,0),
 (55,5,'P','2013-04-07',1,480,480,480,0,0,0,0);
/*!40000 ALTER TABLE `timesheets` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
