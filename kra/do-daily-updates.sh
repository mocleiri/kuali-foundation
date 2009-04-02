#!/bin/sh

~j2eemgr/kuali/kra/db_changes.sh

. ~j2eemgr/kuali/kra/settings
. ~j2eemgr/kuali/kra/kra-functions
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

buildsvnwebapp dev2
buildsvnwebapp dev
kexport stg trunk
kdailytag trunk
#APPLICATION_SERVER_NAME=kuali15; typeset -rx APPLICATION_SERVER_NAME
kupdate cnv standard true false Oracle9i
buildsvnwebapp cnv
runS2sScripts cnv
#APPLICATION_SERVER_NAME=kuali5; typeset -rx APPLICATION_SERVER_NAME
kupdate stg standard false true Oracle9i
kpurge $LOGS_DIRECTORY 7

kcend 0
