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

TRUNCATE TABLE CA_MNXFR_ELIM_T DROP STORAGE
/
INSERT INTO CA_MNXFR_ELIM_T (FOBJ_MNXFR_ELIM_CD,OBJ_ID,VER_NBR,DOBJ_MAINT_CD_ACTV_IND,FOBJ_MNXFR_ELIM_NM)
  VALUES ('E','10253E9DB8CB7EBEE043814FD8817EBE',1.0,'Y','ELIMINATIONS OBJECT')
/
INSERT INTO CA_MNXFR_ELIM_T (FOBJ_MNXFR_ELIM_CD,OBJ_ID,VER_NBR,DOBJ_MAINT_CD_ACTV_IND,FOBJ_MNXFR_ELIM_NM)
  VALUES ('M','10253E9DB8CA7EBEE043814FD8817EBE',1.0,'Y','MANDATORY TRANSFER OBJECT')
/
INSERT INTO CA_MNXFR_ELIM_T (FOBJ_MNXFR_ELIM_CD,OBJ_ID,VER_NBR,DOBJ_MAINT_CD_ACTV_IND,FOBJ_MNXFR_ELIM_NM)
  VALUES ('N','0CFF4715AAFACB80E043814FD881CB80',1.0,'Y','NEITHER')
/
