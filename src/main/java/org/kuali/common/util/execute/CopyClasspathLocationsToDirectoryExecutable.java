package org.kuali.common.util.execute;

import java.io.File;
import java.util.List;

import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopyClasspathLocationsToDirectoryExecutable extends AbstractCopyLocationsExecutable {

	private static final Logger logger = LoggerFactory.getLogger(CopyClasspathLocationsToDirectoryExecutable.class);

	@Override
	protected List<File> getFiles(List<String> locations) {
		List<String> filenames = LocationUtils.getClasspathFilenames(locations);
		logger.debug("Located {} files", filenames.size());
		return LocationUtils.getFiles(directory, filenames);
	}
}
