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

TRUNCATE TABLE PUR_DLVY_REQ_DT_REAS_T DROP STORAGE
/
INSERT INTO PUR_DLVY_REQ_DT_REAS_T (DLVY_REQ_DT_REAS_CD,OBJ_ID,VER_NBR,DLVY_REQ_DT_REAS_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('BUY','8F9E9467-855B-BC96-2BC4-6FFA9C8727CA',1.0,'ESTIMATED DATE','Y')
/
INSERT INTO PUR_DLVY_REQ_DT_REAS_T (DLVY_REQ_DT_REAS_CD,OBJ_ID,VER_NBR,DLVY_REQ_DT_REAS_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('MUST','0FFE69F4-1F19-007F-763C-217CEE18DB41',3.0,'MUST RECEIVE','Y')
/
INSERT INTO PUR_DLVY_REQ_DT_REAS_T (DLVY_REQ_DT_REAS_CD,OBJ_ID,VER_NBR,DLVY_REQ_DT_REAS_DESC,DOBJ_MAINT_CD_ACTV_IND)
  VALUES ('QUOT','4B9320A7-B43B-67DC-4263-7215F0648E44',1.0,'QUOTED DELIVERY','Y')
/
