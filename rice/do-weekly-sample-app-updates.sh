#!/bin/sh

. ~j2eemgr/kuali/rice/common-settings
. ~j2eemgr/kuali/rice/1.0.0-sample-app-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions
. ~j2eemgr/kuali/rice/common-functions

APPLICATION_JAVA_PROJECT=rice-sample-apps/recipe; typeset -x APPLICATION_JAVA_PROJECT

DATABASE_APPLICATION_CODE=ricesamp094; typeset -x DATABASE_APPLICATION_CODE

kupdate stg standard true true Oracle9i
kpurge $LOGS_DIRECTORY 7

kend 0
