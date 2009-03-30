#!/bin/sh

. ~j2eemgr/kuali/rice/common-settings
. ~j2eemgr/kuali/rice/1.0.0-kuali-client-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba $APPLICATION_INFRASTRUCTURE_VERSION
# may need to update kdailytag, it assumes we have one database project per application project!
kdailytag $APPLICATION_INFRASTRUCTURE_VERSION

##
# Args to kupdate
# 	1) environment ("unt","cnv","stg")
#   2) name ("standard" -- do not use an arbitrary value)
#	3) update database (boolean)
# 	4) deploy war (boolean)
#   5) database platform ("Oracle9i" -- do not use an arbitrary value)
##
kupdate cnv standard true true Oracle9i
#kpurge $LOGS_DIRECTORY 7

kend 0
