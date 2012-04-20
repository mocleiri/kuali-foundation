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

-- Create a user, give them a password and set a quota for the default tablespace
CREATE USER ${database} 
IDENTIFIED BY ${databasePassword} 
DEFAULT TABLESPACE ${oracle.tablespace.default} 
TEMPORARY TABLESPACE ${oracle.tablespace.temporary} 
QUOTA ${oracle.tablespace.default.quota} ON ${oracle.tablespace.default}
/

-- Grant permissions as needed
GRANT CREATE PROCEDURE
  , CREATE SEQUENCE
  , CREATE SESSION
  , CREATE SYNONYM
  , CREATE TABLE
  , CREATE TRIGGER
  , CREATE TYPE
  , CREATE VIEW 
TO ${database}
/
