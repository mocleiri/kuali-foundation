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

TRUNCATE TABLE FS_TAX_STATE_T
/
INSERT INTO FS_TAX_STATE_T (POSTAL_CNTRY_CD,STATE_CD,TAX_REGION_CD,OBJ_ID,VER_NBR,ACTV_IND)
  VALUES ('US','AZ','5','5ACF22CFC6134AB0E0404F8189D87AB9',1.0,'Y')
/
INSERT INTO FS_TAX_STATE_T (POSTAL_CNTRY_CD,STATE_CD,TAX_REGION_CD,OBJ_ID,VER_NBR,ACTV_IND)
  VALUES ('US','CA','2','5ACF22CFC6124AB0E0404F8189D87AB9',1.0,'Y')
/
