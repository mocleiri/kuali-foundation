package org.kuali.common.util.sync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.DirectoryDiff;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.SyncResult;

public class DefaultSyncService implements SyncService {

	@Override
	public DirectoryDiff getDiff(DirectoryRequest request) {
		List<DirectoryRequest> requests = Arrays.asList(request);
		List<DirectoryDiff> results = getDiffs(requests);
		return results.get(0);
	}

	@Override
	public List<DirectoryDiff> getDiffs(List<DirectoryRequest> requests) {
		List<DirectoryDiff> diffs = new ArrayList<DirectoryDiff>();
		for (DirectoryRequest request : requests) {
			DirectoryDiff diff = FileSystemUtils.getDiff(request);
			diffs.add(diff);
		}
		return diffs;
	}

	@Override
	public SyncResult sync(DirectoryRequest request) {
		List<DirectoryRequest> requests = Arrays.asList(request);
		List<SyncResult> results = sync(requests);
		return results.get(0);
	}

	@Override
	public List<SyncResult> sync(List<DirectoryRequest> requests) {
		// Record the differences between the directories before we do anything
		List<DirectoryDiff> diffs = getDiffs(requests);

		List<SyncResult> results = new ArrayList<SyncResult>();
		for (DirectoryRequest request : requests) {
			
		}
		return results;
	}

}
