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

TRUNCATE TABLE OLE_PUR_PO_ERR_CDS_T
/
INSERT INTO OLE_PUR_PO_ERR_CDS_T (ERR_ID,ERROR,OBJ_ID,VER_NBR,ROW_ACT_IND)
  VALUES (1.0,'Duplicate Purchase Order Found','1',1.0,'Y')
/
INSERT INTO OLE_PUR_PO_ERR_CDS_T (ERR_ID,ERROR,OBJ_ID,VER_NBR,ROW_ACT_IND)
  VALUES (2.0,'Duplicate Bibliographic Description Found','2',1.0,'Y')
/
INSERT INTO OLE_PUR_PO_ERR_CDS_T (ERR_ID,ERROR,OBJ_ID,VER_NBR,ROW_ACT_IND)
  VALUES (3.0,'General Load Failure','3',1.0,'Y')
/
INSERT INTO OLE_PUR_PO_ERR_CDS_T (ERR_ID,ERROR,OBJ_ID,VER_NBR,ROW_ACT_IND)
  VALUES (4.0,'Title Not Found','4',1.0,'Y')
/
INSERT INTO OLE_PUR_PO_ERR_CDS_T (ERR_ID,ERROR,OBJ_ID,VER_NBR,ROW_ACT_IND)
  VALUES (5.0,'Duplicate Isbn Found','5',1.0,'Y')
/
INSERT INTO OLE_PUR_PO_ERR_CDS_T (ERR_ID,ERROR,OBJ_ID,VER_NBR,ROW_ACT_IND)
  VALUES (6.0,'Purchase Order Not Found','6',1.0,'Y')
/
INSERT INTO OLE_PUR_PO_ERR_CDS_T (ERR_ID,ERROR,OBJ_ID,VER_NBR,ROW_ACT_IND)
  VALUES (7.0,'BAD_BFN_NUMBER','7',1.0,'Y')
/
INSERT INTO OLE_PUR_PO_ERR_CDS_T (ERR_ID,ERROR,OBJ_ID,VER_NBR,ROW_ACT_IND)
  VALUES (8.0,'BAD_CONTROL_LINE','8',1.0,'Y')
/
