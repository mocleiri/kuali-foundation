FQDN=$1
FQDN=ec2-54-81-229-17.compute-1.amazonaws.com

ssh ubuntu@$FQDN 'sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys'

ssh root@$FQDN 'wget https://svn.kuali.org/repos/foundation/trunk/kuali-ec2/src/test/resources/jenkins/initial_setup.sh'

