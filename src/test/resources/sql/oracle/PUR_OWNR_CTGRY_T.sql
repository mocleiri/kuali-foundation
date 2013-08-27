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

TRUNCATE TABLE PUR_OWNR_CTGRY_T DROP STORAGE
/
INSERT INTO PUR_OWNR_CTGRY_T (VNDR_OWNR_CTGRY_CD,OBJ_ID,VER_NBR,VNDR_OWNR_CTGRY_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('LE','6D430FCD-E216-8D45-29CC-1F96A80BA843',1.0,'LEGAL SERVICES','Y')
/
INSERT INTO PUR_OWNR_CTGRY_T (VNDR_OWNR_CTGRY_CD,OBJ_ID,VER_NBR,VNDR_OWNR_CTGRY_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('ME','9C0B3059-5FB5-E76D-40F9-5B79A72C952E',3.0,'HEALTH CARE SERVICES','Y')
/
