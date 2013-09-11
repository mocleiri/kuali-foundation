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

TRUNCATE TABLE FP_CR_CARD_VNDR_T DROP STORAGE
/
INSERT INTO FP_CR_CARD_VNDR_T (FDOC_CCRD_VNDR_NBR,OBJ_ID,VER_NBR,FDOC_CCRD_VNDR_NM,FDOC_CCRD_TYP_CD,ROW_ACTV_IND)
  VALUES ('00123457','82A36E63-1B5B-366A-DAF2-18A3F16DB753',1.0,'PARKING','AE','Y')
/
INSERT INTO FP_CR_CARD_VNDR_T (FDOC_CCRD_VNDR_NBR,OBJ_ID,VER_NBR,FDOC_CCRD_VNDR_NM,FDOC_CCRD_TYP_CD,ROW_ACTV_IND)
  VALUES ('001247454','8A6DC343-D622-7ABB-450C-BC6348DCCA55',1.0,'ATHLETICS','VM','Y')
/
INSERT INTO FP_CR_CARD_VNDR_T (FDOC_CCRD_VNDR_NBR,OBJ_ID,VER_NBR,FDOC_CCRD_VNDR_NM,FDOC_CCRD_TYP_CD,ROW_ACTV_IND)
  VALUES ('00142678','B4727410-C3EE-871E-7DFE-00425FD787D4',1.0,'BURSARS OFFICE','DI','Y')
/
