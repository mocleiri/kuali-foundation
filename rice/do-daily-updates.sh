#!/bin/sh

#do-daily-updates.sh
. ~j2eemgr/kuali/rice/common-settings
. ~j2eemgr/kuali/rice/1.0.0-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba $APPLICATION_INFRASTRUCTURE_VERSION
kdailytag $APPLICATION_INFRASTRUCTURE_VERSION

##
# Args to kupdate
# 	1) environment ("unt","cnv","stg")
#   2) name ("standard" -- do not use an arbitrary value)
#	3) update database (boolean)
# 	4) deploy war (boolean)
#   5) database platform ("Oracle9i" -- do not use an arbitrary value)
##
kupdate stg standard true true Oracle9i

. ~j2eemgr/kuali/rice/do-daily-updates.sh

kpurge $LOGS_DIRECTORY 7

kend 0
