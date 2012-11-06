Maven Ingester Plugin
==================================
Maven Ingester Plugin can be called by
 
    ## start mysql db (2.1.0)
    mvn install
    mvn -X  org.kuali.maven.plugins:ingester-maven-plugin:1.0.7-beta-SNAPSHOT:export -DjdbcPassword=ricedev -Dingester.jdbcUsername=ricedev -Dingester.jdbcUrl=http://localhost:3306/ricedev -Dnamespace=KEW -Dingester.jdbcDriver=com.mysql.jdbc.Driver

Changes
----------------------------------
- replaced versions of several jars to property tags
- Updated to use rice 2.1.0 
- Updated spring*.xml file to use spring 3 xsd
- Updated java imports to use new rice api/impl packages
- replaced most of SpringContext; removed all getBean calls

Notes
----------------------------------
ServiceContext and loading of rice for the plugin was based on code/examples in the RiceTestCase and KewTestCase.  

The plugin works by building a seed configuration which creates any necessary datasources, etc... before generating 
the Rice Configuration using the seed.  

TODO
----------------------------------
- Need to get the properties loader to be db agnostic
- Need to test on oracle
- Need to make finish testing reingesting files (can use XmlIngesterTest pointing at the results or the ingester-maven-plugin:ingest)


