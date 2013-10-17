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

TRUNCATE TABLE FP_CR_CARD_TYP_T
/
INSERT INTO FP_CR_CARD_TYP_T (FDOC_CCRD_TYP_CD,OBJ_ID,VER_NBR,FDOC_CCRD_CMPNY_NM,ROW_ACTV_IND)
  VALUES ('AE','10C4F1D2303031FAE043814FD88131FA',1.0,'AMERICAN EXPRESS','Y')
/
INSERT INTO FP_CR_CARD_TYP_T (FDOC_CCRD_TYP_CD,OBJ_ID,VER_NBR,FDOC_CCRD_CMPNY_NM,ROW_ACTV_IND)
  VALUES ('DI','10C4F1D2303131FAE043814FD88131FA',1.0,'DISCOVER','Y')
/
INSERT INTO FP_CR_CARD_TYP_T (FDOC_CCRD_TYP_CD,OBJ_ID,VER_NBR,FDOC_CCRD_CMPNY_NM,ROW_ACTV_IND)
  VALUES ('VM','10C4F1D2303231FAE043814FD88131FA',1.0,'VISA/MASTERCARD','Y')
/
