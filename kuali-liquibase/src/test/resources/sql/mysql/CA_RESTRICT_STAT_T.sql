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

TRUNCATE TABLE CA_RESTRICT_STAT_T
/
INSERT INTO CA_RESTRICT_STAT_T (ACCT_RSTRC_STAT_CD,OBJ_ID,VER_NBR,ACCT_RSTRC_STAT_NM,ROW_ACTV_IND)
  VALUES ('N','014F3DAF8209A448E043814FD28EA448',1.0,'NOT APPLICABLE','Y')
/
INSERT INTO CA_RESTRICT_STAT_T (ACCT_RSTRC_STAT_CD,OBJ_ID,VER_NBR,ACCT_RSTRC_STAT_NM,ROW_ACTV_IND)
  VALUES ('R','014F3DAF820AA448E043814FD28EA448',1.0,'RESTRICTED','Y')
/
INSERT INTO CA_RESTRICT_STAT_T (ACCT_RSTRC_STAT_CD,OBJ_ID,VER_NBR,ACCT_RSTRC_STAT_NM,ROW_ACTV_IND)
  VALUES ('T','014F3DAF820BA448E043814FD28EA448',1.0,'TEMPORARLIY RESTRICTED','Y')
/
INSERT INTO CA_RESTRICT_STAT_T (ACCT_RSTRC_STAT_CD,OBJ_ID,VER_NBR,ACCT_RSTRC_STAT_NM,ROW_ACTV_IND)
  VALUES ('U','014F3DAF820CA448E043814FD28EA448',1.0,'UNRESTRICTED','Y')
/
