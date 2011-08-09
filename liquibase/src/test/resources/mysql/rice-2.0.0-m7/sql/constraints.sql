-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml
-- Ran at: 8/8/11 6:38 PM
-- Against: LIQUIBASE@localhost@jdbc:mysql://localhost/LIQUIBASE
-- Liquibase version: 2.0.4-SNAPSHOT
-- *********************************************************************

-- Lock Database
-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-1::Jeff Caddel (generated)::(Checksum: 3:01a28c4b9667abecd2ff02690a2d93ec)
ALTER TABLE `acct_dd_attr_doc` ADD PRIMARY KEY (`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-1', '2.0.4-SNP', '3:01a28c4b9667abecd2ff02690a2d93ec', 1);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-2::Jeff Caddel (generated)::(Checksum: 3:1644394ea66c57c6ea49c26dcfa1761f)
ALTER TABLE `krcr_cmpnt_t` ADD PRIMARY KEY (`NMSPC_CD`, `CMPNT_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-2', '2.0.4-SNP', '3:1644394ea66c57c6ea49c26dcfa1761f', 2);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-3::Jeff Caddel (generated)::(Checksum: 3:1a0e7716936b4afa85b9f58460e4aa80)
ALTER TABLE `krcr_nmspc_t` ADD PRIMARY KEY (`NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-3', '2.0.4-SNP', '3:1a0e7716936b4afa85b9f58460e4aa80', 3);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-4::Jeff Caddel (generated)::(Checksum: 3:d8c55237eb1e325684911e65c13b45d2)
ALTER TABLE `krcr_parm_t` ADD PRIMARY KEY (`NMSPC_CD`, `CMPNT_CD`, `PARM_NM`, `APPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-4', '2.0.4-SNP', '3:d8c55237eb1e325684911e65c13b45d2', 4);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-5::Jeff Caddel (generated)::(Checksum: 3:3869a82fe2a61f43184553c1bf87f9bb)
ALTER TABLE `krcr_parm_typ_t` ADD PRIMARY KEY (`PARM_TYP_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-5', '2.0.4-SNP', '3:3869a82fe2a61f43184553c1bf87f9bb', 5);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-6::Jeff Caddel (generated)::(Checksum: 3:a42b05c3e45ec362667024f0501fd5d2)
ALTER TABLE `kren_chnl_prodcr_t` ADD PRIMARY KEY (`CHNL_ID`, `PRODCR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-6', '2.0.4-SNP', '3:a42b05c3e45ec362667024f0501fd5d2', 6);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-7::Jeff Caddel (generated)::(Checksum: 3:0ab00544b07b0668a448358d02011d0e)
ALTER TABLE `kren_chnl_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-7', '2.0.4-SNP', '3:0ab00544b07b0668a448358d02011d0e', 7);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-8::Jeff Caddel (generated)::(Checksum: 3:3770ec1fdc9e99ac0ea086bf661d0f74)
ALTER TABLE `kren_chnl_subscrp_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-8', '2.0.4-SNP', '3:3770ec1fdc9e99ac0ea086bf661d0f74', 8);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-9::Jeff Caddel (generated)::(Checksum: 3:9e78c9a1a0d4f982310c584275e0a489)
ALTER TABLE `kren_chnl_subscrp_t` ADD PRIMARY KEY (`CHNL_SUBSCRP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-9', '2.0.4-SNP', '3:9e78c9a1a0d4f982310c584275e0a489', 9);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-10::Jeff Caddel (generated)::(Checksum: 3:0201bddb8f013aebab2357e1837bf234)
ALTER TABLE `kren_chnl_t` ADD PRIMARY KEY (`CHNL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-10', '2.0.4-SNP', '3:0201bddb8f013aebab2357e1837bf234', 10);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-11::Jeff Caddel (generated)::(Checksum: 3:c51e7fbf375612b884198970c28c4258)
ALTER TABLE `kren_cntnt_typ_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-11', '2.0.4-SNP', '3:c51e7fbf375612b884198970c28c4258', 11);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-12::Jeff Caddel (generated)::(Checksum: 3:a1e3e97da2c167dfcc17881fba4f6853)
ALTER TABLE `kren_cntnt_typ_t` ADD PRIMARY KEY (`CNTNT_TYP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-12', '2.0.4-SNP', '3:a1e3e97da2c167dfcc17881fba4f6853', 12);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-13::Jeff Caddel (generated)::(Checksum: 3:586403292feffe6e4e6efef66cf1235b)
ALTER TABLE `kren_msg_deliv_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-13', '2.0.4-SNP', '3:586403292feffe6e4e6efef66cf1235b', 13);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-14::Jeff Caddel (generated)::(Checksum: 3:a4583b6ad6c3c37002cb986b9506b1f9)
ALTER TABLE `kren_msg_deliv_t` ADD PRIMARY KEY (`MSG_DELIV_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-14', '2.0.4-SNP', '3:a4583b6ad6c3c37002cb986b9506b1f9', 14);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-15::Jeff Caddel (generated)::(Checksum: 3:ad095857776f777177710836420eca4f)
ALTER TABLE `kren_msg_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-15', '2.0.4-SNP', '3:ad095857776f777177710836420eca4f', 15);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-16::Jeff Caddel (generated)::(Checksum: 3:989b3f05ecf086101a78d32e936d39f6)
ALTER TABLE `kren_msg_t` ADD PRIMARY KEY (`MSG_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-16', '2.0.4-SNP', '3:989b3f05ecf086101a78d32e936d39f6', 16);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-17::Jeff Caddel (generated)::(Checksum: 3:0a19225c447323c09cab9aa5120c7e54)
ALTER TABLE `kren_ntfctn_msg_deliv_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-17', '2.0.4-SNP', '3:0a19225c447323c09cab9aa5120c7e54', 17);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-18::Jeff Caddel (generated)::(Checksum: 3:3e3f5ac765e8bc553e757566748f913d)
ALTER TABLE `kren_ntfctn_msg_deliv_t` ADD PRIMARY KEY (`NTFCTN_MSG_DELIV_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-18', '2.0.4-SNP', '3:3e3f5ac765e8bc553e757566748f913d', 18);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-19::Jeff Caddel (generated)::(Checksum: 3:f050e44bb646d8c2fda5c24e8a414f98)
ALTER TABLE `kren_ntfctn_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-19', '2.0.4-SNP', '3:f050e44bb646d8c2fda5c24e8a414f98', 19);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-20::Jeff Caddel (generated)::(Checksum: 3:012503246673c9d3e1f50fe4c406c69a)
ALTER TABLE `kren_ntfctn_t` ADD PRIMARY KEY (`NTFCTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-20', '2.0.4-SNP', '3:012503246673c9d3e1f50fe4c406c69a', 20);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-21::Jeff Caddel (generated)::(Checksum: 3:2e084364a18280972cf8b48fdce54671)
ALTER TABLE `kren_prio_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-21', '2.0.4-SNP', '3:2e084364a18280972cf8b48fdce54671', 21);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-22::Jeff Caddel (generated)::(Checksum: 3:b631b659f05c605eac0430236356fb2a)
ALTER TABLE `kren_prio_t` ADD PRIMARY KEY (`PRIO_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-22', '2.0.4-SNP', '3:b631b659f05c605eac0430236356fb2a', 22);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-23::Jeff Caddel (generated)::(Checksum: 3:42eb014ef1d918e948b0bfbe1bc3a1e6)
ALTER TABLE `kren_prodcr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-23', '2.0.4-SNP', '3:42eb014ef1d918e948b0bfbe1bc3a1e6', 23);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-24::Jeff Caddel (generated)::(Checksum: 3:20c7ee6b28db7e13e07e159a4cb1eee4)
ALTER TABLE `kren_prodcr_t` ADD PRIMARY KEY (`PRODCR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-24', '2.0.4-SNP', '3:20c7ee6b28db7e13e07e159a4cb1eee4', 24);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-25::Jeff Caddel (generated)::(Checksum: 3:e980142f84bc73e44a3c044e1e674e4b)
ALTER TABLE `kren_recip_deliv_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-25', '2.0.4-SNP', '3:e980142f84bc73e44a3c044e1e674e4b', 25);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-26::Jeff Caddel (generated)::(Checksum: 3:d6bcb273d58118ea02a6512dd84092e2)
ALTER TABLE `kren_recip_deliv_t` ADD PRIMARY KEY (`RECIP_DELIV_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-26', '2.0.4-SNP', '3:d6bcb273d58118ea02a6512dd84092e2', 26);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-27::Jeff Caddel (generated)::(Checksum: 3:97e689a591bc5846b20ecf1e0da7e43b)
ALTER TABLE `kren_recip_list_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-27', '2.0.4-SNP', '3:97e689a591bc5846b20ecf1e0da7e43b', 27);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-28::Jeff Caddel (generated)::(Checksum: 3:97292629a0f9ae6d0b040954ad855635)
ALTER TABLE `kren_recip_list_t` ADD PRIMARY KEY (`RECIP_LIST_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-28', '2.0.4-SNP', '3:97292629a0f9ae6d0b040954ad855635', 28);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-29::Jeff Caddel (generated)::(Checksum: 3:56ada12b99a4d6c5822097c77fcfae26)
ALTER TABLE `kren_recip_pref_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-29', '2.0.4-SNP', '3:56ada12b99a4d6c5822097c77fcfae26', 29);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-30::Jeff Caddel (generated)::(Checksum: 3:e0e4fb895735e4c48c5eb4f3951eb4ef)
ALTER TABLE `kren_recip_prefs_t` ADD PRIMARY KEY (`RECIP_PREFS_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-30', '2.0.4-SNP', '3:e0e4fb895735e4c48c5eb4f3951eb4ef', 30);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-31::Jeff Caddel (generated)::(Checksum: 3:74c584be0aad7ee225e3e0dc96b2fa4d)
ALTER TABLE `kren_recip_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-31', '2.0.4-SNP', '3:74c584be0aad7ee225e3e0dc96b2fa4d', 31);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-32::Jeff Caddel (generated)::(Checksum: 3:f2fae2615f61e8335263ad8ddbaa66b2)
ALTER TABLE `kren_recip_t` ADD PRIMARY KEY (`RECIP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-32', '2.0.4-SNP', '3:f2fae2615f61e8335263ad8ddbaa66b2', 32);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-33::Jeff Caddel (generated)::(Checksum: 3:74c2016c0a9966c93889d5cf66a945a7)
ALTER TABLE `kren_rvwer_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-33', '2.0.4-SNP', '3:74c2016c0a9966c93889d5cf66a945a7', 33);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-34::Jeff Caddel (generated)::(Checksum: 3:5873d2bbe8003aa7a8f4d97fad8f368a)
ALTER TABLE `kren_rvwer_t` ADD PRIMARY KEY (`RVWER_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-34', '2.0.4-SNP', '3:5873d2bbe8003aa7a8f4d97fad8f368a', 34);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-35::Jeff Caddel (generated)::(Checksum: 3:78ad0bca082c33f7ab71b630b64952f0)
ALTER TABLE `kren_sndr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-35', '2.0.4-SNP', '3:78ad0bca082c33f7ab71b630b64952f0', 35);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-36::Jeff Caddel (generated)::(Checksum: 3:2b1637f924357618f1e2e89111c429c4)
ALTER TABLE `kren_sndr_t` ADD PRIMARY KEY (`SNDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-36', '2.0.4-SNP', '3:2b1637f924357618f1e2e89111c429c4', 36);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-37::Jeff Caddel (generated)::(Checksum: 3:3564385b22999ebbf4f8a98af5f71d2b)
ALTER TABLE `krew_actn_itm_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-37', '2.0.4-SNP', '3:3564385b22999ebbf4f8a98af5f71d2b', 37);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-38::Jeff Caddel (generated)::(Checksum: 3:8022d0304ac1db95bf9465255ae473af)
ALTER TABLE `krew_actn_itm_t` ADD PRIMARY KEY (`ACTN_ITM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-38', '2.0.4-SNP', '3:8022d0304ac1db95bf9465255ae473af', 38);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-39::Jeff Caddel (generated)::(Checksum: 3:207a13dbfd5578ae208a59883eeef804)
ALTER TABLE `krew_actn_list_optn_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-39', '2.0.4-SNP', '3:207a13dbfd5578ae208a59883eeef804', 39);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-40::Jeff Caddel (generated)::(Checksum: 3:823c3b98ea9bb3922e6f34eb2cc1e4ce)
ALTER TABLE `krew_actn_rqst_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-40', '2.0.4-SNP', '3:823c3b98ea9bb3922e6f34eb2cc1e4ce', 40);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-41::Jeff Caddel (generated)::(Checksum: 3:701150e6d83ac75dafe5dfbcaa97edf5)
ALTER TABLE `krew_actn_rqst_t` ADD PRIMARY KEY (`ACTN_RQST_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-41', '2.0.4-SNP', '3:701150e6d83ac75dafe5dfbcaa97edf5', 41);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-42::Jeff Caddel (generated)::(Checksum: 3:27a579ca56539217f06654b47483c8c3)
ALTER TABLE `krew_actn_tkn_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-42', '2.0.4-SNP', '3:27a579ca56539217f06654b47483c8c3', 42);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-43::Jeff Caddel (generated)::(Checksum: 3:fc4d6f606fa304b4d993b4e0468c2ec7)
ALTER TABLE `krew_actn_tkn_t` ADD PRIMARY KEY (`ACTN_TKN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-43', '2.0.4-SNP', '3:fc4d6f606fa304b4d993b4e0468c2ec7', 43);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-44::Jeff Caddel (generated)::(Checksum: 3:499f6ddaf338f6022df8ecb798720e88)
ALTER TABLE `krew_app_doc_stat_tran_t` ADD PRIMARY KEY (`APP_DOC_STAT_TRAN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-44', '2.0.4-SNP', '3:499f6ddaf338f6022df8ecb798720e88', 44);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-45::Jeff Caddel (generated)::(Checksum: 3:e40e33c2334e41afa4b4e0d28aefb2b5)
ALTER TABLE `krew_att_t` ADD PRIMARY KEY (`ATTACHMENT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-45', '2.0.4-SNP', '3:e40e33c2334e41afa4b4e0d28aefb2b5', 45);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-46::Jeff Caddel (generated)::(Checksum: 3:a4699fb2ae021c00bd34c4c3d2437197)
ALTER TABLE `krew_dlgn_rsp_t` ADD PRIMARY KEY (`DLGN_RULE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-46', '2.0.4-SNP', '3:a4699fb2ae021c00bd34c4c3d2437197', 46);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-47::Jeff Caddel (generated)::(Checksum: 3:55fdb5ce8c742a7509343ab1d1808905)
ALTER TABLE `krew_doc_hdr_cntnt_t` ADD PRIMARY KEY (`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-47', '2.0.4-SNP', '3:55fdb5ce8c742a7509343ab1d1808905', 47);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-48::Jeff Caddel (generated)::(Checksum: 3:d8b21bd3fe56eb6babe7789caaf62f4b)
ALTER TABLE `krew_doc_hdr_ext_dt_t` ADD PRIMARY KEY (`DOC_HDR_EXT_DT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-48', '2.0.4-SNP', '3:d8b21bd3fe56eb6babe7789caaf62f4b', 48);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-49::Jeff Caddel (generated)::(Checksum: 3:765d5c89c918224f56ae20553478e003)
ALTER TABLE `krew_doc_hdr_ext_flt_t` ADD PRIMARY KEY (`DOC_HDR_EXT_FLT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-49', '2.0.4-SNP', '3:765d5c89c918224f56ae20553478e003', 49);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-50::Jeff Caddel (generated)::(Checksum: 3:a0642093bccd39a5ff9282c8a9a31fb7)
ALTER TABLE `krew_doc_hdr_ext_long_t` ADD PRIMARY KEY (`DOC_HDR_EXT_LONG_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-50', '2.0.4-SNP', '3:a0642093bccd39a5ff9282c8a9a31fb7', 50);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-51::Jeff Caddel (generated)::(Checksum: 3:5c95bbb9d69a51334433cd44a8be2b5c)
ALTER TABLE `krew_doc_hdr_ext_t` ADD PRIMARY KEY (`DOC_HDR_EXT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-51', '2.0.4-SNP', '3:5c95bbb9d69a51334433cd44a8be2b5c', 51);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-52::Jeff Caddel (generated)::(Checksum: 3:cd28349185ea488b9908ee7e12004cd7)
ALTER TABLE `krew_doc_hdr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-52', '2.0.4-SNP', '3:cd28349185ea488b9908ee7e12004cd7', 52);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-53::Jeff Caddel (generated)::(Checksum: 3:9d3f1f778a0e972b85ef51724bbe8328)
ALTER TABLE `krew_doc_hdr_t` ADD PRIMARY KEY (`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-53', '2.0.4-SNP', '3:9d3f1f778a0e972b85ef51724bbe8328', 53);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-54::Jeff Caddel (generated)::(Checksum: 3:52a74dd8c9769665947895f502586c7f)
ALTER TABLE `krew_doc_lnk_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-54', '2.0.4-SNP', '3:52a74dd8c9769665947895f502586c7f', 54);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-55::Jeff Caddel (generated)::(Checksum: 3:978acee8defba6d14935a67f7b38ecb4)
ALTER TABLE `krew_doc_lnk_t` ADD PRIMARY KEY (`DOC_LNK_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-55', '2.0.4-SNP', '3:978acee8defba6d14935a67f7b38ecb4', 55);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-56::Jeff Caddel (generated)::(Checksum: 3:24657ca9945affe165752a839315b7fa)
ALTER TABLE `krew_doc_nte_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-56', '2.0.4-SNP', '3:24657ca9945affe165752a839315b7fa', 56);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-57::Jeff Caddel (generated)::(Checksum: 3:399d11406e4e7b1ebf28b43f4ea04fc1)
ALTER TABLE `krew_doc_nte_t` ADD PRIMARY KEY (`DOC_NTE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-57', '2.0.4-SNP', '3:399d11406e4e7b1ebf28b43f4ea04fc1', 57);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-58::Jeff Caddel (generated)::(Checksum: 3:e498c3c61780c12fcc106eeff6ad7683)
ALTER TABLE `krew_doc_typ_app_doc_stat_t` ADD PRIMARY KEY (`DOC_TYP_ID`, `DOC_STAT_NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-58', '2.0.4-SNP', '3:e498c3c61780c12fcc106eeff6ad7683', 58);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-59::Jeff Caddel (generated)::(Checksum: 3:794f9a8fe06b2e2ed1f559d4c740e35e)
ALTER TABLE `krew_doc_typ_attr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-59', '2.0.4-SNP', '3:794f9a8fe06b2e2ed1f559d4c740e35e', 59);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-60::Jeff Caddel (generated)::(Checksum: 3:e233ebf81c27be13ca95fd8542961623)
ALTER TABLE `krew_doc_typ_attr_t` ADD PRIMARY KEY (`DOC_TYP_ATTRIB_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-60', '2.0.4-SNP', '3:e233ebf81c27be13ca95fd8542961623', 60);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-61::Jeff Caddel (generated)::(Checksum: 3:d1e2746ebef667014c81ed5d5ecd3547)
ALTER TABLE `krew_doc_typ_plcy_reln_t` ADD PRIMARY KEY (`DOC_TYP_ID`, `DOC_PLCY_NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-61', '2.0.4-SNP', '3:d1e2746ebef667014c81ed5d5ecd3547', 61);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-62::Jeff Caddel (generated)::(Checksum: 3:8a9d9bb11059435a7fc21e17fe671867)
ALTER TABLE `krew_doc_typ_proc_t` ADD PRIMARY KEY (`DOC_TYP_PROC_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-62', '2.0.4-SNP', '3:8a9d9bb11059435a7fc21e17fe671867', 62);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-63::Jeff Caddel (generated)::(Checksum: 3:9ef534981888699960cffd8382832d0e)
ALTER TABLE `krew_doc_typ_t` ADD PRIMARY KEY (`DOC_TYP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-63', '2.0.4-SNP', '3:9ef534981888699960cffd8382832d0e', 63);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-64::Jeff Caddel (generated)::(Checksum: 3:9ef890fa235a929b45e20b6a9d349207)
ALTER TABLE `krew_edl_assctn_t` ADD PRIMARY KEY (`EDOCLT_ASSOC_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-64', '2.0.4-SNP', '3:9ef890fa235a929b45e20b6a9d349207', 64);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-65::Jeff Caddel (generated)::(Checksum: 3:cb97862730912cbc860ebe60f99441c2)
ALTER TABLE `krew_edl_def_t` ADD PRIMARY KEY (`EDOCLT_DEF_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-65', '2.0.4-SNP', '3:cb97862730912cbc860ebe60f99441c2', 65);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-66::Jeff Caddel (generated)::(Checksum: 3:0d66b1f8327f2b7cc32cbacbe5792f53)
ALTER TABLE `krew_edl_dmp_t` ADD PRIMARY KEY (`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-66', '2.0.4-SNP', '3:0d66b1f8327f2b7cc32cbacbe5792f53', 66);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-67::Jeff Caddel (generated)::(Checksum: 3:9f69c8df147acf3f652b56bade24d735)
ALTER TABLE `krew_edl_fld_dmp_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-67', '2.0.4-SNP', '3:9f69c8df147acf3f652b56bade24d735', 67);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-68::Jeff Caddel (generated)::(Checksum: 3:e676094c76385e86470e4819167e8650)
ALTER TABLE `krew_edl_fld_dmp_t` ADD PRIMARY KEY (`EDL_FIELD_DMP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-68', '2.0.4-SNP', '3:e676094c76385e86470e4819167e8650', 68);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-69::Jeff Caddel (generated)::(Checksum: 3:de8b52fe5a4fba13f1fa224700ec076e)
ALTER TABLE `krew_edl_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-69', '2.0.4-SNP', '3:de8b52fe5a4fba13f1fa224700ec076e', 69);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-70::Jeff Caddel (generated)::(Checksum: 3:59592f30456f920d33dbd597d67e6714)
ALTER TABLE `krew_hlp_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-70', '2.0.4-SNP', '3:59592f30456f920d33dbd597d67e6714', 70);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-71::Jeff Caddel (generated)::(Checksum: 3:bec43c259d6823480580b6f29f1d9180)
ALTER TABLE `krew_hlp_t` ADD PRIMARY KEY (`HLP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-71', '2.0.4-SNP', '3:bec43c259d6823480580b6f29f1d9180', 71);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-72::Jeff Caddel (generated)::(Checksum: 3:79a7b116758172ce474751b7f3f9741e)
ALTER TABLE `krew_init_rte_node_instn_t` ADD PRIMARY KEY (`DOC_HDR_ID`, `RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-72', '2.0.4-SNP', '3:79a7b116758172ce474751b7f3f9741e', 72);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-73::Jeff Caddel (generated)::(Checksum: 3:b8921526c3a9c511365bfff6ddd99032)
ALTER TABLE `krew_out_box_itm_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-73', '2.0.4-SNP', '3:b8921526c3a9c511365bfff6ddd99032', 73);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-74::Jeff Caddel (generated)::(Checksum: 3:7fa877edb3566e07c72f0046455a40b8)
ALTER TABLE `krew_out_box_itm_t` ADD PRIMARY KEY (`ACTN_ITM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-74', '2.0.4-SNP', '3:7fa877edb3566e07c72f0046455a40b8', 74);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-75::Jeff Caddel (generated)::(Checksum: 3:0df9ebc425bad722d08df9db4f8c613b)
ALTER TABLE `krew_ria_doc_t` ADD PRIMARY KEY (`RIA_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-75', '2.0.4-SNP', '3:0df9ebc425bad722d08df9db4f8c613b', 75);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-76::Jeff Caddel (generated)::(Checksum: 3:93750ffa6d170f271bd7e946d8326a3a)
ALTER TABLE `krew_ria_doctype_map_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-76', '2.0.4-SNP', '3:93750ffa6d170f271bd7e946d8326a3a', 76);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-77::Jeff Caddel (generated)::(Checksum: 3:50b6471fabb861d0d6fd2a8f3eb853a4)
ALTER TABLE `krew_ria_doctype_map_t` ADD PRIMARY KEY (`ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-77', '2.0.4-SNP', '3:50b6471fabb861d0d6fd2a8f3eb853a4', 77);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-78::Jeff Caddel (generated)::(Checksum: 3:033cb8b76fe667f6c300833c72bf3fd2)
ALTER TABLE `krew_rmv_rplc_doc_t` ADD PRIMARY KEY (`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-78', '2.0.4-SNP', '3:033cb8b76fe667f6c300833c72bf3fd2', 78);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-79::Jeff Caddel (generated)::(Checksum: 3:dcc390e09ccb7edaf3bd0d72013ecdf2)
ALTER TABLE `krew_rmv_rplc_grp_t` ADD PRIMARY KEY (`DOC_HDR_ID`, `GRP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-79', '2.0.4-SNP', '3:dcc390e09ccb7edaf3bd0d72013ecdf2', 79);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-80::Jeff Caddel (generated)::(Checksum: 3:52935a5bc7d4fb0759080a55dd9f4a6f)
ALTER TABLE `krew_rmv_rplc_rule_t` ADD PRIMARY KEY (`DOC_HDR_ID`, `RULE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-80', '2.0.4-SNP', '3:52935a5bc7d4fb0759080a55dd9f4a6f', 80);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-81::Jeff Caddel (generated)::(Checksum: 3:90edd51b0b9406ee9c67e1180af6a17d)
ALTER TABLE `krew_rsp_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-81', '2.0.4-SNP', '3:90edd51b0b9406ee9c67e1180af6a17d', 81);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-82::Jeff Caddel (generated)::(Checksum: 3:76702388c1eb755a82043a7ad0331061)
ALTER TABLE `krew_rte_brch_proto_t` ADD PRIMARY KEY (`RTE_BRCH_PROTO_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-82', '2.0.4-SNP', '3:76702388c1eb755a82043a7ad0331061', 82);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-83::Jeff Caddel (generated)::(Checksum: 3:048de45ef2792048c57ab3f44678e400)
ALTER TABLE `krew_rte_brch_st_t` ADD PRIMARY KEY (`RTE_BRCH_ST_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-83', '2.0.4-SNP', '3:048de45ef2792048c57ab3f44678e400', 83);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-84::Jeff Caddel (generated)::(Checksum: 3:4c94a8f5b1c8512b97a357946c4b6114)
ALTER TABLE `krew_rte_brch_t` ADD PRIMARY KEY (`RTE_BRCH_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-84', '2.0.4-SNP', '3:4c94a8f5b1c8512b97a357946c4b6114', 84);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-85::Jeff Caddel (generated)::(Checksum: 3:0f5b3361d6a7c6ac15b8dd589e558a31)
ALTER TABLE `krew_rte_node_cfg_parm_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-85', '2.0.4-SNP', '3:0f5b3361d6a7c6ac15b8dd589e558a31', 85);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-86::Jeff Caddel (generated)::(Checksum: 3:3cefb60a1ade143caf3492eb5f4493d2)
ALTER TABLE `krew_rte_node_cfg_parm_t` ADD PRIMARY KEY (`RTE_NODE_CFG_PARM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-86', '2.0.4-SNP', '3:3cefb60a1ade143caf3492eb5f4493d2', 86);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-87::Jeff Caddel (generated)::(Checksum: 3:6af73c4814787399e1d30fd584c00820)
ALTER TABLE `krew_rte_node_instn_lnk_t` ADD PRIMARY KEY (`FROM_RTE_NODE_INSTN_ID`, `TO_RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-87', '2.0.4-SNP', '3:6af73c4814787399e1d30fd584c00820', 87);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-88::Jeff Caddel (generated)::(Checksum: 3:4d5701c6dd4f3730b1f5014060237efd)
ALTER TABLE `krew_rte_node_instn_st_t` ADD PRIMARY KEY (`RTE_NODE_INSTN_ST_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-88', '2.0.4-SNP', '3:4d5701c6dd4f3730b1f5014060237efd', 88);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-89::Jeff Caddel (generated)::(Checksum: 3:580bf5290393cb14781aaaf6ad207f44)
ALTER TABLE `krew_rte_node_instn_t` ADD PRIMARY KEY (`RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-89', '2.0.4-SNP', '3:580bf5290393cb14781aaaf6ad207f44', 89);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-90::Jeff Caddel (generated)::(Checksum: 3:a6ac7feb1c27f64c8564c45a93a00cb7)
ALTER TABLE `krew_rte_node_lnk_t` ADD PRIMARY KEY (`FROM_RTE_NODE_ID`, `TO_RTE_NODE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-90', '2.0.4-SNP', '3:a6ac7feb1c27f64c8564c45a93a00cb7', 90);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-91::Jeff Caddel (generated)::(Checksum: 3:f02226f33b08649d97733b9abca43a0b)
ALTER TABLE `krew_rte_node_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-91', '2.0.4-SNP', '3:f02226f33b08649d97733b9abca43a0b', 91);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-92::Jeff Caddel (generated)::(Checksum: 3:31e9bc98c89abdfed28532964933600f)
ALTER TABLE `krew_rte_node_t` ADD PRIMARY KEY (`RTE_NODE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-92', '2.0.4-SNP', '3:31e9bc98c89abdfed28532964933600f', 92);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-93::Jeff Caddel (generated)::(Checksum: 3:d60f412bb813d0b749ee3a9ef2231f3d)
ALTER TABLE `krew_rte_tmpl_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-93', '2.0.4-SNP', '3:d60f412bb813d0b749ee3a9ef2231f3d', 93);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-94::Jeff Caddel (generated)::(Checksum: 3:97c166ab4a376b05d466884815d1d644)
ALTER TABLE `krew_rule_attr_t` ADD PRIMARY KEY (`RULE_ATTR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-94', '2.0.4-SNP', '3:97c166ab4a376b05d466884815d1d644', 94);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-95::Jeff Caddel (generated)::(Checksum: 3:812e55be1f17f000f439b3d9d915bd8d)
ALTER TABLE `krew_rule_expr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-95', '2.0.4-SNP', '3:812e55be1f17f000f439b3d9d915bd8d', 95);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-96::Jeff Caddel (generated)::(Checksum: 3:4358eb7c38bcc2d83867dee519eeb34f)
ALTER TABLE `krew_rule_expr_t` ADD PRIMARY KEY (`RULE_EXPR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-96', '2.0.4-SNP', '3:4358eb7c38bcc2d83867dee519eeb34f', 96);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-97::Jeff Caddel (generated)::(Checksum: 3:9af8f66241eeac66af5b3ca0b4602cfa)
ALTER TABLE `krew_rule_ext_t` ADD PRIMARY KEY (`RULE_EXT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-97', '2.0.4-SNP', '3:9af8f66241eeac66af5b3ca0b4602cfa', 97);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-98::Jeff Caddel (generated)::(Checksum: 3:f6d38f98f0ce9b4af0c61bb476e8b1dd)
ALTER TABLE `krew_rule_ext_val_t` ADD PRIMARY KEY (`RULE_EXT_VAL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-98', '2.0.4-SNP', '3:f6d38f98f0ce9b4af0c61bb476e8b1dd', 98);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-99::Jeff Caddel (generated)::(Checksum: 3:1808632d1c08d1e4b89a7a28a2cbcdce)
ALTER TABLE `krew_rule_rsp_t` ADD PRIMARY KEY (`RULE_RSP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-99', '2.0.4-SNP', '3:1808632d1c08d1e4b89a7a28a2cbcdce', 99);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-100::Jeff Caddel (generated)::(Checksum: 3:d56f314ba35de74493319f85a66b09a9)
ALTER TABLE `krew_rule_t` ADD PRIMARY KEY (`RULE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-100', '2.0.4-SNP', '3:d56f314ba35de74493319f85a66b09a9', 100);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-101::Jeff Caddel (generated)::(Checksum: 3:03653d8a8fd6e754bf2d972e1ffe5284)
ALTER TABLE `krew_rule_tmpl_attr_t` ADD PRIMARY KEY (`RULE_TMPL_ATTR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-101', '2.0.4-SNP', '3:03653d8a8fd6e754bf2d972e1ffe5284', 101);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-102::Jeff Caddel (generated)::(Checksum: 3:084244790dc4f3ac24a00b08c24fcfbb)
ALTER TABLE `krew_rule_tmpl_optn_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-102', '2.0.4-SNP', '3:084244790dc4f3ac24a00b08c24fcfbb', 102);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-103::Jeff Caddel (generated)::(Checksum: 3:4e8c91f379f6447c9da13ca62f0458cb)
ALTER TABLE `krew_rule_tmpl_optn_t` ADD PRIMARY KEY (`RULE_TMPL_OPTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-103', '2.0.4-SNP', '3:4e8c91f379f6447c9da13ca62f0458cb', 103);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-104::Jeff Caddel (generated)::(Checksum: 3:58b0cc4d6d8d0ac1e083143d005e9506)
ALTER TABLE `krew_rule_tmpl_t` ADD PRIMARY KEY (`RULE_TMPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-104', '2.0.4-SNP', '3:58b0cc4d6d8d0ac1e083143d005e9506', 104);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-105::Jeff Caddel (generated)::(Checksum: 3:3015acdba80601f9ba5d1b7c5654e78a)
ALTER TABLE `krew_srch_attr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-105', '2.0.4-SNP', '3:3015acdba80601f9ba5d1b7c5654e78a', 105);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-106::Jeff Caddel (generated)::(Checksum: 3:43ad0e5371a92f93a0b611be11359bd8)
ALTER TABLE `krew_style_t` ADD PRIMARY KEY (`STYLE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-106', '2.0.4-SNP', '3:43ad0e5371a92f93a0b611be11359bd8', 106);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-107::Jeff Caddel (generated)::(Checksum: 3:91aeacf2fef4c0e54402b3e45f3cbfda)
ALTER TABLE `krew_usr_optn_t` ADD PRIMARY KEY (`PRNCPL_ID`, `PRSN_OPTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-107', '2.0.4-SNP', '3:91aeacf2fef4c0e54402b3e45f3cbfda', 107);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-108::Jeff Caddel (generated)::(Checksum: 3:68000d5dd991d7b47d1e1843b8ea2a8e)
ALTER TABLE `krew_usr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-108', '2.0.4-SNP', '3:68000d5dd991d7b47d1e1843b8ea2a8e', 108);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-109::Jeff Caddel (generated)::(Checksum: 3:9a7d2f8df3431dfd3ba29e7d50c890ad)
ALTER TABLE `krim_addr_typ_t` ADD PRIMARY KEY (`ADDR_TYP_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-109', '2.0.4-SNP', '3:9a7d2f8df3431dfd3ba29e7d50c890ad', 109);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-110::Jeff Caddel (generated)::(Checksum: 3:455fae351120fe41a8e67acdeb5b071d)
ALTER TABLE `krim_afltn_typ_t` ADD PRIMARY KEY (`AFLTN_TYP_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-110', '2.0.4-SNP', '3:455fae351120fe41a8e67acdeb5b071d', 110);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-111::Jeff Caddel (generated)::(Checksum: 3:2c77f0aa79ddbbd1b92de109e14b1dd9)
ALTER TABLE `krim_attr_data_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-111', '2.0.4-SNP', '3:2c77f0aa79ddbbd1b92de109e14b1dd9', 111);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-112::Jeff Caddel (generated)::(Checksum: 3:d9abf0d31c245da28ced96a8b80e9588)
ALTER TABLE `krim_attr_defn_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-112', '2.0.4-SNP', '3:d9abf0d31c245da28ced96a8b80e9588', 112);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-113::Jeff Caddel (generated)::(Checksum: 3:ec437bd4b801f6afa0282efcf9a245df)
ALTER TABLE `krim_attr_defn_t` ADD PRIMARY KEY (`KIM_ATTR_DEFN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-113', '2.0.4-SNP', '3:ec437bd4b801f6afa0282efcf9a245df', 113);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-114::Jeff Caddel (generated)::(Checksum: 3:3cc434181d80b1b1d89274cb5d487c31)
ALTER TABLE `krim_ctznshp_stat_t` ADD PRIMARY KEY (`CTZNSHP_STAT_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-114', '2.0.4-SNP', '3:3cc434181d80b1b1d89274cb5d487c31', 114);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-115::Jeff Caddel (generated)::(Checksum: 3:2b2a7137dbc9a34fa11b713463541ea9)
ALTER TABLE `krim_dlgn_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-115', '2.0.4-SNP', '3:2b2a7137dbc9a34fa11b713463541ea9', 115);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-116::Jeff Caddel (generated)::(Checksum: 3:4d3bac04357ad8948e4aa6d686477012)
ALTER TABLE `krim_dlgn_mbr_attr_data_t` ADD PRIMARY KEY (`ATTR_DATA_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-116', '2.0.4-SNP', '3:4d3bac04357ad8948e4aa6d686477012', 116);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-117::Jeff Caddel (generated)::(Checksum: 3:3f54fc3275e0fc32efee55855008ce6b)
ALTER TABLE `krim_dlgn_mbr_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-117', '2.0.4-SNP', '3:3f54fc3275e0fc32efee55855008ce6b', 117);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-118::Jeff Caddel (generated)::(Checksum: 3:8066f112ef0ed9751b6735a29cfe6635)
ALTER TABLE `krim_dlgn_mbr_t` ADD PRIMARY KEY (`DLGN_MBR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-118', '2.0.4-SNP', '3:8066f112ef0ed9751b6735a29cfe6635', 118);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-119::Jeff Caddel (generated)::(Checksum: 3:1fafadb5f5028a960fb0380332d22e4c)
ALTER TABLE `krim_dlgn_t` ADD PRIMARY KEY (`DLGN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-119', '2.0.4-SNP', '3:1fafadb5f5028a960fb0380332d22e4c', 119);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-120::Jeff Caddel (generated)::(Checksum: 3:de788a0cfd4c5b9be09534ee98f953cc)
ALTER TABLE `krim_email_typ_t` ADD PRIMARY KEY (`EMAIL_TYP_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-120', '2.0.4-SNP', '3:de788a0cfd4c5b9be09534ee98f953cc', 120);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-121::Jeff Caddel (generated)::(Checksum: 3:a07c4eba744ac15e09d5961535675689)
ALTER TABLE `krim_emp_stat_t` ADD PRIMARY KEY (`EMP_STAT_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-121', '2.0.4-SNP', '3:a07c4eba744ac15e09d5961535675689', 121);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-122::Jeff Caddel (generated)::(Checksum: 3:492b93c5cddc6c5cc3b4689ecf727208)
ALTER TABLE `krim_emp_typ_t` ADD PRIMARY KEY (`EMP_TYP_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-122', '2.0.4-SNP', '3:492b93c5cddc6c5cc3b4689ecf727208', 122);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-123::Jeff Caddel (generated)::(Checksum: 3:91a2faada70a1c68aa7818fb34d8e50a)
ALTER TABLE `krim_ent_nm_typ_t` ADD PRIMARY KEY (`ENT_NM_TYP_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-123', '2.0.4-SNP', '3:91a2faada70a1c68aa7818fb34d8e50a', 123);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-124::Jeff Caddel (generated)::(Checksum: 3:2125dc8ed4f5f72ec943dbac6d82a3bf)
ALTER TABLE `krim_ent_typ_t` ADD PRIMARY KEY (`ENT_TYP_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-124', '2.0.4-SNP', '3:2125dc8ed4f5f72ec943dbac6d82a3bf', 124);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-125::Jeff Caddel (generated)::(Checksum: 3:5e8e92a61b91540ea5067fc6e37238b2)
ALTER TABLE `krim_entity_addr_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-125', '2.0.4-SNP', '3:5e8e92a61b91540ea5067fc6e37238b2', 125);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-126::Jeff Caddel (generated)::(Checksum: 3:eddeca6340347eee542b3b6442c270c9)
ALTER TABLE `krim_entity_addr_t` ADD PRIMARY KEY (`ENTITY_ADDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-126', '2.0.4-SNP', '3:eddeca6340347eee542b3b6442c270c9', 126);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-127::Jeff Caddel (generated)::(Checksum: 3:2fb64daf75c1ded68df78c1cba055135)
ALTER TABLE `krim_entity_afltn_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-127', '2.0.4-SNP', '3:2fb64daf75c1ded68df78c1cba055135', 127);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-128::Jeff Caddel (generated)::(Checksum: 3:370373455b8890e4460e56a8b846f643)
ALTER TABLE `krim_entity_afltn_t` ADD PRIMARY KEY (`ENTITY_AFLTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-128', '2.0.4-SNP', '3:370373455b8890e4460e56a8b846f643', 128);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-129::Jeff Caddel (generated)::(Checksum: 3:a46544aea8517605052edb9a1524265d)
ALTER TABLE `krim_entity_bio_t` ADD PRIMARY KEY (`ENTITY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-129', '2.0.4-SNP', '3:a46544aea8517605052edb9a1524265d', 129);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-130::Jeff Caddel (generated)::(Checksum: 3:a7ad9b8fb563ad78dc33d258efe023df)
ALTER TABLE `krim_entity_cache_t` ADD PRIMARY KEY (`ENTITY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-130', '2.0.4-SNP', '3:a7ad9b8fb563ad78dc33d258efe023df', 130);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-131::Jeff Caddel (generated)::(Checksum: 3:a59171fea2d082d2b6277311a858e694)
ALTER TABLE `krim_entity_ctznshp_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-131', '2.0.4-SNP', '3:a59171fea2d082d2b6277311a858e694', 131);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-132::Jeff Caddel (generated)::(Checksum: 3:7693a0b107ea00706da65c14edd3c8c6)
ALTER TABLE `krim_entity_ctznshp_t` ADD PRIMARY KEY (`ENTITY_CTZNSHP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-132', '2.0.4-SNP', '3:7693a0b107ea00706da65c14edd3c8c6', 132);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-133::Jeff Caddel (generated)::(Checksum: 3:ea4888a79cb3a6d25c382b2f06a8ee11)
ALTER TABLE `krim_entity_email_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-133', '2.0.4-SNP', '3:ea4888a79cb3a6d25c382b2f06a8ee11', 133);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-134::Jeff Caddel (generated)::(Checksum: 3:6a09979397bf2bba0172d9dc1dc7b273)
ALTER TABLE `krim_entity_email_t` ADD PRIMARY KEY (`ENTITY_EMAIL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-134', '2.0.4-SNP', '3:6a09979397bf2bba0172d9dc1dc7b273', 134);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-135::Jeff Caddel (generated)::(Checksum: 3:a44d1359767c08dab9ed56e83590b98b)
ALTER TABLE `krim_entity_emp_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-135', '2.0.4-SNP', '3:a44d1359767c08dab9ed56e83590b98b', 135);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-136::Jeff Caddel (generated)::(Checksum: 3:2a4f71f2b254d80dca451b489a74e090)
ALTER TABLE `krim_entity_emp_info_t` ADD PRIMARY KEY (`ENTITY_EMP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-136', '2.0.4-SNP', '3:2a4f71f2b254d80dca451b489a74e090', 136);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-137::Jeff Caddel (generated)::(Checksum: 3:43f3b467297d95b90998b047ed26144f)
ALTER TABLE `krim_entity_ent_typ_t` ADD PRIMARY KEY (`ENT_TYP_CD`, `ENTITY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-137', '2.0.4-SNP', '3:43f3b467297d95b90998b047ed26144f', 137);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-138::Jeff Caddel (generated)::(Checksum: 3:db2740c86bae780fee19b4c89521cb33)
ALTER TABLE `krim_entity_ethnic_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-138', '2.0.4-SNP', '3:db2740c86bae780fee19b4c89521cb33', 138);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-139::Jeff Caddel (generated)::(Checksum: 3:9a7f7890ef6ae717be0b99052ced9880)
ALTER TABLE `krim_entity_ethnic_t` ADD PRIMARY KEY (`ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-139', '2.0.4-SNP', '3:9a7f7890ef6ae717be0b99052ced9880', 139);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-140::Jeff Caddel (generated)::(Checksum: 3:3c72921766d57f6054c91fc8ef43cc8e)
ALTER TABLE `krim_entity_ext_id_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-140', '2.0.4-SNP', '3:3c72921766d57f6054c91fc8ef43cc8e', 140);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-141::Jeff Caddel (generated)::(Checksum: 3:2e87200625d6755cc3632766fbc3be5a)
ALTER TABLE `krim_entity_ext_id_t` ADD PRIMARY KEY (`ENTITY_EXT_ID_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-141', '2.0.4-SNP', '3:2e87200625d6755cc3632766fbc3be5a', 141);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-142::Jeff Caddel (generated)::(Checksum: 3:36682525601ceb995fb46de7bfaaa24f)
ALTER TABLE `krim_entity_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-142', '2.0.4-SNP', '3:36682525601ceb995fb46de7bfaaa24f', 142);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-143::Jeff Caddel (generated)::(Checksum: 3:c16b6c6f13b170a4c7b193f313fef623)
ALTER TABLE `krim_entity_nm_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-143', '2.0.4-SNP', '3:c16b6c6f13b170a4c7b193f313fef623', 143);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-144::Jeff Caddel (generated)::(Checksum: 3:fa9187c1395081757dd15c68620afff3)
ALTER TABLE `krim_entity_nm_t` ADD PRIMARY KEY (`ENTITY_NM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-144', '2.0.4-SNP', '3:fa9187c1395081757dd15c68620afff3', 144);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-145::Jeff Caddel (generated)::(Checksum: 3:85f3c15188793145d5b8d3eada0e8cf3)
ALTER TABLE `krim_entity_phone_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-145', '2.0.4-SNP', '3:85f3c15188793145d5b8d3eada0e8cf3', 145);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-146::Jeff Caddel (generated)::(Checksum: 3:8b10a2a2c6320ca771ed97bf19032fdb)
ALTER TABLE `krim_entity_phone_t` ADD PRIMARY KEY (`ENTITY_PHONE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-146', '2.0.4-SNP', '3:8b10a2a2c6320ca771ed97bf19032fdb', 146);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-147::Jeff Caddel (generated)::(Checksum: 3:9c57a5b642cc69117bd4feca685ef2c2)
ALTER TABLE `krim_entity_priv_pref_t` ADD PRIMARY KEY (`ENTITY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-147', '2.0.4-SNP', '3:9c57a5b642cc69117bd4feca685ef2c2', 147);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-148::Jeff Caddel (generated)::(Checksum: 3:59278f8200286d777d0a31fe9722cb74)
ALTER TABLE `krim_entity_residency_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-148', '2.0.4-SNP', '3:59278f8200286d777d0a31fe9722cb74', 148);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-149::Jeff Caddel (generated)::(Checksum: 3:30972c16f8fe0584bb5e28f6ae5567f4)
ALTER TABLE `krim_entity_residency_t` ADD PRIMARY KEY (`ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-149', '2.0.4-SNP', '3:30972c16f8fe0584bb5e28f6ae5567f4', 149);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-150::Jeff Caddel (generated)::(Checksum: 3:baa1d1067e825232a622999ca92c156d)
ALTER TABLE `krim_entity_t` ADD PRIMARY KEY (`ENTITY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-150', '2.0.4-SNP', '3:baa1d1067e825232a622999ca92c156d', 150);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-151::Jeff Caddel (generated)::(Checksum: 3:c73bd3f244a004c8152eb2e33fb203d4)
ALTER TABLE `krim_entity_visa_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-151', '2.0.4-SNP', '3:c73bd3f244a004c8152eb2e33fb203d4', 151);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-152::Jeff Caddel (generated)::(Checksum: 3:1649363dfa127b068bd30f53c137500d)
ALTER TABLE `krim_entity_visa_t` ADD PRIMARY KEY (`ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-152', '2.0.4-SNP', '3:1649363dfa127b068bd30f53c137500d', 152);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-153::Jeff Caddel (generated)::(Checksum: 3:78a958c9ba564418667f20f1e320ef84)
ALTER TABLE `krim_ext_id_typ_t` ADD PRIMARY KEY (`EXT_ID_TYP_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-153', '2.0.4-SNP', '3:78a958c9ba564418667f20f1e320ef84', 153);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-154::Jeff Caddel (generated)::(Checksum: 3:66881d6f0759e0b1df468965136e3b93)
ALTER TABLE `krim_grp_attr_data_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-154', '2.0.4-SNP', '3:66881d6f0759e0b1df468965136e3b93', 154);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-155::Jeff Caddel (generated)::(Checksum: 3:72aff6e48b2f767a62e37fb74a8f15de)
ALTER TABLE `krim_grp_attr_data_t` ADD PRIMARY KEY (`ATTR_DATA_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-155', '2.0.4-SNP', '3:72aff6e48b2f767a62e37fb74a8f15de', 155);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-156::Jeff Caddel (generated)::(Checksum: 3:c3490b17b9077e6d8f4d2af226efe6f9)
ALTER TABLE `krim_grp_document_t` ADD PRIMARY KEY (`FDOC_NBR`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-156', '2.0.4-SNP', '3:c3490b17b9077e6d8f4d2af226efe6f9', 156);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-157::Jeff Caddel (generated)::(Checksum: 3:074d644ce6ce6aff0f6bd005f593c396)
ALTER TABLE `krim_grp_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-157', '2.0.4-SNP', '3:074d644ce6ce6aff0f6bd005f593c396', 157);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-158::Jeff Caddel (generated)::(Checksum: 3:e77b8dabe4d07b0fcb5661c5f373fd8d)
ALTER TABLE `krim_grp_mbr_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-158', '2.0.4-SNP', '3:e77b8dabe4d07b0fcb5661c5f373fd8d', 158);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-159::Jeff Caddel (generated)::(Checksum: 3:99316978d03f7bb1ac7fac53bd025646)
ALTER TABLE `krim_grp_mbr_t` ADD PRIMARY KEY (`GRP_MBR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-159', '2.0.4-SNP', '3:99316978d03f7bb1ac7fac53bd025646', 159);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-160::Jeff Caddel (generated)::(Checksum: 3:ea0e28184abdd24ad5061d28cf14b706)
ALTER TABLE `krim_grp_t` ADD PRIMARY KEY (`GRP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-160', '2.0.4-SNP', '3:ea0e28184abdd24ad5061d28cf14b706', 160);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-161::Jeff Caddel (generated)::(Checksum: 3:2f79829aeb29812cbc02f725ca7797a8)
ALTER TABLE `krim_perm_attr_data_t` ADD PRIMARY KEY (`ATTR_DATA_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-161', '2.0.4-SNP', '3:2f79829aeb29812cbc02f725ca7797a8', 161);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-162::Jeff Caddel (generated)::(Checksum: 3:ef8966842ce5e71ab032d5033d3e1b2f)
ALTER TABLE `krim_perm_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-162', '2.0.4-SNP', '3:ef8966842ce5e71ab032d5033d3e1b2f', 162);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-163::Jeff Caddel (generated)::(Checksum: 3:1897c9bca3d371e0bbb33eb12123e818)
ALTER TABLE `krim_perm_rqrd_attr_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-163', '2.0.4-SNP', '3:1897c9bca3d371e0bbb33eb12123e818', 163);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-164::Jeff Caddel (generated)::(Checksum: 3:d4bcce2bf8b81dce142073d134705911)
ALTER TABLE `krim_perm_t` ADD PRIMARY KEY (`PERM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-164', '2.0.4-SNP', '3:d4bcce2bf8b81dce142073d134705911', 164);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-165::Jeff Caddel (generated)::(Checksum: 3:7a57891f00563f56543cf07bfbfc74ff)
ALTER TABLE `krim_perm_tmpl_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-165', '2.0.4-SNP', '3:7a57891f00563f56543cf07bfbfc74ff', 165);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-166::Jeff Caddel (generated)::(Checksum: 3:ac3c31023204186fa9407be7518b9833)
ALTER TABLE `krim_perm_tmpl_t` ADD PRIMARY KEY (`PERM_TMPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-166', '2.0.4-SNP', '3:ac3c31023204186fa9407be7518b9833', 166);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-167::Jeff Caddel (generated)::(Checksum: 3:841fbdd3ebd102e8e06d8a08a6d99b93)
ALTER TABLE `krim_person_document_t` ADD PRIMARY KEY (`FDOC_NBR`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-167', '2.0.4-SNP', '3:841fbdd3ebd102e8e06d8a08a6d99b93', 167);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-168::Jeff Caddel (generated)::(Checksum: 3:b87982ebb79b9396992f379f58eb8f26)
ALTER TABLE `krim_phone_typ_t` ADD PRIMARY KEY (`PHONE_TYP_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-168', '2.0.4-SNP', '3:b87982ebb79b9396992f379f58eb8f26', 168);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-169::Jeff Caddel (generated)::(Checksum: 3:f07e67689fb2898cc6f3ced80ea1646c)
ALTER TABLE `krim_pnd_addr_mt` ADD PRIMARY KEY (`FDOC_NBR`, `ENTITY_ADDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-169', '2.0.4-SNP', '3:f07e67689fb2898cc6f3ced80ea1646c', 169);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-170::Jeff Caddel (generated)::(Checksum: 3:c2f4686a4ef07978a1d6ab63fddda53f)
ALTER TABLE `krim_pnd_afltn_mt` ADD PRIMARY KEY (`FDOC_NBR`, `ENTITY_AFLTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-170', '2.0.4-SNP', '3:c2f4686a4ef07978a1d6ab63fddda53f', 170);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-171::Jeff Caddel (generated)::(Checksum: 3:366daa8ae92b3bb05c32f2cd4802cb57)
ALTER TABLE `krim_pnd_ctznshp_mt` ADD PRIMARY KEY (`FDOC_NBR`, `ENTITY_CTZNSHP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-171', '2.0.4-SNP', '3:366daa8ae92b3bb05c32f2cd4802cb57', 171);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-172::Jeff Caddel (generated)::(Checksum: 3:e44199f37376b634cd4c5a0183e51900)
ALTER TABLE `krim_pnd_dlgn_mbr_attr_data_t` ADD PRIMARY KEY (`FDOC_NBR`, `ATTR_DATA_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-172', '2.0.4-SNP', '3:e44199f37376b634cd4c5a0183e51900', 172);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-173::Jeff Caddel (generated)::(Checksum: 3:7a67ca7423c07ab2c3f9b4e30ac222a9)
ALTER TABLE `krim_pnd_dlgn_mbr_t` ADD PRIMARY KEY (`FDOC_NBR`, `DLGN_MBR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-173', '2.0.4-SNP', '3:7a67ca7423c07ab2c3f9b4e30ac222a9', 173);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-174::Jeff Caddel (generated)::(Checksum: 3:017d58c5e9e03cbaa2265be22e5bca10)
ALTER TABLE `krim_pnd_dlgn_t` ADD PRIMARY KEY (`FDOC_NBR`, `DLGN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-174', '2.0.4-SNP', '3:017d58c5e9e03cbaa2265be22e5bca10', 174);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-175::Jeff Caddel (generated)::(Checksum: 3:69f2a0eabfb7d62fd9b19b379d73e8d3)
ALTER TABLE `krim_pnd_email_mt` ADD PRIMARY KEY (`FDOC_NBR`, `ENTITY_EMAIL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-175', '2.0.4-SNP', '3:69f2a0eabfb7d62fd9b19b379d73e8d3', 175);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-176::Jeff Caddel (generated)::(Checksum: 3:58f762a768a2490291f7c531f1aa05de)
ALTER TABLE `krim_pnd_emp_info_mt` ADD PRIMARY KEY (`FDOC_NBR`, `ENTITY_EMP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-176', '2.0.4-SNP', '3:58f762a768a2490291f7c531f1aa05de', 176);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-177::Jeff Caddel (generated)::(Checksum: 3:01e5e1cd96e386084208b2ffe8edfbb5)
ALTER TABLE `krim_pnd_grp_attr_data_t` ADD PRIMARY KEY (`FDOC_NBR`, `ATTR_DATA_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-177', '2.0.4-SNP', '3:01e5e1cd96e386084208b2ffe8edfbb5', 177);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-178::Jeff Caddel (generated)::(Checksum: 3:7a5eab88d85512649f150641c25e3fee)
ALTER TABLE `krim_pnd_grp_mbr_t` ADD PRIMARY KEY (`FDOC_NBR`, `GRP_MBR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-178', '2.0.4-SNP', '3:7a5eab88d85512649f150641c25e3fee', 178);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-179::Jeff Caddel (generated)::(Checksum: 3:15d92e1f89d2bf1fa2a9a4e7f94115b4)
ALTER TABLE `krim_pnd_grp_prncpl_mt` ADD PRIMARY KEY (`GRP_MBR_ID`, `FDOC_NBR`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-179', '2.0.4-SNP', '3:15d92e1f89d2bf1fa2a9a4e7f94115b4', 179);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-180::Jeff Caddel (generated)::(Checksum: 3:32fd83710b9ec8ecdf6d8f0c87d6c882)
ALTER TABLE `krim_pnd_nm_mt` ADD PRIMARY KEY (`FDOC_NBR`, `ENTITY_NM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-180', '2.0.4-SNP', '3:32fd83710b9ec8ecdf6d8f0c87d6c882', 180);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-181::Jeff Caddel (generated)::(Checksum: 3:680d00a738025cac4465af12b056e386)
ALTER TABLE `krim_pnd_phone_mt` ADD PRIMARY KEY (`FDOC_NBR`, `ENTITY_PHONE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-181', '2.0.4-SNP', '3:680d00a738025cac4465af12b056e386', 181);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-182::Jeff Caddel (generated)::(Checksum: 3:ae0bd74dc49ef7c71a03642370162413)
ALTER TABLE `krim_pnd_priv_pref_mt` ADD PRIMARY KEY (`FDOC_NBR`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-182', '2.0.4-SNP', '3:ae0bd74dc49ef7c71a03642370162413', 182);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-183::Jeff Caddel (generated)::(Checksum: 3:81fd1b767e7c2487fcf602d03a54cdcb)
ALTER TABLE `krim_pnd_role_mbr_attr_data_mt` ADD PRIMARY KEY (`FDOC_NBR`, `ATTR_DATA_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-183', '2.0.4-SNP', '3:81fd1b767e7c2487fcf602d03a54cdcb', 183);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-184::Jeff Caddel (generated)::(Checksum: 3:13267de667644a73a1f180586d3eb056)
ALTER TABLE `krim_pnd_role_mbr_mt` ADD PRIMARY KEY (`FDOC_NBR`, `ROLE_MBR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-184', '2.0.4-SNP', '3:13267de667644a73a1f180586d3eb056', 184);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-185::Jeff Caddel (generated)::(Checksum: 3:1de741829749ceb36c3f56e1cfb9469d)
ALTER TABLE `krim_pnd_role_mt` ADD PRIMARY KEY (`FDOC_NBR`, `ROLE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-185', '2.0.4-SNP', '3:1de741829749ceb36c3f56e1cfb9469d', 185);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-186::Jeff Caddel (generated)::(Checksum: 3:a495050d0d2583904f696e83e04b4dea)
ALTER TABLE `krim_pnd_role_perm_t` ADD PRIMARY KEY (`FDOC_NBR`, `ROLE_PERM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-186', '2.0.4-SNP', '3:a495050d0d2583904f696e83e04b4dea', 186);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-187::Jeff Caddel (generated)::(Checksum: 3:92af01688679ac4570ba17293876da3c)
ALTER TABLE `krim_pnd_role_rsp_actn_mt` ADD PRIMARY KEY (`ROLE_RSP_ACTN_ID`, `FDOC_NBR`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-187', '2.0.4-SNP', '3:92af01688679ac4570ba17293876da3c', 187);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-188::Jeff Caddel (generated)::(Checksum: 3:6b4a83fef325b63ecb389f1fafd61701)
ALTER TABLE `krim_pnd_role_rsp_t` ADD PRIMARY KEY (`FDOC_NBR`, `ROLE_RSP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-188', '2.0.4-SNP', '3:6b4a83fef325b63ecb389f1fafd61701', 188);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-189::Jeff Caddel (generated)::(Checksum: 3:8bdafa412b124d8c8c51a648cd637611)
ALTER TABLE `krim_prncpl_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-189', '2.0.4-SNP', '3:8bdafa412b124d8c8c51a648cd637611', 189);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-190::Jeff Caddel (generated)::(Checksum: 3:4bf14b9a00d88b33cc70c8bac6d260d0)
ALTER TABLE `krim_prncpl_t` ADD PRIMARY KEY (`PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-190', '2.0.4-SNP', '3:4bf14b9a00d88b33cc70c8bac6d260d0', 190);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-191::Jeff Caddel (generated)::(Checksum: 3:a37f2336c5059dbf1f07552fc3ea7314)
ALTER TABLE `krim_role_document_t` ADD PRIMARY KEY (`FDOC_NBR`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-191', '2.0.4-SNP', '3:a37f2336c5059dbf1f07552fc3ea7314', 191);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-192::Jeff Caddel (generated)::(Checksum: 3:3610f7afc23932fd9c4718a56fab659d)
ALTER TABLE `krim_role_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-192', '2.0.4-SNP', '3:3610f7afc23932fd9c4718a56fab659d', 192);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-193::Jeff Caddel (generated)::(Checksum: 3:a7fa159235d66b3808733c62c88353a7)
ALTER TABLE `krim_role_mbr_attr_data_t` ADD PRIMARY KEY (`ATTR_DATA_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-193', '2.0.4-SNP', '3:a7fa159235d66b3808733c62c88353a7', 193);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-194::Jeff Caddel (generated)::(Checksum: 3:dc44275851a230b0a6a1027b4280828e)
ALTER TABLE `krim_role_mbr_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-194', '2.0.4-SNP', '3:dc44275851a230b0a6a1027b4280828e', 194);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-195::Jeff Caddel (generated)::(Checksum: 3:c0889801ecaf393450fd6ff68eba0c77)
ALTER TABLE `krim_role_mbr_t` ADD PRIMARY KEY (`ROLE_MBR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-195', '2.0.4-SNP', '3:c0889801ecaf393450fd6ff68eba0c77', 195);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-196::Jeff Caddel (generated)::(Checksum: 3:1626ddb7133cb31e1601d9e1814973cd)
ALTER TABLE `krim_role_perm_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-196', '2.0.4-SNP', '3:1626ddb7133cb31e1601d9e1814973cd', 196);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-197::Jeff Caddel (generated)::(Checksum: 3:06cb681748eca52c18393baad306da82)
ALTER TABLE `krim_role_perm_t` ADD PRIMARY KEY (`ROLE_PERM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-197', '2.0.4-SNP', '3:06cb681748eca52c18393baad306da82', 197);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-198::Jeff Caddel (generated)::(Checksum: 3:3d197ce2c7c82586b40277badba4d328)
ALTER TABLE `krim_role_rsp_actn_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-198', '2.0.4-SNP', '3:3d197ce2c7c82586b40277badba4d328', 198);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-199::Jeff Caddel (generated)::(Checksum: 3:5fc44c4b134014d043194daef3551483)
ALTER TABLE `krim_role_rsp_actn_t` ADD PRIMARY KEY (`ROLE_RSP_ACTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-199', '2.0.4-SNP', '3:5fc44c4b134014d043194daef3551483', 199);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-200::Jeff Caddel (generated)::(Checksum: 3:833f1aa477068f4ad28d9ec1ae9a7a1b)
ALTER TABLE `krim_role_rsp_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-200', '2.0.4-SNP', '3:833f1aa477068f4ad28d9ec1ae9a7a1b', 200);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-201::Jeff Caddel (generated)::(Checksum: 3:04e2e364666b45a27f28ab82c1063682)
ALTER TABLE `krim_role_rsp_t` ADD PRIMARY KEY (`ROLE_RSP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-201', '2.0.4-SNP', '3:04e2e364666b45a27f28ab82c1063682', 201);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-202::Jeff Caddel (generated)::(Checksum: 3:a5378ff9183a64bf85c2c0a4070cb159)
ALTER TABLE `krim_role_t` ADD PRIMARY KEY (`ROLE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-202', '2.0.4-SNP', '3:a5378ff9183a64bf85c2c0a4070cb159', 202);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-203::Jeff Caddel (generated)::(Checksum: 3:df5c38eaea724a01d742e30f76df043a)
ALTER TABLE `krim_rsp_attr_data_t` ADD PRIMARY KEY (`ATTR_DATA_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-203', '2.0.4-SNP', '3:df5c38eaea724a01d742e30f76df043a', 203);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-204::Jeff Caddel (generated)::(Checksum: 3:6bd0ec4bebf7ec42a96d1a8b04c5784e)
ALTER TABLE `krim_rsp_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-204', '2.0.4-SNP', '3:6bd0ec4bebf7ec42a96d1a8b04c5784e', 204);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-205::Jeff Caddel (generated)::(Checksum: 3:d42b9a00b6c9a7dc20419612f67c1265)
ALTER TABLE `krim_rsp_rqrd_attr_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-205', '2.0.4-SNP', '3:d42b9a00b6c9a7dc20419612f67c1265', 205);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-206::Jeff Caddel (generated)::(Checksum: 3:ecfcfd8c27485b9a81c255a85d09e78c)
ALTER TABLE `krim_rsp_t` ADD PRIMARY KEY (`RSP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-206', '2.0.4-SNP', '3:ecfcfd8c27485b9a81c255a85d09e78c', 206);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-207::Jeff Caddel (generated)::(Checksum: 3:662e2d87c4af54c174c612ee15aa4f28)
ALTER TABLE `krim_rsp_tmpl_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-207', '2.0.4-SNP', '3:662e2d87c4af54c174c612ee15aa4f28', 207);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-208::Jeff Caddel (generated)::(Checksum: 3:4f05ea98dd05c39c8ebd1da2a3868c1d)
ALTER TABLE `krim_rsp_tmpl_t` ADD PRIMARY KEY (`RSP_TMPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-208', '2.0.4-SNP', '3:4f05ea98dd05c39c8ebd1da2a3868c1d', 208);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-209::Jeff Caddel (generated)::(Checksum: 3:3d4de23b9fab979a691da0895dcb6287)
ALTER TABLE `krim_typ_attr_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-209', '2.0.4-SNP', '3:3d4de23b9fab979a691da0895dcb6287', 209);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-210::Jeff Caddel (generated)::(Checksum: 3:38e14b78e4c3d9678668d6b1a860a010)
ALTER TABLE `krim_typ_attr_t` ADD PRIMARY KEY (`KIM_TYP_ATTR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-210', '2.0.4-SNP', '3:38e14b78e4c3d9678668d6b1a860a010', 210);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-211::Jeff Caddel (generated)::(Checksum: 3:5fc6a95703175ac5bf8387ac8a2060ac)
ALTER TABLE `krim_typ_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-211', '2.0.4-SNP', '3:5fc6a95703175ac5bf8387ac8a2060ac', 211);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-212::Jeff Caddel (generated)::(Checksum: 3:1ed1c24f9371f65abf3c7016592e2da9)
ALTER TABLE `krim_typ_t` ADD PRIMARY KEY (`KIM_TYP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-212', '2.0.4-SNP', '3:1ed1c24f9371f65abf3c7016592e2da9', 212);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-213::Jeff Caddel (generated)::(Checksum: 3:d196402e852af1c141d0288b7b147de1)
ALTER TABLE `krlc_cmp_t` ADD PRIMARY KEY (`CAMPUS_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-213', '2.0.4-SNP', '3:d196402e852af1c141d0288b7b147de1', 213);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-214::Jeff Caddel (generated)::(Checksum: 3:9984efe6e41b30d110acee74b483f6b6)
ALTER TABLE `krlc_cmp_typ_t` ADD PRIMARY KEY (`CAMPUS_TYP_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-214', '2.0.4-SNP', '3:9984efe6e41b30d110acee74b483f6b6', 214);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-215::Jeff Caddel (generated)::(Checksum: 3:1b53d3a212742abf90a92f15297df068)
ALTER TABLE `krlc_cntry_t` ADD PRIMARY KEY (`POSTAL_CNTRY_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-215', '2.0.4-SNP', '3:1b53d3a212742abf90a92f15297df068', 215);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-216::Jeff Caddel (generated)::(Checksum: 3:25fd5bec2cd50718f27f0d7ac276fe48)
ALTER TABLE `krlc_cnty_t` ADD PRIMARY KEY (`COUNTY_CD`, `STATE_CD`, `POSTAL_CNTRY_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-216', '2.0.4-SNP', '3:25fd5bec2cd50718f27f0d7ac276fe48', 216);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-217::Jeff Caddel (generated)::(Checksum: 3:368821ccc7ef17504cfe27c7e0531fa7)
ALTER TABLE `krlc_pstl_cd_t` ADD PRIMARY KEY (`POSTAL_CD`, `POSTAL_CNTRY_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-217', '2.0.4-SNP', '3:368821ccc7ef17504cfe27c7e0531fa7', 217);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-218::Jeff Caddel (generated)::(Checksum: 3:cd21a3a09dff206293225a4812f065fb)
ALTER TABLE `krlc_st_t` ADD PRIMARY KEY (`POSTAL_STATE_CD`, `POSTAL_CNTRY_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-218', '2.0.4-SNP', '3:cd21a3a09dff206293225a4812f065fb', 218);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-219::Jeff Caddel (generated)::(Checksum: 3:37566c7fb635c9be6698b74c0cd01daa)
ALTER TABLE `krms_actn_attr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-219', '2.0.4-SNP', '3:37566c7fb635c9be6698b74c0cd01daa', 219);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-220::Jeff Caddel (generated)::(Checksum: 3:4a65d80637b4750fb295dec9bf038088)
ALTER TABLE `krms_actn_attr_t` ADD PRIMARY KEY (`ACTN_ATTR_DATA_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-220', '2.0.4-SNP', '3:4a65d80637b4750fb295dec9bf038088', 220);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-221::Jeff Caddel (generated)::(Checksum: 3:db6fb23ead468599bd98c5c91699d682)
ALTER TABLE `krms_actn_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-221', '2.0.4-SNP', '3:db6fb23ead468599bd98c5c91699d682', 221);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-222::Jeff Caddel (generated)::(Checksum: 3:5e5a129833ace0925e6762df7276f667)
ALTER TABLE `krms_actn_t` ADD PRIMARY KEY (`ACTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-222', '2.0.4-SNP', '3:5e5a129833ace0925e6762df7276f667', 222);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-223::Jeff Caddel (generated)::(Checksum: 3:5183adfe3288a69ddc8402fe3d0e02d4)
ALTER TABLE `krms_agenda_attr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-223', '2.0.4-SNP', '3:5183adfe3288a69ddc8402fe3d0e02d4', 223);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-224::Jeff Caddel (generated)::(Checksum: 3:6ca36df1fa27227f1d37009aa87560f0)
ALTER TABLE `krms_agenda_attr_t` ADD PRIMARY KEY (`AGENDA_ATTR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-224', '2.0.4-SNP', '3:6ca36df1fa27227f1d37009aa87560f0', 224);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-225::Jeff Caddel (generated)::(Checksum: 3:008f89e132405bb66d4b577a83036128)
ALTER TABLE `krms_agenda_itm_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-225', '2.0.4-SNP', '3:008f89e132405bb66d4b577a83036128', 225);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-226::Jeff Caddel (generated)::(Checksum: 3:e05d3210cfff4e0519594881ba77d9ea)
ALTER TABLE `krms_agenda_itm_t` ADD PRIMARY KEY (`AGENDA_ITM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-226', '2.0.4-SNP', '3:e05d3210cfff4e0519594881ba77d9ea', 226);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-227::Jeff Caddel (generated)::(Checksum: 3:75604583e5abf5970dfb8a4f8ca8f2b0)
ALTER TABLE `krms_agenda_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-227', '2.0.4-SNP', '3:75604583e5abf5970dfb8a4f8ca8f2b0', 227);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-228::Jeff Caddel (generated)::(Checksum: 3:a0023441def4385bd614e47208626d49)
ALTER TABLE `krms_agenda_t` ADD PRIMARY KEY (`AGENDA_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-228', '2.0.4-SNP', '3:a0023441def4385bd614e47208626d49', 228);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-229::Jeff Caddel (generated)::(Checksum: 3:3e82e039267bd42aa30ebf334ad67492)
ALTER TABLE `krms_attr_defn_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-229', '2.0.4-SNP', '3:3e82e039267bd42aa30ebf334ad67492', 229);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-230::Jeff Caddel (generated)::(Checksum: 3:55f43180f97bbf39ed23c2c780223158)
ALTER TABLE `krms_attr_defn_t` ADD PRIMARY KEY (`ATTR_DEFN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-230', '2.0.4-SNP', '3:55f43180f97bbf39ed23c2c780223158', 230);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-231::Jeff Caddel (generated)::(Checksum: 3:9581e119cb4272b241c544cda25a9cdb)
ALTER TABLE `krms_cmpnd_prop_props_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-231', '2.0.4-SNP', '3:9581e119cb4272b241c544cda25a9cdb', 231);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-232::Jeff Caddel (generated)::(Checksum: 3:5c904659646a240054cd7843e2313d7b)
ALTER TABLE `krms_cmpnd_prop_props_t` ADD PRIMARY KEY (`CMPND_PROP_ID`, `PROP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-232', '2.0.4-SNP', '3:5c904659646a240054cd7843e2313d7b', 232);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-233::Jeff Caddel (generated)::(Checksum: 3:5035f7037cfd81bb953ce932de2b935b)
ALTER TABLE `krms_cntxt_attr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-233', '2.0.4-SNP', '3:5035f7037cfd81bb953ce932de2b935b', 233);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-234::Jeff Caddel (generated)::(Checksum: 3:3d6794e4ec53b174083436e081cbe2cf)
ALTER TABLE `krms_cntxt_attr_t` ADD PRIMARY KEY (`CNTXT_ATTR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-234', '2.0.4-SNP', '3:3d6794e4ec53b174083436e081cbe2cf', 234);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-235::Jeff Caddel (generated)::(Checksum: 3:1c72fd1f4d22da84fb4781eddaf4adbe)
ALTER TABLE `krms_cntxt_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-235', '2.0.4-SNP', '3:1c72fd1f4d22da84fb4781eddaf4adbe', 235);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-236::Jeff Caddel (generated)::(Checksum: 3:255704ad819e1cc54461d1d8e83dca5a)
ALTER TABLE `krms_cntxt_t` ADD PRIMARY KEY (`CNTXT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-236', '2.0.4-SNP', '3:255704ad819e1cc54461d1d8e83dca5a', 236);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-237::Jeff Caddel (generated)::(Checksum: 3:9d7d01516bf6ef0902db37d342468dd4)
ALTER TABLE `krms_cntxt_term_spec_prereq_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-237', '2.0.4-SNP', '3:9d7d01516bf6ef0902db37d342468dd4', 237);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-238::Jeff Caddel (generated)::(Checksum: 3:f2160fe3f112c3ddb0a503c3ff1e6070)
ALTER TABLE `krms_cntxt_vld_actn_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-238', '2.0.4-SNP', '3:f2160fe3f112c3ddb0a503c3ff1e6070', 238);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-239::Jeff Caddel (generated)::(Checksum: 3:ab03971d3d2f10e13c64fb6f45f14465)
ALTER TABLE `krms_cntxt_vld_actn_t` ADD PRIMARY KEY (`CNTXT_VLD_ACTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-239', '2.0.4-SNP', '3:ab03971d3d2f10e13c64fb6f45f14465', 239);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-240::Jeff Caddel (generated)::(Checksum: 3:68c9c6aa80cefc7a26979cc7755ba90d)
ALTER TABLE `krms_cntxt_vld_event_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-240', '2.0.4-SNP', '3:68c9c6aa80cefc7a26979cc7755ba90d', 240);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-241::Jeff Caddel (generated)::(Checksum: 3:6c507f9b6db647455b9bdab79a186dd1)
ALTER TABLE `krms_cntxt_vld_event_t` ADD PRIMARY KEY (`CNTXT_VLD_EVENT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-241', '2.0.4-SNP', '3:6c507f9b6db647455b9bdab79a186dd1', 241);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-242::Jeff Caddel (generated)::(Checksum: 3:7fed46dcaa83da8a6e8c3f0f3bf1e011)
ALTER TABLE `krms_cntxt_vld_func_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-242', '2.0.4-SNP', '3:7fed46dcaa83da8a6e8c3f0f3bf1e011', 242);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-243::Jeff Caddel (generated)::(Checksum: 3:1f500872080c3c73c1490a2b67e57f05)
ALTER TABLE `krms_cntxt_vld_func_t` ADD PRIMARY KEY (`CNTXT_VLD_FUNC_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-243', '2.0.4-SNP', '3:1f500872080c3c73c1490a2b67e57f05', 243);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-244::Jeff Caddel (generated)::(Checksum: 3:19f65250e02a69f9ca0d8d4a0db9bda9)
ALTER TABLE `krms_cntxt_vld_rule_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-244', '2.0.4-SNP', '3:19f65250e02a69f9ca0d8d4a0db9bda9', 244);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-245::Jeff Caddel (generated)::(Checksum: 3:2ab5c5e3406e997fd01a3bd8f6a9d53a)
ALTER TABLE `krms_cntxt_vld_rule_t` ADD PRIMARY KEY (`CNTXT_VLD_RULE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-245', '2.0.4-SNP', '3:2ab5c5e3406e997fd01a3bd8f6a9d53a', 245);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-246::Jeff Caddel (generated)::(Checksum: 3:58db704c4e3e75538eae125bb0788894)
ALTER TABLE `krms_cntxt_vld_term_spec_t` ADD PRIMARY KEY (`CNTXT_TERM_SPEC_PREREQ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-246', '2.0.4-SNP', '3:58db704c4e3e75538eae125bb0788894', 246);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-247::Jeff Caddel (generated)::(Checksum: 3:98a4afd9b5459a081308935bb2e37ebd)
ALTER TABLE `krms_ctgry_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-247', '2.0.4-SNP', '3:98a4afd9b5459a081308935bb2e37ebd', 247);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-248::Jeff Caddel (generated)::(Checksum: 3:167dba7dca2527cfe8a274da6df6bdb6)
ALTER TABLE `krms_ctgry_t` ADD PRIMARY KEY (`CTGRY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-248', '2.0.4-SNP', '3:167dba7dca2527cfe8a274da6df6bdb6', 248);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-249::Jeff Caddel (generated)::(Checksum: 3:debe80189d88ad9c20f883b5df5f33a2)
ALTER TABLE `krms_func_ctgry_t` ADD PRIMARY KEY (`FUNC_ID`, `CTGRY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-249', '2.0.4-SNP', '3:debe80189d88ad9c20f883b5df5f33a2', 249);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-250::Jeff Caddel (generated)::(Checksum: 3:bd0ea4897009d2f77fb2c50b5a04cdf5)
ALTER TABLE `krms_func_parm_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-250', '2.0.4-SNP', '3:bd0ea4897009d2f77fb2c50b5a04cdf5', 250);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-251::Jeff Caddel (generated)::(Checksum: 3:fa393f9afbc7ecffbbf95c53e0e2b741)
ALTER TABLE `krms_func_parm_t` ADD PRIMARY KEY (`FUNC_PARM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-251', '2.0.4-SNP', '3:fa393f9afbc7ecffbbf95c53e0e2b741', 251);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-252::Jeff Caddel (generated)::(Checksum: 3:5ca8c04bede1c1f3a6b43e11167a2dca)
ALTER TABLE `krms_func_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-252', '2.0.4-SNP', '3:5ca8c04bede1c1f3a6b43e11167a2dca', 252);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-253::Jeff Caddel (generated)::(Checksum: 3:a6226a195ad4a7d725af1b5a3614d530)
ALTER TABLE `krms_func_t` ADD PRIMARY KEY (`FUNC_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-253', '2.0.4-SNP', '3:a6226a195ad4a7d725af1b5a3614d530', 253);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-254::Jeff Caddel (generated)::(Checksum: 3:ff27b005151c699df6ccaab54433bfcb)
ALTER TABLE `krms_prop_parm_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-254', '2.0.4-SNP', '3:ff27b005151c699df6ccaab54433bfcb', 254);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-255::Jeff Caddel (generated)::(Checksum: 3:38d163c4197828dc37fe61c916ed8936)
ALTER TABLE `krms_prop_parm_t` ADD PRIMARY KEY (`PROP_PARM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-255', '2.0.4-SNP', '3:38d163c4197828dc37fe61c916ed8936', 255);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-256::Jeff Caddel (generated)::(Checksum: 3:892f186842862d9c212fded68e40a3a4)
ALTER TABLE `krms_prop_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-256', '2.0.4-SNP', '3:892f186842862d9c212fded68e40a3a4', 256);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-257::Jeff Caddel (generated)::(Checksum: 3:3d2ae3ac19c01414134fcc60ab0c805a)
ALTER TABLE `krms_prop_t` ADD PRIMARY KEY (`PROP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-257', '2.0.4-SNP', '3:3d2ae3ac19c01414134fcc60ab0c805a', 257);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-258::Jeff Caddel (generated)::(Checksum: 3:240a015137127a347b0ecfd8605f8a1f)
ALTER TABLE `krms_rule_attr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-258', '2.0.4-SNP', '3:240a015137127a347b0ecfd8605f8a1f', 258);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-259::Jeff Caddel (generated)::(Checksum: 3:b228c2c414a0a2e320483ada6e2c83aa)
ALTER TABLE `krms_rule_attr_t` ADD PRIMARY KEY (`RULE_ATTR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-259', '2.0.4-SNP', '3:b228c2c414a0a2e320483ada6e2c83aa', 259);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-260::Jeff Caddel (generated)::(Checksum: 3:fefcc7c8ceaf0e7b6bbcea2994a40882)
ALTER TABLE `krms_rule_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-260', '2.0.4-SNP', '3:fefcc7c8ceaf0e7b6bbcea2994a40882', 260);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-261::Jeff Caddel (generated)::(Checksum: 3:77cd37506ab494b5b226e7b6e6069fb3)
ALTER TABLE `krms_rule_t` ADD PRIMARY KEY (`RULE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-261', '2.0.4-SNP', '3:77cd37506ab494b5b226e7b6e6069fb3', 261);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-262::Jeff Caddel (generated)::(Checksum: 3:484f64d8862add9b28ece586e6cf2f0b)
ALTER TABLE `krms_term_parm_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-262', '2.0.4-SNP', '3:484f64d8862add9b28ece586e6cf2f0b', 262);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-263::Jeff Caddel (generated)::(Checksum: 3:fbde37c83bcfe33a7b06d6a1a8aac4e0)
ALTER TABLE `krms_term_parm_t` ADD PRIMARY KEY (`TERM_PARM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-263', '2.0.4-SNP', '3:fbde37c83bcfe33a7b06d6a1a8aac4e0', 263);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-264::Jeff Caddel (generated)::(Checksum: 3:ebc0ccba9670184cf2ba66ddd067b83b)
ALTER TABLE `krms_term_rslvr_attr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-264', '2.0.4-SNP', '3:ebc0ccba9670184cf2ba66ddd067b83b', 264);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-265::Jeff Caddel (generated)::(Checksum: 3:fc04dd8fbf0a7fdeb0218575e2e17cf2)
ALTER TABLE `krms_term_rslvr_attr_t` ADD PRIMARY KEY (`TERM_RSLVR_ATTR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-265', '2.0.4-SNP', '3:fc04dd8fbf0a7fdeb0218575e2e17cf2', 265);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-266::Jeff Caddel (generated)::(Checksum: 3:0b923b8fc8a285dfd5bfa10fa3e2c67b)
ALTER TABLE `krms_term_rslvr_input_spec_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-266', '2.0.4-SNP', '3:0b923b8fc8a285dfd5bfa10fa3e2c67b', 266);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-267::Jeff Caddel (generated)::(Checksum: 3:8301d3f6d289625c9b571ea9e0af29e0)
ALTER TABLE `krms_term_rslvr_input_spec_t` ADD PRIMARY KEY (`TERM_SPEC_ID`, `TERM_RSLVR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-267', '2.0.4-SNP', '3:8301d3f6d289625c9b571ea9e0af29e0', 267);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-268::Jeff Caddel (generated)::(Checksum: 3:03df474c727e40661dfb2f491a539fd0)
ALTER TABLE `krms_term_rslvr_parm_spec_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-268', '2.0.4-SNP', '3:03df474c727e40661dfb2f491a539fd0', 268);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-269::Jeff Caddel (generated)::(Checksum: 3:6fc17ad7b77a68b40e6aa8851b9edc82)
ALTER TABLE `krms_term_rslvr_parm_spec_t` ADD PRIMARY KEY (`TERM_RSLVR_PARM_SPEC_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-269', '2.0.4-SNP', '3:6fc17ad7b77a68b40e6aa8851b9edc82', 269);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-270::Jeff Caddel (generated)::(Checksum: 3:acfca5023a173b08ffcb70aea0d46ad8)
ALTER TABLE `krms_term_rslvr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-270', '2.0.4-SNP', '3:acfca5023a173b08ffcb70aea0d46ad8', 270);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-271::Jeff Caddel (generated)::(Checksum: 3:d3e3a0b8386609edfce757cb2fcb9850)
ALTER TABLE `krms_term_rslvr_t` ADD PRIMARY KEY (`TERM_RSLVR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-271', '2.0.4-SNP', '3:d3e3a0b8386609edfce757cb2fcb9850', 271);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-272::Jeff Caddel (generated)::(Checksum: 3:b6917693f82228325d33d9aab5103208)
ALTER TABLE `krms_term_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-272', '2.0.4-SNP', '3:b6917693f82228325d33d9aab5103208', 272);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-273::Jeff Caddel (generated)::(Checksum: 3:7a42ece28fff5cfeadd3fd15671de021)
ALTER TABLE `krms_term_spec_ctgry_t` ADD PRIMARY KEY (`TERM_SPEC_ID`, `CTGRY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-273', '2.0.4-SNP', '3:7a42ece28fff5cfeadd3fd15671de021', 273);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-274::Jeff Caddel (generated)::(Checksum: 3:5a8ad8259871fd1c0ae46d3d45e7738b)
ALTER TABLE `krms_term_spec_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-274', '2.0.4-SNP', '3:5a8ad8259871fd1c0ae46d3d45e7738b', 274);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-275::Jeff Caddel (generated)::(Checksum: 3:32aa8ca76b5150db8003656bcb89bfa7)
ALTER TABLE `krms_term_spec_t` ADD PRIMARY KEY (`TERM_SPEC_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-275', '2.0.4-SNP', '3:32aa8ca76b5150db8003656bcb89bfa7', 275);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-276::Jeff Caddel (generated)::(Checksum: 3:17b2a6ca33c97fd7f87e1a71991764e6)
ALTER TABLE `krms_term_t` ADD PRIMARY KEY (`TERM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-276', '2.0.4-SNP', '3:17b2a6ca33c97fd7f87e1a71991764e6', 276);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-277::Jeff Caddel (generated)::(Checksum: 3:a3ad79ce2d4853ab9e0e450f9ea1ea52)
ALTER TABLE `krms_typ_attr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-277', '2.0.4-SNP', '3:a3ad79ce2d4853ab9e0e450f9ea1ea52', 277);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-278::Jeff Caddel (generated)::(Checksum: 3:87048d3da603fe349f3f7055df61b8ca)
ALTER TABLE `krms_typ_attr_t` ADD PRIMARY KEY (`TYP_ATTR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-278', '2.0.4-SNP', '3:87048d3da603fe349f3f7055df61b8ca', 278);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-279::Jeff Caddel (generated)::(Checksum: 3:8f31556b6a3f4fa8571d3027a06d7460)
ALTER TABLE `krms_typ_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-279', '2.0.4-SNP', '3:8f31556b6a3f4fa8571d3027a06d7460', 279);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-280::Jeff Caddel (generated)::(Checksum: 3:946f204dca5972e8dd718a55c60fbcf5)
ALTER TABLE `krms_typ_t` ADD PRIMARY KEY (`TYP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-280', '2.0.4-SNP', '3:946f204dca5972e8dd718a55c60fbcf5', 280);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-281::Jeff Caddel (generated)::(Checksum: 3:b70e5ada92683fef92ccb704d0f94dc6)
ALTER TABLE `krns_adhoc_rte_actn_recip_t` ADD PRIMARY KEY (`RECIP_TYP_CD`, `ACTN_RQST_CD`, `ACTN_RQST_RECIP_ID`, `DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-281', '2.0.4-SNP', '3:b70e5ada92683fef92ccb704d0f94dc6', 281);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-282::Jeff Caddel (generated)::(Checksum: 3:f023722591a2f448a880997f4ed98672)
ALTER TABLE `krns_att_t` ADD PRIMARY KEY (`NTE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-282', '2.0.4-SNP', '3:f023722591a2f448a880997f4ed98672', 282);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-283::Jeff Caddel (generated)::(Checksum: 3:df00b821c7113c8f8e33485f4f94757e)
ALTER TABLE `krns_doc_hdr_t` ADD PRIMARY KEY (`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-283', '2.0.4-SNP', '3:df00b821c7113c8f8e33485f4f94757e', 283);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-284::Jeff Caddel (generated)::(Checksum: 3:af7e9e9693de631e0e743913fddfc483)
ALTER TABLE `krns_doc_typ_attr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-284', '2.0.4-SNP', '3:af7e9e9693de631e0e743913fddfc483', 284);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-285::Jeff Caddel (generated)::(Checksum: 3:e63331cf5ca203778d3efd28d0f29a3f)
ALTER TABLE `krns_lock_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-285', '2.0.4-SNP', '3:e63331cf5ca203778d3efd28d0f29a3f', 285);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-286::Jeff Caddel (generated)::(Checksum: 3:bf5df0447e7279ff04e59bb193c74b22)
ALTER TABLE `krns_lookup_rslt_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-286', '2.0.4-SNP', '3:bf5df0447e7279ff04e59bb193c74b22', 286);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-287::Jeff Caddel (generated)::(Checksum: 3:609b1de112ba8d5197ea1caaa1d5470a)
ALTER TABLE `krns_lookup_rslt_t` ADD PRIMARY KEY (`LOOKUP_RSLT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-287', '2.0.4-SNP', '3:609b1de112ba8d5197ea1caaa1d5470a', 287);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-288::Jeff Caddel (generated)::(Checksum: 3:2ff63f3e520b98bc0057b90bd4da2990)
ALTER TABLE `krns_lookup_sel_t` ADD PRIMARY KEY (`LOOKUP_RSLT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-288', '2.0.4-SNP', '3:2ff63f3e520b98bc0057b90bd4da2990', 288);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-289::Jeff Caddel (generated)::(Checksum: 3:e1cc5ec0193938fc747f753e38bea751)
ALTER TABLE `krns_maint_doc_att_t` ADD PRIMARY KEY (`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-289', '2.0.4-SNP', '3:e1cc5ec0193938fc747f753e38bea751', 289);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-290::Jeff Caddel (generated)::(Checksum: 3:30ba5b3aff0afd2de8225941bc483779)
ALTER TABLE `krns_maint_doc_t` ADD PRIMARY KEY (`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-290', '2.0.4-SNP', '3:30ba5b3aff0afd2de8225941bc483779', 290);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-291::Jeff Caddel (generated)::(Checksum: 3:7769f3b792ac0573d8fc70d40eee1ff0)
ALTER TABLE `krns_maint_lock_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-291', '2.0.4-SNP', '3:7769f3b792ac0573d8fc70d40eee1ff0', 291);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-292::Jeff Caddel (generated)::(Checksum: 3:baf998df586c86cb2cac8cedaee62bba)
ALTER TABLE `krns_maint_lock_t` ADD PRIMARY KEY (`MAINT_LOCK_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-292', '2.0.4-SNP', '3:baf998df586c86cb2cac8cedaee62bba', 292);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-293::Jeff Caddel (generated)::(Checksum: 3:89e40714e9bfe5e85a7a947f6bef895c)
ALTER TABLE `krns_nte_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-293', '2.0.4-SNP', '3:89e40714e9bfe5e85a7a947f6bef895c', 293);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-294::Jeff Caddel (generated)::(Checksum: 3:cf6bb9e136f5ca8e8107342726b82b96)
ALTER TABLE `krns_nte_t` ADD PRIMARY KEY (`NTE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-294', '2.0.4-SNP', '3:cf6bb9e136f5ca8e8107342726b82b96', 294);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-295::Jeff Caddel (generated)::(Checksum: 3:0baec9aff009a26a412d957b6a334508)
ALTER TABLE `krns_nte_typ_t` ADD PRIMARY KEY (`NTE_TYP_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-295', '2.0.4-SNP', '3:0baec9aff009a26a412d957b6a334508', 295);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-296::Jeff Caddel (generated)::(Checksum: 3:e79d80b92d085e2a3ac24197dce5bdf4)
ALTER TABLE `krns_pessimistic_lock_t` ADD PRIMARY KEY (`PESSIMISTIC_LOCK_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-296', '2.0.4-SNP', '3:e79d80b92d085e2a3ac24197dce5bdf4', 296);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-297::Jeff Caddel (generated)::(Checksum: 3:59a7f21883399ef22c1ed7817fd9fdb3)
ALTER TABLE `krns_sesn_doc_t` ADD PRIMARY KEY (`SESN_DOC_ID`, `DOC_HDR_ID`, `PRNCPL_ID`, `IP_ADDR`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-297', '2.0.4-SNP', '3:59a7f21883399ef22c1ed7817fd9fdb3', 297);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-298::Jeff Caddel (generated)::(Checksum: 3:681ad2440e8c07e91808a32eafaa0ce4)
ALTER TABLE `krsb_bam_parm_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-298', '2.0.4-SNP', '3:681ad2440e8c07e91808a32eafaa0ce4', 298);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-299::Jeff Caddel (generated)::(Checksum: 3:d8cc7031aeb0721ab680d83e128be14b)
ALTER TABLE `krsb_bam_parm_t` ADD PRIMARY KEY (`BAM_PARM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-299', '2.0.4-SNP', '3:d8cc7031aeb0721ab680d83e128be14b', 299);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-300::Jeff Caddel (generated)::(Checksum: 3:9378d0726684798c558a697134c2fff3)
ALTER TABLE `krsb_bam_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-300', '2.0.4-SNP', '3:9378d0726684798c558a697134c2fff3', 300);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-301::Jeff Caddel (generated)::(Checksum: 3:bfa7db0b9aa732db8547f2f560362421)
ALTER TABLE `krsb_bam_t` ADD PRIMARY KEY (`BAM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-301', '2.0.4-SNP', '3:bfa7db0b9aa732db8547f2f560362421', 301);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-302::Jeff Caddel (generated)::(Checksum: 3:bb83abf0f108273a354d451d843e3061)
ALTER TABLE `krsb_msg_pyld_t` ADD PRIMARY KEY (`MSG_QUE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-302', '2.0.4-SNP', '3:bb83abf0f108273a354d451d843e3061', 302);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-303::Jeff Caddel (generated)::(Checksum: 3:5d235d82c102f2069c57eaae2effffc5)
ALTER TABLE `krsb_msg_que_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-303', '2.0.4-SNP', '3:5d235d82c102f2069c57eaae2effffc5', 303);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-304::Jeff Caddel (generated)::(Checksum: 3:acb6d6d4b01a61dd04d75afb8ce08c59)
ALTER TABLE `krsb_msg_que_t` ADD PRIMARY KEY (`MSG_QUE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-304', '2.0.4-SNP', '3:acb6d6d4b01a61dd04d75afb8ce08c59', 304);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-305::Jeff Caddel (generated)::(Checksum: 3:bde6284954f5740357cb1c01123cf9c5)
ALTER TABLE `krsb_qrtz_blob_triggers` ADD PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-305', '2.0.4-SNP', '3:bde6284954f5740357cb1c01123cf9c5', 305);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-306::Jeff Caddel (generated)::(Checksum: 3:d73c1fb45b4bbde4ba633abe12566b79)
ALTER TABLE `krsb_qrtz_calendars` ADD PRIMARY KEY (`CALENDAR_NAME`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-306', '2.0.4-SNP', '3:d73c1fb45b4bbde4ba633abe12566b79', 306);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-307::Jeff Caddel (generated)::(Checksum: 3:2db00a9688814eebdc76af04c03ad307)
ALTER TABLE `krsb_qrtz_cron_triggers` ADD PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-307', '2.0.4-SNP', '3:2db00a9688814eebdc76af04c03ad307', 307);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-308::Jeff Caddel (generated)::(Checksum: 3:86f5cf0a6e57e0d54d725054c99db510)
ALTER TABLE `krsb_qrtz_fired_triggers` ADD PRIMARY KEY (`ENTRY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-308', '2.0.4-SNP', '3:86f5cf0a6e57e0d54d725054c99db510', 308);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-309::Jeff Caddel (generated)::(Checksum: 3:f319e6fcc7654ac4fbc28d522093100d)
ALTER TABLE `krsb_qrtz_job_details` ADD PRIMARY KEY (`JOB_NAME`, `JOB_GROUP`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-309', '2.0.4-SNP', '3:f319e6fcc7654ac4fbc28d522093100d', 309);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-310::Jeff Caddel (generated)::(Checksum: 3:c1b2c801f3de28cdf07629e4e2785448)
ALTER TABLE `krsb_qrtz_job_listeners` ADD PRIMARY KEY (`JOB_NAME`, `JOB_GROUP`, `JOB_LISTENER`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-310', '2.0.4-SNP', '3:c1b2c801f3de28cdf07629e4e2785448', 310);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-311::Jeff Caddel (generated)::(Checksum: 3:d7dc30dd18689599d4da46ca259ed4f2)
ALTER TABLE `krsb_qrtz_locks` ADD PRIMARY KEY (`LOCK_NAME`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-311', '2.0.4-SNP', '3:d7dc30dd18689599d4da46ca259ed4f2', 311);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-312::Jeff Caddel (generated)::(Checksum: 3:ce767681e7b22827904e5c8e43d3bf84)
ALTER TABLE `krsb_qrtz_paused_trigger_grps` ADD PRIMARY KEY (`TRIGGER_GROUP`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-312', '2.0.4-SNP', '3:ce767681e7b22827904e5c8e43d3bf84', 312);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-313::Jeff Caddel (generated)::(Checksum: 3:9d34252622a5f4d6aef3be3d6bd1ae10)
ALTER TABLE `krsb_qrtz_scheduler_state` ADD PRIMARY KEY (`INSTANCE_NAME`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-313', '2.0.4-SNP', '3:9d34252622a5f4d6aef3be3d6bd1ae10', 313);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-314::Jeff Caddel (generated)::(Checksum: 3:b335d7517940900db9887bd5ef00ba85)
ALTER TABLE `krsb_qrtz_simple_triggers` ADD PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-314', '2.0.4-SNP', '3:b335d7517940900db9887bd5ef00ba85', 314);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-315::Jeff Caddel (generated)::(Checksum: 3:e2c4254bfd474fb6d2a80b02e20964e2)
ALTER TABLE `krsb_qrtz_trigger_listeners` ADD PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_LISTENER`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-315', '2.0.4-SNP', '3:e2c4254bfd474fb6d2a80b02e20964e2', 315);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-316::Jeff Caddel (generated)::(Checksum: 3:bdfba49086b70632271c3c13c6680798)
ALTER TABLE `krsb_qrtz_triggers` ADD PRIMARY KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-316', '2.0.4-SNP', '3:bdfba49086b70632271c3c13c6680798', 316);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-317::Jeff Caddel (generated)::(Checksum: 3:2692865bcb22b36e9ad8514ea092602c)
ALTER TABLE `krsb_svc_def_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-317', '2.0.4-SNP', '3:2692865bcb22b36e9ad8514ea092602c', 317);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-318::Jeff Caddel (generated)::(Checksum: 3:3be6e58cefa6860c47d8d4be7fa3fb0f)
ALTER TABLE `krsb_svc_def_t` ADD PRIMARY KEY (`SVC_DEF_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-318', '2.0.4-SNP', '3:3be6e58cefa6860c47d8d4be7fa3fb0f', 318);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-319::Jeff Caddel (generated)::(Checksum: 3:4426f68fce33568f1887ee2db5dda994)
ALTER TABLE `krsb_svc_dscrptr_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-319', '2.0.4-SNP', '3:4426f68fce33568f1887ee2db5dda994', 319);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-320::Jeff Caddel (generated)::(Checksum: 3:f61c47009f4b79a55da360445bb99461)
ALTER TABLE `krsb_svc_dscrptr_t` ADD PRIMARY KEY (`SVC_DSCRPTR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-320', '2.0.4-SNP', '3:f61c47009f4b79a55da360445bb99461', 320);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-321::Jeff Caddel (generated)::(Checksum: 3:3f02f5aa1a235f4e0abffcc54d9881b1)
ALTER TABLE `trav_doc_2_accounts` ADD PRIMARY KEY (`FDOC_NBR`, `ACCT_NUM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-321', '2.0.4-SNP', '3:3f02f5aa1a235f4e0abffcc54d9881b1', 321);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-322::Jeff Caddel (generated)::(Checksum: 3:52811e6fa17c2e764f405c8e36236e2a)
ALTER TABLE `trv_acct` ADD PRIMARY KEY (`ACCT_NUM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-322', '2.0.4-SNP', '3:52811e6fa17c2e764f405c8e36236e2a', 322);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-323::Jeff Caddel (generated)::(Checksum: 3:3a3d5464046d6ca3f1d46c070659eac2)
ALTER TABLE `trv_acct_ext` ADD PRIMARY KEY (`ACCT_NUM`, `ACCT_TYPE`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-323', '2.0.4-SNP', '3:3a3d5464046d6ca3f1d46c070659eac2', 323);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-324::Jeff Caddel (generated)::(Checksum: 3:8f64f11a6b4194fb785a88dcee8d0b71)
ALTER TABLE `trv_acct_fo` ADD PRIMARY KEY (`ACCT_FO_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-324', '2.0.4-SNP', '3:8f64f11a6b4194fb785a88dcee8d0b71', 324);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-325::Jeff Caddel (generated)::(Checksum: 3:dd7930ce927c36a2f1786aeb5aaff1e7)
ALTER TABLE `trv_acct_type` ADD PRIMARY KEY (`ACCT_TYPE`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-325', '2.0.4-SNP', '3:dd7930ce927c36a2f1786aeb5aaff1e7', 325);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-326::Jeff Caddel (generated)::(Checksum: 3:d053283c3e1777e4cfe309c1d75b8e60)
ALTER TABLE `trv_acct_use_rt_t` ADD PRIMARY KEY (`ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-326', '2.0.4-SNP', '3:d053283c3e1777e4cfe309c1d75b8e60', 326);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-327::Jeff Caddel (generated)::(Checksum: 3:c8ee5a26c94de25884847ee4ad9accc7)
ALTER TABLE `trv_doc_2` ADD PRIMARY KEY (`FDOC_NBR`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-327', '2.0.4-SNP', '3:c8ee5a26c94de25884847ee4ad9accc7', 327);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-328::Jeff Caddel (generated)::(Checksum: 3:94c4492fb17e1e836bf6afa30f4f4ef2)
ALTER TABLE `trv_doc_acct` ADD PRIMARY KEY (`DOC_HDR_ID`, `ACCT_NUM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-328', '2.0.4-SNP', '3:94c4492fb17e1e836bf6afa30f4f4ef2', 328);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-329::Jeff Caddel (generated)::(Checksum: 3:0928825df87b647e37687f8fde628b17)
ALTER TABLE `trv_fo_id_s` ADD PRIMARY KEY (`id`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-329', '2.0.4-SNP', '3:0928825df87b647e37687f8fde628b17', 329);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-330::Jeff Caddel (generated)::(Checksum: 3:12be91c9c7169da1bb0b593e751d20c0)
ALTER TABLE `tst_search_attr_indx_tst_doc_t` ADD PRIMARY KEY (`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Primary Key', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-330', '2.0.4-SNP', '3:12be91c9c7169da1bb0b593e751d20c0', 330);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-331::Jeff Caddel (generated)::(Checksum: 3:4114d3a1157b1654d396e32156cf09d8)
CREATE UNIQUE INDEX `KRNS_PARM_DTL_TYP_TC0` ON `krcr_cmpnt_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-331', '2.0.4-SNP', '3:4114d3a1157b1654d396e32156cf09d8', 331);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-332::Jeff Caddel (generated)::(Checksum: 3:fbf891e1386d60a7a942d382e4ce0b1c)
CREATE UNIQUE INDEX `KRNS_NMSPC_TC0` ON `krcr_nmspc_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-332', '2.0.4-SNP', '3:fbf891e1386d60a7a942d382e4ce0b1c', 332);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-333::Jeff Caddel (generated)::(Checksum: 3:f05dda90c5ef681ac11e199e71e25bfe)
CREATE UNIQUE INDEX `KRNS_PARM_TC0` ON `krcr_parm_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-333', '2.0.4-SNP', '3:f05dda90c5ef681ac11e199e71e25bfe', 333);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-334::Jeff Caddel (generated)::(Checksum: 3:d99b35cbd8f5b5e0fabe68a5ea54b147)
CREATE UNIQUE INDEX `KRNS_PARM_TYP_TC0` ON `krcr_parm_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-334', '2.0.4-SNP', '3:d99b35cbd8f5b5e0fabe68a5ea54b147', 334);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-335::Jeff Caddel (generated)::(Checksum: 3:59a9fe4daa464bb0c0ce0443a06dd81e)
CREATE UNIQUE INDEX `KREN_CHNL_SUBSCRP_TC0` ON `kren_chnl_subscrp_t`(`CHNL_ID`, `PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-335', '2.0.4-SNP', '3:59a9fe4daa464bb0c0ce0443a06dd81e', 335);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-336::Jeff Caddel (generated)::(Checksum: 3:383ca836d4c39d39f7eec2e9d4203f7a)
CREATE UNIQUE INDEX `KREN_CHNL_TC0` ON `kren_chnl_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-336', '2.0.4-SNP', '3:383ca836d4c39d39f7eec2e9d4203f7a', 336);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-337::Jeff Caddel (generated)::(Checksum: 3:b89d51dd6b0c4d43821bbc0a3476cd9c)
CREATE UNIQUE INDEX `KREN_CNTNT_TYP_TC0` ON `kren_cntnt_typ_t`(`NM`, `CNTNT_TYP_VER_NBR`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-337', '2.0.4-SNP', '3:b89d51dd6b0c4d43821bbc0a3476cd9c', 337);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-338::Jeff Caddel (generated)::(Checksum: 3:67def80d81f2344573e839e9f82c0bb9)
CREATE UNIQUE INDEX `KREN_MSG_DELIV_TC0` ON `kren_msg_deliv_t`(`MSG_ID`, `TYP_NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-338', '2.0.4-SNP', '3:67def80d81f2344573e839e9f82c0bb9', 338);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-339::Jeff Caddel (generated)::(Checksum: 3:eba38fcdd5f9fc268382dd2be58da771)
CREATE UNIQUE INDEX `KREN_MSG_TC0` ON `kren_msg_t`(`ORGN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-339', '2.0.4-SNP', '3:eba38fcdd5f9fc268382dd2be58da771', 339);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-340::Jeff Caddel (generated)::(Checksum: 3:c18cba77dac56a43dc56b8bd97b158e8)
CREATE UNIQUE INDEX `KREN_NTFCTN_MSG_DELIV_TC0` ON `kren_ntfctn_msg_deliv_t`(`NTFCTN_ID`, `RECIP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-340', '2.0.4-SNP', '3:c18cba77dac56a43dc56b8bd97b158e8', 340);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-341::Jeff Caddel (generated)::(Checksum: 3:8ce51c7ad3dcc6ad8d3603b42688cf88)
CREATE UNIQUE INDEX `KREN_PRIO_TC0` ON `kren_prio_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-341', '2.0.4-SNP', '3:8ce51c7ad3dcc6ad8d3603b42688cf88', 341);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-342::Jeff Caddel (generated)::(Checksum: 3:6ced347ac4a005c4293cbff394aedd6a)
CREATE UNIQUE INDEX `KREN_PRODCR_TC0` ON `kren_prodcr_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-342', '2.0.4-SNP', '3:6ced347ac4a005c4293cbff394aedd6a', 342);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-343::Jeff Caddel (generated)::(Checksum: 3:c72524f8234724e426611a5b912def10)
CREATE UNIQUE INDEX `KREN_RECIP_LIST_TC0` ON `kren_recip_list_t`(`CHNL_ID`, `RECIP_TYP_CD`, `RECIP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-343', '2.0.4-SNP', '3:c72524f8234724e426611a5b912def10', 343);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-344::Jeff Caddel (generated)::(Checksum: 3:6f06afc77c121a2899c2c7c634e88f33)
CREATE UNIQUE INDEX `KREN_RECIP_PREFS_TC0` ON `kren_recip_prefs_t`(`RECIP_ID`, `PROP`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-344', '2.0.4-SNP', '3:6f06afc77c121a2899c2c7c634e88f33', 344);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-345::Jeff Caddel (generated)::(Checksum: 3:0b89153695b1226ed648a775a254b64c)
CREATE UNIQUE INDEX `KREN_RECIP_TC0` ON `kren_recip_t`(`NTFCTN_ID`, `RECIP_TYP_CD`, `PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-345', '2.0.4-SNP', '3:0b89153695b1226ed648a775a254b64c', 345);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-346::Jeff Caddel (generated)::(Checksum: 3:c1747d3a22fc7b3aa3675bb4d023456a)
CREATE UNIQUE INDEX `KREN_RVWER_TC0` ON `kren_rvwer_t`(`CHNL_ID`, `TYP`, `PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-346', '2.0.4-SNP', '3:c1747d3a22fc7b3aa3675bb4d023456a', 346);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-347::Jeff Caddel (generated)::(Checksum: 3:72b2dab829a89bc87167817ec2ce64f7)
CREATE UNIQUE INDEX `KREN_SNDR_TC0` ON `kren_sndr_t`(`NTFCTN_ID`, `NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-347', '2.0.4-SNP', '3:72b2dab829a89bc87167817ec2ce64f7', 347);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-348::Jeff Caddel (generated)::(Checksum: 3:c1478398f92a29ac61923e078ded82bf)
CREATE INDEX `KREW_ACTN_ITM_T1` ON `krew_actn_itm_t`(`PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-348', '2.0.4-SNP', '3:c1478398f92a29ac61923e078ded82bf', 348);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-349::Jeff Caddel (generated)::(Checksum: 3:8b31b2d17c227e82b0ca2e9b3aecf0de)
CREATE INDEX `KREW_ACTN_ITM_TI2` ON `krew_actn_itm_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-349', '2.0.4-SNP', '3:8b31b2d17c227e82b0ca2e9b3aecf0de', 349);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-350::Jeff Caddel (generated)::(Checksum: 3:94afc3bd3c45d4c343e852ea382f27e6)
CREATE INDEX `KREW_ACTN_ITM_TI3` ON `krew_actn_itm_t`(`ACTN_RQST_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-350', '2.0.4-SNP', '3:94afc3bd3c45d4c343e852ea382f27e6', 350);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-351::Jeff Caddel (generated)::(Checksum: 3:d270a6c207dbc43800f1b8e12b4fc653)
CREATE INDEX `KREW_ACTN_ITM_TI5` ON `krew_actn_itm_t`(`PRNCPL_ID`, `DLGN_TYP`, `DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-351', '2.0.4-SNP', '3:d270a6c207dbc43800f1b8e12b4fc653', 351);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-352::Jeff Caddel (generated)::(Checksum: 3:8cd43ab10dcfd4946491289c76c1fcac)
CREATE INDEX `KREW_ACTN_RQST_T11` ON `krew_actn_rqst_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-352', '2.0.4-SNP', '3:8cd43ab10dcfd4946491289c76c1fcac', 352);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-353::Jeff Caddel (generated)::(Checksum: 3:62b212eab3fdda06d504c1459c38729a)
CREATE INDEX `KREW_ACTN_RQST_T12` ON `krew_actn_rqst_t`(`PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-353', '2.0.4-SNP', '3:62b212eab3fdda06d504c1459c38729a', 353);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-354::Jeff Caddel (generated)::(Checksum: 3:2f3424afe8b497902f8f953519fb9a73)
CREATE INDEX `KREW_ACTN_RQST_T13` ON `krew_actn_rqst_t`(`ACTN_TKN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-354', '2.0.4-SNP', '3:2f3424afe8b497902f8f953519fb9a73', 354);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-355::Jeff Caddel (generated)::(Checksum: 3:b92866720e04e8eadbe73d1c9197dfd3)
CREATE INDEX `KREW_ACTN_RQST_T14` ON `krew_actn_rqst_t`(`PARNT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-355', '2.0.4-SNP', '3:b92866720e04e8eadbe73d1c9197dfd3', 355);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-356::Jeff Caddel (generated)::(Checksum: 3:834300492c9de103a18cb5cec416c1e6)
CREATE INDEX `KREW_ACTN_RQST_T15` ON `krew_actn_rqst_t`(`RSP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-356', '2.0.4-SNP', '3:834300492c9de103a18cb5cec416c1e6', 356);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-357::Jeff Caddel (generated)::(Checksum: 3:d1f16636f6a8b4b417e6e1a97cf596f4)
CREATE INDEX `KREW_ACTN_RQST_T16` ON `krew_actn_rqst_t`(`STAT_CD`, `RSP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-357', '2.0.4-SNP', '3:d1f16636f6a8b4b417e6e1a97cf596f4', 357);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-358::Jeff Caddel (generated)::(Checksum: 3:e120807313ad2c8d09af67395f0b4354)
CREATE INDEX `KREW_ACTN_RQST_T17` ON `krew_actn_rqst_t`(`RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-358', '2.0.4-SNP', '3:e120807313ad2c8d09af67395f0b4354', 358);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-359::Jeff Caddel (generated)::(Checksum: 3:21b2ec598f58d8db1fae8bb2e8c8f72d)
CREATE INDEX `KREW_ACTN_RQST_T19` ON `krew_actn_rqst_t`(`STAT_CD`, `DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-359', '2.0.4-SNP', '3:21b2ec598f58d8db1fae8bb2e8c8f72d', 359);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-360::Jeff Caddel (generated)::(Checksum: 3:f78433391f03f2da6450ec4e4672c25d)
CREATE INDEX `KREW_ACTN_TKN_TI1` ON `krew_actn_tkn_t`(`DOC_HDR_ID`, `PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-360', '2.0.4-SNP', '3:f78433391f03f2da6450ec4e4672c25d', 360);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-361::Jeff Caddel (generated)::(Checksum: 3:1d3d01390b187f0049d596d214e8164b)
CREATE INDEX `KREW_ACTN_TKN_TI2` ON `krew_actn_tkn_t`(`DOC_HDR_ID`, `PRNCPL_ID`, `ACTN_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-361', '2.0.4-SNP', '3:1d3d01390b187f0049d596d214e8164b', 361);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-362::Jeff Caddel (generated)::(Checksum: 3:660269b8a067487006e5f39e08d4ff9d)
CREATE INDEX `KREW_ACTN_TKN_TI3` ON `krew_actn_tkn_t`(`PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-362', '2.0.4-SNP', '3:660269b8a067487006e5f39e08d4ff9d', 362);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-363::Jeff Caddel (generated)::(Checksum: 3:f16eb8550a68dd162fc805025f3605f1)
CREATE INDEX `KREW_ACTN_TKN_TI4` ON `krew_actn_tkn_t`(`DLGTR_PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-363', '2.0.4-SNP', '3:f16eb8550a68dd162fc805025f3605f1', 363);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-364::Jeff Caddel (generated)::(Checksum: 3:dc943575225f4e0209a36864f146fcdf)
CREATE INDEX `KREW_ACTN_TKN_TI5` ON `krew_actn_tkn_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-364', '2.0.4-SNP', '3:dc943575225f4e0209a36864f146fcdf', 364);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-365::Jeff Caddel (generated)::(Checksum: 3:93a49113434313946d16c45aed85ef31)
CREATE INDEX `KREW_APP_DOC_STAT_TI1` ON `krew_app_doc_stat_tran_t`(`DOC_HDR_ID`, `STAT_TRANS_DATE`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-365', '2.0.4-SNP', '3:93a49113434313946d16c45aed85ef31', 365);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-366::Jeff Caddel (generated)::(Checksum: 3:ed43a87acdd245cd5b442a75f3e2b2be)
CREATE INDEX `KREW_APP_DOC_STAT_TI2` ON `krew_app_doc_stat_tran_t`(`DOC_HDR_ID`, `APP_DOC_STAT_FROM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-366', '2.0.4-SNP', '3:ed43a87acdd245cd5b442a75f3e2b2be', 366);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-367::Jeff Caddel (generated)::(Checksum: 3:1f9e7d7eb0c30db49f2fbc5fd59e4e04)
CREATE INDEX `KREW_APP_DOC_STAT_TI3` ON `krew_app_doc_stat_tran_t`(`DOC_HDR_ID`, `APP_DOC_STAT_TO`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-367', '2.0.4-SNP', '3:1f9e7d7eb0c30db49f2fbc5fd59e4e04', 367);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-368::Jeff Caddel (generated)::(Checksum: 3:ce4e0a4f79c168e92262052cb22eb999)
CREATE UNIQUE INDEX `KREW_APP_DOC_STAT_TRAN_TC0` ON `krew_app_doc_stat_tran_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-368', '2.0.4-SNP', '3:ce4e0a4f79c168e92262052cb22eb999', 368);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-369::Jeff Caddel (generated)::(Checksum: 3:26c58a819221bb1c66aac100f4814cb2)
CREATE INDEX `KREW_ATT_TI1` ON `krew_att_t`(`NTE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-369', '2.0.4-SNP', '3:26c58a819221bb1c66aac100f4814cb2', 369);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-370::Jeff Caddel (generated)::(Checksum: 3:5da9d6eb56b608cae68a69a764269ef0)
CREATE UNIQUE INDEX `KREW_DLGN_RSP_TC0` ON `krew_dlgn_rsp_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-370', '2.0.4-SNP', '3:5da9d6eb56b608cae68a69a764269ef0', 370);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-371::Jeff Caddel (generated)::(Checksum: 3:d28f23b5fc29c21f017f7bc608bc6ed2)
CREATE INDEX `KREW_DOC_HDR_EXT_DT_TI1` ON `krew_doc_hdr_ext_dt_t`(`KEY_CD`, `VAL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-371', '2.0.4-SNP', '3:d28f23b5fc29c21f017f7bc608bc6ed2', 371);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-372::Jeff Caddel (generated)::(Checksum: 3:438ea12c13e5fe2a69f2c5ba0832d228)
CREATE INDEX `KREW_DOC_HDR_EXT_DT_TI2` ON `krew_doc_hdr_ext_dt_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-372', '2.0.4-SNP', '3:438ea12c13e5fe2a69f2c5ba0832d228', 372);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-373::Jeff Caddel (generated)::(Checksum: 3:24eb18a21928cdda5bc2ef2e194d96f6)
CREATE INDEX `KREW_DOC_HDR_EXT_DT_TI3` ON `krew_doc_hdr_ext_dt_t`(`VAL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-373', '2.0.4-SNP', '3:24eb18a21928cdda5bc2ef2e194d96f6', 373);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-374::Jeff Caddel (generated)::(Checksum: 3:553b79746b7376c83640c6ea841eadcd)
CREATE INDEX `KREW_DOC_HDR_EXT_FLT_TI1` ON `krew_doc_hdr_ext_flt_t`(`KEY_CD`, `VAL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-374', '2.0.4-SNP', '3:553b79746b7376c83640c6ea841eadcd', 374);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-375::Jeff Caddel (generated)::(Checksum: 3:78aee53590f89a4c646c1c526964bbb0)
CREATE INDEX `KREW_DOC_HDR_EXT_FLT_TI2` ON `krew_doc_hdr_ext_flt_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-375', '2.0.4-SNP', '3:78aee53590f89a4c646c1c526964bbb0', 375);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-376::Jeff Caddel (generated)::(Checksum: 3:b399f932658ff06c821b8f42bdab25f9)
CREATE INDEX `KREW_DOC_HDR_EXT_FLT_TI3` ON `krew_doc_hdr_ext_flt_t`(`VAL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-376', '2.0.4-SNP', '3:b399f932658ff06c821b8f42bdab25f9', 376);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-377::Jeff Caddel (generated)::(Checksum: 3:bce65ad2a16031e486599e1eae722519)
CREATE INDEX `KREW_DOC_HDR_EXT_LONG_TI1` ON `krew_doc_hdr_ext_long_t`(`KEY_CD`, `VAL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-377', '2.0.4-SNP', '3:bce65ad2a16031e486599e1eae722519', 377);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-378::Jeff Caddel (generated)::(Checksum: 3:f22357471f7261847b653b03ea62b21b)
CREATE INDEX `KREW_DOC_HDR_EXT_LONG_TI2` ON `krew_doc_hdr_ext_long_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-378', '2.0.4-SNP', '3:f22357471f7261847b653b03ea62b21b', 378);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-379::Jeff Caddel (generated)::(Checksum: 3:d61db07f7ba65ab544a4d375a996e145)
CREATE INDEX `KREW_DOC_HDR_EXT_LONG_TI3` ON `krew_doc_hdr_ext_long_t`(`VAL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-379', '2.0.4-SNP', '3:d61db07f7ba65ab544a4d375a996e145', 379);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-380::Jeff Caddel (generated)::(Checksum: 3:a11a34f493c48901473f97ad5a602233)
CREATE INDEX `KREW_DOC_HDR_EXT_TI1` ON `krew_doc_hdr_ext_t`(`KEY_CD`, `VAL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-380', '2.0.4-SNP', '3:a11a34f493c48901473f97ad5a602233', 380);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-381::Jeff Caddel (generated)::(Checksum: 3:a3eca1a18a74cf2fafa5af2cf0ccca3b)
CREATE INDEX `KREW_DOC_HDR_EXT_TI2` ON `krew_doc_hdr_ext_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-381', '2.0.4-SNP', '3:a3eca1a18a74cf2fafa5af2cf0ccca3b', 381);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-382::Jeff Caddel (generated)::(Checksum: 3:3377b426fc58e822575ff15f5345a62d)
CREATE INDEX `KREW_DOC_HDR_EXT_TI3` ON `krew_doc_hdr_ext_t`(`VAL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-382', '2.0.4-SNP', '3:3377b426fc58e822575ff15f5345a62d', 382);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-383::Jeff Caddel (generated)::(Checksum: 3:67582a51cb95d11706dba81522c42e83)
CREATE INDEX `KREW_DOC_HDR_T10` ON `krew_doc_hdr_t`(`APP_DOC_STAT`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-383', '2.0.4-SNP', '3:67582a51cb95d11706dba81522c42e83', 383);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-384::Jeff Caddel (generated)::(Checksum: 3:3b2af0042dc8b99e7f9652112199f0af)
CREATE INDEX `KREW_DOC_HDR_T12` ON `krew_doc_hdr_t`(`APP_DOC_STAT_MDFN_DT`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-384', '2.0.4-SNP', '3:3b2af0042dc8b99e7f9652112199f0af', 384);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-385::Jeff Caddel (generated)::(Checksum: 3:4188382300bf089dd96eadc0e8e2ad5c)
CREATE UNIQUE INDEX `KREW_DOC_HDR_TC0` ON `krew_doc_hdr_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-385', '2.0.4-SNP', '3:4188382300bf089dd96eadc0e8e2ad5c', 385);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-386::Jeff Caddel (generated)::(Checksum: 3:b7cd7d1653cf806b344fb550349d9700)
CREATE INDEX `KREW_DOC_HDR_TI1` ON `krew_doc_hdr_t`(`DOC_TYP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-386', '2.0.4-SNP', '3:b7cd7d1653cf806b344fb550349d9700', 386);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-387::Jeff Caddel (generated)::(Checksum: 3:8037dc034df99bc56317999ddd05c357)
CREATE INDEX `KREW_DOC_HDR_TI2` ON `krew_doc_hdr_t`(`INITR_PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-387', '2.0.4-SNP', '3:8037dc034df99bc56317999ddd05c357', 387);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-388::Jeff Caddel (generated)::(Checksum: 3:1c5a3476aa6d5f80ddbee59ffecc5680)
CREATE INDEX `KREW_DOC_HDR_TI3` ON `krew_doc_hdr_t`(`DOC_HDR_STAT_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-388', '2.0.4-SNP', '3:1c5a3476aa6d5f80ddbee59ffecc5680', 388);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-389::Jeff Caddel (generated)::(Checksum: 3:ef7de20fa031fa31215f6ac97cb61abe)
CREATE INDEX `KREW_DOC_HDR_TI4` ON `krew_doc_hdr_t`(`TTL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-389', '2.0.4-SNP', '3:ef7de20fa031fa31215f6ac97cb61abe', 389);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-390::Jeff Caddel (generated)::(Checksum: 3:93a54d2f8fcf4c5fea98bf708e7ff3d5)
CREATE INDEX `KREW_DOC_HDR_TI5` ON `krew_doc_hdr_t`(`CRTE_DT`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-390', '2.0.4-SNP', '3:93a54d2f8fcf4c5fea98bf708e7ff3d5', 390);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-391::Jeff Caddel (generated)::(Checksum: 3:d2a746a66de84d7006f62e6a9706c998)
CREATE INDEX `KREW_DOC_HDR_TI6` ON `krew_doc_hdr_t`(`RTE_STAT_MDFN_DT`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-391', '2.0.4-SNP', '3:d2a746a66de84d7006f62e6a9706c998', 391);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-392::Jeff Caddel (generated)::(Checksum: 3:9c35ff6c73b2a0943919bbbb67d7d8be)
CREATE INDEX `KREW_DOC_HDR_TI7` ON `krew_doc_hdr_t`(`APRV_DT`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-392', '2.0.4-SNP', '3:9c35ff6c73b2a0943919bbbb67d7d8be', 392);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-393::Jeff Caddel (generated)::(Checksum: 3:cfce23ce8631b257c07439b7b3aa8a67)
CREATE INDEX `KREW_DOC_HDR_TI8` ON `krew_doc_hdr_t`(`FNL_DT`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-393', '2.0.4-SNP', '3:cfce23ce8631b257c07439b7b3aa8a67', 393);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-394::Jeff Caddel (generated)::(Checksum: 3:b945e677663338738603d554c5b9cba5)
CREATE INDEX `KREW_DOC_HDR_TI9` ON `krew_doc_hdr_t`(`APP_DOC_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-394', '2.0.4-SNP', '3:b945e677663338738603d554c5b9cba5', 394);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-395::Jeff Caddel (generated)::(Checksum: 3:8a9a256789d665e0c5ed00aea9714726)
CREATE INDEX `KREW_DOC_LNK_TI1` ON `krew_doc_lnk_t`(`ORGN_DOC_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-395', '2.0.4-SNP', '3:8a9a256789d665e0c5ed00aea9714726', 395);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-396::Jeff Caddel (generated)::(Checksum: 3:9c1cc30139a2f480ed0a5607423c5f13)
CREATE INDEX `KREW_DOC_NTE_TI1` ON `krew_doc_nte_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-396', '2.0.4-SNP', '3:9c1cc30139a2f480ed0a5607423c5f13', 396);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-397::Jeff Caddel (generated)::(Checksum: 3:756c31a1fd72533a615064822accf76c)
CREATE INDEX `KREW_DOC_TYP_APP_DOC_STAT_T1` ON `krew_doc_typ_app_doc_stat_t`(`DOC_TYP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-397', '2.0.4-SNP', '3:756c31a1fd72533a615064822accf76c', 397);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-398::Jeff Caddel (generated)::(Checksum: 3:930b9bad64f48f16b0aac29a6ca129ac)
CREATE UNIQUE INDEX `KREW_DOC_TYP_APP_DOC_STAT_TC0` ON `krew_doc_typ_app_doc_stat_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-398', '2.0.4-SNP', '3:930b9bad64f48f16b0aac29a6ca129ac', 398);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-399::Jeff Caddel (generated)::(Checksum: 3:438c194d8995baec8f7b8a478c64e744)
CREATE INDEX `KREW_DOC_TYP_ATTR_TI1` ON `krew_doc_typ_attr_t`(`DOC_TYP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-399', '2.0.4-SNP', '3:438c194d8995baec8f7b8a478c64e744', 399);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-400::Jeff Caddel (generated)::(Checksum: 3:96bd265c02bf5d6b1edf9ff8e77f6572)
CREATE UNIQUE INDEX `KREW_DOC_TYP_PLCY_RELN_TC0` ON `krew_doc_typ_plcy_reln_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-400', '2.0.4-SNP', '3:96bd265c02bf5d6b1edf9ff8e77f6572', 400);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-401::Jeff Caddel (generated)::(Checksum: 3:2b36581bb9205c3f7a94fabfc6f960db)
CREATE INDEX `KREW_DOC_TYP_PROC_TI1` ON `krew_doc_typ_proc_t`(`DOC_TYP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-401', '2.0.4-SNP', '3:2b36581bb9205c3f7a94fabfc6f960db', 401);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-402::Jeff Caddel (generated)::(Checksum: 3:271bb8535ddb3743bf4265284ddfd7e8)
CREATE INDEX `KREW_DOC_TYP_PROC_TI2` ON `krew_doc_typ_proc_t`(`INIT_RTE_NODE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-402', '2.0.4-SNP', '3:271bb8535ddb3743bf4265284ddfd7e8', 402);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-403::Jeff Caddel (generated)::(Checksum: 3:3560ab246c1c240cbd67b8ed725467f8)
CREATE INDEX `KREW_DOC_TYP_PROC_TI3` ON `krew_doc_typ_proc_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-403', '2.0.4-SNP', '3:3560ab246c1c240cbd67b8ed725467f8', 403);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-404::Jeff Caddel (generated)::(Checksum: 3:2912fd46ea36ec639815453877d609f3)
CREATE UNIQUE INDEX `KREW_DOC_TYP_TC0` ON `krew_doc_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-404', '2.0.4-SNP', '3:2912fd46ea36ec639815453877d609f3', 404);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-405::Jeff Caddel (generated)::(Checksum: 3:8daeafbd0c669e624ec9ea14398dc804)
CREATE UNIQUE INDEX `KREW_DOC_TYP_TI1` ON `krew_doc_typ_t`(`DOC_TYP_NM`, `DOC_TYP_VER_NBR`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-405', '2.0.4-SNP', '3:8daeafbd0c669e624ec9ea14398dc804', 405);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-406::Jeff Caddel (generated)::(Checksum: 3:c9acc7edfd354b186ce6aba28676ddcd)
CREATE INDEX `KREW_DOC_TYP_TI2` ON `krew_doc_typ_t`(`PARNT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-406', '2.0.4-SNP', '3:c9acc7edfd354b186ce6aba28676ddcd', 406);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-407::Jeff Caddel (generated)::(Checksum: 3:f4c677c2557ea756a14a1592452c18fa)
CREATE INDEX `KREW_DOC_TYP_TI3` ON `krew_doc_typ_t`(`DOC_TYP_ID`, `PARNT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-407', '2.0.4-SNP', '3:f4c677c2557ea756a14a1592452c18fa', 407);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-408::Jeff Caddel (generated)::(Checksum: 3:1daa574cd080cc98b09e3868d356f263)
CREATE INDEX `KREW_DOC_TYP_TI4` ON `krew_doc_typ_t`(`PREV_DOC_TYP_VER_NBR`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-408', '2.0.4-SNP', '3:1daa574cd080cc98b09e3868d356f263', 408);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-409::Jeff Caddel (generated)::(Checksum: 3:01afde24c8ed4cfa341df6bda8fc30a1)
CREATE INDEX `KREW_DOC_TYP_TI5` ON `krew_doc_typ_t`(`CUR_IND`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-409', '2.0.4-SNP', '3:01afde24c8ed4cfa341df6bda8fc30a1', 409);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-410::Jeff Caddel (generated)::(Checksum: 3:42500c04a617213190c6e885f4c3471c)
CREATE INDEX `KREW_DOC_TYP_TI6` ON `krew_doc_typ_t`(`DOC_TYP_NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-410', '2.0.4-SNP', '3:42500c04a617213190c6e885f4c3471c', 410);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-411::Jeff Caddel (generated)::(Checksum: 3:c4c006ac74597c62c0ebc79964b3a33c)
CREATE UNIQUE INDEX `KREW_EDL_ASSCTN_TC0` ON `krew_edl_assctn_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-411', '2.0.4-SNP', '3:c4c006ac74597c62c0ebc79964b3a33c', 411);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-412::Jeff Caddel (generated)::(Checksum: 3:04d76e257a3f62eca0a3a57f74c82d98)
CREATE UNIQUE INDEX `KREW_EDL_DEF_TC0` ON `krew_edl_def_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-412', '2.0.4-SNP', '3:04d76e257a3f62eca0a3a57f74c82d98', 412);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-413::Jeff Caddel (generated)::(Checksum: 3:e2c6e0a2d5418edaa1740f2d8ef18812)
CREATE INDEX `KREW_EDL_DMP_TI1` ON `krew_edl_dmp_t`(`DOC_TYP_NM`, `DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-413', '2.0.4-SNP', '3:e2c6e0a2d5418edaa1740f2d8ef18812', 413);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-414::Jeff Caddel (generated)::(Checksum: 3:2f6abd3a535bd2c39e5e621997ad9857)
CREATE INDEX `KREW_HLP_TI1` ON `krew_hlp_t`(`KEY_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-414', '2.0.4-SNP', '3:2f6abd3a535bd2c39e5e621997ad9857', 414);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-415::Jeff Caddel (generated)::(Checksum: 3:daba1b721d822cbf92ecf67b3e7d2df9)
CREATE INDEX `KREW_INIT_RTE_NODE_INSTN_TI1` ON `krew_init_rte_node_instn_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-415', '2.0.4-SNP', '3:daba1b721d822cbf92ecf67b3e7d2df9', 415);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-416::Jeff Caddel (generated)::(Checksum: 3:47f7ef81b2967ccd0f677bebfa7af246)
CREATE INDEX `KREW_INIT_RTE_NODE_INSTN_TI2` ON `krew_init_rte_node_instn_t`(`RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-416', '2.0.4-SNP', '3:47f7ef81b2967ccd0f677bebfa7af246', 416);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-417::Jeff Caddel (generated)::(Checksum: 3:284e0a89b613c5153ca5fc611745ed90)
CREATE INDEX `KREW_OUT_BOX_ITM_TI1` ON `krew_out_box_itm_t`(`PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-417', '2.0.4-SNP', '3:284e0a89b613c5153ca5fc611745ed90', 417);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-418::Jeff Caddel (generated)::(Checksum: 3:dbc0a9fc5b8a707d217f77e8a7cc6c51)
CREATE INDEX `KREW_OUT_BOX_ITM_TI2` ON `krew_out_box_itm_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-418', '2.0.4-SNP', '3:dbc0a9fc5b8a707d217f77e8a7cc6c51', 418);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-419::Jeff Caddel (generated)::(Checksum: 3:2869b86ab1369fcd9a174598dbba2266)
CREATE INDEX `KREW_OUT_BOX_ITM_TI3` ON `krew_out_box_itm_t`(`ACTN_RQST_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-419', '2.0.4-SNP', '3:2869b86ab1369fcd9a174598dbba2266', 419);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-420::Jeff Caddel (generated)::(Checksum: 3:13ffa3386a334c3533488c4f1d1acbd3)
CREATE INDEX `KREW_RTE_BRCH_PROTO_TI1` ON `krew_rte_brch_proto_t`(`BRCH_NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-420', '2.0.4-SNP', '3:13ffa3386a334c3533488c4f1d1acbd3', 420);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-421::Jeff Caddel (generated)::(Checksum: 3:a68ac2c4d210443bbe7fb74d295c3412)
CREATE INDEX `KREW_RTE_BRCH_ST_TI1` ON `krew_rte_brch_st_t`(`RTE_BRCH_ID`, `KEY_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-421', '2.0.4-SNP', '3:a68ac2c4d210443bbe7fb74d295c3412', 421);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-422::Jeff Caddel (generated)::(Checksum: 3:8d54aed8c249861ee05ed27cd9612b5e)
CREATE INDEX `KREW_RTE_BRCH_ST_TI2` ON `krew_rte_brch_st_t`(`RTE_BRCH_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-422', '2.0.4-SNP', '3:8d54aed8c249861ee05ed27cd9612b5e', 422);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-423::Jeff Caddel (generated)::(Checksum: 3:c6c4fd3fecada163f78c11a8114c252e)
CREATE INDEX `KREW_RTE_BRCH_ST_TI3` ON `krew_rte_brch_st_t`(`KEY_CD`, `VAL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-423', '2.0.4-SNP', '3:c6c4fd3fecada163f78c11a8114c252e', 423);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-424::Jeff Caddel (generated)::(Checksum: 3:18bfc79aed5b92237777a93c231a7e5c)
CREATE INDEX `KREW_RTE_BRCH_TI1` ON `krew_rte_brch_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-424', '2.0.4-SNP', '3:18bfc79aed5b92237777a93c231a7e5c', 424);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-425::Jeff Caddel (generated)::(Checksum: 3:e2f9fde3bc0d4e3ea34c0bc2f22a834a)
CREATE INDEX `KREW_RTE_BRCH_TI2` ON `krew_rte_brch_t`(`PARNT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-425', '2.0.4-SNP', '3:e2f9fde3bc0d4e3ea34c0bc2f22a834a', 425);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-426::Jeff Caddel (generated)::(Checksum: 3:3c2f44d90cd7e8b0e2c187f8d1d082d2)
CREATE INDEX `KREW_RTE_BRCH_TI3` ON `krew_rte_brch_t`(`INIT_RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-426', '2.0.4-SNP', '3:3c2f44d90cd7e8b0e2c187f8d1d082d2', 426);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-427::Jeff Caddel (generated)::(Checksum: 3:4fceb116555ebbe849a74aa30492b61f)
CREATE INDEX `KREW_RTE_BRCH_TI4` ON `krew_rte_brch_t`(`SPLT_RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-427', '2.0.4-SNP', '3:4fceb116555ebbe849a74aa30492b61f', 427);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-428::Jeff Caddel (generated)::(Checksum: 3:e832624fcb5e435c8bca80d353582ee3)
CREATE INDEX `KREW_RTE_BRCH_TI5` ON `krew_rte_brch_t`(`JOIN_RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-428', '2.0.4-SNP', '3:e832624fcb5e435c8bca80d353582ee3', 428);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-429::Jeff Caddel (generated)::(Checksum: 3:87e2dcee4b6e2bb92ed1d81fbccae1d3)
CREATE INDEX `KREW_RTE_NODE_INSTN_LNK_TI1` ON `krew_rte_node_instn_lnk_t`(`FROM_RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-429', '2.0.4-SNP', '3:87e2dcee4b6e2bb92ed1d81fbccae1d3', 429);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-430::Jeff Caddel (generated)::(Checksum: 3:69f640429aa7d7c7fdfb91d1b31a4c09)
CREATE INDEX `KREW_RTE_NODE_INSTN_LNK_TI2` ON `krew_rte_node_instn_lnk_t`(`TO_RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-430', '2.0.4-SNP', '3:69f640429aa7d7c7fdfb91d1b31a4c09', 430);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-431::Jeff Caddel (generated)::(Checksum: 3:d1e2c71f9fcce6946250a77a0cc7ce66)
CREATE INDEX `KREW_RTE_NODE_INSTN_ST_TI1` ON `krew_rte_node_instn_st_t`(`RTE_NODE_INSTN_ID`, `KEY_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-431', '2.0.4-SNP', '3:d1e2c71f9fcce6946250a77a0cc7ce66', 431);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-432::Jeff Caddel (generated)::(Checksum: 3:b729c00dee30bba523870b0db7864672)
CREATE INDEX `KREW_RTE_NODE_INSTN_ST_TI2` ON `krew_rte_node_instn_st_t`(`RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-432', '2.0.4-SNP', '3:b729c00dee30bba523870b0db7864672', 432);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-433::Jeff Caddel (generated)::(Checksum: 3:87bb5fa597e176be3ee29c40cf65ecb5)
CREATE INDEX `KREW_RTE_NODE_INSTN_ST_TI3` ON `krew_rte_node_instn_st_t`(`KEY_CD`, `VAL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-433', '2.0.4-SNP', '3:87bb5fa597e176be3ee29c40cf65ecb5', 433);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-434::Jeff Caddel (generated)::(Checksum: 3:1915af24ef0d4b9cc0a34335d5e21c9d)
CREATE INDEX `KREW_RTE_NODE_INSTN_TI1` ON `krew_rte_node_instn_t`(`DOC_HDR_ID`, `ACTV_IND`, `CMPLT_IND`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-434', '2.0.4-SNP', '3:1915af24ef0d4b9cc0a34335d5e21c9d', 434);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-435::Jeff Caddel (generated)::(Checksum: 3:0c841a2b543dcf56ca3d80946b97311c)
CREATE INDEX `KREW_RTE_NODE_INSTN_TI2` ON `krew_rte_node_instn_t`(`RTE_NODE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-435', '2.0.4-SNP', '3:0c841a2b543dcf56ca3d80946b97311c', 435);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-436::Jeff Caddel (generated)::(Checksum: 3:2026fd85a18b82cb2dd907fc584f8843)
CREATE INDEX `KREW_RTE_NODE_INSTN_TI3` ON `krew_rte_node_instn_t`(`BRCH_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-436', '2.0.4-SNP', '3:2026fd85a18b82cb2dd907fc584f8843', 436);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-437::Jeff Caddel (generated)::(Checksum: 3:ac53d8475e0c106e8c0eb30f0fe6d0fd)
CREATE INDEX `KREW_RTE_NODE_INSTN_TI4` ON `krew_rte_node_instn_t`(`PROC_RTE_NODE_INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-437', '2.0.4-SNP', '3:ac53d8475e0c106e8c0eb30f0fe6d0fd', 437);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-438::Jeff Caddel (generated)::(Checksum: 3:bb89f3124c409d827775d8f0eb4da148)
CREATE INDEX `KREW_RTE_NODE_LNK_TI1` ON `krew_rte_node_lnk_t`(`FROM_RTE_NODE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-438', '2.0.4-SNP', '3:bb89f3124c409d827775d8f0eb4da148', 438);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-439::Jeff Caddel (generated)::(Checksum: 3:453288cdd190f1d63470c05c9172bf42)
CREATE INDEX `KREW_RTE_NODE_LNK_TI2` ON `krew_rte_node_lnk_t`(`TO_RTE_NODE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-439', '2.0.4-SNP', '3:453288cdd190f1d63470c05c9172bf42', 439);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-440::Jeff Caddel (generated)::(Checksum: 3:77668518e17d076c86ef860d101c3249)
CREATE INDEX `KREW_RTE_NODE_TI1` ON `krew_rte_node_t`(`NM`, `DOC_TYP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-440', '2.0.4-SNP', '3:77668518e17d076c86ef860d101c3249', 440);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-441::Jeff Caddel (generated)::(Checksum: 3:c847c7d24e6840fc57ae765308555ee6)
CREATE INDEX `KREW_RTE_NODE_TI2` ON `krew_rte_node_t`(`DOC_TYP_ID`, `FNL_APRVR_IND`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-441', '2.0.4-SNP', '3:c847c7d24e6840fc57ae765308555ee6', 441);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-442::Jeff Caddel (generated)::(Checksum: 3:459495e7b4e4f0f061b4586aeca87c9c)
CREATE INDEX `KREW_RTE_NODE_TI3` ON `krew_rte_node_t`(`BRCH_PROTO_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-442', '2.0.4-SNP', '3:459495e7b4e4f0f061b4586aeca87c9c', 442);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-443::Jeff Caddel (generated)::(Checksum: 3:7e69412955dd87e851a135fa4650fb04)
CREATE INDEX `KREW_RTE_NODE_TI4` ON `krew_rte_node_t`(`DOC_TYP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-443', '2.0.4-SNP', '3:7e69412955dd87e851a135fa4650fb04', 443);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-444::Jeff Caddel (generated)::(Checksum: 3:a0c71487e9457954baf49ce206fd2e91)
CREATE UNIQUE INDEX `KREW_RULE_ATTR_TC0` ON `krew_rule_attr_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-444', '2.0.4-SNP', '3:a0c71487e9457954baf49ce206fd2e91', 444);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-445::Jeff Caddel (generated)::(Checksum: 3:b968257d0e08aa62197e85475f6892de)
CREATE UNIQUE INDEX `KREW_RULE_EXPR_TC0` ON `krew_rule_expr_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-445', '2.0.4-SNP', '3:b968257d0e08aa62197e85475f6892de', 445);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-446::Jeff Caddel (generated)::(Checksum: 3:a661cba03e57e0dfa0e9ddd687acd4e6)
CREATE INDEX `KREW_RULE_EXT_T1` ON `krew_rule_ext_t`(`RULE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-446', '2.0.4-SNP', '3:a661cba03e57e0dfa0e9ddd687acd4e6', 446);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-447::Jeff Caddel (generated)::(Checksum: 3:ad2c69ee8aa2c33d9d79a3378d906e05)
CREATE INDEX `KREW_RULE_EXT_VAL_T1` ON `krew_rule_ext_val_t`(`RULE_EXT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-447', '2.0.4-SNP', '3:ad2c69ee8aa2c33d9d79a3378d906e05', 447);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-448::Jeff Caddel (generated)::(Checksum: 3:6bfbd57c399249e190da2a13f2828019)
CREATE INDEX `KREW_RULE_EXT_VAL_T2` ON `krew_rule_ext_val_t`(`RULE_EXT_VAL_ID`, `KEY_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-448', '2.0.4-SNP', '3:6bfbd57c399249e190da2a13f2828019', 448);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-449::Jeff Caddel (generated)::(Checksum: 3:a74424cc87bce10137f84403705a9a69)
CREATE UNIQUE INDEX `KREW_RULE_RSP_TC0` ON `krew_rule_rsp_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-449', '2.0.4-SNP', '3:a74424cc87bce10137f84403705a9a69', 449);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-450::Jeff Caddel (generated)::(Checksum: 3:f03e403c20029751283a72899a93c7f9)
CREATE INDEX `KREW_RULE_RSP_TI1` ON `krew_rule_rsp_t`(`RULE_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-450', '2.0.4-SNP', '3:f03e403c20029751283a72899a93c7f9', 450);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-451::Jeff Caddel (generated)::(Checksum: 3:b1f2aac4bd6a63334a860ac4a298046b)
CREATE UNIQUE INDEX `KREW_RULE_TC0` ON `krew_rule_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-451', '2.0.4-SNP', '3:b1f2aac4bd6a63334a860ac4a298046b', 451);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-452::Jeff Caddel (generated)::(Checksum: 3:9dc9680c14f8304479bccfdf91266616)
CREATE UNIQUE INDEX `KREW_RULE_TMPL_ATTR_TC0` ON `krew_rule_tmpl_attr_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-452', '2.0.4-SNP', '3:9dc9680c14f8304479bccfdf91266616', 452);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-453::Jeff Caddel (generated)::(Checksum: 3:b3a8094f36e4e0cacfbd2eeb119a76a2)
CREATE INDEX `KREW_RULE_TMPL_ATTR_TI1` ON `krew_rule_tmpl_attr_t`(`RULE_TMPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-453', '2.0.4-SNP', '3:b3a8094f36e4e0cacfbd2eeb119a76a2', 453);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-454::Jeff Caddel (generated)::(Checksum: 3:b4d3590c76a134a88f9122e488b853b4)
CREATE INDEX `KREW_RULE_TMPL_ATTR_TI2` ON `krew_rule_tmpl_attr_t`(`RULE_ATTR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-454', '2.0.4-SNP', '3:b4d3590c76a134a88f9122e488b853b4', 454);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-455::Jeff Caddel (generated)::(Checksum: 3:5a14f6eeabbb05633c862f7e45ce6917)
CREATE UNIQUE INDEX `KREW_RULE_TMPL_TC0` ON `krew_rule_tmpl_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-455', '2.0.4-SNP', '3:5a14f6eeabbb05633c862f7e45ce6917', 455);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-456::Jeff Caddel (generated)::(Checksum: 3:f19e2057be611ba1cc77f63895398c8b)
CREATE UNIQUE INDEX `KREW_RULE_TMPL_TI1` ON `krew_rule_tmpl_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-456', '2.0.4-SNP', '3:f19e2057be611ba1cc77f63895398c8b', 456);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-457::Jeff Caddel (generated)::(Checksum: 3:89d640b56f3e83c11179b2d3c86427f4)
CREATE UNIQUE INDEX `KREW_STYLE_TC0` ON `krew_style_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-457', '2.0.4-SNP', '3:89d640b56f3e83c11179b2d3c86427f4', 457);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-458::Jeff Caddel (generated)::(Checksum: 3:bdbd32691ce7e7f4f12643b7065a5b2c)
CREATE INDEX `KREW_USR_OPTN_TI1` ON `krew_usr_optn_t`(`PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-458', '2.0.4-SNP', '3:bdbd32691ce7e7f4f12643b7065a5b2c', 458);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-459::Jeff Caddel (generated)::(Checksum: 3:21aff4d33a94b41b5ed7d9a6e4263581)
CREATE UNIQUE INDEX `KRIM_ADDR_TYP_TC0` ON `krim_addr_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-459', '2.0.4-SNP', '3:21aff4d33a94b41b5ed7d9a6e4263581', 459);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-460::Jeff Caddel (generated)::(Checksum: 3:2bb4a01e1bd59910fd62853d2a5eac17)
CREATE UNIQUE INDEX `KRIM_ADDR_TYP_TC1` ON `krim_addr_typ_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-460', '2.0.4-SNP', '3:2bb4a01e1bd59910fd62853d2a5eac17', 460);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-461::Jeff Caddel (generated)::(Checksum: 3:404fb89260c975e93994963b6aa76945)
CREATE UNIQUE INDEX `KRIM_AFLTN_TYP_TC0` ON `krim_afltn_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-461', '2.0.4-SNP', '3:404fb89260c975e93994963b6aa76945', 461);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-462::Jeff Caddel (generated)::(Checksum: 3:f7c5b39173849012c124d4113e9e5928)
CREATE UNIQUE INDEX `KRIM_AFLTN_TYP_TC1` ON `krim_afltn_typ_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-462', '2.0.4-SNP', '3:f7c5b39173849012c124d4113e9e5928', 462);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-463::Jeff Caddel (generated)::(Checksum: 3:3c4ab005419b3996951a084d3058982c)
CREATE UNIQUE INDEX `KRIM_ATTR_DEFN_TC0` ON `krim_attr_defn_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-463', '2.0.4-SNP', '3:3c4ab005419b3996951a084d3058982c', 463);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-464::Jeff Caddel (generated)::(Checksum: 3:6f8321f2cf75e8d11b12e3a4ac41f9fe)
CREATE UNIQUE INDEX `KRIM_CTZNSHP_STAT_TC0` ON `krim_ctznshp_stat_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-464', '2.0.4-SNP', '3:6f8321f2cf75e8d11b12e3a4ac41f9fe', 464);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-465::Jeff Caddel (generated)::(Checksum: 3:b691a416de0985222e069b2165ac5967)
CREATE UNIQUE INDEX `KRIM_CTZNSHP_STAT_TC1` ON `krim_ctznshp_stat_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-465', '2.0.4-SNP', '3:b691a416de0985222e069b2165ac5967', 465);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-466::Jeff Caddel (generated)::(Checksum: 3:5fb91276a4974fa5062a00c5bbd67922)
CREATE UNIQUE INDEX `KRIM_DLGN_MBR_ATTR_DATA_TC0` ON `krim_dlgn_mbr_attr_data_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-466', '2.0.4-SNP', '3:5fb91276a4974fa5062a00c5bbd67922', 466);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-467::Jeff Caddel (generated)::(Checksum: 3:c16fddcd1dae8f3dad6183e50f4d2f84)
CREATE UNIQUE INDEX `KRIM_DLGN_MBR_TC0` ON `krim_dlgn_mbr_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-467', '2.0.4-SNP', '3:c16fddcd1dae8f3dad6183e50f4d2f84', 467);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-468::Jeff Caddel (generated)::(Checksum: 3:f3706f135f14ed14c3a26eee13d187cc)
CREATE UNIQUE INDEX `KRIM_DLGN_TC0` ON `krim_dlgn_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-468', '2.0.4-SNP', '3:f3706f135f14ed14c3a26eee13d187cc', 468);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-469::Jeff Caddel (generated)::(Checksum: 3:435a2dba1f5d0995bdaab69a8fdf05e7)
CREATE UNIQUE INDEX `KRIM_EMAIL_TYP_TC0` ON `krim_email_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-469', '2.0.4-SNP', '3:435a2dba1f5d0995bdaab69a8fdf05e7', 469);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-470::Jeff Caddel (generated)::(Checksum: 3:1d84b8551c98dbe38d2445fc8ff881e8)
CREATE UNIQUE INDEX `KRIM_EMAIL_TYP_TC1` ON `krim_email_typ_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-470', '2.0.4-SNP', '3:1d84b8551c98dbe38d2445fc8ff881e8', 470);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-471::Jeff Caddel (generated)::(Checksum: 3:c6b0ddf900c61ecb632d9be399555a70)
CREATE UNIQUE INDEX `KRIM_EMP_STAT_TC0` ON `krim_emp_stat_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-471', '2.0.4-SNP', '3:c6b0ddf900c61ecb632d9be399555a70', 471);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-472::Jeff Caddel (generated)::(Checksum: 3:648d92db807f0de6a40e34b3c10f30de)
CREATE UNIQUE INDEX `KRIM_EMP_STAT_TC1` ON `krim_emp_stat_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-472', '2.0.4-SNP', '3:648d92db807f0de6a40e34b3c10f30de', 472);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-473::Jeff Caddel (generated)::(Checksum: 3:348906f01c471b134233b11c27f16e56)
CREATE UNIQUE INDEX `KRIM_EMP_TYP_TC0` ON `krim_emp_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-473', '2.0.4-SNP', '3:348906f01c471b134233b11c27f16e56', 473);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-474::Jeff Caddel (generated)::(Checksum: 3:4e48f0cb66db37572fb33d82d0d0606b)
CREATE UNIQUE INDEX `KRIM_EMP_TYP_TC1` ON `krim_emp_typ_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-474', '2.0.4-SNP', '3:4e48f0cb66db37572fb33d82d0d0606b', 474);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-475::Jeff Caddel (generated)::(Checksum: 3:61306a338a9f02108c353fafc9b2d9ae)
CREATE UNIQUE INDEX `KRIM_ENT_NM_TYP_TC0` ON `krim_ent_nm_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-475', '2.0.4-SNP', '3:61306a338a9f02108c353fafc9b2d9ae', 475);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-476::Jeff Caddel (generated)::(Checksum: 3:040d2ed50bdea2156cb89ba1eec1c219)
CREATE UNIQUE INDEX `KRIM_ENT_NM_TYP_TC1` ON `krim_ent_nm_typ_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-476', '2.0.4-SNP', '3:040d2ed50bdea2156cb89ba1eec1c219', 476);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-477::Jeff Caddel (generated)::(Checksum: 3:8c8c37c2b191193c36138609588c1f19)
CREATE UNIQUE INDEX `KRIM_ENT_TYP_TC0` ON `krim_ent_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-477', '2.0.4-SNP', '3:8c8c37c2b191193c36138609588c1f19', 477);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-478::Jeff Caddel (generated)::(Checksum: 3:08f8d794fe87eced2cf7b2510c74f0d7)
CREATE UNIQUE INDEX `KRIM_ENT_TYP_TC1` ON `krim_ent_typ_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-478', '2.0.4-SNP', '3:08f8d794fe87eced2cf7b2510c74f0d7', 478);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-479::Jeff Caddel (generated)::(Checksum: 3:6798a00b538736547cc5b12c6f37f52d)
CREATE UNIQUE INDEX `KRIM_ENTITY_ADDR_TC0` ON `krim_entity_addr_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-479', '2.0.4-SNP', '3:6798a00b538736547cc5b12c6f37f52d', 479);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-480::Jeff Caddel (generated)::(Checksum: 3:a808df69ca028954a04f1e5f2584ab58)
CREATE INDEX `KRIM_ENTITY_ADDR_TI1` ON `krim_entity_addr_t`(`ENTITY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-480', '2.0.4-SNP', '3:a808df69ca028954a04f1e5f2584ab58', 480);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-481::Jeff Caddel (generated)::(Checksum: 3:11c72c28d68560566aaf2a9df382037f)
CREATE UNIQUE INDEX `KRIM_ENTITY_AFLTN_TC0` ON `krim_entity_afltn_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-481', '2.0.4-SNP', '3:11c72c28d68560566aaf2a9df382037f', 481);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-482::Jeff Caddel (generated)::(Checksum: 3:65178857c1399a3411b2497eafb1bfcb)
CREATE UNIQUE INDEX `KRIM_ENTITY_BIO_TC0` ON `krim_entity_bio_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-482', '2.0.4-SNP', '3:65178857c1399a3411b2497eafb1bfcb', 482);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-483::Jeff Caddel (generated)::(Checksum: 3:40e5393f17f307bb7e4bae488c71ff4c)
CREATE UNIQUE INDEX `KRIM_ENTITY_CACHE_TC0` ON `krim_entity_cache_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-483', '2.0.4-SNP', '3:40e5393f17f307bb7e4bae488c71ff4c', 483);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-484::Jeff Caddel (generated)::(Checksum: 3:d6f8f1e6151f489f49259a4555e36343)
CREATE UNIQUE INDEX `KRIM_ENTITY_CACHE_TC1` ON `krim_entity_cache_t`(`PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-484', '2.0.4-SNP', '3:d6f8f1e6151f489f49259a4555e36343', 484);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-485::Jeff Caddel (generated)::(Checksum: 3:4d557d7bddfe053a203e1c767eff2391)
CREATE UNIQUE INDEX `KRIM_ENTITY_CTZNSHP_TC0` ON `krim_entity_ctznshp_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-485', '2.0.4-SNP', '3:4d557d7bddfe053a203e1c767eff2391', 485);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-486::Jeff Caddel (generated)::(Checksum: 3:64d8c52b271ef905fde5585347863f5b)
CREATE UNIQUE INDEX `KRIM_ENTITY_EMAIL_TC0` ON `krim_entity_email_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-486', '2.0.4-SNP', '3:64d8c52b271ef905fde5585347863f5b', 486);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-487::Jeff Caddel (generated)::(Checksum: 3:79a7fe5980fc736b7cc0e5cb6c051544)
CREATE INDEX `KRIM_ENTITY_EMAIL_TI1` ON `krim_entity_email_t`(`ENTITY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-487', '2.0.4-SNP', '3:79a7fe5980fc736b7cc0e5cb6c051544', 487);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-488::Jeff Caddel (generated)::(Checksum: 3:71dbc5f97d57ce345e836dbb82759563)
CREATE UNIQUE INDEX `KRIM_ENTITY_EMP_INFO_TC0` ON `krim_entity_emp_info_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-488', '2.0.4-SNP', '3:71dbc5f97d57ce345e836dbb82759563', 488);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-489::Jeff Caddel (generated)::(Checksum: 3:040f2d8d0e9a2775abb1a7b6243ec00c)
CREATE UNIQUE INDEX `KRIM_ENTITY_ENT_TYP_TC0` ON `krim_entity_ent_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-489', '2.0.4-SNP', '3:040f2d8d0e9a2775abb1a7b6243ec00c', 489);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-490::Jeff Caddel (generated)::(Checksum: 3:9d1c53b554e76548b9638d0df766c250)
CREATE UNIQUE INDEX `KRIM_ENTITY_ETHNIC_TC0` ON `krim_entity_ethnic_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-490', '2.0.4-SNP', '3:9d1c53b554e76548b9638d0df766c250', 490);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-491::Jeff Caddel (generated)::(Checksum: 3:d30d9eb936a56425f25e395af43ea67d)
CREATE UNIQUE INDEX `KRIM_ENTITY_EXT_ID_TC0` ON `krim_entity_ext_id_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-491', '2.0.4-SNP', '3:d30d9eb936a56425f25e395af43ea67d', 491);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-492::Jeff Caddel (generated)::(Checksum: 3:4f3830268a5af346bac4c704304039fe)
CREATE UNIQUE INDEX `KRIM_ENTITY_NM_TC0` ON `krim_entity_nm_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-492', '2.0.4-SNP', '3:4f3830268a5af346bac4c704304039fe', 492);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-493::Jeff Caddel (generated)::(Checksum: 3:3a034a55ddf3770d09e972b1aae1b5bd)
CREATE UNIQUE INDEX `KRIM_ENTITY_PHONE_TC0` ON `krim_entity_phone_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-493', '2.0.4-SNP', '3:3a034a55ddf3770d09e972b1aae1b5bd', 493);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-494::Jeff Caddel (generated)::(Checksum: 3:6670ede3b154db5993cbf5465495f59a)
CREATE INDEX `KRIM_ENTITY_PHONE_TI1` ON `krim_entity_phone_t`(`ENTITY_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-494', '2.0.4-SNP', '3:6670ede3b154db5993cbf5465495f59a', 494);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-495::Jeff Caddel (generated)::(Checksum: 3:b34ff3f6e4a7c7159546efc5aa932069)
CREATE UNIQUE INDEX `KRIM_ENTITY_PRIV_PREF_TC0` ON `krim_entity_priv_pref_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-495', '2.0.4-SNP', '3:b34ff3f6e4a7c7159546efc5aa932069', 495);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-496::Jeff Caddel (generated)::(Checksum: 3:ccdea67a27b34775b46b2fba69056a9d)
CREATE UNIQUE INDEX `KRIM_ENTITY_RESIDENCY_TC0` ON `krim_entity_residency_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-496', '2.0.4-SNP', '3:ccdea67a27b34775b46b2fba69056a9d', 496);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-497::Jeff Caddel (generated)::(Checksum: 3:25e4c59a4e080296fac4c7701d3c6791)
CREATE UNIQUE INDEX `KRIM_ENTITY_TC0` ON `krim_entity_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-497', '2.0.4-SNP', '3:25e4c59a4e080296fac4c7701d3c6791', 497);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-498::Jeff Caddel (generated)::(Checksum: 3:904a9eba2915f5535711f7ac9bbaa073)
CREATE UNIQUE INDEX `KRIM_ENTITY_VISA_TC0` ON `krim_entity_visa_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-498', '2.0.4-SNP', '3:904a9eba2915f5535711f7ac9bbaa073', 498);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-499::Jeff Caddel (generated)::(Checksum: 3:46d90e52247d846eb8f8af61d232a072)
CREATE UNIQUE INDEX `KRIM_EXT_ID_TYP_TC0` ON `krim_ext_id_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-499', '2.0.4-SNP', '3:46d90e52247d846eb8f8af61d232a072', 499);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-500::Jeff Caddel (generated)::(Checksum: 3:7ab6d5afb8ddbb9495208cf19ab13faa)
CREATE UNIQUE INDEX `KRIM_EXT_ID_TYP_TC1` ON `krim_ext_id_typ_t`(`NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-500', '2.0.4-SNP', '3:7ab6d5afb8ddbb9495208cf19ab13faa', 500);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-501::Jeff Caddel (generated)::(Checksum: 3:286aabe8e6fbccdaf67ad4b16434e72a)
CREATE UNIQUE INDEX `KRIM_GRP_ATTR_DATA_TC0` ON `krim_grp_attr_data_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-501', '2.0.4-SNP', '3:286aabe8e6fbccdaf67ad4b16434e72a', 501);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-502::Jeff Caddel (generated)::(Checksum: 3:dc922d01c17860eb55159232ca0b4150)
CREATE UNIQUE INDEX `KRIM_GRP_MBR_TC0` ON `krim_grp_mbr_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-502', '2.0.4-SNP', '3:dc922d01c17860eb55159232ca0b4150', 502);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-503::Jeff Caddel (generated)::(Checksum: 3:f533c35d0e24b83e7d0fc55c262bf472)
CREATE INDEX `KRIM_GRP_MBR_TI1` ON `krim_grp_mbr_t`(`MBR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-503', '2.0.4-SNP', '3:f533c35d0e24b83e7d0fc55c262bf472', 503);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-504::Jeff Caddel (generated)::(Checksum: 3:87463dd9fbeaa2ba81e01fc01a740a3a)
CREATE UNIQUE INDEX `KRIM_GRP_TC0` ON `krim_grp_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-504', '2.0.4-SNP', '3:87463dd9fbeaa2ba81e01fc01a740a3a', 504);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-505::Jeff Caddel (generated)::(Checksum: 3:0848dd20b49392c9a8016fb484ded6f8)
CREATE UNIQUE INDEX `KRIM_GRP_TC1` ON `krim_grp_t`(`GRP_NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-505', '2.0.4-SNP', '3:0848dd20b49392c9a8016fb484ded6f8', 505);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-506::Jeff Caddel (generated)::(Checksum: 3:61c69155530672c5e400ef65eee2b586)
CREATE UNIQUE INDEX `KRIM_PERM_ATTR_DATA_TC0` ON `krim_perm_attr_data_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-506', '2.0.4-SNP', '3:61c69155530672c5e400ef65eee2b586', 506);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-507::Jeff Caddel (generated)::(Checksum: 3:d50e9520736a7fe5d8b8c458055bd7cb)
CREATE UNIQUE INDEX `KRIM_PERM_TC0` ON `krim_perm_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-507', '2.0.4-SNP', '3:d50e9520736a7fe5d8b8c458055bd7cb', 507);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-508::Jeff Caddel (generated)::(Checksum: 3:d5c5769930c12d332fa48362884f60f0)
CREATE UNIQUE INDEX `KRIM_PERM_T_TC1` ON `krim_perm_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-508', '2.0.4-SNP', '3:d5c5769930c12d332fa48362884f60f0', 508);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-509::Jeff Caddel (generated)::(Checksum: 3:872d08804c8cdd7d7660aabcfb8bfbc5)
CREATE UNIQUE INDEX `KRIM_PERM_TMPL_TC0` ON `krim_perm_tmpl_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-509', '2.0.4-SNP', '3:872d08804c8cdd7d7660aabcfb8bfbc5', 509);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-510::Jeff Caddel (generated)::(Checksum: 3:5649d096f1ce6d618d770698034d258d)
CREATE UNIQUE INDEX `KRIM_PERM_TMPL_TC1` ON `krim_perm_tmpl_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-510', '2.0.4-SNP', '3:5649d096f1ce6d618d770698034d258d', 510);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-511::Jeff Caddel (generated)::(Checksum: 3:9c2035ebac7a133ad168d02128ceaa3e)
CREATE UNIQUE INDEX `KRIM_PHONE_TYP_TC0` ON `krim_phone_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-511', '2.0.4-SNP', '3:9c2035ebac7a133ad168d02128ceaa3e', 511);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-512::Jeff Caddel (generated)::(Checksum: 3:3e6006ff097a1c8d69fb858ea70b5bf4)
CREATE UNIQUE INDEX `KRIM_PHONE_TYP_TC1` ON `krim_phone_typ_t`(`PHONE_TYP_NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-512', '2.0.4-SNP', '3:3e6006ff097a1c8d69fb858ea70b5bf4', 512);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-513::Jeff Caddel (generated)::(Checksum: 3:ba84e6b39ee1db04bb0fafc11e9c8d18)
CREATE UNIQUE INDEX `KRIM_PRNCPL_TC0` ON `krim_prncpl_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-513', '2.0.4-SNP', '3:ba84e6b39ee1db04bb0fafc11e9c8d18', 513);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-514::Jeff Caddel (generated)::(Checksum: 3:a57f6f1a9c0dc8a19a958cf52845ea26)
CREATE UNIQUE INDEX `KRIM_PRNCPL_TC1` ON `krim_prncpl_t`(`PRNCPL_NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-514', '2.0.4-SNP', '3:a57f6f1a9c0dc8a19a958cf52845ea26', 514);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-515::Jeff Caddel (generated)::(Checksum: 3:28a5fdb6d50fecc616cd1d3dd4534ff7)
CREATE UNIQUE INDEX `KRIM_ROLE_MBR_ATTR_DATA_TC0` ON `krim_role_mbr_attr_data_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-515', '2.0.4-SNP', '3:28a5fdb6d50fecc616cd1d3dd4534ff7', 515);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-516::Jeff Caddel (generated)::(Checksum: 3:aba9501c5585f59b3dc2434e9e5a7828)
CREATE INDEX `KRIM_ROLE_MBR_ATTR_DATA_TI1` ON `krim_role_mbr_attr_data_t`(`ROLE_MBR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-516', '2.0.4-SNP', '3:aba9501c5585f59b3dc2434e9e5a7828', 516);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-517::Jeff Caddel (generated)::(Checksum: 3:77048db45b81c082ce6e20d011bb7bcc)
CREATE UNIQUE INDEX `KRIM_ROLE_MBR_TC0` ON `krim_role_mbr_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-517', '2.0.4-SNP', '3:77048db45b81c082ce6e20d011bb7bcc', 517);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-518::Jeff Caddel (generated)::(Checksum: 3:e63a7f4d60239ce477f3e49899dfd701)
CREATE INDEX `KRIM_ROLE_MBR_TI1` ON `krim_role_mbr_t`(`MBR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-518', '2.0.4-SNP', '3:e63a7f4d60239ce477f3e49899dfd701', 518);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-519::Jeff Caddel (generated)::(Checksum: 3:856964cdf8f54c2ad31d04d18b15aed0)
CREATE UNIQUE INDEX `KRIM_ROLE_PERM_TC0` ON `krim_role_perm_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-519', '2.0.4-SNP', '3:856964cdf8f54c2ad31d04d18b15aed0', 519);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-520::Jeff Caddel (generated)::(Checksum: 3:d13d41185b1ebae9f730cd7e866ea08b)
CREATE UNIQUE INDEX `KRIM_ROLE_RSP_ACTN_TC0` ON `krim_role_rsp_actn_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-520', '2.0.4-SNP', '3:d13d41185b1ebae9f730cd7e866ea08b', 520);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-521::Jeff Caddel (generated)::(Checksum: 3:5fc0779c5223b406f917c2e2f49a72ee)
CREATE UNIQUE INDEX `KRIM_ROLE_RSP_ACTN_TC1` ON `krim_role_rsp_actn_t`(`ROLE_RSP_ID`, `ROLE_MBR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-521', '2.0.4-SNP', '3:5fc0779c5223b406f917c2e2f49a72ee', 521);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-522::Jeff Caddel (generated)::(Checksum: 3:61f899cf10e33bf4f0ffebf76f907ece)
CREATE UNIQUE INDEX `KRIM_ROLE_RSP_TC0` ON `krim_role_rsp_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-522', '2.0.4-SNP', '3:61f899cf10e33bf4f0ffebf76f907ece', 522);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-523::Jeff Caddel (generated)::(Checksum: 3:3459b7b1c054a631b012cc332a2e67a1)
CREATE UNIQUE INDEX `KRIM_ROLE_TC0` ON `krim_role_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-523', '2.0.4-SNP', '3:3459b7b1c054a631b012cc332a2e67a1', 523);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-524::Jeff Caddel (generated)::(Checksum: 3:b1d64c1e4364d2717ad7eb4eafbb3a91)
CREATE UNIQUE INDEX `KRIM_ROLE_TC1` ON `krim_role_t`(`ROLE_NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-524', '2.0.4-SNP', '3:b1d64c1e4364d2717ad7eb4eafbb3a91', 524);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-525::Jeff Caddel (generated)::(Checksum: 3:389b3c2ae0ac62e3d09fe031067f5c5f)
CREATE UNIQUE INDEX `KRIM_RSP_ATTR_DATA_TC0` ON `krim_rsp_attr_data_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-525', '2.0.4-SNP', '3:389b3c2ae0ac62e3d09fe031067f5c5f', 525);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-526::Jeff Caddel (generated)::(Checksum: 3:8b6735bef7a2dd590e4e038e5d78dbab)
CREATE UNIQUE INDEX `KRIM_RSP_TC0` ON `krim_rsp_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-526', '2.0.4-SNP', '3:8b6735bef7a2dd590e4e038e5d78dbab', 526);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-527::Jeff Caddel (generated)::(Checksum: 3:e5308957ca4ba95597a203ee5bc3f1bd)
CREATE UNIQUE INDEX `KRIM_RSP_T_TC1` ON `krim_rsp_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-527', '2.0.4-SNP', '3:e5308957ca4ba95597a203ee5bc3f1bd', 527);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-528::Jeff Caddel (generated)::(Checksum: 3:ed870e65a9378d624f791e1d5ba92a81)
CREATE UNIQUE INDEX `KRIM_RSP_TMPL_TC0` ON `krim_rsp_tmpl_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-528', '2.0.4-SNP', '3:ed870e65a9378d624f791e1d5ba92a81', 528);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-529::Jeff Caddel (generated)::(Checksum: 3:bbb401057a0ea6a99931431d3d428071)
CREATE UNIQUE INDEX `KRIM_RSP_TMPL_TC1` ON `krim_rsp_tmpl_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-529', '2.0.4-SNP', '3:bbb401057a0ea6a99931431d3d428071', 529);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-530::Jeff Caddel (generated)::(Checksum: 3:990b19ec2b9578025eca52780f20e645)
CREATE UNIQUE INDEX `KRIM_TYP_ATTR_TC0` ON `krim_typ_attr_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-530', '2.0.4-SNP', '3:990b19ec2b9578025eca52780f20e645', 530);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-531::Jeff Caddel (generated)::(Checksum: 3:be3a2b6a31426d1eb4e057b7b27cea98)
CREATE UNIQUE INDEX `KRIM_TYP_TC0` ON `krim_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-531', '2.0.4-SNP', '3:be3a2b6a31426d1eb4e057b7b27cea98', 531);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-532::Jeff Caddel (generated)::(Checksum: 3:8f961a441246ca55aade55c775fd0abc)
CREATE UNIQUE INDEX `KRNS_CAMPUS_TC0` ON `krlc_cmp_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-532', '2.0.4-SNP', '3:8f961a441246ca55aade55c775fd0abc', 532);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-533::Jeff Caddel (generated)::(Checksum: 3:72ea5ffbe416b2cbdab88163e5fa41ec)
CREATE UNIQUE INDEX `KRNS_CMP_TYP_TC0` ON `krlc_cmp_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-533', '2.0.4-SNP', '3:72ea5ffbe416b2cbdab88163e5fa41ec', 533);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-534::Jeff Caddel (generated)::(Checksum: 3:ee6b1c2ceafaa8f1668e479e717df26c)
CREATE UNIQUE INDEX `KR_COUNTRY_TC0` ON `krlc_cntry_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-534', '2.0.4-SNP', '3:ee6b1c2ceafaa8f1668e479e717df26c', 534);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-535::Jeff Caddel (generated)::(Checksum: 3:93d2d3db821a6956fce68773aa394924)
CREATE UNIQUE INDEX `KR_COUNTY_TC0` ON `krlc_cnty_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-535', '2.0.4-SNP', '3:93d2d3db821a6956fce68773aa394924', 535);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-536::Jeff Caddel (generated)::(Checksum: 3:ddb3e93b81beb5995a8bbf232f1fa0f6)
CREATE UNIQUE INDEX `KR_POSTAL_CODE_TC0` ON `krlc_pstl_cd_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-536', '2.0.4-SNP', '3:ddb3e93b81beb5995a8bbf232f1fa0f6', 536);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-537::Jeff Caddel (generated)::(Checksum: 3:1051cf9aa2ce201cb9b9f8b45ddaa556)
CREATE UNIQUE INDEX `KR_STATE_TC0` ON `krlc_st_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-537', '2.0.4-SNP', '3:1051cf9aa2ce201cb9b9f8b45ddaa556', 537);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-538::Jeff Caddel (generated)::(Checksum: 3:3447c8ed31f82e502cf3f176cd2d0784)
CREATE UNIQUE INDEX `KRMS_ACTN_TC2` ON `krms_actn_t`(`ACTN_ID`, `RULE_ID`, `SEQ_NO`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-538', '2.0.4-SNP', '3:3447c8ed31f82e502cf3f176cd2d0784', 538);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-539::Jeff Caddel (generated)::(Checksum: 3:59d2ccc8d309308099f093a5141c2dd2)
CREATE INDEX `KRMS_ACTN_TI1` ON `krms_actn_t`(`TYP_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-539', '2.0.4-SNP', '3:59d2ccc8d309308099f093a5141c2dd2', 539);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-540::Jeff Caddel (generated)::(Checksum: 3:4d1f7fcccfc27e8ac677d863c3e5a7ba)
CREATE INDEX `KRMS_ACTN_TI3` ON `krms_actn_t`(`RULE_ID`, `SEQ_NO`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-540', '2.0.4-SNP', '3:4d1f7fcccfc27e8ac677d863c3e5a7ba', 540);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-541::Jeff Caddel (generated)::(Checksum: 3:cb79af57029799c6cb697d0c88f11463)
CREATE UNIQUE INDEX `KRMS_AGENDA_TC1` ON `krms_agenda_t`(`NM`, `CNTXT_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-541', '2.0.4-SNP', '3:cb79af57029799c6cb697d0c88f11463', 541);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-542::Jeff Caddel (generated)::(Checksum: 3:f0358b9dafb8ec9fe5b9e2ee34d86407)
CREATE UNIQUE INDEX `KRMS_ATTR_DEFN_TC1` ON `krms_attr_defn_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-542', '2.0.4-SNP', '3:f0358b9dafb8ec9fe5b9e2ee34d86407', 542);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-543::Jeff Caddel (generated)::(Checksum: 3:a3c6ffeeff7aa0369e5c7996430978d8)
CREATE UNIQUE INDEX `KRMS_CNTXT_TC1` ON `krms_cntxt_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-543', '2.0.4-SNP', '3:a3c6ffeeff7aa0369e5c7996430978d8', 543);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-544::Jeff Caddel (generated)::(Checksum: 3:c8121d92fe74c35a6836cb320b75515b)
CREATE UNIQUE INDEX `KRMS_CTGRY_TC0` ON `krms_ctgry_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-544', '2.0.4-SNP', '3:c8121d92fe74c35a6836cb320b75515b', 544);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-545::Jeff Caddel (generated)::(Checksum: 3:174ad4264415f4616111bb6f2c6899da)
CREATE UNIQUE INDEX `KRMS_FUNC_TC1` ON `krms_func_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-545', '2.0.4-SNP', '3:174ad4264415f4616111bb6f2c6899da', 545);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-546::Jeff Caddel (generated)::(Checksum: 3:c6a805a2dfe3542c68095077b27d5058)
CREATE UNIQUE INDEX `KRMS_RULE_TC1` ON `krms_rule_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-546', '2.0.4-SNP', '3:c6a805a2dfe3542c68095077b27d5058', 546);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-547::Jeff Caddel (generated)::(Checksum: 3:67bfb2499534481ba16edc664f787c8b)
CREATE UNIQUE INDEX `KRMS_TERM_RSLVR_TC1` ON `krms_term_rslvr_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-547', '2.0.4-SNP', '3:67bfb2499534481ba16edc664f787c8b', 547);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-548::Jeff Caddel (generated)::(Checksum: 3:70b8cbd4edafe89ec12479f1a05bf719)
CREATE UNIQUE INDEX `KRMS_TERM_SPEC_TC1` ON `krms_term_spec_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-548', '2.0.4-SNP', '3:70b8cbd4edafe89ec12479f1a05bf719', 548);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-549::Jeff Caddel (generated)::(Checksum: 3:a00a0f0fa901afa4d18d3e7e922daef1)
CREATE UNIQUE INDEX `KRMS_TYP_ATTR_TC1` ON `krms_typ_attr_t`(`TYP_ID`, `ATTR_DEFN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-549', '2.0.4-SNP', '3:a00a0f0fa901afa4d18d3e7e922daef1', 549);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-550::Jeff Caddel (generated)::(Checksum: 3:de8ca3f1982494c801b1130cc987dae1)
CREATE UNIQUE INDEX `KRMS_TYP_TC1` ON `krms_typ_t`(`NM`, `NMSPC_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-550', '2.0.4-SNP', '3:de8ca3f1982494c801b1130cc987dae1', 550);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-551::Jeff Caddel (generated)::(Checksum: 3:8ae8fca9de9eff25f802231b90b793b8)
CREATE INDEX `KRNS_ADHOC_RTE_ACTN_RECIP_T2` ON `krns_adhoc_rte_actn_recip_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-551', '2.0.4-SNP', '3:8ae8fca9de9eff25f802231b90b793b8', 551);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-552::Jeff Caddel (generated)::(Checksum: 3:1880c11caf9695f24081897091b4fe13)
CREATE UNIQUE INDEX `KRNS_ADHOC_RTE_ACTN_RECIP_TC0` ON `krns_adhoc_rte_actn_recip_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-552', '2.0.4-SNP', '3:1880c11caf9695f24081897091b4fe13', 552);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-553::Jeff Caddel (generated)::(Checksum: 3:3424e5c423577d07da3ff26ac45165f0)
CREATE UNIQUE INDEX `KRNS_ATT_TC0` ON `krns_att_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-553', '2.0.4-SNP', '3:3424e5c423577d07da3ff26ac45165f0', 553);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-554::Jeff Caddel (generated)::(Checksum: 3:f0c24f532946f1d60bf939a01020d86c)
CREATE UNIQUE INDEX `KRNS_DOC_HDR_TC0` ON `krns_doc_hdr_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-554', '2.0.4-SNP', '3:f0c24f532946f1d60bf939a01020d86c', 554);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-555::Jeff Caddel (generated)::(Checksum: 3:e4c942a8c46a6ee06505cdbd9ed82348)
CREATE INDEX `KRNS_DOC_HDR_TI3` ON `krns_doc_hdr_t`(`ORG_DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-555', '2.0.4-SNP', '3:e4c942a8c46a6ee06505cdbd9ed82348', 555);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-556::Jeff Caddel (generated)::(Checksum: 3:43ce2acd482e2f7779359b5d2280cc17)
CREATE UNIQUE INDEX `KRNS_LOOKUP_RSLT_TC0` ON `krns_lookup_rslt_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-556', '2.0.4-SNP', '3:43ce2acd482e2f7779359b5d2280cc17', 556);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-557::Jeff Caddel (generated)::(Checksum: 3:6b54e6beac8cac10a24cea8423c5aa5c)
CREATE UNIQUE INDEX `KRNS_LOOKUP_SEL_TC0` ON `krns_lookup_sel_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-557', '2.0.4-SNP', '3:6b54e6beac8cac10a24cea8423c5aa5c', 557);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-558::Jeff Caddel (generated)::(Checksum: 3:5b0170f1ecd280cebc1aa5ebb071f966)
CREATE UNIQUE INDEX `KRNS_MAINT_DOC_ATT_TC0` ON `krns_maint_doc_att_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-558', '2.0.4-SNP', '3:5b0170f1ecd280cebc1aa5ebb071f966', 558);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-559::Jeff Caddel (generated)::(Checksum: 3:e569a49397a9132c364bffd2c778ab33)
CREATE UNIQUE INDEX `KRNS_MAINT_DOC_TC0` ON `krns_maint_doc_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-559', '2.0.4-SNP', '3:e569a49397a9132c364bffd2c778ab33', 559);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-560::Jeff Caddel (generated)::(Checksum: 3:0386987647ed8ccec89168c909409ce7)
CREATE UNIQUE INDEX `KRNS_MAINT_LOCK_TC0` ON `krns_maint_lock_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-560', '2.0.4-SNP', '3:0386987647ed8ccec89168c909409ce7', 560);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-561::Jeff Caddel (generated)::(Checksum: 3:a70d27b4dd978cc997171f148b802a41)
CREATE INDEX `KRNS_MAINT_LOCK_TI2` ON `krns_maint_lock_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-561', '2.0.4-SNP', '3:a70d27b4dd978cc997171f148b802a41', 561);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-562::Jeff Caddel (generated)::(Checksum: 3:ec33accb71616471d4e9c56556b2e5d1)
CREATE UNIQUE INDEX `KRNS_NTE_TC0` ON `krns_nte_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-562', '2.0.4-SNP', '3:ec33accb71616471d4e9c56556b2e5d1', 562);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-563::Jeff Caddel (generated)::(Checksum: 3:f882e4c211f1f1e9e8b31c880bea97ff)
CREATE UNIQUE INDEX `KRNS_NTE_TYP_TC0` ON `krns_nte_typ_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-563', '2.0.4-SNP', '3:f882e4c211f1f1e9e8b31c880bea97ff', 563);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-564::Jeff Caddel (generated)::(Checksum: 3:76624c91b1a3ad84f4bb208deda52b75)
CREATE UNIQUE INDEX `KRNS_PESSIMISTIC_LOCK_TC0` ON `krns_pessimistic_lock_t`(`OBJ_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-564', '2.0.4-SNP', '3:76624c91b1a3ad84f4bb208deda52b75', 564);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-565::Jeff Caddel (generated)::(Checksum: 3:51680ed5951b8860766cad1e1a8e9368)
CREATE INDEX `KRNS_PESSIMISTIC_LOCK_TI1` ON `krns_pessimistic_lock_t`(`DOC_HDR_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-565', '2.0.4-SNP', '3:51680ed5951b8860766cad1e1a8e9368', 565);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-566::Jeff Caddel (generated)::(Checksum: 3:90980bc1f7467098c59f98cd28f7db0e)
CREATE INDEX `KRNS_PESSIMISTIC_LOCK_TI2` ON `krns_pessimistic_lock_t`(`PRNCPL_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-566', '2.0.4-SNP', '3:90980bc1f7467098c59f98cd28f7db0e', 566);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-567::Jeff Caddel (generated)::(Checksum: 3:c396eee38da79de6f1e2e455a5f9ac26)
CREATE INDEX `KRNS_SESN_DOC_TI1` ON `krns_sesn_doc_t`(`LAST_UPDT_DT`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-567', '2.0.4-SNP', '3:c396eee38da79de6f1e2e455a5f9ac26', 567);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-568::Jeff Caddel (generated)::(Checksum: 3:b374abb68bc6b86eb07868f20fb57e91)
CREATE INDEX `KREW_BAM_PARM_TI1` ON `krsb_bam_parm_t`(`BAM_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-568', '2.0.4-SNP', '3:b374abb68bc6b86eb07868f20fb57e91', 568);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-569::Jeff Caddel (generated)::(Checksum: 3:7d01ac73decf3a8f14e02d385c6dc0d3)
CREATE INDEX `KRSB_BAM_TI1` ON `krsb_bam_t`(`SVC_NM`, `MTHD_NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-569', '2.0.4-SNP', '3:7d01ac73decf3a8f14e02d385c6dc0d3', 569);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-570::Jeff Caddel (generated)::(Checksum: 3:90331fabbe7cd45374f35fefc6079880)
CREATE INDEX `KRSB_BAM_TI2` ON `krsb_bam_t`(`SVC_NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-570', '2.0.4-SNP', '3:90331fabbe7cd45374f35fefc6079880', 570);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-571::Jeff Caddel (generated)::(Checksum: 3:e0694465042669b2d51054f94deed208)
CREATE INDEX `KRSB_MSG_QUE_TI1` ON `krsb_msg_que_t`(`SVC_NM`, `SVC_MTHD_NM`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-571', '2.0.4-SNP', '3:e0694465042669b2d51054f94deed208', 571);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-572::Jeff Caddel (generated)::(Checksum: 3:2e9abdf6a863fc84c4b1414b5abd4ec0)
CREATE INDEX `KRSB_MSG_QUE_TI2` ON `krsb_msg_que_t`(`APPL_ID`, `STAT_CD`, `IP_NBR`, `DT`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-572', '2.0.4-SNP', '3:2e9abdf6a863fc84c4b1414b5abd4ec0', 572);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-573::Jeff Caddel (generated)::(Checksum: 3:3ba5ab361bff0640ba5f5d7bcddfbabf)
CREATE INDEX `KRSB_QRTZ_FIRED_TRIGGERS_TI1` ON `krsb_qrtz_fired_triggers`(`JOB_GROUP`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-573', '2.0.4-SNP', '3:3ba5ab361bff0640ba5f5d7bcddfbabf', 573);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-574::Jeff Caddel (generated)::(Checksum: 3:bde9e7bb25940c64405779e91f9a1a72)
CREATE INDEX `KRSB_QRTZ_FIRED_TRIGGERS_TI2` ON `krsb_qrtz_fired_triggers`(`JOB_NAME`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-574', '2.0.4-SNP', '3:bde9e7bb25940c64405779e91f9a1a72', 574);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-575::Jeff Caddel (generated)::(Checksum: 3:2d80e5bebcde755510b92bf7198d419e)
CREATE INDEX `KRSB_QRTZ_FIRED_TRIGGERS_TI3` ON `krsb_qrtz_fired_triggers`(`REQUESTS_RECOVERY`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-575', '2.0.4-SNP', '3:2d80e5bebcde755510b92bf7198d419e', 575);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-576::Jeff Caddel (generated)::(Checksum: 3:4b792d811e8c6c3852b8c10a11bad354)
CREATE INDEX `KRSB_QRTZ_FIRED_TRIGGERS_TI4` ON `krsb_qrtz_fired_triggers`(`IS_STATEFUL`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-576', '2.0.4-SNP', '3:4b792d811e8c6c3852b8c10a11bad354', 576);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-577::Jeff Caddel (generated)::(Checksum: 3:5b61a714e3bab98316faaf2d265e7109)
CREATE INDEX `KRSB_QRTZ_FIRED_TRIGGERS_TI5` ON `krsb_qrtz_fired_triggers`(`TRIGGER_GROUP`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-577', '2.0.4-SNP', '3:5b61a714e3bab98316faaf2d265e7109', 577);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-578::Jeff Caddel (generated)::(Checksum: 3:cdb384d8b0f5b7aa80f78ef43814daf1)
CREATE INDEX `KRSB_QRTZ_FIRED_TRIGGERS_TI6` ON `krsb_qrtz_fired_triggers`(`INSTANCE_NAME`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-578', '2.0.4-SNP', '3:cdb384d8b0f5b7aa80f78ef43814daf1', 578);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-579::Jeff Caddel (generated)::(Checksum: 3:85f4582131ea48ec85998954516e626a)
CREATE INDEX `KRSB_QRTZ_FIRED_TRIGGERS_TI7` ON `krsb_qrtz_fired_triggers`(`TRIGGER_NAME`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-579', '2.0.4-SNP', '3:85f4582131ea48ec85998954516e626a', 579);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-580::Jeff Caddel (generated)::(Checksum: 3:d855ac045c2747c5489af6fb4fbcf4c9)
CREATE INDEX `KRSB_QRTZ_FIRED_TRIGGERS_TI8` ON `krsb_qrtz_fired_triggers`(`TRIGGER_NAME`, `TRIGGER_GROUP`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-580', '2.0.4-SNP', '3:d855ac045c2747c5489af6fb4fbcf4c9', 580);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-581::Jeff Caddel (generated)::(Checksum: 3:76590b94b7d4cfbaf14dff44e0ec8fe9)
CREATE INDEX `KRSB_QRTZ_FIRED_TRIGGERS_TI9` ON `krsb_qrtz_fired_triggers`(`IS_VOLATILE`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-581', '2.0.4-SNP', '3:76590b94b7d4cfbaf14dff44e0ec8fe9', 581);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-582::Jeff Caddel (generated)::(Checksum: 3:c632cc0be1ef517aa9ed2f0894763f98)
CREATE INDEX `KRSB_QRTZ_JOB_DETAILS_TI1` ON `krsb_qrtz_job_details`(`REQUESTS_RECOVERY`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-582', '2.0.4-SNP', '3:c632cc0be1ef517aa9ed2f0894763f98', 582);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-583::Jeff Caddel (generated)::(Checksum: 3:2aa66d332d2195cc70ad54ef2906ed29)
CREATE INDEX `KRSB_QRTZ_TRIGGERS_TI1` ON `krsb_qrtz_triggers`(`NEXT_FIRE_TIME`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-583', '2.0.4-SNP', '3:2aa66d332d2195cc70ad54ef2906ed29', 583);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-584::Jeff Caddel (generated)::(Checksum: 3:ef2f23f75978fb9c417f4be039c03a88)
CREATE INDEX `KRSB_QRTZ_TRIGGERS_TI2` ON `krsb_qrtz_triggers`(`NEXT_FIRE_TIME`, `TRIGGER_STATE`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-584', '2.0.4-SNP', '3:ef2f23f75978fb9c417f4be039c03a88', 584);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-585::Jeff Caddel (generated)::(Checksum: 3:c17872ee1e9125b001639a9ac40d0e1d)
CREATE INDEX `KRSB_QRTZ_TRIGGERS_TI3` ON `krsb_qrtz_triggers`(`TRIGGER_STATE`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-585', '2.0.4-SNP', '3:c17872ee1e9125b001639a9ac40d0e1d', 585);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-586::Jeff Caddel (generated)::(Checksum: 3:977288613ec7d93049b689b40da96149)
CREATE INDEX `KRSB_QRTZ_TRIGGERS_TI4` ON `krsb_qrtz_triggers`(`IS_VOLATILE`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-586', '2.0.4-SNP', '3:977288613ec7d93049b689b40da96149', 586);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-587::Jeff Caddel (generated)::(Checksum: 3:e9a55442ffd28be151fb5df69bc0175a)
CREATE INDEX `KRSB_SVC_DEF_TI1` ON `krsb_svc_def_t`(`INSTN_ID`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-587', '2.0.4-SNP', '3:e9a55442ffd28be151fb5df69bc0175a', 587);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-588::Jeff Caddel (generated)::(Checksum: 3:528fee48b0181605d4796d4786ef057c)
CREATE INDEX `KRSB_SVC_DEF_TI2` ON `krsb_svc_def_t`(`SVC_NM`, `STAT_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-588', '2.0.4-SNP', '3:528fee48b0181605d4796d4786ef057c', 588);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-589::Jeff Caddel (generated)::(Checksum: 3:c0c05821dea2cd7243d42f127202d8c7)
CREATE INDEX `KRSB_SVC_DEF_TI3` ON `krsb_svc_def_t`(`STAT_CD`);

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create Index', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-589', '2.0.4-SNP', '3:c0c05821dea2cd7243d42f127202d8c7', 589);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-590::Jeff Caddel (generated)::(Checksum: 3:ce8fa5603385832becbc876a58fd6937)
ALTER TABLE `RICE`.`krcr_cmpnt_t` ADD CONSTRAINT `KRNS_PARM_DTL_TYP_TR1` FOREIGN KEY (`NMSPC_CD`) REFERENCES `RICE`.`krcr_nmspc_t` (`NMSPC_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-590', '2.0.4-SNP', '3:ce8fa5603385832becbc876a58fd6937', 590);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-591::Jeff Caddel (generated)::(Checksum: 3:275e245e6886e5408cc4dac8b76ca529)
ALTER TABLE `RICE`.`krcr_parm_t` ADD CONSTRAINT `KRNS_PARM_TR1` FOREIGN KEY (`NMSPC_CD`) REFERENCES `RICE`.`krcr_nmspc_t` (`NMSPC_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-591', '2.0.4-SNP', '3:275e245e6886e5408cc4dac8b76ca529', 591);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-592::Jeff Caddel (generated)::(Checksum: 3:20a8977c4d8f8651beec4cd3fd508f53)
ALTER TABLE `RICE`.`krcr_parm_t` ADD CONSTRAINT `KRNS_PARM_TR2` FOREIGN KEY (`PARM_TYP_CD`) REFERENCES `RICE`.`krcr_parm_typ_t` (`PARM_TYP_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-592', '2.0.4-SNP', '3:20a8977c4d8f8651beec4cd3fd508f53', 592);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-593::Jeff Caddel (generated)::(Checksum: 3:5c712a1c00c4a5792c3ed3d386408836)
ALTER TABLE `RICE`.`kren_chnl_prodcr_t` ADD CONSTRAINT `KREN_CHNL_PRODCR_FK1` FOREIGN KEY (`CHNL_ID`) REFERENCES `RICE`.`kren_chnl_t` (`CHNL_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-593', '2.0.4-SNP', '3:5c712a1c00c4a5792c3ed3d386408836', 593);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-594::Jeff Caddel (generated)::(Checksum: 3:28a0404405af1b470e59512f501cb2fe)
ALTER TABLE `RICE`.`kren_chnl_prodcr_t` ADD CONSTRAINT `KREN_CHNL_PRODCR_FK2` FOREIGN KEY (`PRODCR_ID`) REFERENCES `RICE`.`kren_prodcr_t` (`PRODCR_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-594', '2.0.4-SNP', '3:28a0404405af1b470e59512f501cb2fe', 594);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-595::Jeff Caddel (generated)::(Checksum: 3:943fc53556237b0030f6e8e2e5f5401a)
ALTER TABLE `RICE`.`kren_chnl_subscrp_t` ADD CONSTRAINT `KREN_CHAN_SUBSCRP_FK1` FOREIGN KEY (`CHNL_ID`) REFERENCES `RICE`.`kren_chnl_t` (`CHNL_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-595', '2.0.4-SNP', '3:943fc53556237b0030f6e8e2e5f5401a', 595);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-596::Jeff Caddel (generated)::(Checksum: 3:ca98cdd6ad3e2aaad491a42642c20d79)
ALTER TABLE `RICE`.`kren_msg_deliv_t` ADD CONSTRAINT `KREN_MSG_DELIV_FK1` FOREIGN KEY (`MSG_ID`) REFERENCES `RICE`.`kren_msg_t` (`MSG_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-596', '2.0.4-SNP', '3:ca98cdd6ad3e2aaad491a42642c20d79', 596);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-597::Jeff Caddel (generated)::(Checksum: 3:d0fd0ae6a82043a57e25de9eab836996)
ALTER TABLE `RICE`.`kren_ntfctn_msg_deliv_t` ADD CONSTRAINT `KREN_NTFCTN_MSG_DELIV_FK1` FOREIGN KEY (`NTFCTN_ID`) REFERENCES `RICE`.`kren_ntfctn_t` (`NTFCTN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-597', '2.0.4-SNP', '3:d0fd0ae6a82043a57e25de9eab836996', 597);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-598::Jeff Caddel (generated)::(Checksum: 3:a6d5c4406d762a8c783e8ecb85015acf)
ALTER TABLE `RICE`.`kren_ntfctn_t` ADD CONSTRAINT `KREN_NTFCTN_FK1` FOREIGN KEY (`CHNL_ID`) REFERENCES `RICE`.`kren_chnl_t` (`CHNL_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-598', '2.0.4-SNP', '3:a6d5c4406d762a8c783e8ecb85015acf', 598);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-599::Jeff Caddel (generated)::(Checksum: 3:aa3f6c7a1570f4ad5c10421b7ff879d3)
ALTER TABLE `RICE`.`kren_ntfctn_t` ADD CONSTRAINT `KREN_NTFCTN_FK2` FOREIGN KEY (`CNTNT_TYP_ID`) REFERENCES `RICE`.`kren_cntnt_typ_t` (`CNTNT_TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-599', '2.0.4-SNP', '3:aa3f6c7a1570f4ad5c10421b7ff879d3', 599);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-600::Jeff Caddel (generated)::(Checksum: 3:3e2f965ba5a3f0580a83a375e3be97f0)
ALTER TABLE `RICE`.`kren_ntfctn_t` ADD CONSTRAINT `KREN_NTFCTN_FK3` FOREIGN KEY (`PRIO_ID`) REFERENCES `RICE`.`kren_prio_t` (`PRIO_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-600', '2.0.4-SNP', '3:3e2f965ba5a3f0580a83a375e3be97f0', 600);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-601::Jeff Caddel (generated)::(Checksum: 3:e08cb3b4c0abf4306df177eea3d10d6a)
ALTER TABLE `RICE`.`kren_ntfctn_t` ADD CONSTRAINT `KREN_NTFCTN_FK4` FOREIGN KEY (`PRODCR_ID`) REFERENCES `RICE`.`kren_prodcr_t` (`PRODCR_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-601', '2.0.4-SNP', '3:e08cb3b4c0abf4306df177eea3d10d6a', 601);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-602::Jeff Caddel (generated)::(Checksum: 3:bec2c93639e78bdda399b5476890d96f)
ALTER TABLE `RICE`.`kren_recip_list_t` ADD CONSTRAINT `KREN_RECIP_LIST_FK1` FOREIGN KEY (`CHNL_ID`) REFERENCES `RICE`.`kren_chnl_t` (`CHNL_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-602', '2.0.4-SNP', '3:bec2c93639e78bdda399b5476890d96f', 602);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-603::Jeff Caddel (generated)::(Checksum: 3:02d3a5d30cb67724775adc8bb7fcda88)
ALTER TABLE `RICE`.`kren_recip_t` ADD CONSTRAINT `KREN_RECIP_FK1` FOREIGN KEY (`NTFCTN_ID`) REFERENCES `RICE`.`kren_ntfctn_t` (`NTFCTN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-603', '2.0.4-SNP', '3:02d3a5d30cb67724775adc8bb7fcda88', 603);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-604::Jeff Caddel (generated)::(Checksum: 3:34cc4a55a081f6a80422752e2adb0c47)
ALTER TABLE `RICE`.`kren_rvwer_t` ADD CONSTRAINT `NOTIFICATION_REVIEWERS_N_FK1` FOREIGN KEY (`CHNL_ID`) REFERENCES `RICE`.`kren_chnl_t` (`CHNL_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-604', '2.0.4-SNP', '3:34cc4a55a081f6a80422752e2adb0c47', 604);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-605::Jeff Caddel (generated)::(Checksum: 3:e462f76b3b18d57f9774851451dd2f1e)
ALTER TABLE `RICE`.`kren_sndr_t` ADD CONSTRAINT `KREN_SNDR_FK1` FOREIGN KEY (`NTFCTN_ID`) REFERENCES `RICE`.`kren_ntfctn_t` (`NTFCTN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-605', '2.0.4-SNP', '3:e462f76b3b18d57f9774851451dd2f1e', 605);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-606::Jeff Caddel (generated)::(Checksum: 3:88ef0406c1b75224bb2aa92be1437aa3)
ALTER TABLE `RICE`.`krew_rte_node_cfg_parm_t` ADD CONSTRAINT `EN_RTE_NODE_CFG_PARM_TR1` FOREIGN KEY (`RTE_NODE_ID`) REFERENCES `RICE`.`krew_rte_node_t` (`RTE_NODE_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-606', '2.0.4-SNP', '3:88ef0406c1b75224bb2aa92be1437aa3', 606);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-607::Jeff Caddel (generated)::(Checksum: 3:911f44a21f1468727cb3cd42706f3062)
ALTER TABLE `RICE`.`krew_rule_t` ADD CONSTRAINT `KREW_RULE_TR1` FOREIGN KEY (`RULE_EXPR_ID`) REFERENCES `RICE`.`krew_rule_expr_t` (`RULE_EXPR_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-607', '2.0.4-SNP', '3:911f44a21f1468727cb3cd42706f3062', 607);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-608::Jeff Caddel (generated)::(Checksum: 3:6424e4581e093a7f4e22014f1afc0196)
ALTER TABLE `RICE`.`krim_dlgn_mbr_attr_data_t` ADD CONSTRAINT `KRIM_DLGN_MBR_ATTR_DATA_TR2` FOREIGN KEY (`KIM_ATTR_DEFN_ID`) REFERENCES `RICE`.`krim_attr_defn_t` (`KIM_ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-608', '2.0.4-SNP', '3:6424e4581e093a7f4e22014f1afc0196', 608);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-609::Jeff Caddel (generated)::(Checksum: 3:f72da03548dd0f283176f09596e20639)
ALTER TABLE `RICE`.`krim_dlgn_mbr_attr_data_t` ADD CONSTRAINT `KRIM_DLGN_MBR_ATTR_DATA_TR1` FOREIGN KEY (`KIM_TYP_ID`) REFERENCES `RICE`.`krim_typ_t` (`KIM_TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-609', '2.0.4-SNP', '3:f72da03548dd0f283176f09596e20639', 609);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-610::Jeff Caddel (generated)::(Checksum: 3:feecd6449f6fe62a2c03675cda2b70fe)
ALTER TABLE `RICE`.`krim_dlgn_mbr_t` ADD CONSTRAINT `KRIM_DLGN_MBR_TR2` FOREIGN KEY (`DLGN_ID`) REFERENCES `RICE`.`krim_dlgn_t` (`DLGN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-610', '2.0.4-SNP', '3:feecd6449f6fe62a2c03675cda2b70fe', 610);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-611::Jeff Caddel (generated)::(Checksum: 3:83e3b1d9aec8624c64ddcd6634756a66)
ALTER TABLE `RICE`.`krim_dlgn_t` ADD CONSTRAINT `KRIM_DLGN_TR2` FOREIGN KEY (`KIM_TYP_ID`) REFERENCES `RICE`.`krim_typ_t` (`KIM_TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-611', '2.0.4-SNP', '3:83e3b1d9aec8624c64ddcd6634756a66', 611);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-612::Jeff Caddel (generated)::(Checksum: 3:25dda0f9876970a19117d1ca8edafc60)
ALTER TABLE `RICE`.`krim_dlgn_t` ADD CONSTRAINT `KRIM_DLGN_TR1` FOREIGN KEY (`ROLE_ID`) REFERENCES `RICE`.`krim_role_t` (`ROLE_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-612', '2.0.4-SNP', '3:25dda0f9876970a19117d1ca8edafc60', 612);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-613::Jeff Caddel (generated)::(Checksum: 3:42719d9d2c644d17da540ab68e667977)
ALTER TABLE `RICE`.`krim_entity_addr_t` ADD CONSTRAINT `KRIM_ENTITY_ADDR_TR2` FOREIGN KEY (`ADDR_TYP_CD`) REFERENCES `RICE`.`krim_addr_typ_t` (`ADDR_TYP_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-613', '2.0.4-SNP', '3:42719d9d2c644d17da540ab68e667977', 613);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-614::Jeff Caddel (generated)::(Checksum: 3:38adf77c3bd949af09855cd71c7101e5)
ALTER TABLE `RICE`.`krim_entity_addr_t` ADD CONSTRAINT `KRIM_ENTITY_ADDR_TR1` FOREIGN KEY (`ENT_TYP_CD`, `ENTITY_ID`) REFERENCES `RICE`.`krim_entity_ent_typ_t` (`ENT_TYP_CD`, `ENTITY_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-614', '2.0.4-SNP', '3:38adf77c3bd949af09855cd71c7101e5', 614);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-615::Jeff Caddel (generated)::(Checksum: 3:a82b7a3b10b288e8bdb113de0b0d545f)
ALTER TABLE `RICE`.`krim_entity_afltn_t` ADD CONSTRAINT `KRIM_ENTITY_AFLTN_TR2` FOREIGN KEY (`AFLTN_TYP_CD`) REFERENCES `RICE`.`krim_afltn_typ_t` (`AFLTN_TYP_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-615', '2.0.4-SNP', '3:a82b7a3b10b288e8bdb113de0b0d545f', 615);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-616::Jeff Caddel (generated)::(Checksum: 3:5d8efb4d33850ad5a688e5ac694a2cce)
ALTER TABLE `RICE`.`krim_entity_afltn_t` ADD CONSTRAINT `KRIM_ENTITY_AFLTN_TR1` FOREIGN KEY (`ENTITY_ID`) REFERENCES `RICE`.`krim_entity_t` (`ENTITY_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-616', '2.0.4-SNP', '3:5d8efb4d33850ad5a688e5ac694a2cce', 616);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-617::Jeff Caddel (generated)::(Checksum: 3:4a5d0d23e901372a51f382c07f0c2cbf)
ALTER TABLE `RICE`.`krim_entity_ctznshp_t` ADD CONSTRAINT `KRIM_ENTITY_CTZNSHP_TR2` FOREIGN KEY (`CTZNSHP_STAT_CD`) REFERENCES `RICE`.`krim_ctznshp_stat_t` (`CTZNSHP_STAT_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-617', '2.0.4-SNP', '3:4a5d0d23e901372a51f382c07f0c2cbf', 617);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-618::Jeff Caddel (generated)::(Checksum: 3:d421040b10683dddca9e9af7daedbbc5)
ALTER TABLE `RICE`.`krim_entity_ctznshp_t` ADD CONSTRAINT `KRIM_ENTITY_CTZNSHP_TR1` FOREIGN KEY (`ENTITY_ID`) REFERENCES `RICE`.`krim_entity_t` (`ENTITY_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-618', '2.0.4-SNP', '3:d421040b10683dddca9e9af7daedbbc5', 618);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-619::Jeff Caddel (generated)::(Checksum: 3:4a66e29a5211ec6d44ac97f1b88ff6ca)
ALTER TABLE `RICE`.`krim_entity_email_t` ADD CONSTRAINT `KRIM_ENTITY_EMAIL_TR1` FOREIGN KEY (`ENT_TYP_CD`, `ENTITY_ID`) REFERENCES `RICE`.`krim_entity_ent_typ_t` (`ENT_TYP_CD`, `ENTITY_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-619', '2.0.4-SNP', '3:4a66e29a5211ec6d44ac97f1b88ff6ca', 619);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-620::Jeff Caddel (generated)::(Checksum: 3:cd012cf53792e0f82b195405084dc72e)
ALTER TABLE `RICE`.`krim_entity_emp_info_t` ADD CONSTRAINT `KRIM_ENTITY_EMP_INFO_TR2` FOREIGN KEY (`EMP_STAT_CD`) REFERENCES `RICE`.`krim_emp_stat_t` (`EMP_STAT_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-620', '2.0.4-SNP', '3:cd012cf53792e0f82b195405084dc72e', 620);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-621::Jeff Caddel (generated)::(Checksum: 3:b14dab8305da8a784f1fe5e876b73dcb)
ALTER TABLE `RICE`.`krim_entity_emp_info_t` ADD CONSTRAINT `KRIM_ENTITY_EMP_INFO_TR3` FOREIGN KEY (`EMP_TYP_CD`) REFERENCES `RICE`.`krim_emp_typ_t` (`EMP_TYP_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-621', '2.0.4-SNP', '3:b14dab8305da8a784f1fe5e876b73dcb', 621);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-622::Jeff Caddel (generated)::(Checksum: 3:992d2ff86f19036f859e7ec7817ef514)
ALTER TABLE `RICE`.`krim_entity_emp_info_t` ADD CONSTRAINT `KRIM_ENTITY_EMP_INFO_TR4` FOREIGN KEY (`ENTITY_AFLTN_ID`) REFERENCES `RICE`.`krim_entity_afltn_t` (`ENTITY_AFLTN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-622', '2.0.4-SNP', '3:992d2ff86f19036f859e7ec7817ef514', 622);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-623::Jeff Caddel (generated)::(Checksum: 3:1ed9a5860c824f7d5fd30afc344cafd0)
ALTER TABLE `RICE`.`krim_entity_emp_info_t` ADD CONSTRAINT `KRIM_ENTITY_EMP_INFO_TR1` FOREIGN KEY (`ENTITY_ID`) REFERENCES `RICE`.`krim_entity_t` (`ENTITY_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-623', '2.0.4-SNP', '3:1ed9a5860c824f7d5fd30afc344cafd0', 623);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-624::Jeff Caddel (generated)::(Checksum: 3:516760f32c4bcc66ee267cd6bb055adf)
ALTER TABLE `RICE`.`krim_entity_ent_typ_t` ADD CONSTRAINT `KRIM_ENTITY_ENT_TYP_TR2` FOREIGN KEY (`ENT_TYP_CD`) REFERENCES `RICE`.`krim_ent_typ_t` (`ENT_TYP_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-624', '2.0.4-SNP', '3:516760f32c4bcc66ee267cd6bb055adf', 624);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-625::Jeff Caddel (generated)::(Checksum: 3:8e75e133ebdbc6e108273232e54b2e75)
ALTER TABLE `RICE`.`krim_entity_ent_typ_t` ADD CONSTRAINT `KRIM_ENTITY_ENT_TYP_TR1` FOREIGN KEY (`ENTITY_ID`) REFERENCES `RICE`.`krim_entity_t` (`ENTITY_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-625', '2.0.4-SNP', '3:8e75e133ebdbc6e108273232e54b2e75', 625);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-626::Jeff Caddel (generated)::(Checksum: 3:4c33ae4250900348fa7b910cb3406b6d)
ALTER TABLE `RICE`.`krim_entity_ethnic_t` ADD CONSTRAINT `KRIM_ENTITY_ETHNIC_TR1` FOREIGN KEY (`ENTITY_ID`) REFERENCES `RICE`.`krim_entity_t` (`ENTITY_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-626', '2.0.4-SNP', '3:4c33ae4250900348fa7b910cb3406b6d', 626);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-627::Jeff Caddel (generated)::(Checksum: 3:6d6a30c04f66c2ae88109f9691a2df3b)
ALTER TABLE `RICE`.`krim_entity_ext_id_t` ADD CONSTRAINT `KRIM_ENTITY_EXT_ID_TR1` FOREIGN KEY (`ENTITY_ID`) REFERENCES `RICE`.`krim_entity_t` (`ENTITY_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-627', '2.0.4-SNP', '3:6d6a30c04f66c2ae88109f9691a2df3b', 627);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-628::Jeff Caddel (generated)::(Checksum: 3:2941b7fa41098ae72c22da3bd7acf2f6)
ALTER TABLE `RICE`.`krim_entity_ext_id_t` ADD CONSTRAINT `KRIM_ENTITY_EXT_ID_TR2` FOREIGN KEY (`EXT_ID_TYP_CD`) REFERENCES `RICE`.`krim_ext_id_typ_t` (`EXT_ID_TYP_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-628', '2.0.4-SNP', '3:2941b7fa41098ae72c22da3bd7acf2f6', 628);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-629::Jeff Caddel (generated)::(Checksum: 3:9c5fb013311becb6282df2c5ae8357e2)
ALTER TABLE `RICE`.`krim_entity_nm_t` ADD CONSTRAINT `KRIM_ENTITY_NM_TR1` FOREIGN KEY (`ENTITY_ID`) REFERENCES `RICE`.`krim_entity_t` (`ENTITY_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-629', '2.0.4-SNP', '3:9c5fb013311becb6282df2c5ae8357e2', 629);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-630::Jeff Caddel (generated)::(Checksum: 3:7aa06b96bc0c56f620916114f76b906e)
ALTER TABLE `RICE`.`krim_entity_phone_t` ADD CONSTRAINT `KRIM_ENTITY_PHONE_TR1` FOREIGN KEY (`ENT_TYP_CD`, `ENTITY_ID`) REFERENCES `RICE`.`krim_entity_ent_typ_t` (`ENT_TYP_CD`, `ENTITY_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-630', '2.0.4-SNP', '3:7aa06b96bc0c56f620916114f76b906e', 630);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-631::Jeff Caddel (generated)::(Checksum: 3:2a1ccdeeccfba5280e7a6ff553272175)
ALTER TABLE `RICE`.`krim_entity_phone_t` ADD CONSTRAINT `KRIM_ENTITY_PHONE_TR2` FOREIGN KEY (`PHONE_TYP_CD`) REFERENCES `RICE`.`krim_phone_typ_t` (`PHONE_TYP_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-631', '2.0.4-SNP', '3:2a1ccdeeccfba5280e7a6ff553272175', 631);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-632::Jeff Caddel (generated)::(Checksum: 3:4ddc59c05806bba35997643873d7ec15)
ALTER TABLE `RICE`.`krim_entity_residency_t` ADD CONSTRAINT `KRIM_ENTITY_RESIDENCY_TR1` FOREIGN KEY (`ENTITY_ID`) REFERENCES `RICE`.`krim_entity_t` (`ENTITY_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-632', '2.0.4-SNP', '3:4ddc59c05806bba35997643873d7ec15', 632);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-633::Jeff Caddel (generated)::(Checksum: 3:19821d49ad5259848e91422c7a403fc9)
ALTER TABLE `RICE`.`krim_entity_visa_t` ADD CONSTRAINT `KRIM_ENTITY_VISA_TR1` FOREIGN KEY (`ENTITY_ID`) REFERENCES `RICE`.`krim_entity_t` (`ENTITY_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-633', '2.0.4-SNP', '3:19821d49ad5259848e91422c7a403fc9', 633);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-634::Jeff Caddel (generated)::(Checksum: 3:1c5f3026807f32be0ef2a0f58e79d009)
ALTER TABLE `RICE`.`krim_grp_attr_data_t` ADD CONSTRAINT `KRIM_GRP_ATTR_DATA_TR3` FOREIGN KEY (`GRP_ID`) REFERENCES `RICE`.`krim_grp_t` (`GRP_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-634', '2.0.4-SNP', '3:1c5f3026807f32be0ef2a0f58e79d009', 634);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-635::Jeff Caddel (generated)::(Checksum: 3:a28e6cf29aedde0c9abf0c364c5c8a60)
ALTER TABLE `RICE`.`krim_grp_attr_data_t` ADD CONSTRAINT `KRIM_GRP_ATTR_DATA_TR2` FOREIGN KEY (`KIM_ATTR_DEFN_ID`) REFERENCES `RICE`.`krim_attr_defn_t` (`KIM_ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-635', '2.0.4-SNP', '3:a28e6cf29aedde0c9abf0c364c5c8a60', 635);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-636::Jeff Caddel (generated)::(Checksum: 3:ca904cdf012493fd237b0f14b75bef32)
ALTER TABLE `RICE`.`krim_grp_attr_data_t` ADD CONSTRAINT `KRIM_GRP_ATTR_DATA_TR1` FOREIGN KEY (`KIM_TYP_ID`) REFERENCES `RICE`.`krim_typ_t` (`KIM_TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-636', '2.0.4-SNP', '3:ca904cdf012493fd237b0f14b75bef32', 636);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-637::Jeff Caddel (generated)::(Checksum: 3:14a8c5dde3566c54413fbde45dadd59b)
ALTER TABLE `RICE`.`krim_grp_mbr_t` ADD CONSTRAINT `KRIM_GRP_MBR_TR1` FOREIGN KEY (`GRP_ID`) REFERENCES `RICE`.`krim_grp_t` (`GRP_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-637', '2.0.4-SNP', '3:14a8c5dde3566c54413fbde45dadd59b', 637);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-638::Jeff Caddel (generated)::(Checksum: 3:3e70a60c971cfbdb0be76a988b97d91f)
ALTER TABLE `RICE`.`krim_grp_t` ADD CONSTRAINT `KRIM_GRP_TR1` FOREIGN KEY (`KIM_TYP_ID`) REFERENCES `RICE`.`krim_typ_t` (`KIM_TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-638', '2.0.4-SNP', '3:3e70a60c971cfbdb0be76a988b97d91f', 638);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-639::Jeff Caddel (generated)::(Checksum: 3:aa69a3310450a76fd44fee3a01853ce6)
ALTER TABLE `RICE`.`krim_perm_attr_data_t` ADD CONSTRAINT `KRIM_PERM_ATTR_DATA_TR2` FOREIGN KEY (`KIM_ATTR_DEFN_ID`) REFERENCES `RICE`.`krim_attr_defn_t` (`KIM_ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-639', '2.0.4-SNP', '3:aa69a3310450a76fd44fee3a01853ce6', 639);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-640::Jeff Caddel (generated)::(Checksum: 3:2b77cb9269c4195b800515e73d145560)
ALTER TABLE `RICE`.`krim_perm_attr_data_t` ADD CONSTRAINT `KRIM_PERM_ATTR_DATA_TR1` FOREIGN KEY (`KIM_TYP_ID`) REFERENCES `RICE`.`krim_typ_t` (`KIM_TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-640', '2.0.4-SNP', '3:2b77cb9269c4195b800515e73d145560', 640);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-641::Jeff Caddel (generated)::(Checksum: 3:76d7f7b9c4500ca7fe1918e8358c961f)
ALTER TABLE `RICE`.`krim_perm_attr_data_t` ADD CONSTRAINT `KRIM_PERM_ATTR_DATA_TR3` FOREIGN KEY (`PERM_ID`) REFERENCES `RICE`.`krim_perm_t` (`PERM_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-641', '2.0.4-SNP', '3:76d7f7b9c4500ca7fe1918e8358c961f', 641);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-642::Jeff Caddel (generated)::(Checksum: 3:a60f6c003c1b42cd8d90daaf659e7ff2)
ALTER TABLE `RICE`.`krim_perm_t` ADD CONSTRAINT `KRIM_PERM_TR1` FOREIGN KEY (`PERM_TMPL_ID`) REFERENCES `RICE`.`krim_perm_tmpl_t` (`PERM_TMPL_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-642', '2.0.4-SNP', '3:a60f6c003c1b42cd8d90daaf659e7ff2', 642);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-643::Jeff Caddel (generated)::(Checksum: 3:ef1f598f3160fbedb5577cb8f1c7a37c)
ALTER TABLE `RICE`.`krim_perm_tmpl_t` ADD CONSTRAINT `KRIM_PERM_TMPL_TR1` FOREIGN KEY (`KIM_TYP_ID`) REFERENCES `RICE`.`krim_typ_t` (`KIM_TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-643', '2.0.4-SNP', '3:ef1f598f3160fbedb5577cb8f1c7a37c', 643);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-644::Jeff Caddel (generated)::(Checksum: 3:fc5a5ba32b96a1175b7c5a568a3e6eac)
ALTER TABLE `RICE`.`krim_pnd_afltn_mt` ADD CONSTRAINT `KRIM_PND_AFLTN_MT_FK1` FOREIGN KEY (`FDOC_NBR`) REFERENCES `RICE`.`krim_person_document_t` (`FDOC_NBR`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-644', '2.0.4-SNP', '3:fc5a5ba32b96a1175b7c5a568a3e6eac', 644);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-645::Jeff Caddel (generated)::(Checksum: 3:173c619cd673ef07fde5d8d61539d515)
ALTER TABLE `RICE`.`krim_prncpl_t` ADD CONSTRAINT `KRIM_PRNCPL_TR1` FOREIGN KEY (`ENTITY_ID`) REFERENCES `RICE`.`krim_entity_t` (`ENTITY_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-645', '2.0.4-SNP', '3:173c619cd673ef07fde5d8d61539d515', 645);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-646::Jeff Caddel (generated)::(Checksum: 3:98c9fd0c19c900bdb2ef6c5209b98841)
ALTER TABLE `RICE`.`krim_role_mbr_attr_data_t` ADD CONSTRAINT `KRIM_ROLE_MBR_ATTR_DATA_TR2` FOREIGN KEY (`KIM_ATTR_DEFN_ID`) REFERENCES `RICE`.`krim_attr_defn_t` (`KIM_ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-646', '2.0.4-SNP', '3:98c9fd0c19c900bdb2ef6c5209b98841', 646);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-647::Jeff Caddel (generated)::(Checksum: 3:c90631d6f82d75b7583a5c7be63101f6)
ALTER TABLE `RICE`.`krim_role_mbr_attr_data_t` ADD CONSTRAINT `KRIM_ROLE_MBR_ATTR_DATA_TR1` FOREIGN KEY (`KIM_TYP_ID`) REFERENCES `RICE`.`krim_typ_t` (`KIM_TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-647', '2.0.4-SNP', '3:c90631d6f82d75b7583a5c7be63101f6', 647);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-648::Jeff Caddel (generated)::(Checksum: 3:fcbb87ddd3a928c412e16b3ed9cfa2d4)
ALTER TABLE `RICE`.`krim_role_mbr_t` ADD CONSTRAINT `KRIM_ROLE_MBR_TR1` FOREIGN KEY (`ROLE_ID`) REFERENCES `RICE`.`krim_role_t` (`ROLE_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-648', '2.0.4-SNP', '3:fcbb87ddd3a928c412e16b3ed9cfa2d4', 648);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-649::Jeff Caddel (generated)::(Checksum: 3:2139cbbb71ddb4c7e9231e6377dc1cbf)
ALTER TABLE `RICE`.`krim_role_perm_t` ADD CONSTRAINT `KRIM_ROLE_PERM_TR1` FOREIGN KEY (`PERM_ID`) REFERENCES `RICE`.`krim_perm_t` (`PERM_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-649', '2.0.4-SNP', '3:2139cbbb71ddb4c7e9231e6377dc1cbf', 649);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-650::Jeff Caddel (generated)::(Checksum: 3:0fee5095007b50b9e28e75f1c02a8874)
ALTER TABLE `RICE`.`krim_role_rsp_t` ADD CONSTRAINT `KRIM_ROLE_RSP_TR1` FOREIGN KEY (`RSP_ID`) REFERENCES `RICE`.`krim_rsp_t` (`RSP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-650', '2.0.4-SNP', '3:0fee5095007b50b9e28e75f1c02a8874', 650);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-651::Jeff Caddel (generated)::(Checksum: 3:01175762184d5b36addb702f8d6b4407)
ALTER TABLE `RICE`.`krim_role_t` ADD CONSTRAINT `KRIM_ROLE_TR1` FOREIGN KEY (`KIM_TYP_ID`) REFERENCES `RICE`.`krim_typ_t` (`KIM_TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-651', '2.0.4-SNP', '3:01175762184d5b36addb702f8d6b4407', 651);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-652::Jeff Caddel (generated)::(Checksum: 3:29d78075e4fce1755308a1dfa52b2594)
ALTER TABLE `RICE`.`krim_rsp_attr_data_t` ADD CONSTRAINT `KRIM_RSP_ATTR_DATA_TR2` FOREIGN KEY (`KIM_ATTR_DEFN_ID`) REFERENCES `RICE`.`krim_attr_defn_t` (`KIM_ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-652', '2.0.4-SNP', '3:29d78075e4fce1755308a1dfa52b2594', 652);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-653::Jeff Caddel (generated)::(Checksum: 3:5452ae6459f29e4553d800910330217a)
ALTER TABLE `RICE`.`krim_rsp_attr_data_t` ADD CONSTRAINT `KRIM_RSP_ATTR_DATA_TR1` FOREIGN KEY (`KIM_TYP_ID`) REFERENCES `RICE`.`krim_typ_t` (`KIM_TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-653', '2.0.4-SNP', '3:5452ae6459f29e4553d800910330217a', 653);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-654::Jeff Caddel (generated)::(Checksum: 3:5ea9969427e0cd496720794cb0c8cbbb)
ALTER TABLE `RICE`.`krim_rsp_attr_data_t` ADD CONSTRAINT `KRIM_RSP_ATTR_DATA_TR3` FOREIGN KEY (`RSP_ID`) REFERENCES `RICE`.`krim_rsp_t` (`RSP_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-654', '2.0.4-SNP', '3:5ea9969427e0cd496720794cb0c8cbbb', 654);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-655::Jeff Caddel (generated)::(Checksum: 3:20901ba8a2e751ba313d9331dc8bee78)
ALTER TABLE `RICE`.`krim_rsp_t` ADD CONSTRAINT `KRIM_RSP_TR1` FOREIGN KEY (`RSP_TMPL_ID`) REFERENCES `RICE`.`krim_rsp_tmpl_t` (`RSP_TMPL_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-655', '2.0.4-SNP', '3:20901ba8a2e751ba313d9331dc8bee78', 655);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-656::Jeff Caddel (generated)::(Checksum: 3:867f5389216ab2b96b8cb3088efde4fb)
ALTER TABLE `RICE`.`krim_rsp_tmpl_t` ADD CONSTRAINT `KRIM_RSP_TMPL_TR1` FOREIGN KEY (`KIM_TYP_ID`) REFERENCES `RICE`.`krim_typ_t` (`KIM_TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-656', '2.0.4-SNP', '3:867f5389216ab2b96b8cb3088efde4fb', 656);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-657::Jeff Caddel (generated)::(Checksum: 3:4fe16e0167c1ef2d39a45e347a6a0331)
ALTER TABLE `RICE`.`krim_typ_attr_t` ADD CONSTRAINT `KRIM_TYP_ATTR_TR2` FOREIGN KEY (`KIM_ATTR_DEFN_ID`) REFERENCES `RICE`.`krim_attr_defn_t` (`KIM_ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-657', '2.0.4-SNP', '3:4fe16e0167c1ef2d39a45e347a6a0331', 657);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-658::Jeff Caddel (generated)::(Checksum: 3:8d409d45f5d22bd5e35f364f910840b1)
ALTER TABLE `RICE`.`krim_typ_attr_t` ADD CONSTRAINT `KRIM_TYP_ATTRIBUTE_TR1` FOREIGN KEY (`KIM_TYP_ID`) REFERENCES `RICE`.`krim_typ_t` (`KIM_TYP_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-658', '2.0.4-SNP', '3:8d409d45f5d22bd5e35f364f910840b1', 658);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-659::Jeff Caddel (generated)::(Checksum: 3:907c520d62ee97a8ae0cd2753f13d626)
ALTER TABLE `RICE`.`krlc_cmp_t` ADD CONSTRAINT `SH_CAMPUS_TR1` FOREIGN KEY (`CAMPUS_TYP_CD`) REFERENCES `RICE`.`krlc_cmp_typ_t` (`CAMPUS_TYP_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-659', '2.0.4-SNP', '3:907c520d62ee97a8ae0cd2753f13d626', 659);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-660::Jeff Caddel (generated)::(Checksum: 3:f403b15c2480b7305cceea14889f2403)
ALTER TABLE `RICE`.`krlc_pstl_cd_t` ADD CONSTRAINT `KR_POSTAL_CODE_TR3` FOREIGN KEY (`COUNTY_CD`, `POSTAL_STATE_CD`, `POSTAL_CNTRY_CD`) REFERENCES `RICE`.`krlc_cnty_t` (`COUNTY_CD`, `STATE_CD`, `POSTAL_CNTRY_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-660', '2.0.4-SNP', '3:f403b15c2480b7305cceea14889f2403', 660);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-661::Jeff Caddel (generated)::(Checksum: 3:81cbb01b739eff6468addace052fc61b)
ALTER TABLE `RICE`.`krlc_pstl_cd_t` ADD CONSTRAINT `KR_POSTAL_CODE_TR1` FOREIGN KEY (`POSTAL_CNTRY_CD`) REFERENCES `RICE`.`krlc_cntry_t` (`POSTAL_CNTRY_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-661', '2.0.4-SNP', '3:81cbb01b739eff6468addace052fc61b', 661);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-662::Jeff Caddel (generated)::(Checksum: 3:e863a2848a98eecd7c9e7b6d2130d2a0)
ALTER TABLE `RICE`.`krlc_pstl_cd_t` ADD CONSTRAINT `KR_POSTAL_CODE_TR2` FOREIGN KEY (`POSTAL_STATE_CD`, `POSTAL_CNTRY_CD`) REFERENCES `RICE`.`krlc_st_t` (`POSTAL_STATE_CD`, `POSTAL_CNTRY_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-662', '2.0.4-SNP', '3:e863a2848a98eecd7c9e7b6d2130d2a0', 662);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-663::Jeff Caddel (generated)::(Checksum: 3:c07f7832dc691055e1feb1f68c4d93d2)
ALTER TABLE `RICE`.`krlc_st_t` ADD CONSTRAINT `KR_STATE_TR1` FOREIGN KEY (`POSTAL_CNTRY_CD`) REFERENCES `RICE`.`krlc_cntry_t` (`POSTAL_CNTRY_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-663', '2.0.4-SNP', '3:c07f7832dc691055e1feb1f68c4d93d2', 663);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-664::Jeff Caddel (generated)::(Checksum: 3:b63289e54027c627dd3ff37ee2470a7e)
ALTER TABLE `RICE`.`krms_actn_attr_t` ADD CONSTRAINT `KRMS_ACTN_ATTR_FK1` FOREIGN KEY (`ACTN_ID`) REFERENCES `RICE`.`krms_actn_t` (`ACTN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-664', '2.0.4-SNP', '3:b63289e54027c627dd3ff37ee2470a7e', 664);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-665::Jeff Caddel (generated)::(Checksum: 3:4fa007c21e959b5032b3a3e6648cb4b8)
ALTER TABLE `RICE`.`krms_actn_attr_t` ADD CONSTRAINT `KRMS_ACTN_ATTR_FK2` FOREIGN KEY (`ATTR_DEFN_ID`) REFERENCES `RICE`.`krms_attr_defn_t` (`ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-665', '2.0.4-SNP', '3:4fa007c21e959b5032b3a3e6648cb4b8', 665);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-666::Jeff Caddel (generated)::(Checksum: 3:921c3c7ac4268cfcb8cbdaa4fb393ecc)
ALTER TABLE `RICE`.`krms_actn_t` ADD CONSTRAINT `KRMS_ACTN_FK1` FOREIGN KEY (`RULE_ID`) REFERENCES `RICE`.`krms_rule_t` (`RULE_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-666', '2.0.4-SNP', '3:921c3c7ac4268cfcb8cbdaa4fb393ecc', 666);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-667::Jeff Caddel (generated)::(Checksum: 3:55d3290f3fb65fbc1cb21327d88b0d4c)
ALTER TABLE `RICE`.`krms_agenda_attr_t` ADD CONSTRAINT `KRMS_AGENDA_ATTR_FK1` FOREIGN KEY (`AGENDA_ID`) REFERENCES `RICE`.`krms_agenda_t` (`AGENDA_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-667', '2.0.4-SNP', '3:55d3290f3fb65fbc1cb21327d88b0d4c', 667);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-668::Jeff Caddel (generated)::(Checksum: 3:89f4c5fe32ac1ea630df2412255e062c)
ALTER TABLE `RICE`.`krms_agenda_attr_t` ADD CONSTRAINT `KRMS_AGENDA_ATTR_FK2` FOREIGN KEY (`ATTR_DEFN_ID`) REFERENCES `RICE`.`krms_attr_defn_t` (`ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-668', '2.0.4-SNP', '3:89f4c5fe32ac1ea630df2412255e062c', 668);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-669::Jeff Caddel (generated)::(Checksum: 3:1304fe2b626c8b803b5cb6ed670fcc82)
ALTER TABLE `RICE`.`krms_agenda_itm_t` ADD CONSTRAINT `KRMS_AGENDA_ITM_FK2` FOREIGN KEY (`AGENDA_ID`) REFERENCES `RICE`.`krms_agenda_t` (`AGENDA_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-669', '2.0.4-SNP', '3:1304fe2b626c8b803b5cb6ed670fcc82', 669);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-670::Jeff Caddel (generated)::(Checksum: 3:21d0c822f97f678d866e5dbe33f13e86)
ALTER TABLE `RICE`.`krms_agenda_itm_t` ADD CONSTRAINT `KRMS_AGENDA_ITM_FK6` FOREIGN KEY (`ALWAYS`) REFERENCES `RICE`.`krms_agenda_itm_t` (`AGENDA_ITM_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-670', '2.0.4-SNP', '3:21d0c822f97f678d866e5dbe33f13e86', 670);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-671::Jeff Caddel (generated)::(Checksum: 3:0946f40c8511cd666f5ae297242c05f6)
ALTER TABLE `RICE`.`krms_agenda_itm_t` ADD CONSTRAINT `KRMS_AGENDA_ITM_FK1` FOREIGN KEY (`RULE_ID`) REFERENCES `RICE`.`krms_rule_t` (`RULE_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-671', '2.0.4-SNP', '3:0946f40c8511cd666f5ae297242c05f6', 671);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-672::Jeff Caddel (generated)::(Checksum: 3:c44d73b0e4cd6cef573275101ee7ff3c)
ALTER TABLE `RICE`.`krms_agenda_itm_t` ADD CONSTRAINT `KRMS_AGENDA_ITM_FK3` FOREIGN KEY (`SUB_AGENDA_ID`) REFERENCES `RICE`.`krms_agenda_t` (`AGENDA_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-672', '2.0.4-SNP', '3:c44d73b0e4cd6cef573275101ee7ff3c', 672);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-673::Jeff Caddel (generated)::(Checksum: 3:5ff76b0aecc4e745206e9e7317e2c317)
ALTER TABLE `RICE`.`krms_agenda_itm_t` ADD CONSTRAINT `KRMS_AGENDA_ITM_FK5` FOREIGN KEY (`WHEN_FALSE`) REFERENCES `RICE`.`krms_agenda_itm_t` (`AGENDA_ITM_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-673', '2.0.4-SNP', '3:5ff76b0aecc4e745206e9e7317e2c317', 673);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-674::Jeff Caddel (generated)::(Checksum: 3:04b78530ea2277bf1abdd3b303a2475d)
ALTER TABLE `RICE`.`krms_agenda_itm_t` ADD CONSTRAINT `KRMS_AGENDA_ITM_FK4` FOREIGN KEY (`WHEN_TRUE`) REFERENCES `RICE`.`krms_agenda_itm_t` (`AGENDA_ITM_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-674', '2.0.4-SNP', '3:04b78530ea2277bf1abdd3b303a2475d', 674);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-675::Jeff Caddel (generated)::(Checksum: 3:f42a9cce0dfa74e751012960ed5b8c39)
ALTER TABLE `RICE`.`krms_agenda_t` ADD CONSTRAINT `KRMS_AGENDA_FK1` FOREIGN KEY (`CNTXT_ID`) REFERENCES `RICE`.`krms_cntxt_t` (`CNTXT_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-675', '2.0.4-SNP', '3:f42a9cce0dfa74e751012960ed5b8c39', 675);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-676::Jeff Caddel (generated)::(Checksum: 3:bc0ba461e7ea41e3be35536f9cdac193)
ALTER TABLE `RICE`.`krms_cmpnd_prop_props_t` ADD CONSTRAINT `KRMS_CMPND_PROP_PROPS_FK2` FOREIGN KEY (`CMPND_PROP_ID`) REFERENCES `RICE`.`krms_prop_t` (`PROP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-676', '2.0.4-SNP', '3:bc0ba461e7ea41e3be35536f9cdac193', 676);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-677::Jeff Caddel (generated)::(Checksum: 3:83bd674a5c09b680cdb283220f132b31)
ALTER TABLE `RICE`.`krms_cmpnd_prop_props_t` ADD CONSTRAINT `KRMS_CMPND_PROP_PROPS_FK1` FOREIGN KEY (`PROP_ID`) REFERENCES `RICE`.`krms_prop_t` (`PROP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-677', '2.0.4-SNP', '3:83bd674a5c09b680cdb283220f132b31', 677);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-678::Jeff Caddel (generated)::(Checksum: 3:5ec2f4e96a434d099f05aab9c1fe0dd6)
ALTER TABLE `RICE`.`krms_cntxt_attr_t` ADD CONSTRAINT `KRMS_CNTXT_ATTR_FK2` FOREIGN KEY (`ATTR_DEFN_ID`) REFERENCES `RICE`.`krms_attr_defn_t` (`ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-678', '2.0.4-SNP', '3:5ec2f4e96a434d099f05aab9c1fe0dd6', 678);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-679::Jeff Caddel (generated)::(Checksum: 3:15ccbede2af956597b81b695d557b4ce)
ALTER TABLE `RICE`.`krms_cntxt_attr_t` ADD CONSTRAINT `KRMS_CNTXT_ATTR_FK1` FOREIGN KEY (`CNTXT_ID`) REFERENCES `RICE`.`krms_cntxt_t` (`CNTXT_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-679', '2.0.4-SNP', '3:15ccbede2af956597b81b695d557b4ce', 679);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-680::Jeff Caddel (generated)::(Checksum: 3:a89a207fb3b04b9f371fe9c8678a3087)
ALTER TABLE `RICE`.`krms_cntxt_vld_actn_t` ADD CONSTRAINT `KRMS_CNTXT_VLD_ACTN_FK1` FOREIGN KEY (`CNTXT_ID`) REFERENCES `RICE`.`krms_cntxt_t` (`CNTXT_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-680', '2.0.4-SNP', '3:a89a207fb3b04b9f371fe9c8678a3087', 680);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-681::Jeff Caddel (generated)::(Checksum: 3:3cdc347e7307b098997836df2d821d5a)
ALTER TABLE `RICE`.`krms_cntxt_vld_event_t` ADD CONSTRAINT `KRMS_CNTXT_VLD_EVENT_FK1` FOREIGN KEY (`CNTXT_ID`) REFERENCES `RICE`.`krms_cntxt_t` (`CNTXT_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-681', '2.0.4-SNP', '3:3cdc347e7307b098997836df2d821d5a', 681);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-682::Jeff Caddel (generated)::(Checksum: 3:81395980674d3d0de642e31653990e28)
ALTER TABLE `RICE`.`krms_cntxt_vld_func_t` ADD CONSTRAINT `KRMS_CNTXT_VLD_FUNC_FK1` FOREIGN KEY (`CNTXT_ID`) REFERENCES `RICE`.`krms_cntxt_t` (`CNTXT_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-682', '2.0.4-SNP', '3:81395980674d3d0de642e31653990e28', 682);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-683::Jeff Caddel (generated)::(Checksum: 3:53cd5218bc5f993c6caccdd3409c93c2)
ALTER TABLE `RICE`.`krms_cntxt_vld_rule_t` ADD CONSTRAINT `KRMS_CNTXT_VLD_RULE_FK1` FOREIGN KEY (`CNTXT_ID`) REFERENCES `RICE`.`krms_cntxt_t` (`CNTXT_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-683', '2.0.4-SNP', '3:53cd5218bc5f993c6caccdd3409c93c2', 683);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-684::Jeff Caddel (generated)::(Checksum: 3:ea17688713e7ebe5c313dede01887b96)
ALTER TABLE `RICE`.`krms_cntxt_vld_term_spec_t` ADD CONSTRAINT `KRMS_CNTXT_VLD_TERM_SPEC_TI1` FOREIGN KEY (`CNTXT_ID`) REFERENCES `RICE`.`krms_cntxt_t` (`CNTXT_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-684', '2.0.4-SNP', '3:ea17688713e7ebe5c313dede01887b96', 684);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-685::Jeff Caddel (generated)::(Checksum: 3:597fc37735e796004494dedbbacb139b)
ALTER TABLE `RICE`.`krms_cntxt_vld_term_spec_t` ADD CONSTRAINT `KRMS_CNTXT_VLD_TERM_SPEC_TI2` FOREIGN KEY (`TERM_SPEC_ID`) REFERENCES `RICE`.`krms_term_spec_t` (`TERM_SPEC_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-685', '2.0.4-SNP', '3:597fc37735e796004494dedbbacb139b', 685);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-686::Jeff Caddel (generated)::(Checksum: 3:e99251e1ebcdced8c6fdc2f26b6fb26e)
ALTER TABLE `RICE`.`krms_func_ctgry_t` ADD CONSTRAINT `KRMS_FUNC_CTGRY_FK2` FOREIGN KEY (`CTGRY_ID`) REFERENCES `RICE`.`krms_ctgry_t` (`CTGRY_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-686', '2.0.4-SNP', '3:e99251e1ebcdced8c6fdc2f26b6fb26e', 686);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-687::Jeff Caddel (generated)::(Checksum: 3:3654afef5193daf195443e658b61add6)
ALTER TABLE `RICE`.`krms_func_ctgry_t` ADD CONSTRAINT `KRMS_FUNC_CTGRY_FK1` FOREIGN KEY (`FUNC_ID`) REFERENCES `RICE`.`krms_func_t` (`FUNC_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-687', '2.0.4-SNP', '3:3654afef5193daf195443e658b61add6', 687);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-688::Jeff Caddel (generated)::(Checksum: 3:91f919943e5a8300ba7c07a05685a092)
ALTER TABLE `RICE`.`krms_func_parm_t` ADD CONSTRAINT `KRMS_FUNC_PARM_FK1` FOREIGN KEY (`FUNC_ID`) REFERENCES `RICE`.`krms_func_t` (`FUNC_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-688', '2.0.4-SNP', '3:91f919943e5a8300ba7c07a05685a092', 688);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-689::Jeff Caddel (generated)::(Checksum: 3:0c853835ff5d5458d4347729b2040683)
ALTER TABLE `RICE`.`krms_func_t` ADD CONSTRAINT `KRMS_FUNC_FK1` FOREIGN KEY (`TYP_ID`) REFERENCES `RICE`.`krms_typ_t` (`TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-689', '2.0.4-SNP', '3:0c853835ff5d5458d4347729b2040683', 689);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-690::Jeff Caddel (generated)::(Checksum: 3:06ebb571cb46ef07b1e6a6e9ad97a89e)
ALTER TABLE `RICE`.`krms_prop_parm_t` ADD CONSTRAINT `KRMS_PROP_PARM_FK1` FOREIGN KEY (`PROP_ID`) REFERENCES `RICE`.`krms_prop_t` (`PROP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-690', '2.0.4-SNP', '3:06ebb571cb46ef07b1e6a6e9ad97a89e', 690);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-691::Jeff Caddel (generated)::(Checksum: 3:370c1a275acf47e565ce4884d9790717)
ALTER TABLE `RICE`.`krms_prop_t` ADD CONSTRAINT `KRMS_PROP_FK1` FOREIGN KEY (`RULE_ID`) REFERENCES `RICE`.`krms_rule_t` (`RULE_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-691', '2.0.4-SNP', '3:370c1a275acf47e565ce4884d9790717', 691);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-692::Jeff Caddel (generated)::(Checksum: 3:d314fed42cbdcc4fff97be93e39b30ac)
ALTER TABLE `RICE`.`krms_prop_t` ADD CONSTRAINT `KRMS_PROP_FK2` FOREIGN KEY (`TYP_ID`) REFERENCES `RICE`.`krms_typ_t` (`TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-692', '2.0.4-SNP', '3:d314fed42cbdcc4fff97be93e39b30ac', 692);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-693::Jeff Caddel (generated)::(Checksum: 3:689bcffbc022e6e2c9103fea71b26904)
ALTER TABLE `RICE`.`krms_rule_attr_t` ADD CONSTRAINT `KRMS_RULE_ATTR_FK2` FOREIGN KEY (`ATTR_DEFN_ID`) REFERENCES `RICE`.`krms_attr_defn_t` (`ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-693', '2.0.4-SNP', '3:689bcffbc022e6e2c9103fea71b26904', 693);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-694::Jeff Caddel (generated)::(Checksum: 3:c86916469f272feb8a08b79b1c7ca11a)
ALTER TABLE `RICE`.`krms_rule_attr_t` ADD CONSTRAINT `KRMS_RULE_ATTR_FK1` FOREIGN KEY (`RULE_ID`) REFERENCES `RICE`.`krms_rule_t` (`RULE_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-694', '2.0.4-SNP', '3:c86916469f272feb8a08b79b1c7ca11a', 694);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-695::Jeff Caddel (generated)::(Checksum: 3:ebb9c1eae3bb2e49e270ddc7cbf37a4c)
ALTER TABLE `RICE`.`krms_rule_t` ADD CONSTRAINT `KRMS_RULE_FK1` FOREIGN KEY (`PROP_ID`) REFERENCES `RICE`.`krms_prop_t` (`PROP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-695', '2.0.4-SNP', '3:ebb9c1eae3bb2e49e270ddc7cbf37a4c', 695);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-696::Jeff Caddel (generated)::(Checksum: 3:3ee2cc1e643f3353a19a83b24dedfb19)
ALTER TABLE `RICE`.`krms_term_parm_t` ADD CONSTRAINT `KRMS_TERM_PARM_FK1` FOREIGN KEY (`TERM_ID`) REFERENCES `RICE`.`krms_term_t` (`TERM_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-696', '2.0.4-SNP', '3:3ee2cc1e643f3353a19a83b24dedfb19', 696);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-697::Jeff Caddel (generated)::(Checksum: 3:9d7590d6dc33a5df57710b774561a0bc)
ALTER TABLE `RICE`.`krms_term_rslvr_attr_t` ADD CONSTRAINT `KRMS_TERM_RSLVR_ATTR_FK2` FOREIGN KEY (`ATTR_DEFN_ID`) REFERENCES `RICE`.`krms_attr_defn_t` (`ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-697', '2.0.4-SNP', '3:9d7590d6dc33a5df57710b774561a0bc', 697);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-698::Jeff Caddel (generated)::(Checksum: 3:99a6d82ed9fe346f4ed67f59d4fc0ca8)
ALTER TABLE `RICE`.`krms_term_rslvr_attr_t` ADD CONSTRAINT `KRMS_TERM_RSLVR_ATTR_FK1` FOREIGN KEY (`TERM_RSLVR_ID`) REFERENCES `RICE`.`krms_term_rslvr_t` (`TERM_RSLVR_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-698', '2.0.4-SNP', '3:99a6d82ed9fe346f4ed67f59d4fc0ca8', 698);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-699::Jeff Caddel (generated)::(Checksum: 3:fa5af3c23ced6cf12e4985907c4a8f36)
ALTER TABLE `RICE`.`krms_term_rslvr_input_spec_t` ADD CONSTRAINT `KRMS_INPUT_ASSET_FK1` FOREIGN KEY (`TERM_RSLVR_ID`) REFERENCES `RICE`.`krms_term_rslvr_t` (`TERM_RSLVR_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-699', '2.0.4-SNP', '3:fa5af3c23ced6cf12e4985907c4a8f36', 699);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-700::Jeff Caddel (generated)::(Checksum: 3:ca417dd1fd843a3209ff8092d19f52b2)
ALTER TABLE `RICE`.`krms_term_rslvr_input_spec_t` ADD CONSTRAINT `KRMS_INPUT_ASSET_FK2` FOREIGN KEY (`TERM_SPEC_ID`) REFERENCES `RICE`.`krms_term_spec_t` (`TERM_SPEC_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-700', '2.0.4-SNP', '3:ca417dd1fd843a3209ff8092d19f52b2', 700);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-701::Jeff Caddel (generated)::(Checksum: 3:db64951b6abf4c207903ad2f828321be)
ALTER TABLE `RICE`.`krms_term_rslvr_parm_spec_t` ADD CONSTRAINT `KRMS_TERM_RESLV_PARM_FK1` FOREIGN KEY (`TERM_RSLVR_ID`) REFERENCES `RICE`.`krms_term_rslvr_t` (`TERM_RSLVR_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-701', '2.0.4-SNP', '3:db64951b6abf4c207903ad2f828321be', 701);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-702::Jeff Caddel (generated)::(Checksum: 3:ddef4dcd5daa9c1bd6fdf9d4b4248459)
ALTER TABLE `RICE`.`krms_term_rslvr_t` ADD CONSTRAINT `KRMS_TERM_RSLVR_FK1` FOREIGN KEY (`OUTPUT_TERM_SPEC_ID`) REFERENCES `RICE`.`krms_term_spec_t` (`TERM_SPEC_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-702', '2.0.4-SNP', '3:ddef4dcd5daa9c1bd6fdf9d4b4248459', 702);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-703::Jeff Caddel (generated)::(Checksum: 3:bca183eb3ca1145109a7a40acf3ac359)
ALTER TABLE `RICE`.`krms_term_rslvr_t` ADD CONSTRAINT `KRMS_TERM_RSLVR_FK3` FOREIGN KEY (`TYP_ID`) REFERENCES `RICE`.`krms_typ_t` (`TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-703', '2.0.4-SNP', '3:bca183eb3ca1145109a7a40acf3ac359', 703);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-704::Jeff Caddel (generated)::(Checksum: 3:66dd1cc6e8445ffbbdbaeb04e7859e89)
ALTER TABLE `RICE`.`krms_term_spec_ctgry_t` ADD CONSTRAINT `KRMS_TERM_SPEC_CTGRY_FK2` FOREIGN KEY (`CTGRY_ID`) REFERENCES `RICE`.`krms_ctgry_t` (`CTGRY_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-704', '2.0.4-SNP', '3:66dd1cc6e8445ffbbdbaeb04e7859e89', 704);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-705::Jeff Caddel (generated)::(Checksum: 3:1b81f2d5b94eed9db177746f8a6fe410)
ALTER TABLE `RICE`.`krms_term_spec_ctgry_t` ADD CONSTRAINT `KRMS_TERM_SPEC_CTGRY_FK1` FOREIGN KEY (`TERM_SPEC_ID`) REFERENCES `RICE`.`krms_term_spec_t` (`TERM_SPEC_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-705', '2.0.4-SNP', '3:1b81f2d5b94eed9db177746f8a6fe410', 705);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-706::Jeff Caddel (generated)::(Checksum: 3:5190b7f810dc2cda5e5bb0ac578e8143)
ALTER TABLE `RICE`.`krms_term_t` ADD CONSTRAINT `KRMS_TERM_T__FK1` FOREIGN KEY (`TERM_SPEC_ID`) REFERENCES `RICE`.`krms_term_spec_t` (`TERM_SPEC_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-706', '2.0.4-SNP', '3:5190b7f810dc2cda5e5bb0ac578e8143', 706);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-707::Jeff Caddel (generated)::(Checksum: 3:02f3231f6acd1d1ecbdec00bbb5c0dec)
ALTER TABLE `RICE`.`krms_typ_attr_t` ADD CONSTRAINT `KRMS_TYP_ATTR_FK1` FOREIGN KEY (`ATTR_DEFN_ID`) REFERENCES `RICE`.`krms_attr_defn_t` (`ATTR_DEFN_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-707', '2.0.4-SNP', '3:02f3231f6acd1d1ecbdec00bbb5c0dec', 707);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-708::Jeff Caddel (generated)::(Checksum: 3:58fa4cdc7daa2835ccb80bee5af78c82)
ALTER TABLE `RICE`.`krms_typ_attr_t` ADD CONSTRAINT `KRMS_TYP_ATTR_FK2` FOREIGN KEY (`TYP_ID`) REFERENCES `RICE`.`krms_typ_t` (`TYP_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-708', '2.0.4-SNP', '3:58fa4cdc7daa2835ccb80bee5af78c82', 708);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-709::Jeff Caddel (generated)::(Checksum: 3:bec0dcc37af27da815ba87075e9797cc)
ALTER TABLE `RICE`.`krns_att_t` ADD CONSTRAINT `KRNS_ATT_TR1` FOREIGN KEY (`NTE_ID`) REFERENCES `RICE`.`krns_nte_t` (`NTE_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-709', '2.0.4-SNP', '3:bec0dcc37af27da815ba87075e9797cc', 709);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-710::Jeff Caddel (generated)::(Checksum: 3:032c1c2bb193bca9f93dacf1e775f125)
ALTER TABLE `RICE`.`krns_maint_doc_t` ADD CONSTRAINT `KRNS_MAINT_DOC_TR1` FOREIGN KEY (`DOC_HDR_ID`) REFERENCES `RICE`.`krns_doc_hdr_t` (`DOC_HDR_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-710', '2.0.4-SNP', '3:032c1c2bb193bca9f93dacf1e775f125', 710);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-711::Jeff Caddel (generated)::(Checksum: 3:da293e303b7a14a85b6072d2670d5378)
ALTER TABLE `RICE`.`krns_nte_t` ADD CONSTRAINT `KRNS_NTE_TR1` FOREIGN KEY (`NTE_TYP_CD`) REFERENCES `RICE`.`krns_nte_typ_t` (`NTE_TYP_CD`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-711', '2.0.4-SNP', '3:da293e303b7a14a85b6072d2670d5378', 711);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-712::Jeff Caddel (generated)::(Checksum: 3:c8aa099c269652d92d4891584078159b)
ALTER TABLE `RICE`.`krsb_qrtz_blob_triggers` ADD CONSTRAINT `KRSB_QRTZ_BLOB_TRIGGERS_TR1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `RICE`.`krsb_qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-712', '2.0.4-SNP', '3:c8aa099c269652d92d4891584078159b', 712);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-713::Jeff Caddel (generated)::(Checksum: 3:c0ead9a24dc0ea547039771447e06228)
ALTER TABLE `RICE`.`krsb_qrtz_cron_triggers` ADD CONSTRAINT `KRSB_QRTZ_CRON_TRIGGERS_TR1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `RICE`.`krsb_qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-713', '2.0.4-SNP', '3:c0ead9a24dc0ea547039771447e06228', 713);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-714::Jeff Caddel (generated)::(Checksum: 3:8cc74f07c1e924b18c16ec13f5cb7199)
ALTER TABLE `RICE`.`krsb_qrtz_job_listeners` ADD CONSTRAINT `KRSB_QUARTZ_JOB_LISTENERS_TR1` FOREIGN KEY (`JOB_NAME`, `JOB_GROUP`) REFERENCES `RICE`.`krsb_qrtz_job_details` (`JOB_NAME`, `JOB_GROUP`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-714', '2.0.4-SNP', '3:8cc74f07c1e924b18c16ec13f5cb7199', 714);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-715::Jeff Caddel (generated)::(Checksum: 3:64fc5a88975176dab52c60c24cdc9ff5)
ALTER TABLE `RICE`.`krsb_qrtz_simple_triggers` ADD CONSTRAINT `KRSB_QRTZ_SIMPLE_TRIGGERS_TR1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `RICE`.`krsb_qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-715', '2.0.4-SNP', '3:64fc5a88975176dab52c60c24cdc9ff5', 715);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-716::Jeff Caddel (generated)::(Checksum: 3:b12feed4aedf016bf69c60b013adc901)
ALTER TABLE `RICE`.`krsb_qrtz_trigger_listeners` ADD CONSTRAINT `KRSB_QRTZ_TRIGGER_LISTENE_TR1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `RICE`.`krsb_qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-716', '2.0.4-SNP', '3:b12feed4aedf016bf69c60b013adc901', 716);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-717::Jeff Caddel (generated)::(Checksum: 3:a798c9efae11985a1f0d5ee6fe96b8b9)
ALTER TABLE `RICE`.`krsb_svc_def_t` ADD CONSTRAINT `KRSB_SVC_DEF_FK1` FOREIGN KEY (`SVC_DSCRPTR_ID`) REFERENCES `RICE`.`krsb_svc_dscrptr_t` (`SVC_DSCRPTR_ID`) ON UPDATE NO ACTION ON DELETE CASCADE;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-717', '2.0.4-SNP', '3:a798c9efae11985a1f0d5ee6fe96b8b9', 717);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-718::Jeff Caddel (generated)::(Checksum: 3:c08e17f3ca09b1b6bb2ee22bc6aee38d)
ALTER TABLE `RICE`.`trv_acct` ADD CONSTRAINT `TRV_ACCT_FK1` FOREIGN KEY (`ACCT_FO_ID`) REFERENCES `RICE`.`trv_acct_fo` (`ACCT_FO_ID`) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Add Foreign Key Constraint', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-718', '2.0.4-SNP', '3:c08e17f3ca09b1b6bb2ee22bc6aee38d', 718);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-719::Jeff Caddel (generated)::(Checksum: 3:fecdc49577a90318b2c800a2a9cca17a)
CREATE VIEW `krim_grp_mbr_v` AS select `g`.`NMSPC_CD` AS `NMSPC_CD`,`g`.`GRP_NM` AS `grp_nm`,`g`.`GRP_ID` AS `GRP_ID`,`p`.`PRNCPL_NM` AS `PRNCPL_NM`,`p`.`PRNCPL_ID` AS `PRNCPL_ID`,`mg`.`GRP_NM` AS `mbr_grp_nm`,`mg`.`GRP_ID` AS `mbr_grp_id` from ((((`rice`.`krim_grp_mbr_t` `gm` left join `rice`.`krim_grp_t` `g` on((`g`.`GRP_ID` = `gm`.`GRP_ID`))) left join `rice`.`krim_grp_t` `mg` on(((`mg`.`GRP_ID` = `gm`.`MBR_ID`) and (`gm`.`MBR_TYP_CD` = 'G')))) left join `rice`.`krim_prncpl_t` `p` on(((`p`.`PRNCPL_ID` = `gm`.`MBR_ID`) and (`gm`.`MBR_TYP_CD` = 'P')))) left join `rice`.`krim_entity_nm_t` `en` on(((`en`.`ENTITY_ID` = `p`.`ENTITY_ID`) and (`en`.`DFLT_IND` = 'Y') and (`en`.`ACTV_IND` = 'Y')))) order by `g`.`NMSPC_CD`,`g`.`GRP_NM`,`p`.`PRNCPL_NM`;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-719', '2.0.4-SNP', '3:fecdc49577a90318b2c800a2a9cca17a', 719);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-720::Jeff Caddel (generated)::(Checksum: 3:1e488628477fc8b725a27f03daf2d47b)
CREATE VIEW `krim_grp_v` AS select `g`.`NMSPC_CD` AS `NMSPC_CD`,`g`.`GRP_NM` AS `grp_nm`,`g`.`GRP_ID` AS `GRP_ID`,`t`.`NM` AS `grp_typ_nm`,`a`.`NM` AS `attr_nm`,`d`.`ATTR_VAL` AS `attr_val` from (((`rice`.`krim_grp_t` `g` left join `rice`.`krim_grp_attr_data_t` `d` on((`d`.`GRP_ID` = `g`.`GRP_ID`))) left join `rice`.`krim_attr_defn_t` `a` on((`a`.`KIM_ATTR_DEFN_ID` = `d`.`KIM_ATTR_DEFN_ID`))) left join `rice`.`krim_typ_t` `t` on((`g`.`KIM_TYP_ID` = `t`.`KIM_TYP_ID`)));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-720', '2.0.4-SNP', '3:1e488628477fc8b725a27f03daf2d47b', 720);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-721::Jeff Caddel (generated)::(Checksum: 3:0c18b0e331aa5d8b020ea6c577944819)
CREATE VIEW `krim_perm_attr_v` AS select `t`.`NMSPC_CD` AS `tmpl_nmspc_cd`,`t`.`NM` AS `tmpl_nm`,`t`.`PERM_TMPL_ID` AS `PERM_TMPL_ID`,`p`.`NMSPC_CD` AS `perm_nmspc_cd`,`p`.`NM` AS `perm_nm`,`p`.`PERM_ID` AS `PERM_ID`,`a`.`NM` AS `attr_nm`,`ad`.`ATTR_VAL` AS `attr_val` from (((`rice`.`krim_perm_t` `p` left join `rice`.`krim_perm_tmpl_t` `t` on((`p`.`PERM_TMPL_ID` = `t`.`PERM_TMPL_ID`))) left join `rice`.`krim_perm_attr_data_t` `ad` on((`p`.`PERM_ID` = `ad`.`PERM_ID`))) left join `rice`.`krim_attr_defn_t` `a` on((`ad`.`KIM_ATTR_DEFN_ID` = `a`.`KIM_ATTR_DEFN_ID`))) order by `t`.`NMSPC_CD`,`t`.`NM`,`p`.`NMSPC_CD`,`p`.`PERM_ID`,`a`.`NM`;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-721', '2.0.4-SNP', '3:0c18b0e331aa5d8b020ea6c577944819', 721);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-722::Jeff Caddel (generated)::(Checksum: 3:7044457dec8a07270ce5e5a5bf81e647)
CREATE VIEW `krim_perm_v` AS select `t`.`NMSPC_CD` AS `tmpl_nmspc_cd`,`t`.`NM` AS `tmpl_nm`,`t`.`PERM_TMPL_ID` AS `PERM_TMPL_ID`,`p`.`NMSPC_CD` AS `perm_nmspc_cd`,`p`.`NM` AS `perm_nm`,`p`.`PERM_ID` AS `PERM_ID`,`typ`.`NM` AS `perm_typ_nm`,`typ`.`SRVC_NM` AS `SRVC_NM` from ((`rice`.`krim_perm_t` `p` join `rice`.`krim_perm_tmpl_t` `t` on((`p`.`PERM_TMPL_ID` = `t`.`PERM_TMPL_ID`))) left join `rice`.`krim_typ_t` `typ` on((`t`.`KIM_TYP_ID` = `typ`.`KIM_TYP_ID`)));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-722', '2.0.4-SNP', '3:7044457dec8a07270ce5e5a5bf81e647', 722);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-723::Jeff Caddel (generated)::(Checksum: 3:8f2cbf826371a6310e9b83367dc7fe40)
CREATE VIEW `krim_prncpl_v` AS select `p`.`PRNCPL_ID` AS `PRNCPL_ID`,`p`.`PRNCPL_NM` AS `PRNCPL_NM`,`en`.`FIRST_NM` AS `FIRST_NM`,`en`.`LAST_NM` AS `LAST_NM`,`ea`.`AFLTN_TYP_CD` AS `AFLTN_TYP_CD`,`ea`.`CAMPUS_CD` AS `CAMPUS_CD`,`eei`.`EMP_STAT_CD` AS `EMP_STAT_CD`,`eei`.`EMP_TYP_CD` AS `EMP_TYP_CD` from (((`rice`.`krim_prncpl_t` `p` left join `rice`.`krim_entity_emp_info_t` `eei` on((`eei`.`ENTITY_ID` = `p`.`ENTITY_ID`))) left join `rice`.`krim_entity_afltn_t` `ea` on((`ea`.`ENTITY_ID` = `p`.`ENTITY_ID`))) left join `rice`.`krim_entity_nm_t` `en` on(((`p`.`ENTITY_ID` = `en`.`ENTITY_ID`) and ('Y' = `en`.`DFLT_IND`))));

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-723', '2.0.4-SNP', '3:8f2cbf826371a6310e9b83367dc7fe40', 723);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-724::Jeff Caddel (generated)::(Checksum: 3:2c4796f904fc43578f86bb7a181c8c62)
CREATE VIEW `krim_role_grp_v` AS select `r`.`NMSPC_CD` AS `NMSPC_CD`,`r`.`ROLE_NM` AS `ROLE_NM`,`r`.`ROLE_ID` AS `role_id`,`g`.`NMSPC_CD` AS `grp_nmspc_cd`,`g`.`GRP_NM` AS `GRP_NM`,`rm`.`ROLE_MBR_ID` AS `ROLE_MBR_ID`,`a`.`NM` AS `attr_nm`,`d`.`ATTR_VAL` AS `attr_val` from ((((`rice`.`krim_role_mbr_t` `rm` left join `rice`.`krim_role_t` `r` on((`r`.`ROLE_ID` = `rm`.`ROLE_ID`))) left join `rice`.`krim_grp_t` `g` on((`g`.`GRP_ID` = `rm`.`MBR_ID`))) left join `rice`.`krim_role_mbr_attr_data_t` `d` on((`d`.`ROLE_MBR_ID` = `rm`.`ROLE_MBR_ID`))) left join `rice`.`krim_attr_defn_t` `a` on((`a`.`KIM_ATTR_DEFN_ID` = `d`.`KIM_ATTR_DEFN_ID`))) where (`rm`.`MBR_TYP_CD` = 'G') order by `r`.`NMSPC_CD`,`r`.`ROLE_NM`,`g`.`NMSPC_CD`,`g`.`GRP_NM`,`rm`.`ROLE_MBR_ID`,`a`.`NM`;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-724', '2.0.4-SNP', '3:2c4796f904fc43578f86bb7a181c8c62', 724);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-725::Jeff Caddel (generated)::(Checksum: 3:10fe185ae6632f62bfe1e50fcde78bd2)
CREATE VIEW `krim_role_perm_v` AS select `r`.`NMSPC_CD` AS `NMSPC_CD`,`r`.`ROLE_NM` AS `ROLE_NM`,`r`.`ROLE_ID` AS `role_id`,`pt`.`NMSPC_CD` AS `tmpl_nmspc_cd`,`pt`.`NM` AS `tmpl_nm`,`pt`.`PERM_TMPL_ID` AS `PERM_TMPL_ID`,`p`.`NMSPC_CD` AS `perm_nmpsc_cd`,`p`.`NM` AS `perm_nm`,`p`.`PERM_ID` AS `PERM_ID`,`a`.`NM` AS `attr_nm`,`ad`.`ATTR_VAL` AS `attr_val` from (((((`rice`.`krim_perm_t` `p` left join `rice`.`krim_perm_tmpl_t` `pt` on((`p`.`PERM_TMPL_ID` = `pt`.`PERM_TMPL_ID`))) left join `rice`.`krim_perm_attr_data_t` `ad` on((`p`.`PERM_ID` = `ad`.`PERM_ID`))) left join `rice`.`krim_attr_defn_t` `a` on((`ad`.`KIM_ATTR_DEFN_ID` = `a`.`KIM_ATTR_DEFN_ID`))) left join `rice`.`krim_role_perm_t` `rp` on((`rp`.`PERM_ID` = `p`.`PERM_ID`))) left join `rice`.`krim_role_t` `r` on((`rp`.`ROLE_ID` = `r`.`ROLE_ID`))) order by `r`.`NMSPC_CD`,`r`.`ROLE_NM`,`pt`.`NMSPC_CD`,`pt`.`NM`,`p`.`PERM_ID`,`a`.`NM`;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-725', '2.0.4-SNP', '3:10fe185ae6632f62bfe1e50fcde78bd2', 725);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-726::Jeff Caddel (generated)::(Checksum: 3:fd1d4797833cf1a258ddaac53fc0efca)
CREATE VIEW `krim_role_prncpl_v` AS select `r`.`NMSPC_CD` AS `NMSPC_CD`,`r`.`ROLE_NM` AS `ROLE_NM`,`r`.`ROLE_ID` AS `ROLE_ID`,`p`.`PRNCPL_NM` AS `PRNCPL_NM`,`p`.`PRNCPL_ID` AS `PRNCPL_ID`,`en`.`FIRST_NM` AS `FIRST_NM`,`en`.`LAST_NM` AS `LAST_NM`,`rm`.`ROLE_MBR_ID` AS `ROLE_MBR_ID`,`ad`.`NM` AS `attr_nm`,`rmad`.`ATTR_VAL` AS `attr_val` from (((((`rice`.`krim_role_t` `r` left join `rice`.`krim_role_mbr_t` `rm` on((`r`.`ROLE_ID` = `rm`.`ROLE_ID`))) left join `rice`.`krim_role_mbr_attr_data_t` `rmad` on((`rm`.`ROLE_MBR_ID` = `rmad`.`ROLE_MBR_ID`))) left join `rice`.`krim_attr_defn_t` `ad` on((`rmad`.`KIM_ATTR_DEFN_ID` = `ad`.`KIM_ATTR_DEFN_ID`))) left join `rice`.`krim_prncpl_t` `p` on(((`rm`.`MBR_ID` = `p`.`PRNCPL_ID`) and (`rm`.`MBR_TYP_CD` = 'P')))) left join `rice`.`krim_entity_nm_t` `en` on((`p`.`ENTITY_ID` = `en`.`ENTITY_ID`))) where (`en`.`DFLT_IND` = 'Y') order by `r`.`NMSPC_CD`,`r`.`ROLE_NM`,`p`.`PRNCPL_NM`,`rm`.`ROLE_MBR_ID`,`ad`.`NM`;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-726', '2.0.4-SNP', '3:fd1d4797833cf1a258ddaac53fc0efca', 726);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-727::Jeff Caddel (generated)::(Checksum: 3:62e46e8b94f6367003e1e7865774ec52)
CREATE VIEW `krim_role_role_v` AS select `r`.`NMSPC_CD` AS `NMSPC_CD`,`r`.`ROLE_NM` AS `ROLE_NM`,`r`.`ROLE_ID` AS `role_id`,`mr`.`NMSPC_CD` AS `mbr_role_nmspc_cd`,`mr`.`ROLE_NM` AS `mbr_role_nm`,`mr`.`ROLE_ID` AS `mbr_role_id`,`rm`.`ROLE_MBR_ID` AS `role_mbr_id`,`a`.`NM` AS `attr_nm`,`d`.`ATTR_VAL` AS `attr_val` from ((((`rice`.`krim_role_mbr_t` `rm` left join `rice`.`krim_role_t` `r` on((`r`.`ROLE_ID` = `rm`.`ROLE_ID`))) left join `rice`.`krim_role_t` `mr` on((`mr`.`ROLE_ID` = `rm`.`MBR_ID`))) left join `rice`.`krim_role_mbr_attr_data_t` `d` on((`d`.`ROLE_MBR_ID` = `rm`.`ROLE_MBR_ID`))) left join `rice`.`krim_attr_defn_t` `a` on((`a`.`KIM_ATTR_DEFN_ID` = `d`.`KIM_ATTR_DEFN_ID`))) where (`rm`.`MBR_TYP_CD` = 'R') order by `r`.`NMSPC_CD`,`r`.`ROLE_NM`,`mr`.`NMSPC_CD`,`mr`.`ROLE_NM`,`rm`.`ROLE_MBR_ID`,`a`.`NM`;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-727', '2.0.4-SNP', '3:62e46e8b94f6367003e1e7865774ec52', 727);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-728::Jeff Caddel (generated)::(Checksum: 3:3832a160096f40e74856dbf3384c577e)
CREATE VIEW `krim_role_v` AS select `r`.`NMSPC_CD` AS `NMSPC_CD`,`r`.`ROLE_NM` AS `ROLE_NM`,`r`.`ROLE_ID` AS `ROLE_ID`,`t`.`NM` AS `role_typ_nm`,`t`.`SRVC_NM` AS `SRVC_NM`,`t`.`KIM_TYP_ID` AS `KIM_TYP_ID` from `rice`.`krim_role_t` `r` join `rice`.`krim_typ_t` `t` where ((`t`.`KIM_TYP_ID` = `r`.`KIM_TYP_ID`) and (`r`.`ACTV_IND` = 'Y')) order by `r`.`NMSPC_CD`,`r`.`ROLE_NM`;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-728', '2.0.4-SNP', '3:3832a160096f40e74856dbf3384c577e', 728);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-729::Jeff Caddel (generated)::(Checksum: 3:b0f78f5a1a38de8e85c07733f151649a)
CREATE VIEW `krim_rsp_attr_v` AS select `rice`.`krim_typ_t`.`NM` AS `responsibility_type_name`,`rice`.`krim_rsp_tmpl_t`.`NM` AS `rsp_TEMPLATE_NAME`,`rice`.`krim_rsp_t`.`NMSPC_CD` AS `rsp_namespace_code`,`rice`.`krim_rsp_t`.`NM` AS `rsp_NAME`,`rice`.`krim_rsp_t`.`RSP_ID` AS `rsp_id`,`rice`.`krim_attr_defn_t`.`NM` AS `attribute_name`,`rice`.`krim_rsp_attr_data_t`.`ATTR_VAL` AS `attribute_value` from ((((`rice`.`krim_rsp_t` join `rice`.`krim_rsp_attr_data_t` on((`rice`.`krim_rsp_t`.`RSP_ID` = `rice`.`krim_rsp_attr_data_t`.`RSP_ID`))) join `rice`.`krim_attr_defn_t` on((`rice`.`krim_rsp_attr_data_t`.`KIM_ATTR_DEFN_ID` = `rice`.`krim_attr_defn_t`.`KIM_ATTR_DEFN_ID`))) join `rice`.`krim_rsp_tmpl_t` on((`rice`.`krim_rsp_t`.`RSP_TMPL_ID` = `rice`.`krim_rsp_tmpl_t`.`RSP_TMPL_ID`))) join `rice`.`krim_typ_t` on((`rice`.`krim_rsp_tmpl_t`.`KIM_TYP_ID` = `rice`.`krim_typ_t`.`KIM_TYP_ID`))) order by `rice`.`krim_rsp_tmpl_t`.`NM`,`rice`.`krim_rsp_t`.`NM`,`rice`.`krim_attr_defn_t`.`NM`;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-729', '2.0.4-SNP', '3:b0f78f5a1a38de8e85c07733f151649a', 729);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-730::Jeff Caddel (generated)::(Checksum: 3:c6928f7c1d5b53d47b45b7607fd2e055)
CREATE VIEW `krim_rsp_role_actn_v` AS select `rsp`.`NMSPC_CD` AS `rsp_nmspc_cd`,`rsp`.`RSP_ID` AS `rsp_id`,`r`.`NMSPC_CD` AS `NMSPC_CD`,`r`.`ROLE_NM` AS `ROLE_NM`,`rr`.`ROLE_ID` AS `ROLE_ID`,`rm`.`MBR_ID` AS `MBR_ID`,`rm`.`MBR_TYP_CD` AS `MBR_TYP_CD`,`rm`.`ROLE_MBR_ID` AS `ROLE_MBR_ID`,`actn`.`ACTN_TYP_CD` AS `ACTN_TYP_CD`,`actn`.`ACTN_PLCY_CD` AS `ACTN_PLCY_CD`,`actn`.`FRC_ACTN` AS `FRC_ACTN`,`actn`.`PRIORITY_NBR` AS `PRIORITY_NBR` from (((((`rice`.`krim_rsp_t` `rsp` left join `rice`.`krim_rsp_tmpl_t` `rspt` on((`rsp`.`RSP_TMPL_ID` = `rspt`.`RSP_TMPL_ID`))) left join `rice`.`krim_role_rsp_t` `rr` on((`rr`.`RSP_ID` = `rsp`.`RSP_ID`))) left join `rice`.`krim_role_mbr_t` `rm` on((`rm`.`ROLE_ID` = `rr`.`ROLE_ID`))) left join `rice`.`krim_role_rsp_actn_t` `actn` on(((`actn`.`ROLE_RSP_ID` = `rr`.`ROLE_RSP_ID`) and ((`actn`.`ROLE_MBR_ID` = `rm`.`ROLE_MBR_ID`) or (`actn`.`ROLE_MBR_ID` = '*'))))) left join `rice`.`krim_role_t` `r` on((`rr`.`ROLE_ID` = `r`.`ROLE_ID`))) order by `rsp`.`NMSPC_CD`,`rsp`.`RSP_ID`,`rr`.`ROLE_ID`,`rm`.`ROLE_MBR_ID`;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-730', '2.0.4-SNP', '3:c6928f7c1d5b53d47b45b7607fd2e055', 730);

-- Changeset src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml::1312847754140-731::Jeff Caddel (generated)::(Checksum: 3:ce56fc697e6f8b24f935d7c776024896)
CREATE VIEW `krim_rsp_role_v` AS select `rspt`.`NMSPC_CD` AS `rsp_tmpl_nmspc_cd`,`rspt`.`NM` AS `rsp_tmpl_nm`,`rsp`.`NMSPC_CD` AS `rsp_nmspc_cd`,`rsp`.`NM` AS `rsp_nm`,`rsp`.`RSP_ID` AS `rsp_id`,`a`.`NM` AS `attr_nm`,`d`.`ATTR_VAL` AS `attr_val`,`r`.`NMSPC_CD` AS `NMSPC_CD`,`r`.`ROLE_NM` AS `ROLE_NM`,`rr`.`ROLE_ID` AS `ROLE_ID` from (((((`rice`.`krim_rsp_t` `rsp` left join `rice`.`krim_rsp_tmpl_t` `rspt` on((`rsp`.`RSP_TMPL_ID` = `rspt`.`RSP_TMPL_ID`))) left join `rice`.`krim_rsp_attr_data_t` `d` on((`rsp`.`RSP_ID` = `d`.`RSP_ID`))) left join `rice`.`krim_attr_defn_t` `a` on((`d`.`KIM_ATTR_DEFN_ID` = `a`.`KIM_ATTR_DEFN_ID`))) left join `rice`.`krim_role_rsp_t` `rr` on((`rr`.`RSP_ID` = `rsp`.`RSP_ID`))) left join `rice`.`krim_role_t` `r` on((`rr`.`ROLE_ID` = `r`.`ROLE_ID`))) order by `rspt`.`NMSPC_CD`,`rspt`.`NM`,`rsp`.`NMSPC_CD`,`rsp`.`NM`,`rsp`.`RSP_ID`,`a`.`NM`,`d`.`ATTR_VAL`;

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('Jeff Caddel (generated)', '', NOW(), 'Create View', 'EXECUTED', 'src/test/resources/mysql/rice-2.0.0-m7/xml/constraints.xml', '1312847754140-731', '2.0.4-SNP', '3:ce56fc697e6f8b24f935d7c776024896', 731);

-- Release Database Lock
-- Release Database Lock
