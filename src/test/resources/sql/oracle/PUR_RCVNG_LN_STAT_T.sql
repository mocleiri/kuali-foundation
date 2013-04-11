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

TRUNCATE TABLE PUR_RCVNG_LN_STAT_T DROP STORAGE
/
INSERT INTO PUR_RCVNG_LN_STAT_T (RCVNG_LN_STAT_CD,OBJ_ID,VER_NBR,RCVNG_LN_STAT_DESC)
  VALUES ('APOO','60A6B6F3F0FBFF46E0404F8189D87ACD',1.0,'Awaiting Purchase Order Open Status')
/
INSERT INTO PUR_RCVNG_LN_STAT_T (RCVNG_LN_STAT_CD,OBJ_ID,VER_NBR,RCVNG_LN_STAT_DESC)
  VALUES ('CANC','6224502233A93004E0404F8189D8236B',1.0,'Cancelled')
/
INSERT INTO PUR_RCVNG_LN_STAT_T (RCVNG_LN_STAT_CD,OBJ_ID,VER_NBR,RCVNG_LN_STAT_DESC)
  VALUES ('CMPT','60A6B6F3F0FCFF46E0404F8189D87ACD',1.0,'Complete')
/
INSERT INTO PUR_RCVNG_LN_STAT_T (RCVNG_LN_STAT_CD,OBJ_ID,VER_NBR,RCVNG_LN_STAT_DESC)
  VALUES ('INPR','60A6B6F3F0FAFF46E0404F8189D87ACD',1.0,'In Process')
/
