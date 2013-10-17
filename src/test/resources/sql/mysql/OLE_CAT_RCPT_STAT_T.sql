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

TRUNCATE TABLE OLE_CAT_RCPT_STAT_T
/
INSERT INTO OLE_CAT_RCPT_STAT_T (RCPT_STAT_ID,RCPT_STAT_CD,RCPT_STAT_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('1','0','Unknown','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','c060dfc4-7afd-4494-94e2-efebc0f09ad6',1.0)
/
INSERT INTO OLE_CAT_RCPT_STAT_T (RCPT_STAT_ID,RCPT_STAT_CD,RCPT_STAT_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('2','1','Other receipt or acquisition status','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','01ea3439-4a85-4acf-8745-4bbcb0757da4',1.0)
/
INSERT INTO OLE_CAT_RCPT_STAT_T (RCPT_STAT_ID,RCPT_STAT_CD,RCPT_STAT_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('3','2','Received and complete or ceased','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','135704f7-822b-4d93-afbc-371d1ab4d7d2',1.0)
/
INSERT INTO OLE_CAT_RCPT_STAT_T (RCPT_STAT_ID,RCPT_STAT_CD,RCPT_STAT_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('4','3','On order','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','e91ac912-a4be-435c-b294-514a5383559d',1.0)
/
INSERT INTO OLE_CAT_RCPT_STAT_T (RCPT_STAT_ID,RCPT_STAT_CD,RCPT_STAT_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('5','4','Currently received','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','1a188ac9-df55-45af-b956-90e4ac8b8b9c',1.0)
/
INSERT INTO OLE_CAT_RCPT_STAT_T (RCPT_STAT_ID,RCPT_STAT_CD,RCPT_STAT_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('6','5','Not currently received','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','1be8e6f4-84fa-4a77-ae9a-bd212fbb3ca3',1.0)
/
