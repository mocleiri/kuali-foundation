#!/bin/sh

#do-daily-updates.sh
. ~j2eemgr/kuali/rice/common-settings
. ~j2eemgr/kuali/rice/1.0.0-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kdailytag $APPLICATION_INFRASTRUCTURE_VERSION
kupdate stg standard true true Oracle9i
#. ~j2eemgr/kuali/rice/do-weekly-kuali-client-updates.sh
#. ~j2eemgr/kuali/rice/do-weekly-sample-app-updates.sh

kpurge $LOGS_DIRECTORY 7

kend 0
