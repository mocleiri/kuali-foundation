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

TRUNCATE TABLE OLE_CAT_ELA_RLSHP_T
/
INSERT INTO OLE_CAT_ELA_RLSHP_T (ELE_RLSHP_ID,ELE_RLSHP_CD,ELE_RLSHP_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (1.0,' ','No information provided','MFHD 856 2nd Indicator Values: http://www.loc.gov/marc/holdings/hd856.html','1000',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_ELA_RLSHP_T (ELE_RLSHP_ID,ELE_RLSHP_CD,ELE_RLSHP_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (2.0,'0','Resource ','MFHD 856 2nd Indicator Values: http://www.loc.gov/marc/holdings/hd856.html','1001',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_ELA_RLSHP_T (ELE_RLSHP_ID,ELE_RLSHP_CD,ELE_RLSHP_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (3.0,'1','Version of resource ','MFHD 856 2nd Indicator Values: http://www.loc.gov/marc/holdings/hd856.html','1002',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_ELA_RLSHP_T (ELE_RLSHP_ID,ELE_RLSHP_CD,ELE_RLSHP_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (4.0,'2','Related resource ','MFHD 856 2nd Indicator Values: http://www.loc.gov/marc/holdings/hd856.html','1003',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_ELA_RLSHP_T (ELE_RLSHP_ID,ELE_RLSHP_CD,ELE_RLSHP_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (5.0,'8','No display constant generated ','MFHD 856 2nd Indicator Values: http://www.loc.gov/marc/holdings/hd856.html','1004',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
