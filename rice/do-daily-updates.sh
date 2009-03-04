#!/bin/sh

#do-daily-updates.sh
. ~j2eemgr/kuali/rice/0.9.4-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba $APPLICATION_INFRASTRUCTURE_VERSION
kdailytag $APPLICATION_INFRASTRUCTURE_VERSION

##
# Args to kupdate
# 	1) environment ("unt","cnv","stg")
#   2) name ("standard" == use project/artifact version)
#	3) update database (boolean)
# 	4) deploy war (boolean)
#   5) database platform ("Oracle9i")
##
kupdate stg standard true true Oracle9i

kend 0
