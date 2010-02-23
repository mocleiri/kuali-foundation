#!/bin/sh

svn checkout https://test.kuali.org/svn/kul-cfg-envs/trunk -r HEAD ~j2eemgr/kuali/temp
find ~j2eemgr/kuali/temp -type f -name \".*\" -exec rm -rf {} \;
cp -f ~j2eemgr/kuali/temp/* ~j2eemgr/kuali/
chmod u+x ~j2eemgr/kuali/*.sh
cp -f ~j2eemgr/kuali/temp/kfs/* ~j2eemgr/kuali/kfs/
chmod u+x ~j2eemgr/kuali/kfs/*.sh
cp -f ~j2eemgr/kuali/temp/kra/* ~j2eemgr/kuali/kra/
chmod u+x ~j2eemgr/kuali/kra/*.sh
cp -f ~j2eemgr/kuali/temp/rice/* ~j2eemgr/kuali/rice/
chmod u+x ~j2eemgr/kuali/rice/*.sh
cp -f ~j2eemgr/kuali/temp/kul-cas/* ~j2eemgr/kuali/kul-cas/
chmod u+x ~j2eemgr/kuali/kul-cas/*.sh
cp -f ~j2eemgr/kuali/temp/copyright-addition-old/* ~j2eemgr/kuali/copyright-addition-old/
chmod u+x ~j2eemgr/kuali/copyright-addition-old/*.sh
chmod u+x ~j2eemgr/kuali/copyright-addition-old/*.pl
rm -rf ~j2eemgr/kuali/temp
rm -rf ~j2eemgr/kuali/kul-cfg-dbs
svn checkout https://test.kuali.org/svn/kul-cfg-dbs/trunk -r HEAD ~j2eemgr/kuali/kul-cfg-dbs
