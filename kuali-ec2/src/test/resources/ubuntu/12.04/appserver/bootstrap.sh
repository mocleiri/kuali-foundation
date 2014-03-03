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
NEXUS_PASSWORD=${1-NOTDEFINED}
if [[ $NEXUS_PASSWORD == "NOTDEFINED" ]]; then
echo "Nexus password is required"
exit 1
fi;

# Bash resources
BOOTSTRAP=$SVN_DIR/bootstrap.sh
SETUP=$SVN_DIR/src/test/resources/jenkins/initial_setup.sh

# Enable root ssh
ssh ubuntu@$FQDN 'sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys'

# Checkout kuali-ec2
ssh root@$FQDN 'apt-get install subversion -y; rm -rf $SVN_DIR; svn --quiet checkout '$SVN_URL' '$SVN_DIR''

# Create the bootstrap.sh script
ssh root@$FQDN 'rm -rf '$BOOTSTRAP'; echo '$SETUP' silent '$NEXUS_PASSWORD' '$HOSTNAME' '$DOMAIN' > '$BOOTSTRAP'; chmod 755 '$BOOTSTRAP''

# Run the bootstrap script
ssh root@$FQDN $BOOTSTRAP
