BASICS="$MODULE/common/install_basics.sh $BASEDIR $QUIET"
  
UNATTENDED="$MODULE/common/unattended_upgrades.sh"
if [ "$QUIET" = "-qq" ]; then
  UNATTENDED="$MODULE/common/unattended_upgrades.sh > /dev/null 2>&1"
fi
  
JAVA="$MODULE/common/install_java.sh $BASEDIR $JDK $NEXUS_PASSWORD $QUIET"
DNS="$MODULE/common/update_hostname.sh $SUBDOMAIN $DOMAIN"
