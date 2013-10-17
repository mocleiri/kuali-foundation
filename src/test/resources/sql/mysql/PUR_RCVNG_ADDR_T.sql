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

TRUNCATE TABLE PUR_RCVNG_ADDR_T
/
INSERT INTO PUR_RCVNG_ADDR_T (PUR_RCVNG_ADDR_ID,OBJ_ID,VER_NBR,FIN_COA_CD,PUR_RCVNG_NM,PUR_RCVNG_LN1_ADDR,PUR_RCVNG_LN2_ADDR,PUR_RCVNG_CTY_NM,PUR_RCVNG_ST_CD,PUR_RCVNG_PSTL_CD,PUR_RCVNG_CNTRY_CD,PUR_RCVNG_DFLT_ADDR_IND,VNDR_ADDR_USE_RCVNG_IND,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1000.0,'4C95554BF981AC7AE0404F8189D81998',1.0,'BL','KUALI UNIVERSITY','400 E. Main Street','Central Receiving','Kuali City','WY','83444','US','Y','Y','Y')
/
INSERT INTO PUR_RCVNG_ADDR_T (PUR_RCVNG_ADDR_ID,OBJ_ID,VER_NBR,FIN_COA_CD,ORG_CD,PUR_RCVNG_NM,PUR_RCVNG_LN1_ADDR,PUR_RCVNG_CTY_NM,PUR_RCVNG_ST_CD,PUR_RCVNG_PSTL_CD,PUR_RCVNG_CNTRY_CD,PUR_RCVNG_DFLT_ADDR_IND,VNDR_ADDR_USE_RCVNG_IND,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1001.0,'4E38D982D10A34BCE0404F8189D82DAB',2.0,'BL','PSY','KUALI UNIVERSITY - PSYCHOLOGY DEPT.','3201 Sigmund Lane','Kuali City','WY','83444','US','Y','Y','Y')
/
