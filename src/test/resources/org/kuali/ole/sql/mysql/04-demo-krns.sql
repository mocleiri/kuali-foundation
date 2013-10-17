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

--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: 04_demo_krns_data.xml
--  *********************************************************************

--  Lock Database
--  Changeset 04_demo_krns_data.xml::OLE_DEMO_LOAD_KRCR_PARM_T_mysql::ole::(Checksum: 3:68a2d67f62393effa40130aedd7baa3b)
DELETE FROM `KRCR_PARM_T`  WHERE APPL_ID = 'OLE'
/

SET sql_mode='NO_BACKSLASH_ESCAPES'
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-COA', 'OLE101', 'Specifies the upper range for the Contracts & Grants Responsibility Id on Contracts & Grants Accounts. The minimum is 1.', 'MAXIMUM_ACCOUNT_RESPONSIBILITY_ID', 'CONFG', '17', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Account', 'A', 'OLE-COA', 'OLE117', 'Indicates the SubFundGroup for this Kuali implementation that is used for CAMS/Capital purposes for capitalization of expenses related to building construction or remodeling.', 'CAPITAL_SUB_FUND_GROUPS', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Account', 'A', 'OLE-COA', 'OLE118', 'This is the value of the fund group or sub-fund group that the institution uses for contracts and grants accounts. Used in conjunction with FUND_GROUP_DENOTES_CG_IND.', 'CG_DENOTING_VALUE', 'CONFG', 'CG', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Account', 'A', 'OLE-COA', 'OLE119', 'The e-mail address to send a notification to when a member of an account derived role - Fiscal Officer, Account Supervisor, Fiscal Officer Primary Delegate, or Fiscal Officer Secondary Delegate - is inactivated.', 'DERIVED_ROLE_MEMBER_INACTIVATION_NOTIFICATION_EMAIL_ADDRESSES', 'CONFG', 'kbatch-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Account', 'A', 'OLE-COA', 'OLE120', 'If this parameter is set to Y then the parameter CG_DENOTING_VALUE should have a value that represents a fund group. If set to N, then CG_DENOTING_VALUE should have a value that represents a sub-fund group.', 'FUND_GROUP_DENOTES_CG_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Account', 'A', 'OLE-COA', 'OLE121', 'Determines for which fund groups an income stream account is required on the account document.', 'INCOME_STREAM_ACCOUNT_REQUIRING_FUND_GROUPS', 'VALID', 'GF;CG', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Account', 'D', 'OLE-COA', 'OLE122', 'Determines for which sub fund groups an income stream account is required on the account document.', 'INCOME_STREAM_ACCOUNT_REQUIRING_SUB_FUND_GROUPS', 'VALID', 'MPRACT', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Account', 'D', 'OLE-COA', 'OLE123', 'Institutionally specified restrictions on account number prefixes (e.g. the account number cannot begin with a 3 or with 00.)', 'PREFIXES', 'VALID', '3;00', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AccountDelegate', 'A', 'OLE-COA', 'OLE124', 'Allowed Employee Status code for an employee to be assigned as a delegate for an account.', 'EMPLOYEE_STATUSES', 'VALID', 'A', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CfdaBatchStep', 'A', 'OLE-COA', 'OLE102', 'Listserv for recipients of CFDA batch process results.', 'RESULT_SUMMARY_TO_EMAIL_ADDRESSES', 'CONFG', 'knoreceipt-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CfdaBatchStep', 'A', 'OLE-COA', 'OLE103', 'CFDA url', 'SOURCE_URL', 'CONFG', 'ftp://ftp.cfda.gov/programs', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ObjectCode', 'D', 'OLE-COA', 'OLE125', 'Institutionally specified unallowable values for Object Codes.', 'OBJECT_CODES', 'VALID', 'N/A;A/L', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OffsetDefinition', 'A', 'OLE-COA', 'OLE126', 'Defines the relationship between Document Type(s) and Object Code status (active/inactive) when creating a new offset definition.', 'DOCUMENT_TYPES_REQUIRING_ACTIVE_OBJECT_CODES', 'VALID', 'OLE_ARIN;OLE_ARNI;OLE_ARIM;OLE_BC', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OffsetDefinition', 'A', 'OLE-COA', 'OLE127', 'This allows the system to redirect offset entries to another specified account when enabled (Y). The Offset Account Maintenance Document defines the offset account for an object code/account combination. When not enabled (N)?the offsets are applied to the same account as the original transaction, under a different object code as defined in the Offset Definition table.', 'USE_FLEXIBLE_OFFSET_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Organization', 'A', 'OLE-COA', 'OLE128', 'When a new organization is being created, the default account number is not required for these organization types. This is to avoid a chicken/egg situation where the value for the default account cannot be created until the organization exists.', 'ORGANIZATION_TYPES_NOT_REQUIRING_DEFAULT_ACCOUNT', 'VALID', 'C;U', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Organization', 'A', 'OLE-COA', 'OLE129', 'Defines the Organization Type that can / must report to itself when creating a new organization.  This parameter should only contain 1 Organization Type; more will cause problems in the system.  The system will only allow one Organization with this Organization Type, thus ensuring that there is a single Organization at the top of the hierarchy that all others report to.', 'ORGANIZATION_TYPES_THAT_MUST_REPORT_TO_SELF', 'VALID', 'U', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Organization', 'A', 'OLE-COA', 'OLE130', 'Flag (Y/N) indicating whether HRMS Org Tab will appear on the Organization document.', 'USE_HRMS_ORGANIZATION_ATTRIBUTES_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'A', 'OLE-COA', 'OLE131', 'The balance type to use on the budget reversion ledger entries generated by the Organization Reversion process. In the base Kuali installation, RE is a balance type that end users won''t typically be aware of. This allows entries to be posted as an audit trail, but typically doesn''t appear on reports or balance inquiry screens.', 'BUDGET_REVERSION_DEFAULT_BALANCE_TYPE', 'CONFG', 'RE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'A', 'OLE-COA', 'OLE132', 'The object code used to balance the budget ledger entries generated by the Organization Reversion process when carrying forward budget to the new fiscal year.', 'CARRY_FORWARD_OBJECT_CODE', 'CONFG', '0110', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'A', 'OLE-COA', 'OLE133', 'The balance type to use on the cash reversion ledger entries generated by the Organization Reversion process. In the base Kuali installation, NB is a balance type that end users won''t typically be aware of. This allows entries to be posted as an audit trail, but typically doesn''t appear on reports or balance inquiry screens. ?', 'CASH_REVERSION_DEFAULT_BALANCE_TYPE', 'CONFG', 'NB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'A', 'OLE-COA', 'OLE134', 'The literal placed at the beginning of the document number for the Organization Reversion ledger entries. The process takes this literal and appends the account number to create multiple document numbers for posting. Then in the event of a scrubber error, only the transactions for a given account number (and not the entire feed) are rejected and need correcting. ?', 'DEFAULT_DOCUMENT_NUMBER_PREFIX', 'CONFG', 'AC', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'A', 'OLE-COA', 'OLE135', 'The system origination code to use on the ledger entries generated by the Organization Reversion process.', 'MANUAL_FEED_ORIGINATION', 'CONFG', 'MF', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'A', 'OLE-COA', 'OLE136', 'Fund group code(s) that should be included in the Organization Reversion Selection.', 'SELECTION_1', 'CONFG', 'account.subFundGroup.fundGroupCode=GF', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'D', 'OLE-COA', 'OLE137', 'Organization codes that should be excluded from Organization Reversion Selection.', 'SELECTION_2', 'CONFG', 'account.organizationCode=BALS;RESV', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'D', 'OLE-COA', 'OLE138', 'Object codes that should be excluded from Organization Reversion Selection.', 'SELECTION_3', 'CONFG', 'objectCode=9890;9891;9892;9893', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'D', 'OLE-COA', 'OLE139', 'Sub fund group codes that should be excluded from Organization Reversion Selection.', 'SELECTION_4', 'CONFG', 'account.subFundGroupCode=MPRACT;MWISH;MPROF;MHOUSE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'D', 'OLE-COA', 'OLE140', 'Chart of Accounts codes that should be excluded from Organization Reversion Selection.', 'SELECTION_5', 'CONFG', 'account.chartOfAccountsCode=', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'D', 'OLE-COA', 'OLE141', 'Account numbers that should be excluded from Organization Reversion Selection.', 'SELECTION_6', 'CONFG', 'account.accountNumber=', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversion', 'A', 'OLE-COA', 'OLE142', 'Defines the object code to use for carry-forward if the Carry Forward by Object Code Indicator is not checked in the Organization Reversion Maintenance Document.', 'UNALLOCATED_OBJECT_CODE', 'CONFG', '7900', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversionCategory', 'D', 'OLE-COA', 'OLE143', 'Object consolidations to exclude for a given organization reversion category. Format of list is organization reversion category 1=object consolidation 1, object consolidation 2;organization reversion category 2=object consolidation 3,object consolidation 4.', 'EXTENDED_DEFINITIONS_EXCLUDE_OBJECT_CONSOLIDATIONS_BY_ORGANIZATION_REVERSION_CATEGORY', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversionCategory', 'D', 'OLE-COA', 'OLE144', 'Object levels to exclude for a given organization reversion category. Format of list is organization reversion category 1=object level 1, object level 2;organization reversion category 2=object level 3,object level 4,object level 5.', 'EXTENDED_DEFINITIONS_EXCLUDE_OBJECT_LEVELS_BY_ORGANIZATION_REVERSION_CATEGORY', 'CONFG', 'C02=HRCO', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversionCategory', 'D', 'OLE-COA', 'OLE145', 'Object sub types to exclude for a given organization reversion category. Format of list is organization reversion category 1=object sub type 1, object sub type 2;organization reversion category 2=object sub type 3,object sub type 4,object sub type 5.', 'EXTENDED_DEFINITIONS_EXCLUDE_OBJECT_SUB_TYPES_BY_ORGANIZATION_REVERSION_CATEGORY', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversionCategory', 'D', 'OLE-COA', 'OLE146', 'Object types to exclude for a given organization reversion category. Format of list is organization reversion category 1=object type 1, object type 2;organization reversion category 2=object type 3,object type 4,object type 5.', 'EXTENDED_DEFINITIONS_EXCLUDE_OBJECT_TYPES_BY_ORGANIZATION_REVERSION_CATEGORY', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversionCategory', 'A', 'OLE-COA', 'OLE147', 'Organization reversion categories that represent expenses where variance is calculated as budget minus actual minus encumbrance. Categories not listed are assumed to be income categories and variance is calculated as actual minus budget.', 'EXTENDED_DEFINITIONS_EXPENSE_CATEGORIES', 'CONFG', 'C01;C02;C03;C04;C05;C06;C08;C09;C10', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversionCategory', 'A', 'OLE-COA', 'OLE148', 'Object consolidations to include for a given organization reversion category. Format of list is organization reversion category 1=object consolidation 1, object consolidation 2;organization reversion category 2=object consolidation 3,object consolidation 4.', 'EXTENDED_DEFINITIONS_INCLUDE_OBJECT_CONSOLIDATIONS_BY_ORGANIZATION_REVERSION_CATEGORY', 'CONFG', 'C01=CMPN;C02=CMPN;C03=SCHL;C04=CPTL;C05=RSRX;C06=TRSF;C07=TRSF;C08=TRVL;C09=GENX,IDEX;C10=ASEX;C11=ASRE,IDIN,OTRE,SAPR,STFE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversionCategory', 'A', 'OLE-COA', 'OLE149', 'Object levels to include for a given organization reversion category. Format of list is organization reversion category 1=object level 1, object level 2;organization reversion category 2=object level 3,object level 4,object level 5.', 'EXTENDED_DEFINITIONS_INCLUDE_OBJECT_LEVELS_BY_ORGANIZATION_REVERSION_CATEGORY', 'CONFG', 'C01=HRCO', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversionCategory', 'A', 'OLE-COA', 'OLE150', 'Object sub types to include for a given organization reversion category. Format of list is organization reversion category 1=object sub type 1, object sub type 2;organization reversion category 2=object sub type 3,object sub type 4,object sub type 5.', 'EXTENDED_DEFINITIONS_INCLUDE_OBJECT_SUB_TYPES_BY_ORGANIZATION_REVERSION_CATEGORY', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'OrganizationReversionCategory', 'A', 'OLE-COA', 'OLE151', 'Object types to include for a given organization reversion category. Format of list is organization reversion category 1=object type 1, object type 2;organization reversion category 2=object type 3,object type 4,object type 5.', 'EXTENDED_DEFINITIONS_INCLUDE_OBJECT_TYPES_BY_ORGANIZATION_REVERSION_CATEGORY', 'CONFG', 'C06=TE,EX,EE,ES;C07=TI,IN,CH,IC', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AdvanceDeposit', 'A', 'OLE-FP', 'OLE171', 'The chart and account codes which specify that an accounting line in an Advance Deposit document is an electronic payment.', 'ELECTRONIC_FUNDS_ACCOUNTS', 'CONFG', 'UA=9323000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AuxiliaryVoucher', 'D', 'OLE-FP', 'OLE172', 'Restricts available Accounting Period values from the drop-down list on the Auxiliary Voucher document.', 'ACCOUNTING_PERIODS', 'VALID', 'AB;BB;CB;', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AuxiliaryVoucher', 'A', 'OLE-FP', 'OLE173', 'Number of days, after an accounting period has closed, that transactions can be posted back to that period using an Auxiliary Voucher.', 'ACCOUNTING_PERIOD_GRACE_PERIOD', 'CONFG', '20', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AuxiliaryVoucher', 'D', 'OLE-FP', 'OLE174', 'Restricts object codes that relate to a particular combination of Object Type, Object Sub-type, and Object Level from use on Auxiliary Voucher documents. Format of list is object type 1,object sub type 1,object level 1;object type 2,object sub type 2,object level 2;...', 'COMBINATION_OBJECT_TYPE_OBJECT_SUB_TYPE_OBJECT_LEVEL', 'VALID', 'ES,VA,VADJ', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AuxiliaryVoucher', 'A', 'OLE-FP', 'OLE175', 'The offset object code that is used for accruals and adjustments when the document contains accounting lines for more than a single unique account.', 'GLPE_OFFSET_OBJECT_CODE', 'CONFG', '9897', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AuxiliaryVoucher', 'D', 'OLE-FP', 'OLE176', 'Object Sub-Type(s) restricted from use on the Auxiliary Voucher document.', 'OBJECT_SUB_TYPES', 'VALID', 'AM;BF;CF;CA;BU;BD;CL;LF;LE;LA;IF;FB;ES;CP;CO;CM;UO;UF;UC;PL;PI;MT;LI;AS;TF;', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BudgetAdjustment', 'A', 'OLE-FP', 'OLE177', 'Determines for which fund groups transfer of funds general ledger pending entries will be generated by the budget adjustment, when the budget adjustment crosses income stream accounts.', 'CROSS_INCOME_STREAM_GLPE_TRANSFER_GENERATING_FUND_GROUPS', 'CONFG', 'GF', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BudgetAdjustment', 'D', 'OLE-FP', 'OLE178', 'Determines for which sub fund groups transfer of funds general ledger pending entries will be generated by the budget adjustment, when the budget adjustment crosses income stream accounts.', 'CROSS_INCOME_STREAM_GLPE_TRANSFER_GENERATING_SUB_FUND_GROUPS', 'CONFG', 'MPRACT', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BudgetAdjustment', 'A', 'OLE-FP', 'OLE179', 'Indicator for turning on/off (Y/N) the transfer of fund general ledger pending entries on the budget adjustment document.', 'GLPE_GENERATE_TRANSFER_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BudgetAdjustment', 'A', 'OLE-FP', 'OLE180', 'Object code to use for creating the income transfer general ledger pending entries.', 'GLPE_INCOME_TRANSFER_OBJECT_CODE', 'CONFG', '1209', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BudgetAdjustment', 'D', 'OLE-FP', 'OLE181', 'Object Code(s) restricted from use on a Budget Adjustment document.', 'OBJECT_CODES', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BudgetAdjustment', 'D', 'OLE-FP', 'OLE182', 'Object Sub-Type(s) restricted from use on the Budget Adjustment document.', 'OBJECT_SUB_TYPES', 'VALID', 'CR;AR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CashReceipt', 'D', 'OLE-FP', 'OLE183', 'Object Consolidation(s) restricted from use on the Cash Receipt document.', 'OBJECT_CONSOLIDATIONS', 'VALID', 'FDBL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CashReceipt', 'D', 'OLE-FP', 'OLE184', 'Object Sub-Type(s) restricted from use on the Cash Receipt document.', 'OBJECT_SUB_TYPES', 'VALID', 'BU;CA;CE;FB;FR;HW;MT;PL;RE;SA;VA', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CashReceipt', 'D', 'OLE-FP', 'OLE185', 'Object Type(s) restricted from use on the Cash Receipt document.', 'OBJECT_TYPES', 'VALID', 'ES;IC', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CashReceipt', 'A', 'OLE-FP', 'OLEFP6967', 'Indicator to turn Cash receipt change request ability on and off.', 'CHANGE_REQUEST_ENABLED_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CreditCardReceipt', 'D', 'OLE-FP', 'OLE186', 'Object Sub-Type(s) restricted from use on the Credit Card Receipt document.', 'OBJECT_SUB_TYPES', 'VALID', 'BU;CA;CE;FB;FR;HW;MT;PL;RE;SA;VA', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE187', 'The campuses which are taxed for moving reimbursements carried out via Disbursement Vouchers.', 'CAMPUSES_TAXED_FOR_MOVING_REIMBURSEMENTS', 'CONFG', 'BL;EA;IN;KO;NW;SB;SE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE188', 'Indicator for turning the "employee paid outside of payroll" business rule on or off.', 'CHECK_EMPLOYEE_PAID_OUTSIDE_PAYROLL_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE189', 'Indicator for turning the "prepaid active employee" business rule on or off.', 'CHECK_PREPAID_ACTIVE_EMPLOYEE_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE190', 'Text that prints on the Disbursement Voucher cover sheet when hard-copy attachments are required for a payment to be issued.', 'COVER_SHEET_TEMPLATE_ATTACHMENT', 'CONFG', 'Check Enclosure', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE191', 'Text that prints on the Disbursement Voucher cover sheet when hard-copy is required for particular payment reasons.', 'COVER_SHEET_TEMPLATE_BAR', 'CONFG', '________________________________________________________________________________', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE192', 'Text that prints on the Disbursement Voucher cover sheet when hard-copy is required due to special handling requirements.', 'COVER_SHEET_TEMPLATE_HANDLING', 'CONFG', 'Special Handling', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE193', 'Text, relating to tax implications, that prints on the Disbursement Voucher cover sheet when hard-copy is required due to Non-Resident Alien payment requirements.', 'COVER_SHEET_TEMPLATE_LINES', 'CONFG', 'Departments must submit all required document copies and forms to FMS Tax Compliance. FMS reserves the right to review and approve all exemption and nonresident alien payment request forms. Upon approval, for honorarium payments only, there will be a mandatory 10-day waiting period before payment is processed as required by the IRS. Information and documents needed: 1. Name and foreign address 2. Payment reason, including dates, times, places 3. Copy of individual''s I-94 4. Copy of visa 5. Completed W-8BEN **6. Copy of individual''s social security card or proof of application of social security card which can be obtained from any social security office. **7. Federal form 8233 if individual is claiming an exemption from tax and if he holds proper visa status from a country that has tax treaty status with U.S. **If you are paying for travel only, neither a social security number nor a Form 8233 is required. However, if the payment reason is "Nonemployee Travel with Honorarium" then both are required. There are different requirements for royalty payments to nonresident aliens. Please contact the FMS tax area for information. Questions: 855-5657 or 856-5424', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE194', 'Text that prints on the Disbursement Voucher cover sheet that indicates this is a Non-Resident Alien payment.', 'COVER_SHEET_TEMPLATE_NON_RESIDENT_ALIEN', 'CONFG', 'Nonresident Alien', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE195', 'Text that prints on the Disbursement Voucher cover sheet when hard-copy is required due to Subcontract or Personal Services payment requirements.', 'COVER_SHEET_TEMPLATE_RLINES', 'CONFG', 'I hereby certify that the information relating to this FIS (TP) Document requesting payment for expenses is just and correct. I certify that all charges and/or reimbursements pertain to Indiana University business, that the amount is legally due after allowing all just credits and that no part of the same has previously been paid or will be paid by another source. Payee Signature ________________________   Payee Address ________________________ Date: ____________                                                                      ________________________ Account Manager                                                  Payee Phone # ________________________ Signature ______________________________  Payee Tax ID# ________________________', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE196', 'Payment reason(s) used for the compensation in respect to decedent.', 'DECEDENT_COMPENSATION_PAYMENT_REASONS', 'CONFG', 'D', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE197', 'The default document location for Disbursement Vouchers and attachments to route if there is no document validation parameter type that indicates otherwise.', 'DEFAULT_DOCUMENTATION_LOCATION', 'CONFG', 'F', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE198', 'If USE_DEFAULT_EMPLOYEE_ADDRESS_IND is No then this parameter defines the address type that should be used as the default for employee payees on the Disbursement Voucher. ', 'DEFAULT_EMPLOYEE_ADDRESS_TYPE', 'CONFG', 'HM', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE199', 'Accounts with these Higher Education Function Code(s) are disallowed on the Disbursement Voucher document.', 'HIGHER_ED_FUNCTIONS', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE200', 'Vendor ownership types that indicate that the vendor is an individual.', 'INDIVIDUAL_OWNERSHIP_TYPES', 'VALID', 'ID', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE201', 'Defines an invalid relationship between the campus code assigned to the OLE user initiating the Disbursement Voucher and the location(s) where the DV and attachments will route. Format of list is campus 1=documentation location 1, documentation location 2;campus 2=documentation location 3,documentation location 4,documentation location 5', 'INVALID_DOCUMENTATION_LOCATIONS_BY_CAMPUS', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE202', 'Defines an invalid relationship between the payment reason listed on the Disbursement Voucher and the location(s) where the DV and attachments will route. Format of list is payment reason 1=documentation location 1, documentation location 2;payment reason 2=documentation location 3,documentation location 4,documentation location 5.', 'INVALID_DOCUMENTATION_LOCATIONS_BY_PAYMENT_REASON', 'VALID', 'A=T;B=T;E=T;F=T;G=T;H=T;K=T;L=T;R=T;T=T;W=T;Z=T', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE203', 'Invalid objects by payment reason.  Format of list is payment reason 1=object 1, object 2;payment reason 2=object 3,object 4,object 5;?', 'INVALID_OBJECT_CODES_BY_PAYMENT_REASON', 'VALID', 'B=4006,4030,4032,4079,4500,4501,4503,4504,4507,4512,4513,4514,4519,4525,4530,4532,4535,4540,4549,4559,4560,4563,4564,4565,4575,4576,4577,4873,4874,5014,5042,5046,5047,5048,5074,5075,5077,5083,5084,5090,5150,5151,5152,5154,5155,B450;Z=5070;E=5070;H=5070;C=5070;L=5070;A=5070;F=5070;B=5070;T=5070;K=5070;R=5070;W=5070;P=5070;N=5070;X=5070;G=5070', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE204', 'Defines an invalid relationship between the payment reason listed on the Disbursement Voucher and the Object Level(s) on the accounting line of the DV. Format of list is payment reason 1=object level 1, object level 2;payment reason 2=object level 3,object level 4,object level 5.', 'INVALID_OBJECT_LEVELS_BY_PAYMENT_REASON', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE205', 'Defines an invalid relationship between the Sub-Fund Group and the Object Sub-Type(s) on the Disbursement Voucher document. Format of list is sub fund group 1=object sub type 1, object sub type 2;sub fund group 2=object sub type 3,object sub type 4,object sub type 5.', 'INVALID_OBJECT_SUB_TYPES_BY_SUB_FUND_GROUP', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE206', 'Defines an invalid relationship between the Payment Reason and the Payee Types on the Disbursement Voucher. Format of list is payment reason 1=payee type 1, payee type 2;payment reason 2=payee type 3,payee type 4,payee type 5.', 'INVALID_PAYEE_TYPES_BY_PAYMENT_REASON', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE207', 'Defines an invalid relationship between the Payment Reason listed on the Disbursement Voucher and the Sub-Fund Group(s) associated with the account number. Format of list is payment reason 1=sub fund group 1, sub fund group 2;payment reason 2=sub fund group 3,sub fund group 4,sub fund group 5.', 'INVALID_SUB_FUND_GROUPS_BY_PAYMENT_REASON', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE208', 'Payment reason(s) used for moving that trigger the moving business rules.', 'MOVING_PAYMENT_REASONS', 'CONFG', 'M', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE209', 'Indicator used to determine if an institution wishes to prevent the actual mileage amount from exceeding the calculated mileage amount for the Non-Employee Travel tab of the Disbursement Voucher. Set to Yes (Y) to prevent, No (N) to allow.', 'NONEMPLOYEE_TRAVEL_ACTUAL_MILEAGE_LIMIT_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE210', 'Payment reason(s) that trigger Nonemployee Travel tab completion and associated rules.', 'NONEMPLOYEE_TRAVEL_PAYMENT_REASONS', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE211', 'Allowed location(s) where a Disbursement Voucher and attachments will route when the Non-resident Alien box is checked on the DV.', 'NON_RESIDENT_ALIEN_DOCUMENTATION_LOCATIONS', 'VALID', 'F;T', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE212', 'Restricted Payment Reason(s) when the Non-resident Alien box is checked on the Disbursement Voucher.', 'NON_RESIDENT_ALIEN_PAYMENT_REASONS', 'VALID', 'X', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE219', 'Label used for employeed who are not vendors within the Disbursement Voucher.', 'NON_VENDOR_EMPLOYEE_PAYEE_TYPE_LABEL', 'CONFG', 'Employee (Non-Vendor)', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE220', 'Object Level(s) restricted from use on the Disbursement Voucher document.', 'OBJECT_LEVELS', 'VALID', 'BASR;BASE;BCOM;BCAS;BORE;BFRE;BLIA;FUBL;BGEX;ICOR;HRCO;BASS;CRIN', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE221', 'Object Sub-Type(s) restricted from use on the Disbursement Voucher document.', 'OBJECT_SUB_TYPES', 'VALID', 'BU', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE222', 'Object Type(s) restricted from use on the Disbursement Voucher document.', 'OBJECT_TYPES', 'VALID', 'CH;ES;FB;IC', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE223', 'Payment reasons that require the Disbursement Voucher to be routed to the tax area.', 'PAYMENT_REASONS_REQUIRING_TAX_REVIEW', 'CONFG', 'D;N;P;M', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE224', 'Label used for vendors with type code PO or DV within the Disbursement Voucher.', 'PO_AND_DV_PAYEE_TYPE_LABEL', 'CONFG', 'Vendor', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE225', 'Payment reason(s) that trigger Prepaid Travel tab completion and associated rules.', 'PREPAID_TRAVEL_PAYMENT_REASONS', 'CONFG', 'P', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE226', 'Allowed organization code for loading Disbursement Voucher payments to process checks.', 'PRE_DISBURSEMENT_EXTRACT_ORGANIZATION', 'CONFG', 'KUAL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE227', 'PDP Sub-Unit Code for loading DV payments', 'PRE_DISBURSEMENT_EXTRACT_SUB_UNIT', 'CONFG', 'DV', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE228', 'Total amount limit on Disbursement Voucher document for payment reasons associated with the parameter, RESEARCH_PAYMENT_REASON.', 'RESEARCH_NON_VENDOR_PAY_LIMIT_AMOUNT', 'CONFG', '100', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE229', 'Payment reason(s) that trigger enforcement of parameter, RESEARCH_NON_VENDOR_PAY_LIMIT_AMOUNT.', 'RESEARCH_PAYMENT_REASONS', 'CONFG', 'C', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE230', 'Allowed payment reason(s) when the payee on a Disbursement Voucher document is a revolving fund payee.', 'REVOLVING_FUND_PAYMENT_REASONS', 'CONFG', 'K', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'D', 'OLE-FP', 'OLE231', 'Sub-Fund Group(s) restricted from use on the Disbursement Voucher document.', 'SUB_FUND_GROUPS', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE233', 'Allowed location where a Disbursement Voucher and attachments will route when there are tax compliance issues associated with the DV.', 'TAX_DOCUMENTATION_LOCATION', 'CONFG', 'X', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE234', 'Creates the message that appears when the Per Diem link is selected on the Non-Employee Travel Expense tab of the Disbursement Voucher.', 'TRAVEL_PER_DIEM_LINK_PAGE_MESSAGE', 'CONFG', 'Click on the selected category link to connect to the federal government''s web site listing their official per diem rates. Once you have determined the correct M & IE rate from the MIDDLE column, please enter it in the Per Diem Rate field.  Once the rate has been entered in this field, the correct per diem amount can be calculated by pressing the calculate button. If the traveller has visited more than one city, the calculated per diem rate will not be correct, and you will have to manually calculate the per diem amount and enter it in the Per Diem Actual Amount field.  Travel Management will verify the per diem amount from the lodging receipts included with the supporting documentation.', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE235', 'If the value is Yes then use whatever address is marked as the default address for the person record as the default address for employee payees on the Disbursement Voucher. If the value is No then consult the DEFAULT_EMPLOYEE_ADDRESS_TYPE parameter to determine which address to use.', 'USE_DEFAULT_EMPLOYEE_ADDRESS_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE236', 'Defines a valid relationship between the campus code assigned to the OLE user initiating the Disbursement Voucher and the location(s) where the DV and attachments will route. Format of list is campus 1=documentation location 1, documentation location 2;campus 2=documentation location 3,documentation location 4,documentation location 5', 'VALID_DOCUMENTATION_LOCATIONS_BY_CAMPUS', 'VALID', 'BL=F,N,O,T;EA=A,F,T,N,O;IN=I,F,T,N,O;KO=K,F,T,N,O;NW=W,F,T,N,O;SB=S,F,N,T,O;SE=Z,F,T,N,O', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE237', 'Defines a valid relationship between the payment reason listed on the Disbursement Voucher and the location(s) where the DV and attachments will route. Format of list is payment reason 1=documentation location 1, documentation location 2;payment reason 2=documentation location 3,documentation location 4,documentation location 5.', 'VALID_DOCUMENTATION_LOCATIONS_BY_PAYMENT_REASON', 'VALID', 'C=O,F;D=F;M=F;N=T;P=T;X=T', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE238', 'Defines a valid relationship between the payment reason listed on the Disbursement Voucher and the Object Code(s) on the accounting line of the DV. Format of list is payment reason 1=object level 1, object level 2;payment reason 2=object level 3,object level 4,object level 5.', 'VALID_OBJECT_CODES_BY_PAYMENT_REASON', 'VALID', 'D=4566;M=5070', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE239', 'Defines a valid relationship between the payment reason listed on the Disbursement Voucher and the Object Level(s) on the accounting line of the DV. Format of list is payment reason 1=object level 1, object level 2;payment reason 2=object level 3,object level 4,object level 5.', 'VALID_OBJECT_LEVELS_BY_PAYMENT_REASON', 'VALID', 'A=OEXP,NORE,FINA;B=SERV,ADV,COMP,OEXP,PHON,PRIN,R&M,RESA,S&E,TAXP,UTIL,S&S;C=SERV;E=ADV,CREX,DEBT,OEXP,R&M,SASV,SERV,SUPL;F=ACPA,AR,BENF,C&G,DEBT,GIFT,INSS,INVR,OLIA,OTHR,OTHS,TAXP;G=CAP,PHON,RESA,S&E,SASV,TAXP,UTIL;H=SERV,SASV,BENF,CREX,S&E;J=COSV,SERV,CREX;K=COSV,CREX,SERV;L=CAP,R&M,COSV,S&E,RESV;N=TRAV,S&E,OEXP;P=TRAV,S&E,OEXP;R=ADV,S&E,COSV;T=RENT;W=SASV,OEXP,PRIN,RESA,S&E,INV,CAP;Y=R&M;Z=TAXP,ACPA,TAX,S&E,OLIA,DEBT,CASH,CREX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE240', 'Defines a valid relationship between the Sub-Fund Group and the Object Sub-Type(s) on the Disbursement Voucher document. Format of list is sub fund group 1=object sub type 1, object sub type 2;sub fund group 2=object sub type 3,object sub type 4,object sub type 5.', 'VALID_OBJECT_SUB_TYPES_BY_SUB_FUND_GROUP', 'VALID', 'LOANFD=', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE241', 'Defines an valid relationship between the Payment Reason and the Payee Types on the Disbursement Voucher. Format of list is payment reason 1=payee type 1, payee type 2;payment reason 2=payee type 3,payee type 4,payee type 5.', 'VALID_PAYEE_TYPES_BY_PAYMENT_REASON', 'VALID', 'A=V;B=V,E;C=V,E,VSP;D=V;E=V;F=V,E;G=V;H=V;K=V,VRF;L=V;M=V,E;N=V;P=V,E;R=V;T=V;W=V;X=V;Z=E', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE242', 'Defines a valid relationship between the Payment Reason listed on the Disbursement Voucher and the Sub-Fund Group(s) associated with the account number. Format of list is payment reason 1=sub fund group 1, sub fund group 2;payment reason 2=sub fund group 3,sub fund group 4,sub fund group 5.', 'VALID_SUB_FUND_GROUPS_BY_PAYMENT_REASON', 'VALID', 'D=', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLE243', 'Defines an valid relationship between the vendor ownership types and the payment reasons on the Disbursement Voucher. Format of list is payment reason 1=ownership type1, ownership type2;payment reason 2=ownership type3, ownership type4, ownership type5.', 'VALID_VENDOR_OWNERSHIP_TYPES_BY_PAYMENT_REASON', 'VALID', 'M=ID', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DistributionOfIncomeAndExpense', 'A', 'OLE-FP', 'OLE245', 'The wording that should go in the description field for a DI document that claims electronic funds.', 'ELECTRONIC_FUNDS_DOCUMENT_DESCRIPTION', 'CONFG', 'Electronic Payment Claim', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DistributionOfIncomeAndExpense', 'D', 'OLE-FP', 'OLE246', 'Object Sub-Type(s) restricted from use on the Distribution of Income and Expense document.', 'OBJECT_SUB_TYPES', 'VALID', 'CA;HW;MT;PL;SW;SA;OP;LD;FR;BU;TN;TF', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DistributionOfIncomeAndExpense', 'D', 'OLE-FP', 'OLE247', 'Object Type(s) restricted from use on the Distribution of Income and Expense document.', 'OBJECT_TYPES', 'VALID', 'IC;CH', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-FP', 'OLE248', 'object sub type codes which are used by the TransactionalDocumentRuleBase to determine if an object sub type code can be categorized as Mandatory Transfer. Specifically, this is used by isMandatoryTransferSubType() in the base rule class.', 'MANDATORY_TRANSFER_OBJECT_SUB_TYPES', 'CONFG', 'MT', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-FP', 'OLE249', 'Object sub type codes which are used by the TransactionalDocumentRuleBase to determine if an object sub type code can be categorized as Non-Mandatory Transfer. Specifically, this is used by isNonMandatoryTransferSubType() in the base rule class.', 'NON_MANDATORY_TRANSFER_OBJECT_SUB_TYPES', 'CONFG', 'TN', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'D', 'OLE-FP', 'OLE250', 'Object Code(s) restricted from use on any Financial Transaction document.', 'OBJECT_CODES', 'VALID', '8160;8116;8118;5019', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'D', 'OLE-FP', 'OLE251', 'Object Type(s) restricted from use on any Financial Transaction document.', 'OBJECT_TYPES', 'VALID', 'FB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-FP', 'OLE252', 'Account and Object Code for checking if Sales Tax data is needed for an accounting line. Format of list is account 1:object code 1;account 2:object code 2', 'SALES_TAX_APPLICABLE_ACCOUNTS_AND_OBJECT_CODES', 'CONFG', '9612706:9015;9612779:9015;9612704:9015', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-FP', 'OLE253', 'Document types for which sales tax should be collected on accounting lines.', 'SALES_TAX_APPLICABLE_DOCUMENT_TYPES', 'CONFG', 'OLE_CR;OLE_DI;OLE_DV;OLE_GEC;OLE_IB;OLE_YEDI;OLE_YEGE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'D', 'OLE-FP', 'OLE254', 'Sub-Fund Group(s) restricted from use on any Financial Transaction document.', 'SUB_FUND_GROUPS', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DvToPdpExtractStep', 'A', 'OLE-FP', 'OLE255', 'Vendor Ownership Type representing Corporations', 'CORPORATION_OWNERSHIP_TYPE', 'CONFG', 'CP', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DvToPdpExtractStep', 'D', 'OLE-FP', 'OLE256', 'Non-taxable payment reason codes by corporation ownership type category', 'NON_TAXABLE_PAYMENT_REASON_CODES_BY_CORPORATION_OWNERSHIP_TYPE_CATEGORY', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DvToPdpExtractStep', 'D', 'OLE-FP', 'OLE257', 'Non-taxable payment reason codes', 'NON_TAXABLE_PAYMENT_REASON_CODES_BY_OWNERSHIP_CODES', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DvToPdpExtractStep', 'A', 'OLE-FP', 'OLE258', 'Taxable payment reason codes by corporation ownership type category', 'TAXABLE_PAYMENT_REASON_CODES_BY_CORPORATION_OWNERSHIP_TYPE_CATEGORY', 'CONFG', 'ME=A,C,E,H,R,T,X,Y,L,J;LE=A,X,E,H,R,T,L,J', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DvToPdpExtractStep', 'A', 'OLE-FP', 'OLE259', 'Taxable payment reason codes', 'TAXABLE_PAYMENT_REASON_CODES_BY_OWNERSHIP_CODES', 'CONFG', 'NP=H,J;FC=H,J;NR=A,C,E,H,R,T,X,Y.L,J;ID=A,C,E,H,R,T,X,Y.L,J;PT=A,C,E,H,R,T,X,Y.L,J;', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DvToPdpExtractStep', 'A', 'OLE-FP', 'OLE260', 'Taxable payment reason codes for corporations with blank ownership type categories.', 'TAXABLE_PAYMENT_REASON_CODES_FOR_BLANK_CORPORATION_OWNERSHIP_TYPE_CATEGORIES', 'CONFG', 'H,J', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'GeneralErrorCorrection', 'D', 'OLE-FP', 'OLE261', 'Defines an invalid relationship between an Object Type and the Object Sub-Type(s) on the General Error Correction document. Format of list is object type 1=object sub type 1, object sub type 2;object type 2=object sub type 3,object sub type 4,object sub type 5.', 'INVALID_OBJECT_SUB_TYPES_BY_OBJECT_TYPE', 'VALID', 'ES=CF,CM,WO', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'GeneralErrorCorrection', 'D', 'OLE-FP', 'OLE262', 'Object Sub-Type(s) restricted from use on the General Error Correction document.', 'OBJECT_SUB_TYPES', 'VALID', 'CA;FR;LD;OP;SA;BU;MT;PL;DR;LO;VA;HW;AS;TN', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'GeneralErrorCorrection', 'D', 'OLE-FP', 'OLE263', 'Object Type(s) restricted from use on the General Error Correction document.', 'OBJECT_TYPES', 'VALID', 'IC', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'GeneralErrorCorrection', 'D', 'OLE-FP', 'OLE264', 'Defines a valid relationship between an Object Type and the Object Sub-Type(s) on the General Error Correction document. Format of list is object type 1=object sub type 1, object sub type 2;object type 2=object sub type 3,object sub type 4,object sub type 5', 'VALID_OBJECT_SUB_TYPES_BY_OBJECT_TYPE', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'IndirectCostAdjustment', 'A', 'OLE-FP', 'OLE265', 'Allowed Object Code for the Grant accounting line on the Indirect Cost Adjustment document.', 'GRANT_OBJECT_CODE', 'CONFG', '5500', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'IndirectCostAdjustment', 'A', 'OLE-FP', 'OLE266', 'Allowed Object Code for the Receipt accounting line on the Indirect Cost Adjustment document.', 'RECEIPT_OBJECT_CODE', 'CONFG', '1803', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'InternalBilling', 'D', 'OLE-FP', 'OLE267', 'Restricts Fund Group(s) for use the on Internal Billing document.', 'FUND_GROUPS', 'VALID', 'LF', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'InternalBilling', 'D', 'OLE-FP', 'OLE268', 'Object Level(s) restricted from use on the Internal Billing document.', 'OBJECT_LEVELS', 'VALID', 'C&G', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'InternalBilling', 'D', 'OLE-FP', 'OLE269', 'Object Sub-Type(s) restricted from use on the Internal Billing document.', 'OBJECT_SUB_TYPES', 'VALID', 'FR;ST;MT;BU;RE;SW;SA;GI;IV;CP;WO;TF;HW;PL;TN', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'InternalBilling', 'A', 'OLE-FP', 'OLE270', 'Allowed Object Type(s) for use on the Internal Billing document.', 'OBJECT_TYPES', 'VALID', 'AS;LI;CH;IN;EE;EX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'InternalBilling', 'D', 'OLE-FP', 'OLE271', 'Sub-Fund Group(s) restricted from use on the Internal Billing document.', 'SUB_FUND_GROUPS', 'VALID', 'PFRI;PFIP', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'NonCheckDisbursement', 'D', 'OLE-FP', 'OLE272', 'Object Consolidation(s) restricted from use on the Non-Check Disbursement document.', 'OBJECT_CONSOLIDATIONS', 'VALID', 'FDBL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'NonCheckDisbursement', 'D', 'OLE-FP', 'OLE273', 'Object Sub-Type(s) restricted from use on the Non-Check Disbursement document.', 'OBJECT_SUB_TYPES', 'VALID', 'BU;CA;FB;HW;SA;VA;RE;MT;FR;CE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'NonCheckDisbursement', 'D', 'OLE-FP', 'OLE274', 'Object Type(s) restricted from use on the Non-Check Disbursement document.', 'OBJECT_TYPES', 'VALID', 'IC;ES', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'NonCheckDisbursement', 'D', 'OLE-FP', 'OLE275', 'Sub-Fund Group(s) restricted from use on the Non-Check Disbursement document.', 'SUB_FUND_GROUPS', 'VALID', 'PFRR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PreEncumbrance', 'A', 'OLE-FP', 'OLE276', 'Allowed Object Type(s) for use on the Pre-Encumbrance document.', 'OBJECT_TYPES', 'VALID', 'EE;EX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCard', 'A', 'OLE-FP', 'OLE277', 'URL to launch on Procurement Card document for disputing a transaction.', 'DISPUTE_URL', 'CONFG', 'http://kuali.org/', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCard', 'D', 'OLE-FP', 'OLE278', 'Defines an invalid relationship between the Merchant Category Code (MCC) and the Object Code(s) on the Procurement Card document. Format of list is mcc code 1=object 1, object 2;mcc code 2=object 3,object 4,object 5.', 'INVALID_OBJECT_CODES_BY_MCC_CODE', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCard', 'D', 'OLE-FP', 'OLE279', 'Invalid object sub types by merchant category code.  Format of list is merchant category code 1=object sub type 1,object sub type 2;merchant category code 2=object sub type 3,object sub type 4,object sub type 5;...', 'INVALID_OBJECT_SUB_TYPES_BY_MCC_CODE', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCard', 'D', 'OLE-FP', 'OLE280', 'Object Consolidation(s) restricted from use on the Procurement Card document.', 'OBJECT_CONSOLIDATIONS', 'VALID', 'FDBL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCard', 'D', 'OLE-FP', 'OLE281', 'Object Sub-Type(s) restricted from use on the Procurement Card document.', 'OBJECT_SUB_TYPES', 'VALID', 'AC;AR;AS;BU;CA;CE;CR;CU;FB;FD;LD;MT;OP;PI;PL;RE;                          SF;SS;ST;TF;TN;VA;WO', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCard', 'D', 'OLE-FP', 'OLE282', 'Object Type(s) restricted from use on the Procurement Card document.', 'OBJECT_TYPES', 'VALID', 'IC', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCard', 'A', 'OLE-FP', 'OLE283', 'Defines a valid relationship between the Merchant Category Code (MCC) and the Object Code(s) on the Procurement Card document. Format of list is mcc code 1=object 1, object 2;mcc code 2=object 3,object 4,object 5.', 'VALID_OBJECT_CODES_BY_MCC_CODE', 'VALID', '5193=4868,4870,4088,5080,6000,6100,2100,5327,4507,4035,4938;5422=4868,4870,4088,5080,6000,6100,2100,5327,4507,4938,4080;5441=4868,4870,4088,5080,6000,6100,2100,5327,4507,2163;5451=4868,4870,4088,5080,6000,6100,2100,5327,4507;5461=4868,4870,4088,5080,6000,6100,2100,5327,4507;5811=4868,4870,4088,5080,6000,6100,2100,5327,4507;5812=4868,4870,4088,5080,6000,6100,2100,5327,4507;5814=4868,4870,4088,5080,6000,6100,2100,5327,4507', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCard', 'A', 'OLE-FP', 'OLE284', 'Valid object sub types by merchant category code.  Format of list is merchant category code 1=object sub type 1,object sub type 2;merchant category code 2=object sub type 3,object sub type 4,object sub type 5;...', 'VALID_OBJECT_SUB_TYPES_BY_MCC_CODE', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCardAutoApproveDocumentsStep', 'A', 'OLE-FP', 'OLE285', 'Flag that determines auto approval of the transactions (Y/N) in the Procurement Card document (batch process) to be turned on or off. Used in conjunction with parameter, AUTO_APPROVE_NUMBER_OF_DAYS.', 'AUTO_APPROVE_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCardAutoApproveDocumentsStep', 'A', 'OLE-FP', 'OLE286', 'Number of days a Procurement Card document is allowed to remain in "enroute" status before being auto-approved by the system. Used in conjunction with parameter, AUTO_APPROVE_IND.', 'AUTO_APPROVE_NUMBER_OF_DAYS', 'CONFG', '2', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCardCreateDocumentsStep', 'A', 'OLE-FP', 'OLE287', 'Account Number to substitute into the transaction, during the Procurement Card batch process, if the account is invalid.', 'ERROR_TRANSACTION_ACCOUNT_NUMBER', 'CONFG', '1031400', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCardCreateDocumentsStep', 'A', 'OLE-FP', 'OLE288', 'Chart Code to substitute into the transaction, during the Procurement Card batch process, if the account is invalid.', 'ERROR_TRANSACTION_CHART', 'CONFG', 'BL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCardCreateDocumentsStep', 'A', 'OLE-FP', 'OLE289', 'Indicator to create a Procurement Card document for each transaction if set to "Y" or a Procurement Card document for each cardholder if set to "N".', 'SINGLE_TRANSACTION_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCardLoadStep', 'A', 'OLE-FP', 'OLE290', 'Clearing account for Procurement Card Batch Process.', 'DEFAULT_TRANSACTION_ACCOUNT', 'CONFG', '1031400', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCardLoadStep', 'A', 'OLE-FP', 'OLE291', 'Chart to which the clearing account for the Procurement Card Batch Process belongs.', 'DEFAULT_TRANSACTION_CHART', 'CONFG', 'BL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCardLoadStep', 'A', 'OLE-FP', 'OLE292', 'Allowed object code for all charges processed through the Procurement Card Batch process.', 'DEFAULT_TRANSACTION_OBJECT_CODE', 'CONFG', '4190', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ServiceBilling', 'D', 'OLE-FP', 'OLE293', 'Restricts Fund Group(s) for use the on Internal Billing document.', 'FUND_GROUPS', 'VALID', 'LF', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ServiceBilling', 'D', 'OLE-FP', 'OLE294', 'Object Level(s) restricted from use on the Internal Billing document.', 'OBJECT_LEVELS', 'VALID', 'C&G', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ServiceBilling', 'D', 'OLE-FP', 'OLE295', 'Object Sub-Type(s) restricted from use on the Internal Billing document.', 'OBJECT_SUB_TYPES', 'VALID', 'FR;ST;MT;BU;RE;SW;SA;GI;IV;CP;WO;TF;HW;PL;TN', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ServiceBilling', 'A', 'OLE-FP', 'OLE296', 'Allowed Object Type(s) for use on the Service Billing document.', 'OBJECT_TYPES', 'VALID', 'IN;EE;EX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ServiceBilling', 'D', 'OLE-FP', 'OLE297', 'Sub-Fund Group(s) restricted from use on the Internal Billing document.', 'SUB_FUND_GROUPS', 'VALID', 'PFRI;PFIP', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLEFP298', 'Non Taxable Payment Reason Code - Royalties', 'PAYMENT_REASON_CODE_ROYALTIES', 'CONFG', 'R', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLEFP299', 'Non Taxable Payment Reason Code - Rental Payment', 'PAYMENT_REASON_CODE_RENTAL_PAYMENT', 'CONFG', 'T', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLEFP300', 'Non Taxable Payment Reason Code - Travel Honorarium', 'PAYMENT_REASON_CODE_TRAVEL_HONORARIUM', 'CONFG', 'X', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLEFP4643', 'Indicates whether separation of duties should take place when the payee is the only approver on a DV value: Y', 'ENABLE_SEPARATION_OF_DUTIES_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'TransferOfFunds', 'A', 'OLE-FP', 'OLE298', 'The Transfer of Funds document relies on a balancing check that transfers of funds from agency and clearing funds must balance between the To and From sections. This rule may be updated by configuring the parameter values to hold other Fund Group codes.', 'FUND_GROUP_BALANCING_SET', 'CONFG', 'AF;CL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BalanceForwardStep', 'A', 'OLE-GL', 'OLE299', 'The balance type(s) that will be considered when bringing assets, liabilities, and fund balance forward to the new fiscal year.', 'BALANCE_TYPES_TO_ROLL_FORWARD_FOR_BALANCE_SHEET', 'CONFG', 'AC;NB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BalanceForwardStep', 'A', 'OLE-GL', 'OLE300', 'The balance type(s) that will be considered when bringing income and expenses forward to the new fiscal year for inception to date reporting.', 'BALANCE_TYPES_TO_ROLL_FORWARD_FOR_INCOME_EXPENSE', 'CONFG', 'AC;CB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BalanceForwardStep', 'A', 'OLE-GL', 'OLE301', 'The sub fund group(s) that are eligible for inception to date reporting as part of the balance forward process.', 'SUB_FUND_GROUPS_FOR_INCEPTION_TO_DATE_REPORTING', 'CONFG', 'SDCI;PFCMR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-GL', 'OLE302', 'The document type of the accounting entries generated by the year-end closing process. This document type overrides certain scrubber logic (continuation accounts, inactive sub accounts, offset generation, etc...) so entries can post in the same accounting string where the balances originated.', 'ANNUAL_CLOSING_DOCUMENT_TYPE', 'CONFG', 'OLE_ACLO', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-GL', 'OLE303', 'Fiscal to select from when determining GL balance for the year-end process.', 'ANNUAL_CLOSING_FISCAL_YEAR', 'CONFG', '2010', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-GL', 'OLE304', 'Object Code for remaining fund balances in the year-end closing process.', 'ANNUAL_CLOSING_FUND_BALANCE_OBJECT_CODE', 'CONFG', '9899', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-GL', 'OLE305', 'Object Type for remaining fund balances in the year-end closing process.', 'ANNUAL_CLOSING_FUND_BALANCE_OBJECT_TYPE', 'CONFG', 'FB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-GL', 'OLE306', 'Date used on all transactions related to year-end closing process.', 'ANNUAL_CLOSING_TRANSACTION_DATE', 'CONFG', '2010-07-30', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-GL', 'OLE307', 'Default origin code for entries created in the accounting cycle and during year-end closing processes.', 'MANUAL_FEED_ORIGINATION', 'CONFG', 'MF', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CollectorStep', 'A', 'OLE-GL', 'OLE308', 'Document types that trigger a balance of credits, debits and total as part of the Collector Step process.', 'EQUAL_DEBIT_CREDIT_TOTAL_DOCUMENT_TYPES', 'CONFG', 'OLE_ID*;OLE_EB*', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CollectorStep', 'A', 'OLE-GL', 'OLE309', 'This is the subject line used when the collector sends out email notifying the user that the collector has excluded origin entries from processing (i.e. demerged).', 'ERROR_EMAIL_SUBJECT_LINE', 'CONFG', 'Collector Input - INVALID DOCUMENT', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CollectorStep', 'A', 'OLE-GL', 'OLE310', 'Flag for turning the duplicate file check on or off.', 'PERFORM_DUPLICATE_HEADER_CHECK_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CollectorStep', 'A', 'OLE-GL', 'OLE311', 'This is the subject line used when the collector sends out email relating to the validation status of a particular file.', 'VALIDATION_EMAIL_SUBJECT_LINE', 'CONFG', 'File Upload Successful', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Encumbrance', 'A', 'OLE-GL', 'OLE312', 'Indirect Cost Recovery Encumbrance Enabled Flag (Y/N).', 'USE_ICR_ENCUMBRANCE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'EncumbranceForwardStep', 'A', 'OLE-GL', 'OLE313', 'Default Transaction Ledger Entry Description to use for generated beginning fund balance offsets created by the encumbrance closing process.', 'BEGINNING_FUND_BALANCE_TRANSACTION_LEDGER_ENTRY_DESCRIPTION', 'CONFG', 'BEGINNING FUND BALANCE OFFSET', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'EncumbranceForwardStep', 'D', 'OLE-GL', 'OLE314', 'Used to narrow down the set of encumbrance balance types (obtained from the balance type table) that will be used to identify the encumbrances that should be carried forward to the next fiscal year.', 'FORWARDING_ENCUMBRANCE_BALANCE_TYPES', 'CONFG', 'CE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'EncumbranceForwardStep', 'D', 'OLE-GL', 'OLE315', 'Either includes or excludes all encumbrances from forward', 'FORWARD_ENCUMBRANCE_BALANCE_TYPE_AND_ORIGIN_CODE', 'CONFG', 'IE=LD', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'EncumbranceForwardStep', 'A', 'OLE-GL', 'OLE316', 'Default Transaction Ledger Entry Description to use for generated offset created by the encumbrance closing process.', 'GENERATED_TRANSACTION_LEDGER_ENTRY_DESCRIPTION', 'CONFG', 'GENERATED OFFSET', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'EncumbranceForwardStep', 'A', 'OLE-GL', 'OLE317', 'The object code used to balance external encumbrance entries from the encumbrance closing process.', 'OFFSET_OBJECT_CODE_FOR_EXTERNAL_ENCUMBRANCE', 'CONFG', '9892', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'EncumbranceForwardStep', 'A', 'OLE-GL', 'OLE318', 'The offset object code used to balance internal encumbrance entries from encumbrance closing process.', 'OFFSET_OBJECT_CODE_FOR_INTERNAL_ENCUMBRANCE', 'CONFG', '9891', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'EncumbranceForwardStep', 'A', 'OLE-GL', 'OLE319', 'The offset object code used to balance pre-encumbrance entries from the encumbrance closing process.', 'OFFSET_OBJECT_CODE_FOR_PRE_ENCUMBRANCE', 'CONFG', '9890', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'EnterpriseFeedStep', 'A', 'OLE-GL', 'OLE320', 'E-mail address(es) where notification will be sent in the event of a reconciliation or parse error.', 'INVALID_FILE_TO_EMAIL_ADDRESSES', 'CONFG', 'knoreceipt-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'GeneralLedgerCorrectionProcess', 'A', 'OLE-GL', 'OLE321', 'Maximum upload size for origin entry files within the General Ledger Correction Process. Must be an integer, optionally followed by "K", "M", or "G". (Used by General Ledger Correction Process.)', 'MAX_FILE_SIZE_ORIGIN_ENTRY_IMPORT', 'CONFG', '25M', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'GeneralLedgerCorrectionProcess', 'A', 'OLE-GL', 'OLE322', 'The maximum number of origin entry that will be displayed per the General Ledger Correction Process search results page.', 'RECORDS_PER_PAGE', 'CONFG', '10', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'GeneralLedgerCorrectionProcess', 'A', 'OLE-GL', 'OLE323', 'For the General Ledger Correction Process document, if the selected origin entry group contains more rows than the value of this parameter, then the document functionality will be restricted. In particular: 1) error if the user selected the "Manual Edit" "Edit Method" on the "Correction Process" (the user has to select another origin entry group or edit method); 2) the display of the search results tab will be disabled, regardless of what the "Edit Method" is; and 3) the display of the "show" button that normally appears below the checkboxes in the "Edit Options and Action" tab will not be rendered.', 'RECORD_COUNT_FUNCTIONALITY_LIMIT', 'CONFG', '1000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'NominalActivityClosingStep', 'A', 'OLE-GL', 'OLE324', 'Nominal balances remaining in expense object codes, after other year-end processes are run, are closed to this object code.', 'NET_EXPENSE_OBJECT_CODE', 'CONFG', '5000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'NominalActivityClosingStep', 'A', 'OLE-GL', 'OLE325', 'Nominal balances remaining in revenue object codes, after other year-end processes are run, are closed to this object code.', 'NET_REVENUE_OBJECT_CODE', 'CONFG', '1800', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PosterBalancingStep', 'A', 'OLE-GL', 'OLE326', 'Total number of failures to print on the report for each category of balancing failures on the balancing batch job. This is used for posterBalancingStep.', 'NUMBER_OF_COMPARISON_FAILURES_TO_PRINT_PER_REPORT', 'CONFG', '10', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PosterBalancingStep', 'A', 'OLE-GL', 'OLE327', 'Number of fiscal years to subtract from current fiscal year to represent what the start range on the balancing batch job is. This is used for posterBalancingStep.', 'NUMBER_OF_PAST_FISCAL_YEARS_TO_INCLUDE', 'CONFG', '1', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PosterEntriesStep', 'A', 'OLE-GL', 'OLE328', 'Encumbrance transactions with these document types will always update the Open Amount field on the GL Open Encumbrance table. As with other encumbrance transactions, the GL poster will use the document information, when the encumbrance update code = D, or the reference document information, when the encumbrance update code = R, when determining the key values needed to insert into or update the Open Encumbrance table.', 'ENCUMBRANCE_OPEN_AMOUNT_OVERRIDING_DOCUMENT_TYPES', 'CONFG', 'OLE_PO;OLE_POA;OLE_POC;OLE_POPH;OLE_PORH;OLE_POR;OLE_PORT;OLE_POSP;OLE_POV', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PosterIndirectCostRecoveryEntriesStep', 'A', 'OLE-GL', 'OLE329', 'Determines, when the Poster walks up the object code reports-to hierarchy to check if an Indirect Cost Recovery Exclusion by Type or Indirect Cost Recovery Exclusion by Account exists for the given transaction, if only the transaction''s object code and the top level object code of the hierarchy should be consulted, as opposed to seeking exclusions at every step of the object code reports-to hierarchy', 'ICR_EXCLUSIONS_AT_TRANSACTION_AND_TOP_LEVEL_ONLY_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PosterIndirectCostRecoveryEntriesStep', 'D', 'OLE-GL', 'OLE330', 'Excludes transactions associated with certain fiscal period(s) from consideration for posting of indirect cost entries.', 'INDIRECT_COST_FISCAL_PERIODS', 'CONFG', 'AB;BB;CB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PosterIndirectCostRecoveryEntriesStep', 'A', 'OLE-GL', 'OLE331', 'Document type used for indirect cost recovery origin entries generated by the poster.', 'INDIRECT_COST_RECOVERY_DOCUMENT_TYPE', 'CONFG', 'OLE_ICR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PosterIndirectCostRecoveryEntriesStep', 'D', 'OLE-GL', 'OLE332', 'Excludes transactions associated with certain indirect cost type(s) from consideration for posting of indirect cost entries.', 'INDIRECT_COST_TYPES', 'CONFG', '10', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PosterSummaryReportStep', 'A', 'OLE-GL', 'OLE333', 'The month and day marking the end of normal eDoc processing (not Year-End documents) for the fiscal year. This date is needed to help the accounting cycle know which versions of the GL Summary Report to run.', 'CURRENT_AND_LAST_YEAR', 'CONFG', '0630', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PosterSummaryReportStep', 'A', 'OLE-GL', 'OLE334', 'The month and day near the end of a fiscal year when the accounting cycle will begin producing a GL Summary Report for the next fiscal year.', 'CURRENT_YEAR_LOWER', 'CONFG', '0615', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PosterSummaryReportStep', 'A', 'OLE-GL', 'OLE335', 'The month and day near the beginning of a fiscal year when the accounting cycle will stop producing a GL Summary Report for last fiscal year.', 'CURRENT_YEAR_UPPER', 'CONFG', '0807', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurgeAccountBalancesStep', 'A', 'OLE-GL', 'OLE336', 'Purge data from GL_ACCT_BALANCES_T before this year', 'PRIOR_TO_YEAR', 'CONFG', '2001', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurgeBalanceStep', 'A', 'OLE-GL', 'OLE337', 'Purge data from GL_BALANCE_T before this year', 'PRIOR_TO_YEAR', 'CONFG', '2001', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurgeCollectorDetailStep', 'A', 'OLE-GL', 'OLE338', 'Purge data from GL_ID_BILL_T before this year', 'PRIOR_TO_YEAR', 'CONFG', '2004', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurgeEncumbranceStep', 'A', 'OLE-GL', 'OLE339', 'Purge data from GL_ENCUMBRANCE_T before this year', 'PRIOR_TO_YEAR', 'CONFG', '2004', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurgeEntryStep', 'A', 'OLE-GL', 'OLE340', 'Purge data from GL_ENTRY_T before this year', 'PRIOR_TO_YEAR', 'CONFG', '2004', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurgeSufficientFundBalancesStep', 'A', 'OLE-GL', 'OLE341', 'Purge data from GL_SF_BALANCES_T before this year', 'PRIOR_TO_YEAR', 'CONFG', '2001', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE342', 'Excludes transactions associated with certain chart(s) from consideration for capitalization in the scrubber process.', 'CAPITALIZATION_CHARTS', 'CONFG', 'HO', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE343', 'Excludes transactions associated with certain document type(s) from consideration for capitalization in the scrubber process.', 'CAPITALIZATION_DOCUMENT_TYPES', 'CONFG', 'OLE_TF;OLE_YETF;OLE_AV;OLE_AVAC;OLE_AVAE;OLE_AVRC;OLE_ACLO', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE344', 'Excludes transactions associated with certain fiscal period(s) from consideration for capitalization in the scrubber process.', 'CAPITALIZATION_FISCAL_PERIODS', 'CONFG', 'BB;CB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE345', 'Turns on (Y) or off (N) the capitalization process in the scrubber.', 'CAPITALIZATION_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE346', 'The plant fund object code(s) to which the generated capitalization will be assigned, by object sub-type(s) of the original transaction, in the scrubber process. Format of list is object sub type 1=object 1;object sub type 2=object 2.', 'CAPITALIZATION_OBJECT_CODE_BY_OBJECT_SUB_TYPE', 'CONFG', 'AF=8616;AM=8615;BD=8601;BF=8605;BI=8629;BR=8601;BX=8640;BY=8641;C1=8627;C2=8628;C3=9607;CF=8611;CM=8610;ES=8630;IF=8604;LA=8603;LE=8608;LF=8614;LI=8613;LR=8665;UC=8618;UF=8619', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE347', 'The object sub-type(s) associated with transactions that will be considered as part of the generated capitalization in the scrubber process.', 'CAPITALIZATION_OBJECT_SUB_TYPES', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE348', 'This overrides the Chart Fund Balance object code for Capitalization.', 'CAPITALIZATION_OFFSET_CODE', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE349', 'Restricts transactions associated with certain sub fund group(s) from consideration in the capitalization scrubber process.', 'CAPITALIZATION_SUB_FUND_GROUPS', 'CONFG', 'EXTAGY', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE350', 'The number of days added to the expiration date of C&G accounts to allow for necessary adjusting entries.', 'CG_ACCOUNT_EXPIRATION_EXTENSION_DAYS', 'CONFG', '90', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE351', 'The document type(s) that are allowed to post to expired accounts.', 'CONTINUATION_ACCOUNT_BYPASS_DOCUMENT_TYPES', 'CONFG', 'OLE_PREQ;OLE_ACHC;OLE_ACHD;OLE_ACHR;OLE_CHKC;OLE_CHKD;OLE_CHKR;OLE_TOPS;OLE_CD;OLE_LOCR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE352', 'The origination code(s) that are allowed to post to expired accounts. This relates to system feeds.', 'CONTINUATION_ACCOUNT_BYPASS_ORIGINATIONS', 'CONFG', '01;02;EU;PL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE353', 'Excludes document type(s) from consideration for cost-share encumbrances in the scrubber process.', 'COST_SHARE_DOCUMENT_TYPES', 'CONFG', 'OLE_JV;OLE_AA', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE354', 'Encumbrance balance type(s) that must be associated with the transaction cost-share encumbrances in order to be posted to the source account during the scrubber process.', 'COST_SHARE_ENCUMBRANCE_BALANCE_TYPES', 'CONFG', 'EX;IE;PE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE355', 'Excludes transactions associated with certain fiscal period(s) from consideration for cost-share encumbrances in the scrubber process.', 'COST_SHARE_ENCUMBRANCE_FISCAL_PERIODS', 'CONFG', 'BB;CB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE356', 'Excludes transactions associated with certain fiscal period(s) from consideration for cost-share transfer in the scrubber process.', 'COST_SHARE_FISCAL_PERIODS', 'CONFG', 'BB;CB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE357', 'Object code for the summary cost-share transfer into the contract and grant cost share sub-account that is generated during the scrubber process.', 'COST_SHARE_OBJECT_CODE', 'CONFG', '9915', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE358', 'The object code(s) for recording charges in the source account based upon the object code level of the original expense as part of the cost share transfer in the scrubber process. Format of list is Object Level 1=Object Code 1; Object Level 2=Object Code 2; etc.', 'COST_SHARE_OBJECT_CODE_BY_OBJECT_LEVEL', 'CONFG', 'ACSA=9920;BASE=9959;BENF=9957;BISA=9925;CAP=9970;CORE=9951;CORI=9912;FINA=9958;HRCO=9930;ICOE=9955;PART=9923;PRSA=9924;RESV=9979;SAAP=9923;TRAN=9959;TRAV=9960;TREX=9959;TRIN=9915;DEFAULT=9940', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE359', 'Object type(s) that would relate to transactions eligible for generating cost-share transfers during the scrubber process.', 'COST_SHARE_OBJECT_TYPES', 'CONFG', 'EE;EX;ES;TE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE360', 'Cutoff time for scrubber process, represented in 24 hour time format. Leave blank if no cutoff time desired (i.e. the scrubber run date is considered to be the date it is run). If the cutoff time is set and if the scrubber is run before the time indicated in this parameter, then the run date is considered to be 11:59:59.000 PM of the previous day. If it is run on or after the run date, then the run date of scrubber is the date and time of when the scrubber is actually executed, according to the DateTimeService. Examples: 00:35:00 is 12:35 midnight, and 13:24:56 is 1:24:56 PM.', 'CUTOFF_TIME', 'CONFG', '10:00:00', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE361', 'Determines if the Scrubber should generate offsets for entries of the given document type', 'DOCUMENT_TYPES_REQUIRING_FLEXIBLE_OFFSET_BALANCING_ENTRIES', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE362', 'Excludes transactions associated with certain chart(s) from consideration as a generated plant fund liability (leasehold equities) in the scrubber process.', 'LIABILITY_CHARTS', 'CONFG', 'HO', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE363', 'Excludes transactions associated with certain document type(s) from consideration as a generated plant fund liability (leasehold equities) in the scrubber process.', 'LIABILITY_DOCUMENT_TYPES', 'CONFG', 'OLE_ACLO;OLE_TF;OLE_YETF;OLE_AV;OLE_AVAC;OLE_AVAE;OLE_AVRC', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE364', 'Excludes transactions associated with certain fiscal period(s) from consideration as a generated plant fund liability (leasehold equities) in the scrubber process.', 'LIABILITY_FISCAL_PERIODS', 'CONFG', 'BB;CB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE365', 'Turns on (Y) or off (N) the generated plant liability (leasehold equities) process in the scrubber.', 'LIABILITY_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE366', 'The object code to which the generated plant fund liability (leasehold equities) will be assigned in the scrubber process.', 'LIABILITY_OBJECT_CODE', 'CONFG', '9603', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE367', 'The allowable object sub-type(s) associated with transactions that will be considered as part of the generated plant fund liability (leasehold equities) in the scrubber process.', 'LIABILITY_OBJECT_SUB_TYPES', 'CONFG', 'CL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE368', 'This overrides the Chart Fund Balance object code for Liability.', 'LIABILITY_OFFSET_CODE', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE369', 'Excludes transactions associated with certain sub fund group(s) from consideration as a generated plant fund liability (leasehold equities) in the scrubber process.', 'LIABILITY_SUB_FUND_GROUPS', 'CONFG', 'EXTAGY', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE370', 'Document types that are not eligible for capitalization, liability, plant indebtedness, or offsets created by the scrubber.', 'OFFSET_GENERATION_DOCUMENT_TYPES', 'CONFG', 'OLE_JV;OLE_ACLO', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'D', 'OLE-GL', 'OLE371', 'Restricts offsets from being generated for these fiscal periods during the scrubber process.', 'OFFSET_GENERATION_FISCAL_PERIODS', 'CONFG', 'BB;CB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE372', 'The object sub-type(s) associated with non-moveable assets that are associated with a campus.', 'PLANT_FUND_CAMPUS_OBJECT_SUB_TYPES', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE373', 'The object sub-type(s) associated with moveable assets that are associated with an organization. Used by the capitalization process in the scrubber.', 'PLANT_FUND_ORGANIZATION_OBJECT_SUB_TYPES', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE374', 'Turns on (Y) or off (N) the plant indebtedness process in the scrubber.', 'PLANT_INDEBTEDNESS_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE375', 'The object sub-type(s) that must be associated with transactions to generate a bond and note liability in the plant-indebtedness scrubber process.', 'PLANT_INDEBTEDNESS_OBJECT_SUB_TYPES', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE376', 'This overrides the Chart Fund Balance object code for Plant Indebtedness.', 'PLANT_INDEBTEDNESS_OFFSET_CODE', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScrubberStep', 'A', 'OLE-GL', 'OLE377', 'The sub-fund group(s) that must be associated with transactions to generate a bond and note liability in the plant-indebtedness scrubber process.', 'PLANT_INDEBTEDNESS_SUB_FUND_GROUPS', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'SufficientFundsAccountUpdateStep', 'A', 'OLE-GL', 'OLE378', 'Fiscal Year for sufficient funds rebuilder', 'FISCAL_YEAR', 'CONFG', '2012', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-PDP', 'OLE406', 'The name of the file to load into the Federal ACH Bank Directory File. This file is used to validate bank routing numbers.', 'ACH_BANK_INPUT_FILE', 'CONFG', 'FedACHdir.txt', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PDP', 'OLE407', 'A central administrative email address to send a notice to when a PDP check bundle contains more notes than are allowed on a check stub.  The associated check bundle is not allowed to load to PDP and is held by the purchasing system until further action can be taken to resolve the problem.', 'ERROR_EXCEEDS_NOTE_LIMIT_EMAIL_ADDRESSES', 'CONFG', 'knoreceipt-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PDP', 'OLE408', 'The maximum number of note/text lines allowable on a PDP payment. Typically this is the maximum number of lines that can be printed on a check stub.', 'MAX_NOTE_LINES', 'VALID', '27', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PDP', 'OLE409', 'If set to Y PDP will send ACH email notifications to payees. If set to N PDP will not send these notifications.', 'SEND_ACH_EMAIL_NOTIFICATION_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PDP', 'OLE410', 'List of email address that are copied on the cancellation email that is sent to the c customer.', 'TAX_CANCEL_TO_EMAIL_ADDRESSES', 'CONFG', 'knoreceipt-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PDP', 'OLE411', 'The group or person that will be identified as the contact in the email that is sent to a payee when a payment is canceled.', 'TAX_CONTACT', 'CONFG', 'Financial Management Services Tax Department', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PDP', 'OLE412', 'Email address notified when a payment is held for tax review and used for customer questions if a held payment is canceled.', 'TAX_GROUP_TO_EMAIL_ADDRESSES', 'CONFG', 'knoreceipt-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ExtractAchPaymentsStep', 'A', 'OLE-PDP', 'OLE413', 'Email subject line for the ACH summary.', 'ACH_SUMMARY_EMAIL_SUBJECT', 'CONFG', 'Load PDP ACH Direct-Deposit', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ExtractAchPaymentsStep', 'A', 'OLE-PDP', 'OLE414', 'A central administrative email address to send summary information when ACH payments are extracted.', 'ACH_SUMMARY_TO_EMAIL_ADDRESSES', 'CONFG', 'knoreceipt-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'LoadPaymentsStep', 'A', 'OLE-PDP', 'OLE415', 'Email subject line for a payment load that failed.', 'FAILURE_EMAIL_SUBJECT', 'CONFG', 'PDP --- Payment file NOT loaded', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'LoadPaymentsStep', 'A', 'OLE-PDP', 'OLE416', 'A central administrative email address to send a notice to when a PDP file fails to load properly.', 'HARD_EDIT_TO_EMAIL_ADDRESSES', 'CONFG', 'knoreceipt-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'LoadPaymentsStep', 'A', 'OLE-PDP', 'OLE417', 'A central administrative email address to send a notice to when PDP finds errors during the file load process. These types of errors do not prevent the file from loading and are for informational purposes.', 'SOFT_EDIT_TO_EMAIL_ADDRESSES', 'CONFG', 'knoreceipt-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'LoadPaymentsStep', 'A', 'OLE-PDP', 'OLE418', 'Email subject line for a successful payment load.', 'SUCCESS_EMAIL_SUBJECT', 'CONFG', 'PDP --- Payment file loaded', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'LoadPaymentsStep', 'A', 'OLE-PDP', 'OLE419', 'Email subject line for a payment load that contained payments held for tax.', 'TAX_EMAIL_SUBJECT', 'CONFG', 'PDP --- Payment file loaded with payment(s) held for Tax', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'LoadPaymentsStep', 'A', 'OLE-PDP', 'OLE420', 'Email subject line for a payment load that violated the customer threshold.', 'THRESHOLD_EMAIL_SUBJECT', 'CONFG', 'PDP --- Payment file loaded with Threshold Warnings', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Lookup', 'A', 'OLE-PDP', 'OLE421', 'The number of results that are returned on the Format Summary Review screen in PDP.', 'FORMAT_SUMMARY_REVIEW_RESULTS_PER_PAGE', 'CONFG', '30', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Lookup', 'A', 'OLE-PDP', 'OLE422', 'Maximum number of results returned in a look-up query.', 'RESULTS_LIMIT', 'CONFG', '500', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Lookup', 'A', 'OLE-PDP', 'OLE423', 'Maximum number of results per page returned in a look-up query.', 'RESULTS_PER_PAGE', 'CONFG', '50', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentDetail', 'A', 'OLE-PDP', 'OLE424', 'Number of days past since a payment was disbursed before cancel or cancel and reissue is disallowed.', 'DISBURSEMENT_CANCELLATION_DAYS', 'VALID', '30', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentGroup', 'A', 'OLE-PDP', 'OLE425', 'Default priority sort order for pdp payment files.', 'DEFAULT_SORT_GROUP_ID', 'CONFG', '4', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentGroup', 'A', 'OLE-PDP', 'OLE426', 'Highest priority sort order for pdp payment files.', 'SORT_GROUP_SELECTION_1', 'CONFG', 'processImmediate=true', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentGroup', 'A', 'OLE-PDP', 'OLE427', 'Second highest priority sort order for pdp payment files.', 'SORT_GROUP_SELECTION_2', 'CONFG', 'pymtAttachment=true', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentGroup', 'A', 'OLE-PDP', 'OLE428', 'Third highest priority sort order for pdp payment files.', 'SORT_GROUP_SELECTION_3', 'CONFG', 'pymtSpecialHandling=true', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PURAP', 'OLE429', 'Format with 0s is so if vendor sends time information we will only check the year, month and date.', 'CXML_DATE_FORMAT', 'CONFG', '0000-00-00', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PURAP', 'OLE430', 'Simple data format for CXML.', 'CXML_SIMPLE_DATE_FORMAT', 'CONFG', 'yyyy-MM-dd', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PURAP', 'OLE431', 'Simple time format used in CXML.', 'CXML_SIMPLE_TIME_FORMAT', 'CONFG', 'HH:mm:ss.sss', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PURAP', 'OLE432', 'Format with 0s is so if vendor sends time information we will only check the year, month and date.', 'KUALI_DATE_FORMAT', 'CONFG', '00/00/0000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PURAP', 'OLE433', 'Generic PURAP OLE date format.', 'KUALI_SIMPLE_DATE_FORMAT', 'CONFG', 'MM/dd/yyyy', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-PURAP', 'OLE434', 'Month first simple date format.', 'KUALI_SIMPLE_DATE_FORMAT_2', 'CONFG', 'MM-dd-yyyy', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AutoCloseRecurringOrdersStep', 'A', 'OLE-PURAP', 'OLE435', 'Date used by batch job to automatically close recurring POs prior to this date. Parameter is defaulted to "mm/dd/yyyy" when job shouldn''t close any orders. User should set the date when ready to close orders.', 'AUTO_CLOSE_RECURRING_PO_DATE', 'CONFG', 'mm/dd/yyyy', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AutoCloseRecurringOrdersStep', 'A', 'OLE-PURAP', 'OLE436', 'Email address used by batch job for automatically closing POs. Email is sent to this address upon successful completion of the job to inform user of the number of POs closed. ', 'AUTO_CLOSE_RECURRING_PO_TO_EMAIL_ADDRESSES', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-PURAP', 'OLE437', 'Cutoff time for auto approve payment requests and extract jobs, represented in 24 hour time format. If either of these jobs run between midnight and the CUTOFF_TIME, they consider the job run date - 1 to be the current day. If they run between the CUTOFF_TIME and midnight, they consider the job run date to be the current day. For example, if the CUTOFF_TIME is 5 AM and the auto approve job runs at 2 AM on March 27th , it will consider March 26th to be the current day when looking for payment requests that meet the auto approve criteria and have a pay date less than or equal to the current day. If this parameter is blank, the jobs will always consider their run date to be the current day.', 'PRE_DISBURSEMENT_EXTRACT_CUTOFF_TIME', 'CONFG', '05:00:00', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-PURAP', 'OLE438', 'Allowed organization code for loading EPIC payments to process checks.', 'PRE_DISBURSEMENT_EXTRACT_ORGANIZATION', 'CONFG', 'KUAL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-PURAP', 'OLE439', 'PDP Sub-Unit Code for loading EPIC payments', 'PRE_DISBURSEMENT_EXTRACT_SUB_UNIT', 'CONFG', 'PRAP', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ContractManagerAssignment', 'A', 'OLE-PURAP', 'OLE440', 'Indicator whether the contract manager assignment document should default the contract manager based on the data in the Requisition.', 'ENABLE_DEFAULT_CONTRACT_MANAGER_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ContractManagerAssignment', 'A', 'OLE-PURAP', 'OLE441', 'Indicator related default document titles. Set to "N" if accepting the Kuali default document title, related to Contract Manager Assignment, as listed in the Document Search results.', 'OVERRIDE_DOCUMENT_TITLE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ContractManagerAssignment', 'A', 'OLE-PURAP', 'OLE442', 'The required file extension for the file containing the contract manager(s)'' signature image file(s).', 'PDF_IMAGE_EXTENSION', 'CONFG', 'gif', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ContractManagerAssignment', 'A', 'OLE-PURAP', 'OLE443', 'The required file prefix for the file containing the contract manager(s)'' signature image file(s).', 'PDF_IMAGE_PREFIX', 'CONFG', 'cm', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE444', 'When the vendor address information is populated on a Payment Request or Credit
 Memo, indicate whether the attention line should be cleared when the default ad
