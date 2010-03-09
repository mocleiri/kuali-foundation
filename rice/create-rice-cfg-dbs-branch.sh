#!/bin/sh

# chb
# the value for SETTINGS_FILE should be the filename of a file in 
# https://test.kuali.org/svn/kul-cfg-envs/trunk/rice/
# Said file should contain at least the following properties (accompanying values given as examples only)
#	JAVA_APPLICATION_CODE=recipe; typeset -x JAVA_APPLICATION_CODE
#	DATABASE_APPLICATION_CODE=rice1011; typeset -x DATABASE_APPLICATION_CODE
#	APPLICATION_DATABASE_PROJECT=$APPLICATION"-cfg-dbs/rice-client-db"; typeset -x APPLICATION_DATABASE_PROJECT
#	APPLICATION_INFRASTRUCTURE_VERSION=1.0.1.1; typeset -x APPLICATION_INFRASTRUCTURE_VERSION
#	APPLICATION_SERVER_NAME=kuali11; typeset -x APPLICATION_SERVER_NAME
SETTINGS_FILE=1.1.0-kuali-client-settings

. ~j2eemgr/kuali/rice/common-settings
. ~j2eemgr/kuali/rice/$SETTINGS_FILE
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

#$APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION is set in shared-settings file
kexport dba $APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION
#kdailytag $APPLICATION_INFRASTRUCTURE_SRCCTRL_VERSION

kend 0

