--
-- Copyright 2010-2014 The Kuali Foundation
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

TRUNCATE TABLE KRMS_NL_USAGE_T DROP STORAGE
/
INSERT INTO KRMS_NL_USAGE_T (ACTV,DESC_TXT,NL_USAGE_ID,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','Kuali Rule Edit','10000','kuali.krms.edit','KS-SYS',0)
/
INSERT INTO KRMS_NL_USAGE_T (ACTV,DESC_TXT,NL_USAGE_ID,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','Kuali Rule Composition','10001','kuali.krms.composition','KS-SYS',0)
/
INSERT INTO KRMS_NL_USAGE_T (ACTV,DESC_TXT,NL_USAGE_ID,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','Kuali Rule Example','10002','kuali.krms.example','KS-SYS',0)
/
INSERT INTO KRMS_NL_USAGE_T (ACTV,DESC_TXT,NL_USAGE_ID,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','Kuali Rule Preview','10003','kuali.krms.preview','KS-SYS',0)
/
INSERT INTO KRMS_NL_USAGE_T (ACTV,DESC_TXT,NL_USAGE_ID,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','Kuali Rule Type Description','10004','kuali.krms.type.description','KS-SYS',0)
/
