*.original             = the unmodified version from the original Apache distribution
*.original.minimum.xml = the original version minus comments + formatting
*.xml                  = the version Kuali uses in its production systems

The only modification to web.xml is to set listings=true (enables directory listings)

server.xml is modified in the following ways

1 - Remove the <GlobalNamingResources> and <Realm> snippets related to conf/tomcat-users.xml based authentication
2 - Remove the AJP connector
3 - Set URI encoding to UTF-8
4 - Turn on compression 