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