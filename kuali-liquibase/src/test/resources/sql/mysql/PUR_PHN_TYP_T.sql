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

TRUNCATE TABLE PUR_PHN_TYP_T
/
INSERT INTO PUR_PHN_TYP_T (VNDR_PHN_TYP_CD,OBJ_ID,VER_NBR,VNDR_PHN_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('FX','186D917D-3E28-4A55-ACCA-806A0DF12D76',1.0,'FAX','Y')
/
INSERT INTO PUR_PHN_TYP_T (VNDR_PHN_TYP_CD,OBJ_ID,VER_NBR,VNDR_PHN_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('MB','89481C07-8666-B6BA-8FD5-A8D24EB78851',1.0,'MOBILE','Y')
/
INSERT INTO PUR_PHN_TYP_T (VNDR_PHN_TYP_CD,OBJ_ID,VER_NBR,VNDR_PHN_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('PG','8115E355-677C-A427-64BD-B70582243606',1.0,'PAGER','Y')
/
INSERT INTO PUR_PHN_TYP_T (VNDR_PHN_TYP_CD,OBJ_ID,VER_NBR,VNDR_PHN_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('PH','F7BF0ABD-608E-BF70-8A40-469DF73BAEA0',1.0,'PHONE NUMBER','Y')
/
INSERT INTO PUR_PHN_TYP_T (VNDR_PHN_TYP_CD,OBJ_ID,VER_NBR,VNDR_PHN_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('PO','ADF65BDC-6630-7576-61BD-34D97AC9BEDB',2.0,'PURCHASE ORDER','Y')
/
INSERT INTO PUR_PHN_TYP_T (VNDR_PHN_TYP_CD,OBJ_ID,VER_NBR,VNDR_PHN_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('TF','8C48A5BE-0312-01EB-5323-A1417A1555E1',1.0,'TOLL FREE','Y')
/
