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

TRUNCATE TABLE OLE_CAT_TYPE_OWNERSHIP_T
/
INSERT INTO OLE_CAT_TYPE_OWNERSHIP_T (TYPE_OWNERSHIP_ID,OBJ_ID,VER_NBR,TYPE_OWNERSHIP_CD,TYPE_OWNERSHIP_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (5.0,'4dcdf94f-fc19-40f4-86b6-9a3a66d42bda',3.0,'Textual Holdings - Basic Bibliographic Unit','Textual Holdings - Basic Bibliographic Unit','(enumeration in schema) http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120504000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_TYPE_OWNERSHIP_T (TYPE_OWNERSHIP_ID,OBJ_ID,VER_NBR,TYPE_OWNERSHIP_CD,TYPE_OWNERSHIP_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (6.0,'5d1f9f6f-a53e-4ffc-b65c-91ace3d84fd3',3.0,'Textual Holdings - Supplementary Material','Textual Holdings - Supplementary Material','(enumeration in schema) http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120504000000', '%Y%m%d%H%i%s' ),'Y')
/
INSERT INTO OLE_CAT_TYPE_OWNERSHIP_T (TYPE_OWNERSHIP_ID,OBJ_ID,VER_NBR,TYPE_OWNERSHIP_CD,TYPE_OWNERSHIP_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES (7.0,'ff6aa12d-beec-4a09-a526-bc215aa1c9bf',3.0,'Textual Holdings - Indexes','Textual Holdings - Indexes','(enumeration in schema) http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120504000000', '%Y%m%d%H%i%s' ),'Y')
/
