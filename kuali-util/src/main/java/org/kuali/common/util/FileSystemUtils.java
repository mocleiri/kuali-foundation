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
import org.kuali.common.util.execute.CopyFilesExecutable;
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

	/**
	 * This method recursively copies a file system directory to a different directory under the control of SCM. Before it does so, it records 3 types of files:
	 * 
	 * <pre>
	 *  1 - updates - files that exist in both directories 
	 *  2 - adds    - files that exist in the source directory but not the SCM directory
	 *  3 - deletes - files that exist in the SCM directory but not the source directory
	 * </pre>
	 * 
	 * This provides enough information for SCM tooling to then complete the work of making the SCM directory exactly match the file system directory and commit any changes to the
	 * SCM system.
	 */
	public static DirectoryDiffResult prepareScmDir(PrepareScmDirRequest request) {

		// Make sure we are configured correctly
		Assert.notNull(request, "request is null");
		Assert.notNull(request.getSrcDir(), "srcDir is null");
		Assert.notNull(request.getScmDir(), "scmDir is null");

		// Both must already exist and must be directories (can't be a regular file)
		Assert.isExistingDir(request.getSrcDir(), "srcDir is not an existing directory");
		Assert.isExistingDir(request.getScmDir(), "scmDir is not an existing directory");

		// Setup a diff request
		DirectoryDiffRequest diffRequest = new DirectoryDiffRequest();
		diffRequest.setDir1(request.getSrcDir());
		diffRequest.setDir2(request.getScmDir());
		diffRequest.setExcludes(request.getScmIgnorePatterns());

		// Record the differences between the two directories
		DirectoryDiffResult diff = getDiff(diffRequest);

		// Copy files from the source directory to the SCM directory
		CopyFilesExecutable exec = new CopyFilesExecutable();
		exec.setSrcDir(request.getSrcDir());
		exec.setDstDir(request.getScmDir());
		exec.setExcludes(request.getScmIgnorePatterns());
		exec.execute();

		// Return the diff so we'll know what SCM needs to add/delete from its directory
		return diff;
	}

	public static List<File> getFiles(File dir, List<String> includes, List<String> excludes) {
		SimpleScanner scanner = new SimpleScanner(dir, includes, excludes);
		return scanner.getFiles();
	}

	public static DirectoryDiffResult getDiff(File dir1, File dir2, List<String> includes, List<String> excludes) {
		DirectoryDiffRequest request = new DirectoryDiffRequest();
		request.setDir1(dir1);
		request.setDir2(dir2);
		request.setIncludes(includes);
		request.setExcludes(excludes);
		return getDiff(request);
	}

	public static DirectoryDiffResult getDiff(DirectoryDiffRequest request) {

		// Get a listing of files from both directories using the exact same includes/excludes
		List<File> dir1Files = getFiles(request.getDir1(), request.getIncludes(), request.getExcludes());
		List<File> dir2Files = getFiles(request.getDir2(), request.getIncludes(), request.getExcludes());

		// Get the unique set of paths for each file relative to their parent directory
		Set<String> dir1Paths = new HashSet<String>(getRelativePaths(request.getDir1(), dir1Files));
		Set<String> dir2Paths = new HashSet<String>(getRelativePaths(request.getDir2(), dir2Files));

		// Paths that exist in both directories
		Set<String> both = SetUtils.intersection(dir1Paths, dir2Paths);

		// Paths that exist in dir1 but not dir2
		Set<String> dir1Only = SetUtils.difference(dir1Paths, dir2Paths);

		// Paths that exist in dir2 but not dir1
		Set<String> dir2Only = SetUtils.difference(dir2Paths, dir1Paths);

		// Store the information we've collected into a result object
		DirectoryDiffResult result = new DirectoryDiffResult(request.getDir1(), request.getDir2());

		// Store the relative paths on the diff object
		result.setBoth(new ArrayList<String>(both));
		result.setDir1Only(new ArrayList<String>(dir1Only));
		result.setDir2Only(new ArrayList<String>(dir2Only));

		// Sort the relative paths
		Collections.sort(result.getBoth());
		Collections.sort(result.getDir1Only());
		Collections.sort(result.getDir2Only());

		// return the diff
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
		List<File> dstFiles = getAllNonScmFiles(request.getDstDir());
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

	protected static List<File> getSortedFullPaths(File dir, List<String> relativePaths) {
		List<File> files = getFullPaths(dir, relativePaths);
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

}
