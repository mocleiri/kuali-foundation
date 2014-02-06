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
package org.kuali.common.util.execute.impl;

import java.io.File;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCopyLocationsExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(AbstractCopyLocationsExecutable.class);

	private final String locationListing;
	private final File directory;
	private final boolean skip;

	public AbstractCopyLocationsExecutable(String locationListing, File directory) {
		this(locationListing, directory, false);
	}

	public AbstractCopyLocationsExecutable(String locationListing, File directory, boolean skip) {
		Assert.noBlanks(locationListing);
		Assert.notNull(directory);
		Assert.exists(locationListing);

		this.locationListing = locationListing;
		this.directory = directory;
		this.skip = skip;
	}

	protected abstract List<File> getFiles(List<String> locations);

	@Override
	public void execute() {
		logger.info("Copying [{}] -> [{}]", locationListing, LocationUtils.getCanonicalPath(directory));
		List<String> locations = LocationUtils.getLocations(locationListing);
		List<File> files = getFiles(locations);
		LocationUtils.copyLocationsToFiles(locations, files);
		logger.info("Copied {} files", locations.size());
	}

	public String getLocationListing() {
		return locationListing;
	}

	public File getDirectory() {
		return directory;
	}

	public boolean isSkip() {
		return skip;
	}
}
