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

TRUNCATE TABLE OLE_PUR_REQ_RCPT_STATUS_T DROP STORAGE
/
INSERT INTO OLE_PUR_REQ_RCPT_STATUS_T (OLE_REQ_RCPT_STATUS_ID,OLE_REQ_RCPT_STATUS_NM,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (1.0,'Partially Received',1.0,'1','Y')
/
INSERT INTO OLE_PUR_REQ_RCPT_STATUS_T (OLE_REQ_RCPT_STATUS_ID,OLE_REQ_RCPT_STATUS_NM,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (2.0,'Fully Received',1.0,'2','Y')
/
INSERT INTO OLE_PUR_REQ_RCPT_STATUS_T (OLE_REQ_RCPT_STATUS_ID,OLE_REQ_RCPT_STATUS_NM,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (3.0,'NOT Received',1.0,'3','Y')
/
