package org.kuali.common.util.execute;

import java.io.File;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopyLocationsToDirectoryExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(CopyLocationsToDirectoryExecutable.class);

	boolean addSequenceToFilenames = true;
	String locationListing;
	File directory;
	String encoding;

	@Override
	public void execute() {
		if (NullUtils.isNullOrNone(locationListing)) {
			logger.info("Skipping execution.  Location listing [{}]", locationListing);
			return;
		}
		Assert.notNull(locationListing);
		Assert.notNull(directory);
		Assert.isTrue(LocationUtils.exists(locationListing));
		logger.info("Copying [{}] -> [{}]", locationListing, LocationUtils.getCanonicalPath(directory));
		List<String> locations = LocationUtils.getLocations(locationListing, encoding);
		List<String> filenames = LocationUtils.getFilenames(locations);
		if (addSequenceToFilenames) {
			filenames = CollectionUtils.getSequencedStrings(filenames);
		}
		List<File> files = LocationUtils.getFiles(directory, filenames);
		copyLocationsToFiles(locations, files, encoding);
		logger.info("Copied {} files", locations.size());
	}

	protected void copyLocationsToFiles(List<String> locations, List<File> files, String encoding) {
		Assert.isTrue(locations.size() == files.size());
		for (int i = 0; i < locations.size(); i++) {
			String location = locations.get(i);
			File destination = files.get(i);
			LocationUtils.copyLocationToFile(location, destination, encoding);
		}
	}

	public String getLocationListing() {
		return locationListing;
	}

	public void setLocationListing(String locationListing) {
		this.locationListing = locationListing;
	}

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	public boolean isAddSequenceToFilenames() {
		return addSequenceToFilenames;
	}

	public void setAddSequenceToFilenames(boolean addSequenceToFilenames) {
		this.addSequenceToFilenames = addSequenceToFilenames;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
