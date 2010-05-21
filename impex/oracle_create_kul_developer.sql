
CREATE ROLE KFS_ROLE
/
GRANT CREATE DATABASE LINK TO KFS_ROLE
/
GRANT CREATE MATERIALIZED VIEW TO KFS_ROLE
/
GRANT CREATE PROCEDURE TO KFS_ROLE
/
GRANT CREATE PUBLIC SYNONYM TO KFS_ROLE
/
GRANT CREATE SESSION TO KFS_ROLE
/
GRANT CREATE SYNONYM TO KFS_ROLE
/
GRANT CREATE TABLE TO KFS_ROLE
/
GRANT CREATE TRIGGER TO KFS_ROLE
/
GRANT CREATE VIEW TO KFS_ROLE
/
GRANT RESTRICTED SESSION TO KFS_ROLE
/
GRANT UNLIMITED TABLESPACE TO KFS_ROLE
/

-- create a role for the application user (KFS_USER)

CREATE ROLE kfs_user_role
/
GRANT CREATE SESSION TO KFS_USER_ROLE
/
