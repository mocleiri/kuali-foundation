#!/bin/sh

. ~j2eemgr/kuali/rice/common-settings
. ~j2eemgr/kuali/rice/1.0.1-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba $APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION
kdailytag $APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION

##
# Args to kupdate
#   1) environment ("unt","cnv","stg")
#   2) name ("standard" -- do not use an arbitrary value)
#   3) update database (boolean)
#   4) deploy war (boolean)
#   5) database platform ("Oracle9i" -- do not use an arbitrary value)
##
# uncomment this when we get a 101 cnv env setup
kupdate cnv standard true true Oracle9i

kpurge $LOGS_DIRECTORY 15

kend 0

