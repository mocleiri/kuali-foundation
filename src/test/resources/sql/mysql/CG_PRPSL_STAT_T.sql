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

TRUNCATE TABLE CG_PRPSL_STAT_T
/
INSERT INTO CG_PRPSL_STAT_T (CGPRPSL_STAT_CD,OBJ_ID,VER_NBR,CGPRPSL_STAT_DESC,ROW_ACTV_IND)
  VALUES ('A','12BF192103039D6CE043814FD8819D6C',1.0,'Award','Y')
/
INSERT INTO CG_PRPSL_STAT_T (CGPRPSL_STAT_CD,OBJ_ID,VER_NBR,CGPRPSL_STAT_DESC,ROW_ACTV_IND)
  VALUES ('P','12BF192103049D6CE043814FD8819D6C',1.0,'Proposal','Y')
/
INSERT INTO CG_PRPSL_STAT_T (CGPRPSL_STAT_CD,OBJ_ID,VER_NBR,CGPRPSL_STAT_DESC,ROW_ACTV_IND)
  VALUES ('R','12BF192103059D6CE043814FD8819D6C',1.0,'Rejected','Y')
/
INSERT INTO CG_PRPSL_STAT_T (CGPRPSL_STAT_CD,OBJ_ID,VER_NBR,CGPRPSL_STAT_DESC,ROW_ACTV_IND)
  VALUES ('U','12BF192103069D6CE043814FD8819D6C',1.0,'Underwrite','Y')
/
INSERT INTO CG_PRPSL_STAT_T (CGPRPSL_STAT_CD,OBJ_ID,VER_NBR,CGPRPSL_STAT_DESC,ROW_ACTV_IND)
  VALUES ('W','12BF192103079D6CE043814FD8819D6C',1.0,'Withdrawn','Y')
/
