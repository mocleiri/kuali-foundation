The scripting here provides startup/shutdown hooks for EC2 slaves

#
# Installation
#
Copy ec2slave to /etc/init.d/ec2slave

As root run update-rc.d ec2slave defaults

This creates symlinks as described here: http://www.debuntu.org/how-to-manage-services-with-update-rc.d

#
# Configuration
#
init-script is run on the slave at startup
shutdown-script is run on the slave before it shuts down

Before init-script and shutdown-script are run, svn update is called
to ensure they will always be running the latest version of that script

#
# WARNING: READ THIS BEFORE EDITING ANYTHING IN THIS DIRECTORY
#
When editing the scripting here BE CAREFUL!!!!!

This scripts are in use by the live CI server, so messing with them alters the behavior 
of the live slave instances

If you are playing around with trying out something new, it would be a *very* good idea
to copy the script you are messing with to a different name and play with it under the 
new name.

