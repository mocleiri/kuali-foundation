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

TRUNCATE TABLE PUR_CPTL_AST_SYS_TYP_T
/
INSERT INTO PUR_CPTL_AST_SYS_TYP_T (CPTL_AST_SYS_TYP_CD,OBJ_ID,VER_NBR,CPTL_AST_SYS_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('IND','D1D060B7-6100-26B1-44C0-42505FA89117',1.0,'Individual Assets','Y')
/
INSERT INTO PUR_CPTL_AST_SYS_TYP_T (CPTL_AST_SYS_TYP_CD,OBJ_ID,VER_NBR,CPTL_AST_SYS_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('MUL','35F298D5-202F-8A7C-D32F-3C5E9B692315',1.0,'Multiple Systems','Y')
/
INSERT INTO PUR_CPTL_AST_SYS_TYP_T (CPTL_AST_SYS_TYP_CD,OBJ_ID,VER_NBR,CPTL_AST_SYS_TYP_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('ONE','0A6314AE-3E3A-47B9-7DAE-088C854C68CA',1.0,'One System','Y')
/
