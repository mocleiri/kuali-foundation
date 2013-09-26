package org.kuali.common.util.file;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.SimpleScanner;
import org.kuali.common.util.file.model.FileExtension;
import org.kuali.common.util.file.model.RepoFile;
import org.kuali.common.util.file.model.RepoStats;
import org.kuali.common.util.file.model.Repository;
import org.kuali.common.util.log.LoggerUtils;

import com.google.common.collect.ImmutableList;

public class ListTest {

	@Test
	public void getRepoListTest() {
		try {
			List<Repository> repos = getRepoList();
			logRepos(repos);
			Set<String> paths = getPaths(repos);
			List<FileExtension> extensions = getExtensions(paths);
			System.out.println("     Unique paths: " + FormatUtils.getCount(paths.size()));
			logFileExtensions(extensions);
			// logWeird(paths);
			analyzeRepos(repos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void analyzeRepos(List<Repository> repos) {
		for (Repository repo : repos) {
			analyzeRepo(repo);
		}
	}

	protected boolean hasCheckSum(RepoFile artifact, List<RepoFile> checksums) {
		String path = artifact.getPath();
		List<String> extensions = getCheckSumExtensions();
		for (String extension : extensions) {
			String checksumPath = path + "." + extension;
			if (checksums.contains(checksumPath)) {
				return true;
			}
		}
		return false;
	}

	protected RepoStats analyzeRepo(Repository repo) {
		List<RepoFile> checksums = getCheckSums(repo.getFiles());
		List<RepoFile> artifacts = getArtifacts(repo.getFiles());

		List<RepoFile> haveChecksums = new ArrayList<RepoFile>();
		List<RepoFile> missingChecksums = new ArrayList<RepoFile>();
		for (RepoFile artifact : artifacts) {
			boolean hasCheckSum = hasCheckSum(artifact, checksums);
			if (hasCheckSum) {
				haveChecksums.add(artifact);
			} else {
				missingChecksums.add(artifact);
			}
		}
		return new RepoStats(repo, checksums, artifacts, haveChecksums.size());
	}

	protected List<RepoFile> getArtifacts(List<RepoFile> files) {
		List<RepoFile> checksums = new ArrayList<RepoFile>();
		for (RepoFile file : files) {
			String path = file.getPath();
			if (!isChecksum(path)) {
				checksums.add(file);
			}
		}
		return checksums;
	}

	protected List<String> getCheckSumExtensions() {
		return Arrays.asList("sha1", "md5");
	}

	protected List<RepoFile> getCheckSums(List<RepoFile> files) {
		List<RepoFile> checksums = new ArrayList<RepoFile>();
		for (RepoFile file : files) {
			String path = file.getPath();
			if (isChecksum(path)) {
				checksums.add(file);
			}
		}
		return checksums;
	}

	protected void logWeird(Set<String> paths) {
		List<String> weird = new ArrayList<String>();
		for (String path : paths) {
			if (isWeird(path)) {
				weird.add(path);
			}
		}
		Collections.sort(weird);
		for (String s : weird) {
			System.out.println(s);
		}
	}

	protected boolean ignore(String path) {
		if (path.endsWith("archetype-catalog.xml")) {
			return true;
		}
		if (path.endsWith("maven-metadata.xml")) {
			return true;
		}
		if (path.endsWith("asc")) {
			return true;
		}
		if (path.endsWith("signature")) {
			return true;
		}
		if (path.endsWith("txt")) {
			return true;
		}
		if (path.contains("texen/1.0/orig/texen")) {
			return true;
		}
		if (path.contains("texen/1.0/try.texen")) {
			return true;
		}
		return false;
	}

	protected boolean isChecksum(String path) {
		List<String> checksumExtensions = getCheckSumExtensions();
		for (String ext : checksumExtensions) {
			if (path.endsWith(ext)) {
				return true;
			}
		}
		return false;
	}

	protected boolean isWeird(String path) {
		if (path.endsWith("tld")) {
			return true;
		}
		if (path.endsWith("mar")) {
			return true;
		}
		if (path.contains("texen")) {
			return true;
		}
		if (path.endsWith("signature")) {
			return true;
		}
		if (path.endsWith("txt")) {
			return true;
		}
		if (path.endsWith("asc")) {
			return true;
		}
		if (path.endsWith("gz")) {
			return true;
		}
		return false;
	}

	protected void logFileExtensions(List<FileExtension> extensions) {
		System.out.println("Unique extensions: " + FormatUtils.getCount(extensions.size()));
		for (FileExtension extension : extensions) {
			String count = FormatUtils.getCount(extension.getCount());
			System.out.println(StringUtils.rightPad(extension.getValue(), 16) + " - " + count);
		}
	}

	protected List<FileExtension> getExtensions(Set<String> paths) {
		Map<String, Integer> extensions = new TreeMap<String, Integer>();
		for (String path : paths) {
			String extension = FilenameUtils.getExtension(path);
			Integer count = extensions.get(extension) == null ? 1 : extensions.get(extension) + 1;
			extensions.put(extension, count);
		}
		List<FileExtension> list = new ArrayList<FileExtension>();
		for (Map.Entry<String, Integer> pair : extensions.entrySet()) {
			FileExtension fe = new FileExtension(pair.getKey(), pair.getValue());
			list.add(fe);
		}
		Collections.sort(list);
		Collections.reverse(list);
		return list;
	}

	protected Set<String> getPaths(List<Repository> repos) {
		Set<String> paths = new TreeSet<String>();
		for (Repository repo : repos) {
			List<RepoFile> files = repo.getFiles();
			for (RepoFile file : files) {
				String path = file.getPath();
				paths.add(path);
			}
		}
		return paths;
	}

	protected List<Repository> getRepoList() {
		List<String> names = getRepoNames();
		List<Repository> repos = new ArrayList<Repository>();
		for (String name : names) {
			String location = "classpath:repos/" + name + ".txt";
			List<String> lines = LocationUtils.readLines(location);
			List<RepoFile> files = getRepoFiles(lines);
			Repository repo = new Repository(name, files);
			repos.add(repo);
		}
		return repos;
	}

	protected void logRepos(List<Repository> repos) {
		Collections.sort(repos);
		Collections.reverse(repos);
		List<String> columns = ImmutableList.of("repo", "files", "size");
		List<Object[]> rows = new ArrayList<Object[]>();
		long totalFiles = 0;
		long totalSize = 0;
		for (Repository repo : repos) {
			String count = FormatUtils.getCount(repo.getFiles().size());
			String size = FormatUtils.getSize(repo.getSize());
			totalSize += repo.getSize();
			totalFiles += repo.getFiles().size();
			Object[] row = { repo.getName(), count, size };
			rows.add(row);
		}
		String tc = FormatUtils.getCount(totalFiles);
		String ts = FormatUtils.getSize(totalSize);
		rows.add(new Object[] { " ", " ", " " });
		rows.add(new Object[] { "totals", tc, ts });
		LoggerUtils.logTable("Repo Summary", columns, rows);
	}

	protected List<RepoFile> getRepoFiles(List<String> lines) {
		List<RepoFile> files = new ArrayList<RepoFile>();
		for (String line : lines) {
			String[] tokens = StringUtils.split(line, ",");
			String path = tokens[0];
			if (ignore(path)) {
				continue;
			}
			long size = Long.parseLong(tokens[1]);
			RepoFile file = new RepoFile(path, size);
			files.add(file);
		}
		return files;
	}

	@Test
	@Ignore
	public void generateFileListingsTest() {
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
