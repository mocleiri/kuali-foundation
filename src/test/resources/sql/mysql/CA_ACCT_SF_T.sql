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

TRUNCATE TABLE CA_ACCT_SF_T
/
INSERT INTO CA_ACCT_SF_T (ACCT_SF_CD,OBJ_ID,VER_NBR,ACCT_SF_NM,ROW_ACTV_IND)
  VALUES ('A','3C4DCACBEA9830FEE043814FD88130FE',1.0,'Account','Y')
/
INSERT INTO CA_ACCT_SF_T (ACCT_SF_CD,OBJ_ID,VER_NBR,ACCT_SF_NM,ROW_ACTV_IND)
  VALUES ('C','3C4DCACBEA9930FEE043814FD88130FE',1.0,'Consolidation','Y')
/
INSERT INTO CA_ACCT_SF_T (ACCT_SF_CD,OBJ_ID,VER_NBR,ACCT_SF_NM,ROW_ACTV_IND)
  VALUES ('H','3C4DCACBEA9A30FEE043814FD88130FE',1.0,'Cash','Y')
/
INSERT INTO CA_ACCT_SF_T (ACCT_SF_CD,OBJ_ID,VER_NBR,ACCT_SF_NM,ROW_ACTV_IND)
  VALUES ('L','3C4DCACBEA9B30FEE043814FD88130FE',1.0,'Level','Y')
/
INSERT INTO CA_ACCT_SF_T (ACCT_SF_CD,OBJ_ID,VER_NBR,ACCT_SF_NM,ROW_ACTV_IND)
  VALUES ('N','3C4DCACBEA9C30FEE043814FD88130FE',1.0,'No Checking','Y')
/
INSERT INTO CA_ACCT_SF_T (ACCT_SF_CD,OBJ_ID,VER_NBR,ACCT_SF_NM,ROW_ACTV_IND)
  VALUES ('O','3C4DCACBEA9D30FEE043814FD88130FE',1.0,'Object Code','Y')
/
