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

# caseyhb 1June2009
# modified to work with SVN


function usage
{
    echo "kul_copyright_update.sh svnurl [local_dir]"
    echo "where 'svnurl' is the Subversion repository URL of the project"
    echo "      'local_dir' is the absolute path of a target directory (default '/tmp/kuali-license-check')"
    echo
    echo "kul_copyright_update checks out a project from SVN, operates on that local copy and then exits."
    echo "It's up to the user to check the 'licensed' code back in. Output logged to file 'licenseScript.log'"
    exit 1
}

typeset SCRIPTLOG=licenseScript.log

# $Id: do_command,v 1.6 2003/02/07 22:20:25 jbmorris Exp $
#----------------------------------------------------------------------------
# do_command
#   This function will execute an arbitrary command. It will echo the exact
#   command with all the parameters into the log file. It will then execute
#   the command with standard out and standard errror being redirected to the
#   log file. Finally it will check the return status. A non-zero value will
#   cause the script to process an error.
#
#   SYNTAX: do_command <command string>
#----------------------------------------------------------------------------
function do_command 
{
    {
        #    if [[ $BRTE_DEBUG > 1 ]]; then
        #        set -x
        #    fi
        #
        #    if [[ $__BATCH_MODULES__ = "" ]]; then
        #        echo "The function do_command can only be used in BRTE."
        #        exit 8
        #    fi

        typeset Command_String=$*
        typeset Return_Status

        print_to_log "Starting command \"$Command_String\""
        eval $Command_String
        Return_Status=$?
        #check_execute $Return_Status
        return $Return_Status
    } >> $SCRIPTLOG 2>&1
}
typeset -xf do_command

# $Id: print_to_log,v 1.6 2003/02/07 22:20:28 jbmorris Exp $
#----------------------------------------------------------------------------
# Basic function to write to the log. Can be called by developers.
#
# SYNTAX: print_to_log <message>
#----------------------------------------------------------------------------
function print_to_log 
{
    {
        #        if [[ $BRTE_DEBUG > 1 ]]; then
        #            set -x
        #        fi
        #
        #        if [[ $__BATCH_MODULES__ = "" ]]; then
        #            echo "The function print_to_log can only be used in BRTE."
        #            exit 8
        #        fi

        typeset Message
        Message=$1
        echo `date +%Y%m%d:%T`: "$Message"
    } >> $SCRIPTLOG 2>&1
}
typeset -xf print_to_log

# $Id: set_error_message,v 1.5 2003/02/11 23:08:37 jbmorris Exp $
#
#----------------------------------------------------------------------------
# Basic function to set the ERRMESG value. Should be called by developers.
#
# SYNTAX: set_error_message <message>
#----------------------------------------------------------------------------
function set_error_message {
{
    #    if [[ $BRTE_DEBUG > 0 ]]; then
    #        set -x
    #    fi
    #
    #    if [[ $__BATCH_MODULES__ = "" ]]; then
    #        echo "The function set_error_message can only be used in BRTE."
    #        exit 8
    #    fi
    #
    if (( $# != 1 )); then
        print_to_log "Warning NULL value passed to set_error_message"
        return 2
    fi
    ERRMESG=$1
} >> $SCRIPTLOG 2>&1
}
typeset -xf set_error_message


#chb: don't care about revision; assume HEAD
#if [[ $1 = "" ]]; then
#  typeset Revision="-A"
#else
#  typeset Revision="-r$1"
#fi
#to run for a branch, send in branch name as a parameter

#. /opt/brte/batch_modules
#set_error_class "004"

#typeset Project="kuali_project"
#typeset Step=0
#cd $HOME/kuali

if [[ $1 = "" || $# -eq 0 ]]; then
    usage
else
    typeset Project="$1"
fi

if [[ $2 = "" ]]; then
    typeset Localdir="/tmp/kuali-license-check"
    if [[ -z "/tmp/kuali-license-check" ]]; then
        mkdir /tmp/kuali-license-check
    fi
else
    typeset Localdir="$2"
fi

# chb: don't care about checking for user or commit hooks
#function STEP10 {
##disable commits
#	set_error_message "unable to disable commits on $Project"
#	do_command "cp $PROGRAMDIR/commitinfo.disable $CVSROOT/CVSROOT/commitinfo"
#}

function STEP20 {
    set_error_message "error checking out project at $Project"
    #do_command "cvs co $Branch $Project"
    do_command "svn co $Revision $Project $Localdir"
}

function STEP30 {
    #run the update script on all files in the project, except in the root directory
    set_error_message "error adding/updating copyright info; commits will be re-enabled, so after fixing the problem it may be best to restart the script from the top rather than this step"
    do_command "find $Localdir/* -type d -exec addComment.sh {} `pwd` \;"
}

function STEP40 {
    set_error_message "commit failure"
    #Step=$STEP  #used in final_processing
    do_command "svn commit -m 'automated update of standard license header' $Localdir"
    #Step=0
}

function STEP50 {
    set_error_message "error cleaning up temporary local copy"
    do_command "rm -rf $Project"
}

#function final_processing {
#    #re-enable commits when script ends, success or failure (unless it failed during the commit)
#    if (( $Step != 40 )); then
#        do_command "cp $PROGRAMDIR/commitinfo.enable $CVSROOT/CVSROOT/commitinfo"
#    fi
#}

STEP20
STEP30
exit

