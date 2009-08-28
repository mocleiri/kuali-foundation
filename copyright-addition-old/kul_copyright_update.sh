#!/bin/ksh
#
# Copyright 2006 The Kuali Foundation.
# 
# Licensed under the Educational Community License, Version 1.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# 
# http://www.opensource.org/licenses/ecl2.php
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# DO NOT add comments before the blank line below, or they will disappear.

# caseyhb 18June2009
# modified to work with SVN


function usage
{
    echo "kul_copyright_update.sh svnurl [local_dir] [debug]"
    echo "where 'svnurl' is the Subversion repository URL of the project"
    echo "      'local_dir' is the absolute path of a target directory (default '/tmp/kuali-license-check')"
    echo "      'debug' is the literal string 'debug' (default is to not log debugging information)"
    echo
    echo "kul_copyright_update checks out a project from SVN, operates on that local copy and then exits."
    echo "It's up to the user to check the 'licensed' code back in. Output logged to file 'licenseScript.log'"
    exit 1
}

typeset -x SCRIPTLOG=licenseScript.log

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
        typeset Command_String=$*
        typeset Return_Status
        #echo "Starting command \"$Command_String\""
        print_to_log "Starting command \"$Command_String\""
        eval $Command_String
        Return_Status=$?
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
function set_error_message 
{
    {
        if (( $# != 1 )); then
            print_to_log "Warning NULL value passed to set_error_message"
            return 2
        fi
        ERRMESG=$1
    } >> $SCRIPTLOG 2>&1
}
typeset -xf set_error_message

#capture pwd
Programdir=`pwd`

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

if [[ $3 = "debug" ]]; then
    typeset Debug="true"
else
    typeset Debug="false"
fi

function STEP20 {
    set_error_message "error checking out project at $Project"
    do_command "svn co $Revision $Project $Localdir"
}

function STEP30 {
    set_error_message "error adding/updating copyright info in base directory; after fixing the problem it may be best to restart the script from the top rather than this step"
    #chb: take care of $Localdir first (that's the boolean value after $Debug) 
    cd $Localdir/..
    Base=`basename $Localdir` 
    #echo "This is BASE: $Base"
    #echo "This is localdir: $Localdir"
    #echo "This is programdir: $Programdir"
    #do_command "$Programdir/addComment.sh $Base $Programdir $Debug 0"
    # chb: this works, but is bad... do_command "find / -wholename $Localdir -exec addComment.sh {} $Programdir $Debug 0 \;"
    # chb : this works, but only for the one directory ...  do_command "$Programdir/addComment.sh $Base $Programdir $Debug 0"
    #this works, but debug output is not redirected .... eval $Programdir/addComment.sh $Base $Programdir $Debug 0 2>&1 $SCRIPTLOG
    do_command "$Programdir/addComment.sh $Base $Programdir $Debug 0"
}

function STEP35 {
    #echo "This is localdir: $Localdir"
    #echo "This is programdir: $Programdir"
    set_error_message "error adding/updating copyright info while recursing; after fixing the problem it may be best to restart the script from the top rather than this step"
    cd $Programdir
    do_command "find $Localdir/* -type d -exec $Programdir/addComment.sh {} $Programdir $Debug 1 \;"
}

function STEP40 {
    set_error_message "commit failure"
    do_command "svn commit -m 'automated update of standard license header' $Localdir"
}

function STEP50 {
    set_error_message "error cleaning up temporary local copy"
    do_command "rm -rf $Project"
}

STEP20
STEP30
STEP35
#to enable commit and cleanup, uncomment next two lines
#STEP40
#STEP50

echo `date +%Y%m%d:%T` >> $SCRIPTLOG

exit

