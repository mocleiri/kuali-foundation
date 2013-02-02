package org.kuali.common.util.execute;

import java.io.File;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCopyLocationsExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(AbstractCopyLocationsExecutable.class);

	String locationListing;
	File directory;

	protected abstract List<File> getFiles(List<String> locations);

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
		List<String> locations = LocationUtils.getLocations(locationListing);
		List<File> files = getFiles(locations);
		LocationUtils.copyLocationsToFiles(locations, files);
		logger.info("Copied {} files", locations.size());
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
}
