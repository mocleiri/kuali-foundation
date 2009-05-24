#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba branches/kfs-200907-implementations-br
kdailytag branches/kfs-200907-implementations-br
kupdate cnv standard true true Oracle9i
kupdate unt standard true false Oracle9i
kupdate stg trunk true true Oracle9i
kpurge $LOGS_DIRECTORY 7

#sh ~j2eemgr/kuali/kfs/check-modularization.sh

kend 0