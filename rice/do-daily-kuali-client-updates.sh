#!/bin/sh

. ~j2eemgr/kuali/rice/common-settings
. ~j2eemgr/kuali/rice/1.0.1-kuali-client-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions
# build number needs to be different for each project
BUILD_NUMBER_FILE=$APPLICATION_DIRECTORY/$JAVA_APPLICATION_CODE-build-number.txt; typeset -rx BUILD_NUMBER_FILE

kexport dba $APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION
kdailytag $APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION

##
# Args to kupdate
# 	1) environment ("unt","cnv","stg")
#   2) name ("standard" -- do not use an arbitrary value)
#	3) update database (boolean)
# 	4) deploy war (boolean)
#   5) database platform ("Oracle9i" -- do not use an arbitrary value)
##
kupdate cnv standard true true Oracle9i false
kpurge $LOGS_DIRECTORY 7

kend 0
