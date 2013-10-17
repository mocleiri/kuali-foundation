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
-- Change Log: 03_bootstrap_krim_role_perm_data.xml
-- *********************************************************************

-- Lock Database
-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_ATTR_DEFN_T::ole::(Checksum: 3:8c1c66be2d7665b2b69eecf27723fa4d)
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE22', '', 'chartOfAccountsCode', 'OLE-COA', 'OLE22', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE23', '', 'accountNumber', 'OLE-COA', 'OLE23', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE24', '', 'organizationCode', 'OLE-COA', 'OLE24', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE25', '', 'descendHierarchy', 'OLE-COA', 'OLE25', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE26', '', 'fromAmount', 'OLE-COA', 'OLE26', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE27', '', 'toAmount', 'OLE-COA', 'OLE27', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE28', '', 'accountingLineOverrideCode', 'OLE-COA', 'OLE28', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE30', '', 'subFundGroupCode', 'OLE-COA', 'OLE30', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE31', '', 'purchasingCommodityCode', 'OLE-VND', 'OLE31', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE32', '', 'contractManagerCode', 'OLE-VND', 'OLE32', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE33', '', 'customerProfileId', 'OLE-PDP', 'OLE33', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.module.purap.identity.PurapKimAttributes', 'OLE35', '', 'sensitiveDataCode', 'OLE-PURAP', 'OLE35', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE36', '', 'vendorTypeCode', 'OLE-VND', 'OLE36', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE38', '', 'disbursementVoucherPaymentMethodCode', 'OLE-FP', 'OLE38', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLE39', '', 'subAccountNumber', 'OLE-COA', 'OLE39', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.module.purap.identity.PurapKimAttributes', 'OLE45', '', 'documentSensitive', 'OLE-PURAP', 'OLE45', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sec.identity.SecKimAttributes', 'OLESEC100', '', 'operator', 'OLE-SEC', 'OLESEC100', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sec.identity.SecKimAttributes', 'OLESEC101', '', 'propertyValue', 'OLE-SEC', 'OLESEC101', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sec.identity.SecKimAttributes', 'OLESEC102', '', 'constraintCode', 'OLE-SEC', 'OLESEC102', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sec.identity.SecKimAttributes', 'OLESEC103', '', 'overrideDeny', 'OLE-SEC', 'OLESEC103', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLEMI6886-ATTRDEF', '', 'achTransactionTypeCode', 'OLE-PDP', 'OLEMI6886-ATTRDEF', '1')
/

INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND, CMPNT_NM, KIM_ATTR_DEFN_ID, LBL, NM, NMSPC_CD, OBJ_ID, VER_NBR) VALUES ('Y', 'org.kuali.ole.sys.identity.OleKimAttributes', 'OLECNTRB199-ATTRDEF1', '', 'filePath', 'OLE-SYS', 'OLECNTRB199-ATTRDEF1', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_ATTR_DEFN_T', '2.0.5', '3:8c1c66be2d7665b2b69eecf27723fa4d', 1)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_TYP_T::ole::(Checksum: 3:2583667f71f45c28adb783f565a02635)
INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE25', 'Chart', 'OLE-COA', 'OLE25', '{OLE}chartRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE26', 'Account', 'OLE-COA', 'OLE26', '{OLE}accountRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE27', 'Organization', 'OLE-COA', 'OLE27', '{OLE}organizationRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE28', 'Organization: Optionally Hierarchical', 'OLE-SYS', 'OLE28', '{OLE}organizationOptionalHierarchyQualifierRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE29', 'Organization: Always Hierarchical & Document Type', 'OLE-SYS', 'OLE29', '{OLE}organizationHierarchyReviewRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE30', 'Organization: Always Hierarchical, Document Type & Accounting', 'OLE-SYS', 'OLE30', '{OLE}accountingOrganizationHierarchyReviewRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE31', 'Sub-Fund & Document Type', 'OLE-COA', 'OLE31', '{OLE}subFundReviewRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE32', 'Customer', 'OLE-PDP', 'OLE32', '{OLE}customerRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE33', 'Commodity & Document Type', 'OLE-VND', 'OLE33', '{OLE}commodityReviewRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE34', 'Contract Manager', 'OLE-VND', 'OLE34', '{OLE}contractManagerRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE35', 'Sensitive Data', 'OLE-PURAP', 'OLE35', '{OLE}sensitiveDataRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE36', 'Financial System User', 'OLE-SYS', 'OLE36', '{OLE}financialSystemUserRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE37', 'Vendor Type', 'OLE-VND', 'OLE37', '{OLE}vendorTypeRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE39', 'Derived Role: Account', 'OLE-COA', 'OLE39', '{OLE}accountDerivedRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE40', 'Derived Role: Chart', 'OLE-COA', 'OLE40', '{OLE}chartDerivedRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE41', 'Derived Role: Employee', 'OLE-SYS', 'OLE41', '{OLE}employeeDerivedRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE46', 'Payment Method', 'OLE-FP', 'OLE46', '{OLE}paymentMethodRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE47', 'Sub-Account & Document Type', 'OLE-COA', 'OLE47', '{OLE}subAccountReviewRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE48', 'Derived Role: Purchasing Document', 'OLE-PURAP', 'OLE48', '{OLE}relatedDocumentDerivedRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE51', 'Financial System Document Type', 'OLE-SYS', 'OLE51', '{OLE}financialSystemDocumentTypePermissionTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE58', 'Derived Role: Permission (Modify Batch Job)', 'OLE-SYS', 'OLE58', '{OLE}batchJobModifierRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE61', 'Derived Role: Payment Request Hold / Cancel Initiator', 'OLE-PURAP', 'OLE61', '{OLE}paymentRequestHoldCancelInitiatorDerivedRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE62', 'Sensitive Document Indicator', 'OLE-PURAP', 'OLE62', '{OLE}potentiallySensitiveDocumentRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE65', 'Derived Role: Cash Receipt Initiator', 'OLE-FP', 'OLE65', '{OLE}cashReceiptInitiatorDerivedRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE68', 'Organization Group', 'OLE-COA', 'OLE68', '{OLE}organizationGroupTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLE69', 'Derived Role: Accounts Payable Document Reviewer', 'OLE-PURAP', 'OLE69', '{OLE}accountsPayableDocumentDerivedRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLESEC1', 'Attribute Name & Document Type', 'OLE-SEC', 'OLESEC1', '{OLE}securityAttributeDocTypePermissionTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLESEC2', 'Attribute Name', 'OLE-SEC', 'OLESEC2', '{OLE}securityAttributePermissionTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLESEC3', 'Attribute Name & Namespace', 'OLE-SEC', 'OLESEC3', '{OLE}securityAttributeNamespacePermissionTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLESEC4', 'Constraint, Operator, & Value', 'OLE-SEC', 'OLESEC4', '{OLE}securityAttributeRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLEMI4553-1', 'Exclude Single Actor Separation Of Duties', 'OLE-SYS', 'OLEMI4553-1', '{OLE}excludeSingleActorSeparationOfDutiesRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLEMI6886-TYP', 'Payee ACH Account', 'OLE-PDP', 'OLEMI6886-TYP', '{OLE}payeeACHAccountRoleTypeService', '1')
/

INSERT INTO KRIM_TYP_T (ACTV_IND, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, SRVC_NM, VER_NBR) VALUES ('Y', 'OLECNTRB199-TYP1', 'Namespace or File Path', 'OLE-SYS', 'OLECNTRB199-TYP1', '{OLE}namespaceOrFilePathPermissionTypeService', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_TYP_T', '2.0.5', '3:2583667f71f45c28adb783f565a02635', 2)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_TYP_ATTR_T::ole::(Checksum: 3:98a04462d82461735dc427a05b184c37)
INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE45', 'OLE110', 'OLE62', 'OLE110', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE22', 'OLE113', 'OLE68', 'OLE113', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE24', 'OLE114', 'OLE68', 'OLE114', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE22', 'OLE40', 'OLE25', 'OLE40', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE22', 'OLE41', 'OLE26', 'OLE41', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE23', 'OLE42', 'OLE26', 'OLE42', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE22', 'OLE43', 'OLE27', 'OLE43', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE24', 'OLE44', 'OLE27', 'OLE44', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE22', 'OLE45', 'OLE28', 'OLE45', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE24', 'OLE46', 'OLE28', 'OLE46', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE25', 'OLE47', 'OLE28', 'OLE47', 'c', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE22', 'OLE48', 'OLE29', 'OLE48', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE24', 'OLE49', 'OLE29', 'OLE49', 'c', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '13', 'OLE50', 'OLE29', 'OLE50', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE22', 'OLE51', 'OLE30', 'OLE51', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE24', 'OLE52', 'OLE30', 'OLE52', 'c', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '13', 'OLE53', 'OLE30', 'OLE53', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE26', 'OLE54', 'OLE30', 'OLE54', 'd', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE27', 'OLE55', 'OLE30', 'OLE55', 'e', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE28', 'OLE56', 'OLE30', 'OLE56', 'f', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE30', 'OLE57', 'OLE31', 'OLE57', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE33', 'OLE58', 'OLE32', 'OLE58', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE31', 'OLE59', 'OLE33', 'OLE59', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE32', 'OLE60', 'OLE34', 'OLE60', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE35', 'OLE61', 'OLE35', 'OLE61', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '4', 'OLE62', 'OLE36', 'OLE62', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE22', 'OLE63', 'OLE36', 'OLE63', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE24', 'OLE64', 'OLE36', 'OLE64', 'c', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE36', 'OLE65', 'OLE37', 'OLE65', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '12', 'OLE67', 'OLE33', 'OLE67', 'c', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '13', 'OLE69', 'OLE31', 'OLE69', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '13', 'OLE70', 'OLE33', 'OLE70', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE38', 'OLE75', 'OLE46', 'OLE75', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE22', 'OLE76', 'OLE47', 'OLE76', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE23', 'OLE77', 'OLE47', 'OLE77', 'c', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE39', 'OLE78', 'OLE47', 'OLE78', 'e', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '13', 'OLE79', 'OLE47', 'OLE79', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '13', 'OLE82', 'OLE51', 'OLE82', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE22', 'OLE85', 'OLE39', 'OLE85', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE23', 'OLE86', 'OLE39', 'OLE86', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE22', 'OLE87', 'OLE40', 'OLE87', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '42', 'OLE92', 'OLE48', 'OLE92', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '13', 'OLESEC1001', 'OLESEC1', 'OLESEC1001', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '6', 'OLESEC1002', 'OLESEC1', 'OLESEC1002', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '6', 'OLESEC1003', 'OLESEC2', 'OLESEC1003', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '4', 'OLESEC1004', 'OLESEC3', 'OLESEC1004', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '6', 'OLESEC1005', 'OLESEC3', 'OLESEC1005', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLESEC102', 'OLESEC1006', 'OLESEC4', 'OLESEC1006', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLESEC100', 'OLESEC1007', 'OLESEC4', 'OLESEC1007', 'b', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLESEC101', 'OLESEC1008', 'OLESEC4', 'OLESEC1008', 'c', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLESEC103', 'OLESEC1009', 'OLESEC4', 'OLESEC1009', 'd', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLE24', 'OLESEC1010', 'OLE47', 'OLESEC1010', 'd', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLEMI6886-ATTRDEF', 'OLEMI6886-TYPEATTR', 'OLEMI6886-TYP', 'OLEMI6886-TYPEATTR', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', '4', 'OLECNTRB199-TYPATTR1', 'OLECNTRB199-TYP1', 'OLECNTRB199-TYPATTR1', 'a', '1')
/

INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND, KIM_ATTR_DEFN_ID, KIM_TYP_ATTR_ID, KIM_TYP_ID, OBJ_ID, SORT_CD, VER_NBR) VALUES ('Y', 'OLECNTRB199-ATTRDEF1', 'OLECNTRB199-TYPATTR2', 'OLECNTRB199-TYP1', 'OLECNTRB199-TYPATTR2', 'b', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_TYP_ATTR_T', '2.0.5', '3:98a04462d82461735dc427a05b184c37', 3)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_ROLE_T::ole::(Checksum: 3:cca764aabab076a25915cf6c48535143)
INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'An optional role that allows users to receive workflow action requests for documents of a specified type that include a specified chart and organization (including the organization hierarchy).,', 'OLE29', 'OLE-SYS', 'OLE7', 'OLE7', 'Organization Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role derives its members from the Account Supervisor field on the Account. Account Supervisors receive workflow action requests for Asset and Asset Retirement Global documents.,', 'OLE39', 'OLE-SYS', 'OLE9', 'OLE9', 'Account Supervisor', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users authorized to work the Cash Management Document and verify Cash Receipt documents for a given campus.', '17', 'OLE-FP', 'OLE11', 'OLE11', 'Cash Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users who receive workflow action requests for Disbursement Vouchers based on the campus code associated with the initiator of the document.', '17', 'OLE-FP', 'OLE12', 'OLE12', 'Disbursement Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users authorized to use the Service Billing document and enter specified accounts on the "Income" side of the document.', 'OLE26', 'OLE-FP', 'OLE13', 'OLE13', 'Service Bill Processor', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users who receive workflow action requests for Disbursement Vouchers for travel payment reasons and can edit the accounting line and Non-Employee Travel Expense or Pre-Paid Travel Expenses tabs.', '1', 'OLE-FP', 'OLE15', 'OLE15', 'Travel Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users who can use the Electronic Fund Transfer screen and use DI or YEDI documents to claim those funds.', '1', 'OLE-SYS', 'OLE16', 'OLE16', 'Treasury Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users authorized to use the Collector Upload screen.', '1', 'OLE-GL', 'OLE17', 'OLE17', 'Interdepartmental Billing Processor', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users associated with PDP customers that can use the Payment File Batch Upload screen and have basic PDP inquiry access. ', 'OLE32', 'OLE-PDP', 'OLE18', 'OLE18', 'Customer Contact', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users who can cancel or hold payments reset locked format processes and view unmasked bank routing and account numbers in PDP.', '1', 'OLE-PDP', 'OLE19', 'OLE19', 'Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users who can set payments for immediate pay and use the Format Checks/ACH screen in PDP.', '17', 'OLE-PDP', 'OLE20', 'OLE20', 'Processor', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Accounts Payable users who can initiate Payment Requests and Credit Memo documents. They also have several permissions related to processing these document types and receive workflow action requests for them. ', '1', 'OLE-PURAP', 'OLE22', 'OLE22', 'Accounts Payable Processor', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users who receive workflow action requests for Purchasing transactional documents that contain a specific commodity code and campus combination.', 'OLE33', 'OLE-PURAP', 'OLE23', 'OLE23', 'Commodity Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users who receive incomplete Requisition documents for completion for a given Chart and Organization.', 'OLE27', 'OLE-PURAP', 'OLE24', 'OLE24', 'Content Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Contract Managers review and approve Purchase Order documents. A Purchase Order is assigned to a given Contract Manager for their review and approval.', 'OLE34', 'OLE-PURAP', 'OLE25', 'OLE25', 'Contract Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role represents central or campus Purchasing staff. They have additional permissions for and receive action requests for most Purchasing document types as well as receiving action requests for Disbursement Vouchers paying PO Type Vendors.', '17', 'OLE-PURAP', 'OLE26', 'OLE26', 'Purchasing Processor', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users authorized to view OLE-PURAP documents identified with a specific Sensitive Data Code. ', 'OLE35', 'OLE-PURAP', 'OLE27', 'OLE27', 'Sensitive Data Viewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'An optional role that allows users to receive workflow action requests for documents of a specified type that contain accounts belonging to a specified chart and organization (including the organization hierarchy) and within a certain dollar amount or involving a specified override code.', 'OLE30', 'OLE-SYS', 'OLE28', 'OLE28', 'Accounting Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users with manager-level access to Accounts Payable documents. This includes the ability to hold or cancel (or remove those states) from Payment Request and Credit Memo documents. ', '1', 'OLE-SYS', 'OLE29', 'OLE29', 'Accounts Payable Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Central Accounts Receivable staff that receive workflow action requests for Cash Control and Lockbox documents. They can also use the Electronic Fund Transfer screen and claim those funds using a Cash Control document.', 'OLE27', 'OLE-SYS', 'OLE30', 'OLE30', 'Accounts Receivable Lockbox Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users that manage the OLE-AR module. This role has no inherent permissions or responsibilities.', '1', 'OLE-SYS', 'OLE31', 'OLE31', 'Accounts Receivable Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'A role that uses the Affiliation Type and Employee Status on a Principal record to determine if a user is an active faculty or staff employee. These users can initiate some OLE-PURAP documents and inquire into certain OLE screens.', 'OLE41', 'OLE-SYS', 'OLE32', 'OLE32', 'Active Faculty or Staff', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'A role that uses the Employee Status (A,L or P) and Employee Type (P) to determine that a given Principal represents a professional staff employee. These users are allowed to be Account Supervisors or Account Managers on Accounts.', 'OLE41', 'OLE-SYS', 'OLE33', 'OLE33', 'Active Professional Employee', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Central Capital Assets staff capable of taking restricted actions on Assets, including retiring or transferring non-moveable assets.', '1', 'OLE-SYS', 'OLE34', 'OLE34', 'Asset Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Central Capital Assets staff capable of applying asset payments, using OLE-CAB and adding negative payments. This role contains permissions to modify restricted asset fields and to override the defined capitalization threshold.', '1', 'OLE-SYS', 'OLE35', 'OLE35', 'Asset Processor', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'An optional role created to receive action requests for Budget Adjustment documents at the Organization Route Node. Intended to receive requests for the top level chart and organization (thus receiving all Budget Adjustment documents).', '1', 'OLE-SYS', 'OLE36', 'OLE36', 'University Administration Budget Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Defines users responsible for managing the chart data for a given Chart of Accounts code. They may initiate Global Object Code and Organization Reversion maintenance documents and modify the Campus and Organization Plant Chart Code and Account on Organization documents.', 'OLE25', 'OLE-SYS', 'OLE37', 'OLE37', 'Chart Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Central contract and grant staff that have special permissions related to Effort Certification. They can override the edit that prevents transferring salary for an open effort reporting period and receive workflow action requests for Effort Certification Recreates.', '1', 'OLE-SYS', 'OLE38', 'OLE38', 'Contracts & Grants Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role defines the list of users that may be selected as Project Directors on the Proposal or Award document.', '1', 'OLE-SYS', 'OLE40', 'OLE40', 'Contracts & Grants Project Director', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role derives its members from the Fiscal Officer field on the Account. Fiscal Officers receive workflow action requests for most transactional documents and have edit permissions that allow them to change accounting lines involving their accounts.', 'OLE39', 'OLE-SYS', 'OLE41', 'OLE41', 'Fiscal Officer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role derives its members from the Primary delegates defined in the Account Delegate table in OLE.', 'OLE39', 'OLE-SYS', 'OLE42', 'OLE42', 'Fiscal Officer Primary Delegate', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role derives its members from the Secondary delegates defined in the Account Delegate table in OLE.', 'OLE39', 'OLE-SYS', 'OLE43', 'OLE43', 'Fiscal Officer Secondary Delegate', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role represents a collection of all the OLE module manager roles and has permission to initiate simple maintenance documents and restricted documents such as the JV and LLJV. These users also have the ability to blanket approve most document types and assign roles and permissions for all OLE namespaces.', '1', 'OLE-SYS', 'OLE44', 'OLE44', 'Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role represents a very select central processing function allowed to run OLE batch jobs, initiate GLCP and LLCP documents and upload Enterprise Feed and Procurement Card files.', '1', 'OLE-SYS', 'OLE45', 'OLE45', 'Operations', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role manages the plant fund functions associated with OLE-CAM and has special permissions related to assets in support of these functions. It can also edit the Organization and Campus Plant Chart and Account fields on the Organization document.', '1', 'OLE-SYS', 'OLE46', 'OLE46', 'Plant Fund Accountant', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users that manage the OLE-PURAP module. This role can take the resend action on Purchase Order documents.', '1', 'OLE-SYS', 'OLE47', 'OLE47', 'Purchasing Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users who receive workflow action requests for documents that include accounts belonging to particular sub-funds groups.', 'OLE31', 'OLE-SYS', 'OLE48', 'OLE48', 'Sub-Fund Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users with a need to view unmasked Tax ID numbers. They can also modify the tax number associated with AR customer records and PURAP vendor records.', '1', 'OLE-SYS', 'OLE49', 'OLE49', 'Tax Identification Number User', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Represents a central tax area that receives workflow action requests for DVs, Payment Requests, and POs involving payments to non-resident aliens or employees. They can also edit the Tax tabs on the DV and Payment Request documents.', '1', 'OLE-SYS', 'OLE50', 'OLE50', 'Tax Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'A technical administrator that is specific to the OLE system. This role has no inherent permissions or responsibilities.', '1', 'OLE-SYS', 'OLE51', 'OLE51', 'Technical Administrator', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role derives its members from the OLE Chart table. It is used to determine the Chart Manager of the top level Chart in the organization hierarchy. This role receives workflow action requests for Chart documents and has the ability to edit the organization and campus Plant Chart and Account fields on the Organization document.', 'OLE40', 'OLE-SYS', 'OLE53', 'OLE53', 'University Chart Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'The basic role that grants users access to OLE. It gives users the ability to initiate most documents and use inquiries and search screens. Users are qualified by namespace, chart and organization. If these fields are not defined the chart and organization are inherited from the Department ID on the users'' Principal record.', 'OLE36', 'OLE-SYS', 'OLE54', 'OLE54', 'User', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users capable of taking superuser action on OLE documents and blanket approving some document types not available to the OLE-SYS Manager role.', '1', 'OLE-SYS', 'OLE55', 'OLE55', 'Workflow Administrator', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role receives workflow action requests for the Vendor document.', 'OLE37', 'OLE-VND', 'OLE56', 'OLE56', 'Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role represents the OLE System User, that is the user ID the system uses when it takes programmed actions (such as auto-initiating or approving documents such as the PCDO and PO).', '1', 'OLE-SYS', 'OLE62', 'OLE62', 'System User', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'An optional role created to receive action requests for Budget Adjustment documents at the Organization route Node. Intended to receive only requests associated with regional campus charts and organizations.', '1', 'OLE-SYS', 'OLE65', 'OLE65', 'Regional Budget Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users who wish to receive workflow action requests for OLE-PURAP documents that involve a specific account number and sub-account number.', 'OLE47', 'OLE-PURAP', 'OLE68', 'OLE68', 'Sub-Account Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Identifies the user who routed the source document (Requisition) for a OLE-PURAP document.', 'OLE48', 'OLE-PURAP', 'OLE69', 'OLE69', 'Source Document Router', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users who receive workflow action requests for Disbursement Vouchers with specified payment methods and can edit the accounting lines and Wire Transfer and Foreign Draft tabs.', 'OLE46', 'OLE-FP', 'OLE70', 'OLE70', 'Disbursement Method Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role is derived from the accounts appearing on an Effort Certification document. OLE finds the most recent award associated with each account and routes workflow action requests to the Project Director''s associated with the accounts on the Effort Certification document.,', 'OLE39', 'OLE-SYS', 'OLE72', 'OLE72', 'Award Project Director', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Central administration users charged with reviewing Purchase Order documents that exceed an account''s sufficient funds balance.', 'OLE25', 'OLE-PURAP', 'OLE79', 'OLE79', 'Budget Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role is derived from users with the Modify Batch Job permission. They are able to use the Schedule lookup.,', 'OLE58', 'OLE-SYS', 'OLE82', 'OLE82', 'Batch Job Modifier', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role houses other roles and indicates which of those can view OLE-PURAP documents that have been identified as potentially sensitive. ', 'OLE62', 'OLE-PURAP', 'OLE84', 'OLE84', 'Potentially Sensitive Document User', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'A role that derives the users who initiated or received a workflow action request for a sensitive OLE-PURAP document.', 'OLE48', 'OLE-PURAP', 'OLE85', 'OLE85', 'Sensitive Related Document Initiator Or Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role derives users who placed a Payment Request or Credit Memo on hold or canceled it in order to determine who can remove those actions. ', 'OLE61', 'OLE-PURAP', 'OLE86', 'OLE86', 'Payment Request Hold / Cancel Initiator', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users authorized to initiate Cash Receipt documents. This role exists to exclude Cash Managers from being able to initiate Cash Receipt documents. You do not need to add explicit members to this role to accomplish this exclusion.', 'OLE65', 'OLE-FP', 'OLE92', 'OLE92', 'Cash Receipt Initiator', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'A role that uses the Employee Status (A,L or P) along with the presence of the OLE-SYS User role to determine that a given Principal represents an employee with OLE access. These users are allowed to be fiscal Officers on Accounts.', 'OLE41', 'OLE-SYS', 'OLE93', 'OLE93', 'Active Employee & Financial System User', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'A role that uses the Employee Status (A,L or P) and Employee Type (P), along with the presence of the OLE-SYS User role to determine that a given Principal represents a professional staff employee with OLE access. These users are allowed to be fiscal Officers on Accounts.', 'OLE41', 'OLE-SYS', 'OLE94', 'OLE94', 'Active Professional Employee & Financial System User', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users that manage the OLE-FP module. This role has no inherent permissions or responsibilities.', '1', 'OLE-FP', 'OLE96', 'OLE96', 'Financial Processing Manager', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Users who receive workflow action requests for Accounts Payable transactional documents.', 'OLE69', 'OLE-PURAP', 'OLE98', 'OLE98', 'Accounts Payable Document Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Year End', '1', 'OLE-SYS', 'OLESYS7145', 'OLESYS1', 'YearEnd', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', '', 'OLEMI4553-1', 'OLE-VND', 'OLEMI4553-2', 'OLEMI4553-2', 'Exclude Single Actor Reviewer', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Administration staff who can view and update Payee ACH Account records and the legacy history of a certain ACH Transaction Type.', 'OLEMI6886-TYP', 'OLE-PDP', 'OLEMI6886-ROLE', 'OLEMI6886-ROLE', 'Payee ACH Accounts Administrator', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Workstudy Reviewer Role', '1', 'OLE-SYS', 'OLECNTRB789-ROLE1', 'OLECNTRB789-ROLE1', 'Workstudy Reviewer', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_ROLE_T', '2.0.5', '3:cca764aabab076a25915cf6c48535143', 4)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_ROLE_MBR_T::ole::(Checksum: 3:cfd1850abfbf94c1cf70e95a96add96a)
INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE45', 'R', 'OLE1308', 'OLE17', 'OLE1308')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE20', 'R', 'OLE1326', 'OLE18', 'OLE1326')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE47', 'R', 'OLE1327', 'OLE18', 'OLE1327')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE47', 'R', 'OLE1328', 'OLE18', 'OLE1328')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE47', 'R', 'OLE1329', 'OLE18', 'OLE1329')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE47', 'R', 'OLE1330', 'OLE18', 'OLE1330')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE47', 'R', 'OLE1331', 'OLE18', 'OLE1331')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE47', 'R', 'OLE1332', 'OLE18', 'OLE1332')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE47', 'R', 'OLE1333', 'OLE18', 'OLE1333')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE47', 'R', 'OLE1334', 'OLE18', 'OLE1334')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE45', 'R', 'OLE1309', 'OLE19', 'OLE1309')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE19', 'R', 'OLE1640', 'OLE20', 'OLE1640')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE29', 'R', 'OLE1310', 'OLE22', 'OLE1310')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE47', 'R', 'OLE1311', 'OLE26', 'OLE1311')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE25', 'R', 'OLE1354', 'OLE26', 'OLE1354')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE36', 'R', 'OLE1339', 'OLE28', 'OLE1339')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE34', 'R', 'OLE1312', 'OLE35', 'OLE1312')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE46', 'R', 'OLE1313', 'OLE35', 'OLE1313')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE45', 'R', 'OLE1314', 'OLE44', 'OLE1314')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE31', 'R', 'OLE1315', 'OLE44', 'OLE1315')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE35', 'R', 'OLE1316', 'OLE44', 'OLE1316')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE38', 'R', 'OLE1317', 'OLE44', 'OLE1317')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE29', 'R', 'OLE1318', 'OLE44', 'OLE1318')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE47', 'R', 'OLE1319', 'OLE44', 'OLE1319')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE36', 'R', 'OLE1325', 'OLE44', 'OLE1325')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE53', 'R', 'OLE1355', 'OLE44', 'OLE1355')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE50', 'R', 'OLE1703', 'OLE44', 'OLE1703')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE96', 'R', 'OLE1704', 'OLE44', 'OLE1704')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE37', 'R', 'OLE1655', 'OLE53', 'OLE1655')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE22', 'R', 'OLE1337', 'OLE56', 'OLE1337')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE51', 'R', 'OLE1324', '63', 'OLE1324')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE22', 'R', 'OLE1348', 'OLE70', 'OLE1348')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE16', 'R', 'OLE1349', 'OLE70', 'OLE1349')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE22', 'R', 'OLE1606', 'OLE84', 'OLE1606')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE26', 'R', 'OLE1607', 'OLE84', 'OLE1607')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE27', 'R', 'OLE1608', 'OLE84', 'OLE1608')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE85', 'R', 'OLE1609', 'OLE84', 'OLE1609')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE54', 'R', 'OLE1610', 'OLE84', 'OLE1610')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('OLE32', 'R', 'OLE1611', 'OLE84', 'OLE1611')
/

INSERT INTO KRIM_ROLE_MBR_T (MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_ID, ROLE_MBR_ID) VALUES ('56', 'R', 'OLEMI4553-3', 'OLEMI4553-2', 'OLEMI4553-3')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_ROLE_MBR_T', '2.0.5', '3:cfd1850abfbf94c1cf70e95a96add96a', 5)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_ROLE_MBR_ATTR_DATA_T::ole::(Checksum: 3:a8b37d5a85ca94bec59ee8da8ca67bbf)
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2792', '10000025', 'OLE33', 'OLE32', 'OLE2792', 'OLE1327', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2793', '10000026', 'OLE33', 'OLE32', 'OLE2793', 'OLE1328', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2794', '10000027', 'OLE33', 'OLE32', 'OLE2794', 'OLE1329', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2795', '10000028', 'OLE33', 'OLE32', 'OLE2795', 'OLE1330', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2796', '10000029', 'OLE33', 'OLE32', 'OLE2796', 'OLE1331', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2797', '10000030', 'OLE33', 'OLE32', 'OLE2797', 'OLE1332', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2798', '10000031', 'OLE33', 'OLE32', 'OLE2798', 'OLE1333', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2799', '10000032', 'OLE33', 'OLE32', 'OLE2799', 'OLE1334', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2887', 'DV', 'OLE36', 'OLE37', 'OLE2887', 'OLE1337', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2927', 'OLE_BA', '13', 'OLE30', 'OLE2927', 'OLE1339', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2928', 'UA', 'OLE22', 'OLE30', 'OLE2928', 'OLE1339', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2929', 'UA', 'OLE24', 'OLE30', 'OLE2929', 'OLE1339', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2962', 'F', 'OLE38', 'OLE46', 'OLE2962', 'OLE1348', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2963', 'W', 'OLE38', 'OLE46', 'OLE2963', 'OLE1349', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2964', 'true', 'OLE45', 'OLE62', 'OLE2964', 'OLE1606', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2965', 'true', 'OLE45', 'OLE62', 'OLE2965', 'OLE1607', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2966', 'true', 'OLE45', 'OLE62', 'OLE2966', 'OLE1608', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2967', 'true', 'OLE45', 'OLE62', 'OLE2967', 'OLE1609', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2968', 'false', 'OLE45', 'OLE62', 'OLE2968', 'OLE1610', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE2969', 'false', 'OLE45', 'OLE62', 'OLE2969', 'OLE1611', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE4040', '*', '12', '17', 'OLE4040', 'OLE1311', '1')
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, ROLE_MBR_ID, VER_NBR) VALUES ('OLE4041', '*', '12', '17', 'OLE4041', 'OLE1354', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_ROLE_MBR_ATTR_DATA_T', '2.0.5', '3:a8b37d5a85ca94bec59ee8da8ca67bbf', 6)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_DLGN_T::ole::(Checksum: 3:27d6b8b4ffe49bc26aedf28131f5ec67)
INSERT INTO KRIM_DLGN_T (ACTV_IND, DLGN_ID, DLGN_TYP_CD, KIM_TYP_ID, OBJ_ID, ROLE_ID, VER_NBR) VALUES ('Y', '1', 'P', 'OLE30', 'OLE1', 'OLE28', '1')
/

INSERT INTO KRIM_DLGN_T (ACTV_IND, DLGN_ID, DLGN_TYP_CD, KIM_TYP_ID, OBJ_ID, ROLE_ID, VER_NBR) VALUES ('Y', '2', 'S', 'OLE30', 'OLE2', 'OLE28', '1')
/

INSERT INTO KRIM_DLGN_T (ACTV_IND, DLGN_ID, DLGN_TYP_CD, KIM_TYP_ID, OBJ_ID, ROLE_ID, VER_NBR) VALUES ('Y', '3', 'P', '1', 'OLE3', 'OLE41', '1')
/

INSERT INTO KRIM_DLGN_T (ACTV_IND, DLGN_ID, DLGN_TYP_CD, KIM_TYP_ID, OBJ_ID, ROLE_ID, VER_NBR) VALUES ('Y', '4', 'S', '1', 'OLE4', 'OLE41', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_DLGN_T', '2.0.5', '3:27d6b8b4ffe49bc26aedf28131f5ec67', 7)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_DLGN_MBR_T::ole::(Checksum: 3:80d44c0b74084996e4a5e227e93418d6)
INSERT INTO KRIM_DLGN_MBR_T (DLGN_ID, DLGN_MBR_ID, MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_MBR_ID) VALUES ('3', 'OLE3', 'OLE42', 'R', 'OLE3', '*')
/

INSERT INTO KRIM_DLGN_MBR_T (DLGN_ID, DLGN_MBR_ID, MBR_ID, MBR_TYP_CD, OBJ_ID, ROLE_MBR_ID) VALUES ('4', 'OLE4', 'OLE43', 'R', 'OLE4', '*')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_DLGN_MBR_T', '2.0.5', '3:80d44c0b74084996e4a5e227e93418d6', 8)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_PERM_TMPL_T::ole::(Checksum: 3:8b2131f4f5e07f9d6eb379cb53d729e1)
INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', '52', 'Modify Accounting Lines', 'OLE-SYS', 'OLE41', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', '3', 'Edit Bank Code', 'OLE-SYS', 'OLE48', 'OLE48', '1')
/

INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', 'OLECNTRB199-TYP1', 'Administer Batch File', 'OLE-SYS', 'OLE50', 'OLE50', '1')
/

INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', 'OLE51', 'Error Correct Document', 'OLE-SYS', 'OLE6', 'OLE6', '1')
/

INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', 'OLE51', 'Claim Electronic Payment', 'OLE-SYS', 'OLE7', 'OLE7', '1')
/

INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', 'OLESEC1', 'View Document with Field Value', 'OLE-SEC', 'OLESEC1001', 'OLESEC1001', '1')
/

INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', 'OLESEC1', 'View Accounting Line with Field Value', 'OLE-SEC', 'OLESEC1002', 'OLESEC1002', '1')
/

INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', 'OLESEC1', 'View Notes/Attachments with Field Value', 'OLE-SEC', 'OLESEC1003', 'OLESEC1003', '1')
/

INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', 'OLESEC1', 'Edit Document with Field Value', 'OLE-SEC', 'OLESEC1004', 'OLESEC1004', '1')
/

INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', 'OLESEC1', 'Edit Accounting Line with Field Value', 'OLE-SEC', 'OLESEC1005', 'OLESEC1005', '1')
/

INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', 'OLESEC2', 'Lookup with Field Value', 'OLE-SEC', 'OLESEC1006', 'OLESEC1006', '1')
/

INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NM, NMSPC_CD, OBJ_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', 'OLESEC3', 'Balance Inquiry with Field Value', 'OLE-SEC', 'OLESEC1007', 'OLESEC1007', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_PERM_TMPL_T', '2.0.5', '3:8b2131f4f5e07f9d6eb379cb53d729e1', 9)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_PERM_T::ole::(Checksum: 3:48547b1bda6c8be17bd3b7026d3f6c21)
INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Global Object Code Document.', 'Initiate Document GOBJ', 'OLE-COA', 'OLE55', 'OLE55', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Organization Reversion Document.', 'Initiate Document ORGR', 'OLE-COA', 'OLE56', 'OLE56', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can edit Accounts that are inactive.', 'Edit Inactive Account', 'OLE-COA', 'OLE58', 'OLE58', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Identifies users that can be Account Managers', 'Serve As Account Manager', 'OLE-COA', 'OLE59', 'OLE59', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit the Sub-Account Type Code on the Sub-Account document.', 'Modify Maintenance Document Field SubAccount a21SubAccount', 'OLE-COA', 'OLE60', 'OLE60', '26', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can edit the Organization Plant Chart Code on the Organization document.', 'Modify Maintenance Document Field Organization organizationPlantChartCode', 'OLE-COA', 'OLE61', 'OLE61', '26', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can edit the Organization Plant Account Number on the Organization document.', 'Modify Maintenance Document Field Organization organizationPlantAccountNumber', 'OLE-COA', 'OLE62', 'OLE62', '26', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can edit the Campus Plant Chart Code on the Organization document.', 'Modify Maintenance Document Field Organization campusPlantChartCode', 'OLE-COA', 'OLE63', 'OLE63', '26', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can edit the Campus Plant Account Number on the Organization document.', 'Modify Maintenance Document Field Organization campusPlantAccountNumber', 'OLE-COA', 'OLE64', 'OLE64', '26', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can use a Distribution of Income and Expense document to claim Electronic Payments.', 'Claim Electronic Payment DI', 'OLE-FP', 'OLE66', 'OLE66', 'OLE7', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can use a Year End Distribution of Income and Expense document to claim Electronic Payments.', 'Claim Electronic Payment YEDI', 'OLE-FP', 'OLE67', 'OLE67', 'OLE7', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Cash Management Document.', 'Initiate Document CMD', 'OLE-FP', 'OLE69', 'OLE69', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Service Billing Document.', 'Initiate Document SB', 'OLE-FP', 'OLE70', 'OLE70', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Journal Voucher Document.', 'Initiate Document JV', 'OLE-FP', 'OLE71', 'OLE71', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the  Procurement Card Document.', 'Initiate Document PCDO', 'OLE-FP', 'OLE72', 'OLE72', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the entire Credit Card number on the Procurement Card document and Inquiry.', 'Full Unmask Field ProcurementCardHolder transactionCreditCardNumber', 'OLE-FP', 'OLE73', 'OLE73', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to access the Procurement Card Upload page.', 'Upload Batch Input File(s) procurementCardInputFileType', 'OLE-FP', 'OLE74', 'OLE74', '33', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the  General Ledger Correction Process Document.', 'Initiate Document GLCP', 'OLE-GL', 'OLE75', 'OLE75', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes user to access the Collector XML Upload page.', 'Upload Batch Input File(s) collectorXmlInputFileType', 'OLE-GL', 'OLE76', 'OLE76', '33', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes user to access the Enterprise Feed Upload page.', 'Upload Batch Input File(s) enterpriseFeederFileSetType', 'OLE-GL', 'OLE77', 'OLE77', '33', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to set and remove immediate status on payments in PDP.', 'Set as Immmediate Pay', 'OLE-PDP', 'OLE80', 'OLE80', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Pre-Disbursement Processor Simple Maintenance Documents.', 'Initiate Document PDSM', 'OLE-PDP', 'OLE81', 'OLE81', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to take the Cancel action on payments in PDP.', 'Cancel Payment', 'OLE-PDP', 'OLE82', 'OLE82', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to access and run the Format Checks /ACH screen in PDP', 'Format', 'OLE-PDP', 'OLE83', 'OLE83', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to hold payments and remove non-tax related holds on payments in PDP.', 'Hold Payment / Remove Non-Tax Payment Hold', 'OLE-PDP', 'OLE84', 'OLE84', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can reset a format process in PDP.', 'Remove Format Lock', 'OLE-PDP', 'OLE86', 'OLE86', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to remove payments held for tax review in PDP.', 'Remove Payment Tax Hold', 'OLE-PDP', 'OLE87', 'OLE87', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to access Pre Disbursement Processor inquiries.', 'Inquire Into Records KFS-PDP', 'OLE-PDP', 'OLE88', 'OLE88', '24', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allow users to access Pre Disbursement Processor lookups.', 'Look Up Records KFS-PDP', 'OLE-PDP', 'OLE89', 'OLE89', '23', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Manually Upload Payment File screen in PDP.', 'Upload Batch Input File(s) paymentInputFileType', 'OLE-PDP', 'OLE90', 'OLE90', '33', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Blanket Approval button on the Payment Request Document.', 'Blanket Approve Document PREQ', 'OLE-PURAP', 'OLE91', 'OLE91', '4', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Requisition Document.', 'Initiate Document REQS', 'OLE-PURAP', 'OLE92', 'OLE92', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Purchasing Transactional Documents.', 'Initiate Document PUR', 'OLE-PURAP', 'OLE93', 'OLE93', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Purchase Order Document.', 'Initiate Document PO', 'OLE-PURAP', 'OLE94', 'OLE94', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Purchase Order Close Document.', 'Initiate Document POC', 'OLE-PURAP', 'OLE95', 'OLE95', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Purchase Order Retransmit Document.', 'Initiate Document PORT', 'OLE-PURAP', 'OLE96', 'OLE96', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Payment Request Document.', 'Initiate Document PREQ', 'OLE-PURAP', 'OLE97', 'OLE97', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Receiving Transactional Documents.', 'Initiate Document RCV', 'OLE-PURAP', 'OLE98', 'OLE98', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Electronic Invoice Reject Document.', 'Initiate Document EIRT', 'OLE-PURAP', 'OLE99', 'OLE99', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission for the Requisition document prior to the document being submitted.', 'Edit Document REQS PreRoute', 'OLE-PURAP', 'OLE100', 'OLE100', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission for the Purchase Order document prior to the document being submitted.', 'Edit Document PO PreRoute', 'OLE-PURAP', 'OLE101', 'OLE101', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission for Accounts Payable Transactional documents prior to the document being submitted.', 'Edit Document AP PreRoute', 'OLE-PURAP', 'OLE102', 'OLE102', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission for Receiving Transactional documents prior to the document being submitted.', 'Edit Document RCV PreRoute', 'OLE-PURAP', 'OLE103', 'OLE103', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Electronic Fund Transfer interface for the claiming of electronic funds.', 'Claim Electronic Payment', 'OLE-SYS', 'OLE107', 'OLE107', 'OLE7', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Error Correction button on OLE Transactional documents.', 'Error Correct Document KFST', 'OLE-SYS', 'OLE108', 'OLE108', 'OLE6', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to open OLE documents via the Super search option in Document Search and take Administrative workflow actions on them (such as approving the document, approving individual requests, or sending the document to a specified route node).', 'Administer Routing for Document KFS', 'OLE-SYS', 'OLE113', 'OLE113', '3', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Blanket Approval button on OLE Financial System Documents.', 'Blanket Approve Document KFS', 'OLE-SYS', 'OLE114', 'OLE114', '4', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Financial System Documents.', 'Initiate Document KFS', 'OLE-SYS', 'OLE115', 'OLE115', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Financial System Simple Maintenance documents.', 'Initiate Document FSSM', 'OLE-SYS', 'OLE116', 'OLE116', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Bank Maintenance Document.', 'Initiate Document BANK', 'OLE-SYS', 'OLE117', 'OLE117', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to modify the information on the Assignees Tab of the Role Document and the Roles section of the Membership Tab on the Person Document for roles with a Module Code beginning with OLE.', 'Assign Role KFS*', 'OLE-SYS', 'OLE119', 'OLE119', '35', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to modify the information on the Permissions tab of the Role Document for roles with a module code beginning with OLE.', 'Grant Permission KFS*', 'OLE-SYS', 'OLE120', 'OLE120', '36', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to modify the information on the Responsibility tab of the Role Document for roles with a Module Code that begins with OLE.', 'Grant Responsibility KFS*', 'OLE-SYS', 'OLE121', 'OLE121', '37', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to modify the information on the Assignees Tab of the Group Document and the Group section of the Membership Tab on the Person Document for groups with namespaces beginning with OLE.', 'Populate Group KFS*', 'OLE-SYS', 'OLE122', 'OLE122', '38', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the entire bank account number on the Bank document and Inquiry.', 'Full Unmask Field Bank bankAccountNumber', 'OLE-SYS', 'OLE128', 'OLE128', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to access OLE inquiries.', 'Inquire Into Records KFS*', 'OLE-SYS', 'OLE129', 'OLE129', '24', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allow users to access OLE lookups.', 'Look Up Records KFS*', 'OLE-SYS', 'OLE130', 'OLE130', '23', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to initiate and edit the Parameter document for pameters with a module code beginning with OLE.', 'Maintain System Parameter KFS*', 'OLE-SYS', 'OLE131', 'OLE131', '34', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to access and run Batch Jobs associated with OLE modules via the Schedule link.', 'Modify Batch Job KFS*', 'OLE-SYS', 'OLE132', 'OLE132', '32', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open OLE Financial System Documents.', 'Open Document KFS', 'OLE-SYS', 'OLE133', 'OLE133', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the last four-digits of the bank account number on the Bank document and Inquiry.', 'Partial Unmask Field Bank bankAccountNumber', 'OLE-SYS', 'OLE134', 'OLE134', '28', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users access to screens in the OLE that are not documents, lookups, inquiries, or batch uploads.', 'Use Screen KFS*', 'OLE-SYS', 'OLE135', 'OLE135', '29', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to initiate the Vendor Maintenance Document.', 'Initiate Document PVEN', 'OLE-VND', 'OLE136', 'OLE136', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the entire Tax Number on the Vendor Maintenance Document and Inquiry.', 'Full Unmask Field VendorDetail vendorHeader.vendorTaxNumber', 'OLE-VND', 'OLE137', 'OLE137', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to modify the Tax Number on a Vendor Maintenance Document.', 'Modify Maintenance Document Field VendorDetail vendorHeader.vendorTaxNumber', 'OLE-VND', 'OLE138', 'OLE138', '26', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to see and edit the Contracts Tab on the Vendor Maintenance Document.', 'Modify Maintenance Document Section VendorDetail vendorContracts', 'OLE-VND', 'OLE139', 'OLE139', '44', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes user to save documents answering to the FinancialSystemDocument parent document Type.', 'Save Document KFS', 'OLE-SYS', 'OLE169', 'OLE169', '15', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Vendor Commodity Codes tab on the Vendor document. ', 'Modify Maintenance Document Section VendorDetail vendorCommodities', 'OLE-VND', 'OLE184', 'OLE184', '44', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users who can edit the Cash Management Document prior to it being submitted for routing.', 'Edit Document CMD PreRoute', 'OLE-FP', 'OLE185', 'OLE185', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to view the tax id in the Disbursement Voucher.', 'Full Unmask Field DisbursementPayee taxNumber', 'OLE-FP', 'OLE187', 'OLE187', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Target accounting lines on a Procurement Card Document that is at the Account Full Entry Node of routing.', 'Modify Accounting Lines SB PreRoute sourceAccountingLines', 'OLE-FP', 'OLE189', 'OLE189', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on documents answering to the parent document Financial Processing Transactional Document when a document is at the Account Node of routing.', 'Modify Accounting Lines FP Account sourceAccountingLines', 'OLE-FP', 'OLE190', 'OLE190', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Target accounting lines on documents answering to the parent document Financial Processing Transactional Document when a document is at the Account Node of routing.', 'Modify Accounting Lines FP Account targetAccountingLines', 'OLE-FP', 'OLE191', 'OLE191', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the object code of Source accounting lines on a Disbursement Voucher document that is at the Campus Node of routing.', 'Modify Accounting Lines DV Campus sourceAccountingLines.financialObjectCode', 'OLE-FP', 'OLE192', 'OLE192', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the amount of Source accounting lines on a Disbursement Voucher document that is at the Tax Node of routing.', 'Modify Accounting Lines DV Tax sourceAccountingLines.amount', 'OLE-FP', 'OLE193', 'OLE193', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the amount of Source accounting lines on a Disbursement Voucher document that is at the Travel Node of routing.', 'Modify Accounting Lines DV Travel sourceAccountingLines.amount', 'OLE-FP', 'OLE194', 'OLE194', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the amount of Source accounting lines on a Disbursement Voucher document that is at the Payment Method Node of routing.', 'Modify Accounting Lines DV PaymentMethod sourceAccountingLines.amount', 'OLE-FP', 'OLE195', 'OLE195', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on documents answering to the parent document Purchasing Transactional Document when a document is at the Account Node of routing.', 'Modify Accounting Lines PUR Account items.sourceAccountingLines', 'OLE-PURAP', 'OLE198', 'OLE198', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on documents answering to the parent document Accounts PayableTransactional Document when a document is at the Account Node of routing.', 'Modify Accounting Lines AP Account items.sourceAccountingLines', 'OLE-PURAP', 'OLE199', 'OLE199', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can complete the Nonresident Alien Tax Tab on the Disbursement Voucher document.', 'Use Transactional Document DV taxEntry', 'OLE-FP', 'OLE209', 'OLE209', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can modify the Foreign Draft tab and disbursement amount on Disbursement Voucher documents.', 'Use Transactional Document DV frnEntry', 'OLE-FP', 'OLE210', 'OLE210', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can modify Wire Transfer tab and disbursement amount on Disbursement Voucher documents.', 'Use Transactional Document DV wireEntry', 'OLE-FP', 'OLE211', 'OLE211', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can modify Non-Employee Travel Expense tab, disbursement amount on Disbursement Voucher documents.', 'Use Transactional Document DV travelEntry', 'OLE-FP', 'OLE212', 'OLE212', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can edit specific data on a Payment Request or Credit Memo before the document is extracted to PDP.', 'Use Transactional Document AP editPreExtract', 'OLE-PURAP', 'OLE217', 'OLE217', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can edit the Bank Code field on documents answering to the parent document Financial System Transactional Document.', 'Edit Bank Code OpenLibraryEnvironmentTransactionalDocument', 'OLE-SYS', 'OLE220', 'OLE220', 'OLE48', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the last four-digits of the bank account number on the Payee ACH document and Inquiry.', 'Partial Unmask Field PayeeACHAccount bankAccountNumber', 'OLE-PDP', 'OLE233', 'OLE233', '28', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the entire bank routing number on the ACH Bank document and Inquiry.', 'Full Unmask Field ACHBank bankRoutingNumber', 'OLE-PDP', 'OLE234', 'OLE234', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the entire bank account number on the Payee ACH document and Inquiry.', 'Full Unmask Field PayeeACHAccount bankAccountNumber', 'OLE-PDP', 'OLE235', 'OLE235', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the entire bank routing number on the Payee ACH document and Inquiry.', 'Full Unmask Field PayeeACHAccount bankRoutingNumber', 'OLE-PDP', 'OLE236', 'OLE236', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on documents answering to the parent document Accounts Financial Processing Transactional Document when a document has not yet been submitted for routing.', 'Modify Accounting Lines FP PreRoute sourceAccountingLines', 'OLE-FP', 'OLE238', 'OLE238', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Target accounting lines on documents answering to the parent document Financial Processing Transactional Document when a document has not yet been submitted for routing.', 'Modify Accounting Lines FP PreRoute targetAccountingLines', 'OLE-FP', 'OLE239', 'OLE239', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on documents answering to the parent document Purchasing Transactional Document when a document has not yet been submitted for routing.', 'Modify Accounting Lines PUR PreRoute items.sourceAccountingLines', 'OLE-PURAP', 'OLE241', 'OLE241', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on documents answering to the parent document Accounts PayableTransactional Document when a document has not yet been submitted for routing.', 'Modify Accounting Lines AP PreRoute items.sourceAccountingLines', 'OLE-PURAP', 'OLE242', 'OLE242', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allow users to access the Batch File lookup.', 'Look Up Records BatchJobStatus', 'OLE-SYS', 'OLE256', 'OLE256', '23', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to take the Shop Catalogs (or B2B) action.', 'Use Screen org.kuali.ole.module.purap.web.struts.B2BAction', 'OLE-PURAP', 'OLE257', 'OLE257', '29', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can add notes and attachments to the Payment Request document when it is at the Invoice Attachment route node.', 'Add Note / Attachment PREQ Invoice Image', 'OLE-PURAP', 'OLE258', 'OLE258', '45', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view attachments with a type of "Invoice Image" on Payment Request documents.', 'View Note / Attachment PREQ Invoice Image', 'OLE-PURAP', 'OLE260', 'OLE260', '46', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to delete notes and attachments created by any user on documents answering to the Financial System Document parent document type.', 'Delete Note / Attachment OLE FALSE', 'OLE-SYS', 'OLE263', 'OLE263', '47', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users who can edit Payment Request Documents that are in ENROUTE status.', 'Edit Document PREQ R', 'OLE-PURAP', 'OLE268', 'OLE268', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open Purchasing Accounts Payable Transactional documents.', 'Open Document PRAP', 'OLE-PURAP', 'OLE269', 'OLE269', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to take the Print action on a Purchase Order.', 'Use Screen org.kuali.kfs.module.purap.document.web.struts.PrintAction', 'OLE-PURAP', 'OLE270', 'OLE270', '29', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to access the Electronic Funds Transfer screen.', 'Use Screen org.kuali.kfs.sys.web.struts.ElectronicFundTransferAction', 'OLE-SYS', 'OLE271', 'OLE271', '29', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can print a Purchase Order document.', 'Use Transactional Document PO printPurchaseOrder', 'OLE-PURAP', 'OLE272', 'OLE272', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can preview a Purchase Order document before printing it.', 'Use Transactional Document PO previewPrintPurchaseOrder', 'OLE-PURAP', 'OLE273', 'OLE273', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can assign sensitive data to a Purchase Order document which locks down who is allowed to view the PO and its related documents.', 'Use Transactional Document PO assignSensitiveData', 'OLE-PURAP', 'OLE274', 'OLE274', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can resend Purchase Order cxml to the B2B integrator.', 'Use Transactional Document PO resendPurchaseOrder', 'OLE-PURAP', 'OLE275', 'OLE275', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users authorized to take the Request Cancel action on Payment Request documents.', 'Use Transactional Document PREQ requestPaymentRequestCancel', 'OLE-PURAP', 'OLE276', 'OLE276', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to remove Holds or Cancels on Payment Request documents.', 'Use Transactional Document PREQ paymentRequestHoldCancelRemoval', 'OLE-PURAP', 'OLE277', 'OLE277', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on a Procurement Card Document that is at the Account Full Entry Node of routing.', 'Modify Accounting Lines PCDO AccountFullEdit sourceAccountingLines', 'OLE-FP', 'OLE280', 'OLE280', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Target accounting lines on a Procurement Card Document that is at the Account Full Entry Node of routing.', 'Modify Accounting Lines PCDO AccountFullEdit targetAccountingLines', 'OLE-FP', 'OLE281', 'OLE281', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can cancel Payment Request or Credit Memo documents at a manager level.', 'Use Transactional Document AP managerCancel', 'OLE-PURAP', 'OLE282', 'OLE282', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can cancel Payment Request or Credit Memo documents at a processor level.', 'Use Transactional Document AP processorCancel', 'OLE-PURAP', 'OLE283', 'OLE283', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open the Contract Manager Assignment Document.', 'Open Document ACM', 'OLE-PURAP', 'OLE284', 'OLE284', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to access all Pre-Disbursement Processor screens.', 'Use Screen KFS-PDP', 'OLE-PDP', 'OLE285', 'OLE285', '29', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to put Payment Request documents on Hold.', 'Use Transactional Document PREQ requestPaymentRequestHold', 'OLE-PURAP', 'OLE286', 'OLE286', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to access Balance Inquiry screens. ', 'Use Screen org.kuali.kfs.gl.web.struts.BalanceInquiryAction', 'OLE-GL', 'OLE287', 'OLE287', '29', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open the Electronic Invoice Reject Document.', 'Open Document EIRT', 'OLE-PURAP', 'OLE291', 'OLE291', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to upload eInvoice files.', 'Upload Batch Input File(s) electronicInvoiceInputFileType', 'OLE-PURAP', 'OLE292', 'OLE292', '33', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to put Credit Memo documents on Hold.', 'Use Transactional Document CM requestVendorCreditMemoHold', 'OLE-PURAP', 'OLE293', 'OLE293', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to remove a Hold from Credit Memo documents.', 'Use Transactional Document CM vendorCreditMemoHoldRemoval', 'OLE-PURAP', 'OLE294', 'OLE294', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to see menu of balance inquiries after hitting "balance inquiry" button on an accounting line in an accounting document.', 'Use Screen org.kuali.kfs.sys.web.struts.KualiBalanceInquiryReportMenuAction', 'OLE-GL', 'OLE301', 'OLE301', '29', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on a Requisition Document that is at the Initiator Node of routing.', 'Modify Accounting Lines REQS Initiator items.sourceAccountingLines', 'OLE-PURAP', 'OLE308', 'OLE308', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to add notes and attachments with a type of "Contracts" to the Purchase Order document. ', 'Add Note / Attachment PO Contracts', 'OLE-PURAP', 'OLE313', 'OLE313', '45', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view attachments with a type of "Contract" on Purchase Order documents and documents answering to that document type.', 'View Note / Attachment PO Contracts', 'OLE-PURAP', 'OLE314', 'OLE314', '46', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to add notes and attachments with a type of "Contract" on Purchase Order documents and documents answering to that document type.', 'Add Note / Attachment PO Contract Ammendments', 'OLE-PURAP', 'OLE315', 'OLE315', '45', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view attachments with a type of "Contract Amendments" on Purchase Order documents and documents answering to that document type.', 'View Note / Attachment PO Contract Ammendments', 'OLE-PURAP', 'OLE316', 'OLE316', '46', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to add notes and attachments with a type of "Quotes" on Purchase Order documents and documents answering to that document type.', 'Add Note / Attachment PO Quotes', 'OLE-PURAP', 'OLE317', 'OLE317', '45', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view attachments with a type of "Quotes" on Purchase Order documents and documents answering to that document type.', 'View Note / Attachment PO Quotes', 'OLE-PURAP', 'OLE318', 'OLE318', '46', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to add notes and attachments with a type of "RFPs" on Purchase Order documents and documents answering to that document type.', 'Add Note / Attachment PO RFPs', 'OLE-PURAP', 'OLE319', 'OLE319', '45', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view attachments with a type of "RFPs" on Purchase Order documents and documents answering to that document type.', 'View Note / Attachment PO RFPs', 'OLE-PURAP', 'OLE320', 'OLE320', '46', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to add notes and attachments with a type of "RFP" on Purchase Order documents and documents answering to that document type.', 'Add Note / Attachment PO RFP Responses', 'OLE-PURAP', 'OLE321', 'OLE321', '45', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view attachments with a type of "RFP" on Purchase Order documents and documents answering to that document type.', 'View Note / Attachment PO RFP Responses', 'OLE-PURAP', 'OLE322', 'OLE322', '46', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to add notes or attachments with a type of "Other-Restricted" on Purchase Order documents and documents answering to that document type.', 'Add Note / Attachment PO Other - Restricted', 'OLE-PURAP', 'OLE323', 'OLE323', '45', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view attachments with a type of "Other-Restricted" on Purchase Order documents and documents answering to that document type.', 'View Note / Attachment PO Other - Restricted', 'OLE-PURAP', 'OLE324', 'OLE324', '46', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to add notes or attachments with a type of "Credit Memo Image" on Credit Memo documents.', 'Add Note / Attachment CM Credit Memo Image', 'OLE-PURAP', 'OLE325', 'OLE325', '45', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view attachments with a type of "Credit Memo Image" on Credit Memo documents.', 'View Note / Attachment CM Credit Memo Image', 'OLE-PURAP', 'OLE326', 'OLE326', '46', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on a Requisition Document that is at the Organization Node of routing.', 'Modify Accounting Lines REQS Organization items.sourceAccountingLines', 'OLE-PURAP', 'OLE328', 'OLE328', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Cash Management Document screen that a user sees when they try to initiate and a document is already open for their campus. The screen provides them a link to the existing document for their campus.', 'Use Screen org.kuali.kfs.fp.document.web.struts.CashManagementStatusAction', 'OLE-FP', 'OLE329', 'OLE329', '29', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Cash Management Document screen from which a user can create new deposits.', 'Use Screen org.kuali.kfs.fp.document.web.struts.DepositWizardAction', 'OLE-FP', 'OLE330', 'OLE330', '29', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Cash Management Document screen from which a user can correct amounts on a closed cash drawer.', 'Use Screen org.kuali.kfs.fp.web.struts.CashDrawerCorrectionAction', 'OLE-FP', 'OLE331', 'OLE331', '29', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to modify the Tax Type Code on a Vendor Maintenance Document.', 'Modify Maintenance Document Field VendorDetail vendorHeader.vendorTaxTypeCode', 'OLE-VND', 'OLE335', 'OLE335', '26', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Cash Receipt Document.', 'Initiate Document CR', 'OLE-FP', 'OLE350', 'OLE350', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Identifies users that can be Account Supervisors.', 'Serve As Account Supervisor', 'OLE-COA', 'OLE351', 'OLE351', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Identifies users that can be Account Fiscal Officers.', 'Serve As Fiscal Officer', 'OLE-COA', 'OLE352', 'OLE352', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Identifies users that can be Account Fiscal Officer Delegates.', 'Serve As Fiscal Officer Delegate', 'OLE-COA', 'OLE353', 'OLE353', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '???', 'Use Screen org.kuali.kfs.fp.document.web.struts.DisbursementVoucherHelpAction', 'OLE-FP', 'OLE354', 'OLE354', '29', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on a Purchase Order Amendment Document that is at the New Unordered Items Node of routing.', 'Modify Accounting Lines POA NewUnorderedItems items.sourceAccountingLines', 'OLE-PURAP', 'OLE355', 'OLE355', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Credit Memo Document.', 'Initiate Document CM', 'OLE-PURAP', 'OLE356', 'OLE356', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the object code of Source accounting lines on a Disbursement Voucher Document that is at the Travel Node of routing.', 'Modify Accounting Lines DV Travel sourceAccountingLines.financialObjectCode', 'OLE-FP', 'OLE358', 'OLE358', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Cash Drawer Document.', 'Initiate Document CDS', 'OLE-FP', 'OLE361', 'OLE361', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view batch files using the Batch File lookup screen.', 'View Batch File(s) KFS*', 'OLE-SYS', 'OLE362', 'OLE362', 'OLE50', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allow users to access Pre Disbursement Processor lookups.', 'Look Up Records KFS-PDP PurchasingPaymentDetail', 'OLE-PDP', 'OLE363', 'OLE363', '23', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the entire Tax Number on the Vendor Maintenance Document and Inquiry.', 'Full Unmask Field VendorDetail vendorHeader.vendorTaxTypeCode', 'OLE-VND', 'OLE364', 'OLE364', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users who can edit Payment Request Documents that are in ENROUTE status.', 'Edit Document PREQ P', 'OLE-PURAP', 'OLE365', 'OLE365', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users who can edit Payment Request Documents that are in ENROUTE status.', 'Edit Document PREQ F', 'OLE-PURAP', 'OLE366', 'OLE366', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users who can edit Credit Memo Documents that are in PROCESSED status.', 'Edit Document CM P', 'OLE-PURAP', 'OLE367', 'OLE367', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users who can edit Credit Memo Documents that are in FINAL status.', 'Edit Document CM F', 'OLE-PURAP', 'OLE368', 'OLE368', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can edit the Bank Code field on the Cash Management Document.', 'Edit Bank Code CMD', 'OLE-FP', 'OLE372', 'OLE372', 'OLE48', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can edit the Bank Code field on the Payment Request Document.', 'Edit Bank Code PREQ', 'OLE-PURAP', 'OLE373', 'OLE373', 'OLE48', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can edit the Bank Code field on the Disbursement Voucher Document.', 'Edit Bank Code DV', 'OLE-FP', 'OLE374', 'OLE374', 'OLE48', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the KIM Group document.', 'Initiate Document IdentityManagementGroupDocument', 'OLE-SYS', 'OLE379', 'OLE379', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of the Organization Reversion Global Document.', 'Initiate Document GORV', 'OLE-COA', 'OLE380', 'OLE380', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the entire bank account number on the Wire Transfer tab of the Disbursement Voucher document.', 'Full Unmask Field DisbursementVoucherWireTransfer disbVchrPayeeAccountNumber', 'OLE-FP', 'OLE381', 'OLE381', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit Simple Maintenance document at the AdHoc route node.', 'Edit Document FSSM AdHoc R', 'OLE-SYS', 'OLE382', 'OLE382', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit Complex Maintenance document at the AdHoc route node.', 'Edit Document KFSM AdHoc R', 'OLE-SYS', 'OLE383', 'OLE383', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes user to access the Collector Flat File Upload page.', 'Upload Batch Input File(s) collectorFlatFileInputFileType', 'OLE-GL', 'OLE385', 'OLE385', '33', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the entire bank routing number on the Payment Detail Inquiry.', 'Full Unmask Field PaymentGroup achBankRoutingNbr', 'OLE-PDP', 'OLE386', 'OLE386', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the entire bank account number on the Payment Detail Inquiry.', 'Full Unmask Field AchAccountNumber achBankAccountNbr', 'OLE-PDP', 'OLE387', 'OLE387', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to take the Approve action on OLE documents Ad Hoc routed to them.', 'Ad Hoc Review Document KFS A', 'OLE-SYS', 'OLE390', 'OLE390', '9', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to take the FYI action on OLE documents Ad Hoc routed to them.', 'Ad Hoc Review Document KFS F', 'OLE-SYS', 'OLE391', 'OLE391', '9', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to take the Acknowledge action on OLE documents Ad Hoc routed to them.', 'Ad Hoc Review Document KFS K', 'OLE-SYS', 'OLE392', 'OLE392', '9', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to open Financial System Documents via the Super search option in Document Search and take Administrative workflow actions on them (such as approving the document, approving individual requests, or sending the document to a specified route node).', 'Administer Routing for Document KFST', 'OLE-SYS', 'OLE1100', 'OLE1100', '3', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Copy button on OLE Financial System Documents.', 'Copy Document KFS', 'OLE-SYS', 'OLE1101', 'OLE1101', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows user to enter a message on the route log', 'Add Message to Route Log KFS', 'OLE-SYS', 'OLE1103', 'OLE1103', '51', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to access the Marc File Upload page.', 'Upload Batch Input File(s) ', 'OLE-PURAP', 'OLE1500', 'OLE1500', '33', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to inactivate the present vendor.', 'Inactivate Vendor', 'OLE-VND', 'OLE1501', 'OLE1501', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Access Security Simple Maintenance Documents.', 'Initiate Document AccessSecuritySimpleMaintenanceDocument', 'OLE-SEC', 'OLESEC6001', 'OLESEC6001', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the lookup of Access Security records.', 'Look Up Records KFS-SEC', 'OLE-SEC', 'OLESEC6002', 'OLESEC6002', '23', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes inquiry of Access Security records.', 'Inquire Into Records KFS-SEC', 'OLE-SEC', 'OLESEC6003', 'OLESEC6003', '24', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes edit of Access Security Simple Maintenance Documents.', 'Edit Document AccessSecuritySimpleMaintenanceDocument PreRoute', 'OLE-SEC', 'OLESEC6004', 'OLESEC6004', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the entire Purchase Order Number on the PO Document and Inquiry', 'Unmask PO # On PO Document', 'OLE-PURAP', 'OLEPURAP6940', 'OLEPURAP6940', '27', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can view the accounting period field when enabled for year end processing.', 'View Accounting Period', 'OLE-SYS', 'OLESYS7145a', 'OLESYS7145a', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can edit the accounting period field when enabled for year end processing.', 'Edit Accounting Period', 'OLE-SYS', 'OLESYS7145b', 'OLESYS7145b', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allow Disbursement Voucher to be marked for immediate disbursement by the Pre-Disbursement Processor.', 'Allow DV Immediate Disbursement', 'OLE-FP', 'OLESYS6007', 'OLESYS6007', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to view and update Payee ACH Accounts of a certain transaction type.', 'Maintain ACH Accounts', 'OLE-PDP', 'OLEMI6886-PRM1', 'OLEMI6886-PRM1', '42', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows the lookup of Payee ACH Account records of certain transaction type(s).', 'Lookup ACH Accounts', 'OLE-PDP', 'OLEMI6886-PRM2', 'OLEMI6886-PRM2', '23', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows initiation the PDP Payee ACH Account maintenance document.', 'Initiate ACH Account Document', 'OLE-PDP', 'OLEMI6886-PRM3', 'OLEMI6886-PRM3', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to access the Vendor Exclude File Upload page.', 'Use Vendor Exclude File Upload Screen', 'OLE-VND', 'OLECNTRB162-PRM', 'OLECNTRB162-PRM', '33', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to access the Batch Semaphore File Upload page.', 'Upload semaphoreInputFileTypeError', 'OLE-SYS', 'OLECNTRB199-P1', 'OLECNTRB199-P1', '33', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to view the batch files using the Batch File lookup screen', 'Administer Batch File OLE-SYS batchContainer', 'OLE-SYS', 'OLECNTRB199-P2', 'OLECNTRB199-P2', 'OLE50', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit the IndirectCostRecoveryAccounts section on the Sub-Account document.', 'Modify Maintenance Document Section SubAccount indirectCostRecoveryAccounts', 'OLE-COA', 'OLEMI8342-PRM', 'OLEMI8342-PRM', '44', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on documents answering to the parent document Purchasing Transactional Document when a document is at the Tax Node of routing.', 'Modify Accounting Lines OLE_PUR Tax itemsourceAccountingLines', 'OLE-PURAP', 'OLEMI8944-PRM', 'OLEMI8944-PRM', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Enable Recall Document functionality for OLEFinancialProcessingTransactionalDocument documents.', 'Recall Document OLEFinancialProcessingTransactionalDocument', 'OLE-FP', 'OLEMI8893-PRM1', 'OLEMI8893-PRM1', '68', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Recall Document functionality for OLE documents.', 'Recall Document OLE', 'OLE-SYS', 'OLEMI8893-PRM2', 'OLEMI8893-PRM2', '68', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', ' "Authorizes users to send Complete ad hoc requests for OLE Documents', 'Send Complete Request OLE', 'OLE-SYS', 'OLEMI9071-PRM1', 'OLEMI9071-PRM1', '49', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allow access to modules, even when locked.', 'Access Locked Module', 'KR-SYS', 'OLECNTRB65-PRM1', 'OLECNTRB65-PRM1', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to super user approve single action requests on the super user tab for OLE Documents.', 'Super User Approve Single Action Request OLE', 'OLE-SYS', 'OLEMI9598-PRM1', 'OLEMI9598-PRM1', 'KR1000', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to super user approve documents on the super user tab for OLE Documents.', 'Super User Approve Document OLE', 'OLE-SYS', 'OLEMI9598-PRM2', 'OLEMI9598-PRM2', 'KR1001', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to super user disapprove documents on the super user tab for OLE Documents.', 'Super User Disapprove Document OLE', 'OLE-SYS', 'OLEMI9598-PRM3', 'OLEMI9598-PRM3', 'KR1002', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_PERM_T', '2.0.5', '3:48547b1bda6c8be17bd3b7026d3f6c21', 10)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_PERM_ATTR_DATA_T::ole::(Checksum: 3:a8733d6a79caebf11538afc87a6dab27)
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE123', 'OLE_REQS', '13', '8', 'OLE123', 'OLE100', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE245', 'PreRoute', '16', '8', 'OLE245', 'OLE100', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE124', 'OLE_PO', '13', '8', 'OLE124', 'OLE101', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE246', 'PreRoute', '16', '8', 'OLE246', 'OLE101', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE125', 'OLE_AP', '13', '8', 'OLE125', 'OLE102', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE247', 'PreRoute', '16', '8', 'OLE247', 'OLE102', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE126', 'OLE_RCV', '13', '8', 'OLE126', 'OLE103', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE248', 'PreRoute', '16', '8', 'OLE248', 'OLE103', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE132', 'OpenLibraryEnvironmentTransactionalDocument', '13', 'OLE51', 'OLE132', 'OLE108', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10000', 'OpenLibraryEnvironmentTransactionalDocument', '13', '3', 'OLE10000', 'OLE1100', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE141', 'OLE', '13', '3', 'OLE141', 'OLE113', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE142', 'OLE', '13', '3', 'OLE142', 'OLE114', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE143', 'OLE', '13', '3', 'OLE143', 'OLE115', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE144', 'OpenLibraryEnvironmentSimpleMaintenanceDocument', '13', '3', 'OLE144', 'OLE116', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE145', 'OLE_BANK', '13', '3', 'OLE145', 'OLE117', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE149', 'OLE*', '4', '18', 'OLE149', 'OLE119', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE150', 'OLE*', '4', '19', 'OLE150', 'OLE120', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE151', 'OLE*', '4', '20', 'OLE151', 'OLE121', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE152', 'OLE*', '4', '21', 'OLE152', 'OLE122', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE162', 'Bank', '5', '11', 'OLE162', 'OLE128', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE163', 'bankAccountNumber', '6', '11', 'OLE163', 'OLE128', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE164', 'OLE*', '4', '10', 'OLE164', 'OLE129', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE165', 'OLE*', '4', '10', 'OLE165', 'OLE130', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE166', 'OLE*', '4', '16', 'OLE166', 'OLE131', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE167', 'OLE*', '4', '15', 'OLE167', 'OLE132', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE168', 'OLE', '13', '3', 'OLE168', 'OLE133', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE169', 'Bank', '5', '11', 'OLE169', 'OLE134', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE170', 'bankAccountNumber', '6', '11', 'OLE170', 'OLE134', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE171', 'OLE*', '4', '12', 'OLE171', 'OLE135', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE172', 'OLE_PVEN', '13', '3', 'OLE172', 'OLE136', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE174', 'VendorDetail', '5', '11', 'OLE174', 'OLE137', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE175', 'vendorHeader.vendorTaxNumber', '6', '11', 'OLE175', 'OLE137', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE176', 'VendorDetail', '5', '11', 'OLE176', 'OLE138', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE177', 'vendorHeader.vendorTaxNumber', '6', '11', 'OLE177', 'OLE138', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE178', 'VendorDetail', '5', '57', 'OLE178', 'OLE139', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE179', 'vendorContracts', '44', '57', 'OLE179', 'OLE139', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE222', 'OLE', '13', '8', 'OLE222', 'OLE169', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE250', 'VendorDetail', '5', '57', 'OLE250', 'OLE184', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE251', 'vendorCommodities', '44', '57', 'OLE251', 'OLE184', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE252', 'OLE_CMD', '13', '8', 'OLE252', 'OLE185', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE253', 'PreRoute', '16', '8', 'OLE253', 'OLE185', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE257', 'DisbursementPayee', '5', '11', 'OLE257', 'OLE187', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE258', 'taxNumber', '6', '11', 'OLE258', 'OLE187', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE265', 'OLE_SB', '13', '52', 'OLE265', 'OLE189', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE266', 'PreRoute', '16', '52', 'OLE266', 'OLE189', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE267', 'sourceAccountingLines', '6', '52', 'OLE267', 'OLE189', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE268', 'OLEFinancialProcessingSimpleMaintenanceDocument', '13', '52', 'OLE268', 'OLE190', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE269', 'Account', '16', '52', 'OLE269', 'OLE190', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE270', 'sourceAccountingLines', '6', '52', 'OLE270', 'OLE190', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE271', 'OLEFinancialProcessingSimpleMaintenanceDocument', '13', '52', 'OLE271', 'OLE191', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE272', 'Account', '16', '52', 'OLE272', 'OLE191', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE273', 'targetAccountingLines', '6', '52', 'OLE273', 'OLE191', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE274', 'OLE_DV', '13', '52', 'OLE274', 'OLE192', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE275', 'Campus', '16', '52', 'OLE275', 'OLE192', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE276', 'sourceAccountingLines.financialObjectCode', '6', '52', 'OLE276', 'OLE192', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE277', 'OLE_DV', '13', '52', 'OLE277', 'OLE193', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE278', 'Tax', '16', '52', 'OLE278', 'OLE193', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE279', 'sourceAccountingLines.amount', '6', '52', 'OLE279', 'OLE193', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE280', 'OLE_DV', '13', '52', 'OLE280', 'OLE194', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE281', 'Travel', '16', '52', 'OLE281', 'OLE194', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE282', 'sourceAccountingLines.amount', '6', '52', 'OLE282', 'OLE194', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE283', 'OLE_DV', '13', '52', 'OLE283', 'OLE195', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE284', 'PaymentMethod', '16', '52', 'OLE284', 'OLE195', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE285', 'sourceAccountingLines.amount', '6', '52', 'OLE285', 'OLE195', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE292', 'OLE_PUR', '13', '52', 'OLE292', 'OLE198', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE293', 'Account', '16', '52', 'OLE293', 'OLE198', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE294', 'items.sourceAccountingLines', '6', '52', 'OLE294', 'OLE198', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE295', 'OLE_AP', '13', '52', 'OLE295', 'OLE199', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE296', 'Account', '16', '52', 'OLE296', 'OLE199', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE297', 'items.sourceAccountingLines', '6', '52', 'OLE297', 'OLE199', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE306', 'taxEntry', '10', '14', 'OLE306', 'OLE209', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE527', 'OLE_DV', '13', '14', 'OLE527', 'OLE209', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE307', 'frnEntry', '10', '14', 'OLE307', 'OLE210', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE528', 'OLE_DV', '13', '14', 'OLE528', 'OLE210', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE308', 'wireEntry', '10', '14', 'OLE308', 'OLE211', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE529', 'OLE_DV', '13', '14', 'OLE529', 'OLE211', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE309', 'travelEntry', '10', '14', 'OLE309', 'OLE212', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE530', 'OLE_DV', '13', '14', 'OLE530', 'OLE212', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE314', 'editPreExtract', '10', '14', 'OLE314', 'OLE217', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE535', 'OLE_AP', '13', '14', 'OLE535', 'OLE217', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE317', 'OpenLibraryEnvironmentTransactionalDocument', '13', '3', 'OLE317', 'OLE220', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE327', 'PayeeACHAccount', '5', '11', 'OLE327', 'OLE233', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE328', 'bankAccountNumber', '6', '11', 'OLE328', 'OLE233', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE329', 'ACHBank', '5', '11', 'OLE329', 'OLE234', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE330', 'bankRoutingNumber', '6', '11', 'OLE330', 'OLE234', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE331', 'PayeeACHAccount', '5', '11', 'OLE331', 'OLE235', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE332', 'bankAccountNumber', '6', '11', 'OLE332', 'OLE235', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE333', 'PayeeACHAccount', '5', '11', 'OLE333', 'OLE236', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE334', 'bankRoutingNumber', '6', '11', 'OLE334', 'OLE236', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE337', 'OLEFinancialProcessingSimpleMaintenanceDocument', '13', '52', 'OLE337', 'OLE238', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE338', 'PreRoute', '16', '52', 'OLE338', 'OLE238', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE339', 'sourceAccountingLines', '6', '52', 'OLE339', 'OLE238', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE340', 'OLEFinancialProcessingSimpleMaintenanceDocument', '13', '52', 'OLE340', 'OLE239', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE341', 'PreRoute', '16', '52', 'OLE341', 'OLE239', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE342', 'targetAccountingLines', '6', '52', 'OLE342', 'OLE239', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE346', 'OLE_PUR', '13', '52', 'OLE346', 'OLE241', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE347', 'PreRoute', '16', '52', 'OLE347', 'OLE241', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE348', 'items.sourceAccountingLines', '6', '52', 'OLE348', 'OLE241', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE349', 'OLE_AP', '13', '52', 'OLE349', 'OLE242', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE350', 'PreRoute', '16', '52', 'OLE350', 'OLE242', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE351', 'items.sourceAccountingLines', '6', '52', 'OLE351', 'OLE242', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE372', 'BatchJobStatus', '5', '10', 'OLE372', 'OLE256', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE373', 'org.kuali.ole.module.purap.web.struts.B2BAction', '2', '12', 'OLE373', 'OLE257', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE374', 'OLE_PREQ', '13', '9', 'OLE374', 'OLE258', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE375', 'Invoice Image', '9', '9', 'OLE375', 'OLE258', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE377', 'OLE_PREQ', '13', '9', 'OLE377', 'OLE260', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE378', 'Invoice Image', '9', '9', 'OLE378', 'OLE260', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE382', 'OLE', '13', '59', 'OLE382', 'OLE263', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE383', 'FALSE', '8', '59', 'OLE383', 'OLE263', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE391', 'OLE_PREQ', '13', '8', 'OLE391', 'OLE268', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE392', 'R', '15', '8', 'OLE392', 'OLE268', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE393', 'OLEPurchasingAccountsPayableTransactionalDocument', '13', '3', 'OLE393', 'OLE269', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE394', 'org.kuali.ole.module.purap.document.web.struts.PrintAction', '2', '12', 'OLE394', 'OLE270', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE395', 'org.kuali.ole.sys.web.struts.ElectronicFundTransferAction', '2', '12', 'OLE395', 'OLE271', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE396', 'printPurchaseOrder', '10', '14', 'OLE396', 'OLE272', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE538', 'OLE_PO', '13', '14', 'OLE538', 'OLE272', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE397', 'previewPrintPurchaseOrder', '10', '14', 'OLE397', 'OLE273', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE539', 'OLE_PO', '13', '14', 'OLE539', 'OLE273', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE398', 'assignSensitiveData', '10', '14', 'OLE398', 'OLE274', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE540', 'OLE_PO', '13', '14', 'OLE540', 'OLE274', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE399', 'resendPurchaseOrder', '10', '14', 'OLE399', 'OLE275', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE541', 'OLE_PO', '13', '14', 'OLE541', 'OLE275', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE400', 'requestPaymentRequestCancel', '10', '14', 'OLE400', 'OLE276', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE542', 'OLE_PREQ', '13', '14', 'OLE542', 'OLE276', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE401', 'paymentRequestHoldCancelRemoval', '10', '14', 'OLE401', 'OLE277', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE543', 'OLE_PREQ', '13', '14', 'OLE543', 'OLE277', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE406', 'OLE_PCDO', '13', '52', 'OLE406', 'OLE280', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE407', 'AccountFullEdit', '16', '52', 'OLE407', 'OLE280', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE408', 'sourceAccountingLines', '6', '52', 'OLE408', 'OLE280', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE409', 'OLE_PCDO', '13', '52', 'OLE409', 'OLE281', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE410', 'AccountFullEdit', '16', '52', 'OLE410', 'OLE281', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE411', 'targetAccountingLines', '6', '52', 'OLE411', 'OLE281', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE412', 'managerCancel', '10', '14', 'OLE412', 'OLE282', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE545', 'OLE_AP', '13', '14', 'OLE545', 'OLE282', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE413', 'processorCancel', '10', '14', 'OLE413', 'OLE283', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE546', 'OLE_AP', '13', '14', 'OLE546', 'OLE283', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE414', 'OLE_ACM', '13', '3', 'OLE414', 'OLE284', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE415', 'OLE-PDP', '4', '12', 'OLE415', 'OLE285', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE416', 'requestPaymentRequestHold', '10', '14', 'OLE416', 'OLE286', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE547', 'OLE_PREQ', '13', '14', 'OLE547', 'OLE286', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE417', 'org.kuali.ole.gl.web.struts.BalanceInquiryAction', '2', '12', 'OLE417', 'OLE287', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE420', 'OLE_EIRT', '13', '3', 'OLE420', 'OLE291', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE421', 'electronicInvoiceInputFileType', '1', '15', 'OLE421', 'OLE292', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE422', 'requestVendorCreditMemoHold', '10', '14', 'OLE422', 'OLE293', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE548', 'OLE_CM', '13', '14', 'OLE548', 'OLE293', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE423', 'vendorCreditMemoHoldRemoval', '10', '14', 'OLE423', 'OLE294', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE549', 'OLE_CM', '13', '14', 'OLE549', 'OLE294', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE425', 'org.kuali.ole.sys.web.struts.KualiBalanceInquiryReportMenuAction', '2', '12', 'OLE425', 'OLE301', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE436', 'OLE_REQS', '13', '52', 'OLE436', 'OLE308', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE437', 'items.sourceAccountingLines', '6', '52', 'OLE437', 'OLE308', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE438', 'Initiator', '16', '52', 'OLE438', 'OLE308', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE447', 'OLE_PO', '13', '9', 'OLE447', 'OLE313', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE448', 'Contracts', '9', '9', 'OLE448', 'OLE313', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE449', 'OLE_PO', '13', '9', 'OLE449', 'OLE314', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE450', 'Contracts', '9', '9', 'OLE450', 'OLE314', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE451', 'OLE_PO', '13', '9', 'OLE451', 'OLE315', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE452', 'Contract Ammendments', '9', '9', 'OLE452', 'OLE315', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE453', 'OLE_PO', '13', '9', 'OLE453', 'OLE316', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE454', 'Contract Ammendments', '9', '9', 'OLE454', 'OLE316', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE455', 'OLE_PO', '13', '9', 'OLE455', 'OLE317', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE456', 'Quotes', '9', '9', 'OLE456', 'OLE317', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE457', 'OLE_PO', '13', '9', 'OLE457', 'OLE318', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE458', 'Quotes', '9', '9', 'OLE458', 'OLE318', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE459', 'OLE_PO', '13', '9', 'OLE459', 'OLE319', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE460', 'RFPs', '9', '9', 'OLE460', 'OLE319', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE461', 'OLE_PO', '13', '9', 'OLE461', 'OLE320', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE462', 'RFPs', '9', '9', 'OLE462', 'OLE320', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE463', 'OLE_PO', '13', '9', 'OLE463', 'OLE321', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE464', 'RFP Responses', '9', '9', 'OLE464', 'OLE321', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE465', 'OLE_PO', '13', '9', 'OLE465', 'OLE322', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE466', 'RFP Responses', '9', '9', 'OLE466', 'OLE322', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE467', 'OLE_PO', '13', '9', 'OLE467', 'OLE323', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE468', 'Other - Restricted', '9', '9', 'OLE468', 'OLE323', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE469', 'OLE_PO', '13', '9', 'OLE469', 'OLE324', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE470', 'Other - Restricted', '9', '9', 'OLE470', 'OLE324', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE471', 'OLE_CM', '13', '9', 'OLE471', 'OLE325', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE472', 'Credit Memo Image', '9', '9', 'OLE472', 'OLE325', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE473', 'OLE_CM', '13', '9', 'OLE473', 'OLE326', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE474', 'Credit Memo Image', '9', '9', 'OLE474', 'OLE326', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE433', 'OLE_REQS', '13', '52', 'OLE433', 'OLE328', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE434', 'items.sourceAccountingLines', '6', '52', 'OLE434', 'OLE328', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE435', 'Organization', '16', '52', 'OLE435', 'OLE328', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE475', 'org.kuali.ole.fp.document.web.struts.CashManagementStatusAction', '2', '12', 'OLE475', 'OLE329', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE476', 'org.kuali.ole.fp.document.web.struts.DepositWizardAction', '2', '12', 'OLE476', 'OLE330', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE477', 'org.kuali.ole.fp.web.struts.CashDrawerCorrectionAction', '2', '12', 'OLE477', 'OLE331', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE484', 'VendorDetail', '5', '11', 'OLE484', 'OLE335', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE485', 'vendorHeader.vendorTaxTypeCode', '6', '11', 'OLE485', 'OLE335', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE510', 'OLE_CR', '13', '3', 'OLE510', 'OLE350', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE511', 'org.kuali.ole.fp.document.web.struts.DisbursementVoucherHelpAction', '2', '12', 'OLE511', 'OLE354', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE512', 'OLE_POA', '13', '52', 'OLE512', 'OLE355', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE513', 'NewUnorderedItems', '16', '52', 'OLE513', 'OLE355', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE514', 'items.sourceAccountingLines', '6', '52', 'OLE514', 'OLE355', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE515', 'OLE_CM', '13', '3', 'OLE515', 'OLE356', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE517', 'OLE_DV', '13', '52', 'OLE517', 'OLE358', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE518', 'Travel', '16', '52', 'OLE518', 'OLE358', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE519', 'sourceAccountingLines.financialObjectCode', '6', '52', 'OLE519', 'OLE358', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE522', 'OLE_CDS', '13', '3', 'OLE522', 'OLE361', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE523', 'OLE*', '4', 'OLECNTRB199-TYP1', 'OLE523', 'OLE362', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE550', 'OLE-PDP', '4', '10', 'OLE550', 'OLE363', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE551', 'PurchasingPaymentDetail', '5', '10', 'OLE551', 'OLE363', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE552', 'VendorDetail', '5', '11', 'OLE552', 'OLE364', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE553', 'vendorHeader.vendorTaxTypeCode', '6', '11', 'OLE553', 'OLE364', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE554', 'OLE_PREQ', '13', '8', 'OLE554', 'OLE365', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE555', 'P', '15', '8', 'OLE555', 'OLE365', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE556', 'OLE_PREQ', '13', '8', 'OLE556', 'OLE366', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE557', 'F', '15', '8', 'OLE557', 'OLE366', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE558', 'OLE_CM', '13', '8', 'OLE558', 'OLE367', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE559', 'P', '15', '8', 'OLE559', 'OLE367', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE560', 'OLE_CM', '13', '8', 'OLE560', 'OLE368', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE561', 'F', '15', '8', 'OLE561', 'OLE368', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE569', 'OLE_CMD', '13', '3', 'OLE569', 'OLE372', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE570', 'OLE_PREQ', '13', '3', 'OLE570', 'OLE373', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE571', 'OLE_DV', '13', '3', 'OLE571', 'OLE374', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE574', 'IdentityManagementGroupDocument', '13', '3', 'OLE574', 'OLE379', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE575', 'OLE_GORV', '13', '3', 'OLE575', 'OLE380', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE576', 'DisbursementVoucherWireTransfer', '5', '11', 'OLE576', 'OLE381', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE577', 'disbVchrPayeeAccountNumber', '6', '11', 'OLE577', 'OLE381', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE578', 'OpenLibraryEnvironmentSimpleMaintenanceDocument', '13', '8', 'OLE578', 'OLE382', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE580', 'AdHoc', '16', '8', 'OLE580', 'OLE382', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE582', 'R', '15', '8', 'OLE582', 'OLE382', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE579', 'OpenLibraryEnvironmentComplexMaintenanceDocument', '13', '8', 'OLE579', 'OLE383', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE581', 'AdHoc', '16', '8', 'OLE581', 'OLE383', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE583', 'R', '15', '8', 'OLE583', 'OLE383', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE586', 'collectorFlatFileInputFileType', '1', '15', 'OLE586', 'OLE385', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE587', 'PaymentGroup', '5', '11', 'OLE587', 'OLE386', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE588', 'achBankRoutingNbr', '6', '11', 'OLE588', 'OLE386', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE589', 'AchAccountNumber', '5', '11', 'OLE589', 'OLE387', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE590', 'achBankAccountNbr', '6', '11', 'OLE590', 'OLE387', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE591', 'OLE', '13', '5', 'OLE591', 'OLE390', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE592', 'A', '14', '5', 'OLE592', 'OLE390', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE593', 'OLE', '13', '5', 'OLE593', 'OLE391', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE594', 'F', '14', '5', 'OLE594', 'OLE391', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE595', 'OLE', '13', '5', 'OLE595', 'OLE392', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE596', 'K', '14', '5', 'OLE596', 'OLE392', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE74', 'OLE_GOBJ', '13', '3', 'OLE74', 'OLE55', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE75', 'OLE_ORGR', '13', '3', 'OLE75', 'OLE56', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE79', 'SubAccount', '5', '11', 'OLE79', 'OLE60', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80', 'a21SubAccount', '6', '11', 'OLE80', 'OLE60', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE81', 'Organization', '5', '11', 'OLE81', 'OLE61', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE82', 'organizationPlantChartCode', '6', '11', 'OLE82', 'OLE61', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE83', 'Organization', '5', '11', 'OLE83', 'OLE62', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE84', 'organizationPlantAccountNumber', '6', '11', 'OLE84', 'OLE62', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE85', 'Organization', '5', '11', 'OLE85', 'OLE63', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE86', 'campusPlantChartCode', '6', '11', 'OLE86', 'OLE63', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE87', 'Organization', '5', '11', 'OLE87', 'OLE64', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE88', 'campusPlantAccountNumber', '6', '11', 'OLE88', 'OLE64', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE90', 'OLE_DI', '13', 'OLE51', 'OLE90', 'OLE66', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE91', 'OLE_YEDI', '13', 'OLE51', 'OLE91', 'OLE67', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE93', 'OLE_CMD', '13', '3', 'OLE93', 'OLE69', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE94', 'OLE_SB', '13', '3', 'OLE94', 'OLE70', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE95', 'OLE_JV', '13', '3', 'OLE95', 'OLE71', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE96', 'OLE_PCDO', '13', '3', 'OLE96', 'OLE72', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE97', 'ProcurementCardHolder', '5', '11', 'OLE97', 'OLE73', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE98', 'transactionCreditCardNumber', '6', '11', 'OLE98', 'OLE73', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE100', 'procurementCardInputFileType', '1', '15', 'OLE100', 'OLE74', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE101', 'OLE_GLCP', '13', '3', 'OLE101', 'OLE75', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE103', 'collectorXmlInputFileType', '1', '15', 'OLE103', 'OLE76', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE105', 'enterpriseFeederFileSetType', '1', '15', 'OLE105', 'OLE77', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE108', 'OLEPreDisbursementProcessorSimpleMaintenanceDocument', '13', '3', 'OLE108', 'OLE81', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE110', 'OLE-PDP', '4', '10', 'OLE110', 'OLE88', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE111', 'OLE-PDP', '4', '10', 'OLE111', 'OLE89', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE113', 'paymentInputFileType', '1', '15', 'OLE113', 'OLE90', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE114', 'OLE_PREQ', '13', '3', 'OLE114', 'OLE91', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE115', 'OLE_REQS', '13', '3', 'OLE115', 'OLE92', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE116', 'OLE_PUR', '13', '3', 'OLE116', 'OLE93', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE117', 'OLE_PO', '13', '3', 'OLE117', 'OLE94', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE118', 'OLE_POC', '13', '3', 'OLE118', 'OLE95', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE119', 'OLE_PORT', '13', '3', 'OLE119', 'OLE96', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE120', 'OLE_PREQ', '13', '3', 'OLE120', 'OLE97', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE121', 'OLE_RCV', '13', '3', 'OLE121', 'OLE98', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE122', 'OLE_EIRT', '13', '3', 'OLE122', 'OLE99', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10001', 'OLE', '13', '3', 'OLE10001', 'OLE1101', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10003', 'OLE', '13', '3', 'OLE10003', 'OLE1103', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE1500', 'marcInputFileType', '1', '15', 'OLE1500', 'OLE1500', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLESEC6001-1', 'OLEAccessSecuritySimpleMaintenanceDocument', '13', '3', 'OLESEC6001-1', 'OLESEC6001', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLESEC6002-1', 'OLE-SEC', '4', '10', 'OLESEC6002-1', 'OLESEC6002', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLESEC6003-1', 'OLE-SEC', '4', '10', 'OLESEC6003-1', 'OLESEC6003', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLESEC6004-1', 'OLEAccessSecuritySimpleMaintenanceDocument', '13', '8', 'OLESEC6004-1', 'OLESEC6004', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLESEC6004-2', 'PreRoute', '16', '8', 'OLESEC6004-2', 'OLESEC6004', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEPURAP6940-01', 'PurchaseOrderDocument', '5', '11', 'OLEPURAP6940-01', 'OLEPURAP6940', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEPURAP6940-02', 'purapDocumentIdentifier', '6', '11', 'OLEPURAP6940-02', 'OLEPURAP6940', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLESYS6007-1', 'OLE_DV', '13', '14', 'OLESYS6007-1', 'OLESYS6007', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLESYS6007-2', 'immediateDisbursementEntryMode', '10', '14', 'OLESYS6007-2', 'OLESYS6007', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI6886-AT1', 'OLE_PAAT', '13', '56', 'OLEMI6886-AT1', 'OLEMI6886-PRM1', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI6886-AT2', 'false', '7', '56', 'OLEMI6886-AT2', 'OLEMI6886-PRM1', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI6886-AT3', 'PayeeACHAccount', '5', '10', 'OLEMI6886-AT3', 'OLEMI6886-PRM2', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI6886-AT4', 'OLE-PDP', '4', '10', 'OLEMI6886-AT4', 'OLEMI6886-PRM2', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI6886-AT5', 'OLE_PAAT', '13', '3', 'OLEMI6886-AT5', 'OLEMI6886-PRM3', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLECNTRB162-AT1', 'vendorExcludeInputFileType', '1', '15', 'OLECNTRB162-AT1', 'OLECNTRB162-PRM', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLECNTRB199-PATD', 'semaphoreInputFileTypeError', '1', '15', 'OLECNTRB199-PATD', 'OLECNTRB199-P1', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLECNTRB199-PAD2', 'OLE-SYS', '4', 'OLECNTRB199-TYP1', 'OLECNTRB199-PAD2', 'OLECNTRB199-P2', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLECNTRB199-PAD3', 'staging/sys/batchContainer/*', 'OLECNTRB199-ATTRDEF1', 'OLECNTRB199-TYP1', 'OLECNTRB199-PAD3', 'OLECNTRB199-P2', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI8342-PRMATT1', 'indirectCostRecoveryAccounts', '44', '57', 'OLEMI8342-PRMATT1', 'OLEMI8342-PRM', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI8342-PRMATT2', 'SubAccount', '5', '57', 'OLEMI8342-PRMATT2', 'OLEMI8342-PRM', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI8944-PRMATT1', 'items.sourceAccountingLines', '6', '52', 'OLEMI8944-PRMATT1', 'OLEMI8944-PRM', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI8944-PRMATT2', 'Tax', '16', '52', 'OLEMI8944-PRMATT2', 'OLEMI8944-PRM', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI8944-PRMATT3', 'OLE_PUR', '13', '52', 'OLEMI8944-PRMATT3', 'OLEMI8944-PRM', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI8893-PRM1ATT1', 'OLEFinancialProcessingTransactionalDocument', '13', '8', 'OLEMI8893-PRM1ATT1', 'OLEMI8893-PRM1', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI8893-PRM2ATT1', 'OLE', '13', '8', 'OLEMI8893-PRM2ATT1', 'OLEMI8893-PRM2', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI9598-PRM1ATT', 'OLE', '13', 'KR1000', 'OLEMI9598-PRM1ATT', 'OLEMI9598-PRM1', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI9598-PRM2ATT', 'OLE', '13', 'KR1000', 'OLEMI9598-PRM2ATT', 'OLEMI9598-PRM2', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLEMI9598-PRM3ATT', 'OLE', '13', 'KR1000', 'OLEMI9598-PRM3ATT', 'OLEMI9598-PRM3', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_PERM_ATTR_DATA_T', '2.0.5', '3:a8733d6a79caebf11538afc87a6dab27', 11)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_RSP_T::ole::(Checksum: 3:bf1f240e956de8470fd652cc19b1308d)
INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE OrganizationHierarchy', 'OLE-SYS', 'OLE1', 'OLE1', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review POA Account', 'OLE-PURAP', 'OLE107', 'OLE107', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review CM Account', 'OLE-PURAP', 'OLE108', 'OLE108', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review EIRT Management', 'OLE-PURAP', 'OLE109', 'OLE109', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review REQS Initiator', 'OLE-PURAP', 'OLE113', 'OLE113', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review IdentityManagementDocument GroupType', 'OLE-SYS', 'OLE117', 'OLE117', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE Account', 'OLE-SYS', 'OLE12', 'OLE12', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review PCDO Account', 'OLE-FP', 'OLE120', 'OLE120', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review IdentityManagementDocument RoleType', 'OLE-SYS', 'OLE123', 'OLE123', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review GDLG Account', 'OLE-COA', 'OLE124', 'OLE124', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE Chart', 'OLE-SYS', 'OLE26', 'OLE26', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review ACCT Account', 'OLE-SYS', 'OLE29', 'OLE29', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review REQS Organization', 'OLE-PURAP', 'OLE3', 'OLE3', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE Award', 'OLE-SYS', 'OLE31', 'OLE31', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLET AccountingOrganizationHierarchy', 'OLE-SYS', 'OLE4', 'OLE4', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review CR CashManagement', 'OLE-FP', 'OLE44', 'OLE44', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review DV Campus', 'OLE-FP', 'OLE46', 'OLE46', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review DV Purchasing', 'OLE-FP', 'OLE48', 'OLE48', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review DV Tax', 'OLE-FP', 'OLE49', 'OLE49', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review DV Travel', 'OLE-FP', 'OLE50', 'OLE50', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review DV PaymentMethod', 'OLE-FP', 'OLE51', 'OLE51', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review PUR Commodity', 'OLE-PURAP', 'OLE6', 'OLE6', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE SubFund', 'OLE-SYS', 'OLE7', 'OLE7', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review AP ImageAttachment', 'OLE-PURAP', 'OLE71', 'OLE71', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review PREQ Receiving', 'OLE-PURAP', 'OLE73', 'OLE73', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review PREQ Tax', 'OLE-PURAP', 'OLE74', 'OLE74', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review POA NewUnorderedItems', 'OLE-PURAP', 'OLE75', 'OLE75', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review PO Budget', 'OLE-PURAP', 'OLE76', 'OLE76', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review PO ContractManagement', 'OLE-PURAP', 'OLE78', 'OLE78', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review PO Tax', 'OLE-PURAP', 'OLE80', 'OLE80', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review PORH AccountsPayable', 'OLE-PURAP', 'OLE81', 'OLE81', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLET SubAccount', 'OLE-PURAP', 'OLE83', 'OLE83', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review REQS SeparationOfDuties', 'OLE-PURAP', 'OLE85', 'OLE85', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Resolve Exception', 'OLE-SYS', 'OLE87', 'OLE87', '2', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review PVEN Initiator', 'OLE-VND', 'OLE91', 'OLE91', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review PVEN Management', 'OLE-VND', 'OLE92', 'OLE92', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review RCVL OutstandingTransactions', 'OLE-PURAP', 'OLE94', 'OLE94', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review PCDO AccountFullEdit', 'OLE-FP', 'OLE99', 'OLE99', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review DV SeparationOfDuties', 'OLE-FP', 'OLEFP7037', 'OLE126', '1', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_RSP_T', '2.0.5', '3:bf1f240e956de8470fd652cc19b1308d', 12)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_RSP_ATTR_DATA_T::ole::(Checksum: 3:2a5699630fe69410d220f6cda2baef11)
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE313', 'OrganizationHierarchy', '16', '7', 'OLE313', 'OLE1', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE314', 'OLE', '13', '7', 'OLE314', 'OLE1', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE315', 'TRUE', '41', '7', 'OLE315', 'OLE1', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE316', 'FALSE', '40', '7', 'OLE316', 'OLE1', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE392', 'Account', '16', '7', 'OLE392', 'OLE107', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE393', 'OLE_POA', '13', '7', 'OLE393', 'OLE107', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE394', 'FALSE', '41', '7', 'OLE394', 'OLE107', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE395', 'TRUE', '40', '7', 'OLE395', 'OLE107', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE396', 'Account', '16', '7', 'OLE396', 'OLE108', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE397', 'OLE_CM', '13', '7', 'OLE397', 'OLE108', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE398', 'FALSE', '41', '7', 'OLE398', 'OLE108', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE399', 'FALSE', '40', '7', 'OLE399', 'OLE108', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE400', 'Management', '16', '7', 'OLE400', 'OLE109', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE401', 'OLE_EIRT', '13', '7', 'OLE401', 'OLE109', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE402', 'FALSE', '41', '7', 'OLE402', 'OLE109', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE403', 'FALSE', '40', '7', 'OLE403', 'OLE109', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE416', 'OLE_REQS', '13', '7', 'OLE416', 'OLE113', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE417', 'Initiator', '16', '7', 'OLE417', 'OLE113', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE418', 'FALSE', '40', '7', 'OLE418', 'OLE113', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE419', 'FALSE', '41', '7', 'OLE419', 'OLE113', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE432', 'IdentityManagementDocument', '13', '7', 'OLE432', 'OLE117', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE433', 'GroupType', '16', '7', 'OLE433', 'OLE117', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE434', 'FALSE', '40', '7', 'OLE434', 'OLE117', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE435', 'TRUE', '41', '7', 'OLE435', 'OLE117', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE436', 'OLE68', '46', '7', 'OLE436', 'OLE117', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10', 'OLE', '13', '7', 'OLE10', 'OLE12', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE11', 'FALSE', '41', '7', 'OLE11', 'OLE12', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE12', 'TRUE', '40', '7', 'OLE12', 'OLE12', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE9', 'Account', '16', '7', 'OLE9', 'OLE12', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE445', 'OLE_PCDO', '13', '7', 'OLE445', 'OLE120', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE446', 'Account', '16', '7', 'OLE446', 'OLE120', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE447', 'TRUE', '40', '7', 'OLE447', 'OLE120', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE448', 'FALSE', '41', '7', 'OLE448', 'OLE120', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE449', 'IdentityManagementDocument', '13', '7', 'OLE449', 'OLE123', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE450', 'RoleType', '16', '7', 'OLE450', 'OLE123', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE451', 'FALSE', '40', '7', 'OLE451', 'OLE123', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE452', 'TRUE', '41', '7', 'OLE452', 'OLE123', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE453', '29', '46', '7', 'OLE453', 'OLE123', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE462', 'TRUE', '40', '7', 'OLE462', 'OLE124', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE463', 'Account', '16', '7', 'OLE463', 'OLE124', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE464', 'OLE_GDLG', '13', '7', 'OLE464', 'OLE124', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE465', 'FALSE', '41', '7', 'OLE465', 'OLE124', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE65', 'Chart', '16', '7', 'OLE65', 'OLE26', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE66', 'OLE', '13', '7', 'OLE66', 'OLE26', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE67', 'FALSE', '41', '7', 'OLE67', 'OLE26', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE68', 'TRUE', '40', '7', 'OLE68', 'OLE26', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE77', 'Account', '16', '7', 'OLE77', 'OLE29', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE78', 'OLE_ACCT', '13', '7', 'OLE78', 'OLE29', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE79', 'FALSE', '41', '7', 'OLE79', 'OLE29', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80', 'TRUE', '40', '7', 'OLE80', 'OLE29', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE305', 'Organization', '16', '7', 'OLE305', 'OLE3', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE306', 'OLE_REQS', '13', '7', 'OLE306', 'OLE3', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE307', 'TRUE', '41', '7', 'OLE307', 'OLE3', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE308', 'FALSE', '40', '7', 'OLE308', 'OLE3', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE85', 'Award', '16', '7', 'OLE85', 'OLE31', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE86', 'OLE', '13', '7', 'OLE86', 'OLE31', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE87', 'FALSE', '41', '7', 'OLE87', 'OLE31', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE88', 'FALSE', '40', '7', 'OLE88', 'OLE31', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE321', 'AccountingOrganizationHierarchy', '16', '7', 'OLE321', 'OLE4', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE322', 'OpenLibraryEnvironmentTransactionalDocument', '13', '7', 'OLE322', 'OLE4', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE323', 'TRUE', '41', '7', 'OLE323', 'OLE4', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE324', 'FALSE', '40', '7', 'OLE324', 'OLE4', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE137', 'CashManagement', '16', '7', 'OLE137', 'OLE44', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE138', 'OLE_CR', '13', '7', 'OLE138', 'OLE44', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE139', 'FALSE', '41', '7', 'OLE139', 'OLE44', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE140', 'TRUE', '40', '7', 'OLE140', 'OLE44', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE145', 'Campus', '16', '7', 'OLE145', 'OLE46', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE146', 'OLE_DV', '13', '7', 'OLE146', 'OLE46', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE147', 'FALSE', '41', '7', 'OLE147', 'OLE46', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE148', 'FALSE', '40', '7', 'OLE148', 'OLE46', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE153', 'Purchasing', '16', '7', 'OLE153', 'OLE48', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE154', 'OLE_DV', '13', '7', 'OLE154', 'OLE48', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE155', 'FALSE', '41', '7', 'OLE155', 'OLE48', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE156', 'FALSE', '40', '7', 'OLE156', 'OLE48', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE157', 'Tax', '16', '7', 'OLE157', 'OLE49', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE158', 'OLE_DV', '13', '7', 'OLE158', 'OLE49', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE159', 'FALSE', '41', '7', 'OLE159', 'OLE49', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE160', 'FALSE', '40', '7', 'OLE160', 'OLE49', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE161', 'Travel', '16', '7', 'OLE161', 'OLE50', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE162', 'OLE_DV', '13', '7', 'OLE162', 'OLE50', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE163', 'FALSE', '41', '7', 'OLE163', 'OLE50', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE164', 'FALSE', '40', '7', 'OLE164', 'OLE50', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE165', 'PaymentMethod', '16', '7', 'OLE165', 'OLE51', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE166', 'OLE_DV', '13', '7', 'OLE166', 'OLE51', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE167', 'FALSE', '41', '7', 'OLE167', 'OLE51', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE168', 'FALSE', '40', '7', 'OLE168', 'OLE51', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE289', 'Commodity', '16', '7', 'OLE289', 'OLE6', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE290', 'OLE_PUR', '13', '7', 'OLE290', 'OLE6', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE291', 'TRUE', '41', '7', 'OLE291', 'OLE6', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE292', 'FALSE', '40', '7', 'OLE292', 'OLE6', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE317', 'SubFund', '16', '7', 'OLE317', 'OLE7', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE318', 'OLE', '13', '7', 'OLE318', 'OLE7', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE319', 'TRUE', '41', '7', 'OLE319', 'OLE7', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE320', 'FALSE', '40', '7', 'OLE320', 'OLE7', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE245', 'ImageAttachment', '16', '7', 'OLE245', 'OLE71', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE246', 'OLE_AP', '13', '7', 'OLE246', 'OLE71', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE247', 'FALSE', '41', '7', 'OLE247', 'OLE71', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE248', 'FALSE', '40', '7', 'OLE248', 'OLE71', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE253', 'Receiving', '16', '7', 'OLE253', 'OLE73', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE254', 'OLE_PREQ', '13', '7', 'OLE254', 'OLE73', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE255', 'FALSE', '41', '7', 'OLE255', 'OLE73', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE256', 'FALSE', '40', '7', 'OLE256', 'OLE73', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE257', 'Tax', '16', '7', 'OLE257', 'OLE74', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE258', 'OLE_PREQ', '13', '7', 'OLE258', 'OLE74', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE259', 'FALSE', '41', '7', 'OLE259', 'OLE74', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE260', 'FALSE', '40', '7', 'OLE260', 'OLE74', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE261', 'NewUnorderedItems', '16', '7', 'OLE261', 'OLE75', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE262', 'OLE_POA', '13', '7', 'OLE262', 'OLE75', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE263', 'FALSE', '41', '7', 'OLE263', 'OLE75', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE264', 'FALSE', '40', '7', 'OLE264', 'OLE75', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE265', 'Budget', '16', '7', 'OLE265', 'OLE76', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE266', 'OLE_PO', '13', '7', 'OLE266', 'OLE76', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE267', 'FALSE', '41', '7', 'OLE267', 'OLE76', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE268', 'FALSE', '40', '7', 'OLE268', 'OLE76', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE273', 'ContractManagement', '16', '7', 'OLE273', 'OLE78', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE274', 'OLE_PO', '13', '7', 'OLE274', 'OLE78', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE275', 'FALSE', '41', '7', 'OLE275', 'OLE78', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE276', 'FALSE', '40', '7', 'OLE276', 'OLE78', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE281', 'Tax', '16', '7', 'OLE281', 'OLE80', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE282', 'OLE_PO', '13', '7', 'OLE282', 'OLE80', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE283', 'FALSE', '41', '7', 'OLE283', 'OLE80', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE284', 'FALSE', '40', '7', 'OLE284', 'OLE80', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE285', 'AccountsPayable', '16', '7', 'OLE285', 'OLE81', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE286', 'OLE_PORH', '13', '7', 'OLE286', 'OLE81', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE287', 'FALSE', '41', '7', 'OLE287', 'OLE81', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE288', 'FALSE', '40', '7', 'OLE288', 'OLE81', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE293', 'SubAccount', '16', '7', 'OLE293', 'OLE83', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE294', 'OpenLibraryEnvironmentTransactionalDocument', '13', '7', 'OLE294', 'OLE83', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE295', 'TRUE', '41', '7', 'OLE295', 'OLE83', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE296', 'FALSE', '40', '7', 'OLE296', 'OLE83', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE301', 'SeparationOfDuties', '16', '7', 'OLE301', 'OLE85', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE302', 'OLE_REQS', '13', '7', 'OLE302', 'OLE85', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE303', 'FALSE', '41', '7', 'OLE303', 'OLE85', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE304', 'FALSE', '40', '7', 'OLE304', 'OLE85', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE310', 'OLE', '13', '54', 'OLE310', 'OLE87', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE325', 'Initiator', '16', '7', 'OLE325', 'OLE91', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE326', 'OLE_PVEN', '13', '7', 'OLE326', 'OLE91', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE327', 'FALSE', '41', '7', 'OLE327', 'OLE91', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE328', 'FALSE', '40', '7', 'OLE328', 'OLE91', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE329', 'Management', '16', '7', 'OLE329', 'OLE92', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE330', 'OLE_PVEN', '13', '7', 'OLE330', 'OLE92', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE331', 'FALSE', '41', '7', 'OLE331', 'OLE92', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE332', 'FALSE', '40', '7', 'OLE332', 'OLE92', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE337', 'OLE_RCVL', '13', '7', 'OLE337', 'OLE94', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE338', 'OutstandingTransactions', '16', '7', 'OLE338', 'OLE94', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE339', 'FALSE', '40', '7', 'OLE339', 'OLE94', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE340', 'FALSE', '41', '7', 'OLE340', 'OLE94', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE360', 'OLE_PCDO', '13', '7', 'OLE360', 'OLE99', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE361', 'AccountFullEdit', '16', '7', 'OLE361', 'OLE99', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE362', 'TRUE', '40', '7', 'OLE362', 'OLE99', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE363', 'FALSE', '41', '7', 'OLE363', 'OLE99', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLEFP7037A', 'false', '41', '7', 'OLEFP7037A', 'OLE126', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLEFP7037B', 'SeparationOfDuties', '16', '7', 'OLEFP7037B', 'OLE126', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLEFP7037C', 'OLE_DV', '13', '7', 'OLEFP7037C', 'OLE126', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLEFP7037D', 'false', '40', '7', 'OLEFP7037D', 'OLE126', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_RSP_ATTR_DATA_T', '2.0.5', '3:2a5699630fe69410d220f6cda2baef11', 13)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_ROLE_PERM_T::ole::(Checksum: 3:5bb3386e7eab2a4e610e03ffd8200191)
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE100', 'OLE77', 'OLE45', 'OLE100')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE1004', '306', 'OLE49', 'OLE1004')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE103', 'OLE80', 'OLE20', 'OLE103')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE104', 'OLE81', 'OLE45', 'OLE104')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE105', 'OLE82', 'OLE19', 'OLE105')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE106', 'OLE83', 'OLE20', 'OLE106')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE107', 'OLE84', 'OLE19', 'OLE107')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE109', 'OLE86', 'OLE19', 'OLE109')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE110', 'OLE87', 'OLE50', 'OLE110')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE1106', 'OLE1100', 'OLE62', 'OLE1106')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE1107', 'OLE1100', 'OLE55', 'OLE1107')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE1108', 'OLE1101', '95', 'OLE1108')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE1111', 'OLE1103', '61', 'OLE1111')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE113', 'OLE89', 'OLE19', 'OLE113')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE114', 'OLE89', 'OLE18', 'OLE114')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE115', 'OLE90', 'OLE18', 'OLE115')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE116', 'OLE91', 'OLE44', 'OLE116')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE117', 'OLE91', 'OLE62', 'OLE117')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE118', 'OLE92', 'OLE32', 'OLE118')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE119', 'OLE92', 'OLE54', 'OLE119')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE121', 'OLE93', 'OLE26', 'OLE121')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE123', 'OLE94', 'OLE26', 'OLE123')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE124', 'OLE94', 'OLE62', 'OLE124')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE125', 'OLE95', 'OLE32', 'OLE125')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE126', 'OLE95', 'OLE54', 'OLE126')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE127', 'OLE96', 'OLE32', 'OLE127')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE128', 'OLE96', 'OLE54', 'OLE128')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE129', 'OLE97', 'OLE22', 'OLE129')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE130', 'OLE98', 'OLE32', 'OLE130')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE131', 'OLE98', 'OLE54', 'OLE131')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE132', 'OLE99', 'OLE62', 'OLE132')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE133', 'OLE100', 'OLE32', 'OLE133')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE134', 'OLE100', 'OLE54', 'OLE134')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE136', 'OLE101', 'OLE26', 'OLE136')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE137', 'OLE102', 'OLE22', 'OLE137')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE138', 'OLE103', 'OLE32', 'OLE138')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE139', 'OLE103', 'OLE54', 'OLE139')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE143', 'OLE107', 'OLE16', 'OLE143')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE144', 'OLE108', '95', 'OLE144')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE151', 'OLE113', 'OLE55', 'OLE151')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE152', 'OLE114', 'OLE44', 'OLE152')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE153', 'OLE115', 'OLE54', 'OLE153')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE154', 'OLE116', 'OLE44', 'OLE154')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE155', 'OLE117', 'OLE45', 'OLE155')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE157', 'OLE119', 'OLE44', 'OLE157')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE158', 'OLE120', 'OLE44', 'OLE158')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE159', 'OLE121', 'OLE44', 'OLE159')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE160', 'OLE122', 'OLE54', 'OLE160')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE167', 'OLE128', 'OLE45', 'OLE167')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE168', 'OLE129', 'OLE32', 'OLE168')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE169', 'OLE129', 'OLE54', 'OLE169')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE170', 'OLE130', 'OLE32', 'OLE170')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE171', 'OLE130', 'OLE54', 'OLE171')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE172', 'OLE131', 'OLE44', 'OLE172')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE173', 'OLE132', 'OLE45', 'OLE173')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE174', 'OLE133', 'OLE54', 'OLE174')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE175', 'OLE133', '61', 'OLE175')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE176', 'OLE134', 'OLE44', 'OLE176')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE177', 'OLE136', 'OLE32', 'OLE177')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE178', 'OLE136', 'OLE54', 'OLE178')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE179', 'OLE137', 'OLE49', 'OLE179')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE180', 'OLE138', 'OLE49', 'OLE180')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE182', 'OLE139', 'OLE26', 'OLE182')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE192', '148', 'OLE55', 'OLE192')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE209', '163', 'OLE44', 'OLE209')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE214', 'OLE169', '66', 'OLE214')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE215', 'OLE169', 'OLE62', 'OLE215')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE230', '183', 'OLE44', 'OLE230')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE232', 'OLE184', 'OLE26', 'OLE232')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE233', 'OLE185', 'OLE11', 'OLE233')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE235', 'OLE187', 'OLE49', 'OLE235')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE236', 'OLE87', 'OLE19', 'OLE236')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE239', 'OLE189', 'OLE13', 'OLE239')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE240', 'OLE190', 'OLE41', 'OLE240')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE241', 'OLE191', 'OLE41', 'OLE241')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE242', 'OLE192', '66', 'OLE242')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE243', 'OLE193', '66', 'OLE243')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE244', 'OLE194', '66', 'OLE244')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE245', 'OLE195', '66', 'OLE245')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE248', 'OLE198', 'OLE41', 'OLE248')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE249', 'OLE199', 'OLE41', 'OLE249')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE270', 'OLE209', 'OLE50', 'OLE270')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE271', 'OLE210', 'OLE70', 'OLE271')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE272', 'OLE211', 'OLE70', 'OLE272')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE273', 'OLE212', 'OLE15', 'OLE273')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE280', 'OLE217', 'OLE22', 'OLE280')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE284', 'OLE220', 'OLE16', 'OLE284')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE299', 'OLE233', 'OLE44', 'OLE299')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE300', 'OLE234', 'OLE19', 'OLE300')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE301', 'OLE235', 'OLE45', 'OLE301')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE302', 'OLE236', 'OLE19', 'OLE302')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE305', 'OLE239', '66', 'OLE305')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE307', 'OLE241', '66', 'OLE307')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE308', 'OLE242', '66', 'OLE308')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE311', 'OLE238', '66', 'OLE311')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE316', 'OLE73', 'OLE45', 'OLE316')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE321', 'OLE212', 'OLE41', 'OLE321')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE508', 'OLE256', 'OLE82', 'OLE508')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE509', 'OLE257', 'OLE54', 'OLE509')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE510', 'OLE257', 'OLE32', 'OLE510')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE511', 'OLE258', 'OLE22', 'OLE511')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE513', 'OLE260', 'OLE22', 'OLE513')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE514', 'OLE260', 'OLE26', 'OLE514')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE518', 'OLE263', 'OLE44', 'OLE518')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE524', 'OLE268', 'OLE22', 'OLE524')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE525', 'OLE268', '59', 'OLE525')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE526', 'OLE269', 'OLE84', 'OLE526')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE527', 'OLE270', 'OLE26', 'OLE527')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE528', 'OLE271', 'OLE30', 'OLE528')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE529', 'OLE271', 'OLE16', 'OLE529')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE530', 'OLE272', 'OLE26', 'OLE530')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE531', 'OLE272', '89', 'OLE531')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE532', 'OLE273', 'OLE26', 'OLE532')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE533', 'OLE274', 'OLE26', 'OLE533')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE534', 'OLE275', 'OLE47', 'OLE534')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE535', 'OLE276', '59', 'OLE535')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE536', 'OLE277', 'OLE86', 'OLE536')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE537', 'OLE277', 'OLE29', 'OLE537')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE540', 'OLE280', '66', 'OLE540')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE541', 'OLE281', '66', 'OLE541')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE542', 'OLE282', 'OLE29', 'OLE542')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE543', 'OLE283', 'OLE22', 'OLE543')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE544', 'OLE284', 'OLE26', 'OLE544')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE545', 'OLE285', 'OLE20', 'OLE545')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE546', 'OLE286', 'OLE22', 'OLE546')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE547', 'OLE286', '59', 'OLE547')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE549', 'OLE287', 'OLE54', 'OLE549')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE551', 'OLE101', '60', 'OLE551')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE554', 'OLE291', 'OLE22', 'OLE554')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE555', 'OLE136', 'OLE62', 'OLE555')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE556', 'OLE292', 'OLE22', 'OLE556')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE557', 'OLE293', 'OLE22', 'OLE557')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE558', 'OLE294', 'OLE29', 'OLE558')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE559', 'OLE294', 'OLE86', 'OLE559')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE563', '149', 'OLE44', 'OLE563')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE565', '298', 'OLE44', 'OLE565')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE567', '299', 'OLE44', 'OLE567')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE571', 'OLE301', 'OLE54', 'OLE571')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE579', '306', 'OLE44', 'OLE579')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE580', 'OLE328', '66', 'OLE580')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE581', 'OLE308', '66', 'OLE581')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE594', 'OLE313', 'OLE26', 'OLE594')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE595', 'OLE314', 'OLE26', 'OLE595')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE596', 'OLE315', 'OLE26', 'OLE596')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE597', 'OLE316', 'OLE26', 'OLE597')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE598', 'OLE317', 'OLE26', 'OLE598')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE599', 'OLE318', 'OLE26', 'OLE599')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE600', 'OLE319', 'OLE26', 'OLE600')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE601', 'OLE320', 'OLE26', 'OLE601')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE602', 'OLE321', 'OLE26', 'OLE602')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE603', 'OLE322', 'OLE26', 'OLE603')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE604', 'OLE323', 'OLE26', 'OLE604')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE605', 'OLE324', 'OLE26', 'OLE605')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE606', 'OLE325', 'OLE22', 'OLE606')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE607', 'OLE326', 'OLE22', 'OLE607')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE608', 'OLE326', 'OLE26', 'OLE608')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE611', 'OLE95', 'OLE62', 'OLE611')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE612', 'OLE329', 'OLE11', 'OLE612')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE613', 'OLE330', 'OLE11', 'OLE613')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE614', 'OLE331', 'OLE11', 'OLE614')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE619', 'OLE335', 'OLE49', 'OLE619')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE649', 'OLE350', 'OLE92', 'OLE649')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE650', 'OLE351', 'OLE33', 'OLE650')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE651', 'OLE352', 'OLE94', 'OLE651')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE652', 'OLE353', 'OLE93', 'OLE652')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE653', 'OLE102', 'OLE62', 'OLE653')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE654', 'OLE354', 'OLE54', 'OLE654')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE656', 'OLE355', '66', 'OLE656')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE657', 'OLE97', 'OLE62', 'OLE657')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE658', 'OLE356', 'OLE22', 'OLE658')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE661', 'OLE358', '66', 'OLE661')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE662', 'OLE354', '83', 'OLE662')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE664', 'OLE361', 'OLE11', 'OLE664')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE665', 'OLE362', 'OLE44', 'OLE665')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE666', 'OLE363', 'OLE54', 'OLE666')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE667', 'OLE363', 'OLE32', 'OLE667')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE668', 'OLE88', 'OLE54', 'OLE668')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE669', 'OLE88', 'OLE32', 'OLE669')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE670', 'OLE364', 'OLE49', 'OLE670')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE671', 'OLE365', 'OLE22', 'OLE671')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE672', 'OLE366', 'OLE22', 'OLE672')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE673', 'OLE367', 'OLE22', 'OLE673')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE674', 'OLE368', 'OLE22', 'OLE674')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE680', 'OLE372', 'OLE11', 'OLE680')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE681', 'OLE373', 'OLE22', 'OLE681')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE682', 'OLE374', 'OLE22', 'OLE682')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE693', 'OLE379', 'OLE54', 'OLE693')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE694', 'OLE380', 'OLE37', 'OLE694')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE696', 'OLE381', '60', 'OLE696')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE697', 'OLE381', 'OLE70', 'OLE697')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE698', 'OLE382', '59', 'OLE698')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE699', 'OLE383', '59', 'OLE699')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE703', 'OLE260', 'OLE50', 'OLE703')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE707', 'OLE385', 'OLE17', 'OLE707')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE708', 'OLE212', 'OLE50', 'OLE708')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE710', 'OLE386', 'OLE19', 'OLE710')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE711', 'OLE387', 'OLE19', 'OLE711')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE712', 'OLE390', 'OLE54', 'OLE712')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE713', 'OLE391', 'OLE54', 'OLE713')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE714', 'OLE391', 'OLE32', 'OLE714')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE715', 'OLE392', 'OLE54', 'OLE715')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE716', 'OLE392', 'OLE32', 'OLE716')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE719', 'OLE260', 'OLE98', 'OLE719')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE721', 'OLE326', 'OLE98', 'OLE721')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE722', 'OLE212', 'OLE70', 'OLE722')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE74', 'OLE55', 'OLE37', 'OLE74')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE75', 'OLE56', 'OLE37', 'OLE75')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE77', 'OLE58', 'OLE44', 'OLE77')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE78', 'OLE59', 'OLE33', 'OLE78')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE79', 'OLE60', 'OLE39', 'OLE79')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80', 'OLE61', 'OLE37', 'OLE80')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE81', 'OLE61', 'OLE46', 'OLE81')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE82', 'OLE62', 'OLE37', 'OLE82')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE83', 'OLE62', 'OLE46', 'OLE83')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE84', 'OLE63', 'OLE37', 'OLE84')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE85', 'OLE63', 'OLE46', 'OLE85')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE86', 'OLE64', 'OLE37', 'OLE86')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE87', 'OLE64', 'OLE46', 'OLE87')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE89', 'OLE66', 'OLE16', 'OLE89')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE90', 'OLE67', 'OLE16', 'OLE90')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE93', 'OLE69', 'OLE11', 'OLE93')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE94', 'OLE70', 'OLE13', 'OLE94')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE95', 'OLE71', 'OLE44', 'OLE95')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE96', 'OLE72', 'OLE62', 'OLE96')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE97', 'OLE74', 'OLE45', 'OLE97')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE98', 'OLE75', 'OLE45', 'OLE98')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE99', 'OLE76', 'OLE17', 'OLE99')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE1500', 'OLE1500', 'OLE54', 'OLE1500')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE1501', 'OLE1501', 'OLE26', 'OLE1501')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLESEC6001-1', 'OLESEC6001', '51', 'OLESEC6001-1')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLESEC6002-1', 'OLESEC6002', '51', 'OLESEC6002-1')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLESEC6003-1', 'OLESEC6003', '51', 'OLESEC6003-1')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLESEC6004-1', 'OLESEC6004', '51', 'OLESEC6004-1')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLESEC6001-2', 'OLESEC6001', '44', 'OLESEC6001-2')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLESEC6002-2', 'OLESEC6002', '44', 'OLESEC6002-2')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLESEC6003-2', 'OLESEC6003', '44', 'OLESEC6003-2')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLESEC6004-2', 'OLESEC6004', '44', 'OLESEC6004-2')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLEPURAP6940-01', 'OLEPURAP6940', '26', 'OLEPURAP6940-01')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESYS7145a-01', 'OLESYS7145a', '54', 'OLESYS7145a-01')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESYS7145a-02', 'OLESYS7145a', 'OLESYS1', 'OLESYS7145a-02')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESYS7145b-01', 'OLESYS7145b', '54', 'OLESYS7145b-01')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESYS7145b-02', 'OLESYS7145b', '22', 'OLESYS7145b-02')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESYS7145b-03', 'OLESYS7145b', '16', 'OLESYS7145b-03')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESYS7145b-04', 'OLESYS7145b', 'OLESYS1', 'OLESYS7145b-04')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESYS6007-1', 'OLESYS6007', '12', 'OLESYS6007-1')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESYS6007-2', 'OLESYS6007', '45', 'OLESYS6007-2')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLEMI6886-RLPRM1', 'OLEMI6886-PRM1', 'OLEMI6886-ROLE', 'OLEMI6886-RLPRM1')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLEMI6886-RLPRM2', 'OLEMI6886-PRM2', 'OLEMI6886-ROLE', 'OLEMI6886-RLPRM2')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLEMI6886-RLPRM3', 'OLEMI6886-PRM3', 'OLEMI6886-ROLE', 'OLEMI6886-RLPRM3')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLEMI6886-RLPRM4', 'OLEMI6886-PRM3', '45', 'OLEMI6886-RLPRM4')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLECNTRB162-RLPRM1', 'OLECNTRB162-PRM', '56', 'OLECNTRB162-RLPRM1')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLECNTRB199-RP1', 'OLECNTRB199-P1', '51', 'OLECNTRB199-RP1')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLECNTRB199-RP2', 'OLECNTRB199-P2', '51', 'OLECNTRB199-RP2')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE50-2', '820', '54', 'OLE50-2')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE50-3', '820', '32', 'OLE50-3')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE50-4', '819', '54', 'OLE50-4')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE50-5', '819', '32', 'OLE50-5')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE50-6', '821', '54', 'OLE50-6')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE50-7', '821', '32', 'OLE50-7')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLEMI8342-RLPRM', 'OLEMI8342-PRM', '39', 'OLEMI8342-RLPRM')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLECNTRB65-RLPRM1', 'OLECNTRB65-PRM1', '45', 'OLECNTRB65-RLPRM1')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLEMI9598-RLPRM1', 'OLEMI9598-PRM1', '45', 'OLEMI9598-RLPRM1')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLEMI9598-RLPRM2', 'OLEMI9598-PRM2', '45', 'OLEMI9598-RLPRM2')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLEMI9598-RLPRM3', 'OLEMI9598-PRM3', '45', 'OLEMI9598-RLPRM3')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_ROLE_PERM_T', '2.0.5', '3:5bb3386e7eab2a4e610e03ffd8200191', 14)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_ROLE_RSP_T::ole::(Checksum: 3:2036c7a6d7a29ea61bc4e4ec81de4e6f)
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1000', 'OLE11', 'OLE1000', 'OLE44')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1001', 'OLE12', 'OLE1001', 'OLE46')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1002', 'OLE15', 'OLE1002', 'OLE50')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1004', 'OLE22', 'OLE1004', 'OLE71')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1006', 'OLE22', 'OLE1006', 'OLE81')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1009', 'OLE25', 'OLE1009', 'OLE78')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1011', 'OLE26', 'OLE1011', 'OLE48')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1012', 'OLE28', 'OLE1012', 'OLE4')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1015', 'OLE37', 'OLE1015', 'OLE26')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1017', 'OLE9', 'OLE1017', 'OLE29')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1024', 'OLE39', 'OLE1024', 'OLE31')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1037', 'OLE41', 'OLE1037', 'OLE12')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1062', 'OLE44', 'OLE1062', 'OLE87')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1063', 'OLE48', 'OLE1063', 'OLE7')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1065', 'OLE50', 'OLE1065', 'OLE49')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1066', 'OLE50', 'OLE1066', 'OLE74')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1067', 'OLE50', 'OLE1067', 'OLE80')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1068', 'OLE53', 'OLE1068', 'OLE26')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1070', 'OLE41', 'OLE1070', 'OLE29')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1077', 'OLE55', 'OLE1077', 'OLE85')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1079', '60', 'OLE1079', 'OLE91')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1082', 'OLE7', 'OLE1082', 'OLE1')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1083', 'OLE70', 'OLE1083', 'OLE51')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1089', 'OLE79', 'OLE1089', 'OLE76')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1092', 'OLE68', 'OLE1092', 'OLE83')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1099', 'OLE41', 'OLE1099', 'OLE99')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1107', 'OLE41', 'OLE1107', 'OLE107')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1108', 'OLE41', 'OLE1108', 'OLE108')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1109', 'OLE22', 'OLE1109', 'OLE109')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1113', '60', 'OLE1113', 'OLE113')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1118', 'OLE7', 'OLE1118', 'OLE117')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1121', 'OLE41', 'OLE1121', 'OLE120')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1124', 'OLE7', 'OLE1124', 'OLE123')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1125', 'OLE41', 'OLE1125', 'OLE124')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLEFP7037A', 'OLE55', 'OLEFP7037A', 'OLE126')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLEMI4553-4', 'OLEMI4553-2', 'OLEMI4553-4', 'OLE92')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_ROLE_RSP_T', '2.0.5', '3:2036c7a6d7a29ea61bc4e4ec81de4e6f', 15)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_KRIM_ROLE_RSP_ACTN_T::ole::(Checksum: 3:b2a85b907dfbb745a8b8d9ed3ef694ca)
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE101', '1', '*', 'OLE101', 'OLE1004')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE104', '1', '*', 'OLE104', 'OLE1066')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'A', 'Y', 'OLE106', '1', '*', 'OLE106', 'OLE1089')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE108', '1', '*', 'OLE108', 'OLE1009')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE110', '1', '*', 'OLE110', 'OLE1067')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'Y', 'OLE111', '1', '*', 'OLE111', 'OLE1006')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE113', '1', '*', 'OLE113', 'OLE1077')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE114', '1', '*', 'OLE114', 'OLE1062')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'Y', 'OLE115', '1', '*', 'OLE115', 'OLE1079')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'A', 'N', 'OLE124', '1', '*', 'OLE124', 'OLE1099')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'F', 'Y', 'OLE132', '1', '*', 'OLE132', 'OLE1107')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'F', 'Y', 'OLE133', '1', '*', 'OLE133', 'OLE1108')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE134', '1', '*', 'OLE134', 'OLE1109')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE138', '1', '*', 'OLE138', 'OLE1113')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'A', 'Y', 'OLE169', '1', '*', 'OLE169', 'OLE1121')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'F', 'Y', 'OLE201', '1', '*', 'OLE201', 'OLE1125')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'A', 'N', 'OLE34', '1', '*', 'OLE34', 'OLE1037')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE47', '1', '*', 'OLE47', 'OLE1015')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE48', '2', '*', 'OLE48', 'OLE1068')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'N', 'OLE52', '1', '*', 'OLE52', 'OLE1017')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'A', 'N', 'OLE53', '2', '*', 'OLE53', 'OLE1070')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE56', '1', '*', 'OLE56', 'OLE1024')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE74', '1', '*', 'OLE74', 'OLE1000')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE76', '1', '*', 'OLE76', 'OLE1001')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE78', '1', '*', 'OLE78', 'OLE1011')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE79', '1', '*', 'OLE79', 'OLE1065')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE80', '1', '*', 'OLE80', 'OLE1002')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE81', '1', '*', 'OLE81', 'OLE1083')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLEFP7037A', '1', '*', 'OLEFP7037A', 'OLEFP7037A')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_KRIM_ROLE_RSP_ACTN_T', '2.0.5', '3:b2a85b907dfbb745a8b8d9ed3ef694ca', 16)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_KRIM_PERM_T::ole::(Checksum: 3:73dd0cd69e08c05667f7088cd51f39b7)
INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Lookup of Requisition Document', 'Requisition LookUp', 'OLE-SELECT', 'OLE10004', 'OLE10004', '23', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Editing the Requisition Document', 'Requisition Edit', 'OLE-SELECT', 'OLE10005', 'OLE10005', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Cancelling Requisition Document', 'Requisition Cancel', 'OLE-SELECT', 'OLE10006', 'OLE10006', '14', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving the Requisition Document', 'Requisition Approve', 'OLE-SELECT', 'OLE10007', 'OLE10007', '8', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'can search, take & assign requisitions to selectors', 'Assign To Others', 'OLE-SELECT', 'OLE10008', 'OLE10008', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Editing PurchaseOrder Document', 'PurchaseOrder Edit', 'OLE-PURAP', 'OLE10009', 'OLE10009', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving PurchaseOrder on Condition', 'PurchaseOrder Approve', 'OLE-PURAP', 'OLE10010', 'OLE10010', '8', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Editing the Amend PurchaseOrder Document', 'PurchaseOrder Amendment Edit', 'OLE-PURAP', 'OLE10011', 'OLE10011', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Cancelling the Amend PurchaseOrder', 'PurchaseOrder Amendment Cancel', 'OLE-PURAP', 'OLE10012', 'OLE10012', '14', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Acknowledging for PurchaseOrder Amend Document', 'PurchaseOrder Amendment Acknowledge', 'OLE-PURAP', 'OLE10013', 'OLE10013', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Creating a PurchaseOrder Amendment Document', 'PurchaseOrder Amendment Creation', 'OLE-PURAP', 'OLE10014', 'OLE10014', '42', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Creating a new Vendor', 'Create Vendor', 'OLE-VND', 'OLE10015', 'OLE10015', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving the vendor which is new', 'Vendor Approval', 'OLE-VND', 'OLE10016', 'OLE10016', '8', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Editing the Vendor which is created', 'Edit Vendor', 'OLE-VND', 'OLE10017', 'OLE10017', '16', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Creating Purchase Order', 'PurchaseOrder Creation', 'OLE-PURAP', 'OLE10018', 'OLE10018', '42', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Cancelling PurchaseOrder Document', 'PurchaseOrder Cancel', 'OLE-PURAP', 'OLE10019', 'OLE10019', '14', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving PurchaseOrder Document depending on amount', 'PurchaseOrder Approve Conditional', 'OLE-PURAP', 'OLE10020', 'OLE10020', '8', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to modify Discount on Vendor Document', 'Edit Discounts', 'OLE-VND', 'OLE10021', 'OLE10021', '26', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to reloads ,routes the Document for action', 'Load Error', 'OLE-SELECT', 'OLE10022', 'OLE10022', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to check for Duplicate Records', 'Load Duplicate Check', 'OLE-SELECT', 'OLE10023', 'OLE10023', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to check the Funds and routes the reports to corresponding selectors', 'Load Fund Check', 'OLE-SELECT', 'OLE10024', 'OLE10024', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Transmitting the PurchaseOrder Document', 'Transmit PO', 'OLE-PURAP', 'OLE10025', 'OLE10025', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to Retransmit the PurchaseOrder Document', 'Retransmit PurchaseOrder', 'OLE-PURAP', 'OLE10026', 'OLE10026', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes Users to add New Users', 'Setup New Users', 'OLE-SELECT', 'OLE10027', 'OLE10027', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to send FYI to ACQ  Manager', 'Condition on Line Item', 'OLE-SELECT', 'OLE10028', 'OLE10028', '8', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes Users to send FYI to ACQ Manager when a new Vendor is Created', 'New Vendor', 'OLE-SELECT', 'OLE10029', 'OLE10029', '8', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes user to route the Document if no Selector identifed', 'No Selector in Load', 'OLE-SELECT', 'OLE10030', 'OLE10030', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'System Generated', 'Requisition Auto', 'OLE-SELECT', 'OLE10031', 'OLE10031', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes Users to send to any Selectors', 'Staff Pickup', 'OLE-SELECT', 'OLE10032', 'OLE10032', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to Inquire Records', 'Inquire Into Records', 'OLE-SELECT', 'OLE10035', 'OLE10035', '24', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open Vendor Documents.', 'Open Document', 'OLE-SELECT', 'OLE10036', 'OLE10036', '40', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Financial System Documents.', 'Initiate OLE Documents', 'OLE-SELECT', 'OLE10041', 'OLE10041', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to Create Vendor Document', 'Initiate Vendor Document', 'OLE-SELECT', 'OLE10042', 'OLE10042', '42', '6')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to Edit Vendor Document', 'Edit Vendor Document', 'OLE-SELECT', 'OLE10043', 'OLE10043', '42', '3')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Receiving Line Items Transactional Documents.', 'Initiate Receiving Line Items Document', 'OLE-SELECT', 'OLE10044', 'OLE10044', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission for Receiving Line Items Transactional documents prior to the document being submitted.', 'Edit Receiving Line Items Document', 'OLE-SELECT', 'OLE10045', 'OLE10045', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Blanket Approval button on OLE Receiving Line Item Documents.', 'Blanket Approve Receiving Line Items  Document', 'OLE-SELECT', 'OLE10046', 'OLE10046', '4', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open Receiving Line Items document.', 'Open Receiving Line Items Document', 'OLE-SELECT', 'OLE10047', 'OLE10047', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Blanket Approval button on OLE Receiving Line Item Documents.', 'Blanket Approve RCVC Document', 'OLE-SELECT', 'OLE10048', 'OLE10048', '4', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('N', 'Authorizes the initiation of the Payment Request Document.', 'Initiate Payment Request Document', 'OLE-SELECT', 'OLE10049', 'OLE10049', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users who can edit Payment Request Documents that are in ENROUTE status.', 'Edit ENROUTE Payment Request Document', 'OLE-SELECT', 'OLE10050', 'OLE10050', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users who can edit Payment Request Documents that are in ENROUTE status.', 'Edit PROCESSED Payment Request Document', 'OLE-SELECT', 'OLE10051', 'OLE10051', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users who can edit Payment Request Documents that are in ENROUTE status.', 'Edit FINAL Payment Request Document', 'OLE-SELECT', 'OLE10052', 'OLE10052', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to modify the Source accounting lines on documents answering to the parent document Accounts PayableTransactional Document when a document is at the Account Node of routing.', 'Modify PREQ Account Accounting Lines', 'OLE-SELECT', 'OLE10053', 'OLE10053', 'OLE41', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users authorized to take the Request Cancel action on Payment Request documents.', 'Cancel Payment Request', 'OLE-SELECT', 'OLE10054', 'OLE10054', '31', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the User to add new Line Item while receiving PO', 'Add New Line Item', 'OLE-SELECT', 'OLE10055', 'OLE10055', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the User Close PO', 'Close Purchase Order', 'OLE-SELECT', 'OLE10056', 'OLE10056', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission Payment Request document prior to the document being submitted.', 'Edit Payment Request Document', 'OLE-SELECT', 'OLE10057', 'OLE10057', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Receiving Line Items Correction Transactional Documents.', 'Initiate Recv Line Item Correction Document', 'OLE-SELECT', 'OLE10058', 'OLE10058', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission for Receiving Line Items Correction Transactional documents prior to the document being submitted.', 'Edit Recv Line Item Correction Document', 'OLE-SELECT', 'OLE10059', 'OLE10059', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Editing the Firm Type Requisition Document', 'Firm Type Requisition Edit', 'OLE-SELECT', 'OLE10060', 'OLE10060', '1', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Editing Firm Type PurchaseOrder Document', 'Firm Type PurchaseOrder Edit', 'OLE-SELECT', 'OLE10061', 'OLE10061', '1', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Editing the Firm Tpye Amend PurchaseOrder Document', 'Firm Type PurchaseOrder Amendment Edit', 'OLE-SELECT', 'OLE10062', 'OLE10062', '16', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission for own Requisition document', 'Edit Own Requisition Document', 'OLE-SELECT', 'OLE10063', 'OLE10063', '1', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission for own or Assigned Requisition document', 'Edit Own Assigned Requisition Document', 'OLE-SELECT', 'OLE10064', 'OLE10064', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission for the Purchase Order Void document prior to the document being submitted.', 'Edit Void Document', 'OLE-SELECT', 'OLE10065', 'OLE10065', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission for the Purchase Order Split document prior to the document being submitted.', 'Edit Split Document', 'OLE-SELECT', 'OLE10066', 'OLE10066', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Edit permission for the Purchase Order Reopen document prior to the document being submitted.', 'Edit Reopen Document', 'OLE-SELECT', 'OLE10067', 'OLE10067', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to take the Print action on a Purchase Order.', 'Print Action', 'OLE-SELECT', 'OLE10068', 'OLE10068', '16', '3')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can print a Purchase Order document.', 'Print Purchase Order', 'OLE-SELECT', 'OLE10069', 'OLE10069', '16', '3')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Users who can preview a Purchase Order document before printing it.', 'Preview Purchase Order', 'OLE-SELECT', 'OLE10070', 'OLE10070', '16', '3')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Editing the Other Order Type Requisition Document', 'Other Type Requisition Edit', 'OLE-SELECT', 'OLE10071', 'OLE10071', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to access and upload the ordinary File.', 'Upload ordInputFileType', 'OLE-PURAP', 'OLE10100', 'OLE10100', '33', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the User to add Search Load Summary', 'Search Load Summary', 'OLE-SELECT', 'OLE10108', 'OLE10108', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the User to add View Load Summary', 'View Load Summary', 'OLE-SELECT', 'OLE10109', 'OLE10109', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Editing the Other Order Type Purchase Order Document', 'Other Type PurchaseOrder Edit', 'OLE-SELECT', 'OLE10110', 'OLE10110', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Editing the Other Order Type Purchase Order Amendment Document', 'Other Type PurchaseOrder Amendment Edit', 'OLE-SELECT', 'OLE10111', 'OLE10111', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Edit Requisition Document', 'OLE-SELECT', 'OLE10112', 'OLE10112', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the User to add Edit BibEditor', 'Edit BibEditor', 'OLE-SELECT', 'OLE10113', 'OLE10113', '1', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the User to Create BibEditor', 'Create BibEditor', 'OLE-SELECT', 'OLE10114', 'OLE10114', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Order Queue Documents.', 'Initiate Order Queue Document', 'OLE-PURAP', 'OLE10115', 'OLE10115', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Receiving Documents.', 'Initiate Receiving Document', 'OLE-SELECT', 'OLE10116', 'OLE10116', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the edit of Receiving Documents.', 'Edit Receiving Document', 'OLE-SELECT', 'OLE10117', 'OLE10117', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the edit of Receiving Queue Documents.', 'Edit Receiving Queue Document', 'OLE-SELECT', 'OLE10118', 'OLE10118', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes the initiation of Receiving Queue Documents.', 'Initiate Receiving Queue Document', 'OLE-SELECT', 'OLE10119', 'OLE10119', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Assign own Requisition', 'Assign own Requisition', 'OLE-SELECT', 'OLE10120', 'OLE10120', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Blanket Approval button on OLE Vendor Documents.', 'Blanket Approve Vendor Document', 'OLE-SELECT', 'OLE10121', 'OLE10121', '4', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows access to the Blanket Approval button on POA Documents.', 'Blanket Approve PO Amendment Document', 'OLE-SELECT', 'OLE10122', 'OLE10122', '4', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Payment Method Document', 'Initiate Payment Method', 'OLE-SELECT', 'OLE80000', 'OLE80000', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Address Type Document', 'Initiate Address Type', 'OLE-VND', 'OLE80002', 'OLE80002', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Campus Parameter', 'Initiate Campus parameter', 'OLE-VND', 'OLE80004', 'OLE80004', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Contact Type', 'Initiate Contact Type', 'OLE-VND', 'OLE80006', 'OLE80006', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating OwnerShipType Document', 'Initiate OwnerShipType Document', 'OLE-VND', 'OLE80008', 'OLE80008', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating OwnerShipType Category Document', 'Initiate OwnerShipType Category Document', 'OLE-VND', 'OLE80011', 'OLE80011', '10', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Payment Terms Type Document', 'Initiate PaymentTermsType Document', 'OLE-VND', 'OLE80014', 'OLE80014', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating ShipppingPaymentTerm Document', 'Initiate ShippingPaymentTerm Document', 'OLE-VND', 'OLE80016', 'OLE80016', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating phoneType Document', 'Initiate PhoneType Document', 'OLE-VND', 'OLE80018', 'OLE80018', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating ShippingSpecialConditiion Document', 'Initiate ShippingSpecialConditiion Document', 'OLE-VND', 'OLE80020', 'OLE80020', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating ShippingTitle Document', 'Initiate ShippingTitle Document', 'OLE-VND', 'OLE80024', 'OLE80024', '10', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating AliasType Document', 'Initiate AliasType Document', 'OLE-VND', 'OLE80026', 'OLE80026', '10', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating BillingAddress Document', 'Initiate BillingAddress Document', 'OLE-PURAP', 'OLE80028', 'OLE80028', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Carrier Document', 'Initiate Carrier Document', 'OLE-PURAP', 'OLE80030', 'OLE80030', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Category Document', 'Initiate Category Document', 'OLE-PURAP', 'OLE80032', 'OLE80032', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating ExceptionType Document', 'Initiate ExceptionType  Document', 'OLE-PURAP', 'OLE80034', 'OLE80034', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating FormatType  Document', 'Initiate FormatType  Document', 'OLE-PURAP', 'OLE80036', 'OLE80036', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating FundingSource  Document', 'Initiate FundingSource  Document', 'OLE-PURAP', 'OLE80038', 'OLE80038', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating InvoiceType  Document', 'Initiate InvoiceType  Document', 'OLE-PURAP', 'OLE80040', 'OLE80040', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating InvoiceSubType  Document', 'Initiate InvoiceSubType  Document', 'OLE-PURAP', 'OLE80042', 'OLE80042', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating ItemPriceSource  Document', 'Initiate ItemPriceSource  Document', 'OLE-PURAP', 'OLE80044', 'OLE80044', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating NoteType Document', 'Initiate NoteType Document', 'OLE-PURAP', 'OLE80047', 'OLE80047', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating PurchaseOrderType Document', 'Initiate PurchaseOrderType Document', 'OLE-PURAP', 'OLE80050', 'OLE80050', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating OrderItemStatus Document', 'Initiate OrderItemStatus Document', 'OLE-PURAP', 'OLE80052', 'OLE80052', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating ReceivingAddress Document', 'Initiate ReceivingAddress Document', 'OLE-PURAP', 'OLE80054', 'OLE80054', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating RequestSourceType Document', 'Initiate RequestSourceType Document', 'OLE-PURAP', 'OLE80056', 'OLE80056', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating ReceivingThreshHold Document', 'Initiate ReceivingThreshHold Document', 'OLE-PURAP', 'OLE80058', 'OLE80058', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Requestor Document', 'Initiate Requestor Document', 'OLE-SELECT', 'OLE80061', 'OLE80061', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating  ContractManager Document', 'Initiate ContractManager Document', 'OLE-VND', 'OLE80063', 'OLE80063', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating  SourceCode Document', 'Initiate SourceCode Document', 'OLE-VND', 'OLE80065', 'OLE80065', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating  CommodityCode Document', 'Initiate CommodityCode Document', 'OLE-VND', 'OLE80067', 'OLE80067', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating  SupplierDiversity Document', 'Initiate SupplierDiversity Document', 'OLE-VND', 'OLE80069', 'OLE80069', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Vendor Inactive ReasonDocument', 'Initiate VendorInactiveReason Document', 'OLE-VND', 'OLE80071', 'OLE80071', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Vendor Transmission Format Document', 'Initiate Vendor Transmission Format Document', 'OLE-VND', 'OLE80073', 'OLE80073', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Vendor Transmission Type Document', 'Initiate Vendor Transmission Type Document', 'OLE-VND', 'OLE80075', 'OLE80075', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating Vendor Type Document', 'Initiate Vendor Type Document', 'OLE-VND', 'OLE80077', 'OLE80077', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating CreditMemoStatus Document', 'Initiate CreditMemoStatus Document', 'OLE-PURAP', 'OLE80080', 'OLE80080', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating CurrencyType Document', 'Initiate CurrencyType Document', 'OLE-PURAP', 'OLE80082', 'OLE80082', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating DeliveryRequiredDateReason Document', 'Initiate DeliveryRequiredDateReason Document', 'OLE-PURAP', 'OLE80084', 'OLE80084', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating ElectronicInvoiceItemMapping Document', 'Initiate ElectronicInvoiceItemMapping Document', 'OLE-PURAP', 'OLE80087', 'OLE80087', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating ItemReasonAdded Document', 'Initiate ItemReasonAdded Document', 'OLE-PURAP', 'OLE80089', 'OLE80089', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating ItemType Document', 'Initiate ItemType Document', 'OLE-PURAP', 'OLE80091', 'OLE80091', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating LineItemReceivingStatus Document', 'Initiate LineItemReceivingStatus Document', 'OLE-PURAP', 'OLE80094', 'OLE80094', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating POMethodTransmission Document', 'Initiate POMethodTransmission Document', 'OLE-PURAP', 'OLE80097', 'OLE80097', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating NegativePaymentRequestLimitApproval Document', 'Initiate NegativePaymentRequestLimitApproval Document', 'OLE-PURAP', 'OLE80099', 'OLE80099', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating OrganisationParameter Document', 'Initiate OrganisationParameter Document', 'OLE-PURAP', 'OLE80101', 'OLE80101', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating AutoApproveExclude Document', 'Initiate AutoApproveExclude Document', 'OLE-PURAP', 'OLE80103', 'OLE80103', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating PaymentRequestStatus Document', 'Initiate PaymentRequestStatus Document', 'OLE-PURAP', 'OLE80105', 'OLE80105', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating PurchaseOrderContractLanguage Document', 'Initiate PurchaseOrderContractLanguage Document', 'OLE-PURAP', 'OLE80107', 'OLE80107', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating PurchaseOrderQuoteLanguage Document', 'Initiate PurchaseOrderQuoteLanguage Document', 'OLE-PURAP', 'OLE80110', 'OLE80110', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating PurchaseOrderQuoteList Document', 'Initiate PurchaseOrderQuoteList Document', 'OLE-PURAP', 'OLE80111', 'OLE80111', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating PurchaseOrderQuoteStatus Document', 'Initiate PurchaseOrderQuoteStatus Document', 'OLE-PURAP', 'OLE80113', 'OLE80113', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating PurchaseOrderStatus Document', 'Initiate PurchaseOrderStatus Document', 'OLE-PURAP', 'OLE80115', 'OLE80115', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating PurchaseOrderVendorChoice Document', 'Initiate PurchaseOrderVendorChoice Document', 'OLE-PURAP', 'OLE80117', 'OLE80117', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating RecurringPaymentFrequency Document', 'Initiate RecurringPaymentFrequency Document', 'OLE-PURAP', 'OLE80120', 'OLE80120', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating RecurringPaymentType Document', 'Initiate RecurringPaymentType Document', 'OLE-PURAP', 'OLE80122', 'OLE80122', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating RequisitionSource Document', 'Initiate RequisitionSource Document', 'OLE-PURAP', 'OLE80124', 'OLE80124', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating RequisitionStatus Document', 'Initiate RequisitionStatus Document', 'OLE-PURAP', 'OLE80126', 'OLE80126', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating SensitiveData Document', 'Initiate SensitiveData Document', 'OLE-PURAP', 'OLE80128', 'OLE80128', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating VendorStipulation Document', 'Initiate VendorStipulation Document', 'OLE-PURAP', 'OLE80130', 'OLE80130', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiating RequestorType Document', 'Initiate RequestorType Document', 'OLE-SELECT', 'OLE80132', 'OLE80132', '10', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', ' Initiating Load Summary Document', 'Initiate Load Summary Document', 'OLE-SELECT', 'OLE80134', 'OLE80134', '10', '2')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Creating a RCVL document.', 'Initiate RCVL Document', 'OLE-SELECT', 'OLE80135', 'OLE80135', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to edit RCVL documents that are in ENROUTE status.', 'Edit RCVL Document', 'OLE-SELECT', 'OLE80136', 'OLE80136', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Creating a RCVL document via receiving queue search.', 'Initiate Document via OLE_QUEUESEARCH', 'OLE-SELECT', 'OLE80138', 'OLE80138', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to Create Vendor Document', 'Initiate vendor Document', 'OLE-SELECT', 'OLE80139', 'OLE80139', '42', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to Edit Vendor Document', 'Edit vendor Document', 'OLE-SELECT', 'OLE80140', 'OLE80140', '42', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to Edit Vendor Document', 'Create Vendor Division', 'OLE-SELECT', 'OLE80141', 'OLE80141', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to deactivate Vendor Document', 'Deactivate Vendor', 'OLE-SELECT', 'OLE80142', 'OLE80142', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to create Chart document.', 'Initiate Chart Document', 'OLE-SELECT', 'OLE80143', 'OLE80143', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to create account document.', 'Initiate Account Document', 'OLE-SELECT', 'OLE80144', 'OLE80144', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to create Object code document.', 'Initiate Object Code Document', 'OLE-SELECT', 'OLE80145', 'OLE80145', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to create Organization document.', 'Initiate Organization Document', 'OLE-SELECT', 'OLE80146', 'OLE80146', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to edit Chart document.', 'Edit Chart Document', 'OLE-SELECT', 'OLE80147', 'OLE80147', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to edit account document.', 'Edit Account Document', 'OLE-SELECT', 'OLE80148', 'OLE80148', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to edit Object code document.', 'Edit Object Code Document', 'OLE-SELECT', 'OLE80149', 'OLE80149', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to edit Organization document.', 'Edit Organization Document', 'OLE-SELECT', 'OLE80150', 'OLE80150', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'This Permission is used to delete the unapproved Payment Request Documents.', 'Delete UnApproved PREQ Document', 'OLE-SELECT', 'OLE80155', 'OLE80155', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'This Permission is used to delete the approved Payment Request Documents.', 'Delete Approved PREQ Document', 'OLE-SELECT', 'OLE80156', 'OLE80156', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'This Permission is for Initiating the Distribution of Income and Expense Document.', 'Initiate Document OLE_DI', 'OLE-SELECT', 'OLE80157', 'OLE80157', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'This Permission is for Initiating the Disbursement Voucher Document.', 'Initiate Document OLE_DV', 'OLE-SELECT', 'OLE80158', 'OLE80158', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'This Permission is for the Disbursement Voucher document in the PreRoute state.', 'Edit Document OLE_DV PreRoute', 'OLE-SELECT', 'OLE80159', 'OLE80159', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'This Permission is for the Disbursement Voucher Document which is in Enroute state.', 'Edit Document OLE_DV ENROUTE', 'OLE-SELECT', 'OLE80160', 'OLE80160', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'This Permission is for the Credit Memo which is in PreRoute state.', 'Edit Document OLE_CM PreRoute', 'OLE-SELECT', 'OLE80161', 'OLE80161', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Initiate Permission of the General Error Correction Document.', 'Initiate Document OLE_GEC', 'OLE-SELECT', 'OLE80162', 'OLE80162', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to edit Vendor Linking Number.', 'Edit Vendor Linking Number', 'OLE-SELECT', 'OLE80163', 'OLE80163', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to create Budget Adjustment document.', 'Initiate Budget Document', 'OLE-SELECT', 'OLE80164', 'OLE80164', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to edit Budget Adjustment document.', 'Edit Budget Document', 'OLE-SELECT', 'OLE80165', 'OLE80165', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to create Transfer Fund document.', 'Initiate Transfer Fund Document', 'OLE-SELECT', 'OLE80166', 'OLE80166', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to edit Transfer Fund document.', 'Edit Transfer Fund Document', 'OLE-SELECT', 'OLE80167', 'OLE80167', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to create Deposit Adjustment document.', 'Initiate Deposit Document', 'OLE-SELECT', 'OLE80168', 'OLE80168', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows to edit Deposit Adjustment document.', 'Edit Deposit Document', 'OLE-SELECT', 'OLE80169', 'OLE80169', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to edit restriction on accounts.', 'Edit Restriction', 'OLE-SELECT', 'OLE80170', 'OLE80170', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to edit Sufficient fund check on accounts.', 'Edit Sufficient fund check', 'OLE-SELECT', 'OLE80171', 'OLE80171', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allows users to upload budget file.', 'Upload Budget', 'OLE-SELECT', 'OLE80172', 'OLE80172', '1', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit BillingAddress document.', 'Edit BillingAddress Document', 'OLE-SELECT', 'OLE80173', 'OLE80173', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy BillingAddress document.', 'Copy BillingAddress Document', 'OLE-SELECT', 'OLE80174', 'OLE80174', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open BillingAddress document.', 'Open BillingAddress Document', 'OLE-SELECT', 'OLE80175', 'OLE80175', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create BillingAddress document.', 'Initiate BillingAddress Document', 'OLE-SELECT', 'OLE80176', 'OLE80176', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit Carrier document.', 'Edit Carrier Document', 'OLE-SELECT', 'OLE80177', 'OLE80177', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy Carrier document.', 'Copy Carrier Document', 'OLE-SELECT', 'OLE80178', 'OLE80178', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open Carrier document.', 'Open Carrier Document', 'OLE-SELECT', 'OLE80179', 'OLE80179', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create Carrier document.', 'Initiate Carrier Document', 'OLE-SELECT', 'OLE80180', 'OLE80180', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit Category document.', 'Edit Category Document', 'OLE-SELECT', 'OLE80181', 'OLE80181', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy Category document.', 'Copy Category Document', 'OLE-SELECT', 'OLE80182', 'OLE80182', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open Category document.', 'Open Category Document', 'OLE-SELECT', 'OLE80183', 'OLE80183', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create Category document.', 'Initiate Category Document', 'OLE-SELECT', 'OLE80184', 'OLE80184', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit CreditMemo Status document.', 'Edit CreditMemo Status Document', 'OLE-SELECT', 'OLE80185', 'OLE80185', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy CreditMemo Status document.', 'Copy CreditMemo Status Document', 'OLE-SELECT', 'OLE80186', 'OLE80186', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open CreditMemo Status document.', 'Open CreditMemo Status Document', 'OLE-SELECT', 'OLE80187', 'OLE80187', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create CreditMemo Status document.', 'Initiate CreditMemo Status Document', 'OLE-SELECT', 'OLE80188', 'OLE80188', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit Delivery Required Date Reason document.', 'Edit Delivery Required Date Reason Document', 'OLE-SELECT', 'OLE80189', 'OLE80189', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy Delivery Required Date Reason document.', 'Copy Delivery Required Date Reason Document', 'OLE-SELECT', 'OLE80190', 'OLE80190', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open Delivery Required Date Reason document.', 'Open Delivery Required Date Reason Document', 'OLE-SELECT', 'OLE80191', 'OLE80191', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create Delivery Required Date Reason document.', 'Initiate Delivery Required Date Reason Document', 'OLE-SELECT', 'OLE80192', 'OLE80192', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit Electronic Invoice Item Mapping document.', 'Edit Electronic Invoice Item Mapping Document', 'OLE-SELECT', 'OLE80193', 'OLE80193', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy Electronic Invoice Item Mapping document.', 'Copy Electronic Invoice Item Mapping Document', 'OLE-SELECT', 'OLE80194', 'OLE80194', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open Electronic Invoice Item Mapping document.', 'Open Electronic Invoice Item Mapping Document', 'OLE-SELECT', 'OLE80195', 'OLE80195', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create Electronic Invoice Item Mapping document.', 'Initiate Electronic Invoice Item Mapping Document', 'OLE-SELECT', 'OLE80196', 'OLE80196', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ExceptionType document.', 'Edit ExceptionType Document', 'OLE-SELECT', 'OLE80197', 'OLE80197', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ExceptionType document.', 'Copy ExceptionType Document', 'OLE-SELECT', 'OLE80198', 'OLE80198', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ExceptionType document.', 'Open ExceptionType Document', 'OLE-SELECT', 'OLE80199', 'OLE80199', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ExceptionType document.', 'Initiate ExceptionType Document', 'OLE-SELECT', 'OLE80200', 'OLE80200', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit Format document.', 'Edit Format Document', 'OLE-SELECT', 'OLE80201', 'OLE80201', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy Format document.', 'Copy Format Document', 'OLE-SELECT', 'OLE80202', 'OLE80202', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open Format document.', 'Open Format Document', 'OLE-SELECT', 'OLE80203', 'OLE80203', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create Format document.', 'Initiate Format Document', 'OLE-SELECT', 'OLE80204', 'OLE80204', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit FundingSource document.', 'Edit FundingSource Document', 'OLE-SELECT', 'OLE80205', 'OLE80205', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy FundingSource document.', 'Copy FundingSource Document', 'OLE-SELECT', 'OLE80206', 'OLE80206', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open FundingSource document.', 'Open FundingSource Document', 'OLE-SELECT', 'OLE80207', 'OLE80207', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create FundingSource document.', 'Initiate FundingSource Document', 'OLE-SELECT', 'OLE80208', 'OLE80208', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit InvoiceSubType document.', 'Edit InvoiceSubType Document', 'OLE-SELECT', 'OLE80209', 'OLE80209', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy InvoiceSubType document.', 'Copy InvoiceSubType Document', 'OLE-SELECT', 'OLE80210', 'OLE80210', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open InvoiceSubType document.', 'Open InvoiceSubType Document', 'OLE-SELECT', 'OLE80211', 'OLE80211', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create InvoiceSubType document.', 'Initiate InvoiceSubType Document', 'OLE-SELECT', 'OLE80212', 'OLE80212', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit InvoiceType document.', 'Edit InvoiceType Document', 'OLE-SELECT', 'OLE80213', 'OLE80213', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy InvoiceType document.', 'Copy InvoiceType Document', 'OLE-SELECT', 'OLE80214', 'OLE80214', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open InvoiceType document.', 'Open InvoiceType Document', 'OLE-SELECT', 'OLE80215', 'OLE80215', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create InvoiceType document.', 'Initiate InvoiceType Document', 'OLE-SELECT', 'OLE80216', 'OLE80216', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ItemPriceSource document.', 'Edit ItemPriceSource Document', 'OLE-SELECT', 'OLE80217', 'OLE80217', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ItemPriceSource document.', 'Copy ItemPriceSource Document', 'OLE-SELECT', 'OLE80218', 'OLE80218', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ItemPriceSource document.', 'Open ItemPriceSource Document', 'OLE-SELECT', 'OLE80219', 'OLE80219', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ItemPriceSource document.', 'Initiate ItemPriceSource Document', 'OLE-SELECT', 'OLE80220', 'OLE80220', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ItemReasonAdded document.', 'Edit ItemReasonAdded Document', 'OLE-SELECT', 'OLE80221', 'OLE80221', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ItemReasonAdded document.', 'Copy ItemReasonAdded Document', 'OLE-SELECT', 'OLE80222', 'OLE80222', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ItemReasonAdded document.', 'Open ItemReasonAdded Document', 'OLE-SELECT', 'OLE80223', 'OLE80223', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ItemReasonAdded document.', 'Initiate ItemReasonAdded Document', 'OLE-SELECT', 'OLE80224', 'OLE80224', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ItemType document.', 'Edit ItemType Document', 'OLE-SELECT', 'OLE80225', 'OLE80225', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ItemType document.', 'Copy ItemType Document', 'OLE-SELECT', 'OLE80226', 'OLE80226', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ItemType document.', 'Open ItemType Document', 'OLE-SELECT', 'OLE80227', 'OLE80227', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ItemType document.', 'Initiate ItemType Document', 'OLE-SELECT', 'OLE80228', 'OLE80228', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit LineItemReceivingStatus document.', 'Edit LineItemReceivingStatus Document', 'OLE-SELECT', 'OLE80229', 'OLE80229', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy LineItemReceivingStatus document.', 'Copy LineItemReceivingStatus Document', 'OLE-SELECT', 'OLE80230', 'OLE80230', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open LineItemReceivingStatus document.', 'Open LineItemReceivingStatus Document', 'OLE-SELECT', 'OLE80231', 'OLE80231', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create LineItemReceivingStatus document.', 'Initiate LineItemReceivingStatus Document', 'OLE-SELECT', 'OLE80232', 'OLE80232', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit MethodOfPOTransmission document.', 'Edit MethodOfPOTransmission Document', 'OLE-SELECT', 'OLE80233', 'OLE80233', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy MethodOfPOTransmission document.', 'Copy MethodOfPOTransmission Document', 'OLE-SELECT', 'OLE80234', 'OLE80234', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open MethodOfPOTransmission document.', 'Open MethodOfPOTransmission Document', 'OLE-SELECT', 'OLE80235', 'OLE80235', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create MethodOfPOTransmission document.', 'Initiate MethodOfPOTransmission Document', 'OLE-SELECT', 'OLE80236', 'OLE80236', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit  NegativePaymentRequestApproval document.', 'Edit  NegativePaymentRequestApproval Document', 'OLE-SELECT', 'OLE80237', 'OLE80237', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy  NegativePaymentRequestApproval document.', 'Copy  NegativePaymentRequestApproval Document', 'OLE-SELECT', 'OLE80238', 'OLE80238', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open  NegativePaymentRequestApproval document.', 'Open  NegativePaymentRequestApproval Document', 'OLE-SELECT', 'OLE80239', 'OLE80239', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create  NegativePaymentRequestApproval document.', 'Initiate  NegativePaymentRequestApproval Document', 'OLE-SELECT', 'OLE80240', 'OLE80240', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit NoteType document.', 'Edit NoteType Document', 'OLE-SELECT', 'OLE80241', 'OLE80241', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy NoteType document.', 'Copy NoteType Document', 'OLE-SELECT', 'OLE80242', 'OLE80242', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open NoteType document.', 'Open NoteType Document', 'OLE-SELECT', 'OLE80243', 'OLE80243', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create NoteType document.', 'Initiate NoteType Document', 'OLE-SELECT', 'OLE80244', 'OLE80244', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit OrganizationParameter document.', 'Edit OrganizationParameter Document', 'OLE-SELECT', 'OLE80245', 'OLE80245', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy OrganizationParameter document.', 'Copy OrganizationParameter Document', 'OLE-SELECT', 'OLE80246', 'OLE80246', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open OrganizationParameter document.', 'Open OrganizationParameter Document', 'OLE-SELECT', 'OLE80247', 'OLE80247', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create OrganizationParameter document.', 'Initiate OrganizationParameter Document', 'OLE-SELECT', 'OLE80248', 'OLE80248', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit OrderType document.', 'Edit OrderType Document', 'OLE-SELECT', 'OLE80249', 'OLE80249', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy OrderType document.', 'Copy OrderType Document', 'OLE-SELECT', 'OLE80250', 'OLE80250', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open OrderType document.', 'Open OrderType Document', 'OLE-SELECT', 'OLE80251', 'OLE80251', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create OrderType document.', 'Initiate OrderType Document', 'OLE-SELECT', 'OLE80252', 'OLE80252', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit PaymentMethod document.', 'Edit PaymentMethod Document', 'OLE-SELECT', 'OLE80253', 'OLE80253', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy PaymentMethod document.', 'Copy PaymentMethod Document', 'OLE-SELECT', 'OLE80254', 'OLE80254', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open PaymentMethod document.', 'Open PaymentMethod Document', 'OLE-SELECT', 'OLE80255', 'OLE80255', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create PaymentMethod document.', 'Initiate PaymentMethod Document', 'OLE-SELECT', 'OLE80256', 'OLE80256', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit PaymentRequestAutoApproveEx document.', 'Edit PaymentRequestAutoApproveEx Document', 'OLE-SELECT', 'OLE80257', 'OLE80257', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy PaymentRequestAutoApproveEx document.', 'Copy PaymentRequestAutoApproveEx Document', 'OLE-SELECT', 'OLE80258', 'OLE80258', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open PaymentRequestAutoApproveEx document.', 'Open PaymentRequestAutoApproveEx Document', 'OLE-SELECT', 'OLE80259', 'OLE80259', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create PaymentRequestAutoApproveEx document.', 'Initiate PaymentRequestAutoApproveEx Document', 'OLE-SELECT', 'OLE80260', 'OLE80260', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit PaymentRequestStatus document.', 'Edit PaymentRequestStatus Document', 'OLE-SELECT', 'OLE80261', 'OLE80261', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy PaymentRequestStatus document.', 'Copy PaymentRequestStatus Document', 'OLE-SELECT', 'OLE80262', 'OLE80262', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open PaymentRequestStatus document.', 'Open PaymentRequestStatus Document', 'OLE-SELECT', 'OLE80263', 'OLE80263', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create PaymentRequestStatus document.', 'Initiate PaymentRequestStatus Document', 'OLE-SELECT', 'OLE80264', 'OLE80264', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit PurchaseOrderContractLanguage document.', 'Edit PurchaseOrderContractLanguage Document', 'OLE-SELECT', 'OLE80265', 'OLE80265', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy PurchaseOrderContractLanguage document.', 'Copy PurchaseOrderContractLanguage Document', 'OLE-SELECT', 'OLE80266', 'OLE80266', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open PurchaseOrderContractLanguage document.', 'Open PurchaseOrderContractLanguage Document', 'OLE-SELECT', 'OLE80267', 'OLE80267', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create PurchaseOrderContractLanguage document.', 'Initiate PurchaseOrderContractLanguage Document', 'OLE-SELECT', 'OLE80268', 'OLE80268', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit PurchaseOrderItemStatus document.', 'Edit PurchaseOrderItemStatus Document', 'OLE-SELECT', 'OLE80269', 'OLE80269', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy PurchaseOrderItemStatus document.', 'Copy PurchaseOrderItemStatus Document', 'OLE-SELECT', 'OLE80270', 'OLE80270', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open PurchaseOrderItemStatus document.', 'Open PurchaseOrderItemStatus Document', 'OLE-SELECT', 'OLE80271', 'OLE80271', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create PurchaseOrderItemStatus document.', 'Initiate PurchaseOrderItemStatus Document', 'OLE-SELECT', 'OLE80272', 'OLE80272', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit PurchaseOrderQuoteLanguage document.', 'Edit PurchaseOrderQuoteLanguage Document', 'OLE-SELECT', 'OLE80273', 'OLE80273', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy PurchaseOrderQuoteLanguage document.', 'Copy PurchaseOrderQuoteLanguage Document', 'OLE-SELECT', 'OLE80274', 'OLE80274', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open PurchaseOrderQuoteLanguage document.', 'Open PurchaseOrderQuoteLanguage Document', 'OLE-SELECT', 'OLE80275', 'OLE80275', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create PurchaseOrderQuoteLanguage document.', 'Initiate PurchaseOrderQuoteLanguage Document', 'OLE-SELECT', 'OLE80276', 'OLE80276', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit PurchaseOrderQuoteList document.', 'Edit PurchaseOrderQuoteList Document', 'OLE-SELECT', 'OLE80277', 'OLE80277', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy PurchaseOrderQuoteList document.', 'Copy PurchaseOrderQuoteList Document', 'OLE-SELECT', 'OLE80278', 'OLE80278', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open PurchaseOrderQuoteList document.', 'Open PurchaseOrderQuoteList Document', 'OLE-SELECT', 'OLE80279', 'OLE80279', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create PurchaseOrderQuoteList document.', 'Initiate PurchaseOrderQuoteList Document', 'OLE-SELECT', 'OLE80280', 'OLE80280', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit PurchaseOrderQuoteStatus document.', 'Edit PurchaseOrderQuoteStatus Document', 'OLE-SELECT', 'OLE80281', 'OLE80281', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy PurchaseOrderQuoteStatus document.', 'Copy PurchaseOrderQuoteStatus Document', 'OLE-SELECT', 'OLE80282', 'OLE80282', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open PurchaseOrderQuoteStatus document.', 'Open PurchaseOrderQuoteStatus Document', 'OLE-SELECT', 'OLE80283', 'OLE80283', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create PurchaseOrderQuoteStatus document.', 'Initiate PurchaseOrderQuoteStatus Document', 'OLE-SELECT', 'OLE80284', 'OLE80284', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit PurchaseOrderStatus document.', 'Edit PurchaseOrderStatus Document', 'OLE-SELECT', 'OLE80285', 'OLE80285', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy PurchaseOrderStatus document.', 'Copy PurchaseOrderStatus Document', 'OLE-SELECT', 'OLE80286', 'OLE80286', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open PurchaseOrderStatus document.', 'Open PurchaseOrderStatus Document', 'OLE-SELECT', 'OLE80287', 'OLE80287', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create PurchaseOrderStatus document.', 'Initiate PurchaseOrderStatus Document', 'OLE-SELECT', 'OLE80288', 'OLE80288', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit PurchaseOrderVendorChoice document.', 'Edit PurchaseOrderVendorChoice Document', 'OLE-SELECT', 'OLE80289', 'OLE80289', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy PurchaseOrderVendorChoice document.', 'Copy PurchaseOrderVendorChoice Document', 'OLE-SELECT', 'OLE80290', 'OLE80290', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open PurchaseOrderVendorChoice document.', 'Open PurchaseOrderVendorChoice Document', 'OLE-SELECT', 'OLE80291', 'OLE80291', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create PurchaseOrderVendorChoice document.', 'Initiate PurchaseOrderVendorChoice Document', 'OLE-SELECT', 'OLE80292', 'OLE80292', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ReceivingAddress document.', 'Edit ReceivingAddress Document', 'OLE-SELECT', 'OLE80293', 'OLE80293', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ReceivingAddress document.', 'Copy ReceivingAddress Document', 'OLE-SELECT', 'OLE80294', 'OLE80294', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ReceivingAddress document.', 'Open ReceivingAddress Document', 'OLE-SELECT', 'OLE80295', 'OLE80295', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ReceivingAddress document.', 'Initiate ReceivingAddress Document', 'OLE-SELECT', 'OLE80296', 'OLE80296', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ReceivingThreshold document.', 'Edit ReceivingThreshold Document', 'OLE-SELECT', 'OLE80297', 'OLE80297', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ReceivingThreshold document.', 'Copy ReceivingThreshold Document', 'OLE-SELECT', 'OLE80298', 'OLE80298', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ReceivingThreshold document.', 'Open ReceivingThreshold Document', 'OLE-SELECT', 'OLE80299', 'OLE80299', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ReceivingThreshold document.', 'Initiate ReceivingThreshold Document', 'OLE-SELECT', 'OLE80300', 'OLE80300', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit RecurringPaymentFrequency document.', 'Edit RecurringPaymentFrequency Document', 'OLE-SELECT', 'OLE80301', 'OLE80301', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy RecurringPaymentFrequency document.', 'Copy RecurringPaymentFrequency Document', 'OLE-SELECT', 'OLE80302', 'OLE80302', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open RecurringPaymentFrequency document.', 'Open RecurringPaymentFrequency Document', 'OLE-SELECT', 'OLE80303', 'OLE80303', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create RecurringPaymentFrequency document.', 'Initiate RecurringPaymentFrequency Document', 'OLE-SELECT', 'OLE80304', 'OLE80304', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit RecurringPaymentType document.', 'Edit RecurringPaymentType Document', 'OLE-SELECT', 'OLE80305', 'OLE80305', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy RecurringPaymentType document.', 'Copy RecurringPaymentType Document', 'OLE-SELECT', 'OLE80306', 'OLE80306', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open RecurringPaymentType document.', 'Open RecurringPaymentType Document', 'OLE-SELECT', 'OLE80307', 'OLE80307', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create RecurringPaymentType document.', 'Initiate RecurringPaymentType Document', 'OLE-SELECT', 'OLE80308', 'OLE80308', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit RequestSourceType document.', 'Edit RequestSourceType Document', 'OLE-SELECT', 'OLE80309', 'OLE80309', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy RequestSourceType document.', 'Copy RequestSourceType Document', 'OLE-SELECT', 'OLE80310', 'OLE80310', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open RequestSourceType document.', 'Open RequestSourceType Document', 'OLE-SELECT', 'OLE80311', 'OLE80311', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create RequestSourceType document.', 'Initiate RequestSourceType Document', 'OLE-SELECT', 'OLE80312', 'OLE80312', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit RequisitionSource document.', 'Edit RequisitionSource Document', 'OLE-SELECT', 'OLE80313', 'OLE80313', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy RequisitionSource document.', 'Copy RequisitionSource Document', 'OLE-SELECT', 'OLE80314', 'OLE80314', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open RequisitionSource document.', 'Open RequisitionSource Document', 'OLE-SELECT', 'OLE80315', 'OLE80315', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create RequisitionSource document.', 'Initiate RequisitionSource Document', 'OLE-SELECT', 'OLE80316', 'OLE80316', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit RequisitionStatus document.', 'Edit RequisitionStatus Document', 'OLE-SELECT', 'OLE80317', 'OLE80317', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy RequisitionStatus document.', 'Copy RequisitionStatus Document', 'OLE-SELECT', 'OLE80318', 'OLE80318', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open RequisitionStatus document.', 'Open RequisitionStatus Document', 'OLE-SELECT', 'OLE80319', 'OLE80319', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create RequisitionStatus document.', 'Initiate RequisitionStatus Document', 'OLE-SELECT', 'OLE80320', 'OLE80320', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit SensitiveData document.', 'Edit SensitiveData Document', 'OLE-SELECT', 'OLE80321', 'OLE80321', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy SensitiveData document.', 'Copy SensitiveData Document', 'OLE-SELECT', 'OLE80322', 'OLE80322', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open SensitiveData document.', 'Open SensitiveData Document', 'OLE-SELECT', 'OLE80323', 'OLE80323', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create SensitiveData document.', 'Initiate SensitiveData Document', 'OLE-SELECT', 'OLE80324', 'OLE80324', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit VendorStipulation document.', 'Edit VendorStipulation Document', 'OLE-SELECT', 'OLE80325', 'OLE80325', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy VendorStipulation document.', 'Copy VendorStipulation Document', 'OLE-SELECT', 'OLE80326', 'OLE80326', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open VendorStipulation document.', 'Open VendorStipulation Document', 'OLE-SELECT', 'OLE80327', 'OLE80327', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create VendorStipulation document.', 'Initiate VendorStipulation Document', 'OLE-SELECT', 'OLE80328', 'OLE80328', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit LicensingRequirement document.', 'Edit LicensingRequirement Document', 'OLE-SELECT', 'OLE80329', 'OLE80329', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy LicensingRequirement document.', 'Copy LicensingRequirement Document', 'OLE-SELECT', 'OLE80330', 'OLE80330', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open LicensingRequirement document.', 'Open LicensingRequirement Document', 'OLE-SELECT', 'OLE80331', 'OLE80331', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create LicensingRequirement document.', 'Initiate LicensingRequirement Document', 'OLE-SELECT', 'OLE80332', 'OLE80332', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizers the users to route the Payment request Document.', 'Route Document.', 'OLE-SELECT', 'OLE80333', 'OLE80333', '5', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit AccountGlobal document.', 'Edit AccountGlobal Document', 'OLE-SELECT', 'OLE80334', 'OLE80334', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy AccountGlobal document.', 'Copy AccountGlobal Document', 'OLE-SELECT', 'OLE80335', 'OLE80335', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open AccountGlobal document.', 'Open AccountGlobal Document', 'OLE-SELECT', 'OLE80336', 'OLE80336', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create AccountGlobal document.', 'Initiate AccountGlobal Document', 'OLE-SELECT', 'OLE80337', 'OLE80337', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit AccountDelegate document.', 'Edit AccountDelegate Document', 'OLE-SELECT', 'OLE80338', 'OLE80338', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy AccountDelegate document.', 'Copy AccountDelegate Document', 'OLE-SELECT', 'OLE80339', 'OLE80339', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open AccountDelegate document.', 'Open AccountDelegate Document', 'OLE-SELECT', 'OLE80340', 'OLE80340', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create AccountDelegate document.', 'Initiate AccountDelegate Document', 'OLE-SELECT', 'OLE80341', 'OLE80341', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit AccountDelegateGlobal document.', 'Edit AccountDelegateGlobal Document', 'OLE-SELECT', 'OLE80342', 'OLE80342', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy AccountDelegateGlobal document.', 'Copy AccountDelegateGlobal Document', 'OLE-SELECT', 'OLE80343', 'OLE80343', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open AccountDelegateGlobal document.', 'Open AccountDelegateGlobal Document', 'OLE-SELECT', 'OLE80344', 'OLE80344', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create AccountDelegateGlobal document.', 'Initiate AccountDelegateGlobal Document', 'OLE-SELECT', 'OLE80345', 'OLE80345', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit AccountDelegateModel document.', 'Edit AccountDelegateModel Document', 'OLE-SELECT', 'OLE80346', 'OLE80346', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy AccountDelegateModel document.', 'Copy AccountDelegateModel Document', 'OLE-SELECT', 'OLE80347', 'OLE80347', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open AccountDelegateModel document.', 'Open AccountDelegateModel Document', 'OLE-SELECT', 'OLE80348', 'OLE80348', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create AccountDelegateModel document.', 'Initiate AccountDelegateModel Document', 'OLE-SELECT', 'OLE80349', 'OLE80349', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ObjectCodeGlobal document.', 'Edit ObjectCodeGlobal Document', 'OLE-SELECT', 'OLE80350', 'OLE80350', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ObjectCodeGlobal document.', 'Copy ObjectCodeGlobal Document', 'OLE-SELECT', 'OLE80351', 'OLE80351', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ObjectCodeGlobal document.', 'Open ObjectCodeGlobal Document', 'OLE-SELECT', 'OLE80352', 'OLE80352', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ObjectCodeGlobal document.', 'Initiate ObjectCodeGlobal Document', 'OLE-SELECT', 'OLE80353', 'OLE80353', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit OrganizationReview document.', 'Edit OrganizationReview Document', 'OLE-SELECT', 'OLE80354', 'OLE80354', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy OrganizationReview document.', 'Copy OrganizationReview Document', 'OLE-SELECT', 'OLE80355', 'OLE80355', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open OrganizationReview document.', 'Open OrganizationReview Document', 'OLE-SELECT', 'OLE80356', 'OLE80356', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create OrganizationReview document.', 'Initiate OrganizationReview Document', 'OLE-SELECT', 'OLE80357', 'OLE80357', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ProjectCode document.', 'Edit ProjectCode Document', 'OLE-SELECT', 'OLE80358', 'OLE80358', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ProjectCode document.', 'Copy ProjectCode Document', 'OLE-SELECT', 'OLE80359', 'OLE80359', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ProjectCode document.', 'Open ProjectCode Document', 'OLE-SELECT', 'OLE80360', 'OLE80360', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ProjectCode document.', 'Initiate ProjectCode Document', 'OLE-SELECT', 'OLE80361', 'OLE80361', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit SubAccount document.', 'Edit SubAccount Document', 'OLE-SELECT', 'OLE80362', 'OLE80362', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy SubAccount document.', 'Copy SubAccount Document', 'OLE-SELECT', 'OLE80363', 'OLE80363', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open SubAccount document.', 'Open SubAccount Document', 'OLE-SELECT', 'OLE80364', 'OLE80364', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create SubAccount document.', 'Initiate SubAccount Document', 'OLE-SELECT', 'OLE80365', 'OLE80365', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit SubObjectCode document.', 'Edit SubObjectCode Document', 'OLE-SELECT', 'OLE80366', 'OLE80366', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy SubObjectCode document.', 'Copy SubObjectCode Document', 'OLE-SELECT', 'OLE80367', 'OLE80367', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open SubObjectCode document.', 'Open SubObjectCode Document', 'OLE-SELECT', 'OLE80368', 'OLE80368', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create SubObjectCode document.', 'Initiate SubObjectCode Document', 'OLE-SELECT', 'OLE80369', 'OLE80369', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit SubObjectCodeGlobal document.', 'Edit SubObjectCodeGlobal Document', 'OLE-SELECT', 'OLE80370', 'OLE80370', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy SubObjectCodeGlobal document.', 'Copy SubObjectCodeGlobal Document', 'OLE-SELECT', 'OLE80371', 'OLE80371', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open SubObjectCodeGlobal document.', 'Open SubObjectCodeGlobal Document', 'OLE-SELECT', 'OLE80372', 'OLE80372', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create SubObjectCodeGlobal document.', 'Initiate SubObjectCodeGlobal Document', 'OLE-SELECT', 'OLE80373', 'OLE80373', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit AccountType document.', 'Edit AccountType Document', 'OLE-SELECT', 'OLE80374', 'OLE80374', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy AccountType document.', 'Copy AccountType Document', 'OLE-SELECT', 'OLE80375', 'OLE80375', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open AccountType document.', 'Open AccountType Document', 'OLE-SELECT', 'OLE80376', 'OLE80376', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create AccountType document.', 'Initiate AccountType Document', 'OLE-SELECT', 'OLE80377', 'OLE80377', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit AccountingPeriod document.', 'Edit AccountingPeriod Document', 'OLE-SELECT', 'OLE80378', 'OLE80378', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy AccountingPeriod document.', 'Copy AccountingPeriod Document', 'OLE-SELECT', 'OLE80379', 'OLE80379', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open AccountingPeriod document.', 'Open AccountingPeriod Document', 'OLE-SELECT', 'OLE80380', 'OLE80380', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create AccountingPeriod document.', 'Initiate AccountingPeriod Document', 'OLE-SELECT', 'OLE80381', 'OLE80381', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit AICPAFunction document.', 'Edit AICPAFunction Document', 'OLE-SELECT', 'OLE80382', 'OLE80382', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy AICPAFunction document.', 'Copy AICPAFunction Document', 'OLE-SELECT', 'OLE80383', 'OLE80383', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open AICPAFunction document.', 'Open AICPAFunction Document', 'OLE-SELECT', 'OLE80384', 'OLE80384', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create AICPAFunction document.', 'Initiate AICPAFunction Document', 'OLE-SELECT', 'OLE80385', 'OLE80385', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit BalanceType document.', 'Edit BalanceType Document', 'OLE-SELECT', 'OLE80386', 'OLE80386', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy BalanceType document.', 'Copy BalanceType Document', 'OLE-SELECT', 'OLE80387', 'OLE80387', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open BalanceType document.', 'Open BalanceType Document', 'OLE-SELECT', 'OLE80388', 'OLE80388', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create BalanceType document.', 'Initiate BalanceType Document', 'OLE-SELECT', 'OLE80389', 'OLE80389', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit BasicAccountingCategory document.', 'Edit BasicAccountingCategory Document', 'OLE-SELECT', 'OLE80390', 'OLE80390', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy BasicAccountingCategory document.', 'Copy BasicAccountingCategory Document', 'OLE-SELECT', 'OLE80391', 'OLE80391', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open BasicAccountingCategory document.', 'Open BasicAccountingCategory Document', 'OLE-SELECT', 'OLE80392', 'OLE80392', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create BasicAccountingCategory document.', 'Initiate BasicAccountingCategory Document', 'OLE-SELECT', 'OLE80393', 'OLE80393', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit BudgetAggregationCode document.', 'Edit BudgetAggregationCode Document', 'OLE-SELECT', 'OLE80394', 'OLE80394', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy BudgetAggregationCode document.', 'Copy BudgetAggregationCode Document', 'OLE-SELECT', 'OLE80395', 'OLE80395', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open BudgetAggregationCode document.', 'Open BudgetAggregationCode Document', 'OLE-SELECT', 'OLE80396', 'OLE80396', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create BudgetAggregationCode document.', 'Initiate BudgetAggregationCode Document', 'OLE-SELECT', 'OLE80397', 'OLE80397', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit BudgetRecordingLevel document.', 'Edit BudgetRecordingLevel Document', 'OLE-SELECT', 'OLE80398', 'OLE80398', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy BudgetRecordingLevel document.', 'Copy BudgetRecordingLevel Document', 'OLE-SELECT', 'OLE80399', 'OLE80399', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open BudgetRecordingLevel document.', 'Open BudgetRecordingLevel Document', 'OLE-SELECT', 'OLE80400', 'OLE80400', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create BudgetRecordingLevel document.', 'Initiate BudgetRecordingLevel Document', 'OLE-SELECT', 'OLE80401', 'OLE80401', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit FederalFunction document.', 'Edit FederalFunction Document', 'OLE-SELECT', 'OLE80402', 'OLE80402', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy FederalFunction document.', 'Copy FederalFunction Document', 'OLE-SELECT', 'OLE80403', 'OLE80403', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open FederalFunction document.', 'Open FederalFunction Document', 'OLE-SELECT', 'OLE80404', 'OLE80404', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create FederalFunction document.', 'Initiate FederalFunction Document', 'OLE-SELECT', 'OLE80405', 'OLE80405', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit FundGroup document.', 'Edit FundGroup Document', 'OLE-SELECT', 'OLE80406', 'OLE80406', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy FundGroup document.', 'Copy FundGroup Document', 'OLE-SELECT', 'OLE80407', 'OLE80407', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open FundGroup document.', 'Open FundGroup Document', 'OLE-SELECT', 'OLE80408', 'OLE80408', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create FundGroup document.', 'Initiate FundGroup Document', 'OLE-SELECT', 'OLE80409', 'OLE80409', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit HigherEducationFunction document.', 'Edit HigherEducationFunction Document', 'OLE-SELECT', 'OLE80410', 'OLE80410', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy HigherEducationFunction document.', 'Copy HigherEducationFunction Document', 'OLE-SELECT', 'OLE80411', 'OLE80411', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open HigherEducationFunction document.', 'Open HigherEducationFunction Document', 'OLE-SELECT', 'OLE80412', 'OLE80412', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create HigherEducationFunction document.', 'Initiate HigherEducationFunction Document', 'OLE-SELECT', 'OLE80413', 'OLE80413', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit MandatoryTransferElimination document.', 'Edit MandatoryTransferElimination Document', 'OLE-SELECT', 'OLE80414', 'OLE80414', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy MandatoryTransferElimination document.', 'Copy MandatoryTransferElimination Document', 'OLE-SELECT', 'OLE80415', 'OLE80415', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open MandatoryTransferElimination document.', 'Open MandatoryTransferElimination Document', 'OLE-SELECT', 'OLE80416', 'OLE80416', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create MandatoryTransferElimination document.', 'Initiate MandatoryTransferElimination Document', 'OLE-SELECT', 'OLE80417', 'OLE80417', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ObjectConsolidation document.', 'Edit ObjectConsolidation Document', 'OLE-SELECT', 'OLE80418', 'OLE80418', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ObjectConsolidation document.', 'Copy ObjectConsolidation Document', 'OLE-SELECT', 'OLE80419', 'OLE80419', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ObjectConsolidation document.', 'Open ObjectConsolidation Document', 'OLE-SELECT', 'OLE80420', 'OLE80420', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ObjectConsolidation document.', 'Initiate ObjectConsolidation Document', 'OLE-SELECT', 'OLE80421', 'OLE80421', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ObjectLevel document.', 'Edit ObjectLevel Document', 'OLE-SELECT', 'OLE80422', 'OLE80422', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ObjectLevel document.', 'Copy ObjectLevel Document', 'OLE-SELECT', 'OLE80423', 'OLE80423', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ObjectLevel document.', 'Open ObjectLevel Document', 'OLE-SELECT', 'OLE80424', 'OLE80424', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ObjectLevel document.', 'Initiate ObjectLevel Document', 'OLE-SELECT', 'OLE80425', 'OLE80425', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ObjectSubType document.', 'Edit ObjectSubType Document', 'OLE-SELECT', 'OLE80426', 'OLE80426', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ObjectSubType document.', 'Copy ObjectSubType Document', 'OLE-SELECT', 'OLE80427', 'OLE80427', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ObjectSubType document.', 'Open ObjectSubType Document', 'OLE-SELECT', 'OLE80428', 'OLE80428', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ObjectSubType document.', 'Initiate ObjectSubType Document', 'OLE-SELECT', 'OLE80429', 'OLE80429', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ObjectType document.', 'Edit ObjectType Document', 'OLE-SELECT', 'OLE80430', 'OLE80430', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ObjectType document.', 'Copy ObjectType Document', 'OLE-SELECT', 'OLE80431', 'OLE80431', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ObjectType document.', 'Open ObjectType Document', 'OLE-SELECT', 'OLE80432', 'OLE80432', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ObjectType document.', 'Initiate ObjectType Document', 'OLE-SELECT', 'OLE80433', 'OLE80433', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit OffsetAccount document.', 'Edit OffsetAccount Document', 'OLE-SELECT', 'OLE80434', 'OLE80434', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy OffsetAccount document.', 'Copy OffsetAccount Document', 'OLE-SELECT', 'OLE80435', 'OLE80435', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open OffsetAccount document.', 'Open OffsetAccount Document', 'OLE-SELECT', 'OLE80436', 'OLE80436', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create OffsetAccount document.', 'Initiate OffsetAccount Document', 'OLE-SELECT', 'OLE80437', 'OLE80437', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit OffsetDefinition document.', 'Edit OffsetDefinition Document', 'OLE-SELECT', 'OLE80438', 'OLE80438', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy OffsetDefinition document.', 'Copy OffsetDefinition Document', 'OLE-SELECT', 'OLE80439', 'OLE80439', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open OffsetDefinition document.', 'Open OffsetDefinition Document', 'OLE-SELECT', 'OLE80440', 'OLE80440', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create OffsetDefinition document.', 'Initiate OffsetDefinition Document', 'OLE-SELECT', 'OLE80441', 'OLE80441', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit OrganizationReversionCategory document.', 'Edit OrganizationReversionCategory Document', 'OLE-SELECT', 'OLE80442', 'OLE80442', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy OrganizationReversionCategory document.', 'Copy OrganizationReversionCategory Document', 'OLE-SELECT', 'OLE80443', 'OLE80443', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open OrganizationReversionCategory document.', 'Open OrganizationReversionCategory Document', 'OLE-SELECT', 'OLE80444', 'OLE80444', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create OrganizationReversionCategory document.', 'Initiate OrganizationReversionCategory Document', 'OLE-SELECT', 'OLE80445', 'OLE80445', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit OrganizationReversion document.', 'Edit OrganizationReversion Document', 'OLE-SELECT', 'OLE80446', 'OLE80446', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy OrganizationReversion document.', 'Copy OrganizationReversion Document', 'OLE-SELECT', 'OLE80447', 'OLE80447', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open OrganizationReversion document.', 'Open OrganizationReversion Document', 'OLE-SELECT', 'OLE80448', 'OLE80448', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create OrganizationReversion document.', 'Initiate OrganizationReversion Document', 'OLE-SELECT', 'OLE80449', 'OLE80449', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ConstraintType document.', 'Edit ConstraintType Document', 'OLE-SELECT', 'OLE80450', 'OLE80450', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ConstraintType document.', 'Copy ConstraintType Document', 'OLE-SELECT', 'OLE80451', 'OLE80451', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ConstraintType document.', 'Open ConstraintType Document', 'OLE-SELECT', 'OLE80452', 'OLE80452', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ConstraintType document.', 'Initiate ConstraintType Document', 'OLE-SELECT', 'OLE80453', 'OLE80453', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit OrganizationReversionGlobal document.', 'Edit OrganizationReversionGlobal Document', 'OLE-SELECT', 'OLE80454', 'OLE80454', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy OrganizationReversionGlobal document.', 'Copy OrganizationReversionGlobal Document', 'OLE-SELECT', 'OLE80455', 'OLE80455', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open OrganizationReversionGlobal document.', 'Open OrganizationReversionGlobal Document', 'OLE-SELECT', 'OLE80456', 'OLE80456', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create OrganizationReversionGlobal document.', 'Initiate OrganizationReversionGlobal Document', 'OLE-SELECT', 'OLE80457', 'OLE80457', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit OrganizationType document.', 'Edit OrganizationType Document', 'OLE-SELECT', 'OLE80458', 'OLE80458', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy OrganizationType document.', 'Copy OrganizationType Document', 'OLE-SELECT', 'OLE80459', 'OLE80459', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open OrganizationType document.', 'Open OrganizationType Document', 'OLE-SELECT', 'OLE80460', 'OLE80460', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create OrganizationType document.', 'Initiate OrganizationType Document', 'OLE-SELECT', 'OLE80461', 'OLE80461', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit FinancialReportingCode document.', 'Edit FinancialReportingCode Document', 'OLE-SELECT', 'OLE80462', 'OLE80462', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy FinancialReportingCode document.', 'Copy FinancialReportingCode Document', 'OLE-SELECT', 'OLE80463', 'OLE80463', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open FinancialReportingCode document.', 'Open FinancialReportingCode Document', 'OLE-SELECT', 'OLE80464', 'OLE80464', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create FinancialReportingCode document.', 'Initiate FinancialReportingCode Document', 'OLE-SELECT', 'OLE80465', 'OLE80465', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit ResponsibilityCenter document.', 'Edit ResponsibilityCenter Document', 'OLE-SELECT', 'OLE80466', 'OLE80466', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ResponsibilityCenter document.', 'Copy ResponsibilityCenter Document', 'OLE-SELECT', 'OLE80467', 'OLE80467', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ResponsibilityCenter document.', 'Open ResponsibilityCenter Document', 'OLE-SELECT', 'OLE80468', 'OLE80468', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create ResponsibilityCenter document.', 'Initiate ResponsibilityCenter Document', 'OLE-SELECT', 'OLE80469', 'OLE80469', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit RestrictedStatus document.', 'Edit RestrictedStatus Document', 'OLE-SELECT', 'OLE80470', 'OLE80470', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy RestrictedStatus document.', 'Copy RestrictedStatus Document', 'OLE-SELECT', 'OLE80471', 'OLE80471', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open RestrictedStatus document.', 'Open RestrictedStatus Document', 'OLE-SELECT', 'OLE80472', 'OLE80472', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create RestrictedStatus document.', 'Initiate RestrictedStatus Document', 'OLE-SELECT', 'OLE80473', 'OLE80473', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit SubFundGroup document.', 'Edit SubFundGroup Document', 'OLE-SELECT', 'OLE80474', 'OLE80474', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy SubFundGroup document.', 'Copy SubFundGroup Document', 'OLE-SELECT', 'OLE80475', 'OLE80475', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open SubFundGroup document.', 'Open SubFundGroup Document', 'OLE-SELECT', 'OLE80476', 'OLE80476', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create SubFundGroup document.', 'Initiate SubFundGroup Document', 'OLE-SELECT', 'OLE80477', 'OLE80477', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit SubFundGroupType document.', 'Edit SubFundGroupType Document', 'OLE-SELECT', 'OLE80478', 'OLE80478', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy SubFundGroupType document.', 'Copy SubFundGroupType Document', 'OLE-SELECT', 'OLE80479', 'OLE80479', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open SubFundGroupType document.', 'Open SubFundGroupType Document', 'OLE-SELECT', 'OLE80480', 'OLE80480', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create SubFundGroupType document.', 'Initiate SubFundGroupType Document', 'OLE-SELECT', 'OLE80481', 'OLE80481', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit SufficientFundsCode document.', 'Edit SufficientFundsCode Document', 'OLE-SELECT', 'OLE80482', 'OLE80482', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy SufficientFundsCode document.', 'Copy SufficientFundsCode Document', 'OLE-SELECT', 'OLE80483', 'OLE80483', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open SufficientFundsCode document.', 'Open SufficientFundsCode Document', 'OLE-SELECT', 'OLE80484', 'OLE80484', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create SufficientFundsCode document.', 'Initiate SufficientFundsCode Document', 'OLE-SELECT', 'OLE80485', 'OLE80485', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit SufficientFundsCheckType document.', 'Edit SufficientFundsCheckType Document', 'OLE-SELECT', 'OLE80486', 'OLE80486', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy SufficientFundsCheckType document.', 'Copy SufficientFundsCheckType Document', 'OLE-SELECT', 'OLE80487', 'OLE80487', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open SufficientFundsCheckType document.', 'Open SufficientFundsCheckType Document', 'OLE-SELECT', 'OLE80488', 'OLE80488', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create SufficientFundsCheckType document.', 'Initiate SufficientFundsCheckType Document', 'OLE-SELECT', 'OLE80489', 'OLE80489', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit StewardshipRequirement document.', 'Edit StewardshipRequirement Document', 'OLE-SELECT', 'OLE80490', 'OLE80490', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy StewardshipRequirement document.', 'Copy StewardshipRequirement Document', 'OLE-SELECT', 'OLE80491', 'OLE80491', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open StewardshipRequirement document.', 'Open StewardshipRequirement Document', 'OLE-SELECT', 'OLE80492', 'OLE80492', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create StewardshipRequirement document.', 'Initiate StewardshipRequirement Document', 'OLE-SELECT', 'OLE80493', 'OLE80493', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit UniversityBudgetOfficeFunction document.', 'Edit UniversityBudgetOfficeFunction Document', 'OLE-SELECT', 'OLE80494', 'OLE80494', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy UniversityBudgetOfficeFunction document.', 'Copy UniversityBudgetOfficeFunction Document', 'OLE-SELECT', 'OLE80495', 'OLE80495', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open UniversityBudgetOfficeFunction document.', 'Open UniversityBudgetOfficeFunction Document', 'OLE-SELECT', 'OLE80496', 'OLE80496', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create UniversityBudgetOfficeFunction document.', 'Initiate UniversityBudgetOfficeFunction Document', 'OLE-SELECT', 'OLE80497', 'OLE80497', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy Chart document.', 'Copy Chart Document', 'OLE-SELECT', 'OLE80498', 'OLE80498', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open Chart document.', 'Open Chart Document', 'OLE-SELECT', 'OLE80499', 'OLE80499', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy Account document.', 'Copy Account Document', 'OLE-SELECT', 'OLE80500', 'OLE80500', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open Account document.', 'Open Account Document', 'OLE-SELECT', 'OLE80501', 'OLE80501', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy ObjectCode document.', 'Copy ObjectCode Document', 'OLE-SELECT', 'OLE80502', 'OLE80502', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open ObjectCode document.', 'Open ObjectCode Document', 'OLE-SELECT', 'OLE80503', 'OLE80503', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy Organization document.', 'Copy Organization Document', 'OLE-SELECT', 'OLE80504', 'OLE80504', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open Organization document.', 'Open Organization Document', 'OLE-SELECT', 'OLE80505', 'OLE80505', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to edit UnitOfMeasure document.', 'Edit UnitOfMeasure Document', 'OLE-SELECT', 'OLE80506', 'OLE80506', '16', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to copy UnitOfMeasure document.', 'Copy UnitOfMeasure Document', 'OLE-SELECT', 'OLE80507', 'OLE80507', '2', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to open UnitOfMeasure document.', 'Open UnitOfMeasure Document', 'OLE-SELECT', 'OLE80508', 'OLE80508', '40', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Authorizes users to create UnitOfMeasure document.', 'Initiate UnitOfMeasure Document', 'OLE-SELECT', 'OLE80509', 'OLE80509', '10', '1')
/

INSERT INTO KRIM_PERM_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, PERM_ID, PERM_TMPL_ID, VER_NBR) VALUES ('Y', 'Allow users to approve and cancel requisition document.', 'Approve OLE_REQS', 'OLE-SELECT', 'OLE80510', 'OLE80510', '1', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_KRIM_PERM_T', '2.0.5', '3:73dd0cd69e08c05667f7088cd51f39b7', 17)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_KRIM_PERM_ATTR_DATA_T::ole::(Checksum: 3:20fba9c7d4f998d0cfc8faa0051edc81)
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10004', 'OLE_REQS', '13', '8', 'OLE10004', 'OLE10006', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10025', 'PreRoute', '16', '8', 'OLE10025', 'OLE10006', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10005', 'A', '14', '4', 'OLE10005', 'OLE10007', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10006', 'OLE_PO', '13', '8', 'OLE10006', 'OLE10009', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10007', 'PreRoute', '16', '8', 'OLE10007', 'OLE10009', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10008', 'A', '14', '4', 'OLE10008', 'OLE10010', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10009', 'OLE_POA', '13', '8', 'OLE10009', 'OLE10011', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10010', 'OLE_POA', '13', '8', 'OLE10010', 'OLE10012', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10011', 'OLE_POA', '13', '56', 'OLE10011', 'OLE10014', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10012', 'OLE_PVEN', '13', '56', 'OLE10012', 'OLE10015', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10013', 'A', '14', '4', 'OLE10013', 'OLE10016', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10014', 'OLE_PVEN', '13', '8', 'OLE10014', 'OLE10017', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10015', 'OLE_PO', '13', '56', 'OLE10015', 'OLE10018', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10016', 'OLE_PO', '13', '8', 'OLE10016', 'OLE10019', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10017', 'A', '14', '4', 'OLE10017', 'OLE10020', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10018', 'vendorDiscountPercentage', '6', '11', 'OLE10018', 'OLE10021', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10019', 'VendorCustomerNumber', '5', '11', 'OLE10019', 'OLE10021', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10020', 'F', '14', '4', 'OLE10020', 'OLE10028', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10021', 'F', '14', '4', 'OLE10021', 'OLE10029', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10023', 'OLE*', '4', '10', 'OLE10023', 'OLE10035', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10024', 'OLE', '13', '3', 'OLE10024', 'OLE10036', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10026', 'OLE', '13', '3', 'OLE10026', 'OLE10041', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10027', 'OLE_PVEN', '13', '56', 'OLE10027', 'OLE10042', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10028', 'FALSE', '7', '56', 'OLE10028', 'OLE10042', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10029', 'OLE_PVEN', '13', '56', 'OLE10029', 'OLE10043', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10030', 'TRUE', '7', '56', 'OLE10030', 'OLE10043', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10031', 'OLE*', '4', '10', 'OLE10031', 'OLE10004', '6')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10032', 'PreRoute', '16', '8', 'OLE10032', 'OLE10005', '6')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10033', 'OLE_REQS', '13', '8', 'OLE10033', 'OLE10005', '3')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10034', 'R', '15', '8', 'OLE10034', 'OLE10005', '3')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10035', 'OLE_RCVL', '13', '3', 'OLE10035', 'OLE10044', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10036', 'PreRoute', '16', '8', 'OLE10036', 'OLE10045', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10037', 'OLE_RCVL', '13', '8', 'OLE10037', 'OLE10045', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10038', 'OLE_RCVL', '13', '3', 'OLE10038', 'OLE10046', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10039', 'OLE_RCVC', '13', '3', 'OLE10039', 'OLE10047', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10040', 'OLE_RCVC', '13', '3', 'OLE10040', 'OLE10048', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10041', 'OLE_PREQ', '13', '3', 'OLE10041', 'OLE10049', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10042', 'OLE_PREQ', '13', '8', 'OLE10042', 'OLE10050', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10043', 'R', '15', '8', 'OLE10043', 'OLE10050', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10044', 'OLE_PREQ', '13', '8', 'OLE10044', 'OLE10051', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10045', 'P', '15', '8', 'OLE10045', 'OLE10051', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10046', 'OLE_PREQ', '13', '8', 'OLE10046', 'OLE10052', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10047', 'F', '15', '8', 'OLE10047', 'OLE10052', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10048', 'items.sourceAccountingLines', '6', '52', 'OLE10048', 'OLE10053', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10049', 'Account', '16', '52', 'OLE10049', 'OLE10053', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10050', 'OLE_PREQ', '13', '52', 'OLE10050', 'OLE10053', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10051', 'requestPaymentRequestCancel', '10', '14', 'OLE10051', 'OLE10054', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10052', 'OLE_PREQ', '13', '14', 'OLE10052', 'OLE10054', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10053', 'OLE_RCVL', '13', '14', 'OLE10053', 'OLE10055', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10054', 'OLE_POC', '13', '8', 'OLE10054', 'OLE10056', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10055', 'PreRoute', '16', '8', 'OLE10055', 'OLE10057', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10056', 'OLE_PREQ', '13', '8', 'OLE10056', 'OLE10057', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10057', 'OLE_RCVC', '13', '3', 'OLE10057', 'OLE10058', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10058', 'PreRoute', '16', '8', 'OLE10058', 'OLE10059', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10059', 'OLE_RCVC', '13', '8', 'OLE10059', 'OLE10059', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10060', 'OLE_REQS', '13', '8', 'OLE10060', 'OLE10060', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10061', 'OLE_PO', '13', '8', 'OLE10061', 'OLE10061', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10062', 'OLE_POA', '13', '8', 'OLE10062', 'OLE10062', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10063', 'OLE_REQS', '13', '8', 'OLE10063', 'OLE10063', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10064', 'OLE_REQS', '13', '8', 'OLE10064', 'OLE10064', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10065', 'OLE_POV', '13', '8', 'OLE10065', 'OLE10065', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10066', 'OLE_POSP', '13', '8', 'OLE10066', 'OLE10066', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10067', 'OLE_POR', '13', '8', 'OLE10067', 'OLE10067', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10068', 'org.kuali.ole.module.purap.document.web.struts.PrintAction', '2', '12', 'OLE10068', 'OLE10068', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10069', 'printPurchaseOrder', '10', '14', 'OLE10069', 'OLE10069', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10070', 'OLE_PO', '13', '14', 'OLE10070', 'OLE10069', '3')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10071', 'previewPrintPurchaseOrder', '10', '14', 'OLE10071', 'OLE10070', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10072', 'OLE_PO', '13', '14', 'OLE10072', 'OLE10070', '3')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10073', 'OLE_PO', '13', '8', 'OLE10073', 'OLE10068', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10074', 'OLE_REQS', '13', '8', 'OLE10074', 'OLE10071', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10075', 'ordInputFileType', '1', '15', 'OLE10075', 'OLE10100', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10076', 'OLE_LOADSUM', '13', '8', 'OLE10076', 'OLE10108', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10077', 'OLE_LOADSUM', '13', '8', 'OLE10077', 'OLE10109', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10078', 'OLE_PO', '13', '8', 'OLE10078', 'OLE10110', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10079', 'OLE_POA', '13', '8', 'OLE10079', 'OLE10111', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10080', 'OLE_REQS', '13', '8', 'OLE10080', 'OLE10114', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10081', 'OLE_REQS', '13', '8', 'OLE10081', 'OLE10113', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10082', 'OLE_ORDQU', '13', '3', 'OLE10082', 'OLE10115', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10083', 'OLE_RCV', '13', '3', 'OLE10083', 'OLE10116', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10084', 'PreRoute', '16', '8', 'OLE10084', 'OLE10117', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10085', 'OLE_RCV', '13', '8', 'OLE10085', 'OLE10117', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10086', 'PreRoute', '16', '8', 'OLE10086', 'OLE10118', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10087', 'RCG_QUEUESEARCH', '13', '8', 'OLE10087', 'OLE10118', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10088', 'RCG_QUEUESEARCH', '13', '3', 'OLE10088', 'OLE10119', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10089', 'OLE_PVEN', '13', '3', 'OLE10089', 'OLE10121', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE10090', 'OLE_POA', '13', '3', 'OLE10090', 'OLE10122', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80000', 'OLE_PYMNTMTHD', '13', '3', 'OLE80000', 'OLE80000', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80002', 'OLE_PMAT', '13', '3', 'OLE80002', 'OLE80002', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80004', 'OLE_PMCP', '13', '3', 'OLE80004', 'OLE80004', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80006', 'OLE_PMCT', '13', '3', 'OLE80006', 'OLE80006', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80008', 'OLE_PMOT', '13', '3', 'OLE80008', 'OLE80008', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80010', 'OLE_PMOC', '13', '3', 'OLE80010', 'OLE80011', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80012', 'OLE_PMPA', '13', '3', 'OLE80012', 'OLE80014', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80014', 'OLE_PMSP', '13', '3', 'OLE80014', 'OLE80016', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80016', 'OLE_PMPT', '13', '3', 'OLE80016', 'OLE80018', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80018', 'OLE_PMSS', '13', '3', 'OLE80018', 'OLE80020', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80020', 'OLE_PMST', '13', '8', 'OLE80020', 'OLE80024', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80022', 'OLE_PMALST', '13', '8', 'OLE80022', 'OLE80026', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80024', 'OLE_PMBA', '13', '3', 'OLE80024', 'OLE80028', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80026', 'OLE_PMCA', '13', '3', 'OLE80026', 'OLE80030', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80028', 'OLE_PMC', '13', '3', 'OLE80028', 'OLE80032', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80030', 'OLE_EXCTYP', '13', '3', 'OLE80030', 'OLE80034', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80032', 'OLE_FTMAT', '13', '3', 'OLE80032', 'OLE80036', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80034', 'OLE_PMFS', '13', '3', 'OLE80034', 'OLE80038', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80036', 'OLE_INVCTYP', '13', '3', 'OLE80036', 'OLE80040', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80038', 'OLE_INVCSBTYP', '13', '3', 'OLE80038', 'OLE80042', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80040', 'OLE_PMIPS', '13', '3', 'OLE80040', 'OLE80044', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80042', 'OLE_NOTETYP', '13', '3', 'OLE80042', 'OLE80047', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80044', 'OLE_PPOT', '13', '3', 'OLE80044', 'OLE80050', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80046', 'OLE_OIS', '13', '3', 'OLE80046', 'OLE80052', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80048', 'OLE_PMRA', '13', '3', 'OLE80048', 'OLE80054', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80050', 'OLE_RSTMAT', '13', '3', 'OLE80050', 'OLE80056', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80052', 'OLE_THLD', '13', '3', 'OLE80052', 'OLE80058', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80054', 'OLE_REQ', '13', '3', 'OLE80054', 'OLE80061', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80056', 'OLE_PMCO', '13', '3', 'OLE80056', 'OLE80063', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80058', 'OLE_PMCS', '13', '3', 'OLE80058', 'OLE80065', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80060', 'OLE_PMCC', '13', '3', 'OLE80060', 'OLE80067', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80062', 'OLE_PMSD', '13', '3', 'OLE80062', 'OLE80069', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80064', 'OLE_PMIR', '13', '3', 'OLE80064', 'OLE80071', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80066', 'OLE_PMVTF', '13', '3', 'OLE80066', 'OLE80073', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80068', 'OLE_PMVTT', '13', '3', 'OLE80068', 'OLE80075', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80070', 'OLE_PMVT', '13', '3', 'OLE80070', 'OLE80077', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80072', 'OLE_PMCM', '13', '3', 'OLE80072', 'OLE80080', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80074', 'OLE_PMCUT', '13', '3', 'OLE80074', 'OLE80082', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80076', 'OLE_PMDR', '13', '3', 'OLE80076', 'OLE80084', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80078', 'OLE_EIIM', '13', '3', 'OLE80078', 'OLE80087', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80080', 'OLE_IRAD', '13', '3', 'OLE80080', 'OLE80089', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80082', 'OLE_PMIT', '13', '3', 'OLE80082', 'OLE80091', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80084', 'OLE_PMLI', '13', '3', 'OLE80084', 'OLE80094', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80086', 'OLE_PMTM', '13', '3', 'OLE80086', 'OLE80097', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80088', 'OLE_PMNP', '13', '3', 'OLE80088', 'OLE80099', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80090', 'OLE_PMOP', '13', '3', 'OLE80090', 'OLE80101', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80092', 'OLE_PMAA', '13', '3', 'OLE80092', 'OLE80103', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80094', 'OLE_PMPR', '13', '3', 'OLE80094', 'OLE80105', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80096', 'OLE_PMCL', '13', '3', 'OLE80096', 'OLE80107', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80099', 'OLE_PMQL', '13', '3', 'OLE80099', 'OLE80110', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80100', 'OLE_PMQT', '13', '3', 'OLE80100', 'OLE80111', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80102', 'OLE_PMQS', '13', '3', 'OLE80102', 'OLE80113', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80104', 'OLE_PMPS', '13', '3', 'OLE80104', 'OLE80115', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80106', 'OLE_PMVC', '13', '3', 'OLE80106', 'OLE80117', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80108', 'OLE_PMRF', '13', '3', 'OLE80108', 'OLE80120', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80110', 'OLE_PMRP', '13', '3', 'OLE80110', 'OLE80122', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80112', 'OLE_PMSO', '13', '3', 'OLE80112', 'OLE80124', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80114', 'OLE_PMRS', '13', '3', 'OLE80114', 'OLE80126', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80116', 'OLE_PMSN', '13', '3', 'OLE80116', 'OLE80128', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80118', 'OLE_PMSI', '13', '3', 'OLE80118', 'OLE80130', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80120', 'OLE_REQTYPE', '13', '3', 'OLE80120', 'OLE80132', '2')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80122', 'OLE_LOADSUM', '13', '3', 'OLE80122', 'OLE80134', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80123', 'OLE_RCVL', '13', '3', 'OLE80123', 'OLE80135', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80124', 'OLE_RCVL', '13', '8', 'OLE80124', 'OLE80136', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80125', 'OLE_RCVL', '13', '3', 'OLE80125', 'OLE80138', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80126', 'OLE_PVEN', '13', '56', 'OLE80126', 'OLE80139', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80127', 'FALSE', '7', '56', 'OLE80127', 'OLE80139', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80128', 'OLE_PVEN', '13', '56', 'OLE80128', 'OLE80140', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80129', 'TRUE', '7', '56', 'OLE80129', 'OLE80140', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80130', 'OLE_COAT', '13', '3', 'OLE80130', 'OLE80143', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80131', 'OLE_ACCT', '13', '3', 'OLE80131', 'OLE80144', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80132', 'OLE_OBJT', '13', '3', 'OLE80132', 'OLE80145', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80133', 'OLE_ORGN', '13', '3', 'OLE80133', 'OLE80146', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80134', 'OLE_COAT', '13', '8', 'OLE80134', 'OLE80147', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80135', 'OLE_ACCT', '13', '8', 'OLE80135', 'OLE80148', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80136', 'OLE_OBJT', '13', '8', 'OLE80136', 'OLE80149', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80137', 'OLE_ORGN', '13', '8', 'OLE80137', 'OLE80150', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80138', 'OLE_DI', '13', '3', 'OLE80138', 'OLE80157', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80139', 'OLE_DV', '13', '3', 'OLE80139', 'OLE80158', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80140', 'PreRoute', '16', '8', 'OLE80140', 'OLE80159', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80141', 'OLE_DV', '13', '8', 'OLE80141', 'OLE80159', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80142', 'OLE_DV', '13', '8', 'OLE80142', 'OLE80160', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80143', 'R', '15', '8', 'OLE80143', 'OLE80160', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80144', 'PreRoute', '16', '8', 'OLE80144', 'OLE80161', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80145', 'OLE_CM', '13', '8', 'OLE80145', 'OLE80161', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80146', 'OLE_GEC', '13', '3', 'OLE80146', 'OLE80162', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80147', 'OLE_BA', '13', '3', 'OLE80147', 'OLE80164', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80148', 'OLE_BA', '13', '8', 'OLE80148', 'OLE80165', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80149', 'OLE_TF', '13', '3', 'OLE80149', 'OLE80166', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80150', 'OLE_TF', '13', '8', 'OLE80150', 'OLE80167', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80151', 'OLE_AD', '13', '3', 'OLE80151', 'OLE80168', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80152', 'OLE_AD', '13', '8', 'OLE80152', 'OLE80169', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80153', 'OLE_PMBA', '13', '8', 'OLE80153', 'OLE80173', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80154', 'OLE_PMBA', '13', '3', 'OLE80154', 'OLE80174', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80155', 'OLE_PMBA', '13', '3', 'OLE80155', 'OLE80175', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80156', 'OLE_PMBA', '13', '3', 'OLE80156', 'OLE80176', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80157', 'OLE_PMCA', '13', '8', 'OLE80157', 'OLE80177', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80158', 'OLE_PMCA', '13', '3', 'OLE80158', 'OLE80178', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80159', 'OLE_PMCA', '13', '3', 'OLE80159', 'OLE80179', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80160', 'OLE_PMCA', '13', '3', 'OLE80160', 'OLE80180', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80161', 'OLE_PMC', '13', '8', 'OLE80161', 'OLE80181', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80162', 'OLE_PMC', '13', '3', 'OLE80162', 'OLE80182', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80163', 'OLE_PMC', '13', '3', 'OLE80163', 'OLE80183', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80164', 'OLE_PMC', '13', '3', 'OLE80164', 'OLE80184', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80165', 'OLE_PMCM', '13', '8', 'OLE80165', 'OLE80185', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80166', 'OLE_PMCM', '13', '3', 'OLE80166', 'OLE80186', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80167', 'OLE_PMCM', '13', '3', 'OLE80167', 'OLE80187', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80168', 'OLE_PMCM', '13', '3', 'OLE80168', 'OLE80188', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80169', 'OLE_PMDR', '13', '8', 'OLE80169', 'OLE80189', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80170', 'OLE_PMDR', '13', '3', 'OLE80170', 'OLE80190', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80171', 'OLE_PMDR', '13', '3', 'OLE80171', 'OLE80191', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80172', 'OLE_PMDR', '13', '3', 'OLE80172', 'OLE80192', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80173', 'OLE_EIIM', '13', '8', 'OLE80173', 'OLE80193', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80174', 'OLE_EIIM', '13', '3', 'OLE80174', 'OLE80194', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80175', 'OLE_EIIM', '13', '3', 'OLE80175', 'OLE80195', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80176', 'OLE_EIIM', '13', '3', 'OLE80176', 'OLE80196', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80177', 'OLE_EXCTYP', '13', '8', 'OLE80177', 'OLE80197', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80178', 'OLE_EXCTYP', '13', '3', 'OLE80178', 'OLE80198', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80179', 'OLE_EXCTYP', '13', '3', 'OLE80179', 'OLE80199', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80180', 'OLE_EXCTYP', '13', '3', 'OLE80180', 'OLE80200', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80181', 'OLE_FTMAT', '13', '8', 'OLE80181', 'OLE80201', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80182', 'OLE_FTMAT', '13', '3', 'OLE80182', 'OLE80202', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80183', 'OLE_FTMAT', '13', '3', 'OLE80183', 'OLE80203', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80184', 'OLE_FTMAT', '13', '3', 'OLE80184', 'OLE80204', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80185', 'OLE_PMFS', '13', '8', 'OLE80185', 'OLE80205', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80186', 'OLE_PMFS', '13', '3', 'OLE80186', 'OLE80206', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80187', 'OLE_PMFS', '13', '3', 'OLE80187', 'OLE80207', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80188', 'OLE_PMFS', '13', '3', 'OLE80188', 'OLE80208', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80189', 'OLE_INVCSBTYP', '13', '8', 'OLE80189', 'OLE80209', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80190', 'OLE_INVCSBTYP', '13', '3', 'OLE80190', 'OLE80210', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80191', 'OLE_INVCSBTYP', '13', '3', 'OLE80191', 'OLE80211', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80192', 'OLE_INVCSBTYP', '13', '3', 'OLE80192', 'OLE80212', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80193', 'OLE_INVCTYP', '13', '8', 'OLE80193', 'OLE80213', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80194', 'OLE_INVCTYP', '13', '3', 'OLE80194', 'OLE80214', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80195', 'OLE_INVCTYP', '13', '3', 'OLE80195', 'OLE80215', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80196', 'OLE_INVCTYP', '13', '3', 'OLE80196', 'OLE80216', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80197', 'OLE_PMIPS', '13', '8', 'OLE80197', 'OLE80217', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80198', 'OLE_PMIPS', '13', '3', 'OLE80198', 'OLE80218', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80199', 'OLE_PMIPS', '13', '3', 'OLE80199', 'OLE80219', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80200', 'OLE_PMIPS', '13', '3', 'OLE80200', 'OLE80220', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80201', 'OLE_IRAD', '13', '8', 'OLE80201', 'OLE80221', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80202', 'OLE_IRAD', '13', '3', 'OLE80202', 'OLE80222', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80203', 'OLE_IRAD', '13', '3', 'OLE80203', 'OLE80223', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80204', 'OLE_IRAD', '13', '3', 'OLE80204', 'OLE80224', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80205', 'OLE_PMIT', '13', '8', 'OLE80205', 'OLE80225', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80206', 'OLE_PMIT', '13', '3', 'OLE80206', 'OLE80226', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80207', 'OLE_PMIT', '13', '3', 'OLE80207', 'OLE80227', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80208', 'OLE_PMIT', '13', '3', 'OLE80208', 'OLE80228', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80209', 'OLE_PMLI', '13', '8', 'OLE80209', 'OLE80229', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80210', 'OLE_PMLI', '13', '3', 'OLE80210', 'OLE80230', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80211', 'OLE_PMLI', '13', '3', 'OLE80211', 'OLE80231', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80212', 'OLE_PMLI', '13', '3', 'OLE80212', 'OLE80232', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80213', 'OLE_PMTM', '13', '8', 'OLE80213', 'OLE80233', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80214', 'OLE_PMTM', '13', '3', 'OLE80214', 'OLE80234', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80215', 'OLE_PMTM', '13', '3', 'OLE80215', 'OLE80235', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80216', 'OLE_PMTM', '13', '3', 'OLE80216', 'OLE80236', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80217', 'OLE_PMNP', '13', '8', 'OLE80217', 'OLE80237', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80218', 'OLE_PMNP', '13', '3', 'OLE80218', 'OLE80238', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80219', 'OLE_PMNP', '13', '3', 'OLE80219', 'OLE80239', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80220', 'OLE_PMNP', '13', '3', 'OLE80220', 'OLE80240', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80221', 'OLE_NOTETYP', '13', '8', 'OLE80221', 'OLE80241', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80222', 'OLE_NOTETYP', '13', '3', 'OLE80222', 'OLE80242', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80223', 'OLE_NOTETYP', '13', '3', 'OLE80223', 'OLE80243', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80224', 'OLE_NOTETYP', '13', '3', 'OLE80224', 'OLE80244', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80225', 'OLE_PMOP', '13', '8', 'OLE80225', 'OLE80245', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80226', 'OLE_PMOP', '13', '3', 'OLE80226', 'OLE80246', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80227', 'OLE_PMOP', '13', '3', 'OLE80227', 'OLE80247', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80228', 'OLE_PMOP', '13', '3', 'OLE80228', 'OLE80248', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80229', 'OLE_PPOT', '13', '8', 'OLE80229', 'OLE80249', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80230', 'OLE_PPOT', '13', '3', 'OLE80230', 'OLE80250', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80231', 'OLE_PPOT', '13', '3', 'OLE80231', 'OLE80251', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80232', 'OLE_PPOT', '13', '3', 'OLE80232', 'OLE80252', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80233', 'OLE_PYMNTMTHD', '13', '8', 'OLE80233', 'OLE80253', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80234', 'OLE_PYMNTMTHD', '13', '3', 'OLE80234', 'OLE80254', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80235', 'OLE_PYMNTMTHD', '13', '3', 'OLE80235', 'OLE80255', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80236', 'OLE_PYMNTMTHD', '13', '3', 'OLE80236', 'OLE80256', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80237', 'OLE_PMAA', '13', '8', 'OLE80237', 'OLE80257', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80238', 'OLE_PMAA', '13', '3', 'OLE80238', 'OLE80258', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80239', 'OLE_PMAA', '13', '3', 'OLE80239', 'OLE80259', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80240', 'OLE_PMAA', '13', '3', 'OLE80240', 'OLE80260', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80241', 'OLE_PMPR', '13', '8', 'OLE80241', 'OLE80261', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80242', 'OLE_PMPR', '13', '3', 'OLE80242', 'OLE80262', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80243', 'OLE_PMPR', '13', '3', 'OLE80243', 'OLE80263', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80244', 'OLE_PMPR', '13', '3', 'OLE80244', 'OLE80264', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80245', 'OLE_PMCL', '13', '8', 'OLE80245', 'OLE80265', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80246', 'OLE_PMCL', '13', '3', 'OLE80246', 'OLE80266', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80247', 'OLE_PMCL', '13', '3', 'OLE80247', 'OLE80267', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80248', 'OLE_PMCL', '13', '3', 'OLE80248', 'OLE80268', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80249', 'OLE_OIS', '13', '8', 'OLE80249', 'OLE80269', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80250', 'OLE_OIS', '13', '3', 'OLE80250', 'OLE80270', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80251', 'OLE_OIS', '13', '3', 'OLE80251', 'OLE80271', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80252', 'OLE_OIS', '13', '3', 'OLE80252', 'OLE80272', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80253', 'OLE_PMQL', '13', '8', 'OLE80253', 'OLE80273', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80254', 'OLE_PMQL', '13', '3', 'OLE80254', 'OLE80274', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80255', 'OLE_PMQL', '13', '3', 'OLE80255', 'OLE80275', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80256', 'OLE_PMQL', '13', '3', 'OLE80256', 'OLE80276', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80257', 'OLE_PMQT', '13', '8', 'OLE80257', 'OLE80277', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80258', 'OLE_PMQT', '13', '3', 'OLE80258', 'OLE80278', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80259', 'OLE_PMQT', '13', '3', 'OLE80259', 'OLE80279', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80260', 'OLE_PMQT', '13', '3', 'OLE80260', 'OLE80280', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80261', 'OLE_PMQS', '13', '8', 'OLE80261', 'OLE80281', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80262', 'OLE_PMQS', '13', '3', 'OLE80262', 'OLE80282', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80263', 'OLE_PMQS', '13', '3', 'OLE80263', 'OLE80283', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80264', 'OLE_PMQS', '13', '3', 'OLE80264', 'OLE80284', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80265', 'OLE_PMPS', '13', '8', 'OLE80265', 'OLE80285', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80266', 'OLE_PMPS', '13', '3', 'OLE80266', 'OLE80286', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80267', 'OLE_PMPS', '13', '3', 'OLE80267', 'OLE80287', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80268', 'OLE_PMPS', '13', '3', 'OLE80268', 'OLE80288', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80269', 'OLE_PMVC', '13', '8', 'OLE80269', 'OLE80289', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80270', 'OLE_PMVC', '13', '3', 'OLE80270', 'OLE80290', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80271', 'OLE_PMVC', '13', '3', 'OLE80271', 'OLE80291', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80272', 'OLE_PMVC', '13', '3', 'OLE80272', 'OLE80292', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80273', 'OLE_PMRA', '13', '8', 'OLE80273', 'OLE80293', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80274', 'OLE_PMRA', '13', '3', 'OLE80274', 'OLE80294', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80275', 'OLE_PMRA', '13', '3', 'OLE80275', 'OLE80295', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80276', 'OLE_PMRA', '13', '3', 'OLE80276', 'OLE80296', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80277', 'OLE_THLD', '13', '8', 'OLE80277', 'OLE80297', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80278', 'OLE_THLD', '13', '3', 'OLE80278', 'OLE80298', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80279', 'OLE_THLD', '13', '3', 'OLE80279', 'OLE80299', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80280', 'OLE_THLD', '13', '3', 'OLE80280', 'OLE80300', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80281', 'OLE_PMRF', '13', '8', 'OLE80281', 'OLE80301', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80282', 'OLE_PMRF', '13', '3', 'OLE80282', 'OLE80302', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80283', 'OLE_PMRF', '13', '3', 'OLE80283', 'OLE80303', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80284', 'OLE_PMRF', '13', '3', 'OLE80284', 'OLE80304', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80285', 'OLE_PMRP', '13', '8', 'OLE80285', 'OLE80305', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80286', 'OLE_PMRP', '13', '3', 'OLE80286', 'OLE80306', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80287', 'OLE_PMRP', '13', '3', 'OLE80287', 'OLE80307', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80288', 'OLE_PMRP', '13', '3', 'OLE80288', 'OLE80308', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80289', 'OLE_RSTMAT', '13', '8', 'OLE80289', 'OLE80309', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80290', 'OLE_RSTMAT', '13', '3', 'OLE80290', 'OLE80310', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80291', 'OLE_RSTMAT', '13', '3', 'OLE80291', 'OLE80311', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80292', 'OLE_RSTMAT', '13', '3', 'OLE80292', 'OLE80312', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80293', 'OLE_PMSO', '13', '8', 'OLE80293', 'OLE80313', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80294', 'OLE_PMSO', '13', '3', 'OLE80294', 'OLE80314', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80295', 'OLE_PMSO', '13', '3', 'OLE80295', 'OLE80315', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80296', 'OLE_PMSO', '13', '3', 'OLE80296', 'OLE80316', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80297', 'OLE_PMRS', '13', '8', 'OLE80297', 'OLE80317', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80298', 'OLE_PMRS', '13', '3', 'OLE80298', 'OLE80318', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80299', 'OLE_PMRS', '13', '3', 'OLE80299', 'OLE80319', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80300', 'OLE_PMRS', '13', '3', 'OLE80300', 'OLE80320', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80301', 'OLE_PMSN', '13', '8', 'OLE80301', 'OLE80321', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80302', 'OLE_PMSN', '13', '3', 'OLE80302', 'OLE80322', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80303', 'OLE_PMSN', '13', '3', 'OLE80303', 'OLE80323', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80304', 'OLE_PMSN', '13', '3', 'OLE80304', 'OLE80324', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80305', 'OLE_PMSI', '13', '8', 'OLE80305', 'OLE80325', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80306', 'OLE_PMSI', '13', '3', 'OLE80306', 'OLE80326', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80307', 'OLE_PMSI', '13', '3', 'OLE80307', 'OLE80327', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80308', 'OLE_PMSI', '13', '3', 'OLE80308', 'OLE80328', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80309', 'OLE_LICRQMT', '13', '8', 'OLE80309', 'OLE80329', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80310', 'OLE_LICRQMT', '13', '3', 'OLE80310', 'OLE80330', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80311', 'OLE_LICRQMT', '13', '3', 'OLE80311', 'OLE80331', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80312', 'OLE_LICRQMT', '13', '3', 'OLE80312', 'OLE80332', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80313', 'OLE_PREQ', '13', '3', 'OLE80313', 'OLE80333', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80314', 'OLE_GACC', '13', '8', 'OLE80314', 'OLE80334', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80315', 'OLE_GACC', '13', '3', 'OLE80315', 'OLE80335', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80316', 'OLE_GACC', '13', '3', 'OLE80316', 'OLE80336', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80317', 'OLE_GACC', '13', '3', 'OLE80317', 'OLE80337', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80318', 'OLE_ADEL', '13', '8', 'OLE80318', 'OLE80338', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80319', 'OLE_ADEL', '13', '3', 'OLE80319', 'OLE80339', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80320', 'OLE_ADEL', '13', '3', 'OLE80320', 'OLE80340', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80321', 'OLE_ADEL', '13', '3', 'OLE80321', 'OLE80341', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80322', 'OLE_GDLG', '13', '8', 'OLE80322', 'OLE80342', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80323', 'OLE_GDLG', '13', '3', 'OLE80323', 'OLE80343', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80324', 'OLE_GDLG', '13', '3', 'OLE80324', 'OLE80344', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80325', 'OLE_GDLG', '13', '3', 'OLE80325', 'OLE80345', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80326', 'OLE_GDLM', '13', '8', 'OLE80326', 'OLE80346', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80327', 'OLE_GDLM', '13', '3', 'OLE80327', 'OLE80347', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80328', 'OLE_GDLM', '13', '3', 'OLE80328', 'OLE80348', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80329', 'OLE_GDLM', '13', '3', 'OLE80329', 'OLE80349', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80330', 'OLE_GOBJ', '13', '8', 'OLE80330', 'OLE80350', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80331', 'OLE_GOBJ', '13', '3', 'OLE80331', 'OLE80351', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80332', 'OLE_GOBJ', '13', '3', 'OLE80332', 'OLE80352', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80333', 'OLE_GOBJ', '13', '3', 'OLE80333', 'OLE80353', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80334', 'OLE_OR', '13', '8', 'OLE80334', 'OLE80354', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80335', 'OLE_OR', '13', '3', 'OLE80335', 'OLE80355', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80336', 'OLE_OR', '13', '3', 'OLE80336', 'OLE80356', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80337', 'OLE_OR', '13', '3', 'OLE80337', 'OLE80357', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80338', 'OLE_PROJ', '13', '8', 'OLE80338', 'OLE80358', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80339', 'OLE_PROJ', '13', '3', 'OLE80339', 'OLE80359', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80340', 'OLE_PROJ', '13', '3', 'OLE80340', 'OLE80360', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80341', 'OLE_PROJ', '13', '3', 'OLE80341', 'OLE80361', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80342', 'OLE_SACC', '13', '8', 'OLE80342', 'OLE80362', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80343', 'OLE_SACC', '13', '3', 'OLE80343', 'OLE80363', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80344', 'OLE_SACC', '13', '3', 'OLE80344', 'OLE80364', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80345', 'OLE_SACC', '13', '3', 'OLE80345', 'OLE80365', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80346', 'OLE_SOBJ', '13', '8', 'OLE80346', 'OLE80366', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80347', 'OLE_SOBJ', '13', '3', 'OLE80347', 'OLE80367', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80348', 'OLE_SOBJ', '13', '3', 'OLE80348', 'OLE80368', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80349', 'OLE_SOBJ', '13', '3', 'OLE80349', 'OLE80369', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80350', 'OLE_GSOB', '13', '8', 'OLE80350', 'OLE80370', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80351', 'OLE_GSOB', '13', '3', 'OLE80351', 'OLE80371', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80352', 'OLE_GSOB', '13', '3', 'OLE80352', 'OLE80372', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80353', 'OLE_GSOB', '13', '3', 'OLE80353', 'OLE80373', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80354', 'OLE_ATYP', '13', '8', 'OLE80354', 'OLE80374', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80355', 'OLE_ATYP', '13', '3', 'OLE80355', 'OLE80375', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80356', 'OLE_ATYP', '13', '3', 'OLE80356', 'OLE80376', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80357', 'OLE_ATYP', '13', '3', 'OLE80357', 'OLE80377', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80358', 'OLE_APRD', '13', '8', 'OLE80358', 'OLE80378', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80359', 'OLE_APRD', '13', '3', 'OLE80359', 'OLE80379', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80360', 'OLE_APRD', '13', '3', 'OLE80360', 'OLE80380', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80361', 'OLE_APRD', '13', '3', 'OLE80361', 'OLE80381', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80362', 'OLE_AFUN', '13', '8', 'OLE80362', 'OLE80382', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80363', 'OLE_AFUN', '13', '3', 'OLE80363', 'OLE80383', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80364', 'OLE_AFUN', '13', '3', 'OLE80364', 'OLE80384', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80365', 'OLE_AFUN', '13', '3', 'OLE80365', 'OLE80385', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80366', 'OLE_BTYP', '13', '8', 'OLE80366', 'OLE80386', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80367', 'OLE_BTYP', '13', '3', 'OLE80367', 'OLE80387', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80368', 'OLE_BTYP', '13', '3', 'OLE80368', 'OLE80388', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80369', 'OLE_BTYP', '13', '3', 'OLE80369', 'OLE80389', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80370', 'OLE_ACTY', '13', '8', 'OLE80370', 'OLE80390', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80371', 'OLE_ACTY', '13', '3', 'OLE80371', 'OLE80391', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80372', 'OLE_ACTY', '13', '3', 'OLE80372', 'OLE80392', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80373', 'OLE_ACTY', '13', '3', 'OLE80373', 'OLE80393', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80374', 'OLE_BAMD', '13', '8', 'OLE80374', 'OLE80394', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80375', 'OLE_BAMD', '13', '3', 'OLE80375', 'OLE80395', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80376', 'OLE_BAMD', '13', '3', 'OLE80376', 'OLE80396', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80377', 'OLE_BAMD', '13', '3', 'OLE80377', 'OLE80397', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80378', 'OLE_BRL', '13', '8', 'OLE80378', 'OLE80398', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80379', 'OLE_BRL', '13', '3', 'OLE80379', 'OLE80399', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80380', 'OLE_BRL', '13', '3', 'OLE80380', 'OLE80400', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80381', 'OLE_BRL', '13', '3', 'OLE80381', 'OLE80401', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80382', 'OLE_FFUN', '13', '8', 'OLE80382', 'OLE80402', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80383', 'OLE_FFUN', '13', '3', 'OLE80383', 'OLE80403', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80384', 'OLE_FFUN', '13', '3', 'OLE80384', 'OLE80404', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80385', 'OLE_FFUN', '13', '3', 'OLE80385', 'OLE80405', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80386', 'OLE_FGRP', '13', '8', 'OLE80386', 'OLE80406', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80387', 'OLE_FGRP', '13', '3', 'OLE80387', 'OLE80407', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80388', 'OLE_FGRP', '13', '3', 'OLE80388', 'OLE80408', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80389', 'OLE_FGRP', '13', '3', 'OLE80389', 'OLE80409', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80390', 'OLE_HEFN', '13', '8', 'OLE80390', 'OLE80410', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80391', 'OLE_HEFN', '13', '3', 'OLE80391', 'OLE80411', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80392', 'OLE_HEFN', '13', '3', 'OLE80392', 'OLE80412', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80393', 'OLE_HEFN', '13', '3', 'OLE80393', 'OLE80413', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80394', 'OLE_MTE', '13', '8', 'OLE80394', 'OLE80414', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80395', 'OLE_MTE', '13', '3', 'OLE80395', 'OLE80415', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80396', 'OLE_MTE', '13', '3', 'OLE80396', 'OLE80416', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80397', 'OLE_MTE', '13', '3', 'OLE80397', 'OLE80417', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80398', 'OLE_OBJC', '13', '8', 'OLE80398', 'OLE80418', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80399', 'OLE_OBJC', '13', '3', 'OLE80399', 'OLE80419', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80400', 'OLE_OBJC', '13', '3', 'OLE80400', 'OLE80420', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80401', 'OLE_OBJC', '13', '3', 'OLE80401', 'OLE80421', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80402', 'OLE_OBJL', '13', '8', 'OLE80402', 'OLE80422', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80403', 'OLE_OBJL', '13', '3', 'OLE80403', 'OLE80423', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80404', 'OLE_OBJL', '13', '3', 'OLE80404', 'OLE80424', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80405', 'OLE_OBJL', '13', '3', 'OLE80405', 'OLE80425', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80406', 'OLE_OSTY', '13', '8', 'OLE80406', 'OLE80426', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80407', 'OLE_OSTY', '13', '3', 'OLE80407', 'OLE80427', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80408', 'OLE_OSTY', '13', '3', 'OLE80408', 'OLE80428', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80409', 'OLE_OSTY', '13', '3', 'OLE80409', 'OLE80429', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80410', 'OLE_OTYP', '13', '8', 'OLE80410', 'OLE80430', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80411', 'OLE_OTYP', '13', '3', 'OLE80411', 'OLE80431', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80412', 'OLE_OTYP', '13', '3', 'OLE80412', 'OLE80432', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80413', 'OLE_OTYP', '13', '3', 'OLE80413', 'OLE80433', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80414', 'OLE_OFAC', '13', '8', 'OLE80414', 'OLE80434', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80415', 'OLE_OFAC', '13', '3', 'OLE80415', 'OLE80435', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80416', 'OLE_OFAC', '13', '3', 'OLE80416', 'OLE80436', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80417', 'OLE_OFAC', '13', '3', 'OLE80417', 'OLE80437', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80418', 'OLE_OFSD', '13', '8', 'OLE80418', 'OLE80438', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80419', 'OLE_OFSD', '13', '3', 'OLE80419', 'OLE80439', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80420', 'OLE_OFSD', '13', '3', 'OLE80420', 'OLE80440', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80421', 'OLE_OFSD', '13', '3', 'OLE80421', 'OLE80441', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80422', 'OLE_ORGC', '13', '8', 'OLE80422', 'OLE80442', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80423', 'OLE_ORGC', '13', '3', 'OLE80423', 'OLE80443', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80424', 'OLE_ORGC', '13', '3', 'OLE80424', 'OLE80444', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80425', 'OLE_ORGC', '13', '3', 'OLE80425', 'OLE80445', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80426', 'OLE_ORGR', '13', '8', 'OLE80426', 'OLE80446', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80427', 'OLE_ORGR', '13', '3', 'OLE80427', 'OLE80447', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80428', 'OLE_ORGR', '13', '3', 'OLE80428', 'OLE80448', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80429', 'OLE_ORGR', '13', '3', 'OLE80429', 'OLE80449', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80430', 'OLE_CNSRTTYP', '13', '8', 'OLE80430', 'OLE80450', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80431', 'OLE_CNSRTTYP', '13', '3', 'OLE80431', 'OLE80451', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80432', 'OLE_CNSRTTYP', '13', '3', 'OLE80432', 'OLE80452', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80433', 'OLE_CNSRTTYP', '13', '3', 'OLE80433', 'OLE80453', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80434', 'OLE_GORV', '13', '8', 'OLE80434', 'OLE80454', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80435', 'OLE_GORV', '13', '3', 'OLE80435', 'OLE80455', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80436', 'OLE_GORV', '13', '3', 'OLE80436', 'OLE80456', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80437', 'OLE_GORV', '13', '3', 'OLE80437', 'OLE80457', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80438', 'OLE_ORTY', '13', '8', 'OLE80438', 'OLE80458', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80439', 'OLE_ORTY', '13', '3', 'OLE80439', 'OLE80459', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80440', 'OLE_ORTY', '13', '3', 'OLE80440', 'OLE80460', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80441', 'OLE_ORTY', '13', '3', 'OLE80441', 'OLE80461', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80442', 'OLE_RPTC', '13', '8', 'OLE80442', 'OLE80462', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80443', 'OLE_RPTC', '13', '3', 'OLE80443', 'OLE80463', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80444', 'OLE_RPTC', '13', '3', 'OLE80444', 'OLE80464', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80445', 'OLE_RPTC', '13', '3', 'OLE80445', 'OLE80465', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80446', 'OLE_RCEN', '13', '8', 'OLE80446', 'OLE80466', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80447', 'OLE_RCEN', '13', '3', 'OLE80447', 'OLE80467', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80448', 'OLE_RCEN', '13', '3', 'OLE80448', 'OLE80468', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80449', 'OLE_RCEN', '13', '3', 'OLE80449', 'OLE80469', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80450', 'OLE_RSTA', '13', '8', 'OLE80450', 'OLE80470', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80451', 'OLE_RSTA', '13', '3', 'OLE80451', 'OLE80471', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80452', 'OLE_RSTA', '13', '3', 'OLE80452', 'OLE80472', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80453', 'OLE_RSTA', '13', '3', 'OLE80453', 'OLE80473', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80454', 'OLE_SFGR', '13', '8', 'OLE80454', 'OLE80474', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80455', 'OLE_SFGR', '13', '3', 'OLE80455', 'OLE80475', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80456', 'OLE_SFGR', '13', '3', 'OLE80456', 'OLE80476', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80457', 'OLE_SFGR', '13', '3', 'OLE80457', 'OLE80477', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80458', 'OLE_SFGT', '13', '8', 'OLE80458', 'OLE80478', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80459', 'OLE_SFGT', '13', '3', 'OLE80459', 'OLE80479', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80460', 'OLE_SFGT', '13', '3', 'OLE80460', 'OLE80480', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80461', 'OLE_SFGT', '13', '3', 'OLE80461', 'OLE80481', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80462', 'OLE_SFC', '13', '8', 'OLE80462', 'OLE80482', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80463', 'OLE_SFC', '13', '3', 'OLE80463', 'OLE80483', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80464', 'OLE_SFC', '13', '3', 'OLE80464', 'OLE80484', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80465', 'OLE_SFC', '13', '3', 'OLE80465', 'OLE80485', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80466', 'OLE_SFCTYP', '13', '8', 'OLE80466', 'OLE80486', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80467', 'OLE_SFCTYP', '13', '3', 'OLE80467', 'OLE80487', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80468', 'OLE_SFCTYP', '13', '3', 'OLE80468', 'OLE80488', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80469', 'OLE_SFCTYP', '13', '3', 'OLE80469', 'OLE80489', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80470', 'OLE_SRYP', '13', '8', 'OLE80470', 'OLE80490', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80471', 'OLE_SRYP', '13', '3', 'OLE80471', 'OLE80491', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80472', 'OLE_SRYP', '13', '3', 'OLE80472', 'OLE80492', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80473', 'OLE_SRYP', '13', '3', 'OLE80473', 'OLE80493', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80474', 'OLE_UFUN', '13', '8', 'OLE80474', 'OLE80494', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80475', 'OLE_UFUN', '13', '3', 'OLE80475', 'OLE80495', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80476', 'OLE_UFUN', '13', '3', 'OLE80476', 'OLE80496', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80477', 'OLE_UFUN', '13', '3', 'OLE80477', 'OLE80497', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80478', 'OLE_COAT ', '13', '3', 'OLE80478', 'OLE80498', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80479', 'OLE_COAT ', '13', '3', 'OLE80479', 'OLE80499', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80480', 'OLE_ACCT ', '13', '3', 'OLE80480', 'OLE80500', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80481', 'OLE_ACCT ', '13', '3', 'OLE80481', 'OLE80501', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80482', 'OLE_OBJT ', '13', '3', 'OLE80482', 'OLE80502', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80483', 'OLE_OBJT ', '13', '3', 'OLE80483', 'OLE80503', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80484', 'OLE_ORGN ', '13', '3', 'OLE80484', 'OLE80504', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80485', 'OLE_ORGN', '13', '3', 'OLE80485', 'OLE80505', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80486', 'OLE_PMUM', '13', '8', 'OLE80486', 'OLE80506', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80487', 'OLE_PMUM', '13', '3', 'OLE80487', 'OLE80507', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80488', 'OLE_PMUM', '13', '3', 'OLE80488', 'OLE80508', '1')
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, PERM_ID, VER_NBR) VALUES ('OLE80489', 'OLE_PMUM', '13', '3', 'OLE80489', 'OLE80509', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_KRIM_PERM_ATTR_DATA_T', '2.0.5', '3:20fba9c7d4f998d0cfc8faa0051edc81', 18)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_KRIM_RSP_T::ole::(Checksum: 3:bb54e69d11f12e72095bb95bc5da967f)
INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_REQS ShowDuplicateRecords', 'OLE-SELECT', 'OLE10002', 'OLE990002', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_PVEN ForInformation', 'OLE_SELECT', 'OLE10009', 'OLE990009', '1', '3')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_REQS GreaterLineItem', 'OLE-SELECT', 'OLE10016', 'OLE990016', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_POA OrderChange', 'OLE-SELECT', 'OLE10018', 'OLE990018', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_REQS NewVendor', 'OLE-SELECT', 'OLE10019', 'OLE990019', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_RCVL ApproveReceiveLineItem', 'OLE-SELECT', 'OLE990020', 'OLE990020', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_RCVC ApproveReceiveLineItemCorrection', 'OLE-SELECT', 'OLE990021', 'OLE990021', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_PREQ Payment', 'OLE-SELECT', 'OLE990022', 'OLE990022', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_REQS NoVendor', 'OLE-SELECT', 'OLE990023', 'OLE990023', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_POA Tax', 'OLE-PURAP', 'OLE990024', 'OLE990024', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_ACQBTHUPLOAD Approval', 'OLE-SELECT', 'OLE990025', 'OLE990025', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving the Commodity Code Document', 'Review OLE_PMCC CommodityCodeApprover', 'OLE-VND', '80004', 'OLE80004', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving the Cost Source Document', 'Review OLE_PMCS CostSourceApprover', 'OLE-VND', '80006', 'OLE80006', '1', '3')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving ContactType Document', 'Review OLE_PMCT ContactTypeApprover', 'OLE-VND', '80008', 'OLE80008', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approver for Category Document', 'Review OLE_PMC CategoryApprover', 'OLE-PURAP', '80009', 'OLE80009', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approver for Exception Type Document', 'Review OLE_EXCTYP ExceptionTypeApprover', 'OLE-PURAP', '80010', 'OLE80010', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving Format Type', 'Review OLE_FTMAT FormatTypeApprover', 'OLE-PURAP', '80012', 'OLE80012', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving Note Type Document', 'Review OLE_NOTETYP NoteTypeApprover', 'OLE-VND', '80013', 'OLE80013', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving OrderType Document', 'Review OLE_PPOT PurchaseOrderTypeApprover', 'OLE-PURAP', '80015', 'OLE80015', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving OrderItemStatus Document', 'Review OLE_OIS OrderItemApprover', 'OLE-VND', '80018', 'OLE80018', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving RequestSourceType Document', 'Review OLE_RSTMAT RequestSourceTypeApprover', 'OLE-VND', '80019', 'OLE80019', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving ItemPriceSource Document', 'Review OLE_PMIPS ItemPriceSourceApprover', 'OLE-PURAP', '80020', 'OLE80020', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'For FYI', 'Review OLE_ACQBTHUPLOAD ForFYI', 'OLE-SELECT', '80021', 'OLE80021', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_REQS LicenseRequest', 'OLE-SELECT', 'OLE990026', 'OLE990026', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving the Credit Memo Document with Prepaid Invoice Type', 'Review OLE_CM PrepaidInvoiceType', 'OLE-SELECT', 'OLE990027', 'OLE990027', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving the Credit Memo Document with Invoice Type other than ''Prepaid''', 'Review OLE_CM NoPrepaidInvoiceType', 'OLE-SELECT', 'OLE990028', 'OLE990028', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving the document Vendor Credit Memo containing the Payment Method', 'Review OLE_CM PaymentMethod', 'OLE-SELECT', 'OLE990029', 'OLE990029', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving the Payment Request Document with Prepaid Invoice Type', 'Review OLE_PREQ PrepaidInvoiceType', 'OLE-SELECT', 'OLE990030', 'OLE990030', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving the Payment  Document with Invoice Type other than ''Prepaid''', 'Review OLE_PREQ NoPrepaidInvoiceType', 'OLE-SELECT', 'OLE990031', 'OLE990031', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_DI FinancialAccount', 'OLE-SELECT', 'OLE990032', 'OLE990032', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_DI VendorDepositAccount', 'OLE-SELECT', 'OLE990033', 'OLE990033', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_DV ClearingAccountType', 'OLE-SELECT', 'OLE990034', 'OLE990034', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'Approving the Disbursement Document with the Payment Method', 'Review OLE_DV Payment', 'OLE-SELECT', 'OLE990035', 'OLE990035', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_DV InvoiceType', 'OLE-SELECT', 'OLE990036', 'OLE990036', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_GEC FinancialAccount', 'OLE-SELECT', 'OLE990037', 'OLE990037', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_GEC VendorDepositAccount', 'OLE-SELECT', 'OLE990038', 'OLE990038', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_PREQ VendorDepositAccount', 'OLE-SELECT', 'OLE990039', 'OLE990039', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_CM FiscalOfficerReview', 'OLE-SELECT', 'OLE990040', 'OLE990040', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_ACCT DepositAccount', 'OLE-SELECT', 'OLE990041', 'OLE990041', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', '', 'Review OLE_PREQ SeparationOfDuties', 'OLE-SELECT', 'OLE990042', 'OLE990042', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'For FYI', 'Review OLE_BA BudgetAdjustment', 'OLE-SELECT', 'OLE990043', 'OLE990043', '1', '1')
/

INSERT INTO KRIM_RSP_T (ACTV_IND, DESC_TXT, NM, NMSPC_CD, OBJ_ID, RSP_ID, RSP_TMPL_ID, VER_NBR) VALUES ('Y', 'For FYI', 'Review OLE_TF TransferFund', 'OLE-SELECT', 'OLE990044', 'OLE990044', '1', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_KRIM_RSP_T', '2.0.5', '3:bb54e69d11f12e72095bb95bc5da967f', 19)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_KRIM_RSP_ATTR_DATA_T::ole::(Checksum: 3:c30547796600b277dd9ed1054edd7f8d)
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10026', 'FALSE', '41', '7', 'OLE10026', 'OLE990002', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10027', 'ShowDuplicateRecords', '16', '7', 'OLE10027', 'OLE990002', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10028', 'OLE_REQS', '13', '7', 'OLE10028', 'OLE990002', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10029', 'FALSE', '40', '7', 'OLE10029', 'OLE990002', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10038', 'FALSE', '41', '7', 'OLE10038', 'OLE990009', '3')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10039', 'ForInformation', '16', '7', 'OLE10039', 'OLE990009', '3')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10040', 'OLE_PVEN', '13', '7', 'OLE10040', 'OLE990009', '3')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10041', 'FALSE', '40', '7', 'OLE10041', 'OLE990009', '3')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10057', 'FALSE', '41', '7', 'OLE10057', 'OLE990016', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10058', 'GreaterLineItem', '16', '7', 'OLE10058', 'OLE990016', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10059', 'OLE_REQS', '13', '7', 'OLE10059', 'OLE990016', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10060', 'FALSE', '40', '7', 'OLE10060', 'OLE990016', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10065', 'FALSE', '41', '7', 'OLE10065', 'OLE990018', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10066', 'OrderChange', '16', '7', 'OLE10066', 'OLE990018', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10067', 'OLE_POA', '13', '7', 'OLE10067', 'OLE990018', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10068', 'FALSE', '40', '7', 'OLE10068', 'OLE990018', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10069', 'FALSE', '41', '7', 'OLE10069', 'OLE990019', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10070', 'NewVendor', '16', '7', 'OLE10070', 'OLE990019', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10071', 'OLE_REQS', '13', '7', 'OLE10071', 'OLE990019', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10072', 'FALSE', '40', '7', 'OLE10072', 'OLE990019', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10073', 'FALSE', '41', '7', 'OLE10073', 'OLE990020', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10074', 'ApproveReceiveLineItem', '16', '7', 'OLE10074', 'OLE990020', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10075', 'OLE_RCVL', '13', '7', 'OLE10075', 'OLE990020', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10076', 'FALSE', '40', '7', 'OLE10076', 'OLE990020', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10077', 'FALSE', '41', '7', 'OLE10077', 'OLE990021', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10078', 'ApproveReceiveLineItemCorrection', '16', '7', 'OLE10078', 'OLE990021', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10079', 'OLE_RCVC', '13', '7', 'OLE10079', 'OLE990021', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10080', 'FALSE', '40', '7', 'OLE10080', 'OLE990021', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10081', 'FALSE', '41', '7', 'OLE10081', 'OLE990022', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10082', 'Payment', '16', '7', 'OLE10082', 'OLE990022', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10083', 'OLE_PREQ', '13', '7', 'OLE10083', 'OLE990022', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10084', 'TRUE', '40', '7', 'OLE10084', 'OLE990022', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10085', 'FALSE', '41', '7', 'OLE10085', 'OLE990023', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10086', 'NoVendor', '16', '7', 'OLE10086', 'OLE990023', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10087', 'OLE_REQS', '13', '7', 'OLE10087', 'OLE990023', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10088', 'TRUE', '40', '7', 'OLE10088', 'OLE990023', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10089', 'Tax', '16', '7', 'OLE10089', 'OLE990024', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10090', 'OLE_POA', '13', '7', 'OLE10090', 'OLE990024', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10091', 'FALSE', '41', '7', 'OLE10091', 'OLE990024', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10092', 'FALSE', '40', '7', 'OLE10092', 'OLE990024', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10093', 'Approval', '16', '7', 'OLE10093', 'OLE990025', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10094', 'OLE_ACQBTHUPLOAD', '13', '7', 'OLE10094', 'OLE990025', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10095', 'TRUE', '41', '7', 'OLE10095', 'OLE990025', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10096', 'FALSE', '40', '7', 'OLE10096', 'OLE990025', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80066', 'FALSE', '41', '7', 'OLE80066', 'OLE80004', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80067', 'CommodityCodeApprover', '16', '7', 'OLE80067', 'OLE80004', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80068', 'OLE_PMCC', '13', '7', 'OLE80068', 'OLE80004', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80069', 'FALSE', '40', '7', 'OLE80069', 'OLE80004', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80070', 'FALSE', '41', '7', 'OLE80070', 'OLE80006', '3')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80071', 'CostSourceApprover', '16', '7', 'OLE80071', 'OLE80006', '3')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80072', 'OLE_PMCS', '13', '7', 'OLE80072', 'OLE80006', '3')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80073', 'FALSE', '40', '7', 'OLE80073', 'OLE80006', '3')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80074', 'FALSE', '41', '7', 'OLE80074', 'OLE80008', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80075', 'ContactTypeApprover', '16', '7', 'OLE80075', 'OLE80008', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80076', 'OLE_PMCT', '13', '7', 'OLE80076', 'OLE80008', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80077', 'FALSE', '40', '7', 'OLE80077', 'OLE80008', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80078', 'FALSE', '41', '7', 'OLE80078', 'OLE80009', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80079', 'CategoryApprover', '16', '7', 'OLE80079', 'OLE80009', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80080', 'OLE_PMC', '13', '7', 'OLE80080', 'OLE80009', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80081', 'FALSE', '40', '7', 'OLE80081', 'OLE80009', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80082', 'FALSE', '41', '7', 'OLE80082', 'OLE80010', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80083', 'ExceptionTypeApprover', '16', '7', 'OLE80083', 'OLE80010', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80084', 'OLE_EXCTYP', '13', '7', 'OLE80084', 'OLE80010', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80085', 'FALSE', '40', '7', 'OLE80085', 'OLE80010', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80086', 'FALSE', '41', '7', 'OLE80086', 'OLE80012', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80087', 'FormatTypeApprover', '16', '7', 'OLE80087', 'OLE80012', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80088', 'OLE_FTMAT', '13', '7', 'OLE80088', 'OLE80012', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80089', 'FALSE', '40', '7', 'OLE80089', 'OLE80012', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80090', 'FALSE', '41', '7', 'OLE80090', 'OLE80013', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80091', 'NoteTypeApprover', '16', '7', 'OLE80091', 'OLE80013', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80092', 'OLE_NOTETYP', '13', '7', 'OLE80092', 'OLE80013', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80093', 'FALSE', '40', '7', 'OLE80093', 'OLE80013', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80094', 'FALSE', '41', '7', 'OLE80094', 'OLE80015', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80095', 'PurchaseOrderTypeApprover', '16', '7', 'OLE80095', 'OLE80015', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80096', 'OLE_PPOT', '13', '7', 'OLE80096', 'OLE80015', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80097', 'FALSE', '40', '7', 'OLE80097', 'OLE80015', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80098', 'FALSE', '41', '7', 'OLE80098', 'OLE80018', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80099', 'OrderItemApprover', '16', '7', 'OLE80099', 'OLE80018', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80100', 'OLE_OIS', '13', '7', 'OLE80100', 'OLE80018', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80101', 'FALSE', '40', '7', 'OLE80101', 'OLE80018', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80102', 'FALSE', '41', '7', 'OLE80102', 'OLE80019', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80103', 'RequestSourceTypeApprover', '16', '7', 'OLE80103', 'OLE80019', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80104', 'OLE_RSTMAT', '13', '7', 'OLE80104', 'OLE80019', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80105', 'FALSE', '40', '7', 'OLE80105', 'OLE80019', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80106', 'FALSE', '41', '7', 'OLE80106', 'OLE80020', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80107', 'ItemPriceSourceApprover', '16', '7', 'OLE80107', 'OLE80020', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80108', 'OLE_PMIPS', '13', '7', 'OLE80108', 'OLE80020', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80109', 'FALSE', '40', '7', 'OLE80109', 'OLE80020', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80110', 'ForFYI', '16', '7', 'OLE80110', 'OLE80021', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80111', 'OLE_ACQBTHUPLOAD', '13', '7', 'OLE80111', 'OLE80021', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80112', 'TRUE', '41', '7', 'OLE80112', 'OLE80021', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE80113', 'FALSE', '40', '7', 'OLE80113', 'OLE80021', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10097', 'OLE_REQS', '13', '7', 'OLE10097', 'OLE990026', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10098', 'TRUE', '40', '7', 'OLE10098', 'OLE990026', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10099', 'TRUE', '41', '7', 'OLE10099', 'OLE990026', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10100', 'LicenseRequest', '16', '7', 'OLE10100', 'OLE990026', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10101', 'false', '41', '7', 'OLE10101', 'OLE990027', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10102', 'PrepaidInvoiceType', '16', '7', 'OLE10102', 'OLE990027', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10103', 'OLE_CM', '13', '7', 'OLE10103', 'OLE990027', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10104', 'false', '40', '7', 'OLE10104', 'OLE990027', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10105', 'false', '41', '7', 'OLE10105', 'OLE990028', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10106', 'NoPrepaidInvoiceType', '16', '7', 'OLE10106', 'OLE990028', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10107', 'OLE_CM', '13', '7', 'OLE10107', 'OLE990028', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10108', 'false', '40', '7', 'OLE10108', 'OLE990028', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10109', 'false', '41', '7', 'OLE10109', 'OLE990029', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10110', 'PaymentMethod', '16', '7', 'OLE10110', 'OLE990029', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10111', 'OLE_CM', '13', '7', 'OLE10111', 'OLE990029', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10112', 'false', '40', '7', 'OLE10112', 'OLE990029', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10113', 'false', '41', '7', 'OLE10113', 'OLE990030', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10114', 'PrepaidInvoiceType', '16', '7', 'OLE10114', 'OLE990030', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10115', 'OLE_PREQ', '13', '7', 'OLE10115', 'OLE990030', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10116', 'false', '40', '7', 'OLE10116', 'OLE990030', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10117', 'false', '41', '7', 'OLE10117', 'OLE990031', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10118', 'NoPrepaidInvoiceType', '16', '7', 'OLE10118', 'OLE990031', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10119', 'OLE_PREQ', '13', '7', 'OLE10119', 'OLE990031', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10120', 'false', '40', '7', 'OLE10120', 'OLE990031', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10121', 'false', '41', '7', 'OLE10121', 'OLE990032', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10122', 'FinancialAccount', '16', '7', 'OLE10122', 'OLE990032', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10123', 'OLE_DI', '13', '7', 'OLE10123', 'OLE990032', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10124', 'false', '40', '7', 'OLE10124', 'OLE990032', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10125', 'false', '41', '7', 'OLE10125', 'OLE990033', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10126', 'VendorDepositAccount', '16', '7', 'OLE10126', 'OLE990033', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10127', 'OLE_DI', '13', '7', 'OLE10127', 'OLE990033', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10128', 'false', '40', '7', 'OLE10128', 'OLE990033', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10129', 'false', '41', '7', 'OLE10129', 'OLE990034', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10130', 'ClearingAccountType', '16', '7', 'OLE10130', 'OLE990034', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10131', 'OLE_DV', '13', '7', 'OLE10131', 'OLE990034', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10132', 'false', '40', '7', 'OLE10132', 'OLE990034', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10133', 'false', '41', '7', 'OLE10133', 'OLE990035', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10134', 'Payment', '16', '7', 'OLE10134', 'OLE990035', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10135', 'OLE_DV', '13', '7', 'OLE10135', 'OLE990035', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10136', 'false', '40', '7', 'OLE10136', 'OLE990035', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10137', 'false', '41', '7', 'OLE10137', 'OLE990036', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10138', 'InvoiceType', '16', '7', 'OLE10138', 'OLE990036', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10139', 'OLE_DV', '13', '7', 'OLE10139', 'OLE990036', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10140', 'false', '40', '7', 'OLE10140', 'OLE990036', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10141', 'false', '41', '7', 'OLE10141', 'OLE990037', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10142', 'FinancialAccount', '16', '7', 'OLE10142', 'OLE990037', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10143', 'OLE_GEC', '13', '7', 'OLE10143', 'OLE990037', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10144', 'false', '40', '7', 'OLE10144', 'OLE990037', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10145', 'false', '41', '7', 'OLE10145', 'OLE990038', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10146', 'VendorDepositAccount', '16', '7', 'OLE10146', 'OLE990038', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10147', 'OLE_GEC', '13', '7', 'OLE10147', 'OLE990038', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10148', 'false', '40', '7', 'OLE10148', 'OLE990038', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10149', 'false', '41', '7', 'OLE10149', 'OLE990039', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10150', 'VendorDepositAccount', '16', '7', 'OLE10150', 'OLE990039', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10151', 'OLE_PREQ', '13', '7', 'OLE10151', 'OLE990039', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10152', 'false', '40', '7', 'OLE10152', 'OLE990039', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10153', 'false', '41', '7', 'OLE10153', 'OLE990040', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10154', 'FiscalOfficerReview', '16', '7', 'OLE10154', 'OLE990040', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10155', 'OLE_CM', '13', '7', 'OLE10155', 'OLE990040', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10156', 'false', '41', '7', 'OLE10156', 'OLE990040', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10157', 'false', '41', '7', 'OLE10157', 'OLE990041', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10158', 'DepositAccount', '16', '7', 'OLE10158', 'OLE990041', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10159', 'OLE_ACCT', '13', '7', 'OLE10159', 'OLE990041', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10160', 'false', '41', '7', 'OLE10160', 'OLE990041', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10161', 'false', '41', '7', 'OLE10161', 'OLE990042', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10162', 'SeparationOfDuties', '16', '7', 'OLE10162', 'OLE990042', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10163', 'OLE_PREQ', '13', '7', 'OLE10163', 'OLE990042', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10164', 'false', '41', '7', 'OLE10164', 'OLE990042', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10165', 'false', '41', '7', 'OLE10165', 'OLE990043', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10166', 'BudgetAdjustment', '16', '7', 'OLE10166', 'OLE990043', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10167', 'OLE_BA', '13', '7', 'OLE10167', 'OLE990043', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10168', 'false', '41', '7', 'OLE10168', 'OLE990043', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10169', 'false', '41', '7', 'OLE10169', 'OLE990044', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10170', 'TransferFund', '16', '7', 'OLE10170', 'OLE990044', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10171', 'OLE_TF', '13', '7', 'OLE10171', 'OLE990044', '1')
/

INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, ATTR_VAL, KIM_ATTR_DEFN_ID, KIM_TYP_ID, OBJ_ID, RSP_ID, VER_NBR) VALUES ('OLE10172', 'false', '41', '7', 'OLE10172', 'OLE990044', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_KRIM_RSP_ATTR_DATA_T', '2.0.5', '3:c30547796600b277dd9ed1054edd7f8d', 20)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_LOAD_OLE_KRIM_ROLE_T::ole::(Checksum: 3:05e09d0d96403ded32a5f3e801b04408)
INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role allows the User to search the Documents', 'OLE36', 'OLE-SELECT', 'OLE10000', 'OLE10000', 'OLE_User', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is used for Library Support System', '1', 'OLE-SELECT', 'OLE10001', 'OLE10001', 'OLE_SYS', '2')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for Requestor OLE', '1', 'OLE-SELECT', 'OLE10002', 'OLE10002', 'OLE_Requestor', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is assigned to admin for Loading', '1', 'OLE-SELECT', 'OLE10003', 'OLE10003', 'OLE_Load', '3')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role for Selectors who take action on the Document', '1', 'OLE-SELECT', 'OLE10004', 'OLE10004', 'OLE_Selectors', '4')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role can assign actions to Super Selector', '1', 'OLE-SELECT', 'OLE10005', 'OLE10005', 'OLE_Super-Selectors', '4')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for Acquisitions Staff', '1', 'OLE-SELECT', 'OLE10006', 'OLE10006', 'OLE_Acquisitions', '3')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for Acquisitions Managers', '1', 'OLE-SELECT', 'OLE10012', 'OLE10012', 'OLE_ACQ-Mgr', '9')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for Approval Plan By Manager', '1', 'OLE-SELECT', 'OLE10013', 'OLE10013', 'OLE_App-Mgr', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for Staffs for Licensing', '1', 'OLE-SELECT', 'OLE10014', 'OLE10014', 'OLE_ERMS', '2')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for Receiving Staff', '1', 'OLE-SELECT', 'OLE10016', 'OLE10016', 'OLE_RCV', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role authorises the Users to allow Licensing', '1', 'OLE-SELECT', 'OLE10017', 'OLE10017', 'OLE_License-Mgr', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role allows to take Action on Funds', '1', 'OLE-SELECT', 'OLE10018', 'OLE10018', 'OLE_Fund -Mgr', '2')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Line Item Receiving Manager Role', '1', 'OLE-SELECT', 'OLE10019', 'OLE10019', 'OLE-Receive-Mgr', '7')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'OLE-Invoicing', '1', 'OLE-SELECT', 'OLE10020', 'OLE10020', 'OLE-Invoicing', '7')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'OLE-Payment', '1', 'OLE-SELECT', 'OLE10021', 'OLE10021', 'OLE-Payment', '5')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role for Selectors who take action on the Document', '1', 'OLE-SELECT', 'OLE10022', 'OLE10022', 'OLE_Selectors-serial', '4')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for Acquisitions Staff', '1', 'OLE-SELECT', 'OLE10023', 'OLE10023', 'OLE_Acquisitions-serial', '3')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for Acquisitions Managers', '1', 'OLE-SELECT', 'OLE10024', 'OLE10024', 'OLE_ACQ-Mgr-serial', '9')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'Order Queue User Role', '1', 'OLE-SELECT', 'OLE10025', 'OLE10025', 'OLE_ORDQU-User', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for View only document(Not for edit)', '1', 'OLE-SELECT', 'OLE10026', 'OLE10026', 'Acquisitions-AQ1', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for low-level staff with specific needs only. This role is able to create and edit selected functions.', '1', 'OLE-SELECT', 'OLE10027', 'OLE10027', 'Acquisitions-AQ2', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for staff performing function.This role is able to create and edit all functions.', '1', 'OLE-SELECT', 'OLE10028', 'OLE10028', 'Acquisitions-AQ3', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for when you want to restrict deletion to high-level staff. This role is able to create edit or delete all functions.', '1', 'OLE-SELECT', 'OLE10029', 'OLE10029', 'Acquisitions-AQ4', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role can perform functions and assign appropriate security level to others.', '1', 'OLE-SELECT', 'OLE10030', 'OLE10030', 'Acquisitions-AQ5', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for View only document(Not for edit)', '1', 'OLE-SELECT', 'OLE10031', 'OLE10031', 'Receiving-AQ1', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for low-level staff with specific needs only. This role is able to create and edit selected functions.', '1', 'OLE-SELECT', 'OLE10032', 'OLE10032', 'Receiving-AQ2', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for staff performing function. This role is able to create and edit all functions.', '1', 'OLE-SELECT', 'OLE10033', 'OLE10033', 'Receiving-AQ3', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for when you want to restrict deletion to high-level staff. This role is able to create edit or delete all functions.', '1', 'OLE-SELECT', 'OLE10034', 'OLE10034', 'Receiving-AQ4', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for View only document(Not for edit)', '1', 'OLE-SELECT', 'OLE10035', 'OLE10035', 'Accounting-AQ1', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for low-level staff with specific needs only. This role is able to create and edit selected functions.', '1', 'OLE-SELECT', 'OLE10036', 'OLE10036', 'Accounting-AQ2', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for staff performing function. This role is able to create and edit all functions.', '1', 'OLE-SELECT', 'OLE10037', 'OLE10037', 'Accounting-AQ3', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for when you want to restrict deletion to high-level staff. This role is able to create edit or delete all functions.', '1', 'OLE-SELECT', 'OLE10038', 'OLE10038', 'Accounting-AQ4', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for View only document(Not for edit)', '1', 'OLE-SELECT', 'OLE10039', 'OLE10039', 'Financial-AQ1', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for low-level staff with specific needs only. This role is able to create and edit selected functions.', '1', 'OLE-SELECT', 'OLE10040', 'OLE10040', 'Financial-AQ2', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for staff performing function. This role is able to create and edit all functions.', '1', 'OLE-SELECT', 'OLE10041', 'OLE10041', 'Financial-AQ3', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role is for when you want to restrict deletion to high-level staff. This role is able to create edit or delete all functions.', '1', 'OLE-SELECT', 'OLE10042', 'OLE10042', 'Financial-AQ4', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This role can perform functions and assign appropriate security level to others.', '1', 'OLE-SELECT', 'OLE10043', 'OLE10043', 'Financial-AQ5', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role perform the approval and security on the Documents DV CM DI Payment Request and the GEC.', '1', 'OLE-SELECT', 'OLE10044', 'OLE10044', 'OLE_Prepayment', '1')
/

INSERT INTO KRIM_ROLE_T (ACTV_IND, DESC_TXT, KIM_TYP_ID, NMSPC_CD, OBJ_ID, ROLE_ID, ROLE_NM, VER_NBR) VALUES ('Y', 'This Role perform the approval on the DI and GEC document.', '1', 'OLE-SELECT', 'OLE10045', 'OLE10045', 'OLE_Accounting', '1')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_LOAD_OLE_KRIM_ROLE_T', '2.0.5', '3:05e09d0d96403ded32a5f3e801b04408', 21)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_KRIM_ROLE_PERM_T::ole::(Checksum: 3:05d1944a925b63f9b9f2819728cdb4b4)
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10000', 'OLE10004', 'OLE10000', 'OLE10000')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10005', 'OLE10005', 'OLE10004', 'OLE10005')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10006', 'OLE10006', 'OLE10004', 'OLE10006')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10007', 'OLE10009', 'OLE10004', 'OLE10007')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10008', 'OLE10011', 'OLE10004', 'OLE10008')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10009', 'OLE10012', 'OLE10004', 'OLE10009')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10010', 'OLE10014', 'OLE10004', 'OLE10010')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10011', 'OLE10017', 'OLE10004', 'OLE10011')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10013', 'OLE10005', 'OLE10005', 'OLE10013')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10014', 'OLE10006', 'OLE10005', 'OLE10014')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10015', 'OLE10009', 'OLE10005', 'OLE10015')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10016', 'OLE10017', 'OLE10005', 'OLE10016')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10018', 'OLE10005', 'OLE10006', 'OLE10018')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10019', 'OLE10006', 'OLE10006', 'OLE10019')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10020', 'OLE10009', 'OLE10006', 'OLE10020')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10021', 'OLE10015', 'OLE10006', 'OLE10021')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10022', 'OLE10017', 'OLE10006', 'OLE10022')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10024', 'OLE10005', 'OLE10012', 'OLE10024')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10025', 'OLE10006', 'OLE10012', 'OLE10025')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10026', 'OLE10009', 'OLE10012', 'OLE10026')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10027', 'OLE10014', 'OLE10012', 'OLE10027')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10028', 'OLE10017', 'OLE10012', 'OLE10028')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10031', 'OLE10015', 'OLE10014', 'OLE10031')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10032', 'OLE10017', 'OLE10014', 'OLE10032')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10037', 'OLE10014', 'OLE10016', 'OLE10037')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10038', 'OLE10011', 'OLE10016', 'OLE10038')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10039', 'OLE10012', 'OLE10016', 'OLE10039')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10040', 'OLE10017', 'OLE10016', 'OLE10040')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10041', 'OLE10023', 'OLE10005', 'OLE10041')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10042', 'OLE10032', 'OLE10005', 'OLE10042')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10043', 'OLE10021', 'OLE10012', 'OLE10043')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10044', 'OLE10021', 'OLE10014', 'OLE10044')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10046', 'OLE10017', 'OLE10017', 'OLE10046')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10047', 'OLE10021', 'OLE10017', 'OLE10047')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10048', 'OLE10015', 'OLE10017', 'OLE10048')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10050', 'OLE10027', 'OLE10001', 'OLE10050')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10051', 'OLE10022', 'OLE10001', 'OLE10051')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10052', 'OLE10031', 'OLE10001', 'OLE10052')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10053', 'OLE10018', 'OLE10001', 'OLE10053')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10054', 'OLE10022', 'OLE10003', 'OLE10054')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10055', 'OLE10032', 'OLE10003', 'OLE10055')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10056', 'OLE10032', 'OLE10004', 'OLE10056')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10057', 'OLE10023', 'OLE10004', 'OLE10057')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10058', 'OLE10030', 'OLE10005', 'OLE10058')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10059', 'OLE10008', 'OLE10005', 'OLE10059')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10060', 'OLE10011', 'OLE10006', 'OLE10060')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10061', 'OLE10012', 'OLE10006', 'OLE10061')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10062', 'OLE10014', 'OLE10006', 'OLE10062')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10063', 'OLE10026', 'OLE10006', 'OLE10063')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10064', 'OLE10024', 'OLE10018', 'OLE10064')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10065', 'OLE10035', 'OLE10000', 'OLE10065')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10078', 'OLE10036', 'OLE10000', 'OLE10078')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10096', 'OLE10041', 'OLE10000', 'OLE10096')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10097', 'OLE94', 'OLE10004', 'OLE10097')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10100', 'OLE10042', 'OLE10017', 'OLE10100')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10101', 'OLE10042', 'OLE10014', 'OLE10101')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10102', 'OLE10042', 'OLE10006', 'OLE10102')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10103', 'OLE10043', 'OLE10012', 'OLE10103')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10104', 'OLE10043', 'OLE10016', 'OLE10104')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10105', 'OLE10043', 'OLE10004', 'OLE10105')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10106', 'OLE10042', 'OLE54', 'OLE10106')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10107', 'OLE10017', 'OLE54', 'OLE10107')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10108', 'OLE10043', 'OLE10005', 'OLE10108')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10152', 'OLE10011', 'OLE26', 'OLE10152')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10153', 'OLE10012', 'OLE26', 'OLE10153')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10154', 'OLE10014', 'OLE26', 'OLE10154')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10109', 'OLESEC6001', 'OLE10000', 'OLESEC10109')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10110', 'OLESEC6002', 'OLE10000', 'OLESEC10110')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10111', 'OLESEC6003', 'OLE10000', 'OLESEC10111')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10112', 'OLESEC6001', 'OLE10001', 'OLESEC10112')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10113', 'OLESEC6002', 'OLE10001', 'OLESEC10113')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10114', 'OLESEC6003', 'OLE10001', 'OLESEC10114')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10115', 'OLESEC6001', 'OLE10002', 'OLESEC10115')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10116', 'OLESEC6002', 'OLE10002', 'OLESEC10116')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10117', 'OLESEC6003', 'OLE10002', 'OLESEC10117')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10118', 'OLESEC6001', 'OLE10003', 'OLESEC10118')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10119', 'OLESEC6002', 'OLE10003', 'OLESEC10119')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10120', 'OLESEC6003', 'OLE10003', 'OLESEC10120')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10121', 'OLESEC6001', 'OLE10004', 'OLESEC10121')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10122', 'OLESEC6002', 'OLE10004', 'OLESEC10122')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10123', 'OLESEC6003', 'OLE10004', 'OLESEC10123')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10124', 'OLESEC6001', 'OLE10005', 'OLESEC10124')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10125', 'OLESEC6002', 'OLE10005', 'OLESEC10125')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10126', 'OLESEC6003', 'OLE10005', 'OLESEC10126')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10127', 'OLESEC6001', 'OLE10006', 'OLESEC10127')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10128', 'OLESEC6002', 'OLE10006', 'OLESEC10128')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10129', 'OLESEC6003', 'OLE10006', 'OLESEC10129')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10130', 'OLESEC6001', 'OLE10012', 'OLESEC10130')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10131', 'OLESEC6002', 'OLE10012', 'OLESEC10131')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10132', 'OLESEC6003', 'OLE10012', 'OLESEC10132')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10133', 'OLESEC6001', 'OLE10013', 'OLESEC10133')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10134', 'OLESEC6002', 'OLE10013', 'OLESEC10134')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10135', 'OLESEC6003', 'OLE10013', 'OLESEC10135')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10136', 'OLESEC6001', 'OLE10014', 'OLESEC10136')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10137', 'OLESEC6002', 'OLE10014', 'OLESEC10137')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10138', 'OLESEC6003', 'OLE10014', 'OLESEC10138')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10139', 'OLESEC6001', 'OLE10016', 'OLESEC10139')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10140', 'OLESEC6002', 'OLE10016', 'OLESEC10140')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10141', 'OLESEC6003', 'OLE10016', 'OLESEC10141')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10142', 'OLESEC6001', 'OLE10017', 'OLESEC10142')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10143', 'OLESEC6002', 'OLE10017', 'OLESEC10143')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10144', 'OLESEC6003', 'OLE10017', 'OLESEC10144')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10145', 'OLESEC6001', 'OLE10018', 'OLESEC10145')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10146', 'OLESEC6002', 'OLE10018', 'OLESEC10146')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10147', 'OLESEC6003', 'OLE10018', 'OLESEC10147')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10148', 'OLESEC6001', 'OLE44', 'OLESEC10148')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10149', 'OLESEC6002', 'OLE44', 'OLESEC10149')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10150', 'OLESEC6003', 'OLE44', 'OLESEC10150')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLESEC10151', 'OLESEC6004', '59', 'OLESEC10151')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10155', '168', 'OLE62', 'OLE10155')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10156', 'OLE10044', 'OLE10019', 'OLE10156')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10157', 'OLE10045', 'OLE10019', 'OLE10157')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10158', 'OLE10044', 'OLE10020', 'OLE10158')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10159', 'OLE10045', 'OLE10020', 'OLE10159')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10160', 'OLE10047', 'OLE10019', 'OLE10160')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10161', 'OLE10047', 'OLE10020', 'OLE10161')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10162', 'OLE10046', 'OLE10019', 'OLE10162')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10163', 'OLE10044', 'OLE10016', 'OLE10163')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10164', 'OLE10045', 'OLE10016', 'OLE10164')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10165', 'OLE10047', 'OLE10016', 'OLE10165')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10166', 'OLE10046', 'OLE10016', 'OLE10166')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10167', 'OLE97', 'OLE10016', 'OLE10167')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10168', 'OLE10050', 'OLE10016', 'OLE10168')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10169', 'OLE10051', 'OLE10016', 'OLE10169')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10170', 'OLE10052', 'OLE10016', 'OLE10170')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10171', 'OLE97', 'OLE10019', 'OLE10171')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10172', 'OLE10050', 'OLE10019', 'OLE10172')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10173', 'OLE10051', 'OLE10019', 'OLE10173')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10174', 'OLE10052', 'OLE10019', 'OLE10174')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10175', 'OLE10053', 'OLE10021', 'OLE10175')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10176', 'OLE10054', 'OLE10021', 'OLE10176')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10177', 'OLE97', 'OLE10020', 'OLE10177')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10178', 'OLE10050', 'OLE10020', 'OLE10178')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10179', 'OLE10051', 'OLE10020', 'OLE10179')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10180', 'OLE10052', 'OLE10020', 'OLE10180')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10181', 'OLE10053', 'OLE10020', 'OLE10181')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10182', 'OLE10054', 'OLE10020', 'OLE10182')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10183', 'OLE10055', 'OLE10016', 'OLE10183')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10184', 'OLE10055', 'OLE10019', 'OLE10184')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10185', 'OLE10011', 'OLE10019', 'OLE10185')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10186', 'OLE10014', 'OLE10019', 'OLE10186')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10187', 'OLE10011', 'OLE10020', 'OLE10187')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10188', 'OLE10014', 'OLE10020', 'OLE10188')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10189', 'OLE10048', 'OLE10019', 'OLE10189')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10190', 'OLE10056', 'OLE10021', 'OLE10190')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10191', 'OLE10057', 'OLE10019', 'OLE10191')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10192', 'OLE10057', 'OLE10016', 'OLE10192')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10193', 'OLE10057', 'OLE10020', 'OLE10193')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10194', 'OLE10058', 'OLE10019', 'OLE10194')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10195', 'OLE10059', 'OLE10019', 'OLE10195')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10196', 'OLE10058', 'OLE10020', 'OLE10196')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10197', 'OLE10059', 'OLE10020', 'OLE10197')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10198', 'OLE10058', 'OLE10016', 'OLE10198')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10199', 'OLE10059', 'OLE10016', 'OLE10199')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10200', 'OLE10060', 'OLE10004', 'OLE10200')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10201', 'OLE10061', 'OLE10004', 'OLE10201')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10202', 'OLE10062', 'OLE10004', 'OLE10202')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10203', 'OLE10060', 'OLE10006', 'OLE10203')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10204', 'OLE10061', 'OLE10006', 'OLE10204')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10205', 'OLE10062', 'OLE10006', 'OLE10205')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10207', 'OLE10061', 'OLE10006', 'OLE10207')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10208', 'OLE10062', 'OLE10006', 'OLE10208')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10209', 'OLE10060', 'OLE10012', 'OLE10209')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10210', 'OLE10061', 'OLE10012', 'OLE10210')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10211', 'OLE10062', 'OLE10012', 'OLE10211')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10212', 'OLE10063', 'OLE10002', 'OLE10212')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10213', 'OLE10064', 'OLE10004', 'OLE10213')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10214', 'OLE10065', 'OLE10012', 'OLE10214')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10215', 'OLE10066', 'OLE10012', 'OLE10215')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10216', 'OLE10067', 'OLE10012', 'OLE10216')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10217', 'OLE10066', 'OLE10006', 'OLE10217')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10218', 'OLE10068', 'OLE10006', 'OLE10218')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10219', 'OLE10069', 'OLE10006', 'OLE10219')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10220', 'OLE10070', 'OLE10006', 'OLE10220')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10221', 'OLE10068', 'OLE10012', 'OLE10221')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10222', 'OLE10069', 'OLE10012', 'OLE10222')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10223', 'OLE10070', 'OLE10012', 'OLE10223')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10224', 'OLE94', 'OLE10012', 'OLE10224')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10225', 'OLE94', 'OLE10006', 'OLE10225')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10226', 'OLE10071', 'OLE10022', 'OLE10226')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10227', 'OLE10071', 'OLE10023', 'OLE10227')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10228', 'OLE10071', 'OLE10024', 'OLE10228')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10229', 'OLE10042', 'OLE10012', 'OLE10229')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10230', 'OLE10015', 'OLE10012', 'OLE10230')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10231', 'OLE10066', 'OLE10006', 'OLE10231')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10232', 'OLE10100', 'OLE10006', 'OLE10232')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10233', 'OLE10100', 'OLE10003', 'OLE10233')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10234', 'OLE10100', 'OLE10012', 'OLE10234')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10235', 'OLE10108', 'OLE10003', 'OLE10235')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10236', 'OLE10109', 'OLE10003', 'OLE10236')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10237', 'OLE10108', 'OLE10012', 'OLE10237')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10238', 'OLE10109', 'OLE10012', 'OLE10238')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10239', 'OLE10108', 'OLE10005', 'OLE10239')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10240', 'OLE10109', 'OLE10005', 'OLE10240')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10241', 'OLE10108', 'OLE10006', 'OLE10241')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10242', 'OLE10109', 'OLE10006', 'OLE10242')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10243', 'OLE10108', 'OLE10004', 'OLE10243')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10244', 'OLE10060', 'OLE62', 'OLE10244')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10245', 'OLE10061', 'OLE62', 'OLE10245')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10246', 'OLE10062', 'OLE62', 'OLE10246')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10247', 'OLE10071', 'OLE62', 'OLE10247')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10248', 'OLE10110', 'OLE10024', 'OLE10248')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10249', 'OLE10111', 'OLE10024', 'OLE10249')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10250', 'OLE10110', 'OLE10023', 'OLE10250')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10251', 'OLE10111', 'OLE10023', 'OLE10251')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10252', 'OLE10110', 'OLE10022', 'OLE10252')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10253', 'OLE10111', 'OLE10022', 'OLE10253')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10254', 'OLE10114', 'OLE10016', 'OLE10254')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10255', 'OLE10113', 'OLE10019', 'OLE10255')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10259', 'OLE10060', 'OLE41', 'OLE10259')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10260', 'OLE10061', 'OLE41', 'OLE10260')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10261', 'OLE10062', 'OLE41', 'OLE10261')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10262', 'OLE10110', 'OLE41', 'OLE10262')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10263', 'OLE10111', 'OLE41', 'OLE10263')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10264', 'OLE10071', 'OLE41', 'OLE10264')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10265', 'OLE10100', 'OLE54', 'OLE10265')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10266', 'OLE10111', 'OLE54', 'OLE10266')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10267', 'OLE10071', 'OLE54', 'OLE10267')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10268', 'OLE10060', 'OLE54', 'OLE10268')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10269', 'OLE10061', 'OLE54', 'OLE10269')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10270', 'OLE10062', 'OLE54', 'OLE10270')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10271', 'OLE10110', 'OLE62', 'OLE10271')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10272', 'OLE10111', 'OLE62', 'OLE10272')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10273', 'OLE10005', 'OLE10022', 'OLE10273')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10274', 'OLE10006', 'OLE10022', 'OLE10274')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10275', 'OLE10009', 'OLE10022', 'OLE10275')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10276', 'OLE10011', 'OLE10022', 'OLE10276')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10277', 'OLE10012', 'OLE10022', 'OLE10277')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10278', 'OLE10014', 'OLE10022', 'OLE10278')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10279', 'OLE10017', 'OLE10022', 'OLE10279')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10280', 'OLE10032', 'OLE10022', 'OLE10280')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10281', 'OLE10023', 'OLE10022', 'OLE10281')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10282', 'OLE94', 'OLE10022', 'OLE10282')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10283', 'OLE10043', 'OLE10022', 'OLE10283')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10284', 'OLE10071', 'OLE10022', 'OLE10284')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10285', 'OLE10110', 'OLE10022', 'OLE10285')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10286', 'OLE10111', 'OLE10022', 'OLE10286')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10287', 'OLE10064', 'OLE10022', 'OLE10287')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10288', 'OLE10108', 'OLE10022', 'OLE10288')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10289', 'OLE10005', 'OLE10023', 'OLE10289')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10290', 'OLE10006', 'OLE10023', 'OLE10290')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10291', 'OLE10009', 'OLE10023', 'OLE10291')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10292', 'OLE10015', 'OLE10023', 'OLE10292')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10293', 'OLE10017', 'OLE10023', 'OLE10293')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10294', 'OLE10011', 'OLE10023', 'OLE10294')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10295', 'OLE10012', 'OLE10023', 'OLE10295')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10296', 'OLE10014', 'OLE10023', 'OLE10296')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10297', 'OLE10026', 'OLE10023', 'OLE10297')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10298', 'OLE10042', 'OLE10023', 'OLE10298')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10299', 'OLE10071', 'OLE10023', 'OLE10299')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10300', 'OLE10110', 'OLE10023', 'OLE10300')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10301', 'OLE10111', 'OLE10023', 'OLE10301')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10302', 'OLE10061', 'OLE10023', 'OLE10302')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10303', 'OLE10062', 'OLE10023', 'OLE10303')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10304', 'OLE10066', 'OLE10023', 'OLE10304')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10305', 'OLE10068', 'OLE10023', 'OLE10305')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10306', 'OLE10069', 'OLE10023', 'OLE10306')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10307', 'OLE10070', 'OLE10023', 'OLE10307')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10308', 'OLE94', 'OLE10023', 'OLE10308')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10309', 'OLE10066', 'OLE10023', 'OLE10309')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10310', 'OLE10100', 'OLE10023', 'OLE10310')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10311', 'OLE10108', 'OLE10023', 'OLE10311')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10312', 'OLE10109', 'OLE10023', 'OLE10312')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10313', 'OLE10005', 'OLE10024', 'OLE10313')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10314', 'OLE10006', 'OLE10024', 'OLE10314')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10315', 'OLE10009', 'OLE10024', 'OLE10315')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10316', 'OLE10014', 'OLE10024', 'OLE10316')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10317', 'OLE10017', 'OLE10024', 'OLE10317')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10318', 'OLE10021', 'OLE10024', 'OLE10318')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10319', 'OLE10043', 'OLE10024', 'OLE10319')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10323', 'OLE10065', 'OLE10024', 'OLE10323')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10324', 'OLE10066', 'OLE10024', 'OLE10324')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10325', 'OLE10067', 'OLE10024', 'OLE10325')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10326', 'OLE10068', 'OLE10024', 'OLE10326')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10327', 'OLE10069', 'OLE10024', 'OLE10327')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10328', 'OLE10070', 'OLE10024', 'OLE10328')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10329', 'OLE94', 'OLE10024', 'OLE10329')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10330', 'OLE10042', 'OLE10024', 'OLE10330')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10331', 'OLE10015', 'OLE10024', 'OLE10331')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10332', 'OLE10100', 'OLE10024', 'OLE10332')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10333', 'OLE10108', 'OLE10024', 'OLE10333')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10334', 'OLE10109', 'OLE10024', 'OLE10334')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10335', 'OLE10115', 'OLE10004', 'OLE10335')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10336', 'OLE10115', 'OLE10005', 'OLE10336')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10337', 'OLE10115', 'OLE10006', 'OLE10337')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10338', 'OLE10115', 'OLE10012', 'OLE10338')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10339', 'OLE10116', 'OLE10012', 'OLE10339')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10340', 'OLE10116', 'OLE10016', 'OLE10340')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10341', 'OLE10116', 'OLE10019', 'OLE10341')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10342', 'OLE10117', 'OLE10012', 'OLE10342')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10343', 'OLE10117', 'OLE10016', 'OLE10343')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10344', 'OLE10117', 'OLE10019', 'OLE10344')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10345', 'OLE10118', 'OLE10012', 'OLE10345')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10346', 'OLE10118', 'OLE10016', 'OLE10346')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10347', 'OLE10118', 'OLE10019', 'OLE10347')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10348', 'OLE10119', 'OLE10012', 'OLE10348')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10349', 'OLE10119', 'OLE10016', 'OLE10349')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10350', 'OLE10119', 'OLE10019', 'OLE10350')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10351', 'OLE10008', 'OLE10012', 'OLE10351')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10352', 'OLE10009', 'OLE10004', 'OLE10352')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10353', 'OLE10120', 'OLE10004', 'OLE10353')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10354', 'OLE10120', 'OLE10005', 'OLE10354')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10355', 'OLE10056', 'OLE10012', 'OLE10355')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10356', 'OLE10056', 'OLE10024', 'OLE10356')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10357', 'OLE10015', 'OLE10020', 'OLE10357')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10358', 'OLE10017', 'OLE10020', 'OLE10358')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10359', 'OLE10042', 'OLE10020', 'OLE10359')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10360', 'OLE10043', 'OLE10020', 'OLE10360')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10362', 'OLE10121', 'OLE10012', 'OLE10362')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10363', 'OLE10122', 'OLE10012', 'OLE10363')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10364', 'OLE92', '63', 'OLE10364')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10365', 'OLE100', '63', 'OLE10365')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10152', 'OLESEC6001', 'OLE10022', 'OLESEC10152')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10153', 'OLESEC6002', 'OLE10022', 'OLESEC10153')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10154', 'OLESEC6003', 'OLE10022', 'OLESEC10154')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10155', 'OLESEC6001', 'OLE10023', 'OLESEC10155')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10156', 'OLESEC6002', 'OLE10023', 'OLESEC10156')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10157', 'OLESEC6003', 'OLE10023', 'OLESEC10157')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10158', 'OLESEC6001', 'OLE10024', 'OLESEC10158')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10159', 'OLESEC6002', 'OLE10024', 'OLESEC10159')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLESEC10160', 'OLESEC6003', 'OLE10024', 'OLESEC10160')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLE80000', 'OLE80000', 'OLE10021', 'OLE80000')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80002', 'OLE80002', '63', 'OLE80002')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80004', 'OLE80004', '63', 'OLE80004')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLE80006', 'OLE80008', 'OLE10006', 'OLE80006')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLE80008', 'OLE80011', 'OLE10006', 'OLE80008')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80010', 'OLE80008', 'OLE10021', 'OLE80010')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80012', 'OLE80011', 'OLE10021', 'OLE80012')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80014', 'OLE80014', 'OLE10021', 'OLE80014')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80016', 'OLE80016', 'OLE10021', 'OLE80016')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80018', 'OLE80018', 'OLE10006', 'OLE80018')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80020', 'OLE80020', 'OLE10006', 'OLE80020')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80022', 'OLE80026', 'OLE10006', 'OLE80022')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80024', 'OLE80028', 'OLE10006', 'OLE80024')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80026', 'OLE80030', 'OLE10006', 'OLE80026')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80028', 'OLE80032', 'OLE10006', 'OLE80028')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLE80030', 'OLE80034', 'OLE10019', 'OLE80030')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80032', 'OLE80036', 'OLE10006', 'OLE80032')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80034', 'OLE80038', 'OLE10021', 'OLE80034')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80036', 'OLE80040', 'OLE10021', 'OLE80036')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80038', 'OLE80042', 'OLE10021', 'OLE80038')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80040', 'OLE80044', 'OLE10006', 'OLE80040')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80042', 'OLE80047', 'OLE10006', 'OLE80042')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80044', 'OLE80050', 'OLE10006', 'OLE80044')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80046', 'OLE80000', 'OLE10021', 'OLE80046')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80048', 'OLE80052', 'OLE10006', 'OLE80048')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80050', 'OLE80054', 'OLE10006', 'OLE80050')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80052', 'OLE80054', 'OLE10004', 'OLE80052')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80054', 'OLE80054', 'OLE10005', 'OLE80054')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80056', 'OLE80054', 'OLE10012', 'OLE80056')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80058', 'OLE80056', 'OLE10006', 'OLE80058')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80060', 'OLE80058', 'OLE10021', 'OLE80060')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80062', 'OLE80058', 'OLE10012', 'OLE80062')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80064', 'OLE80058', 'OLE10019', 'OLE80064')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80066', 'OLE80061', 'OLE10006', 'OLE80066')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80068', 'OLE80061', 'OLE10004', 'OLE80068')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80070', 'OLE80061', 'OLE10005', 'OLE80070')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80072', 'OLE80061', 'OLE10012', 'OLE80072')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80074', 'OLE80063', 'OLE10006', 'OLE80074')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80076', 'OLE80065', 'OLE10006', 'OLE80076')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80078', 'OLE80067', 'OLE10006', 'OLE80078')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80080', 'OLE80006', 'OLE10000', 'OLE80080')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80082', 'OLE80024', 'OLE10006', 'OLE80082')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80084', 'OLE80034', 'OLE10016', 'OLE80084')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80086', 'OLE80099', '63', 'OLE80086')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80088', 'OLE80101', '63', 'OLE80088')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80090', 'OLE80103', '63', 'OLE80090')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80092', 'OLE80105', '63', 'OLE80092')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80094', 'OLE80107', '63', 'OLE80094')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80097', 'OLE80110', '63', 'OLE80097')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80098', 'OLE80111', '63', 'OLE80098')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80102', 'OLE80117', '63', 'OLE80102')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80104', 'OLE80120', '63', 'OLE80104')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80105', 'OLE80122', '63', 'OLE80105')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80106', 'OLE80113', '63', 'OLE80106')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80107', 'OLE80115', '63', 'OLE80107')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80110', 'OLE80124', '63', 'OLE80110')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80112', 'OLE80126', '63', 'OLE80112')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80114', 'OLE80128', '63', 'OLE80114')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80116', 'OLE80130', '63', 'OLE80116')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80118', 'OLE80132', '63', 'OLE80118')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80120', 'OLE80069', '63', 'OLE80120')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80122', 'OLE80080', '63', 'OLE80122')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80124', 'OLE80082', '63', 'OLE80124')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80126', 'OLE80084', '63', 'OLE80126')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80128', 'OLE80087', '63', 'OLE80128')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80130', 'OLE80089', '63', 'OLE80130')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80132', 'OLE80094', '63', 'OLE80132')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80134', 'OLE80097', '63', 'OLE80134')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80136', 'OLE80071', '63', 'OLE80136')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80138', 'OLE80073', '63', 'OLE80138')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80140', 'OLE80075', '63', 'OLE80140')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80142', 'OLE80077', '63', 'OLE80142')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80144', 'OLE80091', '63', 'OLE80144')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80146', 'OLE80054', 'OLE10022', 'OLE80146')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80148', 'OLE80061', 'OLE10022', 'OLE80148')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLE80150', 'OLE80008', 'OLE10023', 'OLE80150')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('N', 'OLE80152', 'OLE80011', 'OLE10023', 'OLE80152')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80154', 'OLE80018', 'OLE10023', 'OLE80154')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80156', 'OLE80020', 'OLE10023', 'OLE80156')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80158', 'OLE80026', 'OLE10023', 'OLE80158')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80160', 'OLE80028', 'OLE10023', 'OLE80160')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80162', 'OLE80030', 'OLE10023', 'OLE80162')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80164', 'OLE80032', 'OLE10023', 'OLE80164')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80166', 'OLE80036', 'OLE10023', 'OLE80166')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80168', 'OLE80044', 'OLE10023', 'OLE80168')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80170', 'OLE80047', 'OLE10023', 'OLE80170')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80172', 'OLE80050', 'OLE10023', 'OLE80172')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80174', 'OLE80052', 'OLE10023', 'OLE80174')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80176', 'OLE80054', 'OLE10023', 'OLE80176')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80178', 'OLE80056', 'OLE10023', 'OLE80178')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80180', 'OLE80061', 'OLE10023', 'OLE80180')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80182', 'OLE80063', 'OLE10023', 'OLE80182')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80184', 'OLE80065', 'OLE10023', 'OLE80184')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80186', 'OLE80067', 'OLE10023', 'OLE80186')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80188', 'OLE80024', 'OLE10023', 'OLE80188')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80190', 'OLE80054', 'OLE10024', 'OLE80190')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80192', 'OLE80058', 'OLE10024', 'OLE80192')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE80194', 'OLE80061', 'OLE10024', 'OLE80194')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE81000', 'OLE130', '63', 'OLE81000')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE110000', 'OLE1500', 'OLE10003', 'OLE110000')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10366', 'OLE325', 'OLE10016', 'OLE10366')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10367', 'OLE326', 'OLE10016', 'OLE10367')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10368', 'OLE356', 'OLE10016', 'OLE10368')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10369', 'OLE293', 'OLE10016', 'OLE10369')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10370', 'OLE294', 'OLE10016', 'OLE10370')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10371', 'OLE367', 'OLE10016', 'OLE10371')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10372', 'OLE368', 'OLE10016', 'OLE10372')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10373', 'OLE325', 'OLE10019', 'OLE10373')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10374', 'OLE326', 'OLE10019', 'OLE10374')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10375', 'OLE356', 'OLE10019', 'OLE10375')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10376', 'OLE293', 'OLE10019', 'OLE10376')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10377', 'OLE294', 'OLE10019', 'OLE10377')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10378', 'OLE367', 'OLE10019', 'OLE10378')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10379', 'OLE368', 'OLE10019', 'OLE10379')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10380', 'OLE325', 'OLE10020', 'OLE10380')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10381', 'OLE326', 'OLE10020', 'OLE10381')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10382', 'OLE356', 'OLE10020', 'OLE10382')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10383', 'OLE293', 'OLE10020', 'OLE10383')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10384', 'OLE294', 'OLE10020', 'OLE10384')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10385', 'OLE367', 'OLE10020', 'OLE10385')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10386', 'OLE368', 'OLE10020', 'OLE10386')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10387', 'OLE80135', 'OLE10027', 'OLE10387')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10388', 'OLE80136', 'OLE10027', 'OLE10388')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10390', 'OLE80138', 'OLE10027', 'OLE10390')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10391', 'OLE80135', 'OLE10028', 'OLE10391')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10392', 'OLE80136', 'OLE10028', 'OLE10392')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10394', 'OLE80138', 'OLE10028', 'OLE10394')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10395', 'OLE80135', 'OLE10029', 'OLE10395')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10396', 'OLE80136', 'OLE10029', 'OLE10396')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10398', 'OLE80138', 'OLE10029', 'OLE10398')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10399', 'OLE80135', 'OLE10030', 'OLE10399')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10400', 'OLE80136', 'OLE10030', 'OLE10400')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10402', 'OLE80138', 'OLE10030', 'OLE10402')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10403', 'OLE80135', 'OLE10032', 'OLE10403')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10404', 'OLE80136', 'OLE10032', 'OLE10404')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10406', 'OLE80138', 'OLE10032', 'OLE10406')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10407', 'OLE80135', 'OLE10033', 'OLE10407')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10408', 'OLE80136', 'OLE10033', 'OLE10408')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10410', 'OLE80138', 'OLE10033', 'OLE10410')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10411', 'OLE80135', 'OLE10034', 'OLE10411')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10412', 'OLE80136', 'OLE10034', 'OLE10412')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10414', 'OLE80138', 'OLE10034', 'OLE10414')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10415', 'OLE80139', 'OLE10028', 'OLE10415')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10416', 'OLE80140', 'OLE10028', 'OLE10416')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10417', 'OLE80141', 'OLE10028', 'OLE10417')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10418', 'OLE80142', 'OLE10028', 'OLE10418')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10419', 'OLE80139', 'OLE10029', 'OLE10419')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10420', 'OLE80140', 'OLE10029', 'OLE10420')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10421', 'OLE80141', 'OLE10029', 'OLE10421')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10422', 'OLE80142', 'OLE10029', 'OLE10422')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10423', 'OLE80139', 'OLE10030', 'OLE10423')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10424', 'OLE80140', 'OLE10030', 'OLE10424')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10425', 'OLE80141', 'OLE10030', 'OLE10425')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10426', 'OLE80142', 'OLE10030', 'OLE10426')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10427', 'OLE80143', 'OLE10042', 'OLE10427')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10428', 'OLE80144', 'OLE10042', 'OLE10428')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10429', 'OLE80145', 'OLE10042', 'OLE10429')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10430', 'OLE80146', 'OLE10042', 'OLE10430')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10431', 'OLE80147', 'OLE10042', 'OLE10431')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10432', 'OLE80148', 'OLE10042', 'OLE10432')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10433', 'OLE80149', 'OLE10042', 'OLE10433')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10434', 'OLE80150', 'OLE10042', 'OLE10434')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10439', 'OLE80143', 'OLE10043', 'OLE10439')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10440', 'OLE80144', 'OLE10043', 'OLE10440')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10441', 'OLE80145', 'OLE10043', 'OLE10441')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10442', 'OLE80146', 'OLE10043', 'OLE10442')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10443', 'OLE80147', 'OLE10043', 'OLE10443')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10444', 'OLE80148', 'OLE10043', 'OLE10444')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10445', 'OLE80149', 'OLE10043', 'OLE10445')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10446', 'OLE80150', 'OLE10043', 'OLE10446')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10451', 'OLE97', 'OLE10033', 'OLE10451')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10452', 'OLE97', 'OLE10034', 'OLE10452')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10453', 'OLE97', 'OLE10036', 'OLE10453')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10454', 'OLE97', 'OLE10037', 'OLE10454')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10455', 'OLE97', 'OLE10038', 'OLE10455')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10456', 'OLE10050', 'OLE10036', 'OLE10456')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10457', 'OLE10050', 'OLE10037', 'OLE10457')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10458', 'OLE10050', 'OLE10038', 'OLE10458')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10459', 'OLE10057', 'OLE10036', 'OLE10459')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10460', 'OLE10057', 'OLE10037', 'OLE10460')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10461', 'OLE10057', 'OLE10038', 'OLE10461')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10462', 'OLE80143', 'OLE10037', 'OLE10462')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10463', 'OLE80143', 'OLE10038', 'OLE10463')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10464', 'OLE80144', 'OLE10038', 'OLE10464')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10465', 'OLE10051', 'OLE10038', 'OLE10465')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10466', 'OLE80158', 'OLE10044', 'OLE10466')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10467', 'OLE80159', 'OLE10044', 'OLE10467')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10468', 'OLE80160', 'OLE10044', 'OLE10468')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10469', 'OLE80157', 'OLE10044', 'OLE10469')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10470', 'OLE80162', 'OLE10044', 'OLE10470')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10471', 'OLE80157', 'OLE10021', 'OLE10471')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10472', 'OLE80162', 'OLE10021', 'OLE10472')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10473', 'OLE80158', 'OLE10000', 'OLE10473')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10474', 'OLE80159', '60', 'OLE10474')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10475', 'OLE80160', '59', 'OLE10475')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10476', 'OLE80160', '97', 'OLE10476')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10477', 'OLE80161', 'OLE62', 'OLE10477')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10478', 'OLE80161', 'OLE62', 'OLE10478')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10479', 'OLE80161', 'OLE22', 'OLE10479')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10480', 'OLE80161', 'OLE10021', 'OLE10480')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10481', 'OLE367', 'OLE10021', 'OLE10481')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10482', 'OLE368', 'OLE10021', 'OLE10482')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10483', 'OLE356', 'OLE10021', 'OLE10483')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10484', 'OLE97', 'OLE10044', 'OLE10484')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10485', 'OLE10050', 'OLE10044', 'OLE10485')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10486', 'OLE10051', 'OLE10044', 'OLE10486')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10487', 'OLE10052', 'OLE10044', 'OLE10487')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10488', 'OLE10057', 'OLE10044', 'OLE10488')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10489', 'OLE10015', 'OLE10044', 'OLE10489')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10490', 'OLE10017', 'OLE10044', 'OLE10490')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10491', 'OLE10042', 'OLE10044', 'OLE10491')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10492', 'OLE80163', 'OLE10028', 'OLE10492')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10493', 'OLE80163', 'OLE10029', 'OLE10493')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10494', 'OLE80163', 'OLE10030', 'OLE10494')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10495', 'OLE80164', 'OLE10042', 'OLE10495')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10496', 'OLE80165', 'OLE10042', 'OLE10496')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10497', 'OLE80166', 'OLE10042', 'OLE10497')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10498', 'OLE80167', 'OLE10042', 'OLE10498')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10499', 'OLE80168', 'OLE10042', 'OLE10499')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10500', 'OLE80169', 'OLE10042', 'OLE10500')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10501', 'OLE80170', 'OLE10042', 'OLE10501')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10502', 'OLE80171', 'OLE10042', 'OLE10502')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10503', 'OLE80164', 'OLE10043', 'OLE10503')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10504', 'OLE80165', 'OLE10043', 'OLE10504')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10505', 'OLE80166', 'OLE10043', 'OLE10505')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10506', 'OLE80167', 'OLE10043', 'OLE10506')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10507', 'OLE80168', 'OLE10043', 'OLE10507')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10508', 'OLE80169', 'OLE10043', 'OLE10508')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10509', 'OLE80170', 'OLE10043', 'OLE10509')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10510', 'OLE80171', 'OLE10043', 'OLE10510')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10511', 'OLE80172', 'OLE10043', 'OLE10511')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10512', 'OLE80173', 'OLE10029', 'OLE10512')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10513', 'OLE80174', 'OLE10029', 'OLE10513')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10514', 'OLE80175', 'OLE10029', 'OLE10514')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10515', 'OLE80176', 'OLE10029', 'OLE10515')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10516', 'OLE80177', 'OLE10029', 'OLE10516')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10517', 'OLE80178', 'OLE10029', 'OLE10517')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10518', 'OLE80179', 'OLE10029', 'OLE10518')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10519', 'OLE80180', 'OLE10029', 'OLE10519')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10520', 'OLE80181', 'OLE10029', 'OLE10520')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10521', 'OLE80182', 'OLE10029', 'OLE10521')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10522', 'OLE80183', 'OLE10029', 'OLE10522')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10523', 'OLE80184', 'OLE10029', 'OLE10523')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10524', 'OLE80185', 'OLE10029', 'OLE10524')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10525', 'OLE80186', 'OLE10029', 'OLE10525')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10526', 'OLE80187', 'OLE10029', 'OLE10526')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10527', 'OLE80188', 'OLE10029', 'OLE10527')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10528', 'OLE80189', 'OLE10029', 'OLE10528')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10529', 'OLE80190', 'OLE10029', 'OLE10529')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10530', 'OLE80191', 'OLE10029', 'OLE10530')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10531', 'OLE80192', 'OLE10029', 'OLE10531')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10532', 'OLE80193', 'OLE10029', 'OLE10532')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10533', 'OLE80194', 'OLE10029', 'OLE10533')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10534', 'OLE80195', 'OLE10029', 'OLE10534')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10535', 'OLE80196', 'OLE10029', 'OLE10535')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10536', 'OLE80197', 'OLE10029', 'OLE10536')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10537', 'OLE80198', 'OLE10029', 'OLE10537')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10538', 'OLE80199', 'OLE10029', 'OLE10538')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10539', 'OLE80200', 'OLE10029', 'OLE10539')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10540', 'OLE80201', 'OLE10029', 'OLE10540')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10541', 'OLE80202', 'OLE10029', 'OLE10541')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10542', 'OLE80203', 'OLE10029', 'OLE10542')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10543', 'OLE80204', 'OLE10029', 'OLE10543')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10544', 'OLE80205', 'OLE10029', 'OLE10544')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10545', 'OLE80206', 'OLE10029', 'OLE10545')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10546', 'OLE80207', 'OLE10029', 'OLE10546')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10547', 'OLE80208', 'OLE10029', 'OLE10547')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10548', 'OLE80209', 'OLE10029', 'OLE10548')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10549', 'OLE80210', 'OLE10029', 'OLE10549')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10550', 'OLE80211', 'OLE10029', 'OLE10550')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10551', 'OLE80212', 'OLE10029', 'OLE10551')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10552', 'OLE80213', 'OLE10029', 'OLE10552')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10553', 'OLE80214', 'OLE10029', 'OLE10553')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10554', 'OLE80215', 'OLE10029', 'OLE10554')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10555', 'OLE80216', 'OLE10029', 'OLE10555')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10556', 'OLE80217', 'OLE10029', 'OLE10556')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10557', 'OLE80218', 'OLE10029', 'OLE10557')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10558', 'OLE80219', 'OLE10029', 'OLE10558')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10559', 'OLE80220', 'OLE10029', 'OLE10559')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10560', 'OLE80221', 'OLE10029', 'OLE10560')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10561', 'OLE80222', 'OLE10029', 'OLE10561')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10562', 'OLE80223', 'OLE10029', 'OLE10562')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10563', 'OLE80224', 'OLE10029', 'OLE10563')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10564', 'OLE80225', 'OLE10029', 'OLE10564')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10565', 'OLE80226', 'OLE10029', 'OLE10565')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10566', 'OLE80227', 'OLE10029', 'OLE10566')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10567', 'OLE80228', 'OLE10029', 'OLE10567')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10568', 'OLE80229', 'OLE10029', 'OLE10568')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10569', 'OLE80230', 'OLE10029', 'OLE10569')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10570', 'OLE80231', 'OLE10029', 'OLE10570')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10571', 'OLE80232', 'OLE10029', 'OLE10571')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10572', 'OLE80233', 'OLE10029', 'OLE10572')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10573', 'OLE80234', 'OLE10029', 'OLE10573')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10574', 'OLE80235', 'OLE10029', 'OLE10574')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10575', 'OLE80236', 'OLE10029', 'OLE10575')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10576', 'OLE80237', 'OLE10029', 'OLE10576')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10577', 'OLE80238', 'OLE10029', 'OLE10577')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10578', 'OLE80239', 'OLE10029', 'OLE10578')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10579', 'OLE80240', 'OLE10029', 'OLE10579')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10580', 'OLE80241', 'OLE10029', 'OLE10580')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10581', 'OLE80242', 'OLE10029', 'OLE10581')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10582', 'OLE80243', 'OLE10029', 'OLE10582')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10583', 'OLE80244', 'OLE10029', 'OLE10583')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10584', 'OLE80245', 'OLE10029', 'OLE10584')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10585', 'OLE80246', 'OLE10029', 'OLE10585')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10586', 'OLE80247', 'OLE10029', 'OLE10586')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10587', 'OLE80248', 'OLE10029', 'OLE10587')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10588', 'OLE80249', 'OLE10029', 'OLE10588')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10589', 'OLE80250', 'OLE10029', 'OLE10589')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10590', 'OLE80251', 'OLE10029', 'OLE10590')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10591', 'OLE80252', 'OLE10029', 'OLE10591')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10592', 'OLE80253', 'OLE10029', 'OLE10592')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10593', 'OLE80254', 'OLE10029', 'OLE10593')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10594', 'OLE80255', 'OLE10029', 'OLE10594')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10595', 'OLE80256', 'OLE10029', 'OLE10595')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10596', 'OLE80257', 'OLE10029', 'OLE10596')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10597', 'OLE80258', 'OLE10029', 'OLE10597')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10598', 'OLE80259', 'OLE10029', 'OLE10598')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10599', 'OLE80260', 'OLE10029', 'OLE10599')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10600', 'OLE80261', 'OLE10029', 'OLE10600')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10601', 'OLE80262', 'OLE10029', 'OLE10601')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10602', 'OLE80263', 'OLE10029', 'OLE10602')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10603', 'OLE80264', 'OLE10029', 'OLE10603')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10604', 'OLE80265', 'OLE10029', 'OLE10604')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10605', 'OLE80266', 'OLE10029', 'OLE10605')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10606', 'OLE80267', 'OLE10029', 'OLE10606')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10607', 'OLE80268', 'OLE10029', 'OLE10607')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10608', 'OLE80269', 'OLE10029', 'OLE10608')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10609', 'OLE80270', 'OLE10029', 'OLE10609')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10610', 'OLE80271', 'OLE10029', 'OLE10610')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10611', 'OLE80272', 'OLE10029', 'OLE10611')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10612', 'OLE80273', 'OLE10029', 'OLE10612')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10613', 'OLE80274', 'OLE10029', 'OLE10613')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10614', 'OLE80275', 'OLE10029', 'OLE10614')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10615', 'OLE80276', 'OLE10029', 'OLE10615')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10616', 'OLE80277', 'OLE10029', 'OLE10616')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10617', 'OLE80278', 'OLE10029', 'OLE10617')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10618', 'OLE80279', 'OLE10029', 'OLE10618')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10619', 'OLE80280', 'OLE10029', 'OLE10619')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10620', 'OLE80281', 'OLE10029', 'OLE10620')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10621', 'OLE80282', 'OLE10029', 'OLE10621')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10622', 'OLE80283', 'OLE10029', 'OLE10622')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10623', 'OLE80284', 'OLE10029', 'OLE10623')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10624', 'OLE80285', 'OLE10029', 'OLE10624')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10625', 'OLE80286', 'OLE10029', 'OLE10625')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10626', 'OLE80287', 'OLE10029', 'OLE10626')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10627', 'OLE80288', 'OLE10029', 'OLE10627')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10628', 'OLE80289', 'OLE10029', 'OLE10628')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10629', 'OLE80290', 'OLE10029', 'OLE10629')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10630', 'OLE80291', 'OLE10029', 'OLE10630')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10631', 'OLE80292', 'OLE10029', 'OLE10631')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10632', 'OLE80293', 'OLE10029', 'OLE10632')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10633', 'OLE80294', 'OLE10029', 'OLE10633')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10634', 'OLE80295', 'OLE10029', 'OLE10634')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10635', 'OLE80296', 'OLE10029', 'OLE10635')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10636', 'OLE80297', 'OLE10029', 'OLE10636')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10637', 'OLE80298', 'OLE10029', 'OLE10637')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10638', 'OLE80299', 'OLE10029', 'OLE10638')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10639', 'OLE80300', 'OLE10029', 'OLE10639')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10640', 'OLE80301', 'OLE10029', 'OLE10640')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10641', 'OLE80302', 'OLE10029', 'OLE10641')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10642', 'OLE80303', 'OLE10029', 'OLE10642')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10643', 'OLE80304', 'OLE10029', 'OLE10643')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10644', 'OLE80305', 'OLE10029', 'OLE10644')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10645', 'OLE80306', 'OLE10029', 'OLE10645')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10646', 'OLE80307', 'OLE10029', 'OLE10646')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10647', 'OLE80308', 'OLE10029', 'OLE10647')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10648', 'OLE80309', 'OLE10029', 'OLE10648')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10649', 'OLE80310', 'OLE10029', 'OLE10649')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10650', 'OLE80311', 'OLE10029', 'OLE10650')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10651', 'OLE80312', 'OLE10029', 'OLE10651')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10652', 'OLE80313', 'OLE10029', 'OLE10652')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10653', 'OLE80314', 'OLE10029', 'OLE10653')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10654', 'OLE80315', 'OLE10029', 'OLE10654')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10655', 'OLE80316', 'OLE10029', 'OLE10655')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10656', 'OLE80317', 'OLE10029', 'OLE10656')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10657', 'OLE80318', 'OLE10029', 'OLE10657')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10658', 'OLE80319', 'OLE10029', 'OLE10658')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10659', 'OLE80320', 'OLE10029', 'OLE10659')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10660', 'OLE80321', 'OLE10029', 'OLE10660')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10661', 'OLE80322', 'OLE10029', 'OLE10661')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10662', 'OLE80323', 'OLE10029', 'OLE10662')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10663', 'OLE80324', 'OLE10029', 'OLE10663')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10664', 'OLE80325', 'OLE10029', 'OLE10664')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10665', 'OLE80326', 'OLE10029', 'OLE10665')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10666', 'OLE80327', 'OLE10029', 'OLE10666')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10667', 'OLE80328', 'OLE10029', 'OLE10667')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10668', 'OLE80329', 'OLE10029', 'OLE10668')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10669', 'OLE80330', 'OLE10029', 'OLE10669')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10670', 'OLE80331', 'OLE10029', 'OLE10670')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10671', 'OLE80332', 'OLE10029', 'OLE10671')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10672', 'OLE80173', 'OLE10030', 'OLE10672')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10673', 'OLE80174', 'OLE10030', 'OLE10673')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10674', 'OLE80175', 'OLE10030', 'OLE10674')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10675', 'OLE80176', 'OLE10030', 'OLE10675')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10676', 'OLE80177', 'OLE10030', 'OLE10676')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10677', 'OLE80178', 'OLE10030', 'OLE10677')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10678', 'OLE80179', 'OLE10030', 'OLE10678')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10679', 'OLE80180', 'OLE10030', 'OLE10679')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10680', 'OLE80181', 'OLE10030', 'OLE10680')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10681', 'OLE80182', 'OLE10030', 'OLE10681')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10682', 'OLE80183', 'OLE10030', 'OLE10682')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10683', 'OLE80184', 'OLE10030', 'OLE10683')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10684', 'OLE80185', 'OLE10030', 'OLE10684')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10685', 'OLE80186', 'OLE10030', 'OLE10685')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10686', 'OLE80187', 'OLE10030', 'OLE10686')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10687', 'OLE80188', 'OLE10030', 'OLE10687')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10688', 'OLE80189', 'OLE10030', 'OLE10688')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10689', 'OLE80190', 'OLE10030', 'OLE10689')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10690', 'OLE80191', 'OLE10030', 'OLE10690')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10691', 'OLE80192', 'OLE10030', 'OLE10691')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10692', 'OLE80193', 'OLE10030', 'OLE10692')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10693', 'OLE80194', 'OLE10030', 'OLE10693')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10694', 'OLE80195', 'OLE10030', 'OLE10694')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10695', 'OLE80196', 'OLE10030', 'OLE10695')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10696', 'OLE80197', 'OLE10030', 'OLE10696')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10697', 'OLE80198', 'OLE10030', 'OLE10697')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10698', 'OLE80199', 'OLE10030', 'OLE10698')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10699', 'OLE80200', 'OLE10030', 'OLE10699')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10700', 'OLE80201', 'OLE10030', 'OLE10700')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10701', 'OLE80202', 'OLE10030', 'OLE10701')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10702', 'OLE80203', 'OLE10030', 'OLE10702')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10703', 'OLE80204', 'OLE10030', 'OLE10703')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10704', 'OLE80205', 'OLE10030', 'OLE10704')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10705', 'OLE80206', 'OLE10030', 'OLE10705')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10706', 'OLE80207', 'OLE10030', 'OLE10706')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10707', 'OLE80208', 'OLE10030', 'OLE10707')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10708', 'OLE80209', 'OLE10030', 'OLE10708')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10709', 'OLE80210', 'OLE10030', 'OLE10709')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10710', 'OLE80211', 'OLE10030', 'OLE10710')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10711', 'OLE80212', 'OLE10030', 'OLE10711')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10712', 'OLE80213', 'OLE10030', 'OLE10712')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10713', 'OLE80214', 'OLE10030', 'OLE10713')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10714', 'OLE80215', 'OLE10030', 'OLE10714')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10715', 'OLE80216', 'OLE10030', 'OLE10715')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10716', 'OLE80217', 'OLE10030', 'OLE10716')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10717', 'OLE80218', 'OLE10030', 'OLE10717')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10718', 'OLE80219', 'OLE10030', 'OLE10718')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10719', 'OLE80220', 'OLE10030', 'OLE10719')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10720', 'OLE80221', 'OLE10030', 'OLE10720')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10721', 'OLE80222', 'OLE10030', 'OLE10721')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10722', 'OLE80223', 'OLE10030', 'OLE10722')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10723', 'OLE80224', 'OLE10030', 'OLE10723')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10724', 'OLE80225', 'OLE10030', 'OLE10724')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10725', 'OLE80226', 'OLE10030', 'OLE10725')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10726', 'OLE80227', 'OLE10030', 'OLE10726')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10727', 'OLE80228', 'OLE10030', 'OLE10727')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10728', 'OLE80229', 'OLE10030', 'OLE10728')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10729', 'OLE80230', 'OLE10030', 'OLE10729')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10730', 'OLE80231', 'OLE10030', 'OLE10730')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10731', 'OLE80232', 'OLE10030', 'OLE10731')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10732', 'OLE80233', 'OLE10030', 'OLE10732')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10733', 'OLE80234', 'OLE10030', 'OLE10733')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10734', 'OLE80235', 'OLE10030', 'OLE10734')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10735', 'OLE80236', 'OLE10030', 'OLE10735')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10736', 'OLE80237', 'OLE10030', 'OLE10736')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10737', 'OLE80238', 'OLE10030', 'OLE10737')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10738', 'OLE80239', 'OLE10030', 'OLE10738')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10739', 'OLE80240', 'OLE10030', 'OLE10739')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10740', 'OLE80241', 'OLE10030', 'OLE10740')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10741', 'OLE80242', 'OLE10030', 'OLE10741')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10742', 'OLE80243', 'OLE10030', 'OLE10742')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10743', 'OLE80244', 'OLE10030', 'OLE10743')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10744', 'OLE80245', 'OLE10030', 'OLE10744')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10745', 'OLE80246', 'OLE10030', 'OLE10745')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10746', 'OLE80247', 'OLE10030', 'OLE10746')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10747', 'OLE80248', 'OLE10030', 'OLE10747')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10748', 'OLE80249', 'OLE10030', 'OLE10748')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10749', 'OLE80250', 'OLE10030', 'OLE10749')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10750', 'OLE80251', 'OLE10030', 'OLE10750')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10751', 'OLE80252', 'OLE10030', 'OLE10751')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10752', 'OLE80253', 'OLE10030', 'OLE10752')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10753', 'OLE80254', 'OLE10030', 'OLE10753')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10754', 'OLE80255', 'OLE10030', 'OLE10754')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10755', 'OLE80256', 'OLE10030', 'OLE10755')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10756', 'OLE80257', 'OLE10030', 'OLE10756')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10757', 'OLE80258', 'OLE10030', 'OLE10757')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10758', 'OLE80259', 'OLE10030', 'OLE10758')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10759', 'OLE80260', 'OLE10030', 'OLE10759')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10760', 'OLE80261', 'OLE10030', 'OLE10760')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10761', 'OLE80262', 'OLE10030', 'OLE10761')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10762', 'OLE80263', 'OLE10030', 'OLE10762')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10763', 'OLE80264', 'OLE10030', 'OLE10763')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10764', 'OLE80265', 'OLE10030', 'OLE10764')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10765', 'OLE80266', 'OLE10030', 'OLE10765')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10766', 'OLE80267', 'OLE10030', 'OLE10766')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10767', 'OLE80268', 'OLE10030', 'OLE10767')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10768', 'OLE80269', 'OLE10030', 'OLE10768')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10769', 'OLE80270', 'OLE10030', 'OLE10769')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10770', 'OLE80271', 'OLE10030', 'OLE10770')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10771', 'OLE80272', 'OLE10030', 'OLE10771')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10772', 'OLE80273', 'OLE10030', 'OLE10772')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10773', 'OLE80274', 'OLE10030', 'OLE10773')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10774', 'OLE80275', 'OLE10030', 'OLE10774')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10775', 'OLE80276', 'OLE10030', 'OLE10775')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10776', 'OLE80277', 'OLE10030', 'OLE10776')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10777', 'OLE80278', 'OLE10030', 'OLE10777')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10778', 'OLE80279', 'OLE10030', 'OLE10778')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10779', 'OLE80280', 'OLE10030', 'OLE10779')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10780', 'OLE80281', 'OLE10030', 'OLE10780')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10781', 'OLE80282', 'OLE10030', 'OLE10781')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10782', 'OLE80283', 'OLE10030', 'OLE10782')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10783', 'OLE80284', 'OLE10030', 'OLE10783')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10784', 'OLE80285', 'OLE10030', 'OLE10784')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10785', 'OLE80286', 'OLE10030', 'OLE10785')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10786', 'OLE80287', 'OLE10030', 'OLE10786')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10787', 'OLE80288', 'OLE10030', 'OLE10787')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10788', 'OLE80289', 'OLE10030', 'OLE10788')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10789', 'OLE80290', 'OLE10030', 'OLE10789')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10790', 'OLE80291', 'OLE10030', 'OLE10790')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10791', 'OLE80292', 'OLE10030', 'OLE10791')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10792', 'OLE80293', 'OLE10030', 'OLE10792')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10793', 'OLE80294', 'OLE10030', 'OLE10793')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10794', 'OLE80295', 'OLE10030', 'OLE10794')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10795', 'OLE80296', 'OLE10030', 'OLE10795')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10796', 'OLE80297', 'OLE10030', 'OLE10796')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10797', 'OLE80298', 'OLE10030', 'OLE10797')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10798', 'OLE80299', 'OLE10030', 'OLE10798')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10799', 'OLE80300', 'OLE10030', 'OLE10799')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10800', 'OLE80301', 'OLE10030', 'OLE10800')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10801', 'OLE80302', 'OLE10030', 'OLE10801')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10802', 'OLE80303', 'OLE10030', 'OLE10802')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10803', 'OLE80304', 'OLE10030', 'OLE10803')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10804', 'OLE80305', 'OLE10030', 'OLE10804')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10805', 'OLE80306', 'OLE10030', 'OLE10805')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10806', 'OLE80307', 'OLE10030', 'OLE10806')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10807', 'OLE80308', 'OLE10030', 'OLE10807')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10808', 'OLE80309', 'OLE10030', 'OLE10808')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10809', 'OLE80310', 'OLE10030', 'OLE10809')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10810', 'OLE80311', 'OLE10030', 'OLE10810')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10811', 'OLE80312', 'OLE10030', 'OLE10811')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10812', 'OLE80313', 'OLE10030', 'OLE10812')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10813', 'OLE80314', 'OLE10030', 'OLE10813')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10814', 'OLE80315', 'OLE10030', 'OLE10814')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10815', 'OLE80316', 'OLE10030', 'OLE10815')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10816', 'OLE80317', 'OLE10030', 'OLE10816')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10817', 'OLE80318', 'OLE10030', 'OLE10817')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10818', 'OLE80319', 'OLE10030', 'OLE10818')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10819', 'OLE80320', 'OLE10030', 'OLE10819')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10820', 'OLE80321', 'OLE10030', 'OLE10820')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10821', 'OLE80322', 'OLE10030', 'OLE10821')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10822', 'OLE80323', 'OLE10030', 'OLE10822')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10823', 'OLE80324', 'OLE10030', 'OLE10823')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10824', 'OLE80325', 'OLE10030', 'OLE10824')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10825', 'OLE80326', 'OLE10030', 'OLE10825')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10826', 'OLE80327', 'OLE10030', 'OLE10826')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10827', 'OLE80328', 'OLE10030', 'OLE10827')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10828', 'OLE80329', 'OLE10030', 'OLE10828')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10829', 'OLE80330', 'OLE10030', 'OLE10829')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10830', 'OLE80331', 'OLE10030', 'OLE10830')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10831', 'OLE80332', 'OLE10030', 'OLE10831')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10832', 'OLE80193', 'OLE10037', 'OLE10832')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10833', 'OLE80194', 'OLE10037', 'OLE10833')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10834', 'OLE80195', 'OLE10037', 'OLE10834')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10835', 'OLE80196', 'OLE10037', 'OLE10835')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10836', 'OLE80209', 'OLE10037', 'OLE10836')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10837', 'OLE80210', 'OLE10037', 'OLE10837')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10838', 'OLE80211', 'OLE10037', 'OLE10838')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10839', 'OLE80212', 'OLE10037', 'OLE10839')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10840', 'OLE80213', 'OLE10037', 'OLE10840')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10841', 'OLE80214', 'OLE10037', 'OLE10841')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10842', 'OLE80215', 'OLE10037', 'OLE10842')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10843', 'OLE80216', 'OLE10037', 'OLE10843')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10844', 'OLE80237', 'OLE10037', 'OLE10844')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10845', 'OLE80238', 'OLE10037', 'OLE10845')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10846', 'OLE80239', 'OLE10037', 'OLE10846')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10847', 'OLE80240', 'OLE10037', 'OLE10847')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10848', 'OLE80253', 'OLE10037', 'OLE10848')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10849', 'OLE80254', 'OLE10037', 'OLE10849')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10850', 'OLE80255', 'OLE10037', 'OLE10850')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10851', 'OLE80256', 'OLE10037', 'OLE10851')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10852', 'OLE80257', 'OLE10037', 'OLE10852')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10853', 'OLE80258', 'OLE10037', 'OLE10853')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10854', 'OLE80259', 'OLE10037', 'OLE10854')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10855', 'OLE80260', 'OLE10037', 'OLE10855')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10856', 'OLE80261', 'OLE10037', 'OLE10856')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10857', 'OLE80262', 'OLE10037', 'OLE10857')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10858', 'OLE80263', 'OLE10037', 'OLE10858')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10859', 'OLE80264', 'OLE10037', 'OLE10859')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10860', 'OLE80305', 'OLE10037', 'OLE10860')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10861', 'OLE80306', 'OLE10037', 'OLE10861')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10862', 'OLE80307', 'OLE10037', 'OLE10862')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10863', 'OLE80308', 'OLE10037', 'OLE10863')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10864', 'OLE80193', 'OLE10038', 'OLE10864')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10865', 'OLE80194', 'OLE10038', 'OLE10865')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10866', 'OLE80195', 'OLE10038', 'OLE10866')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10867', 'OLE80196', 'OLE10038', 'OLE10867')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10868', 'OLE80209', 'OLE10038', 'OLE10868')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10869', 'OLE80210', 'OLE10038', 'OLE10869')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10870', 'OLE80211', 'OLE10038', 'OLE10870')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10871', 'OLE80212', 'OLE10038', 'OLE10871')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10872', 'OLE80213', 'OLE10038', 'OLE10872')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10873', 'OLE80214', 'OLE10038', 'OLE10873')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10874', 'OLE80215', 'OLE10038', 'OLE10874')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10875', 'OLE80216', 'OLE10038', 'OLE10875')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10876', 'OLE80237', 'OLE10038', 'OLE10876')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10877', 'OLE80238', 'OLE10038', 'OLE10877')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10878', 'OLE80239', 'OLE10038', 'OLE10878')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10879', 'OLE80240', 'OLE10038', 'OLE10879')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10880', 'OLE80253', 'OLE10038', 'OLE10880')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10881', 'OLE80254', 'OLE10038', 'OLE10881')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10882', 'OLE80255', 'OLE10038', 'OLE10882')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10883', 'OLE80256', 'OLE10038', 'OLE10883')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10884', 'OLE80257', 'OLE10038', 'OLE10884')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10885', 'OLE80258', 'OLE10038', 'OLE10885')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10886', 'OLE80259', 'OLE10038', 'OLE10886')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10887', 'OLE80260', 'OLE10038', 'OLE10887')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10888', 'OLE80261', 'OLE10038', 'OLE10888')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10889', 'OLE80262', 'OLE10038', 'OLE10889')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10890', 'OLE80263', 'OLE10038', 'OLE10890')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10891', 'OLE80264', 'OLE10038', 'OLE10891')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10892', 'OLE80305', 'OLE10038', 'OLE10892')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10893', 'OLE80306', 'OLE10038', 'OLE10893')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10894', 'OLE80307', 'OLE10038', 'OLE10894')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10895', 'OLE80308', 'OLE10038', 'OLE10895')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10896', 'OLE80193', 'OLE10041', 'OLE10896')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10897', 'OLE80194', 'OLE10041', 'OLE10897')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10898', 'OLE80195', 'OLE10041', 'OLE10898')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10899', 'OLE80196', 'OLE10041', 'OLE10899')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10900', 'OLE80209', 'OLE10041', 'OLE10900')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10901', 'OLE80210', 'OLE10041', 'OLE10901')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10902', 'OLE80211', 'OLE10041', 'OLE10902')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10903', 'OLE80212', 'OLE10041', 'OLE10903')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10904', 'OLE80213', 'OLE10041', 'OLE10904')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10905', 'OLE80214', 'OLE10041', 'OLE10905')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10906', 'OLE80215', 'OLE10041', 'OLE10906')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10907', 'OLE80216', 'OLE10041', 'OLE10907')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10908', 'OLE80237', 'OLE10041', 'OLE10908')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10909', 'OLE80238', 'OLE10041', 'OLE10909')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10910', 'OLE80239', 'OLE10041', 'OLE10910')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10911', 'OLE80240', 'OLE10041', 'OLE10911')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10912', 'OLE80253', 'OLE10041', 'OLE10912')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10913', 'OLE80254', 'OLE10041', 'OLE10913')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10914', 'OLE80255', 'OLE10041', 'OLE10914')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10915', 'OLE80256', 'OLE10041', 'OLE10915')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10916', 'OLE80257', 'OLE10041', 'OLE10916')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10917', 'OLE80258', 'OLE10041', 'OLE10917')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10918', 'OLE80259', 'OLE10041', 'OLE10918')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10919', 'OLE80260', 'OLE10041', 'OLE10919')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10920', 'OLE80261', 'OLE10041', 'OLE10920')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10921', 'OLE80262', 'OLE10041', 'OLE10921')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10922', 'OLE80263', 'OLE10041', 'OLE10922')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10923', 'OLE80264', 'OLE10041', 'OLE10923')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10924', 'OLE80305', 'OLE10041', 'OLE10924')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10925', 'OLE80306', 'OLE10041', 'OLE10925')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10926', 'OLE80307', 'OLE10041', 'OLE10926')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10927', 'OLE80308', 'OLE10041', 'OLE10927')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10928', 'OLE80193', 'OLE10042', 'OLE10928')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10929', 'OLE80194', 'OLE10042', 'OLE10929')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10930', 'OLE80195', 'OLE10042', 'OLE10930')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10931', 'OLE80196', 'OLE10042', 'OLE10931')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10932', 'OLE80209', 'OLE10042', 'OLE10932')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10933', 'OLE80210', 'OLE10042', 'OLE10933')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10934', 'OLE80211', 'OLE10042', 'OLE10934')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10935', 'OLE80212', 'OLE10042', 'OLE10935')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10936', 'OLE80213', 'OLE10042', 'OLE10936')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10937', 'OLE80214', 'OLE10042', 'OLE10937')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10938', 'OLE80215', 'OLE10042', 'OLE10938')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10939', 'OLE80216', 'OLE10042', 'OLE10939')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10940', 'OLE80237', 'OLE10042', 'OLE10940')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10941', 'OLE80238', 'OLE10042', 'OLE10941')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10942', 'OLE80239', 'OLE10042', 'OLE10942')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10943', 'OLE80240', 'OLE10042', 'OLE10943')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10944', 'OLE80253', 'OLE10042', 'OLE10944')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10945', 'OLE80254', 'OLE10042', 'OLE10945')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10946', 'OLE80255', 'OLE10042', 'OLE10946')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10947', 'OLE80256', 'OLE10042', 'OLE10947')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10948', 'OLE80257', 'OLE10042', 'OLE10948')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10949', 'OLE80258', 'OLE10042', 'OLE10949')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10950', 'OLE80259', 'OLE10042', 'OLE10950')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10951', 'OLE80260', 'OLE10042', 'OLE10951')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10952', 'OLE80261', 'OLE10042', 'OLE10952')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10953', 'OLE80262', 'OLE10042', 'OLE10953')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10954', 'OLE80263', 'OLE10042', 'OLE10954')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10955', 'OLE80264', 'OLE10042', 'OLE10955')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10956', 'OLE80305', 'OLE10042', 'OLE10956')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10957', 'OLE80306', 'OLE10042', 'OLE10957')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10958', 'OLE80307', 'OLE10042', 'OLE10958')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10959', 'OLE80308', 'OLE10042', 'OLE10959')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10960', 'OLE80193', 'OLE10043', 'OLE10960')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10961', 'OLE80194', 'OLE10043', 'OLE10961')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10962', 'OLE80195', 'OLE10043', 'OLE10962')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10963', 'OLE80196', 'OLE10043', 'OLE10963')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10964', 'OLE80209', 'OLE10043', 'OLE10964')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10965', 'OLE80210', 'OLE10043', 'OLE10965')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10966', 'OLE80211', 'OLE10043', 'OLE10966')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10967', 'OLE80212', 'OLE10043', 'OLE10967')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10968', 'OLE80213', 'OLE10043', 'OLE10968')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10969', 'OLE80214', 'OLE10043', 'OLE10969')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10970', 'OLE80215', 'OLE10043', 'OLE10970')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10971', 'OLE80216', 'OLE10043', 'OLE10971')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10972', 'OLE80237', 'OLE10043', 'OLE10972')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10973', 'OLE80238', 'OLE10043', 'OLE10973')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10974', 'OLE80239', 'OLE10043', 'OLE10974')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10975', 'OLE80240', 'OLE10043', 'OLE10975')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10976', 'OLE80253', 'OLE10043', 'OLE10976')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10977', 'OLE80254', 'OLE10043', 'OLE10977')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10978', 'OLE80255', 'OLE10043', 'OLE10978')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10979', 'OLE80256', 'OLE10043', 'OLE10979')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10980', 'OLE80257', 'OLE10043', 'OLE10980')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10981', 'OLE80258', 'OLE10043', 'OLE10981')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10982', 'OLE80259', 'OLE10043', 'OLE10982')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10983', 'OLE80260', 'OLE10043', 'OLE10983')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10984', 'OLE80261', 'OLE10043', 'OLE10984')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10985', 'OLE80262', 'OLE10043', 'OLE10985')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10986', 'OLE80263', 'OLE10043', 'OLE10986')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10987', 'OLE80264', 'OLE10043', 'OLE10987')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10988', 'OLE80305', 'OLE10043', 'OLE10988')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10989', 'OLE80306', 'OLE10043', 'OLE10989')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10990', 'OLE80307', 'OLE10043', 'OLE10990')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10991', 'OLE80308', 'OLE10043', 'OLE10991')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10992', 'OLE80333', 'OLE10036', 'OLE10992')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10993', 'OLE80333', 'OLE10037', 'OLE10993')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10994', 'OLE80333', 'OLE10038', 'OLE10994')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10995', 'OLE80333', 'OLE10019', 'OLE10995')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10996', 'OLE80333', 'OLE10033', 'OLE10996')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10997', 'OLE80333', 'OLE10034', 'OLE10997')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10998', 'OLE80333', 'OLE10020', 'OLE10998')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE10999', 'OLE80333', 'OLE10044', 'OLE10999')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11000', 'OLE80333', 'OLE62', 'OLE11000')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11001', 'OLE80333', 'OLE22', 'OLE11001')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11002', 'OLE80333', 'OLE10016', 'OLE11002')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11003', 'OLE80333', '66', 'OLE11003')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11004', 'OLE10057', 'OLE10033', 'OLE11004')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11005', 'OLE10057', 'OLE10034', 'OLE11005')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11006', 'OLE80301', 'OLE10037', 'OLE11006')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11007', 'OLE80302', 'OLE10037', 'OLE11007')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11008', 'OLE80303', 'OLE10037', 'OLE11008')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11009', 'OLE80304', 'OLE10037', 'OLE11009')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11010', 'OLE80301', 'OLE10038', 'OLE11010')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11011', 'OLE80302', 'OLE10038', 'OLE11011')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11012', 'OLE80303', 'OLE10038', 'OLE11012')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11013', 'OLE80304', 'OLE10038', 'OLE11013')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11014', 'OLE80301', 'OLE10041', 'OLE11014')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11015', 'OLE80302', 'OLE10041', 'OLE11015')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11016', 'OLE80303', 'OLE10041', 'OLE11016')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11017', 'OLE80304', 'OLE10041', 'OLE11017')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11018', 'OLE80301', 'OLE10042', 'OLE11018')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11019', 'OLE80302', 'OLE10042', 'OLE11019')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11020', 'OLE80303', 'OLE10042', 'OLE11020')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11021', 'OLE80304', 'OLE10042', 'OLE11021')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11022', 'OLE80301', 'OLE10043', 'OLE11022')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11023', 'OLE80302', 'OLE10043', 'OLE11023')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11024', 'OLE80303', 'OLE10043', 'OLE11024')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11025', 'OLE80304', 'OLE10043', 'OLE11025')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11026', 'OLE80334', 'OLE10042', 'OLE11026')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11027', 'OLE80335', 'OLE10042', 'OLE11027')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11028', 'OLE80336', 'OLE10042', 'OLE11028')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11029', 'OLE80337', 'OLE10042', 'OLE11029')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11030', 'OLE80338', 'OLE10042', 'OLE11030')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11031', 'OLE80339', 'OLE10042', 'OLE11031')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11032', 'OLE80340', 'OLE10042', 'OLE11032')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11033', 'OLE80341', 'OLE10042', 'OLE11033')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11034', 'OLE80342', 'OLE10042', 'OLE11034')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11035', 'OLE80343', 'OLE10042', 'OLE11035')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11036', 'OLE80344', 'OLE10042', 'OLE11036')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11037', 'OLE80345', 'OLE10042', 'OLE11037')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11038', 'OLE80346', 'OLE10042', 'OLE11038')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11039', 'OLE80347', 'OLE10042', 'OLE11039')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11040', 'OLE80348', 'OLE10042', 'OLE11040')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11041', 'OLE80349', 'OLE10042', 'OLE11041')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11042', 'OLE80350', 'OLE10042', 'OLE11042')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11043', 'OLE80351', 'OLE10042', 'OLE11043')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11044', 'OLE80352', 'OLE10042', 'OLE11044')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11045', 'OLE80353', 'OLE10042', 'OLE11045')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11046', 'OLE80354', 'OLE10042', 'OLE11046')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11047', 'OLE80355', 'OLE10042', 'OLE11047')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11048', 'OLE80356', 'OLE10042', 'OLE11048')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11049', 'OLE80357', 'OLE10042', 'OLE11049')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11050', 'OLE80358', 'OLE10042', 'OLE11050')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11051', 'OLE80359', 'OLE10042', 'OLE11051')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11052', 'OLE80360', 'OLE10042', 'OLE11052')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11053', 'OLE80361', 'OLE10042', 'OLE11053')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11054', 'OLE80362', 'OLE10042', 'OLE11054')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11055', 'OLE80363', 'OLE10042', 'OLE11055')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11056', 'OLE80364', 'OLE10042', 'OLE11056')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11057', 'OLE80365', 'OLE10042', 'OLE11057')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11058', 'OLE80366', 'OLE10042', 'OLE11058')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11059', 'OLE80367', 'OLE10042', 'OLE11059')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11060', 'OLE80368', 'OLE10042', 'OLE11060')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11061', 'OLE80369', 'OLE10042', 'OLE11061')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11062', 'OLE80370', 'OLE10042', 'OLE11062')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11063', 'OLE80371', 'OLE10042', 'OLE11063')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11064', 'OLE80372', 'OLE10042', 'OLE11064')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11065', 'OLE80373', 'OLE10042', 'OLE11065')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11066', 'OLE80374', 'OLE10042', 'OLE11066')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11067', 'OLE80375', 'OLE10042', 'OLE11067')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11068', 'OLE80376', 'OLE10042', 'OLE11068')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11069', 'OLE80377', 'OLE10042', 'OLE11069')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11070', 'OLE80378', 'OLE10042', 'OLE11070')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11071', 'OLE80379', 'OLE10042', 'OLE11071')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11072', 'OLE80380', 'OLE10042', 'OLE11072')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11073', 'OLE80381', 'OLE10042', 'OLE11073')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11074', 'OLE80382', 'OLE10042', 'OLE11074')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11075', 'OLE80383', 'OLE10042', 'OLE11075')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11076', 'OLE80384', 'OLE10042', 'OLE11076')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11077', 'OLE80385', 'OLE10042', 'OLE11077')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11078', 'OLE80386', 'OLE10042', 'OLE11078')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11079', 'OLE80387', 'OLE10042', 'OLE11079')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11080', 'OLE80388', 'OLE10042', 'OLE11080')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11081', 'OLE80389', 'OLE10042', 'OLE11081')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11082', 'OLE80390', 'OLE10042', 'OLE11082')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11083', 'OLE80391', 'OLE10042', 'OLE11083')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11084', 'OLE80392', 'OLE10042', 'OLE11084')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11085', 'OLE80393', 'OLE10042', 'OLE11085')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11086', 'OLE80394', 'OLE10042', 'OLE11086')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11087', 'OLE80395', 'OLE10042', 'OLE11087')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11088', 'OLE80396', 'OLE10042', 'OLE11088')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11089', 'OLE80397', 'OLE10042', 'OLE11089')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11090', 'OLE80398', 'OLE10042', 'OLE11090')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11091', 'OLE80399', 'OLE10042', 'OLE11091')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11092', 'OLE80400', 'OLE10042', 'OLE11092')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11093', 'OLE80401', 'OLE10042', 'OLE11093')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11094', 'OLE80402', 'OLE10042', 'OLE11094')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11095', 'OLE80403', 'OLE10042', 'OLE11095')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11096', 'OLE80404', 'OLE10042', 'OLE11096')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11097', 'OLE80405', 'OLE10042', 'OLE11097')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11098', 'OLE80406', 'OLE10042', 'OLE11098')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11099', 'OLE80407', 'OLE10042', 'OLE11099')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11100', 'OLE80408', 'OLE10042', 'OLE11100')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11101', 'OLE80409', 'OLE10042', 'OLE11101')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11102', 'OLE80410', 'OLE10042', 'OLE11102')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11103', 'OLE80411', 'OLE10042', 'OLE11103')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11104', 'OLE80412', 'OLE10042', 'OLE11104')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11105', 'OLE80413', 'OLE10042', 'OLE11105')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11106', 'OLE80414', 'OLE10042', 'OLE11106')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11107', 'OLE80415', 'OLE10042', 'OLE11107')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11108', 'OLE80416', 'OLE10042', 'OLE11108')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11109', 'OLE80417', 'OLE10042', 'OLE11109')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11110', 'OLE80418', 'OLE10042', 'OLE11110')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11111', 'OLE80419', 'OLE10042', 'OLE11111')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11112', 'OLE80420', 'OLE10042', 'OLE11112')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11113', 'OLE80421', 'OLE10042', 'OLE11113')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11114', 'OLE80422', 'OLE10042', 'OLE11114')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11115', 'OLE80423', 'OLE10042', 'OLE11115')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11116', 'OLE80424', 'OLE10042', 'OLE11116')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11117', 'OLE80425', 'OLE10042', 'OLE11117')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11118', 'OLE80426', 'OLE10042', 'OLE11118')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11119', 'OLE80427', 'OLE10042', 'OLE11119')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11120', 'OLE80428', 'OLE10042', 'OLE11120')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11121', 'OLE80429', 'OLE10042', 'OLE11121')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11122', 'OLE80430', 'OLE10042', 'OLE11122')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11123', 'OLE80431', 'OLE10042', 'OLE11123')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11124', 'OLE80432', 'OLE10042', 'OLE11124')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11125', 'OLE80433', 'OLE10042', 'OLE11125')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11126', 'OLE80434', 'OLE10042', 'OLE11126')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11127', 'OLE80435', 'OLE10042', 'OLE11127')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11128', 'OLE80436', 'OLE10042', 'OLE11128')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11129', 'OLE80437', 'OLE10042', 'OLE11129')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11130', 'OLE80438', 'OLE10042', 'OLE11130')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11131', 'OLE80439', 'OLE10042', 'OLE11131')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11132', 'OLE80440', 'OLE10042', 'OLE11132')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11133', 'OLE80441', 'OLE10042', 'OLE11133')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11134', 'OLE80442', 'OLE10042', 'OLE11134')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11135', 'OLE80443', 'OLE10042', 'OLE11135')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11136', 'OLE80444', 'OLE10042', 'OLE11136')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11137', 'OLE80445', 'OLE10042', 'OLE11137')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11138', 'OLE80446', 'OLE10042', 'OLE11138')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11139', 'OLE80447', 'OLE10042', 'OLE11139')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11140', 'OLE80448', 'OLE10042', 'OLE11140')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11141', 'OLE80449', 'OLE10042', 'OLE11141')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11142', 'OLE80450', 'OLE10042', 'OLE11142')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11143', 'OLE80451', 'OLE10042', 'OLE11143')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11144', 'OLE80452', 'OLE10042', 'OLE11144')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11145', 'OLE80453', 'OLE10042', 'OLE11145')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11146', 'OLE80454', 'OLE10042', 'OLE11146')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11147', 'OLE80455', 'OLE10042', 'OLE11147')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11148', 'OLE80456', 'OLE10042', 'OLE11148')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11149', 'OLE80457', 'OLE10042', 'OLE11149')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11150', 'OLE80458', 'OLE10042', 'OLE11150')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11151', 'OLE80459', 'OLE10042', 'OLE11151')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11152', 'OLE80460', 'OLE10042', 'OLE11152')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11153', 'OLE80461', 'OLE10042', 'OLE11153')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11154', 'OLE80462', 'OLE10042', 'OLE11154')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11155', 'OLE80463', 'OLE10042', 'OLE11155')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11156', 'OLE80464', 'OLE10042', 'OLE11156')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11157', 'OLE80465', 'OLE10042', 'OLE11157')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11158', 'OLE80466', 'OLE10042', 'OLE11158')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11159', 'OLE80467', 'OLE10042', 'OLE11159')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11160', 'OLE80468', 'OLE10042', 'OLE11160')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11161', 'OLE80469', 'OLE10042', 'OLE11161')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11162', 'OLE80470', 'OLE10042', 'OLE11162')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11163', 'OLE80471', 'OLE10042', 'OLE11163')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11164', 'OLE80472', 'OLE10042', 'OLE11164')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11165', 'OLE80473', 'OLE10042', 'OLE11165')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11166', 'OLE80474', 'OLE10042', 'OLE11166')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11167', 'OLE80475', 'OLE10042', 'OLE11167')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11168', 'OLE80476', 'OLE10042', 'OLE11168')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11169', 'OLE80477', 'OLE10042', 'OLE11169')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11170', 'OLE80478', 'OLE10042', 'OLE11170')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11171', 'OLE80479', 'OLE10042', 'OLE11171')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11172', 'OLE80480', 'OLE10042', 'OLE11172')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11173', 'OLE80481', 'OLE10042', 'OLE11173')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11174', 'OLE80482', 'OLE10042', 'OLE11174')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11175', 'OLE80483', 'OLE10042', 'OLE11175')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11176', 'OLE80484', 'OLE10042', 'OLE11176')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11177', 'OLE80485', 'OLE10042', 'OLE11177')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11178', 'OLE80486', 'OLE10042', 'OLE11178')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11179', 'OLE80487', 'OLE10042', 'OLE11179')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11180', 'OLE80488', 'OLE10042', 'OLE11180')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11181', 'OLE80489', 'OLE10042', 'OLE11181')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11182', 'OLE80490', 'OLE10042', 'OLE11182')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11183', 'OLE80491', 'OLE10042', 'OLE11183')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11184', 'OLE80492', 'OLE10042', 'OLE11184')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11185', 'OLE80493', 'OLE10042', 'OLE11185')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11186', 'OLE80494', 'OLE10042', 'OLE11186')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11187', 'OLE80495', 'OLE10042', 'OLE11187')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11188', 'OLE80496', 'OLE10042', 'OLE11188')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11189', 'OLE80497', 'OLE10042', 'OLE11189')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11190', 'OLE80334', 'OLE10043', 'OLE11190')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11191', 'OLE80335', 'OLE10043', 'OLE11191')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11192', 'OLE80336', 'OLE10043', 'OLE11192')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11193', 'OLE80337', 'OLE10043', 'OLE11193')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11194', 'OLE80338', 'OLE10043', 'OLE11194')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11195', 'OLE80339', 'OLE10043', 'OLE11195')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11196', 'OLE80340', 'OLE10043', 'OLE11196')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11197', 'OLE80341', 'OLE10043', 'OLE11197')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11198', 'OLE80342', 'OLE10043', 'OLE11198')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11199', 'OLE80343', 'OLE10043', 'OLE11199')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11200', 'OLE80344', 'OLE10043', 'OLE11200')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11201', 'OLE80345', 'OLE10043', 'OLE11201')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11202', 'OLE80346', 'OLE10043', 'OLE11202')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11203', 'OLE80347', 'OLE10043', 'OLE11203')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11204', 'OLE80348', 'OLE10043', 'OLE11204')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11205', 'OLE80349', 'OLE10043', 'OLE11205')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11206', 'OLE80350', 'OLE10043', 'OLE11206')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11207', 'OLE80351', 'OLE10043', 'OLE11207')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11208', 'OLE80352', 'OLE10043', 'OLE11208')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11209', 'OLE80353', 'OLE10043', 'OLE11209')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11210', 'OLE80354', 'OLE10043', 'OLE11210')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11211', 'OLE80355', 'OLE10043', 'OLE11211')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11212', 'OLE80356', 'OLE10043', 'OLE11212')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11213', 'OLE80357', 'OLE10043', 'OLE11213')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11214', 'OLE80358', 'OLE10043', 'OLE11214')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11215', 'OLE80359', 'OLE10043', 'OLE11215')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11216', 'OLE80360', 'OLE10043', 'OLE11216')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11217', 'OLE80361', 'OLE10043', 'OLE11217')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11218', 'OLE80362', 'OLE10043', 'OLE11218')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11219', 'OLE80363', 'OLE10043', 'OLE11219')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11220', 'OLE80364', 'OLE10043', 'OLE11220')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11221', 'OLE80365', 'OLE10043', 'OLE11221')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11222', 'OLE80366', 'OLE10043', 'OLE11222')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11223', 'OLE80367', 'OLE10043', 'OLE11223')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11224', 'OLE80368', 'OLE10043', 'OLE11224')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11225', 'OLE80369', 'OLE10043', 'OLE11225')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11226', 'OLE80370', 'OLE10043', 'OLE11226')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11227', 'OLE80371', 'OLE10043', 'OLE11227')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11228', 'OLE80372', 'OLE10043', 'OLE11228')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11229', 'OLE80373', 'OLE10043', 'OLE11229')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11230', 'OLE80374', 'OLE10043', 'OLE11230')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11231', 'OLE80375', 'OLE10043', 'OLE11231')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11232', 'OLE80376', 'OLE10043', 'OLE11232')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11233', 'OLE80377', 'OLE10043', 'OLE11233')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11234', 'OLE80378', 'OLE10043', 'OLE11234')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11235', 'OLE80379', 'OLE10043', 'OLE11235')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11236', 'OLE80380', 'OLE10043', 'OLE11236')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11237', 'OLE80381', 'OLE10043', 'OLE11237')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11238', 'OLE80382', 'OLE10043', 'OLE11238')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11239', 'OLE80383', 'OLE10043', 'OLE11239')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11240', 'OLE80384', 'OLE10043', 'OLE11240')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11241', 'OLE80385', 'OLE10043', 'OLE11241')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11242', 'OLE80386', 'OLE10043', 'OLE11242')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11243', 'OLE80387', 'OLE10043', 'OLE11243')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11244', 'OLE80388', 'OLE10043', 'OLE11244')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11245', 'OLE80389', 'OLE10043', 'OLE11245')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11246', 'OLE80390', 'OLE10043', 'OLE11246')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11247', 'OLE80391', 'OLE10043', 'OLE11247')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11248', 'OLE80392', 'OLE10043', 'OLE11248')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11249', 'OLE80393', 'OLE10043', 'OLE11249')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11250', 'OLE80394', 'OLE10043', 'OLE11250')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11251', 'OLE80395', 'OLE10043', 'OLE11251')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11252', 'OLE80396', 'OLE10043', 'OLE11252')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11253', 'OLE80397', 'OLE10043', 'OLE11253')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11254', 'OLE80398', 'OLE10043', 'OLE11254')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11255', 'OLE80399', 'OLE10043', 'OLE11255')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11256', 'OLE80400', 'OLE10043', 'OLE11256')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11257', 'OLE80401', 'OLE10043', 'OLE11257')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11258', 'OLE80402', 'OLE10043', 'OLE11258')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11259', 'OLE80403', 'OLE10043', 'OLE11259')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11260', 'OLE80404', 'OLE10043', 'OLE11260')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11261', 'OLE80405', 'OLE10043', 'OLE11261')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11262', 'OLE80406', 'OLE10043', 'OLE11262')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11263', 'OLE80407', 'OLE10043', 'OLE11263')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11264', 'OLE80408', 'OLE10043', 'OLE11264')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11265', 'OLE80409', 'OLE10043', 'OLE11265')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11266', 'OLE80410', 'OLE10043', 'OLE11266')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11267', 'OLE80411', 'OLE10043', 'OLE11267')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11268', 'OLE80412', 'OLE10043', 'OLE11268')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11269', 'OLE80413', 'OLE10043', 'OLE11269')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11270', 'OLE80414', 'OLE10043', 'OLE11270')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11271', 'OLE80415', 'OLE10043', 'OLE11271')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11272', 'OLE80416', 'OLE10043', 'OLE11272')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11273', 'OLE80417', 'OLE10043', 'OLE11273')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11274', 'OLE80418', 'OLE10043', 'OLE11274')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11275', 'OLE80419', 'OLE10043', 'OLE11275')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11276', 'OLE80420', 'OLE10043', 'OLE11276')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11277', 'OLE80421', 'OLE10043', 'OLE11277')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11278', 'OLE80422', 'OLE10043', 'OLE11278')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11279', 'OLE80423', 'OLE10043', 'OLE11279')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11280', 'OLE80424', 'OLE10043', 'OLE11280')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11281', 'OLE80425', 'OLE10043', 'OLE11281')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11282', 'OLE80426', 'OLE10043', 'OLE11282')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11283', 'OLE80427', 'OLE10043', 'OLE11283')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11284', 'OLE80428', 'OLE10043', 'OLE11284')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11285', 'OLE80429', 'OLE10043', 'OLE11285')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11286', 'OLE80430', 'OLE10043', 'OLE11286')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11287', 'OLE80431', 'OLE10043', 'OLE11287')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11288', 'OLE80432', 'OLE10043', 'OLE11288')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11289', 'OLE80433', 'OLE10043', 'OLE11289')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11290', 'OLE80434', 'OLE10043', 'OLE11290')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11291', 'OLE80435', 'OLE10043', 'OLE11291')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11292', 'OLE80436', 'OLE10043', 'OLE11292')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11293', 'OLE80437', 'OLE10043', 'OLE11293')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11294', 'OLE80438', 'OLE10043', 'OLE11294')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11295', 'OLE80439', 'OLE10043', 'OLE11295')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11296', 'OLE80440', 'OLE10043', 'OLE11296')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11297', 'OLE80441', 'OLE10043', 'OLE11297')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11298', 'OLE80442', 'OLE10043', 'OLE11298')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11299', 'OLE80443', 'OLE10043', 'OLE11299')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11300', 'OLE80444', 'OLE10043', 'OLE11300')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11301', 'OLE80445', 'OLE10043', 'OLE11301')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11302', 'OLE80446', 'OLE10043', 'OLE11302')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11303', 'OLE80447', 'OLE10043', 'OLE11303')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11304', 'OLE80448', 'OLE10043', 'OLE11304')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11305', 'OLE80449', 'OLE10043', 'OLE11305')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11306', 'OLE80450', 'OLE10043', 'OLE11306')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11307', 'OLE80451', 'OLE10043', 'OLE11307')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11308', 'OLE80452', 'OLE10043', 'OLE11308')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11309', 'OLE80453', 'OLE10043', 'OLE11309')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11310', 'OLE80454', 'OLE10043', 'OLE11310')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11311', 'OLE80455', 'OLE10043', 'OLE11311')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11312', 'OLE80456', 'OLE10043', 'OLE11312')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11313', 'OLE80457', 'OLE10043', 'OLE11313')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11314', 'OLE80458', 'OLE10043', 'OLE11314')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11315', 'OLE80459', 'OLE10043', 'OLE11315')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11316', 'OLE80460', 'OLE10043', 'OLE11316')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11317', 'OLE80461', 'OLE10043', 'OLE11317')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11318', 'OLE80462', 'OLE10043', 'OLE11318')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11319', 'OLE80463', 'OLE10043', 'OLE11319')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11320', 'OLE80464', 'OLE10043', 'OLE11320')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11321', 'OLE80465', 'OLE10043', 'OLE11321')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11322', 'OLE80466', 'OLE10043', 'OLE11322')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11323', 'OLE80467', 'OLE10043', 'OLE11323')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11324', 'OLE80468', 'OLE10043', 'OLE11324')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11325', 'OLE80469', 'OLE10043', 'OLE11325')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11326', 'OLE80470', 'OLE10043', 'OLE11326')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11327', 'OLE80471', 'OLE10043', 'OLE11327')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11328', 'OLE80472', 'OLE10043', 'OLE11328')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11329', 'OLE80473', 'OLE10043', 'OLE11329')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11330', 'OLE80474', 'OLE10043', 'OLE11330')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11331', 'OLE80475', 'OLE10043', 'OLE11331')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11332', 'OLE80476', 'OLE10043', 'OLE11332')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11333', 'OLE80477', 'OLE10043', 'OLE11333')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11334', 'OLE80478', 'OLE10043', 'OLE11334')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11335', 'OLE80479', 'OLE10043', 'OLE11335')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11336', 'OLE80480', 'OLE10043', 'OLE11336')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11337', 'OLE80481', 'OLE10043', 'OLE11337')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11338', 'OLE80482', 'OLE10043', 'OLE11338')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11339', 'OLE80483', 'OLE10043', 'OLE11339')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11340', 'OLE80484', 'OLE10043', 'OLE11340')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11341', 'OLE80485', 'OLE10043', 'OLE11341')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11342', 'OLE80486', 'OLE10043', 'OLE11342')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11343', 'OLE80487', 'OLE10043', 'OLE11343')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11344', 'OLE80488', 'OLE10043', 'OLE11344')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11345', 'OLE80489', 'OLE10043', 'OLE11345')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11346', 'OLE80490', 'OLE10043', 'OLE11346')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11347', 'OLE80491', 'OLE10043', 'OLE11347')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11348', 'OLE80492', 'OLE10043', 'OLE11348')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11349', 'OLE80493', 'OLE10043', 'OLE11349')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11350', 'OLE80494', 'OLE10043', 'OLE11350')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11351', 'OLE80495', 'OLE10043', 'OLE11351')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11352', 'OLE80496', 'OLE10043', 'OLE11352')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11353', 'OLE80497', 'OLE10043', 'OLE11353')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11354', 'OLE80334', 'OLE10038', 'OLE11354')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11355', 'OLE80335', 'OLE10038', 'OLE11355')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11356', 'OLE80336', 'OLE10038', 'OLE11356')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11357', 'OLE80337', 'OLE10038', 'OLE11357')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11358', 'OLE80338', 'OLE10038', 'OLE11358')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11359', 'OLE80339', 'OLE10038', 'OLE11359')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11360', 'OLE80340', 'OLE10038', 'OLE11360')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11361', 'OLE80341', 'OLE10038', 'OLE11361')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11362', 'OLE80342', 'OLE10038', 'OLE11362')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11363', 'OLE80343', 'OLE10038', 'OLE11363')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11364', 'OLE80344', 'OLE10038', 'OLE11364')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11365', 'OLE80345', 'OLE10038', 'OLE11365')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11366', 'OLE80346', 'OLE10038', 'OLE11366')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11367', 'OLE80347', 'OLE10038', 'OLE11367')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11368', 'OLE80348', 'OLE10038', 'OLE11368')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11369', 'OLE80349', 'OLE10038', 'OLE11369')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11370', 'OLE80350', 'OLE10038', 'OLE11370')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11371', 'OLE80351', 'OLE10038', 'OLE11371')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11372', 'OLE80352', 'OLE10038', 'OLE11372')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11373', 'OLE80353', 'OLE10038', 'OLE11373')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11374', 'OLE80354', 'OLE10038', 'OLE11374')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11375', 'OLE80355', 'OLE10038', 'OLE11375')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11376', 'OLE80356', 'OLE10038', 'OLE11376')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11377', 'OLE80357', 'OLE10038', 'OLE11377')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11378', 'OLE80358', 'OLE10038', 'OLE11378')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11379', 'OLE80359', 'OLE10038', 'OLE11379')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11380', 'OLE80360', 'OLE10038', 'OLE11380')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11381', 'OLE80361', 'OLE10038', 'OLE11381')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11382', 'OLE80362', 'OLE10038', 'OLE11382')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11383', 'OLE80363', 'OLE10038', 'OLE11383')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11384', 'OLE80364', 'OLE10038', 'OLE11384')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11385', 'OLE80365', 'OLE10038', 'OLE11385')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11386', 'OLE80366', 'OLE10038', 'OLE11386')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11387', 'OLE80367', 'OLE10038', 'OLE11387')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11388', 'OLE80368', 'OLE10038', 'OLE11388')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11389', 'OLE80369', 'OLE10038', 'OLE11389')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11390', 'OLE80370', 'OLE10038', 'OLE11390')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11391', 'OLE80371', 'OLE10038', 'OLE11391')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11392', 'OLE80372', 'OLE10038', 'OLE11392')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11393', 'OLE80373', 'OLE10038', 'OLE11393')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11394', 'OLE80374', 'OLE10038', 'OLE11394')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11395', 'OLE80375', 'OLE10038', 'OLE11395')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11396', 'OLE80376', 'OLE10038', 'OLE11396')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11397', 'OLE80377', 'OLE10038', 'OLE11397')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11398', 'OLE80378', 'OLE10038', 'OLE11398')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11399', 'OLE80379', 'OLE10038', 'OLE11399')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11400', 'OLE80380', 'OLE10038', 'OLE11400')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11401', 'OLE80381', 'OLE10038', 'OLE11401')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11402', 'OLE80382', 'OLE10038', 'OLE11402')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11403', 'OLE80383', 'OLE10038', 'OLE11403')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11404', 'OLE80384', 'OLE10038', 'OLE11404')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11405', 'OLE80385', 'OLE10038', 'OLE11405')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11406', 'OLE80386', 'OLE10038', 'OLE11406')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11407', 'OLE80387', 'OLE10038', 'OLE11407')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11408', 'OLE80388', 'OLE10038', 'OLE11408')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11409', 'OLE80389', 'OLE10038', 'OLE11409')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11410', 'OLE80390', 'OLE10038', 'OLE11410')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11411', 'OLE80391', 'OLE10038', 'OLE11411')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11412', 'OLE80392', 'OLE10038', 'OLE11412')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11413', 'OLE80393', 'OLE10038', 'OLE11413')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11414', 'OLE80394', 'OLE10038', 'OLE11414')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11415', 'OLE80395', 'OLE10038', 'OLE11415')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11416', 'OLE80396', 'OLE10038', 'OLE11416')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11417', 'OLE80397', 'OLE10038', 'OLE11417')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11418', 'OLE80398', 'OLE10038', 'OLE11418')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11419', 'OLE80399', 'OLE10038', 'OLE11419')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11420', 'OLE80400', 'OLE10038', 'OLE11420')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11421', 'OLE80401', 'OLE10038', 'OLE11421')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11422', 'OLE80402', 'OLE10038', 'OLE11422')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11423', 'OLE80403', 'OLE10038', 'OLE11423')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11424', 'OLE80404', 'OLE10038', 'OLE11424')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11425', 'OLE80405', 'OLE10038', 'OLE11425')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11426', 'OLE80406', 'OLE10038', 'OLE11426')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11427', 'OLE80407', 'OLE10038', 'OLE11427')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11428', 'OLE80408', 'OLE10038', 'OLE11428')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11429', 'OLE80409', 'OLE10038', 'OLE11429')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11430', 'OLE80410', 'OLE10038', 'OLE11430')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11431', 'OLE80411', 'OLE10038', 'OLE11431')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11432', 'OLE80412', 'OLE10038', 'OLE11432')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11433', 'OLE80413', 'OLE10038', 'OLE11433')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11434', 'OLE80414', 'OLE10038', 'OLE11434')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11435', 'OLE80415', 'OLE10038', 'OLE11435')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11436', 'OLE80416', 'OLE10038', 'OLE11436')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11437', 'OLE80417', 'OLE10038', 'OLE11437')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11438', 'OLE80418', 'OLE10038', 'OLE11438')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11439', 'OLE80419', 'OLE10038', 'OLE11439')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11440', 'OLE80420', 'OLE10038', 'OLE11440')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11441', 'OLE80421', 'OLE10038', 'OLE11441')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11442', 'OLE80422', 'OLE10038', 'OLE11442')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11443', 'OLE80423', 'OLE10038', 'OLE11443')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11444', 'OLE80424', 'OLE10038', 'OLE11444')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11445', 'OLE80425', 'OLE10038', 'OLE11445')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11446', 'OLE80426', 'OLE10038', 'OLE11446')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11447', 'OLE80427', 'OLE10038', 'OLE11447')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11448', 'OLE80428', 'OLE10038', 'OLE11448')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11449', 'OLE80429', 'OLE10038', 'OLE11449')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11450', 'OLE80430', 'OLE10038', 'OLE11450')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11451', 'OLE80431', 'OLE10038', 'OLE11451')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11452', 'OLE80432', 'OLE10038', 'OLE11452')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11453', 'OLE80433', 'OLE10038', 'OLE11453')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11454', 'OLE80434', 'OLE10038', 'OLE11454')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11455', 'OLE80435', 'OLE10038', 'OLE11455')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11456', 'OLE80436', 'OLE10038', 'OLE11456')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11457', 'OLE80437', 'OLE10038', 'OLE11457')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11458', 'OLE80438', 'OLE10038', 'OLE11458')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11459', 'OLE80439', 'OLE10038', 'OLE11459')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11460', 'OLE80440', 'OLE10038', 'OLE11460')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11461', 'OLE80441', 'OLE10038', 'OLE11461')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11462', 'OLE80442', 'OLE10038', 'OLE11462')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11463', 'OLE80443', 'OLE10038', 'OLE11463')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11464', 'OLE80444', 'OLE10038', 'OLE11464')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11465', 'OLE80445', 'OLE10038', 'OLE11465')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11466', 'OLE80446', 'OLE10038', 'OLE11466')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11467', 'OLE80447', 'OLE10038', 'OLE11467')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11468', 'OLE80448', 'OLE10038', 'OLE11468')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11469', 'OLE80449', 'OLE10038', 'OLE11469')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11470', 'OLE80450', 'OLE10038', 'OLE11470')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11471', 'OLE80451', 'OLE10038', 'OLE11471')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11472', 'OLE80452', 'OLE10038', 'OLE11472')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11473', 'OLE80453', 'OLE10038', 'OLE11473')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11474', 'OLE80454', 'OLE10038', 'OLE11474')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11475', 'OLE80455', 'OLE10038', 'OLE11475')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11476', 'OLE80456', 'OLE10038', 'OLE11476')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11477', 'OLE80457', 'OLE10038', 'OLE11477')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11478', 'OLE80458', 'OLE10038', 'OLE11478')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11479', 'OLE80459', 'OLE10038', 'OLE11479')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11480', 'OLE80460', 'OLE10038', 'OLE11480')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11481', 'OLE80461', 'OLE10038', 'OLE11481')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11482', 'OLE80462', 'OLE10038', 'OLE11482')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11483', 'OLE80463', 'OLE10038', 'OLE11483')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11484', 'OLE80464', 'OLE10038', 'OLE11484')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11485', 'OLE80465', 'OLE10038', 'OLE11485')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11486', 'OLE80466', 'OLE10038', 'OLE11486')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11487', 'OLE80467', 'OLE10038', 'OLE11487')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11488', 'OLE80468', 'OLE10038', 'OLE11488')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11489', 'OLE80469', 'OLE10038', 'OLE11489')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11490', 'OLE80470', 'OLE10038', 'OLE11490')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11491', 'OLE80471', 'OLE10038', 'OLE11491')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11492', 'OLE80472', 'OLE10038', 'OLE11492')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11493', 'OLE80473', 'OLE10038', 'OLE11493')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11494', 'OLE80474', 'OLE10038', 'OLE11494')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11495', 'OLE80475', 'OLE10038', 'OLE11495')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11496', 'OLE80476', 'OLE10038', 'OLE11496')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11497', 'OLE80477', 'OLE10038', 'OLE11497')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11498', 'OLE80478', 'OLE10038', 'OLE11498')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11499', 'OLE80479', 'OLE10038', 'OLE11499')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11500', 'OLE80480', 'OLE10038', 'OLE11500')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11501', 'OLE80481', 'OLE10038', 'OLE11501')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11502', 'OLE80482', 'OLE10038', 'OLE11502')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11503', 'OLE80483', 'OLE10038', 'OLE11503')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11504', 'OLE80484', 'OLE10038', 'OLE11504')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11505', 'OLE80485', 'OLE10038', 'OLE11505')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11506', 'OLE80486', 'OLE10038', 'OLE11506')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11507', 'OLE80487', 'OLE10038', 'OLE11507')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11508', 'OLE80488', 'OLE10038', 'OLE11508')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11509', 'OLE80489', 'OLE10038', 'OLE11509')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11510', 'OLE80490', 'OLE10038', 'OLE11510')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11511', 'OLE80491', 'OLE10038', 'OLE11511')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11512', 'OLE80492', 'OLE10038', 'OLE11512')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11513', 'OLE80493', 'OLE10038', 'OLE11513')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11514', 'OLE80494', 'OLE10038', 'OLE11514')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11515', 'OLE80495', 'OLE10038', 'OLE11515')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11516', 'OLE80496', 'OLE10038', 'OLE11516')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11517', 'OLE80497', 'OLE10038', 'OLE11517')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11518', 'OLE80147', 'OLE10038', 'OLE11518')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11519', 'OLE80498', 'OLE10038', 'OLE11519')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11520', 'OLE80499', 'OLE10038', 'OLE11520')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11521', 'OLE80148', 'OLE10038', 'OLE11521')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11522', 'OLE80500', 'OLE10038', 'OLE11522')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11523', 'OLE80501', 'OLE10038', 'OLE11523')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11524', 'OLE80145', 'OLE10038', 'OLE11524')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11525', 'OLE80149', 'OLE10038', 'OLE11525')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11526', 'OLE80502', 'OLE10038', 'OLE11526')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11527', 'OLE80503', 'OLE10038', 'OLE11527')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11528', 'OLE80146', 'OLE10038', 'OLE11528')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11529', 'OLE80150', 'OLE10038', 'OLE11529')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11530', 'OLE80504', 'OLE10038', 'OLE11530')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11531', 'OLE80505', 'OLE10038', 'OLE11531')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11532', 'OLE80498', 'OLE10042', 'OLE11532')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11533', 'OLE80499', 'OLE10042', 'OLE11533')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11534', 'OLE80500', 'OLE10042', 'OLE11534')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11535', 'OLE80501', 'OLE10042', 'OLE11535')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11536', 'OLE80502', 'OLE10042', 'OLE11536')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11537', 'OLE80503', 'OLE10042', 'OLE11537')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11538', 'OLE80504', 'OLE10042', 'OLE11538')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11539', 'OLE80505', 'OLE10042', 'OLE11539')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11540', 'OLE80498', 'OLE10043', 'OLE11540')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11541', 'OLE80499', 'OLE10043', 'OLE11541')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11542', 'OLE80500', 'OLE10043', 'OLE11542')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11543', 'OLE80501', 'OLE10043', 'OLE11543')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11544', 'OLE80502', 'OLE10043', 'OLE11544')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11545', 'OLE80503', 'OLE10043', 'OLE11545')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11546', 'OLE80504', 'OLE10043', 'OLE11546')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11547', 'OLE80505', 'OLE10043', 'OLE11547')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11548', 'OLE80197', 'OLE10034', 'OLE11548')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11549', 'OLE80198', 'OLE10034', 'OLE11549')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11550', 'OLE80199', 'OLE10034', 'OLE11550')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11551', 'OLE80200', 'OLE10034', 'OLE11551')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11552', 'OLE80221', 'OLE10034', 'OLE11552')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11553', 'OLE80222', 'OLE10034', 'OLE11553')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11554', 'OLE80223', 'OLE10034', 'OLE11554')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11555', 'OLE80224', 'OLE10034', 'OLE11555')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11556', 'OLE80241', 'OLE10034', 'OLE11556')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11557', 'OLE80242', 'OLE10034', 'OLE11557')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11558', 'OLE80243', 'OLE10034', 'OLE11558')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11559', 'OLE80244', 'OLE10034', 'OLE11559')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11560', 'OLE80506', 'OLE10034', 'OLE11560')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11561', 'OLE80507', 'OLE10034', 'OLE11561')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11562', 'OLE80508', 'OLE10034', 'OLE11562')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11563', 'OLE80509', 'OLE10034', 'OLE11563')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11564', 'OLE80506', 'OLE10029', 'OLE11564')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11565', 'OLE80507', 'OLE10029', 'OLE11565')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11566', 'OLE80508', 'OLE10029', 'OLE11566')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11567', 'OLE80509', 'OLE10029', 'OLE11567')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11568', 'OLE80506', 'OLE10030', 'OLE11568')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11569', 'OLE80507', 'OLE10030', 'OLE11569')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11570', 'OLE80508', 'OLE10030', 'OLE11570')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11571', 'OLE80509', 'OLE10030', 'OLE11571')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11572', 'OLE80510', 'OLE10028', 'OLE11572')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11573', 'OLE80510', 'OLE10029', 'OLE11573')
/

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND, OBJ_ID, PERM_ID, ROLE_ID, ROLE_PERM_ID) VALUES ('Y', 'OLE11574', 'OLE80510', 'OLE10030', 'OLE11574')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_KRIM_ROLE_PERM_T', '2.0.5', '3:05d1944a925b63f9b9f2819728cdb4b4', 22)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_KRIM_ROLE_RSP_T::ole::(Checksum: 3:9bbd5636c5b9591547f46025a4857b11)
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10002', 'OLE10012', 'OLE10002', 'OLE990009')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10005', 'OLE10012', 'OLE10005', 'OLE990016')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10006', 'OLE10003', 'OLE10006', 'OLE990002')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10011', 'OLE10017', 'OLE10011', 'OLE990009')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10014', 'OLE10004', 'OLE10014', 'OLE990018')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10015', 'OLE10012', 'OLE10015', 'OLE990019')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10016', 'OLE10016', 'OLE10016', 'OLE990020')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10017', 'OLE10019', 'OLE10017', 'OLE990020')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10018', 'OLE10019', 'OLE10018', 'OLE990021')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10019', 'OLE10021', 'OLE10019', 'OLE990022')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10020', 'OLE10022', 'OLE10020', 'OLE990018')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10022', 'OLE10024', 'OLE10022', 'OLE990016')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10023', 'OLE10024', 'OLE10023', 'OLE990019')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10024', 'OLE10012', 'OLE10024', 'OLE990023')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10029', 'OLE10012', 'OLE10029', 'OLE990024')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE10032', 'OLE10003', 'OLE10032', 'OLE990025')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80001', 'OLE10021', 'OLE80001', 'OLE80004')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('N', 'OLE80002', 'OLE10012', 'OLE80002', 'OLE80006')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('N', 'OLE80003', 'OLE10012', 'OLE80003', 'OLE80008')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('N', 'OLE80004', 'OLE10012', 'OLE80004', 'OLE80009')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80005', 'OLE10019', 'OLE80005', 'OLE80010')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80006', 'OLE10012', 'OLE80006', 'OLE80012')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80007', 'OLE10012', 'OLE80007', 'OLE80008')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80008', 'OLE10012', 'OLE80008', 'OLE80006')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80009', 'OLE10012', 'OLE80009', 'OLE80009')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80010', 'OLE10012', 'OLE80010', 'OLE80013')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80011', 'OLE10012', 'OLE80011', 'OLE80015')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80012', 'OLE10012', 'OLE80012', 'OLE80018')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80013', 'OLE10012', 'OLE80013', 'OLE80019')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80014', 'OLE10012', 'OLE80014', 'OLE80020')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE80015', 'OLE10003', 'OLE80015', 'OLE80021')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1126', 'OLE10004', 'OLE1126', 'OLE990026')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1127', 'OLE10044', 'OLE1127', 'OLE990034')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1128', 'OLE10021', 'OLE1128', 'OLE990035')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1129', 'OLE10020', 'OLE1129', 'OLE990036')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1130', 'OLE10044', 'OLE1130', 'OLE990030')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1131', 'OLE10020', 'OLE1131', 'OLE990031')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1132', 'OLE10044', 'OLE1132', 'OLE990027')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1133', 'OLE10020', 'OLE1133', 'OLE990028')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1134', 'OLE10021', 'OLE1134', 'OLE990029')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1135', 'OLE10045', 'OLE1135', 'OLE990032')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1136', 'OLE10045', 'OLE1136', 'OLE990037')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1137', 'OLE41', 'OLE1137', 'OLE990033')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1138', 'OLE41', 'OLE1138', 'OLE990038')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1139', 'OLE10044', 'OLE1139', 'OLE990039')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1140', 'OLE41', 'OLE1140', 'OLE990040')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1141', 'OLE10044', 'OLE1141', 'OLE990041')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1142', 'OLE55', 'OLE1142', 'OLE990042')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1143', 'OLE10042', 'OLE1143', 'OLE990043')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1144', 'OLE10043', 'OLE1144', 'OLE990043')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1145', 'OLE10042', 'OLE1145', 'OLE990044')
/

INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND, OBJ_ID, ROLE_ID, ROLE_RSP_ID, RSP_ID) VALUES ('Y', 'OLE1146', 'OLE10043', 'OLE1146', 'OLE990044')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_KRIM_ROLE_RSP_T', '2.0.5', '3:9bbd5636c5b9591547f46025a4857b11', 23)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_KRIM_ROLE_RSP_ACTN_T::ole::(Checksum: 3:8c4ee2406a43bc94e3f96fce05081f39)
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10002', '1', '*', 'OLE10002', 'OLE10002')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'F', 'N', 'OLE10005', '1', '*', 'OLE10005', 'OLE10005')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'F', 'N', 'OLE10006', '1', '*', 'OLE10006', 'OLE10006')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10011', '1', '*', 'OLE10011', 'OLE10011')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'F', 'N', 'OLE10014', '1', '*', 'OLE10014', 'OLE10014')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'F', 'N', 'OLE10015', '1', '*', 'OLE10015', 'OLE10015')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10016', '1', '*', 'OLE10016', 'OLE10016')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10017', '1', '*', 'OLE10017', 'OLE10017')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10018', '1', '*', 'OLE10018', 'OLE10018')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10019', '1', '*', 'OLE10019', 'OLE10019')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'F', 'N', 'OLE10020', '1', '*', 'OLE10020', 'OLE10020')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'F', 'N', 'OLE10022', '1', '*', 'OLE10022', 'OLE10022')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('A', 'F', 'N', 'OLE10023', '1', '*', 'OLE10023', 'OLE10023')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE10024', '1', '*', 'OLE10024', 'OLE10024')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE10026', '1', '*', 'OLE10026', 'OLE10026')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE10027', '1', '*', 'OLE10027', 'OLE10027')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE10028', '1', '*', 'OLE10028', 'OLE10028')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE10029', '1', '*', 'OLE10029', 'OLE10029')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10030', '1', '*', 'OLE10030', 'OLE10030')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10031', '1', '*', 'OLE10031', 'OLE10031')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE10032', '1', '*', 'OLE10032', 'OLE10032')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80001', '1', 'OLE10024', 'OLE80001', '*')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80002', '1', 'OLE10030', 'OLE80002', '*')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80003', '1', 'OLE10050', 'OLE80003', '*')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE80006', '1', 'OLE10079', 'OLE80006', '*')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE80007', '1', 'OLE10086', 'OLE80007', '*')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80008', '1', '*', 'OLE80008', 'OLE80005')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80009', '1', '*', 'OLE80009', 'OLE80006')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80010', '1', '*', 'OLE80010', 'OLE80010')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80011', '1', '*', 'OLE80011', 'OLE80011')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80012', '1', '*', 'OLE80012', 'OLE80012')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80013', '1', '*', 'OLE80013', 'OLE80013')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80014', '1', '*', 'OLE80014', 'OLE80014')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80015', '1', '*', 'OLE80015', 'OLE80007')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80016', '1', '*', 'OLE80016', 'OLE80008')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80017', '1', '*', 'OLE80017', 'OLE80009')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE80018', '1', '*', 'OLE80018', 'OLE80001')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'N', 'OLE80019', '1', 'OLE10022', 'OLE80019', '*')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'N', 'OLE80020', '1', 'OLE10046', 'OLE80020', '*')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'N', 'OLE80021', '1', 'OLE10059', 'OLE80021', '*')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'Y', 'OLE10033', '1', '*', 'OLE10033', 'OLE1126')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10034', '1', '*', 'OLE10034', 'OLE1127')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10035', '1', '*', 'OLE10035', 'OLE1128')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10036', '1', '*', 'OLE10036', 'OLE1129')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10037', '1', '*', 'OLE10037', 'OLE1130')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10038', '1', '*', 'OLE10038', 'OLE1131')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10039', '1', '*', 'OLE10039', 'OLE1132')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10040', '1', '*', 'OLE10040', 'OLE1133')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10041', '1', '*', 'OLE10041', 'OLE1134')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10042', '1', '*', 'OLE10042', 'OLE1135')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10043', '1', '*', 'OLE10043', 'OLE1136')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10044', '1', '*', 'OLE10044', 'OLE1137')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10045', '1', '*', 'OLE10045', 'OLE1138')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10046', '1', '*', 'OLE10046', 'OLE1139')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10047', '1', '*', 'OLE10047', 'OLE1140')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'N', 'OLE10048', '1', 'OLE10115', 'OLE10048', '*')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'N', 'OLE10049', '1', 'OLE10114', 'OLE10049', '*')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'A', 'N', 'OLE10050', '1', '*', 'OLE10050', 'OLE1142')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'N', 'OLE10051', '1', '*', 'OLE10051', 'OLE1143')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'N', 'OLE10052', '1', '*', 'OLE10052', 'OLE1144')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'N', 'OLE10053', '1', '*', 'OLE10053', 'OLE1145')
/

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ACTN_PLCY_CD, ACTN_TYP_CD, FRC_ACTN, OBJ_ID, PRIORITY_NBR, ROLE_MBR_ID, ROLE_RSP_ACTN_ID, ROLE_RSP_ID) VALUES ('F', 'F', 'N', 'OLE10054', '1', '*', 'OLE10054', 'OLE1146')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_KRIM_ROLE_RSP_ACTN_T', '2.0.5', '3:8c4ee2406a43bc94e3f96fce05081f39', 24)
/

-- Changeset 03_bootstrap_krim_role_perm_data.xml::OLE_KRIM_ROLE_MBR_T::ole::(Checksum: 3:f80f200c8ef0a3d30ed2c3335e2b3554)
INSERT INTO KRIM_ROLE_MBR_T (mbr_id, mbr_typ_cd, obj_id, role_id, role_mbr_id) VALUES ('OLE10004', 'R', 'OLE10091', 'OLE10025', 'OLE10091')
/

INSERT INTO KRIM_ROLE_MBR_T (mbr_id, mbr_typ_cd, obj_id, role_id, role_mbr_id) VALUES ('OLE10005', 'R', 'OLE10092', 'OLE10025', 'OLE10092')
/

INSERT INTO KRIM_ROLE_MBR_T (mbr_id, mbr_typ_cd, obj_id, role_id, role_mbr_id) VALUES ('OLE10006', 'R', 'OLE10093', 'OLE10025', 'OLE10093')
/

INSERT INTO KRIM_ROLE_MBR_T (mbr_id, mbr_typ_cd, obj_id, role_id, role_mbr_id) VALUES ('OLE10012', 'R', 'OLE10094', 'OLE10025', 'OLE10094')
/

INSERT INTO DATABASECHANGELOG (AUTHOR, COMMENTS, DATEEXECUTED, DESCRIPTION, EXECTYPE, FILENAME, ID, LIQUIBASE, MD5SUM, ORDEREXECUTED) VALUES ('ole', '', SYSTIMESTAMP, 'Load Data', 'EXECUTED', '03_bootstrap_krim_role_perm_data.xml', 'OLE_KRIM_ROLE_MBR_T', '2.0.5', '3:f80f200c8ef0a3d30ed2c3335e2b3554', 25)
/

-- Release Database Lock
-- Release Database Lock
