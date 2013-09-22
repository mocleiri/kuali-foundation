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

TRUNCATE TABLE CG_PRPSL_AWD_TYP_T
/
INSERT INTO CG_PRPSL_AWD_TYP_T (CGPRPSL_AWD_TYP_CD,OBJ_ID,VER_NBR,PRPSL_AWD_TYP_DESC,ROW_ACTV_IND)
  VALUES ('C','12BF192103119D6CE043814FD8819D6C',1.0,'Non-Competing/Contin','Y')
/
INSERT INTO CG_PRPSL_AWD_TYP_T (CGPRPSL_AWD_TYP_CD,OBJ_ID,VER_NBR,PRPSL_AWD_TYP_DESC,ROW_ACTV_IND)
  VALUES ('E','12BF192103129D6CE043814FD8819D6C',1.0,'Supplement','Y')
/
INSERT INTO CG_PRPSL_AWD_TYP_T (CGPRPSL_AWD_TYP_CD,OBJ_ID,VER_NBR,PRPSL_AWD_TYP_DESC,ROW_ACTV_IND)
  VALUES ('N','12BF192103139D6CE043814FD8819D6C',1.0,'New','Y')
/
INSERT INTO CG_PRPSL_AWD_TYP_T (CGPRPSL_AWD_TYP_CD,OBJ_ID,VER_NBR,PRPSL_AWD_TYP_DESC,ROW_ACTV_IND)
  VALUES ('R','12BF192103149D6CE043814FD8819D6C',1.0,'Contin/Competing','Y')
/
