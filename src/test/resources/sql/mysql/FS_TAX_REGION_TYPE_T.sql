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

TRUNCATE TABLE FS_TAX_REGION_TYPE_T
/
INSERT INTO FS_TAX_REGION_TYPE_T (TAX_REGION_TYP_CD,OBJ_ID,VER_NBR,TAX_REGION_TYP_NM)
  VALUES ('CNTY','5577381FC377FF1BE0404F8189D851DA',1.0,'COUNTY')
/
INSERT INTO FS_TAX_REGION_TYPE_T (TAX_REGION_TYP_CD,OBJ_ID,VER_NBR,TAX_REGION_TYP_NM)
  VALUES ('POST','5577381FC378FF1BE0404F8189D851DA',1.0,'POSTAL')
/
INSERT INTO FS_TAX_REGION_TYPE_T (TAX_REGION_TYP_CD,OBJ_ID,VER_NBR,TAX_REGION_TYP_NM)
  VALUES ('ST','5577381FC376FF1BE0404F8189D851DA',1.0,'STATE')
/
