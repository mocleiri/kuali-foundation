package org.kuali.common.util.sync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.SyncResult;

public class DefaultSyncService implements SyncService {

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
			DirDiff diff = FileSystemUtils.getDiff(request);
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
		// Record the differences between the directories before we do anything
		List<DirDiff> diffs = getDiffs(requests);

		List<SyncResult> results = new ArrayList<SyncResult>();
		for (DirRequest request : requests) {
			
		}
		return results;
	}

}
