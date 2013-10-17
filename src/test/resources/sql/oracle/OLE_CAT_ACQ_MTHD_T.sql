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

TRUNCATE TABLE OLE_CAT_ACQ_MTHD_T DROP STORAGE
/
INSERT INTO OLE_CAT_ACQ_MTHD_T (ACQ_MTHD_ID,OBJ_ID,VER_NBR,ACQ_MTHD_CD,ACQ_MTHD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (1.0,'4a568bd2-bff0-4866-a106-414b97dc434f',1.0,'c','Cooperative or consortial purchase','MFHD 008-07 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120517000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_ACQ_MTHD_T (ACQ_MTHD_ID,OBJ_ID,VER_NBR,ACQ_MTHD_CD,ACQ_MTHD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (2.0,'d6ca5e1d-a452-4ef5-9fee-57b4575e8930',1.0,'d','Deposit','MFHD 008-07 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120517000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_ACQ_MTHD_T (ACQ_MTHD_ID,OBJ_ID,VER_NBR,ACQ_MTHD_CD,ACQ_MTHD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (3.0,'99c45b6c-6acb-4c18-8a65-f93d1c015356',1.0,'e','Exchange','MFHD 008-07 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120517000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_ACQ_MTHD_T (ACQ_MTHD_ID,OBJ_ID,VER_NBR,ACQ_MTHD_CD,ACQ_MTHD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (4.0,'a7c72b13-22dd-4dbf-b784-b585c460cc9a',1.0,'f','Free','MFHD 008-07 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120517000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_ACQ_MTHD_T (ACQ_MTHD_ID,OBJ_ID,VER_NBR,ACQ_MTHD_CD,ACQ_MTHD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (5.0,'4fe09eda-49bf-4e04-a761-7060355ebadb',1.0,'g','Gift','MFHD 008-07 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120517000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_ACQ_MTHD_T (ACQ_MTHD_ID,OBJ_ID,VER_NBR,ACQ_MTHD_CD,ACQ_MTHD_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (6.0,'fbd9ecab-b40c-46fa-bfdb-52bb5ebc3114',1.0,'l','Legal Deposit','MFHD 008-07 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120517000000', 'YYYYMMDDHH24MISS' ),'Y')
/
