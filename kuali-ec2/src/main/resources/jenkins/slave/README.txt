====
    Copyright 2004-2013 The Kuali Foundation

    Licensed under the Educational Community License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.opensource.org/licenses/ecl2.php

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
====

#
# sambal-setup is a run-once type script
# it was used to setup build slaves to run Kyle's functional 
# automation testing code
#

#
#
# WARNING: READ THIS BEFORE EDITING ANYTHING IN THIS DIRECTORY
#
# WHEN EDITING THE SCRIPTING HERE BE CAREFUL!!!!!
#
#

These scripts are in use by the live CI server, so messing with them alters the behavior 
of the live slave instances

If you are playing around with trying out something new, it would be a *very* good idea
to copy the script you are messing with to a different name and play with it under the 
new name.

The scripting here provides startup/shutdown hooks for EC2 slaves

#
# Installation
#
Copy ec2slave to /etc/init.d/ec2slave

As root run 'update-rc.d ec2slave defaults'

This creates symlinks as described here: http://www.debuntu.org/how-to-manage-services-with-update-rc.d

#
# Configuration
#
init-script is run on the slave at startup
shutdown-script is run on the slave before it shuts down

Before init-script and shutdown-script are run, svn update is called
to ensure they will always be running the latest version of that script

