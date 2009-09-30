#!/bin/sh

. ~j2eemgr/kuali/kul-cas/common-settings
. ~j2eemgr/kuali/kul-cas/1.0.0-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kdailytag $APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION

##
# Args to kupdate
# 	1) environment ("unt","cnv","stg")
#   2) name ("standard" -- do not use an arbitrary value)
#	3) update database (boolean)
# 	4) deploy war (boolean)
#   5) database platform ("Oracle9i" -- do not use an arbitrary value)
##
kupdate cnv standard false true Oracle9i
kupdate stg standard false true Oracle9i

kpurge $LOGS_DIRECTORY 7

kend 0
