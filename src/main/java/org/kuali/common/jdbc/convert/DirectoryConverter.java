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
package org.kuali.common.jdbc.convert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.SimpleScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryConverter {

    public static final String CONVERTED_EXTENSION = ".converted";

    private static final Logger logger = LoggerFactory.getLogger(DirectoryConverter.class);

    public void convert(DirectoryContext context) {
        logger.info("Scanning directory " + context.getDirectory().getAbsolutePath());
        logger.info("Included file pattern: " + context.getInclude());
        logger.info("Excluded file pattern: " + context.getExclude());
		SimpleScanner scanner = new SimpleScanner(context.getDirectory(), context.getInclude(), context.getExclude());
		List<File> oldFiles = scanner.getFiles();
        logger.info("Found " + oldFiles.size() + " files for conversion");
		List<File> newFiles = getNewFiles(oldFiles);
        List<ConversionResult> results = convert(context, oldFiles, newFiles);

        logger.info("Conversion complete");
        if(context.getPostProcessor() != null) {
            logger.info("Initiating post conversion processor of type: " + context.getPostProcessor().getClass().getName());
            context.getPostProcessor().process(results);
        }
	}

	protected List<ConversionResult> convert(DirectoryContext context, List<File> oldFiles, List<File> newFiles) {
		// The lists must be the same size
		Assert.isTrue(oldFiles.size() == newFiles.size());

		SqlConverter converter = context.getConverter();
		List<ConversionResult> results = new ArrayList<ConversionResult>();
		for (int i = 0; i < oldFiles.size(); i++) {
			File oldFile = oldFiles.get(i);
			File newFile = newFiles.get(i);
			ConversionContext cc = new ConversionContext();
			cc.setNewFile(newFile);
			cc.setOldFile(oldFile);
			ConversionResult result = converter.convert(cc);
			results.add(result);
		}
		return results;
	}

	protected List<File> getNewFiles(List<File> oldFiles) {
		List<File> newFiles = new ArrayList<File>();
		for (File oldFile : oldFiles) {
			File newFile = new File(oldFile.getAbsolutePath() + CONVERTED_EXTENSION);
			newFiles.add(newFile);
		}
		return newFiles;
	}
}
