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

TRUNCATE TABLE PUR_SHP_SPCL_COND_T DROP STORAGE
/
INSERT INTO PUR_SHP_SPCL_COND_T (VNDR_SHP_SPCL_COND_CD,OBJ_ID,VER_NBR,VNDR_SHP_SPCL_COND_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('EX','3D7B56B1-57E7-3FEC-BCBC-140E442B8CCE',7.0,'EXPRESS','Y')
/
INSERT INTO PUR_SHP_SPCL_COND_T (VNDR_SHP_SPCL_COND_CD,OBJ_ID,VER_NBR,VNDR_SHP_SPCL_COND_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('IN','07B10B5F-8065-CFA7-3D1C-5FB6DD77E343',1.0,'INSURED','Y')
/
