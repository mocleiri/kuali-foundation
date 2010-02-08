#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba trunk
kdailytag trunk

typeset -x APPLICATION_SERVER_NAME=kuali23
kupdate cnv standard true true Oracle9i true

typeset -x APPLICATION_SERVER_NAME=kuali2
typeset -x APPLICATION_INFRASTRUCTURE_VERSION=1.0.1.1
kupdate stg branches/release-3-0-1-1 true true Oracle9i true branches/rice-release-1-0-1-1-br

# take from trunk
kupdate unt standard true false Oracle9i true

sh ~j2eemgr/kuali/kfs/do-daily-endowment-updates.sh

kpurge $LOGS_DIRECTORY 7

sh ~j2eemgr/kuali/kfs/check-modularization.sh

kend 0