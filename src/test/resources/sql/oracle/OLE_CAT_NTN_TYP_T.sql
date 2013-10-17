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

TRUNCATE TABLE OLE_CAT_NTN_TYP_T DROP STORAGE
/
INSERT INTO OLE_CAT_NTN_TYP_T (NTN_TYP_ID,NTN_TYP_CD,NTN_TYP_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('1','0','Non-standard','MFHD 866-868 2nd Indicator: http://www.loc.gov/marc/holdings/hd866868.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y','1f722a90-87ef-4326-a1ad-25a039d9c451',1.0)
/
INSERT INTO OLE_CAT_NTN_TYP_T (NTN_TYP_ID,NTN_TYP_CD,NTN_TYP_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('2','1','ANSI/NISO Z39.71 or ISO 10324','MFHD 866-868 2nd Indicator: http://www.loc.gov/marc/holdings/hd866868.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y','1434e659-c486-45e8-b384-bf65b32b7774',1.0)
/
INSERT INTO OLE_CAT_NTN_TYP_T (NTN_TYP_ID,NTN_TYP_CD,NTN_TYP_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('3','2','ANSI Z39.42','MFHD 866-868 2nd Indicator: http://www.loc.gov/marc/holdings/hd866868.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y','ad318dd7-391f-48be-a7b3-f6ddd1d9eddb',1.0)
/
