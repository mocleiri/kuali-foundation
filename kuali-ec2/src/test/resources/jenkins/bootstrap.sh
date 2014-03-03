PASSWORD=$1
HOSTNAME=jeff.ci
DOMAIN=kuali.org
FQDN=$HOSTNAME.$DOMAIN

ssh ubuntu@$FQDN 'sudo cp /home/ubuntu/.ssh/authorized_keys /root/.ssh/authorized_keys'

scp initial_setup.sh root@$FQDN:/root/initial_setup.sh
scp unattended-upgrades.sh root@$FQDN:/root/unattended-upgrades.sh
#ssh root@$FQDN './initial_setup.sh silent $PASSWORD $HOSTNAME $DOMAIN'
