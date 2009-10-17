#!/bin/sh

. ~j2eemgr/kuali/kfs/endowment-settings
. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

#kbranch standard endowment
kupdate dev2 branches/endowment-build-1304 false true Oracle9i

kend 0