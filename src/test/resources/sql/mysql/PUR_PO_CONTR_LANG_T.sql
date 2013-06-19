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

TRUNCATE TABLE PUR_PO_CONTR_LANG_T
/
INSERT INTO PUR_PO_CONTR_LANG_T (PO_CONTR_LANG_ID,OBJ_ID,VER_NBR,CMP_CD,PO_CONTR_LANG_DESC,CONTR_LANG_CRTE_DT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1000.0,'F517E488-1C72-0C15-C109-15B3F6C1457B',1.0,'IX','UCSB Net 30 Test to see if duplicate error message is given',STR_TO_DATE( '20070508000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO PUR_PO_CONTR_LANG_T (PO_CONTR_LANG_ID,OBJ_ID,VER_NBR,CMP_CD,PO_CONTR_LANG_DESC,CONTR_LANG_CRTE_DT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1001.0,'BBF01331-0AD3-DED6-62AA-0E0626B377B5',1.0,'KO','2nd test for duplicate error message',STR_TO_DATE( '20070508000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO PUR_PO_CONTR_LANG_T (PO_CONTR_LANG_ID,OBJ_ID,VER_NBR,CMP_CD,PO_CONTR_LANG_DESC,CONTR_LANG_CRTE_DT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1002.0,'43FE30A3-023B-6E11-A076-3CF8B406D498',2.0,'IX','This PO is serious business, no laughing when fillng the order.',STR_TO_DATE( '20070508000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO PUR_PO_CONTR_LANG_T (PO_CONTR_LANG_ID,OBJ_ID,VER_NBR,CMP_CD,PO_CONTR_LANG_DESC,CONTR_LANG_CRTE_DT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1003.0,'1C21FFD7-35F7-EFAB-E2FA-B936E4C68324',1.0,'BL','Attention IUPUI Suppliers: Please be advised of the new IUPUI Tobacco Policy. Effective August 14, 2006 Tobacco use or sale, including, but not limited to smoking, is prohibited on university-owned, -operated, -or leased property. This policy applies to anyone on campus. Inclusive are Patients, visitors, faculty, staff, volunteers, physicians, students, contract/other employees, contractors/suppliers/vendors and service representatives. For further information we refer you to: http://www.iupui.edu/~nosmoke/.',STR_TO_DATE( '20070508000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO PUR_PO_CONTR_LANG_T (PO_CONTR_LANG_ID,OBJ_ID,VER_NBR,CMP_CD,PO_CONTR_LANG_DESC,CONTR_LANG_CRTE_DT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1004.0,'35B62310-EAD3-6C39-8F43-DBD5BA02F3BF',1.0,'BX','University policy prohibits discriminatory practices in regard to race, color, religion, sex, national origin, age, disability, sexual orientation, marital status or veteran status.',STR_TO_DATE( '20070508000000', '%Y%m%d%H%i%s' ),'N')
/
INSERT INTO PUR_PO_CONTR_LANG_T (PO_CONTR_LANG_ID,OBJ_ID,VER_NBR,CMP_CD,PO_CONTR_LANG_DESC,CONTR_LANG_CRTE_DT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1005.0,'9C7FB55A-CAD3-A1EC-0E12-B17E1CC1238E',1.0,'BL','Invoices must be sent showing purchase order number to someone who has money to pay for the purchase.',STR_TO_DATE( '20070508000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO PUR_PO_CONTR_LANG_T (PO_CONTR_LANG_ID,OBJ_ID,VER_NBR,CMP_CD,PO_CONTR_LANG_DESC,CONTR_LANG_CRTE_DT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1006.0,'F95E9396-0218-0E35-3EBC-7E57984F4BBB',1.0,'BL','All deliveries are to be made to the address and room number as listed above.',STR_TO_DATE( '20070508000000', '%Y%m%d%H%i%s' ),'Y')
/
