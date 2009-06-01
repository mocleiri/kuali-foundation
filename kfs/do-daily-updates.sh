#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

#kexport dba trunk
kdailytag trunk
kupdate cnv standard false true Oracle9i
kupdate unt standard false false Oracle9i
kupdate stg branches/kfs-200907-implementations-br false true Oracle9i
kpurge $LOGS_DIRECTORY 7

#sh ~j2eemgr/kuali/kfs/check-modularization.sh

kend 0