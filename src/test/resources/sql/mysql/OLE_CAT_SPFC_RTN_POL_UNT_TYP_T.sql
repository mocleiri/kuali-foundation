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

TRUNCATE TABLE OLE_CAT_SPFC_RTN_POL_UNT_TYP_T
/
INSERT INTO OLE_CAT_SPFC_RTN_POL_UNT_TYP_T (SPFC_RTN_POL_UNT_TYP_ID,SPFC_RTN_POL_UNT_TYP_CD,SPFC_RTN_POL_UNT_TYP_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('1','m','Month(s)','MFHD 008-15: http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','a923c02b-a375-4fcb-bf10-43cd1d77d92f',1.0)
/
INSERT INTO OLE_CAT_SPFC_RTN_POL_UNT_TYP_T (SPFC_RTN_POL_UNT_TYP_ID,SPFC_RTN_POL_UNT_TYP_CD,SPFC_RTN_POL_UNT_TYP_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('2','w','Week(s)','MFHD 008-15: http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','a6e7d0db-1ece-4fed-afaf-c7c07f1e54f1',1.0)
/
INSERT INTO OLE_CAT_SPFC_RTN_POL_UNT_TYP_T (SPFC_RTN_POL_UNT_TYP_ID,SPFC_RTN_POL_UNT_TYP_CD,SPFC_RTN_POL_UNT_TYP_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('3','y','Year(s)','MFHD 008-15: http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','9191c8df-2f02-4e21-8052-9747224b844f',1.0)
/
INSERT INTO OLE_CAT_SPFC_RTN_POL_UNT_TYP_T (SPFC_RTN_POL_UNT_TYP_ID,SPFC_RTN_POL_UNT_TYP_CD,SPFC_RTN_POL_UNT_TYP_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('4','e','Edition(s)','MFHD 008-15: http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','c3e77f75-d107-4601-8f12-bf9ad2365932',1.0)
/
INSERT INTO OLE_CAT_SPFC_RTN_POL_UNT_TYP_T (SPFC_RTN_POL_UNT_TYP_ID,SPFC_RTN_POL_UNT_TYP_CD,SPFC_RTN_POL_UNT_TYP_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('5','i','Issue(s)','MFHD 008-15: http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','66f2c908-d4ce-46b2-82f1-7777792dde54',1.0)
/
INSERT INTO OLE_CAT_SPFC_RTN_POL_UNT_TYP_T (SPFC_RTN_POL_UNT_TYP_ID,SPFC_RTN_POL_UNT_TYP_CD,SPFC_RTN_POL_UNT_TYP_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES ('6','s','Supplement(s)','MFHD 008-15: http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','2ac99f8b-1e33-4761-8856-43458afe582c',1.0)
/
