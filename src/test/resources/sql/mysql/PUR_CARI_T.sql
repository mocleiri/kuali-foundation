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

TRUNCATE TABLE PUR_CARI_T
/
INSERT INTO PUR_CARI_T (CARI_CD,OBJ_ID,VER_NBR,CARI_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('FED','BD51DF30-34E7-B049-6861-9BEA0610A0FE',1.0,'Federal Express','Y')
/
INSERT INTO PUR_CARI_T (CARI_CD,OBJ_ID,VER_NBR,CARI_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('UPS','5A4A8B66-175C-E2E2-7F2F-2A7A4450FDC3',1.0,'UPS','Y')
/
INSERT INTO PUR_CARI_T (CARI_CD,OBJ_ID,VER_NBR,CARI_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('USPS','8999DCBC-B1D8-069E-F9B3-D296A6062E32',1.0,'U.S. Postal Service','Y')
/
