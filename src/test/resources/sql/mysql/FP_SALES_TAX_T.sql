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

TRUNCATE TABLE FP_SALES_TAX_T
/
INSERT INTO FP_SALES_TAX_T (FDOC_NBR,FDOC_LN_TYP_CD,FDOC_LINE_NBR,OBJ_ID,VER_NBR,FIN_COA_CD,ACCOUNT_NBR,FDOC_GRS_SALES_AMT,FDOC_TXBL_SALES_AMT,FDOC_SALE_DT)
  VALUES ('QU9084604','F',3.0,'13FEFBF14ED141AAE043814FD88141AA',1.0,'BA','6040000',-380.0,-380.0,STR_TO_DATE( '20040401000000', '%Y%m%d%H%i%s' ))
/
INSERT INTO FP_SALES_TAX_T (FDOC_NBR,FDOC_LN_TYP_CD,FDOC_LINE_NBR,OBJ_ID,VER_NBR,FIN_COA_CD,ACCOUNT_NBR,FDOC_GRS_SALES_AMT,FDOC_TXBL_SALES_AMT,FDOC_SALE_DT)
  VALUES ('YF2857804','F',3.0,'13FEFBF14ED241AAE043814FD88141AA',1.0,'BA','6040000',-75.85,-75.85,STR_TO_DATE( '20040501000000', '%Y%m%d%H%i%s' ))
/
