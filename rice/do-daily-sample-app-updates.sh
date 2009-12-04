#!/bin/sh

. ~j2eemgr/kuali/rice/common-settings
. ~j2eemgr/kuali/rice/1.0.1.1-sample-app-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions
# build number needs to be different for each project
BUILD_NUMBER_FILE=$APPLICATION_DIRECTORY/$JAVA_APPLICATION_CODE-build-number.txt; typeset -rx BUILD_NUMBER_FILE

kexport client $APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION
kdailytag $APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION

kpurge $LOGS_DIRECTORY 15

kend 0
