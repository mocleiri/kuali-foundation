/**
 * Copyright 2010-2014 The Kuali Foundation
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
package org.kuali.common.jdbc.convert;

import java.io.File;
import java.util.List;

/**
 * This post conversion processor deletes each file used as input for the conversion, and renames the newly created file by removing the
 * ".converted" extension
 *
 * @author andrewlubbers
 */
public class ReplaceWithConvertedProcessor implements PostConversionProcessor {
	@Override
	public void process(List<ConversionResult> conversionResults) {
		for (ConversionResult result : conversionResults) {
			if (!result.getOldFile().delete()) {
				throw new IllegalStateException("Unable to delete file " + result.getOldFile().getAbsolutePath());
			}

			String newFilePath = result.getNewFile().getAbsolutePath().replace(DirectoryConverter.CONVERTED_EXTENSION, "");

			if (!result.getNewFile().renameTo(new File(newFilePath))) {
				throw new IllegalStateException("Unable to rename file " + result.getNewFile().getAbsolutePath());
			}
		}
	}
}
