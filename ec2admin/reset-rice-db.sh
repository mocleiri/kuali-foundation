it#!/bin/sh

ENV=$1
if [ "$ENV" = "" ]; then
  echo "Must pass in an environment eg 'db.sh 1'"
  exit
fi

cd impex/master
URL=jdbc:oracle:thin:@oracle.rice.kuali.org:1521:ORACLE
ARGS=-Pdb,oracle
ARGS="$ARGS -Dimpex.url=$URL"
ARGS="$ARGS -Dimpex.dba.url=$URL"
ARGS="$ARGS -Dimpex.dba.username=master"
ARGS="$ARGS -Dimpex.username=RICEENV$ENV"
ARGS="$ARGS -Dimpex.password=RICEENV$ENV"
mvn clean install $ARGS

cd ../..
