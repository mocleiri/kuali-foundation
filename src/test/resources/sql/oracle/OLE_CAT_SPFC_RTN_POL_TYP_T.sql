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

TRUNCATE TABLE OLE_CAT_SPFC_RTN_POL_TYP_T DROP STORAGE
/
INSERT INTO OLE_CAT_SPFC_RTN_POL_TYP_T (SPFC_RTN_POL_TYP_ID,OBJ_ID,VER_NBR,SPFC_RTN_POL_TYP_CD,SPFC_RTN_POL_TYP_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (1.0,'167706f6-7947-4da7-9b2d-0ad7aee2545d',1.0,'l','latest','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_SPFC_RTN_POL_TYP_T (SPFC_RTN_POL_TYP_ID,OBJ_ID,VER_NBR,SPFC_RTN_POL_TYP_CD,SPFC_RTN_POL_TYP_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (2.0,'fea69113-d603-4703-b8a1-3c2dd30846c6',1.0,'p','previous','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y')
/
