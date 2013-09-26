package org.kuali.common.util.file;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.SimpleScanner;

import com.google.common.collect.ImmutableList;

public class ListTest {

	@Test
	public void test() {
		try {
			List<String> repos = getRepoNames();
			Collections.reverse(repos);
			List<File> repoDirs = getRepos();
			// System.out.println("repos=" + repos.size());
			for (File repo : repoDirs) {
				// System.out.println("names.add(\"" + repo.getName() + "\");");
			}
			int i = 0;
			FileUtils.forceDelete(new File("/tmp/repos"));
			for (String repo : repos) {
				File dir = new CanonicalFile("/usr/local/sonatype-work/nexus/storage/" + repo);
				long start = System.currentTimeMillis();
				System.out.print(StringUtils.rightPad(dir.getPath(), 75));
				List<File> files = getRepoFiles(dir.getPath());
				printRepo(dir, files);
				String elapsed = FormatUtils.getTime(System.currentTimeMillis() - start);
				String count = FormatUtils.getCount(files.size());
				System.out.println(StringUtils.leftPad(elapsed, 10) + " - " + StringUtils.leftPad(count, 10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void printRepo(File repo, List<File> files) {
		File outputFile = new File("/tmp/repos/" + repo.getName() + ".txt");
		OutputStream out = null;
		try {
			out = FileUtils.openOutputStream(outputFile);
			for (File file : files) {
				long length = file.length();
				String path = StringUtils.replace(file.getPath(), repo.getPath(), "");
				String s = path + "," + length + "\n";
				byte[] bytes = s.getBytes("UTF-8");
				out.write(bytes);
			}
			out.flush();
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	protected List<File> getRepos() {
		File dir = new File("/usr/local/sonatype-work/nexus/storage");
		List<File> repos = Arrays.asList(dir.listFiles());
		List<File> canonical = new ArrayList<File>();
		for (File file : repos) {
			canonical.add(new CanonicalFile(file));
		}
		Collections.sort(canonical);
		return canonical;
	}

	protected List<File> getRepoFiles(String repoDir) {
		File basedir = new File(repoDir);
		List<String> includes = ImmutableList.of("**/**");
		// List<String> excludes = ImmutableList.of("**/.index/**", "**/.meta/**", "archetype-catalog.xml.sha1", "archetype-catalog.xml", "**/maven-metadata.xml",
		// "**/maven-metadata.xml.*");
		List<String> excludes = ImmutableList.of("**/.index/**", "**/.meta/**", "**/.nexus/**");
		SimpleScanner scanner = new SimpleScanner(basedir, includes, excludes);
		List<File> files = scanner.getFiles();
		List<File> canonical = new ArrayList<File>();
		for (File file : files) {
			canonical.add(new CanonicalFile(file));
		}
		Collections.sort(canonical);
		return canonical;
	}

	protected List<String> getRepoNames() {
		List<String> names = new ArrayList<String>();
		names.add("apache-snapshots");
		names.add("atlassian");
		names.add("central");
		names.add("central-m1");
		names.add("codehaus");
		names.add("codehaus-snapshots");
		names.add("developer");
		names.add("eclipselink");
		names.add("glassfish");
		names.add("google");
		names.add("google-reflections");
		names.add("hosted-private");
		names.add("jasperreports-sourceforge");
		names.add("java.net-m1");
		names.add("java.net-m1-m2");
		names.add("java.net-m2");
		names.add("jboss");
		names.add("jsdoctk");
		names.add("kuali-builds");
		names.add("kuali-legacy-releases");
		names.add("kuali-legacy-snapshots");
		names.add("kuali-private");
		names.add("kuali-release");
		names.add("kuali-s3-external");
		names.add("kuali-s3-private");
		names.add("kuali-snapshot");
		names.add("m2eclipse");
		names.add("maven-restlet");
		names.add("ow2");
		names.add("public");
		names.add("releases");
		names.add("saucelabs-repository");
		names.add("snapshots");
		names.add("sonatype-oss-releases");
		names.add("spring-milestones");
		names.add("thirdparty");
		return names;
	}

}
