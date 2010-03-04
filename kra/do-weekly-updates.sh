#!/bin/sh

. ~j2eemgr/kuali/kra/settings
. ~j2eemgr/kuali/kra/kra-functions
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kupdate reg standard true false Oracle9i true
#buildsvnwebapp reg
#kmail 'kc.dev@kuali.org,kcqa@kuali.org' "weekly kra reg build completed" "weekly kra reg build completed, it's ok to test on it."

kend 0
