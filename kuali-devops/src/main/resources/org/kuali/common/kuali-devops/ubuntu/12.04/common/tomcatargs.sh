#!/bin/bash

function check_args {
  check_not_blank TOMCAT $TOMCAT
  check_not_blank JDK $JDK
  check_not_blank GPG_PASSPHRASE $GPG_PASSPHRASE
  check_not_blank MAX_HEAP $MAX_HEAP
  check_not_blank MAX_PERM $MAX_PERM
  check_not_blank DOMAIN $DOMAIN
  check_not_blank MAVEN_OPTS $MAVEN_OPTS
  check_tomcat
}

usage() { echo "Usage: $ME [-h] [-q] [--help] [--quiet] [--maxheap=5g] [--maxperm=256m] [--domain=kuali.org] [--mavenoopts=\"-Xmx2g -XX:MaxPermSize=256m\"] tomcat6/tomcat7 jdk6/jdk7 password" 1>&2; exit 1; }

QUIET=false
MAX_HEAP=5g
MAX_PERM=512m
DOMAIN=kuali.org
MAVEN_OPTS="-Xmx2g -XX:MaxPermSize=256m"

ARGS=$(getopt --options hq --longoptions "help,quiet,maxheap:,maxperm:,domain:,mavenopts:" --name "$ME" -- "$@");
if [ $? -ne 0 ]; then usage; fi
eval set -- "$ARGS";
while true; do
  case "$1" in
    -h|--help)
      usage;
      shift;
      ;;
    -q|--quiet)
      QUIET="true";
      shift;
      ;;
    --maxheap)
      shift;
      MAX_HEAP="$1";
      shift;
      ;;
    --maxperm)
      shift;
      MAX_PERM="$1";
      shift;
      ;;
    --domain)
      shift;
      DOMAIN="$1";
      shift;
      ;;
    --mavenopts)
      shift;
      MAVEN_OPTS="$1";
      shift;
      ;;
    --)
      shift;
      break;
      ;;
  esac
done

TOMCAT=$1
JDK=$2
GPG_PASSPHRASE=$3

check_args
