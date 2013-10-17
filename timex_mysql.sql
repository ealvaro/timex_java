-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.37-community


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
-- Definition of table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employeeId` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `employeeCode` char(1) NOT NULL,
  `password` varchar(10) NOT NULL,
  `managerEmployeeId` int(11) default NULL,
  UNIQUE KEY `EmployeeIdIndex` (`employeeId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`employeeId`,`name`,`email`,`employeeCode`,`password`,`managerEmployeeId`) VALUES 
 (1,'Mike Dover','mdover@acme.com','H','rapidjava',3),
 (2,'Ajay Kumar','akumar@acme.com','H','visualpatt',3),
 (3,'Teresa Walker','twalker@acme.com','M','agilestuff',4),
 (4,'Tom Brady','tbrady@acme.com','E','superbowl',4),
 (5,'Alvaro E. Escobar','aescobar@acme.com','A','123456',4);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


--
-- Definition of table `timesheet`
--

DROP TABLE IF EXISTS `timesheet`;
CREATE TABLE `timesheet` (
  `timesheetid` int(10) unsigned NOT NULL auto_increment,
  `employeeid` int(10) unsigned NOT NULL,
  `statuscode` char(1) NOT NULL,
  `periodEndingDate` date NOT NULL,
  `departmentCode` char(4) NOT NULL,
  `minutesMon` int(11) default NULL,
  `minutesTue` int(11) default NULL,
  `minutesWed` int(11) default NULL,
  `minutesThu` int(11) default NULL,
  `minutesFri` int(11) default NULL,
  `minutesSat` int(11) default NULL,
  `minutesSun` int(11) default NULL,
  PRIMARY KEY  (`timesheetid`),
  UNIQUE KEY `TimesheetIndex` (`employeeid`,`periodEndingDate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timesheet`
--

/*!40000 ALTER TABLE `timesheet` DISABLE KEYS */;
INSERT INTO `timesheet` (`timesheetid`,`employeeid`,`statuscode`,`periodEndingDate`,`departmentCode`,`minutesMon`,`minutesTue`,`minutesWed`,`minutesThu`,`minutesFri`,`minutesSat`,`minutesSun`) VALUES 
 (1,2,'P','2006-08-19','IT',480,480,360,480,480,0,0),
 (2,1,'A','2006-08-19','HR',0,0,480,480,480,0,0);
/*!40000 ALTER TABLE `timesheet` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
