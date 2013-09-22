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

TRUNCATE TABLE OLE_CAT_REPRO_POL_T
/
INSERT INTO OLE_CAT_REPRO_POL_T (REPRO_POL_ID,OBJ_ID,VER_NBR,REPRO_POL_CD,REPRO_POL_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (1.0,'89c05582-c048-4ceb-8301-d69984788ffe',1.0,'a','Will Reproduce','MFHD 008-21 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_REPRO_POL_T (REPRO_POL_ID,OBJ_ID,VER_NBR,REPRO_POL_CD,REPRO_POL_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (2.0,'85d9c259-a6e8-4a49-8832-f71dc70d895d',1.0,'b','Will Not Reproduce','MFHD 008-21 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_REPRO_POL_T (REPRO_POL_ID,OBJ_ID,VER_NBR,REPRO_POL_CD,REPRO_POL_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (3.0,'c8c9a29c-6798-4252-bc93-141348143c7a',1.0,'u','unknown','MFHD 008-21 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y')
/
