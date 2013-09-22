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

TRUNCATE TABLE CA_ICR_EXCL_ACCT_T DROP STORAGE
/
INSERT INTO CA_ICR_EXCL_ACCT_T (FIN_COA_CD,ACCOUNT_NBR,FIN_OBJ_COA_CD,FIN_OBJECT_CD,OBJ_ID,VER_NBR)
  VALUES ('BA','6044900','BA','9900','0CC48498C5CCDF3EE043814FD881DF3E',1.0)
/
