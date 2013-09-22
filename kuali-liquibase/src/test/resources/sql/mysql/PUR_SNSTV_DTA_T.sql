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

TRUNCATE TABLE PUR_SNSTV_DTA_T
/
INSERT INTO PUR_SNSTV_DTA_T (SNSTV_DTA_CD,OBJ_ID,VER_NBR,SNSTV_DTA_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('ANIM','636024D36C7A8EC6E0404F8189D80405',1.0,'Animal','Y')
/
INSERT INTO PUR_SNSTV_DTA_T (SNSTV_DTA_CD,OBJ_ID,VER_NBR,SNSTV_DTA_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('RADI','636024D36C7B8EC6E0404F8189D80405',1.0,'Radio Active Material','Y')
/
