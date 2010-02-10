#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

typeset -x APPLICATION_SERVER_NAME=$CNV_APPLICATION_SERVER_NAME
kupdate cnv standard false true Oracle9i true

kend 0
