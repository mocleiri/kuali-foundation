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

TRUNCATE TABLE GL_SF_REBUILD_T
/
INSERT INTO GL_SF_REBUILD_T (FIN_COA_CD,ACCT_FOBJ_TYP_CD,ACCT_NBR_FOBJ_CD,OBJ_ID,VER_NBR)
  VALUES ('BL','A','1031420','0CD74F3CD377043EE043814FD881043E',1.0)
/
