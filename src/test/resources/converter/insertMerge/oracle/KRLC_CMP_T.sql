--
-- Copyright 2010-2014 The Kuali Foundation
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

TRUNCATE TABLE KRLC_CMP_T DROP STORAGE
/
INSERT INTO KRLC_CMP_T (ACTV_IND,CAMPUS_CD,CAMPUS_NM,CAMPUS_SHRT_NM,CAMPUS_TYP_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','AL','All','All','B','9D42B6A9-5784-E5AA-C6BA-FDE44408BA53',1)
/
INSERT INTO KRLC_CMP_T (ACTV_IND,CAMPUS_CD,CAMPUS_NM,CAMPUS_SHRT_NM,CAMPUS_TYP_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','EX','Extended','Extended','B','ACF22650-F113-8C28-9C69-2801F89CCC35',1)
/
INSERT INTO KRLC_CMP_T (ACTV_IND,CAMPUS_CD,CAMPUS_NM,CAMPUS_SHRT_NM,CAMPUS_TYP_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','NO','North','North','B','2FD61B04-5758-FE68-55C7-A35A8793D183',1)
/
INSERT INTO KRLC_CMP_T (ACTV_IND,CAMPUS_CD,CAMPUS_NM,CAMPUS_SHRT_NM,CAMPUS_TYP_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','SO','South','South','B','64DD27F2-312B-F304-64AB-58F468F91379',1)
/
