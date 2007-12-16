#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba
kdailytag HEAD
kupdate dev true true false
kupdate unt true true false
kupdate cnv true true true
kupdate stg false true true
kpurge $LOGS_DIRECTORY 7

kend 0