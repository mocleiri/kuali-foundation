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

TRUNCATE TABLE OLE_CAT_REC_TYP_T DROP STORAGE
/
INSERT INTO OLE_CAT_REC_TYP_T (REC_TYP_ID,OBJ_ID,VER_NBR,REC_TYP_CD,REC_TYP_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (1.0,'051945bf-3fb3-4d3b-bfc3-a2101b3ffebf',1.0,'u','Unknown','MFHD Leader 06: http://www.loc.gov/marc/holdings/hdleader.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_REC_TYP_T (REC_TYP_ID,OBJ_ID,VER_NBR,REC_TYP_CD,REC_TYP_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (2.0,'7ad19acc-a1ff-4c60-842d-7ac3a920f205',1.0,'v','Multipart item holdings','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_REC_TYP_T (REC_TYP_ID,OBJ_ID,VER_NBR,REC_TYP_CD,REC_TYP_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (3.0,'ba29e03f-1431-4c0a-bf7c-0cd8a754103f',1.0,'x','Single-part item holdings','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_REC_TYP_T (REC_TYP_ID,OBJ_ID,VER_NBR,REC_TYP_CD,REC_TYP_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (4.0,'c0459a23-4d3e-4ae9-9c9d-4ddd70aa9716',1.0,'y','Serial item holdings','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y')
/
