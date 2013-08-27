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

TRUNCATE TABLE FP_FUNC_CTRL_CD_T DROP STORAGE
/
INSERT INTO FP_FUNC_CTRL_CD_T (FS_FUNC_CTRL_CD,OBJ_ID,VER_NBR,FS_FUNC_DFLT_IND,FS_FUNC_DESC)
  VALUES ('BAACTV','1362A7CFD659B972E043814FD881B972',1.0,'N','Budget Adjustements Allowed')
/
INSERT INTO FP_FUNC_CTRL_CD_T (FS_FUNC_CTRL_CD,OBJ_ID,VER_NBR,FS_FUNC_DFLT_IND,FS_FUNC_DESC)
  VALUES ('BASEAD','1362A7CFD65AB972E043814FD881B972',1.0,'N','Base Adjustments Allowed')
/
INSERT INTO FP_FUNC_CTRL_CD_T (FS_FUNC_CTRL_CD,OBJ_ID,VER_NBR,FS_FUNC_DFLT_IND,FS_FUNC_DESC)
  VALUES ('BCACTV','1362A7CFD65BB972E043814FD881B972',1.0,'N','Budget Construction Active')
/
INSERT INTO FP_FUNC_CTRL_CD_T (FS_FUNC_CTRL_CD,OBJ_ID,VER_NBR,FS_FUNC_DFLT_IND,FS_FUNC_DESC)
  VALUES ('BCGENE','1362A7CFD65CB972E043814FD881B972',1.0,'N','Budget Construction Genesis Executing')
/
INSERT INTO FP_FUNC_CTRL_CD_T (FS_FUNC_CTRL_CD,OBJ_ID,VER_NBR,FS_FUNC_DFLT_IND,FS_FUNC_DESC)
  VALUES ('BCUPDT','1362A7CFD65EB972E043814FD881B972',1.0,'N','Budget Construction Updates Allowed')
/
INSERT INTO FP_FUNC_CTRL_CD_T (FS_FUNC_CTRL_CD,OBJ_ID,VER_NBR,FS_FUNC_DFLT_IND,FS_FUNC_DESC)
  VALUES ('BSSYNC','1362A7CFD65DB972E043814FD881B972',1.0,'N','Batch Sync BC with PeopleSoft')
/
INSERT INTO FP_FUNC_CTRL_CD_T (FS_FUNC_CTRL_CD,OBJ_ID,VER_NBR,FS_FUNC_DFLT_IND,FS_FUNC_DESC)
  VALUES ('CSFUPD','1362A7CFD65FB972E043814FD881B972',1.0,'N','Calculated Salary Found Updates Allowed')
/
INSERT INTO FP_FUNC_CTRL_CD_T (FS_FUNC_CTRL_CD,OBJ_ID,VER_NBR,FS_FUNC_DFLT_IND,FS_FUNC_DESC)
  VALUES ('PSSYNC','1362A7CFD660B972E043814FD881B972',1.0,'N','Synchronize BC with PeopleSoft')
/
