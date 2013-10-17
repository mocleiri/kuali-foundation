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

TRUNCATE TABLE PDP_ACH_BNK_T
/
INSERT INTO PDP_ACH_BNK_T (BNK_RTNG_NBR,OBJ_ID,VER_NBR,BNK_OFFC_CD,BNK_SRVC_NBR,BNK_TYP_CD,BNK_NEW_RTNG_NBR,BNK_NM,BNK_STR_ADDR,BNK_CTY_NM,BNK_ST_CD,BNK_ZIP_CD,BNK_PHN_AREA_CD,BNK_PHN_PRFX_NBR,BNK_PHN_SFX_NBR,BNK_INST_STAT_CD,BNK_DTA_V_CD,ACTV_IND)
  VALUES ('012345678','7F39FA5C-E463-B231-CB1C-FAFF209AC453',3.0,'O','011000015','1','000000001','MY FRIENDLY NEIGHBORHOOD BANK','1000 PEACHTREE ST N.E.','ATLANTA','GA','30309','866','234','5681','1','1','Y')
/
INSERT INTO PDP_ACH_BNK_T (BNK_RTNG_NBR,OBJ_ID,VER_NBR,BNK_OFFC_CD,BNK_SRVC_NBR,BNK_TYP_CD,BNK_NEW_RTNG_NBR,BNK_NM,BNK_STR_ADDR,BNK_CTY_NM,BNK_ST_CD,BNK_ZIP_CD,BNK_PHN_AREA_CD,BNK_PHN_PRFX_NBR,BNK_PHN_SFX_NBR,BNK_INST_STAT_CD,BNK_DTA_V_CD,ACTV_IND)
  VALUES ('876543210','C0307043-45F0-C065-B20E-49D7AD5FD639',1.0,'B','011000017','1','000000002','YOUR PERSONAL CREDIT UNION','1776 HERITAGE DR (JAB 3S3)','NORTH QUINCY','MA','02171','617','664','2400','1','1','Y')
/
