#!/bin/sh

. ~j2eemgr/kuali/rice/common-settings
. ~j2eemgr/kuali/rice/1.1.0-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions
BUILD_NUMBER_FILE=$APPLICATION_DIRECTORY/$JAVA_APPLICATION_CODE-build-number.txt; typeset -rx BUILD_NUMBER_FILE
kexport dba $APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION
kdailytag $APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION
kupdate cnv standard true true Oracle9i false


kpurge $LOGS_DIRECTORY 15

kend 0

