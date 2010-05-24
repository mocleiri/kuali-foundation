#!/bin/sh

~j2eemgr/kuali/kra/db_changes.sh

. ~j2eemgr/kuali/kra/settings
. ~j2eemgr/kuali/kra/kra-functions
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport stg trunk
kdailytag trunk

#kupdate cnv standard true false Oracle9i true
#buildsvnwebapp cnv
#runS2sScripts cnv

#kupdate dev standard true false Oracle9i true
#buildsvnwebapp dev
#runS2sScripts dev

#kupdate dev2 standard true false Oracle9i true
#buildsvnwebapp dev2

#buildsvnwebapp stg
kpurge $LOGS_DIRECTORY 7

kcend 0
