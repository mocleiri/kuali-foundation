#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba
kdailytag HEAD
kupdate unt true true false
kupdate cnv true true true
kpurge $LOGS_DIRECTORY 7

sh ~j2eemgr/kuali/kfs/check-modularization.sh

kend 0