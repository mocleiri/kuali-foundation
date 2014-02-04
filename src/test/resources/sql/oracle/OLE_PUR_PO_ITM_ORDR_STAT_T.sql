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

TRUNCATE TABLE OLE_PUR_PO_ITM_ORDR_STAT_T DROP STORAGE
/
INSERT INTO OLE_PUR_PO_ITM_ORDR_STAT_T (OLE_PO_ITM_ORDR_STAT_ID,OLE_PO_ITM_ORDR_STAT,VER_NBR,OBJ_ID,ROW_ACTV_IND,DESC_TXT)
  VALUES (1.0,'On order',1.0,'1','Y','PO has been sent to vendor; awaiting receipt')
/
INSERT INTO OLE_PUR_PO_ITM_ORDR_STAT_T (OLE_PO_ITM_ORDR_STAT_ID,OLE_PO_ITM_ORDR_STAT,VER_NBR,OBJ_ID,ROW_ACTV_IND,DESC_TXT)
  VALUES (2.0,'Received',1.0,'2','Y','Title has been received from vendor')
/
INSERT INTO OLE_PUR_PO_ITM_ORDR_STAT_T (OLE_PO_ITM_ORDR_STAT_ID,OLE_PO_ITM_ORDR_STAT,VER_NBR,OBJ_ID,ROW_ACTV_IND,DESC_TXT)
  VALUES (3.0,'In process',1.0,'3','Y','Title has been received from vendor and awaits action by a staff member')
/
