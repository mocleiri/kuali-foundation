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

TRUNCATE TABLE OLE_VNDR_ALIAS_TYP_T DROP STORAGE
/
INSERT INTO OLE_VNDR_ALIAS_TYP_T (OLE_ALIAS_TYP_ID,OLE_ALIAS_TYP,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (1.0,'Vendor Code',1.0,'0B6DD568-6653-CFFC-EB29-ECBB397A811E','Y','Library specific code for a vendor.')
/
INSERT INTO OLE_VNDR_ALIAS_TYP_T (OLE_ALIAS_TYP_ID,OLE_ALIAS_TYP,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (2.0,'External Vendor Code',1.0,'72194C09-0511-3F2B-6B86-332F57952B5F','Y','Code used by the librarys parent organization for this vendor')
/
INSERT INTO OLE_VNDR_ALIAS_TYP_T (OLE_ALIAS_TYP_ID,OLE_ALIAS_TYP,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (3.0,'Abbreviation',1.0,'AB5FF529-F651-6304-DE25-4D0F2B9D1F17','Y','Short form of vendor name')
/
INSERT INTO OLE_VNDR_ALIAS_TYP_T (OLE_ALIAS_TYP_ID,OLE_ALIAS_TYP,VER_NBR,OBJ_ID,ROW_ACT_IND,DESC_TXT)
  VALUES (4.0,'Standard Address Numbers',1.0,'BD08353F-503C-DEF8-A17F-466FE71BE683','Y','The Standard Address Number (SAN) is a unique seven-digit identifier used to signify a specific address of an organization in (or served by) the publishing industry. It is an American National Standard, ANSI/NISO Z39.43-1993.')
/
