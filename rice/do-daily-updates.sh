#!/bin/sh

. ~j2eemgr/kuali/rice/0.9.4-settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

kexport dba branches/rice-release-0-9-4-br
kdailytag branches/rice-release-0-9-4-br

kend 0