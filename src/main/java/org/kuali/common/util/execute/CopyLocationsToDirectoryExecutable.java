package org.kuali.common.util.execute;

import java.io.File;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopyLocationsToDirectoryExecutable extends AbstractCopyLocationsExecutable {

	private static final Logger logger = LoggerFactory.getLogger(CopyLocationsToDirectoryExecutable.class);

	boolean addSequenceToFilenames = true;
	int initialSequenceNumber = 1;

	@Override
	protected List<File> getFiles(List<String> locations) {
		List<String> filenames = LocationUtils.getFilenames(locations);
		if (addSequenceToFilenames) {
			logger.debug("Adding sequences");
			filenames = CollectionUtils.getSequencedStrings(filenames, initialSequenceNumber);
		}
		return LocationUtils.getFiles(directory, filenames);
	}

	public boolean isAddSequenceToFilenames() {
		return addSequenceToFilenames;
	}

	public void setAddSequenceToFilenames(boolean addSequenceToFilenames) {
		this.addSequenceToFilenames = addSequenceToFilenames;
	}

	public int getInitialSequenceNumber() {
		return initialSequenceNumber;
	}

	public void setInitialSequenceNumber(int initialSequenceNumber) {
		this.initialSequenceNumber = initialSequenceNumber;
	}

}
