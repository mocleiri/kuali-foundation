/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSystemUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileSystemUtils.class);

	public static final String RECURSIVE_FILE_INCLUDE_PATTERN = "**/*";
	public static final List<String> DEFAULT_RECURSIVE_INCLUDES = Arrays.asList(RECURSIVE_FILE_INCLUDE_PATTERN);

	private static final String SVN_PATTERN = "**/.svn/*";
	private static final String GIT_PATTERN = "**/.git/*";
	public static final List<String> DEFAULT_SCM_IGNORE_PATTERNS = Arrays.asList(SVN_PATTERN, GIT_PATTERN);

	/**
	 * Return a recursive listing of all files in the directory ignoring <code>&#43;&#43;/.svn/*</code> and <code>&#43;&#43;/.git/*</code>
	 */
	public static List<File> getAllNonScmFiles(File dir) {
		return getAllNonScmFiles(dir, DEFAULT_SCM_IGNORE_PATTERNS);
	}

	/**
	 * Return a recursive listing of all files in the directory ignoring files that match <code>scmIgnorePatterns</code>
	 */
	public static List<File> getAllNonScmFiles(File dir, List<String> scmIgnorePatterns) {
		SimpleScanner scanner = new SimpleScanner(dir, DEFAULT_RECURSIVE_INCLUDES, scmIgnorePatterns);
		return scanner.getFiles();
	}

	public static SyncScmDirResult syncScmDir(SyncScmDirRequest request) {

		// Make sure the request is configured correctly
		Assert.notNull(request.getSrcDir(), "srcDir is null");
		Assert.notNull(request.getScmDir(), "scmDir is null");
		Assert.isExistingDir(request.getSrcDir(), "srcDir is not an existing directory");
		Assert.isExistingDir(request.getScmDir(), "scmDir is not an existing directory");

		// Get a recursive listing of all files from both directories. Ignore SCM metadata directories like .svn, .git, etc
		List<File> srcFiles = getAllNonScmFiles(request.getSrcDir(), request.getScmIgnorePatterns());
		List<File> scmFiles = getAllNonScmFiles(request.getScmDir(), request.getScmIgnorePatterns());

		// Get the unique set of paths for each file relative to their parent directory
		Set<String> srcPaths = new HashSet<String>(getRelativePaths(request.getSrcDir(), srcFiles));
		Set<String> scmPaths = new HashSet<String>(getRelativePaths(request.getScmDir(), scmFiles));

		// Files that already exist in both directories do not require an additional SCM step eg adding them or deleting them from SCM
		Set<String> updates = SetUtils.intersection(srcPaths, scmPaths);

		// Files that exist in the source directory but not the SCM directory need to be added to SCM
		Set<String> adds = SetUtils.difference(srcPaths, scmPaths);

		// Files that exist in the SCM directory but not the source directory need to be deleted from SCM
		Set<String> deletes = SetUtils.difference(scmPaths, srcPaths);

		try {
			copyFiles(request.getSrcDir(), srcFiles, request.getScmDir());
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}

		SyncScmDirResult result = new SyncScmDirResult();
		result.setAdds(getSortedFullPaths(request.getScmDir(), adds));
		result.setUpdates(getSortedFullPaths(request.getScmDir(), updates));
		result.setDeletes(getSortedFullPaths(request.getScmDir(), deletes));
		return result;
	}

	/**
	 * Examine the contents of a text file, stopping as soon as it contains <code>token</code>, or <code>timeout</code> is exceeded, whichever comes first.
	 */
	public static MonitorTextFileResult monitorTextFile(File file, String token, int intervalMillis, int timeoutMillis, String encoding) {

		// Make sure we are configured correctly
		Assert.notNull(file, "file is null");
		Assert.hasText(token, "token has no text");
		Assert.hasText(encoding, "encoding has no text");
		Assert.isTrue(intervalMillis > 0, "interval must be a positive integer");
		Assert.isTrue(timeoutMillis > 0, "timeout must be a positive integer");

		// Setup some member variables to record what happens
		long start = System.currentTimeMillis();
		long stop = start + timeoutMillis;
		boolean exists = false;
		boolean contains = false;
		boolean timeoutExceeded = false;
		long now = -1;
		String content = null;

		// loop until timeout is exceeded or we find the token inside the file
		for (;;) {

			// Always pause (unless this is the first iteration)
			if (now != -1) {
				ThreadUtils.sleep(intervalMillis);
			}

			// Check to make sure we haven't exceeded our timeout limit
			now = System.currentTimeMillis();
			if (now > stop) {
				timeoutExceeded = true;
				break;
			}

			// If the file does not exist, no point in going any further
			exists = LocationUtils.exists(file);
			if (!exists) {
				continue;
			}

			// The file exists, check to see if the token we are looking for is present in the file
			content = LocationUtils.toString(file, encoding);
			contains = StringUtils.contains(content, token);
			if (contains) {
				// We found what we are looking for, we are done
				break;
			}
		}

		// Record how long the overall process took
		long elapsed = now - start;

		// Fill in a pojo detailing what happened
		MonitorTextFileResult mtfr = new MonitorTextFileResult(exists, contains, timeoutExceeded, elapsed);
		mtfr.setAbsolutePath(LocationUtils.getCanonicalPath(file));
		mtfr.setContent(content);
		return mtfr;
	}

	public static List<SyncResult> syncFiles(List<SyncRequest> requests) throws IOException {
		List<SyncResult> results = new ArrayList<SyncResult>();
		for (SyncRequest request : requests) {
			SyncResult result = syncFiles(request);
			results.add(result);
		}
		return results;
	}

	public static SyncResult syncFilesQuietly(SyncRequest request) {
		try {
			return syncFiles(request);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error");
		}
	}

	public static SyncResult syncFiles(SyncRequest request) throws IOException {
		logger.info("Sync [{}] -> [{}]", request.getSrcDir(), request.getDstDir());
		List<File> dstFiles = getAllFiles(request.getDstDir());
		List<File> srcFiles = request.getSrcFiles();

		List<String> dstPaths = getRelativePaths(request.getDstDir(), dstFiles);
		List<String> srcPaths = getRelativePaths(request.getSrcDir(), srcFiles);

		List<String> adds = new ArrayList<String>();
		List<String> updates = new ArrayList<String>();
		List<String> deletes = new ArrayList<String>();

		for (String srcPath : srcPaths) {
			boolean existing = dstPaths.contains(srcPath);
			if (existing) {
				updates.add(srcPath);
			} else {
				adds.add(srcPath);
			}
		}
		for (String dstPath : dstPaths) {
			boolean extra = !srcPaths.contains(dstPath);
			if (extra) {
				deletes.add(dstPath);
			}
		}

		copyFiles(request.getSrcDir(), request.getSrcFiles(), request.getDstDir());

		SyncResult result = new SyncResult();
		result.setAdds(getFullPaths(request.getDstDir(), adds));
		result.setUpdates(getFullPaths(request.getDstDir(), updates));
		result.setDeletes(getFullPaths(request.getDstDir(), deletes));
		return result;
	}

	protected static void copyFiles(File srcDir, List<File> files, File dstDir) throws IOException {
		for (File file : files) {
			String relativePath = getRelativePath(srcDir, file);
			File dstFile = new File(dstDir, relativePath);
			FileUtils.copyFile(file, dstFile);
		}
	}

	protected static List<File> getFullPaths(File dir, Set<String> relativePaths) {
		return getFullPaths(dir, new ArrayList<String>(relativePaths));
	}

	protected static List<File> getSortedFullPaths(File dir, Set<String> relativePaths) {
		List<File> files = getFullPaths(dir, new ArrayList<String>(relativePaths));
		Collections.sort(files);
		return files;
	}

	protected static List<File> getFullPaths(File dir, List<String> relativePaths) {
		List<File> files = new ArrayList<File>();
		for (String relativePath : relativePaths) {
			File file = new File(dir, relativePath);
			File canonical = new File(LocationUtils.getCanonicalPath(file));
			files.add(canonical);
		}
		return files;
	}

	protected static List<String> getRelativePaths(File dir, List<File> files) {
		List<String> relativePaths = new ArrayList<String>();
		for (File file : files) {
			String relativePath = getRelativePath(dir, file);
			relativePaths.add(relativePath);
		}
		return relativePaths;
	}

	/**
	 * Return true if child lives on the file system somewhere underneath parent, false otherwise.
	 */
	public static boolean isParent(File parent, File child) {
		if (parent == null || child == null) {
			return false;
		}

		String parentPath = LocationUtils.getCanonicalPath(parent);
		String childPath = LocationUtils.getCanonicalPath(child);

		if (StringUtils.equals(parentPath, childPath)) {
			return false;
		} else {
			return StringUtils.contains(childPath, parentPath);
		}
	}

	/**
	 * Return the relative path to <code>file</code> from <code>relativePath</code>. <code>relativePath</code> is optional and can be <code>null</code>. If
	 * <code>relativePath</code> is not supplied (or is not a parent directory to <code>file</code> the canonical path to <code>file</code> is returned.
	 */
	public static String getRelativePathQuietly(File parentDir, File file) {
		Assert.notNull(file, "file is null");
		if (isParent(parentDir, file)) {
			return getRelativePath(parentDir, file);
		} else {
			return LocationUtils.getCanonicalPath(file);
		}
	}

	public static String getRelativePath(File dir, File file) {
		String dirPath = LocationUtils.getCanonicalPath(dir);
		String filePath = LocationUtils.getCanonicalPath(file);
		if (!StringUtils.contains(filePath, dirPath)) {
			throw new IllegalArgumentException(file + " does not reside under " + dir);
		}
		return StringUtils.remove(filePath, dirPath);
	}

	/**
	 * Return a recursive listing of all files in the directory but ignoring .svn and .git
	 */
	@Deprecated
	protected static List<File> getAllFiles(File dir) {
		return getAllNonScmFiles(dir);
	}

}
