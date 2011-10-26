DROP DATABASE IF EXISTS ${sql.database}
/
-- ${sql.username} @ ${sql.password}
-- MySQL does not have DROP USER IF EXISTS, so we grant permissions on *.*
-- This creates a user if they don't exist (but doesn't error out if they already do)
-- Them immediately drop them
GRANT USAGE ON *.* TO '${sql.username}'@'%' IDENTIFIED BY '${sql.password}'
/
DROP USER ${sql.username}
/
