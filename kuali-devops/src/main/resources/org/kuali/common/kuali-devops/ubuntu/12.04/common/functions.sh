#!/bin/bash
#
# Copyright 2004-2014 The Kuali Foundation
#
# Licensed under the Educational Community License, Version 2.0 (the "License");
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
#

function download {

  download_usage() { echo "Usage: download [-c \$HOME/cookies.txt] [-u username] [-p password] url filename" 1>&2; exit; }

  local COOKIES=""
  local USERNAME=""
  local PASSWORD=""
  local OPTIND
  local CURL
  while getopts ":c:" OPTIONS; do
    case "${OPTIONS}" in
      c)
        COOKIES="${OPTARG}"
        ;;
      u)
        USERNAME="${OPTARG}"
        ;;
      p)
        PASSWORD="${OPTARG}"
        ;;
      *)
        download_usage
        ;;
      esac
  done
  shift $((OPTIND-1))
  
  local URL=$1
  local FILENAME=$2

  check_not_blank URL $URL
  check_not_blank FILENAME $FILENAME
  
  CURL="curl --location --fail --create-dirs"
  if [ -n "$USERNAME" ]; then 
    CURL="$CURL --user $USERNAME:$PASSWORD" 
  fi
  
  if [ -n "$COOKIES" ]; then 
    CURL="$CURL --cookie $COOKIES" 
  fi

  CURL="$CURL --output $FILENAME $URL"
  
  execute_quietly "$CURL"

}

# download a url to file
# create any local directories as needed
# follow redirects
# return a non-zero error code if the download fails (ie 404 etc)
# optionally use basic authentication
function download_url {

  CURL_URL=$1
  CURL_FILENAME=$2
  CURL_USERNAME=$3
  CURL_PASSWORD=$4
  
  check_not_blank CURL_URL $CURL_URL
  check_not_blank CURL_FILENAME $CURL_FILENAME
  
  CURL_COMMAND="curl --location --fail --create-dirs"
  if [ -n "$CURL_USERNAME" ]; then 
    CURL_COMMAND="$CURL_COMMAND --user $CURL_USERNAME:$CURL_PASSWORD" 
  fi
  
  CURL_COMMAND="$CURL_COMMAND --output $CURL_FILENAME $CURL_URL"
  
  execute_quietly "$CURL_COMMAND"
}

function install_packages {
  echo "packages  -> $PACKAGES"
  execute_quietly "apt-get install $PACKAGES -y"
}

# Make sure a variable is set and is not the empty string
# usage: check_not_blank JDK $JDK
function check_not_blank {
  if [ ! -n "$2" ]; then 
    echo $1 cannot be blank; 
    usage; 
  fi
}

# Make sure a file exists
# usage: check_exists /root/.bashrc
function check_exists {
  FILENAME=$1

  # They didn't give us a filename
  if [ ! -n "$FILENAME" ]; then echo "FILENAME cannot be blank";        exit 1; fi

  # The filename they gave us doesn't exist
  if [ ! -f $FILENAME   ]; then echo "file [$FILENAME] does not exist"; exit 1; fi
}

function wait_for_string {

  FILENAME=$1
  STRING=$2

  check_not_blank STRING $STRING
  check_exists $FILENAME

  tail -f "$FILENAME" | while read LOGLINE
  do
    [[ "${LOGLINE}" == *"$STRING"* ]] && pkill -P $$ tail
  done
  
}

function execute_quietly {
  COMMAND=$1
  if [ "$QUIET" = "true" ]; then
    $COMMAND > /dev/null 2>&1
  else
    echo "$COMMAND"
    $COMMAND
  fi
  check_status $COMMAND
}

function check_status {
  STATUS="$?"
  COMMAND=$1
  if [ ! "$STATUS" == "0" ]; then
    echo
    echo "error executing: $COMMAND"
    echo "exit value: $STATUS"
    echo 
    exit $STATUS
  fi
}

function check_userdel_status {
  STATUS="$?"
  # 6 == user does not exist
  # 0 == user was successfully deleted
  if [[ ! ("$STATUS" == "6" || "$STATUS" == "0") ]]; then
    echo
    echo "error executing: \"$COMMAND\""
    echo "exit value: $STATUS"
    echo 
    exit $STATUS
  fi
}

function check_jdk {
  if [[ ! ("$JDK" == "jdk6" || "$JDK" == "jdk7" || "$JDK" == "jdk8") ]]; then
    echo "\"$JDK\" is invalid.  Must be jdk6, jdk7, or jdk8"
    usage
  fi
}

function check_tomcat {
  if [[ ! ("$TOMCAT" == "tomcat6" || "$TOMCAT" == "tomcat7") ]]; then
    echo "\"$TOMCAT\" is invalid.  Must be either tomcat6 or tomcat7"
    usage
  fi
}

# hmacsha1 value key
function hmacsha1 {
  echo -n "$1" | openssl sha1 -hmac "$2" -binary | xxd -p
}

# base64encode value
function base64encode {
  echo -n "$1" | base64 --wrap=0
}

# hex2base64 value
function hex2base64 {
  echo -n "$1" | xxd -r -p | base64 --wrap=0
}

# convert any dots into forward slashes
function get_path {
  echo -n "$1" | tr '.' '/'
}

function encrypt_file {
  check_not_blank AES_PASSPHRASE $AES_PASSPHRASE
  AES_DECRYPTED=$1
  AES_ENCRYPTED=$2
  check_exists $AES_DECRYPTED
  execute_quietly "openssl enc -aes128 -e -base64 -k $AES_PASSPHRASE -in $AES_DECRYPTED -out $AES_ENCRYPTED"
}

function decrypt_file {
  check_not_blank AES_PASSPHRASE $AES_PASSPHRASE
  AES_ENCRYPTED=$1
  AES_DECRYPTED=$2
  check_exists $AES_ENCRYPTED
  execute_quietly "openssl enc -aes128 -d -base64 -k $AES_PASSPHRASE -in $AES_ENCRYPTED -out $AES_DECRYPTED"
}

# decrypt value password
# -e == encrypt
# -A == no line wrapping the base 64
# -k == password
function encrypt {
  echo -e -n "$1" | openssl enc -aes128 -e -base64 -A -k $2
}

# decrypt value password
# -d == decrypt
# -A == no line wrapping the base 64
# -k == password
function decrypt {
  echo -e -n "$1" | openssl enc -aes128 -d -base64 -A -k $2
}

function check_jenkins_mode {
  if [ $MODE == "min" ] || [ $MODE == "thin" ] || [ $MODE == "full" ]; then
    MODE=$MODE
  else
    echo "MODE must be one of empty/min/thin/full"
    usage
  fi
}

# get the line number inside a file that matches a token
function get_line_number {
  local FILE=$1
  local TOKEN=$2
  echo $(cat -n $FILE | grep "$TOKEN" | cut -f 1 | xargs)
}

# get the line number inside a file that comes after the line that matches a token
function get_next_line_number {
  local FILE=$1
  local TOKEN=$2
  local LINE_NUMBER=$(get_line_number "$FILE" "$TOKEN")
  echo $(($LINE_NUMBER + 1))
}

function configure_jenkins_update_center {
  
  local FILE=hudson.model.UpdateCenter.xml
  local DST=$JENKINS_HOME/$FILE
  local SRC=$MY_DIR/master/config/$FILE
  
  echo "configure -> plugins :: lts updates"
  execute_quietly "mkdir -p $JENKINS_HOME"
  execute_quietly "cp $SRC $DST"
  execute_quietly "chown $TOMCAT:$TOMCAT $DST"
}
