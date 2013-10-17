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

TRUNCATE TABLE OLE_CAT_STAT_SRCH_CD_T
/
INSERT INTO OLE_CAT_STAT_SRCH_CD_T (STAT_SRCH_CD_ID,OBJ_ID,VER_NBR,STAT_SRCH_CD,STAT_SRCH_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (1.0,'8598b141-cdfe-41bf-ba77-6eaeb344e8b4',3.0,'CICSPR','__CIC Shared Print Repository','IU SIRSI',STR_TO_DATE( '20120504000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_STAT_SRCH_CD_T (STAT_SRCH_CD_ID,OBJ_ID,VER_NBR,STAT_SRCH_CD,STAT_SRCH_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (2.0,'47d23fb1-eacc-4241-bd14-bd194030e8cc',1.0,'EARLYMUSIC','__Music Library (Blgtn) - Thom','IU SIRSI',STR_TO_DATE( '20120504000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_STAT_SRCH_CD_T (STAT_SRCH_CD_ID,OBJ_ID,VER_NBR,STAT_SRCH_CD,STAT_SRCH_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (3.0,'8e716dd0-75a6-423d-82a1-db9dffaef296',1.0,'EASTASIAN','Wells Library (Blgtn) - East A','IU SIRSI',STR_TO_DATE( '20120504000000', '%Y%m%d%H%i%s' ),'Y')
/
