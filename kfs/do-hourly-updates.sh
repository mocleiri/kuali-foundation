#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

sh ~j2eemgr/kuali/kfs/do-hourly-endowment-updates.sh

kend 0