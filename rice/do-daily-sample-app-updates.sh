#!/bin/sh

. ~j2eemgr/kuali/rice/common-settings
. ~j2eemgr/kuali/rice/1.0.0-sample-app-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions
. ~j2eemgr/kuali/rice/common-functions

kexport client $APPLICATION_INFRASTRUCTURE_VERSION

APPLICATION_JAVA_PROJECT=rice-sample-apps/recipe; typeset -x APPLICATION_JAVA_PROJECT

sampleappdailytag $APPLICATION_INFRASTRUCTURE_VERSION

##
# Args to kupdate
# 	1) environment ("unt","cnv","stg")
#   2) name ("standard" -- do not use an arbitrary value)
#	3) update database (boolean)
# 	4) deploy war (boolean)
#   5) database platform ("Oracle9i" -- do not use an arbitrary value)
##

DATABASE_APPLICATION_CODE=ricesamp094; typeset -x DATABASE_APPLICATION_CODE

kupdate cnv standard true true Oracle9i
kpurge $LOGS_DIRECTORY 7

kend 0
