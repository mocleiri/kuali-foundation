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

TRUNCATE TABLE PDP_PMT_CHG_CD_T
/
INSERT INTO PDP_PMT_CHG_CD_T (PMT_CHG_CD,OBJ_ID,VER_NBR,PMT_CHG_CD_DESC)
  VALUES ('BC','582371A112D54DEDE0404F8189D83A6C',1.0,'Change Bank')
/
INSERT INTO PDP_PMT_CHG_CD_T (PMT_CHG_CD,OBJ_ID,VER_NBR,PMT_CHG_CD_DESC)
  VALUES ('CB','3C4DCACBEAB830FEE043814FD88130FE',0.0,'Cancel Batch')
/
INSERT INTO PDP_PMT_CHG_CD_T (PMT_CHG_CD,OBJ_ID,VER_NBR,PMT_CHG_CD_DESC)
  VALUES ('CD','3C4DCACBEAB930FEE043814FD88130FE',0.0,'Cancel Disbursement')
/
INSERT INTO PDP_PMT_CHG_CD_T (PMT_CHG_CD,OBJ_ID,VER_NBR,PMT_CHG_CD_DESC)
  VALUES ('CP','3C4DCACBEAB730FEE043814FD88130FE',0.0,'Cancel Payment')
/
INSERT INTO PDP_PMT_CHG_CD_T (PMT_CHG_CD,OBJ_ID,VER_NBR,PMT_CHG_CD_DESC)
  VALUES ('CRD','3C4DCACBEABA30FEE043814FD88130FE',0.0,'Cancel-Reissue Disbursement')
/
INSERT INTO PDP_PMT_CHG_CD_T (PMT_CHG_CD,OBJ_ID,VER_NBR,PMT_CHG_CD_DESC)
  VALUES ('HB','3C4DCACBEAB430FEE043814FD88130FE',0.0,'Hold Batch')
/
INSERT INTO PDP_PMT_CHG_CD_T (PMT_CHG_CD,OBJ_ID,VER_NBR,PMT_CHG_CD_DESC)
  VALUES ('HP','3C4DCACBEAB330FEE043814FD88130FE',0.0,'Hold Payment')
/
INSERT INTO PDP_PMT_CHG_CD_T (PMT_CHG_CD,OBJ_ID,VER_NBR,PMT_CHG_CD_DESC)
  VALUES ('IMP','3C4DCACBEABB30FEE043814FD88130FE',0.0,'Change Immediate Print')
/
INSERT INTO PDP_PMT_CHG_CD_T (PMT_CHG_CD,OBJ_ID,VER_NBR,PMT_CHG_CD_DESC)
  VALUES ('RC','C472501079135765E040220A4C16066C',0.0,'REISSUE CANCEL')
/
INSERT INTO PDP_PMT_CHG_CD_T (PMT_CHG_CD,OBJ_ID,VER_NBR,PMT_CHG_CD_DESC)
  VALUES ('RHB','3C4DCACBEAB630FEE043814FD88130FE',0.0,'Release Hold on Batch')
/
INSERT INTO PDP_PMT_CHG_CD_T (PMT_CHG_CD,OBJ_ID,VER_NBR,PMT_CHG_CD_DESC)
  VALUES ('RHP','3C4DCACBEAB530FEE043814FD88130FE',0.0,'Release Hold on Payment')
/
