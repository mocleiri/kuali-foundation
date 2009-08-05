#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba trunk
kdailytag trunk
kupdate cnv standard true true Oracle9i
kupdate unt standard true false Oracle9i

sh ~j2eemgr/kuali/kfs/do-daily-endowment-updates.sh

kpurge $LOGS_DIRECTORY 7

sh ~j2eemgr/kuali/kfs/check-modularization.sh

kend 0