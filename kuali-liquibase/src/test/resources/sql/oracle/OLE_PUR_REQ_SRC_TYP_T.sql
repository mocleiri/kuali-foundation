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

TRUNCATE TABLE OLE_PUR_REQ_SRC_TYP_T DROP STORAGE
/
INSERT INTO OLE_PUR_REQ_SRC_TYP_T (OLE_REQ_SRC_TYP_ID,OLE_REQ_SRC_TYPE,VER_NBR,OBJ_ID,ROW_ACTV_IND)
  VALUES (1.0,'BatchIngest',1.0,'89B234B6-71A6-14C8-04D6-2F2A571AEFE9','Y')
/
INSERT INTO OLE_PUR_REQ_SRC_TYP_T (OLE_REQ_SRC_TYP_ID,OLE_REQ_SRC_TYPE,VER_NBR,OBJ_ID,ROW_ACTV_IND)
  VALUES (2.0,'Staff',1.0,'2FDAFC95-DFDB-89C6-4516-48F6A25277BC','Y')
/
INSERT INTO OLE_PUR_REQ_SRC_TYP_T (OLE_REQ_SRC_TYP_ID,OLE_REQ_SRC_TYPE,VER_NBR,OBJ_ID,ROW_ACTV_IND)
  VALUES (3.0,'WebForm',1.0,'5B876DC3-175A-CBD0-DCFD-88BDDE66386F','Y')
/
INSERT INTO OLE_PUR_REQ_SRC_TYP_T (OLE_REQ_SRC_TYP_ID,OLE_REQ_SRC_TYPE,VER_NBR,OBJ_ID,ROW_ACTV_IND)
  VALUES (4.0,'WorldCat',1.0,'89B234B6-71A6-14C8-04D6-2F2A571AEBE9','Y')
/
INSERT INTO OLE_PUR_REQ_SRC_TYP_T (OLE_REQ_SRC_TYP_ID,OLE_REQ_SRC_TYPE,VER_NBR,OBJ_ID,ROW_ACTV_IND)
  VALUES (5.0,'Amazon',1.0,'2FDAFC95-DFDB-89C6-4516-48F6A25077BC','Y')
/
INSERT INTO OLE_PUR_REQ_SRC_TYP_T (OLE_REQ_SRC_TYP_ID,OLE_REQ_SRC_TYPE,VER_NBR,OBJ_ID,ROW_ACTV_IND)
  VALUES (6.0,'Other',1.0,'5B876DC3-175A-CBD0-DCFD-88BDDE66186F','Y')
/
