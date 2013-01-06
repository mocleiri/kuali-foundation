package org.kuali.common.util.execute;

import java.io.File;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;

public class CopyLocationsExecutable implements Executable {

	String locationListing;
	File directory;
	boolean addSequenceToFilenames = true;
	String encoding;

	@Override
    public void execute() {
		Assert.notNull(locationListing);
		Assert.notNull(directory);
		List<String> locations = LocationUtils.getLocations(locationListing, encoding);
		List<String> filenames = LocationUtils.getFilenames(locations);
		if (addSequenceToFilenames) {
			filenames = CollectionUtils.getSequencedStrings(filenames);
		}
		List<File> files = LocationUtils.getFiles(directory, filenames);
		LocationUtils.copyLocationsToFiles(locations, files, encoding);
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
