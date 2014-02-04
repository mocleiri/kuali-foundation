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

TRUNCATE TABLE FP_DV_MLG_T
/
INSERT INTO FP_DV_MLG_T (DV_MLG_EFF_DT,DV_MLG_LMT_AMT,OBJ_ID,VER_NBR,DV_MLG_RT)
  VALUES (STR_TO_DATE( '20030701000000', '%Y%m%d%H%i%s' ),0.0,'0D9056D0301839FEE043814FD88139FE',1.0,0.375)
/
INSERT INTO FP_DV_MLG_T (DV_MLG_EFF_DT,DV_MLG_LMT_AMT,OBJ_ID,VER_NBR,DV_MLG_RT)
  VALUES (STR_TO_DATE( '20030701000000', '%Y%m%d%H%i%s' ),500.0,'0D9056D0301939FEE043814FD88139FE',1.0,0.18)
/
INSERT INTO FP_DV_MLG_T (DV_MLG_EFF_DT,DV_MLG_LMT_AMT,OBJ_ID,VER_NBR,DV_MLG_RT)
  VALUES (STR_TO_DATE( '20030701000000', '%Y%m%d%H%i%s' ),3000.0,'0D9056D0301A39FEE043814FD88139FE',1.0,0.0)
/
