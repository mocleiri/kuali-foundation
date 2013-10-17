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

TRUNCATE TABLE OLE_CAT_ACTION_T DROP STORAGE
/
INSERT INTO OLE_CAT_ACTION_T (ACTION_ID,OBJ_ID,VER_NBR,ACTION_CD,ACTION_NM,ACTION_DESC,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (1.0,'8598b141-cdfe-41bf-ba77-6eaeb344e8b4',3.0,'condition reviewed','condition reviewed','Preservation','Maps to MFHD 583 $a Action Standardized terminology descriptive of the action',TO_DATE( '20120517000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_ACTION_T (ACTION_ID,OBJ_ID,VER_NBR,ACTION_CD,ACTION_NM,ACTION_DESC,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (2.0,'49a2801c-8ecc-4897-8ee9-9ceb11814911',3.0,'queued for preservation','queued for preservation','Preservation','Maps to MFHD 583 $a Action Standardized terminology descriptive of the action',TO_DATE( '20120517000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_ACTION_T (ACTION_ID,OBJ_ID,VER_NBR,ACTION_CD,ACTION_NM,ACTION_DESC,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (3.0,'9e4bdbb3-7de3-47f9-b4d7-7e8eaa815aba',3.0,'preservation interim treatment','preservation interim treatment','Preservation','Maps to MFHD 583 $a Action Standardized terminology descriptive of the action',TO_DATE( '20120517000000', 'YYYYMMDDHH24MISS' ),'Y')
/
