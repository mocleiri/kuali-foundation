--
-- Copyright 2010-2013 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

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