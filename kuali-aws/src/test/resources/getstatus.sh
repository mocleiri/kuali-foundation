#!/bin/ksh

#numENV=15
#GROUP=rice


############################################################

# Clear out existing csv files

# Check to make sure an environment file containing URLs is specified.
if [[ $# -eq 0 ]];then
   print "No environments file specfified."
   print "Example:"
   print "    getstatus.sh rice.env"
   print
   exit 1
fi

# Check to make sure environment file exists.
if [ ! -f $1 ]; then
    echo "File not found! - $1"
    exit 1
fi

## Begin get_info()
function get_info {
    #databaseURL=http://env$COUNT.$GROUP.kuali.org/home/kuali/main/dev/common-config.xml
    #versionURL=http://env$COUNT.$GROUP.kuali.org/tomcat/webapps/ROOT/META-INF/MANIFEST.MF
    versionURL=http://$URL/tomcat/webapps/ROOT/META-INF/MANIFEST.MF
    #tomcatURL=http://$URL/tomcat/logs/env.jsp
    if [[ $myGroup == "ole" ]];then
      if [[ $URL == *dev* ]];then
        databaseURL=http://$URL/home/kuali/main/dev/common-config.xml
      elif [[ $URL == *qa* ]];then
        databaseURL=http://$URL/home/kuali/main/qa/common-config.xml
      elif [[ $URL == *demo* ]];then
        databaseURL=http://$URL/home/kuali/main/demo/common-config.xml
      elif [[ $URL == *tst* ]];then
        databaseURL=http://$URL/home/kuali/main/tst/common-config.xml
      else
        databaseURL=http://$URL/home/kuali/main/dev/common-config.xml
      fi
    elif [[ $myGroup == "ks" ]]; then
      getAPP=$(curl -L -s $versionURL  | grep -i Bundle-SymbolicName|tr -d '\n')
      if [[ $getAPP == *ks-with-rice-bundled* ]];then
        databaseURL=http://$URL/home/kuali/main/dev/ks-with-rice-bundled-config.xml
      elif [[ $getAPP == *ks-rice-standalone* ]];then
        databaseURL=http://$URL/home/kuali/main/dev/ks-rice-standalone-config.xml
      elif [[ $getAPP == *ks-with-rice-embedded* ]];then
        databaseURL=http://$URL/home/kuali/main/dev/ks-with-rice-embedded-config.xml
      else
        databaseURL=http://$URL/home/kuali/main/dev/common-config.xml
      fi
    else
      databaseURL=http://$URL/home/kuali/main/dev/common-config.xml
    fi


    #VER=`curl -L -s $URL  | grep -i sample |grep -v viewId | grep -E "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}" | grep -v style`
    getDB=$(curl -L -s $databaseURL  | grep -i "\"datasource.username"|tr -d '\n')
    #getDBcore=$(curl -L -s $databaseURL  | grep -i "\"core.datasource.username"|tr -d '\n')
    getDBtype=""
    getDBtype=$(curl -L -s $databaseURL  | grep -i db.vendor|tr -d '\n')
    if [[ $getDBtype == "" ]];then
      getDBtype=$(curl -L -s $databaseURL  | grep -i datasource.url|tr -d '\n')
    fi
    getAPP=$(curl -L -s $versionURL  | grep -i Bundle-SymbolicName|tr -d '\n')
    getVersion=$(curl -L -s $versionURL  | grep -i Bundle-Version|tr -d '\n')
    getJDK=$(curl -L -s $versionURL  | grep -i Build-Jdk|tr -d '\n')
    getDate=$(curl -L -s $versionURL  | grep -i Bundle-Timestamp|tr -d '\n')
    #getMX=$(curl -L -s $tomcatURL  | grep -i Xmx|tr -d '\n')
    
    myDate=$(echo "$getDate" | sed -n 's/[^\:]*\://p' | sed -e 's/^[ \t]*//' | tr -d '\r')
    myVersion=$(echo "$getVersion" | sed -n 's/[^\:]*\://p' | sed -e 's/^[ \t]*//' | tr -d '\r')
    myJDK=$(echo "$getJDK" | sed -n 's/[^\:]*\://p' | sed -e 's/^[ \t]*//' | tr -d '\r')
    #myDB=$(echo "$getDB" | sed -n 's/[^\>]*[\>]//p' |sed -e 's/[\<][^\<]*$//' | sed -e 's/^[ \t]*//' | tr -d '\r')
    myDB=$(echo "$getDB" | sed -n 's/[^\>]*[\>]//p' |sed -e 's/[\<][^\<]*$//' | sed -e 's/^[ \t]*//' | tr -d '\r')
    myAPP=$(echo "$getAPP" | sed -n s/.*kuali.rice.//p | sed -e 's/^[ \t]*//' | tr -d '\r')
    #myMX=$(echo "$getMX" | sed -e 's/^[Xmx]//' |sed -e 's/[^\<]*$//' | sed -e 's/^[ \t]*//' | tr -d '\r')

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
##  End get_info()

#while [[ $COUNT -lt $numENV ]];do
while read URL;do
((COUNT=COUNT+1))

# Clear out the existing csv file
if [[ $COUNT -eq 1 ]];then 
  echo > $myGroup.csv
fi

if [[ "$URL" == *rice.kuali.org* ]]; then
   myGroup="rice"
elif [[ "$URL" == *ks.kuali.org* ]]; then
   myGroup="ks"
elif [[ "$URL" == *ole.kuali.org* ]]; then
   myGroup="ole"
else
   exit 1
fi

#URL="http://env$COUNT.$GROUP.kuali.org"
#URL="http://$URL"

HTTP_CODE=$(curl -m 10 -Is $URL | grep HTTP | cut -d ' ' -f2|tr -d '\n')
#HTTP_CODE="302"

if [[ $HTTP_CODE != "302" ]] && [[ $HTTP_CODE != "200" ]];then
    echo "$URL - Not Available - HTTP Code: $HTTP_CODE"
    print "No versioning available"
    print -n $COUNT >> $myGroup.csv
    print -n "," >> $myGroup.csv
    print -n http://$URL >> $myGroup.csv
    print -n "," >> $myGroup.csv
    #print -n $myVersion >> $myGroup.csv
    print -n "," >> $myGroup.csv
    #print -n $myAPP >> $myGroup.csv
    print -n "," >> $myGroup.csv
    #print -n $myJDK >> $myGroup.csv
    print -n "," >> $myGroup.csv
    #print -n $myDBtype >> $myGroup.csv
    print -n "," >> $myGroup.csv
    #print -n $myDate >> $myGroup.csv
    print -n "," >> $myGroup.csv
    print -n "NA" >> $myGroup.csv
    print >> $myGroup.csv
  else
    get_info
    print "Number      = $COUNT"
    print "$URL - HTTP Code: $HTTP_CODE"
    print "Group       = $myGroup"
    print "Application = $myAPP"
    print "Version     = $myVersion"
    print "JDK         = $myJDK"
    print "DB Type     = $myDBtype"
    print "DB Name     = $myDB"
    print "Date        = $myDate"
    print "Status      = OK"
    print -n $COUNT >> $myGroup.csv
    print -n "," >> $myGroup.csv
    print -n http://$URL >> $myGroup.csv
    print -n "," >> $myGroup.csv
    print -n $myVersion >> $myGroup.csv
    print -n "," >> $myGroup.csv
    print -n $myAPP >> $myGroup.csv
    print -n "," >> $myGroup.csv
    print -n $myJDK >> $myGroup.csv
    print -n "," >> $myGroup.csv
    print -n $myDBtype >> $myGroup.csv
    print -n "," >> $myGroup.csv
    print -n $myDB >> $myGroup.csv
    print -n "," >> $myGroup.csv
    print -n $myDate >> $myGroup.csv
    print -n "," >> $myGroup.csv
    print -n "OK" >> $myGroup.csv
    print >> $myGroup.csv
fi
echo
done <"$1"
echo
