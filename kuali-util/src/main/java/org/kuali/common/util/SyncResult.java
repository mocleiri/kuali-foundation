package org.kuali.common.util;

import java.io.File;
import java.util.List;

public class SyncResult {

	List<File> adds;
	List<File> updates;
	List<File> deletes;

	public List<File> getAdds() {
		return adds;
	}

	public void setAdds(List<File> adds) {
		this.adds = adds;
	}

	public List<File> getUpdates() {
		return updates;
	}

	public void setUpdates(List<File> updates) {
		this.updates = updates;
	}

	public List<File> getDeletes() {
		return deletes;
	}

	public void setDeletes(List<File> deletes) {
		this.deletes = deletes;
	}

}
