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

TRUNCATE TABLE FP_CASHIER_ITM_IN_PROC_T DROP STORAGE
/
INSERT INTO FP_CASHIER_ITM_IN_PROC_T (CAMPUS_CD,ITM_ID,OBJ_ID,VER_NBR,ITM_AMT,ITM_TOT_AMT,ITM_OPEN_DT,ITM_DESC)
  VALUES ('NS',354.0,'33816C4EDA64C03EE043814FD881C03E',2.0,20.0,20.0,TO_DATE( '20070618000000', 'YYYYMMDDHH24MISS' ),'test')
/
