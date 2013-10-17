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

TRUNCATE TABLE OLE_CAT_PRVCY_T
/
INSERT INTO OLE_CAT_PRVCY_T (PRVCY_ID,PRVCY_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('1','No information provided','MFHD 583 1st Indicator: http://www.loc.gov/marc/holdings/hd583.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','d80bd602-7c9d-473a-9929-1919d5af3e6a',1.0)
/
INSERT INTO OLE_CAT_PRVCY_T (PRVCY_ID,PRVCY_CD,PRVCY_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('2','0','Private','MFHD 583 1st Indicator: http://www.loc.gov/marc/holdings/hd583.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','ab93e07b-9b0d-4fde-8429-3e7149ae280b',1.0)
/
INSERT INTO OLE_CAT_PRVCY_T (PRVCY_ID,PRVCY_CD,PRVCY_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('3','1','Not private','MFHD 583 1st Indicator: http://www.loc.gov/marc/holdings/hd583.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','cbaf223f-2bff-4525-a68d-6e2b31f452ac',1.0)
/
