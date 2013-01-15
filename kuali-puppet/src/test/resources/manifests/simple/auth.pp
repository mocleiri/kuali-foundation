# Make sure the openssh-server package is present 
# Make sure of this BEFORE sync'ing the sshd_config file
package { "openssh-server":
  ensure => present,
  before => File["/etc/ssh/sshd_config"],
}

# Make sure the sshd_config file exists
# Make sure it is a file (not a directory)
# Make sure it has the right permissions
# Replace the contents of /etc/ssh/sshd_config with the contents of our template file
file { "/etc/ssh/sshd_config":
  ensure => file,
  mode   => 600,
  source => "/root/learning-manifests/resources/sshd_config",
}

# Make sure the sshd service is running
# Make sure it starts automatically at boot time 
# Restart it anytime /etc/ssh/sshd_config changes
service { "sshd":
  ensure     => running,
  enable     => true,
  subscribe  => File["/etc/ssh/sshd_config"],
}
    
# Create the file /root/.ssh/authorized_keys (if it does not already exist)
# The default permissions are what you'd expect
# 700 on the .ssh directory
# 400 on authorized_keys
# Create one entry in authorized_keys with ks-key in it
ssh_authorized_key {
  "ks-key":
    ensure  => present,
    user    => root,
    type    => "ssh-rsa",
    key     => "AAAAB3NzaC1yc2EAAAADAQABAAABAQCsfSVB0+PwKrfkQw81XD37tjlD8cXiEJBuPjjCv4tjEqGSTVRES7yOK0oheMVTil6Pm5GLWjnym0eA5EEZ0vBLtY91SIGby9WRlHbDg2maZEQR0WnA8PonOCMDK6hEuHBQDnGGC0J1zWm/6q6iJU1CbfIaHSLCtQonJpZIwI1wV3amwl/5cnwrVVuuX1nvY+L9kVu3vD7jw+KSyFQvtRvXIn6bS9vQXcqzwfqyfg9/aa/Tou1UkRa3gAxN/+1JtWei9AYu2BQ3SZRDAavxxnOTf1DyykDzO1AC0g0JnyyE3JuyeFeUJtDA+70/O7CP9SD0LEhVyWkrgLnHB775C7KZ";
}