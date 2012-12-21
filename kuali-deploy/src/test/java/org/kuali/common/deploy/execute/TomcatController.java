package org.kuali.common.deploy.execute;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TomcatController {

	private static final Logger logger = LoggerFactory.getLogger(TomcatController.class);

	@Test
	public void test() {
		try {
			logger.info("Testing Tomcat Basics");
			File jdbcDriver = new File("/Users/jeffcaddel/.m2/repository/com/oracle/ojdbc6_g/11.2.0.3/ojdbc6_g-11.2.0.3.jar");
			// File war = new File("/Users/jeffcaddel/ws/ole-kfs-5.0/ole-app/ole-fs/target/ole-fs-0.8.0-q-SNAPSHOT-webapp.war");
			File war = new File("/Users/jeffcaddel/Downloads/tomcatPluginV33.zip");
			String hostname = "env7.ole.kuali.org";
			List<Executable> executables = new ArrayList<Executable>();
			executables.add(new TomcatShutdown(hostname));
			executables.add(new TomcatRemoveJdbcDrivers(hostname));
			executables.add(new TomcatRemoveRootWebapp(hostname));
			executables.add(new TomcatCopyJdbcDriver(hostname, jdbcDriver));
			executables.add(new TomcatCopyWar(hostname, war));
			executables.add(new TomcatCleanup(hostname));
			executables.add(new TomcatStartup(hostname));
			for (Executable executable : executables) {
				executable.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
