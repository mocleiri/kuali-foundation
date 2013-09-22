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

TRUNCATE TABLE OLE_CAT_ENCD_LVL_T
/
INSERT INTO OLE_CAT_ENCD_LVL_T (ENCD_LVL_ID,ENCD_LVL_CD,ENCD_LVL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (1.0,'1','Holdings level 1 ','MFHD Leader 17 Encoding Level: http://www.loc.gov/marc/holdings/hdleader.html','1000',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_ENCD_LVL_T (ENCD_LVL_ID,ENCD_LVL_CD,ENCD_LVL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (2.0,'2','Holdings level 2 ','MFHD Leader 17 Encoding Level: http://www.loc.gov/marc/holdings/hdleader.html','1001',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_ENCD_LVL_T (ENCD_LVL_ID,ENCD_LVL_CD,ENCD_LVL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (3.0,'3','Holdings level 3 ','MFHD Leader 17 Encoding Level: http://www.loc.gov/marc/holdings/hdleader.html','1002',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_ENCD_LVL_T (ENCD_LVL_ID,ENCD_LVL_CD,ENCD_LVL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (4.0,'4','Holdings level 4 ','MFHD Leader 17 Encoding Level: http://www.loc.gov/marc/holdings/hdleader.html','1003',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_ENCD_LVL_T (ENCD_LVL_ID,ENCD_LVL_CD,ENCD_LVL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (5.0,'5','Holdings level 4 with piece designation ','MFHD Leader 17 Encoding Level: http://www.loc.gov/marc/holdings/hdleader.html','1004',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_ENCD_LVL_T (ENCD_LVL_ID,ENCD_LVL_CD,ENCD_LVL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (6.0,'m','Mixed level ','MFHD Leader 17 Encoding Level: http://www.loc.gov/marc/holdings/hdleader.html','1006',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_ENCD_LVL_T (ENCD_LVL_ID,ENCD_LVL_CD,ENCD_LVL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (7.0,'u','Unknown ','MFHD Leader 17 Encoding Level: http://www.loc.gov/marc/holdings/hdleader.html','1007',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_ENCD_LVL_T (ENCD_LVL_ID,ENCD_LVL_CD,ENCD_LVL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (8.0,'z','Other level ','MFHD Leader 17 Encoding Level: http://www.loc.gov/marc/holdings/hdleader.html','1008',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
