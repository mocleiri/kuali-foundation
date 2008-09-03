#!/bin/sh

. ~j2eemgr/kuali/cvs-svn-staging/kra/settings
. ~j2eemgr/kuali/cvs-svn-staging/kra/kra-functions
. ~j2eemgr/kuali/cvs-svn-staging/shared-settings
. ~j2eemgr/kuali/cvs-svn-staging/shared-functions

 kexport stg
 kdailytag HEAD
# kupdate unt true true false
# kupdate stg true false true
 kupdate dev true true false
 runS2sScripts dev
 buildSvnWebapp dev
# kupdate cnv true true true 
# kupdate tst2 true true false
# kupdate dev2 true true false
# kdailytaghead
# kupdate dev false false true
kpurge $LOGS_DIRECTORY 7

kend 0
