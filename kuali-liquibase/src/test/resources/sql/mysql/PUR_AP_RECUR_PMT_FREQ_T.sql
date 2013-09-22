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

TRUNCATE TABLE PUR_AP_RECUR_PMT_FREQ_T
/
INSERT INTO PUR_AP_RECUR_PMT_FREQ_T (RECUR_PMT_FREQ_CD,OBJ_ID,VER_NBR,RECUR_PMT_FREQ_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('ANN','F2A31E46-51A9-06A5-12AC-4E98C5977AE1',2.0,'ANNUALLY','Y')
/
INSERT INTO PUR_AP_RECUR_PMT_FREQ_T (RECUR_PMT_FREQ_CD,OBJ_ID,VER_NBR,RECUR_PMT_FREQ_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('MONT','F0753903-E983-7D95-66C8-F9AF900A99AC',1.0,'MONTHLY','Y')
/
INSERT INTO PUR_AP_RECUR_PMT_FREQ_T (RECUR_PMT_FREQ_CD,OBJ_ID,VER_NBR,RECUR_PMT_FREQ_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('QUAR','2E49B043-2C5A-8425-3679-7C768FB969E6',1.0,'QUARTERLY','Y')
/
INSERT INTO PUR_AP_RECUR_PMT_FREQ_T (RECUR_PMT_FREQ_CD,OBJ_ID,VER_NBR,RECUR_PMT_FREQ_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('SEMI','AF212D0D-B116-0E32-104B-0F8A003837D5',1.0,'SEMIANNUALLY','Y')
/
INSERT INTO PUR_AP_RECUR_PMT_FREQ_T (RECUR_PMT_FREQ_CD,OBJ_ID,VER_NBR,RECUR_PMT_FREQ_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('WEEK','1094217A-F2D5-4625-915C-079E2BAE3064',1.0,'WEEKLY','Y')
/
