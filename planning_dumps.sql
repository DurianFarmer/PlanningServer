CREATE DATABASE  IF NOT EXISTS `planning` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `planning`;
-- MySQL dump 10.13  Distrib 5.5.59, for debian-linux-gnu (x86_64)
--
-- Host: 147.47.206.14    Database: planning
-- ------------------------------------------------------
-- Server version	5.6.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Constraint_Rule`
--

DROP TABLE IF EXISTS `Constraint_Rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Constraint_Rule` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Plan_id` int(11) NOT NULL,
  `Constraint_rule` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Constraint_fk0` (`Plan_id`),
  CONSTRAINT `Constraint_fk0` FOREIGN KEY (`Plan_id`) REFERENCES `Plan` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Constraint_Rule`
--

LOCK TABLES `Constraint_Rule` WRITE;
/*!40000 ALTER TABLE `Constraint_Rule` DISABLE KEYS */;
INSERT INTO `Constraint_Rule` VALUES (1,7,'\"Indirect cost\" is 29.50% of the \"direct cost\"'),(2,5,'\"Project expense\" is minimum 15% of \"direct cost\"'),(3,6,'\"Research allowance\" is maximum 10% of \"Labor costs\"'),(4,9,'Rule test');
/*!40000 ALTER TABLE `Constraint_Rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Detail_Revision`
--

DROP TABLE IF EXISTS `Detail_Revision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Detail_Revision` (
  `Plan_id` int(30) NOT NULL,
  `Detail_id` int(30) NOT NULL,
  `Old_version` int(30) NOT NULL,
  PRIMARY KEY (`Plan_id`,`Detail_id`,`Old_version`),
  KEY `Detail_Revision_fk1` (`Detail_id`),
  CONSTRAINT `Detail_Revision_fk0` FOREIGN KEY (`Plan_id`) REFERENCES `Plan` (`Id`),
  CONSTRAINT `Detail_Revision_fk1` FOREIGN KEY (`Detail_id`) REFERENCES `Plan_Detail` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Detail_Revision`
--

LOCK TABLES `Detail_Revision` WRITE;
/*!40000 ALTER TABLE `Detail_Revision` DISABLE KEYS */;
INSERT INTO `Detail_Revision` VALUES (3,2,0),(4,3,0),(4,3,1),(4,7,1),(5,9,0),(14,17,0),(14,17,1),(14,18,1),(15,20,0);
/*!40000 ALTER TABLE `Detail_Revision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Execution_Log`
--

DROP TABLE IF EXISTS `Execution_Log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Execution_Log` (
  `Id` int(30) NOT NULL AUTO_INCREMENT,
  `Plan_id` int(30) NOT NULL,
  `Version_id` int(30) DEFAULT NULL,
  `State_id` int(30) DEFAULT NULL,
  `Date_log` datetime NOT NULL,
  `Item_id` int(30) NOT NULL,
  `Item_value` varchar(255) NOT NULL,
  `User_executed` int(30) NOT NULL,
  `Date_start` datetime DEFAULT NULL,
  `Date_end` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Execution_Log_fk0` (`Plan_id`),
  KEY `Execution_Log_fk1` (`Version_id`),
  KEY `Execution_Log_fk2` (`State_id`),
  KEY `Execution_Log_fk3` (`Item_id`),
  KEY `Execution_Log_fk4` (`User_executed`),
  CONSTRAINT `Execution_Log_fk0` FOREIGN KEY (`Plan_id`) REFERENCES `Plan` (`Id`),
  CONSTRAINT `Execution_Log_fk2` FOREIGN KEY (`State_id`) REFERENCES `State` (`Id`),
  CONSTRAINT `Execution_Log_fk3` FOREIGN KEY (`Item_id`) REFERENCES `Plan_Item` (`Id`),
  CONSTRAINT `Execution_Log_fk4` FOREIGN KEY (`User_executed`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Execution_Log`
--

LOCK TABLES `Execution_Log` WRITE;
/*!40000 ALTER TABLE `Execution_Log` DISABLE KEYS */;
INSERT INTO `Execution_Log` VALUES (1,3,NULL,NULL,'2018-08-23 08:21:32',40,'3,600,000',1,'2017-07-31 15:00:00','2017-07-31 15:00:00'),(2,4,NULL,NULL,'2018-08-24 02:38:00',41,'3,447,733',1,'2017-07-31 15:00:00','2017-08-06 15:00:00'),(3,6,NULL,NULL,'2018-08-24 04:00:13',42,'2,930,000',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(4,7,NULL,NULL,'2018-08-24 04:00:42',43,'17,310,000',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(5,2,NULL,NULL,'2018-08-24 04:26:54',24,'7,500,000',1,'2017-07-31 15:00:00','2017-12-30 15:00:00'),(6,2,NULL,NULL,'2018-08-24 04:28:43',44,'7,500,000',1,'2017-07-31 15:00:00','2017-12-30 15:00:00'),(7,2,NULL,NULL,'2018-08-24 04:29:14',45,'2,600,000',1,'2017-07-31 15:00:00','2017-09-29 15:00:00'),(8,2,NULL,NULL,'2018-08-24 04:31:09',46,'1,000,000',1,'2017-07-31 15:00:00','2017-08-30 15:00:00'),(9,5,NULL,NULL,'2018-08-24 04:32:17',35,'24,000',1,'2017-08-13 15:00:00','2017-08-13 15:00:00'),(10,5,NULL,NULL,'2018-08-24 04:32:56',37,'53,000',1,'2017-08-16 15:00:00','2017-08-16 15:00:00'),(11,9,NULL,NULL,'2018-08-27 08:19:59',48,'400,000',1,'2017-12-31 15:00:00','2018-05-29 15:00:00'),(12,10,NULL,NULL,'2018-08-27 08:31:13',48,'500,000',1,'2017-12-31 15:00:00','2018-03-30 15:00:00'),(13,12,NULL,NULL,'2018-09-03 02:24:15',49,'3,700,000',1,'2017-12-31 15:00:00','2018-05-30 15:00:00'),(14,12,NULL,NULL,'2018-09-03 02:27:35',49,'18,700,000',1,'2018-05-31 15:00:00','2018-06-29 15:00:00'),(15,12,NULL,NULL,'2018-09-03 02:28:06',49,'18,600,000',1,'2018-06-30 15:00:00','2018-07-30 15:00:00'),(16,13,NULL,NULL,'2018-09-03 02:29:46',33,'5,720,000',1,'2018-08-14 15:00:00','2018-08-19 15:00:00'),(17,15,NULL,NULL,'2018-09-03 02:30:26',34,'33,500',1,'2018-05-30 15:00:00','2018-05-30 15:00:00'),(18,15,NULL,NULL,'2018-09-03 02:33:17',35,'45,000',1,'2018-06-03 15:00:00','2018-06-03 15:00:00'),(19,15,NULL,NULL,'2018-09-03 02:33:50',39,'360,000',1,'2017-12-31 15:00:00','2018-07-30 15:00:00'),(20,15,NULL,NULL,'2018-09-03 02:35:27',50,'702,980',1,'2018-06-30 15:00:00','2018-07-30 15:00:00');
/*!40000 ALTER TABLE `Execution_Log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Goal`
--

DROP TABLE IF EXISTS `Goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Goal` (
  `Id` int(30) NOT NULL AUTO_INCREMENT,
  `Parent_id` int(30) DEFAULT NULL,
  `Target` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Date_created` datetime DEFAULT NULL,
  `User_created` int(30) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Goal_fk1` (`User_created`),
  KEY `Goal_fk0` (`Parent_id`),
  CONSTRAINT `Goal_fk0` FOREIGN KEY (`Parent_id`) REFERENCES `Goal` (`Id`),
  CONSTRAINT `Goal_fk1` FOREIGN KEY (`User_created`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Goal`
--

LOCK TABLES `Goal` WRITE;
/*!40000 ALTER TABLE `Goal` DISABLE KEYS */;
INSERT INTO `Goal` VALUES (1,NULL,'Parent goal: Self-driving car video and image database management system.','Build a Self-driving car video and image database management system. The time duration is 5 years with budget total is 300 million won.','2017-01-01 00:00:00',1),(2,NULL,'Parent goal: High Performance Computing System.','Build an HPC system with time duration is 3 years and the budget is 200 million won. The HPL benchmark is 100 TFlops.','2017-03-01 00:00:00',2),(3,1,'Sub financial goal 1: First year. Duration 8 months. Total budget: 76,000,000 won','Sub goal 1: First year. Duration 8 months (05/01/2017~12/31/2017). Total budget: 76,000,000 won','2017-05-01 00:00:00',1),(4,1,'Sub financial goal 2: Second year. Duration 12 months. Total budget: 157,250,000 won','Sub goal 2: Second year. Duration 12 months (01/01/2018~12/31/2018). Total budget: 157,250,000 won','2018-01-01 00:00:00',1),(5,1,'Sub production goal 1: First year. 빅데이터 관리 시스템 기본 구조 설계 및 구축. 공개 데이터를 활용한 Prototype 구현(1/2)','Sub production goal 1: First year. 빅데이터 관리 시스템 기본 구조 설계 및 구축. 공개 데이터를 활용한 Prototype 구현(1/2)','2018-08-27 05:47:31',1),(6,1,'Sub production goal 2: Second year. 스토리지 및 검색 엔진 개발 (1/3). 공개 데이터를 활용한 Prototype 구현(2/2). 데이터 배포용 인터페이스 S/W 개발(1/2)','Sub production goal 2: Second year. 스토리지 및 검색 엔진 개발 (1/3). 공개 데이터를 활용한 Prototype 구현(2/2). 데이터 배포용 인터페이스 S/W 개발(1/2)','2018-08-27 05:48:18',1),(7,2,'Sub financial goal 1: First year. Duration: 12 months. Total budget: 100,000,000 won','Sub financial goal 1: First year. Duration: 12 months. Total budget: 100,000,000 won','2018-08-27 08:56:09',2),(8,2,'Sub financial goal 2: Second year. Duration: 12 months. Total budget: 166,000,000 won','Sub financial goal 2: Second year. Duration: 12 months. Total budget: 166,000,000 won','2018-08-27 08:56:52',2);
/*!40000 ALTER TABLE `Goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Plan`
--

DROP TABLE IF EXISTS `Plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Plan` (
  `Id` int(30) NOT NULL AUTO_INCREMENT,
  `Parent_id` int(30) DEFAULT NULL,
  `Project_id` int(30) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Cur_version` int(30) NOT NULL,
  `User_created` int(30) NOT NULL,
  `Date_created` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Plan_fk0` (`Parent_id`),
  KEY `Plan_fk1` (`Project_id`),
  KEY `Plan_fk2` (`User_created`),
  CONSTRAINT `Plan_fk0` FOREIGN KEY (`Parent_id`) REFERENCES `Plan` (`Id`),
  CONSTRAINT `Plan_fk1` FOREIGN KEY (`Project_id`) REFERENCES `Project` (`Id`),
  CONSTRAINT `Plan_fk2` FOREIGN KEY (`User_created`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Plan`
--

LOCK TABLES `Plan` WRITE;
/*!40000 ALTER TABLE `Plan` DISABLE KEYS */;
INSERT INTO `Plan` VALUES (1,NULL,1,'Budget plan',0,1,'2018-08-23 08:02:20'),(2,1,1,'Labor plan',0,1,'2018-08-23 08:05:04'),(3,1,1,'Research equipment & Material plan',1,1,'2018-08-23 08:05:36'),(4,1,1,'Research activity costs plan',2,1,'2018-08-23 08:05:54'),(5,1,1,'Project expenses plan',1,1,'2018-08-23 08:09:10'),(6,1,1,'Research allowance plan',0,1,'2018-08-23 08:09:23'),(7,1,1,'Indirect costs plan',0,1,'2018-08-23 08:09:34'),(8,NULL,4,'Production plan',0,1,'2018-08-27 08:01:50'),(9,8,4,'Self-driving dataset Survey plan',0,1,'2018-08-27 08:02:36'),(10,8,4,'Database schema plan',0,1,'2018-08-27 08:03:14'),(11,NULL,2,'Budget plan',0,1,'2018-09-03 01:55:54'),(12,11,2,'Labor costs plan',0,1,'2018-09-03 01:56:28'),(13,11,2,'Research equipment and Material',0,1,'2018-09-03 01:56:53'),(14,11,2,'Research activity costs plan',2,1,'2018-09-03 01:57:09'),(15,11,2,'Project expenses plan',1,1,'2018-09-03 01:57:21'),(16,11,2,'Research allowance plan',0,1,'2018-09-03 01:57:33'),(17,11,2,'Indirect costs plan',0,1,'2018-09-03 01:57:42');
/*!40000 ALTER TABLE `Plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Plan_Detail`
--

DROP TABLE IF EXISTS `Plan_Detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Plan_Detail` (
  `Id` int(30) NOT NULL AUTO_INCREMENT,
  `Plan_id` int(30) NOT NULL,
  `Cur_version` int(30) DEFAULT NULL,
  `Item_id` int(30) NOT NULL,
  `Item_value` varchar(255) NOT NULL,
  `User_assigned` int(30) NOT NULL,
  `Date_start` datetime DEFAULT NULL,
  `Date_end` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Plan_Detail_fk0` (`Plan_id`),
  KEY `Plan_Detail_fk1` (`Item_id`),
  KEY `Plan_Detail_fk2` (`User_assigned`),
  CONSTRAINT `Plan_Detail_fk0` FOREIGN KEY (`Plan_id`) REFERENCES `Plan` (`Id`),
  CONSTRAINT `Plan_Detail_fk1` FOREIGN KEY (`Item_id`) REFERENCES `Plan_Item` (`Id`),
  CONSTRAINT `Plan_Detail_fk2` FOREIGN KEY (`User_assigned`) REFERENCES `User` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Plan_Detail`
--

LOCK TABLES `Plan_Detail` WRITE;
/*!40000 ALTER TABLE `Plan_Detail` DISABLE KEYS */;
INSERT INTO `Plan_Detail` VALUES (1,2,0,7,'40,960,000',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(2,3,1,8,'3,600,000',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(3,4,2,10,'4,000,000',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(6,3,1,9,'0',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(7,4,2,12,'500,000',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(8,4,2,13,'500,000',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(9,5,1,16,'3,000,000',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(10,5,1,17,'3,200,000',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(11,6,0,5,'2,930,000',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(12,7,0,6,'17,310,000',1,'2017-04-30 15:00:00','2017-12-30 15:00:00'),(13,9,0,47,'500,000',1,'2017-12-31 15:00:00','2018-07-29 15:00:00'),(14,10,0,47,'600,000',1,'2017-12-31 15:00:00','2018-08-30 15:00:00'),(15,12,0,7,'96,500,000',1,'2017-12-31 15:00:00','2018-12-30 15:00:00'),(16,13,0,8,'6,000,000',1,'2017-12-31 15:00:00','2018-12-30 15:00:00'),(17,14,2,10,'4,000,000',1,'2017-12-31 15:00:00','2018-12-30 15:00:00'),(18,14,2,12,'500,000',1,'2017-12-31 15:00:00','2018-12-30 15:00:00'),(19,14,2,13,'500,000',1,'2017-12-31 15:00:00','2018-12-30 15:00:00'),(20,15,1,16,'2,400,000',1,'2017-12-31 15:00:00','2018-12-30 15:00:00'),(21,15,1,17,'8,470,000',1,'2017-12-31 15:00:00','2018-12-30 15:00:00'),(22,16,0,5,'4,636,000',1,'2017-12-31 15:00:00','2018-12-30 15:00:00'),(23,17,0,6,'34,244,000',1,'2017-12-31 15:00:00','2018-12-30 15:00:00');
/*!40000 ALTER TABLE `Plan_Detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Plan_Item`
--

DROP TABLE IF EXISTS `Plan_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Plan_Item` (
  `Id` int(30) NOT NULL AUTO_INCREMENT,
  `Parent_id` int(30) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Unit` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Plan_Item_fk0` (`Parent_id`),
  CONSTRAINT `Plan_Item_fk0` FOREIGN KEY (`Parent_id`) REFERENCES `Plan_Item` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Plan_Item`
--

LOCK TABLES `Plan_Item` WRITE;
/*!40000 ALTER TABLE `Plan_Item` DISABLE KEYS */;
INSERT INTO `Plan_Item` VALUES (1,NULL,'Labor costs','won','Labor costs'),(2,NULL,'Research equipment & Material','won','Research equipment & Material'),(3,NULL,'Research activity costs','won','Research activity costs'),(4,NULL,'Project expenses','won','Project expenses'),(5,NULL,'Research allowance','won','Research allowance'),(6,NULL,'Indirect costs','won','Indirect costs'),(7,1,'Student labor costs','won','Student labor costs'),(8,2,'Research equipment','won','Research equipment'),(9,2,'Material','won','Material'),(10,3,'Overseas travel expenses','won','Overseas travel expenses'),(11,3,'Print expenses','won','Print expenses'),(12,3,'Publication fee','won','Publication fee'),(13,3,'Conference ','won','Conference '),(14,4,'Domestic travel expenses','won','Domestic travel expenses'),(15,4,'Office supplies','won','Office supplies'),(16,4,'Meeting','won','Meeting'),(17,4,'Night time meals','won','Night time meals'),(18,7,'Kim A','won',''),(19,7,'Kim B','won',''),(20,7,'Kim C','won',''),(21,7,'Kim D','won',''),(22,7,'Lee A','won',''),(23,7,'Lee B','won',''),(24,7,'Lee C','won',''),(25,7,'Cha A','won',''),(26,7,'Cha B','won',''),(27,7,'Han A','won',''),(28,7,'Han B','won',''),(29,7,'Han C','won',''),(30,7,'Baek A','won',''),(31,7,'Baek B','won',''),(32,7,'Baek C','won',''),(33,8,'NAS Server','won',''),(34,17,'가마솥도사락','won',''),(35,17,'낙성대점미스터보쌈5379','won',''),(36,17,'만리향','won',''),(37,17,'우리밥상','won',''),(38,17,'코바코','won',''),(39,14,'국내출장','won',''),(40,8,'Laptop','won',''),(41,10,'미국출장','won',''),(42,5,'Research allowance','won','Research allowance child item'),(43,6,'Indirect costs','won','Indirect costs child item'),(44,7,'Park A','won',''),(45,7,'Park B','won',''),(46,7,'Park C','won',''),(47,NULL,'Test parent','won','Test parent'),(48,47,'Test item','won','Test item'),(49,7,'학생인건비','won','Students costs when not show specific student'),(50,16,'고결쌤 회의비 처리','won','고결쌤 회의비 처리'),(51,17,'고결쌤 식대 영수증처리','won','고결쌤 식대 영수증처리');
/*!40000 ALTER TABLE `Plan_Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Plan_Revision`
--

DROP TABLE IF EXISTS `Plan_Revision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Plan_Revision` (
  `Version_Id` int(30) NOT NULL,
  `Plan_id` int(30) NOT NULL,
  `Date_created` datetime DEFAULT NULL,
  `Date_updated` datetime DEFAULT NULL,
  `User_created` int(11) DEFAULT NULL,
  `User_updated` int(30) DEFAULT NULL,
  `Update_reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Version_Id`,`Plan_id`),
  KEY `Plan_Revision_fk0` (`Plan_id`),
  KEY `Plan_Revision_fk1` (`User_created`),
  KEY `Plan_Revision_fk2` (`User_updated`),
  CONSTRAINT `Plan_Revision_fk0` FOREIGN KEY (`Plan_id`) REFERENCES `Plan` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Plan_Revision`
--

LOCK TABLES `Plan_Revision` WRITE;
/*!40000 ALTER TABLE `Plan_Revision` DISABLE KEYS */;
INSERT INTO `Plan_Revision` VALUES (0,3,'2018-08-23 08:05:36','2018-08-24 01:30:37',1,1,'Add new Plan Detail'),(0,4,'2018-08-23 08:05:54','2018-08-24 03:18:53',1,1,'Add new Plan Detail'),(0,5,'2018-08-23 08:09:10','2018-08-24 03:21:58',1,1,'Add new Plan Detail'),(0,14,'2018-09-03 01:57:09','2018-09-03 02:18:23',1,1,'Add new Plan Detail'),(0,15,'2018-09-03 01:57:21','2018-09-03 02:20:00',1,1,'Add new Plan Detail'),(1,4,'2018-08-23 08:05:54','2018-08-24 03:20:50',1,1,'Add new Plan Detail'),(1,14,'2018-09-03 01:57:09','2018-09-03 02:18:45',1,1,'Add new Plan Detail');
/*!40000 ALTER TABLE `Plan_Revision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Project`
--

DROP TABLE IF EXISTS `Project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Project` (
  `Id` int(30) NOT NULL AUTO_INCREMENT,
  `Code` varchar(255) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Manager_id` int(30) DEFAULT NULL,
  `Goal_id` int(30) DEFAULT NULL,
  `Date_start` datetime DEFAULT NULL,
  `Date_end` datetime DEFAULT NULL,
  `Date_created` datetime DEFAULT NULL,
  `Total_budget` int(30) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Project_fk0` (`Manager_id`),
  KEY `Project_fk1` (`Goal_id`),
  CONSTRAINT `Project_fk0` FOREIGN KEY (`Manager_id`) REFERENCES `User` (`Id`),
  CONSTRAINT `Project_fk1` FOREIGN KEY (`Goal_id`) REFERENCES `Goal` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Project`
--

LOCK TABLES `Project` WRITE;
/*!40000 ALTER TABLE `Project` DISABLE KEYS */;
INSERT INTO `Project` VALUES (1,'2017-001','자율주행 차량용 전방 및 측방 영상센서 모듈 개발 - 1st year','자율주행 차량용 전방 및 측방 영상센서 모듈 개발 - 1st year',1,3,'2017-05-01 00:00:00','2017-12-31 00:00:00','2017-01-01 00:00:00',76000000),(2,'2018-001','자율주행 차량용 전방 및 측방 영상센서 모듈 개발 - 2nd year','자율주행 차량용 전방 및 측방 영상센서 모듈 개발 - 2nd year',1,4,'2018-01-01 00:00:00','2018-12-31 00:00:00','2017-12-20 00:00:00',157200000),(3,'2017-002','HPC (유전체 수준 단백질 구조 모델링) - 2nd year','HPC (유전체 수준 단백질 구조 모델링) - 2nd year',2,2,'2017-08-01 00:00:00','2018-05-30 00:00:00','2017-06-30 00:00:00',NULL),(4,'Test','Test project','',1,6,'2017-12-31 15:00:00','2018-08-30 15:00:00','2018-08-27 07:16:49',NULL);
/*!40000 ALTER TABLE `Project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `State`
--

DROP TABLE IF EXISTS `State`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `State` (
  `Id` int(30) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Project_id` int(11) NOT NULL,
  `Is_initial` tinyint(1) DEFAULT NULL,
  `Is_goal` tinyint(1) DEFAULT NULL,
  `Content` varchar(255) DEFAULT NULL COMMENT 'Current state',
  `Plan_state` varchar(255) DEFAULT NULL,
  `Checkpoint_date` datetime DEFAULT NULL,
  `Result` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `State_fk0` (`Project_id`),
  CONSTRAINT `State_fk0` FOREIGN KEY (`Project_id`) REFERENCES `Project` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `State`
--

LOCK TABLES `State` WRITE;
/*!40000 ALTER TABLE `State` DISABLE KEYS */;
INSERT INTO `State` VALUES (1,'State 0',3,1,0,'0% budget. 0 month.','0% budget. 0 month.','2017-08-01 00:00:00','Initial state. No HPC experiment. 2 full-time PhD students.'),(2,'State 1.1',3,0,0,'10% budget. 1 month.','10% budget. 1 month.','2017-08-31 00:00:00','Buy 2 HPC servers. 1 month research.'),(3,'State 1.2',3,0,0,'30% budget. 4 months.','30% budget. 4 months.','2017-11-30 00:00:00','4 months research so far. HPC system draft proposal.'),(4,'State 1.3',3,0,0,'60% budget. 5 months.','60% budget. 5 months.','2017-12-31 00:00:00','Buy 2 more HPC servers. Need 1 outside expert for advising. HPC v0.8 proposal'),(5,'State 1.4',3,0,0,'80% budget. 10 months.','80% budget. 10 months.','2018-05-30 00:00:00','5 months experiment so far. HPC system v0.9'),(6,'State 2.1',3,0,0,'20% budget. 1 month.','20% budget. 1 month.','2017-08-30 00:00:00','Buy 4 HPC servers. 1 month research.'),(7,'State 2.2',3,0,0,'55% budget. 7 months.','55% budget. 7 months.','2018-02-28 00:00:00','7 months research so far. HPC system v0.8 proposal.'),(8,'State 2.3',3,0,0,'85% budget. 11 months.','85% budget. 11 months.','2018-06-30 00:00:00','4 months experiment so far. Need 1 Master student to do experiment. HPC system v0.9'),(9,'State 3',3,0,1,'100% budget. 12 months.','100% budget. 12 months.','2018-07-31 00:00:00','Goal state. HPC system v1.0'),(10,'State 0',2,1,0,'0% budget. 0 month.','0% budget. 0 month.','2018-01-01 00:00:00','Initial state. Start the project.'),(11,'State 12',2,0,1,'100% budget. 12 months.','100% budget. 12 months.','2018-12-31 00:00:00','Goal state. Finished the project.'),(16,'State 0',1,1,0,'0% budget. 0 month','0% budget. 0 month','2017-04-30 15:00:00','Initial state. Project start.'),(17,'State 8',1,0,1,'100% budget. 8 months.','100% budget. 8 months.','2017-12-30 15:00:00','Goal state. Project completed'),(41,'State 1',2,0,0,'0% budget. 1 months.','8% budget. 1 months.','2018-01-30 15:00:00',''),(42,'State 1.2',2,0,0,'0% budget. 2 months.','16% budget. 2 months.','2018-02-27 15:00:00',''),(43,'State 1.3',2,0,0,'0% budget. 3 months.','24% budget. 3 months.','2018-03-30 15:00:00',''),(44,'State 1.4',2,0,0,'0% budget. 4 months.','33% budget. 4 months.','2018-04-29 15:00:00',''),(46,'State 1.6',2,0,0,'14% budget. 6 months.','49% budget. 6 months.','2018-06-29 15:00:00',''),(47,'State 1.5',2,0,0,'2% budget. 5 months.','41% budget. 5 months.','2018-05-30 15:00:00',''),(48,'State 11',2,0,0,'30% budget. 11 months.','92% budget. 11 months.','2018-11-29 15:00:00','');
/*!40000 ALTER TABLE `State` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `Id` int(30) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'Juhyun','Researcher','PIDL'),(2,'Haelim','Researcher','PIDL');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-04 14:48:17
