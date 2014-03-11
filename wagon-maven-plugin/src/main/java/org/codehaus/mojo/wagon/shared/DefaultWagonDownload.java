/**
 * Copyright 2008-2013 The Kuali Foundation
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
package org.codehaus.mojo.wagon.shared;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.apache.commons.io.FileUtils.touch;
import static org.codehaus.plexus.util.StringUtils.isBlank;
import static org.codehaus.plexus.util.StringUtils.leftPad;
import static org.kuali.common.util.FormatUtils.getTime;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.wagon.TransferFailedException;
import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.WagonException;

/**
 * @plexus.component role="org.codehaus.mojo.wagon.shared.WagonDownload" role-hint="default"
 */

public class DefaultWagonDownload implements WagonDownload {

	@Override
	public List<String> getFileList(Wagon wagon, WagonFileSet fileSet, Log logger) throws WagonException {
		logger.info("Scanning repository - " + wagon.getRepository().getUrl());

		WagonDirectoryScanner dirScan = new WagonDirectoryScanner();
		dirScan.setLogger(logger);
		dirScan.setWagon(wagon);
		dirScan.setExcludes(fileSet.getExcludes());
		dirScan.setIncludes(fileSet.getIncludes());
		dirScan.setCaseSensitive(fileSet.isCaseSensitive());
		dirScan.setDirectory(fileSet.getDirectory());
		if (fileSet.isUseDefaultExcludes()) {
			dirScan.addDefaultExcludes();
		}

		long start = currentTimeMillis();
		dirScan.scan();
		long elapsed = currentTimeMillis() - start;
		logger.info(format("Scan time: %ss", elapsed / 1000));
		logger.info("Files located: " + dirScan.getFilesIncluded().size());

		return dirScan.getFilesIncluded();
	}

	@Override
	public void download(Wagon wagon, WagonFileSet remoteFileSet, Log logger, boolean skipExisting) throws WagonException {
		List<String> fileList = getFileList(wagon, remoteFileSet, logger);

		String url = wagon.getRepository().getUrl();
		url = url.endsWith("/") ? url : url + "/";

		if (fileList.size() == 0) {
			logger.info("Nothing to download.");
			return;
		}

		int count = 0;
		long start = currentTimeMillis();
		List<String> skipped = newArrayList();
		for (String remoteFile : fileList) {
			String index = leftPad((++count) + "", 5, " ");

			File destination = new File(remoteFileSet.getDownloadDirectory() + "/" + remoteFile);

			if (skipExisting && destination.exists()) {
				String msg = index + " Skipping " + url + remoteFile + " - " + destination + " already exists";
				logger.debug(msg);
				skipped.add(msg);
				continue;
			}

			if (!isBlank(remoteFileSet.getDirectory())) {
				remoteFile = remoteFileSet.getDirectory() + "/" + remoteFile;
			}

			logger.info(index + " Downloading " + url + remoteFile + " to " + destination);
			try {
				touch(destination);
			} catch (IOException e) {
				throw new TransferFailedException("Unexpected IO error", e);
			}

			wagon.get(remoteFile, destination);
		}
		if (skipped.size() > 0) {
			logger.info("Skipped " + skipped.size() + " resources that already exist on the local file system");
		}
		logger.info(String.format("Download time: %s", getTime(currentTimeMillis() - start)));
	}

	/**
	 * 
	 * @param wagon
	 *            - a Wagon instance
	 * @param resource
	 *            - Remote resource to check
	 * @throws WagonException
	 */
	@Override
	public boolean exists(Wagon wagon, String resource) throws WagonException {
		return wagon.resourceExists(resource);
	}

}
