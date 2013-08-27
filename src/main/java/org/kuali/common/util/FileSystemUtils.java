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
import org.kuali.common.util.execute.CopyFileRequest;
import org.kuali.common.util.execute.CopyFileResult;
import org.kuali.common.util.file.DirDiff;
import org.kuali.common.util.file.DirRequest;
import org.kuali.common.util.file.MD5Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSystemUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileSystemUtils.class);

	public static final String RECURSIVE_FILE_INCLUDE_PATTERN = "**/**";
	public static final List<String> DEFAULT_RECURSIVE_INCLUDES = Arrays.asList(RECURSIVE_FILE_INCLUDE_PATTERN);

	private static final String SVN_PATTERN = "**/.svn/**";
	private static final String GIT_PATTERN = "**/.git/**";
	public static final List<String> DEFAULT_SCM_IGNORE_PATTERNS = Arrays.asList(SVN_PATTERN, GIT_PATTERN);

	/**
	 * Return a recursive listing of all files in the directory ignoring <code>&#43;&#43;/.svn/&#43;&#43;</code> and <code>&#43;&#43;/.git/&#43;&#43;</code>
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
	 * This method recursively copies one file system directory to another directory under the control of SCM. Before doing so, it records 3 types of files:
	 * 
	 * <pre>
	 *  1 - both     - files that exist in both directories 
	 *  2 - dir1Only - files that exist in the source directory but not the SCM directory
	 *  3 - dir2Only - files that exist in the SCM directory but not the source directory
	 * </pre>
	 * 
	 * This provides enough information for SCM tooling to then complete the work of making the SCM directory exactly match the file system directory and commit any changes to the
	 * SCM system.
	 */
	@Deprecated
	public static DirectoryDiff prepareScmDir(PrepareScmDirRequest request) {
		return prepareScmDir(request, null, false);
	}

	/**
	 * This method recursively copies one file system directory to another directory under the control of SCM. Before doing so, it records 3 types of files:
	 * 
	 * <pre>
	 *  1 - both     - files that exist in both directories 
	 *  2 - dir1Only - files that exist in the source directory but not the SCM directory
	 *  3 - dir2Only - files that exist in the SCM directory but not the source directory
	 * </pre>
	 * 
	 * This provides enough information for SCM tooling to then complete the work of making the SCM directory exactly match the file system directory and commit any changes to the
	 * SCM system.
	 * 
	 * @deprecated
	 */
	@Deprecated
	public static DirectoryDiff prepareScmDir(PrepareScmDirRequest request, File relativeDir, boolean diffOnly) {

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
		DirectoryDiff diff = getDiff(diffRequest);

		// Copy files from the source directory to the SCM directory
		if (!diffOnly) {
			org.kuali.common.util.execute.CopyFilePatternsExecutable exec = new org.kuali.common.util.execute.CopyFilePatternsExecutable();
			exec.setSrcDir(request.getSrcDir());
			exec.setDstDir(request.getScmDir());
			exec.setExcludes(request.getScmIgnorePatterns());
			exec.setRelativeDir(relativeDir);
			exec.execute();
		}

		// Return the diff so we'll know what SCM needs to add/delete from its directory
		return diff;
	}

	public static List<File> getFiles(File dir, List<String> includes, List<String> excludes) {
		SimpleScanner scanner = new SimpleScanner(dir, includes, excludes);
		return scanner.getFiles();
	}

	@Deprecated
	public static DirectoryDiff getDiff(File dir1, File dir2, List<String> includes, List<String> excludes) {
		DirectoryDiffRequest request = new DirectoryDiffRequest();
		request.setDir1(dir1);
		request.setDir2(dir2);
		request.setIncludes(includes);
		request.setExcludes(excludes);
		return getDiff(request);
	}

	/**
	 * Compare 2 directories on the file system and return an object containing the results. All of the files contained in either of the 2 directories get aggregated into 5
	 * categories.
	 * 
	 * <pre>
	 * 1 - Both            - Files that exist in both directories
	 * 2 - Different       - Files that exist in both directories but who's MD5 checksums do not match 
	 * 3 - Identical       - Files that exist in both directories with matching MD5 checksums 
	 * 4 - Source Dir Only - Files that exist only in the source directory
	 * 5 - Target Dir Only - Files that exist only in the target directory
	 * </pre>
	 * 
	 * The 5 lists in <code>DirDiff</code> contain the relative paths to files for each category.
	 */
	public static DirDiff getMD5Diff(DirRequest request) {

		// Do a quick diff (just figures out what files are unique to each directory vs files that are in both)
		DirDiff diff = getQuickDiff(request);

		// Do a deep diff
		// This computes MD5 checksums for any files present in both directories
		fillInMD5Results(diff);

		// return the diff result
		return diff;
	}

	public static List<MD5Result> getMD5Results(List<File> sources, List<File> targets) {
		Assert.isTrue(sources.size() == targets.size(), "lists are not the same size");
		List<MD5Result> results = new ArrayList<MD5Result>();
		for (int i = 0; i < sources.size(); i++) {
			File source = sources.get(i);
			File target = targets.get(i);
			MD5Result md5Result = getMD5Result(source, target);
			results.add(md5Result);
		}
		return results;
	}

	protected static void fillInMD5Results(DirDiff diff) {
		List<File> sources = getFullPaths(diff.getSourceDir(), diff.getBoth());
		List<File> targets = getFullPaths(diff.getTargetDir(), diff.getBoth());

		List<MD5Result> results = getMD5Results(sources, targets);

		List<MD5Result> different = new ArrayList<MD5Result>();
		List<MD5Result> identical = new ArrayList<MD5Result>();
		for (MD5Result md5Result : results) {
			String sourceChecksum = md5Result.getSourceChecksum();
			String targetChecksum = md5Result.getTargetChecksum();
			Assert.notNull(sourceChecksum, "sourceChecksum is null");
			Assert.notNull(targetChecksum, "targetChecksum is null");
			if (StringUtils.equals(sourceChecksum, targetChecksum)) {
				identical.add(md5Result);
			} else {
				different.add(md5Result);
			}
		}

		//
		diff.setDifferent(different);
		diff.setIdentical(identical);
	}

	public static MD5Result getMD5Result(File source, File target) {

		String sourceChecksum = LocationUtils.getMD5Checksum(source);
		String targetChecksum = LocationUtils.getMD5Checksum(target);

		return new MD5Result(source, sourceChecksum, target, targetChecksum);
	}

	/**
	 * Compare 2 directories on the file system and return an object containing the results. All of the files contained in either of the 2 directories get placed into one of 3
	 * categories.
	 * 
	 * <pre>
	 * 1 - Both       - Files that exist in both directories
	 * 2 - Dir 1 Only - Files that exist only in directory 1
	 * 3 - Dir 2 Only - Files that exist only in directory 2
	 * </pre>
	 * 
	 * The 3 lists in <code>DirectoryDiff</code> contain the relative paths to files for each category.
	 */
	@Deprecated
	public static DirectoryDiff getDiff(DirectoryDiffRequest request) {
		DirRequest newRequest = new DirRequest();
		newRequest.setExcludes(request.getExcludes());
		newRequest.setIncludes(request.getIncludes());
		newRequest.setSourceDir(request.getDir1());
		newRequest.setTargetDir(request.getDir2());
		DirDiff diff = getQuickDiff(newRequest);

		DirectoryDiff dd = new DirectoryDiff(diff.getSourceDir(), diff.getTargetDir());
		dd.setBoth(diff.getBoth());
		dd.setDir1Only(diff.getSourceDirOnly());
		dd.setDir2Only(diff.getTargetDirOnly());
		return dd;
	}

	public static DirDiff getQuickDiff(DirRequest request) {

		// Get a listing of files from both directories using the exact same includes/excludes
		List<File> sourceFiles = getFiles(request.getSourceDir(), request.getIncludes(), request.getExcludes());
		List<File> targetFiles = getFiles(request.getTargetDir(), request.getIncludes(), request.getExcludes());

		// Get the unique set of paths for each file relative to their parent directory
		Set<String> sourcePaths = new HashSet<String>(getRelativePaths(request.getSourceDir(), sourceFiles));
		Set<String> targetPaths = new HashSet<String>(getRelativePaths(request.getTargetDir(), targetFiles));

		// Paths that exist in both directories
		Set<String> both = SetUtils.intersection(sourcePaths, targetPaths);

		// Paths that exist in source but not target
		Set<String> sourceOnly = SetUtils.difference(sourcePaths, targetPaths);

		// Paths that exist in target but not source
		Set<String> targetOnly = SetUtils.difference(targetPaths, sourcePaths);

		logger.debug("source={}, sourceOnly.size()={}", request.getSourceDir(), sourceOnly.size());
		logger.debug("target={}, targetOnly.size()={}", request.getTargetDir(), targetOnly.size());

		// Store the information we've collected into a result object
		DirDiff result = new DirDiff(request.getSourceDir(), request.getTargetDir());

		// Store the relative paths on the diff object
		result.setBoth(new ArrayList<String>(both));
		result.setSourceDirOnly(new ArrayList<String>(sourceOnly));
		result.setTargetDirOnly(new ArrayList<String>(targetOnly));

		// Sort the relative paths
		Collections.sort(result.getBoth());
		Collections.sort(result.getSourceDirOnly());
		Collections.sort(result.getTargetDirOnly());

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

	public static List<File> getFullPaths(File dir, Set<String> relativePaths) {
		return getFullPaths(dir, new ArrayList<String>(relativePaths));
	}

	public static List<File> getSortedFullPaths(File dir, List<String> relativePaths) {
		List<File> files = getFullPaths(dir, relativePaths);
		Collections.sort(files);
		return files;
	}

	public static List<File> getFullPaths(File dir, List<String> relativePaths) {
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
	 * Return the relative path to <code>file</code> from <code>parentDir</code>. <code>parentDir</code> is optional and can be <code>null</code>. If <code>parentDir</code> is not
	 * supplied (or is not a parent directory to <code>file</code> the canonical path to <code>file</code> is returned.
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

	public static List<CopyFileRequest> getCopyFileRequests(File srcDir, List<String> includes, List<String> excludes, File dstDir) {
		SimpleScanner scanner = new SimpleScanner(srcDir, includes, excludes);
		List<File> srcFiles = scanner.getFiles();

		List<CopyFileRequest> requests = new ArrayList<CopyFileRequest>();
		for (File srcFile : srcFiles) {
			String relativePath = FileSystemUtils.getRelativePath(srcDir, srcFile);
			File dstFile = new File(dstDir, relativePath);
			CopyFileRequest request = new CopyFileRequest(srcFile, dstFile);
			requests.add(request);
		}
		return requests;
	}

	public static CopyFileResult copyFile(File src, File dst) {
		try {
			long start = System.currentTimeMillis();
			boolean overwritten = dst.exists();
			FileUtils.copyFile(src, dst);
			return new CopyFileResult(src, dst, overwritten, System.currentTimeMillis() - start);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	public static List<CopyFileResult> copyFiles(List<CopyFileRequest> requests) {
		List<CopyFileResult> results = new ArrayList<CopyFileResult>();
		for (CopyFileRequest request : requests) {
			CopyFileResult result = copyFile(request.getSource(), request.getDestination());
			results.add(result);
		}
		return results;
	}

}
