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

TRUNCATE TABLE PUR_VNDR_INACTV_REAS_T
/
INSERT INTO PUR_VNDR_INACTV_REAS_T (VNDR_INACTV_REAS_CD,OBJ_ID,VER_NBR,VNDR_INACTV_REAS_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('ME','17D43179-0F73-BD17-1386-CDD214F9134F',1.0,'MERGED','Y')
/
INSERT INTO PUR_VNDR_INACTV_REAS_T (VNDR_INACTV_REAS_CD,OBJ_ID,VER_NBR,VNDR_INACTV_REAS_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('OB','922043E0-B0C7-21E1-235F-B95391017119',1.0,'OUT OF BUSINESS','Y')
/
INSERT INTO PUR_VNDR_INACTV_REAS_T (VNDR_INACTV_REAS_CD,OBJ_ID,VER_NBR,VNDR_INACTV_REAS_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('SO','9BFCF4E2-C8B0-77DC-F7B5-02D0FF336B7F',1.0,'SOLD','Y')
/
