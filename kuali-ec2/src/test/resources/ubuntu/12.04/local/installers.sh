function show_usage {
  echo
  echo requires MODULES BASEDIR JDK NEXUS_PASSWORD SUBDOMAIN DOMAIN
  echo
  exit 1
}

function check_args {
  check_not_blank MODULES $MODULES
  check_not_blank BASEDIR $BASEDIR
  check_not_blank JDK $JDK
  check_not_blank NEXUS_PASSWORD $NEXUS_PASSWORD
  check_not_blank SUBDOMAIN $SUBDOMAIN
  check_not_blank DOMAIN $DOMAIN
}

check_args

BASICS="$MODULES/common/install_basics.sh
JAVA="$MODULES/common/install_java.sh $JDK $NEXUS_PASSWORD $QUIET"
DNS="$MODULES/common/update_hostname.sh $SUBDOMAIN $DOMAIN"
