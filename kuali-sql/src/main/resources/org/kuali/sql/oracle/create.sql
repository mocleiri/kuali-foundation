--
-- Copyright 2004-2012 The Kuali Foundation
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

CREATE USER ${sql.username}
IDENTIFIED BY ${sql.password}
DEFAULT TABLESPACE ${sql.oracle.defaultTablespace}
TEMPORARY TABLESPACE ${sql.oracle.temporaryTablespace}
QUOTA ${sql.oracle.quota} ON ${sql.oracle.defaultTablespace}
/

GRANT CREATE PROCEDURE
 , CREATE SEQUENCE
 , CREATE SESSION
 , CREATE SYNONYM
 , CREATE TABLE
 , CREATE TRIGGER
 , CREATE TYPE
 , CREATE VIEW
 TO ${sql.username}
/
 