DROP DATABASE IF EXISTS ${sql.database}
/
-- MySQL does not have DROP USER IF EXISTS, so we grant permissions on *.*
-- which creates the user if they don't exist (and doesn't error out if
-- they already exist) then immediately drop them
GRANT USAGE ON *.* TO '${sql.username}'@'%' IDENTIFIED BY '${sql.password}'
/
DROP USER ${sql.username}
/
