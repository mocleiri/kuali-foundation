#!/bin/sh

. ~j2eemgr/kuali/kfs/settings
. ~j2eemgr/kuali/shared-settings
. ~j2eemgr/kuali/shared-functions

PROJECT_DIRECTORY=$APPLICATION_DIRECTORY/$APPLICATION; typeset -rx PROJECT_DIRECTORY
SOURCE_DIRECTORY=$PROJECT_DIRECTORY/work/src; typeset -rx SOURCE_DIRECTORY

function kgrepcode {
	{
	typeset directory=$1
	typeset pattern=$2
	klog "Testing path [$directory] for pattern [$pattern]"	
	grep -rnE --include=*.java --include=*.xml "$pattern" $directory
	typeset return_status=$?
	if [[ "$return_status" != "0" ]]; then
	    echo "NO MATCHES"
	fi
	echo "\n"
	} >> $SCRIPT_LOG 2>&1
}
typeset -xf kgrepcode

kcheckmodularization unt trunk Oracle9i

# these grep tests catch code references that the verify-design task can not

# check core for reference to the external modules
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/coa "org\.kuali\.kfs\.module\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/fp "org\.kuali\.kfs\.module\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/gl "org\.kuali\.kfs\.module\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/pdp "org\.kuali\.kfs\.module\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/sys "org\.kuali\.kfs\.module\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/vnd "org\.kuali\.kfs\.module\."

# check the integration package for references to the external modules
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/integration "org\.kuali\.kfs\.module\."

# check the optional modules for references to other optional modules
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/module/ar "org\.kuali\.kfs\.module\.(bc|cab|cam|cg|ec|ld|purap)\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/module/bc "org\.kuali\.kfs\.module\.(ar|cab|cam|cg|ec|ld|purap)\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/module/cab "org\.kuali\.kfs\.module\.(ar|bc|cg|ec|ld)\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/module/cam "org\.kuali\.kfs\.module\.(ar|bc|cab|cg|ec|ld|purap)\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/module/cg "org\.kuali\.kfs\.module\.(ar|bc|cab|cam|ec|ld|purap)\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/module/ec "org\.kuali\.kfs\.module\.(ar|bc|cab|cam|cg|ld|purap)\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/module/ld "org\.kuali\.kfs\.module\.(ar|bc|cab|cam|cg|ec|purap)\."
kgrepcode $SOURCE_DIRECTORY/org/kuali/kfs/module/purap "org\.kuali\.kfs\.module\.(ar|bc|cab|cam|cg|ec|ld)\."

kend 0