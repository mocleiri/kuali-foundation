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
INSERT INTO `krms_prop_parm_t` VALUES ('10000','10000','10000','T',1,1),('10001','10000','true','C',2,1),('10002','10000','=','O',3,1),('10003','10001','10001','T',1,1),('10004','10001','true','C',2,1),('10005','10001','=','O',3,1),('10006','10002','10002','T',1,1),('10007','10002','true','C',2,1),('10008','10002','=','O',3,1),('10009','10003','10003','T',1,1),('10010','10003','true','C',2,1),('10011','10003','=','O',3,1),('10012','10004','10004','T',1,1),('10013','10004','true','C',2,1),('10014','10004','=','O',3,1),('10015','10005','10005','T',1,1),('10016','10005','true','C',2,1),('10017','10005','=','O',3,1),('10018','10007','10006','T',1,1),('10019','10007','true','C',2,1),('10020','10007','=','O',3,1),('10021','10008','10007','T',1,1),('10022','10008','true','C',2,1),('10023','10008','=','O',3,1),('10024','10010','10008','T',1,1),('10025','10010','true','C',2,1),('10026','10010','=','O',3,1),('10027','10011','10009','T',1,1),('10028','10011','true','C',2,1),('10029','10011','=','O',3,1),('10030','10012','10010','T',1,1),('10031','10012','true','C',2,1),('10032','10012','=','O',3,1),('10033','10014','10011','T',1,1),('10034','10014','true','C',2,1),('10035','10014','=','O',3,1),('10036','10015','10012','T',1,1),('10037','10015','1','C',2,1),('10038','10015','=','O',3,1),('10039','10017','10013','T',1,1),('10040','10017','1','C',2,1),('10041','10017','=','O',3,1),('10042','10018','10014','T',1,1),('10043','10018','true','C',2,1),('10044','10018','=','O',3,1),('10045','10019','10015','T',1,1),('10046','10019','true','C',2,1),('10047','10019','=','O',3,1),('10048','10020','10016','T',1,1),('10049','10020','true','C',2,1),('10050','10020','=','O',3,1),('10051','10022','10017','T',1,1),('10052','10022','1','C',2,1),('10053','10022','=','O',3,1),('10054','10023','10018','T',1,1),('10055','10023','true','C',2,1),('10056','10023','=','O',3,1),('10057','10024','10019','T',1,1),('10058','10024','true','C',2,1),('10059','10024','=','O',3,1),('10060','10026','10020','T',1,1),('10061','10026','true','C',2,1),('10062','10026','=','O',3,1),('10063','10027','10021','T',1,1),('10064','10027','true','C',2,1),('10065','10027','=','O',3,1),('10066','10029','10022','T',1,1),('10067','10029','true','C',2,1),('10068','10029','=','O',3,1),('10069','10031','10023','T',1,1),('10070','10031','true','C',2,1),('10071','10031','=','O',3,1),('10072','10032','10024','T',1,1),('10073','10032','true','C',2,1),('10074','10032','=','O',3,1),('10075','10033','10025','T',1,1),('10076','10033','true','C',2,1),('10077','10033','=','O',3,1),('10078','10034','10026','T',1,1),('10079','10034','true','C',2,1),('10080','10034','=','O',3,1),('10081','10035','10027','T',1,1),('10082','10035','true','C',2,1),('10083','10035','=','O',3,1),('10084','10037','10028','T',1,1),('10085','10037','true','C',2,1),('10086','10037','=','O',3,1),('10087','10038','10029','T',1,1),('10088','10038','1','C',2,1),('10089','10038','=','O',3,1),('10090','10039','10030','T',1,1),('10091','10039','true','C',2,1),('10092','10039','=','O',3,1),('10093','10040','10031','T',1,1),('10094','10040','true','C',2,1),('10095','10040','=','O',3,1),('10096','10041','10032','T',1,1),('10097','10041','true','C',2,1),('10098','10041','=','O',3,1),('10099','10042','10033','T',1,1),('10100','10042','true','C',2,1),('10101','10042','=','O',3,1),('10102','10043','10034','T',1,1),('10103','10043','true','C',2,1),('10104','10043','=','O',3,1),('10105','10044','10035','T',1,1),('10106','10044','true','C',2,1),('10107','10044','=','O',3,1),('10108','10045','10036','T',1,1),('10109','10045','true','C',2,1),('10110','10045','=','O',3,1),('10111','10046','10037','T',1,1),('10112','10046','true','C',2,1),('10113','10046','=','O',3,1),('10114','10047','10038','T',1,1),('10115','10047','true','C',2,1),('10116','10047','=','O',3,1),('10117','10048','10039','T',1,1),('10118','10048','true','C',2,1),('10119','10048','=','O',3,1),('10120','10049','10040','T',1,1),('10121','10049','true','C',2,1),('10122','10049','=','O',3,1),('10123','10050','10041','T',1,1),('10124','10050','true','C',2,1),('10125','10050','=','O',3,1),('10126','10051','10042','T',1,1),('10127','10051','true','C',2,1),('10128','10051','=','O',3,1),('10129','10052','10043','T',1,1),('10130','10052','true','C',2,1),('10131','10052','=','O',3,1),('10132','10054','10044','T',1,1),('10133','10054','1','C',2,1),('10134','10054','=','O',3,1),('10135','10055','10045','T',1,1),('10136','10055','true','C',2,1),('10137','10055','=','O',3,1),('10138','10056','10046','T',1,1),('10139','10056','1','C',2,1),('10140','10056','=','O',3,1),('10141','10057','10047','T',1,1),('10142','10057','true','C',2,1),('10143','10057','=','O',3,1),('10144','10059','10048','T',1,1),('10145','10059','true','C',2,1),('10146','10059','=','O',3,1),('10147','10060','10049','T',1,1),('10148','10060','true','C',2,1),('10149','10060','=','O',3,1),('10150','10062','10050','T',1,1),('10151','10062','true','C',2,1),('10152','10062','=','O',3,1),('10153','10063','10051','T',1,1),('10154','10063','true','C',2,1),('10155','10063','=','O',3,1),('10156','10065','10052','T',1,1),('10157','10065','true','C',2,1),('10158','10065','=','O',3,1),('10159','10066','10053','T',1,1),('10160','10066','true','C',2,1),('10161','10066','=','O',3,1),('10162','10068','10054','T',1,1),('10163','10068','true','C',2,1),('10164','10068','=','O',3,1),('10165','10069','10055','T',1,1),('10166','10069','true','C',2,1),('10167','10069','=','O',3,1),('10168','10070','10056','T',1,1),('10169','10070','true','C',2,1),('10170','10070','=','O',3,1),('10171','10071','10057','T',1,1),('10172','10071','true','C',2,1),('10173','10071','=','O',3,1),('10174','10073','10058','T',1,1),('10175','10073','1','C',2,1),('10176','10073','=','O',3,1),('10177','10074','10059','T',1,1),('10178','10074','true','C',2,1),('10179','10074','=','O',3,1),('10180','10076','10060','T',1,1),('10181','10076','true','C',2,1),('10182','10076','=','O',3,1),('10183','10077','10061','T',1,1),('10184','10077','1','C',2,1),('10185','10077','=','O',3,1),('10186','10078','10062','T',1,1),('10187','10078','1','C',2,1),('10188','10078','=','O',3,1),('10189','10079','10063','T',1,1),('10190','10079','true','C',2,1),('10191','10079','=','O',3,1),('10192','10080','10064','T',1,1),('10193','10080','true','C',2,1),('10194','10080','=','O',3,1),('10195','10081','10065','T',1,1),('10196','10081','true','C',2,1),('10197','10081','=','O',3,1),('10198','10083','10066','T',1,1),('10199','10083','1','C',2,1),('10200','10083','=','O',3,1),('10201','10084','10067','T',1,1),('10202','10084','true','C',2,1),('10203','10084','=','O',3,1),('10204','10085','10068','T',1,1),('10205','10085','true','C',2,1),('10206','10085','=','O',3,1),('10207','10086','10069','T',1,1),('10208','10086','true','C',2,1),('10209','10086','=','O',3,1),('10210','10087','10070','T',1,1),('10211','10087','true','C',2,1),('10212','10087','=','O',3,1),('10213','10088','10071','T',1,1),('10214','10088','true','C',2,1),('10215','10088','=','O',3,1),('10216','10089','10072','T',1,1),('10217','10089','true','C',2,1),('10218','10089','=','O',3,1),('10219','10091','10073','T',1,1),('10220','10091','1','C',2,1),('10221','10091','=','O',3,1),('10222','10092','10074','T',1,1),('10223','10092','true','C',2,1),('10224','10092','=','O',3,1),('10225','10093','10075','T',1,1),('10226','10093','true','C',2,1),('10227','10093','=','O',3,1),('10228','10095','10076','T',1,1),('10229','10095','true','C',2,1),('10230','10095','=','O',3,1);
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

-- Dump completed on 2013-02-02 19:33:00
