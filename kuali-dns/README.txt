

Example CLI commands:


mvn dnsme:showrecords -Ddnsme.recordNameContains=nexus 
mvn dnsme:deleterecord -Ddnsme.recordName=nexus1 
mvn dnsme:addrecord -Ddnsme.recordName=nexus1 -Ddnsme.recordValue=ec2-107-20-15-48.compute-1.amazonaws.com
