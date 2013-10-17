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

TRUNCATE TABLE PDP_PAYEE_TYP_T
/
INSERT INTO PDP_PAYEE_TYP_T (PAYEE_TYP_CD,OBJ_ID,VER_NBR,PAYEE_TYP_DESC,ACH_ELGBL_IND)
  VALUES ('E','59DD323FB195B3EFE0404F8189D83BC1',1.0,'Employee ID','Y')
/
INSERT INTO PDP_PAYEE_TYP_T (PAYEE_TYP_CD,OBJ_ID,VER_NBR,PAYEE_TYP_DESC,ACH_ELGBL_IND)
  VALUES ('F','59DD323FB196B3EFE0404F8189D83BC1',1.0,'Federal Employer Identification Number','N')
/
INSERT INTO PDP_PAYEE_TYP_T (PAYEE_TYP_CD,OBJ_ID,VER_NBR,PAYEE_TYP_DESC,ACH_ELGBL_IND)
  VALUES ('S','59DD323FB194B3EFE0404F8189D83BC1',1.0,'Social Security Number','N')
/
INSERT INTO PDP_PAYEE_TYP_T (PAYEE_TYP_CD,OBJ_ID,VER_NBR,PAYEE_TYP_DESC,ACH_ELGBL_IND)
  VALUES ('T','695A5E10B3EFA53AE0404F8189D871CC',1.0,'Entity ID','Y')
/
INSERT INTO PDP_PAYEE_TYP_T (PAYEE_TYP_CD,OBJ_ID,VER_NBR,PAYEE_TYP_DESC,ACH_ELGBL_IND)
  VALUES ('V','59DD323FB197B3EFE0404F8189D83BC1',1.0,'Vendor Number','Y')
/
INSERT INTO PDP_PAYEE_TYP_T (PAYEE_TYP_CD,OBJ_ID,VER_NBR,PAYEE_TYP_DESC,ACH_ELGBL_IND)
  VALUES ('X','59DD323FB198B3EFE0404F8189D83BC1',1.0,'Other','N')
/
