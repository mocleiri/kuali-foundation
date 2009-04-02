#!/bin/sh

. ~j2eemgr/kuali/kra/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kupdate reg true true true
kmail kc.dev@kuali.org "weekly kra reg build completed" "weekly kra reg build completed, it's ok to test on it."

kend 0
