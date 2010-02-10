#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

typeset -x APPLICATION_SERVER_NAME=$STG_APPLICATION_SERVER_NAME
typeset -x APPLICATION_INFRASTRUCTURE_VERSION=1.0.1.1
kupdate stg branches/release-3-0-1-1 true true Oracle9i true branches/rice-release-1-0-1-1-br

kend 0
