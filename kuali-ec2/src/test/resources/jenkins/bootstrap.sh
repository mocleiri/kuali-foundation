PWD=$1
HOSTNAME=jeff.ci
DOMAIN=kuali.org
FQDN=$HOSTNAME.$DOMAIN

ssh ubuntu@$FQDN 'sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys'

ssh root@$FQDN 'wget https://svn.kuali.org/repos/foundation/trunk/kuali-ec2/src/test/resources/jenkins/initial_setup.sh; chmod 755 ./initial_setup.sh'
ssh root@$FQDN './initial_setup.sh silent $PWD $HOSTNAME $DOMAIN'
