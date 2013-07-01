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

TRUNCATE TABLE OLE_CAT_LOC_COUNTRY_CD_T
/
INSERT INTO OLE_CAT_LOC_COUNTRY_CD_T (LOC_COUNTRY_CD_ID,OBJ_ID,VER_NBR,LOC_COUNTRY_CD,LOC_COUNTRY_NM,LOC_COUNTRY_REGION_NM,LOC_COUNTRY_SEQUENCE_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (1.0,'4dcdf94f-fc19-40f4-86b6-9a3a66d42bda',3.0,'aa','Albania','Europe','Reunion','http://www.loc.gov/marc/countries',STR_TO_DATE( '20120504000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_LOC_COUNTRY_CD_T (LOC_COUNTRY_CD_ID,OBJ_ID,VER_NBR,LOC_COUNTRY_CD,LOC_COUNTRY_NM,LOC_COUNTRY_REGION_NM,LOC_COUNTRY_SEQUENCE_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (2.0,'4dcdf94f-fc19-40f4-86b6-9a3a66d42bda',3.0,'in','India','Asia','Reunion','http://www.loc.gov/marc/countries',STR_TO_DATE( '20120504000000', '%Y%m%d%H%i%s' ),'Y')
/
