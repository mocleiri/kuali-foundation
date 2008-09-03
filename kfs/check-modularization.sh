#!/bin/sh

. ~j2eemgr/kuali/kfs/cvs-svn-staging/settings
. ~j2eemgr/kuali/cvs-svn-staging/shared-settings
. ~j2eemgr/kuali/cvs-svn-staging/shared-functions

kant check-modularization unt $APPLICATION_DEV_MAIL_LIST

kend 0