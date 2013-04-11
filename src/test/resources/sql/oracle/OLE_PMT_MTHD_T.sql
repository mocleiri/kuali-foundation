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

TRUNCATE TABLE OLE_PMT_MTHD_T DROP STORAGE
/
INSERT INTO OLE_PMT_MTHD_T (OLE_PMT_MTHD_ID,PMT_MTHD,PMT_MTHD_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (1.0,'Check','Check',1.0,'2C6E315A-FB42-9C71-6962-F002860D2ABD','Y')
/
INSERT INTO OLE_PMT_MTHD_T (OLE_PMT_MTHD_ID,PMT_MTHD,PMT_MTHD_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (2.0,'Credit Card','Credit Card',1.0,'3E816200-ED98-318F-50AA-B86B7D86B9FA','Y')
/
INSERT INTO OLE_PMT_MTHD_T (OLE_PMT_MTHD_ID,PMT_MTHD,PMT_MTHD_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (3.0,'Wire Transfer','Wire Transfer',1.0,'DD46BBA5-CE27-E4C1-678C-51A60D4DA77A','Y')
/
INSERT INTO OLE_PMT_MTHD_T (OLE_PMT_MTHD_ID,PMT_MTHD,PMT_MTHD_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (4.0,'Cash','Cash',1.0,'02229BFB-85E8-92FC-ABF3-0922EE60A2D6','Y')
/
INSERT INTO OLE_PMT_MTHD_T (OLE_PMT_MTHD_ID,PMT_MTHD,PMT_MTHD_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (5.0,'Internal Transfer','Internal Transfer',1.0,'855DA5F1-1C3F-AB27-F472-BC94E2BE89C3','Y')
/
INSERT INTO OLE_PMT_MTHD_T (OLE_PMT_MTHD_ID,PMT_MTHD,PMT_MTHD_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (6.0,'Foreign Draft','Foreign Draft',1.0,'B2A88E36-2DDD-9AA8-5152-11004080951D','Y')
/
