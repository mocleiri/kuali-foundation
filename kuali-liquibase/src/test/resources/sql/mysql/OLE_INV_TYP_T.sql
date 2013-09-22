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

TRUNCATE TABLE OLE_INV_TYP_T
/
INSERT INTO OLE_INV_TYP_T (OLE_INV_TYP_ID,INV_TYP,INV_TYP_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (1.0,'Standard','Standard',1.0,'5141CDDC-4491-D325-A712-5075AFDEE469','Y')
/
INSERT INTO OLE_INV_TYP_T (OLE_INV_TYP_ID,INV_TYP,INV_TYP_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (2.0,'Internal','Internal',1.0,'AC753E5C-371C-DC9E-F226-79E17AA1D080','Y')
/
INSERT INTO OLE_INV_TYP_T (OLE_INV_TYP_ID,INV_TYP,INV_TYP_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (3.0,'Prepay','Prepay',1.0,'695801C2-A252-53F2-85FC-D0B67233DBBF','Y')
/
INSERT INTO OLE_INV_TYP_T (OLE_INV_TYP_ID,INV_TYP,INV_TYP_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (4.0,'Correction','Correction',1.0,'DE46828B-603D-BDC6-5484-420734B6895B','Y')
/
INSERT INTO OLE_INV_TYP_T (OLE_INV_TYP_ID,INV_TYP,INV_TYP_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (5.0,'Proforma','Proforma',1.0,'E551FFE8-2285-0335-3253-1ACBA4C642AA','Y')
/
INSERT INTO OLE_INV_TYP_T (OLE_INV_TYP_ID,INV_TYP,INV_TYP_DESC,VER_NBR,OBJ_ID,ROW_ACT_IND)
  VALUES (6.0,'Deposit','Deposit',1.0,'6','Y')
/
