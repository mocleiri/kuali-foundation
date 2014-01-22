#!/bin/ksh

numENV=15
GROUP=rice

echo
echo > scrape.out

function get_info {
databaseURL=http://env$COUNT.$GROUP.kuali.org/home/kuali/main/dev/common-config.xml
versionURL=http://env$COUNT.$GROUP.kuali.org/tomcat/webapps/ROOT/META-INF/MANIFEST.MF
getDB=""
getDBtype=""
getAPP=""
getVersion=""
getJDK=""
getDate=""

#curl $URL | grep -i Sample
#VER=`curl -L -s $URL  | grep -i sample |grep -v viewId | grep -E "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}" | grep -v style`
getDB=$(curl -L -s $databaseURL  | grep -i datasource.username|tr -d '\n')
getDBtype=$(curl -L -s $databaseURL  | grep -i datasource.url|tr -d '\n')
getAPP=$(curl -L -s $versionURL  | grep -i Bundle-SymbolicName|tr -d '\n')
getVersion=$(curl -L -s $versionURL  | grep -i Bundle-Version|tr -d '\n')
getJDK=$(curl -L -s $versionURL  | grep -i Build-Jdk|tr -d '\n')
getDate=$(curl -L -s $versionURL  | grep -i Bundle-Timestamp|tr -d '\n')

myDate=$(echo "$getDate" | sed -n 's/[^\:]*\://p' | sed -e 's/^[ \t]*//' | tr -d '\r')
myVersion=$(echo "$getVersion" | sed -n 's/[^\:]*\://p' | sed -e 's/^[ \t]*//' | tr -d '\r')
myJDK=$(echo "$getJDK" | sed -n 's/[^\:]*\://p' | sed -e 's/^[ \t]*//' | tr -d '\r')
myDB=$(echo "$getDB" | sed -n 's/[^\>]*\>//p' |sed -e 's/\<[^\<]*$//' | sed -e 's/^[ \t]*//' | tr -d '\r')
myAPP=$(echo "$getAPP" | sed -n s/.*kuali.rice.//p | sed -e 's/^[ \t]*//' | tr -d '\r')


dbType01="oracle"
dbType02="mysql"
if [[ "$getDBtype" == *${dbType01}* ]]; then
  myDBtype="Oracle"
elif [[ "$getDBtype" == *${dbType02}* ]]; then
  myDBtype="MySQL"
else
  myDBtype="Unknown"
fi

}
##  End get_info

while [[ $COUNT -lt $numENV ]];do
((COUNT=COUNT+1))
URL="http://env$COUNT.$GROUP.kuali.org"
#echo $URL

HTTP_CODE=$(curl -m 10 -Is $URL | grep HTTP | cut -d ' ' -f2|tr -d '\n')
#HTTP_CODE="302"

if [[ $HTTP_CODE != "302" ]];then
  echo "$URL - Not Available - HTTP Code: $HTTP_CODE"
  #elif [[ $myVer = "" ]];then
  #  echo "$URL - HTTP Code: $HTTP_CODE"
    print "No versioning available"
    print -n $COUNT >> scrape.out
    print -n "," >> scrape.out
    print -n $URL >> scrape.out
    print -n "," >> scrape.out
    #print -n $myVersion >> scrape.out
    print -n "," >> scrape.out
    #print -n $myAPP >> scrape.out
    print -n "," >> scrape.out
    #print -n $myJDK >> scrape.out
    print -n "," >> scrape.out
    #print -n $myDBtype >> scrape.out
    print -n "," >> scrape.out
    #print -n $myDate >> scrape.out
    print -n "," >> scrape.out
    print -n "Not Available" >> scrape.out
    print >> scrape.out
    
  else
    get_info
    print "Number      = $COUNT"
    print "$URL - HTTP Code: $HTTP_CODE"
    print "Application = $myAPP"
    print "Version     = $myVersion"
    print "JDK         = $myJDK"
    print "DB Type     = $myDBtype"
    print "DB Name     = $myDB"
    print "Date        = $myDate"
    print "Status      = OK"

print
print -n $COUNT >> scrape.out
print -n "," >> scrape.out
print -n $URL >> scrape.out
print -n "," >> scrape.out
print -n $myVersion >> scrape.out
print -n "," >> scrape.out
print -n $myAPP >> scrape.out
print -n "," >> scrape.out
print -n $myJDK >> scrape.out
print -n "," >> scrape.out
print -n $myDBtype >> scrape.out
print -n "," >> scrape.out
print -n $myDate >> scrape.out
print -n "," >> scrape.out
print -n "OK" >> scrape.out
print >> scrape.out

fi

#echo "###############################"
echo
done

echo "scrape.out"
cat scrape.out
echo
