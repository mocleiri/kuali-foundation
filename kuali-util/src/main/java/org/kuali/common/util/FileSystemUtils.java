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

	public static MonitorTextFileResult monitorTextFile(File file, String token, int interval, int timeout, String encoding) {

		// Make sure we are configured correctly
		Assert.notNull(file, "file is null");
		Assert.hasText(token, "token has no text");
		Assert.isTrue(interval > 0, "interval must be a positive integer");
		Assert.isTrue(timeout > 0, "timeout must be a positive integer");

		// Setup some member variables to record what happens
		long start = System.currentTimeMillis();
		long stop = start + timeout;
		boolean exists = false;
		boolean contains = false;
		boolean timeoutExceeded = false;
		long now = -1;
		String content = null;
		for (;;) {

			// Always pause (unless this is the first iteration)
			if (now != -1) {
				sleep(interval);
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
		mtfr.setFile(file);
		mtfr.setContent(content);
		return mtfr;
	}

	protected static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	public static List<SyncResult> syncFiles(List<SyncRequest> requests) throws IOException {
		List<SyncResult> results = new ArrayList<SyncResult>();
		for (SyncRequest request : requests) {
			SyncResult result = syncFiles(request);
			results.add(result);
		}
		return results;
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

	protected static String getRelativePath(File dir, File file) {
		String dirPath = LocationUtils.getCanonicalPath(dir);
		String filePath = LocationUtils.getCanonicalPath(file);
		if (!StringUtils.contains(filePath, dirPath)) {
			throw new IllegalArgumentException(file + " does not reside under " + dir);
		}
		return StringUtils.remove(filePath, dirPath);
	}

	protected static List<File> getAllFiles(File dir) {
		SimpleScanner scanner = new SimpleScanner(dir, Arrays.asList("**/*"), Arrays.asList("**/.svn/**", "**/.git/**"));
		return scanner.getFiles();
	}

}
