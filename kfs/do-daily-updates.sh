#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba trunk
kdailytag trunk
kupdate unt standard true false Oracle9i
kupdate cnv standard true true Oracle9i
kpurge $LOGS_DIRECTORY 7

sh ~j2eemgr/kuali/kfs/check-modularization.sh
sh ~j2eemgr/kuali/kfs/do-weekly-updates.sh

kend 0