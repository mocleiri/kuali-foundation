#!/bin/sh

. ~j2eemgr/kuali/rice/0.9.4-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba branches/rice-release-0-9-4-br
kdailytag trunk
kupdate unt standard true false Oracle9i
kupdate cnv standard true true Oracle9i
kpurge $LOGS_DIRECTORY 7

sh ~j2eemgr/kuali/kfs/check-modularization.sh

kend 0