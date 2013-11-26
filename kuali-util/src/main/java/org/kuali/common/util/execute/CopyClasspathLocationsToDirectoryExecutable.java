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

import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopyClasspathLocationsToDirectoryExecutable extends AbstractCopyLocationsExecutable {

	private static final Logger logger = LoggerFactory.getLogger(CopyClasspathLocationsToDirectoryExecutable.class);

    public CopyClasspathLocationsToDirectoryExecutable() {
        super(null, null);
    }

    public CopyClasspathLocationsToDirectoryExecutable(String locationListing, File directory) {
        super(locationListing, directory);
    }

	@Override
	protected List<File> getFiles(List<String> locations) {
		List<String> filenames = LocationUtils.getClasspathFilenames(locations);
		logger.debug("Located {} files", filenames.size());
		return LocationUtils.getFiles(directory, filenames);
    }
}
