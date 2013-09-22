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

TRUNCATE TABLE KRIM_ENT_TYP_T
/
INSERT INTO KRIM_ENT_TYP_T (ACTV_IND,DISPLAY_SORT_CD,ENT_TYP_CD,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','01','PERSON','Person','5B97C50B03886110E0404F8189D85213',1)
/
INSERT INTO KRIM_ENT_TYP_T (ACTV_IND,DISPLAY_SORT_CD,ENT_TYP_CD,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','02','SYSTEM','System','5B97C50B03896110E0404F8189D85213',1)
/
