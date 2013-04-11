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

TRUNCATE TABLE KRIM_PRNCPL_T DROP STORAGE
/
INSERT INTO KRIM_PRNCPL_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,PRNCPL_ID,PRNCPL_NM,VER_NBR)
  VALUES ('Y','1',TO_DATE( '20081107094902', 'YYYYMMDDHH24MISS' ),'5B1B6B919CCA6496E0404F8189D822F2','1','kr',1)
/
INSERT INTO KRIM_PRNCPL_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,PRNCPL_ID,PRNCPL_NM,VER_NBR)
  VALUES ('Y','1100',TO_DATE( '20081113140643', 'YYYYMMDDHH24MISS' ),'5B97C50B03C56110E0404F8189D85213','admin','admin',1)
/
INSERT INTO KRIM_PRNCPL_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,PRNCPL_ID,PRNCPL_NM,VER_NBR)
  VALUES ('Y','1131',TO_DATE( '20081113140642', 'YYYYMMDDHH24MISS' ),'5B97C50B03BB6110E0404F8189D85213','notsys','notsys',1)
/
