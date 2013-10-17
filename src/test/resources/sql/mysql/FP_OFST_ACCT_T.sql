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

TRUNCATE TABLE FP_OFST_ACCT_T
/
INSERT INTO FP_OFST_ACCT_T (FIN_COA_CD,ACCOUNT_NBR,FIN_OFST_OBJ_CD,OBJ_ID,VER_NBR,FIN_OFST_COA_CD,FIN_OFST_ACCT_NBR,ROW_ACTV_IND)
  VALUES ('BL','2231401','8000','0F0F5562BCA679F4E043814FD88179F4',1.0,'UA','1912201','Y')
/
