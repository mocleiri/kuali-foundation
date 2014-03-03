FQDN=$1
FQDN=jeff.ci.kuali.org

ssh ubuntu@$FQDN 'sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys'

ssh root@$FQDN 'wget https://svn.kuali.org/repos/foundation/trunk/kuali-ec2/src/test/resources/jenkins/initial_setup.sh; chmod 755 ./initial_setup.sh'

