#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba
koldexport
kdailytag HEAD
kupdate unt true true
kupdate cnv true true
kpurge $LOGS_DIRECTORY 7

kend 0