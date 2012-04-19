DROP DATABASE IF EXISTS ${sql.database}
/
GRANT USAGE ON *.* TO '${sql.username}'@'%'
IDENTIFIED BY '${sql.password}'
/
GRANT USAGE ON *.* TO '${sql.username}'@'localhost'
IDENTIFIED BY
'${sql.password}'
/
DROP
USER '${sql.username}'@'%'
/
DROP
USER '${sql.username}'@'localhost'
/