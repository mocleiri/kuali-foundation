package org.kuali.common.util.sync;

import java.util.List;

import org.kuali.common.util.DirectoryDiff;
import org.kuali.common.util.DirectoryDiffRequest;

public interface SyncService {

	DirectoryDiff getDiff(DirectoryDiffRequest request);

	List<DirectoryDiff> getDiffs(List<DirectoryDiff> requests);

}
