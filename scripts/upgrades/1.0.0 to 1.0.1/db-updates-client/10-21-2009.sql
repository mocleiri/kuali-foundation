--
-- Copyright 2009 The Kuali Foundation
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
-- DO NOT add comments before the blank line below, or they will disappear.
ALTER TABLE KRNS_LOOKUP_RSLT_T MODIFY PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KRNS_LOOKUP_SEL_T MODIFY PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KRNS_NTE_T MODIFY AUTH_PRNCPL_ID VARCHAR2(40)
/
ALTER TABLE KRNS_PESSIMISTIC_LOCK_T MODIFY PRNCPL_ID VARCHAR2(40)
/
