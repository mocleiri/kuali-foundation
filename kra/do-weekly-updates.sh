#!/bin/sh

. ~j2eemgr/kuali/kra/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kupdate reg true true true
# kmail kuali-rice-dev@googlegroups.com "weekly kra reg build completed" "weekly kra reg build completed, all clear to deploy rice snapshot"
kmail kra-developers-l@indiana.edu "weekly kra reg build completed" "weekly kra reg build completed, it's ok to test on it."

kend 0
