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

TRUNCATE TABLE PDP_ACCTG_CHG_CD_T DROP STORAGE
/
INSERT INTO PDP_ACCTG_CHG_CD_T (ACCTG_CHG_CD,OBJ_ID,VER_NBR,ACCTG_CHG_CD_DESC)
  VALUES ('ACCT','3C4DCACBEAB230FEE043814FD88130FE',0.0,'Invalid Account Number')
/
INSERT INTO PDP_ACCTG_CHG_CD_T (ACCTG_CHG_CD,OBJ_ID,VER_NBR,ACCTG_CHG_CD_DESC)
  VALUES ('OBJ','3C4DCACBEAB130FEE043814FD88130FE',0.0,'Invalid Object Code')
/
INSERT INTO PDP_ACCTG_CHG_CD_T (ACCTG_CHG_CD,OBJ_ID,VER_NBR,ACCTG_CHG_CD_DESC)
  VALUES ('PROJ','6E38252C8BF2965DE0404F8189D83B69',0.0,'Invalid Project Code')
/
INSERT INTO PDP_ACCTG_CHG_CD_T (ACCTG_CHG_CD,OBJ_ID,VER_NBR,ACCTG_CHG_CD_DESC)
  VALUES ('SA','3C4DCACBEAAF30FEE043814FD88130FE',0.0,'Invalid Sub Account')
/
INSERT INTO PDP_ACCTG_CHG_CD_T (ACCTG_CHG_CD,OBJ_ID,VER_NBR,ACCTG_CHG_CD_DESC)
  VALUES ('SO','3C4DCACBEAB030FEE043814FD88130FE',0.0,'Invalid Sub Object Code')
/