dress is a PO type address.', 'BLANK_ATTENTION_LINE_FOR_PO_TYPE_ADDRESS', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE445', 'Default item type code for non-quantity driven regular (i.e. above-the-line) items.', 'DEFAULT_NON_QUANTITY_ITEM_TYPE', 'CONFG', 'SRVC', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE446', 'Default item type code for quantity driven regular (i.e. above-the-line) items.', 'DEFAULT_QUANTITY_ITEM_TYPE', 'CONFG', 'ITEM', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE447', 'If set to N, the B2B integrator should use vendor ID to indicate the vendor. If set to Y, the B2B integrator should use the vendor DUNS number to indicate the vendor.', 'ENABLE_B2B_BY_VENDOR_DUNS_NUMBER_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE448', 'Indicator whether commodity code functionality should be completely turned on for the Requisition and Purchase Order', 'ENABLE_COMMODITY_CODE_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE449', 'Indicates whether receiving address functionality should be completely turned off for the Requisition and Purchase Order which will hide the "Receiving Address" and "Address to Vendor" section on the Delivery tab. An institution would select to hide these sections if they have no need for receiving addresses.', 'ENABLE_RECEIVING_ADDRESS_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE450', 'Specifies whether tax is enabled globally for Purchasing/Accounts Payable documents', 'ENABLE_SALES_TAX_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE451', 'Relate URL for the line item import help.', 'LINE_ITEM_IMPORT', 'CONFG', 'default.htm?turl=WordDocuments%2Fdataimporttemplates.htm', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE452', 'Object Code subtypes that require the associated item to have a quantity.', 'OBJECT_SUB_TYPES_REQUIRING_QUANTITY', 'VALID', 'UC;UO;UF;CM;CF;CO;AM', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE453', 'The temporary path location for storing Purap''s pdf file.', 'PDF_DIRECTORY', 'CONFG', '.', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE454', 'The required file extension for the file containing the purchasing director(s)'' signature image file(s).', 'PDF_DIRECTOR_IMAGE_EXTENSION', 'CONFG', 'gif', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE455', 'The required file prefix for the file containing the purchasing director(s)'' signature image file(s).', 'PDF_DIRECTOR_IMAGE_PREFIX', 'CONFG', 'pd', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE456', 'Indicator for whether the images for purap pdf documents are available. Y means yes and N means no.', 'PDF_IMAGES_AVAILABLE_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE457', 'The required file extension for the file containing the logo image file.', 'PDF_LOGO_IMAGE_EXTENSION', 'CONFG', 'jpg', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE458', 'The required file prefix for the file containing the logo image file.', 'PDF_LOGO_IMAGE_PREFIX', 'CONFG', 'logo', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE459', 'The status inquiry URL to be displayed on the Purap pdf documents.', 'PDF_STATUS_INQUIRY_URL', 'CONFG', '<a href=''http://kuali.org/''>"http://kuali.org/"</a>', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE460', 'Allowed vendor type(s) for Purchase Order and Requisition documents.', 'REQ_AND_PO_VENDOR_TYPES', 'VALID', 'PO', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE461', 'Allows the display of a continuation account warning to AP Users on credit memo and payment request documents.', 'SHOW_CONTINUATION_ACCOUNT_WARNING_AP_USERS_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE462', 'Allows the display of a continuation account warning to fiscal officers on credit memo and payment request documents.', 'SHOW_CONTINUATION_ACCOUNT_WARNING_FISCAL_OFFICERS_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'D', 'OLE-PURAP', 'OLE463', 'A list of delivery states that are allowed to collect taxes/denied from collecting taxes', 'TAXABLE_DELIVERY_STATES', 'CONFG', 'IN;NY', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE464', 'A list of fund groups that are allowed/denied from being taxed based on the value of the constraint code when the delivery state is not taxable', 'TAXABLE_FUND_GROUPS_FOR_NON_TAXABLE_STATES', 'CONFG', 'AE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'D', 'OLE-PURAP', 'OLE465', 'A list of fund groups that are allowed/denied from being taxed based on the value of the constraint code when the delivery state is taxable', 'TAXABLE_FUND_GROUPS_FOR_TAXABLE_STATES', 'CONFG', 'RF', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE466', 'A list of object code consolidations that are allowed/denied from being taxed based on the value of the constraint code when the delivery state is not taxable', 'TAXABLE_OBJECT_CONSOLIDATIONS_FOR_NON_TAXABLE_STATES', 'CONFG', 'ASEX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'D', 'OLE-PURAP', 'OLE467', 'A list of object code consolidations that are allowed/denied from being taxed based on the value of the constraint code when the delivery state is taxable', 'TAXABLE_OBJECT_CONSOLIDATIONS_FOR_TAXABLE_STATES', 'CONFG', 'OTRE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE468', 'A list of object code levels that are allowed/denied from being taxed based on the value of the constraint code when the delivery state is not taxable', 'TAXABLE_OBJECT_LEVELS_FOR_NON_TAXABLE_STATES', 'CONFG', 'CASH', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'D', 'OLE-PURAP', 'OLE469', 'A list of object code levels that are allowed/denied from being taxed based on the value of the constraint code when the delivery state is taxable', 'TAXABLE_OBJECT_LEVELS_FOR_TAXABLE_STATES', 'CONFG', 'RESA', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE470', 'A list of sub-fund groups that are allowed/denied from being taxed based on the value of the constraint code when the delivery state is not taxable', 'TAXABLE_SUB_FUND_GROUPS_FOR_NON_TAXABLE_STATES', 'CONFG', 'DFRES', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'D', 'OLE-PURAP', 'OLE471', 'A list of sub-fund groups that are allowed/denied from being taxed based on the value of the constraint code when the delivery state is taxable', 'TAXABLE_SUB_FUND_GROUPS_FOR_TAXABLE_STATES', 'CONFG', 'FEDERE', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE472', 'the object code to be used for the trade in item accounts if the object code subtype of the account is one of the given set of Capital Asset subtypes', 'TRADE_IN_OBJECT_CODE_FOR_CAPITAL_ASSET', 'CONFG', '7070', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE473', 'the object code to be used for the trade in item accounts if the object code subtype of the account is Capital Lease', 'TRADE_IN_OBJECT_CODE_FOR_CAPITAL_LEASE', 'CONFG', '7099', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE474', 'Indicator whether the distribute accounts functionality should perform validations upon the addition of a new accounting line to the distribution account list.', 'VALIDATE_ACCOUNT_DISTRIBUTION_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLEPURAP7033', 'Indicates if an accounting line with zero dollar amount from Purchase Order document can be copied to Payment Request document.', 'COPY_ACCOUNTING_LINES_WITH_ZERO_AMOUNT_FROM_PO_TO_PREQ_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLEPURAP7033b', 'Indicates if Fiscal Officer can approve an accounting line that has zero dollar amount.', 'APPROVE_ACCOUNTING_LINES_WITH_ZERO_DOLLAR_AMOUNT_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ElectronicInvoiceStep', 'A', 'OLE-PURAP', 'OLE475', 'Send the Electronic Invoice daily summary report from these email address', 'DAILY_SUMMARY_REPORT_FROM_EMAIL_ADDRESS', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ElectronicInvoiceStep', 'A', 'OLE-PURAP', 'OLE476', 'Email addresses to send the Electronic Invoice daily summary report', 'DAILY_SUMMARY_REPORT_TO_EMAIL_ADDRESSES', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ElectronicInvoiceStep', 'A', 'OLE-PURAP', 'OLE477', 'Value indicating whether the loaded eInvoice cxml file shall be moved to the accept/reject directory.', 'FILE_MOVE_AFTER_LOAD_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ElectronicInvoiceStep', 'A', 'OLE-PURAP', 'OLE478', 'List of Requisition Source codes that require the catalog matching on electronic invoices.', 'REQUISITION_SOURCES_REQUIRING_CATALOG_MATCHING', 'CONFG', 'B2B', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ElectronicInvoiceStep', 'A', 'OLE-PURAP', 'OLE479', 'Acceptable lower variance percentage to determine the sales tax matching', 'SALES_TAX_LOWER_VARIANCE_PERCENT', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ElectronicInvoiceStep', 'A', 'OLE-PURAP', 'OLE480', 'Acceptable upper variance percentage to determine the sales tax matching', 'SALES_TAX_UPPER_VARIANCE_PERCENT', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'LineItemReceiving', 'A', 'OLE-PURAP', 'OLE481', 'Allows the display of the clear and load quantity buttons on a receiving line document.', 'SHOW_CLEAR_AND_LOAD_QTY_BUTTONS', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE482', 'Allowed Item Type code(s) for below the line items on Payment Request documents.', 'ADDITIONAL_CHARGES_ITEM_TYPES', 'CONFG', 'FRHT;SPHD;MNOR;MISC;DISC;ORDS;TRDI;FDTX;FDGR;STTX;STGR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE483', 'The number of days after fiscal year-end that a Payment Request is allowed to post to the previous fiscal year if the PO was created in the previous fiscal year.', 'ALLOW_BACKPOST_DAYS', 'CONFG', '7', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE484', 'Note generated when a Payment Request document is cancelled during pre disbursement processing', 'CANCEL_NOTE', 'CONFG', 'Cancelled inside PDP', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE485', 'Default amount above which positive payment request approval is required.', 'DEFAULT_POS_APRVL_LMT', 'CONFG', '5000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE486', 'Item type code(s) that allow for negative amounts on below the line items in the Payment Request document.', 'ITEM_TYPES_ALLOWING_NEGATIVE', 'VALID', 'MISC;DISC;ORDS;TRDI;FDTX;STTX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE487', 'Item type code(s) that allow for positive amounts on below the line items in the Payment Request document.', 'ITEM_TYPES_ALLOWING_POSITIVE', 'VALID', 'FRHT;SPHD;MNOR;FDGR;STGR;MISC', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE488', 'Item type code(s) that allow for zero amounts on below the line items in the Payment Request document.', 'ITEM_TYPES_ALLOWING_ZERO', 'VALID', 'FRHT;SPHD;MNOR;MISC;DISC;ORDS;TRDI;FDTX;FDGR;STTX;STGR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE489', 'Item type code(s) that require the description field to be completed on below the line items in the Payment Request document.', 'ITEM_TYPES_REQUIRING_USER_ENTERED_DESCRIPTION', 'VALID', 'MISC;ORDS;TRDI', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE490', 'Specifies item types that do not allow account editing on a specific document', 'ITEM_TYPES_RESTRICTING_ACCOUNT_EDIT', 'CONFG', 'TRDI;FDTX;FDGR;STTX;STGR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE491', 'Allowed Account on the Non-resident Alien Federal Tax line.', 'NON_RESIDENT_ALIEN_TAX_FEDERAL_ACCOUNT', 'CONFG', '9612729', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE492', 'Allowed Chart on the Non-resident Alien Federal Tax line.', 'NON_RESIDENT_ALIEN_TAX_FEDERAL_CHART', 'CONFG', 'UA', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE493', 'NRA Federal Tax Line objects by income class.  Format of list is income class 1=object 1;income class 2=object 2;?', 'NON_RESIDENT_ALIEN_TAX_FEDERAL_OBJECT_CODE_BY_INCOME_CLASS', 'CONFG', 'F=9021;I=9022;R=9022', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE494', 'Allowed Account on the Non-resident Alien State Tax line.', 'NON_RESIDENT_ALIEN_TAX_STATE_ACCOUNT', 'CONFG', '9612732', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE495', 'Allowed Chart on the Non-resident Alien State Tax line.', 'NON_RESIDENT_ALIEN_TAX_STATE_CHART', 'CONFG', 'UA', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE496', 'Allowed Object Code(s) on the Non-resident Alien State Tax Line by income class. Format of list is income class 1=object 1;income class 2=object 2.', 'NON_RESIDENT_ALIEN_TAX_STATE_OBJECT_CODE_BY_INCOME_CLASS', 'CONFG', 'F=9021;I=9022;R=9022', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE497', 'The number of days added to the processed date to calculate the default pay date.', 'NUMBER_OF_DAYS_USED_TO_CALCULATE_DEFAULT_PAY_DATE', 'CONFG', '10', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE498', 'Object Code(s) allowed for use on a <doc type> document regardless of object code restrictions from other parameters.', 'OBJECT_CODES_OVERRIDING_RESTRICTIONS', 'CONFG', '5881;9000;9118', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'D', 'OLE-PURAP', 'OLE499', 'Object Consolidation(s) restricted from use on a PaymentRequest document.', 'OBJECT_CONSOLIDATIONS', 'CONFG', 'CMPN;SCHL;RSRX;ASEX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'D', 'OLE-PURAP', 'OLE500', 'Object Level(s) restricted from use on a PaymentRequest document.', 'OBJECT_LEVELS', 'CONFG', 'DEPR;ICOE;VADJ;TAX;DEBT', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'D', 'OLE-PURAP', 'OLE501', 'Object Sub-Type(s) restricted from use on a PaymentRequest document.', 'OBJECT_SUB_TYPES', 'CONFG', 'TN', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE502', 'Object Type(s) restricted from use on a PaymentRequest document.', 'OBJECT_TYPES', 'CONFG', 'EE;ES;EX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE503', 'Indicator related default document titles. Set to "N" if accepting the Kuali default document title, related to Payment Request, as listed in the Document Search results.', 'OVERRIDE_DOCUMENT_TITLE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE504', 'Indicates whether or not an attachment is required on a Payment Request to route for fiscal approval.', 'REQUIRE_ATTACHMENT_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE505', 'Note generated when a Payment Request document is reset during pre disbursement processing', 'RESET_NOTE', 'CONFG', 'Reset for Extraction by PDP', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentRequest', 'A', 'OLE-PURAP', 'OLE506', 'Allowed object levels by object type for use on a PaymentRequest document. Format of list is object type 1=object level 1, object level 2;object type 2=object level 3,object level 4,object level 5.', 'VALID_OBJECT_LEVELS_BY_OBJECT_TYPE', 'CONFG', 'AS=INV;AS=OASS', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE507', 'Allowed Item Type code(s) for below the line items on Purchase Order documents.', 'ADDITIONAL_CHARGES_ITEM_TYPES', 'CONFG', 'FRHT;SPHD;ORDS;TRDI', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE508', 'Object codes that, when associated with Contract & Grant accounts on a Purchase Order, will force the PO to route to a C&G workgroup for approval. Format of list is chart 1=object 1, object 2;chart 2=object 3,object 4,object 5.', 'CG_ROUTE_OBJECT_CODES_BY_CHART', 'CONFG', 'BL=5011,5012', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE509', 'Object levels that, when associated with Contract & Grant accounts on a Purchase Order, will force the PO to route to a C&G workgroup for approval. Format of list is chart 1=object level 1, object level 2;chart 2=object level 3,object level 4,object level 5', 'CG_ROUTE_OBJECT_LEVELS_BY_CHART', 'CONFG', 'BL=ADV,CAP,COSV,CREX,RENT,RESA,SERV;EA=ADV,CAP,COSV,CREX,RENT,RESA,SERV;IN=ADV,CAP,COSV,CREX,RENT,RESA,SERV;KO=ADV,CAP,COSV,CREX,RENT,RESA,SERV;NW=ADV,CAP,COSV,CREX,RENT,RESA,SERV;SB=ADV,CAP,COSV,CREX,RENT,RESA,SERV;SE=ADV,CAP,COSV,CREX,RENT,RESA,SERV;UA=ADV,CAP,COSV,CREX,RENT,RESA,SERV ', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE510', 'The default vendor choice for automatic purchase orders (typically referred to as APOs).', 'DEFAULT_APO_VENDOR_CHOICE', 'CONFG', 'SMAL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE511', 'The default vendor choice for B2B purchase orders.', 'DEFAULT_B2B_VENDOR_CHOICE', 'CONFG', 'CONT', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE512', 'Indicator whether commodity code is required on Purchase Order items.', 'ITEMS_REQUIRE_COMMODITY_CODE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE513', 'Item type code(s) that allow for negative amounts on below the line items in the Purchase Order document.', 'ITEM_TYPES_ALLOWING_NEGATIVE', 'VALID', 'ORDS;TRDI', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE514', 'Item type code(s) that allow for positive amounts on below the line items in the Purchase Order document.', 'ITEM_TYPES_ALLOWING_POSITIVE', 'VALID', 'FRHT;SPHD', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE515', 'Item type code(s) that allow for zero amounts on below the line items in the Purchase Order document.', 'ITEM_TYPES_ALLOWING_ZERO', 'VALID', 'FRHT;SPHD;ORDS;TRDI', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE516', 'Item type code(s) that require the description field to be completed on below the line items in the Purchase Order document.', 'ITEM_TYPES_REQUIRING_USER_ENTERED_DESCRIPTION', 'VALID', 'FRHT;SPHD;ORDS;TRDI', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE517', 'Specifies item types that do not allow account editing on a specific document', 'ITEM_TYPES_RESTRICTING_ACCOUNT_EDIT', 'CONFG', 'TRDI', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'D', 'OLE-PURAP', 'OLE518', 'Object codes that, when associated with Contract & Grant accounts on a Purchase Order, will not force the PO to route to a C&G workgroup for approval. Format of list is chart 1=object 1, object 2;chart 2=object 3,object 4,object 5.', 'NO_CG_ROUTE_OBJECT_CODES_BY_CHART', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE519', 'Object levels that, when associated with Contract & Grant accounts on a Purchase Order, will not force the PO to route to a C&G workgroup for approval. Format of list is chart 1=object level 1, object level 2;chart 2=object level 3,object level 4,object level 5', 'NO_CG_ROUTE_OBJECT_LEVELS_BY_CHART', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE520', 'Object Code(s) allowed for use on a <doc type> document regardless of object code restrictions from other parameters.', 'OBJECT_CODES_OVERRIDING_RESTRICTIONS', 'CONFG', '5881;9000;9118', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'D', 'OLE-PURAP', 'OLE521', 'Object Consolidation(s) restricted from use on a PurchaseOrder document.', 'OBJECT_CONSOLIDATIONS', 'CONFG', 'CMPN;SCHL;RSRX;ASEX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'D', 'OLE-PURAP', 'OLE522', 'Object Level(s) restricted from use on a PurchaseOrder document.', 'OBJECT_LEVELS', 'CONFG', 'DEPR;ICOE;VADJ;TAX;DEBT', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'D', 'OLE-PURAP', 'OLE523', 'Object Sub-Type(s) restricted from use on a PurchaseOrder document.', 'OBJECT_SUB_TYPES', 'CONFG', 'TN', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE524', 'Object Type(s) restricted from use on a PurchaseOrder document.', 'OBJECT_TYPES', 'CONFG', 'EE;ES;EX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE525', 'Indicator related default document titles. Set to "N" if accepting the Kuali default document title, related to Purchase Order, as listed in the Document Search results.', 'OVERRIDE_DOCUMENT_TITLE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE526', 'The PO transmission method types allowed for printing a preview of the Purchase Order.', 'PRINT_PREVIEW_TRANSMISSION_METHOD_TYPES', 'CONFG', 'PRIN;FAX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE527', 'The PO transmission method types allowed for the Retransmit document.', 'RETRANSMIT_TRANSMISSION_METHOD_TYPES', 'CONFG', 'PRIN;FAX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE528', 'Allowed object levels by object type for use on a PurchaseOrder document. Format of list is object type 1=object level 1, object level 2;object type 2=object level 3,object level 4,object level 5.', 'VALID_OBJECT_LEVELS_BY_OBJECT_TYPE', 'CONFG', 'AS=INV;AS=OASS', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE529', 'Allowed Item Type code(s) for below the line items on Requisition documents.', 'ADDITIONAL_CHARGES_ITEM_TYPES', 'CONFG', 'FRHT;SPHD;ORDS;TRDI', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE530', 'The numbers of days prior to fiscal year-end that a requisition can be created and can qualify to become an Automatic Purchase Order if the posting year is set to the next fiscal year.', 'ALLOW_APO_NEXT_FY_DAYS', 'CONFG', '10', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE531', 'The number of days before fiscal year-end that the Requisition is allowed to set the posting year to the next posting year so the encumbrance will be created on the next fiscal year.', 'ALLOW_ENCUMBER_NEXT_YEAR_DAYS', 'CONFG', '181', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE532', 'If the automatic Purchase Order limit amount cannot be determined based on the vendor contract or the organization on the Requisition, use this default limit amount.', 'AUTOMATIC_PURCHASE_ORDER_DEFAULT_LIMIT_AMOUNT', 'CONFG', '1000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE533', 'The number of days after the creation of a requisition in which it can be copied.', 'B2B_ALLOW_COPY_DAYS', 'CONFG', '5', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE534', 'The default funding source for a requisition.', 'DEFAULT_FUNDING_SOURCE', 'CONFG', 'INST', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE535', 'The default "Method of PO Transmission" code value that appears on the requisition document.', 'DEFAULT_METHOD_OF_PO_TRANSMISSION', 'CONFG', 'PRIN', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE536', 'Indicates whether the "Address to Vendor" section of the Delivery tab on the Requisition should be readonly to not allow the user to select which address should be sent to the vendor (delivery or receiving address). An institution would select to hide this section if they prefer to not allow Requestors editing the final delivery/receiving address indicator.', 'ENABLE_ADDRESS_TO_VENDOR_SELECTION_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE537', 'Indicator whether commodity code is required on Requisition items.', 'ITEMS_REQUIRE_COMMODITY_CODE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE538', 'Item type code(s) that allow for negative amounts on below the line items in the Requisition document.', 'ITEM_TYPES_ALLOWING_NEGATIVE', 'VALID', 'TRDI;ORDS', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE539', 'Item type code(s) that allow for positive amounts on below the line items in the Requisition document.', 'ITEM_TYPES_ALLOWING_POSITIVE', 'VALID', 'FRHT;SPHD', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE540', 'Item type code(s) that allow for zero amounts on below the line items in the Requisition document.', 'ITEM_TYPES_ALLOWING_ZERO', 'VALID', 'FRHT;SPHD;ORDS;TRDI', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE541', 'Item type code(s) that require the description field to be completed on below the line items in the Requisition document.', 'ITEM_TYPES_REQUIRING_USER_ENTERED_DESCRIPTION', 'VALID', 'FRHT;SPHD;ORDS;TRDI', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE542', 'Specifies item types that do not allow account editing on a specific document', 'ITEM_TYPES_RESTRICTING_ACCOUNT_EDIT', 'CONFG', 'ORDS;TRDI', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE543', 'Object Code(s) allowed for use on a <doc type> document regardless of object code restrictions from other parameters.', 'OBJECT_CODES_OVERRIDING_RESTRICTIONS', 'CONFG', '5881;9000;9118', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'D', 'OLE-PURAP', 'OLE544', 'Object Consolidation(s) restricted from use on a Requisition document.', 'OBJECT_CONSOLIDATIONS', 'CONFG', 'CMPN;SCHL;RSRX;ASEX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'D', 'OLE-PURAP', 'OLE545', 'Object Level(s) restricted from use on a Requisition document.', 'OBJECT_LEVELS', 'CONFG', 'DEPR;ICOE;VADJ;TAX;DEBT', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'D', 'OLE-PURAP', 'OLE546', 'Object Sub-Type(s) restricted from use on a Requisition document.', 'OBJECT_SUB_TYPES', 'CONFG', 'TN', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE547', 'Object Type(s) restricted from use on a Requisition document.', 'OBJECT_TYPES', 'CONFG', 'EE;ES;EX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE548', 'Indicator related default document titles. Set to "N" if accepting the Kuali default document title, related to Requisition, as listed in the Document Search results.', 'OVERRIDE_DOCUMENT_TITLE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE549', 'The default amount where anything greater than the listed value will fail separation of duties route node.', 'SEPARATION_OF_DUTIES_DOLLAR_AMOUNT', 'CONFG', '10000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE550', 'Allowed object levels by object type for use on a Requisition document. Format of list is object type 1=object level 1, object level 2;object type 2=object level 3,object level 4,object level 5.', 'VALID_OBJECT_LEVELS_BY_OBJECT_TYPE', 'CONFG', 'AS=INV;AS=OASS', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE551', 'Allowed Item Type code(s) for below the line items on Credit Memo documents.', 'ADDITIONAL_CHARGES_ITEM_TYPES', 'CONFG', 'RSTO;MSCR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE552', 'Note generated when a Credit Memo document is cancelled during pre disbursement processing', 'CANCEL_NOTE', 'CONFG', 'Cancelled inside PDP', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'D', 'OLE-PURAP', 'OLE553', 'Restricted object levels by object type for use on a Credit Memo document. Format of list is object type 1=object level 1, object level 2;object type 2=object level 3,object level 4,object level 5.', 'INVALID_OBJECT_LEVELS_BY_OBJECT_TYPE', 'VALID', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE554', 'Item type code(s) that allow for negative amounts on below the line items in the Credit Memo Document created from a Payment Request.', 'ITEM_TYPES_ALLOWING_NEGATIVE', 'VALID', 'RSTO;MSCR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE555', 'Item type code(s) that allow for positive amounts on below the line items in the Credit Memo Document created from a Payment Request.', 'ITEM_TYPES_ALLOWING_POSITIVE', 'VALID', 'MSCR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE556', 'Item type code(s) that allow for zero amounts on below the line items in the Credit Memo Document created from a Payment Request.', 'ITEM_TYPES_ALLOWING_ZERO', 'VALID', 'RSTO;MSCR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE557', 'Item type code(s) that require the description field to be completed on below the line items in the Credit Memo Document created from a Payment Request.', 'ITEM_TYPES_REQUIRING_USER_ENTERED_DESCRIPTION', 'VALID', 'MSCR', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE558', 'Object Code(s) allowed for use on a <doc type> document regardless of object code restrictions from other parameters.', 'OBJECT_CODES_OVERRIDING_RESTRICTIONS', 'CONFG', '5881;9000;9118', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'D', 'OLE-PURAP', 'OLE559', 'Object Consolidation(s) restricted from use on a Credit Memo document.', 'OBJECT_CONSOLIDATIONS', 'VALID', 'CMPN;SCHL;RSRX;ASEX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'D', 'OLE-PURAP', 'OLE560', 'Object Level(s) restricted from use on a Credit Memo document.', 'OBJECT_LEVELS', 'VALID', 'DEPR;ICOE;VADJ;TAX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'D', 'OLE-PURAP', 'OLE561', 'Object Sub-Type(s) restricted from use on a Credit Memo document.', 'OBJECT_SUB_TYPES', 'VALID', 'TN', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE562', 'Allowed object type code(s) for use on a Credit Memo document.', 'OBJECT_TYPES', 'VALID', 'EE;ES;EX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE563', 'Indicator related default document titles. Set to "N" if accepting the Kuali default document title, related to Credit Memo, as listed in the Document Search results.', 'OVERRIDE_DOCUMENT_TITLE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE564', 'Indicates whether or not an attachment is required in order for a Credit Memo to route for fiscal approval.', 'REQUIRE_ATTACHMENT_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE565', 'Note generated when a Credit Memo document is reset during pre disbursement processing', 'RESET_NOTE', 'CONFG', 'Reset for Extraction by PDP', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorCreditMemo', 'A', 'OLE-PURAP', 'OLE566', 'Allowed object levels by object type for use on a Credit Memo document. Format of list is object type 1=object level 1, object level 2;object type 2=object level 3,object level 4,object level 5.', 'VALID_OBJECT_LEVELS_BY_OBJECT_TYPE', 'VALID', 'AS=INV', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SYS', 'OLE567', 'This parameter indicates whether the same account number may be allowed to be associated with multiple chart codes. For example, if account number "123" is in chart "BL", it may not also be in chart "BA". When the value is false, it will turn on the pre-scrubber for the GL scrubber, LL scrubber, GLCP scrubber, LLCP scrubber, and GL collector. When the work is complete, if the parameter is false, the Account maintenance document will prevent the creation of account numbers that span mulitple charts and will allow accounting documents deduce the chart code based on the account number.', 'ACCOUNTS_CAN_CROSS_CHARTS_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SYS', 'OLE568', 'Current liability object codes used in cash sufficient funds calculation. The formula is: available cash = cash balance less current liabilities plus/minus open encumbrances plus/minus pending ledger entries. There isn''t a given (or group of) object type, sub-type, level, or consolidation that defines current liabilities so these are the codes that are current liabilities that commonly appear on accounts. ?', 'SUFFICIENT_FUNDS_CURRENT_LIABILITIES_OBJECT_CODES', 'CONFG', '9040;9041;9050', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AutoDisapproveDocumentsStep', 'A', 'OLE-SYS', 'OLE569', 'The annotation that will be added to the route log when a document is auto disapproved.', 'YEAR_END_AUTO_DISAPPROVE_ANNOTATION', 'CONFG', 'This document has been automatically disapproved as part of year-end closing.  If these are valid  transactions they should be recreated using a year-end closing document.', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AutoDisapproveDocumentsStep', 'A', 'OLE-SYS', 'OLE570', 'The automatic disapproval job will disapprove enroute documents with a create date equal to or earlier than this date.', 'YEAR_END_AUTO_DISAPPROVE_DOCUMENT_CREATE_DATE', 'CONFG', '6/30/2010', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AutoDisapproveDocumentsStep', 'A', 'OLE-SYS', 'OLE571', 'Controls the date on which the auto disapproval step should run.', 'YEAR_END_AUTO_DISAPPROVE_DOCUMENT_RUN_DATE', 'CONFG', '6/30/2010', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AutoDisapproveDocumentsStep', 'D', 'OLE-SYS', 'OLE572', 'The document type(s) that are not allowed to be automatically disapproved.', 'YEAR_END_AUTO_DISAPPROVE_DOCUMENT_TYPES', 'CONFG', 'OLE_AV;OLE_CR;OLE_ND;OLE_PCDO;OLE_DV;OLE_CCR;OLE_AD;OLE_CMD', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AutoDisapproveDocumentsStep', 'A', 'OLE-SYS', 'OLE573', 'Documents that are children of this document type are eligible for automatic disapproval.', 'YEAR_END_AUTO_DISAPPROVE_PARENT_DOCUMENT_TYPE', 'CONFG', 'OLEFinancialProcessingTransactionalDocument', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Bank', 'A', 'OLE-SYS', 'OLE574', 'This specifies the document types that should display the bank code field when the ENABLE_BANK_SPECIFICATION_IND parameter is set to Y. Document types not in the list that support the functionality will still generate bank offsets if the specification indicator is Y. For document types not in the list the default bank code given by the DEFAULT_BANK_BY_DOCUMENT_TYPE parameter will be used to generate the offsets. Supported document types include DV;ND;AD;CMD;PREQ;CM;CTRL.', 'BANK_CODE_DOCUMENT_TYPES', 'CONFG', 'OLE_DV;OLE_ND;OLE_AD;OLE_CMD;OLE_PREQ;OLE_CM;OLE_CTRL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Bank', 'A', 'OLE-SYS', 'OLE575', 'This specifies the default bank for document types where bank can be selected when the ENABLE_BANK_SPECIFICATION_IND parameter is set to Y.', 'DEFAULT_BANK_BY_DOCUMENT_TYPE', 'CONFG', 'OLE_DV=TEST;OLE_ND=TEST;OLE_AD=TEST;OLE_CMD=TEST;OLE_PREQ=TEST;OLE_CM=TEST;OLE_CTRL=TEST;OLE_CCR=TEST', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Bank', 'A', 'OLE-SYS', 'OLE576', 'Controls whether the bank code functionality is enabled in the system. If set to Y additional bank entries will be created on supported documents. Also when set to Y document types that appear in the BANK_CODE_DOCUMENT_TYPES parameter list will display the bank code for viewing and editing by users who have permission.', 'ENABLE_BANK_SPECIFICATION_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-SYS', 'OLE577', 'Batch file types that are active options for the file upload screen.', 'ACTIVE_FILE_TYPES', 'CONFG', 'collectorInputFileType;procurementCardInputFileType;enterpriseFeederFileSetType;assetBarcodeInventoryInputFileType;customerLoadInputFileType', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-SYS', 'OLE578', 'Relative URL for the accounting line import help. ', 'ACCOUNTING_LINE_IMPORT', 'CONFG', 'default.htm?turl=WordDocuments%2Fdataimporttemplates.htm', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-SYS', 'OLE579', 'Maximum upload size for accounting line spreadsheets. Used by KualiAccountingDocumentFormBase. Must be an integer, optionally followed by "K", "M", or "G".', 'MAX_FILE_SIZE_ACCOUNTING_LINE_IMPORT', 'CONFG', '5M', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ElectronicPaymentClaim', 'A', 'OLE-SYS', 'OLE580', 'The number of ElectronicPaymentClaim summaries which should appear on a single note attached to a claiming document.', 'ELECTRONIC_FUNDS_CLAIM_SUMMARIES_PER_NOTE', 'CONFG', '10', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'FilePurgeStep', 'A', 'OLE-SYS', 'OLE581', 'The FilePurgeStep will traverse the application I/O directories and their sub-directories deleting files with a last modified date more that this number of days prior to today.', 'DEFAULT_NUMBER_OF_DAYS_OLD', 'CONFG', '30', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'FilePurgeStep', 'A', 'OLE-SYS', 'OLE582', 'The FilePurgeStep will traverse the temporary files directory, deleting files with a last modified date more that this number of days prior to today.', 'TEMPORARY_FILES_NUMBER_OF_DAYS_OLD', 'CONFG', '1', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'FiscalYearMakerStep', 'A', 'OLE-SYS', 'OLE583', 'Controls in which mode the Fiscal Year Makers batch process runs. N=copies values to the new fiscal year without overwriting any values that already exist in the new year, and Y=copies values to the new fiscal year and overwrites any values that already exist in the new year.', 'OVERRIDE_TARGET_YEAR_DATA_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'FiscalYearMakerStep', 'A', 'OLE-SYS', 'OLE584', 'Controls the fiscal year for which the Fiscal Year Makers batch process will copy certain Chart, GL, and Labor data to the next sequential year.', 'SOURCE_FISCAL_YEAR', 'CONFG', '2013', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurgePendingAttachmentsStep', 'A', 'OLE-SYS', 'OLE585', 'Pending attachments are attachments that do not yet have a permanent link with the associated Business Object (BO). These pending attachments are stored in the attachments.pending.directory (defined in the configuration service). If the BO is never persisted, then this attachment will become orphaned (i.e. not associated with any BO), but will remain in this directory. The PurgePendingAttachmentsStep batch step deletes these pending attachment files that are older than the value of this parameter. The unit of this value is seconds. Do not set this value too short, as this will cause problems attaching files to BO''s.', 'MAX_AGE', 'CONFG', '86400', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurgeSessionDocumentsStep', 'A', 'OLE-SYS', 'OLE586', 'Determines the age of the session document records that the the step will operate on, e.g. if this param is set to 4, the rows with a last update timestamp older that 4 days prior to when the job is running will be deleted. ', 'NUMBER_OF_DAYS_SINCE_LAST_UPDATE', 'CONFG', '1', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScheduleStep', 'A', 'OLE-SYS', 'OLE587', 'Controls when the daily batch schedule should terminate. The scheduler service implementation compares the start time of the schedule job from quartz with this time on day after the schedule job started running.', 'CUTOFF_TIME', 'CONFG', '02:00:00:AM', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScheduleStep', 'A', 'OLE-SYS', 'OLE588', 'Controls whether when the system is comparing the schedule start day & time with the scheduleStep_CUTOFF_TIME parameter, it considers the specified time to apply to the day after the schedule starts.', 'CUTOFF_TIME_NEXT_DAY_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ScheduleStep', 'A', 'OLE-SYS', 'OLE589', 'Time in milliseconds that the scheduleStep should wait between iterations.', 'STATUS_CHECK_INTERVAL', 'CONFG', '30000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PaymentTermType', 'A', 'OLE-VND', 'OLE590', 'This parameter returns the list of vendor net due type descriptions used in the Payment Term Type maintenance table.', 'PAYMENT_TERMS_DUE_TYPE_DESC', 'CONFG', 'days;date', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'A', 'OLE-VND', 'OLE591', 'The number of digits in a valid tax number.', 'DEFAULT_TAX_NUMBER_DIGITS', 'CONFG', '9', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'A', 'OLE-VND', 'OLE592', 'Ownership types that are allowed when the vendor tax id is of type "FEIN"', 'FEIN_OWNERSHIP_TYPES', 'VALID', 'ID;PT;CP;ET;GV;NP', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'A', 'OLE-VND', 'OLE593', 'The number of digits in a phone number, for formatting.', 'GENERIC_DEFAULT_PHONE_NUMBER_LENGTH', 'CONFG', '10', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'A', 'OLE-VND', 'OLE594', 'Java Regular expressions that describe the acceptable formats for standard phone numbers which allows for the normal 3 digit area code, 3 digit exchange and 4 digit phone number.', 'GENERIC_PHONE_NUMBER_FORMATS', 'CONFG', '\d{3}-\d{3}-\d{4};\(\d{3}\)\s\d{3}-\d{4};\d{3}\s\d{3}\s\d{4}', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'A', 'OLE-VND', 'OLE595', 'The minimum number of characters required in the name field to limit or filter the vendor search.', 'LOOKUP_MINIMUM_NAME_LENGTH', 'VALID', '2', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'A', 'OLE-VND', 'OLE596', 'The minimum number of criteria required as input for a search or lookup. (More criteria returns smaller sets of results.)', 'LOOKUP_MINIMUM_NUMBER_OF_CRITERIA', 'VALID', '1', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'A', 'OLE-VND', 'OLE597', 'This amount is treated as the maximum that a vendor may have as a minimum order amount.', 'MIN_ORDER_AMOUNT', 'VALID', '100000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'A', 'OLE-VND', 'OLE598', 'Indicator related default document titles. Set to "N" if accepting the Kuali default document title, related to Vendor Detail, as listed in the Document Search results.', 'OVERRIDE_DOCUMENT_TITLE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'A', 'OLE-VND', 'OLE599', 'Ownership types that are allowed when the vendor tax id is of type "SSN".', 'SSN_OWNERSHIP_TYPES', 'VALID', 'ID;PT', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'A', 'OLE-VND', 'OLE600', 'Java Regular expressions that describe the acceptable formats for tax (FEIN) numbers which allows 9 digit numbers with last 4 digits not 0000 and the first 2 digits not 00.', 'TAX_FEIN_NUMBER_FORMATS', 'VALID', '(?!00)(\d{3})([ \-]?)(\d{2})([\-]?)(?!0000)(\d{4})', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'D', 'OLE-VND', 'OLE601', 'Tax IDs that are identified as not being allowable when creating a new vendor.', 'TAX_NUMBERS', 'VALID', '356001673;000000000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'VendorDetail', 'A', 'OLE-VND', 'OLE602', 'Java Regular expressions that describe the acceptable formats for tax (SSN) numbers which allows 9 digit numbers with last 4 digits not 0000 and the first 2 digits not 00.', 'TAX_SSN_NUMBER_FORMATS', 'VALID', '(?!000)(?!666)(\d{3})([ \-]?)(?!00)(\d{2})([\-]?)(?!0000)(\d{4});', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'KR-NS', 'OLE603', 'Flag for enabling/disabling direct inquiries on screens that are drawn by the nervous system (i.e. lookups and maintenance documents)', 'ENABLE_DIRECT_INQUIRIES_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'KR-NS', 'OLE604', 'Indicates whether field level help links are enabled on lookup pages and documents.', 'ENABLE_FIELD_LEVEL_HELP_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Account', 'A', 'OLE-COA', 'OLE606', 'Defines the cascading order for extracting the addresses', 'ACCOUNT_ADDRESS_TYPE', 'CONFG', 'ADMIN;PAYMENT;DEFAULT', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ObjectCode', 'A', 'OLE-COA', 'OLE607', 'Set this value to Y if you are using both Research Admin System and OLE and wish for KC budgets to use OLE Object Codes. This will cause the Research Admin Attributes tab to appear on the Object Code document. Set this value to N if you are not using Research Admin System and OLE or if you wish Research Admin System to maintain a distinct list of object codes.', 'ENABLE_RESEARCH_ADMIN_OBJECT_CODE_ATTRIBUTE_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'KR-WKFLW', 'OLE608', 'Flag for enabling/disabling document type permission checks to use KIM Permissions as priority over Document Type policies.', 'KIM_PRIORITY_ON_DOC_TYP_PERMS_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ElectronicInvoiceReject', 'A', 'OLE-PURAP', 'OLE609', 'Indicator related default document titles. Set to "N" if accepting the Kuali default document title, related to Contract Manager Assignment, as listed in the Document Search results.', 'OVERRIDE_DOCUMENT_TITLE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Account', 'A', 'OLE-COA', 'OLE610', 'This parameter defines the workflow action that will be taken on an account document automatically generated by an integrated research admin system. The possible parameter values are save submit and blanketApprove.', 'RESEARCH_ADMIN_AUTO_CREATE_ACCOUNT_WORKFLOW_ACTION', 'CONFG', 'save', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BudgetAdjustment', 'A', 'OLE-FP', 'OLE613', 'If OLE is integrated with a Research Admin System that has the ability to generate OLE budget adjustment documents this is the routing action that will take place on the generated document.', 'RESEARCH_ADMIN_BA_DOCUMENT_ROUTE_ACTION', 'CONFG', 'save', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CreditCardReceipt', 'D', 'OLE-FP', 'OLE669', 'Object Types restricted from use on the Credit Card Receipt document', 'OBJECT_TYPES', 'VALID', 'IC;ES', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CreditCardReceipt', 'D', 'OLE-FP', 'OLE670', 'Object Consolidations restricted from use on the Credit Card Receipt document', 'OBJECT_CONSOLIDATIONS', 'VALID', 'FDBL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AdvanceDeposit', 'D', 'OLE-FP', 'OLE671', 'Object Sub-Types restricted from use on the Advance Deposit document', 'OBJECT_SUB_TYPES', 'VALID', 'CA;BU;FB;PL;MT;CE;FR;HW;RE;SA;VA', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AdvanceDeposit', 'D', 'OLE-FP', 'OLE672', 'Object Types restricted from use on the Advance Deposit document', 'OBJECT_TYPES', 'VALID', 'ES;IC', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AdvanceDeposit', 'D', 'OLE-FP', 'OLE673', 'Object Consolidations restricted from use on the Advance Deposit document', 'OBJECT_CONSOLIDATIONS', 'VALID', 'FDBL', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'SalesTax', 'A', 'OLE-FP', 'OLE674', 'This determines the number of digits from the start that the Tax Service will use from the Postal Code to determine what Tax Regions to use For example, this is commonly set to 5 in the US, in order to ignore the dash and +4 digits in the ZIP+4 formatted postal codes This value can be set to blank to indicate to the system not to truncate the postal codes', 'POSTAL_CODE_DIGITS_PASSED_TO_SALES_TAX_REGION_SERVICE', 'CONFG', '5', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BatchUpload', 'A', 'OLE-SYS', 'OLE676', 'A Parameter to provide customizing the help URL for the Batch Upload help page', 'BATCH_UPLOAD_HELP_URL', 'HELP', 'customerLoadInputFileType=default.htm?turl=WordDocuments%2Fbatch.htm;procurementCardInputFileType=default.htm?turl=WordDocuments%2Fbatch.htm;collectorFlatFileInputFileType=default.htm?turl=WordDocuments%2Fbatch.htm;collectorXmlInputFileType=default.htm?turl=WordDocuments%2Fbatch.htm;enterpriseFeederFileSetType=default.htm?turl=WordDocuments%2Fbatch.htm;laborEnterpriseFeederFileSetType=default.htm?turl=WordDocuments%2Fbatch.htm;assetBarcodeInventoryInputFileType=default.htm?turl=WordDocuments%2Fbatch.htm;paymentInputFileType=default.htm?turl=WordDocuments%2Fbatch.htm', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ElectronicInvoiceReject', 'A', 'OLE-PURAP', 'OLE677', 'Specifies which types of EIRT reject reasons should be ignored when an EIRT document is Approved.', 'SUPPRESS_REJECT_REASON_CODES_ON_EIRT_APPROVAL', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE678', 'The URL used by the pdf generation process to find the images managed by Purchasing.', 'PDF_IMAGE_LOCATION_URL', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SEC', 'OLESEC1', 'List of documents that the security can be applied to. The document type can be at the parent level.', 'ACCESS_SECURITY_DOCUMENT_TYPES', 'CONFG', 'AD;AP;AV;BA;BT;CCR;CM;CR;DI;DV;FP;FPYE;GEC;IB;ICA;JV;LD;ND;PCDO;PE;PO;PREQ;REQS;SB;ST;TF', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SEC', 'OLESEC2', 'Enables access security checks in the system.', 'ENABLE_ACCESS_SECURITY', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SEC', 'OLESEC3', 'Allows the document initiator to always view the document and notes/attachments regardless of access permissions.', 'ALWAYS_ALLOW_INITIATOR_DOCUMENT_ACCESS_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SEC', 'OLESEC6', 'Allows the document initiator to always view the accounting line regardless of access permissions.', 'ALWAYS_ALLOW_INITIATOR_LINE_ACCESS_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SEC', 'OLESEC4', 'Allows fiscal officers to always view or edit accounting lines for their accounts regardless of access permissions.', 'ALWAYS_ALLOW_FISCAL_OFFICER_LINE_ACCESS_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SEC', 'OLESEC5', 'Allows principal investigators to always view or edit accounting lines for their accounts regardless of access permissions.', 'ALWAYS_ALLOW_PRINCIPAL_INVESTIGATOR_LINE_ACCESS_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLE624', 'The default Order Type code value that appears on the requisition document.', 'DEFAULT_ORDER_TYPE', 'CONFG', '1', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Account', 'A', 'OLE-COA', 'OLELD6987f', 'Defines the default benefit rate category code on the account maintenance document by account type. The format is Account Type=Benefit Rate Category Code (eg. DT=AA;A1=BB, etc).', 'DEFAULT_BENEFIT_RATE_CATEGORY_CODE_BY_ACCOUNT_TYPE', 'CONFG', 'EN=AA;CG=BB', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Account', 'A', 'OLE-COA', 'OLELD6987g', 'Defines the default benefit rate category code on the account maintenance document for all accounts. This default code is used when the  DEFAULT_BENEFIT_RATE_CATEGORY_CODE_BY_ACCOUNT_TYPE does not apply to an account. -- (two dashes) implies the category code is not applicable. The default cannot be null.', 'DEFAULT_BENEFIT_RATE_CATEGORY_CODE', 'CONFG', '--', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AddPriorYearAccountsStep', 'A', 'OLE-COA', 'OLE685', 'The list of accounts created after the start of a new fiscal year, but need to be used for the prior year and should be added to the Prior Year Account table by the batch job addPriorYearAccountsJob. If no such account exist, this parameter shall be left empty; otherwise, the value shall be in the format of "coaCode1-accountNumber1;coaCode2-accountNumber2;coaCode3-accountNumber3" and so on; for example, the value "BL-0142900;BA-1111111;UA-1000000" defines 3 accounts to be added. The value of this parameter shall be updated after each year end, before running addPriorYearAccountsJob.', 'PRIOR_YEAR_ACCOUNTS_TO_BE_ADDED', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'UpdatePriorYearDataStep', 'A', 'OLE-COA', 'OLEMI6944', 'The run date (in format MM/DD/YYYY ) to run the update prior year data job.', 'RUN_DATE', 'CONFG', '06/30/2011', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-COA', 'OLECNTRB65-6', 'Y/N indicator showing if the chart of account module is currently unavailable for Online Transaction Processing (OLTP). Y means the module is currently locked and cannot be used. N means the module is unlocked and available for use.', 'OLTP_LOCKOUT_ACTIVE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLEMI307_1', 'Email address which sends out the notification that a disbursement voucher was prepared for immediate extraction.', 'IMMEDIATE_EXTRACT_NOTIFICATION_FROM_EMAIL_ADDRESS', 'CONFG', 'knoreceipt-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'DisbursementVoucher', 'A', 'OLE-FP', 'OLEMI307_2', 'Email addresses which receive notification that a disbursement voucher was prepared for immediate extraction.', 'IMMEDIATE_EXTRACT_NOTIFICATION_TO_EMAIL_ADDRESSES', 'CONFG', 'knoreceipt-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCardCreateDocumentsStep', 'A', 'OLE-FP', 'OLEMI6285', 'If yes, accounting information is displayed / required on the Procurement Card Document Default maintenance document and accounting line information on the Procurement Card document is populated from Procurement Card Document Default table, if present.  If accounting information does not exist in the reference table for a given credit card, the ERROR_TRANSACTION* parameter values will be used.  If No, the accounting line information on the Procurement Card document is generated from the bank file.', 'PROCUREMENT_CARD_ACCOUNTING_DEFAULT_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ProcurementCardCreateDocumentsStep', 'A', 'OLE-FP', 'OLEMI6285_2', 'If yes, cardholder information is displayed / required on the Procurement Card Document Default maintenance document and cardholder information on the Procurement Card document is populated from Procurement Card Document Default table, if present.  If No, the cardholder information on the Procurement Card document is generated from the bank file.', 'PROCUREMENT_CARD_HOLDER_DEFAULT_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'AccountBalanceByConsolidation', 'A', 'OLE-GL', 'OLE690', 'The basic accounting category type which will represent expenses in Account Balance by Consolidation lookups.', 'BASIC_ACCOUNTING_CATEGORY_REPRESENTING_EXPENSES', 'CONFG', 'EX', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-GL', 'OLEMI6820_1', 'From email address used for messages sent by the system.', 'FROM_EMAIL_ADDRESS', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'CollectorStep', 'A', 'OLE-GL', 'OLEMI5918', 'This is the subject line used when the collector sends out email relating to a file that failed validation.', 'VALIDATION_ERROR_EMAIL_SUBJECT_LINE', 'CONFG', 'File Uploaded Not Processed', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-PDP', 'OLEMI6890', 'The URL of the file to download from the Federal ACH Bank Directory website. This file is then loaded into the ACH bank table.', 'FEDERAL_ACH_BANK_FILE_URL', 'CONFG', 'https://www.fededirectory.frb.org/FedACHdir.txt', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-PDP', 'OLEMI6820_3', 'From email address used for messages sent by the system.', 'FROM_EMAIL_ADDRESS', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'SendAchAdviceNotificationsStep', 'A', 'OLE-PDP', 'OLEMI5346', 'The pdpSendAchAdviceNotificationsJob generates ACH payment summary email notifications for payees and copies of these emails are sent to the email address(es) specified in this parameter.', 'ACH_SUMMARY_CC_EMAIL_ADDRESSES', 'CONFG', 'knoreceipt-l@indiana.edu', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-PURAP', 'OLEMI6820_4', 'From email address used for messages sent by the system.', 'FROM_EMAIL_ADDRESS', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE686', 'Display the functionality on a Requisition and Purchase Order document to trigger Positive Approval indicator. If this parameter is set to "N", the indicator will not be displayed on any document, and any functionality connected to the indicator will be turned off.', 'PAYMENT_REQUEST_POSITIVE_APPROVAL_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-PURAP', 'OLE687', 'Display the functionality on a Requisition and Purchase Order document to trigger Receiving Required Indicator. If this parameter is set to "N", the indicator will not be displayed on any document, and any functionality connected to the indicator will be turned off.', 'RECEIVING_DOCUMENT_REQUIRED_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'ElectronicInvoiceStep', 'A', 'OLE-PURAP', 'OLEMI6319', 'This parameter is to determine the campus for electronic invoice payments. If parameter value is populated, set the processing campus on the eInvoice generated PREQ to the parameter value. If parameter value is empty, use the Requisition initiator''''s campus code.', 'OVERRIDE_PROCESSING_CAMPUS', 'CONFG', '', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'LineItemReceiving', 'A', 'OLE-PURAP', 'OLEMI6342', 'Indicates whether a warning message shall be given when a user clicks "add unordered item" button. When set to Y the user will receive a warning.', 'UNORDERED_ITEM_WARNING_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLECNTRB162_3', 'This indicates whether a warning or an error should be displayed when a debarred vendor is selected in a purchase order. If Y then a warning is displayed. If N an error is displayed.', 'SHOW_DEBARRED_VENDOR_WARNING_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrderAmendment', 'A', 'OLE-PURAP', 'OLEPURAP7172', 'The default commodity code for unordered items. If commodity code is enabled and required on purchase order items, this value will be populated as the default commodity code for all unordered items when a Purchase Order Amendment Document is created from a Line Item Receiving Document.', 'UNORDERED_ITEM_DEFAULT_COMMODITY_CODE', 'CONFG', '99200000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Requisition', 'A', 'OLE-PURAP', 'OLECNTRB162_2', 'This indicates whether a warning or an error should be displayed when a debarred vendor is selected in a requisition. If Y then a warning is displayed. If N an error is displayed.', 'SHOW_DEBARRED_VENDOR_WARNING_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SYS', 'OLEMI4452', 'This parameter is to set the default message for the various documents during the lockout time.', 'DOCUMENT_LOCKOUT_DEFAULT_MESSAGE', 'CONFG', 'This document is currently locked out for running batch job.', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SYS', 'OLELD6987a', 'When this parameter is enabled, the fringe benefit rate calculation will be performed using the benefit category code assigned to the account and labor benefit calculation type. When the parameter is not enabled, the fringe benefit will be calculated solely based on the fringe benefit type.', 'ENABLE_FRINGE_BENEFIT_CALC_BY_BENEFIT_RATE_CATEGORY_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SYS', 'OLELD6987b', 'When this parameter is enabled, the fringe benefit rate calculation will be performed using the benefit category code assigned to the cost share source account and labor benefit calculation type for the cost share sub-account.  When the parameter is not enabled, the fringe benefit will be calculated solely based on benefit category code assigned to the grant account associated with the sub-account.', 'USE_COST_SHARE_SOURCE_ACCOUNT_BENEFIT_RATE_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-SYS', 'OLECNTRB672', 'The size limitation for batch file uploads in megabytes.', 'MAX_FILE_SIZE_UPLOAD', 'CONFG', '30M', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Batch', 'A', 'OLE-SYS', 'OLEMI7006', 'Time, in 24 hour time format (hh:mm:ss), after which it is considered to be the next day for the purposes of evaluating RUN_DATE parameters. For example: Today is 9/27/2011, RUN_DATE=9/27/2011, RUN_DATE_CUTOFF_TIME=2:00:00. Due to a slow running schedule a job triggered by RUN_DATE does not have its turn until 1:00 9/28/2011. The job will still run because the cutoff time is 2 hours after midnight. In this case, anything running after 2:00 is considered the next day.', 'RUN_DATE_CUTOFF_TIME', 'CONFG', '2:00:00', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'BatchContainerStep', 'A', 'OLE-SYS', 'OLEMI6315', 'Controls the frequency (in milliseconds) with which the batch container (BatchContainerStep) will check for semaphore step files to process. The semaphore files are created by the brte scripts via BatchStepTrigger.', 'SEMAPHORE_PROCESSING_INTERVAL', 'CONFG', '3000', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-SYS', 'OLESYS7145-01', 'Enables fiscal period field on financial documents for posting back to a previous period (year end processing).', 'ENABLE_FISCAL_PERIOD_SELECTION_IND', 'CONFG', 'Y', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-SYS', 'OLESYS7145-03', 'Document types for which the accounting period will be rendered for posting back to previous periods (year end processing). Use in conjunction with ENABLE_FISCAL_PERIOD_SELECTION_IND.', 'FISCAL_PERIOD_SELECTION_DOCUMENT_TYPES', 'CONFG', 'OLE_AD;OLE_AV;OLE_AVAD;OLE_AVAE;OLE_DI;OLE_DVCA;OLE_DVWF;OLE_GEC;OLE_IB;OLE_ICA;OLE_ND;OLE_SB;OLE_TF;OLE_PE;OLE_ARG;OLE_AA;OLE_AT;OLE_MPAY', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-SYS', 'OLESYS7145-02', 'Number of periods previous to the current period to allow for year end posting.', 'NUMBER_OF_POSTBACK_PERIODS', 'CONFG', '1', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Document', 'A', 'OLE-SYS', 'OLEMI7014', 'Determines whether functionality to update a financial document''s total amount during routing post-processing should be carried out.', 'UPDATE_TOTAL_AMOUNT_IN_POST_PROCESSING_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-SYS', 'OLECNTRB65-14', 'Y/N indicator showing if the system module is currently unavailable for Online Transaction Processing (OLTP). Y means the module is currently locked and cannot be used. N means the module is unlocked and available for use.', 'OLTP_LOCKOUT_ACTIVE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'Lookup', 'A', 'OLE-VND', 'OLECNTRB162', 'This parameter identifies the vendor types available as search criteria on the Vendor Exclusion and Debarred Unmatched Vendor lookups.', 'EXCLUSION_AND_DEBARRED_VENDOR_TYPES', 'CONFG', 'OLE_PO;OLE_DV', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'All', 'A', 'OLE-VND', 'OLECNTRB65-15', 'Y/N indicator showing if the vendor module is currently unavailable for Online Transaction Processing (OLTP). Y means the module is currently locked and cannot be used. N means the module is unlocked and available for use.', 'OLTP_LOCKOUT_ACTIVE_IND', 'CONFG', 'N', '1')
/

