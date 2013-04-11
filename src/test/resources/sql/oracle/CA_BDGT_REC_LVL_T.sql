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

TRUNCATE TABLE CA_BDGT_REC_LVL_T DROP STORAGE
/
INSERT INTO CA_BDGT_REC_LVL_T (BDGT_REC_LVL_CD,OBJ_ID,VER_NBR,BDGT_REC_LVL_NM,ROW_ACTV_IND)
  VALUES ('A','3C4DCACBEA9130FEE043814FD88130FE',1.0,'Account','Y')
/
INSERT INTO CA_BDGT_REC_LVL_T (BDGT_REC_LVL_CD,OBJ_ID,VER_NBR,BDGT_REC_LVL_NM,ROW_ACTV_IND)
  VALUES ('C','3C4DCACBEA9230FEE043814FD88130FE',1.0,'Consolidation','Y')
/
INSERT INTO CA_BDGT_REC_LVL_T (BDGT_REC_LVL_CD,OBJ_ID,VER_NBR,BDGT_REC_LVL_NM,ROW_ACTV_IND)
  VALUES ('L','3C4DCACBEA9330FEE043814FD88130FE',1.0,'Level','Y')
/
INSERT INTO CA_BDGT_REC_LVL_T (BDGT_REC_LVL_CD,OBJ_ID,VER_NBR,BDGT_REC_LVL_NM,ROW_ACTV_IND)
  VALUES ('M','3C4DCACBEA9430FEE043814FD88130FE',1.0,'Mixed','Y')
/
INSERT INTO CA_BDGT_REC_LVL_T (BDGT_REC_LVL_CD,OBJ_ID,VER_NBR,BDGT_REC_LVL_NM,ROW_ACTV_IND)
  VALUES ('N','3C4DCACBEA9530FEE043814FD88130FE',1.0,'No Budget','Y')
/
INSERT INTO CA_BDGT_REC_LVL_T (BDGT_REC_LVL_CD,OBJ_ID,VER_NBR,BDGT_REC_LVL_NM,ROW_ACTV_IND)
  VALUES ('O','3C4DCACBEA9630FEE043814FD88130FE',1.0,'Object Code','Y')
/
INSERT INTO CA_BDGT_REC_LVL_T (BDGT_REC_LVL_CD,OBJ_ID,VER_NBR,BDGT_REC_LVL_NM,ROW_ACTV_IND)
  VALUES ('S','3C4DCACBEA9730FEE043814FD88130FE',1.0,'Sub-Account','Y')
/
