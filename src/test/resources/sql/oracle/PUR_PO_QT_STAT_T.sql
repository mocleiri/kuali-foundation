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

TRUNCATE TABLE PUR_PO_QT_STAT_T DROP STORAGE
/
INSERT INTO PUR_PO_QT_STAT_T (PO_QT_STAT_CD,OBJ_ID,VER_NBR,PO_QT_STAT_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('FUIP','46DBEE8C-344B-E5F1-7325-120A9F01D4B3',1.0,'Follow up in Progress','Y')
/
INSERT INTO PUR_PO_QT_STAT_T (PO_QT_STAT_CD,OBJ_ID,VER_NBR,PO_QT_STAT_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('MULT','E86594E6-7D1C-0B90-A00B-4C7C14255481',1.0,'Multiple Award','Y')
/
INSERT INTO PUR_PO_QT_STAT_T (PO_QT_STAT_CD,OBJ_ID,VER_NBR,PO_QT_STAT_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('NOBD','59B4D1CB-1C36-4B74-1E70-4DC87B484E51',1.0,'No Bid','Y')
/
INSERT INTO PUR_PO_QT_STAT_T (PO_QT_STAT_CD,OBJ_ID,VER_NBR,PO_QT_STAT_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('NORS','A40287A6-F919-5538-20C7-87902B33B836',1.0,'No Response','Y')
/
INSERT INTO PUR_PO_QT_STAT_T (PO_QT_STAT_CD,OBJ_ID,VER_NBR,PO_QT_STAT_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('RCAC','FF1A43DD-AB81-A209-6F92-2356BEA8516F',1.0,'Received, Bid Acceptable','Y')
/
INSERT INTO PUR_PO_QT_STAT_T (PO_QT_STAT_CD,OBJ_ID,VER_NBR,PO_QT_STAT_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('RCUA','B7F50275-AB62-769A-7E14-E8CB81ECDE2A',1.0,'Received, Bid Unacceptable','Y')
/
