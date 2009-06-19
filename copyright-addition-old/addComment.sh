#!/bin/ksh
#jbmorris October/November, 2006
#modified in December to work with relative path to CVS directory, on all files
# therein, instead of working only on committed files via commitinfo

cd "$1"  #get into directory we're going to process
typeset Programdir=$2
#typeset Directory="$CVSROOT/$1"
typeset Directory="$1"
#typeset Branch=$2
typeset File
typeset -l Extension  #ignore case
typeset Style
typeset Message=""
typeset ReturnStatus

function ignore_log {
#this used to echo to a log file, so was a little more useful as a function then
  echo "IGNORING: $*"
}

function add_to_message {
  typeset NewMessage=""
  case $2 in
    -1) NewMessage="unknown extension" ;;
    -2) NewMessage="no extension" ;;
    -3) NewMessage="space in filename" ;;
    -4) NewMessage="space in directory name" ;;
    1) NewMessage="perl - style not known" ;;
    2) NewMessage="perl - unrecognized cvs log output" ;;
    3) NewMessage="perl - too few arguments" ;;
    4) NewMessage="perl - too many arguments" ;;
    5) NewMessage="perl - invalid tag specification" ;;
    *) NewMessage="unknown error $2" ;;
  esac
  Message="$Message\n$Directory/$1: $NewMessage"
}

#traverse the directory tree from current up to root, checking each one
#if find a directory whose name begins with . don't do license processing
#(e.g. .settings)
#OR if directory is in ignore.dirs file, don't do license processing
#while [[ $Directory != $CVSROOT && $Directory != "/" ]]; do
while [[ $Directory != "/" ]]; do
  if [[ ${Directory#* } != $Directory ]]; then  #contains space
    add_to_message "" -4
  fi
  if [[ ${Directory#.} != $Directory ]]; then
    ignore_log $1
    exit 0
  elif ( grep -q "^`basename "$Directory"`\$" "$Programdir/ignore.dirs" ); then
    ignore_log $1
    exit 0
  else
    Directory=`dirname "$Directory"`
  fi
done

#Directory="$CVSROOT/$1"  #get back to the correct value for use in add_to_message
#now examine all the files in the directory one by one
#ignore files in ignore.files, or if they have no extension, or if their
# extension is in the ignore list
for File in `ls`; do
  if [[ -f $File ]]; then  #if not a regular file (e.g. directory, link, pipe) it's no need to do anything with it
    Extension=${File##*.}
    if [[ ${File#* } != $File ]]; then  #contains space
      add_to_message "$File" -3
    fi
    if [[ ${File#.} != $File ]]; then  #begins with .
      ignore_log $File
    elif ( grep -q "^$File\$" "$Programdir/ignore.files" ); then
      ignore_log $File
    elif [[ $Extension = $File ]]; then  #no extension
      add_to_message "$File" -2
    else
      case $Extension in
        java|js|ddl|table|indexes|sql|css) Style="C" ;;
        html|htm|xml|xsd|xsl|jmx|tld|wsdd|dia|dtd) Style="HTML" ;;
        jsp|tag) Style="JSP" ;;
        vm) Style="VM" ;;
        sh|pl) Style="SHELL" ;;
        bat) Style="DOS" ;;
        ctl) Style="SQLLDR" ;;
        sqr|h) Style="SQR" ;;
        jpg|gif|zip|dmp|ico|war|jar|pdf|xls|png|ois|project|properties|jj|log|sla|tex|tsv|csv|txt|eps|fb|prefs|xml) Style="" ;;
        *) Style="" ; add_to_message "$File" -1 ;;
      esac
      if [[ $Style != "" ]]; then
        "$Programdir/addComment.pl" "$File" "$Style"
        ReturnStatus=$?
        if (( $ReturnStatus != 0 )); then
          add_to_message "$File" $ReturnStatus
        fi
      fi
    fi
  fi
done

if [[ $Message != "" ]]; then
  #echo $Message | mail -s "$0 WARNINGS/ERRORS" kfcm-l@indiana.edu
  echo $Message
fi
exit
