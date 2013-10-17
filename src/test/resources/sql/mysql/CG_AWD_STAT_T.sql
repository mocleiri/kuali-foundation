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

TRUNCATE TABLE CG_AWD_STAT_T
/
INSERT INTO CG_AWD_STAT_T (CGAWD_STAT_CD,OBJ_ID,VER_NBR,CGAWD_STAT_DESC,ROW_ACTV_IND)
  VALUES ('A','12D4ED2159F6EF34E043814FD881EF34',1.0,'Award','Y')
/
INSERT INTO CG_AWD_STAT_T (CGAWD_STAT_CD,OBJ_ID,VER_NBR,CGAWD_STAT_DESC,ROW_ACTV_IND)
  VALUES ('R','12D4ED2159F7EF34E043814FD881EF34',1.0,'Rejected','Y')
/
INSERT INTO CG_AWD_STAT_T (CGAWD_STAT_CD,OBJ_ID,VER_NBR,CGAWD_STAT_DESC,ROW_ACTV_IND)
  VALUES ('U','12D4ED2159F8EF34E043814FD881EF34',1.0,'Underwrite','Y')
/
INSERT INTO CG_AWD_STAT_T (CGAWD_STAT_CD,OBJ_ID,VER_NBR,CGAWD_STAT_DESC,ROW_ACTV_IND)
  VALUES ('W','12D4ED2159F9EF34E043814FD881EF34',1.0,'Withdrawn','Y')
/
