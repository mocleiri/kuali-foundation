#!/bin/ksh

# Usage:
# getstatus.sh file.env
#
# file.env contains FQDNs of servers/urls to scan.
# ie:
# env1.rice.kuali.edu
# env2.rice.kuali.edu
#
# getstatus.sh will output to a csv file the following:
# Number,URL,BuildVersion,BuildName,JDKversion,DBvendor,DBusername,BuildDate,Status
#
#  For instance:
# 1,http://env1.rice.kuali.org,2.4.0-M4-r44288,rice-sampleapp,1.6.0_43,MySQL,RICEENV1,2014-01-23 06:55 UTC,OK

# Location where to place the output files
DropLocation="./target/env"

############################################################

# Check to see if target directory exists.  If not, create it.
if [ ! -d $DropLocation ]; then
    print "$DropLocation does not exist!"
    print "Making dir:  $DropLocation"
    mkdir -p $DropLocation
fi

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
    # MANIFEST.MF contains app/jdk versioning information
    versionURL=http://$URL/tomcat/webapps/ROOT/META-INF/MANIFEST.MF

    # env.jsp contains Tomcat information
    #tomcatURL=http://$URL/tomcat/logs/env.jsp

    # *-config.xml contains DB information
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

    # Find Database username
    getDB=$(curl -L -s $databaseURL  | grep -i "\"datasource.username"|tr -d '\n')
    if [[ $myGroup == "ole" ]];then
      getDB=$(curl -L -s $databaseURL  | grep -i "\"jdbc.username"|tr -d '\n')
    else
      getDB=$(curl -L -s $databaseURL  | grep -i "\"datasource.username"|tr -d '\n')
    fi
      myDB=$(echo "$getDB" | sed -n 's/[^\>]*[\>]//p' |sed -e 's/[\<][^\<]*$//' | sed -e 's/^[ \t]*//' | tr -d '\r')

    # Find Database type (Oracle, MySQL, etc)
    getDBtype=""
    getDBtype=$(curl -L -s $databaseURL  | grep -i db.vendor|tr -d '\n')
    if [[ $getDBtype == "" ]];then
      getDBtype=$(curl -L -s $databaseURL  | grep -i datasource.url|tr -d '\n')
    fi

    # Find Application name
    getAPP=$(curl -L -s $versionURL  | grep -i Bundle-SymbolicName|tr -d '\n')
    if [[ $myGroup == "ks" ]];then
      myAPP=$(echo "$getAPP" | sed -n s/.*kuali.student.web.//p | sed -e 's/^[ \t]*//' | tr -d '\r')
    else
      myAPP=$(echo "$getAPP" | sed -n s/.*kuali.$myGroup.//p | sed -e 's/^[ \t]*//' | tr -d '\r')
    fi

    # Find Application Build version
    getVersion=$(curl -L -s $versionURL  | grep -i Bundle-Version|tr -d '\n')
    myVersion=$(echo "$getVersion" | sed -n 's/[^\:]*\://p' | sed -e 's/^[ \t]*//' | tr -d '\r')

    # Find JDK Version
    getJDK=$(curl -L -s $versionURL  | grep -i Build-Jdk|tr -d '\n')
    myJDK=$(echo "$getJDK" | sed -n 's/[^\:]*\://p' | sed -e 's/^[ \t]*//' | tr -d '\r')

    # Find Build date
    getDate=$(curl -L -s $versionURL  | grep -i Bundle-Timestamp|tr -d '\n')
    myDate=$(echo "$getDate" | sed -n 's/[^\:]*\://p' | sed -e 's/^[ \t]*//' | tr -d '\r')

    # Find Tomcat Max Mem
    #getMX=$(curl -L -s $tomcatURL  | grep -i Xmx|tr -d '\n')
    #myMX=$(echo "$getMX" | sed -e 's/^[Xmx]//' |sed -e 's/[^\<]*$//' | sed -e 's/^[ \t]*//' | tr -d '\r')

    # Determine if DB type is Oracle or MySQL (add more elif, if additional types (ie: postgres, sqlserver)
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

while read URL;do
((COUNT=COUNT+1))

#if [[ $COUNT == "2" ]];then 
#echo "Stopping after 1 run."
#exit
#fi

if [[ "$URL" == *rice.kuali.org* ]]; then
   myGroup="rice"
   myGroupDisplay="Rice"
   myFile="$DropLocation/$myGroup.csv"
elif [[ "$URL" == *ks.kuali.org* ]]; then
   myGroup="ks"
   myGroupDisplay="Student"
   myFile="$DropLocation/$myGroup.csv"
elif [[ "$URL" == *ole.kuali.org* ]]; then
   myGroup="ole"
   myGroupDisplay="OLE"
   myFile="$DropLocation/$myGroup.csv"
else
   print "Unknown URL - exiting"
   exit 1
fi

# Zero out the csv output file
if [[ $COUNT == "1" ]];then
#echo "my count = 1.   Clearing out $myFile"
> $myFile
fi

# Get the HTTP CODE of the URL
HTTP_CODE=$(curl -m 10 -Is $URL | grep HTTP | cut -d ' ' -f2|tr -d '\n')

# Check to see if get a 302 or 200 error code.  If do, then proceed.
if [[ $HTTP_CODE != "302" ]] && [[ $HTTP_CODE != "200" ]];then
    echo "$URL - Not Available - HTTP Code: $HTTP_CODE"
    print -n $COUNT >> $myFile
    print "No versioning available"
    print -n "," >> $myFile
    print -n $myGroupDisplay >> $myFile
    print -n "," >> $myFile
    print -n http://$URL >> $myFile
    print -n "," >> $myFile
    #print -n $myVersion >> $myFile
    print -n "," >> $myFile
    #print -n $myAPP >> $myFile
    print -n "," >> $myFile
    #print -n $myJDK >> $myFile
    print -n "," >> $myFile
    #print -n $myDBtype >> $myFile
    print -n "," >> $myFile
    #print -n $myDB >> $myFile
    print -n "," >> $myFile
    #print -n $myDate >> $myFile
    print -n "," >> $myFile
    print -n "NA" >> $myFile
    print >> $myFile
  else
    get_info
    print "Number      = $COUNT"
    print "$URL - HTTP Code: $HTTP_CODE"
    print "Group       = $myGroup"
    print "Application = $myAPP"
    print "Version     = $myVersion"
    print "JDK         = $myJDK"
    print "DB Type     = $myDBtype"
    print "DB Username = $myDB"
    print "Date        = $myDate"
    print "Status      = OK"
    print -n $COUNT >> $myFile
    print -n "," >> $myFile
    print -n $myGroupDisplay >> $myFile
    print -n "," >> $myFile
    print -n http://$URL >> $myFile
    print -n "," >> $myFile
    print -n $myVersion >> $myFile
    print -n "," >> $myFile
    print -n $myAPP >> $myFile
    print -n "," >> $myFile
    print -n $myJDK >> $myFile
    print -n "," >> $myFile
    print -n $myDBtype >> $myFile
    print -n "," >> $myFile
    print -n $myDB >> $myFile
    print -n "," >> $myFile
    print -n $myDate >> $myFile
    print -n "," >> $myFile
    print -n "OK" >> $myFile
    print >> $myFile
fi
echo
done <"$1"
echo
