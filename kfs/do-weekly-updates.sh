#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kupdate reg standard true true MySQL

sh ~j2eemgr/kuali/kfs/do-weekly-endowment-updates.sh

kend 0