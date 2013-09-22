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

TRUNCATE TABLE AP_CRDT_MEMO_STAT_T DROP STORAGE
/
INSERT INTO AP_CRDT_MEMO_STAT_T (CRDT_MEMO_STAT_CD,OBJ_ID,VER_NBR,CRDT_MEMO_STAT_DESC)
  VALUES ('APAD','35B3A59FDC87719AE043814FD881719A',2.0,'Awaiting AP Review')
/
INSERT INTO AP_CRDT_MEMO_STAT_T (CRDT_MEMO_STAT_CD,OBJ_ID,VER_NBR,CRDT_MEMO_STAT_DESC)
  VALUES ('CANC','5460B05B-957A-D43A-6A88-24B8F0A4A874',2.0,'Cancelled')
/
INSERT INTO AP_CRDT_MEMO_STAT_T (CRDT_MEMO_STAT_CD,OBJ_ID,VER_NBR,CRDT_MEMO_STAT_DESC)
  VALUES ('CIPR','35B3A59FDC84719AE043814FD881719A',4.0,'Cancelled - In Process')
/
INSERT INTO AP_CRDT_MEMO_STAT_T (CRDT_MEMO_STAT_CD,OBJ_ID,VER_NBR,CRDT_MEMO_STAT_DESC)
  VALUES ('CMPT','38A20D30-955A-A862-CCB7-68C5A50E83BE',1.0,'Complete')
/
INSERT INTO AP_CRDT_MEMO_STAT_T (CRDT_MEMO_STAT_CD,OBJ_ID,VER_NBR,CRDT_MEMO_STAT_DESC)
  VALUES ('INIT','35B3A59FDC86719AE043814FD881719A',2.0,'Initiated')
/
INSERT INTO AP_CRDT_MEMO_STAT_T (CRDT_MEMO_STAT_CD,OBJ_ID,VER_NBR,CRDT_MEMO_STAT_DESC)
  VALUES ('INPR','BB71414E-C7D0-A399-08C1-CE0871AE4296',1.0,'In Process')
/
