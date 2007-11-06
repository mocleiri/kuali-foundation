#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba
koldexport
kdailytag kfs-release-2-br
kupdate unt true false false
kupdate cnv true false true
kpurge $LOGS_DIRECTORY 7

kend 0
