PASSWORD=$1
HOSTNAME=jeff.ci
DOMAIN=kuali.org
FQDN=$HOSTNAME.$DOMAIN
SVN_REPO=https://svn.kuali.org/repos/foundation
SVN_PATH=trunk/kuali-ec2
SVN_URL=$SVN_REPO/$SVN_PATH

ssh ubuntu@$FQDN 'sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys'
ssh root@$FQDN 'apt-get install subversion -y; rm -rf kuali-ec2; svn --quiet checkout $SVN_URL'
