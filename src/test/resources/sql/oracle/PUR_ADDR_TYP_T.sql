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

TRUNCATE TABLE PUR_ADDR_TYP_T DROP STORAGE
/
INSERT INTO PUR_ADDR_TYP_T (VNDR_ADDR_TYP_CD,OBJ_ID,VER_NBR,VNDR_ADDR_TYP_DESC,VNDR_DFLT_IND,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('PO','4312253E-2D1D-8E8C-67D1-5C17AF11E0DA',2.0,'PURCHASE ORDER','Y','Y')
/
INSERT INTO PUR_ADDR_TYP_T (VNDR_ADDR_TYP_CD,OBJ_ID,VER_NBR,VNDR_ADDR_TYP_DESC,VNDR_DFLT_IND,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('QT','85CE50E2-9AB8-41E6-5268-D0BEF6D4B43B',5.0,'QUOTE','Y','Y')
/
INSERT INTO PUR_ADDR_TYP_T (VNDR_ADDR_TYP_CD,OBJ_ID,VER_NBR,VNDR_ADDR_TYP_DESC,VNDR_DFLT_IND,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('RM','41FC8A59-00CF-8376-8E87-AFCC7CB0D93A',3.0,'REMIT','Y','Y')
/
INSERT INTO PUR_ADDR_TYP_T (VNDR_ADDR_TYP_CD,OBJ_ID,VER_NBR,VNDR_ADDR_TYP_DESC,VNDR_DFLT_IND,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('RT','2AC59814-9872-2523-2B6C-69234E48AFE1',2.0,'RETURN GOODS','N','Y')
/
INSERT INTO PUR_ADDR_TYP_T (VNDR_ADDR_TYP_CD,OBJ_ID,VER_NBR,VNDR_ADDR_TYP_DESC,VNDR_DFLT_IND,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('TX','0343CC39-00A2-0040-01B8-C6E1E2B4ADC9',2.0,'TAX','N','Y')
/
