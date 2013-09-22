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

TRUNCATE TABLE PUR_SHP_TTL_T
/
INSERT INTO PUR_SHP_TTL_T (VNDR_SHP_TTL_CD,OBJ_ID,VER_NBR,VNDR_SHP_TTL_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('CI','BD3A73C7-BA3E-A68C-8C3D-B2BAE3304C7B',1.0,'CUSTOMS-INTERNATIONAL PORT','Y')
/
INSERT INTO PUR_SHP_TTL_T (VNDR_SHP_TTL_CD,OBJ_ID,VER_NBR,VNDR_SHP_TTL_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('CU','2DD981DD-F80F-F5B4-C92C-39483A7394EE',1.0,'CUSTOMS-U.S. PORT','Y')
/
INSERT INTO PUR_SHP_TTL_T (VNDR_SHP_TTL_CD,OBJ_ID,VER_NBR,VNDR_SHP_TTL_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('DE','28767BE3-1708-2708-B113-99914AE68BF8',1.0,'DESTINATION','Y')
/
INSERT INTO PUR_SHP_TTL_T (VNDR_SHP_TTL_CD,OBJ_ID,VER_NBR,VNDR_SHP_TTL_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('OR','FC3AFA33-7C57-1A98-F3B2-39E67F0C2E01',1.0,'ORIGIN (VENDOR LOCATION)','Y')
/
INSERT INTO PUR_SHP_TTL_T (VNDR_SHP_TTL_CD,OBJ_ID,VER_NBR,VNDR_SHP_TTL_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('SP','BD314A70-DCE1-61CA-7B3A-6D1A340A95DE',1.0,'SHIPPING POINT (DISTRIBUTOR)','Y')
/