INSERT INTO `KRCR_PARM_T` (`APPL_ID`, `CMPNT_CD`, `EVAL_OPRTR_CD`, `NMSPC_CD`, `OBJ_ID`, `PARM_DESC_TXT`, `PARM_NM`, `PARM_TYP_CD`, `VAL`, `VER_NBR`) VALUES ('OLE', 'PurchaseOrder', 'A', 'OLE-PURAP', 'OLE-PURAP_PurchaseOrder_APORules', '', 'APORules', 'CONFG', 'REQ_GT_PO;REQ_GT_ZE;ITM_REST;VEN_NOT_SEL_DB;ERR_RTV_VEN_DB;VEN_REST;REQ_NO_CONT;REQ_PAY_RECUR;PO_TOT_NOT_EXC;ALT_VEN_NAM;REQ_ENCUM_FISCAL_YEAR;REQ_TRAD_IN;REQ_CPT_ASST_ITM;REQ_CON_ACC_LN', '1')
/

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('ole', '', NOW(), 'Delete Data, Custom SQL, Load Data', 'EXECUTED', '04_demo_krns_data.xml', 'OLE_DEMO_LOAD_KRCR_PARM_T_mysql', '2.0.5', '3:68a2d67f62393effa40130aedd7baa3b', 1)
/

