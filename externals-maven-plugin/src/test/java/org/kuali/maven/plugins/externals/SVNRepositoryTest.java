package org.kuali.maven.plugins.externals;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SVNRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(SVNRepositoryTest.class);
	SVNUtils svnUtils = SVNUtils.getInstance();
	String username = "jcaddel";
	String password = System.getProperty("svn.password");

	@Test
	public void testGetExternalsFromUrl() {
		try {
			String url = "https://svn.kuali.org/repos/student/sandbox/enrollment/aggregate/trunk";
			List<SVNExternal> externals = svnUtils.getExternals(url);
			showExternals(externals);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetExternalsFromWorkingCopyPath() {
		try {
			File workingCopyPath = new File("/Users/jeffcaddel/ws/aggregate");
			List<SVNExternal> externals = svnUtils.getExternals(workingCopyPath);
			showExternals(externals);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void showExternals(List<SVNExternal> externals) {
		log.info("svn:externals count=" + externals.size());
		for (SVNExternal e : externals) {
			StringBuilder sb = new StringBuilder();
			sb.append("[" + e.getPath());
			sb.append(", " + e.getWorkingCopyPath());
			sb.append(", " + e.getUrl());
			sb.append("]");
			log.info(sb.toString());
		}
	}

	@Test
	public void testGetUrl() {
		try {
			File workingCopyPath = new File("/Users/jeffcaddel/ws/aggregate");
			String url = svnUtils.getUrl(workingCopyPath);
			log.info("url=" + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLastRevision() {
		try {
			String url = "https://svn.kuali.org/repos/student/enrollment/aggregate/trunk";
			File workingCopyPath = new File(".");
			log.info(url + " - Last revision: " + svnUtils.getLastRevision(url));
			log.info(workingCopyPath.getAbsolutePath() + " - Last revision: " + svnUtils.getLastRevision(workingCopyPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
