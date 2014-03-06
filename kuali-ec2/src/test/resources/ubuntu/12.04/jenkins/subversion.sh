#!/bin/bash


# Location of the "secret" directory that contains the subversion directory
# that has the common usernames, passwords and settings.
SECRET_SVN_DIR=/root/secrets/.subversion

function install_subversion {
# Check for subversion install
if hash svn 2>/dev/null; then
  echo "Subversion is already installed."
else
  echo "Subversion is not currently installed.  Installing..."
  apt-get $SILENT install subversion
fi
}

function check_user_subversion_dir {
# Check to make sure the "secret" directory where the .subversion folder structure exists.
if [ ! -d "$SECRET_SVN_DIR" ]; then
  echo "$SECRET_SVN_DIR does not exist."
else
  # Check to see if the .subversion folder has been setup for the root user.  If not, copy from the "secret" directory.
  if [ ! -d "/root/.subversion" ]; then
    echo "Subversion has not been setup for the root user. Copying .subversion directory for the root user."
    cp -R $SECRET_SVN_DIR /root/.subversion
    # Since copying a fresh copy of .subversion into root's home dir, no need for other checks. Else, check other settings/files.
  else
    echo "Subversion has been setup for the root user.  Checking repository/password files and that plain text passwords are allowed to be stored."
    # Check to make sure passwords can be stored in plain text.  If not, allow.
    if [ ! -f "/root/.subversion/servers" ]; then
      echo "Server file not found!"
    else
      # First get all non commmented lines from servers file
      SERVER_FILE=$(grep -v "^#" /root/.subversion/servers)
      # Check to see if plain text passwords already enabled.
      PLAIN_TEXT_TRUE=$(echo $SERVER_FILE | grep "store-plaintext-passwords = yes")
      if [ "$PLAIN_TEXT_TRUE" == "" ]; then
        echo "Enabling storing of plain text passwords for subversion."
        eval "sed -i -e '/store-plaintext-passwords \= no/astore-plaintext-passwords \= yes' /root/.subversion/servers"
      else
        echo "Plain text passwords allowed."
      fi
    fi
    # Check to make sure repository/password files are there.
    if [ ! -f "/root/.subversion/auth/svn.simple/7bde6701d85dbc4080b5213025d59913" ]; then
     echo "Password file does not exist for https://svn.kuali.org:443.  Copying from secure location."
     cp $SECRET_SVN_DIR/auth/svn.simple/7bde6701d85dbc4080b5213025d59913 /root/.subversion/auth/svn.simple/
    else
     echo "File for https://svn.kuali.org:443 exists."
    fi
    if [ ! -f "/root/.subversion/auth/svn.simple/50da47f9670ec8a77d4ab62e7fe6d127" ]; then
     echo "Password file does not exist for http://svn.kuali.org:80  Copying from secure location."
     cp $SECRET_SVN_DIR/auth/svn.simple/50da47f9670ec8a77d4ab62e7fe6d127 /root/.subversion/auth/svn.simple/
    else
     echo "File for http://svn.kuali.org:80 exists."
    fi
    if [ ! -f "/root/.subversion/auth/svn.simple/818073cdf486428e59e0f01ca295faa3" ]; then
     echo "Password file does not exist for https://test.kuali.org:443.  Copying from secure location."
     cp $SECRET_SVN_DIR/auth/svn.simple/818073cdf486428e59e0f01ca295faa3 /root/.subversion/auth/svn.simple/
    else
     echo "File for https://test.kuali.org:443 exists."
    fi
  fi
fi
}

install_subversion
check_user_subversion_dir

exit 0
