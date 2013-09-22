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

TRUNCATE TABLE OLE_LIC_RQMT_T DROP STORAGE
/
INSERT INTO OLE_LIC_RQMT_T (LIC_RQMT_CD,LIC_RQMT_DESC,OBJ_ID,VER_NBR,ACT_IND)
  VALUES ('ILR','Initiate License Request ','1',1.0,'Y')
/
INSERT INTO OLE_LIC_RQMT_T (LIC_RQMT_CD,LIC_RQMT_DESC,OBJ_ID,VER_NBR,ACT_IND)
  VALUES ('LRC','License Request Complete ','3',1.0,'Y')
/
INSERT INTO OLE_LIC_RQMT_T (LIC_RQMT_CD,LIC_RQMT_DESC,OBJ_ID,VER_NBR,ACT_IND)
  VALUES ('NF','Negotiation Failed ','4',1.0,'Y')
/
INSERT INTO OLE_LIC_RQMT_T (LIC_RQMT_CD,LIC_RQMT_DESC,OBJ_ID,VER_NBR,ACT_IND)
  VALUES ('NLR','No License Required','2',1.0,'Y')
/
