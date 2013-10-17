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

TRUNCATE TABLE SH_UOM_T DROP STORAGE
/
INSERT INTO SH_UOM_T (ITM_UOM_CD,OBJ_ID,VER_NBR,ITM_UOM_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('CO','2',1.0,'COPY','Y')
/
INSERT INTO SH_UOM_T (ITM_UOM_CD,OBJ_ID,VER_NBR,ITM_UOM_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('EA','1',1.0,'EACH','Y')
/
INSERT INTO SH_UOM_T (ITM_UOM_CD,OBJ_ID,VER_NBR,ITM_UOM_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('YR','3',1.0,'YEAR','Y')
/
