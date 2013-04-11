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

TRUNCATE TABLE PUR_REQS_SRC_T
/
INSERT INTO PUR_REQS_SRC_T (REQS_SRC_CD,OBJ_ID,VER_NBR,REQS_SRC_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('AUTO','ECE42664-436B-5C9D-84AD-2E6E22925011',1.0,'Auto Ingest','Y')
/
INSERT INTO PUR_REQS_SRC_T (REQS_SRC_CD,OBJ_ID,VER_NBR,REQS_SRC_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('B2B','EA454ED4-C702-5998-5360-FF6C7E55AA82',1.0,'B2B','Y')
/
INSERT INTO PUR_REQS_SRC_T (REQS_SRC_CD,OBJ_ID,VER_NBR,REQS_SRC_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('LGCY','9AF21026-E8A2-1622-F7F9-F2A1EFFB14D6',1.0,'LEGACY','Y')
/
INSERT INTO PUR_REQS_SRC_T (REQS_SRC_CD,OBJ_ID,VER_NBR,REQS_SRC_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('MAN','B9F40BD2-81B0-5AC9-D93A-161D16CBDA00',1.0,'Manual Ingest','Y')
/
INSERT INTO PUR_REQS_SRC_T (REQS_SRC_CD,OBJ_ID,VER_NBR,REQS_SRC_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('STAN','B27A551A-859D-420A-5564-56D7D7EC8218',1.0,'DIRECT INPUT','Y')
/
INSERT INTO PUR_REQS_SRC_T (REQS_SRC_CD,OBJ_ID,VER_NBR,REQS_SRC_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('WEB','88EC56B6-86C8-7197-792A-9D7C219E02A9',1.0,'Web Form','Y')
/
