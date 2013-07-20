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
package org.kuali.common.util.sync;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.SyncResult;
import org.kuali.common.util.execute.CopyFileRequest;
import org.kuali.common.util.execute.CopyFileResult;
import org.kuali.common.util.file.DirDiff;
import org.kuali.common.util.file.DirRequest;
import org.kuali.common.util.file.MD5Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSyncService implements SyncService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSyncService.class);

	@Override
	public DirDiff getDiff(DirRequest request) {
		List<DirRequest> requests = Arrays.asList(request);
		List<DirDiff> results = getDiffs(requests);
		return results.get(0);
	}

	@Override
	public List<DirDiff> getDiffs(List<DirRequest> requests) {
		List<DirDiff> diffs = new ArrayList<DirDiff>();
		for (DirRequest request : requests) {
			DirDiff diff = FileSystemUtils.getMD5Diff(request);
			diffs.add(diff);
		}
		return diffs;
	}

	@Override
	public SyncResult sync(DirRequest request) {
		List<DirRequest> requests = Arrays.asList(request);
		List<SyncResult> results = sync(requests);
		return results.get(0);
	}

	@Override
	public List<SyncResult> sync(List<DirRequest> requests) {

		logger.info("Synchronizing {} directories", requests.size());
		for (DirRequest request : requests) {
			String src = FileSystemUtils.getRelativePathQuietly(request.getRelativeDir(), request.getSourceDir());
			String dst = FileSystemUtils.getRelativePathQuietly(request.getRelativeDir(), request.getTargetDir());
			logger.info("  [{}] -> [{}]", src, dst);
		}

		// Scan the file system and record the differences between the directories.
		// This does a deep, heavy, scan of both directories.
		// It recursively examines the contents of both directories.
		// Files that exist in both, are compared for equality using an MD5 checksum
		List<DirDiff> diffs = getDiffs(requests);

		// Use the diff info to generate a list of files to copy
		List<CopyFileRequest> copyRequests = getCopyFileRequests(diffs);

		// Show how many files we are copying
		if (copyRequests.size() > 0) {
			logger.info("Copying {} files", copyRequests.size());
		}

		// Copy the files and record the results
		List<CopyFileResult> copyResults = FileSystemUtils.copyFiles(copyRequests);

		// Log the number of files copied
		logger.debug("Copied {} files", copyResults.size());

		// Convert the diff information into sync information (adds, deletes, updates)
		List<SyncResult> results = getSyncResults(diffs);

		// return the sync info
		return results;
	}

	protected List<SyncResult> getSyncResults(List<DirDiff> diffs) {
		List<SyncResult> results = new ArrayList<SyncResult>();
		for (DirDiff diff : diffs) {
			SyncResult result = getSyncResult(diff);
			results.add(result);
		}
		return results;
	}

	protected SyncResult getSyncResult(DirDiff diff) {
		List<File> adds = FileSystemUtils.getFullPaths(diff.getTargetDir(), diff.getSourceDirOnly());
		List<File> deletes = FileSystemUtils.getFullPaths(diff.getTargetDir(), diff.getTargetDirOnly());
		List<File> updates = new ArrayList<File>();
		for (MD5Result result : diff.getDifferent()) {
			updates.add(result.getTarget());
		}

		SyncResult result = new SyncResult();
		result.setAdds(adds);
		result.setDeletes(deletes);
		result.setUpdates(updates);
		return result;
	}

	protected List<CopyFileRequest> getCopyFileRequests(List<DirDiff> diffs) {
		List<CopyFileRequest> copyRequests = new ArrayList<CopyFileRequest>();
		for (DirDiff diff : diffs) {
			List<CopyFileRequest> list = getCopyFileRequests(diff);
			copyRequests.addAll(list);
		}
		return copyRequests;
	}

	/**
	 * Convert the diff into requests for copying files.
	 */
	protected List<CopyFileRequest> getCopyFileRequests(DirDiff diff) {

		// Copy all the files that were in source dir only
		List<CopyFileRequest> source = getCopyFileRequests(diff, diff.getSourceDirOnly());

		// Copy files that are in both directories but have different contents
		List<CopyFileRequest> different = getCopyFileRequestsForFilesThatAreDifferent(diff.getDifferent());

		// Return the combined list
		return CollectionUtils.combine(different, source);
	}

	protected List<CopyFileRequest> getCopyFileRequestsForFilesThatAreDifferent(List<MD5Result> different) {
		List<CopyFileRequest> requests = new ArrayList<CopyFileRequest>();
		for (MD5Result md5Result : different) {
			File source = md5Result.getSource();
			File target = md5Result.getTarget();
			CopyFileRequest request = new CopyFileRequest(source, target);
			requests.add(request);
		}
		return requests;
	}

	protected List<CopyFileRequest> getCopyFileRequests(DirDiff diff, List<String> relativePaths) {
		List<CopyFileRequest> requests = new ArrayList<CopyFileRequest>();
		for (String relativePath : relativePaths) {
			File source = new File(diff.getSourceDir(), relativePath);
			File target = new File(diff.getTargetDir(), relativePath);
			CopyFileRequest request = new CopyFileRequest(source, target);
			requests.add(request);
		}
		return requests;
	}

}
