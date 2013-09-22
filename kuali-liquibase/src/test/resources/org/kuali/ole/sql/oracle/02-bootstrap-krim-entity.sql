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

-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: 02_bootstrap_krim_entity_data.xml
-- *********************************************************************

-- Lock Database
-- Changeset 02_bootstrap_krim_entity_data.xml::KRIM_ENTITY_T::ole::(Checksum: 3:f81c23e7bbd1329029d9ca5981e6a2cd)
INSERT INTO KRIM_ENTITY_T (ACTV_IND, ENTITY_ID, OBJ_ID) VALUES ('Y', 'OLE2', 'OLE2')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '02_bootstrap_krim_entity_data.xml', 'KRIM_ENTITY_T', '2.0.5', '3:f81c23e7bbd1329029d9ca5981e6a2cd', 1)
/

-- Changeset 02_bootstrap_krim_entity_data.xml::KRIM_PRNCPL_T::ole::(Checksum: 3:8f5d04880517008a052bd8cd9308fc63)
INSERT INTO KRIM_PRNCPL_T (ACTV_IND, ENTITY_ID, OBJ_ID, PRNCPL_ID, PRNCPL_NM) VALUES ('Y', 'OLE2', 'OLE2', 'OLE2', 'ole')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '02_bootstrap_krim_entity_data.xml', 'KRIM_PRNCPL_T', '2.0.5', '3:8f5d04880517008a052bd8cd9308fc63', 2)
/

-- Changeset 02_bootstrap_krim_entity_data.xml::KRIM_ENTITY_ENT_TYP_T::ole::(Checksum: 3:5e66dac267ce7ed932a7aba73a8526ca)
INSERT INTO KRIM_ENTITY_ENT_TYP_T (ACTV_IND, ENTITY_ID, ENT_TYP_CD, OBJ_ID) VALUES ('Y', 'OLE2', 'SYSTEM', 'OLE2-SYSTEM')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '02_bootstrap_krim_entity_data.xml', 'KRIM_ENTITY_ENT_TYP_T', '2.0.5', '3:5e66dac267ce7ed932a7aba73a8526ca', 3)
/

-- Changeset 02_bootstrap_krim_entity_data.xml::KRIM_ENTITY_NM_T::ole::(Checksum: 3:ce993b55f22c6ee43e8d8ca1ce1e1d5e)
INSERT INTO KRIM_ENTITY_NM_T (ACTV_IND, DFLT_IND, ENTITY_ID, ENTITY_NM_ID, FIRST_NM, LAST_NM, MIDDLE_NM, NM_TYP_CD, OBJ_ID, SUFFIX_NM, TITLE_NM) VALUES ('Y', 'Y', 'OLE2', 'OLE2', 'OLE', 'Admin', '', 'PRFR', 'OLE2', '', '')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '02_bootstrap_krim_entity_data.xml', 'KRIM_ENTITY_NM_T', '2.0.5', '3:ce993b55f22c6ee43e8d8ca1ce1e1d5e', 4)
/

-- Release Database Lock
-- Release Database Lock
