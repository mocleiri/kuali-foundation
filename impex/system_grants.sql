
-- these grants are needed since the functions are being used from within a package
-- and the permissions from a role do not propagate to a package

CREATE USER kulusermaint IDENTIFIED BY kulusermaint
 DEFAULT TABLESPACE KUL01
 TEMPORARY TABLESPACE TEMP
 QUOTA UNLIMITED ON KUL01
/

GRANT create session TO KULUSERMAINT WITH ADMIN OPTION
/
GRANT select any dictionary to KULUSERMAINT
/
GRANT EXECUTE on dbms_lock to KULUSERMAINT
/
GRANT alter system to KULUSERMAINT
/
GRANT create table to KULUSERMAINT
/
GRANT create procedure to KULUSERMAINT
/
GRANT alter user to KULUSERMAINT
/
GRANT create user to KULUSERMAINT
/
GRANT drop user TO KULUSERMAINT
/
GRANT kul_developer TO KULUSERMAINT WITH ADMIN OPTION
/
