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

SELECT a.table_name
, a.constraint_name
, a.column_name
, a.position
, c.constraint_type
FROM ALL_CONS_COLUMNS A
 , all_constraints c
where a.table_name like 'KS%'
and a.constraint_name = c.constraint_name
and c.constraint_type='R'
and a.owner = 'KS_SOURCE_DB_SPRING'
order by a.table_name, a.constraint_name, a.position


select * 
from 