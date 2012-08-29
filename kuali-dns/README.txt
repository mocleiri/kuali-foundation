
Example CLI commands:

mvn dnsme:showrecords -Ddnsme.recordNameContains=nexus 
mvn dnsme:deleterecord -Ddnsme.recordName=nexus1 
mvn dnsme:addrecord -Ddnsme.recordName=nexus1 -Ddnsme.recordValue=ec2-107-20-15-48.compute-1.amazonaws.com

For A records that map to an IP number

mvn -Pnodot dnsme:addrecord -Ddnsme.recordName=dev.ole -Ddnsme.recordValue=128.18.23.119

