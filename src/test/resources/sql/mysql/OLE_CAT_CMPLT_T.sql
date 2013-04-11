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

TRUNCATE TABLE OLE_CAT_CMPLT_T
/
INSERT INTO OLE_CAT_CMPLT_T (CMPLT_ID,CMPLT_CD,CMPLT_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (1.0,'0','Other','MFHD 008-16 http://www.loc.gov/marc/holdings/hd008.html','1000',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_CMPLT_T (CMPLT_ID,CMPLT_CD,CMPLT_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (2.0,'1','Complete','MFHD 008-16 http://www.loc.gov/marc/holdings/hd008.html','1001',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_CMPLT_T (CMPLT_ID,CMPLT_CD,CMPLT_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (3.0,'2','Incomplete','MFHD 008-16 http://www.loc.gov/marc/holdings/hd008.html','1002',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_CMPLT_T (CMPLT_ID,CMPLT_CD,CMPLT_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (4.0,'3','Scattered','MFHD 008-16 http://www.loc.gov/marc/holdings/hd008.html','1003',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO OLE_CAT_CMPLT_T (CMPLT_ID,CMPLT_CD,CMPLT_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (5.0,'4','Not applicable','MFHD 008-16 http://www.loc.gov/marc/holdings/hd008.html','1004',1.0,'Y',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ))
/
