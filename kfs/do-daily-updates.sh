#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba
koldexport
kdailytag kfs-release-2-1-br
kupdate unt true true false
kupdate cnv true true true
kpurge $LOGS_DIRECTORY 7

kend 0
