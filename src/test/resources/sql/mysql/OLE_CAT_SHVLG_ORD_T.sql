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

TRUNCATE TABLE OLE_CAT_SHVLG_ORD_T
/
INSERT INTO OLE_CAT_SHVLG_ORD_T (SHVLG_ORD_ID,OBJ_ID,VER_NBR,SHVLG_ORD_CD,SHVLG_ORD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (1.0,'90681179-6ff9-4022-9b3a-acc0f669fad7',1.0,' ','No information provided','MFHD 852 2nd Indictator: http://www.loc.gov/marc/holdings/hd852.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_SHVLG_ORD_T (SHVLG_ORD_ID,OBJ_ID,VER_NBR,SHVLG_ORD_CD,SHVLG_ORD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (2.0,'d4cfe02f-d53e-46c7-9cd4-743eb9394607',1.0,'0','Not enumeration','MFHD 852 2nd Indictator: http://www.loc.gov/marc/holdings/hd852.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_SHVLG_ORD_T (SHVLG_ORD_ID,OBJ_ID,VER_NBR,SHVLG_ORD_CD,SHVLG_ORD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (3.0,'5a7f04cd-b9ed-4824-8666-577bb85d0806',1.0,'1','Primary Enumeration','MFHD 852 2nd Indictator: http://www.loc.gov/marc/holdings/hd852.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_SHVLG_ORD_T (SHVLG_ORD_ID,OBJ_ID,VER_NBR,SHVLG_ORD_CD,SHVLG_ORD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (4.0,'b75289a6-8cc1-47f0-abda-9fa5cb6d19c6',1.0,'2','Alternative Enumeration','MFHD 852 2nd Indictator: http://www.loc.gov/marc/holdings/hd852.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y')
/
