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

TRUNCATE TABLE OLE_CAT_SRC_TRM_T DROP STORAGE
/
INSERT INTO OLE_CAT_SRC_TRM_T (SRC_TRM_ID,SRC_TRM_CD,SRC_TRM_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('8','pda','Preservation and digitization actions: terminology for MARC 21 Field 583','http://www.loc.gov/standards/sourcelist/resource-action.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y','a4b4faa4-1b1c-4a0b-80bc-e2137f593e88',1.0)
/
INSERT INTO OLE_CAT_SRC_TRM_T (SRC_TRM_ID,SRC_TRM_CD,SRC_TRM_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('9','stmanf','Standard terminology for the MARC 21 Actions Note Field','http://www.loc.gov/standards/sourcelist/resource-action.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y','aafce01c-c970-4b59-90a0-8c1990f3a622',1.0)
/
