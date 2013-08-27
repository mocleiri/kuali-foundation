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

TRUNCATE TABLE PUR_SHP_PMT_TERM_T DROP STORAGE
/
INSERT INTO PUR_SHP_PMT_TERM_T (VNDR_SHP_PMT_TERM_CD,OBJ_ID,VER_NBR,VNDR_SHP_PMT_TERM_DESC,VNDR_SHP_PMT_TERM_PAY_IND,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('AL','39F17A7F-7D98-AC92-C7FB-C922D20475EF',1.0,'VENDOR PAYS ("ALLOWED")','Y','Y')
/
INSERT INTO PUR_SHP_PMT_TERM_T (VNDR_SHP_PMT_TERM_CD,OBJ_ID,VER_NBR,VNDR_SHP_PMT_TERM_DESC,VNDR_SHP_PMT_TERM_PAY_IND,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('CL','BA763DBD-4E1A-86C5-37AC-55EB72CD3ED2',2.0,'INST PAYS, SEPARATE BILL ("COLLECT")','Y','Y')
/
INSERT INTO PUR_SHP_PMT_TERM_T (VNDR_SHP_PMT_TERM_CD,OBJ_ID,VER_NBR,VNDR_SHP_PMT_TERM_DESC,VNDR_SHP_PMT_TERM_PAY_IND,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('PA','777F543C-C5CD-F29C-91CF-A30F4F41EAEC',2.0,'INST PAYS, PART OF PO ("PREPAID AND ADD")','Y','Y')
/
INSERT INTO PUR_SHP_PMT_TERM_T (VNDR_SHP_PMT_TERM_CD,OBJ_ID,VER_NBR,VNDR_SHP_PMT_TERM_DESC,VNDR_SHP_PMT_TERM_PAY_IND,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('PD','4FFB4620-ECB0-FEE0-A489-80F5E4684291',1.0,'PAID BY 3RD PARTY','Y','Y')
/
