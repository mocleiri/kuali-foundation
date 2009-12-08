#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

#typeset -x APPLICATION_SERVER_NAME=kuali22
#kupdate reg standard true true MySQL false

sh ~j2eemgr/kuali/kfs/do-weekly-endowment-updates.sh

kend 0