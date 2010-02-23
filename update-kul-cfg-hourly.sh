#!/bin/sh

svn checkout https://test.kuali.org/svn/kul-cfg-envs/trunk -r HEAD ~j2eemgr/kuali/temp
find ~j2eemgr/kuali/temp -type f -name \".*\" -exec rm -rf {} \;
cp -f ~j2eemgr/kuali/temp/kfs/*hourly*.sh ~j2eemgr/kuali/kfs/
chmod u+x ~j2eemgr/kuali/kfs/*.sh
rm -rf ~j2eemgr/kuali/temp
