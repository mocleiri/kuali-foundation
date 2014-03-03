PASSWORD=$1
HOSTNAME=jeff.ci
DOMAIN=kuali.org
FQDN=$HOSTNAME.$DOMAIN

ssh ubuntu@$FQDN 'sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys'
ssh root@$FQDN 'apt-get install subversion -y; svn co https://svn.kuali.org/repos/foundation/trunk/kuali-ec2'
