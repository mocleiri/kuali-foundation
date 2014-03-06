function show_usage {
  echo
  echo requires MODULE BASEDIR JDK NEXUS_PASSWORD SUBDOMAIN DOMAIN
  echo
}

function check_args {
  check_not_blank MODULE $MODULE
  check_not_blank BASEDIR $BASEDIR
  check_not_blank JDKK $JDKK
  check_not_blank NEXUS_PASSWORD $NEXUS_PASSWORD
  check_not_blank SUBDOMAIN $SUBDOMAIN
  check_not_blank DOMAIN $DOMAIN
}

check_args

BASICS="$MODULE/common/install_basics.sh $BASEDIR $QUIET"
  
UNATTENDED="$MODULE/common/unattended_upgrades.sh"
if [ "$QUIET" = "-qq" ]; then
  UNATTENDED="$MODULE/common/unattended_upgrades.sh > /dev/null 2>&1"
fi
  
JAVA="$MODULE/common/install_java.sh $BASEDIR $JDK $NEXUS_PASSWORD $QUIET"
DNS="$MODULE/common/update_hostname.sh $SUBDOMAIN $DOMAIN"
