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

TRUNCATE TABLE PUR_CPTL_AST_SYS_ST_T DROP STORAGE
/
INSERT INTO PUR_CPTL_AST_SYS_ST_T (CPTL_AST_SYS_ST_CD,OBJ_ID,VER_NBR,CPTL_AST_SYS_ST_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('MOD','5431A26A0E3A9722E0404F8189D874DC',1.0,'Modify Existing System','Y')
/
INSERT INTO PUR_CPTL_AST_SYS_ST_T (CPTL_AST_SYS_ST_CD,OBJ_ID,VER_NBR,CPTL_AST_SYS_ST_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('NEW','5431A26A0E399722E0404F8189D874DC',1.0,'New System','Y')
/
