@echo off
echo %DATE% %TIME%
call mvn clean
call mvn process-resources -f pom-export.xml -Pstudent,oracle
call mvn process-resources -f pom-export.xml -Price,oracle
call mvn process-resources -f pom-export.xml -Price,mysql
call mvn process-resources -f pom-sql-gen.xml -Pstudent,oracle
call mvn process-resources -f pom-sql-gen.xml -Price,oracle
call mvn process-resources -f pom-sql-gen.xml -Price,mysql
call mvn process-resources -f pom-sql-exec.xml -Price,oracle
call mvn process-resources -f pom-sql-exec.xml -Price,mysql
echo %DATE% %TIME%
