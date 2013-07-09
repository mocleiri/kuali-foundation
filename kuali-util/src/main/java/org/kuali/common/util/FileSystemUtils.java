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
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSystemUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileSystemUtils.class);

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

	protected static List<File> getFullPaths(File dir, List<String> relativePaths) {
		List<File> files = new ArrayList<File>();
		for (String relativePath : relativePaths) {
			File file = new File(dir, relativePath);
			files.add(file);
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
	public static String getRelativePathQuietly(File relativeDir, File file) {
		Assert.notNull(file, "file is null");
		if (isParent(relativeDir, file)) {
			return getRelativePath(relativeDir, file);
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
	public static List<File> getAllNonScmFiles(File dir) {
		SimpleScanner scanner = new SimpleScanner(dir, Arrays.asList("**/*"), Arrays.asList("**/.svn/**", "**/.git/**"));
		return scanner.getFiles();
	}

	/**
	 * Return a recursive listing of all files in the directory but ignoring .svn and .git
	 */
	@Deprecated
	protected static List<File> getAllFiles(File dir) {
		return getAllNonScmFiles(dir);
	}

}
