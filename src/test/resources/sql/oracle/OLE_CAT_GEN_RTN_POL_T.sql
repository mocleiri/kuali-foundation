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

TRUNCATE TABLE OLE_CAT_GEN_RTN_POL_T DROP STORAGE
/
INSERT INTO OLE_CAT_GEN_RTN_POL_T (GEN_RTN_POL_ID,GEN_RTN_POL_CD,GEN_RTN_POL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (1.0,'0','Unknown','MFHD 008-12 http://www.loc.gov/marc/holdings/hd008.html','1000',1.0,'Y',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ))
/
INSERT INTO OLE_CAT_GEN_RTN_POL_T (GEN_RTN_POL_ID,GEN_RTN_POL_CD,GEN_RTN_POL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (2.0,'1','Other general retention policy','MFHD 008-12 http://www.loc.gov/marc/holdings/hd008.html','1001',1.0,'Y',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ))
/
INSERT INTO OLE_CAT_GEN_RTN_POL_T (GEN_RTN_POL_ID,GEN_RTN_POL_CD,GEN_RTN_POL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (3.0,'2','Retained except as replaced by updates','MFHD 008-12 http://www.loc.gov/marc/holdings/hd008.html','1002',1.0,'Y',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ))
/
INSERT INTO OLE_CAT_GEN_RTN_POL_T (GEN_RTN_POL_ID,GEN_RTN_POL_CD,GEN_RTN_POL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (4.0,'3','Sample issue retained','MFHD 008-12 http://www.loc.gov/marc/holdings/hd008.html','1003',1.0,'Y',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ))
/
INSERT INTO OLE_CAT_GEN_RTN_POL_T (GEN_RTN_POL_ID,GEN_RTN_POL_CD,GEN_RTN_POL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (5.0,'4','Retained until replaced by microform','MFHD Leader 17 Encoding Level: http://www.loc.gov/marc/holdings/hdleader.html','1004',1.0,'Y',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ))
/
INSERT INTO OLE_CAT_GEN_RTN_POL_T (GEN_RTN_POL_ID,GEN_RTN_POL_CD,GEN_RTN_POL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (6.0,'5','Retained until replaced by cumulation, replacement volume, or revision','MFHD 008-12 http://www.loc.gov/marc/holdings/hd008.html','1006',1.0,'Y',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ))
/
INSERT INTO OLE_CAT_GEN_RTN_POL_T (GEN_RTN_POL_ID,GEN_RTN_POL_CD,GEN_RTN_POL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (7.0,'6','Retained for a limited period','MFHD 008-12 http://www.loc.gov/marc/holdings/hd008.html','1007',1.0,'Y',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ))
/
INSERT INTO OLE_CAT_GEN_RTN_POL_T (GEN_RTN_POL_ID,GEN_RTN_POL_CD,GEN_RTN_POL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (8.0,'7','Not retained','MFHD 008-12 http://www.loc.gov/marc/holdings/hd008.html','1008',1.0,'Y',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ))
/
INSERT INTO OLE_CAT_GEN_RTN_POL_T (GEN_RTN_POL_ID,GEN_RTN_POL_CD,GEN_RTN_POL_NM,SRC,OBJ_ID,VER_NBR,ROW_ACT_IND,SRC_DT)
  VALUES (9.0,'8','Permanently retained','MFHD 008-12 http://www.loc.gov/marc/holdings/hd008.html','1009',1.0,'Y',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ))
/
