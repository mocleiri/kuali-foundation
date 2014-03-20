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

# download a url to file
# create any local directories as needed
# follow redirects
# return a non-zero error code if the download fails (ie 404 etc)
# optionally use basic authentication
function download_url {

  usage() { echo "Usage: download_url url filename username password" 1>&2; exit 1; }

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

function encrypt_file {
  check_not_blank GPG_PASSPHRASE $GPG_PASSPHRASE
  GPG_DECRYPTED=$1
  GPG_ENCRYPTED=$2
  check_exists $GPG_DECRYPTED
  execute_quietly "gpg --batch --yes --passphrase $GPG_PASSPHRASE --cipher-algo AES256 --symmetric --output $GPG_ENCRYPTED $GPG_DECRYPTED"
}

function decrypt_file {
  check_not_blank GPG_PASSPHRASE $GPG_PASSPHRASE
  GPG_ENCRYPTED=$1
  GPG_DECRYPTED=$2
  check_exists $GPG_ENCRYPTED
  execute_quietly "gpg --batch --yes --passphrase $GPG_PASSPHRASE --decrypt --output $GPG_DECRYPTED $GPG_ENCRYPTED"
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

function echo_lines {
  FILENAME=$1
  check_exists $FILENAME
  while read LINE
  do
    echo "$LINE"
  done < $FILENAME
}
