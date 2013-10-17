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

TRUNCATE TABLE CA_BDGT_AGGR_T DROP STORAGE
/
INSERT INTO CA_BDGT_AGGR_T (FOBJ_BDGT_AGGR_CD,OBJ_ID,VER_NBR,DOBJ_MAINT_CD_ACTV_IND,FIN_BDGT_AGGR_NM)
  VALUES ('C','DCE60A80C7204D5F9C529A99FFDCE1E8',1.0,'Y','OBJECT CONSOLIDATION')
/
INSERT INTO CA_BDGT_AGGR_T (FOBJ_BDGT_AGGR_CD,OBJ_ID,VER_NBR,DOBJ_MAINT_CD_ACTV_IND,FIN_BDGT_AGGR_NM)
  VALUES ('L','C90C8F420CF04342A4E099022762F8F8',1.0,'Y','OBJECT LEVEL')
/
INSERT INTO CA_BDGT_AGGR_T (FOBJ_BDGT_AGGR_CD,OBJ_ID,VER_NBR,DOBJ_MAINT_CD_ACTV_IND,FIN_BDGT_AGGR_NM)
  VALUES ('O','F48EAC3E871244678B11854DAE9B1B78',439.0,'Y','OBJECT')
/
