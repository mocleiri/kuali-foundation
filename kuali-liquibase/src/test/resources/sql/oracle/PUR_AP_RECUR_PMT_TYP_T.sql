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

TRUNCATE TABLE PUR_AP_RECUR_PMT_TYP_T DROP STORAGE
/
INSERT INTO PUR_AP_RECUR_PMT_TYP_T (RECUR_PMT_TYP_CD,OBJ_ID,VER_NBR,RECUR_PMT_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('FIXD','2D6D987C-8141-912E-BC34-EB204477CB33',1.0,'FIXED SCHEDULE, FIXED AMOUNT','Y')
/
INSERT INTO PUR_AP_RECUR_PMT_TYP_T (RECUR_PMT_TYP_CD,OBJ_ID,VER_NBR,RECUR_PMT_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('FVAR','B06FD9B2-F852-8EEA-7A62-AD8DBC695D28',1.0,'FIXED SCHEDULE, VARIABLE AMOUNT','Y')
/
INSERT INTO PUR_AP_RECUR_PMT_TYP_T (RECUR_PMT_TYP_CD,OBJ_ID,VER_NBR,RECUR_PMT_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('VARV','08FD73AE-8160-2FE2-D9CF-7241A9649C4A',1.0,'VARIABLE SCHEDULE, VARIABLE AMOUNT','Y')
/
