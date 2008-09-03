#!/bin/sh

. ~j2eemgr/kuali/cvs-svn-staging/kfs/settings
. ~j2eemgr/kuali/cvs-svn-staging/shared-settings
. ~j2eemgr/kuali/cvs-svn-staging/shared-functions

kexport dba
kdailytag HEAD
kupdate unt true true false
kupdate cnv true true true
kpurge $LOGS_DIRECTORY 7

sh ~j2eemgr/kuali/cvs-svn-staging/kfs/check-modularization.sh

kend 0