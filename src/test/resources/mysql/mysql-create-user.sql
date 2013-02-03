    DROP DATABASE IF EXISTS smartload
    ;
    GRANT USAGE ON *.* TO 'smartload'@'%' IDENTIFIED BY 'smartload'
    ;
    GRANT USAGE ON *.* TO 'smartload'@'localhost' IDENTIFIED BY 'smartload'
    ;
    DROP USER 'smartload'@'%'
    ;
    DROP USER 'smartload'@'localhost'
    ;
    CREATE DATABASE IF NOT EXISTS smartload DEFAULT CHARACTER SET 'utf8' DEFAULT COLLATE 'utf8_bin'
    ;
    CREATE USER 'smartload'@'%' IDENTIFIED BY 'smartload'
    ;
    CREATE USER 'smartload'@'localhost' IDENTIFIED BY 'smartload'
    ;
    GRANT ALL ON smartload.* TO 'smartload'@'%' WITH GRANT OPTION
    ;
    GRANT ALL ON smartload.* TO 'smartload'@'localhost' WITH GRANT OPTION
    ;