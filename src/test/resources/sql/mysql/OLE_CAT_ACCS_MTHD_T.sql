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

TRUNCATE TABLE OLE_CAT_ACCS_MTHD_T
/
INSERT INTO OLE_CAT_ACCS_MTHD_T (ACCS_MTHD_ID,OBJ_ID,VER_NBR,ACCS_MTHD_CD,ACCS_MTHD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (1.0,'7d051884-1872-4513-aa81-22757a7bdea7',1.0,'0','Email','MFHD 856 1st Indicator Values: http://www.loc.gov/marc/holdings/hd856.html',STR_TO_DATE( '20120517000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_ACCS_MTHD_T (ACCS_MTHD_ID,OBJ_ID,VER_NBR,ACCS_MTHD_CD,ACCS_MTHD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (2.0,'7d051884-1872-4513-aa81-22757a7bdea7',1.0,'1','FTP','MFHD 856 1st Indicator Values: http://www.loc.gov/marc/holdings/hd856.html',STR_TO_DATE( '20120517000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_ACCS_MTHD_T (ACCS_MTHD_ID,OBJ_ID,VER_NBR,ACCS_MTHD_CD,ACCS_MTHD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (3.0,'7d051884-1872-4513-aa81-22757a7bdea7',1.0,'2','Remote Login (Telnet)','MFHD 856 1st Indicator Values: http://www.loc.gov/marc/holdings/hd856.html',STR_TO_DATE( '20120517000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_ACCS_MTHD_T (ACCS_MTHD_ID,OBJ_ID,VER_NBR,ACCS_MTHD_CD,ACCS_MTHD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (4.0,'7d051884-1872-4513-aa81-22757a7bdea7',1.0,'3','Dial Up','MFHD 856 1st Indicator Values: http://www.loc.gov/marc/holdings/hd856.html',STR_TO_DATE( '20120517000000', '%Y%m%d%H%i%s' ),'Y')
/
