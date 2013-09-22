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

TRUNCATE TABLE PDP_ACH_TRANS_CD_T DROP STORAGE
/
INSERT INTO PDP_ACH_TRANS_CD_T (TRANS_CD,OBJ_ID,VER_NBR,TRANS_DESC)
  VALUES ('22','582371A112D34DEDE0404F8189D83A6C',1.0,'Checking Deposit (Credit)')
/
INSERT INTO PDP_ACH_TRANS_CD_T (TRANS_CD,OBJ_ID,VER_NBR,TRANS_DESC)
  VALUES ('32','582371A112D44DEDE0404F8189D83A6C',1.0,'Share Deposit (Credit)')
/
