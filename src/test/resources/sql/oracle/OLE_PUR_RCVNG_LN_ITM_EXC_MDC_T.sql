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

TRUNCATE TABLE OLE_PUR_RCVNG_LN_ITM_EXC_MDC_T DROP STORAGE
/
INSERT INTO OLE_PUR_RCVNG_LN_ITM_EXC_MDC_T (OLE_RCVNG_LN_ITM_EXC_MDOC_ID,OLE_RCVNG_LN_ITM_EXC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (1.0,'Received-damaged',1.0,'74EC89C2-9A4F-B76A-F34C-129B9991D64D','Y')
/
INSERT INTO OLE_PUR_RCVNG_LN_ITM_EXC_MDC_T (OLE_RCVNG_LN_ITM_EXC_MDOC_ID,OLE_RCVNG_LN_ITM_EXC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (2.0,'Received-wrong edition',1.0,'A46EA409-A1F0-6909-DD1C-95B072C2727A','Y')
/
INSERT INTO OLE_PUR_RCVNG_LN_ITM_EXC_MDC_T (OLE_RCVNG_LN_ITM_EXC_MDOC_ID,OLE_RCVNG_LN_ITM_EXC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (3.0,'Received-wrong format',1.0,'B1FDB26C-1155-367D-F7F6-532833EEDF66','Y')
/
INSERT INTO OLE_PUR_RCVNG_LN_ITM_EXC_MDC_T (OLE_RCVNG_LN_ITM_EXC_MDOC_ID,OLE_RCVNG_LN_ITM_EXC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (4.0,'Received-other problem',1.0,'1684A67B-31EB-3F0A-5472-4137EF53CAAD','Y')
/
