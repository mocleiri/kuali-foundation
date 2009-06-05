#!/bin/ksh
#
# Copyright 2006 The Kuali Foundation.
# 
# Licensed under the Educational Community License, Version 1.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# 
# http://www.opensource.org/licenses/ecl1.php
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# DO NOT add comments before the blank line below, or they will disappear.

if [[ $1 = "" ]]; then
  typeset Branch="-A"
else
  typeset Branch="-r$1"
fi
#to run for a branch, send in branch name as a parameter

. /opt/brte/batch_modules
set_error_class "004"

typeset Project="kuali_project"
typeset Step=0
cd $HOME/kuali

function STEP10 {
#disable commits
	set_error_message "unable to disable commits on $Project"
	do_command "cp $PROGRAMDIR/commitinfo.disable $CVSROOT/CVSROOT/commitinfo"
}

function STEP20 {
	set_error_message "error checking out $Project"
	do_command "cvs co $Branch $Project"
}

function STEP30 {
#run the update script on all files in the project, except in the root directory
	set_error_message "error adding/updating copyright info; commits will be re-enabled, so after fixing the problem it may be best to restart the script from the top rather than this step"
	do_command "find $Project/* -type d ! -name CVS -exec $CVSROOT/kuali_utilities/configuration-management/licensing/addComment.sh {} \"$Branch\" \;"
}

function STEP40 {
	set_error_message "commit failure - $Project commits are still disabled!"
	Step=$STEP  #used in final_processing
	do_command "cvs commit -m 'automated update of standard license header' $Project"
	Step=0
}

function STEP50 {
	set_error_message "error cleaning up temporary local copy"
	do_command "rm -rf $Project"
}

function final_processing {
#re-enable commits when script ends, success or failure (unless it failed during the commit)
	if (( $Step != 40 )); then
		do_command "cp $PROGRAMDIR/commitinfo.enable $CVSROOT/CVSROOT/commitinfo"
	fi
}

executor
end_run 0
