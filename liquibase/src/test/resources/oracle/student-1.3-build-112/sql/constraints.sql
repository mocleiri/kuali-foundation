-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml
-- Ran at: 8/8/11 6:37 PM
-- Against: LIQUIBASE@jdbc:oracle:thin:@localhost:1521:XE
-- Liquibase version: 2.0.4-SNAPSHOT
-- *********************************************************************

-- Lock Database
-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1::Jeff Caddel (generated)::(Checksum: 3:aac7229ad891cc7651cea3d5b0115d5b)
ALTER TABLE KRCR_NMSPC_T ADD CONSTRAINT KRCR_NMSPC_TP1 PRIMARY KEY (NMSPC_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1', '2.0.4-SNP', '3:aac7229ad891cc7651cea3d5b0115d5b', 1);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-2::Jeff Caddel (generated)::(Checksum: 3:7befe59c8ae03af3b00f182eeee48174)
ALTER TABLE KRCR_PARM_DTL_TYP_T ADD CONSTRAINT KRCR_PARM_DTL_TYP_TP1 PRIMARY KEY (NMSPC_CD, PARM_DTL_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-2', '2.0.4-SNP', '3:7befe59c8ae03af3b00f182eeee48174', 2);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-3::Jeff Caddel (generated)::(Checksum: 3:c1020d7d859c10209c4a0e219aa40a10)
ALTER TABLE KRCR_PARM_T ADD CONSTRAINT KRCR_PARM_TP1 PRIMARY KEY (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, APPL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-3', '2.0.4-SNP', '3:c1020d7d859c10209c4a0e219aa40a10', 3);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-4::Jeff Caddel (generated)::(Checksum: 3:815fcbaf43c15516444b91750de6d6f9)
ALTER TABLE KRCR_PARM_TYP_T ADD CONSTRAINT KRCR_PARM_TYP_TP1 PRIMARY KEY (PARM_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-4', '2.0.4-SNP', '3:815fcbaf43c15516444b91750de6d6f9', 4);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-5::Jeff Caddel (generated)::(Checksum: 3:650ba02388a0b124a4dab5b2cd4befc9)
ALTER TABLE KREN_CHNL_PRODCR_T ADD CONSTRAINT KREN_CHNL_PRODCR_TP1 PRIMARY KEY (CHNL_ID, PRODCR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-5', '2.0.4-SNP', '3:650ba02388a0b124a4dab5b2cd4befc9', 5);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-6::Jeff Caddel (generated)::(Checksum: 3:d0ba51ffbfa46ce1e461a3506bd00e69)
ALTER TABLE KREN_CHNL_SUBSCRP_T ADD CONSTRAINT KREN_CHNL_SUBSCRP_TP1 PRIMARY KEY (CHNL_SUBSCRP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-6', '2.0.4-SNP', '3:d0ba51ffbfa46ce1e461a3506bd00e69', 6);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-7::Jeff Caddel (generated)::(Checksum: 3:0d22b9f81881e6780c7819eda565d5b4)
ALTER TABLE KREN_CHNL_T ADD CONSTRAINT KREN_CHNL_TP1 PRIMARY KEY (CHNL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-7', '2.0.4-SNP', '3:0d22b9f81881e6780c7819eda565d5b4', 7);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-8::Jeff Caddel (generated)::(Checksum: 3:616bd37f7daa0b43c5016c0eb4157b56)
ALTER TABLE KREN_CNTNT_TYP_T ADD CONSTRAINT KREN_CNTNT_TYP_TP1 PRIMARY KEY (CNTNT_TYP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-8', '2.0.4-SNP', '3:616bd37f7daa0b43c5016c0eb4157b56', 8);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-9::Jeff Caddel (generated)::(Checksum: 3:16c878ac182436b5e5ecce0a8eca1903)
ALTER TABLE KREN_MSG_DELIV_T ADD CONSTRAINT KREN_MSG_DELIV_TP1 PRIMARY KEY (MSG_DELIV_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-9', '2.0.4-SNP', '3:16c878ac182436b5e5ecce0a8eca1903', 9);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-10::Jeff Caddel (generated)::(Checksum: 3:c9661054f97a4c99a962ac0aa1b3e947)
ALTER TABLE KREN_MSG_T ADD CONSTRAINT KREN_MSG_TP1 PRIMARY KEY (MSG_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-10', '2.0.4-SNP', '3:c9661054f97a4c99a962ac0aa1b3e947', 10);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-11::Jeff Caddel (generated)::(Checksum: 3:3f064d50b1ad7c6b58d2ffd6496033aa)
ALTER TABLE KREN_NTFCTN_MSG_DELIV_T ADD CONSTRAINT KREN_NTFCTN_MSG_DELIV_TP1 PRIMARY KEY (NTFCTN_MSG_DELIV_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-11', '2.0.4-SNP', '3:3f064d50b1ad7c6b58d2ffd6496033aa', 11);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-12::Jeff Caddel (generated)::(Checksum: 3:d26b37c50112d7073e5a39edd2e62e6c)
ALTER TABLE KREN_NTFCTN_T ADD CONSTRAINT KREN_NTFCTN_TP1 PRIMARY KEY (NTFCTN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-12', '2.0.4-SNP', '3:d26b37c50112d7073e5a39edd2e62e6c', 12);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-13::Jeff Caddel (generated)::(Checksum: 3:7576ca5aebda12354274221df0079f1c)
ALTER TABLE KREN_PRIO_T ADD CONSTRAINT KREN_PRIO_TP1 PRIMARY KEY (PRIO_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-13', '2.0.4-SNP', '3:7576ca5aebda12354274221df0079f1c', 13);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-14::Jeff Caddel (generated)::(Checksum: 3:109e464129e2be35403d6af1d043b0da)
ALTER TABLE KREN_PRODCR_T ADD CONSTRAINT KREN_PRODCR_TP1 PRIMARY KEY (PRODCR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-14', '2.0.4-SNP', '3:109e464129e2be35403d6af1d043b0da', 14);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-15::Jeff Caddel (generated)::(Checksum: 3:ec556b387f8c88a51ac3cdbc122fe7fd)
ALTER TABLE KREN_RECIP_DELIV_T ADD CONSTRAINT KREN_RECIP_DELIV_TP1 PRIMARY KEY (RECIP_DELIV_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-15', '2.0.4-SNP', '3:ec556b387f8c88a51ac3cdbc122fe7fd', 15);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-16::Jeff Caddel (generated)::(Checksum: 3:3d2da99251f7503ea508d2bfaac4d1a4)
ALTER TABLE KREN_RECIP_LIST_T ADD CONSTRAINT KREN_RECIP_LIST_TP1 PRIMARY KEY (RECIP_LIST_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-16', '2.0.4-SNP', '3:3d2da99251f7503ea508d2bfaac4d1a4', 16);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-17::Jeff Caddel (generated)::(Checksum: 3:3dec20c2f9839da4073320d59b76bcd9)
ALTER TABLE KREN_RECIP_PREFS_T ADD CONSTRAINT KREN_RECIP_PREFS_TP1 PRIMARY KEY (RECIP_PREFS_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-17', '2.0.4-SNP', '3:3dec20c2f9839da4073320d59b76bcd9', 17);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-18::Jeff Caddel (generated)::(Checksum: 3:92b5ba2847bfb29c6ddb44bc280e2b5a)
ALTER TABLE KREN_RECIP_T ADD CONSTRAINT KREN_RECIP_TP1 PRIMARY KEY (RECIP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-18', '2.0.4-SNP', '3:92b5ba2847bfb29c6ddb44bc280e2b5a', 18);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-19::Jeff Caddel (generated)::(Checksum: 3:30b4f04aa4f0a871e58cdbc62ad073b2)
ALTER TABLE KREN_RVWER_T ADD CONSTRAINT KREN_RVWER_TP1 PRIMARY KEY (RVWER_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-19', '2.0.4-SNP', '3:30b4f04aa4f0a871e58cdbc62ad073b2', 19);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-20::Jeff Caddel (generated)::(Checksum: 3:88040bb3a34ff3b9d672452584798139)
ALTER TABLE KREN_SNDR_T ADD CONSTRAINT KREN_SNDR_TP1 PRIMARY KEY (SNDR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-20', '2.0.4-SNP', '3:88040bb3a34ff3b9d672452584798139', 20);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-21::Jeff Caddel (generated)::(Checksum: 3:a14b3075cfcef5219fca39f63075d55f)
ALTER TABLE KREW_ACTN_ITM_T ADD CONSTRAINT KREW_ACTN_ITM_TP1 PRIMARY KEY (ACTN_ITM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-21', '2.0.4-SNP', '3:a14b3075cfcef5219fca39f63075d55f', 21);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-22::Jeff Caddel (generated)::(Checksum: 3:8248b43c747e17483610912c83ed192d)
ALTER TABLE KREW_ACTN_RQST_T ADD CONSTRAINT KREW_ACTN_RQST_TP1 PRIMARY KEY (ACTN_RQST_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-22', '2.0.4-SNP', '3:8248b43c747e17483610912c83ed192d', 22);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-23::Jeff Caddel (generated)::(Checksum: 3:56ecee5aeb3342dd9fe2215ea4de9d09)
ALTER TABLE KREW_ACTN_TKN_T ADD CONSTRAINT KREW_ACTN_TKN_TP1 PRIMARY KEY (ACTN_TKN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-23', '2.0.4-SNP', '3:56ecee5aeb3342dd9fe2215ea4de9d09', 23);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-24::Jeff Caddel (generated)::(Checksum: 3:32269ef6856d2f286e807daf390cb54c)
ALTER TABLE KREW_APP_DOC_STAT_TRAN_T ADD CONSTRAINT KREW_APP_DOC_STAT_TRAN_TP1 PRIMARY KEY (APP_DOC_STAT_TRAN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-24', '2.0.4-SNP', '3:32269ef6856d2f286e807daf390cb54c', 24);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-25::Jeff Caddel (generated)::(Checksum: 3:6d1ab08680a156d44058fdcb8a7fd173)
ALTER TABLE KREW_ATT_T ADD CONSTRAINT KREW_ATT_TP1 PRIMARY KEY (ATTACHMENT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-25', '2.0.4-SNP', '3:6d1ab08680a156d44058fdcb8a7fd173', 25);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-26::Jeff Caddel (generated)::(Checksum: 3:10a0efc2590d5a9c02d37d636aca2064)
ALTER TABLE KREW_DLGN_RSP_T ADD CONSTRAINT KREW_DLGN_RSP_TP1 PRIMARY KEY (DLGN_RULE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-26', '2.0.4-SNP', '3:10a0efc2590d5a9c02d37d636aca2064', 26);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-27::Jeff Caddel (generated)::(Checksum: 3:0e7a631933c810527a29eea0c2bba734)
ALTER TABLE KREW_DOC_HDR_CNTNT_T ADD CONSTRAINT KREW_DOC_HDR_CNTNT_TP1 PRIMARY KEY (DOC_HDR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-27', '2.0.4-SNP', '3:0e7a631933c810527a29eea0c2bba734', 27);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-28::Jeff Caddel (generated)::(Checksum: 3:584cb208633c135cfb9746c0d2ad270c)
ALTER TABLE KREW_DOC_HDR_EXT_DT_T ADD CONSTRAINT KREW_DOC_HDR_EXT_DT_TP1 PRIMARY KEY (DOC_HDR_EXT_DT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-28', '2.0.4-SNP', '3:584cb208633c135cfb9746c0d2ad270c', 28);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-29::Jeff Caddel (generated)::(Checksum: 3:4a71078fdb58138e2df4cbaba5993014)
ALTER TABLE KREW_DOC_HDR_EXT_FLT_T ADD CONSTRAINT KREW_DOC_HDR_EXT_FLT_TP1 PRIMARY KEY (DOC_HDR_EXT_FLT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-29', '2.0.4-SNP', '3:4a71078fdb58138e2df4cbaba5993014', 29);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-30::Jeff Caddel (generated)::(Checksum: 3:8e7bd2b1661bb8a90e4aa65dff266cb5)
ALTER TABLE KREW_DOC_HDR_EXT_LONG_T ADD CONSTRAINT KREW_DOC_HDR_EXT_LONG_TP1 PRIMARY KEY (DOC_HDR_EXT_LONG_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-30', '2.0.4-SNP', '3:8e7bd2b1661bb8a90e4aa65dff266cb5', 30);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-31::Jeff Caddel (generated)::(Checksum: 3:3034de0d456803e754cf561f5cc51da4)
ALTER TABLE KREW_DOC_HDR_EXT_T ADD CONSTRAINT KREW_DOC_HDR_EXT_TP1 PRIMARY KEY (DOC_HDR_EXT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-31', '2.0.4-SNP', '3:3034de0d456803e754cf561f5cc51da4', 31);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-32::Jeff Caddel (generated)::(Checksum: 3:0e4a47d5c3e925df3d398a2a7d32dfc8)
ALTER TABLE KREW_DOC_HDR_T ADD CONSTRAINT KREW_DOC_HDR_TP1 PRIMARY KEY (DOC_HDR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-32', '2.0.4-SNP', '3:0e4a47d5c3e925df3d398a2a7d32dfc8', 32);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-33::Jeff Caddel (generated)::(Checksum: 3:d2995f8399fe13eb39332d34afbcbc0a)
ALTER TABLE KREW_DOC_LNK_T ADD CONSTRAINT KREW_DOC_LNK_TP1 PRIMARY KEY (DOC_LNK_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-33', '2.0.4-SNP', '3:d2995f8399fe13eb39332d34afbcbc0a', 33);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-34::Jeff Caddel (generated)::(Checksum: 3:92368bb65fbc3995bf5be302714f7559)
ALTER TABLE KREW_DOC_NTE_T ADD CONSTRAINT KREW_DOC_NTE_TP1 PRIMARY KEY (DOC_NTE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-34', '2.0.4-SNP', '3:92368bb65fbc3995bf5be302714f7559', 34);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-35::Jeff Caddel (generated)::(Checksum: 3:c955f33d3688935eaedb6134298da30a)
ALTER TABLE KREW_DOC_TYP_APP_DOC_STAT_T ADD CONSTRAINT KREW_DOC_TYP_APP_DOC_STAT_TP1 PRIMARY KEY (DOC_TYP_ID, DOC_STAT_NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-35', '2.0.4-SNP', '3:c955f33d3688935eaedb6134298da30a', 35);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-36::Jeff Caddel (generated)::(Checksum: 3:ab406bf3d3be58b173d87f992a9fb41d)
ALTER TABLE KREW_DOC_TYP_ATTR_T ADD CONSTRAINT KREW_DOC_TYP_ATTR_TP1 PRIMARY KEY (DOC_TYP_ATTRIB_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-36', '2.0.4-SNP', '3:ab406bf3d3be58b173d87f992a9fb41d', 36);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-37::Jeff Caddel (generated)::(Checksum: 3:70173ac44dad51c189070b8eba65fcbb)
ALTER TABLE KREW_DOC_TYP_PLCY_RELN_T ADD CONSTRAINT KREW_DOC_TYP_PLCY_RELN_TP1 PRIMARY KEY (DOC_TYP_ID, DOC_PLCY_NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-37', '2.0.4-SNP', '3:70173ac44dad51c189070b8eba65fcbb', 37);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-38::Jeff Caddel (generated)::(Checksum: 3:c0bcf5f2d1ddc3cdc5babeea8dae0123)
ALTER TABLE KREW_DOC_TYP_PROC_T ADD CONSTRAINT KREW_DOC_TYP_PROC_TP1 PRIMARY KEY (DOC_TYP_PROC_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-38', '2.0.4-SNP', '3:c0bcf5f2d1ddc3cdc5babeea8dae0123', 38);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-39::Jeff Caddel (generated)::(Checksum: 3:2b083301ddb0613142204cf30d6e3252)
ALTER TABLE KREW_DOC_TYP_T ADD CONSTRAINT KREW_DOC_TYP_TP1 PRIMARY KEY (DOC_TYP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-39', '2.0.4-SNP', '3:2b083301ddb0613142204cf30d6e3252', 39);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-40::Jeff Caddel (generated)::(Checksum: 3:6e2dce3d5abbebfdb77b147589795434)
ALTER TABLE KREW_EDL_ASSCTN_T ADD CONSTRAINT KREW_EDL_ASSCTN_TP1 PRIMARY KEY (EDOCLT_ASSOC_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-40', '2.0.4-SNP', '3:6e2dce3d5abbebfdb77b147589795434', 40);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-41::Jeff Caddel (generated)::(Checksum: 3:4ae6008ead06f07ea76eecc316554cf3)
ALTER TABLE KREW_EDL_DEF_T ADD CONSTRAINT KREW_EDL_DEF_TP1 PRIMARY KEY (EDOCLT_DEF_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-41', '2.0.4-SNP', '3:4ae6008ead06f07ea76eecc316554cf3', 41);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-42::Jeff Caddel (generated)::(Checksum: 3:ee0b70c1b4664974bbff7054dcd10d23)
ALTER TABLE KREW_EDL_DMP_T ADD CONSTRAINT KREW_EDL_DMP_TP1 PRIMARY KEY (DOC_HDR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-42', '2.0.4-SNP', '3:ee0b70c1b4664974bbff7054dcd10d23', 42);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-43::Jeff Caddel (generated)::(Checksum: 3:6b5fa9d4f5d95a41b6743f68cb8f5cce)
ALTER TABLE KREW_EDL_FLD_DMP_T ADD CONSTRAINT KREW_EDL_FLD_DMP_TP1 PRIMARY KEY (EDL_FIELD_DMP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-43', '2.0.4-SNP', '3:6b5fa9d4f5d95a41b6743f68cb8f5cce', 43);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-44::Jeff Caddel (generated)::(Checksum: 3:65513490851f7e994e2202c6a4ee514d)
ALTER TABLE KREW_HLP_T ADD CONSTRAINT KREW_HLP_TP1 PRIMARY KEY (HLP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-44', '2.0.4-SNP', '3:65513490851f7e994e2202c6a4ee514d', 44);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-45::Jeff Caddel (generated)::(Checksum: 3:0e4d67e0266605392e44cd4c37494dbb)
ALTER TABLE KREW_INIT_RTE_NODE_INSTN_T ADD CONSTRAINT KREW_INIT_RTE_NODE_INSTN_TP1 PRIMARY KEY (DOC_HDR_ID, RTE_NODE_INSTN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-45', '2.0.4-SNP', '3:0e4d67e0266605392e44cd4c37494dbb', 45);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-46::Jeff Caddel (generated)::(Checksum: 3:0f4c60578a0e2cdd6691e904183b9811)
ALTER TABLE KREW_OUT_BOX_ITM_T ADD CONSTRAINT KREW_OUT_BOX_ITM_TP1 PRIMARY KEY (ACTN_ITM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-46', '2.0.4-SNP', '3:0f4c60578a0e2cdd6691e904183b9811', 46);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-47::Jeff Caddel (generated)::(Checksum: 3:f833bf44969112e017ede0756b35ebda)
ALTER TABLE KREW_RIA_DOCTYPE_MAP_T ADD CONSTRAINT KREW_RIA_DOCTYPE_MAP_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-47', '2.0.4-SNP', '3:f833bf44969112e017ede0756b35ebda', 47);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-48::Jeff Caddel (generated)::(Checksum: 3:c79872c00ab7716a982a677995d5c039)
ALTER TABLE KREW_RIA_DOC_T ADD CONSTRAINT KREW_RIA_DOC_TP1 PRIMARY KEY (RIA_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-48', '2.0.4-SNP', '3:c79872c00ab7716a982a677995d5c039', 48);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-49::Jeff Caddel (generated)::(Checksum: 3:55393ce956d80eaf1fd86c35668f0b30)
ALTER TABLE KREW_RMV_RPLC_DOC_T ADD CONSTRAINT KREW_RMV_RPLC_DOC_TP1 PRIMARY KEY (DOC_HDR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-49', '2.0.4-SNP', '3:55393ce956d80eaf1fd86c35668f0b30', 49);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-50::Jeff Caddel (generated)::(Checksum: 3:3e7aca6ba6010b084eca66ee5cdb1c2d)
ALTER TABLE KREW_RMV_RPLC_GRP_T ADD CONSTRAINT KREW_RMV_RPLC_GRP_TP1 PRIMARY KEY (DOC_HDR_ID, GRP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-50', '2.0.4-SNP', '3:3e7aca6ba6010b084eca66ee5cdb1c2d', 50);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-51::Jeff Caddel (generated)::(Checksum: 3:0c841c3994b695ccb7ad4f4368a8a555)
ALTER TABLE KREW_RMV_RPLC_RULE_T ADD CONSTRAINT KREW_RMV_RPLC_RULE_TP1 PRIMARY KEY (DOC_HDR_ID, RULE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-51', '2.0.4-SNP', '3:0c841c3994b695ccb7ad4f4368a8a555', 51);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-52::Jeff Caddel (generated)::(Checksum: 3:712c6f628d9eb9444cb58781ad87e51a)
ALTER TABLE KREW_RTE_BRCH_PROTO_T ADD CONSTRAINT KREW_RTE_BRCH_PROTO_TP1 PRIMARY KEY (RTE_BRCH_PROTO_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-52', '2.0.4-SNP', '3:712c6f628d9eb9444cb58781ad87e51a', 52);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-53::Jeff Caddel (generated)::(Checksum: 3:2c7ff32a484edcd9ccf8f96b46df5821)
ALTER TABLE KREW_RTE_BRCH_ST_T ADD CONSTRAINT KREW_RTE_BRCH_ST_TP1 PRIMARY KEY (RTE_BRCH_ST_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-53', '2.0.4-SNP', '3:2c7ff32a484edcd9ccf8f96b46df5821', 53);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-54::Jeff Caddel (generated)::(Checksum: 3:17bcb1a2bd0d8a6123ba516edf4503f5)
ALTER TABLE KREW_RTE_BRCH_T ADD CONSTRAINT KREW_RTE_BRCH_TP1 PRIMARY KEY (RTE_BRCH_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-54', '2.0.4-SNP', '3:17bcb1a2bd0d8a6123ba516edf4503f5', 54);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-55::Jeff Caddel (generated)::(Checksum: 3:d2ad0d8108defc9e8a1370a2ceda0e6b)
ALTER TABLE KREW_RTE_NODE_CFG_PARM_T ADD CONSTRAINT KREW_RTE_NODE_CFG_PARM_TP1 PRIMARY KEY (RTE_NODE_CFG_PARM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-55', '2.0.4-SNP', '3:d2ad0d8108defc9e8a1370a2ceda0e6b', 55);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-56::Jeff Caddel (generated)::(Checksum: 3:8cbaac6292cc6b908dc7134203ff4760)
ALTER TABLE KREW_RTE_NODE_INSTN_LNK_T ADD CONSTRAINT KREW_RTE_NODE_INSTN_LNK_TP1 PRIMARY KEY (FROM_RTE_NODE_INSTN_ID, TO_RTE_NODE_INSTN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-56', '2.0.4-SNP', '3:8cbaac6292cc6b908dc7134203ff4760', 56);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-57::Jeff Caddel (generated)::(Checksum: 3:e351e63d5cb3b2585ee085291803c1b8)
ALTER TABLE KREW_RTE_NODE_INSTN_ST_T ADD CONSTRAINT KREW_RTE_NODE_INSTN_ST_TP1 PRIMARY KEY (RTE_NODE_INSTN_ST_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-57', '2.0.4-SNP', '3:e351e63d5cb3b2585ee085291803c1b8', 57);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-58::Jeff Caddel (generated)::(Checksum: 3:7875b5b27e9ba212e150d3e6ae489fec)
ALTER TABLE KREW_RTE_NODE_INSTN_T ADD CONSTRAINT KREW_RTE_NODE_INSTN_TP1 PRIMARY KEY (RTE_NODE_INSTN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-58', '2.0.4-SNP', '3:7875b5b27e9ba212e150d3e6ae489fec', 58);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-59::Jeff Caddel (generated)::(Checksum: 3:2004265d6b12a8d4b990138b0ccca4a4)
ALTER TABLE KREW_RTE_NODE_LNK_T ADD CONSTRAINT KREW_RTE_NODE_LNK_TP1 PRIMARY KEY (FROM_RTE_NODE_ID, TO_RTE_NODE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-59', '2.0.4-SNP', '3:2004265d6b12a8d4b990138b0ccca4a4', 59);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-60::Jeff Caddel (generated)::(Checksum: 3:9a61b3c0fd771476c1536640f787b8b7)
ALTER TABLE KREW_RTE_NODE_T ADD CONSTRAINT KREW_RTE_NODE_TP1 PRIMARY KEY (RTE_NODE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-60', '2.0.4-SNP', '3:9a61b3c0fd771476c1536640f787b8b7', 60);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-61::Jeff Caddel (generated)::(Checksum: 3:aabbb2fe4df838dd5971218ef7cfd80b)
ALTER TABLE KREW_RULE_ATTR_T ADD CONSTRAINT KREW_RULE_ATTR_TP1 PRIMARY KEY (RULE_ATTR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-61', '2.0.4-SNP', '3:aabbb2fe4df838dd5971218ef7cfd80b', 61);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-62::Jeff Caddel (generated)::(Checksum: 3:65bdecded429060699dac15316125bbe)
ALTER TABLE KREW_RULE_EXPR_T ADD CONSTRAINT KREW_RULE_EXPR_TP1 PRIMARY KEY (RULE_EXPR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-62', '2.0.4-SNP', '3:65bdecded429060699dac15316125bbe', 62);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-63::Jeff Caddel (generated)::(Checksum: 3:d371a0f2adbf96d6d0e308815019810a)
ALTER TABLE KREW_RULE_EXT_T ADD CONSTRAINT KREW_RULE_EXT_TP1 PRIMARY KEY (RULE_EXT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-63', '2.0.4-SNP', '3:d371a0f2adbf96d6d0e308815019810a', 63);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-64::Jeff Caddel (generated)::(Checksum: 3:2ec5611de116ce08fd8433c8c0084b48)
ALTER TABLE KREW_RULE_EXT_VAL_T ADD CONSTRAINT KREW_RULE_EXT_VAL_TP1 PRIMARY KEY (RULE_EXT_VAL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-64', '2.0.4-SNP', '3:2ec5611de116ce08fd8433c8c0084b48', 64);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-65::Jeff Caddel (generated)::(Checksum: 3:a924d0119ebe84c53d6f1b2dac75ecb8)
ALTER TABLE KREW_RULE_RSP_T ADD CONSTRAINT KREW_RULE_RSP_TP1 PRIMARY KEY (RULE_RSP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-65', '2.0.4-SNP', '3:a924d0119ebe84c53d6f1b2dac75ecb8', 65);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-66::Jeff Caddel (generated)::(Checksum: 3:5b11ddf7ffbafebc1e4171d945b7fd5b)
ALTER TABLE KREW_RULE_T ADD CONSTRAINT KREW_RULE_TP1 PRIMARY KEY (RULE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-66', '2.0.4-SNP', '3:5b11ddf7ffbafebc1e4171d945b7fd5b', 66);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-67::Jeff Caddel (generated)::(Checksum: 3:6a59e428e84a2840d8e6921ebada7abf)
ALTER TABLE KREW_RULE_TMPL_ATTR_T ADD CONSTRAINT KREW_RULE_TMPL_ATTR_TP1 PRIMARY KEY (RULE_TMPL_ATTR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-67', '2.0.4-SNP', '3:6a59e428e84a2840d8e6921ebada7abf', 67);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-68::Jeff Caddel (generated)::(Checksum: 3:ed2f2a40c2182ef31b02404c0024d523)
ALTER TABLE KREW_RULE_TMPL_OPTN_T ADD CONSTRAINT KREW_RULE_TMPL_OPTN_TP1 PRIMARY KEY (RULE_TMPL_OPTN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-68', '2.0.4-SNP', '3:ed2f2a40c2182ef31b02404c0024d523', 68);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-69::Jeff Caddel (generated)::(Checksum: 3:e9b603ce7ac945d09083de565293f332)
ALTER TABLE KREW_RULE_TMPL_T ADD CONSTRAINT KREW_RULE_TMPL_TP1 PRIMARY KEY (RULE_TMPL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-69', '2.0.4-SNP', '3:e9b603ce7ac945d09083de565293f332', 69);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-70::Jeff Caddel (generated)::(Checksum: 3:5009422c0679df265ede4e5a8fb7da6c)
ALTER TABLE KREW_STYLE_T ADD CONSTRAINT KREW_STYLE_TP1 PRIMARY KEY (STYLE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-70', '2.0.4-SNP', '3:5009422c0679df265ede4e5a8fb7da6c', 70);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-71::Jeff Caddel (generated)::(Checksum: 3:746c634d9112949954c0901527458383)
ALTER TABLE KREW_USR_OPTN_T ADD CONSTRAINT KREW_USR_OPTN_TP1 PRIMARY KEY (PRNCPL_ID, PRSN_OPTN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-71', '2.0.4-SNP', '3:746c634d9112949954c0901527458383', 71);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-72::Jeff Caddel (generated)::(Checksum: 3:2e01486c5881852638e72abb0aa85c00)
ALTER TABLE KRIM_ADDR_TYP_T ADD CONSTRAINT KRIM_ADDR_TYP_TP1 PRIMARY KEY (ADDR_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-72', '2.0.4-SNP', '3:2e01486c5881852638e72abb0aa85c00', 72);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-73::Jeff Caddel (generated)::(Checksum: 3:c0cdc4feb496225626d22a9685c033a3)
ALTER TABLE KRIM_AFLTN_TYP_T ADD CONSTRAINT KRIM_AFLTN_TYP_TP1 PRIMARY KEY (AFLTN_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-73', '2.0.4-SNP', '3:c0cdc4feb496225626d22a9685c033a3', 73);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-74::Jeff Caddel (generated)::(Checksum: 3:5b213c2b5fe29525110ecc904a810da3)
ALTER TABLE KRIM_ATTR_DEFN_T ADD CONSTRAINT KRIM_ATTR_DEFN_TP1 PRIMARY KEY (KIM_ATTR_DEFN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-74', '2.0.4-SNP', '3:5b213c2b5fe29525110ecc904a810da3', 74);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-75::Jeff Caddel (generated)::(Checksum: 3:d4ca9b3772a687963bd29745d53d2662)
ALTER TABLE KRIM_CTZNSHP_STAT_T ADD CONSTRAINT KRIM_CTZNSHP_STAT_TP1 PRIMARY KEY (CTZNSHP_STAT_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-75', '2.0.4-SNP', '3:d4ca9b3772a687963bd29745d53d2662', 75);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-76::Jeff Caddel (generated)::(Checksum: 3:dc1dddd401b685cb5159093d7f02b8c4)
ALTER TABLE KRIM_DLGN_MBR_ATTR_DATA_T ADD CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TP1 PRIMARY KEY (ATTR_DATA_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-76', '2.0.4-SNP', '3:dc1dddd401b685cb5159093d7f02b8c4', 76);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-77::Jeff Caddel (generated)::(Checksum: 3:7cb2ba3eee835e5f2292e100f3e98f22)
ALTER TABLE KRIM_DLGN_MBR_T ADD CONSTRAINT KRIM_DLGN_MBR_TP1 PRIMARY KEY (DLGN_MBR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-77', '2.0.4-SNP', '3:7cb2ba3eee835e5f2292e100f3e98f22', 77);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-78::Jeff Caddel (generated)::(Checksum: 3:123cc9a9f764ae5c06a7fb8ce1d24193)
ALTER TABLE KRIM_DLGN_T ADD CONSTRAINT KRIM_DLGN_TP1 PRIMARY KEY (DLGN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-78', '2.0.4-SNP', '3:123cc9a9f764ae5c06a7fb8ce1d24193', 78);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-79::Jeff Caddel (generated)::(Checksum: 3:5ff4dcde0d95bfd6f4ab43f82675d4c7)
ALTER TABLE KRIM_EMAIL_TYP_T ADD CONSTRAINT KRIM_EMAIL_TYP_TP1 PRIMARY KEY (EMAIL_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-79', '2.0.4-SNP', '3:5ff4dcde0d95bfd6f4ab43f82675d4c7', 79);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-80::Jeff Caddel (generated)::(Checksum: 3:f1b653632af4fc8b5c94421221f87e04)
ALTER TABLE KRIM_EMP_STAT_T ADD CONSTRAINT KRIM_EMP_STAT_TP1 PRIMARY KEY (EMP_STAT_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-80', '2.0.4-SNP', '3:f1b653632af4fc8b5c94421221f87e04', 80);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-81::Jeff Caddel (generated)::(Checksum: 3:177b261c7421295b2cd738ce2f9ade90)
ALTER TABLE KRIM_EMP_TYP_T ADD CONSTRAINT KRIM_EMP_TYP_TP1 PRIMARY KEY (EMP_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-81', '2.0.4-SNP', '3:177b261c7421295b2cd738ce2f9ade90', 81);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-82::Jeff Caddel (generated)::(Checksum: 3:e4892f13800b6c5bcc96dfec470bfd8d)
ALTER TABLE KRIM_ENTITY_ADDR_T ADD CONSTRAINT KRIM_ENTITY_ADDR_TP1 PRIMARY KEY (ENTITY_ADDR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-82', '2.0.4-SNP', '3:e4892f13800b6c5bcc96dfec470bfd8d', 82);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-83::Jeff Caddel (generated)::(Checksum: 3:6c1354251aac5b74ede708b875f9a3e4)
ALTER TABLE KRIM_ENTITY_AFLTN_T ADD CONSTRAINT KRIM_ENTITY_AFLTN_TP1 PRIMARY KEY (ENTITY_AFLTN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-83', '2.0.4-SNP', '3:6c1354251aac5b74ede708b875f9a3e4', 83);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-84::Jeff Caddel (generated)::(Checksum: 3:ba0e94ad85a70123405271e9c12e7ba9)
ALTER TABLE KRIM_ENTITY_BIO_T ADD CONSTRAINT KRIM_ENTITY_BIO_TP1 PRIMARY KEY (ENTITY_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-84', '2.0.4-SNP', '3:ba0e94ad85a70123405271e9c12e7ba9', 84);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-85::Jeff Caddel (generated)::(Checksum: 3:c936f1bb5f4e661eee09cde644cee05c)
ALTER TABLE KRIM_ENTITY_CACHE_T ADD CONSTRAINT KRIM_ENTITY_CACHE_TP1 PRIMARY KEY (ENTITY_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-85', '2.0.4-SNP', '3:c936f1bb5f4e661eee09cde644cee05c', 85);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-86::Jeff Caddel (generated)::(Checksum: 3:e2deaeb131bffaeaff577c552f1a3973)
ALTER TABLE KRIM_ENTITY_CTZNSHP_T ADD CONSTRAINT KRIM_ENTITY_CTZNSHP_TP1 PRIMARY KEY (ENTITY_CTZNSHP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-86', '2.0.4-SNP', '3:e2deaeb131bffaeaff577c552f1a3973', 86);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-87::Jeff Caddel (generated)::(Checksum: 3:c3a430d1b3469afaaa3e795d48aca0c3)
ALTER TABLE KRIM_ENTITY_EMAIL_T ADD CONSTRAINT KRIM_ENTITY_EMAIL_TP1 PRIMARY KEY (ENTITY_EMAIL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-87', '2.0.4-SNP', '3:c3a430d1b3469afaaa3e795d48aca0c3', 87);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-88::Jeff Caddel (generated)::(Checksum: 3:f9c938c42aa0b308cd3abfa82efe9afc)
ALTER TABLE KRIM_ENTITY_EMP_INFO_T ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TP1 PRIMARY KEY (ENTITY_EMP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-88', '2.0.4-SNP', '3:f9c938c42aa0b308cd3abfa82efe9afc', 88);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-89::Jeff Caddel (generated)::(Checksum: 3:3b61285d996847fb66678270b3598f25)
ALTER TABLE KRIM_ENTITY_ENT_TYP_T ADD CONSTRAINT KRIM_ENTITY_ENT_TYP_TP1 PRIMARY KEY (ENT_TYP_CD, ENTITY_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-89', '2.0.4-SNP', '3:3b61285d996847fb66678270b3598f25', 89);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-90::Jeff Caddel (generated)::(Checksum: 3:ba0fc6ee8148f3d8c0253cb3084d415d)
ALTER TABLE KRIM_ENTITY_ETHNIC_T ADD CONSTRAINT KRIM_ENTITY_ETHNIC_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-90', '2.0.4-SNP', '3:ba0fc6ee8148f3d8c0253cb3084d415d', 90);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-91::Jeff Caddel (generated)::(Checksum: 3:671c564a1c32f3207cb682910b86e90b)
ALTER TABLE KRIM_ENTITY_EXT_ID_T ADD CONSTRAINT KRIM_ENTITY_EXT_ID_TP1 PRIMARY KEY (ENTITY_EXT_ID_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-91', '2.0.4-SNP', '3:671c564a1c32f3207cb682910b86e90b', 91);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-92::Jeff Caddel (generated)::(Checksum: 3:dcc7fa03d2922173e514cb8f9730728f)
ALTER TABLE KRIM_ENTITY_NM_T ADD CONSTRAINT KRIM_ENTITY_NM_TP1 PRIMARY KEY (ENTITY_NM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-92', '2.0.4-SNP', '3:dcc7fa03d2922173e514cb8f9730728f', 92);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-93::Jeff Caddel (generated)::(Checksum: 3:c0afeeea399db0dc8c0a966ee4a32d19)
ALTER TABLE KRIM_ENTITY_PHONE_T ADD CONSTRAINT KRIM_ENTITY_PHONE_TP1 PRIMARY KEY (ENTITY_PHONE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-93', '2.0.4-SNP', '3:c0afeeea399db0dc8c0a966ee4a32d19', 93);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-94::Jeff Caddel (generated)::(Checksum: 3:fc89c6f27ee35d45de2bff734a22f48c)
ALTER TABLE KRIM_ENTITY_PRIV_PREF_T ADD CONSTRAINT KRIM_ENTITY_PRIV_PREF_TP1 PRIMARY KEY (ENTITY_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-94', '2.0.4-SNP', '3:fc89c6f27ee35d45de2bff734a22f48c', 94);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-95::Jeff Caddel (generated)::(Checksum: 3:acc7e4a385f14de2702696f9e3166d39)
ALTER TABLE KRIM_ENTITY_RESIDENCY_T ADD CONSTRAINT KRIM_ENTITY_RESIDENCY_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-95', '2.0.4-SNP', '3:acc7e4a385f14de2702696f9e3166d39', 95);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-96::Jeff Caddel (generated)::(Checksum: 3:da6d6c4c47c6ebf4501fec02f4891bf3)
ALTER TABLE KRIM_ENTITY_T ADD CONSTRAINT KRIM_ENTITY_TP1 PRIMARY KEY (ENTITY_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-96', '2.0.4-SNP', '3:da6d6c4c47c6ebf4501fec02f4891bf3', 96);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-97::Jeff Caddel (generated)::(Checksum: 3:ccc9510116304ad0ce3d4872899e4cd9)
ALTER TABLE KRIM_ENTITY_VISA_T ADD CONSTRAINT KRIM_ENTITY_VISA_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-97', '2.0.4-SNP', '3:ccc9510116304ad0ce3d4872899e4cd9', 97);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-98::Jeff Caddel (generated)::(Checksum: 3:ef41a21eed0323fc49d1c4e8ca9d1bd4)
ALTER TABLE KRIM_ENT_NM_TYP_T ADD CONSTRAINT KRIM_ENT_NM_TYP_TP1 PRIMARY KEY (ENT_NM_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-98', '2.0.4-SNP', '3:ef41a21eed0323fc49d1c4e8ca9d1bd4', 98);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-99::Jeff Caddel (generated)::(Checksum: 3:ab38f11e166d3a57ab4916dc78fa92a0)
ALTER TABLE KRIM_ENT_TYP_T ADD CONSTRAINT KRIM_ENT_TYP_TP1 PRIMARY KEY (ENT_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-99', '2.0.4-SNP', '3:ab38f11e166d3a57ab4916dc78fa92a0', 99);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-100::Jeff Caddel (generated)::(Checksum: 3:49a57e3e2264904500a6aecba370f9d8)
ALTER TABLE KRIM_EXT_ID_TYP_T ADD CONSTRAINT KRIM_EXT_ID_TYP_TP1 PRIMARY KEY (EXT_ID_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-100', '2.0.4-SNP', '3:49a57e3e2264904500a6aecba370f9d8', 100);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-101::Jeff Caddel (generated)::(Checksum: 3:04b9250544008b82c6ebc7df18889929)
ALTER TABLE KRIM_GRP_ATTR_DATA_T ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TP1 PRIMARY KEY (ATTR_DATA_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-101', '2.0.4-SNP', '3:04b9250544008b82c6ebc7df18889929', 101);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-102::Jeff Caddel (generated)::(Checksum: 3:da7f0425e461bc15f4b6565c01b9166d)
ALTER TABLE KRIM_GRP_DOCUMENT_T ADD CONSTRAINT KRIM_GRP_DOCUMENT_TP1 PRIMARY KEY (FDOC_NBR) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-102', '2.0.4-SNP', '3:da7f0425e461bc15f4b6565c01b9166d', 102);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-103::Jeff Caddel (generated)::(Checksum: 3:37c1a93a0469cf6e6ec94d65c08dc25f)
ALTER TABLE KRIM_GRP_MBR_T ADD CONSTRAINT KRIM_GRP_MBR_TP1 PRIMARY KEY (GRP_MBR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-103', '2.0.4-SNP', '3:37c1a93a0469cf6e6ec94d65c08dc25f', 103);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-104::Jeff Caddel (generated)::(Checksum: 3:a219b2ec4ab2d046db51c28ea600a336)
ALTER TABLE KRIM_GRP_T ADD CONSTRAINT KRIM_GRP_TP1 PRIMARY KEY (GRP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-104', '2.0.4-SNP', '3:a219b2ec4ab2d046db51c28ea600a336', 104);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-105::Jeff Caddel (generated)::(Checksum: 3:724f31294953fecb1191ecbee071a881)
ALTER TABLE KRIM_PERM_ATTR_DATA_T ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TP1 PRIMARY KEY (ATTR_DATA_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-105', '2.0.4-SNP', '3:724f31294953fecb1191ecbee071a881', 105);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-106::Jeff Caddel (generated)::(Checksum: 3:b32f5638ae36227d4539bdb612736d19)
ALTER TABLE KRIM_PERM_T ADD CONSTRAINT KRIM_PERM_TP1 PRIMARY KEY (PERM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-106', '2.0.4-SNP', '3:b32f5638ae36227d4539bdb612736d19', 106);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-107::Jeff Caddel (generated)::(Checksum: 3:bfcf3274425d65a473b06310e7bf4bdb)
ALTER TABLE KRIM_PERM_TMPL_T ADD CONSTRAINT KRIM_PERM_TMPL_TP1 PRIMARY KEY (PERM_TMPL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-107', '2.0.4-SNP', '3:bfcf3274425d65a473b06310e7bf4bdb', 107);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-108::Jeff Caddel (generated)::(Checksum: 3:00a6ca72a9e7f812c91d14822a03afb4)
ALTER TABLE KRIM_PERSON_DOCUMENT_T ADD CONSTRAINT KRIM_PERSON_DOCUMENT_TP1 PRIMARY KEY (FDOC_NBR) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-108', '2.0.4-SNP', '3:00a6ca72a9e7f812c91d14822a03afb4', 108);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-109::Jeff Caddel (generated)::(Checksum: 3:e13330b22f4feabb63c68707c4608c95)
ALTER TABLE KRIM_PHONE_TYP_T ADD CONSTRAINT KRIM_PHONE_TYP_TP1 PRIMARY KEY (PHONE_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-109', '2.0.4-SNP', '3:e13330b22f4feabb63c68707c4608c95', 109);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-110::Jeff Caddel (generated)::(Checksum: 3:da2d09780d68b743b0977f2e6062f7ef)
ALTER TABLE KRIM_PND_ADDR_MT ADD CONSTRAINT KRIM_PND_ADDR_MTP1 PRIMARY KEY (FDOC_NBR, ENTITY_ADDR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-110', '2.0.4-SNP', '3:da2d09780d68b743b0977f2e6062f7ef', 110);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-111::Jeff Caddel (generated)::(Checksum: 3:c9c29978226cf704ef79d423597c53ab)
ALTER TABLE KRIM_PND_AFLTN_MT ADD CONSTRAINT KRIM_PND_AFLTN_MTP1 PRIMARY KEY (FDOC_NBR, ENTITY_AFLTN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-111', '2.0.4-SNP', '3:c9c29978226cf704ef79d423597c53ab', 111);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-112::Jeff Caddel (generated)::(Checksum: 3:21d8b1bd34f6c8ae6449026323e92f2f)
ALTER TABLE KRIM_PND_CTZNSHP_MT ADD CONSTRAINT KRIM_PND_CTZNSHP_MTP1 PRIMARY KEY (FDOC_NBR, ENTITY_CTZNSHP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-112', '2.0.4-SNP', '3:21d8b1bd34f6c8ae6449026323e92f2f', 112);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-113::Jeff Caddel (generated)::(Checksum: 3:2f832629827c105728195909e989f92a)
ALTER TABLE KRIM_PND_DLGN_MBR_ATTR_DATA_T ADD CONSTRAINT KRIM_PND_DLGN_MBR_ATTR_DATAP1 PRIMARY KEY (FDOC_NBR, ATTR_DATA_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-113', '2.0.4-SNP', '3:2f832629827c105728195909e989f92a', 113);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-114::Jeff Caddel (generated)::(Checksum: 3:b0ca5bdc2b4ef8bb02016f651c5f5c37)
ALTER TABLE KRIM_PND_DLGN_MBR_T ADD CONSTRAINT KRIM_PND_DLGN_MBR_TP1 PRIMARY KEY (FDOC_NBR, DLGN_MBR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-114', '2.0.4-SNP', '3:b0ca5bdc2b4ef8bb02016f651c5f5c37', 114);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-115::Jeff Caddel (generated)::(Checksum: 3:a314be04db30d0263fae49e44cf48ad8)
ALTER TABLE KRIM_PND_DLGN_T ADD CONSTRAINT KRIM_PND_DLGN_TP1 PRIMARY KEY (FDOC_NBR, DLGN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-115', '2.0.4-SNP', '3:a314be04db30d0263fae49e44cf48ad8', 115);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-116::Jeff Caddel (generated)::(Checksum: 3:50f8c3871bfddb900b7ce90e94017806)
ALTER TABLE KRIM_PND_EMAIL_MT ADD CONSTRAINT KRIM_PND_EMAIL_MTP1 PRIMARY KEY (FDOC_NBR, ENTITY_EMAIL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-116', '2.0.4-SNP', '3:50f8c3871bfddb900b7ce90e94017806', 116);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-117::Jeff Caddel (generated)::(Checksum: 3:0ca556e568a5a47e5532eca561ff50f0)
ALTER TABLE KRIM_PND_EMP_INFO_MT ADD CONSTRAINT KRIM_PND_EMP_INFO_MTP1 PRIMARY KEY (FDOC_NBR, ENTITY_EMP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-117', '2.0.4-SNP', '3:0ca556e568a5a47e5532eca561ff50f0', 117);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-118::Jeff Caddel (generated)::(Checksum: 3:168aab07ac4a468da319e7398dc6411e)
ALTER TABLE KRIM_PND_GRP_ATTR_DATA_T ADD CONSTRAINT KRIM_PND_GRP_ATTR_DATA_TP1 PRIMARY KEY (FDOC_NBR, ATTR_DATA_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-118', '2.0.4-SNP', '3:168aab07ac4a468da319e7398dc6411e', 118);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-119::Jeff Caddel (generated)::(Checksum: 3:32c49443149282850d72c387e10ccf6b)
ALTER TABLE KRIM_PND_GRP_MBR_T ADD CONSTRAINT KRIM_PND_GRP_MBR_TP1 PRIMARY KEY (FDOC_NBR, GRP_MBR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-119', '2.0.4-SNP', '3:32c49443149282850d72c387e10ccf6b', 119);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-120::Jeff Caddel (generated)::(Checksum: 3:8b8be5754d185c6fa850081cea20d21f)
ALTER TABLE KRIM_PND_GRP_PRNCPL_MT ADD CONSTRAINT KRIM_PND_GRP_PRNCPL_MTP1 PRIMARY KEY (GRP_MBR_ID, FDOC_NBR) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-120', '2.0.4-SNP', '3:8b8be5754d185c6fa850081cea20d21f', 120);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-121::Jeff Caddel (generated)::(Checksum: 3:7daeafb7994e324dd6cbb0c1c4c81b84)
ALTER TABLE KRIM_PND_NM_MT ADD CONSTRAINT KRIM_PND_NM_MTP1 PRIMARY KEY (FDOC_NBR, ENTITY_NM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-121', '2.0.4-SNP', '3:7daeafb7994e324dd6cbb0c1c4c81b84', 121);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-122::Jeff Caddel (generated)::(Checksum: 3:14422f57e61779e76c3a2eab5fcd2254)
ALTER TABLE KRIM_PND_PHONE_MT ADD CONSTRAINT KRIM_PND_PHONE_MTP1 PRIMARY KEY (FDOC_NBR, ENTITY_PHONE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-122', '2.0.4-SNP', '3:14422f57e61779e76c3a2eab5fcd2254', 122);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-123::Jeff Caddel (generated)::(Checksum: 3:5cb75d4beedfc92486710f8cad9a53c7)
ALTER TABLE KRIM_PND_PRIV_PREF_MT ADD CONSTRAINT KRIM_PND_PRIV_PREF_MTP1 PRIMARY KEY (FDOC_NBR) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-123', '2.0.4-SNP', '3:5cb75d4beedfc92486710f8cad9a53c7', 123);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-124::Jeff Caddel (generated)::(Checksum: 3:34ba72c00dfb4d1d49335b3fe056087d)
ALTER TABLE KRIM_PND_ROLE_MBR_ATTR_DATA_MT ADD CONSTRAINT KRIM_PND_ROLE_MBR_ATTR_DATAP1 PRIMARY KEY (FDOC_NBR, ATTR_DATA_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-124', '2.0.4-SNP', '3:34ba72c00dfb4d1d49335b3fe056087d', 124);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-125::Jeff Caddel (generated)::(Checksum: 3:e7e874b7fa8c4959e82df5c419934439)
ALTER TABLE KRIM_PND_ROLE_MBR_MT ADD CONSTRAINT KRIM_PND_ROLE_MBR_MTP1 PRIMARY KEY (FDOC_NBR, ROLE_MBR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-125', '2.0.4-SNP', '3:e7e874b7fa8c4959e82df5c419934439', 125);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-126::Jeff Caddel (generated)::(Checksum: 3:76bb5afd49bc9997818491b6c48d4bf4)
ALTER TABLE KRIM_PND_ROLE_MT ADD CONSTRAINT KRIM_PND_ROLE_MTP1 PRIMARY KEY (FDOC_NBR, ROLE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-126', '2.0.4-SNP', '3:76bb5afd49bc9997818491b6c48d4bf4', 126);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-127::Jeff Caddel (generated)::(Checksum: 3:327bf598adc1cec3cc9f48c8d47b0187)
ALTER TABLE KRIM_PND_ROLE_PERM_T ADD CONSTRAINT KRIM_PND_ROLE_PERM_TP1 PRIMARY KEY (FDOC_NBR, ROLE_PERM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-127', '2.0.4-SNP', '3:327bf598adc1cec3cc9f48c8d47b0187', 127);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-128::Jeff Caddel (generated)::(Checksum: 3:26e8276905abb6cbb5bb677f1279abcc)
ALTER TABLE KRIM_PND_ROLE_RSP_ACTN_MT ADD CONSTRAINT KRIM_PND_ROLE_RSP_ACTN_MTP1 PRIMARY KEY (ROLE_RSP_ACTN_ID, FDOC_NBR) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-128', '2.0.4-SNP', '3:26e8276905abb6cbb5bb677f1279abcc', 128);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-129::Jeff Caddel (generated)::(Checksum: 3:5e73afe603f3118929c80de194bda898)
ALTER TABLE KRIM_PND_ROLE_RSP_T ADD CONSTRAINT KRIM_PND_ROLE_RSP_TP1 PRIMARY KEY (FDOC_NBR, ROLE_RSP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-129', '2.0.4-SNP', '3:5e73afe603f3118929c80de194bda898', 129);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-130::Jeff Caddel (generated)::(Checksum: 3:f97192fc31696fdda30285638c484052)
ALTER TABLE KRIM_PRNCPL_T ADD CONSTRAINT KRIM_PRNCPL_TP1 PRIMARY KEY (PRNCPL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-130', '2.0.4-SNP', '3:f97192fc31696fdda30285638c484052', 130);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-131::Jeff Caddel (generated)::(Checksum: 3:4f293fed91f090932bfd868ff5864d30)
ALTER TABLE KRIM_ROLE_DOCUMENT_T ADD CONSTRAINT KRIM_ROLE_DOCUMENT_TP1 PRIMARY KEY (FDOC_NBR) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-131', '2.0.4-SNP', '3:4f293fed91f090932bfd868ff5864d30', 131);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-132::Jeff Caddel (generated)::(Checksum: 3:ec592f2ecde953f0d27fb91ae589416b)
ALTER TABLE KRIM_ROLE_MBR_ATTR_DATA_T ADD CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TP1 PRIMARY KEY (ATTR_DATA_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-132', '2.0.4-SNP', '3:ec592f2ecde953f0d27fb91ae589416b', 132);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-133::Jeff Caddel (generated)::(Checksum: 3:73dc757a57dbbfce743e3bf9cc81c15e)
ALTER TABLE KRIM_ROLE_MBR_T ADD CONSTRAINT KRIM_ROLE_MBR_TP1 PRIMARY KEY (ROLE_MBR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-133', '2.0.4-SNP', '3:73dc757a57dbbfce743e3bf9cc81c15e', 133);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-134::Jeff Caddel (generated)::(Checksum: 3:774351f8029e413e5ce66d37025a639c)
ALTER TABLE KRIM_ROLE_PERM_T ADD CONSTRAINT KRIM_ROLE_PERM_TP1 PRIMARY KEY (ROLE_PERM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-134', '2.0.4-SNP', '3:774351f8029e413e5ce66d37025a639c', 134);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-135::Jeff Caddel (generated)::(Checksum: 3:fec2ae68a82502c84cf835093ac4dc22)
ALTER TABLE KRIM_ROLE_RSP_ACTN_T ADD CONSTRAINT KRIM_ROLE_RSP_ACTN_TP1 PRIMARY KEY (ROLE_RSP_ACTN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-135', '2.0.4-SNP', '3:fec2ae68a82502c84cf835093ac4dc22', 135);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-136::Jeff Caddel (generated)::(Checksum: 3:8c0746824835d267aa69198cd5f5ec90)
ALTER TABLE KRIM_ROLE_RSP_T ADD CONSTRAINT KRIM_ROLE_RSP_TP1 PRIMARY KEY (ROLE_RSP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-136', '2.0.4-SNP', '3:8c0746824835d267aa69198cd5f5ec90', 136);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-137::Jeff Caddel (generated)::(Checksum: 3:06475d1792d4b49990ba10531dfeb94c)
ALTER TABLE KRIM_ROLE_T ADD CONSTRAINT KRIM_ROLE_TP1 PRIMARY KEY (ROLE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-137', '2.0.4-SNP', '3:06475d1792d4b49990ba10531dfeb94c', 137);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-138::Jeff Caddel (generated)::(Checksum: 3:5b93e427e4588aedc9f83880c6ebc93e)
ALTER TABLE KRIM_RSP_ATTR_DATA_T ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TP1 PRIMARY KEY (ATTR_DATA_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-138', '2.0.4-SNP', '3:5b93e427e4588aedc9f83880c6ebc93e', 138);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-139::Jeff Caddel (generated)::(Checksum: 3:3c6111c908adc8ec168d4b975e6ea538)
ALTER TABLE KRIM_RSP_T ADD CONSTRAINT KRIM_RSP_TP1 PRIMARY KEY (RSP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-139', '2.0.4-SNP', '3:3c6111c908adc8ec168d4b975e6ea538', 139);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-140::Jeff Caddel (generated)::(Checksum: 3:a363210a827d1f82e0fabed30b83cf8f)
ALTER TABLE KRIM_RSP_TMPL_T ADD CONSTRAINT KRIM_RSP_TMPL_TP1 PRIMARY KEY (RSP_TMPL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-140', '2.0.4-SNP', '3:a363210a827d1f82e0fabed30b83cf8f', 140);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-141::Jeff Caddel (generated)::(Checksum: 3:933d479d3c05022d6f874ffee9687e52)
ALTER TABLE KRIM_TYP_ATTR_T ADD CONSTRAINT KRIM_TYP_ATTR_TP1 PRIMARY KEY (KIM_TYP_ATTR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-141', '2.0.4-SNP', '3:933d479d3c05022d6f874ffee9687e52', 141);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-142::Jeff Caddel (generated)::(Checksum: 3:fc2bfa7efaad9465b01fd6e2bcd6be0c)
ALTER TABLE KRIM_TYP_T ADD CONSTRAINT KRIM_TYP_TP1 PRIMARY KEY (KIM_TYP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-142', '2.0.4-SNP', '3:fc2bfa7efaad9465b01fd6e2bcd6be0c', 142);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-143::Jeff Caddel (generated)::(Checksum: 3:438f72cb9f85f80f86a15a404708643e)
ALTER TABLE KRLC_CMP_T ADD CONSTRAINT KRLC_CMP_TP1 PRIMARY KEY (CAMPUS_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-143', '2.0.4-SNP', '3:438f72cb9f85f80f86a15a404708643e', 143);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-144::Jeff Caddel (generated)::(Checksum: 3:4fb37171fd6c141b4c892f246749b51a)
ALTER TABLE KRLC_CMP_TYP_T ADD CONSTRAINT KRLC_CMP_TYP_TP1 PRIMARY KEY (CAMPUS_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-144', '2.0.4-SNP', '3:4fb37171fd6c141b4c892f246749b51a', 144);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-145::Jeff Caddel (generated)::(Checksum: 3:d296ae35d8c6ddc0a1dbfb843081a608)
ALTER TABLE KRLC_CNTRY_T ADD CONSTRAINT KRLC_CNTRY_TP1 PRIMARY KEY (POSTAL_CNTRY_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-145', '2.0.4-SNP', '3:d296ae35d8c6ddc0a1dbfb843081a608', 145);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-146::Jeff Caddel (generated)::(Checksum: 3:54703efd8633b8bf026aab39b90e7b06)
ALTER TABLE KRLC_CNTY_T ADD CONSTRAINT KRLC_CNTY_TP1 PRIMARY KEY (COUNTY_CD, STATE_CD, POSTAL_CNTRY_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-146', '2.0.4-SNP', '3:54703efd8633b8bf026aab39b90e7b06', 146);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-147::Jeff Caddel (generated)::(Checksum: 3:28de1bda001a361bd65bcbb5be3ceb5f)
ALTER TABLE KRLC_PSTL_CD_T ADD CONSTRAINT KRLC_PSTL_CD_TP1 PRIMARY KEY (POSTAL_CD, POSTAL_CNTRY_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-147', '2.0.4-SNP', '3:28de1bda001a361bd65bcbb5be3ceb5f', 147);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-148::Jeff Caddel (generated)::(Checksum: 3:1c6d85b726a342145402a88b58c8fde7)
ALTER TABLE KRLC_ST_T ADD CONSTRAINT KRLC_ST_TP1 PRIMARY KEY (POSTAL_STATE_CD, POSTAL_CNTRY_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-148', '2.0.4-SNP', '3:1c6d85b726a342145402a88b58c8fde7', 148);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-149::Jeff Caddel (generated)::(Checksum: 3:7786bd0e1c039ee28fa38218b8afd628)
ALTER TABLE KRMS_ACTN_ATTR_T ADD CONSTRAINT KRMS_ACTN_ATTR_TP1 PRIMARY KEY (ACTN_ATTR_DATA_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-149', '2.0.4-SNP', '3:7786bd0e1c039ee28fa38218b8afd628', 149);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-150::Jeff Caddel (generated)::(Checksum: 3:4c9d6e36f0620bb5f6eecdc9038781c3)
ALTER TABLE KRMS_ACTN_T ADD CONSTRAINT KRMS_ACTN_TP1 PRIMARY KEY (ACTN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-150', '2.0.4-SNP', '3:4c9d6e36f0620bb5f6eecdc9038781c3', 150);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-151::Jeff Caddel (generated)::(Checksum: 3:d733d3c9fdb5d396b1e1c94d0541e934)
ALTER TABLE KRMS_AGENDA_ATTR_T ADD CONSTRAINT KRMS_AGENDA_ATTR_TP1 PRIMARY KEY (AGENDA_ATTR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-151', '2.0.4-SNP', '3:d733d3c9fdb5d396b1e1c94d0541e934', 151);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-152::Jeff Caddel (generated)::(Checksum: 3:368206c93cfd545ba8d66104ee2a998e)
ALTER TABLE KRMS_AGENDA_ITM_T ADD CONSTRAINT KRMS_AGENDA_ITM_TP1 PRIMARY KEY (AGENDA_ITM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-152', '2.0.4-SNP', '3:368206c93cfd545ba8d66104ee2a998e', 152);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-153::Jeff Caddel (generated)::(Checksum: 3:154b98da5445c26fd3f1731fc3570e98)
ALTER TABLE KRMS_AGENDA_T ADD CONSTRAINT KRMS_AGENDA_TP1 PRIMARY KEY (AGENDA_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-153', '2.0.4-SNP', '3:154b98da5445c26fd3f1731fc3570e98', 153);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-154::Jeff Caddel (generated)::(Checksum: 3:72363a60f0cb890829ddcc07b3a41d0f)
ALTER TABLE KRMS_ATTR_DEFN_T ADD CONSTRAINT KRMS_ATTR_DEFN_TP1 PRIMARY KEY (ATTR_DEFN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-154', '2.0.4-SNP', '3:72363a60f0cb890829ddcc07b3a41d0f', 154);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-155::Jeff Caddel (generated)::(Checksum: 3:8ba335e18569ae8df6dd220ca595e9fa)
ALTER TABLE KRMS_CMPND_PROP_PROPS_T ADD CONSTRAINT KRMS_CMPND_PROP_PROPS_TP1 PRIMARY KEY (CMPND_PROP_ID, PROP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-155', '2.0.4-SNP', '3:8ba335e18569ae8df6dd220ca595e9fa', 155);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-156::Jeff Caddel (generated)::(Checksum: 3:27134ee11fc1c2d5762a50a85ecc064a)
ALTER TABLE KRMS_CNTXT_ATTR_T ADD CONSTRAINT KRMS_CNTXT_ATTR_TP1 PRIMARY KEY (CNTXT_ATTR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-156', '2.0.4-SNP', '3:27134ee11fc1c2d5762a50a85ecc064a', 156);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-157::Jeff Caddel (generated)::(Checksum: 3:33a73261079307210e3d4f9f036a9a89)
ALTER TABLE KRMS_CNTXT_T ADD CONSTRAINT KRMS_CNTXT_TP1 PRIMARY KEY (CNTXT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-157', '2.0.4-SNP', '3:33a73261079307210e3d4f9f036a9a89', 157);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-158::Jeff Caddel (generated)::(Checksum: 3:d0a31e994ea6f3435897c2fcdb894755)
ALTER TABLE KRMS_CNTXT_TERM_SPEC_PREREQ_T ADD CONSTRAINT KRMS_CNTXT_TERM_SPEC_PREREQP1 PRIMARY KEY (CNTXT_TERM_SPEC_PREREQ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-158', '2.0.4-SNP', '3:d0a31e994ea6f3435897c2fcdb894755', 158);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-159::Jeff Caddel (generated)::(Checksum: 3:dec448fd7976c42e87357d3112b293bf)
ALTER TABLE KRMS_CNTXT_VLD_ACTN_T ADD CONSTRAINT KRMS_CNTXT_VLD_ACTN_TP1 PRIMARY KEY (CNTXT_VLD_ACTN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-159', '2.0.4-SNP', '3:dec448fd7976c42e87357d3112b293bf', 159);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-160::Jeff Caddel (generated)::(Checksum: 3:5b98a1056575154b5535e0a806921fdd)
ALTER TABLE KRMS_CNTXT_VLD_EVENT_T ADD CONSTRAINT KRMS_CNTXT_VLD_EVENT_TP1 PRIMARY KEY (CNTXT_VLD_EVENT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-160', '2.0.4-SNP', '3:5b98a1056575154b5535e0a806921fdd', 160);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-161::Jeff Caddel (generated)::(Checksum: 3:1219bb03ae921a8384e02e35952ac706)
ALTER TABLE KRMS_CNTXT_VLD_FUNC_T ADD CONSTRAINT KRMS_CNTXT_VLD_FUNC_TP1 PRIMARY KEY (CNTXT_VLD_FUNC_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-161', '2.0.4-SNP', '3:1219bb03ae921a8384e02e35952ac706', 161);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-162::Jeff Caddel (generated)::(Checksum: 3:00d15db7670d356bdf135f0a405a6cca)
ALTER TABLE KRMS_CNTXT_VLD_RULE_T ADD CONSTRAINT KRMS_CNTXT_VLD_RULE_TP1 PRIMARY KEY (CNTXT_VLD_RULE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-162', '2.0.4-SNP', '3:00d15db7670d356bdf135f0a405a6cca', 162);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-163::Jeff Caddel (generated)::(Checksum: 3:8407197623805555b8b1e4d8859748e4)
ALTER TABLE KRMS_FUNC_PARM_T ADD CONSTRAINT KRMS_FUNC_PARM_TP1 PRIMARY KEY (FUNC_PARM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-163', '2.0.4-SNP', '3:8407197623805555b8b1e4d8859748e4', 163);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-164::Jeff Caddel (generated)::(Checksum: 3:a0b60e05fbae32c5f16462dcbb50dd53)
ALTER TABLE KRMS_FUNC_T ADD CONSTRAINT KRMS_FUNC_TP1 PRIMARY KEY (FUNC_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-164', '2.0.4-SNP', '3:a0b60e05fbae32c5f16462dcbb50dd53', 164);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-165::Jeff Caddel (generated)::(Checksum: 3:1d668873762ad12efe9961fa18c5ee69)
ALTER TABLE KRMS_PROP_PARM_T ADD CONSTRAINT KRMS_PROP_PARM_TP1 PRIMARY KEY (PROP_PARM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-165', '2.0.4-SNP', '3:1d668873762ad12efe9961fa18c5ee69', 165);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-166::Jeff Caddel (generated)::(Checksum: 3:e81137b6c208b59026090dc3380903c9)
ALTER TABLE KRMS_PROP_T ADD CONSTRAINT KRMS_PROP_TP1 PRIMARY KEY (PROP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-166', '2.0.4-SNP', '3:e81137b6c208b59026090dc3380903c9', 166);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-167::Jeff Caddel (generated)::(Checksum: 3:d010ca1724fcf7afc4bddfec6e34d087)
ALTER TABLE KRMS_RULE_ATTR_T ADD CONSTRAINT KRMS_RULE_ATTR_TP1 PRIMARY KEY (RULE_ATTR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-167', '2.0.4-SNP', '3:d010ca1724fcf7afc4bddfec6e34d087', 167);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-168::Jeff Caddel (generated)::(Checksum: 3:5392ad15ab45d44da5af655b04f13299)
ALTER TABLE KRMS_RULE_T ADD CONSTRAINT KRMS_RULE_TP1 PRIMARY KEY (RULE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-168', '2.0.4-SNP', '3:5392ad15ab45d44da5af655b04f13299', 168);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-169::Jeff Caddel (generated)::(Checksum: 3:015979dd6144b25e65559e4f3546f907)
ALTER TABLE KRMS_TERM_PARM_T ADD CONSTRAINT KRMS_TERM_PARM_TP1 PRIMARY KEY (TERM_PARM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-169', '2.0.4-SNP', '3:015979dd6144b25e65559e4f3546f907', 169);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-170::Jeff Caddel (generated)::(Checksum: 3:5acc49d551660f41bcc157f5c2433a51)
ALTER TABLE KRMS_TERM_RSLVR_ATTR_T ADD CONSTRAINT KRMS_TERM_RSLVR_ATTR_TP1 PRIMARY KEY (TERM_RSLVR_ATTR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-170', '2.0.4-SNP', '3:5acc49d551660f41bcc157f5c2433a51', 170);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-171::Jeff Caddel (generated)::(Checksum: 3:7736572e8215c8893ca72ddde76cd246)
ALTER TABLE KRMS_TERM_RSLVR_INPUT_SPEC_T ADD CONSTRAINT KRMS_TERM_RSLVR_INPUT_SPEC_P1 PRIMARY KEY (TERM_SPEC_ID, TERM_RSLVR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-171', '2.0.4-SNP', '3:7736572e8215c8893ca72ddde76cd246', 171);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-172::Jeff Caddel (generated)::(Checksum: 3:741e428fc0f74c7c38acf345a3554927)
ALTER TABLE KRMS_TERM_RSLVR_PARM_SPEC_T ADD CONSTRAINT KRMS_TERM_RSLVR_PARM_SPEC_TP1 PRIMARY KEY (TERM_RSLVR_PARM_SPEC_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-172', '2.0.4-SNP', '3:741e428fc0f74c7c38acf345a3554927', 172);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-173::Jeff Caddel (generated)::(Checksum: 3:ccf3c368f39ba9489b0514c7363364bb)
ALTER TABLE KRMS_TERM_RSLVR_T ADD CONSTRAINT KRMS_TERM_RSLVR_TP1 PRIMARY KEY (TERM_RSLVR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-173', '2.0.4-SNP', '3:ccf3c368f39ba9489b0514c7363364bb', 173);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-174::Jeff Caddel (generated)::(Checksum: 3:340d7ce9949b5450da31ea0be3fd635f)
ALTER TABLE KRMS_TERM_SPEC_T ADD CONSTRAINT KRMS_TERM_SPEC_TP1 PRIMARY KEY (TERM_SPEC_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-174', '2.0.4-SNP', '3:340d7ce9949b5450da31ea0be3fd635f', 174);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-175::Jeff Caddel (generated)::(Checksum: 3:74b33e5c67f73b294d88ca31d41761f4)
ALTER TABLE KRMS_TERM_T ADD CONSTRAINT KRMS_TERM_TP1 PRIMARY KEY (TERM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-175', '2.0.4-SNP', '3:74b33e5c67f73b294d88ca31d41761f4', 175);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-176::Jeff Caddel (generated)::(Checksum: 3:17c706614627a18fc85c25c1365620a5)
ALTER TABLE KRMS_TYP_ATTR_T ADD CONSTRAINT KRMS_TYP_ATTR_TP1 PRIMARY KEY (TYP_ATTR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-176', '2.0.4-SNP', '3:17c706614627a18fc85c25c1365620a5', 176);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-177::Jeff Caddel (generated)::(Checksum: 3:776c36b9322cdd6902b58b33d4834ab2)
ALTER TABLE KRMS_TYP_T ADD CONSTRAINT KRMS_TYP_TP1 PRIMARY KEY (TYP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-177', '2.0.4-SNP', '3:776c36b9322cdd6902b58b33d4834ab2', 177);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-178::Jeff Caddel (generated)::(Checksum: 3:f83552d5cdaacc8cfc32ce9fc8856452)
ALTER TABLE KRNS_ADHOC_RTE_ACTN_RECIP_T ADD CONSTRAINT KRNS_ADHOC_RTE_ACTN_RECIP_TP1 PRIMARY KEY (RECIP_TYP_CD, ACTN_RQST_CD, ACTN_RQST_RECIP_ID, DOC_HDR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-178', '2.0.4-SNP', '3:f83552d5cdaacc8cfc32ce9fc8856452', 178);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-179::Jeff Caddel (generated)::(Checksum: 3:f92b83394c52ca35192e162946c8470c)
ALTER TABLE KRNS_ATT_T ADD CONSTRAINT KRNS_ATT_TP1 PRIMARY KEY (NTE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-179', '2.0.4-SNP', '3:f92b83394c52ca35192e162946c8470c', 179);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-180::Jeff Caddel (generated)::(Checksum: 3:0232dbe464377e9d34fb0ca3a23b698c)
ALTER TABLE KRNS_DOC_HDR_T ADD CONSTRAINT KRNS_DOC_HDR_TP1 PRIMARY KEY (DOC_HDR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-180', '2.0.4-SNP', '3:0232dbe464377e9d34fb0ca3a23b698c', 180);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-181::Jeff Caddel (generated)::(Checksum: 3:748b0503c4df83808f5599b5e1f4b072)
ALTER TABLE KRNS_LOOKUP_RSLT_T ADD CONSTRAINT KRNS_LOOKUP_RSLT_TP1 PRIMARY KEY (LOOKUP_RSLT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-181', '2.0.4-SNP', '3:748b0503c4df83808f5599b5e1f4b072', 181);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-182::Jeff Caddel (generated)::(Checksum: 3:2be434c166c678ba3d510c58c478aabe)
ALTER TABLE KRNS_LOOKUP_SEL_T ADD CONSTRAINT KRNS_LOOKUP_SEL_TP1 PRIMARY KEY (LOOKUP_RSLT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-182', '2.0.4-SNP', '3:2be434c166c678ba3d510c58c478aabe', 182);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-183::Jeff Caddel (generated)::(Checksum: 3:dc991404fc54e460084e3965f6144a59)
ALTER TABLE KRNS_MAINT_DOC_ATT_T ADD CONSTRAINT KRNS_MAINT_DOC_ATT_TP1 PRIMARY KEY (DOC_HDR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-183', '2.0.4-SNP', '3:dc991404fc54e460084e3965f6144a59', 183);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-184::Jeff Caddel (generated)::(Checksum: 3:5dc0887b29033c1a3d6dda1c87113629)
ALTER TABLE KRNS_MAINT_DOC_T ADD CONSTRAINT KRNS_MAINT_DOC_TP1 PRIMARY KEY (DOC_HDR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-184', '2.0.4-SNP', '3:5dc0887b29033c1a3d6dda1c87113629', 184);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-185::Jeff Caddel (generated)::(Checksum: 3:53b600de2f81e91d52e052baaea15e4a)
ALTER TABLE KRNS_MAINT_LOCK_T ADD CONSTRAINT KRNS_MAINT_LOCK_TP1 PRIMARY KEY (MAINT_LOCK_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-185', '2.0.4-SNP', '3:53b600de2f81e91d52e052baaea15e4a', 185);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-186::Jeff Caddel (generated)::(Checksum: 3:2a40a80be6e6e2f3cd7ccf884f84c885)
ALTER TABLE KRNS_NTE_T ADD CONSTRAINT KRNS_NTE_TP1 PRIMARY KEY (NTE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-186', '2.0.4-SNP', '3:2a40a80be6e6e2f3cd7ccf884f84c885', 186);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-187::Jeff Caddel (generated)::(Checksum: 3:db57eb69f3d8df1f91f36db91c5e8978)
ALTER TABLE KRNS_NTE_TYP_T ADD CONSTRAINT KRNS_NTE_TYP_TP1 PRIMARY KEY (NTE_TYP_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-187', '2.0.4-SNP', '3:db57eb69f3d8df1f91f36db91c5e8978', 187);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-188::Jeff Caddel (generated)::(Checksum: 3:e3dacea7fe41646e0044acff52f49256)
ALTER TABLE KRNS_PESSIMISTIC_LOCK_T ADD CONSTRAINT KRNS_PESSIMISTIC_LOCK_TP1 PRIMARY KEY (PESSIMISTIC_LOCK_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-188', '2.0.4-SNP', '3:e3dacea7fe41646e0044acff52f49256', 188);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-189::Jeff Caddel (generated)::(Checksum: 3:f2d90169556755516c86f90130021879)
ALTER TABLE KRNS_SESN_DOC_T ADD CONSTRAINT KRNS_SESN_DOC_TP1 PRIMARY KEY (SESN_DOC_ID, DOC_HDR_ID, PRNCPL_ID, IP_ADDR) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-189', '2.0.4-SNP', '3:f2d90169556755516c86f90130021879', 189);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-190::Jeff Caddel (generated)::(Checksum: 3:725ce1dd6cefc4f69ebf1f2fda422c69)
ALTER TABLE KRSB_BAM_PARM_T ADD CONSTRAINT KRSB_BAM_PARM_TP1 PRIMARY KEY (BAM_PARM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-190', '2.0.4-SNP', '3:725ce1dd6cefc4f69ebf1f2fda422c69', 190);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-191::Jeff Caddel (generated)::(Checksum: 3:9c47590984de5f4d4e05d6ea1090dd6b)
ALTER TABLE KRSB_BAM_T ADD CONSTRAINT KRSB_BAM_TP1 PRIMARY KEY (BAM_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-191', '2.0.4-SNP', '3:9c47590984de5f4d4e05d6ea1090dd6b', 191);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-192::Jeff Caddel (generated)::(Checksum: 3:e651cc9e82ab11936eb6b3b49104f93a)
ALTER TABLE KRSB_MSG_PYLD_T ADD CONSTRAINT KRSB_MSG_PYLD_TP1 PRIMARY KEY (MSG_QUE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-192', '2.0.4-SNP', '3:e651cc9e82ab11936eb6b3b49104f93a', 192);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-193::Jeff Caddel (generated)::(Checksum: 3:f3c17442cf401d8fa199fd2b9c5a1e76)
ALTER TABLE KRSB_MSG_QUE_T ADD CONSTRAINT KRSB_MSG_QUE_TP1 PRIMARY KEY (MSG_QUE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-193', '2.0.4-SNP', '3:f3c17442cf401d8fa199fd2b9c5a1e76', 193);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-194::Jeff Caddel (generated)::(Checksum: 3:06ce3f0868aa0718931f0fb32b26e5ba)
ALTER TABLE KRSB_QRTZ_BLOB_TRIGGERS ADD CONSTRAINT KRSB_QRTZ_BLOB_TRIGGERSP1 PRIMARY KEY (TRIGGER_NAME, TRIGGER_GROUP) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-194', '2.0.4-SNP', '3:06ce3f0868aa0718931f0fb32b26e5ba', 194);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-195::Jeff Caddel (generated)::(Checksum: 3:e97c34dbda228c8bef5d8c7dbbf6fe11)
ALTER TABLE KRSB_QRTZ_CALENDARS ADD CONSTRAINT KRSB_QRTZ_CALENDARSP1 PRIMARY KEY (CALENDAR_NAME) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-195', '2.0.4-SNP', '3:e97c34dbda228c8bef5d8c7dbbf6fe11', 195);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-196::Jeff Caddel (generated)::(Checksum: 3:7e0a0253a559f63d1ed229bb85375e0c)
ALTER TABLE KRSB_QRTZ_CRON_TRIGGERS ADD CONSTRAINT KRSB_QRTZ_CRON_TRIGGERSP1 PRIMARY KEY (TRIGGER_NAME, TRIGGER_GROUP) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-196', '2.0.4-SNP', '3:7e0a0253a559f63d1ed229bb85375e0c', 196);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-197::Jeff Caddel (generated)::(Checksum: 3:d47ab73099d9caafedd1324abea04006)
ALTER TABLE KRSB_QRTZ_FIRED_TRIGGERS ADD CONSTRAINT KRSB_QRTZ_FIRED_TRIGGERSP1 PRIMARY KEY (ENTRY_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-197', '2.0.4-SNP', '3:d47ab73099d9caafedd1324abea04006', 197);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-198::Jeff Caddel (generated)::(Checksum: 3:9ff1ddffee93553e3320b74d72fc96c0)
ALTER TABLE KRSB_QRTZ_JOB_DETAILS ADD CONSTRAINT KRSB_QRTZ_JOB_DETAILSP1 PRIMARY KEY (JOB_NAME, JOB_GROUP) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-198', '2.0.4-SNP', '3:9ff1ddffee93553e3320b74d72fc96c0', 198);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-199::Jeff Caddel (generated)::(Checksum: 3:849ddf998dab77d7a5f987cc29c19999)
ALTER TABLE KRSB_QRTZ_JOB_LISTENERS ADD CONSTRAINT KRSB_QRTZ_JOB_LISTENERSP1 PRIMARY KEY (JOB_NAME, JOB_GROUP, JOB_LISTENER) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-199', '2.0.4-SNP', '3:849ddf998dab77d7a5f987cc29c19999', 199);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-200::Jeff Caddel (generated)::(Checksum: 3:ebf7a705fbb60de0e1f2e55ae2f8dd8c)
ALTER TABLE KRSB_QRTZ_LOCKS ADD CONSTRAINT KRSB_QRTZ_LOCKSP1 PRIMARY KEY (LOCK_NAME) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-200', '2.0.4-SNP', '3:ebf7a705fbb60de0e1f2e55ae2f8dd8c', 200);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-201::Jeff Caddel (generated)::(Checksum: 3:e7d9bdb29c6e7feaca0ea14e589983c4)
ALTER TABLE KRSB_QRTZ_PAUSED_TRIGGER_GRPS ADD CONSTRAINT KRSB_QRTZ_PAUSED_TRIGGER_GRP1 PRIMARY KEY (TRIGGER_GROUP) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-201', '2.0.4-SNP', '3:e7d9bdb29c6e7feaca0ea14e589983c4', 201);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-202::Jeff Caddel (generated)::(Checksum: 3:de6f9cb616316d106fc23f8e28efa2c6)
ALTER TABLE KRSB_QRTZ_SCHEDULER_STATE ADD CONSTRAINT KRSB_QRTZ_SCHEDULER_STATEP1 PRIMARY KEY (INSTANCE_NAME) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-202', '2.0.4-SNP', '3:de6f9cb616316d106fc23f8e28efa2c6', 202);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-203::Jeff Caddel (generated)::(Checksum: 3:8acfbf89cdb99fc07eee473ba533c8c9)
ALTER TABLE KRSB_QRTZ_SIMPLE_TRIGGERS ADD CONSTRAINT KRSB_QRTZ_SIMPLE_TRIGGERSP1 PRIMARY KEY (TRIGGER_NAME, TRIGGER_GROUP) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-203', '2.0.4-SNP', '3:8acfbf89cdb99fc07eee473ba533c8c9', 203);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-204::Jeff Caddel (generated)::(Checksum: 3:00dedfe2ac497bd53ade058a7fee9898)
ALTER TABLE KRSB_QRTZ_TRIGGERS ADD CONSTRAINT KRSB_QRTZ_TRIGGERSP1 PRIMARY KEY (TRIGGER_NAME, TRIGGER_GROUP) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-204', '2.0.4-SNP', '3:00dedfe2ac497bd53ade058a7fee9898', 204);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-205::Jeff Caddel (generated)::(Checksum: 3:4e1399be2b37be164f4e6f92b8bd72c9)
ALTER TABLE KRSB_QRTZ_TRIGGER_LISTENERS ADD CONSTRAINT KRSB_QRTZ_TRIGGER_LISTENERSP1 PRIMARY KEY (TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_LISTENER) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-205', '2.0.4-SNP', '3:4e1399be2b37be164f4e6f92b8bd72c9', 205);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-206::Jeff Caddel (generated)::(Checksum: 3:7942b364321b08754ab30af6d57aee7d)
ALTER TABLE KRSB_SVC_DEF_T ADD CONSTRAINT KRSB_SVC_DEF_TP1 PRIMARY KEY (SVC_DEF_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-206', '2.0.4-SNP', '3:7942b364321b08754ab30af6d57aee7d', 206);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-207::Jeff Caddel (generated)::(Checksum: 3:1a9e5f1b963bc799d8a4d790dc23e786)
ALTER TABLE KRSB_SVC_DSCRPTR_T ADD CONSTRAINT KRSB_SVC_DSCRPTR_TP1 PRIMARY KEY (SVC_DSCRPTR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-207', '2.0.4-SNP', '3:1a9e5f1b963bc799d8a4d790dc23e786', 207);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-208::Jeff Caddel (generated)::(Checksum: 3:1668f5427d7e1d16e58b1586c5791c60)
ALTER TABLE KSAP_ATP ADD CONSTRAINT KSAP_ATPP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-208', '2.0.4-SNP', '3:1668f5427d7e1d16e58b1586c5791c60', 208);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-209::Jeff Caddel (generated)::(Checksum: 3:78ab86d5284e8d57855224b55263516d)
ALTER TABLE KSAP_ATP_ATTR ADD CONSTRAINT KSAP_ATP_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-209', '2.0.4-SNP', '3:78ab86d5284e8d57855224b55263516d', 209);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-210::Jeff Caddel (generated)::(Checksum: 3:d90fc440a6084bbd6c8f3b2636eb3f3b)
ALTER TABLE KSAP_ATP_DUR_TYPE ADD CONSTRAINT KSAP_ATP_DUR_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-210', '2.0.4-SNP', '3:d90fc440a6084bbd6c8f3b2636eb3f3b', 210);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-211::Jeff Caddel (generated)::(Checksum: 3:974bcd099c1a629dfbadfb77e414531b)
ALTER TABLE KSAP_ATP_DUR_TYPE_ATTR ADD CONSTRAINT KSAP_ATP_DUR_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-211', '2.0.4-SNP', '3:974bcd099c1a629dfbadfb77e414531b', 211);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-212::Jeff Caddel (generated)::(Checksum: 3:3b65c10e94d9751d9f18564c5cf76083)
ALTER TABLE KSAP_ATP_SEASONAL_TYPE ADD CONSTRAINT KSAP_ATP_SEASONAL_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-212', '2.0.4-SNP', '3:3b65c10e94d9751d9f18564c5cf76083', 212);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-213::Jeff Caddel (generated)::(Checksum: 3:4fdacbdc012e0ecc96aaf4fc95c4ba6a)
ALTER TABLE KSAP_ATP_SEASONAL_TYPE_ATTR ADD CONSTRAINT KSAP_ATP_SEASONAL_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-213', '2.0.4-SNP', '3:4fdacbdc012e0ecc96aaf4fc95c4ba6a', 213);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-214::Jeff Caddel (generated)::(Checksum: 3:a6d14cffaadff98c61ff939266a2a7d8)
ALTER TABLE KSAP_ATP_TYPE ADD CONSTRAINT KSAP_ATP_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-214', '2.0.4-SNP', '3:a6d14cffaadff98c61ff939266a2a7d8', 214);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-215::Jeff Caddel (generated)::(Checksum: 3:05dc42f15ebddd0108335adbe84d280d)
ALTER TABLE KSAP_ATP_TYPE_ATTR ADD CONSTRAINT KSAP_ATP_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-215', '2.0.4-SNP', '3:05dc42f15ebddd0108335adbe84d280d', 215);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-216::Jeff Caddel (generated)::(Checksum: 3:a8f2915743f55ea44ebabf6e219896fe)
ALTER TABLE KSAP_DT_RANGE ADD CONSTRAINT KSAP_DT_RANGEP1 PRIMARY KEY (DATERANGE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-216', '2.0.4-SNP', '3:a8f2915743f55ea44ebabf6e219896fe', 216);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-217::Jeff Caddel (generated)::(Checksum: 3:8238e67beddfa1b140e90fafda724719)
ALTER TABLE KSAP_DT_RANGE_ATTR ADD CONSTRAINT KSAP_DT_RANGE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-217', '2.0.4-SNP', '3:8238e67beddfa1b140e90fafda724719', 217);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-218::Jeff Caddel (generated)::(Checksum: 3:870eb24201c606f03914eaae65350eaa)
ALTER TABLE KSAP_DT_RANGE_TYPE ADD CONSTRAINT KSAP_DT_RANGE_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-218', '2.0.4-SNP', '3:870eb24201c606f03914eaae65350eaa', 218);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-219::Jeff Caddel (generated)::(Checksum: 3:b2180d3e98d5bc496368f256fe391ac1)
ALTER TABLE KSAP_DT_RANGE_TYPE_ATTR ADD CONSTRAINT KSAP_DT_RANGE_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-219', '2.0.4-SNP', '3:b2180d3e98d5bc496368f256fe391ac1', 219);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-220::Jeff Caddel (generated)::(Checksum: 3:ad064f3dd5ecdebf087591dceb1e859c)
ALTER TABLE KSAP_MLSTN ADD CONSTRAINT KSAP_MLSTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-220', '2.0.4-SNP', '3:ad064f3dd5ecdebf087591dceb1e859c', 220);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-221::Jeff Caddel (generated)::(Checksum: 3:69c18077ec43e805b864568a98f58e24)
ALTER TABLE KSAP_MLSTN_ATTR ADD CONSTRAINT KSAP_MLSTN_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-221', '2.0.4-SNP', '3:69c18077ec43e805b864568a98f58e24', 221);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-222::Jeff Caddel (generated)::(Checksum: 3:435761997ea4bcbe40c870b77769af8b)
ALTER TABLE KSAP_MLSTN_TYPE ADD CONSTRAINT KSAP_MLSTN_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-222', '2.0.4-SNP', '3:435761997ea4bcbe40c870b77769af8b', 222);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-223::Jeff Caddel (generated)::(Checksum: 3:85d8b9b5458c32c65050cf0c6224e28a)
ALTER TABLE KSAP_MLSTN_TYPE_ATTR ADD CONSTRAINT KSAP_MLSTN_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-223', '2.0.4-SNP', '3:85d8b9b5458c32c65050cf0c6224e28a', 223);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-224::Jeff Caddel (generated)::(Checksum: 3:24e8b00a1cf5819097eb0c45faf2da8d)
ALTER TABLE KSAP_RICH_TEXT_T ADD CONSTRAINT KSAP_RICH_TEXT_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-224', '2.0.4-SNP', '3:24e8b00a1cf5819097eb0c45faf2da8d', 224);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-225::Jeff Caddel (generated)::(Checksum: 3:abe3a7440a88ffc97273e770086c6519)
ALTER TABLE KSCO_COMMENT ADD CONSTRAINT KSCO_COMMENTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-225', '2.0.4-SNP', '3:abe3a7440a88ffc97273e770086c6519', 225);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-226::Jeff Caddel (generated)::(Checksum: 3:b5c13545c461b0e740cbcf4ddee11e99)
ALTER TABLE KSCO_COMMENT_ATTR ADD CONSTRAINT KSCO_COMMENT_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-226', '2.0.4-SNP', '3:b5c13545c461b0e740cbcf4ddee11e99', 226);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-227::Jeff Caddel (generated)::(Checksum: 3:a07ca5d89702164d49a6169a22b351f2)
ALTER TABLE KSCO_COMMENT_TYPE ADD CONSTRAINT KSCO_COMMENT_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-227', '2.0.4-SNP', '3:a07ca5d89702164d49a6169a22b351f2', 227);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-228::Jeff Caddel (generated)::(Checksum: 3:6d2da28d3daf0f2ae505cda5f4453b98)
ALTER TABLE KSCO_COMMENT_TYPE_ATTR ADD CONSTRAINT KSCO_COMMENT_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-228', '2.0.4-SNP', '3:6d2da28d3daf0f2ae505cda5f4453b98', 228);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-229::Jeff Caddel (generated)::(Checksum: 3:35232943658311ce40d33f7c1f57beb7)
ALTER TABLE KSCO_REFERENCE ADD CONSTRAINT KSCO_REFERENCEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-229', '2.0.4-SNP', '3:35232943658311ce40d33f7c1f57beb7', 229);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-230::Jeff Caddel (generated)::(Checksum: 3:cab079b6a3feb827c095b1771858e328)
ALTER TABLE KSCO_REFERENCE_TYPE ADD CONSTRAINT KSCO_REFERENCE_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-230', '2.0.4-SNP', '3:cab079b6a3feb827c095b1771858e328', 230);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-231::Jeff Caddel (generated)::(Checksum: 3:9077caf947d04cf117c1212edb952f39)
ALTER TABLE KSCO_REFERENCE_TYPE_ATTR ADD CONSTRAINT KSCO_REFERENCE_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-231', '2.0.4-SNP', '3:9077caf947d04cf117c1212edb952f39', 231);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-232::Jeff Caddel (generated)::(Checksum: 3:3beaec0e856700dd531d2ec7d8f19331)
ALTER TABLE KSCO_RICH_TEXT_T ADD CONSTRAINT KSCO_RICH_TEXT_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-232', '2.0.4-SNP', '3:3beaec0e856700dd531d2ec7d8f19331', 232);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-233::Jeff Caddel (generated)::(Checksum: 3:59e81472e536eab0a811d1a75aff715d)
ALTER TABLE KSCO_TAG ADD CONSTRAINT KSCO_TAGP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-233', '2.0.4-SNP', '3:59e81472e536eab0a811d1a75aff715d', 233);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-234::Jeff Caddel (generated)::(Checksum: 3:42df3642350b6d0ab21b5ab3a61bf58b)
ALTER TABLE KSCO_TAG_ATTR ADD CONSTRAINT KSCO_TAG_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-234', '2.0.4-SNP', '3:42df3642350b6d0ab21b5ab3a61bf58b', 234);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-235::Jeff Caddel (generated)::(Checksum: 3:956e07c7d34fa1f3f0339a35ede72387)
ALTER TABLE KSCO_TAG_TYPE ADD CONSTRAINT KSCO_TAG_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-235', '2.0.4-SNP', '3:956e07c7d34fa1f3f0339a35ede72387', 235);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-236::Jeff Caddel (generated)::(Checksum: 3:fea32190337dac9ca5a04192e1903a54)
ALTER TABLE KSCO_TAG_TYPE_ATTR ADD CONSTRAINT KSCO_TAG_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-236', '2.0.4-SNP', '3:fea32190337dac9ca5a04192e1903a54', 236);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-237::Jeff Caddel (generated)::(Checksum: 3:4f57004f5c97411e4f2cfbb52da5cc42)
ALTER TABLE KSDO_DOCUMENT ADD CONSTRAINT KSDO_DOCUMENTP1 PRIMARY KEY (DOC_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-237', '2.0.4-SNP', '3:4f57004f5c97411e4f2cfbb52da5cc42', 237);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-238::Jeff Caddel (generated)::(Checksum: 3:af313b198b529c3a11f53c3c015b52bf)
ALTER TABLE KSDO_DOCUMENT_ATTR ADD CONSTRAINT KSDO_DOCUMENT_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-238', '2.0.4-SNP', '3:af313b198b529c3a11f53c3c015b52bf', 238);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-239::Jeff Caddel (generated)::(Checksum: 3:4e9879fb4e9f73208af7e63fa89ab58e)
ALTER TABLE KSDO_DOCUMENT_CATEGORY ADD CONSTRAINT KSDO_DOCUMENT_CATEGORYP1 PRIMARY KEY (CATEGORY_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-239', '2.0.4-SNP', '3:4e9879fb4e9f73208af7e63fa89ab58e', 239);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-240::Jeff Caddel (generated)::(Checksum: 3:1e4b6f4d577963a6a4003737a961c33f)
ALTER TABLE KSDO_DOCUMENT_CATEGORY_ATTR ADD CONSTRAINT KSDO_DOCUMENT_CATEGORY_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-240', '2.0.4-SNP', '3:1e4b6f4d577963a6a4003737a961c33f', 240);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-241::Jeff Caddel (generated)::(Checksum: 3:f0718945733e6a687cb5168076e0131a)
ALTER TABLE KSDO_DOCUMENT_TYPE ADD CONSTRAINT KSDO_DOCUMENT_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-241', '2.0.4-SNP', '3:f0718945733e6a687cb5168076e0131a', 241);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-242::Jeff Caddel (generated)::(Checksum: 3:867283373fd3286404b7f459379a7497)
ALTER TABLE KSDO_DOCUMENT_TYPE_ATTR ADD CONSTRAINT KSDO_DOCUMENT_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-242', '2.0.4-SNP', '3:867283373fd3286404b7f459379a7497', 242);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-243::Jeff Caddel (generated)::(Checksum: 3:06e706a268ebd3812f90a99352fb1e67)
ALTER TABLE KSDO_REF_DOC_RELTN ADD CONSTRAINT KSDO_REF_DOC_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-243', '2.0.4-SNP', '3:06e706a268ebd3812f90a99352fb1e67', 243);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-244::Jeff Caddel (generated)::(Checksum: 3:a8ca1e78a32e6f0058d019a461411d63)
ALTER TABLE KSDO_REF_DOC_RELTN_TYPE ADD CONSTRAINT KSDO_REF_DOC_RELTN_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-244', '2.0.4-SNP', '3:a8ca1e78a32e6f0058d019a461411d63', 244);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-245::Jeff Caddel (generated)::(Checksum: 3:f3f410753036c42a913d84ed2c5976da)
ALTER TABLE KSDO_REF_DOC_RELTN_TYPE_ATTR ADD CONSTRAINT KSDO_REF_DOC_RELTN_TYPE_ATTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-245', '2.0.4-SNP', '3:f3f410753036c42a913d84ed2c5976da', 245);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-246::Jeff Caddel (generated)::(Checksum: 3:eaf968216240a1dac1622ee1e19a165c)
ALTER TABLE KSDO_REF_DOC_REL_ATTR ADD CONSTRAINT KSDO_REF_DOC_REL_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-246', '2.0.4-SNP', '3:eaf968216240a1dac1622ee1e19a165c', 246);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-247::Jeff Caddel (generated)::(Checksum: 3:74d7ba1387315bee8f89299cf0b58dd8)
ALTER TABLE KSDO_REF_OBJ_SUB_TYPE ADD CONSTRAINT KSDO_REF_OBJ_SUB_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-247', '2.0.4-SNP', '3:74d7ba1387315bee8f89299cf0b58dd8', 247);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-248::Jeff Caddel (generated)::(Checksum: 3:e2b7875bd2307a99200402a23c0f1e12)
ALTER TABLE KSDO_REF_OBJ_SUB_TYPE_ATTR ADD CONSTRAINT KSDO_REF_OBJ_SUB_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-248', '2.0.4-SNP', '3:e2b7875bd2307a99200402a23c0f1e12', 248);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-249::Jeff Caddel (generated)::(Checksum: 3:39ff05373b091f5ffc5bc94dd607175e)
ALTER TABLE KSDO_REF_OBJ_TYPE ADD CONSTRAINT KSDO_REF_OBJ_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-249', '2.0.4-SNP', '3:39ff05373b091f5ffc5bc94dd607175e', 249);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-250::Jeff Caddel (generated)::(Checksum: 3:554103f12429f6d50e74f0c3999dc7a7)
ALTER TABLE KSDO_REF_OBJ_TYPE_ATTR ADD CONSTRAINT KSDO_REF_OBJ_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-250', '2.0.4-SNP', '3:554103f12429f6d50e74f0c3999dc7a7', 250);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-251::Jeff Caddel (generated)::(Checksum: 3:3056cee12fc6ed4ef7260792dc0b5043)
ALTER TABLE KSDO_RICH_TEXT_T ADD CONSTRAINT KSDO_RICH_TEXT_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-251', '2.0.4-SNP', '3:3056cee12fc6ed4ef7260792dc0b5043', 251);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-252::Jeff Caddel (generated)::(Checksum: 3:3f40040336952639cd671ca1bf4531f5)
ALTER TABLE KSEM_CTX_T ADD CONSTRAINT KSEM_CTX_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-252', '2.0.4-SNP', '3:3f40040336952639cd671ca1bf4531f5', 252);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-253::Jeff Caddel (generated)::(Checksum: 3:a490ec1d5365ecf778ed31b52bb8254a)
ALTER TABLE KSEM_ENUM_T ADD CONSTRAINT KSEM_ENUM_TP1 PRIMARY KEY (ENUM_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-253', '2.0.4-SNP', '3:a490ec1d5365ecf778ed31b52bb8254a', 253);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-254::Jeff Caddel (generated)::(Checksum: 3:385afb542f8d15cff6e0bba293f4dae7)
ALTER TABLE KSEM_ENUM_VAL_T ADD CONSTRAINT KSEM_ENUM_VAL_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-254', '2.0.4-SNP', '3:385afb542f8d15cff6e0bba293f4dae7', 254);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-255::Jeff Caddel (generated)::(Checksum: 3:bf25cd20e8c3cdbebed3e8d5de7e88a9)
ALTER TABLE KSEN_ATP ADD CONSTRAINT KSEN_ATPP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-255', '2.0.4-SNP', '3:bf25cd20e8c3cdbebed3e8d5de7e88a9', 255);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-256::Jeff Caddel (generated)::(Checksum: 3:94da4969860a4c5587aebdd11172b077)
ALTER TABLE KSEN_ATPATP_RELTN ADD CONSTRAINT KSEN_ATPATP_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-256', '2.0.4-SNP', '3:94da4969860a4c5587aebdd11172b077', 256);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-257::Jeff Caddel (generated)::(Checksum: 3:33ce2752c1b26dbda911c07b2306646b)
ALTER TABLE KSEN_ATPATP_RELTN_ATTR ADD CONSTRAINT KSEN_ATPATP_RELTN_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-257', '2.0.4-SNP', '3:33ce2752c1b26dbda911c07b2306646b', 257);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-258::Jeff Caddel (generated)::(Checksum: 3:e749a9b621875d8a7410455e87a2550e)
ALTER TABLE KSEN_ATPMSTONE_RELTN ADD CONSTRAINT KSEN_ATPMSTONE_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-258', '2.0.4-SNP', '3:e749a9b621875d8a7410455e87a2550e', 258);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-259::Jeff Caddel (generated)::(Checksum: 3:950ef7384edb2b3eb6f572a23745ccdd)
ALTER TABLE KSEN_ATPMSTONE_RELTN_ATTR ADD CONSTRAINT KSEN_ATPMSTONE_RELTN_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-259', '2.0.4-SNP', '3:950ef7384edb2b3eb6f572a23745ccdd', 259);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-260::Jeff Caddel (generated)::(Checksum: 3:826cee6279c165136b40ce1e72c1e8e3)
ALTER TABLE KSEN_ATP_ATTR ADD CONSTRAINT KSEN_ATP_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-260', '2.0.4-SNP', '3:826cee6279c165136b40ce1e72c1e8e3', 260);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-261::Jeff Caddel (generated)::(Checksum: 3:105c8bbc24f51ba2e4fb1a859c47d9f9)
ALTER TABLE KSEN_ATP_STATE ADD CONSTRAINT KSEN_ATP_STATEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-261', '2.0.4-SNP', '3:105c8bbc24f51ba2e4fb1a859c47d9f9', 261);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-262::Jeff Caddel (generated)::(Checksum: 3:e34f814a85b52dc54683ff96c8293445)
ALTER TABLE KSEN_ATP_TYPE ADD CONSTRAINT KSEN_ATP_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-262', '2.0.4-SNP', '3:e34f814a85b52dc54683ff96c8293445', 262);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-263::Jeff Caddel (generated)::(Checksum: 3:5ad46495a021cb58310b8c40a8e78fae)
ALTER TABLE KSEN_COMM_STATE ADD CONSTRAINT KSEN_COMM_STATEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-263', '2.0.4-SNP', '3:5ad46495a021cb58310b8c40a8e78fae', 263);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-264::Jeff Caddel (generated)::(Checksum: 3:c2ff588375237cc4ab28833fb018d569)
ALTER TABLE KSEN_HOLD ADD CONSTRAINT KSEN_HOLDP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-264', '2.0.4-SNP', '3:c2ff588375237cc4ab28833fb018d569', 264);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-265::Jeff Caddel (generated)::(Checksum: 3:8b14845136aced5f1833b3e83ae32fe2)
ALTER TABLE KSEN_HOLD_ATTR ADD CONSTRAINT KSEN_HOLD_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-265', '2.0.4-SNP', '3:8b14845136aced5f1833b3e83ae32fe2', 265);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-266::Jeff Caddel (generated)::(Checksum: 3:9a7f79034dfabc0cd98473e675aeb339)
ALTER TABLE KSEN_HOLD_RICH_TEXT ADD CONSTRAINT KSEN_HOLD_RICH_TEXTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-266', '2.0.4-SNP', '3:9a7f79034dfabc0cd98473e675aeb339', 266);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-267::Jeff Caddel (generated)::(Checksum: 3:c0445952093f05308280436416f8a647)
ALTER TABLE KSEN_HOLD_TYPE ADD CONSTRAINT KSEN_HOLD_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-267', '2.0.4-SNP', '3:c0445952093f05308280436416f8a647', 267);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-268::Jeff Caddel (generated)::(Checksum: 3:b1fb98bbb8c10255f9e83939837ff8b6)
ALTER TABLE KSEN_HOLD_TYPE_ATTR ADD CONSTRAINT KSEN_HOLD_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-268', '2.0.4-SNP', '3:b1fb98bbb8c10255f9e83939837ff8b6', 268);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-269::Jeff Caddel (generated)::(Checksum: 3:21479ae795d96ef868831121d9e1dbab)
ALTER TABLE KSEN_ISSRESTRCTN_RELTN ADD CONSTRAINT KSEN_ISSRESTRCTN_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-269', '2.0.4-SNP', '3:21479ae795d96ef868831121d9e1dbab', 269);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-270::Jeff Caddel (generated)::(Checksum: 3:17154da05d8530494d17bf1ea6481571)
ALTER TABLE KSEN_ISSUE ADD CONSTRAINT KSEN_ISSUEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-270', '2.0.4-SNP', '3:17154da05d8530494d17bf1ea6481571', 270);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-271::Jeff Caddel (generated)::(Checksum: 3:ff2b3c9d576063b23baf84d4dcc81e75)
ALTER TABLE KSEN_ISSUE_ATTR ADD CONSTRAINT KSEN_ISSUE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-271', '2.0.4-SNP', '3:ff2b3c9d576063b23baf84d4dcc81e75', 271);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-272::Jeff Caddel (generated)::(Checksum: 3:b6c933c7d18b24650d959da6df974424)
ALTER TABLE KSEN_LUI ADD CONSTRAINT KSEN_LUIP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-272', '2.0.4-SNP', '3:b6c933c7d18b24650d959da6df974424', 272);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-273::Jeff Caddel (generated)::(Checksum: 3:b1513cd67ec66bbd5f1d67d8da5d0766)
ALTER TABLE KSEN_LUILUI_RELTN ADD CONSTRAINT KSEN_LUILUI_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-273', '2.0.4-SNP', '3:b1513cd67ec66bbd5f1d67d8da5d0766', 273);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-274::Jeff Caddel (generated)::(Checksum: 3:3ff4c305af13e48827390eb1c1197647)
ALTER TABLE KSEN_LUILUI_RELTN_ATTR ADD CONSTRAINT KSEN_LUILUI_RELTN_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-274', '2.0.4-SNP', '3:3ff4c305af13e48827390eb1c1197647', 274);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-275::Jeff Caddel (generated)::(Checksum: 3:c19775e4e938ce00564a0cd1dc2860cc)
ALTER TABLE KSEN_LUI_ATTR ADD CONSTRAINT KSEN_LUI_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-275', '2.0.4-SNP', '3:c19775e4e938ce00564a0cd1dc2860cc', 275);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-276::Jeff Caddel (generated)::(Checksum: 3:daa04c5c4ab378ba8c872ea2f16ab1df)
ALTER TABLE KSEN_LUI_IDENT ADD CONSTRAINT KSEN_LUI_IDENTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-276', '2.0.4-SNP', '3:daa04c5c4ab378ba8c872ea2f16ab1df', 276);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-277::Jeff Caddel (generated)::(Checksum: 3:c63c9442de233a24582eb6843f83e20b)
ALTER TABLE KSEN_LUI_IDENT_ATTR ADD CONSTRAINT KSEN_LUI_IDENT_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-277', '2.0.4-SNP', '3:c63c9442de233a24582eb6843f83e20b', 277);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-278::Jeff Caddel (generated)::(Checksum: 3:ce0bbf289a7b36fb3928fdb1a6d88418)
ALTER TABLE KSEN_LUI_INSTR ADD CONSTRAINT KSEN_LUI_INSTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-278', '2.0.4-SNP', '3:ce0bbf289a7b36fb3928fdb1a6d88418', 278);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-279::Jeff Caddel (generated)::(Checksum: 3:3aaef0d3e0c50afb91f90c72160e288a)
ALTER TABLE KSEN_LUI_LUCD ADD CONSTRAINT KSEN_LUI_LUCDP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-279', '2.0.4-SNP', '3:3aaef0d3e0c50afb91f90c72160e288a', 279);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-280::Jeff Caddel (generated)::(Checksum: 3:908dd516d98e52223bbed29711e1e82c)
ALTER TABLE KSEN_LUI_LUCD_ATTR ADD CONSTRAINT KSEN_LUI_LUCD_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-280', '2.0.4-SNP', '3:908dd516d98e52223bbed29711e1e82c', 280);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-281::Jeff Caddel (generated)::(Checksum: 3:bbe2c23532c30f62983328a1b08ba640)
ALTER TABLE KSEN_LUI_RICH_TEXT ADD CONSTRAINT KSEN_LUI_RICH_TEXTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-281', '2.0.4-SNP', '3:bbe2c23532c30f62983328a1b08ba640', 281);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-282::Jeff Caddel (generated)::(Checksum: 3:52fd2022db6c1f09693f657ffe1d8b5b)
ALTER TABLE KSEN_LUI_TYPE ADD CONSTRAINT KSEN_LUI_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-282', '2.0.4-SNP', '3:52fd2022db6c1f09693f657ffe1d8b5b', 282);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-283::Jeff Caddel (generated)::(Checksum: 3:83c799b84e9a65d092f39f09a88f289a)
ALTER TABLE KSEN_LUI_TYPE_ATTR ADD CONSTRAINT KSEN_LUI_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-283', '2.0.4-SNP', '3:83c799b84e9a65d092f39f09a88f289a', 283);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-284::Jeff Caddel (generated)::(Checksum: 3:7b30eb2755d7d3223b2a80718a271dc9)
ALTER TABLE KSEN_MSTONE ADD CONSTRAINT KSEN_MSTONEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-284', '2.0.4-SNP', '3:7b30eb2755d7d3223b2a80718a271dc9', 284);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-285::Jeff Caddel (generated)::(Checksum: 3:31e4941d7c440bbd2a293358aaf7e23f)
ALTER TABLE KSEN_RESTRICTION ADD CONSTRAINT KSEN_RESTRICTIONP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-285', '2.0.4-SNP', '3:31e4941d7c440bbd2a293358aaf7e23f', 285);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-286::Jeff Caddel (generated)::(Checksum: 3:3116297d37b05bc3424595230feaaa80)
ALTER TABLE KSEN_RESTRICTION_ATTR ADD CONSTRAINT KSEN_RESTRICTION_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-286', '2.0.4-SNP', '3:3116297d37b05bc3424595230feaaa80', 286);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-287::Jeff Caddel (generated)::(Checksum: 3:6d55d4cafee871724d2ecd1a2e5a4ba5)
ALTER TABLE KSEN_RICH_TEXT_T ADD CONSTRAINT KSEN_RICH_TEXT_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-287', '2.0.4-SNP', '3:6d55d4cafee871724d2ecd1a2e5a4ba5', 287);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-288::Jeff Caddel (generated)::(Checksum: 3:1105cb70f2a2b63f9f0e0814db2602f2)
ALTER TABLE KSEN_STATEPROCESS_RELTN ADD CONSTRAINT KSEN_STATEPROCESS_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-288', '2.0.4-SNP', '3:1105cb70f2a2b63f9f0e0814db2602f2', 288);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-289::Jeff Caddel (generated)::(Checksum: 3:7c9d587b799d3069adf0606b4c2330c2)
ALTER TABLE KSEN_STATE_ATTR ADD CONSTRAINT KSEN_STATE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-289', '2.0.4-SNP', '3:7c9d587b799d3069adf0606b4c2330c2', 289);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-290::Jeff Caddel (generated)::(Checksum: 3:36d4b6b6d35e8b7b6bfc43a9d90dd29a)
ALTER TABLE KSEN_STATE_PROCESS ADD CONSTRAINT KSEN_STATE_PROCESSP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-290', '2.0.4-SNP', '3:36d4b6b6d35e8b7b6bfc43a9d90dd29a', 290);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-291::Jeff Caddel (generated)::(Checksum: 3:47d84faea856b6a25c0fed4f56067c2e)
ALTER TABLE KSEN_TYPETYPE_RELTN ADD CONSTRAINT KSEN_TYPETYPE_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-291', '2.0.4-SNP', '3:47d84faea856b6a25c0fed4f56067c2e', 291);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-292::Jeff Caddel (generated)::(Checksum: 3:d78a984084a8c862b5a9ee17cf4ed666)
ALTER TABLE KSLO_ATTR ADD CONSTRAINT KSLO_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-292', '2.0.4-SNP', '3:d78a984084a8c862b5a9ee17cf4ed666', 292);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-293::Jeff Caddel (generated)::(Checksum: 3:5bac8c219dcc1931e39f6e25d0c04602)
ALTER TABLE KSLO_LO ADD CONSTRAINT KSLO_LOP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-293', '2.0.4-SNP', '3:5bac8c219dcc1931e39f6e25d0c04602', 293);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-294::Jeff Caddel (generated)::(Checksum: 3:7bee5e68ff8f165ddc4bef5f2557c489)
ALTER TABLE KSLO_LO_ALLOWED_RELTN_TYPE ADD CONSTRAINT KSLO_LO_ALLOWED_RELTN_TYPEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-294', '2.0.4-SNP', '3:7bee5e68ff8f165ddc4bef5f2557c489', 294);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-295::Jeff Caddel (generated)::(Checksum: 3:f97e734ec93e2982e0398896013a5f0a)
ALTER TABLE KSLO_LO_CATEGORY ADD CONSTRAINT KSLO_LO_CATEGORYP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-295', '2.0.4-SNP', '3:f97e734ec93e2982e0398896013a5f0a', 295);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-296::Jeff Caddel (generated)::(Checksum: 3:455b8e2f6bd2025bd53ac5c22861cf0b)
ALTER TABLE KSLO_LO_CATEGORY_ATTR ADD CONSTRAINT KSLO_LO_CATEGORY_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-296', '2.0.4-SNP', '3:455b8e2f6bd2025bd53ac5c22861cf0b', 296);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-297::Jeff Caddel (generated)::(Checksum: 3:c03585c286b4c6cf38692d0c240e1a8c)
ALTER TABLE KSLO_LO_CATEGORY_TYPE ADD CONSTRAINT KSLO_LO_CATEGORY_TYPEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-297', '2.0.4-SNP', '3:c03585c286b4c6cf38692d0c240e1a8c', 297);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-298::Jeff Caddel (generated)::(Checksum: 3:4cb543ffd6f9909cc32ee35d56adb5d2)
ALTER TABLE KSLO_LO_CATEGORY_TYPE_ATTR ADD CONSTRAINT KSLO_LO_CATEGORY_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-298', '2.0.4-SNP', '3:4cb543ffd6f9909cc32ee35d56adb5d2', 298);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-299::Jeff Caddel (generated)::(Checksum: 3:60a8d5715025ed126c2fee03bc6986b5)
ALTER TABLE KSLO_LO_JN_LOCATEGORY ADD CONSTRAINT KSLO_LO_JN_LOCATEGORYP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-299', '2.0.4-SNP', '3:60a8d5715025ed126c2fee03bc6986b5', 299);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-300::Jeff Caddel (generated)::(Checksum: 3:2bb5ac9336f65ea17e17673453f18d61)
ALTER TABLE KSLO_LO_RELTN ADD CONSTRAINT KSLO_LO_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-300', '2.0.4-SNP', '3:2bb5ac9336f65ea17e17673453f18d61', 300);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-301::Jeff Caddel (generated)::(Checksum: 3:d5a35b4fbdf1546510a76be20cefd8f5)
ALTER TABLE KSLO_LO_RELTN_ATTR ADD CONSTRAINT KSLO_LO_RELTN_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-301', '2.0.4-SNP', '3:d5a35b4fbdf1546510a76be20cefd8f5', 301);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-302::Jeff Caddel (generated)::(Checksum: 3:cc51b96ced37a4d4b8e16f363cdcff1c)
ALTER TABLE KSLO_LO_RELTN_TYPE ADD CONSTRAINT KSLO_LO_RELTN_TYPEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-302', '2.0.4-SNP', '3:cc51b96ced37a4d4b8e16f363cdcff1c', 302);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-303::Jeff Caddel (generated)::(Checksum: 3:0c1fd1090022fad2a3e03a34ec4ba916)
ALTER TABLE KSLO_LO_RELTN_TYPE_ATTR ADD CONSTRAINT KSLO_LO_RELTN_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-303', '2.0.4-SNP', '3:0c1fd1090022fad2a3e03a34ec4ba916', 303);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-304::Jeff Caddel (generated)::(Checksum: 3:6a552e43f6d1d3600ac431e33b3c2c72)
ALTER TABLE KSLO_LO_REPOSITORY ADD CONSTRAINT KSLO_LO_REPOSITORYP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-304', '2.0.4-SNP', '3:6a552e43f6d1d3600ac431e33b3c2c72', 304);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-305::Jeff Caddel (generated)::(Checksum: 3:919cbc30f97824b9cfff9c93acda1c7e)
ALTER TABLE KSLO_LO_REPOSITORY_ATTR ADD CONSTRAINT KSLO_LO_REPOSITORY_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-305', '2.0.4-SNP', '3:919cbc30f97824b9cfff9c93acda1c7e', 305);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-306::Jeff Caddel (generated)::(Checksum: 3:9c03ae61f165088900d90ae706fd914c)
ALTER TABLE KSLO_LO_TYPE ADD CONSTRAINT KSLO_LO_TYPEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-306', '2.0.4-SNP', '3:9c03ae61f165088900d90ae706fd914c', 306);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-307::Jeff Caddel (generated)::(Checksum: 3:f831f16cf0164ec2bb9eed4c03f410de)
ALTER TABLE KSLO_LO_TYPE_ATTR ADD CONSTRAINT KSLO_LO_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-307', '2.0.4-SNP', '3:f831f16cf0164ec2bb9eed4c03f410de', 307);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-308::Jeff Caddel (generated)::(Checksum: 3:d31a7e7f23145c9dfa7eec0f32f44b06)
ALTER TABLE KSLO_RICH_TEXT_T ADD CONSTRAINT KSLO_RICH_TEXT_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-308', '2.0.4-SNP', '3:d31a7e7f23145c9dfa7eec0f32f44b06', 308);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-309::Jeff Caddel (generated)::(Checksum: 3:7999c0485d46e10e57e9ceb8e0c4aa85)
ALTER TABLE KSLP_LPR ADD CONSTRAINT KSLP_LPRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-309', '2.0.4-SNP', '3:7999c0485d46e10e57e9ceb8e0c4aa85', 309);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-310::Jeff Caddel (generated)::(Checksum: 3:0b53ec668e091d59f1c6bc20031ea0d5)
ALTER TABLE KSLP_LPR_ATTR ADD CONSTRAINT KSLP_LPR_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-310', '2.0.4-SNP', '3:0b53ec668e091d59f1c6bc20031ea0d5', 310);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-311::Jeff Caddel (generated)::(Checksum: 3:6896df3b4757fd742322c2ccc80f3476)
ALTER TABLE KSLP_LPR_STATE ADD CONSTRAINT KSLP_LPR_STATEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-311', '2.0.4-SNP', '3:6896df3b4757fd742322c2ccc80f3476', 311);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-312::Jeff Caddel (generated)::(Checksum: 3:e5fe9a76db3670321aad8cf458cf4d5e)
ALTER TABLE KSLP_LPR_TYPE ADD CONSTRAINT KSLP_LPR_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-312', '2.0.4-SNP', '3:e5fe9a76db3670321aad8cf458cf4d5e', 312);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-313::Jeff Caddel (generated)::(Checksum: 3:097de96f8395e4dad0162cc4b14b2581)
ALTER TABLE KSLR_RESCOMP ADD CONSTRAINT KSLR_RESCOMPP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-313', '2.0.4-SNP', '3:097de96f8395e4dad0162cc4b14b2581', 313);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-314::Jeff Caddel (generated)::(Checksum: 3:00585266187335e2f94f678851e776f5)
ALTER TABLE KSLR_RESCOMP_ATTR ADD CONSTRAINT KSLR_RESCOMP_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-314', '2.0.4-SNP', '3:00585266187335e2f94f678851e776f5', 314);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-315::Jeff Caddel (generated)::(Checksum: 3:a31611b673e226eb3926770886fb6d64)
ALTER TABLE KSLR_RESCOMP_TYPE ADD CONSTRAINT KSLR_RESCOMP_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-315', '2.0.4-SNP', '3:a31611b673e226eb3926770886fb6d64', 315);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-316::Jeff Caddel (generated)::(Checksum: 3:49b15b90f486f43a3f732fe0e24c7559)
ALTER TABLE KSLR_RESCOMP_TYPE_ATTR ADD CONSTRAINT KSLR_RESCOMP_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-316', '2.0.4-SNP', '3:49b15b90f486f43a3f732fe0e24c7559', 316);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-317::Jeff Caddel (generated)::(Checksum: 3:987690c807ba520a926a77ff987a74dd)
ALTER TABLE KSLR_RESULT_VALUE ADD CONSTRAINT KSLR_RESULT_VALUEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-317', '2.0.4-SNP', '3:987690c807ba520a926a77ff987a74dd', 317);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-318::Jeff Caddel (generated)::(Checksum: 3:c20d94a088ebe974b76d730f8cab3693)
ALTER TABLE KSLR_RICH_TEXT_T ADD CONSTRAINT KSLR_RICH_TEXT_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-318', '2.0.4-SNP', '3:c20d94a088ebe974b76d730f8cab3693', 318);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-319::Jeff Caddel (generated)::(Checksum: 3:2bc931aed224952dbe8de02ad69200ea)
ALTER TABLE KSLR_SCALE ADD CONSTRAINT KSLR_SCALEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-319', '2.0.4-SNP', '3:2bc931aed224952dbe8de02ad69200ea', 319);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-320::Jeff Caddel (generated)::(Checksum: 3:6a625cdf14479eae70a45a40ecf3099d)
ALTER TABLE KSLR_SCALE_ATTR ADD CONSTRAINT KSLR_SCALE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-320', '2.0.4-SNP', '3:6a625cdf14479eae70a45a40ecf3099d', 320);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-321::Jeff Caddel (generated)::(Checksum: 3:4efd77091e0a12653837b88a46d1025d)
ALTER TABLE KSLU_CLU ADD CONSTRAINT KSLU_CLUP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-321', '2.0.4-SNP', '3:4efd77091e0a12653837b88a46d1025d', 321);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-322::Jeff Caddel (generated)::(Checksum: 3:936a63d60a0f4063468bac1cee8f6727)
ALTER TABLE KSLU_CLUCLU_RELTN ADD CONSTRAINT KSLU_CLUCLU_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-322', '2.0.4-SNP', '3:936a63d60a0f4063468bac1cee8f6727', 322);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-323::Jeff Caddel (generated)::(Checksum: 3:67c1c092092f16119644ca4eeeb1bf42)
ALTER TABLE KSLU_CLUCLU_RELTN_ATTR ADD CONSTRAINT KSLU_CLUCLU_RELTN_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-323', '2.0.4-SNP', '3:67c1c092092f16119644ca4eeeb1bf42', 323);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-324::Jeff Caddel (generated)::(Checksum: 3:f859c9c3b1e5441da6f27eb75e6853ec)
ALTER TABLE KSLU_CLU_ACCRED ADD CONSTRAINT KSLU_CLU_ACCREDP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-324', '2.0.4-SNP', '3:f859c9c3b1e5441da6f27eb75e6853ec', 324);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-325::Jeff Caddel (generated)::(Checksum: 3:f35cb20fcda60a8e14eb06db8fcf91d5)
ALTER TABLE KSLU_CLU_ACCRED_ATTR ADD CONSTRAINT KSLU_CLU_ACCRED_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-325', '2.0.4-SNP', '3:f35cb20fcda60a8e14eb06db8fcf91d5', 325);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-326::Jeff Caddel (generated)::(Checksum: 3:1d4f3906f739822351a60d0b42950890)
ALTER TABLE KSLU_CLU_ACCT ADD CONSTRAINT KSLU_CLU_ACCTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-326', '2.0.4-SNP', '3:1d4f3906f739822351a60d0b42950890', 326);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-327::Jeff Caddel (generated)::(Checksum: 3:6f5e0ccf14f5aeb8dca13e4e70f1f5a0)
ALTER TABLE KSLU_CLU_ACCT_ATTR ADD CONSTRAINT KSLU_CLU_ACCT_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-327', '2.0.4-SNP', '3:6f5e0ccf14f5aeb8dca13e4e70f1f5a0', 327);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-328::Jeff Caddel (generated)::(Checksum: 3:4334bdee1d1840cc75d45f05453e073b)
ALTER TABLE KSLU_CLU_ADMIN_ORG ADD CONSTRAINT KSLU_CLU_ADMIN_ORGP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-328', '2.0.4-SNP', '3:4334bdee1d1840cc75d45f05453e073b', 328);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-329::Jeff Caddel (generated)::(Checksum: 3:82e2ab9bc99ce4b2d85eaf65acde5f7f)
ALTER TABLE KSLU_CLU_ADMIN_ORG_ATTR ADD CONSTRAINT KSLU_CLU_ADMIN_ORG_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-329', '2.0.4-SNP', '3:82e2ab9bc99ce4b2d85eaf65acde5f7f', 329);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-330::Jeff Caddel (generated)::(Checksum: 3:2c9002dbbf5afdbdcfb72032afe87697)
ALTER TABLE KSLU_CLU_AFFIL_ORG ADD CONSTRAINT KSLU_CLU_AFFIL_ORGP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-330', '2.0.4-SNP', '3:2c9002dbbf5afdbdcfb72032afe87697', 330);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-331::Jeff Caddel (generated)::(Checksum: 3:4b8d706344f8063e493d9045b20b803a)
ALTER TABLE KSLU_CLU_ATP_TYPE_KEY ADD CONSTRAINT KSLU_CLU_ATP_TYPE_KEYP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-331', '2.0.4-SNP', '3:4b8d706344f8063e493d9045b20b803a', 331);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-332::Jeff Caddel (generated)::(Checksum: 3:bc31ccd2af6f277f71f3cc29a594253f)
ALTER TABLE KSLU_CLU_ATTR ADD CONSTRAINT KSLU_CLU_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-332', '2.0.4-SNP', '3:bc31ccd2af6f277f71f3cc29a594253f', 332);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-333::Jeff Caddel (generated)::(Checksum: 3:df8d04b523ccca2b10a8fba190b37e31)
ALTER TABLE KSLU_CLU_CR ADD CONSTRAINT KSLU_CLU_CRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-333', '2.0.4-SNP', '3:df8d04b523ccca2b10a8fba190b37e31', 333);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-334::Jeff Caddel (generated)::(Checksum: 3:a8f819efb80650829d1cfbbeac5d355e)
ALTER TABLE KSLU_CLU_FEE ADD CONSTRAINT KSLU_CLU_FEEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-334', '2.0.4-SNP', '3:a8f819efb80650829d1cfbbeac5d355e', 334);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-335::Jeff Caddel (generated)::(Checksum: 3:2208628cadb6e125a68f5f8370f96910)
ALTER TABLE KSLU_CLU_FEE_AMOUNT ADD CONSTRAINT KSLU_CLU_FEE_AMOUNTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-335', '2.0.4-SNP', '3:2208628cadb6e125a68f5f8370f96910', 335);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-336::Jeff Caddel (generated)::(Checksum: 3:164957b02bf69d78b2ba1506cb1512b3)
ALTER TABLE KSLU_CLU_FEE_ATTR ADD CONSTRAINT KSLU_CLU_FEE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-336', '2.0.4-SNP', '3:164957b02bf69d78b2ba1506cb1512b3', 336);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-337::Jeff Caddel (generated)::(Checksum: 3:054c64019402d1a2cd136348f37147d2)
ALTER TABLE KSLU_CLU_FEE_REC ADD CONSTRAINT KSLU_CLU_FEE_RECP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-337', '2.0.4-SNP', '3:054c64019402d1a2cd136348f37147d2', 337);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-338::Jeff Caddel (generated)::(Checksum: 3:ca03c5c12be247fa88fe9a65628300f1)
ALTER TABLE KSLU_CLU_FEE_REC_ATTR ADD CONSTRAINT KSLU_CLU_FEE_REC_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-338', '2.0.4-SNP', '3:ca03c5c12be247fa88fe9a65628300f1', 338);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-339::Jeff Caddel (generated)::(Checksum: 3:071b54b1ee9bd139a56d30fe83f89c83)
ALTER TABLE KSLU_CLU_IDENT ADD CONSTRAINT KSLU_CLU_IDENTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-339', '2.0.4-SNP', '3:071b54b1ee9bd139a56d30fe83f89c83', 339);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-340::Jeff Caddel (generated)::(Checksum: 3:9c3e32c7fcb063162f4aa7a5f3f85ac7)
ALTER TABLE KSLU_CLU_INSTR ADD CONSTRAINT KSLU_CLU_INSTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-340', '2.0.4-SNP', '3:9c3e32c7fcb063162f4aa7a5f3f85ac7', 340);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-341::Jeff Caddel (generated)::(Checksum: 3:91ad3f562275e10ce2a6ce715100f2da)
ALTER TABLE KSLU_CLU_INSTR_ATTR ADD CONSTRAINT KSLU_CLU_INSTR_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-341', '2.0.4-SNP', '3:91ad3f562275e10ce2a6ce715100f2da', 341);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-342::Jeff Caddel (generated)::(Checksum: 3:0c6c145154daa0cbf93a2e938dadefd6)
ALTER TABLE KSLU_CLU_JN_CAMP_LOC ADD CONSTRAINT KSLU_CLU_JN_CAMP_LOCP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-342', '2.0.4-SNP', '3:0c6c145154daa0cbf93a2e938dadefd6', 342);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-343::Jeff Caddel (generated)::(Checksum: 3:c474f585ce3afb6203ec88f8cea3ef51)
ALTER TABLE KSLU_CLU_JN_SUBJ_ORG ADD CONSTRAINT KSLU_CLU_JN_SUBJ_ORGP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-343', '2.0.4-SNP', '3:c474f585ce3afb6203ec88f8cea3ef51', 343);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-344::Jeff Caddel (generated)::(Checksum: 3:88db5f71a950f61b95e4f31953c9cf0b)
ALTER TABLE KSLU_CLU_LO_ALOW_RELTN_TYPE ADD CONSTRAINT KSLU_CLU_LO_ALOW_RELTN_TYPEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-344', '2.0.4-SNP', '3:88db5f71a950f61b95e4f31953c9cf0b', 344);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-345::Jeff Caddel (generated)::(Checksum: 3:e0e80b9809d607671f321cebec7c0439)
ALTER TABLE KSLU_CLU_LO_RELTN ADD CONSTRAINT KSLU_CLU_LO_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-345', '2.0.4-SNP', '3:e0e80b9809d607671f321cebec7c0439', 345);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-346::Jeff Caddel (generated)::(Checksum: 3:d5fe75cf6cc3f014cf8fe17d9160afe9)
ALTER TABLE KSLU_CLU_LO_RELTN_ATTR ADD CONSTRAINT KSLU_CLU_LO_RELTN_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-346', '2.0.4-SNP', '3:d5fe75cf6cc3f014cf8fe17d9160afe9', 346);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-347::Jeff Caddel (generated)::(Checksum: 3:060cda1ff31a5c238ea6c9512641ef2c)
ALTER TABLE KSLU_CLU_LO_RELTN_TYPE ADD CONSTRAINT KSLU_CLU_LO_RELTN_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-347', '2.0.4-SNP', '3:060cda1ff31a5c238ea6c9512641ef2c', 347);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-348::Jeff Caddel (generated)::(Checksum: 3:eb5d6b629ff85801f574258b50369c33)
ALTER TABLE KSLU_CLU_LO_RELTN_TYPE_ATTR ADD CONSTRAINT KSLU_CLU_LO_RELTN_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-348', '2.0.4-SNP', '3:eb5d6b629ff85801f574258b50369c33', 348);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-349::Jeff Caddel (generated)::(Checksum: 3:048c8c50e196b047cb7a919e95b1ee62)
ALTER TABLE KSLU_CLU_PUBL ADD CONSTRAINT KSLU_CLU_PUBLP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-349', '2.0.4-SNP', '3:048c8c50e196b047cb7a919e95b1ee62', 349);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-350::Jeff Caddel (generated)::(Checksum: 3:94e6981b7d6b7bc6ad7f705b2a49ae9e)
ALTER TABLE KSLU_CLU_PUBL_ATTR ADD CONSTRAINT KSLU_CLU_PUBL_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-350', '2.0.4-SNP', '3:94e6981b7d6b7bc6ad7f705b2a49ae9e', 350);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-351::Jeff Caddel (generated)::(Checksum: 3:8b808cb9be025043176867593b4bfcd6)
ALTER TABLE KSLU_CLU_PUBL_TYPE ADD CONSTRAINT KSLU_CLU_PUBL_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-351', '2.0.4-SNP', '3:8b808cb9be025043176867593b4bfcd6', 351);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-352::Jeff Caddel (generated)::(Checksum: 3:b88489c40947db0851adc86d8b90453f)
ALTER TABLE KSLU_CLU_PUBL_TYPE_ATTR ADD CONSTRAINT KSLU_CLU_PUBL_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-352', '2.0.4-SNP', '3:b88489c40947db0851adc86d8b90453f', 352);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-353::Jeff Caddel (generated)::(Checksum: 3:5cdc9466ed5dbec3c28889e1dd4d8452)
ALTER TABLE KSLU_CLU_PUBL_VARI ADD CONSTRAINT KSLU_CLU_PUBL_VARIP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-353', '2.0.4-SNP', '3:5cdc9466ed5dbec3c28889e1dd4d8452', 353);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-354::Jeff Caddel (generated)::(Checksum: 3:4843043dc7b50092663c921bae69d1b6)
ALTER TABLE KSLU_CLU_PUB_TYPE ADD CONSTRAINT KSLU_CLU_PUB_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-354', '2.0.4-SNP', '3:4843043dc7b50092663c921bae69d1b6', 354);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-355::Jeff Caddel (generated)::(Checksum: 3:6cd327aa500ea64b19319c799e955e3e)
ALTER TABLE KSLU_CLU_PUB_TYPE_ATTR ADD CONSTRAINT KSLU_CLU_PUB_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-355', '2.0.4-SNP', '3:6cd327aa500ea64b19319c799e955e3e', 355);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-356::Jeff Caddel (generated)::(Checksum: 3:9028c1dd666bf6bdf7d784b02d96921c)
ALTER TABLE KSLU_CLU_RESULT_TYPE_ATTR ADD CONSTRAINT KSLU_CLU_RESULT_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-356', '2.0.4-SNP', '3:9028c1dd666bf6bdf7d784b02d96921c', 356);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-357::Jeff Caddel (generated)::(Checksum: 3:71c2455d21785c68b3ee9c8e41ff9615)
ALTER TABLE KSLU_CLU_RSLT ADD CONSTRAINT KSLU_CLU_RSLTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-357', '2.0.4-SNP', '3:71c2455d21785c68b3ee9c8e41ff9615', 357);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-358::Jeff Caddel (generated)::(Checksum: 3:84d52ac495331b6ea5a9037fe0245620)
ALTER TABLE KSLU_CLU_RSLT_LU_ALOW_TYPE ADD CONSTRAINT KSLU_CLU_RSLT_LU_ALOW_TYPEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-358', '2.0.4-SNP', '3:84d52ac495331b6ea5a9037fe0245620', 358);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-359::Jeff Caddel (generated)::(Checksum: 3:df33e8eaacdb242e6307de7c99012947)
ALTER TABLE KSLU_CLU_RSLT_TYP ADD CONSTRAINT KSLU_CLU_RSLT_TYPP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-359', '2.0.4-SNP', '3:df33e8eaacdb242e6307de7c99012947', 359);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-360::Jeff Caddel (generated)::(Checksum: 3:154b1cb7a877cd35b5afcde4788ad2e1)
ALTER TABLE KSLU_CLU_SET ADD CONSTRAINT KSLU_CLU_SETP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-360', '2.0.4-SNP', '3:154b1cb7a877cd35b5afcde4788ad2e1', 360);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-361::Jeff Caddel (generated)::(Checksum: 3:40b7022f8f2b872a5e4547cb83ed8cb3)
ALTER TABLE KSLU_CLU_SET_ATTR ADD CONSTRAINT KSLU_CLU_SET_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-361', '2.0.4-SNP', '3:40b7022f8f2b872a5e4547cb83ed8cb3', 361);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-362::Jeff Caddel (generated)::(Checksum: 3:0bbd398504d7f42d99773c2cc3843432)
ALTER TABLE KSLU_CLU_SET_JN_CLU ADD CONSTRAINT KSLU_CLU_SET_JN_CLUP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-362', '2.0.4-SNP', '3:0bbd398504d7f42d99773c2cc3843432', 362);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-363::Jeff Caddel (generated)::(Checksum: 3:dc239096eaeb62dc9472388066712da5)
ALTER TABLE KSLU_CLU_SET_TYPE ADD CONSTRAINT KSLU_CLU_SET_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-363', '2.0.4-SNP', '3:dc239096eaeb62dc9472388066712da5', 363);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-364::Jeff Caddel (generated)::(Checksum: 3:65e3bb91e822a2633c9af871008ce82d)
ALTER TABLE KSLU_CLU_SET_TYPE_ATTR ADD CONSTRAINT KSLU_CLU_SET_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-364', '2.0.4-SNP', '3:65e3bb91e822a2633c9af871008ce82d', 364);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-365::Jeff Caddel (generated)::(Checksum: 3:891b83f95f90ebdba063d5c369ea328c)
ALTER TABLE KSLU_DLVMTHD_TYPE ADD CONSTRAINT KSLU_DLVMTHD_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-365', '2.0.4-SNP', '3:891b83f95f90ebdba063d5c369ea328c', 365);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-366::Jeff Caddel (generated)::(Checksum: 3:c4a39ad6156c4b03a8b1701646fd95b2)
ALTER TABLE KSLU_DLVMTHD_TYPE_ATTR ADD CONSTRAINT KSLU_DLVMTHD_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-366', '2.0.4-SNP', '3:c4a39ad6156c4b03a8b1701646fd95b2', 366);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-367::Jeff Caddel (generated)::(Checksum: 3:ac6b43b788a08fa2b3a3934d3014664e)
ALTER TABLE KSLU_INSTFRMT_TYPE ADD CONSTRAINT KSLU_INSTFRMT_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-367', '2.0.4-SNP', '3:ac6b43b788a08fa2b3a3934d3014664e', 367);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-368::Jeff Caddel (generated)::(Checksum: 3:dfc3977b729fbddd9e4cd857e1f197e6)
ALTER TABLE KSLU_INSTFRMT_TYPE_ATTR ADD CONSTRAINT KSLU_INSTFRMT_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-368', '2.0.4-SNP', '3:dfc3977b729fbddd9e4cd857e1f197e6', 368);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-369::Jeff Caddel (generated)::(Checksum: 3:9fe68a45ff3e779e74d7c7b7d78407f5)
ALTER TABLE KSLU_LUI ADD CONSTRAINT KSLU_LUIP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-369', '2.0.4-SNP', '3:9fe68a45ff3e779e74d7c7b7d78407f5', 369);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-370::Jeff Caddel (generated)::(Checksum: 3:ec4a74e10761f15efaf0ab13cbcb9b50)
ALTER TABLE KSLU_LUILUI_RELTN ADD CONSTRAINT KSLU_LUILUI_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-370', '2.0.4-SNP', '3:ec4a74e10761f15efaf0ab13cbcb9b50', 370);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-371::Jeff Caddel (generated)::(Checksum: 3:b58f5896eb030ad5aee0acddd2719152)
ALTER TABLE KSLU_LUILUI_RELTN_ATTR ADD CONSTRAINT KSLU_LUILUI_RELTN_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-371', '2.0.4-SNP', '3:b58f5896eb030ad5aee0acddd2719152', 371);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-372::Jeff Caddel (generated)::(Checksum: 3:a11e073241dc2ae3b8b8f6040d3c8965)
ALTER TABLE KSLU_LUI_ATTR ADD CONSTRAINT KSLU_LUI_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-372', '2.0.4-SNP', '3:a11e073241dc2ae3b8b8f6040d3c8965', 372);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-373::Jeff Caddel (generated)::(Checksum: 3:a2f93c64b85c1e2cefbaa531b4c4cf84)
ALTER TABLE KSLU_LULU_RELTN_TYPE ADD CONSTRAINT KSLU_LULU_RELTN_TYPEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-373', '2.0.4-SNP', '3:a2f93c64b85c1e2cefbaa531b4c4cf84', 373);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-374::Jeff Caddel (generated)::(Checksum: 3:4196af1e79651cf0bd34d65a1d9b441b)
ALTER TABLE KSLU_LULU_RELTN_TYPE_ATTR ADD CONSTRAINT KSLU_LULU_RELTN_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-374', '2.0.4-SNP', '3:4196af1e79651cf0bd34d65a1d9b441b', 374);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-375::Jeff Caddel (generated)::(Checksum: 3:e35153b4f39a031772dd0dc02345ded6)
ALTER TABLE KSLU_LUTYPE ADD CONSTRAINT KSLU_LUTYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-375', '2.0.4-SNP', '3:e35153b4f39a031772dd0dc02345ded6', 375);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-376::Jeff Caddel (generated)::(Checksum: 3:8cfd0e76239877466ae173be97e9ce95)
ALTER TABLE KSLU_LU_CD_TYPE ADD CONSTRAINT KSLU_LU_CD_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-376', '2.0.4-SNP', '3:8cfd0e76239877466ae173be97e9ce95', 376);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-377::Jeff Caddel (generated)::(Checksum: 3:f6b8ba7de89fb56d9b8edc818dd54f28)
ALTER TABLE KSLU_LU_CD_TYPE_ATTR ADD CONSTRAINT KSLU_LU_CD_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-377', '2.0.4-SNP', '3:f6b8ba7de89fb56d9b8edc818dd54f28', 377);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-378::Jeff Caddel (generated)::(Checksum: 3:5c3b923827efabd5480c3f3c50a0b220)
ALTER TABLE KSLU_LU_CODE ADD CONSTRAINT KSLU_LU_CODEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-378', '2.0.4-SNP', '3:5c3b923827efabd5480c3f3c50a0b220', 378);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-379::Jeff Caddel (generated)::(Checksum: 3:21ac2525f02c8a0fa3e4616d9671b8c1)
ALTER TABLE KSLU_LU_CODE_ATTR ADD CONSTRAINT KSLU_LU_CODE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-379', '2.0.4-SNP', '3:21ac2525f02c8a0fa3e4616d9671b8c1', 379);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-380::Jeff Caddel (generated)::(Checksum: 3:b7f75f605a21b8832f7d8c306034a4b4)
ALTER TABLE KSLU_LU_LU_ALOW_RELTN_TYPE ADD CONSTRAINT KSLU_LU_LU_ALOW_RELTN_TYPEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-380', '2.0.4-SNP', '3:b7f75f605a21b8832f7d8c306034a4b4', 380);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-381::Jeff Caddel (generated)::(Checksum: 3:0a7a721ba2bcd882bb0ce6b59f636d3e)
ALTER TABLE KSLU_LU_PUBL_TYPE ADD CONSTRAINT KSLU_LU_PUBL_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-381', '2.0.4-SNP', '3:0a7a721ba2bcd882bb0ce6b59f636d3e', 381);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-382::Jeff Caddel (generated)::(Checksum: 3:7905d2be18d94ab4b4f1d76227cbbbaa)
ALTER TABLE KSLU_LU_PUBL_TYPE_ATTR ADD CONSTRAINT KSLU_LU_PUBL_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-382', '2.0.4-SNP', '3:7905d2be18d94ab4b4f1d76227cbbbaa', 382);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-383::Jeff Caddel (generated)::(Checksum: 3:310835db1e5655553bf8d7538591d157)
ALTER TABLE KSLU_LU_TYPE_ATTR ADD CONSTRAINT KSLU_LU_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-383', '2.0.4-SNP', '3:310835db1e5655553bf8d7538591d157', 383);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-384::Jeff Caddel (generated)::(Checksum: 3:3b1787d2984e0e3c97ac5363ff8061d2)
ALTER TABLE KSLU_MEMSHIP ADD CONSTRAINT KSLU_MEMSHIPP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-384', '2.0.4-SNP', '3:3b1787d2984e0e3c97ac5363ff8061d2', 384);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-385::Jeff Caddel (generated)::(Checksum: 3:d001f72a0b11b892b6ca7552bf398f40)
ALTER TABLE KSLU_RICH_TEXT_T ADD CONSTRAINT KSLU_RICH_TEXT_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-385', '2.0.4-SNP', '3:d001f72a0b11b892b6ca7552bf398f40', 385);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-386::Jeff Caddel (generated)::(Checksum: 3:a8b74c3ff717d7fcba614b787705ba7f)
ALTER TABLE KSLU_RSLTUSAGE_LU_ALOW_TYPE ADD CONSTRAINT KSLU_RSLTUSAGE_LU_ALOW_TYPEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-386', '2.0.4-SNP', '3:a8b74c3ff717d7fcba614b787705ba7f', 386);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-387::Jeff Caddel (generated)::(Checksum: 3:5e35185e26a0099804917c0d551ce589)
ALTER TABLE KSLU_RSLT_COMP_USG_ALOW_TYPE ADD CONSTRAINT KSLU_RSLT_COMP_USG_ALOW_TYPP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-387', '2.0.4-SNP', '3:5e35185e26a0099804917c0d551ce589', 387);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-388::Jeff Caddel (generated)::(Checksum: 3:ed96fcffa1eeefa509baa53c3b795f28)
ALTER TABLE KSLU_RSLT_OPT ADD CONSTRAINT KSLU_RSLT_OPTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-388', '2.0.4-SNP', '3:ed96fcffa1eeefa509baa53c3b795f28', 388);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-389::Jeff Caddel (generated)::(Checksum: 3:887e17b68f2306a7db670faf69b6b8c5)
ALTER TABLE KSLU_RSLT_USG_TYPE ADD CONSTRAINT KSLU_RSLT_USG_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-389', '2.0.4-SNP', '3:887e17b68f2306a7db670faf69b6b8c5', 389);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-390::Jeff Caddel (generated)::(Checksum: 3:38a54ed4dee3f8d3770de3a652c48767)
ALTER TABLE KSLU_RSLT_USG_TYPE_ATTR ADD CONSTRAINT KSLU_RSLT_USG_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-390', '2.0.4-SNP', '3:38a54ed4dee3f8d3770de3a652c48767', 390);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-391::Jeff Caddel (generated)::(Checksum: 3:1339793f344a0d64cd2f4485498c02e7)
ALTER TABLE KSLU_RSRC ADD CONSTRAINT KSLU_RSRCP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-391', '2.0.4-SNP', '3:1339793f344a0d64cd2f4485498c02e7', 391);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-392::Jeff Caddel (generated)::(Checksum: 3:c511250c10242fa7fe0f0d558528e796)
ALTER TABLE KSLU_SPARAM ADD CONSTRAINT KSLU_SPARAMP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-392', '2.0.4-SNP', '3:c511250c10242fa7fe0f0d558528e796', 392);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-393::Jeff Caddel (generated)::(Checksum: 3:7e71e4c326afb0d9a6d1cf71cf5ddcdd)
ALTER TABLE KSLU_SPVALUE ADD CONSTRAINT KSLU_SPVALUEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-393', '2.0.4-SNP', '3:7e71e4c326afb0d9a6d1cf71cf5ddcdd', 393);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-394::Jeff Caddel (generated)::(Checksum: 3:723c67432df16bcfb8282d03d82e7341)
ALTER TABLE KSMG_MESSAGE ADD CONSTRAINT KSMG_MESSAGEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-394', '2.0.4-SNP', '3:723c67432df16bcfb8282d03d82e7341', 394);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-395::Jeff Caddel (generated)::(Checksum: 3:2b887ba8a0e87b0e22578eb76b5f1e08)
ALTER TABLE KSOR_ORG ADD CONSTRAINT KSOR_ORGP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-395', '2.0.4-SNP', '3:2b887ba8a0e87b0e22578eb76b5f1e08', 395);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-396::Jeff Caddel (generated)::(Checksum: 3:3a9655a9ab00e6b0ab8b95d98592b952)
ALTER TABLE KSOR_ORG_ATTR ADD CONSTRAINT KSOR_ORG_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-396', '2.0.4-SNP', '3:3a9655a9ab00e6b0ab8b95d98592b952', 396);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-397::Jeff Caddel (generated)::(Checksum: 3:8a6621466707c7d744faf5abff8261e5)
ALTER TABLE KSOR_ORG_HIRCHY ADD CONSTRAINT KSOR_ORG_HIRCHYP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-397', '2.0.4-SNP', '3:8a6621466707c7d744faf5abff8261e5', 397);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-398::Jeff Caddel (generated)::(Checksum: 3:9298863b9a19146b78a2e5802e9e8f2e)
ALTER TABLE KSOR_ORG_HIRCHY_ATTR ADD CONSTRAINT KSOR_ORG_HIRCHY_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-398', '2.0.4-SNP', '3:9298863b9a19146b78a2e5802e9e8f2e', 398);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-399::Jeff Caddel (generated)::(Checksum: 3:55af8aaace39b72c8dc764aaeb2229ff)
ALTER TABLE KSOR_ORG_ORG_RELTN ADD CONSTRAINT KSOR_ORG_ORG_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-399', '2.0.4-SNP', '3:55af8aaace39b72c8dc764aaeb2229ff', 399);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-400::Jeff Caddel (generated)::(Checksum: 3:530573bb1dbe382c22f89bb14ae57f9d)
ALTER TABLE KSOR_ORG_ORG_RELTN_ATTR ADD CONSTRAINT KSOR_ORG_ORG_RELTN_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-400', '2.0.4-SNP', '3:530573bb1dbe382c22f89bb14ae57f9d', 400);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-401::Jeff Caddel (generated)::(Checksum: 3:9900b7ba2749bc4e1fc023b17cab5eaf)
ALTER TABLE KSOR_ORG_ORG_RELTN_TYPE ADD CONSTRAINT KSOR_ORG_ORG_RELTN_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-401', '2.0.4-SNP', '3:9900b7ba2749bc4e1fc023b17cab5eaf', 401);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-402::Jeff Caddel (generated)::(Checksum: 3:25dd58b22434c7e10ead4c5b65790f01)
ALTER TABLE KSOR_ORG_ORG_RELTN_TYPE_ATTR ADD CONSTRAINT KSOR_ORG_ORG_RELTN_TYPE_ATTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-402', '2.0.4-SNP', '3:25dd58b22434c7e10ead4c5b65790f01', 402);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-403::Jeff Caddel (generated)::(Checksum: 3:8ca83e57dda679959f34b712e41cc4dc)
ALTER TABLE KSOR_ORG_PERS_RELTN ADD CONSTRAINT KSOR_ORG_PERS_RELTNP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-403', '2.0.4-SNP', '3:8ca83e57dda679959f34b712e41cc4dc', 403);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-404::Jeff Caddel (generated)::(Checksum: 3:6f1ec76a7a3067f87c0cadea77258629)
ALTER TABLE KSOR_ORG_PERS_RELTN_ATTR ADD CONSTRAINT KSOR_ORG_PERS_RELTN_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-404', '2.0.4-SNP', '3:6f1ec76a7a3067f87c0cadea77258629', 404);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-405::Jeff Caddel (generated)::(Checksum: 3:5752aad8e1572d1707e51ae37e73e51b)
ALTER TABLE KSOR_ORG_PERS_RELTN_TYPE ADD CONSTRAINT KSOR_ORG_PERS_RELTN_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-405', '2.0.4-SNP', '3:5752aad8e1572d1707e51ae37e73e51b', 405);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-406::Jeff Caddel (generated)::(Checksum: 3:07df16159fd226f6f39ed7bc1716790e)
ALTER TABLE KSOR_ORG_PERS_RELTN_TYPE_ATTR ADD CONSTRAINT KSOR_ORG_PERS_RELTN_TYPE_ATP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-406', '2.0.4-SNP', '3:07df16159fd226f6f39ed7bc1716790e', 406);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-407::Jeff Caddel (generated)::(Checksum: 3:300fa91a2a51a160c0c851e55cee9305)
ALTER TABLE KSOR_ORG_POS_RESTR ADD CONSTRAINT KSOR_ORG_POS_RESTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-407', '2.0.4-SNP', '3:300fa91a2a51a160c0c851e55cee9305', 407);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-408::Jeff Caddel (generated)::(Checksum: 3:a610dbd84b879cf202f819f31a5307e7)
ALTER TABLE KSOR_ORG_POS_RESTR_ATTR ADD CONSTRAINT KSOR_ORG_POS_RESTR_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-408', '2.0.4-SNP', '3:a610dbd84b879cf202f819f31a5307e7', 408);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-409::Jeff Caddel (generated)::(Checksum: 3:f778f7928f6618fdda2355d9ca9a05a7)
ALTER TABLE KSOR_ORG_TYPE ADD CONSTRAINT KSOR_ORG_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-409', '2.0.4-SNP', '3:f778f7928f6618fdda2355d9ca9a05a7', 409);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-410::Jeff Caddel (generated)::(Checksum: 3:20626756c12223fce4bd3def7709c698)
ALTER TABLE KSOR_ORG_TYPE_ATTR ADD CONSTRAINT KSOR_ORG_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-410', '2.0.4-SNP', '3:20626756c12223fce4bd3def7709c698', 410);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-411::Jeff Caddel (generated)::(Checksum: 3:82e8fa9f0f81827f0a9d6b16e02c85ac)
ALTER TABLE KSPR_PROPOSAL ADD CONSTRAINT KSPR_PROPOSALP1 PRIMARY KEY (PROPOSAL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-411', '2.0.4-SNP', '3:82e8fa9f0f81827f0a9d6b16e02c85ac', 411);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-412::Jeff Caddel (generated)::(Checksum: 3:00da5af15d835e22fd3c0e668188543d)
ALTER TABLE KSPR_PROPOSAL_ATTR ADD CONSTRAINT KSPR_PROPOSAL_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-412', '2.0.4-SNP', '3:00da5af15d835e22fd3c0e668188543d', 412);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-413::Jeff Caddel (generated)::(Checksum: 3:4ea8edd7830dceb3b8d1ea5170551f35)
ALTER TABLE KSPR_PROPOSAL_JN_ORG ADD CONSTRAINT KSPR_PROPOSAL_JN_ORGP1 PRIMARY KEY (ORGREF_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-413', '2.0.4-SNP', '3:4ea8edd7830dceb3b8d1ea5170551f35', 413);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-414::Jeff Caddel (generated)::(Checksum: 3:b9622d00dc9e3cfa040f0ef062225e03)
ALTER TABLE KSPR_PROPOSAL_JN_PERSON ADD CONSTRAINT KSPR_PROPOSAL_JN_PERSONP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-414', '2.0.4-SNP', '3:b9622d00dc9e3cfa040f0ef062225e03', 414);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-415::Jeff Caddel (generated)::(Checksum: 3:1784ce39b677f9e071f8a633d3501311)
ALTER TABLE KSPR_PROPOSAL_REFERENCE ADD CONSTRAINT KSPR_PROPOSAL_REFERENCEP1 PRIMARY KEY (REFERENCE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-415', '2.0.4-SNP', '3:1784ce39b677f9e071f8a633d3501311', 415);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-416::Jeff Caddel (generated)::(Checksum: 3:a0db43dc0a50f8146b2a6b9cb9fa4679)
ALTER TABLE KSPR_PROPOSAL_REFTYPE ADD CONSTRAINT KSPR_PROPOSAL_REFTYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-416', '2.0.4-SNP', '3:a0db43dc0a50f8146b2a6b9cb9fa4679', 416);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-417::Jeff Caddel (generated)::(Checksum: 3:35ca0c4b49f3b5e1c4a8ba82423982c5)
ALTER TABLE KSPR_PROPOSAL_REFTYPE_ATTR ADD CONSTRAINT KSPR_PROPOSAL_REFTYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-417', '2.0.4-SNP', '3:35ca0c4b49f3b5e1c4a8ba82423982c5', 417);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-418::Jeff Caddel (generated)::(Checksum: 3:76f9acb75f53194013029410a5f03760)
ALTER TABLE KSPR_PROPOSAL_TYPE ADD CONSTRAINT KSPR_PROPOSAL_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-418', '2.0.4-SNP', '3:76f9acb75f53194013029410a5f03760', 418);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-419::Jeff Caddel (generated)::(Checksum: 3:7376937751bf0ae6a8fb4856259a7b80)
ALTER TABLE KSPR_PROPOSAL_TYPE_ATTR ADD CONSTRAINT KSPR_PROPOSAL_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-419', '2.0.4-SNP', '3:7376937751bf0ae6a8fb4856259a7b80', 419);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-420::Jeff Caddel (generated)::(Checksum: 3:ef7a55ac11e8b0d8c4f8a3213c1a1425)
ALTER TABLE KSPR_RICH_TEXT_T ADD CONSTRAINT KSPR_RICH_TEXT_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-420', '2.0.4-SNP', '3:ef7a55ac11e8b0d8c4f8a3213c1a1425', 420);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-421::Jeff Caddel (generated)::(Checksum: 3:8ab42b583ee67d8e92496317b28fd457)
ALTER TABLE KSST_NL_USAGE_TYPE ADD CONSTRAINT KSST_NL_USAGE_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-421', '2.0.4-SNP', '3:8ab42b583ee67d8e92496317b28fd457', 421);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-422::Jeff Caddel (generated)::(Checksum: 3:4289c8ea6803830e862ac002a38c87a3)
ALTER TABLE KSST_OBJECT_SUB_TYPE ADD CONSTRAINT KSST_OBJECT_SUB_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-422', '2.0.4-SNP', '3:4289c8ea6803830e862ac002a38c87a3', 422);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-423::Jeff Caddel (generated)::(Checksum: 3:70ec22f4a056224bad54dbe039fdbb6f)
ALTER TABLE KSST_OBJECT_SUB_TYPE_ATTR ADD CONSTRAINT KSST_OBJECT_SUB_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-423', '2.0.4-SNP', '3:70ec22f4a056224bad54dbe039fdbb6f', 423);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-424::Jeff Caddel (generated)::(Checksum: 3:750c4c3aac1cf79e70b7927942ad2579)
ALTER TABLE KSST_OBJECT_TYPE ADD CONSTRAINT KSST_OBJECT_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-424', '2.0.4-SNP', '3:750c4c3aac1cf79e70b7927942ad2579', 424);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-425::Jeff Caddel (generated)::(Checksum: 3:8d3e4bcc4b11dbc37bba05e137693f41)
ALTER TABLE KSST_OBJECT_TYPE_ATTR ADD CONSTRAINT KSST_OBJECT_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-425', '2.0.4-SNP', '3:8d3e4bcc4b11dbc37bba05e137693f41', 425);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-426::Jeff Caddel (generated)::(Checksum: 3:bde1ddde7c5d2774da80c25ed0251147)
ALTER TABLE KSST_REF_STMT_REL ADD CONSTRAINT KSST_REF_STMT_RELP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-426', '2.0.4-SNP', '3:bde1ddde7c5d2774da80c25ed0251147', 426);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-427::Jeff Caddel (generated)::(Checksum: 3:b421bbf1402ae4cbfbbbe01789abe7b3)
ALTER TABLE KSST_REF_STMT_REL_ATTR ADD CONSTRAINT KSST_REF_STMT_REL_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-427', '2.0.4-SNP', '3:b421bbf1402ae4cbfbbbe01789abe7b3', 427);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-428::Jeff Caddel (generated)::(Checksum: 3:88fb24588ffeb9b40e92a33d3ecb919b)
ALTER TABLE KSST_REF_STMT_REL_TYPE ADD CONSTRAINT KSST_REF_STMT_REL_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-428', '2.0.4-SNP', '3:88fb24588ffeb9b40e92a33d3ecb919b', 428);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-429::Jeff Caddel (generated)::(Checksum: 3:83fc202b2c8fa5b7249db32f9cceb733)
ALTER TABLE KSST_REF_STMT_REL_TYPE_ATTR ADD CONSTRAINT KSST_REF_STMT_REL_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-429', '2.0.4-SNP', '3:83fc202b2c8fa5b7249db32f9cceb733', 429);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-430::Jeff Caddel (generated)::(Checksum: 3:a1adf6685ce4e007cf2944e360dec00a)
ALTER TABLE KSST_REQ_COM ADD CONSTRAINT KSST_REQ_COMP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-430', '2.0.4-SNP', '3:a1adf6685ce4e007cf2944e360dec00a', 430);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-431::Jeff Caddel (generated)::(Checksum: 3:92684f47c00b4255d32367a6648ee985)
ALTER TABLE KSST_REQ_COM_FIELD ADD CONSTRAINT KSST_REQ_COM_FIELDP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-431', '2.0.4-SNP', '3:92684f47c00b4255d32367a6648ee985', 431);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-432::Jeff Caddel (generated)::(Checksum: 3:34e574b99c71c086e41701caf0b8d732)
ALTER TABLE KSST_REQ_COM_FIELD_TYPE ADD CONSTRAINT KSST_REQ_COM_FIELD_TYPEP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-432', '2.0.4-SNP', '3:34e574b99c71c086e41701caf0b8d732', 432);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-433::Jeff Caddel (generated)::(Checksum: 3:7be35a18287be3eb800f4652693452bb)
ALTER TABLE KSST_REQ_COM_TYPE ADD CONSTRAINT KSST_REQ_COM_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-433', '2.0.4-SNP', '3:7be35a18287be3eb800f4652693452bb', 433);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-434::Jeff Caddel (generated)::(Checksum: 3:c7e3c12b510532704864f2710220bfc8)
ALTER TABLE KSST_REQ_COM_TYPE_ATTR ADD CONSTRAINT KSST_REQ_COM_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-434', '2.0.4-SNP', '3:c7e3c12b510532704864f2710220bfc8', 434);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-435::Jeff Caddel (generated)::(Checksum: 3:e27f95adf48dc54cb49dcb293409df72)
ALTER TABLE KSST_REQ_COM_TYPE_NL_TMPL ADD CONSTRAINT KSST_REQ_COM_TYPE_NL_TMPLP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-435', '2.0.4-SNP', '3:e27f95adf48dc54cb49dcb293409df72', 435);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-436::Jeff Caddel (generated)::(Checksum: 3:da82b7f768441d7218a67315a11bd51f)
ALTER TABLE KSST_RICH_TEXT_T ADD CONSTRAINT KSST_RICH_TEXT_TP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-436', '2.0.4-SNP', '3:da82b7f768441d7218a67315a11bd51f', 436);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-437::Jeff Caddel (generated)::(Checksum: 3:00dd7fa5cd40913836892956c622d932)
ALTER TABLE KSST_STMT ADD CONSTRAINT KSST_STMTP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-437', '2.0.4-SNP', '3:00dd7fa5cd40913836892956c622d932', 437);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-438::Jeff Caddel (generated)::(Checksum: 3:f2b79aa9f537278ade2bc9f7120be231)
ALTER TABLE KSST_STMT_ATTR ADD CONSTRAINT KSST_STMT_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-438', '2.0.4-SNP', '3:f2b79aa9f537278ade2bc9f7120be231', 438);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-439::Jeff Caddel (generated)::(Checksum: 3:a22358dc95a89e059d5aa945ea5ad6bb)
ALTER TABLE KSST_STMT_TYPE ADD CONSTRAINT KSST_STMT_TYPEP1 PRIMARY KEY (TYPE_KEY) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-439', '2.0.4-SNP', '3:a22358dc95a89e059d5aa945ea5ad6bb', 439);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-440::Jeff Caddel (generated)::(Checksum: 3:f494b5c8751306b89de343163c68113e)
ALTER TABLE KSST_STMT_TYPE_ATTR ADD CONSTRAINT KSST_STMT_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-440', '2.0.4-SNP', '3:f494b5c8751306b89de343163c68113e', 440);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-441::Jeff Caddel (generated)::(Checksum: 3:08bed09cf14878e7ef673ea04cbb3484)
ALTER TABLE KSST_STMT_TYP_JN_RC_TYP ADD CONSTRAINT KSST_STMT_TYP_JN_RC_TYPP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-441', '2.0.4-SNP', '3:08bed09cf14878e7ef673ea04cbb3484', 441);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-442::Jeff Caddel (generated)::(Checksum: 3:59d5a105a773635b8a5c4af465e4922f)
ALTER TABLE KSST_STMT_TYP_JN_STMT_TYP ADD CONSTRAINT KSST_STMT_TYP_JN_STMT_TYPP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-442', '2.0.4-SNP', '3:59d5a105a773635b8a5c4af465e4922f', 442);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-443::Jeff Caddel (generated)::(Checksum: 3:19b5781ad90397d7f41d791a9674edbc)
ALTER TABLE KSST_USAGE_TYPE_ATTR ADD CONSTRAINT KSST_USAGE_TYPE_ATTRP1 PRIMARY KEY (ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Primary Key', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-443', '2.0.4-SNP', '3:19b5781ad90397d7f41d791a9674edbc', 443);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-444::Jeff Caddel (generated)::(Checksum: 3:9701bce29f7f5aa8edcec8b860ec7690)
ALTER TABLE KRCR_NMSPC_T ADD CONSTRAINT KRNS_NMSPC_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-444', '2.0.4-SNP', '3:9701bce29f7f5aa8edcec8b860ec7690', 444);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-445::Jeff Caddel (generated)::(Checksum: 3:8713a187622b0a37a201eabfa8926d71)
ALTER TABLE KRCR_PARM_DTL_TYP_T ADD CONSTRAINT KRNS_PARM_DTL_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-445', '2.0.4-SNP', '3:8713a187622b0a37a201eabfa8926d71', 445);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-446::Jeff Caddel (generated)::(Checksum: 3:21300f4dc23d76436173d8184fccb1ff)
ALTER TABLE KRCR_PARM_T ADD CONSTRAINT KRNS_PARM_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-446', '2.0.4-SNP', '3:21300f4dc23d76436173d8184fccb1ff', 446);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-447::Jeff Caddel (generated)::(Checksum: 3:77d31f85969f1cd22fef4dbe67b97e59)
ALTER TABLE KRCR_PARM_TYP_T ADD CONSTRAINT KRNS_PARM_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-447', '2.0.4-SNP', '3:77d31f85969f1cd22fef4dbe67b97e59', 447);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-448::Jeff Caddel (generated)::(Checksum: 3:1f313a9db4af523da44ac034d5825ce1)
ALTER TABLE KREN_CHNL_SUBSCRP_T ADD CONSTRAINT KREN_CHNL_SUBSCRP_TC0 UNIQUE (CHNL_ID, PRNCPL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-448', '2.0.4-SNP', '3:1f313a9db4af523da44ac034d5825ce1', 448);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-449::Jeff Caddel (generated)::(Checksum: 3:4e8c52e1940b60fa5cf9011f17eed74a)
ALTER TABLE KREN_CHNL_T ADD CONSTRAINT KREN_CHNL_TC0 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-449', '2.0.4-SNP', '3:4e8c52e1940b60fa5cf9011f17eed74a', 449);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-450::Jeff Caddel (generated)::(Checksum: 3:d9c054b3e98d979e9ee9ae940c9fa439)
ALTER TABLE KREN_CNTNT_TYP_T ADD CONSTRAINT KREN_CNTNT_TYP_TC0 UNIQUE (NM, CNTNT_TYP_VER_NBR) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-450', '2.0.4-SNP', '3:d9c054b3e98d979e9ee9ae940c9fa439', 450);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-451::Jeff Caddel (generated)::(Checksum: 3:2f55230fa3b5886e44a857900097caa1)
ALTER TABLE KREN_MSG_DELIV_T ADD CONSTRAINT KREN_MSG_DELIV_TC0 UNIQUE (MSG_ID, TYP_NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-451', '2.0.4-SNP', '3:2f55230fa3b5886e44a857900097caa1', 451);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-452::Jeff Caddel (generated)::(Checksum: 3:b131a13844dda2f1a2524a3eb8346f39)
ALTER TABLE KREN_MSG_T ADD CONSTRAINT KREN_MSG_TC0 UNIQUE (ORGN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-452', '2.0.4-SNP', '3:b131a13844dda2f1a2524a3eb8346f39', 452);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-453::Jeff Caddel (generated)::(Checksum: 3:25b8a4f56459b4e54be304a7b35e4855)
ALTER TABLE KREN_NTFCTN_MSG_DELIV_T ADD CONSTRAINT KREN_NTFCTN_MSG_DELIV_TC0 UNIQUE (NTFCTN_ID, RECIP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-453', '2.0.4-SNP', '3:25b8a4f56459b4e54be304a7b35e4855', 453);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-454::Jeff Caddel (generated)::(Checksum: 3:e9202c0eebfa59cf6d010447b0c6edb7)
ALTER TABLE KREN_PRIO_T ADD CONSTRAINT KREN_PRIO_TC0 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-454', '2.0.4-SNP', '3:e9202c0eebfa59cf6d010447b0c6edb7', 454);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-455::Jeff Caddel (generated)::(Checksum: 3:841233dd5d97de0f0861465f22b193fd)
ALTER TABLE KREN_PRODCR_T ADD CONSTRAINT KREN_PRODCR_TC0 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-455', '2.0.4-SNP', '3:841233dd5d97de0f0861465f22b193fd', 455);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-456::Jeff Caddel (generated)::(Checksum: 3:80800af4e9bc1bf0d18e033d35fbd35c)
ALTER TABLE KREN_RECIP_LIST_T ADD CONSTRAINT KREN_RECIP_LIST_TC0 UNIQUE (CHNL_ID, RECIP_TYP_CD, RECIP_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-456', '2.0.4-SNP', '3:80800af4e9bc1bf0d18e033d35fbd35c', 456);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-457::Jeff Caddel (generated)::(Checksum: 3:0e27f334883f0680e7668fbf3d0641bb)
ALTER TABLE KREN_RECIP_PREFS_T ADD CONSTRAINT KREN_RECIP_PREFS_TC0 UNIQUE (RECIP_ID, PROP) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-457', '2.0.4-SNP', '3:0e27f334883f0680e7668fbf3d0641bb', 457);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-458::Jeff Caddel (generated)::(Checksum: 3:696ad4c780a48259d6ae1af605b1f181)
ALTER TABLE KREN_RECIP_T ADD CONSTRAINT KREN_RECIP_TC0 UNIQUE (NTFCTN_ID, RECIP_TYP_CD, PRNCPL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-458', '2.0.4-SNP', '3:696ad4c780a48259d6ae1af605b1f181', 458);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-459::Jeff Caddel (generated)::(Checksum: 3:f2fcd89f74cc571a70567a00db5090bd)
ALTER TABLE KREN_RVWER_T ADD CONSTRAINT KREN_RVWER_TC0 UNIQUE (CHNL_ID, TYP, PRNCPL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-459', '2.0.4-SNP', '3:f2fcd89f74cc571a70567a00db5090bd', 459);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-460::Jeff Caddel (generated)::(Checksum: 3:cd0ceb955d0abc875d8162765a9d5020)
ALTER TABLE KREN_SNDR_T ADD CONSTRAINT KREN_SNDR_TC0 UNIQUE (NTFCTN_ID, NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-460', '2.0.4-SNP', '3:cd0ceb955d0abc875d8162765a9d5020', 460);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-461::Jeff Caddel (generated)::(Checksum: 3:e92064c6a1052654ce3befc0c96367bb)
ALTER TABLE KREW_APP_DOC_STAT_TRAN_T ADD CONSTRAINT KREW_APP_DOC_STAT_TRAN_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-461', '2.0.4-SNP', '3:e92064c6a1052654ce3befc0c96367bb', 461);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-462::Jeff Caddel (generated)::(Checksum: 3:400f1fa016bb1108ef4e5e43d247873b)
ALTER TABLE KREW_DLGN_RSP_T ADD CONSTRAINT KREW_DLGN_RSP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-462', '2.0.4-SNP', '3:400f1fa016bb1108ef4e5e43d247873b', 462);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-463::Jeff Caddel (generated)::(Checksum: 3:8d57a5abec4105140d5f21845461ab10)
ALTER TABLE KREW_DOC_HDR_T ADD CONSTRAINT KREW_DOC_HDR_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-463', '2.0.4-SNP', '3:8d57a5abec4105140d5f21845461ab10', 463);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-464::Jeff Caddel (generated)::(Checksum: 3:b75decb127e7cf9eb77f7426eafe7e1a)
ALTER TABLE KREW_DOC_TYP_APP_DOC_STAT_T ADD CONSTRAINT KREW_DOC_TYP_APP_DOC_STAT_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-464', '2.0.4-SNP', '3:b75decb127e7cf9eb77f7426eafe7e1a', 464);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-465::Jeff Caddel (generated)::(Checksum: 3:62b54cf8bbac99beaa5a8af1b4a1fbf9)
ALTER TABLE KREW_DOC_TYP_PLCY_RELN_T ADD CONSTRAINT KREW_DOC_TYP_PLCY_RELN_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-465', '2.0.4-SNP', '3:62b54cf8bbac99beaa5a8af1b4a1fbf9', 465);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-466::Jeff Caddel (generated)::(Checksum: 3:816215cd770724797347457ed05be572)
ALTER TABLE KREW_DOC_TYP_T ADD CONSTRAINT KREW_DOC_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-466', '2.0.4-SNP', '3:816215cd770724797347457ed05be572', 466);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-467::Jeff Caddel (generated)::(Checksum: 3:a49ac30ad9d1c37a72adb251dce0c778)
ALTER TABLE KREW_DOC_TYP_T ADD CONSTRAINT KREW_DOC_TYP_TI1 UNIQUE (DOC_TYP_NM, DOC_TYP_VER_NBR) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-467', '2.0.4-SNP', '3:a49ac30ad9d1c37a72adb251dce0c778', 467);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-468::Jeff Caddel (generated)::(Checksum: 3:cee5b8bb5cca074318ca948cda87c705)
ALTER TABLE KREW_EDL_ASSCTN_T ADD CONSTRAINT KREW_EDL_ASSCTN_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-468', '2.0.4-SNP', '3:cee5b8bb5cca074318ca948cda87c705', 468);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-469::Jeff Caddel (generated)::(Checksum: 3:fa577190a573ce020ad9752ba0d95bf3)
ALTER TABLE KREW_EDL_DEF_T ADD CONSTRAINT KREW_EDL_DEF_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-469', '2.0.4-SNP', '3:fa577190a573ce020ad9752ba0d95bf3', 469);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-470::Jeff Caddel (generated)::(Checksum: 3:f17f90bc38f76c1bcf9a053ff8d60ae6)
ALTER TABLE KREW_RULE_ATTR_T ADD CONSTRAINT KREW_RULE_ATTR_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-470', '2.0.4-SNP', '3:f17f90bc38f76c1bcf9a053ff8d60ae6', 470);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-471::Jeff Caddel (generated)::(Checksum: 3:df3e089829c5ac8425d410d9f5274fed)
ALTER TABLE KREW_RULE_EXPR_T ADD CONSTRAINT KREW_RULE_EXPR_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-471', '2.0.4-SNP', '3:df3e089829c5ac8425d410d9f5274fed', 471);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-472::Jeff Caddel (generated)::(Checksum: 3:023c91558018183ed09db5330552bdd2)
ALTER TABLE KREW_RULE_RSP_T ADD CONSTRAINT KREW_RULE_RSP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-472', '2.0.4-SNP', '3:023c91558018183ed09db5330552bdd2', 472);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-473::Jeff Caddel (generated)::(Checksum: 3:3f4fe5e1798b115449bb405af47dcd2e)
ALTER TABLE KREW_RULE_T ADD CONSTRAINT KREW_RULE_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-473', '2.0.4-SNP', '3:3f4fe5e1798b115449bb405af47dcd2e', 473);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-474::Jeff Caddel (generated)::(Checksum: 3:fa892f29b0d9a13b2ff556b449acc4b4)
ALTER TABLE KREW_RULE_TMPL_ATTR_T ADD CONSTRAINT KREW_RULE_TMPL_ATTR_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-474', '2.0.4-SNP', '3:fa892f29b0d9a13b2ff556b449acc4b4', 474);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-475::Jeff Caddel (generated)::(Checksum: 3:f5b47aabe82d24c61c0de2545f42aa1e)
ALTER TABLE KREW_RULE_TMPL_T ADD CONSTRAINT KREW_RULE_TMPL_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-475', '2.0.4-SNP', '3:f5b47aabe82d24c61c0de2545f42aa1e', 475);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-476::Jeff Caddel (generated)::(Checksum: 3:f1f30435e8fe9215cc82fe060c88a9f0)
ALTER TABLE KREW_RULE_TMPL_T ADD CONSTRAINT KREW_RULE_TMPL_TI1 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-476', '2.0.4-SNP', '3:f1f30435e8fe9215cc82fe060c88a9f0', 476);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-477::Jeff Caddel (generated)::(Checksum: 3:46b1f1076a6a7cb70cddd0f229ba541e)
ALTER TABLE KREW_STYLE_T ADD CONSTRAINT KREW_STYLE_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-477', '2.0.4-SNP', '3:46b1f1076a6a7cb70cddd0f229ba541e', 477);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-478::Jeff Caddel (generated)::(Checksum: 3:c5c323388c06b20bcbdd10491b335301)
ALTER TABLE KRIM_ADDR_TYP_T ADD CONSTRAINT KRIM_ADDR_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-478', '2.0.4-SNP', '3:c5c323388c06b20bcbdd10491b335301', 478);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-479::Jeff Caddel (generated)::(Checksum: 3:abdc55af56cdc6986ad6ce8f69d85049)
ALTER TABLE KRIM_ADDR_TYP_T ADD CONSTRAINT KRIM_ADDR_TYP_TC1 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-479', '2.0.4-SNP', '3:abdc55af56cdc6986ad6ce8f69d85049', 479);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-480::Jeff Caddel (generated)::(Checksum: 3:1649b13f16f75bd3d8345e55a3852f4c)
ALTER TABLE KRIM_AFLTN_TYP_T ADD CONSTRAINT KRIM_AFLTN_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-480', '2.0.4-SNP', '3:1649b13f16f75bd3d8345e55a3852f4c', 480);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-481::Jeff Caddel (generated)::(Checksum: 3:851a226b36fc15179ed972b92bec8ee4)
ALTER TABLE KRIM_AFLTN_TYP_T ADD CONSTRAINT KRIM_AFLTN_TYP_TC1 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-481', '2.0.4-SNP', '3:851a226b36fc15179ed972b92bec8ee4', 481);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-482::Jeff Caddel (generated)::(Checksum: 3:6ce5972c0a17ba7d082ea599ee882cd1)
ALTER TABLE KRIM_ATTR_DEFN_T ADD CONSTRAINT KRIM_ATTR_DEFN_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-482', '2.0.4-SNP', '3:6ce5972c0a17ba7d082ea599ee882cd1', 482);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-483::Jeff Caddel (generated)::(Checksum: 3:cc7e750f80a2199312467a8dc8c5d5d2)
ALTER TABLE KRIM_CTZNSHP_STAT_T ADD CONSTRAINT KRIM_CTZNSHP_STAT_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-483', '2.0.4-SNP', '3:cc7e750f80a2199312467a8dc8c5d5d2', 483);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-484::Jeff Caddel (generated)::(Checksum: 3:5b6c017c5d14b167845ba1c58c477875)
ALTER TABLE KRIM_CTZNSHP_STAT_T ADD CONSTRAINT KRIM_CTZNSHP_STAT_TC1 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-484', '2.0.4-SNP', '3:5b6c017c5d14b167845ba1c58c477875', 484);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-485::Jeff Caddel (generated)::(Checksum: 3:09702ea995a6d523d58ce880699933f3)
ALTER TABLE KRIM_DLGN_MBR_ATTR_DATA_T ADD CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-485', '2.0.4-SNP', '3:09702ea995a6d523d58ce880699933f3', 485);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-486::Jeff Caddel (generated)::(Checksum: 3:be5248044a9be3caa5dce36f6b4a3529)
ALTER TABLE KRIM_DLGN_MBR_T ADD CONSTRAINT KRIM_DLGN_MBR_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-486', '2.0.4-SNP', '3:be5248044a9be3caa5dce36f6b4a3529', 486);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-487::Jeff Caddel (generated)::(Checksum: 3:1a4629ed2e685c28767ecf2f23435b5c)
ALTER TABLE KRIM_DLGN_T ADD CONSTRAINT KRIM_DLGN_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-487', '2.0.4-SNP', '3:1a4629ed2e685c28767ecf2f23435b5c', 487);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-488::Jeff Caddel (generated)::(Checksum: 3:c4bbb8d7e007e9f435523b26336dc8ac)
ALTER TABLE KRIM_EMAIL_TYP_T ADD CONSTRAINT KRIM_EMAIL_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-488', '2.0.4-SNP', '3:c4bbb8d7e007e9f435523b26336dc8ac', 488);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-489::Jeff Caddel (generated)::(Checksum: 3:204612501b8c47baad9c1b91c302e401)
ALTER TABLE KRIM_EMAIL_TYP_T ADD CONSTRAINT KRIM_EMAIL_TYP_TC1 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-489', '2.0.4-SNP', '3:204612501b8c47baad9c1b91c302e401', 489);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-490::Jeff Caddel (generated)::(Checksum: 3:02b47044d6d324ce9f08982cad3f03bd)
ALTER TABLE KRIM_EMP_STAT_T ADD CONSTRAINT KRIM_EMP_STAT_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-490', '2.0.4-SNP', '3:02b47044d6d324ce9f08982cad3f03bd', 490);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-491::Jeff Caddel (generated)::(Checksum: 3:5ad5ad83538125ab19753bc124e73ab4)
ALTER TABLE KRIM_EMP_STAT_T ADD CONSTRAINT KRIM_EMP_STAT_TC1 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-491', '2.0.4-SNP', '3:5ad5ad83538125ab19753bc124e73ab4', 491);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-492::Jeff Caddel (generated)::(Checksum: 3:5066ea4e3a3bc615a191b9b6bab75bf3)
ALTER TABLE KRIM_EMP_TYP_T ADD CONSTRAINT KRIM_EMP_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-492', '2.0.4-SNP', '3:5066ea4e3a3bc615a191b9b6bab75bf3', 492);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-493::Jeff Caddel (generated)::(Checksum: 3:aabd7e6723c787918b5e43f5f3f311c0)
ALTER TABLE KRIM_EMP_TYP_T ADD CONSTRAINT KRIM_EMP_TYP_TC1 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-493', '2.0.4-SNP', '3:aabd7e6723c787918b5e43f5f3f311c0', 493);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-494::Jeff Caddel (generated)::(Checksum: 3:3d50659ae87412efaed7a46afcbb0429)
ALTER TABLE KRIM_ENTITY_ADDR_T ADD CONSTRAINT KRIM_ENTITY_ADDR_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-494', '2.0.4-SNP', '3:3d50659ae87412efaed7a46afcbb0429', 494);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-495::Jeff Caddel (generated)::(Checksum: 3:fffc9e868dbea234c1df7303a3536173)
ALTER TABLE KRIM_ENTITY_AFLTN_T ADD CONSTRAINT KRIM_ENTITY_AFLTN_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-495', '2.0.4-SNP', '3:fffc9e868dbea234c1df7303a3536173', 495);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-496::Jeff Caddel (generated)::(Checksum: 3:cd532c6002edb80a75729fabeb92a694)
ALTER TABLE KRIM_ENTITY_BIO_T ADD CONSTRAINT KRIM_ENTITY_BIO_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-496', '2.0.4-SNP', '3:cd532c6002edb80a75729fabeb92a694', 496);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-497::Jeff Caddel (generated)::(Checksum: 3:f3d51840e5233617d2f5b6a7aaf48d72)
ALTER TABLE KRIM_ENTITY_CACHE_T ADD CONSTRAINT KRIM_ENTITY_CACHE_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-497', '2.0.4-SNP', '3:f3d51840e5233617d2f5b6a7aaf48d72', 497);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-498::Jeff Caddel (generated)::(Checksum: 3:8dfe5101a6af9b16cf5cf9408fc4653b)
ALTER TABLE KRIM_ENTITY_CACHE_T ADD CONSTRAINT KRIM_ENTITY_CACHE_TC1 UNIQUE (PRNCPL_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-498', '2.0.4-SNP', '3:8dfe5101a6af9b16cf5cf9408fc4653b', 498);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-499::Jeff Caddel (generated)::(Checksum: 3:1f3cae886afb60ff23c24d9767345d8c)
ALTER TABLE KRIM_ENTITY_CTZNSHP_T ADD CONSTRAINT KRIM_ENTITY_CTZNSHP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-499', '2.0.4-SNP', '3:1f3cae886afb60ff23c24d9767345d8c', 499);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-500::Jeff Caddel (generated)::(Checksum: 3:b24910fa7eaa82fccf886f7a0172f757)
ALTER TABLE KRIM_ENTITY_EMAIL_T ADD CONSTRAINT KRIM_ENTITY_EMAIL_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-500', '2.0.4-SNP', '3:b24910fa7eaa82fccf886f7a0172f757', 500);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-501::Jeff Caddel (generated)::(Checksum: 3:080da3837ac77476cdcd966e7927e76a)
ALTER TABLE KRIM_ENTITY_EMP_INFO_T ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-501', '2.0.4-SNP', '3:080da3837ac77476cdcd966e7927e76a', 501);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-502::Jeff Caddel (generated)::(Checksum: 3:3844d6cb57c94b191e688ec02c623911)
ALTER TABLE KRIM_ENTITY_ENT_TYP_T ADD CONSTRAINT KRIM_ENTITY_ENT_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-502', '2.0.4-SNP', '3:3844d6cb57c94b191e688ec02c623911', 502);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-503::Jeff Caddel (generated)::(Checksum: 3:5a3d05a91791cc46a85a6fd3d96adb92)
ALTER TABLE KRIM_ENTITY_ETHNIC_T ADD CONSTRAINT KRIM_ENTITY_ETHNIC_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-503', '2.0.4-SNP', '3:5a3d05a91791cc46a85a6fd3d96adb92', 503);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-504::Jeff Caddel (generated)::(Checksum: 3:39c6b7ae9bb34cb7f8761648b04827a2)
ALTER TABLE KRIM_ENTITY_EXT_ID_T ADD CONSTRAINT KRIM_ENTITY_EXT_ID_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-504', '2.0.4-SNP', '3:39c6b7ae9bb34cb7f8761648b04827a2', 504);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-505::Jeff Caddel (generated)::(Checksum: 3:aea34c3925b00968007339a2ed9d4cbd)
ALTER TABLE KRIM_ENTITY_NM_T ADD CONSTRAINT KRIM_ENTITY_NM_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-505', '2.0.4-SNP', '3:aea34c3925b00968007339a2ed9d4cbd', 505);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-506::Jeff Caddel (generated)::(Checksum: 3:1df0c060b3a4a2ec02353922fc8bad7c)
ALTER TABLE KRIM_ENTITY_PHONE_T ADD CONSTRAINT KRIM_ENTITY_PHONE_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-506', '2.0.4-SNP', '3:1df0c060b3a4a2ec02353922fc8bad7c', 506);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-507::Jeff Caddel (generated)::(Checksum: 3:d4d1e1e8d37f8008686fc44670588fb1)
ALTER TABLE KRIM_ENTITY_PRIV_PREF_T ADD CONSTRAINT KRIM_ENTITY_PRIV_PREF_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-507', '2.0.4-SNP', '3:d4d1e1e8d37f8008686fc44670588fb1', 507);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-508::Jeff Caddel (generated)::(Checksum: 3:8d7a3618728fb61c184dfb3d4b048b32)
ALTER TABLE KRIM_ENTITY_RESIDENCY_T ADD CONSTRAINT KRIM_ENTITY_RESIDENCY_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-508', '2.0.4-SNP', '3:8d7a3618728fb61c184dfb3d4b048b32', 508);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-509::Jeff Caddel (generated)::(Checksum: 3:13a4225a195e18267d2d69569329e24f)
ALTER TABLE KRIM_ENTITY_T ADD CONSTRAINT KRIM_ENTITY_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-509', '2.0.4-SNP', '3:13a4225a195e18267d2d69569329e24f', 509);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-510::Jeff Caddel (generated)::(Checksum: 3:f167b9f6622385e9ea39d51c0cd9d4d5)
ALTER TABLE KRIM_ENTITY_VISA_T ADD CONSTRAINT KRIM_ENTITY_VISA_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-510', '2.0.4-SNP', '3:f167b9f6622385e9ea39d51c0cd9d4d5', 510);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-511::Jeff Caddel (generated)::(Checksum: 3:30f8574b32bf1c73cf0d1a88942ee6d1)
ALTER TABLE KRIM_ENT_NM_TYP_T ADD CONSTRAINT KRIM_ENT_NM_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-511', '2.0.4-SNP', '3:30f8574b32bf1c73cf0d1a88942ee6d1', 511);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-512::Jeff Caddel (generated)::(Checksum: 3:c80a2d30afd5f6a35711dd6f552dc9d9)
ALTER TABLE KRIM_ENT_NM_TYP_T ADD CONSTRAINT KRIM_ENT_NM_TYP_TC1 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-512', '2.0.4-SNP', '3:c80a2d30afd5f6a35711dd6f552dc9d9', 512);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-513::Jeff Caddel (generated)::(Checksum: 3:9cdbede6d1a12f2327c6775be53c3ec9)
ALTER TABLE KRIM_ENT_TYP_T ADD CONSTRAINT KRIM_ENT_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-513', '2.0.4-SNP', '3:9cdbede6d1a12f2327c6775be53c3ec9', 513);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-514::Jeff Caddel (generated)::(Checksum: 3:2c3e498fbcdd42acb238a52c93b8d681)
ALTER TABLE KRIM_ENT_TYP_T ADD CONSTRAINT KRIM_ENT_TYP_TC1 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-514', '2.0.4-SNP', '3:2c3e498fbcdd42acb238a52c93b8d681', 514);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-515::Jeff Caddel (generated)::(Checksum: 3:76f39570449d24726ce2ad29e3b091c9)
ALTER TABLE KRIM_EXT_ID_TYP_T ADD CONSTRAINT KRIM_EXT_ID_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-515', '2.0.4-SNP', '3:76f39570449d24726ce2ad29e3b091c9', 515);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-516::Jeff Caddel (generated)::(Checksum: 3:d0251332fe62b6430c005bc41a0980ca)
ALTER TABLE KRIM_EXT_ID_TYP_T ADD CONSTRAINT KRIM_EXT_ID_TYP_TC1 UNIQUE (NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-516', '2.0.4-SNP', '3:d0251332fe62b6430c005bc41a0980ca', 516);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-517::Jeff Caddel (generated)::(Checksum: 3:de614943e7f57d61bdceb83592c79c18)
ALTER TABLE KRIM_GRP_ATTR_DATA_T ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-517', '2.0.4-SNP', '3:de614943e7f57d61bdceb83592c79c18', 517);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-518::Jeff Caddel (generated)::(Checksum: 3:86c298bd4e5887ee8ebadb6d63e66cb1)
ALTER TABLE KRIM_GRP_MBR_T ADD CONSTRAINT KRIM_GRP_MBR_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-518', '2.0.4-SNP', '3:86c298bd4e5887ee8ebadb6d63e66cb1', 518);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-519::Jeff Caddel (generated)::(Checksum: 3:a32e8fe203a4a713b44bc439df32137c)
ALTER TABLE KRIM_GRP_T ADD CONSTRAINT KRIM_GRP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-519', '2.0.4-SNP', '3:a32e8fe203a4a713b44bc439df32137c', 519);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-520::Jeff Caddel (generated)::(Checksum: 3:09b423f0657a1ad70a826821e3715134)
ALTER TABLE KRIM_GRP_T ADD CONSTRAINT KRIM_GRP_TC1 UNIQUE (GRP_NM, NMSPC_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-520', '2.0.4-SNP', '3:09b423f0657a1ad70a826821e3715134', 520);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-521::Jeff Caddel (generated)::(Checksum: 3:6640669ec40490509afb44e89cf9535f)
ALTER TABLE KRIM_PERM_ATTR_DATA_T ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-521', '2.0.4-SNP', '3:6640669ec40490509afb44e89cf9535f', 521);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-522::Jeff Caddel (generated)::(Checksum: 3:353afe2220c1d8e6aba71bc9ea4da0cd)
ALTER TABLE KRIM_PERM_T ADD CONSTRAINT KRIM_PERM_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-522', '2.0.4-SNP', '3:353afe2220c1d8e6aba71bc9ea4da0cd', 522);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-523::Jeff Caddel (generated)::(Checksum: 3:c56c525c030def607ff073c3c077ffc4)
ALTER TABLE KRIM_PERM_TMPL_T ADD CONSTRAINT KRIM_PERM_TMPL_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-523', '2.0.4-SNP', '3:c56c525c030def607ff073c3c077ffc4', 523);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-524::Jeff Caddel (generated)::(Checksum: 3:b89a4fc8d7d9ad1559ae8f61b5727dc3)
ALTER TABLE KRIM_PHONE_TYP_T ADD CONSTRAINT KRIM_PHONE_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-524', '2.0.4-SNP', '3:b89a4fc8d7d9ad1559ae8f61b5727dc3', 524);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-525::Jeff Caddel (generated)::(Checksum: 3:0147337202d8328f8139cdbc2ba42397)
ALTER TABLE KRIM_PHONE_TYP_T ADD CONSTRAINT KRIM_PHONE_TYP_TC1 UNIQUE (PHONE_TYP_NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-525', '2.0.4-SNP', '3:0147337202d8328f8139cdbc2ba42397', 525);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-526::Jeff Caddel (generated)::(Checksum: 3:5c8d216429f942428acb26dd1952374b)
ALTER TABLE KRIM_PRNCPL_T ADD CONSTRAINT KRIM_PRNCPL_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-526', '2.0.4-SNP', '3:5c8d216429f942428acb26dd1952374b', 526);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-527::Jeff Caddel (generated)::(Checksum: 3:86a9f7b52f19eb6b715bfa7113eaebf1)
ALTER TABLE KRIM_PRNCPL_T ADD CONSTRAINT KRIM_PRNCPL_TC1 UNIQUE (PRNCPL_NM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-527', '2.0.4-SNP', '3:86a9f7b52f19eb6b715bfa7113eaebf1', 527);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-528::Jeff Caddel (generated)::(Checksum: 3:44c4449a3f132b084a05fec593f59594)
ALTER TABLE KRIM_ROLE_MBR_ATTR_DATA_T ADD CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-528', '2.0.4-SNP', '3:44c4449a3f132b084a05fec593f59594', 528);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-529::Jeff Caddel (generated)::(Checksum: 3:4388cd3c1eb1c9331728cd02ecd28def)
ALTER TABLE KRIM_ROLE_MBR_T ADD CONSTRAINT KRIM_ROLE_MBR_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-529', '2.0.4-SNP', '3:4388cd3c1eb1c9331728cd02ecd28def', 529);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-530::Jeff Caddel (generated)::(Checksum: 3:9ae031bc38255a84319f307a30b7efe8)
ALTER TABLE KRIM_ROLE_PERM_T ADD CONSTRAINT KRIM_ROLE_PERM_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-530', '2.0.4-SNP', '3:9ae031bc38255a84319f307a30b7efe8', 530);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-531::Jeff Caddel (generated)::(Checksum: 3:f0ac9a7f0a098e2c78060c83aaec6a30)
ALTER TABLE KRIM_ROLE_RSP_ACTN_T ADD CONSTRAINT KRIM_ROLE_RSP_ACTN_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-531', '2.0.4-SNP', '3:f0ac9a7f0a098e2c78060c83aaec6a30', 531);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-532::Jeff Caddel (generated)::(Checksum: 3:0a961b023f416861e44950e21b79aeb1)
ALTER TABLE KRIM_ROLE_RSP_ACTN_T ADD CONSTRAINT KRIM_ROLE_RSP_ACTN_TC1 UNIQUE (ROLE_RSP_ID, ROLE_MBR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-532', '2.0.4-SNP', '3:0a961b023f416861e44950e21b79aeb1', 532);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-533::Jeff Caddel (generated)::(Checksum: 3:506436c963379af21bcfd4d22bcac430)
ALTER TABLE KRIM_ROLE_RSP_T ADD CONSTRAINT KRIM_ROLE_RSP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-533', '2.0.4-SNP', '3:506436c963379af21bcfd4d22bcac430', 533);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-534::Jeff Caddel (generated)::(Checksum: 3:9d7a45057e4a7ae9d76143526738c5bd)
ALTER TABLE KRIM_ROLE_T ADD CONSTRAINT KRIM_ROLE_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-534', '2.0.4-SNP', '3:9d7a45057e4a7ae9d76143526738c5bd', 534);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-535::Jeff Caddel (generated)::(Checksum: 3:656ac099ab5df0c71da7841c5b61023b)
ALTER TABLE KRIM_ROLE_T ADD CONSTRAINT KRIM_ROLE_TC1 UNIQUE (ROLE_NM, NMSPC_CD) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-535', '2.0.4-SNP', '3:656ac099ab5df0c71da7841c5b61023b', 535);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-536::Jeff Caddel (generated)::(Checksum: 3:a8ee8ec947f8d8b92602a18fcdcf1959)
ALTER TABLE KRIM_RSP_ATTR_DATA_T ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-536', '2.0.4-SNP', '3:a8ee8ec947f8d8b92602a18fcdcf1959', 536);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-537::Jeff Caddel (generated)::(Checksum: 3:124edbc6e14292f94f9d7353188e9edc)
ALTER TABLE KRIM_RSP_T ADD CONSTRAINT KRIM_RSP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-537', '2.0.4-SNP', '3:124edbc6e14292f94f9d7353188e9edc', 537);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-538::Jeff Caddel (generated)::(Checksum: 3:9f8dd86c55c0701315933fbca62245d7)
ALTER TABLE KRIM_RSP_TMPL_T ADD CONSTRAINT KRIM_RSP_TMPL_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-538', '2.0.4-SNP', '3:9f8dd86c55c0701315933fbca62245d7', 538);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-539::Jeff Caddel (generated)::(Checksum: 3:117be9ba58c37adc0296b1cdcba90c26)
ALTER TABLE KRIM_TYP_ATTR_T ADD CONSTRAINT KRIM_TYP_ATTR_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-539', '2.0.4-SNP', '3:117be9ba58c37adc0296b1cdcba90c26', 539);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-540::Jeff Caddel (generated)::(Checksum: 3:554fc3cabed721e5223111969736fbc9)
ALTER TABLE KRIM_TYP_T ADD CONSTRAINT KRIM_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-540', '2.0.4-SNP', '3:554fc3cabed721e5223111969736fbc9', 540);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-541::Jeff Caddel (generated)::(Checksum: 3:664f02105a9e8cf50d2c91fe66443f5c)
ALTER TABLE KRLC_CMP_T ADD CONSTRAINT KRNS_CAMPUS_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-541', '2.0.4-SNP', '3:664f02105a9e8cf50d2c91fe66443f5c', 541);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-542::Jeff Caddel (generated)::(Checksum: 3:8bd312703eb88aca8f4e45f9d182a687)
ALTER TABLE KRLC_CMP_TYP_T ADD CONSTRAINT KRNS_CMP_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-542', '2.0.4-SNP', '3:8bd312703eb88aca8f4e45f9d182a687', 542);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-543::Jeff Caddel (generated)::(Checksum: 3:b1e303480921231f264e14189758f3bb)
ALTER TABLE KRLC_CNTRY_T ADD CONSTRAINT KR_COUNTRY_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-543', '2.0.4-SNP', '3:b1e303480921231f264e14189758f3bb', 543);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-544::Jeff Caddel (generated)::(Checksum: 3:d2bf600159be6a01fca0374f3fa950a3)
ALTER TABLE KRLC_CNTY_T ADD CONSTRAINT KR_COUNTY_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-544', '2.0.4-SNP', '3:d2bf600159be6a01fca0374f3fa950a3', 544);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-545::Jeff Caddel (generated)::(Checksum: 3:f3bc064c850c35306184464f867e4e07)
ALTER TABLE KRLC_PSTL_CD_T ADD CONSTRAINT KR_POSTAL_CODE_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-545', '2.0.4-SNP', '3:f3bc064c850c35306184464f867e4e07', 545);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-546::Jeff Caddel (generated)::(Checksum: 3:fa79bfa580922b7b6dec0657a1f6d21d)
ALTER TABLE KRLC_ST_T ADD CONSTRAINT KR_STATE_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-546', '2.0.4-SNP', '3:fa79bfa580922b7b6dec0657a1f6d21d', 546);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-547::Jeff Caddel (generated)::(Checksum: 3:523e2cae98532c2aad7f7e1c25148cd4)
ALTER TABLE KRMS_ACTN_T ADD CONSTRAINT KRMS_ACTN_TC2 UNIQUE (ACTN_ID, RULE_ID, SEQ_NO) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-547', '2.0.4-SNP', '3:523e2cae98532c2aad7f7e1c25148cd4', 547);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-548::Jeff Caddel (generated)::(Checksum: 3:f24d2945fdbcbaa7d776a118ffcbe076)
ALTER TABLE KRMS_TERM_RSLVR_T ADD CONSTRAINT KRMS_TERM_RSLVR_TC1 UNIQUE (NMSPC_CD, NM, CNTXT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-548', '2.0.4-SNP', '3:f24d2945fdbcbaa7d776a118ffcbe076', 548);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-549::Jeff Caddel (generated)::(Checksum: 3:9d3d3bdb9a3dea51069f42d28d50bd7b)
ALTER TABLE KRMS_TERM_SPEC_T ADD CONSTRAINT KRMS_ASSET_TC1 UNIQUE (NM, CNTXT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-549', '2.0.4-SNP', '3:9d3d3bdb9a3dea51069f42d28d50bd7b', 549);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-550::Jeff Caddel (generated)::(Checksum: 3:d548d66c1c1235cbc735bbdd6c4a6f45)
ALTER TABLE KRMS_TYP_ATTR_T ADD CONSTRAINT KRMS_TYP_ATTR_TC1 UNIQUE (TYP_ID, ATTR_DEFN_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-550', '2.0.4-SNP', '3:d548d66c1c1235cbc735bbdd6c4a6f45', 550);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-551::Jeff Caddel (generated)::(Checksum: 3:e71a5a69ef22b20e5a911eee5aaffe07)
ALTER TABLE KRNS_ADHOC_RTE_ACTN_RECIP_T ADD CONSTRAINT KRNS_ADHOC_RTE_ACTN_RECIP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-551', '2.0.4-SNP', '3:e71a5a69ef22b20e5a911eee5aaffe07', 551);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-552::Jeff Caddel (generated)::(Checksum: 3:a3c1195ae8c28132d077f14ded1f2331)
ALTER TABLE KRNS_ATT_T ADD CONSTRAINT KRNS_ATT_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-552', '2.0.4-SNP', '3:a3c1195ae8c28132d077f14ded1f2331', 552);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-553::Jeff Caddel (generated)::(Checksum: 3:0a809e2c37543573b6112875059c891b)
ALTER TABLE KRNS_DOC_HDR_T ADD CONSTRAINT KRNS_DOC_HDR_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-553', '2.0.4-SNP', '3:0a809e2c37543573b6112875059c891b', 553);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-554::Jeff Caddel (generated)::(Checksum: 3:af01192dad59f245198a6fcc40a96e3b)
ALTER TABLE KRNS_LOOKUP_RSLT_T ADD CONSTRAINT KRNS_LOOKUP_RSLT_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-554', '2.0.4-SNP', '3:af01192dad59f245198a6fcc40a96e3b', 554);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-555::Jeff Caddel (generated)::(Checksum: 3:caf89e048ab4845cd0c74471edcf626d)
ALTER TABLE KRNS_LOOKUP_SEL_T ADD CONSTRAINT KRNS_LOOKUP_SEL_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-555', '2.0.4-SNP', '3:caf89e048ab4845cd0c74471edcf626d', 555);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-556::Jeff Caddel (generated)::(Checksum: 3:6d706621f42c13c88b9127048c7ada45)
ALTER TABLE KRNS_MAINT_DOC_ATT_T ADD CONSTRAINT KRNS_MAINT_DOC_ATT_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-556', '2.0.4-SNP', '3:6d706621f42c13c88b9127048c7ada45', 556);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-557::Jeff Caddel (generated)::(Checksum: 3:f25762a6db55cb9130194d7afe8d79f2)
ALTER TABLE KRNS_MAINT_DOC_T ADD CONSTRAINT KRNS_MAINT_DOC_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-557', '2.0.4-SNP', '3:f25762a6db55cb9130194d7afe8d79f2', 557);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-558::Jeff Caddel (generated)::(Checksum: 3:eb86e0d788499f4cbe2e271c26f7529f)
ALTER TABLE KRNS_MAINT_LOCK_T ADD CONSTRAINT KRNS_MAINT_LOCK_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-558', '2.0.4-SNP', '3:eb86e0d788499f4cbe2e271c26f7529f', 558);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-559::Jeff Caddel (generated)::(Checksum: 3:8a6ee1988cce5e068b79d99ee5d99480)
ALTER TABLE KRNS_NTE_T ADD CONSTRAINT KRNS_NTE_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-559', '2.0.4-SNP', '3:8a6ee1988cce5e068b79d99ee5d99480', 559);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-560::Jeff Caddel (generated)::(Checksum: 3:b15712a01c22f7da45c6c3907df9aac3)
ALTER TABLE KRNS_NTE_TYP_T ADD CONSTRAINT KRNS_NTE_TYP_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-560', '2.0.4-SNP', '3:b15712a01c22f7da45c6c3907df9aac3', 560);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-561::Jeff Caddel (generated)::(Checksum: 3:929982b9c4b28c3f730ece64dbefc289)
ALTER TABLE KRNS_PESSIMISTIC_LOCK_T ADD CONSTRAINT KRNS_PESSIMISTIC_LOCK_TC0 UNIQUE (OBJ_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-561', '2.0.4-SNP', '3:929982b9c4b28c3f730ece64dbefc289', 561);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-562::Jeff Caddel (generated)::(Checksum: 3:abe567a8c862842554b1463bb5ab9a4e)
ALTER TABLE KSCO_REFERENCE ADD CONSTRAINT SYS_C0033779 UNIQUE (REFERENCE_ID, REFERENCE_TYPE) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-562', '2.0.4-SNP', '3:abe567a8c862842554b1463bb5ab9a4e', 562);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-563::Jeff Caddel (generated)::(Checksum: 3:445f457c6b4687f55cf0b241f4a6ace3)
ALTER TABLE KSEM_CTX_T ADD CONSTRAINT SYS_C00285110 UNIQUE (CTX_KEY, CTX_VAL) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-563', '2.0.4-SNP', '3:445f457c6b4687f55cf0b241f4a6ace3', 563);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-564::Jeff Caddel (generated)::(Checksum: 3:32757cb45c74be5af9347e7369b92e4b)
ALTER TABLE KSLO_LO_JN_LOCATEGORY ADD CONSTRAINT SYS_C0011301 UNIQUE (LO_ID, LOCATEGORY_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-564', '2.0.4-SNP', '3:32757cb45c74be5af9347e7369b92e4b', 564);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-565::Jeff Caddel (generated)::(Checksum: 3:4a8407b78760e935b18dca1117fb77b6)
ALTER TABLE KSLU_CLU ADD CONSTRAINT SYS_C0011370 UNIQUE (VER_IND_ID, SEQ_NUM) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-565', '2.0.4-SNP', '3:4a8407b78760e935b18dca1117fb77b6', 565);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-566::Jeff Caddel (generated)::(Checksum: 3:d31391fb4d1fa1ca217f40fd9f4b5248)
ALTER TABLE KSLU_CLURES_JN_RESOPT ADD CONSTRAINT SYS_C0011378 UNIQUE (RES_OPT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-566', '2.0.4-SNP', '3:d31391fb4d1fa1ca217f40fd9f4b5248', 566);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-567::Jeff Caddel (generated)::(Checksum: 3:02468eb7d9c9ce67508743ff5fa54029)
ALTER TABLE KSLU_CLU_JN_ACCRED ADD CONSTRAINT SYS_C0011436 UNIQUE (CLU_ACCRED_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-567', '2.0.4-SNP', '3:02468eb7d9c9ce67508743ff5fa54029', 567);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-568::Jeff Caddel (generated)::(Checksum: 3:9ae24273157e0c0972e8b984e647a555)
ALTER TABLE KSLU_CLU_JN_CLU_IDENT ADD CONSTRAINT SYS_C0011441 UNIQUE (ALT_CLU_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-568', '2.0.4-SNP', '3:9ae24273157e0c0972e8b984e647a555', 568);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-569::Jeff Caddel (generated)::(Checksum: 3:726efccdaf5f4a5ae2bfc4e184285f79)
ALTER TABLE KSLU_CLU_JN_CLU_INSTR ADD CONSTRAINT SYS_C0011444 UNIQUE (CLU_INSTR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-569', '2.0.4-SNP', '3:726efccdaf5f4a5ae2bfc4e184285f79', 569);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-570::Jeff Caddel (generated)::(Checksum: 3:e510c86d743b634a37037d422a1e27c1)
ALTER TABLE KSLU_CLU_PUBL_JN_CLU_INSTR ADD CONSTRAINT SYS_C009456 UNIQUE (CLU_INSTR_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-570', '2.0.4-SNP', '3:e510c86d743b634a37037d422a1e27c1', 570);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-571::Jeff Caddel (generated)::(Checksum: 3:74a4d696896e71b05d3f7b57025a2a98)
ALTER TABLE KSLU_CLU_PUBL_VARI ADD CONSTRAINT SYS_C0011470 UNIQUE (VARI_KEY, OWNER) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-571', '2.0.4-SNP', '3:74a4d696896e71b05d3f7b57025a2a98', 571);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-572::Jeff Caddel (generated)::(Checksum: 3:2bcad9d0ad5f2d5912199bb3ebf3b4d3)
ALTER TABLE KSLU_CLU_SET_JN_CLU ADD CONSTRAINT KSLU_CLU_SET_JN_CLU_I1 UNIQUE (CLU_SET_ID, CLU_VER_IND_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-572', '2.0.4-SNP', '3:2bcad9d0ad5f2d5912199bb3ebf3b4d3', 572);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-573::Jeff Caddel (generated)::(Checksum: 3:af7be88f875019125965cd483a106e30)
ALTER TABLE KSLU_MEMSHIP_KSLU_SPARAM ADD CONSTRAINT SYS_C0011543 UNIQUE (SEARCHPARAMETERS_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-573', '2.0.4-SNP', '3:af7be88f875019125965cd483a106e30', 573);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-574::Jeff Caddel (generated)::(Checksum: 3:988163a435bd366f541f849dadaac3cd)
ALTER TABLE KSLU_RSRC ADD CONSTRAINT SYS_C0011561 UNIQUE (RSRC_TYPE_ID, CLU_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-574', '2.0.4-SNP', '3:988163a435bd366f541f849dadaac3cd', 574);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-575::Jeff Caddel (generated)::(Checksum: 3:c59271192ecc01e81d5c177759b92c9d)
ALTER TABLE KSLU_SPARAM_KSLU_SPVALUE ADD CONSTRAINT SYS_C0011566 UNIQUE (VALUES_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-575', '2.0.4-SNP', '3:c59271192ecc01e81d5c177759b92c9d', 575);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-576::Jeff Caddel (generated)::(Checksum: 3:7eecd129c29ec3b5b45eb3b1886b2dc0)
ALTER TABLE KSOR_ORG_PERS_RELTN ADD CONSTRAINT SYS_C0011691 UNIQUE (ORG_PERS_RELTN_TYPE, ORG, PERS_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-576', '2.0.4-SNP', '3:7eecd129c29ec3b5b45eb3b1886b2dc0', 576);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-577::Jeff Caddel (generated)::(Checksum: 3:0e7a3f09278a02f678e2d8bfeecf0dd6)
ALTER TABLE KSOR_ORG_POS_RESTR ADD CONSTRAINT SYS_C0011701 UNIQUE (ORG, PERS_RELTN_TYPE) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-577', '2.0.4-SNP', '3:0e7a3f09278a02f678e2d8bfeecf0dd6', 577);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-578::Jeff Caddel (generated)::(Checksum: 3:fdd11fc20b97e5d0790dd537585be870)
ALTER TABLE KSST_OBJ_TYP_JN_OBJ_SUB_TYP ADD CONSTRAINT SYS_C0011792 UNIQUE (OBJ_SUB_TYPE_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-578', '2.0.4-SNP', '3:fdd11fc20b97e5d0790dd537585be870', 578);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-579::Jeff Caddel (generated)::(Checksum: 3:91c9abe0798b02432b72b6f2ee49d2cd)
ALTER TABLE KSST_RC_JN_RC_FIELD ADD CONSTRAINT SYS_C0011797 UNIQUE (REQ_COM_FIELD_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-579', '2.0.4-SNP', '3:91c9abe0798b02432b72b6f2ee49d2cd', 579);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-580::Jeff Caddel (generated)::(Checksum: 3:00558ac4701c0243a3d287da57d5edad)
ALTER TABLE KSST_STMT_JN_STMT ADD CONSTRAINT SYS_C0011839 UNIQUE (CHLD_STMT_ID) USING INDEX TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Unique Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-580', '2.0.4-SNP', '3:00558ac4701c0243a3d287da57d5edad', 580);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-581::Jeff Caddel (generated)::(Checksum: 3:da9a32769622e1bb06ea5fb63c6dfeb6)
CREATE INDEX KREW_ACTN_ITM_T1 ON KREW_ACTN_ITM_T(PRNCPL_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-581', '2.0.4-SNP', '3:da9a32769622e1bb06ea5fb63c6dfeb6', 581);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-582::Jeff Caddel (generated)::(Checksum: 3:4c8de9b0a915aa98485e96a53bc7ecd4)
CREATE INDEX KREW_ACTN_ITM_TI2 ON KREW_ACTN_ITM_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-582', '2.0.4-SNP', '3:4c8de9b0a915aa98485e96a53bc7ecd4', 582);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-583::Jeff Caddel (generated)::(Checksum: 3:17a8a016f837a7318b7cf9657e97ed77)
CREATE INDEX KREW_ACTN_ITM_TI3 ON KREW_ACTN_ITM_T(ACTN_RQST_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-583', '2.0.4-SNP', '3:17a8a016f837a7318b7cf9657e97ed77', 583);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-584::Jeff Caddel (generated)::(Checksum: 3:7b2d6afe9c005dd86b44881ad16b3f54)
CREATE INDEX KREW_ACTN_ITM_TI5 ON KREW_ACTN_ITM_T(PRNCPL_ID, DLGN_TYP, DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-584', '2.0.4-SNP', '3:7b2d6afe9c005dd86b44881ad16b3f54', 584);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-585::Jeff Caddel (generated)::(Checksum: 3:db17edb0f2464294815fa06b84035e38)
CREATE INDEX KREW_ACTN_RQST_T11 ON KREW_ACTN_RQST_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-585', '2.0.4-SNP', '3:db17edb0f2464294815fa06b84035e38', 585);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-586::Jeff Caddel (generated)::(Checksum: 3:118d5ef3a0dc4d8efcf7d664712ebc1d)
CREATE INDEX KREW_ACTN_RQST_T12 ON KREW_ACTN_RQST_T(PRNCPL_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-586', '2.0.4-SNP', '3:118d5ef3a0dc4d8efcf7d664712ebc1d', 586);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-587::Jeff Caddel (generated)::(Checksum: 3:b2d382082bb7e2da49291d7961e92e30)
CREATE INDEX KREW_ACTN_RQST_T13 ON KREW_ACTN_RQST_T(ACTN_TKN_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-587', '2.0.4-SNP', '3:b2d382082bb7e2da49291d7961e92e30', 587);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-588::Jeff Caddel (generated)::(Checksum: 3:26d50bb21d0ce36eadb19304ffd49a15)
CREATE INDEX KREW_ACTN_RQST_T14 ON KREW_ACTN_RQST_T(PARNT_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-588', '2.0.4-SNP', '3:26d50bb21d0ce36eadb19304ffd49a15', 588);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-589::Jeff Caddel (generated)::(Checksum: 3:199bb10d7ce7c5e1fa489216cf057ff9)
CREATE INDEX KREW_ACTN_RQST_T15 ON KREW_ACTN_RQST_T(RSP_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-589', '2.0.4-SNP', '3:199bb10d7ce7c5e1fa489216cf057ff9', 589);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-590::Jeff Caddel (generated)::(Checksum: 3:f9281d2c6516d5340e0617fdcce40dcc)
CREATE INDEX KREW_ACTN_RQST_T16 ON KREW_ACTN_RQST_T(STAT_CD, RSP_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-590', '2.0.4-SNP', '3:f9281d2c6516d5340e0617fdcce40dcc', 590);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-591::Jeff Caddel (generated)::(Checksum: 3:b3639698348085b125e2cc644af49548)
CREATE INDEX KREW_ACTN_RQST_T17 ON KREW_ACTN_RQST_T(RTE_NODE_INSTN_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-591', '2.0.4-SNP', '3:b3639698348085b125e2cc644af49548', 591);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-592::Jeff Caddel (generated)::(Checksum: 3:f83c105c557776208cce120db1522853)
CREATE INDEX KREW_ACTN_RQST_T19 ON KREW_ACTN_RQST_T(STAT_CD, DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-592', '2.0.4-SNP', '3:f83c105c557776208cce120db1522853', 592);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-593::Jeff Caddel (generated)::(Checksum: 3:1a935072d643cade3dcfb3e08a011d49)
CREATE INDEX KREW_ACTN_TKN_TI1 ON KREW_ACTN_TKN_T(DOC_HDR_ID, PRNCPL_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-593', '2.0.4-SNP', '3:1a935072d643cade3dcfb3e08a011d49', 593);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-594::Jeff Caddel (generated)::(Checksum: 3:09aea705e0dd81693c8819fa5fc94c57)
CREATE INDEX KREW_ACTN_TKN_TI2 ON KREW_ACTN_TKN_T(DOC_HDR_ID, PRNCPL_ID, ACTN_CD) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-594', '2.0.4-SNP', '3:09aea705e0dd81693c8819fa5fc94c57', 594);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-595::Jeff Caddel (generated)::(Checksum: 3:2eb1a1ab2878a8d38ce763ac61d18878)
CREATE INDEX KREW_ACTN_TKN_TI3 ON KREW_ACTN_TKN_T(PRNCPL_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-595', '2.0.4-SNP', '3:2eb1a1ab2878a8d38ce763ac61d18878', 595);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-596::Jeff Caddel (generated)::(Checksum: 3:1cbc2631afd5f2278364b38a5a9c5118)
CREATE INDEX KREW_ACTN_TKN_TI4 ON KREW_ACTN_TKN_T(DLGTR_PRNCPL_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-596', '2.0.4-SNP', '3:1cbc2631afd5f2278364b38a5a9c5118', 596);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-597::Jeff Caddel (generated)::(Checksum: 3:efae8cdea2f5d548f130e08c8227bda3)
CREATE INDEX KREW_ACTN_TKN_TI5 ON KREW_ACTN_TKN_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-597', '2.0.4-SNP', '3:efae8cdea2f5d548f130e08c8227bda3', 597);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-598::Jeff Caddel (generated)::(Checksum: 3:c1d3bb77100a455a20a29ad1eaf65c1d)
CREATE INDEX KREW_APP_DOC_STAT_TI1 ON KREW_APP_DOC_STAT_TRAN_T(DOC_HDR_ID, STAT_TRANS_DATE) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-598', '2.0.4-SNP', '3:c1d3bb77100a455a20a29ad1eaf65c1d', 598);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-599::Jeff Caddel (generated)::(Checksum: 3:7f8d8cb5870349f4b49e36d6120a7c6e)
CREATE INDEX KREW_APP_DOC_STAT_TI2 ON KREW_APP_DOC_STAT_TRAN_T(DOC_HDR_ID, APP_DOC_STAT_FROM) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-599', '2.0.4-SNP', '3:7f8d8cb5870349f4b49e36d6120a7c6e', 599);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-600::Jeff Caddel (generated)::(Checksum: 3:d8332d8a4c663fe50c2d1e6a5e4374fc)
CREATE INDEX KREW_APP_DOC_STAT_TI3 ON KREW_APP_DOC_STAT_TRAN_T(DOC_HDR_ID, APP_DOC_STAT_TO) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-600', '2.0.4-SNP', '3:d8332d8a4c663fe50c2d1e6a5e4374fc', 600);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-601::Jeff Caddel (generated)::(Checksum: 3:11cab6f600eaa1eed02cf1cbf77c74c6)
CREATE INDEX KREW_DOC_HDR_EXT_DT_TI1 ON KREW_DOC_HDR_EXT_DT_T(KEY_CD, VAL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-601', '2.0.4-SNP', '3:11cab6f600eaa1eed02cf1cbf77c74c6', 601);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-602::Jeff Caddel (generated)::(Checksum: 3:313169c853a256deaea84365205b391b)
CREATE INDEX KREW_DOC_HDR_EXT_DT_TI2 ON KREW_DOC_HDR_EXT_DT_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-602', '2.0.4-SNP', '3:313169c853a256deaea84365205b391b', 602);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-603::Jeff Caddel (generated)::(Checksum: 3:4e34962f7e4fe478c4dcacce0e6a4fb2)
CREATE INDEX KREW_DOC_HDR_EXT_DT_TI3 ON KREW_DOC_HDR_EXT_DT_T(VAL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-603', '2.0.4-SNP', '3:4e34962f7e4fe478c4dcacce0e6a4fb2', 603);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-604::Jeff Caddel (generated)::(Checksum: 3:ae018869b78006e60aaa88ebef8d2a65)
CREATE INDEX KREW_DOC_HDR_EXT_FLT_TI1 ON KREW_DOC_HDR_EXT_FLT_T(KEY_CD, VAL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-604', '2.0.4-SNP', '3:ae018869b78006e60aaa88ebef8d2a65', 604);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-605::Jeff Caddel (generated)::(Checksum: 3:69266c66684da69a774fa26e7b373f47)
CREATE INDEX KREW_DOC_HDR_EXT_FLT_TI2 ON KREW_DOC_HDR_EXT_FLT_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-605', '2.0.4-SNP', '3:69266c66684da69a774fa26e7b373f47', 605);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-606::Jeff Caddel (generated)::(Checksum: 3:77e0e427ad4328f4b3d7982b5992b1c8)
CREATE INDEX KREW_DOC_HDR_EXT_FLT_TI3 ON KREW_DOC_HDR_EXT_FLT_T(VAL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-606', '2.0.4-SNP', '3:77e0e427ad4328f4b3d7982b5992b1c8', 606);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-607::Jeff Caddel (generated)::(Checksum: 3:e62f05067a7fb6436505aee310ba7a4b)
CREATE INDEX KREW_DOC_HDR_EXT_LONG_TI1 ON KREW_DOC_HDR_EXT_LONG_T(KEY_CD, VAL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-607', '2.0.4-SNP', '3:e62f05067a7fb6436505aee310ba7a4b', 607);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-608::Jeff Caddel (generated)::(Checksum: 3:75109f4743954fefe871babe546ef0da)
CREATE INDEX KREW_DOC_HDR_EXT_LONG_TI2 ON KREW_DOC_HDR_EXT_LONG_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-608', '2.0.4-SNP', '3:75109f4743954fefe871babe546ef0da', 608);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-609::Jeff Caddel (generated)::(Checksum: 3:5f744e5fc2e8e5c3e656b6b2657c75e8)
CREATE INDEX KREW_DOC_HDR_EXT_LONG_TI3 ON KREW_DOC_HDR_EXT_LONG_T(VAL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-609', '2.0.4-SNP', '3:5f744e5fc2e8e5c3e656b6b2657c75e8', 609);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-610::Jeff Caddel (generated)::(Checksum: 3:0119ebc04d76b6ba5daa1d4687affafe)
CREATE INDEX KREW_DOC_HDR_EXT_TI1 ON KREW_DOC_HDR_EXT_T(KEY_CD, VAL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-610', '2.0.4-SNP', '3:0119ebc04d76b6ba5daa1d4687affafe', 610);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-611::Jeff Caddel (generated)::(Checksum: 3:6868a840a16ab869e4968f7beb097c5a)
CREATE INDEX KREW_DOC_HDR_EXT_TI2 ON KREW_DOC_HDR_EXT_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-611', '2.0.4-SNP', '3:6868a840a16ab869e4968f7beb097c5a', 611);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-612::Jeff Caddel (generated)::(Checksum: 3:dcd805aa9d5845af2056405d24b2ed63)
CREATE INDEX KREW_DOC_HDR_EXT_TI3 ON KREW_DOC_HDR_EXT_T(VAL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-612', '2.0.4-SNP', '3:dcd805aa9d5845af2056405d24b2ed63', 612);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-613::Jeff Caddel (generated)::(Checksum: 3:67c982a4580a7db5983f4ccccaa2572f)
CREATE INDEX KREW_DOC_HDR_T10 ON KREW_DOC_HDR_T(APP_DOC_STAT) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-613', '2.0.4-SNP', '3:67c982a4580a7db5983f4ccccaa2572f', 613);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-614::Jeff Caddel (generated)::(Checksum: 3:0ae37fc319881cd7e4c792860fc80e68)
CREATE INDEX KREW_DOC_HDR_T12 ON KREW_DOC_HDR_T(APP_DOC_STAT_MDFN_DT) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-614', '2.0.4-SNP', '3:0ae37fc319881cd7e4c792860fc80e68', 614);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-615::Jeff Caddel (generated)::(Checksum: 3:83ec609e1bccfda7e0761ab6491532df)
CREATE INDEX KREW_DOC_HDR_TI1 ON KREW_DOC_HDR_T(DOC_TYP_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-615', '2.0.4-SNP', '3:83ec609e1bccfda7e0761ab6491532df', 615);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-616::Jeff Caddel (generated)::(Checksum: 3:d99617e92578500cfc751778ab4bedba)
CREATE INDEX KREW_DOC_HDR_TI2 ON KREW_DOC_HDR_T(INITR_PRNCPL_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-616', '2.0.4-SNP', '3:d99617e92578500cfc751778ab4bedba', 616);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-617::Jeff Caddel (generated)::(Checksum: 3:850b46fe6bc69c8c9358b6418a4677dc)
CREATE INDEX KREW_DOC_HDR_TI3 ON KREW_DOC_HDR_T(DOC_HDR_STAT_CD) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-617', '2.0.4-SNP', '3:850b46fe6bc69c8c9358b6418a4677dc', 617);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-618::Jeff Caddel (generated)::(Checksum: 3:cd0c0793aea17d66d74ea5adf08b744a)
CREATE INDEX KREW_DOC_HDR_TI4 ON KREW_DOC_HDR_T(TTL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-618', '2.0.4-SNP', '3:cd0c0793aea17d66d74ea5adf08b744a', 618);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-619::Jeff Caddel (generated)::(Checksum: 3:6a0fccb74a87dd58ea84233995ffd98a)
CREATE INDEX KREW_DOC_HDR_TI5 ON KREW_DOC_HDR_T(CRTE_DT) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-619', '2.0.4-SNP', '3:6a0fccb74a87dd58ea84233995ffd98a', 619);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-620::Jeff Caddel (generated)::(Checksum: 3:319fd455c5d597f30fa064af2242a127)
CREATE INDEX KREW_DOC_HDR_TI6 ON KREW_DOC_HDR_T(RTE_STAT_MDFN_DT) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-620', '2.0.4-SNP', '3:319fd455c5d597f30fa064af2242a127', 620);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-621::Jeff Caddel (generated)::(Checksum: 3:643b83652e08afe93d16af1a318edda3)
CREATE INDEX KREW_DOC_HDR_TI7 ON KREW_DOC_HDR_T(APRV_DT) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-621', '2.0.4-SNP', '3:643b83652e08afe93d16af1a318edda3', 621);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-622::Jeff Caddel (generated)::(Checksum: 3:88601b5ade2c312f78af490db0b13362)
CREATE INDEX KREW_DOC_HDR_TI8 ON KREW_DOC_HDR_T(FNL_DT) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-622', '2.0.4-SNP', '3:88601b5ade2c312f78af490db0b13362', 622);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-623::Jeff Caddel (generated)::(Checksum: 3:98f8e58b2b5f5f5620a4c201ee01ce8e)
CREATE INDEX KREW_DOC_HDR_TI9 ON KREW_DOC_HDR_T(APP_DOC_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-623', '2.0.4-SNP', '3:98f8e58b2b5f5f5620a4c201ee01ce8e', 623);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-624::Jeff Caddel (generated)::(Checksum: 3:cac90555f2f89263c9fbe6bf51a38312)
CREATE INDEX KREW_DOC_LNK_TI1 ON KREW_DOC_LNK_T(ORGN_DOC_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-624', '2.0.4-SNP', '3:cac90555f2f89263c9fbe6bf51a38312', 624);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-625::Jeff Caddel (generated)::(Checksum: 3:8529e5b1888740850587e9086f264b26)
CREATE INDEX KREW_DOC_NTE_TI1 ON KREW_DOC_NTE_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-625', '2.0.4-SNP', '3:8529e5b1888740850587e9086f264b26', 625);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-626::Jeff Caddel (generated)::(Checksum: 3:ff13b23067d81247765d036775b3e935)
CREATE INDEX KREW_DOC_TYP_APP_DOC_STAT_T1 ON KREW_DOC_TYP_APP_DOC_STAT_T(DOC_TYP_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-626', '2.0.4-SNP', '3:ff13b23067d81247765d036775b3e935', 626);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-627::Jeff Caddel (generated)::(Checksum: 3:2b541c7fbb48f5ac6604d1238cec02ac)
CREATE INDEX KREW_DOC_TYP_ATTR_TI1 ON KREW_DOC_TYP_ATTR_T(DOC_TYP_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-627', '2.0.4-SNP', '3:2b541c7fbb48f5ac6604d1238cec02ac', 627);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-628::Jeff Caddel (generated)::(Checksum: 3:033452642d2b643f73761f67648ebbc6)
CREATE INDEX KREW_DOC_TYP_PROC_TI1 ON KREW_DOC_TYP_PROC_T(DOC_TYP_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-628', '2.0.4-SNP', '3:033452642d2b643f73761f67648ebbc6', 628);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-629::Jeff Caddel (generated)::(Checksum: 3:9ebbea412d6a5ae70db8cf305aa4b171)
CREATE INDEX KREW_DOC_TYP_PROC_TI2 ON KREW_DOC_TYP_PROC_T(INIT_RTE_NODE_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-629', '2.0.4-SNP', '3:9ebbea412d6a5ae70db8cf305aa4b171', 629);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-630::Jeff Caddel (generated)::(Checksum: 3:cf65b3d5031fa5e91e8300918a8b220b)
CREATE INDEX KREW_DOC_TYP_PROC_TI3 ON KREW_DOC_TYP_PROC_T(NM) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-630', '2.0.4-SNP', '3:cf65b3d5031fa5e91e8300918a8b220b', 630);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-631::Jeff Caddel (generated)::(Checksum: 3:73c817dcfb1b4e8724e32c7671c300c9)
CREATE INDEX KREW_DOC_TYP_TI2 ON KREW_DOC_TYP_T(PARNT_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-631', '2.0.4-SNP', '3:73c817dcfb1b4e8724e32c7671c300c9', 631);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-632::Jeff Caddel (generated)::(Checksum: 3:ffbd03b6a3bee26aabf4c668fc302f40)
CREATE INDEX KREW_DOC_TYP_TI3 ON KREW_DOC_TYP_T(DOC_TYP_ID, PARNT_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-632', '2.0.4-SNP', '3:ffbd03b6a3bee26aabf4c668fc302f40', 632);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-633::Jeff Caddel (generated)::(Checksum: 3:0c4c799f8a249e6f6d8f137d4a909041)
CREATE INDEX KREW_DOC_TYP_TI4 ON KREW_DOC_TYP_T(PREV_DOC_TYP_VER_NBR) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-633', '2.0.4-SNP', '3:0c4c799f8a249e6f6d8f137d4a909041', 633);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-634::Jeff Caddel (generated)::(Checksum: 3:5adb22ca398a8e72f3473cec0b8b0904)
CREATE INDEX KREW_DOC_TYP_TI5 ON KREW_DOC_TYP_T(CUR_IND) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-634', '2.0.4-SNP', '3:5adb22ca398a8e72f3473cec0b8b0904', 634);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-635::Jeff Caddel (generated)::(Checksum: 3:a06d73ff842b0df3e890e07564229b58)
CREATE INDEX KREW_DOC_TYP_TI6 ON KREW_DOC_TYP_T(DOC_TYP_NM) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-635', '2.0.4-SNP', '3:a06d73ff842b0df3e890e07564229b58', 635);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-636::Jeff Caddel (generated)::(Checksum: 3:6f81f80b69cb0b05e0fc7a06d19ab4b1)
CREATE INDEX KREW_EDL_DMP_TI1 ON KREW_EDL_DMP_T(DOC_TYP_NM, DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-636', '2.0.4-SNP', '3:6f81f80b69cb0b05e0fc7a06d19ab4b1', 636);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-637::Jeff Caddel (generated)::(Checksum: 3:ba9bd7b345da6c28e5cadc69f8aeae7b)
CREATE INDEX KREW_HLP_TI1 ON KREW_HLP_T(KEY_CD) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-637', '2.0.4-SNP', '3:ba9bd7b345da6c28e5cadc69f8aeae7b', 637);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-638::Jeff Caddel (generated)::(Checksum: 3:230d467acb52a7a9b21b196121337f93)
CREATE INDEX KREW_INIT_RTE_NODE_INSTN_TI1 ON KREW_INIT_RTE_NODE_INSTN_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-638', '2.0.4-SNP', '3:230d467acb52a7a9b21b196121337f93', 638);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-639::Jeff Caddel (generated)::(Checksum: 3:339bd932ac906c398321abc536072aea)
CREATE INDEX KREW_INIT_RTE_NODE_INSTN_TI2 ON KREW_INIT_RTE_NODE_INSTN_T(RTE_NODE_INSTN_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-639', '2.0.4-SNP', '3:339bd932ac906c398321abc536072aea', 639);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-640::Jeff Caddel (generated)::(Checksum: 3:a0c220900a7abd20c0f34fd94051c91a)
CREATE INDEX KREW_OUT_BOX_ITM_TI1 ON KREW_OUT_BOX_ITM_T(PRNCPL_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-640', '2.0.4-SNP', '3:a0c220900a7abd20c0f34fd94051c91a', 640);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-641::Jeff Caddel (generated)::(Checksum: 3:79318b769a277ac4ab6af7c9f57ff5a9)
CREATE INDEX KREW_OUT_BOX_ITM_TI2 ON KREW_OUT_BOX_ITM_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-641', '2.0.4-SNP', '3:79318b769a277ac4ab6af7c9f57ff5a9', 641);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-642::Jeff Caddel (generated)::(Checksum: 3:8841c0b55afa169189ac21764f37c222)
CREATE INDEX KREW_OUT_BOX_ITM_TI3 ON KREW_OUT_BOX_ITM_T(ACTN_RQST_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-642', '2.0.4-SNP', '3:8841c0b55afa169189ac21764f37c222', 642);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-643::Jeff Caddel (generated)::(Checksum: 3:44ad1e365b80673205fabe357b05b35e)
CREATE INDEX KREW_RTE_BRCH_PROTO_TI1 ON KREW_RTE_BRCH_PROTO_T(BRCH_NM) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-643', '2.0.4-SNP', '3:44ad1e365b80673205fabe357b05b35e', 643);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-644::Jeff Caddel (generated)::(Checksum: 3:dbefe1571270df3bc0b7a1be83b9438c)
CREATE INDEX KREW_RTE_BRCH_ST_TI1 ON KREW_RTE_BRCH_ST_T(RTE_BRCH_ID, KEY_CD) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-644', '2.0.4-SNP', '3:dbefe1571270df3bc0b7a1be83b9438c', 644);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-645::Jeff Caddel (generated)::(Checksum: 3:8d519b935fb5aae10370f44d5b8225d4)
CREATE INDEX KREW_RTE_BRCH_ST_TI2 ON KREW_RTE_BRCH_ST_T(RTE_BRCH_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-645', '2.0.4-SNP', '3:8d519b935fb5aae10370f44d5b8225d4', 645);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-646::Jeff Caddel (generated)::(Checksum: 3:1c6fa7ab31e0bcb9d1840c251d48894b)
CREATE INDEX KREW_RTE_BRCH_ST_TI3 ON KREW_RTE_BRCH_ST_T(KEY_CD, VAL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-646', '2.0.4-SNP', '3:1c6fa7ab31e0bcb9d1840c251d48894b', 646);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-647::Jeff Caddel (generated)::(Checksum: 3:48d1d11b44926c37bc03fc35d3cebdea)
CREATE INDEX KREW_RTE_BRCH_TI1 ON KREW_RTE_BRCH_T(NM) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-647', '2.0.4-SNP', '3:48d1d11b44926c37bc03fc35d3cebdea', 647);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-648::Jeff Caddel (generated)::(Checksum: 3:365aefd1503c7756bd53b7f9b818bd18)
CREATE INDEX KREW_RTE_BRCH_TI2 ON KREW_RTE_BRCH_T(PARNT_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-648', '2.0.4-SNP', '3:365aefd1503c7756bd53b7f9b818bd18', 648);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-649::Jeff Caddel (generated)::(Checksum: 3:80d8157bebbd144db8b0a186e79a49f5)
CREATE INDEX KREW_RTE_BRCH_TI3 ON KREW_RTE_BRCH_T(INIT_RTE_NODE_INSTN_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-649', '2.0.4-SNP', '3:80d8157bebbd144db8b0a186e79a49f5', 649);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-650::Jeff Caddel (generated)::(Checksum: 3:6084e434b095dd2b14a772426ae9e22b)
CREATE INDEX KREW_RTE_BRCH_TI4 ON KREW_RTE_BRCH_T(SPLT_RTE_NODE_INSTN_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-650', '2.0.4-SNP', '3:6084e434b095dd2b14a772426ae9e22b', 650);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-651::Jeff Caddel (generated)::(Checksum: 3:93609ee5f4d666a88ef91c7af7248401)
CREATE INDEX KREW_RTE_BRCH_TI5 ON KREW_RTE_BRCH_T(JOIN_RTE_NODE_INSTN_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-651', '2.0.4-SNP', '3:93609ee5f4d666a88ef91c7af7248401', 651);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-652::Jeff Caddel (generated)::(Checksum: 3:2d7e949fb17a10d1b87ff3cb07bbd169)
CREATE INDEX KREW_RTE_NODE_INSTN_LNK_TI1 ON KREW_RTE_NODE_INSTN_LNK_T(FROM_RTE_NODE_INSTN_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-652', '2.0.4-SNP', '3:2d7e949fb17a10d1b87ff3cb07bbd169', 652);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-653::Jeff Caddel (generated)::(Checksum: 3:b2f70ccac34d8941478b7cfa8a6d215c)
CREATE INDEX KREW_RTE_NODE_INSTN_LNK_TI2 ON KREW_RTE_NODE_INSTN_LNK_T(TO_RTE_NODE_INSTN_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-653', '2.0.4-SNP', '3:b2f70ccac34d8941478b7cfa8a6d215c', 653);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-654::Jeff Caddel (generated)::(Checksum: 3:e8133db477f11a9af0978c07a1878f90)
CREATE INDEX KREW_RTE_NODE_INSTN_ST_TI1 ON KREW_RTE_NODE_INSTN_ST_T(RTE_NODE_INSTN_ID, KEY_CD) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-654', '2.0.4-SNP', '3:e8133db477f11a9af0978c07a1878f90', 654);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-655::Jeff Caddel (generated)::(Checksum: 3:0b56af46bfe56d395085ac3021a8c56d)
CREATE INDEX KREW_RTE_NODE_INSTN_ST_TI2 ON KREW_RTE_NODE_INSTN_ST_T(RTE_NODE_INSTN_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-655', '2.0.4-SNP', '3:0b56af46bfe56d395085ac3021a8c56d', 655);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-656::Jeff Caddel (generated)::(Checksum: 3:1736579240ff6514ab38a4453fa274a2)
CREATE INDEX KREW_RTE_NODE_INSTN_ST_TI3 ON KREW_RTE_NODE_INSTN_ST_T(KEY_CD, VAL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-656', '2.0.4-SNP', '3:1736579240ff6514ab38a4453fa274a2', 656);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-657::Jeff Caddel (generated)::(Checksum: 3:99d5b549045a843cb0e7ef32a0b8d9ac)
CREATE INDEX KREW_RTE_NODE_INSTN_TI1 ON KREW_RTE_NODE_INSTN_T(DOC_HDR_ID, ACTV_IND, CMPLT_IND) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-657', '2.0.4-SNP', '3:99d5b549045a843cb0e7ef32a0b8d9ac', 657);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-658::Jeff Caddel (generated)::(Checksum: 3:bcddac068f3f56904da40b93152e21bf)
CREATE INDEX KREW_RTE_NODE_INSTN_TI2 ON KREW_RTE_NODE_INSTN_T(RTE_NODE_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-658', '2.0.4-SNP', '3:bcddac068f3f56904da40b93152e21bf', 658);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-659::Jeff Caddel (generated)::(Checksum: 3:a54a0aa3b666f96248966afb3835993d)
CREATE INDEX KREW_RTE_NODE_INSTN_TI3 ON KREW_RTE_NODE_INSTN_T(BRCH_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-659', '2.0.4-SNP', '3:a54a0aa3b666f96248966afb3835993d', 659);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-660::Jeff Caddel (generated)::(Checksum: 3:950fe194bd5b9fe83dad3590b43bc85c)
CREATE INDEX KREW_RTE_NODE_INSTN_TI4 ON KREW_RTE_NODE_INSTN_T(PROC_RTE_NODE_INSTN_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-660', '2.0.4-SNP', '3:950fe194bd5b9fe83dad3590b43bc85c', 660);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-661::Jeff Caddel (generated)::(Checksum: 3:5b6f678dbfb12d9b1defd49fbcf3a2a6)
CREATE INDEX KREW_RTE_NODE_LNK_TI1 ON KREW_RTE_NODE_LNK_T(FROM_RTE_NODE_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-661', '2.0.4-SNP', '3:5b6f678dbfb12d9b1defd49fbcf3a2a6', 661);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-662::Jeff Caddel (generated)::(Checksum: 3:6c0e6b61faa4e5a08d992bcdc90b4dde)
CREATE INDEX KREW_RTE_NODE_LNK_TI2 ON KREW_RTE_NODE_LNK_T(TO_RTE_NODE_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-662', '2.0.4-SNP', '3:6c0e6b61faa4e5a08d992bcdc90b4dde', 662);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-663::Jeff Caddel (generated)::(Checksum: 3:8d4ad60606a5b9bd51ca7d0ad485cd94)
CREATE INDEX KREW_RTE_NODE_TI1 ON KREW_RTE_NODE_T(NM, DOC_TYP_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-663', '2.0.4-SNP', '3:8d4ad60606a5b9bd51ca7d0ad485cd94', 663);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-664::Jeff Caddel (generated)::(Checksum: 3:866044a6f1e9c51c99618de139318c9c)
CREATE INDEX KREW_RTE_NODE_TI2 ON KREW_RTE_NODE_T(DOC_TYP_ID, FNL_APRVR_IND) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-664', '2.0.4-SNP', '3:866044a6f1e9c51c99618de139318c9c', 664);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-665::Jeff Caddel (generated)::(Checksum: 3:d0be28ec7984b8fa377eef3e2b741edd)
CREATE INDEX KREW_RTE_NODE_TI3 ON KREW_RTE_NODE_T(BRCH_PROTO_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-665', '2.0.4-SNP', '3:d0be28ec7984b8fa377eef3e2b741edd', 665);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-666::Jeff Caddel (generated)::(Checksum: 3:f7683112a2b507efbee42355938b06f2)
CREATE INDEX KREW_RTE_NODE_TI4 ON KREW_RTE_NODE_T(DOC_TYP_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-666', '2.0.4-SNP', '3:f7683112a2b507efbee42355938b06f2', 666);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-667::Jeff Caddel (generated)::(Checksum: 3:aece3ad568f21330b27e088efda969e0)
CREATE INDEX KREW_RULE_EXT_T1 ON KREW_RULE_EXT_T(RULE_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-667', '2.0.4-SNP', '3:aece3ad568f21330b27e088efda969e0', 667);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-668::Jeff Caddel (generated)::(Checksum: 3:6bbbe6aecc6557deb7f009c146bff7ba)
CREATE INDEX KREW_RULE_EXT_VAL_T1 ON KREW_RULE_EXT_VAL_T(RULE_EXT_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-668', '2.0.4-SNP', '3:6bbbe6aecc6557deb7f009c146bff7ba', 668);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-669::Jeff Caddel (generated)::(Checksum: 3:77409b7726fbf288f83dfd9698b33cc5)
CREATE INDEX KREW_RULE_EXT_VAL_T2 ON KREW_RULE_EXT_VAL_T(RULE_EXT_VAL_ID, KEY_CD) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-669', '2.0.4-SNP', '3:77409b7726fbf288f83dfd9698b33cc5', 669);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-670::Jeff Caddel (generated)::(Checksum: 3:befa0698aa2044f5e207af0e1ec47c87)
CREATE INDEX KREW_RULE_RSP_TI1 ON KREW_RULE_RSP_T(RULE_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-670', '2.0.4-SNP', '3:befa0698aa2044f5e207af0e1ec47c87', 670);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-671::Jeff Caddel (generated)::(Checksum: 3:c29fa8fb56990a414755d72cb7b9a373)
CREATE INDEX KREW_RULE_TMPL_ATTR_TI1 ON KREW_RULE_TMPL_ATTR_T(RULE_TMPL_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-671', '2.0.4-SNP', '3:c29fa8fb56990a414755d72cb7b9a373', 671);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-672::Jeff Caddel (generated)::(Checksum: 3:de2fc219dc15fc91b9c8aec6c29cf78f)
CREATE INDEX KREW_RULE_TMPL_ATTR_TI2 ON KREW_RULE_TMPL_ATTR_T(RULE_ATTR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-672', '2.0.4-SNP', '3:de2fc219dc15fc91b9c8aec6c29cf78f', 672);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-673::Jeff Caddel (generated)::(Checksum: 3:7ce65acd432bcf51c734ea974c392bbb)
CREATE INDEX KREW_USR_OPTN_TI1 ON KREW_USR_OPTN_T(PRNCPL_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-673', '2.0.4-SNP', '3:7ce65acd432bcf51c734ea974c392bbb', 673);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-674::Jeff Caddel (generated)::(Checksum: 3:b79481021417ca20c6ecf872ed0f71ac)
CREATE INDEX KRIM_ENTITY_ADDR_TI1 ON KRIM_ENTITY_ADDR_T(ENTITY_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-674', '2.0.4-SNP', '3:b79481021417ca20c6ecf872ed0f71ac', 674);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-675::Jeff Caddel (generated)::(Checksum: 3:b51d27da7bf71fbede56999ca291928a)
CREATE INDEX KRIM_ENTITY_EMAIL_TI1 ON KRIM_ENTITY_EMAIL_T(ENTITY_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-675', '2.0.4-SNP', '3:b51d27da7bf71fbede56999ca291928a', 675);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-676::Jeff Caddel (generated)::(Checksum: 3:d9bf7a539da0ece40e638a8c2b4e7cca)
CREATE INDEX KRIM_ENTITY_PHONE_TI1 ON KRIM_ENTITY_PHONE_T(ENTITY_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-676', '2.0.4-SNP', '3:d9bf7a539da0ece40e638a8c2b4e7cca', 676);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-677::Jeff Caddel (generated)::(Checksum: 3:74c792a9dbc5ed11e4bedd6f80f0b7bd)
CREATE INDEX KRIM_GRP_MBR_TI1 ON KRIM_GRP_MBR_T(MBR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-677', '2.0.4-SNP', '3:74c792a9dbc5ed11e4bedd6f80f0b7bd', 677);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-678::Jeff Caddel (generated)::(Checksum: 3:d3c8a792372c4769d1c45a05a113cca6)
CREATE INDEX KRIM_ROLE_MBR_ATTR_DATA_TI1 ON KRIM_ROLE_MBR_ATTR_DATA_T(ROLE_MBR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-678', '2.0.4-SNP', '3:d3c8a792372c4769d1c45a05a113cca6', 678);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-679::Jeff Caddel (generated)::(Checksum: 3:1ccca008a47989e14ff71b2176c16661)
CREATE INDEX KRIM_ROLE_MBR_TI1 ON KRIM_ROLE_MBR_T(MBR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-679', '2.0.4-SNP', '3:1ccca008a47989e14ff71b2176c16661', 679);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-680::Jeff Caddel (generated)::(Checksum: 3:394945367720c1af1e3539bb2c7543ae)
CREATE INDEX KRMS_ACTN_TI1 ON KRMS_ACTN_T(TYP_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-680', '2.0.4-SNP', '3:394945367720c1af1e3539bb2c7543ae', 680);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-681::Jeff Caddel (generated)::(Checksum: 3:2c961ffaa5777ae5aa208c0c42162938)
CREATE INDEX KRMS_ACTN_TI3 ON KRMS_ACTN_T(RULE_ID, SEQ_NO) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-681', '2.0.4-SNP', '3:2c961ffaa5777ae5aa208c0c42162938', 681);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-682::Jeff Caddel (generated)::(Checksum: 3:f862cbcd5550b7164582eb55da142e62)
CREATE INDEX KRNS_ADHOC_RTE_ACTN_RECIP_T2 ON KRNS_ADHOC_RTE_ACTN_RECIP_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-682', '2.0.4-SNP', '3:f862cbcd5550b7164582eb55da142e62', 682);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-683::Jeff Caddel (generated)::(Checksum: 3:f04adb52160145e095bfdc256402e199)
CREATE INDEX KRNS_DOC_HDR_TI3 ON KRNS_DOC_HDR_T(ORG_DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-683', '2.0.4-SNP', '3:f04adb52160145e095bfdc256402e199', 683);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-684::Jeff Caddel (generated)::(Checksum: 3:86b70d49df4d47959b477ce4a4165be9)
CREATE INDEX KRNS_MAINT_LOCK_TI2 ON KRNS_MAINT_LOCK_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-684', '2.0.4-SNP', '3:86b70d49df4d47959b477ce4a4165be9', 684);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-685::Jeff Caddel (generated)::(Checksum: 3:8c61fc48392f955350089b3eae83480a)
CREATE INDEX KRNS_PESSIMISTIC_LOCK_TI1 ON KRNS_PESSIMISTIC_LOCK_T(DOC_HDR_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-685', '2.0.4-SNP', '3:8c61fc48392f955350089b3eae83480a', 685);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-686::Jeff Caddel (generated)::(Checksum: 3:e929d8d2f3d208a2f1849b10c3d57793)
CREATE INDEX KRNS_PESSIMISTIC_LOCK_TI2 ON KRNS_PESSIMISTIC_LOCK_T(PRNCPL_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-686', '2.0.4-SNP', '3:e929d8d2f3d208a2f1849b10c3d57793', 686);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-687::Jeff Caddel (generated)::(Checksum: 3:336f38e2c3446861f6dd447eadcd6fce)
CREATE INDEX KRNS_SESN_DOC_TI1 ON KRNS_SESN_DOC_T(LAST_UPDT_DT) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-687', '2.0.4-SNP', '3:336f38e2c3446861f6dd447eadcd6fce', 687);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-688::Jeff Caddel (generated)::(Checksum: 3:d934d26387b623c1be43b4b76560356d)
CREATE INDEX KREW_BAM_PARM_TI1 ON KRSB_BAM_PARM_T(BAM_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-688', '2.0.4-SNP', '3:d934d26387b623c1be43b4b76560356d', 688);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-689::Jeff Caddel (generated)::(Checksum: 3:f207caa58c5444986b3c66c2e3f77bbd)
CREATE INDEX KRSB_BAM_TI1 ON KRSB_BAM_T(SVC_NM, MTHD_NM) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-689', '2.0.4-SNP', '3:f207caa58c5444986b3c66c2e3f77bbd', 689);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-690::Jeff Caddel (generated)::(Checksum: 3:53653261d92511f0b7efa57464ea2175)
CREATE INDEX KRSB_BAM_TI2 ON KRSB_BAM_T(SVC_NM) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-690', '2.0.4-SNP', '3:53653261d92511f0b7efa57464ea2175', 690);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-691::Jeff Caddel (generated)::(Checksum: 3:f22594d64fcfb30976fd55e920b89ac5)
CREATE INDEX KRSB_MSG_QUE_TI1 ON KRSB_MSG_QUE_T(SVC_NM, SVC_MTHD_NM) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-691', '2.0.4-SNP', '3:f22594d64fcfb30976fd55e920b89ac5', 691);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-692::Jeff Caddel (generated)::(Checksum: 3:869aa9cdee5796c4ef7c90d38901af2d)
CREATE INDEX KRSB_MSG_QUE_TI2 ON KRSB_MSG_QUE_T(APPL_ID, STAT_CD, IP_NBR, DT) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-692', '2.0.4-SNP', '3:869aa9cdee5796c4ef7c90d38901af2d', 692);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-693::Jeff Caddel (generated)::(Checksum: 3:1fbf1a0c060a01ca65e02f9b815ddfdd)
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI1 ON KRSB_QRTZ_FIRED_TRIGGERS(JOB_GROUP) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-693', '2.0.4-SNP', '3:1fbf1a0c060a01ca65e02f9b815ddfdd', 693);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-694::Jeff Caddel (generated)::(Checksum: 3:4d2d9e9208ad30b35f75a8729b6aa809)
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI2 ON KRSB_QRTZ_FIRED_TRIGGERS(JOB_NAME) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-694', '2.0.4-SNP', '3:4d2d9e9208ad30b35f75a8729b6aa809', 694);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-695::Jeff Caddel (generated)::(Checksum: 3:a48c66369a032222c0519bd68ebbaaaa)
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI3 ON KRSB_QRTZ_FIRED_TRIGGERS(REQUESTS_RECOVERY) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-695', '2.0.4-SNP', '3:a48c66369a032222c0519bd68ebbaaaa', 695);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-696::Jeff Caddel (generated)::(Checksum: 3:36803fdc6ec20c8a95074fbe935c9f88)
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI4 ON KRSB_QRTZ_FIRED_TRIGGERS(IS_STATEFUL) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-696', '2.0.4-SNP', '3:36803fdc6ec20c8a95074fbe935c9f88', 696);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-697::Jeff Caddel (generated)::(Checksum: 3:912c98a06820e8e1e2e35ae49bb415c0)
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI5 ON KRSB_QRTZ_FIRED_TRIGGERS(TRIGGER_GROUP) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-697', '2.0.4-SNP', '3:912c98a06820e8e1e2e35ae49bb415c0', 697);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-698::Jeff Caddel (generated)::(Checksum: 3:cae2ad7208eab019818fa6916f93a326)
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI6 ON KRSB_QRTZ_FIRED_TRIGGERS(INSTANCE_NAME) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-698', '2.0.4-SNP', '3:cae2ad7208eab019818fa6916f93a326', 698);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-699::Jeff Caddel (generated)::(Checksum: 3:8ecce32b46808dc989eda7bbfeda5a98)
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI7 ON KRSB_QRTZ_FIRED_TRIGGERS(TRIGGER_NAME) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-699', '2.0.4-SNP', '3:8ecce32b46808dc989eda7bbfeda5a98', 699);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-700::Jeff Caddel (generated)::(Checksum: 3:12541fe771e560aaa1d18acc9f479b9a)
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI8 ON KRSB_QRTZ_FIRED_TRIGGERS(TRIGGER_NAME, TRIGGER_GROUP) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-700', '2.0.4-SNP', '3:12541fe771e560aaa1d18acc9f479b9a', 700);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-701::Jeff Caddel (generated)::(Checksum: 3:48b4737aa5608c0cbc30a8ef8cd53074)
CREATE INDEX KRSB_QRTZ_FIRED_TRIGGERS_TI9 ON KRSB_QRTZ_FIRED_TRIGGERS(IS_VOLATILE) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-701', '2.0.4-SNP', '3:48b4737aa5608c0cbc30a8ef8cd53074', 701);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-702::Jeff Caddel (generated)::(Checksum: 3:b956250b296bdec29b09ee5f6dfb85c6)
CREATE INDEX KRSB_QRTZ_JOB_DETAILS_TI1 ON KRSB_QRTZ_JOB_DETAILS(REQUESTS_RECOVERY) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-702', '2.0.4-SNP', '3:b956250b296bdec29b09ee5f6dfb85c6', 702);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-703::Jeff Caddel (generated)::(Checksum: 3:f7e3f56983f9a1d4e45cda350b5a793b)
CREATE INDEX KRSB_QRTZ_TRIGGERS_TI1 ON KRSB_QRTZ_TRIGGERS(NEXT_FIRE_TIME) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-703', '2.0.4-SNP', '3:f7e3f56983f9a1d4e45cda350b5a793b', 703);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-704::Jeff Caddel (generated)::(Checksum: 3:28ca93eacaf03ba8d9a3e15a964714e6)
CREATE INDEX KRSB_QRTZ_TRIGGERS_TI2 ON KRSB_QRTZ_TRIGGERS(NEXT_FIRE_TIME, TRIGGER_STATE) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-704', '2.0.4-SNP', '3:28ca93eacaf03ba8d9a3e15a964714e6', 704);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-705::Jeff Caddel (generated)::(Checksum: 3:98db8d350ee0d36a8468d4e0f4ce3394)
CREATE INDEX KRSB_QRTZ_TRIGGERS_TI3 ON KRSB_QRTZ_TRIGGERS(TRIGGER_STATE) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-705', '2.0.4-SNP', '3:98db8d350ee0d36a8468d4e0f4ce3394', 705);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-706::Jeff Caddel (generated)::(Checksum: 3:28e6e50d8cec16114f80981f462a08b1)
CREATE INDEX KRSB_QRTZ_TRIGGERS_TI4 ON KRSB_QRTZ_TRIGGERS(IS_VOLATILE) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-706', '2.0.4-SNP', '3:28e6e50d8cec16114f80981f462a08b1', 706);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-707::Jeff Caddel (generated)::(Checksum: 3:20a2ae2a6696df01e845ab8c4c4dc871)
CREATE INDEX KRSB_SVC_DEF_TI1 ON KRSB_SVC_DEF_T(INSTN_ID) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-707', '2.0.4-SNP', '3:20a2ae2a6696df01e845ab8c4c4dc871', 707);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-708::Jeff Caddel (generated)::(Checksum: 3:78e77a300304f5ceeae692d78cb7dc87)
CREATE INDEX KRSB_SVC_DEF_TI2 ON KRSB_SVC_DEF_T(SVC_NM, STAT_CD) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-708', '2.0.4-SNP', '3:78e77a300304f5ceeae692d78cb7dc87', 708);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-709::Jeff Caddel (generated)::(Checksum: 3:5019744e6b0716a387443b7bc1c52a11)
CREATE INDEX KRSB_SVC_DEF_TI3 ON KRSB_SVC_DEF_T(STAT_CD) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-709', '2.0.4-SNP', '3:5019744e6b0716a387443b7bc1c52a11', 709);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-710::Jeff Caddel (generated)::(Checksum: 3:4663552ba502ecd09e82c2d092cc26c9)
CREATE INDEX KSEM_ENUM_VAL_I2 ON KSEM_ENUM_VAL_T(CD) TABLESPACE USERS;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Index', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-710', '2.0.4-SNP', '3:4663552ba502ecd09e82c2d092cc26c9', 710);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-711::Jeff Caddel (generated)::(Checksum: 3:e36a57f62a3f5a3ab01e0f8840edc425)
ALTER TABLE KSEMBEDDED.KRCR_PARM_DTL_TYP_T ADD CONSTRAINT KRNS_PARM_DTL_TYP_TR1 FOREIGN KEY (NMSPC_CD) REFERENCES KSEMBEDDED.KRCR_NMSPC_T (NMSPC_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-711', '2.0.4-SNP', '3:e36a57f62a3f5a3ab01e0f8840edc425', 711);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-712::Jeff Caddel (generated)::(Checksum: 3:fb6efc7b10d11bac28bc24e83198cd38)
ALTER TABLE KSEMBEDDED.KRCR_PARM_T ADD CONSTRAINT KRNS_PARM_TR1 FOREIGN KEY (NMSPC_CD) REFERENCES KSEMBEDDED.KRCR_NMSPC_T (NMSPC_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-712', '2.0.4-SNP', '3:fb6efc7b10d11bac28bc24e83198cd38', 712);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-713::Jeff Caddel (generated)::(Checksum: 3:49b29e053eaf81a0731bf8b8576916b2)
ALTER TABLE KSEMBEDDED.KRCR_PARM_T ADD CONSTRAINT KRNS_PARM_TR2 FOREIGN KEY (PARM_TYP_CD) REFERENCES KSEMBEDDED.KRCR_PARM_TYP_T (PARM_TYP_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-713', '2.0.4-SNP', '3:49b29e053eaf81a0731bf8b8576916b2', 713);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-714::Jeff Caddel (generated)::(Checksum: 3:a55df90c73bfebbb5f5d7b5b4ca69d68)
ALTER TABLE KSEMBEDDED.KREN_CHNL_PRODCR_T ADD CONSTRAINT KREN_CHNL_PRODCR_FK1 FOREIGN KEY (CHNL_ID) REFERENCES KSEMBEDDED.KREN_CHNL_T (CHNL_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-714', '2.0.4-SNP', '3:a55df90c73bfebbb5f5d7b5b4ca69d68', 714);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-715::Jeff Caddel (generated)::(Checksum: 3:2bd1e7a79c6708386eaed746fa700eb8)
ALTER TABLE KSEMBEDDED.KREN_CHNL_PRODCR_T ADD CONSTRAINT KREN_CHNL_PRODCR_FK2 FOREIGN KEY (PRODCR_ID) REFERENCES KSEMBEDDED.KREN_PRODCR_T (PRODCR_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-715', '2.0.4-SNP', '3:2bd1e7a79c6708386eaed746fa700eb8', 715);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-716::Jeff Caddel (generated)::(Checksum: 3:e64e6e2037d4aec2d3da84c5d713dba6)
ALTER TABLE KSEMBEDDED.KREN_CHNL_SUBSCRP_T ADD CONSTRAINT KREN_CHAN_SUBSCRP_FK1 FOREIGN KEY (CHNL_ID) REFERENCES KSEMBEDDED.KREN_CHNL_T (CHNL_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-716', '2.0.4-SNP', '3:e64e6e2037d4aec2d3da84c5d713dba6', 716);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-717::Jeff Caddel (generated)::(Checksum: 3:c870ccdb4983c54cbed5d49de3b2b415)
ALTER TABLE KSEMBEDDED.KREN_MSG_DELIV_T ADD CONSTRAINT KREN_MSG_DELIV_FK1 FOREIGN KEY (MSG_ID) REFERENCES KSEMBEDDED.KREN_MSG_T (MSG_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-717', '2.0.4-SNP', '3:c870ccdb4983c54cbed5d49de3b2b415', 717);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-718::Jeff Caddel (generated)::(Checksum: 3:9baeaa1127a4fb69f2829214bf358399)
ALTER TABLE KSEMBEDDED.KREN_NTFCTN_MSG_DELIV_T ADD CONSTRAINT KREN_NTFCTN_MSG_DELIV_FK1 FOREIGN KEY (NTFCTN_ID) REFERENCES KSEMBEDDED.KREN_NTFCTN_T (NTFCTN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-718', '2.0.4-SNP', '3:9baeaa1127a4fb69f2829214bf358399', 718);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-719::Jeff Caddel (generated)::(Checksum: 3:b274e626fc7d8e02f1dd43cb8e0283cb)
ALTER TABLE KSEMBEDDED.KREN_NTFCTN_T ADD CONSTRAINT KREN_NTFCTN_FK1 FOREIGN KEY (CHNL_ID) REFERENCES KSEMBEDDED.KREN_CHNL_T (CHNL_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-719', '2.0.4-SNP', '3:b274e626fc7d8e02f1dd43cb8e0283cb', 719);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-720::Jeff Caddel (generated)::(Checksum: 3:3400e2df82e4d5a73653d3b559e04b8e)
ALTER TABLE KSEMBEDDED.KREN_NTFCTN_T ADD CONSTRAINT KREN_NTFCTN_FK2 FOREIGN KEY (CNTNT_TYP_ID) REFERENCES KSEMBEDDED.KREN_CNTNT_TYP_T (CNTNT_TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-720', '2.0.4-SNP', '3:3400e2df82e4d5a73653d3b559e04b8e', 720);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-721::Jeff Caddel (generated)::(Checksum: 3:5e2fdd5d2c005f405c0227e7aa563815)
ALTER TABLE KSEMBEDDED.KREN_NTFCTN_T ADD CONSTRAINT KREN_NTFCTN_FK3 FOREIGN KEY (PRIO_ID) REFERENCES KSEMBEDDED.KREN_PRIO_T (PRIO_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-721', '2.0.4-SNP', '3:5e2fdd5d2c005f405c0227e7aa563815', 721);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-722::Jeff Caddel (generated)::(Checksum: 3:68dc7191ab8a8606ee09edf48d2dfba8)
ALTER TABLE KSEMBEDDED.KREN_NTFCTN_T ADD CONSTRAINT KREN_NTFCTN_FK4 FOREIGN KEY (PRODCR_ID) REFERENCES KSEMBEDDED.KREN_PRODCR_T (PRODCR_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-722', '2.0.4-SNP', '3:68dc7191ab8a8606ee09edf48d2dfba8', 722);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-723::Jeff Caddel (generated)::(Checksum: 3:b34af3ed6d5db75cd371edad860f6b3d)
ALTER TABLE KSEMBEDDED.KREN_RECIP_LIST_T ADD CONSTRAINT KREN_RECIP_LIST_FK1 FOREIGN KEY (CHNL_ID) REFERENCES KSEMBEDDED.KREN_CHNL_T (CHNL_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-723', '2.0.4-SNP', '3:b34af3ed6d5db75cd371edad860f6b3d', 723);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-724::Jeff Caddel (generated)::(Checksum: 3:c3c8b24cd8ffe97741811750e36ee041)
ALTER TABLE KSEMBEDDED.KREN_RECIP_T ADD CONSTRAINT KREN_RECIP_FK1 FOREIGN KEY (NTFCTN_ID) REFERENCES KSEMBEDDED.KREN_NTFCTN_T (NTFCTN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-724', '2.0.4-SNP', '3:c3c8b24cd8ffe97741811750e36ee041', 724);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-725::Jeff Caddel (generated)::(Checksum: 3:6e3719e023a975f5e7d94295db01be63)
ALTER TABLE KSEMBEDDED.KREN_RVWER_T ADD CONSTRAINT NOTIFICATION_REVIEWERS_N_FK1 FOREIGN KEY (CHNL_ID) REFERENCES KSEMBEDDED.KREN_CHNL_T (CHNL_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-725', '2.0.4-SNP', '3:6e3719e023a975f5e7d94295db01be63', 725);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-726::Jeff Caddel (generated)::(Checksum: 3:4cbf0edb776ec340725d3c8a9ae5e4a8)
ALTER TABLE KSEMBEDDED.KREN_SNDR_T ADD CONSTRAINT KREN_SNDR_FK1 FOREIGN KEY (NTFCTN_ID) REFERENCES KSEMBEDDED.KREN_NTFCTN_T (NTFCTN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-726', '2.0.4-SNP', '3:4cbf0edb776ec340725d3c8a9ae5e4a8', 726);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-727::Jeff Caddel (generated)::(Checksum: 3:e8e06e2d82e626d6839b4841eec7343e)
ALTER TABLE KSEMBEDDED.KREW_RTE_NODE_CFG_PARM_T ADD CONSTRAINT EN_RTE_NODE_CFG_PARM_TR1 FOREIGN KEY (RTE_NODE_ID) REFERENCES KSEMBEDDED.KREW_RTE_NODE_T (RTE_NODE_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-727', '2.0.4-SNP', '3:e8e06e2d82e626d6839b4841eec7343e', 727);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-728::Jeff Caddel (generated)::(Checksum: 3:f04b086c77100fcb9be46fee3efcd502)
ALTER TABLE KSEMBEDDED.KREW_RULE_T ADD CONSTRAINT KREW_RULE_TR1 FOREIGN KEY (RULE_EXPR_ID) REFERENCES KSEMBEDDED.KREW_RULE_EXPR_T (RULE_EXPR_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-728', '2.0.4-SNP', '3:f04b086c77100fcb9be46fee3efcd502', 728);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-729::Jeff Caddel (generated)::(Checksum: 3:fe6d2ade89f3a5ac517118d986a52f3d)
ALTER TABLE KSEMBEDDED.KRIM_DLGN_MBR_ATTR_DATA_T ADD CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-729', '2.0.4-SNP', '3:fe6d2ade89f3a5ac517118d986a52f3d', 729);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-730::Jeff Caddel (generated)::(Checksum: 3:02182e3e3c8cc5c495eaf7681d33d82c)
ALTER TABLE KSEMBEDDED.KRIM_DLGN_MBR_ATTR_DATA_T ADD CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID) REFERENCES KSEMBEDDED.KRIM_TYP_T (KIM_TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-730', '2.0.4-SNP', '3:02182e3e3c8cc5c495eaf7681d33d82c', 730);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-731::Jeff Caddel (generated)::(Checksum: 3:dcec14d3aab895ec553306f440735b88)
ALTER TABLE KSEMBEDDED.KRIM_DLGN_MBR_T ADD CONSTRAINT KRIM_DLGN_MBR_TR2 FOREIGN KEY (DLGN_ID) REFERENCES KSEMBEDDED.KRIM_DLGN_T (DLGN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-731', '2.0.4-SNP', '3:dcec14d3aab895ec553306f440735b88', 731);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-732::Jeff Caddel (generated)::(Checksum: 3:c2ae7ae4cc69c2e19bf8284c09c9052a)
ALTER TABLE KSEMBEDDED.KRIM_DLGN_T ADD CONSTRAINT KRIM_DLGN_TR2 FOREIGN KEY (KIM_TYP_ID) REFERENCES KSEMBEDDED.KRIM_TYP_T (KIM_TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-732', '2.0.4-SNP', '3:c2ae7ae4cc69c2e19bf8284c09c9052a', 732);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-733::Jeff Caddel (generated)::(Checksum: 3:9602cde17aae3d7a567dd785974a8869)
ALTER TABLE KSEMBEDDED.KRIM_DLGN_T ADD CONSTRAINT KRIM_DLGN_TR1 FOREIGN KEY (ROLE_ID) REFERENCES KSEMBEDDED.KRIM_ROLE_T (ROLE_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-733', '2.0.4-SNP', '3:9602cde17aae3d7a567dd785974a8869', 733);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-734::Jeff Caddel (generated)::(Checksum: 3:383ff957ed6c37ec27e3be9e00922287)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_ADDR_T ADD CONSTRAINT KRIM_ENTITY_ADDR_TR2 FOREIGN KEY (ADDR_TYP_CD) REFERENCES KSEMBEDDED.KRIM_ADDR_TYP_T (ADDR_TYP_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-734', '2.0.4-SNP', '3:383ff957ed6c37ec27e3be9e00922287', 734);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-735::Jeff Caddel (generated)::(Checksum: 3:ed3dbb358ec130f9196cd2df57fc5e20)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_ADDR_T ADD CONSTRAINT KRIM_ENTITY_ADDR_TR1 FOREIGN KEY (ENT_TYP_CD, ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_ENT_TYP_T (ENT_TYP_CD, ENTITY_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-735', '2.0.4-SNP', '3:ed3dbb358ec130f9196cd2df57fc5e20', 735);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-736::Jeff Caddel (generated)::(Checksum: 3:faa1b196afd44869cb904f75f11377f3)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_AFLTN_T ADD CONSTRAINT KRIM_ENTITY_AFLTN_TR2 FOREIGN KEY (AFLTN_TYP_CD) REFERENCES KSEMBEDDED.KRIM_AFLTN_TYP_T (AFLTN_TYP_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-736', '2.0.4-SNP', '3:faa1b196afd44869cb904f75f11377f3', 736);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-737::Jeff Caddel (generated)::(Checksum: 3:6ab5b0d44658262207fbe7dfe41aaa67)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_AFLTN_T ADD CONSTRAINT KRIM_ENTITY_AFLTN_TR1 FOREIGN KEY (ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_T (ENTITY_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-737', '2.0.4-SNP', '3:6ab5b0d44658262207fbe7dfe41aaa67', 737);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-738::Jeff Caddel (generated)::(Checksum: 3:7228cf93e92254a5763616d7ef5a0875)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_CTZNSHP_T ADD CONSTRAINT KRIM_ENTITY_CTZNSHP_TR2 FOREIGN KEY (CTZNSHP_STAT_CD) REFERENCES KSEMBEDDED.KRIM_CTZNSHP_STAT_T (CTZNSHP_STAT_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-738', '2.0.4-SNP', '3:7228cf93e92254a5763616d7ef5a0875', 738);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-739::Jeff Caddel (generated)::(Checksum: 3:4bb253e43c6caf7b020a1cecc65c6cfe)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_CTZNSHP_T ADD CONSTRAINT KRIM_ENTITY_CTZNSHP_TR1 FOREIGN KEY (ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_T (ENTITY_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-739', '2.0.4-SNP', '3:4bb253e43c6caf7b020a1cecc65c6cfe', 739);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-740::Jeff Caddel (generated)::(Checksum: 3:8ff8babbf90592dcc23b2afab6db27c9)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_EMAIL_T ADD CONSTRAINT KRIM_ENTITY_EMAIL_TR1 FOREIGN KEY (ENT_TYP_CD, ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_ENT_TYP_T (ENT_TYP_CD, ENTITY_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-740', '2.0.4-SNP', '3:8ff8babbf90592dcc23b2afab6db27c9', 740);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-741::Jeff Caddel (generated)::(Checksum: 3:930a9b7523ab2fb2fc6b486a24d2f657)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_EMP_INFO_T ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR2 FOREIGN KEY (EMP_STAT_CD) REFERENCES KSEMBEDDED.KRIM_EMP_STAT_T (EMP_STAT_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-741', '2.0.4-SNP', '3:930a9b7523ab2fb2fc6b486a24d2f657', 741);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-742::Jeff Caddel (generated)::(Checksum: 3:7b38464b31b40eee412cc837d68112ea)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_EMP_INFO_T ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR3 FOREIGN KEY (EMP_TYP_CD) REFERENCES KSEMBEDDED.KRIM_EMP_TYP_T (EMP_TYP_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-742', '2.0.4-SNP', '3:7b38464b31b40eee412cc837d68112ea', 742);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-743::Jeff Caddel (generated)::(Checksum: 3:3f2ed658e88e6d9ce1245b68b0d0efb8)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_EMP_INFO_T ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR4 FOREIGN KEY (ENTITY_AFLTN_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_AFLTN_T (ENTITY_AFLTN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-743', '2.0.4-SNP', '3:3f2ed658e88e6d9ce1245b68b0d0efb8', 743);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-744::Jeff Caddel (generated)::(Checksum: 3:b4e1639e3cfd0ec45348f26a15519789)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_EMP_INFO_T ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR1 FOREIGN KEY (ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_T (ENTITY_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-744', '2.0.4-SNP', '3:b4e1639e3cfd0ec45348f26a15519789', 744);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-745::Jeff Caddel (generated)::(Checksum: 3:36b9ecc559e52f045d5307a9a022b295)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_ENT_TYP_T ADD CONSTRAINT KRIM_ENTITY_ENT_TYP_TR2 FOREIGN KEY (ENT_TYP_CD) REFERENCES KSEMBEDDED.KRIM_ENT_TYP_T (ENT_TYP_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-745', '2.0.4-SNP', '3:36b9ecc559e52f045d5307a9a022b295', 745);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-746::Jeff Caddel (generated)::(Checksum: 3:2761d26f45791c98dcfbde29e12a0e33)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_ENT_TYP_T ADD CONSTRAINT KRIM_ENTITY_ENT_TYP_TR1 FOREIGN KEY (ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_T (ENTITY_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-746', '2.0.4-SNP', '3:2761d26f45791c98dcfbde29e12a0e33', 746);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-747::Jeff Caddel (generated)::(Checksum: 3:d9c0e926842e363fc3a379cb4c2416ec)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_ETHNIC_T ADD CONSTRAINT KRIM_ENTITY_ETHNIC_TR1 FOREIGN KEY (ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_T (ENTITY_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-747', '2.0.4-SNP', '3:d9c0e926842e363fc3a379cb4c2416ec', 747);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-748::Jeff Caddel (generated)::(Checksum: 3:00d400a695756a812563acdefa6d6aea)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_EXT_ID_T ADD CONSTRAINT KRIM_ENTITY_EXT_ID_TR1 FOREIGN KEY (ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_T (ENTITY_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-748', '2.0.4-SNP', '3:00d400a695756a812563acdefa6d6aea', 748);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-749::Jeff Caddel (generated)::(Checksum: 3:a8b7436f00c3774082ef765ddeb1df24)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_EXT_ID_T ADD CONSTRAINT KRIM_ENTITY_EXT_ID_TR2 FOREIGN KEY (EXT_ID_TYP_CD) REFERENCES KSEMBEDDED.KRIM_EXT_ID_TYP_T (EXT_ID_TYP_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-749', '2.0.4-SNP', '3:a8b7436f00c3774082ef765ddeb1df24', 749);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-750::Jeff Caddel (generated)::(Checksum: 3:32c6520390b7b20d3fbf1d6e917f74d6)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_NM_T ADD CONSTRAINT KRIM_ENTITY_NM_TR1 FOREIGN KEY (ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_T (ENTITY_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-750', '2.0.4-SNP', '3:32c6520390b7b20d3fbf1d6e917f74d6', 750);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-751::Jeff Caddel (generated)::(Checksum: 3:ce71f915d3f81cd513bec87790a46c34)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_PHONE_T ADD CONSTRAINT KRIM_ENTITY_PHONE_TR1 FOREIGN KEY (ENT_TYP_CD, ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_ENT_TYP_T (ENT_TYP_CD, ENTITY_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-751', '2.0.4-SNP', '3:ce71f915d3f81cd513bec87790a46c34', 751);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-752::Jeff Caddel (generated)::(Checksum: 3:febfe605746c1865f6a0188e2fa9e021)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_PHONE_T ADD CONSTRAINT KRIM_ENTITY_PHONE_TR2 FOREIGN KEY (PHONE_TYP_CD) REFERENCES KSEMBEDDED.KRIM_PHONE_TYP_T (PHONE_TYP_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-752', '2.0.4-SNP', '3:febfe605746c1865f6a0188e2fa9e021', 752);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-753::Jeff Caddel (generated)::(Checksum: 3:f76f2c6805178b4fa714578538ce105c)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_RESIDENCY_T ADD CONSTRAINT KRIM_ENTITY_RESIDENCY_TR1 FOREIGN KEY (ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_T (ENTITY_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-753', '2.0.4-SNP', '3:f76f2c6805178b4fa714578538ce105c', 753);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-754::Jeff Caddel (generated)::(Checksum: 3:ce9bffcc6b50e11e73b5ea61461ccae6)
ALTER TABLE KSEMBEDDED.KRIM_ENTITY_VISA_T ADD CONSTRAINT KRIM_ENTITY_VISA_TR1 FOREIGN KEY (ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_T (ENTITY_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-754', '2.0.4-SNP', '3:ce9bffcc6b50e11e73b5ea61461ccae6', 754);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-755::Jeff Caddel (generated)::(Checksum: 3:3775ea064bcfd35ab4e80e33de0459bf)
ALTER TABLE KSEMBEDDED.KRIM_GRP_ATTR_DATA_T ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TR3 FOREIGN KEY (GRP_ID) REFERENCES KSEMBEDDED.KRIM_GRP_T (GRP_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-755', '2.0.4-SNP', '3:3775ea064bcfd35ab4e80e33de0459bf', 755);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-756::Jeff Caddel (generated)::(Checksum: 3:70ce0781a9684ea8bae71bd75e98d0c8)
ALTER TABLE KSEMBEDDED.KRIM_GRP_ATTR_DATA_T ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-756', '2.0.4-SNP', '3:70ce0781a9684ea8bae71bd75e98d0c8', 756);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-757::Jeff Caddel (generated)::(Checksum: 3:055f09105b046c9b55ca4252b2bbb3db)
ALTER TABLE KSEMBEDDED.KRIM_GRP_ATTR_DATA_T ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID) REFERENCES KSEMBEDDED.KRIM_TYP_T (KIM_TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-757', '2.0.4-SNP', '3:055f09105b046c9b55ca4252b2bbb3db', 757);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-758::Jeff Caddel (generated)::(Checksum: 3:bd2e571c7b406a48c6dd8cdae098cf68)
ALTER TABLE KSEMBEDDED.KRIM_GRP_MBR_T ADD CONSTRAINT KRIM_GRP_MBR_TR1 FOREIGN KEY (GRP_ID) REFERENCES KSEMBEDDED.KRIM_GRP_T (GRP_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-758', '2.0.4-SNP', '3:bd2e571c7b406a48c6dd8cdae098cf68', 758);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-759::Jeff Caddel (generated)::(Checksum: 3:ee82a61ac86b55522f9c84e17eba36ee)
ALTER TABLE KSEMBEDDED.KRIM_GRP_T ADD CONSTRAINT KRIM_GRP_TR1 FOREIGN KEY (KIM_TYP_ID) REFERENCES KSEMBEDDED.KRIM_TYP_T (KIM_TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-759', '2.0.4-SNP', '3:ee82a61ac86b55522f9c84e17eba36ee', 759);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-760::Jeff Caddel (generated)::(Checksum: 3:805c4a6e9a4195585de9f2dfe1016afd)
ALTER TABLE KSEMBEDDED.KRIM_PERM_ATTR_DATA_T ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-760', '2.0.4-SNP', '3:805c4a6e9a4195585de9f2dfe1016afd', 760);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-761::Jeff Caddel (generated)::(Checksum: 3:5a47590abce41fdae9168bea9e5af714)
ALTER TABLE KSEMBEDDED.KRIM_PERM_ATTR_DATA_T ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID) REFERENCES KSEMBEDDED.KRIM_TYP_T (KIM_TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-761', '2.0.4-SNP', '3:5a47590abce41fdae9168bea9e5af714', 761);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-762::Jeff Caddel (generated)::(Checksum: 3:a32e4a615aa9228c8f4109c49f9da173)
ALTER TABLE KSEMBEDDED.KRIM_PERM_ATTR_DATA_T ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TR3 FOREIGN KEY (PERM_ID) REFERENCES KSEMBEDDED.KRIM_PERM_T (PERM_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-762', '2.0.4-SNP', '3:a32e4a615aa9228c8f4109c49f9da173', 762);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-763::Jeff Caddel (generated)::(Checksum: 3:98fcdf3e467bdead465e72e9fca43f91)
ALTER TABLE KSEMBEDDED.KRIM_PERM_T ADD CONSTRAINT KRIM_PERM_TR1 FOREIGN KEY (PERM_TMPL_ID) REFERENCES KSEMBEDDED.KRIM_PERM_TMPL_T (PERM_TMPL_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-763', '2.0.4-SNP', '3:98fcdf3e467bdead465e72e9fca43f91', 763);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-764::Jeff Caddel (generated)::(Checksum: 3:561b15acb1977358bf4c95198a58ad86)
ALTER TABLE KSEMBEDDED.KRIM_PERM_TMPL_T ADD CONSTRAINT KRIM_PERM_TMPL_TR1 FOREIGN KEY (KIM_TYP_ID) REFERENCES KSEMBEDDED.KRIM_TYP_T (KIM_TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-764', '2.0.4-SNP', '3:561b15acb1977358bf4c95198a58ad86', 764);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-765::Jeff Caddel (generated)::(Checksum: 3:fa8bbf66596f0b7c7bc211e47e311848)
ALTER TABLE KSEMBEDDED.KRIM_PND_AFLTN_MT ADD CONSTRAINT KRIM_PND_AFLTN_MT_FK1 FOREIGN KEY (FDOC_NBR) REFERENCES KSEMBEDDED.KRIM_PERSON_DOCUMENT_T (FDOC_NBR);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-765', '2.0.4-SNP', '3:fa8bbf66596f0b7c7bc211e47e311848', 765);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-766::Jeff Caddel (generated)::(Checksum: 3:5bf7936cdc50ffc73f2608dcbde24a8e)
ALTER TABLE KSEMBEDDED.KRIM_PRNCPL_T ADD CONSTRAINT KRIM_PRNCPL_TR1 FOREIGN KEY (ENTITY_ID) REFERENCES KSEMBEDDED.KRIM_ENTITY_T (ENTITY_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-766', '2.0.4-SNP', '3:5bf7936cdc50ffc73f2608dcbde24a8e', 766);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-767::Jeff Caddel (generated)::(Checksum: 3:1ef936ccf2ee6c15608de1f5b56fee13)
ALTER TABLE KSEMBEDDED.KRIM_ROLE_MBR_ATTR_DATA_T ADD CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-767', '2.0.4-SNP', '3:1ef936ccf2ee6c15608de1f5b56fee13', 767);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-768::Jeff Caddel (generated)::(Checksum: 3:a67aea93e5dc0e1847b79b41c07e2867)
ALTER TABLE KSEMBEDDED.KRIM_ROLE_MBR_ATTR_DATA_T ADD CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID) REFERENCES KSEMBEDDED.KRIM_TYP_T (KIM_TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-768', '2.0.4-SNP', '3:a67aea93e5dc0e1847b79b41c07e2867', 768);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-769::Jeff Caddel (generated)::(Checksum: 3:9eba35fd5959c02d8ce3d15b6b91b24f)
ALTER TABLE KSEMBEDDED.KRIM_ROLE_MBR_T ADD CONSTRAINT KRIM_ROLE_MBR_TR1 FOREIGN KEY (ROLE_ID) REFERENCES KSEMBEDDED.KRIM_ROLE_T (ROLE_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-769', '2.0.4-SNP', '3:9eba35fd5959c02d8ce3d15b6b91b24f', 769);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-770::Jeff Caddel (generated)::(Checksum: 3:3e41964af87d4499b2422e8426b495d3)
ALTER TABLE KSEMBEDDED.KRIM_ROLE_PERM_T ADD CONSTRAINT KRIM_ROLE_PERM_TR1 FOREIGN KEY (PERM_ID) REFERENCES KSEMBEDDED.KRIM_PERM_T (PERM_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-770', '2.0.4-SNP', '3:3e41964af87d4499b2422e8426b495d3', 770);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-771::Jeff Caddel (generated)::(Checksum: 3:6ddd3c607da6e1c5c92b3c536d75c564)
ALTER TABLE KSEMBEDDED.KRIM_ROLE_RSP_T ADD CONSTRAINT KRIM_ROLE_RSP_TR1 FOREIGN KEY (RSP_ID) REFERENCES KSEMBEDDED.KRIM_RSP_T (RSP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-771', '2.0.4-SNP', '3:6ddd3c607da6e1c5c92b3c536d75c564', 771);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-772::Jeff Caddel (generated)::(Checksum: 3:c3b3b6079e06c87a3b54babd47fda0a0)
ALTER TABLE KSEMBEDDED.KRIM_ROLE_T ADD CONSTRAINT KRIM_ROLE_TR1 FOREIGN KEY (KIM_TYP_ID) REFERENCES KSEMBEDDED.KRIM_TYP_T (KIM_TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-772', '2.0.4-SNP', '3:c3b3b6079e06c87a3b54babd47fda0a0', 772);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-773::Jeff Caddel (generated)::(Checksum: 3:a935d8be27f5aaf92b07e3a046e14a81)
ALTER TABLE KSEMBEDDED.KRIM_RSP_ATTR_DATA_T ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-773', '2.0.4-SNP', '3:a935d8be27f5aaf92b07e3a046e14a81', 773);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-774::Jeff Caddel (generated)::(Checksum: 3:52f426ffcaf8b48d170c5ed4b8dace32)
ALTER TABLE KSEMBEDDED.KRIM_RSP_ATTR_DATA_T ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID) REFERENCES KSEMBEDDED.KRIM_TYP_T (KIM_TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-774', '2.0.4-SNP', '3:52f426ffcaf8b48d170c5ed4b8dace32', 774);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-775::Jeff Caddel (generated)::(Checksum: 3:7e28606821c10dded30afb4101b93dd6)
ALTER TABLE KSEMBEDDED.KRIM_RSP_ATTR_DATA_T ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TR3 FOREIGN KEY (RSP_ID) REFERENCES KSEMBEDDED.KRIM_RSP_T (RSP_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-775', '2.0.4-SNP', '3:7e28606821c10dded30afb4101b93dd6', 775);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-776::Jeff Caddel (generated)::(Checksum: 3:ae23b10693cbe85e2b3ff8f443b5dc86)
ALTER TABLE KSEMBEDDED.KRIM_RSP_T ADD CONSTRAINT KRIM_RSP_TR1 FOREIGN KEY (RSP_TMPL_ID) REFERENCES KSEMBEDDED.KRIM_RSP_TMPL_T (RSP_TMPL_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-776', '2.0.4-SNP', '3:ae23b10693cbe85e2b3ff8f443b5dc86', 776);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-777::Jeff Caddel (generated)::(Checksum: 3:8694d752ac4ca33553bc73fcfee7fa48)
ALTER TABLE KSEMBEDDED.KRIM_RSP_TMPL_T ADD CONSTRAINT KRIM_RSP_TMPL_TR1 FOREIGN KEY (KIM_TYP_ID) REFERENCES KSEMBEDDED.KRIM_TYP_T (KIM_TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-777', '2.0.4-SNP', '3:8694d752ac4ca33553bc73fcfee7fa48', 777);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-778::Jeff Caddel (generated)::(Checksum: 3:ffd7e67e8ea657f204bd522cabb21d62)
ALTER TABLE KSEMBEDDED.KRIM_TYP_ATTR_T ADD CONSTRAINT KRIM_TYP_ATTR_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-778', '2.0.4-SNP', '3:ffd7e67e8ea657f204bd522cabb21d62', 778);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-779::Jeff Caddel (generated)::(Checksum: 3:fa9aee4501075c7d443e482afa908d1e)
ALTER TABLE KSEMBEDDED.KRIM_TYP_ATTR_T ADD CONSTRAINT KRIM_TYP_ATTRIBUTE_TR1 FOREIGN KEY (KIM_TYP_ID) REFERENCES KSEMBEDDED.KRIM_TYP_T (KIM_TYP_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-779', '2.0.4-SNP', '3:fa9aee4501075c7d443e482afa908d1e', 779);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-780::Jeff Caddel (generated)::(Checksum: 3:8c473899269e8685a0ba8b8230c44123)
ALTER TABLE KSEMBEDDED.KRLC_CMP_T ADD CONSTRAINT SH_CAMPUS_TR1 FOREIGN KEY (CAMPUS_TYP_CD) REFERENCES KSEMBEDDED.KRLC_CMP_TYP_T (CAMPUS_TYP_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-780', '2.0.4-SNP', '3:8c473899269e8685a0ba8b8230c44123', 780);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-781::Jeff Caddel (generated)::(Checksum: 3:0a5b8ed108bb62bcf4496b0b7cabde47)
ALTER TABLE KSEMBEDDED.KRLC_PSTL_CD_T ADD CONSTRAINT KR_POSTAL_CODE_TR3 FOREIGN KEY (COUNTY_CD, POSTAL_STATE_CD, POSTAL_CNTRY_CD) REFERENCES KSEMBEDDED.KRLC_CNTY_T (COUNTY_CD, STATE_CD, POSTAL_CNTRY_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-781', '2.0.4-SNP', '3:0a5b8ed108bb62bcf4496b0b7cabde47', 781);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-782::Jeff Caddel (generated)::(Checksum: 3:613f7950f910d1d6d678712a8ba278a8)
ALTER TABLE KSEMBEDDED.KRLC_PSTL_CD_T ADD CONSTRAINT KR_POSTAL_CODE_TR1 FOREIGN KEY (POSTAL_CNTRY_CD) REFERENCES KSEMBEDDED.KRLC_CNTRY_T (POSTAL_CNTRY_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-782', '2.0.4-SNP', '3:613f7950f910d1d6d678712a8ba278a8', 782);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-783::Jeff Caddel (generated)::(Checksum: 3:bd9d0434146bb6171ac47e2d7d3ad886)
ALTER TABLE KSEMBEDDED.KRLC_PSTL_CD_T ADD CONSTRAINT KR_POSTAL_CODE_TR2 FOREIGN KEY (POSTAL_STATE_CD, POSTAL_CNTRY_CD) REFERENCES KSEMBEDDED.KRLC_ST_T (POSTAL_STATE_CD, POSTAL_CNTRY_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-783', '2.0.4-SNP', '3:bd9d0434146bb6171ac47e2d7d3ad886', 783);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-784::Jeff Caddel (generated)::(Checksum: 3:0f3006b2f6357c7b056261322221c4de)
ALTER TABLE KSEMBEDDED.KRLC_ST_T ADD CONSTRAINT KR_STATE_TR1 FOREIGN KEY (POSTAL_CNTRY_CD) REFERENCES KSEMBEDDED.KRLC_CNTRY_T (POSTAL_CNTRY_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-784', '2.0.4-SNP', '3:0f3006b2f6357c7b056261322221c4de', 784);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-785::Jeff Caddel (generated)::(Checksum: 3:7182465e83f53483337d91882c3e1d74)
ALTER TABLE KSEMBEDDED.KRMS_ACTN_ATTR_T ADD CONSTRAINT KRMS_ACTN_ATTR_FK1 FOREIGN KEY (ACTN_ID) REFERENCES KSEMBEDDED.KRMS_ACTN_T (ACTN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-785', '2.0.4-SNP', '3:7182465e83f53483337d91882c3e1d74', 785);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-786::Jeff Caddel (generated)::(Checksum: 3:08bc3a4efa2febd3261e601e84c0b09f)
ALTER TABLE KSEMBEDDED.KRMS_ACTN_ATTR_T ADD CONSTRAINT KRMS_ACTN_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRMS_ATTR_DEFN_T (ATTR_DEFN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-786', '2.0.4-SNP', '3:08bc3a4efa2febd3261e601e84c0b09f', 786);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-787::Jeff Caddel (generated)::(Checksum: 3:3618292b77c3bf393f066afec5a3cd2c)
ALTER TABLE KSEMBEDDED.KRMS_ACTN_T ADD CONSTRAINT KRMS_ACTN_FK1 FOREIGN KEY (RULE_ID) REFERENCES KSEMBEDDED.KRMS_RULE_T (RULE_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-787', '2.0.4-SNP', '3:3618292b77c3bf393f066afec5a3cd2c', 787);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-788::Jeff Caddel (generated)::(Checksum: 3:e640f5aa1574140eca2baff35e097237)
ALTER TABLE KSEMBEDDED.KRMS_AGENDA_ATTR_T ADD CONSTRAINT KRMS_AGENDA_ATTR_FK1 FOREIGN KEY (AGENDA_ID) REFERENCES KSEMBEDDED.KRMS_AGENDA_T (AGENDA_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-788', '2.0.4-SNP', '3:e640f5aa1574140eca2baff35e097237', 788);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-789::Jeff Caddel (generated)::(Checksum: 3:150617887d790077eaa2680c1b3efc41)
ALTER TABLE KSEMBEDDED.KRMS_AGENDA_ATTR_T ADD CONSTRAINT KRMS_AGENDA_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRMS_ATTR_DEFN_T (ATTR_DEFN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-789', '2.0.4-SNP', '3:150617887d790077eaa2680c1b3efc41', 789);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-790::Jeff Caddel (generated)::(Checksum: 3:d8d15942d259477f32f84eed0eca51ed)
ALTER TABLE KSEMBEDDED.KRMS_AGENDA_ITM_T ADD CONSTRAINT KRMS_AGENDA_ITM_FK2 FOREIGN KEY (AGENDA_ID) REFERENCES KSEMBEDDED.KRMS_AGENDA_T (AGENDA_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-790', '2.0.4-SNP', '3:d8d15942d259477f32f84eed0eca51ed', 790);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-791::Jeff Caddel (generated)::(Checksum: 3:4d826edf9c3b859b620e121c3c2a1a81)
ALTER TABLE KSEMBEDDED.KRMS_AGENDA_ITM_T ADD CONSTRAINT KRMS_AGENDA_ITM_FK6 FOREIGN KEY (ALWAYS) REFERENCES KSEMBEDDED.KRMS_AGENDA_ITM_T (AGENDA_ITM_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-791', '2.0.4-SNP', '3:4d826edf9c3b859b620e121c3c2a1a81', 791);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-792::Jeff Caddel (generated)::(Checksum: 3:1a2e31b199592e0ee86a5141b83b01b1)
ALTER TABLE KSEMBEDDED.KRMS_AGENDA_ITM_T ADD CONSTRAINT KRMS_AGENDA_ITM_FK1 FOREIGN KEY (RULE_ID) REFERENCES KSEMBEDDED.KRMS_RULE_T (RULE_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-792', '2.0.4-SNP', '3:1a2e31b199592e0ee86a5141b83b01b1', 792);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-793::Jeff Caddel (generated)::(Checksum: 3:0a11541c211a8a19ba8eecd2e8d5973e)
ALTER TABLE KSEMBEDDED.KRMS_AGENDA_ITM_T ADD CONSTRAINT KRMS_AGENDA_ITM_FK3 FOREIGN KEY (SUB_AGENDA_ID) REFERENCES KSEMBEDDED.KRMS_AGENDA_T (AGENDA_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-793', '2.0.4-SNP', '3:0a11541c211a8a19ba8eecd2e8d5973e', 793);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-794::Jeff Caddel (generated)::(Checksum: 3:5ee04968ef2a033511031616d1703988)
ALTER TABLE KSEMBEDDED.KRMS_AGENDA_ITM_T ADD CONSTRAINT KRMS_AGENDA_ITM_FK5 FOREIGN KEY (WHEN_FALSE) REFERENCES KSEMBEDDED.KRMS_AGENDA_ITM_T (AGENDA_ITM_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-794', '2.0.4-SNP', '3:5ee04968ef2a033511031616d1703988', 794);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-795::Jeff Caddel (generated)::(Checksum: 3:45a7fb438df03fbac61275bc2c3b3b10)
ALTER TABLE KSEMBEDDED.KRMS_AGENDA_ITM_T ADD CONSTRAINT KRMS_AGENDA_ITM_FK4 FOREIGN KEY (WHEN_TRUE) REFERENCES KSEMBEDDED.KRMS_AGENDA_ITM_T (AGENDA_ITM_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-795', '2.0.4-SNP', '3:45a7fb438df03fbac61275bc2c3b3b10', 795);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-796::Jeff Caddel (generated)::(Checksum: 3:3b872b74b19720012f5974a12e1ded66)
ALTER TABLE KSEMBEDDED.KRMS_AGENDA_T ADD CONSTRAINT KRMS_AGENDA_FK1 FOREIGN KEY (CNTXT_ID) REFERENCES KSEMBEDDED.KRMS_CNTXT_T (CNTXT_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-796', '2.0.4-SNP', '3:3b872b74b19720012f5974a12e1ded66', 796);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-797::Jeff Caddel (generated)::(Checksum: 3:6283ca3b6fe7c956b9c0553252f29210)
ALTER TABLE KSEMBEDDED.KRMS_CMPND_PROP_PROPS_T ADD CONSTRAINT KRMS_CMPND_PROP_PROPS_FK2 FOREIGN KEY (CMPND_PROP_ID) REFERENCES KSEMBEDDED.KRMS_PROP_T (PROP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-797', '2.0.4-SNP', '3:6283ca3b6fe7c956b9c0553252f29210', 797);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-798::Jeff Caddel (generated)::(Checksum: 3:9fe22f51fd3766c23ff0452bc013956b)
ALTER TABLE KSEMBEDDED.KRMS_CMPND_PROP_PROPS_T ADD CONSTRAINT KRMS_CMPND_PROP_PROPS_FK1 FOREIGN KEY (PROP_ID) REFERENCES KSEMBEDDED.KRMS_PROP_T (PROP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-798', '2.0.4-SNP', '3:9fe22f51fd3766c23ff0452bc013956b', 798);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-799::Jeff Caddel (generated)::(Checksum: 3:3458026ebf96609d41a5855e3a649f26)
ALTER TABLE KSEMBEDDED.KRMS_CNTXT_ATTR_T ADD CONSTRAINT KRMS_CNTXT_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRMS_ATTR_DEFN_T (ATTR_DEFN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-799', '2.0.4-SNP', '3:3458026ebf96609d41a5855e3a649f26', 799);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-800::Jeff Caddel (generated)::(Checksum: 3:11a30764d160e75196a0f1f8ca41d93b)
ALTER TABLE KSEMBEDDED.KRMS_CNTXT_ATTR_T ADD CONSTRAINT KRMS_CNTXT_ATTR_FK1 FOREIGN KEY (CNTXT_ID) REFERENCES KSEMBEDDED.KRMS_CNTXT_T (CNTXT_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-800', '2.0.4-SNP', '3:11a30764d160e75196a0f1f8ca41d93b', 800);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-801::Jeff Caddel (generated)::(Checksum: 3:908bf8952bcc3268c51ee73ec159f0be)
ALTER TABLE KSEMBEDDED.KRMS_CNTXT_TERM_SPEC_PREREQ_T ADD CONSTRAINT KRMS_CNTXT_ASSET_PREREQ_FK1 FOREIGN KEY (CNTXT_ID) REFERENCES KSEMBEDDED.KRMS_CNTXT_T (CNTXT_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-801', '2.0.4-SNP', '3:908bf8952bcc3268c51ee73ec159f0be', 801);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-802::Jeff Caddel (generated)::(Checksum: 3:6a7526e991a441f95e4b88a8ffc004d2)
ALTER TABLE KSEMBEDDED.KRMS_CNTXT_TERM_SPEC_PREREQ_T ADD CONSTRAINT KRMS_CNTXT_ASSET_PREREQ_FK2 FOREIGN KEY (TERM_SPEC_ID) REFERENCES KSEMBEDDED.KRMS_TERM_SPEC_T (TERM_SPEC_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-802', '2.0.4-SNP', '3:6a7526e991a441f95e4b88a8ffc004d2', 802);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-803::Jeff Caddel (generated)::(Checksum: 3:208381e2aa7f87442b46535b6e6c34bd)
ALTER TABLE KSEMBEDDED.KRMS_CNTXT_VLD_ACTN_T ADD CONSTRAINT KRMS_CNTXT_VLD_ACTN_FK1 FOREIGN KEY (CNTXT_ID) REFERENCES KSEMBEDDED.KRMS_CNTXT_T (CNTXT_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-803', '2.0.4-SNP', '3:208381e2aa7f87442b46535b6e6c34bd', 803);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-804::Jeff Caddel (generated)::(Checksum: 3:d5b7ceeb13d51e03817bc8624f66afb4)
ALTER TABLE KSEMBEDDED.KRMS_CNTXT_VLD_EVENT_T ADD CONSTRAINT KRMS_CNTXT_VLD_EVENT_FK1 FOREIGN KEY (CNTXT_ID) REFERENCES KSEMBEDDED.KRMS_CNTXT_T (CNTXT_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-804', '2.0.4-SNP', '3:d5b7ceeb13d51e03817bc8624f66afb4', 804);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-805::Jeff Caddel (generated)::(Checksum: 3:24e397456fb0a3e6446b6db1c790e812)
ALTER TABLE KSEMBEDDED.KRMS_CNTXT_VLD_FUNC_T ADD CONSTRAINT KRMS_CNTXT_VLD_FUNC_FK1 FOREIGN KEY (CNTXT_ID) REFERENCES KSEMBEDDED.KRMS_CNTXT_T (CNTXT_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-805', '2.0.4-SNP', '3:24e397456fb0a3e6446b6db1c790e812', 805);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-806::Jeff Caddel (generated)::(Checksum: 3:b622c58243bbebfbe75435cf799d0be5)
ALTER TABLE KSEMBEDDED.KRMS_CNTXT_VLD_RULE_T ADD CONSTRAINT KRMS_CNTXT_VLD_RULE_FK1 FOREIGN KEY (CNTXT_ID) REFERENCES KSEMBEDDED.KRMS_CNTXT_T (CNTXT_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-806', '2.0.4-SNP', '3:b622c58243bbebfbe75435cf799d0be5', 806);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-807::Jeff Caddel (generated)::(Checksum: 3:eda428f3260f5f106f41ebc7bbd542e9)
ALTER TABLE KSEMBEDDED.KRMS_FUNC_PARM_T ADD CONSTRAINT KRMS_FUNC_PARM_FK1 FOREIGN KEY (FUNC_ID) REFERENCES KSEMBEDDED.KRMS_FUNC_T (FUNC_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-807', '2.0.4-SNP', '3:eda428f3260f5f106f41ebc7bbd542e9', 807);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-808::Jeff Caddel (generated)::(Checksum: 3:c5ea5968c5af9e42c7e0e8e7333408a1)
ALTER TABLE KSEMBEDDED.KRMS_FUNC_T ADD CONSTRAINT KRMS_FUNC_FK1 FOREIGN KEY (TYP_ID) REFERENCES KSEMBEDDED.KRMS_TYP_T (TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-808', '2.0.4-SNP', '3:c5ea5968c5af9e42c7e0e8e7333408a1', 808);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-809::Jeff Caddel (generated)::(Checksum: 3:f8d8e3b240773c05339bc82d4db67b72)
ALTER TABLE KSEMBEDDED.KRMS_PROP_PARM_T ADD CONSTRAINT KRMS_PROP_PARM_FK1 FOREIGN KEY (PROP_ID) REFERENCES KSEMBEDDED.KRMS_PROP_T (PROP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-809', '2.0.4-SNP', '3:f8d8e3b240773c05339bc82d4db67b72', 809);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-810::Jeff Caddel (generated)::(Checksum: 3:e3512b64aac80ac3886db0d3777b77da)
ALTER TABLE KSEMBEDDED.KRMS_PROP_T ADD CONSTRAINT KRMS_PROP_FK1 FOREIGN KEY (RULE_ID) REFERENCES KSEMBEDDED.KRMS_RULE_T (RULE_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-810', '2.0.4-SNP', '3:e3512b64aac80ac3886db0d3777b77da', 810);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-811::Jeff Caddel (generated)::(Checksum: 3:3af601d3673d46f2c925c3add5e2b535)
ALTER TABLE KSEMBEDDED.KRMS_PROP_T ADD CONSTRAINT KRMS_PROP_FK2 FOREIGN KEY (TYP_ID) REFERENCES KSEMBEDDED.KRMS_TYP_T (TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-811', '2.0.4-SNP', '3:3af601d3673d46f2c925c3add5e2b535', 811);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-812::Jeff Caddel (generated)::(Checksum: 3:53e6a161b2941239d657bbacfc2d257f)
ALTER TABLE KSEMBEDDED.KRMS_RULE_ATTR_T ADD CONSTRAINT KRMS_RULE_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRMS_ATTR_DEFN_T (ATTR_DEFN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-812', '2.0.4-SNP', '3:53e6a161b2941239d657bbacfc2d257f', 812);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-813::Jeff Caddel (generated)::(Checksum: 3:9fb77cdf317b619363aa1aa674df2acd)
ALTER TABLE KSEMBEDDED.KRMS_RULE_ATTR_T ADD CONSTRAINT KRMS_RULE_ATTR_FK1 FOREIGN KEY (RULE_ID) REFERENCES KSEMBEDDED.KRMS_RULE_T (RULE_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-813', '2.0.4-SNP', '3:9fb77cdf317b619363aa1aa674df2acd', 813);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-814::Jeff Caddel (generated)::(Checksum: 3:6f54b320dcd7bccd8904ee7f2c65998e)
ALTER TABLE KSEMBEDDED.KRMS_RULE_T ADD CONSTRAINT KRMS_RULE_FK1 FOREIGN KEY (PROP_ID) REFERENCES KSEMBEDDED.KRMS_PROP_T (PROP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-814', '2.0.4-SNP', '3:6f54b320dcd7bccd8904ee7f2c65998e', 814);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-815::Jeff Caddel (generated)::(Checksum: 3:1be1d62a68271bee9f245481a5df4f26)
ALTER TABLE KSEMBEDDED.KRMS_TERM_PARM_T ADD CONSTRAINT KRMS_TERM_PARM_FK1 FOREIGN KEY (TERM_ID) REFERENCES KSEMBEDDED.KRMS_TERM_T (TERM_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-815', '2.0.4-SNP', '3:1be1d62a68271bee9f245481a5df4f26', 815);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-816::Jeff Caddel (generated)::(Checksum: 3:3df97e74998dd329d2195c1a788c2dcc)
ALTER TABLE KSEMBEDDED.KRMS_TERM_RSLVR_ATTR_T ADD CONSTRAINT KRMS_ASSET_RSLVR_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRMS_ATTR_DEFN_T (ATTR_DEFN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-816', '2.0.4-SNP', '3:3df97e74998dd329d2195c1a788c2dcc', 816);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-817::Jeff Caddel (generated)::(Checksum: 3:3d1fa48c7055551490940f868ce85d27)
ALTER TABLE KSEMBEDDED.KRMS_TERM_RSLVR_ATTR_T ADD CONSTRAINT KRMS_ASSET_RSLVR_ATTR_FK1 FOREIGN KEY (TERM_RSLVR_ID) REFERENCES KSEMBEDDED.KRMS_TERM_RSLVR_T (TERM_RSLVR_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-817', '2.0.4-SNP', '3:3d1fa48c7055551490940f868ce85d27', 817);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-818::Jeff Caddel (generated)::(Checksum: 3:6326adcd20bedceab84ace1f032f87c3)
ALTER TABLE KSEMBEDDED.KRMS_TERM_RSLVR_INPUT_SPEC_T ADD CONSTRAINT KRMS_INPUT_ASSET_FK1 FOREIGN KEY (TERM_RSLVR_ID) REFERENCES KSEMBEDDED.KRMS_TERM_RSLVR_T (TERM_RSLVR_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-818', '2.0.4-SNP', '3:6326adcd20bedceab84ace1f032f87c3', 818);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-819::Jeff Caddel (generated)::(Checksum: 3:8aa41cdd8ac4371a80947cca09b22c2d)
ALTER TABLE KSEMBEDDED.KRMS_TERM_RSLVR_INPUT_SPEC_T ADD CONSTRAINT KRMS_INPUT_ASSET_FK2 FOREIGN KEY (TERM_SPEC_ID) REFERENCES KSEMBEDDED.KRMS_TERM_SPEC_T (TERM_SPEC_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-819', '2.0.4-SNP', '3:8aa41cdd8ac4371a80947cca09b22c2d', 819);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-820::Jeff Caddel (generated)::(Checksum: 3:d7e34621a01ff6e66ecdcb26d5e714a0)
ALTER TABLE KSEMBEDDED.KRMS_TERM_RSLVR_PARM_SPEC_T ADD CONSTRAINT KRMS_TERM_RESLV_PARM_FK1 FOREIGN KEY (TERM_RSLVR_ID) REFERENCES KSEMBEDDED.KRMS_TERM_RSLVR_T (TERM_RSLVR_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-820', '2.0.4-SNP', '3:d7e34621a01ff6e66ecdcb26d5e714a0', 820);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-821::Jeff Caddel (generated)::(Checksum: 3:53e26587a6c7880843d84b348e3d3b65)
ALTER TABLE KSEMBEDDED.KRMS_TERM_RSLVR_T ADD CONSTRAINT KRMS_TERM_RSLVR_FK2 FOREIGN KEY (CNTXT_ID) REFERENCES KSEMBEDDED.KRMS_CNTXT_T (CNTXT_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-821', '2.0.4-SNP', '3:53e26587a6c7880843d84b348e3d3b65', 821);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-822::Jeff Caddel (generated)::(Checksum: 3:d7e89f431b856b0a6aad07115a61b850)
ALTER TABLE KSEMBEDDED.KRMS_TERM_RSLVR_T ADD CONSTRAINT KRMS_TERM_RSLVR_FK1 FOREIGN KEY (OUTPUT_TERM_SPEC_ID) REFERENCES KSEMBEDDED.KRMS_TERM_SPEC_T (TERM_SPEC_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-822', '2.0.4-SNP', '3:d7e89f431b856b0a6aad07115a61b850', 822);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-823::Jeff Caddel (generated)::(Checksum: 3:82eb096ffebb052a2caec09ff0962daf)
ALTER TABLE KSEMBEDDED.KRMS_TERM_RSLVR_T ADD CONSTRAINT KRMS_TERM_RSLVR_FK3 FOREIGN KEY (TYP_ID) REFERENCES KSEMBEDDED.KRMS_TYP_T (TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-823', '2.0.4-SNP', '3:82eb096ffebb052a2caec09ff0962daf', 823);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-824::Jeff Caddel (generated)::(Checksum: 3:026211e759e2620f82065633c290d6d4)
ALTER TABLE KSEMBEDDED.KRMS_TERM_SPEC_T ADD CONSTRAINT KRMS_ASSET_FK1 FOREIGN KEY (CNTXT_ID) REFERENCES KSEMBEDDED.KRMS_CNTXT_T (CNTXT_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-824', '2.0.4-SNP', '3:026211e759e2620f82065633c290d6d4', 824);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-825::Jeff Caddel (generated)::(Checksum: 3:ce45df78ce8897cb406bd6a5855266c0)
ALTER TABLE KSEMBEDDED.KRMS_TERM_T ADD CONSTRAINT KRMS_TERM_T__FK1 FOREIGN KEY (TERM_SPEC_ID) REFERENCES KSEMBEDDED.KRMS_TERM_SPEC_T (TERM_SPEC_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-825', '2.0.4-SNP', '3:ce45df78ce8897cb406bd6a5855266c0', 825);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-826::Jeff Caddel (generated)::(Checksum: 3:b2a134eec6658005c4dfc87beb98f1d7)
ALTER TABLE KSEMBEDDED.KRMS_TYP_ATTR_T ADD CONSTRAINT KRMS_TYP_ATTR_FK1 FOREIGN KEY (ATTR_DEFN_ID) REFERENCES KSEMBEDDED.KRMS_ATTR_DEFN_T (ATTR_DEFN_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-826', '2.0.4-SNP', '3:b2a134eec6658005c4dfc87beb98f1d7', 826);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-827::Jeff Caddel (generated)::(Checksum: 3:dda64433f5d859e2f4318c26dca9d256)
ALTER TABLE KSEMBEDDED.KRMS_TYP_ATTR_T ADD CONSTRAINT KRMS_TYP_ATTR_FK2 FOREIGN KEY (TYP_ID) REFERENCES KSEMBEDDED.KRMS_TYP_T (TYP_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-827', '2.0.4-SNP', '3:dda64433f5d859e2f4318c26dca9d256', 827);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-828::Jeff Caddel (generated)::(Checksum: 3:11167655e8da8bf8bbd71043ac59d589)
ALTER TABLE KSEMBEDDED.KRNS_ATT_T ADD CONSTRAINT KRNS_ATT_TR1 FOREIGN KEY (NTE_ID) REFERENCES KSEMBEDDED.KRNS_NTE_T (NTE_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-828', '2.0.4-SNP', '3:11167655e8da8bf8bbd71043ac59d589', 828);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-829::Jeff Caddel (generated)::(Checksum: 3:576279bbfffb9e8fd65643624580a6df)
ALTER TABLE KSEMBEDDED.KRNS_MAINT_DOC_T ADD CONSTRAINT KRNS_MAINT_DOC_TR1 FOREIGN KEY (DOC_HDR_ID) REFERENCES KSEMBEDDED.KRNS_DOC_HDR_T (DOC_HDR_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-829', '2.0.4-SNP', '3:576279bbfffb9e8fd65643624580a6df', 829);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-830::Jeff Caddel (generated)::(Checksum: 3:c6e5110ce5dac4a2fd1955357ded8f22)
ALTER TABLE KSEMBEDDED.KRNS_NTE_T ADD CONSTRAINT KRNS_NTE_TR1 FOREIGN KEY (NTE_TYP_CD) REFERENCES KSEMBEDDED.KRNS_NTE_TYP_T (NTE_TYP_CD);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-830', '2.0.4-SNP', '3:c6e5110ce5dac4a2fd1955357ded8f22', 830);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-831::Jeff Caddel (generated)::(Checksum: 3:f10fe8c63f024c4eb82f92b9c6241cab)
ALTER TABLE KSEMBEDDED.KRSB_QRTZ_BLOB_TRIGGERS ADD CONSTRAINT KRSB_QRTZ_BLOB_TRIGGERS_TR1 FOREIGN KEY (TRIGGER_NAME, TRIGGER_GROUP) REFERENCES KSEMBEDDED.KRSB_QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-831', '2.0.4-SNP', '3:f10fe8c63f024c4eb82f92b9c6241cab', 831);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-832::Jeff Caddel (generated)::(Checksum: 3:771856fd8b5346e88e3ec0d953d31a97)
ALTER TABLE KSEMBEDDED.KRSB_QRTZ_CRON_TRIGGERS ADD CONSTRAINT KRSB_QRTZ_CRON_TRIGGERS_TR1 FOREIGN KEY (TRIGGER_NAME, TRIGGER_GROUP) REFERENCES KSEMBEDDED.KRSB_QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-832', '2.0.4-SNP', '3:771856fd8b5346e88e3ec0d953d31a97', 832);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-833::Jeff Caddel (generated)::(Checksum: 3:a2f0a1775cb8a232ba3927781fa19d0e)
ALTER TABLE KSEMBEDDED.KRSB_QRTZ_JOB_LISTENERS ADD CONSTRAINT KRSB_QUARTZ_JOB_LISTENERS_TR1 FOREIGN KEY (JOB_NAME, JOB_GROUP) REFERENCES KSEMBEDDED.KRSB_QRTZ_JOB_DETAILS (JOB_NAME, JOB_GROUP);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-833', '2.0.4-SNP', '3:a2f0a1775cb8a232ba3927781fa19d0e', 833);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-834::Jeff Caddel (generated)::(Checksum: 3:55c22d3e0e9ec46a216cfa3186bdc3be)
ALTER TABLE KSEMBEDDED.KRSB_QRTZ_SIMPLE_TRIGGERS ADD CONSTRAINT KRSB_QRTZ_SIMPLE_TRIGGERS_TR1 FOREIGN KEY (TRIGGER_NAME, TRIGGER_GROUP) REFERENCES KSEMBEDDED.KRSB_QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-834', '2.0.4-SNP', '3:55c22d3e0e9ec46a216cfa3186bdc3be', 834);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-835::Jeff Caddel (generated)::(Checksum: 3:38367c2dde732db523f64fc4aa1d9820)
ALTER TABLE KSEMBEDDED.KRSB_QRTZ_TRIGGER_LISTENERS ADD CONSTRAINT KRSB_QRTZ_TRIGGER_LISTENE_TR1 FOREIGN KEY (TRIGGER_NAME, TRIGGER_GROUP) REFERENCES KSEMBEDDED.KRSB_QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-835', '2.0.4-SNP', '3:38367c2dde732db523f64fc4aa1d9820', 835);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-836::Jeff Caddel (generated)::(Checksum: 3:1170778372c3d693e2429b031ad3dfea)
ALTER TABLE KSEMBEDDED.KRSB_SVC_DEF_T ADD CONSTRAINT KRSB_SVC_DEF_FK1 FOREIGN KEY (SVC_DSCRPTR_ID) REFERENCES KSEMBEDDED.KRSB_SVC_DSCRPTR_T (SVC_DSCRPTR_ID) ON DELETE CASCADE;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-836', '2.0.4-SNP', '3:1170778372c3d693e2429b031ad3dfea', 836);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-837::Jeff Caddel (generated)::(Checksum: 3:10d9c15acbf39f2250fba92792d9ec40)
ALTER TABLE KSEMBEDDED.KSAP_ATP ADD CONSTRAINT KSAP_ATP_FK2 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSAP_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-837', '2.0.4-SNP', '3:10d9c15acbf39f2250fba92792d9ec40', 837);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-838::Jeff Caddel (generated)::(Checksum: 3:e9dad0c5ad2a1f4b8c4501b7094964a4)
ALTER TABLE KSEMBEDDED.KSAP_ATP ADD CONSTRAINT KSAP_ATP_FK1 FOREIGN KEY (TYPE) REFERENCES KSEMBEDDED.KSAP_ATP_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-838', '2.0.4-SNP', '3:e9dad0c5ad2a1f4b8c4501b7094964a4', 838);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-839::Jeff Caddel (generated)::(Checksum: 3:11ca576f993ec1dd1d80686225a9f058)
ALTER TABLE KSEMBEDDED.KSAP_ATP_ATTR ADD CONSTRAINT KSAP_ATP_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSAP_ATP (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-839', '2.0.4-SNP', '3:11ca576f993ec1dd1d80686225a9f058', 839);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-840::Jeff Caddel (generated)::(Checksum: 3:23ed95777b6d7d9c3f27d0bdce0fd2e3)
ALTER TABLE KSEMBEDDED.KSAP_ATP_DUR_TYPE_ATTR ADD CONSTRAINT KSAP_ATP_DUR_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSAP_ATP_DUR_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-840', '2.0.4-SNP', '3:23ed95777b6d7d9c3f27d0bdce0fd2e3', 840);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-841::Jeff Caddel (generated)::(Checksum: 3:7343f9278c51c34b81581cbe9ecc13e2)
ALTER TABLE KSEMBEDDED.KSAP_ATP_SEASONAL_TYPE_ATTR ADD CONSTRAINT KSAP_ATP_SEAS_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSAP_ATP_SEASONAL_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-841', '2.0.4-SNP', '3:7343f9278c51c34b81581cbe9ecc13e2', 841);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-842::Jeff Caddel (generated)::(Checksum: 3:3ea86915eba9596bcece6c61fe766441)
ALTER TABLE KSEMBEDDED.KSAP_ATP_TYPE ADD CONSTRAINT KSAP_ATP_TYPE_FK2 FOREIGN KEY (DUR_TYPE) REFERENCES KSEMBEDDED.KSAP_ATP_DUR_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-842', '2.0.4-SNP', '3:3ea86915eba9596bcece6c61fe766441', 842);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-843::Jeff Caddel (generated)::(Checksum: 3:d9296e7e2cbae1fba19bdc038e6df158)
ALTER TABLE KSEMBEDDED.KSAP_ATP_TYPE ADD CONSTRAINT KSAP_ATP_TYPE_FK1 FOREIGN KEY (SEASONAL_TYPE) REFERENCES KSEMBEDDED.KSAP_ATP_SEASONAL_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-843', '2.0.4-SNP', '3:d9296e7e2cbae1fba19bdc038e6df158', 843);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-844::Jeff Caddel (generated)::(Checksum: 3:059bcba0273a057e407ad7bf258d92cb)
ALTER TABLE KSEMBEDDED.KSAP_ATP_TYPE_ATTR ADD CONSTRAINT KSAP_ATP_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSAP_ATP_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-844', '2.0.4-SNP', '3:059bcba0273a057e407ad7bf258d92cb', 844);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-845::Jeff Caddel (generated)::(Checksum: 3:1ad0e64b50ece4a64672e80b62eed04d)
ALTER TABLE KSEMBEDDED.KSAP_DT_RANGE ADD CONSTRAINT KSAP_DT_RANGE_FK3 FOREIGN KEY (ATP_ID) REFERENCES KSEMBEDDED.KSAP_ATP (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-845', '2.0.4-SNP', '3:1ad0e64b50ece4a64672e80b62eed04d', 845);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-846::Jeff Caddel (generated)::(Checksum: 3:1d99ae062f026334df17cfde02094663)
ALTER TABLE KSEMBEDDED.KSAP_DT_RANGE ADD CONSTRAINT KSAP_DT_RANGE_FK1 FOREIGN KEY (DT_RANGE_TYPE_ID) REFERENCES KSEMBEDDED.KSAP_DT_RANGE_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-846', '2.0.4-SNP', '3:1d99ae062f026334df17cfde02094663', 846);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-847::Jeff Caddel (generated)::(Checksum: 3:83e1f0d281bf4ac35d3b246ec1f4118e)
ALTER TABLE KSEMBEDDED.KSAP_DT_RANGE ADD CONSTRAINT KSAP_DT_RANGE_FK2 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSAP_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-847', '2.0.4-SNP', '3:83e1f0d281bf4ac35d3b246ec1f4118e', 847);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-848::Jeff Caddel (generated)::(Checksum: 3:e2a6ab89d2a4f43d70bf777666cef39e)
ALTER TABLE KSEMBEDDED.KSAP_DT_RANGE_ATTR ADD CONSTRAINT KSAP_DT_RANGE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSAP_DT_RANGE (DATERANGE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-848', '2.0.4-SNP', '3:e2a6ab89d2a4f43d70bf777666cef39e', 848);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-849::Jeff Caddel (generated)::(Checksum: 3:c9bb6b4e479c0402b8ef6c6d7f69f0ad)
ALTER TABLE KSEMBEDDED.KSAP_DT_RANGE_TYPE_ATTR ADD CONSTRAINT KSAP_DT_RANGE_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSAP_DT_RANGE_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-849', '2.0.4-SNP', '3:c9bb6b4e479c0402b8ef6c6d7f69f0ad', 849);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-850::Jeff Caddel (generated)::(Checksum: 3:818a16f308f90df232faf445149f0156)
ALTER TABLE KSEMBEDDED.KSAP_MLSTN ADD CONSTRAINT KSAP_MLSTN_FK1 FOREIGN KEY (ATP_ID) REFERENCES KSEMBEDDED.KSAP_ATP (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-850', '2.0.4-SNP', '3:818a16f308f90df232faf445149f0156', 850);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-851::Jeff Caddel (generated)::(Checksum: 3:f374d643783dfa0e393a4d5fafb85844)
ALTER TABLE KSEMBEDDED.KSAP_MLSTN ADD CONSTRAINT KSAP_MLSTN_FK3 FOREIGN KEY (MLSTN_TYPE_ID) REFERENCES KSEMBEDDED.KSAP_MLSTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-851', '2.0.4-SNP', '3:f374d643783dfa0e393a4d5fafb85844', 851);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-852::Jeff Caddel (generated)::(Checksum: 3:96e23e04af96db512bf750dab0ff8f3f)
ALTER TABLE KSEMBEDDED.KSAP_MLSTN ADD CONSTRAINT KSAP_MLSTN_FK2 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSAP_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-852', '2.0.4-SNP', '3:96e23e04af96db512bf750dab0ff8f3f', 852);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-853::Jeff Caddel (generated)::(Checksum: 3:2689274392c06f65b1ae22e373902634)
ALTER TABLE KSEMBEDDED.KSAP_MLSTN_ATTR ADD CONSTRAINT KSAP_MLSTN_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSAP_MLSTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-853', '2.0.4-SNP', '3:2689274392c06f65b1ae22e373902634', 853);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-854::Jeff Caddel (generated)::(Checksum: 3:1dce09cd7c02ac4012c9344795201b38)
ALTER TABLE KSEMBEDDED.KSAP_MLSTN_TYPE_ATTR ADD CONSTRAINT KSAP_MLSTN_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSAP_MLSTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-854', '2.0.4-SNP', '3:1dce09cd7c02ac4012c9344795201b38', 854);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-855::Jeff Caddel (generated)::(Checksum: 3:86d478e6a97ed62116202f182f1dcfa8)
ALTER TABLE KSEMBEDDED.KSCO_COMMENT ADD CONSTRAINT KSCO_COMMENT_FK3 FOREIGN KEY (REFERENCE) REFERENCES KSEMBEDDED.KSCO_REFERENCE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-855', '2.0.4-SNP', '3:86d478e6a97ed62116202f182f1dcfa8', 855);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-856::Jeff Caddel (generated)::(Checksum: 3:a4c71ad622c0782c566caa8083fe4c01)
ALTER TABLE KSEMBEDDED.KSCO_COMMENT ADD CONSTRAINT KSCO_COMMENT_FK2 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSCO_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-856', '2.0.4-SNP', '3:a4c71ad622c0782c566caa8083fe4c01', 856);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-857::Jeff Caddel (generated)::(Checksum: 3:892e40f6a13b58eb9d77b41817e62111)
ALTER TABLE KSEMBEDDED.KSCO_COMMENT ADD CONSTRAINT KSCO_COMMENT_FK1 FOREIGN KEY (TYPE) REFERENCES KSEMBEDDED.KSCO_COMMENT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-857', '2.0.4-SNP', '3:892e40f6a13b58eb9d77b41817e62111', 857);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-858::Jeff Caddel (generated)::(Checksum: 3:f737e78c6e9f02aabf6650ed2ccaa825)
ALTER TABLE KSEMBEDDED.KSCO_COMMENT_ATTR ADD CONSTRAINT KSCO_COMMENT_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSCO_COMMENT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-858', '2.0.4-SNP', '3:f737e78c6e9f02aabf6650ed2ccaa825', 858);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-859::Jeff Caddel (generated)::(Checksum: 3:6ce6261922f7bee73049e124c4372a09)
ALTER TABLE KSEMBEDDED.KSCO_COMMENT_TYPE_ATTR ADD CONSTRAINT KSCO_COMMENT_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSCO_COMMENT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-859', '2.0.4-SNP', '3:6ce6261922f7bee73049e124c4372a09', 859);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-860::Jeff Caddel (generated)::(Checksum: 3:5d0596130339d183c4fd0d5224639a5e)
ALTER TABLE KSEMBEDDED.KSCO_REFERENCE ADD CONSTRAINT KSCO_REFERENCE_FK1 FOREIGN KEY (REFERENCE_TYPE) REFERENCES KSEMBEDDED.KSCO_REFERENCE_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-860', '2.0.4-SNP', '3:5d0596130339d183c4fd0d5224639a5e', 860);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-861::Jeff Caddel (generated)::(Checksum: 3:fed88f3d856d4032f0287d3156ba6972)
ALTER TABLE KSEMBEDDED.KSCO_REFERENCE_TYPE_ATTR ADD CONSTRAINT KSCO_REFERENCE_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSCO_REFERENCE_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-861', '2.0.4-SNP', '3:fed88f3d856d4032f0287d3156ba6972', 861);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-862::Jeff Caddel (generated)::(Checksum: 3:b04c4a9e491acb82299101c945e1f783)
ALTER TABLE KSEMBEDDED.KSCO_TAG ADD CONSTRAINT KSCO_TAG_FK1 FOREIGN KEY (REFERENCE) REFERENCES KSEMBEDDED.KSCO_REFERENCE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-862', '2.0.4-SNP', '3:b04c4a9e491acb82299101c945e1f783', 862);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-863::Jeff Caddel (generated)::(Checksum: 3:429125fb8f863f0fbba292ac2bd22377)
ALTER TABLE KSEMBEDDED.KSCO_TAG ADD CONSTRAINT KSCO_TAG_FK2 FOREIGN KEY (TYPE) REFERENCES KSEMBEDDED.KSCO_TAG_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-863', '2.0.4-SNP', '3:429125fb8f863f0fbba292ac2bd22377', 863);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-864::Jeff Caddel (generated)::(Checksum: 3:bec5f9c7c8076a4dd8ea8a4870b91858)
ALTER TABLE KSEMBEDDED.KSCO_TAG_ATTR ADD CONSTRAINT KSCO_TAG_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSCO_TAG (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-864', '2.0.4-SNP', '3:bec5f9c7c8076a4dd8ea8a4870b91858', 864);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-865::Jeff Caddel (generated)::(Checksum: 3:bf852dbb6cf22e18f465b5ce12d66031)
ALTER TABLE KSEMBEDDED.KSCO_TAG_TYPE_ATTR ADD CONSTRAINT KSCO_TAG_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSCO_TAG_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-865', '2.0.4-SNP', '3:bf852dbb6cf22e18f465b5ce12d66031', 865);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-866::Jeff Caddel (generated)::(Checksum: 3:86781aa4918f689ecf18fab8d8731871)
ALTER TABLE KSEMBEDDED.KSDO_DOCUMENT ADD CONSTRAINT KSDO_DOCUMENT_FK1 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSDO_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-866', '2.0.4-SNP', '3:86781aa4918f689ecf18fab8d8731871', 866);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-867::Jeff Caddel (generated)::(Checksum: 3:97b54d3f2a7f942ebcb5da3738ee6d5b)
ALTER TABLE KSEMBEDDED.KSDO_DOCUMENT ADD CONSTRAINT KSDO_DOCUMENT_FK2 FOREIGN KEY (TYPE) REFERENCES KSEMBEDDED.KSDO_DOCUMENT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-867', '2.0.4-SNP', '3:97b54d3f2a7f942ebcb5da3738ee6d5b', 867);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-868::Jeff Caddel (generated)::(Checksum: 3:45fd4fddfc92006306aa80bddf31c45c)
ALTER TABLE KSEMBEDDED.KSDO_DOCUMENT_ATTR ADD CONSTRAINT KSDO_DOCUMENT_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSDO_DOCUMENT (DOC_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-868', '2.0.4-SNP', '3:45fd4fddfc92006306aa80bddf31c45c', 868);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-869::Jeff Caddel (generated)::(Checksum: 3:450e1588d24852947e2d4e35ffe2b030)
ALTER TABLE KSEMBEDDED.KSDO_DOCUMENT_CATEGORY ADD CONSTRAINT KSDO_DOCUMENT_CATEGORY_FK1 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSDO_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-869', '2.0.4-SNP', '3:450e1588d24852947e2d4e35ffe2b030', 869);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-870::Jeff Caddel (generated)::(Checksum: 3:525527eb720db17dff77d0966768a718)
ALTER TABLE KSEMBEDDED.KSDO_DOCUMENT_CATEGORY_ATTR ADD CONSTRAINT KSDO_DOC_CATEGORY_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSDO_DOCUMENT_CATEGORY (CATEGORY_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-870', '2.0.4-SNP', '3:525527eb720db17dff77d0966768a718', 870);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-871::Jeff Caddel (generated)::(Checksum: 3:9b6c18a9ae2a05416f8b2a8a102c17bc)
ALTER TABLE KSEMBEDDED.KSDO_DOCUMENT_JN_DOC_CATEGORY ADD CONSTRAINT KSDO_DOC_JN_DOC_CATEGORY_FK2 FOREIGN KEY (CATEGORY_ID) REFERENCES KSEMBEDDED.KSDO_DOCUMENT_CATEGORY (CATEGORY_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-871', '2.0.4-SNP', '3:9b6c18a9ae2a05416f8b2a8a102c17bc', 871);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-872::Jeff Caddel (generated)::(Checksum: 3:4f4caffcf3ce9b4988f90879ad6253d4)
ALTER TABLE KSEMBEDDED.KSDO_DOCUMENT_JN_DOC_CATEGORY ADD CONSTRAINT KSDO_DOC_JN_DOC_CATEGORY_FK1 FOREIGN KEY (DOC_ID) REFERENCES KSEMBEDDED.KSDO_DOCUMENT (DOC_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-872', '2.0.4-SNP', '3:4f4caffcf3ce9b4988f90879ad6253d4', 872);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-873::Jeff Caddel (generated)::(Checksum: 3:428dcea6864f8ea58873edb68d7b806a)
ALTER TABLE KSEMBEDDED.KSDO_DOCUMENT_TYPE_ATTR ADD CONSTRAINT KSDO_DOCUMENT_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSDO_DOCUMENT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-873', '2.0.4-SNP', '3:428dcea6864f8ea58873edb68d7b806a', 873);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-874::Jeff Caddel (generated)::(Checksum: 3:69b5cabe338dea196617aff3f5bc1f3e)
ALTER TABLE KSEMBEDDED.KSDO_REF_DOC_REL_ATTR ADD CONSTRAINT KSDO_REF_DOC_REL_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSDO_REF_DOC_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-874', '2.0.4-SNP', '3:69b5cabe338dea196617aff3f5bc1f3e', 874);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-875::Jeff Caddel (generated)::(Checksum: 3:f7524806f35130e8bb2765ac450976f0)
ALTER TABLE KSEMBEDDED.KSDO_REF_DOC_RELTN ADD CONSTRAINT KSDO_REF_DOC_RELTN_FK4 FOREIGN KEY (DOC_ID) REFERENCES KSEMBEDDED.KSDO_DOCUMENT (DOC_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-875', '2.0.4-SNP', '3:f7524806f35130e8bb2765ac450976f0', 875);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-876::Jeff Caddel (generated)::(Checksum: 3:9b2f21683bde2c64cac40fcb31f128a3)
ALTER TABLE KSEMBEDDED.KSDO_REF_DOC_RELTN ADD CONSTRAINT KSDO_REF_DOC_RELTN_FK2 FOREIGN KEY (REF_OBJ_TYPE_KEY) REFERENCES KSEMBEDDED.KSDO_REF_OBJ_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-876', '2.0.4-SNP', '3:9b2f21683bde2c64cac40fcb31f128a3', 876);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-877::Jeff Caddel (generated)::(Checksum: 3:2dcf26713f06aa92663ef17f0ba446b3)
ALTER TABLE KSEMBEDDED.KSDO_REF_DOC_RELTN ADD CONSTRAINT KSDO_REF_DOC_RELTN_FK3 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSDO_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-877', '2.0.4-SNP', '3:2dcf26713f06aa92663ef17f0ba446b3', 877);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-878::Jeff Caddel (generated)::(Checksum: 3:aba4e386aa0598d0c64e81d68df4c1e3)
ALTER TABLE KSEMBEDDED.KSDO_REF_DOC_RELTN ADD CONSTRAINT KSDO_REF_DOC_RELTN_FK1 FOREIGN KEY (TYPE_KEY) REFERENCES KSEMBEDDED.KSDO_REF_DOC_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-878', '2.0.4-SNP', '3:aba4e386aa0598d0c64e81d68df4c1e3', 878);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-879::Jeff Caddel (generated)::(Checksum: 3:4a480638a6ee6664c3509cb80ae783b8)
ALTER TABLE KSEMBEDDED.KSDO_REF_DOC_RELTN_TYPE_ATTR ADD CONSTRAINT KSDO_REF_DOC_REL_TYP_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSDO_REF_DOC_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-879', '2.0.4-SNP', '3:4a480638a6ee6664c3509cb80ae783b8', 879);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-880::Jeff Caddel (generated)::(Checksum: 3:dbac708e4d119e72cb7a39fd2a00b7f4)
ALTER TABLE KSEMBEDDED.KSDO_REF_OBJ_SUB_TYPE ADD CONSTRAINT KSDO_REF_OBJ_SUB_TYPE_FK1 FOREIGN KEY (REF_OBJ_TYPE_KEY) REFERENCES KSEMBEDDED.KSDO_REF_OBJ_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-880', '2.0.4-SNP', '3:dbac708e4d119e72cb7a39fd2a00b7f4', 880);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-881::Jeff Caddel (generated)::(Checksum: 3:263d743a6785e191a9e33b9537e8778f)
ALTER TABLE KSEMBEDDED.KSDO_REF_OBJ_SUB_TYPE_ATTR ADD CONSTRAINT KSDO_REF_OBJ_SUB_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSDO_REF_OBJ_SUB_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-881', '2.0.4-SNP', '3:263d743a6785e191a9e33b9537e8778f', 881);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-882::Jeff Caddel (generated)::(Checksum: 3:89466397778813d7a1b5a309ce9514e4)
ALTER TABLE KSEMBEDDED.KSDO_REF_OBJ_TYPE_ATTR ADD CONSTRAINT KSDO_REF_OBJ_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSDO_REF_OBJ_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-882', '2.0.4-SNP', '3:89466397778813d7a1b5a309ce9514e4', 882);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-883::Jeff Caddel (generated)::(Checksum: 3:7f0dbc15023c6fbeeaa3a89a6a3b8707)
ALTER TABLE KSEMBEDDED.KSDO_REF_REL_TYP_JN_SUB_TYP ADD CONSTRAINT KSDO_REF_REL_TYP_JN_SUBTYP_FK2 FOREIGN KEY (REF_DOC_RELTN_TYPE_KEY) REFERENCES KSEMBEDDED.KSDO_REF_DOC_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-883', '2.0.4-SNP', '3:7f0dbc15023c6fbeeaa3a89a6a3b8707', 883);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-884::Jeff Caddel (generated)::(Checksum: 3:50202db56dcb155b13bf74d5f1652c1c)
ALTER TABLE KSEMBEDDED.KSDO_REF_REL_TYP_JN_SUB_TYP ADD CONSTRAINT KSDO_REF_REL_TYP_JN_SUBTYP_FK1 FOREIGN KEY (REF_OBJ_SUB_TYPE_KEY) REFERENCES KSEMBEDDED.KSDO_REF_OBJ_SUB_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-884', '2.0.4-SNP', '3:50202db56dcb155b13bf74d5f1652c1c', 884);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-885::Jeff Caddel (generated)::(Checksum: 3:d85e9cc0147ed8b7997d408384f4929e)
ALTER TABLE KSEMBEDDED.KSEM_CTX_JN_ENUM_VAL_T ADD CONSTRAINT KSEM_CTX_JN_ENUM_VAL_T_FK1 FOREIGN KEY (CTX_ID) REFERENCES KSEMBEDDED.KSEM_CTX_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-885', '2.0.4-SNP', '3:d85e9cc0147ed8b7997d408384f4929e', 885);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-886::Jeff Caddel (generated)::(Checksum: 3:9a03fa53a96f38461d296325ca687bf9)
ALTER TABLE KSEMBEDDED.KSEM_CTX_JN_ENUM_VAL_T ADD CONSTRAINT KSEM_CTX_JN_ENUM_VAL_T_FK2 FOREIGN KEY (ENUM_VAL_ID) REFERENCES KSEMBEDDED.KSEM_ENUM_VAL_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-886', '2.0.4-SNP', '3:9a03fa53a96f38461d296325ca687bf9', 886);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-887::Jeff Caddel (generated)::(Checksum: 3:9664c974a55554dfb2c2dce7c5d47bfb)
ALTER TABLE KSEMBEDDED.KSEM_ENUM_VAL_T ADD CONSTRAINT KSEM_ENUM_VAL_T_FK1 FOREIGN KEY (ENUM_KEY) REFERENCES KSEMBEDDED.KSEM_ENUM_T (ENUM_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-887', '2.0.4-SNP', '3:9664c974a55554dfb2c2dce7c5d47bfb', 887);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-888::Jeff Caddel (generated)::(Checksum: 3:f5367f8ee812f10a4b75197816d4d437)
ALTER TABLE KSEMBEDDED.KSEN_ATP ADD CONSTRAINT FKFD3154F6252E155 FOREIGN KEY (ATP_STATE_ID) REFERENCES KSEMBEDDED.KSEN_ATP_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-888', '2.0.4-SNP', '3:f5367f8ee812f10a4b75197816d4d437', 888);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-889::Jeff Caddel (generated)::(Checksum: 3:5e78f68cb97f1319797d91da79e7a5ab)
ALTER TABLE KSEMBEDDED.KSEN_ATP ADD CONSTRAINT FKFD3154F804B2705 FOREIGN KEY (ATP_TYPE_ID) REFERENCES KSEMBEDDED.KSEN_ATP_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-889', '2.0.4-SNP', '3:5e78f68cb97f1319797d91da79e7a5ab', 889);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-890::Jeff Caddel (generated)::(Checksum: 3:ae8fd06b6b777260e647183058c4d24c)
ALTER TABLE KSEMBEDDED.KSEN_ATP ADD CONSTRAINT FKFD3154F6C2F628C FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSEN_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-890', '2.0.4-SNP', '3:ae8fd06b6b777260e647183058c4d24c', 890);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-891::Jeff Caddel (generated)::(Checksum: 3:310d3cd9df45f0a61a5e14374fe1d726)
ALTER TABLE KSEMBEDDED.KSEN_ATP_ATTR ADD CONSTRAINT FK3DFA6EE13309051A FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_ATP_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-891', '2.0.4-SNP', '3:310d3cd9df45f0a61a5e14374fe1d726', 891);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-892::Jeff Caddel (generated)::(Checksum: 3:1615af10c9ccfadbcbf702aad4068638)
ALTER TABLE KSEMBEDDED.KSEN_ATP_ATTR ADD CONSTRAINT FK3DFA6EE162FD4240 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_ATP (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-892', '2.0.4-SNP', '3:1615af10c9ccfadbcbf702aad4068638', 892);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-893::Jeff Caddel (generated)::(Checksum: 3:2e11ba706a0028102e772d811d8e41f8)
ALTER TABLE KSEMBEDDED.KSEN_ATP_ATTR ADD CONSTRAINT FK3DFA6EE1BA0FC113 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_MSTONE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-893', '2.0.4-SNP', '3:2e11ba706a0028102e772d811d8e41f8', 893);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-894::Jeff Caddel (generated)::(Checksum: 3:792b7eb3e1922f66bae7e97ecd267e42)
ALTER TABLE KSEMBEDDED.KSEN_ATPATP_RELTN ADD CONSTRAINT FK607450028C6658A FOREIGN KEY (ATP_ID) REFERENCES KSEMBEDDED.KSEN_ATP (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-894', '2.0.4-SNP', '3:792b7eb3e1922f66bae7e97ecd267e42', 894);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-895::Jeff Caddel (generated)::(Checksum: 3:fc76a60fae11d7a4e4f05fb1350372a5)
ALTER TABLE KSEMBEDDED.KSEN_ATPATP_RELTN ADD CONSTRAINT FK60745002E4C71119 FOREIGN KEY (ATP_RELTN_TYPE_ID) REFERENCES KSEMBEDDED.KSEN_ATP_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-895', '2.0.4-SNP', '3:fc76a60fae11d7a4e4f05fb1350372a5', 895);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-896::Jeff Caddel (generated)::(Checksum: 3:85d46c9af80ac8fece916da2539440a9)
ALTER TABLE KSEMBEDDED.KSEN_ATPATP_RELTN ADD CONSTRAINT FK607450026252E155 FOREIGN KEY (ATP_STATE_ID) REFERENCES KSEMBEDDED.KSEN_ATP_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-896', '2.0.4-SNP', '3:85d46c9af80ac8fece916da2539440a9', 896);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-897::Jeff Caddel (generated)::(Checksum: 3:fec8eb61591355b68e85cd2fce4959c5)
ALTER TABLE KSEMBEDDED.KSEN_ATPATP_RELTN ADD CONSTRAINT FK60745002C681D01E FOREIGN KEY (RELATED_ATP_ID) REFERENCES KSEMBEDDED.KSEN_ATP (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-897', '2.0.4-SNP', '3:fec8eb61591355b68e85cd2fce4959c5', 897);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-898::Jeff Caddel (generated)::(Checksum: 3:1474aa4750b4967f5830de420b284de8)
ALTER TABLE KSEMBEDDED.KSEN_ATPATP_RELTN_ATTR ADD CONSTRAINT FK5480EC0E8934C5C5 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_ATPATP_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-898', '2.0.4-SNP', '3:1474aa4750b4967f5830de420b284de8', 898);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-899::Jeff Caddel (generated)::(Checksum: 3:6fef666e1b6c13dd16f5fea850e9a816)
ALTER TABLE KSEMBEDDED.KSEN_ATPATP_RELTN_ATTR ADD CONSTRAINT FK5480EC0EF1998125 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_TYPETYPE_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-899', '2.0.4-SNP', '3:6fef666e1b6c13dd16f5fea850e9a816', 899);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-900::Jeff Caddel (generated)::(Checksum: 3:9fd3ad298b63cb3d961344c6813452ac)
ALTER TABLE KSEMBEDDED.KSEN_ATPMSTONE_RELTN ADD CONSTRAINT FK5480BC9B89043C68 FOREIGN KEY (AM_RELTN_TYPE_ID) REFERENCES KSEMBEDDED.KSEN_ATP_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-900', '2.0.4-SNP', '3:9fd3ad298b63cb3d961344c6813452ac', 900);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-901::Jeff Caddel (generated)::(Checksum: 3:61f6d0d96853df42e8f81ad7ed450524)
ALTER TABLE KSEMBEDDED.KSEN_ATPMSTONE_RELTN ADD CONSTRAINT FK5480BC9B8C6658A FOREIGN KEY (ATP_ID) REFERENCES KSEMBEDDED.KSEN_ATP (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-901', '2.0.4-SNP', '3:61f6d0d96853df42e8f81ad7ed450524', 901);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-902::Jeff Caddel (generated)::(Checksum: 3:ccb890c63d81a83e1cf57269c345f027)
ALTER TABLE KSEMBEDDED.KSEN_ATPMSTONE_RELTN ADD CONSTRAINT FK5480BC9B6252E155 FOREIGN KEY (ATP_STATE_ID) REFERENCES KSEMBEDDED.KSEN_ATP_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-902', '2.0.4-SNP', '3:ccb890c63d81a83e1cf57269c345f027', 902);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-903::Jeff Caddel (generated)::(Checksum: 3:093dc7bd02682644593953204476359b)
ALTER TABLE KSEMBEDDED.KSEN_ATPMSTONE_RELTN ADD CONSTRAINT FK5480BC9B3B007502 FOREIGN KEY (MSTONE_ID) REFERENCES KSEMBEDDED.KSEN_MSTONE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-903', '2.0.4-SNP', '3:093dc7bd02682644593953204476359b', 903);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-904::Jeff Caddel (generated)::(Checksum: 3:7f0e6417a241cc39ebc0cb1642939258)
ALTER TABLE KSEMBEDDED.KSEN_ATPMSTONE_RELTN_ATTR ADD CONSTRAINT FK586D7715FC300ED8 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_ATPMSTONE_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-904', '2.0.4-SNP', '3:7f0e6417a241cc39ebc0cb1642939258', 904);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-905::Jeff Caddel (generated)::(Checksum: 3:28bab17c440e69693deed5ee65498798)
ALTER TABLE KSEMBEDDED.KSEN_HOLD ADD CONSTRAINT FKEA92B02DB16974B FOREIGN KEY (ISSUE_ID) REFERENCES KSEMBEDDED.KSEN_ISSUE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-905', '2.0.4-SNP', '3:28bab17c440e69693deed5ee65498798', 905);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-906::Jeff Caddel (generated)::(Checksum: 3:c4b9a58a00aff99db12315cc0e9f5391)
ALTER TABLE KSEMBEDDED.KSEN_HOLD ADD CONSTRAINT FKEA92B02DCDDEC8D3 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSEN_HOLD_RICH_TEXT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-906', '2.0.4-SNP', '3:c4b9a58a00aff99db12315cc0e9f5391', 906);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-907::Jeff Caddel (generated)::(Checksum: 3:341d885e29b268e8db4b418bb2bb90c1)
ALTER TABLE KSEMBEDDED.KSEN_HOLD ADD CONSTRAINT FKEA92B02D98517E2C FOREIGN KEY (STATE_ID) REFERENCES KSEMBEDDED.KSEN_COMM_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-907', '2.0.4-SNP', '3:341d885e29b268e8db4b418bb2bb90c1', 907);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-908::Jeff Caddel (generated)::(Checksum: 3:50b639074453558f08ddec1c2b15f1f9)
ALTER TABLE KSEMBEDDED.KSEN_HOLD ADD CONSTRAINT FKEA92B02D654F946E FOREIGN KEY (TYPE_ID) REFERENCES KSEMBEDDED.KSEN_HOLD_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-908', '2.0.4-SNP', '3:50b639074453558f08ddec1c2b15f1f9', 908);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-909::Jeff Caddel (generated)::(Checksum: 3:3bc250e44f90b3dc6a5e23d1774a29bf)
ALTER TABLE KSEMBEDDED.KSEN_HOLD_ATTR ADD CONSTRAINT FK87BEEEC3DE9EDF87 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_HOLD (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-909', '2.0.4-SNP', '3:3bc250e44f90b3dc6a5e23d1774a29bf', 909);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-910::Jeff Caddel (generated)::(Checksum: 3:b31b0cde37eae2c307e1eab27eb2bf9a)
ALTER TABLE KSEMBEDDED.KSEN_HOLD_TYPE_ATTR ADD CONSTRAINT FK31328E449E7CA6E1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_HOLD_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-910', '2.0.4-SNP', '3:b31b0cde37eae2c307e1eab27eb2bf9a', 910);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-911::Jeff Caddel (generated)::(Checksum: 3:caa497e5e0714f019e77d84621f89113)
ALTER TABLE KSEMBEDDED.KSEN_ISSUE ADD CONSTRAINT FK67D35B0BCDDEC8D3 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSEN_HOLD_RICH_TEXT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-911', '2.0.4-SNP', '3:caa497e5e0714f019e77d84621f89113', 911);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-912::Jeff Caddel (generated)::(Checksum: 3:6f1879f5bb6b244b9e645974bc6f0f2e)
ALTER TABLE KSEMBEDDED.KSEN_ISSUE ADD CONSTRAINT FK67D35B0B98517E2C FOREIGN KEY (STATE_ID) REFERENCES KSEMBEDDED.KSEN_COMM_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-912', '2.0.4-SNP', '3:6f1879f5bb6b244b9e645974bc6f0f2e', 912);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-913::Jeff Caddel (generated)::(Checksum: 3:7a0b2cad694c02f044a6c10cc321c6f1)
ALTER TABLE KSEMBEDDED.KSEN_ISSUE ADD CONSTRAINT FK67D35B0B654F946E FOREIGN KEY (TYPE_ID) REFERENCES KSEMBEDDED.KSEN_HOLD_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-913', '2.0.4-SNP', '3:7a0b2cad694c02f044a6c10cc321c6f1', 913);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-914::Jeff Caddel (generated)::(Checksum: 3:7c2866f8ef1f887463f61df7280abc3c)
ALTER TABLE KSEMBEDDED.KSEN_ISSUE_ATTR ADD CONSTRAINT FKE52C5EA5E7C51BBD FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_ISSUE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-914', '2.0.4-SNP', '3:7c2866f8ef1f887463f61df7280abc3c', 914);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-915::Jeff Caddel (generated)::(Checksum: 3:43679932ae921193b2923ae46c8c420b)
ALTER TABLE KSEMBEDDED.KSEN_LUI ADD CONSTRAINT FKFD33EB2656AF27 FOREIGN KEY (OFFIC_LUI_ID) REFERENCES KSEMBEDDED.KSEN_LUI_IDENT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-915', '2.0.4-SNP', '3:43679932ae921193b2923ae46c8c420b', 915);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-916::Jeff Caddel (generated)::(Checksum: 3:71f4151d715ffef1333910fa0c930d04)
ALTER TABLE KSEMBEDDED.KSEN_LUI ADD CONSTRAINT FKFD33EB28D1000ED FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSEN_LUI_RICH_TEXT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-916', '2.0.4-SNP', '3:71f4151d715ffef1333910fa0c930d04', 916);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-917::Jeff Caddel (generated)::(Checksum: 3:8823dc26608c81bb3348ed6d8b3821e6)
ALTER TABLE KSEMBEDDED.KSEN_LUI ADD CONSTRAINT FKFD33EB298517E2C FOREIGN KEY (STATE_ID) REFERENCES KSEMBEDDED.KSEN_COMM_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-917', '2.0.4-SNP', '3:8823dc26608c81bb3348ed6d8b3821e6', 917);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-918::Jeff Caddel (generated)::(Checksum: 3:5788410359dc43538896f4a89410e5c8)
ALTER TABLE KSEMBEDDED.KSEN_LUI ADD CONSTRAINT FKFD33EB2B7C4E988 FOREIGN KEY (TYPE_ID) REFERENCES KSEMBEDDED.KSEN_LUI_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-918', '2.0.4-SNP', '3:5788410359dc43538896f4a89410e5c8', 918);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-919::Jeff Caddel (generated)::(Checksum: 3:99c6fda63d9dceb9afe94080cedbcebe)
ALTER TABLE KSEMBEDDED.KSEN_LUI_ATTR ADD CONSTRAINT FKDD9BAB5E6831FFE7 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_LUILUI_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-919', '2.0.4-SNP', '3:99c6fda63d9dceb9afe94080cedbcebe', 919);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-920::Jeff Caddel (generated)::(Checksum: 3:70f41fd03488a2ea05604fb44dbfc548)
ALTER TABLE KSEMBEDDED.KSEN_LUI_ATTR ADD CONSTRAINT FKDD9BAB5E9CEED1A1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_LUI (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-920', '2.0.4-SNP', '3:70f41fd03488a2ea05604fb44dbfc548', 920);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-921::Jeff Caddel (generated)::(Checksum: 3:deaf4dddbe9c22c99644289428fbbe42)
ALTER TABLE KSEMBEDDED.KSEN_LUI_ATTR ADD CONSTRAINT FKDD9BAB5EA1B912DE FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_LUI_INSTR (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-921', '2.0.4-SNP', '3:deaf4dddbe9c22c99644289428fbbe42', 921);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-922::Jeff Caddel (generated)::(Checksum: 3:367943dd92db33d13d03a35cc3157486)
ALTER TABLE KSEMBEDDED.KSEN_LUI_ATTR ADD CONSTRAINT FKDD9BAB5EF0F1FBFB FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_LUI_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-922', '2.0.4-SNP', '3:367943dd92db33d13d03a35cc3157486', 922);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-923::Jeff Caddel (generated)::(Checksum: 3:682aaf9ecea687fb9fcdbc9ab39e6ff6)
ALTER TABLE KSEMBEDDED.KSEN_LUI_IDENT_ATTR ADD CONSTRAINT FK3C63D6ADB1BE75EA FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_LUI_IDENT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-923', '2.0.4-SNP', '3:682aaf9ecea687fb9fcdbc9ab39e6ff6', 923);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-924::Jeff Caddel (generated)::(Checksum: 3:195dcb9785d408c5c1c4a2fe769caa27)
ALTER TABLE KSEMBEDDED.KSEN_LUI_JN_LUI_IDENT ADD CONSTRAINT FK40FD27E33A82D447 FOREIGN KEY (ALT_LUI_ID) REFERENCES KSEMBEDDED.KSEN_LUI_IDENT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-924', '2.0.4-SNP', '3:195dcb9785d408c5c1c4a2fe769caa27', 924);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-925::Jeff Caddel (generated)::(Checksum: 3:0958c9ff10b22192e61874848cd5181d)
ALTER TABLE KSEMBEDDED.KSEN_LUI_JN_LUI_IDENT ADD CONSTRAINT FK40FD27E355882CA8 FOREIGN KEY (LUI_ID) REFERENCES KSEMBEDDED.KSEN_LUI (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-925', '2.0.4-SNP', '3:0958c9ff10b22192e61874848cd5181d', 925);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-926::Jeff Caddel (generated)::(Checksum: 3:873492d23c6a08f96b3a1b431daaec33)
ALTER TABLE KSEMBEDDED.KSEN_LUI_LUCD ADD CONSTRAINT FKDDA0AD1755882CA8 FOREIGN KEY (LUI_ID) REFERENCES KSEMBEDDED.KSEN_LUI (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-926', '2.0.4-SNP', '3:873492d23c6a08f96b3a1b431daaec33', 926);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-927::Jeff Caddel (generated)::(Checksum: 3:65ff5ab4c7f8c88cf2ea8060faf617f1)
ALTER TABLE KSEMBEDDED.KSEN_LUI_LUCD ADD CONSTRAINT FKDDA0AD178D1000ED FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSEN_LUI_RICH_TEXT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-927', '2.0.4-SNP', '3:65ff5ab4c7f8c88cf2ea8060faf617f1', 927);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-928::Jeff Caddel (generated)::(Checksum: 3:e6e49b68e908eac8582cf0baef97dd6c)
ALTER TABLE KSEMBEDDED.KSEN_LUI_LUCD_ATTR ADD CONSTRAINT FK95C7419C1170681 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_LUI_LUCD (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-928', '2.0.4-SNP', '3:e6e49b68e908eac8582cf0baef97dd6c', 928);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-929::Jeff Caddel (generated)::(Checksum: 3:253eabd8584912cc9386544699ebfe75)
ALTER TABLE KSEMBEDDED.KSEN_LUI_TYPE_ATTR ADD CONSTRAINT FKFC6A7A89F0F1FBFB FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_LUI_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-929', '2.0.4-SNP', '3:253eabd8584912cc9386544699ebfe75', 929);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-930::Jeff Caddel (generated)::(Checksum: 3:04d3e878711e1c96f2c9b05aff681bea)
ALTER TABLE KSEMBEDDED.KSEN_LUILUI_RELTN ADD CONSTRAINT FKF07F592255882CA8 FOREIGN KEY (LUI_ID) REFERENCES KSEMBEDDED.KSEN_LUI (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-930', '2.0.4-SNP', '3:04d3e878711e1c96f2c9b05aff681bea', 930);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-931::Jeff Caddel (generated)::(Checksum: 3:0ad3e881f22a9e24ddc2672a93adc25d)
ALTER TABLE KSEMBEDDED.KSEN_LUILUI_RELTN ADD CONSTRAINT FKF07F59221343973C FOREIGN KEY (RELATED_LUI_ID) REFERENCES KSEMBEDDED.KSEN_LUI (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-931', '2.0.4-SNP', '3:0ad3e881f22a9e24ddc2672a93adc25d', 931);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-932::Jeff Caddel (generated)::(Checksum: 3:d278164f5a465603a061d3952cb361ac)
ALTER TABLE KSEMBEDDED.KSEN_LUILUI_RELTN ADD CONSTRAINT FKF07F59228D1000ED FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSEN_LUI_RICH_TEXT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-932', '2.0.4-SNP', '3:d278164f5a465603a061d3952cb361ac', 932);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-933::Jeff Caddel (generated)::(Checksum: 3:d45ef11d6f4a20042b28fac38ab9276e)
ALTER TABLE KSEMBEDDED.KSEN_LUILUI_RELTN ADD CONSTRAINT FKF07F592298517E2C FOREIGN KEY (STATE_ID) REFERENCES KSEMBEDDED.KSEN_COMM_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-933', '2.0.4-SNP', '3:d45ef11d6f4a20042b28fac38ab9276e', 933);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-934::Jeff Caddel (generated)::(Checksum: 3:8cd7a1e0b4f9e7899c181e31d798212e)
ALTER TABLE KSEMBEDDED.KSEN_LUILUI_RELTN ADD CONSTRAINT FKF07F5922B7C4E988 FOREIGN KEY (TYPE_ID) REFERENCES KSEMBEDDED.KSEN_LUI_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-934', '2.0.4-SNP', '3:8cd7a1e0b4f9e7899c181e31d798212e', 934);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-935::Jeff Caddel (generated)::(Checksum: 3:55b92d9446e7fb31c2b4b5c1586df573)
ALTER TABLE KSEMBEDDED.KSEN_LUILUI_RELTN_ATTR ADD CONSTRAINT FKA58E96EE6831FFE7 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_LUILUI_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-935', '2.0.4-SNP', '3:55b92d9446e7fb31c2b4b5c1586df573', 935);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-936::Jeff Caddel (generated)::(Checksum: 3:34f852eacb0c030f8db3af1bcc8dadcc)
ALTER TABLE KSEMBEDDED.KSEN_MSTONE ADD CONSTRAINT FK996BC806C06A3FE2 FOREIGN KEY (MILESTONE_STATE_ID) REFERENCES KSEMBEDDED.KSEN_ATP_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-936', '2.0.4-SNP', '3:34f852eacb0c030f8db3af1bcc8dadcc', 936);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-937::Jeff Caddel (generated)::(Checksum: 3:5d1b39d34ce32104960f7a18bd62c8d4)
ALTER TABLE KSEMBEDDED.KSEN_MSTONE ADD CONSTRAINT FK996BC806FB742D8 FOREIGN KEY (MILESTONE_TYPE_ID) REFERENCES KSEMBEDDED.KSEN_ATP_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-937', '2.0.4-SNP', '3:5d1b39d34ce32104960f7a18bd62c8d4', 937);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-938::Jeff Caddel (generated)::(Checksum: 3:0c8e1e3d7016b248e4fdde8ce3564f96)
ALTER TABLE KSEMBEDDED.KSEN_MSTONE ADD CONSTRAINT FK996BC8066C2F628C FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSEN_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-938', '2.0.4-SNP', '3:0c8e1e3d7016b248e4fdde8ce3564f96', 938);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-939::Jeff Caddel (generated)::(Checksum: 3:81252ef2f88ba6ad9d7e28c1fb5dfd71)
ALTER TABLE KSEMBEDDED.KSEN_RESTRICTION ADD CONSTRAINT FKC2C6CC7ECDDEC8D3 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSEN_HOLD_RICH_TEXT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-939', '2.0.4-SNP', '3:81252ef2f88ba6ad9d7e28c1fb5dfd71', 939);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-940::Jeff Caddel (generated)::(Checksum: 3:2d676f9fe09d5aad9797e4b78503d871)
ALTER TABLE KSEMBEDDED.KSEN_RESTRICTION ADD CONSTRAINT FKC2C6CC7E98517E2C FOREIGN KEY (STATE_ID) REFERENCES KSEMBEDDED.KSEN_COMM_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-940', '2.0.4-SNP', '3:2d676f9fe09d5aad9797e4b78503d871', 940);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-941::Jeff Caddel (generated)::(Checksum: 3:b2a86661f2642140142b8682bb582c63)
ALTER TABLE KSEMBEDDED.KSEN_RESTRICTION ADD CONSTRAINT FKC2C6CC7E654F946E FOREIGN KEY (TYPE_ID) REFERENCES KSEMBEDDED.KSEN_HOLD_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-941', '2.0.4-SNP', '3:b2a86661f2642140142b8682bb582c63', 941);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-942::Jeff Caddel (generated)::(Checksum: 3:2704d66be16320edf7cc7d135f841474)
ALTER TABLE KSEMBEDDED.KSEN_RESTRICTION_ATTR ADD CONSTRAINT FK1F4DD12D828D9F0 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_RESTRICTION (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-942', '2.0.4-SNP', '3:2704d66be16320edf7cc7d135f841474', 942);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-943::Jeff Caddel (generated)::(Checksum: 3:6e61c4e230abccc7b0cc319a7f45ade1)
ALTER TABLE KSEMBEDDED.KSEN_STATE_ATTR ADD CONSTRAINT FK8193D5ED50135956 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_COMM_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-943', '2.0.4-SNP', '3:6e61c4e230abccc7b0cc319a7f45ade1', 943);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-944::Jeff Caddel (generated)::(Checksum: 3:dc78c6eeee05a22636eb44a7d24cda79)
ALTER TABLE KSEMBEDDED.KSEN_STATE_ATTR ADD CONSTRAINT FK8193D5EDD052F725 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSEN_STATE_PROCESS (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-944', '2.0.4-SNP', '3:dc78c6eeee05a22636eb44a7d24cda79', 944);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-945::Jeff Caddel (generated)::(Checksum: 3:7e5ba108f5a498c9999d088a728b5d01)
ALTER TABLE KSEMBEDDED.KSEN_STATEPROCESS_RELTN ADD CONSTRAINT FKCDFDC0E09F94F29D FOREIGN KEY (NEXT_STATEKEY) REFERENCES KSEMBEDDED.KSEN_COMM_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-945', '2.0.4-SNP', '3:7e5ba108f5a498c9999d088a728b5d01', 945);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-946::Jeff Caddel (generated)::(Checksum: 3:1194b76e2ed81fab417cefe7ae3cc101)
ALTER TABLE KSEMBEDDED.KSEN_STATEPROCESS_RELTN ADD CONSTRAINT FKCDFDC0E024D2ACA6 FOREIGN KEY (PRIOR_STATEKEY) REFERENCES KSEMBEDDED.KSEN_COMM_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-946', '2.0.4-SNP', '3:1194b76e2ed81fab417cefe7ae3cc101', 946);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-947::Jeff Caddel (generated)::(Checksum: 3:ac8a271d3567dce53d95b32ea4ca2669)
ALTER TABLE KSEMBEDDED.KSEN_STATEPROCESS_RELTN ADD CONSTRAINT FKCDFDC0E0D6162101 FOREIGN KEY (PROCESS_KEY) REFERENCES KSEMBEDDED.KSEN_STATE_PROCESS (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-947', '2.0.4-SNP', '3:ac8a271d3567dce53d95b32ea4ca2669', 947);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-948::Jeff Caddel (generated)::(Checksum: 3:72dfb75556c04935577e11969e342814)
ALTER TABLE KSEMBEDDED.KSEN_TYPETYPE_RELTN ADD CONSTRAINT FK71693C166C2F628C FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSEN_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-948', '2.0.4-SNP', '3:72dfb75556c04935577e11969e342814', 948);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-949::Jeff Caddel (generated)::(Checksum: 3:9bfd3aa3208e2834a64e8d21ea1537c4)
ALTER TABLE KSEMBEDDED.KSLO_ATTR ADD CONSTRAINT KSLO_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLO_LO (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-949', '2.0.4-SNP', '3:9bfd3aa3208e2834a64e8d21ea1537c4', 949);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-950::Jeff Caddel (generated)::(Checksum: 3:01725085ccdba567b670b3404f3ed23a)
ALTER TABLE KSEMBEDDED.KSLO_LO ADD CONSTRAINT KSLO_LO_FK3 FOREIGN KEY (LO_REPO_ID) REFERENCES KSEMBEDDED.KSLO_LO_REPOSITORY (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-950', '2.0.4-SNP', '3:01725085ccdba567b670b3404f3ed23a', 950);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-951::Jeff Caddel (generated)::(Checksum: 3:80b8125088ddb94c95335be282dd7cbf)
ALTER TABLE KSEMBEDDED.KSLO_LO ADD CONSTRAINT KSLO_LO_FK2 FOREIGN KEY (LOTYPE_ID) REFERENCES KSEMBEDDED.KSLO_LO_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-951', '2.0.4-SNP', '3:80b8125088ddb94c95335be282dd7cbf', 951);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-952::Jeff Caddel (generated)::(Checksum: 3:35375d3640fbbf442189984f53552aa6)
ALTER TABLE KSEMBEDDED.KSLO_LO ADD CONSTRAINT KSLO_LO_FK1 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSLO_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-952', '2.0.4-SNP', '3:35375d3640fbbf442189984f53552aa6', 952);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-953::Jeff Caddel (generated)::(Checksum: 3:75d5b77cb9f4ed0dfcc468a7efc9e7db)
ALTER TABLE KSEMBEDDED.KSLO_LO_CATEGORY ADD CONSTRAINT KSLO_LO_CATEGORY_FK3 FOREIGN KEY (LO_CATEGORY_TYPE_ID) REFERENCES KSEMBEDDED.KSLO_LO_CATEGORY_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-953', '2.0.4-SNP', '3:75d5b77cb9f4ed0dfcc468a7efc9e7db', 953);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-954::Jeff Caddel (generated)::(Checksum: 3:ea492e788e1342c2e747750780ecfee6)
ALTER TABLE KSEMBEDDED.KSLO_LO_CATEGORY ADD CONSTRAINT KSLO_LO_CATEGORY_FK2 FOREIGN KEY (LO_REPO_ID) REFERENCES KSEMBEDDED.KSLO_LO_REPOSITORY (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-954', '2.0.4-SNP', '3:ea492e788e1342c2e747750780ecfee6', 954);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-955::Jeff Caddel (generated)::(Checksum: 3:f1caa610ce187def86f0ca788e726012)
ALTER TABLE KSEMBEDDED.KSLO_LO_CATEGORY ADD CONSTRAINT KSLO_LO_CATEGORY_FK1 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSLO_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-955', '2.0.4-SNP', '3:f1caa610ce187def86f0ca788e726012', 955);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-956::Jeff Caddel (generated)::(Checksum: 3:5f0e8e44c5b06086523751f5e2dd709c)
ALTER TABLE KSEMBEDDED.KSLO_LO_CATEGORY_ATTR ADD CONSTRAINT KSLO_LO_CATEGORY_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLO_LO_CATEGORY (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-956', '2.0.4-SNP', '3:5f0e8e44c5b06086523751f5e2dd709c', 956);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-957::Jeff Caddel (generated)::(Checksum: 3:2dd8744fac11f6a20f90febf4f930455)
ALTER TABLE KSEMBEDDED.KSLO_LO_CATEGORY_TYPE_ATTR ADD CONSTRAINT KSLO_LO_CATEGORY_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLO_LO_CATEGORY_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-957', '2.0.4-SNP', '3:2dd8744fac11f6a20f90febf4f930455', 957);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-958::Jeff Caddel (generated)::(Checksum: 3:9c545daf46654c719fa8db650fb7eef6)
ALTER TABLE KSEMBEDDED.KSLO_LO_JN_LOCATEGORY ADD CONSTRAINT KSLO_LO_JN_LOCATEGORY_FK2 FOREIGN KEY (LO_ID) REFERENCES KSEMBEDDED.KSLO_LO (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-958', '2.0.4-SNP', '3:9c545daf46654c719fa8db650fb7eef6', 958);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-959::Jeff Caddel (generated)::(Checksum: 3:a05397b491b83c0a539273e2b1f83c61)
ALTER TABLE KSEMBEDDED.KSLO_LO_JN_LOCATEGORY ADD CONSTRAINT KSLO_LO_JN_LOCATEGORY_FK1 FOREIGN KEY (LOCATEGORY_ID) REFERENCES KSEMBEDDED.KSLO_LO_CATEGORY (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-959', '2.0.4-SNP', '3:a05397b491b83c0a539273e2b1f83c61', 959);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-960::Jeff Caddel (generated)::(Checksum: 3:25e54b8bd7762966d6df4602a3a767ba)
ALTER TABLE KSEMBEDDED.KSLO_LO_RELTN ADD CONSTRAINT KSLO_LO_RELTN_FK3 FOREIGN KEY (LO_ID) REFERENCES KSEMBEDDED.KSLO_LO (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-960', '2.0.4-SNP', '3:25e54b8bd7762966d6df4602a3a767ba', 960);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-961::Jeff Caddel (generated)::(Checksum: 3:be130fcfcfe0f78e51149f10b3c58a39)
ALTER TABLE KSEMBEDDED.KSLO_LO_RELTN ADD CONSTRAINT KSLO_LO_RELTN_FK1 FOREIGN KEY (LO_LO_RELATION_TYPE_ID) REFERENCES KSEMBEDDED.KSLO_LO_RELTN_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-961', '2.0.4-SNP', '3:be130fcfcfe0f78e51149f10b3c58a39', 961);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-962::Jeff Caddel (generated)::(Checksum: 3:698a88477f3a5059678bf13ee224075c)
ALTER TABLE KSEMBEDDED.KSLO_LO_RELTN ADD CONSTRAINT KSLO_LO_RELTN_FK2 FOREIGN KEY (RELATED_LO_ID) REFERENCES KSEMBEDDED.KSLO_LO (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-962', '2.0.4-SNP', '3:698a88477f3a5059678bf13ee224075c', 962);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-963::Jeff Caddel (generated)::(Checksum: 3:f35cedd38049d76139a6b4727700a2f8)
ALTER TABLE KSEMBEDDED.KSLO_LO_RELTN_ATTR ADD CONSTRAINT KSLO_LO_RELTN_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLO_LO_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-963', '2.0.4-SNP', '3:f35cedd38049d76139a6b4727700a2f8', 963);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-964::Jeff Caddel (generated)::(Checksum: 3:be14a9f14ef9c1e5150c5f485eec1f0f)
ALTER TABLE KSEMBEDDED.KSLO_LO_RELTN_TYPE_ATTR ADD CONSTRAINT KSLO_LO_RELTN_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLO_LO_RELTN_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-964', '2.0.4-SNP', '3:be14a9f14ef9c1e5150c5f485eec1f0f', 964);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-965::Jeff Caddel (generated)::(Checksum: 3:20a5f19cf099dde45dd92f6f6e6d39ff)
ALTER TABLE KSEMBEDDED.KSLO_LO_REPOSITORY ADD CONSTRAINT KSLO_LO_REPOSITORY_FK1 FOREIGN KEY (LO_ROOT_ID) REFERENCES KSEMBEDDED.KSLO_LO (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-965', '2.0.4-SNP', '3:20a5f19cf099dde45dd92f6f6e6d39ff', 965);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-966::Jeff Caddel (generated)::(Checksum: 3:518a0dc8a61b0ac0bad10526bffe4e7c)
ALTER TABLE KSEMBEDDED.KSLO_LO_REPOSITORY ADD CONSTRAINT KSLO_LO_REPOSITORY_FK2 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSLO_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-966', '2.0.4-SNP', '3:518a0dc8a61b0ac0bad10526bffe4e7c', 966);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-967::Jeff Caddel (generated)::(Checksum: 3:5cc888d9b30a56149af89cf62e1136e3)
ALTER TABLE KSEMBEDDED.KSLO_LO_REPOSITORY_ATTR ADD CONSTRAINT KSLO_LO_REPOSITORY_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLO_LO_REPOSITORY (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-967', '2.0.4-SNP', '3:5cc888d9b30a56149af89cf62e1136e3', 967);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-968::Jeff Caddel (generated)::(Checksum: 3:b46d42fa285ae461b7213b538566e87e)
ALTER TABLE KSEMBEDDED.KSLO_LO_TYPE_ATTR ADD CONSTRAINT KSLO_LO_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLO_LO_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-968', '2.0.4-SNP', '3:b46d42fa285ae461b7213b538566e87e', 968);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-969::Jeff Caddel (generated)::(Checksum: 3:47efe70af2e75b7c5f80b72acbaf9287)
ALTER TABLE KSEMBEDDED.KSLP_LPR ADD CONSTRAINT FK1BE1597B61E975F6 FOREIGN KEY (RELATION_STATE_ID) REFERENCES KSEMBEDDED.KSLP_LPR_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-969', '2.0.4-SNP', '3:47efe70af2e75b7c5f80b72acbaf9287', 969);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-970::Jeff Caddel (generated)::(Checksum: 3:994a32889f814189d2a2b18ed431f894)
ALTER TABLE KSEMBEDDED.KSLP_LPR ADD CONSTRAINT FK1BE1597B1D2EFA44 FOREIGN KEY (RELATION_TYPE_ID) REFERENCES KSEMBEDDED.KSLP_LPR_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-970', '2.0.4-SNP', '3:994a32889f814189d2a2b18ed431f894', 970);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-971::Jeff Caddel (generated)::(Checksum: 3:82f920905c10ce6e77a7c4dd5b5014b0)
ALTER TABLE KSEMBEDDED.KSLP_LPR_ATTR ADD CONSTRAINT FKDF4BE635A8427FA FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLP_LPR_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-971', '2.0.4-SNP', '3:82f920905c10ce6e77a7c4dd5b5014b0', 971);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-972::Jeff Caddel (generated)::(Checksum: 3:5373b371f98af99ff7b8632776fd559a)
ALTER TABLE KSEMBEDDED.KSLP_LPR_ATTR ADD CONSTRAINT FKDF4BE635DC3CD520 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLP_LPR (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-972', '2.0.4-SNP', '3:5373b371f98af99ff7b8632776fd559a', 972);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-973::Jeff Caddel (generated)::(Checksum: 3:3f60d00b40b9b396eee6b99670080d1a)
ALTER TABLE KSEMBEDDED.KSLP_LPR_ATTR ADD CONSTRAINT FKDF4BE635EA869E3D FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLP_LPR_STATE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-973', '2.0.4-SNP', '3:3f60d00b40b9b396eee6b99670080d1a', 973);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-974::Jeff Caddel (generated)::(Checksum: 3:f4e4f01814ac883ffe60ba300ceacffc)
ALTER TABLE KSEMBEDDED.KSLR_RESCOMP ADD CONSTRAINT KSLR_RESCOMP_FK2 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSLR_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-974', '2.0.4-SNP', '3:f4e4f01814ac883ffe60ba300ceacffc', 974);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-975::Jeff Caddel (generated)::(Checksum: 3:aede8bc764b29d25e92871b55267ad03)
ALTER TABLE KSEMBEDDED.KSLR_RESCOMP ADD CONSTRAINT KSLR_RESCOMP_FK1 FOREIGN KEY (TYPE) REFERENCES KSEMBEDDED.KSLR_RESCOMP_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-975', '2.0.4-SNP', '3:aede8bc764b29d25e92871b55267ad03', 975);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-976::Jeff Caddel (generated)::(Checksum: 3:170e121d5b42e3da0c5d9bfcd9526814)
ALTER TABLE KSEMBEDDED.KSLR_RESCOMP_ATTR ADD CONSTRAINT KSLR_RESCOMP_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLR_RESCOMP (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-976', '2.0.4-SNP', '3:170e121d5b42e3da0c5d9bfcd9526814', 976);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-977::Jeff Caddel (generated)::(Checksum: 3:9d5d6e42a9bd0d1fd80dfb6ccf059591)
ALTER TABLE KSEMBEDDED.KSLR_RESCOMP_TYPE_ATTR ADD CONSTRAINT KSLR_RESCOMP_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLR_RESCOMP_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-977', '2.0.4-SNP', '3:9d5d6e42a9bd0d1fd80dfb6ccf059591', 977);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-978::Jeff Caddel (generated)::(Checksum: 3:97f9cb83ef971441ead517465659cc2b)
ALTER TABLE KSEMBEDDED.KSLR_RESULT_VALUE ADD CONSTRAINT KSLR_RESULT_VALUE_FK1 FOREIGN KEY (RSLT_COMP_ID) REFERENCES KSEMBEDDED.KSLR_RESCOMP (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-978', '2.0.4-SNP', '3:97f9cb83ef971441ead517465659cc2b', 978);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-979::Jeff Caddel (generated)::(Checksum: 3:8de7aff05344650ca9c4dd6d539c6d2b)
ALTER TABLE KSEMBEDDED.KSLR_SCALE ADD CONSTRAINT KSLR_SCALE_FK1 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSLR_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-979', '2.0.4-SNP', '3:8de7aff05344650ca9c4dd6d539c6d2b', 979);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-980::Jeff Caddel (generated)::(Checksum: 3:604a0c93a1118a8321fa75e86fdf84de)
ALTER TABLE KSEMBEDDED.KSLR_SCALE_ATTR ADD CONSTRAINT KSLR_SCALE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLR_SCALE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-980', '2.0.4-SNP', '3:604a0c93a1118a8321fa75e86fdf84de', 980);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-981::Jeff Caddel (generated)::(Checksum: 3:e2ac918210957b10a29cb3d746501f03)
ALTER TABLE KSEMBEDDED.KSLU_CLU ADD CONSTRAINT KSLU_CLU_FK6 FOREIGN KEY (ACCT_ID) REFERENCES KSEMBEDDED.KSLU_CLU_ACCT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-981', '2.0.4-SNP', '3:e2ac918210957b10a29cb3d746501f03', 981);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-982::Jeff Caddel (generated)::(Checksum: 3:7c0c1c77613eb95249d6569257f67213)
ALTER TABLE KSEMBEDDED.KSLU_CLU ADD CONSTRAINT KSLU_CLU_FK4 FOREIGN KEY (FEE_ID) REFERENCES KSEMBEDDED.KSLU_CLU_FEE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-982', '2.0.4-SNP', '3:7c0c1c77613eb95249d6569257f67213', 982);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-983::Jeff Caddel (generated)::(Checksum: 3:42a3884f93b15008c7ec3f085cdf9891)
ALTER TABLE KSEMBEDDED.KSLU_CLU ADD CONSTRAINT KSLU_CLU_FK2 FOREIGN KEY (LUTYPE_ID) REFERENCES KSEMBEDDED.KSLU_LUTYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-983', '2.0.4-SNP', '3:42a3884f93b15008c7ec3f085cdf9891', 983);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-984::Jeff Caddel (generated)::(Checksum: 3:054ae572b2504972a3500c34b2adf9f6)
ALTER TABLE KSEMBEDDED.KSLU_CLU ADD CONSTRAINT KSLU_CLU_FK5 FOREIGN KEY (OFFIC_CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU_IDENT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-984', '2.0.4-SNP', '3:054ae572b2504972a3500c34b2adf9f6', 984);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-985::Jeff Caddel (generated)::(Checksum: 3:454822ab930f8c4c7282a6c7dc084cce)
ALTER TABLE KSEMBEDDED.KSLU_CLU ADD CONSTRAINT KSLU_CLU_FK1 FOREIGN KEY (PRI_INSTR_ID) REFERENCES KSEMBEDDED.KSLU_CLU_INSTR (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-985', '2.0.4-SNP', '3:454822ab930f8c4c7282a6c7dc084cce', 985);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-986::Jeff Caddel (generated)::(Checksum: 3:ce33b48e5b3b34f3d579162233d7bc69)
ALTER TABLE KSEMBEDDED.KSLU_CLU ADD CONSTRAINT KSLU_CLU_FK3 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSLU_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-986', '2.0.4-SNP', '3:ce33b48e5b3b34f3d579162233d7bc69', 986);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-987::Jeff Caddel (generated)::(Checksum: 3:fb36e6bfbe2c4335041d9a30ed0daf06)
ALTER TABLE KSEMBEDDED.KSLU_CLU_ACCRED_ATTR ADD CONSTRAINT KSLU_CLU_ACCRED_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_ACCRED (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-987', '2.0.4-SNP', '3:fb36e6bfbe2c4335041d9a30ed0daf06', 987);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-988::Jeff Caddel (generated)::(Checksum: 3:948f5b1a16ed2064f06fb0b53a063a4b)
ALTER TABLE KSEMBEDDED.KSLU_CLU_ACCT_ATTR ADD CONSTRAINT KSLU_CLU_ACCT_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_ACCT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-988', '2.0.4-SNP', '3:948f5b1a16ed2064f06fb0b53a063a4b', 988);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-989::Jeff Caddel (generated)::(Checksum: 3:ea25e990257802562bd9a7d32441a9bb)
ALTER TABLE KSEMBEDDED.KSLU_CLU_ACCT_JN_AFFIL_ORG ADD CONSTRAINT KSLU_CLU_ACCT_JN_AFFIL_ORG_FK1 FOREIGN KEY (AFFIL_ORG_ID) REFERENCES KSEMBEDDED.KSLU_CLU_AFFIL_ORG (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-989', '2.0.4-SNP', '3:ea25e990257802562bd9a7d32441a9bb', 989);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-990::Jeff Caddel (generated)::(Checksum: 3:5a422a3c657e2bf02f354d75a07a967f)
ALTER TABLE KSEMBEDDED.KSLU_CLU_ACCT_JN_AFFIL_ORG ADD CONSTRAINT KSLU_CLU_ACCT_JN_AFFIL_ORG_FK2 FOREIGN KEY (CLU_ACCT_ID) REFERENCES KSEMBEDDED.KSLU_CLU_ACCT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-990', '2.0.4-SNP', '3:5a422a3c657e2bf02f354d75a07a967f', 990);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-991::Jeff Caddel (generated)::(Checksum: 3:848fd275c4304b78eaea57b53a41dea8)
ALTER TABLE KSEMBEDDED.KSLU_CLU_ADMIN_ORG ADD CONSTRAINT KSLU_CLU_ADMIN_ORG_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-991', '2.0.4-SNP', '3:848fd275c4304b78eaea57b53a41dea8', 991);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-992::Jeff Caddel (generated)::(Checksum: 3:b441a8d174ce9d0a37d51e94d08cebdf)
ALTER TABLE KSEMBEDDED.KSLU_CLU_ADMIN_ORG_ATTR ADD CONSTRAINT KSLU_CLU_ADMIN_ORG_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_ADMIN_ORG (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-992', '2.0.4-SNP', '3:b441a8d174ce9d0a37d51e94d08cebdf', 992);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-993::Jeff Caddel (generated)::(Checksum: 3:29ec40f6b945695e99e061ec26c3912b)
ALTER TABLE KSEMBEDDED.KSLU_CLU_ATP_TYPE_KEY ADD CONSTRAINT KSLU_CLU_ATP_TYPE_KEY_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-993', '2.0.4-SNP', '3:29ec40f6b945695e99e061ec26c3912b', 993);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-994::Jeff Caddel (generated)::(Checksum: 3:6d0c25a4f4474f12c260d02b8e3490ef)
ALTER TABLE KSEMBEDDED.KSLU_CLU_ATTR ADD CONSTRAINT KSLU_CLU_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-994', '2.0.4-SNP', '3:6d0c25a4f4474f12c260d02b8e3490ef', 994);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-995::Jeff Caddel (generated)::(Checksum: 3:778f7c84506b367e0b6d1085dcd38650)
ALTER TABLE KSEMBEDDED.KSLU_CLU_FEE ADD CONSTRAINT KSLU_CLU_FEE_FK1 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSLU_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-995', '2.0.4-SNP', '3:778f7c84506b367e0b6d1085dcd38650', 995);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-996::Jeff Caddel (generated)::(Checksum: 3:a95c7239a4a1655e52c380b0f263c18f)
ALTER TABLE KSEMBEDDED.KSLU_CLU_FEE_AMOUNT ADD CONSTRAINT KSLU_CLU_FEE_AMOUNT_FK1 FOREIGN KEY (CLUE_FEE_REC_ID) REFERENCES KSEMBEDDED.KSLU_CLU_FEE_REC (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-996', '2.0.4-SNP', '3:a95c7239a4a1655e52c380b0f263c18f', 996);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-997::Jeff Caddel (generated)::(Checksum: 3:e1e6fd349c206824b55e3f928f536c41)
ALTER TABLE KSEMBEDDED.KSLU_CLU_FEE_ATTR ADD CONSTRAINT KSLU_CLU_FEE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_FEE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-997', '2.0.4-SNP', '3:e1e6fd349c206824b55e3f928f536c41', 997);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-998::Jeff Caddel (generated)::(Checksum: 3:e403a60267d1f3815905bed4969db2b3)
ALTER TABLE KSEMBEDDED.KSLU_CLU_FEE_JN_CLU_FEE_REC ADD CONSTRAINT KSLU_CLUFEE_JN_CLUFEE_REC_FK2 FOREIGN KEY (CLU_FEE_ID) REFERENCES KSEMBEDDED.KSLU_CLU_FEE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-998', '2.0.4-SNP', '3:e403a60267d1f3815905bed4969db2b3', 998);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-999::Jeff Caddel (generated)::(Checksum: 3:daf7cf29e399a95e825f21e531456944)
ALTER TABLE KSEMBEDDED.KSLU_CLU_FEE_JN_CLU_FEE_REC ADD CONSTRAINT KSLU_CLUFEE_JN_CLUFEE_REC_FK1 FOREIGN KEY (CLU_FEE_REC_ID) REFERENCES KSEMBEDDED.KSLU_CLU_FEE_REC (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-999', '2.0.4-SNP', '3:daf7cf29e399a95e825f21e531456944', 999);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1000::Jeff Caddel (generated)::(Checksum: 3:92329bf7ab12f9499ebcad773d474094)
ALTER TABLE KSEMBEDDED.KSLU_CLU_FEE_REC ADD CONSTRAINT KSLU_CLU_FEE_REC_FK1 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSLU_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1000', '2.0.4-SNP', '3:92329bf7ab12f9499ebcad773d474094', 1000);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1001::Jeff Caddel (generated)::(Checksum: 3:a763ef74a0f80feab1271479bef5fd52)
ALTER TABLE KSEMBEDDED.KSLU_CLU_FEE_REC_ATTR ADD CONSTRAINT KSLU_CLU_FEE_REC_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_FEE_REC (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1001', '2.0.4-SNP', '3:a763ef74a0f80feab1271479bef5fd52', 1001);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1002::Jeff Caddel (generated)::(Checksum: 3:d2811f87b26ae0a30a08811e403b9ddf)
ALTER TABLE KSEMBEDDED.KSLU_CLU_FEEREC_JN_AFFIL_ORG ADD CONSTRAINT KSLU_CLU_FEEREC_JN_AFF_ORG_FK1 FOREIGN KEY (AFFIL_ORG_ID) REFERENCES KSEMBEDDED.KSLU_CLU_AFFIL_ORG (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1002', '2.0.4-SNP', '3:d2811f87b26ae0a30a08811e403b9ddf', 1002);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1003::Jeff Caddel (generated)::(Checksum: 3:88ce0c99f61fae035b53c15eb5dea2c9)
ALTER TABLE KSEMBEDDED.KSLU_CLU_FEEREC_JN_AFFIL_ORG ADD CONSTRAINT KSLU_CLU_FEEREC_JN_AFF_ORG_FK2 FOREIGN KEY (CLU_FEE_REC_ID) REFERENCES KSEMBEDDED.KSLU_CLU_FEE_REC (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1003', '2.0.4-SNP', '3:88ce0c99f61fae035b53c15eb5dea2c9', 1003);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1004::Jeff Caddel (generated)::(Checksum: 3:11c0ee138d4342a4504a7d860d099fb0)
ALTER TABLE KSEMBEDDED.KSLU_CLU_INSTR_ATTR ADD CONSTRAINT KSLU_CLU_INSTR_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_INSTR (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1004', '2.0.4-SNP', '3:11c0ee138d4342a4504a7d860d099fb0', 1004);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1005::Jeff Caddel (generated)::(Checksum: 3:78719b101852cffe5d244619186b8698)
ALTER TABLE KSEMBEDDED.KSLU_CLU_JN_ACCRED ADD CONSTRAINT KSLU_CLU_JN_ACCRED_FK2 FOREIGN KEY (CLU_ACCRED_ID) REFERENCES KSEMBEDDED.KSLU_CLU_ACCRED (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1005', '2.0.4-SNP', '3:78719b101852cffe5d244619186b8698', 1005);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1006::Jeff Caddel (generated)::(Checksum: 3:d08826f356cc44f905fd456019f4417c)
ALTER TABLE KSEMBEDDED.KSLU_CLU_JN_ACCRED ADD CONSTRAINT KSLU_CLU_JN_ACCRED_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1006', '2.0.4-SNP', '3:d08826f356cc44f905fd456019f4417c', 1006);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1007::Jeff Caddel (generated)::(Checksum: 3:df5b77d09c07019cc5e055456d6ffecd)
ALTER TABLE KSEMBEDDED.KSLU_CLU_JN_CAMP_LOC ADD CONSTRAINT KSLU_CLU_JN_CAMP_LOC_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1007', '2.0.4-SNP', '3:df5b77d09c07019cc5e055456d6ffecd', 1007);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1008::Jeff Caddel (generated)::(Checksum: 3:fbcdaed1eabe450cf187438f2e600be9)
ALTER TABLE KSEMBEDDED.KSLU_CLU_JN_CLU_IDENT ADD CONSTRAINT KSLU_CLU_JN_CLU_IDENT_FK1 FOREIGN KEY (ALT_CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU_IDENT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1008', '2.0.4-SNP', '3:fbcdaed1eabe450cf187438f2e600be9', 1008);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1009::Jeff Caddel (generated)::(Checksum: 3:15c5787352412b450b41857de4ecf30b)
ALTER TABLE KSEMBEDDED.KSLU_CLU_JN_CLU_IDENT ADD CONSTRAINT KSLU_CLU_JN_CLU_IDENT_FK2 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1009', '2.0.4-SNP', '3:15c5787352412b450b41857de4ecf30b', 1009);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1010::Jeff Caddel (generated)::(Checksum: 3:e80b9229286fcbeefd857368b96cbe3f)
ALTER TABLE KSEMBEDDED.KSLU_CLU_JN_CLU_INSTR ADD CONSTRAINT KSLU_CLU_JN_CLU_INSTR_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1010', '2.0.4-SNP', '3:e80b9229286fcbeefd857368b96cbe3f', 1010);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1011::Jeff Caddel (generated)::(Checksum: 3:be6e4f608f292578c79a8b671fa64b5e)
ALTER TABLE KSEMBEDDED.KSLU_CLU_JN_CLU_INSTR ADD CONSTRAINT KSLU_CLU_JN_CLU_INSTR_FK2 FOREIGN KEY (CLU_INSTR_ID) REFERENCES KSEMBEDDED.KSLU_CLU_INSTR (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1011', '2.0.4-SNP', '3:be6e4f608f292578c79a8b671fa64b5e', 1011);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1012::Jeff Caddel (generated)::(Checksum: 3:7cc4a591bb61f9258e611c0aedbeb703)
ALTER TABLE KSEMBEDDED.KSLU_CLU_JN_SUBJ_ORG ADD CONSTRAINT KSLU_CLU_JN_SUBJ_ORG_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1012', '2.0.4-SNP', '3:7cc4a591bb61f9258e611c0aedbeb703', 1012);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1013::Jeff Caddel (generated)::(Checksum: 3:fc5eae89e38907665026d471cabb63f9)
ALTER TABLE KSEMBEDDED.KSLU_CLU_LO_ALOW_RELTN_TYPE ADD CONSTRAINT KSLU_CLU_LO_ALOW_REL_TYPE_FK2 FOREIGN KEY (CLULO_RELTN_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_CLU_LO_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1013', '2.0.4-SNP', '3:fc5eae89e38907665026d471cabb63f9', 1013);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1014::Jeff Caddel (generated)::(Checksum: 3:ce532ebb005493187b0fcc11c2f75b4f)
ALTER TABLE KSEMBEDDED.KSLU_CLU_LO_ALOW_RELTN_TYPE ADD CONSTRAINT KSLU_CLU_LO_ALOW_REL_TYPE_FK1 FOREIGN KEY (LU_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_LUTYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1014', '2.0.4-SNP', '3:ce532ebb005493187b0fcc11c2f75b4f', 1014);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1015::Jeff Caddel (generated)::(Checksum: 3:f1b4884720d55168276bf39c8c102fc1)
ALTER TABLE KSEMBEDDED.KSLU_CLU_LO_RELTN ADD CONSTRAINT KSLU_CLU_LO_RELTN_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1015', '2.0.4-SNP', '3:f1b4884720d55168276bf39c8c102fc1', 1015);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1016::Jeff Caddel (generated)::(Checksum: 3:4173ebd05dedc6e67867ed39f34c48fb)
ALTER TABLE KSEMBEDDED.KSLU_CLU_LO_RELTN ADD CONSTRAINT KSLU_CLU_LO_RELTN_FK2 FOREIGN KEY (TYPE) REFERENCES KSEMBEDDED.KSLU_CLU_LO_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1016', '2.0.4-SNP', '3:4173ebd05dedc6e67867ed39f34c48fb', 1016);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1017::Jeff Caddel (generated)::(Checksum: 3:e9e6c28ebfad9cf94246cd982f50eea3)
ALTER TABLE KSEMBEDDED.KSLU_CLU_LO_RELTN_ATTR ADD CONSTRAINT KSLU_CLU_LO_RELTN_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_LO_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1017', '2.0.4-SNP', '3:e9e6c28ebfad9cf94246cd982f50eea3', 1017);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1018::Jeff Caddel (generated)::(Checksum: 3:781f7e0ae163f363605dc333fefcb0a5)
ALTER TABLE KSEMBEDDED.KSLU_CLU_LO_RELTN_TYPE_ATTR ADD CONSTRAINT KSLU_CLU_LO_REL_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_LO_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1018', '2.0.4-SNP', '3:781f7e0ae163f363605dc333fefcb0a5', 1018);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1019::Jeff Caddel (generated)::(Checksum: 3:233b84918871db7acebbefed1992d76e)
ALTER TABLE KSEMBEDDED.KSLU_CLU_PUB_TYPE_ATTR ADD CONSTRAINT KSLU_CLU_PUB_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_PUB_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1019', '2.0.4-SNP', '3:233b84918871db7acebbefed1992d76e', 1019);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1020::Jeff Caddel (generated)::(Checksum: 3:383e2054a0b4756412324912d7e0b8bf)
ALTER TABLE KSEMBEDDED.KSLU_CLU_PUBL ADD CONSTRAINT KSLU_CLU_PUBL_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1020', '2.0.4-SNP', '3:383e2054a0b4756412324912d7e0b8bf', 1020);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1021::Jeff Caddel (generated)::(Checksum: 3:88e2250c143a46478546a3ba29b4742f)
ALTER TABLE KSEMBEDDED.KSLU_CLU_PUBL ADD CONSTRAINT KSLU_CLU_PUBL_FK2 FOREIGN KEY (CLU_PUB_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_CLU_PUBL_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1021', '2.0.4-SNP', '3:88e2250c143a46478546a3ba29b4742f', 1021);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1022::Jeff Caddel (generated)::(Checksum: 3:c239943359510708cd8902213bc00d5f)
ALTER TABLE KSEMBEDDED.KSLU_CLU_PUBL_ATTR ADD CONSTRAINT KSLU_CLU_PUBL_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_PUBL (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1022', '2.0.4-SNP', '3:c239943359510708cd8902213bc00d5f', 1022);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1023::Jeff Caddel (generated)::(Checksum: 3:83d53f5da1d4fa3a4bb1705154cdd27f)
ALTER TABLE KSEMBEDDED.KSLU_CLU_PUBL_TYPE_ATTR ADD CONSTRAINT KSLU_CLU_PUBL_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_PUBL_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1023', '2.0.4-SNP', '3:83d53f5da1d4fa3a4bb1705154cdd27f', 1023);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1024::Jeff Caddel (generated)::(Checksum: 3:4590ae6c843ed090a2abf400cbb2c161)
ALTER TABLE KSEMBEDDED.KSLU_CLU_PUBL_VARI ADD CONSTRAINT KSLU_CLU_PUBL_VARI_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_PUBL (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1024', '2.0.4-SNP', '3:4590ae6c843ed090a2abf400cbb2c161', 1024);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1025::Jeff Caddel (generated)::(Checksum: 3:0d9f2350234f6f10b5fb8f4ca8cccab4)
ALTER TABLE KSEMBEDDED.KSLU_CLU_RESULT_TYPE_ATTR ADD CONSTRAINT KSLU_CLU_RESULT_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_RSLT_TYP (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1025', '2.0.4-SNP', '3:0d9f2350234f6f10b5fb8f4ca8cccab4', 1025);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1026::Jeff Caddel (generated)::(Checksum: 3:6484def71bec803ae624fb6a120b676d)
ALTER TABLE KSEMBEDDED.KSLU_CLU_RSLT ADD CONSTRAINT KSLU_CLU_RSLT_FK2 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1026', '2.0.4-SNP', '3:6484def71bec803ae624fb6a120b676d', 1026);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1027::Jeff Caddel (generated)::(Checksum: 3:8b533ee8c28dd187ba59a68fe8c11130)
ALTER TABLE KSEMBEDDED.KSLU_CLU_RSLT ADD CONSTRAINT KSLU_CLU_RSLT_FK3 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSLU_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1027', '2.0.4-SNP', '3:8b533ee8c28dd187ba59a68fe8c11130', 1027);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1028::Jeff Caddel (generated)::(Checksum: 3:683a37a6d777b78f2e7730a9d100836e)
ALTER TABLE KSEMBEDDED.KSLU_CLU_RSLT ADD CONSTRAINT KSLU_CLU_RSLT_FK1 FOREIGN KEY (TYPE_KEY_ID) REFERENCES KSEMBEDDED.KSLU_CLU_RSLT_TYP (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1028', '2.0.4-SNP', '3:683a37a6d777b78f2e7730a9d100836e', 1028);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1029::Jeff Caddel (generated)::(Checksum: 3:ce4de1245c8a65159ff39b1ee01ef065)
ALTER TABLE KSEMBEDDED.KSLU_CLU_RSLT_LU_ALOW_TYPE ADD CONSTRAINT KSLU_CLU_RSLT_LU_ALOW_TYPE_FK1 FOREIGN KEY (CLU_RSLT_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_CLU_RSLT_TYP (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1029', '2.0.4-SNP', '3:ce4de1245c8a65159ff39b1ee01ef065', 1029);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1030::Jeff Caddel (generated)::(Checksum: 3:f35b83a7b617af41f9123dce24bd3418)
ALTER TABLE KSEMBEDDED.KSLU_CLU_RSLT_LU_ALOW_TYPE ADD CONSTRAINT KSLU_CLU_RSLT_LU_ALOW_TYPE_FK2 FOREIGN KEY (LU_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_LUTYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1030', '2.0.4-SNP', '3:f35b83a7b617af41f9123dce24bd3418', 1030);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1031::Jeff Caddel (generated)::(Checksum: 3:156f33cdaee01d738840b099afa6d29b)
ALTER TABLE KSEMBEDDED.KSLU_CLU_SET ADD CONSTRAINT KSLU_CLU_SET_FK1 FOREIGN KEY (MEM_QUERY_ID) REFERENCES KSEMBEDDED.KSLU_MEMSHIP (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1031', '2.0.4-SNP', '3:156f33cdaee01d738840b099afa6d29b', 1031);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1032::Jeff Caddel (generated)::(Checksum: 3:06381f62dd3e4c67115cbd10cd7016c6)
ALTER TABLE KSEMBEDDED.KSLU_CLU_SET ADD CONSTRAINT KSLU_CLU_SET_FK2 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSLU_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1032', '2.0.4-SNP', '3:06381f62dd3e4c67115cbd10cd7016c6', 1032);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1033::Jeff Caddel (generated)::(Checksum: 3:fb8f64fb8f9af0c27d52694ed9455861)
ALTER TABLE KSEMBEDDED.KSLU_CLU_SET_ATTR ADD CONSTRAINT KSLU_CLU_SET_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_SET (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1033', '2.0.4-SNP', '3:fb8f64fb8f9af0c27d52694ed9455861', 1033);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1034::Jeff Caddel (generated)::(Checksum: 3:02e436518da88e10b27bd641f874c2bc)
ALTER TABLE KSEMBEDDED.KSLU_CLU_SET_JN_CLU ADD CONSTRAINT KSLU_CLU_SET_JN_CLU_FK1 FOREIGN KEY (CLU_SET_ID) REFERENCES KSEMBEDDED.KSLU_CLU_SET (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1034', '2.0.4-SNP', '3:02e436518da88e10b27bd641f874c2bc', 1034);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1035::Jeff Caddel (generated)::(Checksum: 3:36b03e450a99ad8f7b03582c47ffe024)
ALTER TABLE KSEMBEDDED.KSLU_CLU_SET_JN_CLU_SET ADD CONSTRAINT KSLU_CLU_SET_JN_CLU_SET_FK2 FOREIGN KEY (CLU_SET_CHILD_ID) REFERENCES KSEMBEDDED.KSLU_CLU_SET (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1035', '2.0.4-SNP', '3:36b03e450a99ad8f7b03582c47ffe024', 1035);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1036::Jeff Caddel (generated)::(Checksum: 3:ad09869cb7049e195ffd1d1836c30ea6)
ALTER TABLE KSEMBEDDED.KSLU_CLU_SET_JN_CLU_SET ADD CONSTRAINT KSLU_CLU_SET_JN_CLU_SET_FK1 FOREIGN KEY (CLU_SET_PARENT_ID) REFERENCES KSEMBEDDED.KSLU_CLU_SET (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1036', '2.0.4-SNP', '3:ad09869cb7049e195ffd1d1836c30ea6', 1036);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1037::Jeff Caddel (generated)::(Checksum: 3:cbfc4b5543047e41288c57cc49a2763b)
ALTER TABLE KSEMBEDDED.KSLU_CLU_SET_TYPE_ATTR ADD CONSTRAINT KSLU_CLU_SET_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLU_SET_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1037', '2.0.4-SNP', '3:cbfc4b5543047e41288c57cc49a2763b', 1037);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1038::Jeff Caddel (generated)::(Checksum: 3:657e95e726923fd2550e4318d059a38b)
ALTER TABLE KSEMBEDDED.KSLU_CLUCLU_RELTN ADD CONSTRAINT KSLU_CLUCLU_RELTN_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1038', '2.0.4-SNP', '3:657e95e726923fd2550e4318d059a38b', 1038);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1039::Jeff Caddel (generated)::(Checksum: 3:b152b8e37973764c09149ae37e1a5ee6)
ALTER TABLE KSEMBEDDED.KSLU_CLUCLU_RELTN ADD CONSTRAINT KSLU_CLUCLU_RELTN_FK3 FOREIGN KEY (LU_RELTN_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_LULU_RELTN_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1039', '2.0.4-SNP', '3:b152b8e37973764c09149ae37e1a5ee6', 1039);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1040::Jeff Caddel (generated)::(Checksum: 3:224d0a1d4fa7054ada08ab3f8e25e63e)
ALTER TABLE KSEMBEDDED.KSLU_CLUCLU_RELTN ADD CONSTRAINT KSLU_CLUCLU_RELTN_FK2 FOREIGN KEY (RELATED_CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1040', '2.0.4-SNP', '3:224d0a1d4fa7054ada08ab3f8e25e63e', 1040);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1041::Jeff Caddel (generated)::(Checksum: 3:d73aadb53fc2b13c79fe74e261c66c04)
ALTER TABLE KSEMBEDDED.KSLU_CLUCLU_RELTN_ATTR ADD CONSTRAINT KSLU_CLUCLU_RELTN_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_CLUCLU_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1041', '2.0.4-SNP', '3:d73aadb53fc2b13c79fe74e261c66c04', 1041);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1042::Jeff Caddel (generated)::(Checksum: 3:901f584d10617f2a03462c755ac31df6)
ALTER TABLE KSEMBEDDED.KSLU_CLURES_JN_RESOPT ADD CONSTRAINT KSLU_CLURES_JN_RESOPT_FK1 FOREIGN KEY (CLU_RES_ID) REFERENCES KSEMBEDDED.KSLU_CLU_RSLT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1042', '2.0.4-SNP', '3:901f584d10617f2a03462c755ac31df6', 1042);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1043::Jeff Caddel (generated)::(Checksum: 3:9648f435d98bbbfd63f6ce69641351e5)
ALTER TABLE KSEMBEDDED.KSLU_CLURES_JN_RESOPT ADD CONSTRAINT KSLU_CLURES_JN_RESOPT_FK2 FOREIGN KEY (RES_OPT_ID) REFERENCES KSEMBEDDED.KSLU_RSLT_OPT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1043', '2.0.4-SNP', '3:9648f435d98bbbfd63f6ce69641351e5', 1043);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1044::Jeff Caddel (generated)::(Checksum: 3:f82a3c40a638935f7ad04c149f4789f9)
ALTER TABLE KSEMBEDDED.KSLU_DLVMTHD_TYPE_ATTR ADD CONSTRAINT KSLU_DLVMTHD_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_DLVMTHD_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1044', '2.0.4-SNP', '3:f82a3c40a638935f7ad04c149f4789f9', 1044);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1045::Jeff Caddel (generated)::(Checksum: 3:f1df6710dfaa496cbe1d8ba27b384d54)
ALTER TABLE KSEMBEDDED.KSLU_INSTFRMT_TYPE_ATTR ADD CONSTRAINT KSLU_INSTFRMT_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_INSTFRMT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1045', '2.0.4-SNP', '3:f1df6710dfaa496cbe1d8ba27b384d54', 1045);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1046::Jeff Caddel (generated)::(Checksum: 3:c170c437d1ae82c126f35bb8b5f0b473)
ALTER TABLE KSEMBEDDED.KSLU_LU_CD_TYPE_ATTR ADD CONSTRAINT KSLU_LU_CD_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_LU_CD_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1046', '2.0.4-SNP', '3:c170c437d1ae82c126f35bb8b5f0b473', 1046);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1047::Jeff Caddel (generated)::(Checksum: 3:d33f9941f95d67b5a14dbe52934fc056)
ALTER TABLE KSEMBEDDED.KSLU_LU_CODE ADD CONSTRAINT KSLU_LU_CODE_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1047', '2.0.4-SNP', '3:d33f9941f95d67b5a14dbe52934fc056', 1047);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1048::Jeff Caddel (generated)::(Checksum: 3:753366ca60b1d05a80b7f53e9b05a7a2)
ALTER TABLE KSEMBEDDED.KSLU_LU_CODE_ATTR ADD CONSTRAINT KSLU_LU_CODE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_LU_CODE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1048', '2.0.4-SNP', '3:753366ca60b1d05a80b7f53e9b05a7a2', 1048);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1049::Jeff Caddel (generated)::(Checksum: 3:a63bca27df50f8a56cae52df4d202ae0)
ALTER TABLE KSEMBEDDED.KSLU_LU_LU_ALOW_RELTN_TYPE ADD CONSTRAINT KSLU_LU_LU_ALOW_RELTN_TYPE_FK2 FOREIGN KEY (LU_LU_RELTN_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_LULU_RELTN_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1049', '2.0.4-SNP', '3:a63bca27df50f8a56cae52df4d202ae0', 1049);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1050::Jeff Caddel (generated)::(Checksum: 3:dfa45b435b9c8064b3d1abb18ae5db35)
ALTER TABLE KSEMBEDDED.KSLU_LU_LU_ALOW_RELTN_TYPE ADD CONSTRAINT KSLU_LU_LU_ALOW_RELTN_TYPE_FK1 FOREIGN KEY (LU_REL_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_LUTYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1050', '2.0.4-SNP', '3:dfa45b435b9c8064b3d1abb18ae5db35', 1050);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1051::Jeff Caddel (generated)::(Checksum: 3:4f8931ab647c405b1ae8f4a73e014f3a)
ALTER TABLE KSEMBEDDED.KSLU_LU_LU_ALOW_RELTN_TYPE ADD CONSTRAINT KSLU_LU_LU_ALOW_RELTN_TYPE_FK3 FOREIGN KEY (LU_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_LUTYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1051', '2.0.4-SNP', '3:4f8931ab647c405b1ae8f4a73e014f3a', 1051);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1052::Jeff Caddel (generated)::(Checksum: 3:f0e0ba1c853bbceca824641c50e3f2de)
ALTER TABLE KSEMBEDDED.KSLU_LU_PUBL_TYPE_ATTR ADD CONSTRAINT KSLU_LU_PUBL_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_LU_PUBL_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1052', '2.0.4-SNP', '3:f0e0ba1c853bbceca824641c50e3f2de', 1052);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1053::Jeff Caddel (generated)::(Checksum: 3:1d2e71bd921bf871f1e31c1dfd55fab6)
ALTER TABLE KSEMBEDDED.KSLU_LU_TYPE_ATTR ADD CONSTRAINT KSLU_LU_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_LUTYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1053', '2.0.4-SNP', '3:1d2e71bd921bf871f1e31c1dfd55fab6', 1053);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1054::Jeff Caddel (generated)::(Checksum: 3:209c7b300479c554e9f7658867385fcc)
ALTER TABLE KSEMBEDDED.KSLU_LUI ADD CONSTRAINT KSLU_LUI_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1054', '2.0.4-SNP', '3:209c7b300479c554e9f7658867385fcc', 1054);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1055::Jeff Caddel (generated)::(Checksum: 3:89e2ba65c8d6336671310de10bec4d3a)
ALTER TABLE KSEMBEDDED.KSLU_LUI_ATTR ADD CONSTRAINT KSLU_LUI_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_LUI (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1055', '2.0.4-SNP', '3:89e2ba65c8d6336671310de10bec4d3a', 1055);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1056::Jeff Caddel (generated)::(Checksum: 3:264f6d7ef7a43f7c17eb767e62bc622b)
ALTER TABLE KSEMBEDDED.KSLU_LUILUI_RELTN ADD CONSTRAINT KSLU_LUILUI_RELTN_FK3 FOREIGN KEY (LUI_ID) REFERENCES KSEMBEDDED.KSLU_LUI (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1056', '2.0.4-SNP', '3:264f6d7ef7a43f7c17eb767e62bc622b', 1056);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1057::Jeff Caddel (generated)::(Checksum: 3:619d3f648eaff5ba070e9933adc22c20)
ALTER TABLE KSEMBEDDED.KSLU_LUILUI_RELTN ADD CONSTRAINT KSLU_LUILUI_RELTN_FK2 FOREIGN KEY (LULU_RELTN_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_LULU_RELTN_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1057', '2.0.4-SNP', '3:619d3f648eaff5ba070e9933adc22c20', 1057);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1058::Jeff Caddel (generated)::(Checksum: 3:5779a06abfad8cae75d6115c6c3a874c)
ALTER TABLE KSEMBEDDED.KSLU_LUILUI_RELTN ADD CONSTRAINT KSLU_LUILUI_RELTN_FK1 FOREIGN KEY (RELATED_LUI_ID) REFERENCES KSEMBEDDED.KSLU_LUI (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1058', '2.0.4-SNP', '3:5779a06abfad8cae75d6115c6c3a874c', 1058);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1059::Jeff Caddel (generated)::(Checksum: 3:8d4f6e6c01cadbd47820ed9cfd5b8bef)
ALTER TABLE KSEMBEDDED.KSLU_LUILUI_RELTN_ATTR ADD CONSTRAINT KSLU_LUILUI_RELTN_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_LUILUI_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1059', '2.0.4-SNP', '3:8d4f6e6c01cadbd47820ed9cfd5b8bef', 1059);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1060::Jeff Caddel (generated)::(Checksum: 3:27d69ec3507eb678827876009e52d9c6)
ALTER TABLE KSEMBEDDED.KSLU_LULU_RELTN_TYPE_ATTR ADD CONSTRAINT KSLU_LULU_RELTN_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_LULU_RELTN_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1060', '2.0.4-SNP', '3:27d69ec3507eb678827876009e52d9c6', 1060);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1061::Jeff Caddel (generated)::(Checksum: 3:0a3a32cade7feea304dd6b544a63b11a)
ALTER TABLE KSEMBEDDED.KSLU_LULU_RELTN_TYPE_JN_LU_TYP ADD CONSTRAINT KSLU_LULU_RELTYP_JN_LUTYP_FK2 FOREIGN KEY (LU_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_LUTYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1061', '2.0.4-SNP', '3:0a3a32cade7feea304dd6b544a63b11a', 1061);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1062::Jeff Caddel (generated)::(Checksum: 3:018dbbc3678a7c56d0942adec4f6871a)
ALTER TABLE KSEMBEDDED.KSLU_LULU_RELTN_TYPE_JN_LU_TYP ADD CONSTRAINT KSLU_LULU_RELTYP_JN_LUTYP_FK1 FOREIGN KEY (LULU_RELTN_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_LULU_RELTN_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1062', '2.0.4-SNP', '3:018dbbc3678a7c56d0942adec4f6871a', 1062);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1063::Jeff Caddel (generated)::(Checksum: 3:823a3e9298ced6634aca1a6f85d0ce59)
ALTER TABLE KSEMBEDDED.KSLU_MEMSHIP_KSLU_SPARAM ADD CONSTRAINT KSLU_MEMSHIP_KSLU_SPARAM_FK1 FOREIGN KEY (KSLU_MEMSHIP_ID) REFERENCES KSEMBEDDED.KSLU_MEMSHIP (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1063', '2.0.4-SNP', '3:823a3e9298ced6634aca1a6f85d0ce59', 1063);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1064::Jeff Caddel (generated)::(Checksum: 3:76de645410d459d2d1cde444d07a4aa5)
ALTER TABLE KSEMBEDDED.KSLU_MEMSHIP_KSLU_SPARAM ADD CONSTRAINT KSLU_MEMSHIP_KSLU_SPARAM_FK2 FOREIGN KEY (SEARCHPARAMETERS_ID) REFERENCES KSEMBEDDED.KSLU_SPARAM (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1064', '2.0.4-SNP', '3:76de645410d459d2d1cde444d07a4aa5', 1064);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1065::Jeff Caddel (generated)::(Checksum: 3:abc9bafb7c82134378c7e0c8bb715c8d)
ALTER TABLE KSEMBEDDED.KSLU_RSLT_COMP_USG_ALOW_TYPE ADD CONSTRAINT KSLU_RSLTCOMP_USG_ALOW_TYP_FK1 FOREIGN KEY (RSLT_USG_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_RSLT_USG_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1065', '2.0.4-SNP', '3:abc9bafb7c82134378c7e0c8bb715c8d', 1065);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1066::Jeff Caddel (generated)::(Checksum: 3:6afd7a490ed0d72023764ce621b23a89)
ALTER TABLE KSEMBEDDED.KSLU_RSLT_OPT ADD CONSTRAINT KSLU_RSLT_OPT_FK1 FOREIGN KEY (RES_USAGE_ID) REFERENCES KSEMBEDDED.KSLU_RSLT_USG_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1066', '2.0.4-SNP', '3:6afd7a490ed0d72023764ce621b23a89', 1066);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1067::Jeff Caddel (generated)::(Checksum: 3:6f3a2955041f21b1856ea1b25da1ed1a)
ALTER TABLE KSEMBEDDED.KSLU_RSLT_OPT ADD CONSTRAINT KSLU_RSLT_OPT_FK2 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSLU_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1067', '2.0.4-SNP', '3:6f3a2955041f21b1856ea1b25da1ed1a', 1067);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1068::Jeff Caddel (generated)::(Checksum: 3:64bdf6ec0d045af7fda822e158f567da)
ALTER TABLE KSEMBEDDED.KSLU_RSLT_USG_TYPE_ATTR ADD CONSTRAINT KSLU_RSLT_USG_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSLU_RSLT_USG_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1068', '2.0.4-SNP', '3:64bdf6ec0d045af7fda822e158f567da', 1068);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1069::Jeff Caddel (generated)::(Checksum: 3:4032fe3295a8f923b64b106383c5eb63)
ALTER TABLE KSEMBEDDED.KSLU_RSLTUSAGE_LU_ALOW_TYPE ADD CONSTRAINT KSLU_RSLTUSAGE_LU_ALOW_TYP_FK1 FOREIGN KEY (CLU_RSLT_USAGE_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_RSLT_USG_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1069', '2.0.4-SNP', '3:4032fe3295a8f923b64b106383c5eb63', 1069);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1070::Jeff Caddel (generated)::(Checksum: 3:88915db52dd996275bd1cf40d4626a5e)
ALTER TABLE KSEMBEDDED.KSLU_RSLTUSAGE_LU_ALOW_TYPE ADD CONSTRAINT KSLU_RSLTUSAGE_LU_ALOW_TYP_FK2 FOREIGN KEY (LU_TYPE_ID) REFERENCES KSEMBEDDED.KSLU_LUTYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1070', '2.0.4-SNP', '3:88915db52dd996275bd1cf40d4626a5e', 1070);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1071::Jeff Caddel (generated)::(Checksum: 3:3aa8a1c38d1ae42569a40a523b801040)
ALTER TABLE KSEMBEDDED.KSLU_RSRC ADD CONSTRAINT KSLU_RSRC_FK1 FOREIGN KEY (CLU_ID) REFERENCES KSEMBEDDED.KSLU_CLU (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1071', '2.0.4-SNP', '3:3aa8a1c38d1ae42569a40a523b801040', 1071);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1072::Jeff Caddel (generated)::(Checksum: 3:75a44765156d4555faa368aa5b816f95)
ALTER TABLE KSEMBEDDED.KSLU_SPARAM_KSLU_SPVALUE ADD CONSTRAINT KSLU_SPARAM_KSLU_SPVALUE_FK1 FOREIGN KEY (KSLU_SPARAM_ID) REFERENCES KSEMBEDDED.KSLU_SPARAM (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1072', '2.0.4-SNP', '3:75a44765156d4555faa368aa5b816f95', 1072);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1073::Jeff Caddel (generated)::(Checksum: 3:8fc328a461c607f06654091f49d940b9)
ALTER TABLE KSEMBEDDED.KSLU_SPARAM_KSLU_SPVALUE ADD CONSTRAINT KSLU_SPARAM_KSLU_SPVALUE_FK2 FOREIGN KEY (VALUES_ID) REFERENCES KSEMBEDDED.KSLU_SPVALUE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1073', '2.0.4-SNP', '3:8fc328a461c607f06654091f49d940b9', 1073);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1074::Jeff Caddel (generated)::(Checksum: 3:9799210607cbfc5b65dbe287b924de52)
ALTER TABLE KSEMBEDDED.KSOR_ORG ADD CONSTRAINT KSOR_ORG_FK1 FOREIGN KEY (TYPE) REFERENCES KSEMBEDDED.KSOR_ORG_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1074', '2.0.4-SNP', '3:9799210607cbfc5b65dbe287b924de52', 1074);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1075::Jeff Caddel (generated)::(Checksum: 3:053f9e03428a62b0cfb230fe390413f2)
ALTER TABLE KSEMBEDDED.KSOR_ORG_ATTR ADD CONSTRAINT KSOR_ORG_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSOR_ORG (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1075', '2.0.4-SNP', '3:053f9e03428a62b0cfb230fe390413f2', 1075);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1076::Jeff Caddel (generated)::(Checksum: 3:e63e709422563c73a6957217615710b7)
ALTER TABLE KSEMBEDDED.KSOR_ORG_HIRCHY ADD CONSTRAINT KSOR_ORG_HIRCHY_FK1 FOREIGN KEY (ROOT_ORG) REFERENCES KSEMBEDDED.KSOR_ORG (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1076', '2.0.4-SNP', '3:e63e709422563c73a6957217615710b7', 1076);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1077::Jeff Caddel (generated)::(Checksum: 3:18128e7b1b023113416728359309c11e)
ALTER TABLE KSEMBEDDED.KSOR_ORG_HIRCHY_ATTR ADD CONSTRAINT KSOR_ORG_HIRCHY_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSOR_ORG_HIRCHY (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1077', '2.0.4-SNP', '3:18128e7b1b023113416728359309c11e', 1077);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1078::Jeff Caddel (generated)::(Checksum: 3:2507e772a57e3bf620c573f66e492082)
ALTER TABLE KSEMBEDDED.KSOR_ORG_HIRCHY_JN_ORG_TYPE ADD CONSTRAINT KSOR_ORG_HIRCHY_JN_ORG_TYP_FK1 FOREIGN KEY (ORG_HIRCHY_ID) REFERENCES KSEMBEDDED.KSOR_ORG_HIRCHY (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1078', '2.0.4-SNP', '3:2507e772a57e3bf620c573f66e492082', 1078);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1079::Jeff Caddel (generated)::(Checksum: 3:8761835a0bbe8420a48389636505b6ba)
ALTER TABLE KSEMBEDDED.KSOR_ORG_HIRCHY_JN_ORG_TYPE ADD CONSTRAINT KSOR_ORG_HIRCHY_JN_ORG_TYP_FK2 FOREIGN KEY (ORG_TYPE_ID) REFERENCES KSEMBEDDED.KSOR_ORG_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1079', '2.0.4-SNP', '3:8761835a0bbe8420a48389636505b6ba', 1079);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1080::Jeff Caddel (generated)::(Checksum: 3:44aae149036e50468bd2d34449308fbb)
ALTER TABLE KSEMBEDDED.KSOR_ORG_JN_ORG_PERS_REL_TYPE ADD CONSTRAINT KSOR_ORG_JN_ORG_PERREL_TYP_FK2 FOREIGN KEY (ORG_ID) REFERENCES KSEMBEDDED.KSOR_ORG (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1080', '2.0.4-SNP', '3:44aae149036e50468bd2d34449308fbb', 1080);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1081::Jeff Caddel (generated)::(Checksum: 3:e2b972a2ff28e253d79e21e15227a39f)
ALTER TABLE KSEMBEDDED.KSOR_ORG_JN_ORG_PERS_REL_TYPE ADD CONSTRAINT KSOR_ORG_JN_ORG_PERREL_TYP_FK1 FOREIGN KEY (ORG_PERS_RELTN_TYPE_ID) REFERENCES KSEMBEDDED.KSOR_ORG_PERS_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1081', '2.0.4-SNP', '3:e2b972a2ff28e253d79e21e15227a39f', 1081);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1082::Jeff Caddel (generated)::(Checksum: 3:ab9410a855d015190cea9830211d4849)
ALTER TABLE KSEMBEDDED.KSOR_ORG_ORG_RELTN ADD CONSTRAINT KSOR_ORG_ORG_RELTN_FK1 FOREIGN KEY (ORG) REFERENCES KSEMBEDDED.KSOR_ORG (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1082', '2.0.4-SNP', '3:ab9410a855d015190cea9830211d4849', 1082);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1083::Jeff Caddel (generated)::(Checksum: 3:4ab77c0de7cdcb0cfbf5d436d17a2fc8)
ALTER TABLE KSEMBEDDED.KSOR_ORG_ORG_RELTN ADD CONSTRAINT KSOR_ORG_ORG_RELTN_FK2 FOREIGN KEY (RELATED_ORG) REFERENCES KSEMBEDDED.KSOR_ORG (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1083', '2.0.4-SNP', '3:4ab77c0de7cdcb0cfbf5d436d17a2fc8', 1083);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1084::Jeff Caddel (generated)::(Checksum: 3:5e0682bd38f5e3b94b8be0dbe5af3641)
ALTER TABLE KSEMBEDDED.KSOR_ORG_ORG_RELTN ADD CONSTRAINT KSOR_ORG_ORG_RELTN_FK3 FOREIGN KEY (TYPE) REFERENCES KSEMBEDDED.KSOR_ORG_ORG_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1084', '2.0.4-SNP', '3:5e0682bd38f5e3b94b8be0dbe5af3641', 1084);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1085::Jeff Caddel (generated)::(Checksum: 3:19b4e7de0a809da0ed9efa14d57e33c6)
ALTER TABLE KSEMBEDDED.KSOR_ORG_ORG_RELTN_ATTR ADD CONSTRAINT KSOR_ORG_ORG_RELTN_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSOR_ORG_ORG_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1085', '2.0.4-SNP', '3:19b4e7de0a809da0ed9efa14d57e33c6', 1085);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1086::Jeff Caddel (generated)::(Checksum: 3:74faf1d9d4706d57148eeb1e74cc80f8)
ALTER TABLE KSEMBEDDED.KSOR_ORG_ORG_RELTN_TYPE ADD CONSTRAINT KSOR_ORG_ORG_RELTN_TYPE_FK1 FOREIGN KEY (ORG_HIRCHY) REFERENCES KSEMBEDDED.KSOR_ORG_HIRCHY (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1086', '2.0.4-SNP', '3:74faf1d9d4706d57148eeb1e74cc80f8', 1086);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1087::Jeff Caddel (generated)::(Checksum: 3:5e8df1ee91d88949d1477afdb29e4868)
ALTER TABLE KSEMBEDDED.KSOR_ORG_ORG_RELTN_TYPE_ATTR ADD CONSTRAINT KSOR_ORG_ORG_REL_TYP_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSOR_ORG_ORG_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1087', '2.0.4-SNP', '3:5e8df1ee91d88949d1477afdb29e4868', 1087);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1088::Jeff Caddel (generated)::(Checksum: 3:740d097b3b3d85ea08364161c7879446)
ALTER TABLE KSEMBEDDED.KSOR_ORG_PERS_RELTN ADD CONSTRAINT KSOR_ORG_PERS_RELTN_FK1 FOREIGN KEY (ORG) REFERENCES KSEMBEDDED.KSOR_ORG (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1088', '2.0.4-SNP', '3:740d097b3b3d85ea08364161c7879446', 1088);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1089::Jeff Caddel (generated)::(Checksum: 3:759907f00ef8a1b03a99cb46cacc17ce)
ALTER TABLE KSEMBEDDED.KSOR_ORG_PERS_RELTN ADD CONSTRAINT KSOR_ORG_PERS_RELTN_FK2 FOREIGN KEY (ORG_PERS_RELTN_TYPE) REFERENCES KSEMBEDDED.KSOR_ORG_PERS_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1089', '2.0.4-SNP', '3:759907f00ef8a1b03a99cb46cacc17ce', 1089);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1090::Jeff Caddel (generated)::(Checksum: 3:c9250757d6a9b1dd6711c1cb7dcb518e)
ALTER TABLE KSEMBEDDED.KSOR_ORG_PERS_RELTN_ATTR ADD CONSTRAINT KSOR_ORG_PERS_RELTN_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSOR_ORG_PERS_RELTN (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1090', '2.0.4-SNP', '3:c9250757d6a9b1dd6711c1cb7dcb518e', 1090);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1091::Jeff Caddel (generated)::(Checksum: 3:b4d331274296d75d2958e452ed161f07)
ALTER TABLE KSEMBEDDED.KSOR_ORG_PERS_RELTN_TYPE_ATTR ADD CONSTRAINT KSOR_ORG_PERS_REL_TYP_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSOR_ORG_PERS_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1091', '2.0.4-SNP', '3:b4d331274296d75d2958e452ed161f07', 1091);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1092::Jeff Caddel (generated)::(Checksum: 3:f6849957a445f118d88ded82204a709c)
ALTER TABLE KSEMBEDDED.KSOR_ORG_POS_RESTR ADD CONSTRAINT KSOR_ORG_POS_RESTR_FK2 FOREIGN KEY (ORG) REFERENCES KSEMBEDDED.KSOR_ORG (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1092', '2.0.4-SNP', '3:f6849957a445f118d88ded82204a709c', 1092);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1093::Jeff Caddel (generated)::(Checksum: 3:b49d07e8eae31af2333498f5e84c08e2)
ALTER TABLE KSEMBEDDED.KSOR_ORG_POS_RESTR ADD CONSTRAINT KSOR_ORG_POS_RESTR_FK1 FOREIGN KEY (PERS_RELTN_TYPE) REFERENCES KSEMBEDDED.KSOR_ORG_PERS_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1093', '2.0.4-SNP', '3:b49d07e8eae31af2333498f5e84c08e2', 1093);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1094::Jeff Caddel (generated)::(Checksum: 3:88d455e433e2846b8d81b29354cb33e4)
ALTER TABLE KSEMBEDDED.KSOR_ORG_POS_RESTR_ATTR ADD CONSTRAINT KSOR_ORG_POS_RESTR_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSOR_ORG_POS_RESTR (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1094', '2.0.4-SNP', '3:88d455e433e2846b8d81b29354cb33e4', 1094);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1095::Jeff Caddel (generated)::(Checksum: 3:1ab1c534d637979378c7593029f57e77)
ALTER TABLE KSEMBEDDED.KSOR_ORG_TYPE_ATTR ADD CONSTRAINT KSOR_ORG_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSOR_ORG_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1095', '2.0.4-SNP', '3:1ab1c534d637979378c7593029f57e77', 1095);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1096::Jeff Caddel (generated)::(Checksum: 3:f9f59e712adbf7cb26960e030758a9f8)
ALTER TABLE KSEMBEDDED.KSOR_ORG_TYPE_JN_ORG_PERRL_TYP ADD CONSTRAINT KSOR_ORGTYP_JN_ORGPREL_TYP_FK1 FOREIGN KEY (ORG_PERS_RELTN_TYPE_ID) REFERENCES KSEMBEDDED.KSOR_ORG_PERS_RELTN_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1096', '2.0.4-SNP', '3:f9f59e712adbf7cb26960e030758a9f8', 1096);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1097::Jeff Caddel (generated)::(Checksum: 3:37858c382dae28d03bc8fd9a6f8fc23c)
ALTER TABLE KSEMBEDDED.KSOR_ORG_TYPE_JN_ORG_PERRL_TYP ADD CONSTRAINT KSOR_ORGTYP_JN_ORGPREL_TYP_FK2 FOREIGN KEY (ORG_TYPE_ID) REFERENCES KSEMBEDDED.KSOR_ORG_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1097', '2.0.4-SNP', '3:37858c382dae28d03bc8fd9a6f8fc23c', 1097);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1098::Jeff Caddel (generated)::(Checksum: 3:737e8ef95d54d1fb3f96ce0ec0534235)
ALTER TABLE KSEMBEDDED.KSPR_PROPOSAL ADD CONSTRAINT KSPR_PROPOSAL_FK1 FOREIGN KEY (TYPE) REFERENCES KSEMBEDDED.KSPR_PROPOSAL_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1098', '2.0.4-SNP', '3:737e8ef95d54d1fb3f96ce0ec0534235', 1098);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1099::Jeff Caddel (generated)::(Checksum: 3:d6155c1cf3900e4e87b9a584f0c3bec6)
ALTER TABLE KSEMBEDDED.KSPR_PROPOSAL_ATTR ADD CONSTRAINT KSPR_PROPOSAL_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSPR_PROPOSAL (PROPOSAL_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1099', '2.0.4-SNP', '3:d6155c1cf3900e4e87b9a584f0c3bec6', 1099);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1100::Jeff Caddel (generated)::(Checksum: 3:103d2862e65c8836408c3c30bc2a5700)
ALTER TABLE KSEMBEDDED.KSPR_PROPOSAL_JN_ORG ADD CONSTRAINT KSPR_PROPOSAL_JN_ORG_FK1 FOREIGN KEY (PROPOSAL_ID) REFERENCES KSEMBEDDED.KSPR_PROPOSAL (PROPOSAL_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1100', '2.0.4-SNP', '3:103d2862e65c8836408c3c30bc2a5700', 1100);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1101::Jeff Caddel (generated)::(Checksum: 3:2074374afad047269e36b89198a5b7ae)
ALTER TABLE KSEMBEDDED.KSPR_PROPOSAL_JN_PERSON ADD CONSTRAINT KSPR_PROPOSAL_JN_PERSON_FK1 FOREIGN KEY (PROPOSAL_ID) REFERENCES KSEMBEDDED.KSPR_PROPOSAL (PROPOSAL_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1101', '2.0.4-SNP', '3:2074374afad047269e36b89198a5b7ae', 1101);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1102::Jeff Caddel (generated)::(Checksum: 3:4efc2e27cd30fd83b9067b82e0caca21)
ALTER TABLE KSEMBEDDED.KSPR_PROPOSAL_JN_REFERENCE ADD CONSTRAINT KSPR_PROPOSAL_JN_REFERENCE_FK2 FOREIGN KEY (PROPOSAL_ID) REFERENCES KSEMBEDDED.KSPR_PROPOSAL (PROPOSAL_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1102', '2.0.4-SNP', '3:4efc2e27cd30fd83b9067b82e0caca21', 1102);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1103::Jeff Caddel (generated)::(Checksum: 3:338be72debebf1f83f70c541a63b30f8)
ALTER TABLE KSEMBEDDED.KSPR_PROPOSAL_JN_REFERENCE ADD CONSTRAINT KSPR_PROPOSAL_JN_REFERENCE_FK1 FOREIGN KEY (REFERENCE_ID) REFERENCES KSEMBEDDED.KSPR_PROPOSAL_REFERENCE (REFERENCE_ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1103', '2.0.4-SNP', '3:338be72debebf1f83f70c541a63b30f8', 1103);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1104::Jeff Caddel (generated)::(Checksum: 3:d5becc84e1231381d59d6c53939fea89)
ALTER TABLE KSEMBEDDED.KSPR_PROPOSAL_REFERENCE ADD CONSTRAINT KSPR_PROPOSAL_REFERENCE_FK1 FOREIGN KEY (TYPE) REFERENCES KSEMBEDDED.KSPR_PROPOSAL_REFTYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1104', '2.0.4-SNP', '3:d5becc84e1231381d59d6c53939fea89', 1104);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1105::Jeff Caddel (generated)::(Checksum: 3:8e41fe3bc35bd6da9a609aae9787fd16)
ALTER TABLE KSEMBEDDED.KSPR_PROPOSAL_REFTYPE_ATTR ADD CONSTRAINT KSPR_PROPOSAL_REFTYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSPR_PROPOSAL_REFTYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1105', '2.0.4-SNP', '3:8e41fe3bc35bd6da9a609aae9787fd16', 1105);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1106::Jeff Caddel (generated)::(Checksum: 3:9dd58d1306c120743f4a31923b151179)
ALTER TABLE KSEMBEDDED.KSPR_PROPOSAL_TYPE_ATTR ADD CONSTRAINT KSPR_PROPOSAL_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSPR_PROPOSAL_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1106', '2.0.4-SNP', '3:9dd58d1306c120743f4a31923b151179', 1106);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1107::Jeff Caddel (generated)::(Checksum: 3:0b8b4cb21029624ad1a34dba8fea9158)
ALTER TABLE KSEMBEDDED.KSST_OBJ_TYP_JN_OBJ_SUB_TYP ADD CONSTRAINT KSST_OBJ_TYP_JN_OBJ_SUBTYP_FK1 FOREIGN KEY (OBJ_SUB_TYPE_ID) REFERENCES KSEMBEDDED.KSST_OBJECT_SUB_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1107', '2.0.4-SNP', '3:0b8b4cb21029624ad1a34dba8fea9158', 1107);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1108::Jeff Caddel (generated)::(Checksum: 3:9eb8c7cf03d04cc04f431b43422e80ea)
ALTER TABLE KSEMBEDDED.KSST_OBJ_TYP_JN_OBJ_SUB_TYP ADD CONSTRAINT KSST_OBJ_TYP_JN_OBJ_SUBTYP_FK2 FOREIGN KEY (OBJ_TYPE_ID) REFERENCES KSEMBEDDED.KSST_OBJECT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1108', '2.0.4-SNP', '3:9eb8c7cf03d04cc04f431b43422e80ea', 1108);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1109::Jeff Caddel (generated)::(Checksum: 3:66fdbb829656faa6acc3652da13fb3f0)
ALTER TABLE KSEMBEDDED.KSST_OBJECT_SUB_TYPE_ATTR ADD CONSTRAINT KSST_OBJECT_SUB_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSST_OBJECT_SUB_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1109', '2.0.4-SNP', '3:66fdbb829656faa6acc3652da13fb3f0', 1109);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1110::Jeff Caddel (generated)::(Checksum: 3:80864afa04e0bb15c3a60af056d9eab4)
ALTER TABLE KSEMBEDDED.KSST_OBJECT_TYPE_ATTR ADD CONSTRAINT KSST_OBJECT_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSST_OBJECT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1110', '2.0.4-SNP', '3:80864afa04e0bb15c3a60af056d9eab4', 1110);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1111::Jeff Caddel (generated)::(Checksum: 3:2a1a854d640fd04064ec46377f3d2db3)
ALTER TABLE KSEMBEDDED.KSST_RC_JN_RC_FIELD ADD CONSTRAINT KSST_RC_JN_RC_FIELD_FK2 FOREIGN KEY (REQ_COM_FIELD_ID) REFERENCES KSEMBEDDED.KSST_REQ_COM_FIELD (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1111', '2.0.4-SNP', '3:2a1a854d640fd04064ec46377f3d2db3', 1111);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1112::Jeff Caddel (generated)::(Checksum: 3:40cb707a97d2cd302baba902bf07fae5)
ALTER TABLE KSEMBEDDED.KSST_RC_JN_RC_FIELD ADD CONSTRAINT KSST_RC_JN_RC_FIELD_FK1 FOREIGN KEY (REQ_COM_ID) REFERENCES KSEMBEDDED.KSST_REQ_COM (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1112', '2.0.4-SNP', '3:40cb707a97d2cd302baba902bf07fae5', 1112);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1113::Jeff Caddel (generated)::(Checksum: 3:4915bce9151c591ec89f6619c05461ff)
ALTER TABLE KSEMBEDDED.KSST_RCTYP_JN_RCFLDTYP ADD CONSTRAINT KSST_RCTYP_JN_RCFLDTYP_FK1 FOREIGN KEY (REQ_COMP_FIELD_TYPE_ID) REFERENCES KSEMBEDDED.KSST_REQ_COM_FIELD_TYPE (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1113', '2.0.4-SNP', '3:4915bce9151c591ec89f6619c05461ff', 1113);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1114::Jeff Caddel (generated)::(Checksum: 3:fa06fd9eaf0c0a120db1f38f84d119fe)
ALTER TABLE KSEMBEDDED.KSST_RCTYP_JN_RCFLDTYP ADD CONSTRAINT KSST_RCTYP_JN_RCFLDTYP_FK2 FOREIGN KEY (REQ_COMP_TYPE_ID) REFERENCES KSEMBEDDED.KSST_REQ_COM_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1114', '2.0.4-SNP', '3:fa06fd9eaf0c0a120db1f38f84d119fe', 1114);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1115::Jeff Caddel (generated)::(Checksum: 3:70294af65b2d208cfe54811ee8a90ccd)
ALTER TABLE KSEMBEDDED.KSST_REF_STMT_REL ADD CONSTRAINT KSST_REF_STMT_REL_FK1 FOREIGN KEY (REF_STMT_REL_TYPE_ID) REFERENCES KSEMBEDDED.KSST_REF_STMT_REL_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1115', '2.0.4-SNP', '3:70294af65b2d208cfe54811ee8a90ccd', 1115);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1116::Jeff Caddel (generated)::(Checksum: 3:16ee7b25da38167270b5e23930192830)
ALTER TABLE KSEMBEDDED.KSST_REF_STMT_REL ADD CONSTRAINT KSST_REF_STMT_REL_FK2 FOREIGN KEY (STMT_ID) REFERENCES KSEMBEDDED.KSST_STMT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1116', '2.0.4-SNP', '3:16ee7b25da38167270b5e23930192830', 1116);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1117::Jeff Caddel (generated)::(Checksum: 3:7117bdbc7b5642d8fa3d36730ed7b4f7)
ALTER TABLE KSEMBEDDED.KSST_REF_STMT_REL_ATTR ADD CONSTRAINT KSST_REF_STMT_REL_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSST_REF_STMT_REL (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1117', '2.0.4-SNP', '3:7117bdbc7b5642d8fa3d36730ed7b4f7', 1117);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1118::Jeff Caddel (generated)::(Checksum: 3:a840bb26a813e78312050bd9d5a96aa0)
ALTER TABLE KSEMBEDDED.KSST_REF_STMT_REL_TYPE_ATTR ADD CONSTRAINT KSST_REF_STMT_REL_TYP_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSST_REF_STMT_REL_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1118', '2.0.4-SNP', '3:a840bb26a813e78312050bd9d5a96aa0', 1118);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1119::Jeff Caddel (generated)::(Checksum: 3:92e0bdbea01f96e5f15f2bf5bb311a39)
ALTER TABLE KSEMBEDDED.KSST_REQ_COM ADD CONSTRAINT KSST_REQ_COM_FK1 FOREIGN KEY (REQ_COM_TYPE_ID) REFERENCES KSEMBEDDED.KSST_REQ_COM_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1119', '2.0.4-SNP', '3:92e0bdbea01f96e5f15f2bf5bb311a39', 1119);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1120::Jeff Caddel (generated)::(Checksum: 3:c9c13b43234a4aa7b17d22dbb9d81314)
ALTER TABLE KSEMBEDDED.KSST_REQ_COM ADD CONSTRAINT KSST_REQ_COM_FK2 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSST_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1120', '2.0.4-SNP', '3:c9c13b43234a4aa7b17d22dbb9d81314', 1120);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1121::Jeff Caddel (generated)::(Checksum: 3:eb9899e5f9d52792316e250b60726f5a)
ALTER TABLE KSEMBEDDED.KSST_REQ_COM_TYPE_ATTR ADD CONSTRAINT KSST_REQ_COM_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSST_REQ_COM_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1121', '2.0.4-SNP', '3:eb9899e5f9d52792316e250b60726f5a', 1121);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1122::Jeff Caddel (generated)::(Checksum: 3:519631f006f630c85cbf947cae2f60f8)
ALTER TABLE KSEMBEDDED.KSST_REQ_COM_TYPE_NL_TMPL ADD CONSTRAINT KSST_REQ_COM_TYPE_NL_TMPL_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSST_REQ_COM_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1122', '2.0.4-SNP', '3:519631f006f630c85cbf947cae2f60f8', 1122);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1123::Jeff Caddel (generated)::(Checksum: 3:6921f1ce9bd7582c967fe80d3d5841d1)
ALTER TABLE KSEMBEDDED.KSST_RSTMT_RTYP_JN_OSUB_TYP ADD CONSTRAINT KSST_RSTMT_RTYP_JN_OSUBTYP_FK1 FOREIGN KEY (OBJ_SUB_TYPE_ID) REFERENCES KSEMBEDDED.KSST_OBJECT_SUB_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1123', '2.0.4-SNP', '3:6921f1ce9bd7582c967fe80d3d5841d1', 1123);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1124::Jeff Caddel (generated)::(Checksum: 3:4ca02c8d5696745d6d3e061ba14cdfb9)
ALTER TABLE KSEMBEDDED.KSST_RSTMT_RTYP_JN_OSUB_TYP ADD CONSTRAINT KSST_RSTMT_RTYP_JN_OSUBTYP_FK2 FOREIGN KEY (REF_STMT_REL_TYPE_ID) REFERENCES KSEMBEDDED.KSST_REF_STMT_REL_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1124', '2.0.4-SNP', '3:4ca02c8d5696745d6d3e061ba14cdfb9', 1124);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1125::Jeff Caddel (generated)::(Checksum: 3:07e30268420ddd0a47e7d60b50949f1a)
ALTER TABLE KSEMBEDDED.KSST_RSTMT_RTYP_JN_STMT_TYP ADD CONSTRAINT KSST_RSTMT_RTYP_JN_ST_TYP_FK1 FOREIGN KEY (REF_STMT_REL_TYPE_ID) REFERENCES KSEMBEDDED.KSST_REF_STMT_REL_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1125', '2.0.4-SNP', '3:07e30268420ddd0a47e7d60b50949f1a', 1125);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1126::Jeff Caddel (generated)::(Checksum: 3:2f83cb0e1f956de209a97df610cf719b)
ALTER TABLE KSEMBEDDED.KSST_RSTMT_RTYP_JN_STMT_TYP ADD CONSTRAINT KSST_RSTMT_RTYP_JN_ST_TYP_FK2 FOREIGN KEY (STMT_TYPE_ID) REFERENCES KSEMBEDDED.KSST_STMT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1126', '2.0.4-SNP', '3:2f83cb0e1f956de209a97df610cf719b', 1126);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1127::Jeff Caddel (generated)::(Checksum: 3:f2cd60dd209cdce3b357c91f0e6cc346)
ALTER TABLE KSEMBEDDED.KSST_STMT ADD CONSTRAINT KSST_STMT_FK2 FOREIGN KEY (RT_DESCR_ID) REFERENCES KSEMBEDDED.KSST_RICH_TEXT_T (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1127', '2.0.4-SNP', '3:f2cd60dd209cdce3b357c91f0e6cc346', 1127);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1128::Jeff Caddel (generated)::(Checksum: 3:ff88f010c53840fc734e8b720bc7e154)
ALTER TABLE KSEMBEDDED.KSST_STMT ADD CONSTRAINT KSST_STMT_FK1 FOREIGN KEY (STMT_TYPE_ID) REFERENCES KSEMBEDDED.KSST_STMT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1128', '2.0.4-SNP', '3:ff88f010c53840fc734e8b720bc7e154', 1128);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1129::Jeff Caddel (generated)::(Checksum: 3:0a1190e379d8cd4abce5e2b807fbd126)
ALTER TABLE KSEMBEDDED.KSST_STMT_ATTR ADD CONSTRAINT KSST_STMT_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSST_STMT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1129', '2.0.4-SNP', '3:0a1190e379d8cd4abce5e2b807fbd126', 1129);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1130::Jeff Caddel (generated)::(Checksum: 3:45505b7dd5977686322813c4e638ce58)
ALTER TABLE KSEMBEDDED.KSST_STMT_JN_REQ_COM ADD CONSTRAINT KSST_STMT_JN_REQ_COM_FK2 FOREIGN KEY (REQ_COM_ID) REFERENCES KSEMBEDDED.KSST_REQ_COM (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1130', '2.0.4-SNP', '3:45505b7dd5977686322813c4e638ce58', 1130);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1131::Jeff Caddel (generated)::(Checksum: 3:9630b4bfe20d708ab3e9385d1cc70d1d)
ALTER TABLE KSEMBEDDED.KSST_STMT_JN_REQ_COM ADD CONSTRAINT KSST_STMT_JN_REQ_COM_FK1 FOREIGN KEY (STMT_ID) REFERENCES KSEMBEDDED.KSST_STMT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1131', '2.0.4-SNP', '3:9630b4bfe20d708ab3e9385d1cc70d1d', 1131);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1132::Jeff Caddel (generated)::(Checksum: 3:011e29151538affe20b49857805f12bc)
ALTER TABLE KSEMBEDDED.KSST_STMT_JN_STMT ADD CONSTRAINT KSST_STMT_JN_STMT_FK2 FOREIGN KEY (CHLD_STMT_ID) REFERENCES KSEMBEDDED.KSST_STMT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1132', '2.0.4-SNP', '3:011e29151538affe20b49857805f12bc', 1132);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1133::Jeff Caddel (generated)::(Checksum: 3:1518cd40615d9b409ea76da377e2e9ea)
ALTER TABLE KSEMBEDDED.KSST_STMT_JN_STMT ADD CONSTRAINT KSST_STMT_JN_STMT_FK1 FOREIGN KEY (STMT_ID) REFERENCES KSEMBEDDED.KSST_STMT (ID);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1133', '2.0.4-SNP', '3:1518cd40615d9b409ea76da377e2e9ea', 1133);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1134::Jeff Caddel (generated)::(Checksum: 3:2f83d9fac0f84d1d87de1807a896986f)
ALTER TABLE KSEMBEDDED.KSST_STMT_TYP_JN_RC_TYP ADD CONSTRAINT KSST_STMT_TYP_JN_RC_TYP_FK1 FOREIGN KEY (REQ_COM_TYPE_ID) REFERENCES KSEMBEDDED.KSST_REQ_COM_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1134', '2.0.4-SNP', '3:2f83d9fac0f84d1d87de1807a896986f', 1134);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1135::Jeff Caddel (generated)::(Checksum: 3:73132ea846424d9513c1c289c864845f)
ALTER TABLE KSEMBEDDED.KSST_STMT_TYP_JN_RC_TYP ADD CONSTRAINT KSST_STMT_TYP_JN_RC_TYP_FK2 FOREIGN KEY (STMT_TYPE_ID) REFERENCES KSEMBEDDED.KSST_STMT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1135', '2.0.4-SNP', '3:73132ea846424d9513c1c289c864845f', 1135);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1136::Jeff Caddel (generated)::(Checksum: 3:a37bd41196e26a9ae882cd91b26b5b62)
ALTER TABLE KSEMBEDDED.KSST_STMT_TYP_JN_STMT_TYP ADD CONSTRAINT KSST_STMT_TYP_JN_STMT_TYP_FK1 FOREIGN KEY (CHLD_STMT_TYPE_ID) REFERENCES KSEMBEDDED.KSST_STMT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1136', '2.0.4-SNP', '3:a37bd41196e26a9ae882cd91b26b5b62', 1136);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1137::Jeff Caddel (generated)::(Checksum: 3:405cc7665444394026f836bbdd567d5c)
ALTER TABLE KSEMBEDDED.KSST_STMT_TYP_JN_STMT_TYP ADD CONSTRAINT KSST_STMT_TYP_JN_STMT_TYP_FK2 FOREIGN KEY (STMT_TYPE_ID) REFERENCES KSEMBEDDED.KSST_STMT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1137', '2.0.4-SNP', '3:405cc7665444394026f836bbdd567d5c', 1137);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1138::Jeff Caddel (generated)::(Checksum: 3:965f5e21a229c46cec4bb995a05f71c4)
ALTER TABLE KSEMBEDDED.KSST_STMT_TYPE_ATTR ADD CONSTRAINT KSST_STMT_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSST_STMT_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1138', '2.0.4-SNP', '3:965f5e21a229c46cec4bb995a05f71c4', 1138);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1139::Jeff Caddel (generated)::(Checksum: 3:cd876350005f5276f0d10c387359a3d7)
ALTER TABLE KSEMBEDDED.KSST_USAGE_TYPE_ATTR ADD CONSTRAINT KSST_USAGE_TYPE_ATTR_FK1 FOREIGN KEY (OWNER) REFERENCES KSEMBEDDED.KSST_NL_USAGE_TYPE (TYPE_KEY);

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1139', '2.0.4-SNP', '3:cd876350005f5276f0d10c387359a3d7', 1139);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1140::Jeff Caddel (generated)::(Checksum: 3:9be6758886d08fdf050f250753261973)
CREATE SEQUENCE KSEMBEDDED.KREN_CHNL_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1140', '2.0.4-SNP', '3:9be6758886d08fdf050f250753261973', 1140);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1141::Jeff Caddel (generated)::(Checksum: 3:23965f5a15c099def363279d7038ac3b)
CREATE SEQUENCE KSEMBEDDED.KREN_CHNL_SUBSCRP_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1141', '2.0.4-SNP', '3:23965f5a15c099def363279d7038ac3b', 1141);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1142::Jeff Caddel (generated)::(Checksum: 3:3505cb41f0a205efdf5685265041e393)
CREATE SEQUENCE KSEMBEDDED.KREN_CNTNT_TYP_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1142', '2.0.4-SNP', '3:3505cb41f0a205efdf5685265041e393', 1142);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1143::Jeff Caddel (generated)::(Checksum: 3:d796bc5e7d3ab4f79d061aad54518ee0)
CREATE SEQUENCE KSEMBEDDED.KREN_MSG_DELIV_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1143', '2.0.4-SNP', '3:d796bc5e7d3ab4f79d061aad54518ee0', 1143);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1144::Jeff Caddel (generated)::(Checksum: 3:c9519baa5645fb5af6c5af1696891443)
CREATE SEQUENCE KSEMBEDDED.KREN_MSG_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1144', '2.0.4-SNP', '3:c9519baa5645fb5af6c5af1696891443', 1144);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1145::Jeff Caddel (generated)::(Checksum: 3:16520d2c33433547688e750a38478ec3)
CREATE SEQUENCE KSEMBEDDED.KREN_NTFCTN_MSG_DELIV_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1145', '2.0.4-SNP', '3:16520d2c33433547688e750a38478ec3', 1145);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1146::Jeff Caddel (generated)::(Checksum: 3:7f558ef17a0b82e4cdcb73a624fb6cca)
CREATE SEQUENCE KSEMBEDDED.KREN_NTFCTN_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1146', '2.0.4-SNP', '3:7f558ef17a0b82e4cdcb73a624fb6cca', 1146);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1147::Jeff Caddel (generated)::(Checksum: 3:4171acf1eca5e181133b945bca458d6b)
CREATE SEQUENCE KSEMBEDDED.KREN_PRIO_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1147', '2.0.4-SNP', '3:4171acf1eca5e181133b945bca458d6b', 1147);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1148::Jeff Caddel (generated)::(Checksum: 3:62e9906f29829d0ebc971fa4d495ed5c)
CREATE SEQUENCE KSEMBEDDED.KREN_PRODCR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1148', '2.0.4-SNP', '3:62e9906f29829d0ebc971fa4d495ed5c', 1148);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1149::Jeff Caddel (generated)::(Checksum: 3:0697b5c06121cb45583c0b9e75534cff)
CREATE SEQUENCE KSEMBEDDED.KREN_RECIP_DELIV_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1149', '2.0.4-SNP', '3:0697b5c06121cb45583c0b9e75534cff', 1149);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1150::Jeff Caddel (generated)::(Checksum: 3:0d08d09b02d17180dd9c7171f7580322)
CREATE SEQUENCE KSEMBEDDED.KREN_RECIP_LIST_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1150', '2.0.4-SNP', '3:0d08d09b02d17180dd9c7171f7580322', 1150);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1151::Jeff Caddel (generated)::(Checksum: 3:e74a950b7e89944f002781116aae515b)
CREATE SEQUENCE KSEMBEDDED.KREN_RECIP_PREF_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1151', '2.0.4-SNP', '3:e74a950b7e89944f002781116aae515b', 1151);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1152::Jeff Caddel (generated)::(Checksum: 3:4ec8ea4e16bd7477e70f1a87ac4f5b05)
CREATE SEQUENCE KSEMBEDDED.KREN_RECIP_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1152', '2.0.4-SNP', '3:4ec8ea4e16bd7477e70f1a87ac4f5b05', 1152);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1153::Jeff Caddel (generated)::(Checksum: 3:9847900f466f2b82754bc3f6ad22f781)
CREATE SEQUENCE KSEMBEDDED.KREN_RVWER_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1153', '2.0.4-SNP', '3:9847900f466f2b82754bc3f6ad22f781', 1153);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1154::Jeff Caddel (generated)::(Checksum: 3:3158584c9927ee095b7a6388b978a205)
CREATE SEQUENCE KSEMBEDDED.KREN_SNDR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1154', '2.0.4-SNP', '3:3158584c9927ee095b7a6388b978a205', 1154);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1155::Jeff Caddel (generated)::(Checksum: 3:7a14d54ef5cbed700753e2c52c553ad6)
CREATE SEQUENCE KSEMBEDDED.KREW_ACTN_ITM_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1155', '2.0.4-SNP', '3:7a14d54ef5cbed700753e2c52c553ad6', 1155);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1156::Jeff Caddel (generated)::(Checksum: 3:df48b2039cb5a76ecb3b437ad22ece05)
CREATE SEQUENCE KSEMBEDDED.KREW_ACTN_LIST_OPTN_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1156', '2.0.4-SNP', '3:df48b2039cb5a76ecb3b437ad22ece05', 1156);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1157::Jeff Caddel (generated)::(Checksum: 3:fc77c707109e8243a13c2a313b06a230)
CREATE SEQUENCE KSEMBEDDED.KREW_ACTN_RQST_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1157', '2.0.4-SNP', '3:fc77c707109e8243a13c2a313b06a230', 1157);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1158::Jeff Caddel (generated)::(Checksum: 3:63511c437e5a877da5fdcae0927da495)
CREATE SEQUENCE KSEMBEDDED.KREW_ACTN_TKN_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1158', '2.0.4-SNP', '3:63511c437e5a877da5fdcae0927da495', 1158);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1159::Jeff Caddel (generated)::(Checksum: 3:830e8b6ae497dcb6f2ad06524590c718)
CREATE SEQUENCE KSEMBEDDED.KREW_DOC_HDR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1159', '2.0.4-SNP', '3:830e8b6ae497dcb6f2ad06524590c718', 1159);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1160::Jeff Caddel (generated)::(Checksum: 3:b6cc32e92b67c87e936044319fdb6aa6)
CREATE SEQUENCE KSEMBEDDED.KREW_DOC_LNK_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1160', '2.0.4-SNP', '3:b6cc32e92b67c87e936044319fdb6aa6', 1160);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1161::Jeff Caddel (generated)::(Checksum: 3:0d84fa0f3cd93ccd36be446d0f5b5aa0)
CREATE SEQUENCE KSEMBEDDED.KREW_DOC_NTE_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1161', '2.0.4-SNP', '3:0d84fa0f3cd93ccd36be446d0f5b5aa0', 1161);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1162::Jeff Caddel (generated)::(Checksum: 3:08b6c478e5d13f0633d45434cd4573d4)
CREATE SEQUENCE KSEMBEDDED.KREW_DOC_TYP_ATTR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1162', '2.0.4-SNP', '3:08b6c478e5d13f0633d45434cd4573d4', 1162);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1163::Jeff Caddel (generated)::(Checksum: 3:58634339448bfd365c4ee1c9c36f6034)
CREATE SEQUENCE KSEMBEDDED.KREW_EDL_FLD_DMP_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1163', '2.0.4-SNP', '3:58634339448bfd365c4ee1c9c36f6034', 1163);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1164::Jeff Caddel (generated)::(Checksum: 3:5001772431e20e60b309cf5df2b83cc8)
CREATE SEQUENCE KSEMBEDDED.KREW_EDL_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1164', '2.0.4-SNP', '3:5001772431e20e60b309cf5df2b83cc8', 1164);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1165::Jeff Caddel (generated)::(Checksum: 3:005de86b80c6b74c21d0e2fa83ede9c6)
CREATE SEQUENCE KSEMBEDDED.KREW_HLP_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1165', '2.0.4-SNP', '3:005de86b80c6b74c21d0e2fa83ede9c6', 1165);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1166::Jeff Caddel (generated)::(Checksum: 3:e401f6fb0dbca72731b5849cb9b16a3c)
CREATE SEQUENCE KSEMBEDDED.KREW_OUT_BOX_ITM_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1166', '2.0.4-SNP', '3:e401f6fb0dbca72731b5849cb9b16a3c', 1166);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1167::Jeff Caddel (generated)::(Checksum: 3:ebfdd2ebd52eb61ee538e0768fac34ff)
CREATE SEQUENCE KSEMBEDDED.KREW_RIA_DOCTYPE_MAP_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1167', '2.0.4-SNP', '3:ebfdd2ebd52eb61ee538e0768fac34ff', 1167);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1168::Jeff Caddel (generated)::(Checksum: 3:8934ca89f5c7bae05e9bc8238bad9fd2)
CREATE SEQUENCE KSEMBEDDED.KREW_RSP_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1168', '2.0.4-SNP', '3:8934ca89f5c7bae05e9bc8238bad9fd2', 1168);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1169::Jeff Caddel (generated)::(Checksum: 3:885c2fa692c155278bd4fbd907415251)
CREATE SEQUENCE KSEMBEDDED.KREW_RTE_NODE_CFG_PARM_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1169', '2.0.4-SNP', '3:885c2fa692c155278bd4fbd907415251', 1169);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1170::Jeff Caddel (generated)::(Checksum: 3:3e18202c0c2a347f977880308661ca79)
CREATE SEQUENCE KSEMBEDDED.KREW_RTE_NODE_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1170', '2.0.4-SNP', '3:3e18202c0c2a347f977880308661ca79', 1170);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1171::Jeff Caddel (generated)::(Checksum: 3:d4cd246c1dccd1ba9ab359bcf2fbc919)
CREATE SEQUENCE KSEMBEDDED.KREW_RTE_TMPL_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1171', '2.0.4-SNP', '3:d4cd246c1dccd1ba9ab359bcf2fbc919', 1171);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1172::Jeff Caddel (generated)::(Checksum: 3:94719a8972dd5c8635a6b9f93e84817b)
CREATE SEQUENCE KSEMBEDDED.KREW_RULE_EXPR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1172', '2.0.4-SNP', '3:94719a8972dd5c8635a6b9f93e84817b', 1172);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1173::Jeff Caddel (generated)::(Checksum: 3:15f611f457a397a76d7f4ea0316f2eba)
CREATE SEQUENCE KSEMBEDDED.KREW_RULE_TMPL_OPTN_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1173', '2.0.4-SNP', '3:15f611f457a397a76d7f4ea0316f2eba', 1173);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1174::Jeff Caddel (generated)::(Checksum: 3:6c8e193b98f5fc5c6cc5369dd8314701)
CREATE SEQUENCE KSEMBEDDED.KREW_SRCH_ATTR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1174', '2.0.4-SNP', '3:6c8e193b98f5fc5c6cc5369dd8314701', 1174);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1175::Jeff Caddel (generated)::(Checksum: 3:158ad7538b9d0dbdf3ed85813bc3136e)
CREATE SEQUENCE KSEMBEDDED.KREW_USR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1175', '2.0.4-SNP', '3:158ad7538b9d0dbdf3ed85813bc3136e', 1175);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1176::Jeff Caddel (generated)::(Checksum: 3:c9ff8e324b1dfe776d0501ad59730434)
CREATE SEQUENCE KSEMBEDDED.KRIM_ATTR_DATA_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1176', '2.0.4-SNP', '3:c9ff8e324b1dfe776d0501ad59730434', 1176);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1177::Jeff Caddel (generated)::(Checksum: 3:9b6b2c0bb3cf4937d33d60987360720a)
CREATE SEQUENCE KSEMBEDDED.KRIM_ATTR_DEFN_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1177', '2.0.4-SNP', '3:9b6b2c0bb3cf4937d33d60987360720a', 1177);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1178::Jeff Caddel (generated)::(Checksum: 3:93e7c76148ad11f07af328abeaf82c33)
CREATE SEQUENCE KSEMBEDDED.KRIM_DLGN_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1178', '2.0.4-SNP', '3:93e7c76148ad11f07af328abeaf82c33', 1178);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1179::Jeff Caddel (generated)::(Checksum: 3:bdd0181fccc8eaf10e96a1c62a90a1c6)
CREATE SEQUENCE KSEMBEDDED.KRIM_DLGN_MBR_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1179', '2.0.4-SNP', '3:bdd0181fccc8eaf10e96a1c62a90a1c6', 1179);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1180::Jeff Caddel (generated)::(Checksum: 3:03714097398e65635111fa02b6fef7c3)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_ADDR_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1180', '2.0.4-SNP', '3:03714097398e65635111fa02b6fef7c3', 1180);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1181::Jeff Caddel (generated)::(Checksum: 3:16e873f5649903c5c113cfd0d47cab86)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_AFLTN_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1181', '2.0.4-SNP', '3:16e873f5649903c5c113cfd0d47cab86', 1181);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1182::Jeff Caddel (generated)::(Checksum: 3:fd73aa0149504dfcb0936ebc9be50648)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_CTZNSHP_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1182', '2.0.4-SNP', '3:fd73aa0149504dfcb0936ebc9be50648', 1182);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1183::Jeff Caddel (generated)::(Checksum: 3:a54303718d32a28999e81a1f7624a43b)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_EMAIL_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1183', '2.0.4-SNP', '3:a54303718d32a28999e81a1f7624a43b', 1183);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1184::Jeff Caddel (generated)::(Checksum: 3:cf08ba8d979e39de2193c19a9cdae7ca)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_EMP_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1184', '2.0.4-SNP', '3:cf08ba8d979e39de2193c19a9cdae7ca', 1184);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1185::Jeff Caddel (generated)::(Checksum: 3:a3990b7a6fcdf636b0f7e5606b1c4566)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_ETHNIC_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1185', '2.0.4-SNP', '3:a3990b7a6fcdf636b0f7e5606b1c4566', 1185);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1186::Jeff Caddel (generated)::(Checksum: 3:a19d5f325236c94b82410314be251772)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_EXT_ID_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1186', '2.0.4-SNP', '3:a19d5f325236c94b82410314be251772', 1186);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1187::Jeff Caddel (generated)::(Checksum: 3:0339fd786de407090a1655570180dfa9)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1187', '2.0.4-SNP', '3:0339fd786de407090a1655570180dfa9', 1187);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1188::Jeff Caddel (generated)::(Checksum: 3:06641fb429c879a8dc96ce28f676c72d)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_NM_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1188', '2.0.4-SNP', '3:06641fb429c879a8dc96ce28f676c72d', 1188);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1189::Jeff Caddel (generated)::(Checksum: 3:d46d0e58a75b6f997c201daa86d310ae)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_PHONE_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1189', '2.0.4-SNP', '3:d46d0e58a75b6f997c201daa86d310ae', 1189);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1190::Jeff Caddel (generated)::(Checksum: 3:df8f3e00f142e9b3d81ddb16727cd921)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_RESIDENCY_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1190', '2.0.4-SNP', '3:df8f3e00f142e9b3d81ddb16727cd921', 1190);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1191::Jeff Caddel (generated)::(Checksum: 3:eaf6737a1232953042484e9481c46558)
CREATE SEQUENCE KSEMBEDDED.KRIM_ENTITY_VISA_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1191', '2.0.4-SNP', '3:eaf6737a1232953042484e9481c46558', 1191);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1192::Jeff Caddel (generated)::(Checksum: 3:df9c8cb40d7a42786f4a3a40984f7d3b)
CREATE SEQUENCE KSEMBEDDED.KRIM_GRP_ATTR_DATA_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1192', '2.0.4-SNP', '3:df9c8cb40d7a42786f4a3a40984f7d3b', 1192);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1193::Jeff Caddel (generated)::(Checksum: 3:6c2af45a0c95f339421f9a0bcc1a43a3)
CREATE SEQUENCE KSEMBEDDED.KRIM_GRP_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1193', '2.0.4-SNP', '3:6c2af45a0c95f339421f9a0bcc1a43a3', 1193);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1194::Jeff Caddel (generated)::(Checksum: 3:90227d3bdf895b85ddb0532a0c1c0817)
CREATE SEQUENCE KSEMBEDDED.KRIM_GRP_MBR_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1194', '2.0.4-SNP', '3:90227d3bdf895b85ddb0532a0c1c0817', 1194);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1195::Jeff Caddel (generated)::(Checksum: 3:97d032ac68e09d91a1e6afb1208ee7f8)
CREATE SEQUENCE KSEMBEDDED.KRIM_PERM_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1195', '2.0.4-SNP', '3:97d032ac68e09d91a1e6afb1208ee7f8', 1195);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1196::Jeff Caddel (generated)::(Checksum: 3:458354879c6cb72e1b8de8233a788fd9)
CREATE SEQUENCE KSEMBEDDED.KRIM_PERM_RQRD_ATTR_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1196', '2.0.4-SNP', '3:458354879c6cb72e1b8de8233a788fd9', 1196);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1197::Jeff Caddel (generated)::(Checksum: 3:73a7ab38010c7c463ad295ab32ed341e)
CREATE SEQUENCE KSEMBEDDED.KRIM_PERM_TMPL_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1197', '2.0.4-SNP', '3:73a7ab38010c7c463ad295ab32ed341e', 1197);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1198::Jeff Caddel (generated)::(Checksum: 3:5846ddde8378da31e7e5cad69d70b4f8)
CREATE SEQUENCE KSEMBEDDED.KRIM_PRNCPL_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1198', '2.0.4-SNP', '3:5846ddde8378da31e7e5cad69d70b4f8', 1198);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1199::Jeff Caddel (generated)::(Checksum: 3:d816bdea03af4e0fd364f4cc81d616c0)
CREATE SEQUENCE KSEMBEDDED.KRIM_ROLE_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1199', '2.0.4-SNP', '3:d816bdea03af4e0fd364f4cc81d616c0', 1199);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1200::Jeff Caddel (generated)::(Checksum: 3:91ffe1c925f6baefc37c9c06ef342014)
CREATE SEQUENCE KSEMBEDDED.KRIM_ROLE_MBR_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1200', '2.0.4-SNP', '3:91ffe1c925f6baefc37c9c06ef342014', 1200);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1201::Jeff Caddel (generated)::(Checksum: 3:ff5d5fe81e493226ae9b24ff50d30651)
CREATE SEQUENCE KSEMBEDDED.KRIM_ROLE_PERM_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1201', '2.0.4-SNP', '3:ff5d5fe81e493226ae9b24ff50d30651', 1201);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1202::Jeff Caddel (generated)::(Checksum: 3:0f11b7a5a85e35ccb54316864c064a10)
CREATE SEQUENCE KSEMBEDDED.KRIM_ROLE_RSP_ACTN_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1202', '2.0.4-SNP', '3:0f11b7a5a85e35ccb54316864c064a10', 1202);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1203::Jeff Caddel (generated)::(Checksum: 3:c1d3ad5ef1c70c37c48b9e78f3f697d0)
CREATE SEQUENCE KSEMBEDDED.KRIM_ROLE_RSP_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1203', '2.0.4-SNP', '3:c1d3ad5ef1c70c37c48b9e78f3f697d0', 1203);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1204::Jeff Caddel (generated)::(Checksum: 3:41de88d3b97ea45978af5ef9e0320740)
CREATE SEQUENCE KSEMBEDDED.KRIM_RSP_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1204', '2.0.4-SNP', '3:41de88d3b97ea45978af5ef9e0320740', 1204);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1205::Jeff Caddel (generated)::(Checksum: 3:d19e0ccef7ddc1792456ec01f6f63f82)
CREATE SEQUENCE KSEMBEDDED.KRIM_RSP_RQRD_ATTR_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1205', '2.0.4-SNP', '3:d19e0ccef7ddc1792456ec01f6f63f82', 1205);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1206::Jeff Caddel (generated)::(Checksum: 3:f1b86f37ce2f44f68f56b67d26a8602c)
CREATE SEQUENCE KSEMBEDDED.KRIM_RSP_TMPL_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1206', '2.0.4-SNP', '3:f1b86f37ce2f44f68f56b67d26a8602c', 1206);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1207::Jeff Caddel (generated)::(Checksum: 3:8abe0785e3223fab3b952a32061ac738)
CREATE SEQUENCE KSEMBEDDED.KRIM_TYP_ATTR_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1207', '2.0.4-SNP', '3:8abe0785e3223fab3b952a32061ac738', 1207);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1208::Jeff Caddel (generated)::(Checksum: 3:dcca998df276bd3b0c7e3b16e96b87c5)
CREATE SEQUENCE KSEMBEDDED.KRIM_TYP_ID_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1208', '2.0.4-SNP', '3:dcca998df276bd3b0c7e3b16e96b87c5', 1208);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1209::Jeff Caddel (generated)::(Checksum: 3:58b5a3d30ad4d47b1e1395add6754df1)
CREATE SEQUENCE KSEMBEDDED.KRMS_ACTN_ATTR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1209', '2.0.4-SNP', '3:58b5a3d30ad4d47b1e1395add6754df1', 1209);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1210::Jeff Caddel (generated)::(Checksum: 3:9a476cf66183a2d807f404ac3289a7fa)
CREATE SEQUENCE KSEMBEDDED.KRMS_ACTN_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1210', '2.0.4-SNP', '3:9a476cf66183a2d807f404ac3289a7fa', 1210);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1211::Jeff Caddel (generated)::(Checksum: 3:6b37bde5776db7a00dc984e96a087dc8)
CREATE SEQUENCE KSEMBEDDED.KRMS_AGENDA_ATTR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1211', '2.0.4-SNP', '3:6b37bde5776db7a00dc984e96a087dc8', 1211);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1212::Jeff Caddel (generated)::(Checksum: 3:c178a50edaa5148b853ce4a7a6bafaa5)
CREATE SEQUENCE KSEMBEDDED.KRMS_AGENDA_ITM_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1212', '2.0.4-SNP', '3:c178a50edaa5148b853ce4a7a6bafaa5', 1212);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1213::Jeff Caddel (generated)::(Checksum: 3:58bb1304089656c86001be2adfacd86c)
CREATE SEQUENCE KSEMBEDDED.KRMS_AGENDA_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1213', '2.0.4-SNP', '3:58bb1304089656c86001be2adfacd86c', 1213);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1214::Jeff Caddel (generated)::(Checksum: 3:03f05d484a36f86f377748674d275810)
CREATE SEQUENCE KSEMBEDDED.KRMS_ATTR_DEFN_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1214', '2.0.4-SNP', '3:03f05d484a36f86f377748674d275810', 1214);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1215::Jeff Caddel (generated)::(Checksum: 3:c29eec09c66b3c0240a9dc01413558cf)
CREATE SEQUENCE KSEMBEDDED.KRMS_CMPND_PROP_PROPS_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1215', '2.0.4-SNP', '3:c29eec09c66b3c0240a9dc01413558cf', 1215);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1216::Jeff Caddel (generated)::(Checksum: 3:b1abaf3bfad3157dbdc6a4d9a8facd5e)
CREATE SEQUENCE KSEMBEDDED.KRMS_CNTXT_ATTR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1216', '2.0.4-SNP', '3:b1abaf3bfad3157dbdc6a4d9a8facd5e', 1216);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1217::Jeff Caddel (generated)::(Checksum: 3:61ae3e6e52f19c5e420cfc71e7b3ef21)
CREATE SEQUENCE KSEMBEDDED.KRMS_CNTXT_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1217', '2.0.4-SNP', '3:61ae3e6e52f19c5e420cfc71e7b3ef21', 1217);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1218::Jeff Caddel (generated)::(Checksum: 3:4229e8c48f7a5735be18f249be9bbabc)
CREATE SEQUENCE KSEMBEDDED.KRMS_CNTXT_TERM_SPEC_PREREQ_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1218', '2.0.4-SNP', '3:4229e8c48f7a5735be18f249be9bbabc', 1218);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1219::Jeff Caddel (generated)::(Checksum: 3:455edc39567321731963db92fdb0e6a1)
CREATE SEQUENCE KSEMBEDDED.KRMS_CNTXT_VLD_ACTN_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1219', '2.0.4-SNP', '3:455edc39567321731963db92fdb0e6a1', 1219);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1220::Jeff Caddel (generated)::(Checksum: 3:f2ddf731c7eabf0937b88739c366b62b)
CREATE SEQUENCE KSEMBEDDED.KRMS_CNTXT_VLD_EVENT_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1220', '2.0.4-SNP', '3:f2ddf731c7eabf0937b88739c366b62b', 1220);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1221::Jeff Caddel (generated)::(Checksum: 3:2389315fd12e1701b7d851b3caad7e17)
CREATE SEQUENCE KSEMBEDDED.KRMS_CNTXT_VLD_FUNC_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1221', '2.0.4-SNP', '3:2389315fd12e1701b7d851b3caad7e17', 1221);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1222::Jeff Caddel (generated)::(Checksum: 3:c08fd186264dee34a0e5ca62f3183268)
CREATE SEQUENCE KSEMBEDDED.KRMS_CNTXT_VLD_RULE_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1222', '2.0.4-SNP', '3:c08fd186264dee34a0e5ca62f3183268', 1222);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1223::Jeff Caddel (generated)::(Checksum: 3:82b4889a201dc625b199df091a5ecd5d)
CREATE SEQUENCE KSEMBEDDED.KRMS_FUNC_PARM_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1223', '2.0.4-SNP', '3:82b4889a201dc625b199df091a5ecd5d', 1223);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1224::Jeff Caddel (generated)::(Checksum: 3:42095624842ce2b2ce331de2a20e2d87)
CREATE SEQUENCE KSEMBEDDED.KRMS_FUNC_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1224', '2.0.4-SNP', '3:42095624842ce2b2ce331de2a20e2d87', 1224);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1225::Jeff Caddel (generated)::(Checksum: 3:36eb7fa121146c55a72d9d2c5ab4ea67)
CREATE SEQUENCE KSEMBEDDED.KRMS_PROP_PARM_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1225', '2.0.4-SNP', '3:36eb7fa121146c55a72d9d2c5ab4ea67', 1225);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1226::Jeff Caddel (generated)::(Checksum: 3:874b04ce71b2f597563639eef92a0e37)
CREATE SEQUENCE KSEMBEDDED.KRMS_PROP_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1226', '2.0.4-SNP', '3:874b04ce71b2f597563639eef92a0e37', 1226);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1227::Jeff Caddel (generated)::(Checksum: 3:a93bc2b3915869ea519d34a29375b28e)
CREATE SEQUENCE KSEMBEDDED.KRMS_RULE_ATTR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1227', '2.0.4-SNP', '3:a93bc2b3915869ea519d34a29375b28e', 1227);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1228::Jeff Caddel (generated)::(Checksum: 3:d6ef11870ee2887f5d4df54985000fb3)
CREATE SEQUENCE KSEMBEDDED.KRMS_RULE_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1228', '2.0.4-SNP', '3:d6ef11870ee2887f5d4df54985000fb3', 1228);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1229::Jeff Caddel (generated)::(Checksum: 3:c050d14fb2f67ae33c7f2304418b4657)
CREATE SEQUENCE KSEMBEDDED.KRMS_TERM_PARM_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1229', '2.0.4-SNP', '3:c050d14fb2f67ae33c7f2304418b4657', 1229);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1230::Jeff Caddel (generated)::(Checksum: 3:14a5e95d2e4cf3d4b8653e83a7402bd0)
CREATE SEQUENCE KSEMBEDDED.KRMS_TERM_RSLVR_ATTR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1230', '2.0.4-SNP', '3:14a5e95d2e4cf3d4b8653e83a7402bd0', 1230);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1231::Jeff Caddel (generated)::(Checksum: 3:3eadff9e7e217d97a8a2cd9079efb4bb)
CREATE SEQUENCE KSEMBEDDED.KRMS_TERM_RSLVR_INPUT_SPEC_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1231', '2.0.4-SNP', '3:3eadff9e7e217d97a8a2cd9079efb4bb', 1231);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1232::Jeff Caddel (generated)::(Checksum: 3:9889abcd9a085a8844613f7d0bcee321)
CREATE SEQUENCE KSEMBEDDED.KRMS_TERM_RSLVR_PARM_SPEC_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1232', '2.0.4-SNP', '3:9889abcd9a085a8844613f7d0bcee321', 1232);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1233::Jeff Caddel (generated)::(Checksum: 3:150c3bb07ffcad6fd33231652312fc7a)
CREATE SEQUENCE KSEMBEDDED.KRMS_TERM_RSLVR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1233', '2.0.4-SNP', '3:150c3bb07ffcad6fd33231652312fc7a', 1233);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1234::Jeff Caddel (generated)::(Checksum: 3:0befb512f02f799c5be2a6a22cb84755)
CREATE SEQUENCE KSEMBEDDED.KRMS_TERM_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1234', '2.0.4-SNP', '3:0befb512f02f799c5be2a6a22cb84755', 1234);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1235::Jeff Caddel (generated)::(Checksum: 3:8d0b70f84f64e8b9ced416dca088513b)
CREATE SEQUENCE KSEMBEDDED.KRMS_TERM_SPEC_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1235', '2.0.4-SNP', '3:8d0b70f84f64e8b9ced416dca088513b', 1235);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1236::Jeff Caddel (generated)::(Checksum: 3:339d2c0a29bd830b8abc19199c6515d8)
CREATE SEQUENCE KSEMBEDDED.KRMS_TYP_ATTR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1236', '2.0.4-SNP', '3:339d2c0a29bd830b8abc19199c6515d8', 1236);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1237::Jeff Caddel (generated)::(Checksum: 3:a58d0d2c6438f628392fbed1b3e5899a)
CREATE SEQUENCE KSEMBEDDED.KRMS_TYP_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1237', '2.0.4-SNP', '3:a58d0d2c6438f628392fbed1b3e5899a', 1237);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1238::Jeff Caddel (generated)::(Checksum: 3:b188ae0edc0e8ee9c0f9d3718e25c4b6)
CREATE SEQUENCE KSEMBEDDED.KRNS_DOC_TYP_ATTR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1238', '2.0.4-SNP', '3:b188ae0edc0e8ee9c0f9d3718e25c4b6', 1238);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1239::Jeff Caddel (generated)::(Checksum: 3:5c34570bc08ec5deb8cac12d7e74913c)
CREATE SEQUENCE KSEMBEDDED.KRNS_LOCK_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1239', '2.0.4-SNP', '3:5c34570bc08ec5deb8cac12d7e74913c', 1239);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1240::Jeff Caddel (generated)::(Checksum: 3:2b4b173eea4cc50acd7bfae2bc9dd4f2)
CREATE SEQUENCE KSEMBEDDED.KRNS_LOOKUP_RSLT_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1240', '2.0.4-SNP', '3:2b4b173eea4cc50acd7bfae2bc9dd4f2', 1240);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1241::Jeff Caddel (generated)::(Checksum: 3:505c89e528b5cef0c91791445747d73e)
CREATE SEQUENCE KSEMBEDDED.KRNS_MAINT_LOCK_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1241', '2.0.4-SNP', '3:505c89e528b5cef0c91791445747d73e', 1241);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1242::Jeff Caddel (generated)::(Checksum: 3:4ab56ec974d601b2777c77fc080eecb0)
CREATE SEQUENCE KSEMBEDDED.KRNS_NTE_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1242', '2.0.4-SNP', '3:4ab56ec974d601b2777c77fc080eecb0', 1242);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1243::Jeff Caddel (generated)::(Checksum: 3:a763778e9c86c705b9ffb087044a7439)
CREATE SEQUENCE KSEMBEDDED.KRSB_BAM_PARM_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1243', '2.0.4-SNP', '3:a763778e9c86c705b9ffb087044a7439', 1243);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1244::Jeff Caddel (generated)::(Checksum: 3:8724dcbf03cd90a3266607be81b80c24)
CREATE SEQUENCE KSEMBEDDED.KRSB_BAM_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1244', '2.0.4-SNP', '3:8724dcbf03cd90a3266607be81b80c24', 1244);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1245::Jeff Caddel (generated)::(Checksum: 3:21ada2d6781f5beacaffe6e07b717f26)
CREATE SEQUENCE KSEMBEDDED.KRSB_MSG_QUE_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1245', '2.0.4-SNP', '3:21ada2d6781f5beacaffe6e07b717f26', 1245);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1246::Jeff Caddel (generated)::(Checksum: 3:deace91edfab86454952785bbc35a5d2)
CREATE SEQUENCE KSEMBEDDED.KRSB_SVC_DEF_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1246', '2.0.4-SNP', '3:deace91edfab86454952785bbc35a5d2', 1246);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1247::Jeff Caddel (generated)::(Checksum: 3:ce0d3116bec6bc7dd7f7df166e8d41ff)
CREATE SEQUENCE KSEMBEDDED.KRSB_SVC_DSCRPTR_S;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create Sequence', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1247', '2.0.4-SNP', '3:ce0d3116bec6bc7dd7f7df166e8d41ff', 1247);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1248::Jeff Caddel (generated)::(Checksum: 3:0756e54b60482207749ec73a0a20d658)
CREATE VIEW KSEMBEDDED.KRIM_GRP_MBR_V AS SELECT g.NMSPC_CD
, g.grp_nm
, g.GRP_ID
, p.PRNCPL_NM
, p.PRNCPL_ID
, mg.GRP_NM AS mbr_grp_nm
, mg.GRP_ID AS mbr_grp_id
FROM KRIM_GRP_MBR_T gm
LEFT JOIN krim_grp_t g
ON g.GRP_ID = gm.GRP_ID
LEFT OUTER JOIN krim_grp_t mg
ON mg.GRP_ID = gm.MBR_ID
AND gm.MBR_TYP_CD = 'G'
LEFT OUTER JOIN krim_prncpl_t p
ON p.PRNCPL_ID = gm.MBR_ID
AND gm.MBR_TYP_CD = 'P'
LEFT OUTER JOIN krim_entity_nm_t en
ON en.ENTITY_ID = p.ENTITY_ID
AND en.DFLT_IND = 'Y'
AND en.ACTV_IND = 'Y'
ORDER BY nmspc_cd, grp_nm, prncpl_nm;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1248', '2.0.4-SNP', '3:0756e54b60482207749ec73a0a20d658', 1248);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1249::Jeff Caddel (generated)::(Checksum: 3:0dc3649488e76eba887878635ef62afc)
CREATE VIEW KSEMBEDDED.KRIM_GRP_V AS SELECT g.NMSPC_CD
, g.grp_nm
, g.GRP_ID
, t.NM AS grp_typ_nm
, a.NM AS attr_nm
, d.ATTR_VAL AS attr_val
FROM krim_grp_t g
LEFT OUTER JOIN KRIM_GRP_ATTR_DATA_T d
ON d.grp_id = g.GRP_ID
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON a.KIM_ATTR_DEFN_ID = d.KIM_ATTR_DEFN_ID
LEFT OUTER JOIN KRIM_TYP_T t
ON g.KIM_TYP_ID = t.KIM_TYP_ID;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1249', '2.0.4-SNP', '3:0dc3649488e76eba887878635ef62afc', 1249);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1250::Jeff Caddel (generated)::(Checksum: 3:1cda7558964440121f2543ef2521b4e5)
CREATE VIEW KSEMBEDDED.KRIM_PERM_ATTR_V AS SELECT
t.nmspc_cd AS tmpl_nmspc_cd
, t.NM AS tmpl_nm
, t.PERM_TMPL_ID
, p.nmspc_cd AS perm_nmspc_cd
, p.NM AS perm_nm
, p.PERM_ID
, a.NM AS attr_nm
, ad.ATTR_VAL AS attr_val
FROM KRIM_PERM_T p
LEFT JOIN KRIM_PERM_TMPL_T t
ON p.PERM_TMPL_ID = t.PERM_TMPL_ID
LEFT OUTER JOIN KRIM_PERM_ATTR_DATA_T ad
ON p.PERM_ID = ad.perm_id
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON ad.KIM_ATTR_DEFN_ID = a.KIM_ATTR_DEFN_ID
ORDER BY tmpl_nmspc_cd, tmpl_nm, perm_nmspc_cd, perm_id, attr_nm;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1250', '2.0.4-SNP', '3:1cda7558964440121f2543ef2521b4e5', 1250);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1251::Jeff Caddel (generated)::(Checksum: 3:4c93f1526b4c27d045765a3f6b081fbe)
CREATE VIEW KSEMBEDDED.KRIM_PERM_V AS SELECT
t.nmspc_cd AS tmpl_nmspc_cd
, t.NM AS tmpl_nm
, t.PERM_TMPL_ID
, p.nmspc_cd AS perm_nmspc_cd
, p.NM AS perm_nm
, p.PERM_ID
, typ.NM AS perm_typ_nm
, typ.SRVC_NM
FROM KRIM_PERM_T p
INNER JOIN KRIM_PERM_TMPL_T t
ON p.PERM_TMPL_ID = t.PERM_TMPL_ID
LEFT OUTER JOIN KRIM_TYP_T typ
ON t.KIM_TYP_ID = typ.KIM_TYP_ID;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1251', '2.0.4-SNP', '3:4c93f1526b4c27d045765a3f6b081fbe', 1251);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1252::Jeff Caddel (generated)::(Checksum: 3:fe65016dd2925244e0809e8505141169)
CREATE VIEW KSEMBEDDED.KRIM_PRNCPL_V AS SELECT
p.PRNCPL_ID
,p.PRNCPL_NM
,en.FIRST_NM
,en.LAST_NM
,ea.AFLTN_TYP_CD
,ea.CAMPUS_CD
,eei.EMP_STAT_CD
,eei.EMP_TYP_CD
FROM krim_prncpl_t p
LEFT OUTER JOIN krim_entity_emp_info_t eei
ON eei.ENTITY_ID = p.ENTITY_ID
LEFT OUTER JOIN krim_entity_afltn_t ea
ON ea.ENTITY_ID = p.ENTITY_ID
LEFT OUTER JOIN krim_entity_nm_t en
ON p.ENTITY_ID = en.ENTITY_ID
AND 'Y' = en.DFLT_IND;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1252', '2.0.4-SNP', '3:fe65016dd2925244e0809e8505141169', 1252);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1253::Jeff Caddel (generated)::(Checksum: 3:c0df69450a1a9dfdb01761b85b970320)
CREATE VIEW KSEMBEDDED.KRIM_ROLE_GRP_V AS SELECT r.NMSPC_CD
, r.ROLE_NM
, r.role_id
, g.NMSPC_CD AS grp_nmspc_cd
, g.GRP_NM
, rm.ROLE_MBR_ID
, a.NM AS attr_nm
, d.ATTR_VAL AS attr_val
FROM KRIM_ROLE_MBR_T rm
LEFT JOIN KRIM_ROLE_T r
ON r.ROLE_ID = rm.ROLE_ID
LEFT JOIN KRIM_GRP_T g
ON g.GRP_ID = rm.MBR_ID
LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T d
ON d.role_mbr_id = rm.ROLE_MBR_ID
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON a.KIM_ATTR_DEFN_ID = d.KIM_ATTR_DEFN_ID
WHERE rm.MBR_TYP_CD = 'G'
ORDER BY nmspc_cd, role_nm, grp_nmspc_cd, grp_nm, role_mbr_id, attr_nm;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1253', '2.0.4-SNP', '3:c0df69450a1a9dfdb01761b85b970320', 1253);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1254::Jeff Caddel (generated)::(Checksum: 3:9b3b6c4f5b49abd6fca327c0093603d2)
CREATE VIEW KSEMBEDDED.KRIM_ROLE_PERM_V AS SELECT r.NMSPC_CD
, r.ROLE_NM
, r.role_id
, pt.NMSPC_CD AS tmpl_nmspc_cd
, pt.NM AS tmpl_nm
, pt.PERM_TMPL_ID
, p.NMSPC_CD AS perm_nmpsc_cd
, p.NM AS perm_nm
, p.PERM_ID
, a.NM AS attr_nm
, ad.ATTR_VAL AS attr_val
FROM KRIM_PERM_T p
LEFT JOIN KRIM_PERM_TMPL_T pt
ON p.PERM_TMPL_ID = pt.PERM_TMPL_ID
LEFT OUTER JOIN KRIM_PERM_ATTR_DATA_T ad
ON p.PERM_ID = ad.perm_id
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON ad.KIM_ATTR_DEFN_ID = a.KIM_ATTR_DEFN_ID
LEFT OUTER JOIN KRIM_ROLE_PERM_T rp
ON rp.PERM_ID = p.PERM_ID
LEFT OUTER JOIN KRIM_ROLE_T r
ON rp.ROLE_ID = r.ROLE_ID
ORDER BY NMSPC_CD, role_nm, tmpl_nmspc_cd, tmpl_nm, perm_id, attr_nm;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1254', '2.0.4-SNP', '3:9b3b6c4f5b49abd6fca327c0093603d2', 1254);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1255::Jeff Caddel (generated)::(Checksum: 3:a641675a684952df7de34c4b7bc9c5e1)
CREATE VIEW KSEMBEDDED.KRIM_ROLE_PRNCPL_V AS SELECT r.NMSPC_CD
, r.ROLE_NM
, r.ROLE_ID
, p.PRNCPL_NM
, p.PRNCPL_ID
, en.FIRST_NM
, en.LAST_NM
, rm.ROLE_MBR_ID
, ad.NM AS attr_nm
, rmad.ATTR_VAL AS attr_val
FROM KRIM_ROLE_T r
LEFT OUTER JOIN KRIM_ROLE_MBR_T rm
ON r.ROLE_ID = rm.ROLE_ID
LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T rmad
ON rm.ROLE_MBR_ID = rmad.role_mbr_id
LEFT OUTER JOIN KRIM_ATTR_DEFN_T ad
ON rmad.KIM_ATTR_DEFN_ID = ad.KIM_ATTR_DEFN_ID
LEFT OUTER JOIN KRIM_PRNCPL_T p
ON rm.MBR_ID = p.PRNCPL_ID
AND rm.mbr_typ_cd = 'P'
LEFT OUTER JOIN KRIM_ENTITY_NM_T en
ON p.ENTITY_ID = en.ENTITY_ID
WHERE (en.DFLT_IND = 'Y')
ORDER BY nmspc_cd, role_nm, prncpl_nm, rm.ROLE_MBR_ID, attr_nm;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1255', '2.0.4-SNP', '3:a641675a684952df7de34c4b7bc9c5e1', 1255);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1256::Jeff Caddel (generated)::(Checksum: 3:5fb1d94588962d0d8c27bf312741dff8)
CREATE VIEW KSEMBEDDED.KRIM_ROLE_ROLE_V AS SELECT r.NMSPC_CD
, r.ROLE_NM
, r.role_id
, mr.NMSPC_CD AS mbr_role_nmspc_cd
, mr.role_NM AS mbr_role_nm
, mr.role_id AS mbr_role_id
, rm.role_mbr_id
, a.NM AS attr_nm
, d.ATTR_VAL AS attr_val
FROM KRIM_ROLE_MBR_T rm
LEFT JOIN KRIM_ROLE_T r
ON r.ROLE_ID = rm.ROLE_ID
LEFT JOIN KRIM_role_T mr
ON mr.role_ID = rm.MBR_ID
LEFT OUTER JOIN KRIM_ROLE_MBR_ATTR_DATA_T d
ON d.role_mbr_id = rm.ROLE_MBR_ID
LEFT OUTER JOIN KRIM_ATTR_DEFN_T a
ON a.KIM_ATTR_DEFN_ID = d.KIM_ATTR_DEFN_ID
WHERE rm.MBR_TYP_CD = 'R'
ORDER BY nmspc_cd, role_nm, mbr_role_nmspc_cd, mbr_role_nm, role_mbr_id, attr_nm;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1256', '2.0.4-SNP', '3:5fb1d94588962d0d8c27bf312741dff8', 1256);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1257::Jeff Caddel (generated)::(Checksum: 3:4eb65bdd3e531a4fd541f1154c97e77b)
CREATE VIEW KSEMBEDDED.KRIM_ROLE_V AS SELECT r.NMSPC_CD
, r.ROLE_NM
, r.ROLE_ID
, t.nm AS role_typ_nm
, t.SRVC_NM
, t.KIM_TYP_ID
FROM KRIM_ROLE_T r
, KRIM_TYP_T t
WHERE t.KIM_TYP_ID = r.KIM_TYP_ID
AND r.ACTV_IND = 'Y'
ORDER BY nmspc_cd
, role_nm;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1257', '2.0.4-SNP', '3:4eb65bdd3e531a4fd541f1154c97e77b', 1257);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1258::Jeff Caddel (generated)::(Checksum: 3:3f4fc8be8fa87a66f38d591aa8c20995)
CREATE VIEW KSEMBEDDED.KRIM_RSP_ATTR_V AS SELECT
krim_typ_t.NM AS responsibility_type_name
, KRIM_rsp_TMPL_T.NM AS rsp_TEMPLATE_NAME
, KRIM_rsp_T.nmspc_cd AS rsp_namespace_code
, KRIM_rsp_T.NM AS rsp_NAME
, krim_rsp_t.RSP_ID AS rsp_id
, KRIM_ATTR_DEFN_T.NM AS attribute_name
, KRIM_rsp_ATTR_DATA_T.ATTR_VAL AS attribute_value
FROM KRIM_rsp_T KRIM_rsp_T
INNER JOIN KRIM_rsp_ATTR_DATA_T KRIM_rsp_ATTR_DATA_T
ON KRIM_rsp_T.rsp_ID = KRIM_rsp_ATTR_DATA_T.rsp_id
INNER JOIN KRIM_ATTR_DEFN_T KRIM_ATTR_DEFN_T
ON KRIM_rsp_ATTR_DATA_T.KIM_ATTR_DEFN_ID = KRIM_ATTR_DEFN_T.KIM_ATTR_DEFN_ID
INNER JOIN KRIM_rsp_TMPL_T KRIM_rsp_TMPL_T
ON KRIM_rsp_T.rsp_TMPL_ID = KRIM_rsp_TMPL_T.rsp_TMPL_ID
INNER JOIN KRIM_TYP_T KRIM_TYP_T
ON KRIM_rsp_TMPL_T.KIM_TYP_ID = KRIM_TYP_T.KIM_TYP_ID
ORDER BY rsp_TEMPLATE_NAME, rsp_NAME, attribute_name;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1258', '2.0.4-SNP', '3:3f4fc8be8fa87a66f38d591aa8c20995', 1258);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1259::Jeff Caddel (generated)::(Checksum: 3:10a04e0beec220845bd09011f25d480d)
CREATE VIEW KSEMBEDDED.KRIM_RSP_ROLE_ACTN_V AS select
rsp.nmspc_cd as rsp_nmspc_cd
, rsp.rsp_id
, r.NMSPC_CD
, r.ROLE_NM
, rr.ROLE_ID
, rm.MBR_ID
, rm.MBR_TYP_CD
, rm.ROLE_MBR_ID
, actn.ACTN_TYP_CD
, actn.ACTN_PLCY_CD
, actn.FRC_ACTN
, actn.PRIORITY_NBR
from krim_rsp_t rsp
left join krim_rsp_tmpl_t rspt
on rsp.rsp_tmpl_id = rspt.rsp_tmpl_id
left outer join krim_role_rsp_t rr
on rr.rsp_id = rsp.rsp_id
left outer join KRIM_ROLE_MBR_T rm
ON rm.ROLE_ID = rr.ROLE_ID
left outer join KRIM_ROLE_RSP_ACTN_T actn
ON actn.ROLE_RSP_ID = rr.ROLE_RSP_ID
AND (actn.ROLE_MBR_ID = rm.ROLE_MBR_ID OR actn.ROLE_MBR_ID = '*')
left outer join krim_role_t r
on rr.role_id = r.role_id
order by rsp_nmspc_cd
, rsp_id
, role_id
, role_mbr_id;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1259', '2.0.4-SNP', '3:10a04e0beec220845bd09011f25d480d', 1259);

-- Changeset src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml::1312846209828-1260::Jeff Caddel (generated)::(Checksum: 3:f125980d2dff4064ac4d109a1cffe48c)
CREATE VIEW KSEMBEDDED.KRIM_RSP_ROLE_V AS select
rspt.nmspc_cd as rsp_tmpl_nmspc_cd
, rspt.nm as rsp_tmpl_nm
, rsp.nmspc_cd as rsp_nmspc_cd
, rsp.nm as rsp_nm
, rsp.rsp_id
, a.nm as attr_nm
, d.attr_val
, r.NMSPC_CD
, r.ROLE_NM
, rr.ROLE_ID
from krim_rsp_t rsp
left join krim_rsp_tmpl_t rspt
on rsp.rsp_tmpl_id = rspt.rsp_tmpl_id
left outer join krim_rsp_attr_data_t d
on rsp.rsp_id = d.rsp_id
left outer join krim_attr_defn_t a
on d.kim_attr_defn_id = a.kim_attr_defn_id
left outer join krim_role_rsp_t rr
on rr.rsp_id = rsp.rsp_id
left outer join krim_role_t r
on rr.role_id = r.role_id
order by rsp_tmpl_nmspc_cd, rsp_tmpl_nm, rsp_nmspc_cd, rsp_nm, rsp_id, attr_nm, attr_val;

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('Jeff Caddel (generated)', '', SYSTIMESTAMP, 'Create View', 'EXECUTED', 'src/test/resources/oracle/student-1.3-build-112/xml/constraints.xml', '1312846209828-1260', '2.0.4-SNP', '3:f125980d2dff4064ac4d109a1cffe48c', 1260);

-- Release Database Lock
-- Release Database Lock
