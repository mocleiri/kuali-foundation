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

TRUNCATE TABLE OLE_CAT_SPCP_RPT_T DROP STORAGE
/
INSERT INTO OLE_CAT_SPCP_RPT_T (SPCP_RPT_ID,OBJ_ID,VER_NBR,SPCP_RPT_CD,SPCP_RPT_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (1.0,'b3339529-15dd-45e6-97a8-a599bc8ab316',1.0,'0','Separate copy report','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_SPCP_RPT_T (SPCP_RPT_ID,OBJ_ID,VER_NBR,SPCP_RPT_CD,SPCP_RPT_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (2.0,'5576ecb2-2991-4f71-8582-a6d2797e0aae',1.0,'1','Composite copy report','MFHD 008-06 http://www.loc.gov/marc/holdings/hd008.html',TO_DATE( '20120322000000', 'YYYYMMDDHH24MISS' ),'Y')
/
