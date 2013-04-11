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

TRUNCATE TABLE PUR_PO_TRNS_MTHD_T
/
INSERT INTO PUR_PO_TRNS_MTHD_T (PO_TRNS_MTHD_CD,OBJ_ID,VER_NBR,PO_TRNS_MTHD_DESC,DOBJ_MAINT_CD_ACTV_IND,DISPLAY_TO_USER_IND)
  VALUES ('ELEC','6C1578D5-2AC5-9E6F-5F2A-57A808DC79BF',2.0,'ELECTRONIC','N','N')
/
INSERT INTO PUR_PO_TRNS_MTHD_T (PO_TRNS_MTHD_CD,OBJ_ID,VER_NBR,PO_TRNS_MTHD_DESC,DOBJ_MAINT_CD_ACTV_IND,DISPLAY_TO_USER_IND)
  VALUES ('FAX','4D1BB005-9EA2-EAE2-B5FE-32942EE4FEC6',2.0,'FAX','N','Y')
/
INSERT INTO PUR_PO_TRNS_MTHD_T (PO_TRNS_MTHD_CD,OBJ_ID,VER_NBR,PO_TRNS_MTHD_DESC,DOBJ_MAINT_CD_ACTV_IND,DISPLAY_TO_USER_IND)
  VALUES ('NOPR','73CA9EE5-F125-E94C-A3B3-BD0F981014F4',1.0,'NO PRINT','Y','Y')
/
INSERT INTO PUR_PO_TRNS_MTHD_T (PO_TRNS_MTHD_CD,OBJ_ID,VER_NBR,PO_TRNS_MTHD_DESC,DOBJ_MAINT_CD_ACTV_IND,DISPLAY_TO_USER_IND)
  VALUES ('PRIN','4FB20242-1236-6533-137B-4F38C26C62E1',1.0,'PRINT','Y','Y')
/
