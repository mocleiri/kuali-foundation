#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

typeset -x APPLICATION_SERVER_NAME=$REG_APPLICATION_SERVER_NAME
kupdate reg standard true true MySQL false

kend 0
