CREATE DATABASE IF NOT EXISTS ${sql.database}
DEFAULT CHARACTER SET
'${sql.mysql.character.set}'
DEFAULT COLLATE
'${sql.mysql.collate}'
/

CREATE USER '${sql.username}'@'%' IDENTIFIED
BY '${sql.password}'
/
CREATE USER '${sql.username}'@'localhost' IDENTIFIED
BY '${sql.password}'
/
GRANT ALL ON ${sql.database}.*
TO '${sql.username}'@'%'
WITH GRANT OPTION
/
