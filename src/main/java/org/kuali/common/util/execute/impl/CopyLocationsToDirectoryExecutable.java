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

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public final class CopyLocationsToDirectoryExecutable extends AbstractCopyLocationsExecutable {

	private static final Logger logger = LoggerUtils.make();

	private final boolean addSequenceToFilenames;
	private final int initialSequenceNumber;

	public CopyLocationsToDirectoryExecutable(String locationListing, File directory) {
		this(locationListing, directory, true);
	}

	public CopyLocationsToDirectoryExecutable(String locationListing, File directory, boolean addSequenceToFilenames) {
		this(locationListing, directory, addSequenceToFilenames, 1);
	}

	public CopyLocationsToDirectoryExecutable(String locationListing, File directory, boolean addSequenceToFilenames, int initialSequenceNumber) {
		this(locationListing, directory, addSequenceToFilenames, initialSequenceNumber, false);
	}

	public CopyLocationsToDirectoryExecutable(String locationListing, File directory, boolean addSequenceToFilenames, int initialSequenceNumber, boolean skip) {
		super(locationListing, directory, skip);

		this.addSequenceToFilenames = addSequenceToFilenames;
		this.initialSequenceNumber = initialSequenceNumber;
	}

	@Override
	protected List<File> getFiles(List<String> locations) {
		List<String> filenames = LocationUtils.getFilenames(locations);
		if (addSequenceToFilenames) {
			logger.debug("Adding sequences");
			filenames = CollectionUtils.getSequencedStrings(filenames, initialSequenceNumber);
		}
		return LocationUtils.getFiles(getDirectory(), filenames);
	}

	public boolean isAddSequenceToFilenames() {
		return addSequenceToFilenames;
	}

	public int getInitialSequenceNumber() {
		return initialSequenceNumber;
	}
}