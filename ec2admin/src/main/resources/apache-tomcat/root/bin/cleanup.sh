#!/bin/sh

if [ "$CATALINA_BASE" = "" ]; then
  echo "CATALINA_BASE is not set"
  exit
fi

OUT=$CATALINA_BASE/logs/*.out
LOG=$CATALINA_BASE/logs/*.log
TLOG=$CATALINA_BASE/logs/*.tlog
TXT=$CATALINA_BASE/logs/*.txt
PID=$CATALINA_BASE/logs/catalina.pid
WORK=$CATALINA_BASE/work

echo Removing $OUT
echo Removing $LOG
echo Removing $TLOG
echo Removing $TXT
echo Removing $PID
echo Removing $WORK

rm -f $OUT
rm -f $LOG
rm -f $TLOG
rm -f $TXT
rm -f $PID
rm -rf $WORK
