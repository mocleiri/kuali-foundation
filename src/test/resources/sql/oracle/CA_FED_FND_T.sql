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

TRUNCATE TABLE CA_FED_FND_T DROP STORAGE
/
INSERT INTO CA_FED_FND_T (FIN_FED_FUNDED_CD,OBJ_ID,VER_NBR,DOBJ_MAINT_CD_ACTV_IND,FIN_FED_FND_NM)
  VALUES ('F','0CC48498C5C8DF3EE043814FD881DF3E',1.0,'Y','FEDERALLY FUNDED AND OWNED')
/
INSERT INTO CA_FED_FND_T (FIN_FED_FUNDED_CD,OBJ_ID,VER_NBR,DOBJ_MAINT_CD_ACTV_IND,FIN_FED_FND_NM)
  VALUES ('N','0CC48498C5C9DF3EE043814FD881DF3E',1.0,'Y','NOT FEDERALLY FUNDED')
/
INSERT INTO CA_FED_FND_T (FIN_FED_FUNDED_CD,OBJ_ID,VER_NBR,DOBJ_MAINT_CD_ACTV_IND,FIN_FED_FND_NM)
  VALUES ('O','0CC48498C5CADF3EE043814FD881DF3E',1.0,'Y','FEDERALLY FUNDED, UNIVERSITY OWNED')
/
