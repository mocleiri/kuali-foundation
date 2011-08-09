-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml
-- Ran at: 8/8/11 6:38 PM
-- Against: LIQUIBASE@localhost@jdbc:mysql://localhost/LIQUIBASE
-- Liquibase version: 2.0.4-SNAPSHOT
-- *********************************************************************

-- Create Database Lock Table
CREATE TABLE `DATABASECHANGELOGLOCK` (`ID` INT NOT NULL, `LOCKED` TINYINT(1) NOT NULL, `LOCKGRANTED` DATETIME, `LOCKEDBY` VARCHAR(255), CONSTRAINT `PK_DATABASECHANGELOGLOCK` PRIMARY KEY (`ID`));

INSERT INTO `DATABASECHANGELOGLOCK` (`ID`, `LOCKED`) VALUES (1, 0);

-- Lock Database
-- Create Database Change Log Table
CREATE TABLE `DATABASECHANGELOG` (`ID` VARCHAR(63) NOT NULL, `AUTHOR` VARCHAR(63) NOT NULL, `FILENAME` VARCHAR(200) NOT NULL, `DATEEXECUTED` DATETIME NOT NULL, `ORDEREXECUTED` INT NOT NULL, `EXECTYPE` VARCHAR(10) NOT NULL, `MD5SUM` VARCHAR(35), `DESCRIPTION` VARCHAR(255), `COMMENTS` VARCHAR(255), `TAG` VARCHAR(255), `LIQUIBASE` VARCHAR(20), CONSTRAINT `PK_DATABASECHANGELOG` PRIMARY KEY (`ID`, `AUTHOR`, `FILENAME`));

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-1::Jeff Caddel (generated)::(Checksum: 3:d48fdf4d5995d53d3bc84ee220e734ab)
CREATE TABLE `acct_dd_attr_doc` (`DOC_HDR_ID` VARCHAR(14) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36), `VER_NBR` DECIMAL(14,0), `ACCT_NUM` DECIMAL(14,0) NOT NULL, `ACCT_OWNR` VARCHAR(50) NOT NULL, `ACCT_BAL` DECIMAL(16,2) NOT NULL, `ACCT_OPN_DAT` DATETIME NOT NULL, `ACCT_STAT` VARCHAR(30) NOT NULL, `ACCT_UPDATE_DT_TM` DATETIME, `ACCT_AWAKE` VARCHAR(1), CONSTRAINT `PK_ACCT_DD_ATTR_DOC` PRIMARY KEY (`DOC_HDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-1', '2.0.4-SNP', '3:d48fdf4d5995d53d3bc84ee220e734ab', 1);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-2::Jeff Caddel (generated)::(Checksum: 3:84e6d7971d53bb66a712307705ca0aab)
CREATE TABLE `kr_kim_test_bo` (`PK` VARCHAR(40), `PRNCPL_ID` VARCHAR(40));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-2', '2.0.4-SNP', '3:84e6d7971d53bb66a712307705ca0aab', 2);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-3::Jeff Caddel (generated)::(Checksum: 3:775a86e024e889f6e7af9a9ca2670234)
CREATE TABLE `krcr_cmpnt_t` (`NMSPC_CD` VARCHAR(20) DEFAULT '' NOT NULL, `CMPNT_CD` VARCHAR(100) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(255), `ACTV_IND` CHAR(1) DEFAULT 'Y' NOT NULL, CONSTRAINT `PK_KRCR_CMPNT_T` PRIMARY KEY (`NMSPC_CD`, `CMPNT_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-3', '2.0.4-SNP', '3:775a86e024e889f6e7af9a9ca2670234', 3);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-4::Jeff Caddel (generated)::(Checksum: 3:64ec52aee96d7ee7e6a745826aff1fd1)
CREATE TABLE `krcr_nmspc_t` (`NMSPC_CD` VARCHAR(20) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(40), `ACTV_IND` CHAR(1) DEFAULT 'Y' NOT NULL, `APPL_ID` VARCHAR(20), CONSTRAINT `PK_KRCR_NMSPC_T` PRIMARY KEY (`NMSPC_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-4', '2.0.4-SNP', '3:64ec52aee96d7ee7e6a745826aff1fd1', 4);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-5::Jeff Caddel (generated)::(Checksum: 3:cbdbcc238cfeab392971f047c5be70fe)
CREATE TABLE `krcr_parm_t` (`NMSPC_CD` VARCHAR(20) DEFAULT '' NOT NULL, `CMPNT_CD` VARCHAR(100) DEFAULT '' NOT NULL, `PARM_NM` VARCHAR(255) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `PARM_TYP_CD` VARCHAR(5) NOT NULL, `VAL` VARCHAR(4000), `PARM_DESC_TXT` VARCHAR(4000), `EVAL_OPRTR_CD` VARCHAR(1), `APPL_ID` VARCHAR(20) DEFAULT 'KUALI' NOT NULL, CONSTRAINT `PK_KRCR_PARM_T` PRIMARY KEY (`NMSPC_CD`, `CMPNT_CD`, `PARM_NM`, `APPL_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-5', '2.0.4-SNP', '3:cbdbcc238cfeab392971f047c5be70fe', 5);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-6::Jeff Caddel (generated)::(Checksum: 3:63457c69951527fea30454c377ee4e4b)
CREATE TABLE `krcr_parm_typ_t` (`PARM_TYP_CD` VARCHAR(5) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(40), `ACTV_IND` CHAR(1) DEFAULT 'Y' NOT NULL, CONSTRAINT `PK_KRCR_PARM_TYP_T` PRIMARY KEY (`PARM_TYP_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-6', '2.0.4-SNP', '3:63457c69951527fea30454c377ee4e4b', 6);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-7::Jeff Caddel (generated)::(Checksum: 3:98bea80250d0a07aec650a87a45532dc)
CREATE TABLE `kren_chnl_prodcr_t` (`CHNL_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `PRODCR_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KREN_CHNL_PRODCR_T` PRIMARY KEY (`CHNL_ID`, `PRODCR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-7', '2.0.4-SNP', '3:98bea80250d0a07aec650a87a45532dc', 7);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-8::Jeff Caddel (generated)::(Checksum: 3:cfedb760cad1157419f60420f81b7854)
CREATE TABLE `kren_chnl_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_CHNL_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-8', '2.0.4-SNP', '3:cfedb760cad1157419f60420f81b7854', 8);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-9::Jeff Caddel (generated)::(Checksum: 3:a13898c33d6171c577426234d1d815dc)
CREATE TABLE `kren_chnl_subscrp_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_CHNL_SUBSCRP_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-9', '2.0.4-SNP', '3:a13898c33d6171c577426234d1d815dc', 9);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-10::Jeff Caddel (generated)::(Checksum: 3:6549a2e3ff86392c5c02785f885166f2)
CREATE TABLE `kren_chnl_subscrp_t` (`CHNL_SUBSCRP_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `CHNL_ID` DECIMAL(8,0) NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, `OBJ_ID` VARCHAR(36), `VER_NBR` DECIMAL(8,0), CONSTRAINT `PK_KREN_CHNL_SUBSCRP_T` PRIMARY KEY (`CHNL_SUBSCRP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-10', '2.0.4-SNP', '3:6549a2e3ff86392c5c02785f885166f2', 10);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-11::Jeff Caddel (generated)::(Checksum: 3:ecc0d7c92ffd53c7a0724122ba40378c)
CREATE TABLE `kren_chnl_t` (`CHNL_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `NM` VARCHAR(200) NOT NULL, `DESC_TXT` VARCHAR(4000) NOT NULL, `SUBSCRB_IND` CHAR(1) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36), CONSTRAINT `PK_KREN_CHNL_T` PRIMARY KEY (`CHNL_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-11', '2.0.4-SNP', '3:ecc0d7c92ffd53c7a0724122ba40378c', 11);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-12::Jeff Caddel (generated)::(Checksum: 3:7c5920221804bf6fa76dcaa37a488281)
CREATE TABLE `kren_cntnt_typ_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_CNTNT_TYP_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-12', '2.0.4-SNP', '3:7c5920221804bf6fa76dcaa37a488281', 12);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-13::Jeff Caddel (generated)::(Checksum: 3:744348462e53f827565b24c88149fdcb)
CREATE TABLE `kren_cntnt_typ_t` (`CNTNT_TYP_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `NM` VARCHAR(200) NOT NULL, `CUR_IND` CHAR(1) DEFAULT 'T' NOT NULL, `CNTNT_TYP_VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, `DESC_TXT` VARCHAR(1000) NOT NULL, `NMSPC_CD` VARCHAR(1000) NOT NULL, `XSD` LONGTEXT NOT NULL, `XSL` LONGTEXT NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36), CONSTRAINT `PK_KREN_CNTNT_TYP_T` PRIMARY KEY (`CNTNT_TYP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-13', '2.0.4-SNP', '3:744348462e53f827565b24c88149fdcb', 13);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-14::Jeff Caddel (generated)::(Checksum: 3:18611c92cd9ecea82bfaa019f041fa0d)
CREATE TABLE `kren_msg_deliv_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_MSG_DELIV_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-14', '2.0.4-SNP', '3:18611c92cd9ecea82bfaa019f041fa0d', 14);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-15::Jeff Caddel (generated)::(Checksum: 3:0eda28237e1823132c6aff665c4a1c86)
CREATE TABLE `kren_msg_deliv_t` (`MSG_DELIV_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `MSG_ID` DECIMAL(8,0) NOT NULL, `TYP_NM` VARCHAR(200) NOT NULL, `SYS_ID` VARCHAR(300), `STAT_CD` VARCHAR(15) NOT NULL, `PROC_CNT` DECIMAL(4,0) DEFAULT 0 NOT NULL, `LOCKD_DTTM` DATETIME, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KREN_MSG_DELIV_T` PRIMARY KEY (`MSG_DELIV_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-15', '2.0.4-SNP', '3:0eda28237e1823132c6aff665c4a1c86', 15);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-16::Jeff Caddel (generated)::(Checksum: 3:51e0070435fc18cfd40d08f47c768699)
CREATE TABLE `kren_msg_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_MSG_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-16', '2.0.4-SNP', '3:51e0070435fc18cfd40d08f47c768699', 16);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-17::Jeff Caddel (generated)::(Checksum: 3:eb52f9fd0b84ef192c25894af0444b65)
CREATE TABLE `kren_msg_t` (`MSG_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `ORGN_ID` VARCHAR(128), `DELIV_TYP` VARCHAR(500) NOT NULL, `CRTE_DTTM` DATETIME NOT NULL, `TTL` VARCHAR(255), `CHNL` VARCHAR(300) NOT NULL, `PRODCR` VARCHAR(300), `CNTNT` LONGTEXT NOT NULL, `CNTNT_TYP` VARCHAR(128), `URL` VARCHAR(512), `RECIP_ID` VARCHAR(300) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KREN_MSG_T` PRIMARY KEY (`MSG_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-17', '2.0.4-SNP', '3:eb52f9fd0b84ef192c25894af0444b65', 17);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-18::Jeff Caddel (generated)::(Checksum: 3:e1f80879584dbbc75c7c68795a0c9849)
CREATE TABLE `kren_ntfctn_msg_deliv_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_NTFCTN_MSG_DELIV_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-18', '2.0.4-SNP', '3:e1f80879584dbbc75c7c68795a0c9849', 18);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-19::Jeff Caddel (generated)::(Checksum: 3:dffe370ae13d46168fbfcbfdd3cba6e8)
CREATE TABLE `kren_ntfctn_msg_deliv_t` (`NTFCTN_MSG_DELIV_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `NTFCTN_ID` DECIMAL(8,0) NOT NULL, `RECIP_ID` VARCHAR(40) NOT NULL, `STAT_CD` VARCHAR(15) NOT NULL, `SYS_ID` VARCHAR(300), `LOCKD_DTTM` DATETIME, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, `OBJ_ID` VARCHAR(36), CONSTRAINT `PK_KREN_NTFCTN_MSG_DELIV_T` PRIMARY KEY (`NTFCTN_MSG_DELIV_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-19', '2.0.4-SNP', '3:dffe370ae13d46168fbfcbfdd3cba6e8', 19);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-20::Jeff Caddel (generated)::(Checksum: 3:fb68ade9d46bb16c78ede9ce1ac14d46)
CREATE TABLE `kren_ntfctn_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_NTFCTN_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-20', '2.0.4-SNP', '3:fb68ade9d46bb16c78ede9ce1ac14d46', 20);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-21::Jeff Caddel (generated)::(Checksum: 3:0d0542dd90f3e9d64b4a4a7136a2c55a)
CREATE TABLE `kren_ntfctn_t` (`NTFCTN_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `DELIV_TYP` VARCHAR(3) NOT NULL, `CRTE_DTTM` DATETIME NOT NULL, `SND_DTTM` DATETIME, `AUTO_RMV_DTTM` DATETIME, `PRIO_ID` DECIMAL(8,0) NOT NULL, `TTL` VARCHAR(255), `CNTNT` LONGTEXT NOT NULL, `CNTNT_TYP_ID` DECIMAL(8,0) NOT NULL, `CHNL_ID` DECIMAL(8,0) NOT NULL, `PRODCR_ID` DECIMAL(8,0) NOT NULL, `PROCESSING_FLAG` VARCHAR(15) NOT NULL, `LOCKD_DTTM` DATETIME, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, `OBJ_ID` VARCHAR(36), CONSTRAINT `PK_KREN_NTFCTN_T` PRIMARY KEY (`NTFCTN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-21', '2.0.4-SNP', '3:0d0542dd90f3e9d64b4a4a7136a2c55a', 21);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-22::Jeff Caddel (generated)::(Checksum: 3:c8de048b7a57aa3cbe43100dc15d1037)
CREATE TABLE `kren_prio_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_PRIO_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-22', '2.0.4-SNP', '3:c8de048b7a57aa3cbe43100dc15d1037', 22);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-23::Jeff Caddel (generated)::(Checksum: 3:b9f6d28088bc7a4f17158860c0c95fa9)
CREATE TABLE `kren_prio_t` (`PRIO_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `NM` VARCHAR(40) NOT NULL, `DESC_TXT` VARCHAR(500) NOT NULL, `PRIO_ORD` DECIMAL(4,0) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36), CONSTRAINT `PK_KREN_PRIO_T` PRIMARY KEY (`PRIO_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-23', '2.0.4-SNP', '3:b9f6d28088bc7a4f17158860c0c95fa9', 23);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-24::Jeff Caddel (generated)::(Checksum: 3:0904ec8de86fe512a2c59b3473faf150)
CREATE TABLE `kren_prodcr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_PRODCR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-24', '2.0.4-SNP', '3:0904ec8de86fe512a2c59b3473faf150', 24);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-25::Jeff Caddel (generated)::(Checksum: 3:e1d9725c2101888f230cef0c788925ad)
CREATE TABLE `kren_prodcr_t` (`PRODCR_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `NM` VARCHAR(200) NOT NULL, `DESC_TXT` VARCHAR(1000) NOT NULL, `CNTCT_INFO` VARCHAR(1000) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36), CONSTRAINT `PK_KREN_PRODCR_T` PRIMARY KEY (`PRODCR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-25', '2.0.4-SNP', '3:e1d9725c2101888f230cef0c788925ad', 25);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-26::Jeff Caddel (generated)::(Checksum: 3:1a7b31ecd55656170a0b195bc34bd138)
CREATE TABLE `kren_recip_deliv_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_RECIP_DELIV_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-26', '2.0.4-SNP', '3:1a7b31ecd55656170a0b195bc34bd138', 26);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-27::Jeff Caddel (generated)::(Checksum: 3:2c34548b14440f39610a2f4a6be116a4)
CREATE TABLE `kren_recip_deliv_t` (`RECIP_DELIV_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `RECIP_ID` VARCHAR(40) NOT NULL, `CHNL` VARCHAR(300) NOT NULL, `NM` VARCHAR(200) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KREN_RECIP_DELIV_T` PRIMARY KEY (`RECIP_DELIV_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-27', '2.0.4-SNP', '3:2c34548b14440f39610a2f4a6be116a4', 27);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-28::Jeff Caddel (generated)::(Checksum: 3:837bdd5d2fd5d5f4f72f8fde905e4934)
CREATE TABLE `kren_recip_list_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_RECIP_LIST_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-28', '2.0.4-SNP', '3:837bdd5d2fd5d5f4f72f8fde905e4934', 28);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-29::Jeff Caddel (generated)::(Checksum: 3:6bf263d60a09210c962d7adce3f6989c)
CREATE TABLE `kren_recip_list_t` (`RECIP_LIST_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `CHNL_ID` DECIMAL(8,0) NOT NULL, `RECIP_TYP_CD` VARCHAR(10) NOT NULL, `RECIP_ID` VARCHAR(40) NOT NULL, `OBJ_ID` VARCHAR(36), `VER_NBR` DECIMAL(8,0), CONSTRAINT `PK_KREN_RECIP_LIST_T` PRIMARY KEY (`RECIP_LIST_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-29', '2.0.4-SNP', '3:6bf263d60a09210c962d7adce3f6989c', 29);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-30::Jeff Caddel (generated)::(Checksum: 3:b55db03628f4651b80f6b2ded6235c52)
CREATE TABLE `kren_recip_pref_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_RECIP_PREF_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-30', '2.0.4-SNP', '3:b55db03628f4651b80f6b2ded6235c52', 30);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-31::Jeff Caddel (generated)::(Checksum: 3:15b6827b6e900751615d44ad15ee974d)
CREATE TABLE `kren_recip_prefs_t` (`RECIP_PREFS_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `RECIP_ID` VARCHAR(40) NOT NULL, `PROP` VARCHAR(200) NOT NULL, `VAL` VARCHAR(1000) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KREN_RECIP_PREFS_T` PRIMARY KEY (`RECIP_PREFS_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-31', '2.0.4-SNP', '3:15b6827b6e900751615d44ad15ee974d', 31);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-32::Jeff Caddel (generated)::(Checksum: 3:116a9c20749130fa6e37b89e845c21f8)
CREATE TABLE `kren_recip_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_RECIP_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-32', '2.0.4-SNP', '3:116a9c20749130fa6e37b89e845c21f8', 32);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-33::Jeff Caddel (generated)::(Checksum: 3:f9277893d880b97f57ae33bb31ad152e)
CREATE TABLE `kren_recip_t` (`RECIP_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `NTFCTN_ID` DECIMAL(8,0) NOT NULL, `RECIP_TYP_CD` VARCHAR(10) NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, `OBJ_ID` VARCHAR(36), `VER_NBR` DECIMAL(8,0), CONSTRAINT `PK_KREN_RECIP_T` PRIMARY KEY (`RECIP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-33', '2.0.4-SNP', '3:f9277893d880b97f57ae33bb31ad152e', 33);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-34::Jeff Caddel (generated)::(Checksum: 3:97fb4538db009e9d2ed94a262a5a8bd4)
CREATE TABLE `kren_rvwer_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_RVWER_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-34', '2.0.4-SNP', '3:97fb4538db009e9d2ed94a262a5a8bd4', 34);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-35::Jeff Caddel (generated)::(Checksum: 3:a125eef34fc5c34f09c47eee1e0e8bf1)
CREATE TABLE `kren_rvwer_t` (`RVWER_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `CHNL_ID` DECIMAL(8,0) NOT NULL, `TYP` VARCHAR(10) NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36), CONSTRAINT `PK_KREN_RVWER_T` PRIMARY KEY (`RVWER_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-35', '2.0.4-SNP', '3:a125eef34fc5c34f09c47eee1e0e8bf1', 35);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-36::Jeff Caddel (generated)::(Checksum: 3:f554325954dfb5df13d5a1de59ad2520)
CREATE TABLE `kren_sndr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREN_SNDR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-36', '2.0.4-SNP', '3:f554325954dfb5df13d5a1de59ad2520', 36);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-37::Jeff Caddel (generated)::(Checksum: 3:f64abbe82e8f68473be0972c13a7b3ff)
CREATE TABLE `kren_sndr_t` (`SNDR_ID` DECIMAL(8,0) DEFAULT 0 NOT NULL, `NTFCTN_ID` DECIMAL(8,0) NOT NULL, `NM` VARCHAR(200) NOT NULL, `OBJ_ID` VARCHAR(36), `VER_NBR` DECIMAL(8,0), CONSTRAINT `PK_KREN_SNDR_T` PRIMARY KEY (`SNDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-37', '2.0.4-SNP', '3:f64abbe82e8f68473be0972c13a7b3ff', 37);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-38::Jeff Caddel (generated)::(Checksum: 3:f950e4d0a6ce7fbc6b0fa7d9dd6e9097)
CREATE TABLE `krew_actn_itm_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_ACTN_ITM_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-38', '2.0.4-SNP', '3:f950e4d0a6ce7fbc6b0fa7d9dd6e9097', 38);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-39::Jeff Caddel (generated)::(Checksum: 3:3b82d0ee4be76fcb39e951428018c4e4)
CREATE TABLE `krew_actn_itm_t` (`ACTN_ITM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, `ASND_DT` DATETIME NOT NULL, `RQST_CD` CHAR(1) NOT NULL, `ACTN_RQST_ID` VARCHAR(40) NOT NULL, `DOC_HDR_ID` VARCHAR(40) NOT NULL, `ROLE_NM` VARCHAR(2000), `DLGN_PRNCPL_ID` VARCHAR(40), `DOC_HDR_TTL` VARCHAR(255), `DOC_TYP_LBL` VARCHAR(128) NOT NULL, `DOC_HDLR_URL` VARCHAR(255) NOT NULL, `DOC_TYP_NM` VARCHAR(64) NOT NULL, `RSP_ID` VARCHAR(40) NOT NULL, `DLGN_TYP` VARCHAR(1), `VER_NBR` DECIMAL(8,0) DEFAULT 0, `DTYPE` VARCHAR(50), `GRP_ID` VARCHAR(40), `DLGN_GRP_ID` VARCHAR(40), `RQST_LBL` VARCHAR(255), CONSTRAINT `PK_KREW_ACTN_ITM_T` PRIMARY KEY (`ACTN_ITM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-39', '2.0.4-SNP', '3:3b82d0ee4be76fcb39e951428018c4e4', 39);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-40::Jeff Caddel (generated)::(Checksum: 3:c318356eece0b03e18d888fe5ca28fda)
CREATE TABLE `krew_actn_list_optn_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_ACTN_LIST_OPTN_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-40', '2.0.4-SNP', '3:c318356eece0b03e18d888fe5ca28fda', 40);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-41::Jeff Caddel (generated)::(Checksum: 3:728b7020dd9ff9cb11ee2b62735828b0)
CREATE TABLE `krew_actn_rqst_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_ACTN_RQST_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-41', '2.0.4-SNP', '3:728b7020dd9ff9cb11ee2b62735828b0', 41);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-42::Jeff Caddel (generated)::(Checksum: 3:ca7c62710a191e4e9b9cb2e1bf9ea392)
CREATE TABLE `krew_actn_rqst_t` (`ACTN_RQST_ID` VARCHAR(40) DEFAULT '' NOT NULL, `PARNT_ID` VARCHAR(40), `ACTN_RQST_CD` CHAR(1) NOT NULL, `DOC_HDR_ID` VARCHAR(40) NOT NULL, `RULE_ID` VARCHAR(40), `STAT_CD` CHAR(1) NOT NULL, `RSP_ID` VARCHAR(40) NOT NULL, `PRNCPL_ID` VARCHAR(40), `ROLE_NM` VARCHAR(2000), `QUAL_ROLE_NM` VARCHAR(2000), `QUAL_ROLE_NM_LBL_TXT` VARCHAR(2000), `RECIP_TYP_CD` CHAR(1), `PRIO_NBR` DECIMAL(8,0) NOT NULL, `RTE_TYP_NM` VARCHAR(255), `RTE_LVL_NBR` DECIMAL(8,0) NOT NULL, `RTE_NODE_INSTN_ID` VARCHAR(40), `ACTN_TKN_ID` VARCHAR(40), `DOC_VER_NBR` DECIMAL(8,0) NOT NULL, `CRTE_DT` DATETIME NOT NULL, `RSP_DESC_TXT` VARCHAR(200), `FRC_ACTN` DECIMAL(1,0) DEFAULT 0, `ACTN_RQST_ANNOTN_TXT` VARCHAR(2000), `DLGN_TYP` CHAR(1), `APPR_PLCY` CHAR(1), `CUR_IND` DECIMAL(1,0) DEFAULT 1, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `GRP_ID` VARCHAR(40), `RQST_LBL` VARCHAR(255), CONSTRAINT `PK_KREW_ACTN_RQST_T` PRIMARY KEY (`ACTN_RQST_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-42', '2.0.4-SNP', '3:ca7c62710a191e4e9b9cb2e1bf9ea392', 42);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-43::Jeff Caddel (generated)::(Checksum: 3:2c3531bc92c940b9cd152947e6a9e9dd)
CREATE TABLE `krew_actn_tkn_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_ACTN_TKN_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-43', '2.0.4-SNP', '3:2c3531bc92c940b9cd152947e6a9e9dd', 43);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-44::Jeff Caddel (generated)::(Checksum: 3:a4ccc7ae27600215a8cc40e3ce1a03e9)
CREATE TABLE `krew_actn_tkn_t` (`ACTN_TKN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_HDR_ID` VARCHAR(40) NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, `DLGTR_PRNCPL_ID` VARCHAR(40), `ACTN_CD` CHAR(1) NOT NULL, `ACTN_DT` DATETIME NOT NULL, `DOC_VER_NBR` DECIMAL(8,0) NOT NULL, `ANNOTN` VARCHAR(2000), `CUR_IND` DECIMAL(1,0) DEFAULT 1, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `DLGTR_GRP_ID` VARCHAR(40), CONSTRAINT `PK_KREW_ACTN_TKN_T` PRIMARY KEY (`ACTN_TKN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-44', '2.0.4-SNP', '3:a4ccc7ae27600215a8cc40e3ce1a03e9', 44);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-45::Jeff Caddel (generated)::(Checksum: 3:d8910b01927479bb9d8033875e0e6c4c)
CREATE TABLE `krew_app_doc_stat_tran_t` (`APP_DOC_STAT_TRAN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_HDR_ID` VARCHAR(40), `APP_DOC_STAT_FROM` VARCHAR(64), `APP_DOC_STAT_TO` VARCHAR(64), `STAT_TRANS_DATE` DATETIME, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_APP_DOC_STAT_TRAN_T` PRIMARY KEY (`APP_DOC_STAT_TRAN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-45', '2.0.4-SNP', '3:d8910b01927479bb9d8033875e0e6c4c', 45);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-46::Jeff Caddel (generated)::(Checksum: 3:3bfae8db70a123915567a29aec1f4df6)
CREATE TABLE `krew_att_t` (`ATTACHMENT_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NTE_ID` VARCHAR(40), `FILE_NM` VARCHAR(255) NOT NULL, `FILE_LOC` VARCHAR(255) NOT NULL, `MIME_TYP` VARCHAR(255) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_ATT_T` PRIMARY KEY (`ATTACHMENT_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-46', '2.0.4-SNP', '3:3bfae8db70a123915567a29aec1f4df6', 46);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-47::Jeff Caddel (generated)::(Checksum: 3:55958290999e415d80ac1aed6a8324b8)
CREATE TABLE `krew_dlgn_rsp_t` (`DLGN_RULE_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RSP_ID` VARCHAR(40) NOT NULL, `DLGN_RULE_BASE_VAL_ID` VARCHAR(40) NOT NULL, `DLGN_TYP` VARCHAR(20) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_DLGN_RSP_T` PRIMARY KEY (`DLGN_RULE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-47', '2.0.4-SNP', '3:55958290999e415d80ac1aed6a8324b8', 47);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-48::Jeff Caddel (generated)::(Checksum: 3:2fa0ce1634feb24cd1afec7934584608)
CREATE TABLE `krew_doc_hdr_cntnt_t` (`DOC_HDR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_CNTNT_TXT` LONGTEXT, CONSTRAINT `PK_KREW_DOC_HDR_CNTNT_T` PRIMARY KEY (`DOC_HDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-48', '2.0.4-SNP', '3:2fa0ce1634feb24cd1afec7934584608', 48);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-49::Jeff Caddel (generated)::(Checksum: 3:13dc686142ee64327ce8313e5a2255c3)
CREATE TABLE `krew_doc_hdr_ext_dt_t` (`DOC_HDR_EXT_DT_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_HDR_ID` VARCHAR(40) NOT NULL, `KEY_CD` VARCHAR(256) NOT NULL, `VAL` DATETIME, CONSTRAINT `PK_KREW_DOC_HDR_EXT_DT_T` PRIMARY KEY (`DOC_HDR_EXT_DT_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-49', '2.0.4-SNP', '3:13dc686142ee64327ce8313e5a2255c3', 49);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-50::Jeff Caddel (generated)::(Checksum: 3:84c68e8fd772a57c0d0a61efe694fbca)
CREATE TABLE `krew_doc_hdr_ext_flt_t` (`DOC_HDR_EXT_FLT_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_HDR_ID` VARCHAR(40) NOT NULL, `KEY_CD` VARCHAR(256) NOT NULL, `VAL` DECIMAL(30,15), CONSTRAINT `PK_KREW_DOC_HDR_EXT_FLT_T` PRIMARY KEY (`DOC_HDR_EXT_FLT_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-50', '2.0.4-SNP', '3:84c68e8fd772a57c0d0a61efe694fbca', 50);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-51::Jeff Caddel (generated)::(Checksum: 3:318368e336b22cb96f3c54e2f45624d4)
CREATE TABLE `krew_doc_hdr_ext_long_t` (`DOC_HDR_EXT_LONG_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_HDR_ID` VARCHAR(40) NOT NULL, `KEY_CD` VARCHAR(256) NOT NULL, `VAL` DECIMAL(22,0), CONSTRAINT `PK_KREW_DOC_HDR_EXT_LONG_T` PRIMARY KEY (`DOC_HDR_EXT_LONG_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-51', '2.0.4-SNP', '3:318368e336b22cb96f3c54e2f45624d4', 51);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-52::Jeff Caddel (generated)::(Checksum: 3:9f92328ba3a1823a513c2efbd0164246)
CREATE TABLE `krew_doc_hdr_ext_t` (`DOC_HDR_EXT_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_HDR_ID` VARCHAR(40) NOT NULL, `KEY_CD` VARCHAR(256) NOT NULL, `VAL` VARCHAR(2000), CONSTRAINT `PK_KREW_DOC_HDR_EXT_T` PRIMARY KEY (`DOC_HDR_EXT_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-52', '2.0.4-SNP', '3:9f92328ba3a1823a513c2efbd0164246', 52);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-53::Jeff Caddel (generated)::(Checksum: 3:1dba62c47508245d210cc40c0bcc7910)
CREATE TABLE `krew_doc_hdr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_DOC_HDR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-53', '2.0.4-SNP', '3:1dba62c47508245d210cc40c0bcc7910', 53);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-54::Jeff Caddel (generated)::(Checksum: 3:4c0d6555230f45f90bc276a6dde4882f)
CREATE TABLE `krew_doc_hdr_t` (`DOC_HDR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_TYP_ID` VARCHAR(40), `DOC_HDR_STAT_CD` CHAR(1) NOT NULL, `RTE_LVL` DECIMAL(8,0) NOT NULL, `STAT_MDFN_DT` DATETIME NOT NULL, `CRTE_DT` DATETIME NOT NULL, `APRV_DT` DATETIME, `FNL_DT` DATETIME, `RTE_STAT_MDFN_DT` DATETIME, `RTE_LVL_MDFN_DT` DATETIME, `TTL` VARCHAR(255), `APP_DOC_ID` VARCHAR(255), `DOC_VER_NBR` DECIMAL(8,0) NOT NULL, `INITR_PRNCPL_ID` VARCHAR(40) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `RTE_PRNCPL_ID` VARCHAR(40), `DTYPE` VARCHAR(50), `OBJ_ID` VARCHAR(36) NOT NULL, `APP_DOC_STAT` VARCHAR(64), `APP_DOC_STAT_MDFN_DT` DATETIME, CONSTRAINT `PK_KREW_DOC_HDR_T` PRIMARY KEY (`DOC_HDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-54', '2.0.4-SNP', '3:4c0d6555230f45f90bc276a6dde4882f', 54);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-55::Jeff Caddel (generated)::(Checksum: 3:2413c0defdca3d0a9ed97405c104db6a)
CREATE TABLE `krew_doc_lnk_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_DOC_LNK_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-55', '2.0.4-SNP', '3:2413c0defdca3d0a9ed97405c104db6a', 55);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-56::Jeff Caddel (generated)::(Checksum: 3:f526622fcdb3f4244359d935fcf764dc)
CREATE TABLE `krew_doc_lnk_t` (`DOC_LNK_ID` VARCHAR(40) DEFAULT '' NOT NULL, `ORGN_DOC_ID` VARCHAR(40) NOT NULL, `DEST_DOC_ID` VARCHAR(40) NOT NULL, CONSTRAINT `PK_KREW_DOC_LNK_T` PRIMARY KEY (`DOC_LNK_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-56', '2.0.4-SNP', '3:f526622fcdb3f4244359d935fcf764dc', 56);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-57::Jeff Caddel (generated)::(Checksum: 3:07619d110867c6ce4f41d94513eeec2f)
CREATE TABLE `krew_doc_nte_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_DOC_NTE_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-57', '2.0.4-SNP', '3:07619d110867c6ce4f41d94513eeec2f', 57);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-58::Jeff Caddel (generated)::(Checksum: 3:3212f71df9b05754e553b884e1d5a949)
CREATE TABLE `krew_doc_nte_t` (`DOC_NTE_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_HDR_ID` VARCHAR(40) NOT NULL, `AUTH_PRNCPL_ID` VARCHAR(40) NOT NULL, `CRT_DT` DATETIME NOT NULL, `TXT` VARCHAR(4000), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_DOC_NTE_T` PRIMARY KEY (`DOC_NTE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-58', '2.0.4-SNP', '3:3212f71df9b05754e553b884e1d5a949', 58);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-59::Jeff Caddel (generated)::(Checksum: 3:35721f7ca55fd2a6d02c1d61d265aca9)
CREATE TABLE `krew_doc_typ_app_doc_stat_t` (`DOC_TYP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_STAT_NM` VARCHAR(20) DEFAULT '' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_DOC_TYP_APP_DOC_STAT_T` PRIMARY KEY (`DOC_TYP_ID`, `DOC_STAT_NM`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-59', '2.0.4-SNP', '3:35721f7ca55fd2a6d02c1d61d265aca9', 59);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-60::Jeff Caddel (generated)::(Checksum: 3:569a9770c0fc81cfc9c9778f3d52a5ef)
CREATE TABLE `krew_doc_typ_attr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_DOC_TYP_ATTR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-60', '2.0.4-SNP', '3:569a9770c0fc81cfc9c9778f3d52a5ef', 60);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-61::Jeff Caddel (generated)::(Checksum: 3:c4b00edf06557a1eb4e4aea56d79d3ba)
CREATE TABLE `krew_doc_typ_attr_t` (`DOC_TYP_ATTRIB_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_TYP_ID` VARCHAR(40) NOT NULL, `RULE_ATTR_ID` VARCHAR(40) NOT NULL, `ORD_INDX` DECIMAL(4,0) DEFAULT 0, CONSTRAINT `PK_KREW_DOC_TYP_ATTR_T` PRIMARY KEY (`DOC_TYP_ATTRIB_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-61', '2.0.4-SNP', '3:c4b00edf06557a1eb4e4aea56d79d3ba', 61);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-62::Jeff Caddel (generated)::(Checksum: 3:3defa06b0d56068f64b044125c0d9b67)
CREATE TABLE `krew_doc_typ_plcy_reln_t` (`DOC_TYP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_PLCY_NM` VARCHAR(255) DEFAULT '' NOT NULL, `PLCY_NM` DECIMAL(1,0) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `OBJ_ID` VARCHAR(36) NOT NULL, `PLCY_VAL` VARCHAR(64), `PLCY_DESC` VARCHAR(20), CONSTRAINT `PK_KREW_DOC_TYP_PLCY_RELN_T` PRIMARY KEY (`DOC_TYP_ID`, `DOC_PLCY_NM`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-62', '2.0.4-SNP', '3:3defa06b0d56068f64b044125c0d9b67', 62);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-63::Jeff Caddel (generated)::(Checksum: 3:646b682bb6ac84dbd7d0e6fb539b4f2d)
CREATE TABLE `krew_doc_typ_proc_t` (`DOC_TYP_PROC_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_TYP_ID` VARCHAR(40) NOT NULL, `INIT_RTE_NODE_ID` DECIMAL(22,0), `NM` VARCHAR(255) NOT NULL, `INIT_IND` DECIMAL(1,0) DEFAULT 0 NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_DOC_TYP_PROC_T` PRIMARY KEY (`DOC_TYP_PROC_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-63', '2.0.4-SNP', '3:646b682bb6ac84dbd7d0e6fb539b4f2d', 63);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-64::Jeff Caddel (generated)::(Checksum: 3:f2fa6052a42ff20dee002a8507cff247)
CREATE TABLE `krew_doc_typ_t` (`DOC_TYP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `PARNT_ID` VARCHAR(40), `DOC_TYP_NM` VARCHAR(64), `DOC_TYP_VER_NBR` DECIMAL(10,0) DEFAULT 0, `ACTV_IND` DECIMAL(1,0), `CUR_IND` DECIMAL(1,0), `LBL` VARCHAR(128), `PREV_DOC_TYP_VER_NBR` VARCHAR(40), `DOC_TYP_DESC` VARCHAR(4000), `DOC_HDLR_URL` VARCHAR(255), `POST_PRCSR` VARCHAR(255), `JNDI_URL` VARCHAR(255), `BLNKT_APPR_PLCY` VARCHAR(10), `ADV_DOC_SRCH_URL` VARCHAR(255), `CSTM_ACTN_LIST_ATTRIB_CLS_NM` VARCHAR(255), `CSTM_ACTN_EMAIL_ATTRIB_CLS_NM` VARCHAR(255), `CSTM_DOC_NTE_ATTRIB_CLS_NM` VARCHAR(255), `RTE_VER_NBR` VARCHAR(2) DEFAULT '1', `NOTIFY_ADDR` VARCHAR(255), `APPL_ID` VARCHAR(255), `EMAIL_XSL` VARCHAR(255), `SEC_XML` LONGTEXT, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `BLNKT_APPR_GRP_ID` VARCHAR(40), `RPT_GRP_ID` VARCHAR(40), `GRP_ID` VARCHAR(40), `HELP_DEF_URL` VARCHAR(4000), `OBJ_ID` VARCHAR(36) NOT NULL, `DOC_SEARCH_HELP_URL` VARCHAR(4000), `DOC_HDR_ID` VARCHAR(40), CONSTRAINT `PK_KREW_DOC_TYP_T` PRIMARY KEY (`DOC_TYP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-64', '2.0.4-SNP', '3:f2fa6052a42ff20dee002a8507cff247', 64);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-65::Jeff Caddel (generated)::(Checksum: 3:3b4b973eaab4350e7f299958491a7397)
CREATE TABLE `krew_edl_assctn_t` (`EDOCLT_ASSOC_ID` DECIMAL(19,0) DEFAULT 0 NOT NULL, `DOC_TYP_NM` VARCHAR(64) NOT NULL, `EDL_DEF_NM` VARCHAR(200), `STYLE_NM` VARCHAR(200), `ACTV_IND` DECIMAL(1,0) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_EDL_ASSCTN_T` PRIMARY KEY (`EDOCLT_ASSOC_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-65', '2.0.4-SNP', '3:3b4b973eaab4350e7f299958491a7397', 65);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-66::Jeff Caddel (generated)::(Checksum: 3:16ba58bf7c08f0464463b59f1c34098a)
CREATE TABLE `krew_edl_def_t` (`EDOCLT_DEF_ID` DECIMAL(19,0) DEFAULT 0 NOT NULL, `NM` VARCHAR(200) NOT NULL, `XML` LONGTEXT NOT NULL, `ACTV_IND` DECIMAL(1,0) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_EDL_DEF_T` PRIMARY KEY (`EDOCLT_DEF_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-66', '2.0.4-SNP', '3:16ba58bf7c08f0464463b59f1c34098a', 66);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-67::Jeff Caddel (generated)::(Checksum: 3:539d46f61f6f897cd035de5b5b22c1cf)
CREATE TABLE `krew_edl_dmp_t` (`DOC_HDR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_TYP_NM` VARCHAR(64) NOT NULL, `DOC_HDR_STAT_CD` CHAR(1) NOT NULL, `DOC_HDR_MDFN_DT` DATETIME NOT NULL, `DOC_HDR_CRTE_DT` DATETIME NOT NULL, `DOC_HDR_TTL` VARCHAR(255), `DOC_HDR_INITR_PRNCPL_ID` VARCHAR(40) NOT NULL, `CRNT_NODE_NM` VARCHAR(30) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_EDL_DMP_T` PRIMARY KEY (`DOC_HDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-67', '2.0.4-SNP', '3:539d46f61f6f897cd035de5b5b22c1cf', 67);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-68::Jeff Caddel (generated)::(Checksum: 3:51517a9cbd8499d7d937deea4377d99b)
CREATE TABLE `krew_edl_fld_dmp_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_EDL_FLD_DMP_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-68', '2.0.4-SNP', '3:51517a9cbd8499d7d937deea4377d99b', 68);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-69::Jeff Caddel (generated)::(Checksum: 3:edc1bb57357b05b2d1eb7d70b989d4ff)
CREATE TABLE `krew_edl_fld_dmp_t` (`EDL_FIELD_DMP_ID` DECIMAL(14,0) DEFAULT 0 NOT NULL, `DOC_HDR_ID` VARCHAR(40) NOT NULL, `FLD_NM` VARCHAR(255) NOT NULL, `FLD_VAL` VARCHAR(4000), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_EDL_FLD_DMP_T` PRIMARY KEY (`EDL_FIELD_DMP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-69', '2.0.4-SNP', '3:edc1bb57357b05b2d1eb7d70b989d4ff', 69);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-70::Jeff Caddel (generated)::(Checksum: 3:0e5a138195354f20f04ddc93c0adf93b)
CREATE TABLE `krew_edl_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_EDL_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-70', '2.0.4-SNP', '3:0e5a138195354f20f04ddc93c0adf93b', 70);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-71::Jeff Caddel (generated)::(Checksum: 3:5bcac382c618ae746b4972c0078b3dfa)
CREATE TABLE `krew_hlp_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_HLP_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-71', '2.0.4-SNP', '3:5bcac382c618ae746b4972c0078b3dfa', 71);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-72::Jeff Caddel (generated)::(Checksum: 3:405c7e7f1cbbf5d954b91459f38ee30f)
CREATE TABLE `krew_hlp_t` (`HLP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NM` VARCHAR(500) NOT NULL, `KEY_CD` VARCHAR(500) NOT NULL, `HLP_TXT` VARCHAR(4000) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_HLP_T` PRIMARY KEY (`HLP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-72', '2.0.4-SNP', '3:405c7e7f1cbbf5d954b91459f38ee30f', 72);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-73::Jeff Caddel (generated)::(Checksum: 3:6104268b5304158ef13eb48ea5981e8a)
CREATE TABLE `krew_init_rte_node_instn_t` (`DOC_HDR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RTE_NODE_INSTN_ID` VARCHAR(40) DEFAULT '' NOT NULL, CONSTRAINT `PK_KREW_INIT_RTE_NODE_INSTN_T` PRIMARY KEY (`DOC_HDR_ID`, `RTE_NODE_INSTN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-73', '2.0.4-SNP', '3:6104268b5304158ef13eb48ea5981e8a', 73);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-74::Jeff Caddel (generated)::(Checksum: 3:d1f0f531b52c9768d98dce03ab9165d7)
CREATE TABLE `krew_out_box_itm_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_OUT_BOX_ITM_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-74', '2.0.4-SNP', '3:d1f0f531b52c9768d98dce03ab9165d7', 74);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-75::Jeff Caddel (generated)::(Checksum: 3:c27ab7516d88051c152ba002b992cbdb)
CREATE TABLE `krew_out_box_itm_t` (`ACTN_ITM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, `ASND_DT` DATETIME NOT NULL, `RQST_CD` CHAR(1) NOT NULL, `ACTN_RQST_ID` VARCHAR(40) NOT NULL, `DOC_HDR_ID` VARCHAR(40) NOT NULL, `ROLE_NM` VARCHAR(2000), `DLGN_PRNCPL_ID` VARCHAR(40), `DOC_HDR_TTL` VARCHAR(255), `DOC_TYP_LBL` VARCHAR(128) NOT NULL, `DOC_HDLR_URL` VARCHAR(255) NOT NULL, `DOC_TYP_NM` VARCHAR(64) NOT NULL, `RSP_ID` VARCHAR(40) NOT NULL, `DLGN_TYP` VARCHAR(1), `VER_NBR` DECIMAL(8,0) DEFAULT 0, `GRP_ID` VARCHAR(40), `DLGN_GRP_ID` VARCHAR(40), `RQST_LBL` VARCHAR(255), CONSTRAINT `PK_KREW_OUT_BOX_ITM_T` PRIMARY KEY (`ACTN_ITM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-75', '2.0.4-SNP', '3:c27ab7516d88051c152ba002b992cbdb', 75);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-76::Jeff Caddel (generated)::(Checksum: 3:2f19d7cfe7d51261f36a80305cdb8dce)
CREATE TABLE `krew_ria_doc_t` (`RIA_ID` DECIMAL(22,0) DEFAULT 0 NOT NULL, `XML_CONTENT` VARCHAR(4000), `RIA_DOC_TYPE_NAME` VARCHAR(100), `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_RIA_DOC_T` PRIMARY KEY (`RIA_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-76', '2.0.4-SNP', '3:2f19d7cfe7d51261f36a80305cdb8dce', 76);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-77::Jeff Caddel (generated)::(Checksum: 3:a405bfa896154a4420a769e89f79b83c)
CREATE TABLE `krew_ria_doctype_map_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_RIA_DOCTYPE_MAP_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-77', '2.0.4-SNP', '3:a405bfa896154a4420a769e89f79b83c', 77);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-78::Jeff Caddel (generated)::(Checksum: 3:2753223a5bd0f156cbf14a35dddf4732)
CREATE TABLE `krew_ria_doctype_map_t` (`ID` DECIMAL(22,0) DEFAULT 0 NOT NULL, `RIA_DOC_TYPE_NAME` VARCHAR(100), `UPDATED_AT` DATETIME, `RIA_URL` VARCHAR(255), `HELP_URL` VARCHAR(255), `EDITABLE` CHAR(1), `INIT_GROUPS` VARCHAR(255), `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_RIA_DOCTYPE_MAP_T` PRIMARY KEY (`ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-78', '2.0.4-SNP', '3:2753223a5bd0f156cbf14a35dddf4732', 78);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-79::Jeff Caddel (generated)::(Checksum: 3:aa7038e5b937bec5aa20fd8e54077402)
CREATE TABLE `krew_rmv_rplc_doc_t` (`DOC_HDR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OPRN` CHAR(1) NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, `RPLC_PRNCPL_ID` VARCHAR(40), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_RMV_RPLC_DOC_T` PRIMARY KEY (`DOC_HDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-79', '2.0.4-SNP', '3:aa7038e5b937bec5aa20fd8e54077402', 79);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-80::Jeff Caddel (generated)::(Checksum: 3:6742243283719546012a140db6e422a1)
CREATE TABLE `krew_rmv_rplc_grp_t` (`DOC_HDR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `GRP_ID` DECIMAL(14,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KREW_RMV_RPLC_GRP_T` PRIMARY KEY (`DOC_HDR_ID`, `GRP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-80', '2.0.4-SNP', '3:6742243283719546012a140db6e422a1', 80);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-81::Jeff Caddel (generated)::(Checksum: 3:a9b73addc3a57a98f14b3dc30927abd8)
CREATE TABLE `krew_rmv_rplc_rule_t` (`DOC_HDR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RULE_ID` DECIMAL(19,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KREW_RMV_RPLC_RULE_T` PRIMARY KEY (`DOC_HDR_ID`, `RULE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-81', '2.0.4-SNP', '3:a9b73addc3a57a98f14b3dc30927abd8', 81);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-82::Jeff Caddel (generated)::(Checksum: 3:045591dad92858e10462658161eb371a)
CREATE TABLE `krew_rsp_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_RSP_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-82', '2.0.4-SNP', '3:045591dad92858e10462658161eb371a', 82);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-83::Jeff Caddel (generated)::(Checksum: 3:7a8c73a578c059d3612d174dd259d3ed)
CREATE TABLE `krew_rte_brch_proto_t` (`RTE_BRCH_PROTO_ID` VARCHAR(40) DEFAULT '' NOT NULL, `BRCH_NM` VARCHAR(255) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_RTE_BRCH_PROTO_T` PRIMARY KEY (`RTE_BRCH_PROTO_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-83', '2.0.4-SNP', '3:7a8c73a578c059d3612d174dd259d3ed', 83);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-84::Jeff Caddel (generated)::(Checksum: 3:120cacd36a867b64ada729c2fca6e1c7)
CREATE TABLE `krew_rte_brch_st_t` (`RTE_BRCH_ST_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RTE_BRCH_ID` VARCHAR(40) NOT NULL, `KEY_CD` VARCHAR(255) NOT NULL, `VAL` VARCHAR(2000), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_RTE_BRCH_ST_T` PRIMARY KEY (`RTE_BRCH_ST_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-84', '2.0.4-SNP', '3:120cacd36a867b64ada729c2fca6e1c7', 84);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-85::Jeff Caddel (generated)::(Checksum: 3:5d15e26f48c98ef84f5a27574b7bcfbd)
CREATE TABLE `krew_rte_brch_t` (`RTE_BRCH_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NM` VARCHAR(255) NOT NULL, `PARNT_ID` VARCHAR(40), `INIT_RTE_NODE_INSTN_ID` VARCHAR(40), `SPLT_RTE_NODE_INSTN_ID` VARCHAR(40), `JOIN_RTE_NODE_INSTN_ID` VARCHAR(40), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_RTE_BRCH_T` PRIMARY KEY (`RTE_BRCH_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-85', '2.0.4-SNP', '3:5d15e26f48c98ef84f5a27574b7bcfbd', 85);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-86::Jeff Caddel (generated)::(Checksum: 3:f627e174d61b9bd6e0707bc54c2c9f49)
CREATE TABLE `krew_rte_node_cfg_parm_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_RTE_NODE_CFG_PARM_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-86', '2.0.4-SNP', '3:f627e174d61b9bd6e0707bc54c2c9f49', 86);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-87::Jeff Caddel (generated)::(Checksum: 3:2b510d2583f1a77d5792b710043b5ee6)
CREATE TABLE `krew_rte_node_cfg_parm_t` (`RTE_NODE_CFG_PARM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RTE_NODE_ID` VARCHAR(40) NOT NULL, `KEY_CD` VARCHAR(255) NOT NULL, `VAL` VARCHAR(4000), CONSTRAINT `PK_KREW_RTE_NODE_CFG_PARM_T` PRIMARY KEY (`RTE_NODE_CFG_PARM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-87', '2.0.4-SNP', '3:2b510d2583f1a77d5792b710043b5ee6', 87);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-88::Jeff Caddel (generated)::(Checksum: 3:5ebba242a1e8d0769aaa182e5f44fd41)
CREATE TABLE `krew_rte_node_instn_lnk_t` (`FROM_RTE_NODE_INSTN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `TO_RTE_NODE_INSTN_ID` VARCHAR(40) DEFAULT '' NOT NULL, CONSTRAINT `PK_KREW_RTE_NODE_INSTN_LNK_T` PRIMARY KEY (`FROM_RTE_NODE_INSTN_ID`, `TO_RTE_NODE_INSTN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-88', '2.0.4-SNP', '3:5ebba242a1e8d0769aaa182e5f44fd41', 88);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-89::Jeff Caddel (generated)::(Checksum: 3:8bfd1aeb6660192f392b2b64247b5ed0)
CREATE TABLE `krew_rte_node_instn_st_t` (`RTE_NODE_INSTN_ST_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RTE_NODE_INSTN_ID` VARCHAR(40) NOT NULL, `KEY_CD` VARCHAR(255) NOT NULL, `VAL` VARCHAR(2000), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_RTE_NODE_INSTN_ST_T` PRIMARY KEY (`RTE_NODE_INSTN_ST_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-89', '2.0.4-SNP', '3:8bfd1aeb6660192f392b2b64247b5ed0', 89);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-90::Jeff Caddel (generated)::(Checksum: 3:8b298f64e987ef589bfce67873dd8caf)
CREATE TABLE `krew_rte_node_instn_t` (`RTE_NODE_INSTN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_HDR_ID` VARCHAR(40) NOT NULL, `RTE_NODE_ID` VARCHAR(40) NOT NULL, `BRCH_ID` VARCHAR(40), `PROC_RTE_NODE_INSTN_ID` VARCHAR(40), `ACTV_IND` DECIMAL(1,0) DEFAULT 0 NOT NULL, `CMPLT_IND` DECIMAL(1,0) DEFAULT 0 NOT NULL, `INIT_IND` DECIMAL(1,0) DEFAULT 0 NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_RTE_NODE_INSTN_T` PRIMARY KEY (`RTE_NODE_INSTN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-90', '2.0.4-SNP', '3:8b298f64e987ef589bfce67873dd8caf', 90);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-91::Jeff Caddel (generated)::(Checksum: 3:79e90dbae107f2ab9bfa0b54343870e6)
CREATE TABLE `krew_rte_node_lnk_t` (`FROM_RTE_NODE_ID` VARCHAR(40) DEFAULT '' NOT NULL, `TO_RTE_NODE_ID` VARCHAR(40) DEFAULT '' NOT NULL, CONSTRAINT `PK_KREW_RTE_NODE_LNK_T` PRIMARY KEY (`FROM_RTE_NODE_ID`, `TO_RTE_NODE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-91', '2.0.4-SNP', '3:79e90dbae107f2ab9bfa0b54343870e6', 91);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-92::Jeff Caddel (generated)::(Checksum: 3:266ceb591b83a1f53e7b3537cdf7f62a)
CREATE TABLE `krew_rte_node_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_RTE_NODE_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-92', '2.0.4-SNP', '3:266ceb591b83a1f53e7b3537cdf7f62a', 92);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-93::Jeff Caddel (generated)::(Checksum: 3:a0674858326bd1ff2a9eda95aaaa8e24)
CREATE TABLE `krew_rte_node_t` (`RTE_NODE_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_TYP_ID` DECIMAL(19,0), `NM` VARCHAR(255) NOT NULL, `TYP` VARCHAR(255) NOT NULL, `RTE_MTHD_NM` VARCHAR(255), `RTE_MTHD_CD` VARCHAR(2), `FNL_APRVR_IND` DECIMAL(1,0), `MNDTRY_RTE_IND` DECIMAL(1,0), `ACTVN_TYP` VARCHAR(250), `BRCH_PROTO_ID` VARCHAR(40), `VER_NBR` DECIMAL(8,0) DEFAULT 0, `CONTENT_FRAGMENT` VARCHAR(4000), `GRP_ID` VARCHAR(40), `NEXT_DOC_STAT` VARCHAR(64), CONSTRAINT `PK_KREW_RTE_NODE_T` PRIMARY KEY (`RTE_NODE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-93', '2.0.4-SNP', '3:a0674858326bd1ff2a9eda95aaaa8e24', 93);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-94::Jeff Caddel (generated)::(Checksum: 3:a369c90f9b6725fce5230d97d407a28c)
CREATE TABLE `krew_rte_tmpl_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_RTE_TMPL_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-94', '2.0.4-SNP', '3:a369c90f9b6725fce5230d97d407a28c', 94);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-95::Jeff Caddel (generated)::(Checksum: 3:e15625697367b1b19cf76885b6aabe4a)
CREATE TABLE `krew_rule_attr_t` (`RULE_ATTR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NM` VARCHAR(255) NOT NULL, `LBL` VARCHAR(2000) NOT NULL, `RULE_ATTR_TYP_CD` VARCHAR(2000) NOT NULL, `DESC_TXT` VARCHAR(2000), `CLS_NM` VARCHAR(2000), `XML` LONGTEXT, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `APPL_ID` VARCHAR(255), `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_RULE_ATTR_T` PRIMARY KEY (`RULE_ATTR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-95', '2.0.4-SNP', '3:e15625697367b1b19cf76885b6aabe4a', 95);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-96::Jeff Caddel (generated)::(Checksum: 3:44c09d45dea3ba1929af518a33af7154)
CREATE TABLE `krew_rule_expr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_RULE_EXPR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-96', '2.0.4-SNP', '3:44c09d45dea3ba1929af518a33af7154', 96);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-97::Jeff Caddel (generated)::(Checksum: 3:7054ced96b50c46ac7f09d3a2e34456f)
CREATE TABLE `krew_rule_expr_t` (`RULE_EXPR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `TYP` VARCHAR(256) NOT NULL, `RULE_EXPR` VARCHAR(4000), `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_RULE_EXPR_T` PRIMARY KEY (`RULE_EXPR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-97', '2.0.4-SNP', '3:7054ced96b50c46ac7f09d3a2e34456f', 97);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-98::Jeff Caddel (generated)::(Checksum: 3:d3e7a6f8fcf6b4ea54de92b4e8da63d7)
CREATE TABLE `krew_rule_ext_t` (`RULE_EXT_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RULE_TMPL_ATTR_ID` VARCHAR(40) NOT NULL, `RULE_ID` VARCHAR(40) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_RULE_EXT_T` PRIMARY KEY (`RULE_EXT_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-98', '2.0.4-SNP', '3:d3e7a6f8fcf6b4ea54de92b4e8da63d7', 98);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-99::Jeff Caddel (generated)::(Checksum: 3:fc9119f6bb365680ee49c0c7df66bca9)
CREATE TABLE `krew_rule_ext_val_t` (`RULE_EXT_VAL_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RULE_EXT_ID` VARCHAR(40) NOT NULL, `VAL` VARCHAR(2000) NOT NULL, `KEY_CD` VARCHAR(2000) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_RULE_EXT_VAL_T` PRIMARY KEY (`RULE_EXT_VAL_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-99', '2.0.4-SNP', '3:fc9119f6bb365680ee49c0c7df66bca9', 99);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-100::Jeff Caddel (generated)::(Checksum: 3:a3891140c41957e9a1544ec698f9eafd)
CREATE TABLE `krew_rule_rsp_t` (`RULE_RSP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RSP_ID` VARCHAR(40) NOT NULL, `RULE_ID` VARCHAR(40) NOT NULL, `PRIO` DECIMAL(5,0), `ACTN_RQST_CD` VARCHAR(2000), `NM` VARCHAR(200), `TYP` VARCHAR(1), `APPR_PLCY` CHAR(1), `VER_NBR` DECIMAL(8,0) DEFAULT 0, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_RULE_RSP_T` PRIMARY KEY (`RULE_RSP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-100', '2.0.4-SNP', '3:a3891140c41957e9a1544ec698f9eafd', 100);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-101::Jeff Caddel (generated)::(Checksum: 3:a998b763b4e1d9b1ae693883001142b9)
CREATE TABLE `krew_rule_t` (`RULE_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NM` VARCHAR(256), `RULE_TMPL_ID` VARCHAR(40), `RULE_EXPR_ID` VARCHAR(40), `ACTV_IND` DECIMAL(1,0) NOT NULL, `RULE_BASE_VAL_DESC` VARCHAR(2000), `FRC_ACTN` DECIMAL(1,0) NOT NULL, `DOC_TYP_NM` VARCHAR(64) NOT NULL, `DOC_HDR_ID` VARCHAR(40), `TMPL_RULE_IND` DECIMAL(1,0), `FRM_DT` DATETIME, `TO_DT` DATETIME, `DACTVN_DT` DATETIME, `CUR_IND` DECIMAL(1,0) DEFAULT 0, `RULE_VER_NBR` DECIMAL(8,0) DEFAULT 0, `DLGN_IND` DECIMAL(1,0), `PREV_RULE_VER_NBR` VARCHAR(40), `ACTVN_DT` DATETIME, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_RULE_T` PRIMARY KEY (`RULE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-101', '2.0.4-SNP', '3:a998b763b4e1d9b1ae693883001142b9', 101);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-102::Jeff Caddel (generated)::(Checksum: 3:d967c0d079e480e4c3953b2825b2404a)
CREATE TABLE `krew_rule_tmpl_attr_t` (`RULE_TMPL_ATTR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RULE_TMPL_ID` VARCHAR(40) NOT NULL, `RULE_ATTR_ID` VARCHAR(40) NOT NULL, `REQ_IND` DECIMAL(1,0) NOT NULL, `ACTV_IND` DECIMAL(1,0) NOT NULL, `DSPL_ORD` DECIMAL(5,0) NOT NULL, `DFLT_VAL` VARCHAR(2000), `VER_NBR` DECIMAL(8,0) DEFAULT 0, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_RULE_TMPL_ATTR_T` PRIMARY KEY (`RULE_TMPL_ATTR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-102', '2.0.4-SNP', '3:d967c0d079e480e4c3953b2825b2404a', 102);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-103::Jeff Caddel (generated)::(Checksum: 3:4314b02e3b749dc34221c211348fbede)
CREATE TABLE `krew_rule_tmpl_optn_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_RULE_TMPL_OPTN_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-103', '2.0.4-SNP', '3:4314b02e3b749dc34221c211348fbede', 103);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-104::Jeff Caddel (generated)::(Checksum: 3:cd41ea2b6878ee7eed7ece658f811865)
CREATE TABLE `krew_rule_tmpl_optn_t` (`RULE_TMPL_OPTN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RULE_TMPL_ID` VARCHAR(40), `KEY_CD` VARCHAR(250), `VAL` VARCHAR(2000), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_RULE_TMPL_OPTN_T` PRIMARY KEY (`RULE_TMPL_OPTN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-104', '2.0.4-SNP', '3:cd41ea2b6878ee7eed7ece658f811865', 104);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-105::Jeff Caddel (generated)::(Checksum: 3:944b57c08e6582340eaf7c683434b547)
CREATE TABLE `krew_rule_tmpl_t` (`RULE_TMPL_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NM` VARCHAR(250) NOT NULL, `RULE_TMPL_DESC` VARCHAR(2000), `DLGN_RULE_TMPL_ID` VARCHAR(40), `VER_NBR` DECIMAL(8,0) DEFAULT 0, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_RULE_TMPL_T` PRIMARY KEY (`RULE_TMPL_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-105', '2.0.4-SNP', '3:944b57c08e6582340eaf7c683434b547', 105);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-106::Jeff Caddel (generated)::(Checksum: 3:70cdbc7b220f24dc30572b799e9d3105)
CREATE TABLE `krew_srch_attr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_SRCH_ATTR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-106', '2.0.4-SNP', '3:70cdbc7b220f24dc30572b799e9d3105', 106);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-107::Jeff Caddel (generated)::(Checksum: 3:802eb3512e265b56369d6fec0d25df54)
CREATE TABLE `krew_style_t` (`STYLE_ID` DECIMAL(19,0) DEFAULT 0 NOT NULL, `NM` VARCHAR(200) NOT NULL, `XML` LONGTEXT NOT NULL, `ACTV_IND` DECIMAL(1,0) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KREW_STYLE_T` PRIMARY KEY (`STYLE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-107', '2.0.4-SNP', '3:802eb3512e265b56369d6fec0d25df54', 107);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-108::Jeff Caddel (generated)::(Checksum: 3:2bf8da9a931c5ed908b13ae0e430d845)
CREATE TABLE `krew_usr_optn_t` (`PRNCPL_ID` VARCHAR(40) DEFAULT '' NOT NULL, `PRSN_OPTN_ID` VARCHAR(200) DEFAULT '' NOT NULL, `VAL` VARCHAR(2000), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KREW_USR_OPTN_T` PRIMARY KEY (`PRNCPL_ID`, `PRSN_OPTN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-108', '2.0.4-SNP', '3:2bf8da9a931c5ed908b13ae0e430d845', 108);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-109::Jeff Caddel (generated)::(Checksum: 3:6c905239f7c86d63a0bee1815b006636)
CREATE TABLE `krew_usr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KREW_USR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-109', '2.0.4-SNP', '3:6c905239f7c86d63a0bee1815b006636', 109);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-110::Jeff Caddel (generated)::(Checksum: 3:bb318736ab0e21e0277c688992a20e11)
CREATE TABLE `krim_addr_typ_t` (`ADDR_TYP_CD` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `DISPLAY_SORT_CD` VARCHAR(2), `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ADDR_TYP_T` PRIMARY KEY (`ADDR_TYP_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-110', '2.0.4-SNP', '3:bb318736ab0e21e0277c688992a20e11', 110);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-111::Jeff Caddel (generated)::(Checksum: 3:1ee8d1ad4be87cb691932bd5413fab19)
CREATE TABLE `krim_afltn_typ_t` (`AFLTN_TYP_CD` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(40), `EMP_AFLTN_TYP_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `DISPLAY_SORT_CD` VARCHAR(2), `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_AFLTN_TYP_T` PRIMARY KEY (`AFLTN_TYP_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-111', '2.0.4-SNP', '3:1ee8d1ad4be87cb691932bd5413fab19', 111);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-112::Jeff Caddel (generated)::(Checksum: 3:7b31e4692c619328f50c643ef2dd600d)
CREATE TABLE `krim_attr_data_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ATTR_DATA_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-112', '2.0.4-SNP', '3:7b31e4692c619328f50c643ef2dd600d', 112);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-113::Jeff Caddel (generated)::(Checksum: 3:ea6fb5ed758febb9b6c0f40e79b699e0)
CREATE TABLE `krim_attr_defn_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ATTR_DEFN_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-113', '2.0.4-SNP', '3:ea6fb5ed758febb9b6c0f40e79b699e0', 113);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-114::Jeff Caddel (generated)::(Checksum: 3:ebb1612db567374d3922bd3e752d809f)
CREATE TABLE `krim_attr_defn_t` (`KIM_ATTR_DEFN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(100), `LBL` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `NMSPC_CD` VARCHAR(40), `CMPNT_NM` VARCHAR(100), CONSTRAINT `PK_KRIM_ATTR_DEFN_T` PRIMARY KEY (`KIM_ATTR_DEFN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-114', '2.0.4-SNP', '3:ebb1612db567374d3922bd3e752d809f', 114);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-115::Jeff Caddel (generated)::(Checksum: 3:b7443b7fda99899dd1d9af4256a71387)
CREATE TABLE `krim_ctznshp_stat_t` (`CTZNSHP_STAT_CD` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `DISPLAY_SORT_CD` VARCHAR(2), `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_CTZNSHP_STAT_T` PRIMARY KEY (`CTZNSHP_STAT_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-115', '2.0.4-SNP', '3:b7443b7fda99899dd1d9af4256a71387', 115);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-116::Jeff Caddel (generated)::(Checksum: 3:f7a30da2591f3d479d0a594e7df6a7fd)
CREATE TABLE `krim_dlgn_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_DLGN_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-116', '2.0.4-SNP', '3:f7a30da2591f3d479d0a594e7df6a7fd', 116);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-117::Jeff Caddel (generated)::(Checksum: 3:47b3294aa076eed8e9d1064d5c19e554)
CREATE TABLE `krim_dlgn_mbr_attr_data_t` (`ATTR_DATA_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `DLGN_MBR_ID` VARCHAR(40), `KIM_TYP_ID` VARCHAR(40) NOT NULL, `KIM_ATTR_DEFN_ID` VARCHAR(40), `ATTR_VAL` VARCHAR(400), CONSTRAINT `PK_KRIM_DLGN_MBR_ATTR_DATA_T` PRIMARY KEY (`ATTR_DATA_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-117', '2.0.4-SNP', '3:47b3294aa076eed8e9d1064d5c19e554', 117);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-118::Jeff Caddel (generated)::(Checksum: 3:84e1c23b8428490b7c732077ac4e9849)
CREATE TABLE `krim_dlgn_mbr_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_DLGN_MBR_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-118', '2.0.4-SNP', '3:84e1c23b8428490b7c732077ac4e9849', 118);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-119::Jeff Caddel (generated)::(Checksum: 3:7b081fc3fccdbd8e0a686fb9f61350aa)
CREATE TABLE `krim_dlgn_mbr_t` (`DLGN_MBR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `DLGN_ID` VARCHAR(40), `MBR_ID` VARCHAR(40), `MBR_TYP_CD` CHAR(1) DEFAULT 'P', `ACTV_FRM_DT` DATETIME, `ACTV_TO_DT` DATETIME, `LAST_UPDT_DT` DATETIME, `ROLE_MBR_ID` VARCHAR(40), CONSTRAINT `PK_KRIM_DLGN_MBR_T` PRIMARY KEY (`DLGN_MBR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-119', '2.0.4-SNP', '3:7b081fc3fccdbd8e0a686fb9f61350aa', 119);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-120::Jeff Caddel (generated)::(Checksum: 3:c502f0e341a323c24262a433dd53715b)
CREATE TABLE `krim_dlgn_t` (`DLGN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `ROLE_ID` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `KIM_TYP_ID` VARCHAR(40) NOT NULL, `DLGN_TYP_CD` VARCHAR(1), CONSTRAINT `PK_KRIM_DLGN_T` PRIMARY KEY (`DLGN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-120', '2.0.4-SNP', '3:c502f0e341a323c24262a433dd53715b', 120);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-121::Jeff Caddel (generated)::(Checksum: 3:9be3f279b561e6a95e45346ac3244911)
CREATE TABLE `krim_email_typ_t` (`EMAIL_TYP_CD` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `DISPLAY_SORT_CD` VARCHAR(2), `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_EMAIL_TYP_T` PRIMARY KEY (`EMAIL_TYP_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-121', '2.0.4-SNP', '3:9be3f279b561e6a95e45346ac3244911', 121);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-122::Jeff Caddel (generated)::(Checksum: 3:4854141a05743d86fc9504f2ec111fa7)
CREATE TABLE `krim_emp_stat_t` (`EMP_STAT_CD` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `DISPLAY_SORT_CD` VARCHAR(2), `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_EMP_STAT_T` PRIMARY KEY (`EMP_STAT_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-122', '2.0.4-SNP', '3:4854141a05743d86fc9504f2ec111fa7', 122);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-123::Jeff Caddel (generated)::(Checksum: 3:494defce114b72131a9c973f3862b613)
CREATE TABLE `krim_emp_typ_t` (`EMP_TYP_CD` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `DISPLAY_SORT_CD` VARCHAR(2), `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_EMP_TYP_T` PRIMARY KEY (`EMP_TYP_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-123', '2.0.4-SNP', '3:494defce114b72131a9c973f3862b613', 123);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-124::Jeff Caddel (generated)::(Checksum: 3:c17e87a891f464a73d6447f9fcf3eccc)
CREATE TABLE `krim_ent_nm_typ_t` (`ENT_NM_TYP_CD` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `DISPLAY_SORT_CD` VARCHAR(2), `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ENT_NM_TYP_T` PRIMARY KEY (`ENT_NM_TYP_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-124', '2.0.4-SNP', '3:c17e87a891f464a73d6447f9fcf3eccc', 124);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-125::Jeff Caddel (generated)::(Checksum: 3:de74de55186a9dd53f725cccc2fac154)
CREATE TABLE `krim_ent_typ_t` (`ENT_TYP_CD` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(40), `DISPLAY_SORT_CD` VARCHAR(2), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_ENT_TYP_T` PRIMARY KEY (`ENT_TYP_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-125', '2.0.4-SNP', '3:de74de55186a9dd53f725cccc2fac154', 125);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-126::Jeff Caddel (generated)::(Checksum: 3:f93bced778c3297d34ebd55feec0b70e)
CREATE TABLE `krim_entity_addr_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_ADDR_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-126', '2.0.4-SNP', '3:f93bced778c3297d34ebd55feec0b70e', 126);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-127::Jeff Caddel (generated)::(Checksum: 3:0b06622a20e74482338441a8f1d6ce40)
CREATE TABLE `krim_entity_addr_t` (`ENTITY_ADDR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ENTITY_ID` VARCHAR(40), `ENT_TYP_CD` VARCHAR(40), `ADDR_TYP_CD` VARCHAR(40), `ADDR_LINE_1` VARCHAR(45), `ADDR_LINE_2` VARCHAR(45), `ADDR_LINE_3` VARCHAR(45), `CITY_NM` VARCHAR(30), `POSTAL_STATE_CD` VARCHAR(2), `POSTAL_CD` VARCHAR(20), `POSTAL_CNTRY_CD` VARCHAR(2), `DFLT_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ENTITY_ADDR_T` PRIMARY KEY (`ENTITY_ADDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-127', '2.0.4-SNP', '3:0b06622a20e74482338441a8f1d6ce40', 127);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-128::Jeff Caddel (generated)::(Checksum: 3:f104482bb9635b0c8e8b5bfdb924ddc7)
CREATE TABLE `krim_entity_afltn_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_AFLTN_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-128', '2.0.4-SNP', '3:f104482bb9635b0c8e8b5bfdb924ddc7', 128);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-129::Jeff Caddel (generated)::(Checksum: 3:dc2cd342e661e4293d06d7b382873b2f)
CREATE TABLE `krim_entity_afltn_t` (`ENTITY_AFLTN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ENTITY_ID` VARCHAR(40), `AFLTN_TYP_CD` VARCHAR(40), `CAMPUS_CD` VARCHAR(2), `DFLT_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ENTITY_AFLTN_T` PRIMARY KEY (`ENTITY_AFLTN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-129', '2.0.4-SNP', '3:dc2cd342e661e4293d06d7b382873b2f', 129);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-130::Jeff Caddel (generated)::(Checksum: 3:e63e86931775bbaae51698e265a3b735)
CREATE TABLE `krim_entity_bio_t` (`ENTITY_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `BIRTH_DT` DATETIME, `GNDR_CD` VARCHAR(1) NOT NULL, `LAST_UPDT_DT` DATETIME, `DECEASED_DT` DATETIME, `MARITAL_STATUS` VARCHAR(40), `PRIM_LANG_CD` VARCHAR(40), `SEC_LANG_CD` VARCHAR(40), `BIRTH_CNTRY_CD` VARCHAR(2), `BIRTH_STATE_CD` VARCHAR(2), `BIRTH_CITY` VARCHAR(30), `GEO_ORIGIN` VARCHAR(100), CONSTRAINT `PK_KRIM_ENTITY_BIO_T` PRIMARY KEY (`ENTITY_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-130', '2.0.4-SNP', '3:e63e86931775bbaae51698e265a3b735', 130);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-131::Jeff Caddel (generated)::(Checksum: 3:7df340fecd49dcf1aa89185be3b82158)
CREATE TABLE `krim_entity_cache_t` (`ENTITY_ID` VARCHAR(40) DEFAULT '' NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, `PRNCPL_NM` VARCHAR(40), `ENTITY_TYP_CD` VARCHAR(40), `FIRST_NM` VARCHAR(40), `MIDDLE_NM` VARCHAR(40), `LAST_NM` VARCHAR(40), `PRSN_NM` VARCHAR(40), `CAMPUS_CD` VARCHAR(40), `PRMRY_DEPT_CD` VARCHAR(40), `EMP_ID` VARCHAR(40), `LAST_UPDT_TS` DATETIME, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_CACHE_T` PRIMARY KEY (`ENTITY_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-131', '2.0.4-SNP', '3:7df340fecd49dcf1aa89185be3b82158', 131);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-132::Jeff Caddel (generated)::(Checksum: 3:e8bd366ede94abe66c62d0ca76b8376a)
CREATE TABLE `krim_entity_ctznshp_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_CTZNSHP_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-132', '2.0.4-SNP', '3:e8bd366ede94abe66c62d0ca76b8376a', 132);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-133::Jeff Caddel (generated)::(Checksum: 3:131adc8e67ea1a9c881f1b0935e3b127)
CREATE TABLE `krim_entity_ctznshp_t` (`ENTITY_CTZNSHP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ENTITY_ID` VARCHAR(40), `POSTAL_CNTRY_CD` VARCHAR(2), `CTZNSHP_STAT_CD` VARCHAR(40), `STRT_DT` DATETIME, `END_DT` DATETIME, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ENTITY_CTZNSHP_T` PRIMARY KEY (`ENTITY_CTZNSHP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-133', '2.0.4-SNP', '3:131adc8e67ea1a9c881f1b0935e3b127', 133);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-134::Jeff Caddel (generated)::(Checksum: 3:2c9c0839476cc090edc5dde1a98ada47)
CREATE TABLE `krim_entity_email_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_EMAIL_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-134', '2.0.4-SNP', '3:2c9c0839476cc090edc5dde1a98ada47', 134);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-135::Jeff Caddel (generated)::(Checksum: 3:eccde359530276632b7774582e7e4d23)
CREATE TABLE `krim_entity_email_t` (`ENTITY_EMAIL_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ENTITY_ID` VARCHAR(40), `ENT_TYP_CD` VARCHAR(40), `EMAIL_TYP_CD` VARCHAR(40), `EMAIL_ADDR` VARCHAR(200), `DFLT_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ENTITY_EMAIL_T` PRIMARY KEY (`ENTITY_EMAIL_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-135', '2.0.4-SNP', '3:eccde359530276632b7774582e7e4d23', 135);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-136::Jeff Caddel (generated)::(Checksum: 3:24b3dc66ddea5376a42ea0b6470511bb)
CREATE TABLE `krim_entity_emp_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_EMP_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-136', '2.0.4-SNP', '3:24b3dc66ddea5376a42ea0b6470511bb', 136);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-137::Jeff Caddel (generated)::(Checksum: 3:b3b7900c81b034df9fd4e9dd55c85b35)
CREATE TABLE `krim_entity_emp_info_t` (`ENTITY_EMP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ENTITY_ID` VARCHAR(40), `ENTITY_AFLTN_ID` VARCHAR(40), `EMP_STAT_CD` VARCHAR(40), `EMP_TYP_CD` VARCHAR(40), `BASE_SLRY_AMT` DECIMAL(15,2), `PRMRY_IND` VARCHAR(1), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, `PRMRY_DEPT_CD` VARCHAR(40), `EMP_ID` VARCHAR(40), `EMP_REC_ID` VARCHAR(40), CONSTRAINT `PK_KRIM_ENTITY_EMP_INFO_T` PRIMARY KEY (`ENTITY_EMP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-137', '2.0.4-SNP', '3:b3b7900c81b034df9fd4e9dd55c85b35', 137);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-138::Jeff Caddel (generated)::(Checksum: 3:ce73bf63ad5c1cb970bd53dc3d611f3d)
CREATE TABLE `krim_entity_ent_typ_t` (`ENT_TYP_CD` VARCHAR(40) DEFAULT '' NOT NULL, `ENTITY_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ENTITY_ENT_TYP_T` PRIMARY KEY (`ENT_TYP_CD`, `ENTITY_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-138', '2.0.4-SNP', '3:ce73bf63ad5c1cb970bd53dc3d611f3d', 138);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-139::Jeff Caddel (generated)::(Checksum: 3:3db148b17bd0a90f11a40d1b72cc1cc5)
CREATE TABLE `krim_entity_ethnic_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_ETHNIC_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-139', '2.0.4-SNP', '3:3db148b17bd0a90f11a40d1b72cc1cc5', 139);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-140::Jeff Caddel (generated)::(Checksum: 3:f86b9e95984fc40c1c23b26a974c143a)
CREATE TABLE `krim_entity_ethnic_t` (`ID` VARCHAR(40) DEFAULT '' NOT NULL, `ENTITY_ID` VARCHAR(40), `ETHNCTY_CD` VARCHAR(40), `SUB_ETHNCTY_CD` VARCHAR(40), `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_ETHNIC_T` PRIMARY KEY (`ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-140', '2.0.4-SNP', '3:f86b9e95984fc40c1c23b26a974c143a', 140);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-141::Jeff Caddel (generated)::(Checksum: 3:297c34b833852ce6697ab195e0794060)
CREATE TABLE `krim_entity_ext_id_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_EXT_ID_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-141', '2.0.4-SNP', '3:297c34b833852ce6697ab195e0794060', 141);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-142::Jeff Caddel (generated)::(Checksum: 3:b1beb91e98c900b8ec364e532ade98cd)
CREATE TABLE `krim_entity_ext_id_t` (`ENTITY_EXT_ID_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ENTITY_ID` VARCHAR(40), `EXT_ID_TYP_CD` VARCHAR(40), `EXT_ID` VARCHAR(100), `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ENTITY_EXT_ID_T` PRIMARY KEY (`ENTITY_EXT_ID_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-142', '2.0.4-SNP', '3:b1beb91e98c900b8ec364e532ade98cd', 142);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-143::Jeff Caddel (generated)::(Checksum: 3:294b7681a870568a51c53afc8d0fc14a)
CREATE TABLE `krim_entity_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-143', '2.0.4-SNP', '3:294b7681a870568a51c53afc8d0fc14a', 143);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-144::Jeff Caddel (generated)::(Checksum: 3:cea5bf1435dc6b20c7926098de0b3143)
CREATE TABLE `krim_entity_nm_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_NM_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-144', '2.0.4-SNP', '3:cea5bf1435dc6b20c7926098de0b3143', 144);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-145::Jeff Caddel (generated)::(Checksum: 3:28b341a414c6686cb9e1e402647bdedd)
CREATE TABLE `krim_entity_nm_t` (`ENTITY_NM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ENTITY_ID` VARCHAR(40), `NM_TYP_CD` VARCHAR(40), `FIRST_NM` VARCHAR(40), `MIDDLE_NM` VARCHAR(40), `LAST_NM` VARCHAR(80), `SUFFIX_NM` VARCHAR(20), `TITLE_NM` VARCHAR(20), `DFLT_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ENTITY_NM_T` PRIMARY KEY (`ENTITY_NM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-145', '2.0.4-SNP', '3:28b341a414c6686cb9e1e402647bdedd', 145);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-146::Jeff Caddel (generated)::(Checksum: 3:4a4f0f732c658f468b153299ac44b366)
CREATE TABLE `krim_entity_phone_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_PHONE_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-146', '2.0.4-SNP', '3:4a4f0f732c658f468b153299ac44b366', 146);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-147::Jeff Caddel (generated)::(Checksum: 3:9ce4a955e31d8d3f16f6f9fb3b5c0420)
CREATE TABLE `krim_entity_phone_t` (`ENTITY_PHONE_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ENTITY_ID` VARCHAR(40), `ENT_TYP_CD` VARCHAR(40), `PHONE_TYP_CD` VARCHAR(40), `PHONE_NBR` VARCHAR(20), `PHONE_EXTN_NBR` VARCHAR(8), `POSTAL_CNTRY_CD` VARCHAR(2), `DFLT_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ENTITY_PHONE_T` PRIMARY KEY (`ENTITY_PHONE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-147', '2.0.4-SNP', '3:9ce4a955e31d8d3f16f6f9fb3b5c0420', 147);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-148::Jeff Caddel (generated)::(Checksum: 3:7f8c20a2a7efa7b89925b1bf9f803b72)
CREATE TABLE `krim_entity_priv_pref_t` (`ENTITY_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `SUPPRESS_NM_IND` VARCHAR(1) DEFAULT 'N', `SUPPRESS_EMAIL_IND` VARCHAR(1) DEFAULT 'Y', `SUPPRESS_ADDR_IND` VARCHAR(1) DEFAULT 'Y', `SUPPRESS_PHONE_IND` VARCHAR(1) DEFAULT 'Y', `SUPPRESS_PRSNL_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ENTITY_PRIV_PREF_T` PRIMARY KEY (`ENTITY_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-148', '2.0.4-SNP', '3:7f8c20a2a7efa7b89925b1bf9f803b72', 148);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-149::Jeff Caddel (generated)::(Checksum: 3:e83512316948b68064b07ac9c57e4b48)
CREATE TABLE `krim_entity_residency_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_RESIDENCY_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-149', '2.0.4-SNP', '3:e83512316948b68064b07ac9c57e4b48', 149);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-150::Jeff Caddel (generated)::(Checksum: 3:e0ee777d168be74a54920262bf1215e8)
CREATE TABLE `krim_entity_residency_t` (`ID` VARCHAR(40) DEFAULT '' NOT NULL, `ENTITY_ID` VARCHAR(40), `DETERMINATION_METHOD` VARCHAR(40), `IN_STATE` VARCHAR(40), `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_RESIDENCY_T` PRIMARY KEY (`ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-150', '2.0.4-SNP', '3:e0ee777d168be74a54920262bf1215e8', 150);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-151::Jeff Caddel (generated)::(Checksum: 3:a3a1647ceb0f994d925bf43ebd9b60b8)
CREATE TABLE `krim_entity_t` (`ENTITY_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ENTITY_T` PRIMARY KEY (`ENTITY_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-151', '2.0.4-SNP', '3:a3a1647ceb0f994d925bf43ebd9b60b8', 151);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-152::Jeff Caddel (generated)::(Checksum: 3:244f09dbccc6d2a6d17256f9c4269d9c)
CREATE TABLE `krim_entity_visa_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_VISA_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-152', '2.0.4-SNP', '3:244f09dbccc6d2a6d17256f9c4269d9c', 152);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-153::Jeff Caddel (generated)::(Checksum: 3:04725e7b42d5767d30c67fd7c5bdee9c)
CREATE TABLE `krim_entity_visa_t` (`ID` VARCHAR(40) DEFAULT '' NOT NULL, `ENTITY_ID` VARCHAR(40), `VISA_TYPE_KEY` VARCHAR(40), `VISA_ENTRY` VARCHAR(40), `VISA_ID` VARCHAR(40), `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, CONSTRAINT `PK_KRIM_ENTITY_VISA_T` PRIMARY KEY (`ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-153', '2.0.4-SNP', '3:04725e7b42d5767d30c67fd7c5bdee9c', 153);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-154::Jeff Caddel (generated)::(Checksum: 3:3abd9a280f9283baea208d9cdfa04015)
CREATE TABLE `krim_ext_id_typ_t` (`EXT_ID_TYP_CD` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(40), `ENCR_REQ_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `DISPLAY_SORT_CD` VARCHAR(2), `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_EXT_ID_TYP_T` PRIMARY KEY (`EXT_ID_TYP_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-154', '2.0.4-SNP', '3:3abd9a280f9283baea208d9cdfa04015', 154);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-155::Jeff Caddel (generated)::(Checksum: 3:25c293fd7e21d233e97dfbfef13e37f8)
CREATE TABLE `krim_grp_attr_data_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_GRP_ATTR_DATA_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-155', '2.0.4-SNP', '3:25c293fd7e21d233e97dfbfef13e37f8', 155);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-156::Jeff Caddel (generated)::(Checksum: 3:6b847d496f18056e0ca55f4d511183e0)
CREATE TABLE `krim_grp_attr_data_t` (`ATTR_DATA_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `GRP_ID` VARCHAR(40), `KIM_TYP_ID` VARCHAR(40) NOT NULL, `KIM_ATTR_DEFN_ID` VARCHAR(40), `ATTR_VAL` VARCHAR(400), CONSTRAINT `PK_KRIM_GRP_ATTR_DATA_T` PRIMARY KEY (`ATTR_DATA_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-156', '2.0.4-SNP', '3:6b847d496f18056e0ca55f4d511183e0', 156);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-157::Jeff Caddel (generated)::(Checksum: 3:a341640b1ad723f55135bb8de28a2376)
CREATE TABLE `krim_grp_document_t` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `GRP_ID` VARCHAR(40) NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `KIM_TYP_ID` VARCHAR(40) NOT NULL, `GRP_NMSPC` VARCHAR(100) NOT NULL, `GRP_NM` VARCHAR(400), `GRP_DESC` VARCHAR(400), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_GRP_DOCUMENT_T` PRIMARY KEY (`FDOC_NBR`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-157', '2.0.4-SNP', '3:a341640b1ad723f55135bb8de28a2376', 157);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-158::Jeff Caddel (generated)::(Checksum: 3:952da8a9822a4a6540915a7e4cb998b2)
CREATE TABLE `krim_grp_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_GRP_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-158', '2.0.4-SNP', '3:952da8a9822a4a6540915a7e4cb998b2', 158);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-159::Jeff Caddel (generated)::(Checksum: 3:9a55188c4b0fb9749f279b1827c8ca37)
CREATE TABLE `krim_grp_mbr_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_GRP_MBR_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-159', '2.0.4-SNP', '3:9a55188c4b0fb9749f279b1827c8ca37', 159);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-160::Jeff Caddel (generated)::(Checksum: 3:622e0dadb586f5c09d23c67956b8e36d)
CREATE TABLE `krim_grp_mbr_t` (`GRP_MBR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `GRP_ID` VARCHAR(40), `MBR_ID` VARCHAR(40), `MBR_TYP_CD` CHAR(1) DEFAULT 'P', `ACTV_FRM_DT` DATETIME, `ACTV_TO_DT` DATETIME, `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_GRP_MBR_T` PRIMARY KEY (`GRP_MBR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-160', '2.0.4-SNP', '3:622e0dadb586f5c09d23c67956b8e36d', 160);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-161::Jeff Caddel (generated)::(Checksum: 3:d6bf4b77502c2d6a6abab9d77bdd7597)
CREATE TABLE `krim_grp_t` (`GRP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `GRP_NM` VARCHAR(80) NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, `GRP_DESC` VARCHAR(4000), `KIM_TYP_ID` VARCHAR(40) NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_GRP_T` PRIMARY KEY (`GRP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-161', '2.0.4-SNP', '3:d6bf4b77502c2d6a6abab9d77bdd7597', 161);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-162::Jeff Caddel (generated)::(Checksum: 3:d66b6410648a057b3c95ba675355a633)
CREATE TABLE `krim_perm_attr_data_t` (`ATTR_DATA_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `PERM_ID` VARCHAR(40), `KIM_TYP_ID` VARCHAR(40) NOT NULL, `KIM_ATTR_DEFN_ID` VARCHAR(40), `ATTR_VAL` VARCHAR(400), CONSTRAINT `PK_KRIM_PERM_ATTR_DATA_T` PRIMARY KEY (`ATTR_DATA_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-162', '2.0.4-SNP', '3:d66b6410648a057b3c95ba675355a633', 162);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-163::Jeff Caddel (generated)::(Checksum: 3:1f4915f1276c80257198776bd53036cf)
CREATE TABLE `krim_perm_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_PERM_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-163', '2.0.4-SNP', '3:1f4915f1276c80257198776bd53036cf', 163);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-164::Jeff Caddel (generated)::(Checksum: 3:63df622aea42aad735f0fc6fc140809c)
CREATE TABLE `krim_perm_rqrd_attr_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_PERM_RQRD_ATTR_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-164', '2.0.4-SNP', '3:63df622aea42aad735f0fc6fc140809c', 164);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-165::Jeff Caddel (generated)::(Checksum: 3:16495a74f676484f3c2db140657cc3f6)
CREATE TABLE `krim_perm_t` (`PERM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `PERM_TMPL_ID` VARCHAR(40), `NMSPC_CD` VARCHAR(40) NOT NULL, `NM` VARCHAR(100) NOT NULL, `DESC_TXT` VARCHAR(400), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_PERM_T` PRIMARY KEY (`PERM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-165', '2.0.4-SNP', '3:16495a74f676484f3c2db140657cc3f6', 165);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-166::Jeff Caddel (generated)::(Checksum: 3:d60291296b5c54bbace35ae32916a6ac)
CREATE TABLE `krim_perm_tmpl_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_PERM_TMPL_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-166', '2.0.4-SNP', '3:d60291296b5c54bbace35ae32916a6ac', 166);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-167::Jeff Caddel (generated)::(Checksum: 3:e4e2a1266a5b275bef71ddc4b3bc4ba9)
CREATE TABLE `krim_perm_tmpl_t` (`PERM_TMPL_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, `NM` VARCHAR(100) NOT NULL, `DESC_TXT` VARCHAR(400), `KIM_TYP_ID` VARCHAR(40) NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_PERM_TMPL_T` PRIMARY KEY (`PERM_TMPL_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-167', '2.0.4-SNP', '3:e4e2a1266a5b275bef71ddc4b3bc4ba9', 167);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-168::Jeff Caddel (generated)::(Checksum: 3:4ec60cada3aafb374f21096e06a2b524)
CREATE TABLE `krim_person_document_t` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ENTITY_ID` VARCHAR(40) NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, `PRNCPL_NM` VARCHAR(100) NOT NULL, `PRNCPL_PSWD` VARCHAR(400), `UNIV_ID` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_PERSON_DOCUMENT_T` PRIMARY KEY (`FDOC_NBR`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-168', '2.0.4-SNP', '3:4ec60cada3aafb374f21096e06a2b524', 168);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-169::Jeff Caddel (generated)::(Checksum: 3:ca41e7e14569958282c705dcc933f097)
CREATE TABLE `krim_phone_typ_t` (`PHONE_TYP_CD` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `PHONE_TYP_NM` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `DISPLAY_SORT_CD` VARCHAR(2), `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_PHONE_TYP_T` PRIMARY KEY (`PHONE_TYP_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-169', '2.0.4-SNP', '3:ca41e7e14569958282c705dcc933f097', 169);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-170::Jeff Caddel (generated)::(Checksum: 3:dfa5e92c4681960e569a9b10598e3749)
CREATE TABLE `krim_pnd_addr_mt` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ADDR_TYP_CD` VARCHAR(40), `ADDR_LINE_1` VARCHAR(50), `ADDR_LINE_2` VARCHAR(50), `ADDR_LINE_3` VARCHAR(50), `CITY_NM` VARCHAR(30), `POSTAL_STATE_CD` VARCHAR(2), `POSTAL_CD` VARCHAR(20), `POSTAL_CNTRY_CD` VARCHAR(2), `DISPLAY_SORT_CD` VARCHAR(2), `DFLT_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `ENTITY_ADDR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_ADDR_MT` PRIMARY KEY (`FDOC_NBR`, `ENTITY_ADDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-170', '2.0.4-SNP', '3:dfa5e92c4681960e569a9b10598e3749', 170);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-171::Jeff Caddel (generated)::(Checksum: 3:7bc52240692e971e9ccf033270bbbe7a)
CREATE TABLE `krim_pnd_afltn_mt` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ENTITY_AFLTN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `AFLTN_TYP_CD` VARCHAR(40), `CAMPUS_CD` VARCHAR(2), `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', `DFLT_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, CONSTRAINT `PK_KRIM_PND_AFLTN_MT` PRIMARY KEY (`FDOC_NBR`, `ENTITY_AFLTN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-171', '2.0.4-SNP', '3:7bc52240692e971e9ccf033270bbbe7a', 171);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-172::Jeff Caddel (generated)::(Checksum: 3:51d56d2ce8fe89465236f46e4146f758)
CREATE TABLE `krim_pnd_ctznshp_mt` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ENTITY_CTZNSHP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `POSTAL_CNTRY_CD` VARCHAR(2), `CTZNSHP_STAT_CD` VARCHAR(40), `STRT_DT` DATETIME, `END_DT` DATETIME, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_CTZNSHP_MT` PRIMARY KEY (`FDOC_NBR`, `ENTITY_CTZNSHP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-172', '2.0.4-SNP', '3:51d56d2ce8fe89465236f46e4146f758', 172);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-173::Jeff Caddel (generated)::(Checksum: 3:dbbe1625c0f315129daa3895d21f32e2)
CREATE TABLE `krim_pnd_dlgn_mbr_attr_data_t` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ATTR_DATA_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `DLGN_MBR_ID` VARCHAR(40), `KIM_TYP_ID` VARCHAR(40), `KIM_ATTR_DEFN_ID` VARCHAR(40), `ATTR_VAL` VARCHAR(400), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_DLGN_MBR_ATTR_DATA_T` PRIMARY KEY (`FDOC_NBR`, `ATTR_DATA_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-173', '2.0.4-SNP', '3:dbbe1625c0f315129daa3895d21f32e2', 173);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-174::Jeff Caddel (generated)::(Checksum: 3:81d2dce5c48d4c4cf282d158796f33f5)
CREATE TABLE `krim_pnd_dlgn_mbr_t` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `DLGN_MBR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `DLGN_ID` VARCHAR(40) NOT NULL, `MBR_ID` VARCHAR(40), `MBR_NM` VARCHAR(40), `MBR_TYP_CD` VARCHAR(40) NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `ACTV_FRM_DT` DATETIME, `ACTV_TO_DT` DATETIME, `ROLE_MBR_ID` VARCHAR(40), CONSTRAINT `PK_KRIM_PND_DLGN_MBR_T` PRIMARY KEY (`FDOC_NBR`, `DLGN_MBR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-174', '2.0.4-SNP', '3:81d2dce5c48d4c4cf282d158796f33f5', 174);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-175::Jeff Caddel (generated)::(Checksum: 3:f3765f736fd660f79e23ede4c1c73b8a)
CREATE TABLE `krim_pnd_dlgn_t` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `DLGN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `ROLE_ID` VARCHAR(40) NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `KIM_TYP_ID` VARCHAR(40), `DLGN_TYP_CD` VARCHAR(100) NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_PND_DLGN_T` PRIMARY KEY (`FDOC_NBR`, `DLGN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-175', '2.0.4-SNP', '3:f3765f736fd660f79e23ede4c1c73b8a', 175);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-176::Jeff Caddel (generated)::(Checksum: 3:388d3583a1a685eab4ffc88ac19ddf32)
CREATE TABLE `krim_pnd_email_mt` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ENTITY_EMAIL_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ENT_TYP_CD` VARCHAR(40), `EMAIL_TYP_CD` VARCHAR(40), `EMAIL_ADDR` VARCHAR(200), `DFLT_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_EMAIL_MT` PRIMARY KEY (`FDOC_NBR`, `ENTITY_EMAIL_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-176', '2.0.4-SNP', '3:388d3583a1a685eab4ffc88ac19ddf32', 176);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-177::Jeff Caddel (generated)::(Checksum: 3:a529a9d1a517b3563bc0f19b472cead7)
CREATE TABLE `krim_pnd_emp_info_mt` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `PRMRY_DEPT_CD` VARCHAR(40), `ENTITY_EMP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `EMP_ID` VARCHAR(40), `EMP_REC_ID` VARCHAR(40), `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ENTITY_AFLTN_ID` VARCHAR(40), `EMP_STAT_CD` VARCHAR(40), `EMP_TYP_CD` VARCHAR(40), `BASE_SLRY_AMT` DECIMAL(15,2), `PRMRY_IND` VARCHAR(1), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_EMP_INFO_MT` PRIMARY KEY (`FDOC_NBR`, `ENTITY_EMP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-177', '2.0.4-SNP', '3:a529a9d1a517b3563bc0f19b472cead7', 177);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-178::Jeff Caddel (generated)::(Checksum: 3:341a2db5aad3b146489f6048c10dd23b)
CREATE TABLE `krim_pnd_grp_attr_data_t` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ATTR_DATA_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `GRP_ID` VARCHAR(40), `KIM_TYP_ID` VARCHAR(40), `KIM_ATTR_DEFN_ID` VARCHAR(40), `ATTR_VAL` VARCHAR(400), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_GRP_ATTR_DATA_T` PRIMARY KEY (`FDOC_NBR`, `ATTR_DATA_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-178', '2.0.4-SNP', '3:341a2db5aad3b146489f6048c10dd23b', 178);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-179::Jeff Caddel (generated)::(Checksum: 3:f8d54ba5ed6ad55271137588485f9967)
CREATE TABLE `krim_pnd_grp_mbr_t` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `GRP_MBR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `GRP_ID` VARCHAR(40) NOT NULL, `MBR_ID` VARCHAR(40), `MBR_NM` VARCHAR(40), `MBR_TYP_CD` VARCHAR(40) NOT NULL, `ACTV_FRM_DT` DATETIME, `ACTV_TO_DT` DATETIME, CONSTRAINT `PK_KRIM_PND_GRP_MBR_T` PRIMARY KEY (`FDOC_NBR`, `GRP_MBR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-179', '2.0.4-SNP', '3:f8d54ba5ed6ad55271137588485f9967', 179);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-180::Jeff Caddel (generated)::(Checksum: 3:7cc2d75c59554e39ea6571c02c68621d)
CREATE TABLE `krim_pnd_grp_prncpl_mt` (`GRP_MBR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `GRP_ID` VARCHAR(40) NOT NULL, `PRNCPL_ID` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `GRP_NM` VARCHAR(80) NOT NULL, `GRP_TYPE` VARCHAR(80), `KIM_TYP_ID` VARCHAR(40), `NMSPC_CD` VARCHAR(40), `ACTV_FRM_DT` DATETIME, `ACTV_TO_DT` DATETIME, `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_GRP_PRNCPL_MT` PRIMARY KEY (`GRP_MBR_ID`, `FDOC_NBR`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-180', '2.0.4-SNP', '3:7cc2d75c59554e39ea6571c02c68621d', 180);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-181::Jeff Caddel (generated)::(Checksum: 3:44a41ca37fa74920b7ac1ef8ddc23a6f)
CREATE TABLE `krim_pnd_nm_mt` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ENTITY_NM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM_TYP_CD` VARCHAR(40), `FIRST_NM` VARCHAR(40), `MIDDLE_NM` VARCHAR(40), `LAST_NM` VARCHAR(80), `SUFFIX_NM` VARCHAR(20), `TITLE_NM` VARCHAR(20), `DFLT_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_NM_MT` PRIMARY KEY (`FDOC_NBR`, `ENTITY_NM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-181', '2.0.4-SNP', '3:44a41ca37fa74920b7ac1ef8ddc23a6f', 181);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-182::Jeff Caddel (generated)::(Checksum: 3:4a9563156435279fb9fc6280b891c609)
CREATE TABLE `krim_pnd_phone_mt` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ENTITY_PHONE_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ENT_TYP_CD` VARCHAR(40), `PHONE_TYP_CD` VARCHAR(40), `PHONE_NBR` VARCHAR(20), `PHONE_EXTN_NBR` VARCHAR(8), `POSTAL_CNTRY_CD` VARCHAR(2), `DFLT_IND` VARCHAR(1) DEFAULT 'N', `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_PHONE_MT` PRIMARY KEY (`FDOC_NBR`, `ENTITY_PHONE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-182', '2.0.4-SNP', '3:4a9563156435279fb9fc6280b891c609', 182);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-183::Jeff Caddel (generated)::(Checksum: 3:9558c499fba59aa63dcac1626d21fbe0)
CREATE TABLE `krim_pnd_priv_pref_mt` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `SUPPRESS_NM_IND` VARCHAR(1) DEFAULT 'N', `SUPPRESS_EMAIL_IND` VARCHAR(1) DEFAULT 'Y', `SUPPRESS_ADDR_IND` VARCHAR(1) DEFAULT 'Y', `SUPPRESS_PHONE_IND` VARCHAR(1) DEFAULT 'Y', `SUPPRESS_PRSNL_IND` VARCHAR(1) DEFAULT 'Y', `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_PRIV_PREF_MT` PRIMARY KEY (`FDOC_NBR`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-183', '2.0.4-SNP', '3:9558c499fba59aa63dcac1626d21fbe0', 183);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-184::Jeff Caddel (generated)::(Checksum: 3:44ee12cebad5cf7ce03aa15542d89454)
CREATE TABLE `krim_pnd_role_mbr_attr_data_mt` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ATTR_DATA_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ROLE_MBR_ID` VARCHAR(40), `KIM_TYP_ID` VARCHAR(40), `KIM_ATTR_DEFN_ID` VARCHAR(40), `ATTR_VAL` VARCHAR(400), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_ROLE_MBR_ATTR_DATA_MT` PRIMARY KEY (`FDOC_NBR`, `ATTR_DATA_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-184', '2.0.4-SNP', '3:44ee12cebad5cf7ce03aa15542d89454', 184);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-185::Jeff Caddel (generated)::(Checksum: 3:f78c1437a904ab1b6b4aae514c6f9917)
CREATE TABLE `krim_pnd_role_mbr_mt` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ROLE_MBR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ROLE_ID` VARCHAR(40) NOT NULL, `MBR_ID` VARCHAR(40), `MBR_TYP_CD` VARCHAR(40) NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `ACTV_FRM_DT` DATETIME, `ACTV_TO_DT` DATETIME, `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_ROLE_MBR_MT` PRIMARY KEY (`FDOC_NBR`, `ROLE_MBR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-185', '2.0.4-SNP', '3:f78c1437a904ab1b6b4aae514c6f9917', 185);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-186::Jeff Caddel (generated)::(Checksum: 3:2ef7c134c34e5f969791ba2656745d8c)
CREATE TABLE `krim_pnd_role_mt` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ROLE_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ROLE_NM` VARCHAR(100) NOT NULL, `KIM_TYP_ID` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `NMSPC_CD` VARCHAR(40), `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_PND_ROLE_MT` PRIMARY KEY (`FDOC_NBR`, `ROLE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-186', '2.0.4-SNP', '3:2ef7c134c34e5f969791ba2656745d8c', 186);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-187::Jeff Caddel (generated)::(Checksum: 3:2970d82eec7bd16748d9b63325b981d8)
CREATE TABLE `krim_pnd_role_perm_t` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ROLE_PERM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ROLE_ID` VARCHAR(40) NOT NULL, `PERM_ID` VARCHAR(40) NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_PND_ROLE_PERM_T` PRIMARY KEY (`FDOC_NBR`, `ROLE_PERM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-187', '2.0.4-SNP', '3:2970d82eec7bd16748d9b63325b981d8', 187);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-188::Jeff Caddel (generated)::(Checksum: 3:12fe93344bdab486aa113a36c25dedbf)
CREATE TABLE `krim_pnd_role_rsp_actn_mt` (`ROLE_RSP_ACTN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ACTN_TYP_CD` VARCHAR(40), `PRIORITY_NBR` DECIMAL(3,0), `ACTN_PLCY_CD` VARCHAR(40), `ROLE_MBR_ID` VARCHAR(40), `ROLE_RSP_ID` VARCHAR(40), `EDIT_FLAG` VARCHAR(1) DEFAULT 'N', `FRC_ACTN` VARCHAR(1), CONSTRAINT `PK_KRIM_PND_ROLE_RSP_ACTN_MT` PRIMARY KEY (`ROLE_RSP_ACTN_ID`, `FDOC_NBR`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-188', '2.0.4-SNP', '3:12fe93344bdab486aa113a36c25dedbf', 188);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-189::Jeff Caddel (generated)::(Checksum: 3:add2f018e8ce65b788817cd74ac504d6)
CREATE TABLE `krim_pnd_role_rsp_t` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ROLE_RSP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ROLE_ID` VARCHAR(40) NOT NULL, `RSP_ID` VARCHAR(40) NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_PND_ROLE_RSP_T` PRIMARY KEY (`FDOC_NBR`, `ROLE_RSP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-189', '2.0.4-SNP', '3:add2f018e8ce65b788817cd74ac504d6', 189);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-190::Jeff Caddel (generated)::(Checksum: 3:04e92518080049d92a4f264c3c5ed64d)
CREATE TABLE `krim_prncpl_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_PRNCPL_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-190', '2.0.4-SNP', '3:04e92518080049d92a4f264c3c5ed64d', 190);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-191::Jeff Caddel (generated)::(Checksum: 3:b6200ed8a3a04bdde02d42023030acc5)
CREATE TABLE `krim_prncpl_t` (`PRNCPL_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `PRNCPL_NM` VARCHAR(100) NOT NULL, `ENTITY_ID` VARCHAR(40), `PRNCPL_PSWD` VARCHAR(400), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_PRNCPL_T` PRIMARY KEY (`PRNCPL_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-191', '2.0.4-SNP', '3:b6200ed8a3a04bdde02d42023030acc5', 191);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-192::Jeff Caddel (generated)::(Checksum: 3:42753146353d48cb6c1c2134b3044fff)
CREATE TABLE `krim_role_document_t` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ROLE_ID` VARCHAR(40) NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ROLE_TYP_ID` VARCHAR(40) NOT NULL, `ROLE_NMSPC` VARCHAR(100) NOT NULL, `ROLE_NM` VARCHAR(400), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `DESC_TXT` VARCHAR(4000), CONSTRAINT `PK_KRIM_ROLE_DOCUMENT_T` PRIMARY KEY (`FDOC_NBR`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-192', '2.0.4-SNP', '3:42753146353d48cb6c1c2134b3044fff', 192);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-193::Jeff Caddel (generated)::(Checksum: 3:37f00f9fb4522b91d622d39d650ea255)
CREATE TABLE `krim_role_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ROLE_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-193', '2.0.4-SNP', '3:37f00f9fb4522b91d622d39d650ea255', 193);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-194::Jeff Caddel (generated)::(Checksum: 3:89d832741dd6c95956ca4b1b18a1954f)
CREATE TABLE `krim_role_mbr_attr_data_t` (`ATTR_DATA_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ROLE_MBR_ID` VARCHAR(40), `KIM_TYP_ID` VARCHAR(40) NOT NULL, `KIM_ATTR_DEFN_ID` VARCHAR(40), `ATTR_VAL` VARCHAR(400), CONSTRAINT `PK_KRIM_ROLE_MBR_ATTR_DATA_T` PRIMARY KEY (`ATTR_DATA_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-194', '2.0.4-SNP', '3:89d832741dd6c95956ca4b1b18a1954f', 194);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-195::Jeff Caddel (generated)::(Checksum: 3:b093153764dc2dc80090a3e7da5fe7e4)
CREATE TABLE `krim_role_mbr_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ROLE_MBR_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-195', '2.0.4-SNP', '3:b093153764dc2dc80090a3e7da5fe7e4', 195);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-196::Jeff Caddel (generated)::(Checksum: 3:2a17e6ce3eff381d61e72c90da6d3a64)
CREATE TABLE `krim_role_mbr_t` (`ROLE_MBR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `ROLE_ID` VARCHAR(40), `MBR_ID` VARCHAR(40), `MBR_TYP_CD` CHAR(1) DEFAULT 'P', `ACTV_FRM_DT` DATETIME, `ACTV_TO_DT` DATETIME, `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ROLE_MBR_T` PRIMARY KEY (`ROLE_MBR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-196', '2.0.4-SNP', '3:2a17e6ce3eff381d61e72c90da6d3a64', 196);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-197::Jeff Caddel (generated)::(Checksum: 3:4b5b24d4422f62a1484617393b6b2b26)
CREATE TABLE `krim_role_perm_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ROLE_PERM_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-197', '2.0.4-SNP', '3:4b5b24d4422f62a1484617393b6b2b26', 197);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-198::Jeff Caddel (generated)::(Checksum: 3:1667ac3b120049ca7c9f581a4f2ae752)
CREATE TABLE `krim_role_perm_t` (`ROLE_PERM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ROLE_ID` VARCHAR(40), `PERM_ID` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_ROLE_PERM_T` PRIMARY KEY (`ROLE_PERM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-198', '2.0.4-SNP', '3:1667ac3b120049ca7c9f581a4f2ae752', 198);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-199::Jeff Caddel (generated)::(Checksum: 3:b4b467d5234fbe984597e355450cb2fc)
CREATE TABLE `krim_role_rsp_actn_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ROLE_RSP_ACTN_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-199', '2.0.4-SNP', '3:b4b467d5234fbe984597e355450cb2fc', 199);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-200::Jeff Caddel (generated)::(Checksum: 3:bc11d0c0ad960986a34a634bc387466e)
CREATE TABLE `krim_role_rsp_actn_t` (`ROLE_RSP_ACTN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ACTN_TYP_CD` VARCHAR(40), `PRIORITY_NBR` DECIMAL(3,0), `ACTN_PLCY_CD` VARCHAR(40), `ROLE_MBR_ID` VARCHAR(40), `ROLE_RSP_ID` VARCHAR(40), `FRC_ACTN` VARCHAR(1) DEFAULT 'N', CONSTRAINT `PK_KRIM_ROLE_RSP_ACTN_T` PRIMARY KEY (`ROLE_RSP_ACTN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-200', '2.0.4-SNP', '3:bc11d0c0ad960986a34a634bc387466e', 200);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-201::Jeff Caddel (generated)::(Checksum: 3:9aa82bc30b63846ccee470cbd1488f82)
CREATE TABLE `krim_role_rsp_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_ROLE_RSP_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-201', '2.0.4-SNP', '3:9aa82bc30b63846ccee470cbd1488f82', 201);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-202::Jeff Caddel (generated)::(Checksum: 3:8e1403647ea4e4eaa6a7aa8d738084fe)
CREATE TABLE `krim_role_rsp_t` (`ROLE_RSP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ROLE_ID` VARCHAR(40), `RSP_ID` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_ROLE_RSP_T` PRIMARY KEY (`ROLE_RSP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-202', '2.0.4-SNP', '3:8e1403647ea4e4eaa6a7aa8d738084fe', 202);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-203::Jeff Caddel (generated)::(Checksum: 3:0ec2fe3d7a5366dededf5bc6108b44a9)
CREATE TABLE `krim_role_t` (`ROLE_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ROLE_NM` VARCHAR(80) NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, `DESC_TXT` VARCHAR(4000), `KIM_TYP_ID` VARCHAR(40) NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `LAST_UPDT_DT` DATETIME, CONSTRAINT `PK_KRIM_ROLE_T` PRIMARY KEY (`ROLE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-203', '2.0.4-SNP', '3:0ec2fe3d7a5366dededf5bc6108b44a9', 203);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-204::Jeff Caddel (generated)::(Checksum: 3:401c884bf6af6b173cd77e3526ab0362)
CREATE TABLE `krim_rsp_attr_data_t` (`ATTR_DATA_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `RSP_ID` VARCHAR(40), `KIM_TYP_ID` VARCHAR(40) NOT NULL, `KIM_ATTR_DEFN_ID` VARCHAR(40), `ATTR_VAL` VARCHAR(400), CONSTRAINT `PK_KRIM_RSP_ATTR_DATA_T` PRIMARY KEY (`ATTR_DATA_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-204', '2.0.4-SNP', '3:401c884bf6af6b173cd77e3526ab0362', 204);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-205::Jeff Caddel (generated)::(Checksum: 3:836b9188d2def5e852c2668da19b9f46)
CREATE TABLE `krim_rsp_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_RSP_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-205', '2.0.4-SNP', '3:836b9188d2def5e852c2668da19b9f46', 205);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-206::Jeff Caddel (generated)::(Checksum: 3:236fc60e870335c8ef2c10d065e3db1b)
CREATE TABLE `krim_rsp_rqrd_attr_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_RSP_RQRD_ATTR_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-206', '2.0.4-SNP', '3:236fc60e870335c8ef2c10d065e3db1b', 206);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-207::Jeff Caddel (generated)::(Checksum: 3:a42dfd4c77269c2796bbb4abcd09c2e7)
CREATE TABLE `krim_rsp_t` (`RSP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `RSP_TMPL_ID` VARCHAR(40), `NMSPC_CD` VARCHAR(40) NOT NULL, `NM` VARCHAR(100) NOT NULL, `DESC_TXT` VARCHAR(400), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_RSP_T` PRIMARY KEY (`RSP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-207', '2.0.4-SNP', '3:a42dfd4c77269c2796bbb4abcd09c2e7', 207);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-208::Jeff Caddel (generated)::(Checksum: 3:1f3bfa69a567e5b5f762734330b41328)
CREATE TABLE `krim_rsp_tmpl_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_RSP_TMPL_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-208', '2.0.4-SNP', '3:1f3bfa69a567e5b5f762734330b41328', 208);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-209::Jeff Caddel (generated)::(Checksum: 3:60b3b215c7310f91951c59f62250e708)
CREATE TABLE `krim_rsp_tmpl_t` (`RSP_TMPL_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, `NM` VARCHAR(100) NOT NULL, `KIM_TYP_ID` VARCHAR(100) NOT NULL, `DESC_TXT` VARCHAR(400), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_RSP_TMPL_T` PRIMARY KEY (`RSP_TMPL_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-209', '2.0.4-SNP', '3:60b3b215c7310f91951c59f62250e708', 209);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-210::Jeff Caddel (generated)::(Checksum: 3:d5c3af2a6f68b11609fa7016b7cf8e54)
CREATE TABLE `krim_typ_attr_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_TYP_ATTR_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-210', '2.0.4-SNP', '3:d5c3af2a6f68b11609fa7016b7cf8e54', 210);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-211::Jeff Caddel (generated)::(Checksum: 3:5e843015a2f633b5be791cd8003eac34)
CREATE TABLE `krim_typ_attr_t` (`KIM_TYP_ATTR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `SORT_CD` VARCHAR(2), `KIM_TYP_ID` VARCHAR(40) NOT NULL, `KIM_ATTR_DEFN_ID` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', CONSTRAINT `PK_KRIM_TYP_ATTR_T` PRIMARY KEY (`KIM_TYP_ATTR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-211', '2.0.4-SNP', '3:5e843015a2f633b5be791cd8003eac34', 211);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-212::Jeff Caddel (generated)::(Checksum: 3:54934a605555aeea13cb4a58e0912a2b)
CREATE TABLE `krim_typ_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRIM_TYP_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-212', '2.0.4-SNP', '3:54934a605555aeea13cb4a58e0912a2b', 212);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-213::Jeff Caddel (generated)::(Checksum: 3:16309a2b869de1cc21dbb15deedcdd20)
CREATE TABLE `krim_typ_t` (`KIM_TYP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `NM` VARCHAR(100), `SRVC_NM` VARCHAR(200), `ACTV_IND` VARCHAR(1) DEFAULT 'Y', `NMSPC_CD` VARCHAR(40), CONSTRAINT `PK_KRIM_TYP_T` PRIMARY KEY (`KIM_TYP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-213', '2.0.4-SNP', '3:16309a2b869de1cc21dbb15deedcdd20', 213);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-214::Jeff Caddel (generated)::(Checksum: 3:42c6a7dfd8b420705f85807b483f426d)
CREATE TABLE `krlc_cmp_t` (`CAMPUS_CD` VARCHAR(2) DEFAULT '' NOT NULL, `CAMPUS_NM` VARCHAR(250), `CAMPUS_SHRT_NM` VARCHAR(250), `CAMPUS_TYP_CD` VARCHAR(1), `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y' NOT NULL, CONSTRAINT `PK_KRLC_CMP_T` PRIMARY KEY (`CAMPUS_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-214', '2.0.4-SNP', '3:42c6a7dfd8b420705f85807b483f426d', 214);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-215::Jeff Caddel (generated)::(Checksum: 3:0231b9958ab6daa9db81aeb7fe12ee03)
CREATE TABLE `krlc_cmp_typ_t` (`CAMPUS_TYP_CD` VARCHAR(1) DEFAULT '' NOT NULL, `CMP_TYP_NM` VARCHAR(250), `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y' NOT NULL, CONSTRAINT `PK_KRLC_CMP_TYP_T` PRIMARY KEY (`CAMPUS_TYP_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-215', '2.0.4-SNP', '3:0231b9958ab6daa9db81aeb7fe12ee03', 215);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-216::Jeff Caddel (generated)::(Checksum: 3:7444daab5a88cd7474b82564b4368d72)
CREATE TABLE `krlc_cntry_t` (`POSTAL_CNTRY_CD` VARCHAR(2) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `POSTAL_CNTRY_NM` VARCHAR(40), `PSTL_CNTRY_RSTRC_IND` VARCHAR(1) NOT NULL, `ACTV_IND` VARCHAR(1) DEFAULT 'Y' NOT NULL, `ALT_POSTAL_CNTRY_CD` VARCHAR(3), CONSTRAINT `PK_KRLC_CNTRY_T` PRIMARY KEY (`POSTAL_CNTRY_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-216', '2.0.4-SNP', '3:7444daab5a88cd7474b82564b4368d72', 216);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-217::Jeff Caddel (generated)::(Checksum: 3:8296f91697a0b8c136a7803bd5f9a76a)
CREATE TABLE `krlc_cnty_t` (`COUNTY_CD` VARCHAR(10) DEFAULT '' NOT NULL, `STATE_CD` VARCHAR(2) DEFAULT '' NOT NULL, `POSTAL_CNTRY_CD` VARCHAR(2) DEFAULT 'US' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `COUNTY_NM` VARCHAR(100), `ACTV_IND` VARCHAR(1), CONSTRAINT `PK_KRLC_CNTY_T` PRIMARY KEY (`COUNTY_CD`, `STATE_CD`, `POSTAL_CNTRY_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-217', '2.0.4-SNP', '3:8296f91697a0b8c136a7803bd5f9a76a', 217);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-218::Jeff Caddel (generated)::(Checksum: 3:57e71686a9674f2caebf0e1680e49ff8)
CREATE TABLE `krlc_pstl_cd_t` (`POSTAL_CD` VARCHAR(20) DEFAULT '' NOT NULL, `POSTAL_CNTRY_CD` VARCHAR(2) DEFAULT 'US' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `POSTAL_STATE_CD` VARCHAR(2), `COUNTY_CD` VARCHAR(10), `POSTAL_CITY_NM` VARCHAR(30), `ACTV_IND` VARCHAR(1) DEFAULT 'Y' NOT NULL, CONSTRAINT `PK_KRLC_PSTL_CD_T` PRIMARY KEY (`POSTAL_CD`, `POSTAL_CNTRY_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-218', '2.0.4-SNP', '3:57e71686a9674f2caebf0e1680e49ff8', 218);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-219::Jeff Caddel (generated)::(Checksum: 3:e914c76ba036d1b53c396ceb4f8b67f9)
CREATE TABLE `krlc_st_t` (`POSTAL_STATE_CD` VARCHAR(2) DEFAULT '' NOT NULL, `POSTAL_CNTRY_CD` VARCHAR(2) DEFAULT 'US' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `POSTAL_STATE_NM` VARCHAR(40), `ACTV_IND` VARCHAR(1) DEFAULT 'Y' NOT NULL, CONSTRAINT `PK_KRLC_ST_T` PRIMARY KEY (`POSTAL_STATE_CD`, `POSTAL_CNTRY_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-219', '2.0.4-SNP', '3:e914c76ba036d1b53c396ceb4f8b67f9', 219);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-220::Jeff Caddel (generated)::(Checksum: 3:9c3b4e7959448bc0c49f7ddffdedc584)
CREATE TABLE `krms_actn_attr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_ACTN_ATTR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-220', '2.0.4-SNP', '3:9c3b4e7959448bc0c49f7ddffdedc584', 220);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-221::Jeff Caddel (generated)::(Checksum: 3:fe3f1dc4f0c16786b12475a3d72ea813)
CREATE TABLE `krms_actn_attr_t` (`ACTN_ATTR_DATA_ID` VARCHAR(40) DEFAULT '' NOT NULL, `ACTN_ID` VARCHAR(40) NOT NULL, `ATTR_DEFN_ID` VARCHAR(40) NOT NULL, `ATTR_VAL` VARCHAR(400), `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_ACTN_ATTR_T` PRIMARY KEY (`ACTN_ATTR_DATA_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-221', '2.0.4-SNP', '3:fe3f1dc4f0c16786b12475a3d72ea813', 221);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-222::Jeff Caddel (generated)::(Checksum: 3:1f62fe7d2357be5ec8e715f3e487a458)
CREATE TABLE `krms_actn_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_ACTN_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-222', '2.0.4-SNP', '3:1f62fe7d2357be5ec8e715f3e487a458', 222);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-223::Jeff Caddel (generated)::(Checksum: 3:26bd9a71269c8a132ca332953b67651b)
CREATE TABLE `krms_actn_t` (`ACTN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NM` VARCHAR(40), `DESC_TXT` VARCHAR(4000), `TYP_ID` VARCHAR(40) NOT NULL, `RULE_ID` VARCHAR(40), `SEQ_NO` DECIMAL(5,0), `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, CONSTRAINT `PK_KRMS_ACTN_T` PRIMARY KEY (`ACTN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-223', '2.0.4-SNP', '3:26bd9a71269c8a132ca332953b67651b', 223);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-224::Jeff Caddel (generated)::(Checksum: 3:1e209ad68d1e2e8ed653fb4f6fba9938)
CREATE TABLE `krms_agenda_attr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_AGENDA_ATTR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-224', '2.0.4-SNP', '3:1e209ad68d1e2e8ed653fb4f6fba9938', 224);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-225::Jeff Caddel (generated)::(Checksum: 3:5c6a0eaeefb0c26e7627cbd02e83d272)
CREATE TABLE `krms_agenda_attr_t` (`AGENDA_ATTR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `AGENDA_ID` VARCHAR(40) NOT NULL, `ATTR_VAL` VARCHAR(400), `ATTR_DEFN_ID` VARCHAR(40) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_AGENDA_ATTR_T` PRIMARY KEY (`AGENDA_ATTR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-225', '2.0.4-SNP', '3:5c6a0eaeefb0c26e7627cbd02e83d272', 225);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-226::Jeff Caddel (generated)::(Checksum: 3:67c6932c142fb7af38ee298e58b704c2)
CREATE TABLE `krms_agenda_itm_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_AGENDA_ITM_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-226', '2.0.4-SNP', '3:67c6932c142fb7af38ee298e58b704c2', 226);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-227::Jeff Caddel (generated)::(Checksum: 3:33d95d1b2a968b5095de3e4c8abe666b)
CREATE TABLE `krms_agenda_itm_t` (`AGENDA_ITM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RULE_ID` VARCHAR(40), `SUB_AGENDA_ID` VARCHAR(40), `AGENDA_ID` VARCHAR(40) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, `WHEN_TRUE` VARCHAR(40), `WHEN_FALSE` VARCHAR(40), `ALWAYS` VARCHAR(40), CONSTRAINT `PK_KRMS_AGENDA_ITM_T` PRIMARY KEY (`AGENDA_ITM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-227', '2.0.4-SNP', '3:33d95d1b2a968b5095de3e4c8abe666b', 227);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-228::Jeff Caddel (generated)::(Checksum: 3:6f523c0410e5e516d94049f9b6462463)
CREATE TABLE `krms_agenda_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_AGENDA_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-228', '2.0.4-SNP', '3:6f523c0410e5e516d94049f9b6462463', 228);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-229::Jeff Caddel (generated)::(Checksum: 3:d762ec77daed17f3a2df1ea81325277b)
CREATE TABLE `krms_agenda_t` (`AGENDA_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NM` VARCHAR(100) NOT NULL, `CNTXT_ID` VARCHAR(40) NOT NULL, `INIT_AGENDA_ITM_ID` VARCHAR(40), `TYP_ID` VARCHAR(40) NOT NULL, `ACTV` VARCHAR(1) DEFAULT 'Y' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_AGENDA_T` PRIMARY KEY (`AGENDA_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-229', '2.0.4-SNP', '3:d762ec77daed17f3a2df1ea81325277b', 229);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-230::Jeff Caddel (generated)::(Checksum: 3:6aaf82e7bd59b7858676b48f263d3b56)
CREATE TABLE `krms_attr_defn_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_ATTR_DEFN_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-230', '2.0.4-SNP', '3:6aaf82e7bd59b7858676b48f263d3b56', 230);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-231::Jeff Caddel (generated)::(Checksum: 3:013a162d37c7c9171683504e76d1e0c1)
CREATE TABLE `krms_attr_defn_t` (`ATTR_DEFN_ID` VARCHAR(255) DEFAULT '' NOT NULL, `NM` VARCHAR(100) NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, `LBL` VARCHAR(40), `ACTV` VARCHAR(1) DEFAULT 'Y' NOT NULL, `CMPNT_NM` VARCHAR(100), `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, `DESC_TXT` VARCHAR(255), CONSTRAINT `PK_KRMS_ATTR_DEFN_T` PRIMARY KEY (`ATTR_DEFN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-231', '2.0.4-SNP', '3:013a162d37c7c9171683504e76d1e0c1', 231);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-232::Jeff Caddel (generated)::(Checksum: 3:a4e0526cd882cae8e2662ab6239a6e5a)
CREATE TABLE `krms_cmpnd_prop_props_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_CMPND_PROP_PROPS_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-232', '2.0.4-SNP', '3:a4e0526cd882cae8e2662ab6239a6e5a', 232);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-233::Jeff Caddel (generated)::(Checksum: 3:4b6258084b3cbada1289454400f76753)
CREATE TABLE `krms_cmpnd_prop_props_t` (`CMPND_PROP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `PROP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `SEQ_NO` DECIMAL(5,0) NOT NULL, CONSTRAINT `PK_KRMS_CMPND_PROP_PROPS_T` PRIMARY KEY (`CMPND_PROP_ID`, `PROP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-233', '2.0.4-SNP', '3:4b6258084b3cbada1289454400f76753', 233);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-234::Jeff Caddel (generated)::(Checksum: 3:80d5cd83ebb37debfc1425c84b5094bc)
CREATE TABLE `krms_cntxt_attr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_ATTR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-234', '2.0.4-SNP', '3:80d5cd83ebb37debfc1425c84b5094bc', 234);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-235::Jeff Caddel (generated)::(Checksum: 3:b19c082d732c861a432ac33477d15f43)
CREATE TABLE `krms_cntxt_attr_t` (`CNTXT_ATTR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `CNTXT_ID` VARCHAR(40) NOT NULL, `ATTR_VAL` VARCHAR(400), `ATTR_DEFN_ID` VARCHAR(40), `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_ATTR_T` PRIMARY KEY (`CNTXT_ATTR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-235', '2.0.4-SNP', '3:b19c082d732c861a432ac33477d15f43', 235);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-236::Jeff Caddel (generated)::(Checksum: 3:c986ad25fa870a6ebfdd3e9756209bca)
CREATE TABLE `krms_cntxt_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-236', '2.0.4-SNP', '3:c986ad25fa870a6ebfdd3e9756209bca', 236);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-237::Jeff Caddel (generated)::(Checksum: 3:25913563eddf32a7e609e99ab7da1aa7)
CREATE TABLE `krms_cntxt_t` (`CNTXT_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, `NM` VARCHAR(100) NOT NULL, `TYP_ID` VARCHAR(40) NOT NULL, `ACTV` VARCHAR(1) DEFAULT 'Y' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, `DESC_TXT` VARCHAR(255), CONSTRAINT `PK_KRMS_CNTXT_T` PRIMARY KEY (`CNTXT_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-237', '2.0.4-SNP', '3:25913563eddf32a7e609e99ab7da1aa7', 237);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-238::Jeff Caddel (generated)::(Checksum: 3:163864657c9697e75be674734beb5ab9)
CREATE TABLE `krms_cntxt_term_spec_prereq_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_TERM_SPEC_PREREQ_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-238', '2.0.4-SNP', '3:163864657c9697e75be674734beb5ab9', 238);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-239::Jeff Caddel (generated)::(Checksum: 3:4093ee18c8b745f27cd117b4f4644fbf)
CREATE TABLE `krms_cntxt_vld_actn_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_VLD_ACTN_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-239', '2.0.4-SNP', '3:4093ee18c8b745f27cd117b4f4644fbf', 239);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-240::Jeff Caddel (generated)::(Checksum: 3:b18a23335bd53245411041a166b9f607)
CREATE TABLE `krms_cntxt_vld_actn_t` (`CNTXT_VLD_ACTN_ID` VARCHAR(40) DEFAULT '' NOT NULL, `CNTXT_ID` VARCHAR(40) NOT NULL, `ACTN_TYP_ID` VARCHAR(40) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_VLD_ACTN_T` PRIMARY KEY (`CNTXT_VLD_ACTN_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-240', '2.0.4-SNP', '3:b18a23335bd53245411041a166b9f607', 240);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-241::Jeff Caddel (generated)::(Checksum: 3:17b72f091e2cdd5820e99b7d29c455d9)
CREATE TABLE `krms_cntxt_vld_event_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_VLD_EVENT_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-241', '2.0.4-SNP', '3:17b72f091e2cdd5820e99b7d29c455d9', 241);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-242::Jeff Caddel (generated)::(Checksum: 3:cadc49f7674495baa994a16e860609e3)
CREATE TABLE `krms_cntxt_vld_event_t` (`CNTXT_VLD_EVENT_ID` VARCHAR(40) DEFAULT '' NOT NULL, `CNTXT_ID` VARCHAR(40) NOT NULL, `EVENT_NM` VARCHAR(255) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_VLD_EVENT_T` PRIMARY KEY (`CNTXT_VLD_EVENT_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-242', '2.0.4-SNP', '3:cadc49f7674495baa994a16e860609e3', 242);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-243::Jeff Caddel (generated)::(Checksum: 3:adcb187ac66132f6c49d6f88c3a2f0fc)
CREATE TABLE `krms_cntxt_vld_func_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_VLD_FUNC_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-243', '2.0.4-SNP', '3:adcb187ac66132f6c49d6f88c3a2f0fc', 243);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-244::Jeff Caddel (generated)::(Checksum: 3:a01e51327e1eb0cac3a49db8c682e65e)
CREATE TABLE `krms_cntxt_vld_func_t` (`CNTXT_VLD_FUNC_ID` VARCHAR(40) DEFAULT '' NOT NULL, `CNTXT_ID` VARCHAR(40) NOT NULL, `FUNC_ID` VARCHAR(40) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_VLD_FUNC_T` PRIMARY KEY (`CNTXT_VLD_FUNC_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-244', '2.0.4-SNP', '3:a01e51327e1eb0cac3a49db8c682e65e', 244);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-245::Jeff Caddel (generated)::(Checksum: 3:cd182fbe30e1a5b77094d77a6e1211a8)
CREATE TABLE `krms_cntxt_vld_rule_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_VLD_RULE_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-245', '2.0.4-SNP', '3:cd182fbe30e1a5b77094d77a6e1211a8', 245);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-246::Jeff Caddel (generated)::(Checksum: 3:603f99e1b496952483e2417e076f175a)
CREATE TABLE `krms_cntxt_vld_rule_t` (`CNTXT_VLD_RULE_ID` VARCHAR(40) DEFAULT '' NOT NULL, `CNTXT_ID` VARCHAR(40) NOT NULL, `RULE_ID` VARCHAR(40) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_CNTXT_VLD_RULE_T` PRIMARY KEY (`CNTXT_VLD_RULE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-246', '2.0.4-SNP', '3:603f99e1b496952483e2417e076f175a', 246);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-247::Jeff Caddel (generated)::(Checksum: 3:321275be585a9a831006dbe6e16e597a)
CREATE TABLE `krms_cntxt_vld_term_spec_t` (`CNTXT_TERM_SPEC_PREREQ_ID` VARCHAR(40) DEFAULT '' NOT NULL, `CNTXT_ID` VARCHAR(40) NOT NULL, `TERM_SPEC_ID` VARCHAR(40) NOT NULL, `PREREQ` VARCHAR(1) DEFAULT 'n', CONSTRAINT `PK_KRMS_CNTXT_VLD_TERM_SPEC_T` PRIMARY KEY (`CNTXT_TERM_SPEC_PREREQ_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-247', '2.0.4-SNP', '3:321275be585a9a831006dbe6e16e597a', 247);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-248::Jeff Caddel (generated)::(Checksum: 3:d9ac641cb9d2bd8c630d97d914e06ed5)
CREATE TABLE `krms_ctgry_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_CTGRY_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-248', '2.0.4-SNP', '3:d9ac641cb9d2bd8c630d97d914e06ed5', 248);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-249::Jeff Caddel (generated)::(Checksum: 3:b11484b32f4bc3097ae973291d15882c)
CREATE TABLE `krms_ctgry_t` (`CTGRY_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NM` VARCHAR(255) NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KRMS_CTGRY_T` PRIMARY KEY (`CTGRY_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-249', '2.0.4-SNP', '3:b11484b32f4bc3097ae973291d15882c', 249);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-250::Jeff Caddel (generated)::(Checksum: 3:9d4b2421908c2195b372f1ce73705cd0)
CREATE TABLE `krms_func_ctgry_t` (`FUNC_ID` VARCHAR(40) DEFAULT '' NOT NULL, `CTGRY_ID` VARCHAR(40) DEFAULT '' NOT NULL, CONSTRAINT `PK_KRMS_FUNC_CTGRY_T` PRIMARY KEY (`FUNC_ID`, `CTGRY_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-250', '2.0.4-SNP', '3:9d4b2421908c2195b372f1ce73705cd0', 250);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-251::Jeff Caddel (generated)::(Checksum: 3:2161435700b003588853273fc3b32447)
CREATE TABLE `krms_func_parm_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_FUNC_PARM_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-251', '2.0.4-SNP', '3:2161435700b003588853273fc3b32447', 251);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-252::Jeff Caddel (generated)::(Checksum: 3:a60a9acbd90002d67f533cc9f421d810)
CREATE TABLE `krms_func_parm_t` (`FUNC_PARM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NM` VARCHAR(100) NOT NULL, `DESC_TXT` VARCHAR(255), `TYP` VARCHAR(255) NOT NULL, `FUNC_ID` VARCHAR(40) NOT NULL, `SEQ_NO` DECIMAL(5,0) NOT NULL, CONSTRAINT `PK_KRMS_FUNC_PARM_T` PRIMARY KEY (`FUNC_PARM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-252', '2.0.4-SNP', '3:a60a9acbd90002d67f533cc9f421d810', 252);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-253::Jeff Caddel (generated)::(Checksum: 3:3830b98c621e6f21a1b70cbd2394e3d6)
CREATE TABLE `krms_func_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_FUNC_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-253', '2.0.4-SNP', '3:3830b98c621e6f21a1b70cbd2394e3d6', 253);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-254::Jeff Caddel (generated)::(Checksum: 3:7e99f13a8ec4214233dca9ce41b86ff2)
CREATE TABLE `krms_func_t` (`FUNC_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, `NM` VARCHAR(100) NOT NULL, `DESC_TXT` VARCHAR(255), `RTRN_TYP` VARCHAR(255) NOT NULL, `TYP_ID` VARCHAR(40) NOT NULL, `ACTV` VARCHAR(1) DEFAULT 'Y' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_FUNC_T` PRIMARY KEY (`FUNC_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-254', '2.0.4-SNP', '3:7e99f13a8ec4214233dca9ce41b86ff2', 254);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-255::Jeff Caddel (generated)::(Checksum: 3:a65b2be6eb07ff80c04c88c52b60048e)
CREATE TABLE `krms_prop_parm_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_PROP_PARM_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-255', '2.0.4-SNP', '3:a65b2be6eb07ff80c04c88c52b60048e', 255);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-256::Jeff Caddel (generated)::(Checksum: 3:6d5c1458ded614f20dd0292b7a560abe)
CREATE TABLE `krms_prop_parm_t` (`PROP_PARM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `PROP_ID` VARCHAR(40) NOT NULL, `PARM_VAL` VARCHAR(255), `PARM_TYP_CD` VARCHAR(1) NOT NULL, `SEQ_NO` DECIMAL(5,0) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_PROP_PARM_T` PRIMARY KEY (`PROP_PARM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-256', '2.0.4-SNP', '3:6d5c1458ded614f20dd0292b7a560abe', 256);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-257::Jeff Caddel (generated)::(Checksum: 3:e369a6fb426f5f20d757233aaed8f87a)
CREATE TABLE `krms_prop_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_PROP_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-257', '2.0.4-SNP', '3:e369a6fb426f5f20d757233aaed8f87a', 257);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-258::Jeff Caddel (generated)::(Checksum: 3:22b078eeeed0d268d33798fe5952207f)
CREATE TABLE `krms_prop_t` (`PROP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DESC_TXT` VARCHAR(100), `TYP_ID` VARCHAR(40), `DSCRM_TYP_CD` VARCHAR(10) NOT NULL, `CMPND_OP_CD` VARCHAR(40), `RULE_ID` VARCHAR(40) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_PROP_T` PRIMARY KEY (`PROP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-258', '2.0.4-SNP', '3:22b078eeeed0d268d33798fe5952207f', 258);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-259::Jeff Caddel (generated)::(Checksum: 3:87ba62d3811507a5850fb96c6a88d2a0)
CREATE TABLE `krms_rule_attr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_RULE_ATTR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-259', '2.0.4-SNP', '3:87ba62d3811507a5850fb96c6a88d2a0', 259);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-260::Jeff Caddel (generated)::(Checksum: 3:2366b92c002eeec8fa07a831f284eb67)
CREATE TABLE `krms_rule_attr_t` (`RULE_ATTR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `RULE_ID` VARCHAR(40) NOT NULL, `ATTR_DEFN_ID` VARCHAR(40) NOT NULL, `ATTR_VAL` VARCHAR(400), `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_RULE_ATTR_T` PRIMARY KEY (`RULE_ATTR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-260', '2.0.4-SNP', '3:2366b92c002eeec8fa07a831f284eb67', 260);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-261::Jeff Caddel (generated)::(Checksum: 3:44f259e4d744e838dd7ac7dc318ddc63)
CREATE TABLE `krms_rule_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_RULE_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-261', '2.0.4-SNP', '3:44f259e4d744e838dd7ac7dc318ddc63', 261);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-262::Jeff Caddel (generated)::(Checksum: 3:53ba676f8f8cb04394b3e374203df6ad)
CREATE TABLE `krms_rule_t` (`RULE_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, `NM` VARCHAR(100) NOT NULL, `TYP_ID` VARCHAR(40) NOT NULL, `PROP_ID` VARCHAR(40), `ACTV` VARCHAR(1) DEFAULT 'Y' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, `DESC_TXT` VARCHAR(4000), CONSTRAINT `PK_KRMS_RULE_T` PRIMARY KEY (`RULE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-262', '2.0.4-SNP', '3:53ba676f8f8cb04394b3e374203df6ad', 262);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-263::Jeff Caddel (generated)::(Checksum: 3:704b33ae2dd838a7d712c23839d17d20)
CREATE TABLE `krms_term_parm_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_TERM_PARM_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-263', '2.0.4-SNP', '3:704b33ae2dd838a7d712c23839d17d20', 263);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-264::Jeff Caddel (generated)::(Checksum: 3:6cea95cb8a6557b05361daf6cb2cecb4)
CREATE TABLE `krms_term_parm_t` (`TERM_PARM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `TERM_ID` VARCHAR(40) NOT NULL, `NM` VARCHAR(255) NOT NULL, `VAL` VARCHAR(255), `VER_NBR` DECIMAL(8,0) NOT NULL, CONSTRAINT `PK_KRMS_TERM_PARM_T` PRIMARY KEY (`TERM_PARM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-264', '2.0.4-SNP', '3:6cea95cb8a6557b05361daf6cb2cecb4', 264);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-265::Jeff Caddel (generated)::(Checksum: 3:8b8061814f4e36db73f7c81e4acf4d8b)
CREATE TABLE `krms_term_rslvr_attr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_TERM_RSLVR_ATTR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-265', '2.0.4-SNP', '3:8b8061814f4e36db73f7c81e4acf4d8b', 265);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-266::Jeff Caddel (generated)::(Checksum: 3:f7c68e7f7180dc049c0cf9cdfd319e4e)
CREATE TABLE `krms_term_rslvr_attr_t` (`TERM_RSLVR_ATTR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `TERM_RSLVR_ID` VARCHAR(40) NOT NULL, `ATTR_DEFN_ID` VARCHAR(40) NOT NULL, `ATTR_VAL` VARCHAR(400), `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_TERM_RSLVR_ATTR_T` PRIMARY KEY (`TERM_RSLVR_ATTR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-266', '2.0.4-SNP', '3:f7c68e7f7180dc049c0cf9cdfd319e4e', 266);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-267::Jeff Caddel (generated)::(Checksum: 3:59f8e7a6a7ee0117c4137be438d91b50)
CREATE TABLE `krms_term_rslvr_input_spec_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_TERM_RSLVR_INPUT_SPEC_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-267', '2.0.4-SNP', '3:59f8e7a6a7ee0117c4137be438d91b50', 267);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-268::Jeff Caddel (generated)::(Checksum: 3:ca85a188b0830f77a88a6b503162621a)
CREATE TABLE `krms_term_rslvr_input_spec_t` (`TERM_SPEC_ID` VARCHAR(40) DEFAULT '' NOT NULL, `TERM_RSLVR_ID` VARCHAR(40) DEFAULT '' NOT NULL, CONSTRAINT `PK_KRMS_TERM_RSLVR_INPUT_SPEC_T` PRIMARY KEY (`TERM_SPEC_ID`, `TERM_RSLVR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-268', '2.0.4-SNP', '3:ca85a188b0830f77a88a6b503162621a', 268);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-269::Jeff Caddel (generated)::(Checksum: 3:95fd029bd8036a7353e613cf413e599c)
CREATE TABLE `krms_term_rslvr_parm_spec_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_TERM_RSLVR_PARM_SPEC_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-269', '2.0.4-SNP', '3:95fd029bd8036a7353e613cf413e599c', 269);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-270::Jeff Caddel (generated)::(Checksum: 3:a2eb7fbc0e810154c2bdbe40fc59781f)
CREATE TABLE `krms_term_rslvr_parm_spec_t` (`TERM_RSLVR_PARM_SPEC_ID` VARCHAR(40) DEFAULT '' NOT NULL, `TERM_RSLVR_ID` VARCHAR(40) NOT NULL, `NM` VARCHAR(45) NOT NULL, `VER_NBR` DECIMAL(8,0) NOT NULL, CONSTRAINT `PK_KRMS_TERM_RSLVR_PARM_SPEC_T` PRIMARY KEY (`TERM_RSLVR_PARM_SPEC_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-270', '2.0.4-SNP', '3:a2eb7fbc0e810154c2bdbe40fc59781f', 270);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-271::Jeff Caddel (generated)::(Checksum: 3:960aaf179389fbfb336881fd72f25157)
CREATE TABLE `krms_term_rslvr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_TERM_RSLVR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-271', '2.0.4-SNP', '3:960aaf179389fbfb336881fd72f25157', 271);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-272::Jeff Caddel (generated)::(Checksum: 3:44eb712ce103578ce110599c96f4bde9)
CREATE TABLE `krms_term_rslvr_t` (`TERM_RSLVR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, `NM` VARCHAR(100) NOT NULL, `TYP_ID` VARCHAR(40) NOT NULL, `OUTPUT_TERM_SPEC_ID` VARCHAR(40) NOT NULL, `ACTV` VARCHAR(1) DEFAULT 'Y' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_TERM_RSLVR_T` PRIMARY KEY (`TERM_RSLVR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-272', '2.0.4-SNP', '3:44eb712ce103578ce110599c96f4bde9', 272);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-273::Jeff Caddel (generated)::(Checksum: 3:26a1a400644fd2f95155eaae9d7a2e41)
CREATE TABLE `krms_term_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_TERM_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-273', '2.0.4-SNP', '3:26a1a400644fd2f95155eaae9d7a2e41', 273);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-274::Jeff Caddel (generated)::(Checksum: 3:23576d797f1f420b0b1d178cc44e4c50)
CREATE TABLE `krms_term_spec_ctgry_t` (`TERM_SPEC_ID` VARCHAR(40) DEFAULT '' NOT NULL, `CTGRY_ID` VARCHAR(40) DEFAULT '' NOT NULL, CONSTRAINT `PK_KRMS_TERM_SPEC_CTGRY_T` PRIMARY KEY (`TERM_SPEC_ID`, `CTGRY_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-274', '2.0.4-SNP', '3:23576d797f1f420b0b1d178cc44e4c50', 274);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-275::Jeff Caddel (generated)::(Checksum: 3:5921dabec5472f1c0b230cdc9e7d3b7a)
CREATE TABLE `krms_term_spec_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_TERM_SPEC_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-275', '2.0.4-SNP', '3:5921dabec5472f1c0b230cdc9e7d3b7a', 275);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-276::Jeff Caddel (generated)::(Checksum: 3:63b4bc6617fae6362399004b0e9a5454)
CREATE TABLE `krms_term_spec_t` (`TERM_SPEC_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NM` VARCHAR(255) NOT NULL, `TYP` VARCHAR(255) NOT NULL, `ACTV` VARCHAR(1) DEFAULT 'Y' NOT NULL, `VER_NBR` DECIMAL(8,0) NOT NULL, `DESC_TXT` VARCHAR(255), `NMSPC_CD` VARCHAR(40) NOT NULL, CONSTRAINT `PK_KRMS_TERM_SPEC_T` PRIMARY KEY (`TERM_SPEC_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-276', '2.0.4-SNP', '3:63b4bc6617fae6362399004b0e9a5454', 276);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-277::Jeff Caddel (generated)::(Checksum: 3:5e265ef3d6bedd678adbd37ba93e8d29)
CREATE TABLE `krms_term_t` (`TERM_ID` VARCHAR(40) DEFAULT '' NOT NULL, `TERM_SPEC_ID` VARCHAR(40) NOT NULL, `VER_NBR` DECIMAL(8,0) NOT NULL, `DESC_TXT` VARCHAR(255), CONSTRAINT `PK_KRMS_TERM_T` PRIMARY KEY (`TERM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-277', '2.0.4-SNP', '3:5e265ef3d6bedd678adbd37ba93e8d29', 277);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-278::Jeff Caddel (generated)::(Checksum: 3:814271f2d0f0f10069a77483f45c00fe)
CREATE TABLE `krms_typ_attr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_TYP_ATTR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-278', '2.0.4-SNP', '3:814271f2d0f0f10069a77483f45c00fe', 278);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-279::Jeff Caddel (generated)::(Checksum: 3:822d7948b0994a3f236702cd1ca80660)
CREATE TABLE `krms_typ_attr_t` (`TYP_ATTR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `SEQ_NO` DECIMAL(5,0) NOT NULL, `TYP_ID` VARCHAR(40) NOT NULL, `ATTR_DEFN_ID` VARCHAR(255) NOT NULL, `ACTV` VARCHAR(1) DEFAULT 'Y' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_TYP_ATTR_T` PRIMARY KEY (`TYP_ATTR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-279', '2.0.4-SNP', '3:822d7948b0994a3f236702cd1ca80660', 279);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-280::Jeff Caddel (generated)::(Checksum: 3:19bb081f45f76268b29951feb378ca0a)
CREATE TABLE `krms_typ_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRMS_TYP_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-280', '2.0.4-SNP', '3:19bb081f45f76268b29951feb378ca0a', 280);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-281::Jeff Caddel (generated)::(Checksum: 3:12def74a8cac601077f92e10119e4f8d)
CREATE TABLE `krms_typ_t` (`TYP_ID` VARCHAR(40) DEFAULT '' NOT NULL, `NM` VARCHAR(100) NOT NULL, `NMSPC_CD` VARCHAR(40) NOT NULL, `SRVC_NM` VARCHAR(200), `ACTV` VARCHAR(1) DEFAULT 'Y' NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRMS_TYP_T` PRIMARY KEY (`TYP_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-281', '2.0.4-SNP', '3:12def74a8cac601077f92e10119e4f8d', 281);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-282::Jeff Caddel (generated)::(Checksum: 3:31a28a624132c92ef18b3a0167bffdff)
CREATE TABLE `krns_adhoc_rte_actn_recip_t` (`RECIP_TYP_CD` DECIMAL(1,0) DEFAULT 0 NOT NULL, `ACTN_RQST_CD` VARCHAR(30) DEFAULT '' NOT NULL, `ACTN_RQST_RECIP_ID` VARCHAR(70) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `DOC_HDR_ID` VARCHAR(14) DEFAULT '' NOT NULL, CONSTRAINT `PK_KRNS_ADHOC_RTE_ACTN_RECIP_T` PRIMARY KEY (`RECIP_TYP_CD`, `ACTN_RQST_CD`, `ACTN_RQST_RECIP_ID`, `DOC_HDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-282', '2.0.4-SNP', '3:31a28a624132c92ef18b3a0167bffdff', 282);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-283::Jeff Caddel (generated)::(Checksum: 3:d7887989e88087e906593c2f83ed7374)
CREATE TABLE `krns_att_t` (`NTE_ID` DECIMAL(14,0) DEFAULT 0 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `MIME_TYP` VARCHAR(255), `FILE_NM` VARCHAR(250), `ATT_ID` VARCHAR(36), `FILE_SZ` DECIMAL(14,0), `ATT_TYP_CD` VARCHAR(40), CONSTRAINT `PK_KRNS_ATT_T` PRIMARY KEY (`NTE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-283', '2.0.4-SNP', '3:d7887989e88087e906593c2f83ed7374', 283);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-284::Jeff Caddel (generated)::(Checksum: 3:c21a8d18e7f46457af95b765ef2e318a)
CREATE TABLE `krns_doc_hdr_t` (`DOC_HDR_ID` VARCHAR(14) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `FDOC_DESC` VARCHAR(40), `ORG_DOC_HDR_ID` VARCHAR(10), `TMPL_DOC_HDR_ID` VARCHAR(14), `EXPLANATION` VARCHAR(400), CONSTRAINT `PK_KRNS_DOC_HDR_T` PRIMARY KEY (`DOC_HDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-284', '2.0.4-SNP', '3:c21a8d18e7f46457af95b765ef2e318a', 284);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-285::Jeff Caddel (generated)::(Checksum: 3:9fc2d3a5a8017ae4f7ab0cb72446c885)
CREATE TABLE `krns_doc_typ_attr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRNS_DOC_TYP_ATTR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-285', '2.0.4-SNP', '3:9fc2d3a5a8017ae4f7ab0cb72446c885', 285);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-286::Jeff Caddel (generated)::(Checksum: 3:9ed7954f7f61c06a9c88f16a0f01c03f)
CREATE TABLE `krns_lock_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRNS_LOCK_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-286', '2.0.4-SNP', '3:9ed7954f7f61c06a9c88f16a0f01c03f', 286);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-287::Jeff Caddel (generated)::(Checksum: 3:3907af8a025ffdede927c33bdbe8ac30)
CREATE TABLE `krns_lookup_rslt_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRNS_LOOKUP_RSLT_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-287', '2.0.4-SNP', '3:3907af8a025ffdede927c33bdbe8ac30', 287);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-288::Jeff Caddel (generated)::(Checksum: 3:0a023942fec64a9c687f69d09e88e74b)
CREATE TABLE `krns_lookup_rslt_t` (`LOOKUP_RSLT_ID` VARCHAR(14) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, `LOOKUP_DT` DATETIME NOT NULL, `SERIALZD_RSLTS` LONGTEXT, CONSTRAINT `PK_KRNS_LOOKUP_RSLT_T` PRIMARY KEY (`LOOKUP_RSLT_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-288', '2.0.4-SNP', '3:0a023942fec64a9c687f69d09e88e74b', 288);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-289::Jeff Caddel (generated)::(Checksum: 3:4aa09a39ef4a6891c53ef36238f9a304)
CREATE TABLE `krns_lookup_sel_t` (`LOOKUP_RSLT_ID` VARCHAR(14) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, `LOOKUP_DT` DATETIME NOT NULL, `SEL_OBJ_IDS` LONGTEXT, CONSTRAINT `PK_KRNS_LOOKUP_SEL_T` PRIMARY KEY (`LOOKUP_RSLT_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-289', '2.0.4-SNP', '3:4aa09a39ef4a6891c53ef36238f9a304', 289);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-290::Jeff Caddel (generated)::(Checksum: 3:c9b009d8d9f58bc812decdff61d10972)
CREATE TABLE `krns_maint_doc_att_t` (`DOC_HDR_ID` VARCHAR(14) DEFAULT '' NOT NULL, `ATT_CNTNT` LONGBLOB NOT NULL, `FILE_NM` VARCHAR(150), `CNTNT_TYP` VARCHAR(255), `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, CONSTRAINT `PK_KRNS_MAINT_DOC_ATT_T` PRIMARY KEY (`DOC_HDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-290', '2.0.4-SNP', '3:c9b009d8d9f58bc812decdff61d10972', 290);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-291::Jeff Caddel (generated)::(Checksum: 3:df1d6aa0271586f1a36073e34ce2148b)
CREATE TABLE `krns_maint_doc_t` (`DOC_HDR_ID` VARCHAR(14) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `DOC_CNTNT` LONGTEXT, CONSTRAINT `PK_KRNS_MAINT_DOC_T` PRIMARY KEY (`DOC_HDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-291', '2.0.4-SNP', '3:df1d6aa0271586f1a36073e34ce2148b', 291);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-292::Jeff Caddel (generated)::(Checksum: 3:7d7ea87844176fa87caf5cb179d10340)
CREATE TABLE `krns_maint_lock_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRNS_MAINT_LOCK_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-292', '2.0.4-SNP', '3:7d7ea87844176fa87caf5cb179d10340', 292);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-293::Jeff Caddel (generated)::(Checksum: 3:3fb4633080584e469e5173cc07d00a82)
CREATE TABLE `krns_maint_lock_t` (`MAINT_LOCK_REP_TXT` VARCHAR(500), `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `DOC_HDR_ID` VARCHAR(14) NOT NULL, `MAINT_LOCK_ID` VARCHAR(14) DEFAULT '' NOT NULL, CONSTRAINT `PK_KRNS_MAINT_LOCK_T` PRIMARY KEY (`MAINT_LOCK_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-293', '2.0.4-SNP', '3:3fb4633080584e469e5173cc07d00a82', 293);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-294::Jeff Caddel (generated)::(Checksum: 3:ae0c62b599f6eead4588376808e23723)
CREATE TABLE `krns_nte_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRNS_NTE_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-294', '2.0.4-SNP', '3:ae0c62b599f6eead4588376808e23723', 294);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-295::Jeff Caddel (generated)::(Checksum: 3:3ad6725d8f2a9e2a0c5cecd7c7efc86e)
CREATE TABLE `krns_nte_t` (`NTE_ID` DECIMAL(14,0) DEFAULT 0 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `RMT_OBJ_ID` VARCHAR(36) NOT NULL, `AUTH_PRNCPL_ID` VARCHAR(40) NOT NULL, `POST_TS` DATETIME NOT NULL, `NTE_TYP_CD` VARCHAR(4) NOT NULL, `TXT` VARCHAR(800), `PRG_CD` VARCHAR(1), `TPC_TXT` VARCHAR(40), CONSTRAINT `PK_KRNS_NTE_T` PRIMARY KEY (`NTE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-295', '2.0.4-SNP', '3:3ad6725d8f2a9e2a0c5cecd7c7efc86e', 295);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-296::Jeff Caddel (generated)::(Checksum: 3:ac955fb31baf8390e9d71e3599ec455c)
CREATE TABLE `krns_nte_typ_t` (`NTE_TYP_CD` VARCHAR(4) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `TYP_DESC_TXT` VARCHAR(100), `ACTV_IND` VARCHAR(1), CONSTRAINT `PK_KRNS_NTE_TYP_T` PRIMARY KEY (`NTE_TYP_CD`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-296', '2.0.4-SNP', '3:ac955fb31baf8390e9d71e3599ec455c', 296);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-297::Jeff Caddel (generated)::(Checksum: 3:de456e6816a684544c6cabf8f54e57ee)
CREATE TABLE `krns_pessimistic_lock_t` (`PESSIMISTIC_LOCK_ID` DECIMAL(14,0) DEFAULT 0 NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `LOCK_DESC_TXT` VARCHAR(4000), `DOC_HDR_ID` VARCHAR(14) NOT NULL, `GNRT_DT` DATETIME NOT NULL, `PRNCPL_ID` VARCHAR(40) NOT NULL, CONSTRAINT `PK_KRNS_PESSIMISTIC_LOCK_T` PRIMARY KEY (`PESSIMISTIC_LOCK_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-297', '2.0.4-SNP', '3:de456e6816a684544c6cabf8f54e57ee', 297);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-298::Jeff Caddel (generated)::(Checksum: 3:2ad6cd654c2ef2cf10bd0c4250401e2f)
CREATE TABLE `krns_sesn_doc_t` (`SESN_DOC_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DOC_HDR_ID` VARCHAR(14) DEFAULT '' NOT NULL, `PRNCPL_ID` VARCHAR(40) DEFAULT '' NOT NULL, `IP_ADDR` VARCHAR(60) DEFAULT '' NOT NULL, `SERIALZD_DOC_FRM` LONGBLOB, `LAST_UPDT_DT` DATETIME, `CONTENT_ENCRYPTED_IND` CHAR(1) DEFAULT 'N', `OBJ_ID` VARCHAR(36), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KRNS_SESN_DOC_T` PRIMARY KEY (`SESN_DOC_ID`, `DOC_HDR_ID`, `PRNCPL_ID`, `IP_ADDR`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-298', '2.0.4-SNP', '3:2ad6cd654c2ef2cf10bd0c4250401e2f', 298);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-299::Jeff Caddel (generated)::(Checksum: 3:35e8813d5cae3fe42f6ccd6451f4ab01)
CREATE TABLE `krsb_bam_parm_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRSB_BAM_PARM_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-299', '2.0.4-SNP', '3:35e8813d5cae3fe42f6ccd6451f4ab01', 299);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-300::Jeff Caddel (generated)::(Checksum: 3:b7d184d8de9b087490fabb214f275afa)
CREATE TABLE `krsb_bam_parm_t` (`BAM_PARM_ID` DECIMAL(14,0) DEFAULT 0 NOT NULL, `BAM_ID` DECIMAL(14,0) NOT NULL, `PARM` LONGTEXT NOT NULL, CONSTRAINT `PK_KRSB_BAM_PARM_T` PRIMARY KEY (`BAM_PARM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-300', '2.0.4-SNP', '3:b7d184d8de9b087490fabb214f275afa', 300);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-301::Jeff Caddel (generated)::(Checksum: 3:12c23c1dff43aa30a48e603592fde17b)
CREATE TABLE `krsb_bam_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRSB_BAM_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-301', '2.0.4-SNP', '3:12c23c1dff43aa30a48e603592fde17b', 301);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-302::Jeff Caddel (generated)::(Checksum: 3:25ec79deb2fd0befd2977d0f779e7d70)
CREATE TABLE `krsb_bam_t` (`BAM_ID` DECIMAL(14,0) DEFAULT 0 NOT NULL, `SVC_NM` VARCHAR(255) NOT NULL, `SVC_URL` VARCHAR(500) NOT NULL, `MTHD_NM` VARCHAR(2000) NOT NULL, `THRD_NM` VARCHAR(500) NOT NULL, `CALL_DT` DATETIME NOT NULL, `TGT_TO_STR` VARCHAR(2000) NOT NULL, `SRVR_IND` DECIMAL(1,0) NOT NULL, `EXCPN_TO_STR` VARCHAR(2000), `EXCPN_MSG` LONGTEXT, CONSTRAINT `PK_KRSB_BAM_T` PRIMARY KEY (`BAM_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-302', '2.0.4-SNP', '3:25ec79deb2fd0befd2977d0f779e7d70', 302);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-303::Jeff Caddel (generated)::(Checksum: 3:7e90583d434b9947be749e902c6c04ba)
CREATE TABLE `krsb_msg_pyld_t` (`MSG_QUE_ID` DECIMAL(14,0) DEFAULT 0 NOT NULL, `MSG_PYLD` LONGTEXT NOT NULL, CONSTRAINT `PK_KRSB_MSG_PYLD_T` PRIMARY KEY (`MSG_QUE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-303', '2.0.4-SNP', '3:7e90583d434b9947be749e902c6c04ba', 303);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-304::Jeff Caddel (generated)::(Checksum: 3:b86170526db03a37f4be9206deb97733)
CREATE TABLE `krsb_msg_que_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRSB_MSG_QUE_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-304', '2.0.4-SNP', '3:b86170526db03a37f4be9206deb97733', 304);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-305::Jeff Caddel (generated)::(Checksum: 3:4b5a101ea3f76a71a73ccdd416fab224)
CREATE TABLE `krsb_msg_que_t` (`MSG_QUE_ID` DECIMAL(14,0) DEFAULT 0 NOT NULL, `DT` DATETIME NOT NULL, `EXP_DT` DATETIME, `PRIO` DECIMAL(8,0) NOT NULL, `STAT_CD` CHAR(1) NOT NULL, `RTRY_CNT` DECIMAL(8,0) NOT NULL, `IP_NBR` VARCHAR(2000) NOT NULL, `SVC_NM` VARCHAR(255), `APPL_ID` VARCHAR(255) NOT NULL, `SVC_MTHD_NM` VARCHAR(2000), `APP_VAL_ONE` VARCHAR(2000), `APP_VAL_TWO` VARCHAR(2000), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_KRSB_MSG_QUE_T` PRIMARY KEY (`MSG_QUE_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-305', '2.0.4-SNP', '3:4b5a101ea3f76a71a73ccdd416fab224', 305);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-306::Jeff Caddel (generated)::(Checksum: 3:af51197ee73085a9621c5c8c2d7e4916)
CREATE TABLE `krsb_qrtz_blob_triggers` (`TRIGGER_NAME` VARCHAR(80) DEFAULT '' NOT NULL, `TRIGGER_GROUP` VARCHAR(80) DEFAULT '' NOT NULL, `BLOB_DATA` LONGBLOB, CONSTRAINT `PK_KRSB_QRTZ_BLOB_TRIGGERS` PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-306', '2.0.4-SNP', '3:af51197ee73085a9621c5c8c2d7e4916', 306);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-307::Jeff Caddel (generated)::(Checksum: 3:cfeb71833b1dac5e998dfb336cf4a84c)
CREATE TABLE `krsb_qrtz_calendars` (`CALENDAR_NAME` VARCHAR(80) DEFAULT '' NOT NULL, `CALENDAR` LONGBLOB NOT NULL, CONSTRAINT `PK_KRSB_QRTZ_CALENDARS` PRIMARY KEY (`CALENDAR_NAME`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-307', '2.0.4-SNP', '3:cfeb71833b1dac5e998dfb336cf4a84c', 307);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-308::Jeff Caddel (generated)::(Checksum: 3:2045cfc753b6bc6db191bd082685a008)
CREATE TABLE `krsb_qrtz_cron_triggers` (`TRIGGER_NAME` VARCHAR(80) DEFAULT '' NOT NULL, `TRIGGER_GROUP` VARCHAR(80) DEFAULT '' NOT NULL, `CRON_EXPRESSION` VARCHAR(80) NOT NULL, `TIME_ZONE_ID` VARCHAR(80), CONSTRAINT `PK_KRSB_QRTZ_CRON_TRIGGERS` PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-308', '2.0.4-SNP', '3:2045cfc753b6bc6db191bd082685a008', 308);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-309::Jeff Caddel (generated)::(Checksum: 3:86c9dd09a2658e76d2a12339ea99ae34)
CREATE TABLE `krsb_qrtz_fired_triggers` (`ENTRY_ID` VARCHAR(95) DEFAULT '' NOT NULL, `TRIGGER_NAME` VARCHAR(80) NOT NULL, `TRIGGER_GROUP` VARCHAR(80) NOT NULL, `IS_VOLATILE` VARCHAR(1) NOT NULL, `INSTANCE_NAME` VARCHAR(80) NOT NULL, `FIRED_TIME` DECIMAL(13,0) NOT NULL, `PRIORITY` DECIMAL(13,0) NOT NULL, `STATE` VARCHAR(16) NOT NULL, `JOB_NAME` VARCHAR(80), `JOB_GROUP` VARCHAR(80), `IS_STATEFUL` VARCHAR(1), `REQUESTS_RECOVERY` VARCHAR(1), CONSTRAINT `PK_KRSB_QRTZ_FIRED_TRIGGERS` PRIMARY KEY (`ENTRY_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-309', '2.0.4-SNP', '3:86c9dd09a2658e76d2a12339ea99ae34', 309);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-310::Jeff Caddel (generated)::(Checksum: 3:c8022c24ec98c32323d18308f2e54d29)
CREATE TABLE `krsb_qrtz_job_details` (`JOB_NAME` VARCHAR(80) DEFAULT '' NOT NULL, `JOB_GROUP` VARCHAR(80) DEFAULT '' NOT NULL, `DESCRIPTION` VARCHAR(120), `JOB_CLASS_NAME` VARCHAR(128) NOT NULL, `IS_DURABLE` VARCHAR(1) NOT NULL, `IS_VOLATILE` VARCHAR(1) NOT NULL, `IS_STATEFUL` VARCHAR(1) NOT NULL, `REQUESTS_RECOVERY` VARCHAR(1) NOT NULL, `JOB_DATA` LONGBLOB, CONSTRAINT `PK_KRSB_QRTZ_JOB_DETAILS` PRIMARY KEY (`JOB_NAME`, `JOB_GROUP`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-310', '2.0.4-SNP', '3:c8022c24ec98c32323d18308f2e54d29', 310);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-311::Jeff Caddel (generated)::(Checksum: 3:87f8e5f731f48abca3412898134f09f4)
CREATE TABLE `krsb_qrtz_job_listeners` (`JOB_NAME` VARCHAR(80) DEFAULT '' NOT NULL, `JOB_GROUP` VARCHAR(80) DEFAULT '' NOT NULL, `JOB_LISTENER` VARCHAR(80) DEFAULT '' NOT NULL, CONSTRAINT `PK_KRSB_QRTZ_JOB_LISTENERS` PRIMARY KEY (`JOB_NAME`, `JOB_GROUP`, `JOB_LISTENER`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-311', '2.0.4-SNP', '3:87f8e5f731f48abca3412898134f09f4', 311);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-312::Jeff Caddel (generated)::(Checksum: 3:e000791d7bfa7e50f3ef701b66bd82ff)
CREATE TABLE `krsb_qrtz_locks` (`LOCK_NAME` VARCHAR(40) DEFAULT '' NOT NULL, CONSTRAINT `PK_KRSB_QRTZ_LOCKS` PRIMARY KEY (`LOCK_NAME`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-312', '2.0.4-SNP', '3:e000791d7bfa7e50f3ef701b66bd82ff', 312);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-313::Jeff Caddel (generated)::(Checksum: 3:4eb5e600d2bfab001515ac914a932844)
CREATE TABLE `krsb_qrtz_paused_trigger_grps` (`TRIGGER_GROUP` VARCHAR(80) DEFAULT '' NOT NULL, CONSTRAINT `PK_KRSB_QRTZ_PAUSED_TRIGGER_GRPS` PRIMARY KEY (`TRIGGER_GROUP`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-313', '2.0.4-SNP', '3:4eb5e600d2bfab001515ac914a932844', 313);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-314::Jeff Caddel (generated)::(Checksum: 3:ee5f74a4d10bb1a13cdf4167b65b1cf6)
CREATE TABLE `krsb_qrtz_scheduler_state` (`INSTANCE_NAME` VARCHAR(80) DEFAULT '' NOT NULL, `LAST_CHECKIN_TIME` DECIMAL(13,0) NOT NULL, `CHECKIN_INTERVAL` DECIMAL(13,0) NOT NULL, CONSTRAINT `PK_KRSB_QRTZ_SCHEDULER_STATE` PRIMARY KEY (`INSTANCE_NAME`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-314', '2.0.4-SNP', '3:ee5f74a4d10bb1a13cdf4167b65b1cf6', 314);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-315::Jeff Caddel (generated)::(Checksum: 3:2fd893d9e4737da94138c083b3b4de30)
CREATE TABLE `krsb_qrtz_simple_triggers` (`TRIGGER_NAME` VARCHAR(80) DEFAULT '' NOT NULL, `TRIGGER_GROUP` VARCHAR(80) DEFAULT '' NOT NULL, `REPEAT_COUNT` DECIMAL(7,0) NOT NULL, `REPEAT_INTERVAL` DECIMAL(12,0) NOT NULL, `TIMES_TRIGGERED` DECIMAL(7,0) NOT NULL, CONSTRAINT `PK_KRSB_QRTZ_SIMPLE_TRIGGERS` PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-315', '2.0.4-SNP', '3:2fd893d9e4737da94138c083b3b4de30', 315);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-316::Jeff Caddel (generated)::(Checksum: 3:5928814b0406df5b3ffe020a20487821)
CREATE TABLE `krsb_qrtz_trigger_listeners` (`TRIGGER_NAME` VARCHAR(80) DEFAULT '' NOT NULL, `TRIGGER_GROUP` VARCHAR(80) DEFAULT '' NOT NULL, `TRIGGER_LISTENER` VARCHAR(80) DEFAULT '' NOT NULL, CONSTRAINT `PK_KRSB_QRTZ_TRIGGER_LISTENERS` PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_LISTENER`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-316', '2.0.4-SNP', '3:5928814b0406df5b3ffe020a20487821', 316);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-317::Jeff Caddel (generated)::(Checksum: 3:9e7a5d2cc18c508468bd3ebbf5a32460)
CREATE TABLE `krsb_qrtz_triggers` (`TRIGGER_NAME` VARCHAR(80) DEFAULT '' NOT NULL, `TRIGGER_GROUP` VARCHAR(80) DEFAULT '' NOT NULL, `JOB_NAME` VARCHAR(80) NOT NULL, `JOB_GROUP` VARCHAR(80) NOT NULL, `IS_VOLATILE` VARCHAR(1) NOT NULL, `DESCRIPTION` VARCHAR(120), `NEXT_FIRE_TIME` DECIMAL(13,0), `PREV_FIRE_TIME` DECIMAL(13,0), `PRIORITY` DECIMAL(13,0), `TRIGGER_STATE` VARCHAR(16) NOT NULL, `TRIGGER_TYPE` VARCHAR(8) NOT NULL, `START_TIME` DECIMAL(13,0) NOT NULL, `END_TIME` DECIMAL(13,0), `CALENDAR_NAME` VARCHAR(80), `MISFIRE_INSTR` DECIMAL(2,0), `JOB_DATA` LONGBLOB, CONSTRAINT `PK_KRSB_QRTZ_TRIGGERS` PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-317', '2.0.4-SNP', '3:9e7a5d2cc18c508468bd3ebbf5a32460', 317);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-318::Jeff Caddel (generated)::(Checksum: 3:f6e93a41a73982fc7851aa137f12c0d1)
CREATE TABLE `krsb_svc_def_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRSB_SVC_DEF_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-318', '2.0.4-SNP', '3:f6e93a41a73982fc7851aa137f12c0d1', 318);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-319::Jeff Caddel (generated)::(Checksum: 3:8761a6c53439d876b4efa60626a3f4d3)
CREATE TABLE `krsb_svc_def_t` (`SVC_DEF_ID` VARCHAR(40) DEFAULT '' NOT NULL, `SVC_NM` VARCHAR(255) NOT NULL, `SVC_URL` VARCHAR(500) NOT NULL, `INSTN_ID` VARCHAR(255) NOT NULL, `APPL_ID` VARCHAR(255) NOT NULL, `SRVR_IP` VARCHAR(40) NOT NULL, `TYP_CD` VARCHAR(40) NOT NULL, `SVC_VER` VARCHAR(40) NOT NULL, `STAT_CD` VARCHAR(1) NOT NULL, `SVC_DSCRPTR_ID` VARCHAR(40) NOT NULL, `CHKSM` VARCHAR(30) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 0 NOT NULL, CONSTRAINT `PK_KRSB_SVC_DEF_T` PRIMARY KEY (`SVC_DEF_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-319', '2.0.4-SNP', '3:8761a6c53439d876b4efa60626a3f4d3', 319);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-320::Jeff Caddel (generated)::(Checksum: 3:4369949a7f80cdcf163668d12eafe02b)
CREATE TABLE `krsb_svc_dscrptr_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_KRSB_SVC_DSCRPTR_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-320', '2.0.4-SNP', '3:4369949a7f80cdcf163668d12eafe02b', 320);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-321::Jeff Caddel (generated)::(Checksum: 3:6feec14017d629c212a764c959a73bdd)
CREATE TABLE `krsb_svc_dscrptr_t` (`SVC_DSCRPTR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `DSCRPTR` LONGTEXT NOT NULL, CONSTRAINT `PK_KRSB_SVC_DSCRPTR_T` PRIMARY KEY (`SVC_DSCRPTR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-321', '2.0.4-SNP', '3:6feec14017d629c212a764c959a73bdd', 321);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-322::Jeff Caddel (generated)::(Checksum: 3:dde436d4d205bc4026fa1a706e415417)
CREATE TABLE `trav_doc_2_accounts` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `ACCT_NUM` VARCHAR(10) DEFAULT '' NOT NULL, CONSTRAINT `PK_TRAV_DOC_2_ACCOUNTS` PRIMARY KEY (`FDOC_NBR`, `ACCT_NUM`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-322', '2.0.4-SNP', '3:dde436d4d205bc4026fa1a706e415417', 322);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-323::Jeff Caddel (generated)::(Checksum: 3:044d6ab68eed331adff8b61376b3fcf6)
CREATE TABLE `trv_acct` (`ACCT_NUM` VARCHAR(10) DEFAULT '' NOT NULL, `ACCT_NAME` VARCHAR(50), `ACCT_TYPE` VARCHAR(100), `ACCT_FO_ID` DECIMAL(14,0), `OBJ_ID` VARCHAR(36), `VER_NBR` DECIMAL(8,0) DEFAULT 0, `SUB_ACCT` VARCHAR(10), `SUB_ACCT_NAME` VARCHAR(50), `CREATE_DT` DATETIME, `SUBSIDIZED_PCT` FLOAT, CONSTRAINT `PK_TRV_ACCT` PRIMARY KEY (`ACCT_NUM`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-323', '2.0.4-SNP', '3:044d6ab68eed331adff8b61376b3fcf6', 323);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-324::Jeff Caddel (generated)::(Checksum: 3:b4d25bfbe7cff3ade85a1897ad48917c)
CREATE TABLE `trv_acct_ext` (`ACCT_NUM` VARCHAR(10) DEFAULT '' NOT NULL, `ACCT_TYPE` VARCHAR(100) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_TRV_ACCT_EXT` PRIMARY KEY (`ACCT_NUM`, `ACCT_TYPE`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-324', '2.0.4-SNP', '3:b4d25bfbe7cff3ade85a1897ad48917c', 324);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-325::Jeff Caddel (generated)::(Checksum: 3:362b3ddfee1d9fc00da6223355300a15)
CREATE TABLE `trv_acct_fo` (`ACCT_FO_ID` DECIMAL(14,0) DEFAULT 0 NOT NULL, `ACCT_FO_USER_NAME` VARCHAR(50) NOT NULL, `OBJ_ID` VARCHAR(36), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_TRV_ACCT_FO` PRIMARY KEY (`ACCT_FO_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-325', '2.0.4-SNP', '3:362b3ddfee1d9fc00da6223355300a15', 325);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-326::Jeff Caddel (generated)::(Checksum: 3:72b599a31d840b7e008ad6489d2596bc)
CREATE TABLE `trv_acct_type` (`ACCT_TYPE` VARCHAR(10) DEFAULT '' NOT NULL, `ACCT_TYPE_NAME` VARCHAR(50), `OBJ_ID` VARCHAR(36), `VER_NBR` DECIMAL(8,0) DEFAULT 0, CONSTRAINT `PK_TRV_ACCT_TYPE` PRIMARY KEY (`ACCT_TYPE`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-326', '2.0.4-SNP', '3:72b599a31d840b7e008ad6489d2596bc', 326);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-327::Jeff Caddel (generated)::(Checksum: 3:77af30842300d382deb999a67f14ef65)
CREATE TABLE `trv_acct_use_rt_t` (`ID` VARCHAR(40) DEFAULT '' NOT NULL, `ACCT_NUM` VARCHAR(10) NOT NULL, `RATE` DECIMAL(8,0) NOT NULL, `ACTV_FRM_DT` DATETIME, `ACTV_TO_DT` DATETIME, CONSTRAINT `PK_TRV_ACCT_USE_RT_T` PRIMARY KEY (`ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-327', '2.0.4-SNP', '3:77af30842300d382deb999a67f14ef65', 327);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-328::Jeff Caddel (generated)::(Checksum: 3:c928041c06d6490032fbe69c3c433ae7)
CREATE TABLE `trv_doc_2` (`FDOC_NBR` VARCHAR(14) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36) NOT NULL, `VER_NBR` DECIMAL(8,0) DEFAULT 1 NOT NULL, `FDOC_EXPLAIN_TXT` VARCHAR(400), `REQUEST_TRAV` VARCHAR(30) NOT NULL, `TRAVELER` VARCHAR(200), `ORG` VARCHAR(60), `DEST` VARCHAR(60), CONSTRAINT `PK_TRV_DOC_2` PRIMARY KEY (`FDOC_NBR`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-328', '2.0.4-SNP', '3:c928041c06d6490032fbe69c3c433ae7', 328);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-329::Jeff Caddel (generated)::(Checksum: 3:7be653a1c7504f6c365c3cd3afcbe01c)
CREATE TABLE `trv_doc_acct` (`DOC_HDR_ID` VARCHAR(40) DEFAULT '' NOT NULL, `ACCT_NUM` VARCHAR(10) DEFAULT '' NOT NULL, CONSTRAINT `PK_TRV_DOC_ACCT` PRIMARY KEY (`DOC_HDR_ID`, `ACCT_NUM`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-329', '2.0.4-SNP', '3:7be653a1c7504f6c365c3cd3afcbe01c', 329);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-330::Jeff Caddel (generated)::(Checksum: 3:2026cee330b29b61c14edab04394c2bb)
CREATE TABLE `trv_fo_id_s` (`id` BIGINT AUTO_INCREMENT  NOT NULL, CONSTRAINT `PK_TRV_FO_ID_S` PRIMARY KEY (`id`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-330', '2.0.4-SNP', '3:2026cee330b29b61c14edab04394c2bb', 330);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml::1312847750578-331::Jeff Caddel (generated)::(Checksum: 3:5b3bb3ecb0ecb324d0cda7dc18f09bc0)
CREATE TABLE `tst_search_attr_indx_tst_doc_t` (`DOC_HDR_ID` VARCHAR(14) DEFAULT '' NOT NULL, `OBJ_ID` VARCHAR(36), `VER_NBR` DECIMAL(14,0), `RTE_LVL_CNT` DECIMAL(14,0), `CNSTNT_STR` VARCHAR(50), `RTD_STR` VARCHAR(50), `HLD_RTD_STR` VARCHAR(50), `RD_ACCS_CNT` DECIMAL(14,0), CONSTRAINT `PK_TST_SEARCH_ATTR_INDX_TST_DOC_T` PRIMARY KEY (`DOC_HDR_ID`));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Table', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/schema.xml', '1312847750578-331', '2.0.4-SNP', '3:5b3bb3ecb0ecb324d0cda7dc18f09bc0', 331);

