#!/bin/sh


if [ "$CATALINA_HOME" = "" ]; then
  echo "CATALINA_HOME is not set"
  exit
fi

$CATALINA_HOME/bin/catalina.sh stop 5 -force
