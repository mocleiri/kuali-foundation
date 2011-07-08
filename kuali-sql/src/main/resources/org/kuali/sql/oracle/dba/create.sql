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
 