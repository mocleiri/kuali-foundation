# Subversion
SVN_REPO=https://svn.kuali.org/repos/foundation
SVN_PATH=trunk/kuali-ec2
SVN_URL=$SVN_REPO/$SVN_PATH
SVN_DIR=/mnt/kuali-ec2

# DNS
HOSTNAME=jeff.ci
DOMAIN=kuali.org
FQDN=$HOSTNAME.$DOMAIN

# Nexus
NEXUS_PASSWORD=$1

# Bash
BOOTSTRAP=$SVN_DIR/bootstrap.sh
SETUP=$SVN_DIR/src/test/resources/jenkins/initial_setup.sh

SVN1="apt-get install subversion -y"
SVN2="rm -rf $SVN_DIR"
SVN3="svn --quiet checkout '$SVN_URL' '$SVN_DIR'"
SVN="$SVN1; $SVN2; $SVN3"

# Enable root ssh
ssh ubuntu@$FQDN 'sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys'

#
ssh root@$FQDN '$SVN'
ssh root@$FQDN 'rm -rf '$BOOTSTRAP'; echo '$SETUP' silent '$NEXUS_PASSWORD' '$DOMAIN' '$HOSTNAME' > '$BOOTSTRAP'; chmod 755 '$BOOTSTRAP''
