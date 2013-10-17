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

TRUNCATE TABLE PDP_PMT_TYP_T DROP STORAGE
/
INSERT INTO PDP_PMT_TYP_T (PMT_TYP_CD,OBJ_ID,VER_NBR,PMT_TYP_DESC)
  VALUES ('all','59DD323FB199B3EFE0404F8189D83BC1',1.0,'All Payment Types')
/
INSERT INTO PDP_PMT_TYP_T (PMT_TYP_CD,OBJ_ID,VER_NBR,PMT_TYP_DESC)
  VALUES ('immediate','59DD323FB19EB3EFE0404F8189D83BC1',1.0,'Only Disbursements Flagged as Immediate')
/
INSERT INTO PDP_PMT_TYP_T (PMT_TYP_CD,OBJ_ID,VER_NBR,PMT_TYP_DESC)
  VALUES ('pymtAttachment','59DD323FB19AB3EFE0404F8189D83BC1',1.0,'Only Disbursements with Attachments')
/
INSERT INTO PDP_PMT_TYP_T (PMT_TYP_CD,OBJ_ID,VER_NBR,PMT_TYP_DESC)
  VALUES ('pymtAttachmentFalse','59DD323FB19BB3EFE0404F8189D83BC1',1.0,'Only Disbursements with No Attachments')
/
INSERT INTO PDP_PMT_TYP_T (PMT_TYP_CD,OBJ_ID,VER_NBR,PMT_TYP_DESC)
  VALUES ('pymtSpecialHandling','59DD323FB19CB3EFE0404F8189D83BC1',1.0,'Only Disbursements with Special Handling')
/
INSERT INTO PDP_PMT_TYP_T (PMT_TYP_CD,OBJ_ID,VER_NBR,PMT_TYP_DESC)
  VALUES ('pymtSpecialHandlingFalse','59DD323FB19DB3EFE0404F8189D83BC1',1.0,'Only Disbursements with No Special Handling')
/
