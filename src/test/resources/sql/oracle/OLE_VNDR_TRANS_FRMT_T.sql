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

TRUNCATE TABLE OLE_VNDR_TRANS_FRMT_T DROP STORAGE
/
INSERT INTO OLE_VNDR_TRANS_FRMT_T (OLE_VNDR_TRANS_FRMT_ID,OLE_VNDR_TRANS_FRMT,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (1.0,'EDI',1.0,'F685E800-9A8B-ED3D-92D5-405C77F5AD86','Y')
/
INSERT INTO OLE_VNDR_TRANS_FRMT_T (OLE_VNDR_TRANS_FRMT_ID,OLE_VNDR_TRANS_FRMT,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (2.0,'PDF',1.0,'6D81AE8B-C615-1B86-E4B4-FBBB4CDBF32B','Y')
/
