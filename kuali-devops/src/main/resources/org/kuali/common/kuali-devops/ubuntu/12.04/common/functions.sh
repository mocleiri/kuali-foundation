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

# Make sure a variable is set and is not the empty string
# usage: check_not_blank JDK $JDK
function check_not_blank {
  if [ ! -n "$2" ]; then echo $1 cannot be blank; usage; fi
}

# Make sure a file exists
# usage: check_exists /root/.bashrc
function check_exists {
  FILENAME=$1
  if [ ! -n "$FILENAME" ]; then echo FILENAME cannot be blank; exit 1; fi
  if [ ! -f $FILENAME ];   then echo "file [$FILENAME] does not exist"; exit 1; fi
}

function decrypt_password {
  check_not_blank GPG_PASSPHRASE $GPG_PASSPHRASE
  check_exists $1
  GPG_INPUT_FILE=$1
  GPG_OUTPUT_FILE=$(basename $1 .gpg)
  execute_quietly "gpg --batch --yes --passphrase $GPG_PASSPHRASE --decrypt --output $GPG_OUTPUT_FILE $GPG_INPUT_FILE"
  GPG_DECRYPTED=$(<$GPG_OUTPUT_FILE)
  rm $GPG_OUTPUT_FILE
  echo $GPG_DECRYPTED
}

function execute_quietly {
  COMMAND=$1
  if [ "$QUIET" = "true" ]; then
    $COMMAND > /dev/null 2>&1
  else
    $COMMAND
  fi
}
