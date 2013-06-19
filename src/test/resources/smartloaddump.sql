--
-- Copyright 2010-2013 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- MySQL dump 10.13  Distrib 5.5.18, for osx10.6 (i386)
--
-- Host: mysql.rice.kuali.org    Database: smartload
-- ------------------------------------------------------
-- Server version	5.1.63

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
-- Table structure for table `krms_prop_parm_t`
--

DROP TABLE IF EXISTS `krms_prop_parm_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krms_prop_parm_t` (
  `PROP_PARM_ID` varchar(40) COLLATE utf8_bin NOT NULL DEFAULT '',
  `PROP_ID` varchar(40) COLLATE utf8_bin NOT NULL,
  `PARM_VAL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARM_TYP_CD` varchar(1) COLLATE utf8_bin NOT NULL,
  `SEQ_NO` decimal(5,0) NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`PROP_PARM_ID`),
  KEY `KRMS_PROP_PARM_TI1` (`PROP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krms_prop_parm_t`
--

LOCK TABLES `krms_prop_parm_t` WRITE;
/*!40000 ALTER TABLE `krms_prop_parm_t` DISABLE KEYS */;
INSERT INTO `krms_prop_parm_t` VALUES ('10000','10000','10000','T',1,1),('10001','10000','true','C',2,1),('10002','10000','=','O',3,1);
/*!40000 ALTER TABLE `krms_prop_parm_t` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-02-02 19:47:04
