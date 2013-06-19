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

TRUNCATE TABLE CA_SUB_FND_GRP_TYP_T
/
INSERT INTO CA_SUB_FND_GRP_TYP_T (SUB_FUNDGRP_TYP_CD,OBJ_ID,VER_NBR,SUB_FND_GRP_TYP_DESC,SUB_FND_GRP_TYP_ACTV_IND)
  VALUES ('A','18660FE81BDCA0EEE043814FD881A0EE',1.0,'AUXILIARY','Y')
/
INSERT INTO CA_SUB_FND_GRP_TYP_T (SUB_FUNDGRP_TYP_CD,OBJ_ID,VER_NBR,SUB_FND_GRP_TYP_DESC,SUB_FND_GRP_TYP_ACTV_IND)
  VALUES ('I','18660FE81BDDA0EEE043814FD881A0EE',1.0,'INTERNAL SERVICE ORGANIZATION','Y')
/
INSERT INTO CA_SUB_FND_GRP_TYP_T (SUB_FUNDGRP_TYP_CD,OBJ_ID,VER_NBR,SUB_FND_GRP_TYP_DESC,SUB_FND_GRP_TYP_ACTV_IND)
  VALUES ('N','18660FE81BDEA0EEE043814FD881A0EE',1.0,'NEITHER','Y')
/