--  Changeset 04_demo_krns_data.xml::OLE_DEMO_LOAD_KRLC_CMP_T::ole::(Checksum: 3:5e803181aa4e93fdcddf1ce29ea1b444)
DELETE FROM `KRLC_CMP_T`
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'BL', 'BLOOMINGTON', 'BLOOMINGTON', 'B', 'BL', '1')
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'BX', 'BLGTN OFF CAMPUS', 'BLGTN OFF CA', 'P', 'BX', '1')
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'CO', 'COLUMBUS', 'COLUMBUS', 'F', 'CO', '1')
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'EA', 'EAST-RICHMOND', 'EA-RICHMOND', 'B', 'EA', '1')
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'FW', 'FORT WAYNE', 'FORT WAYNE', 'B', 'FW', '1')
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'IN', 'INDIANAPOLIS', 'INDIANAPOLIS', 'B', 'IN', '1')
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'KO', 'KOKOMO', 'KOKOMO', 'B', 'KO', '1')
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'NW', 'NORTHWEST-GARY', 'NW-GARY', 'B', 'NW', '1')
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'OC', 'OFF CAMPUS', 'OFF CAMPUS', 'F', 'OC', '1')
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'SB', 'SOUTH BEND', 'SOUTH BEND', 'B', 'SB', '1')
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'SE', 'SOUTHEAST-NEW ALBANY', 'SE-NEW ALBNY', 'B', 'SE', '1')
/

INSERT INTO `KRLC_CMP_T` (`ACTV_IND`, `CAMPUS_CD`, `CAMPUS_NM`, `CAMPUS_SHRT_NM`, `CAMPUS_TYP_CD`, `OBJ_ID`, `VER_NBR`) VALUES ('Y', 'UA', 'UNIVERSITY ADMINISTRATION', 'UNIVER ADMIN', 'F', 'UA', '1')
/

INSERT INTO `DATABASECHANGELOG` (`AUTHOR`, `COMMENTS`, `DATEEXECUTED`, `DESCRIPTION`, `EXECTYPE`, `FILENAME`, `ID`, `LIQUIBASE`, `MD5SUM`, `ORDEREXECUTED`) VALUES ('ole', '', NOW(), 'Delete Data, Load Data', 'EXECUTED', '04_demo_krns_data.xml', 'OLE_DEMO_LOAD_KRLC_CMP_T', '2.0.5', '3:5e803181aa4e93fdcddf1ce29ea1b444', 2)
/

--  Release Database Lock
--  Release Database Lock
