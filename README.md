Maven Ingester Plugin
==================================
Maven Ingester Plugin can be called by
 
    mvn -X org.kuali.maven.plugins:ingester-maven-plugin:1.0.7-beta-SNAPSHOT:ingest -DjdbcPassword=ricedev -Dingester.jdbcUsername=ricedev -DjdbcUrl=http://localhost:3306/ -Dnamespace=KEW

Changes
----------------------------------
- replaced versions of several jars to property tags
- Updated to use rice 2.1.0 
- Updated spring*.xml file to use spring 3 xsd
- Updated java imports to use new rice api/impl packages