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
package org.kuali.common.util.execute;

import java.io.File;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
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
