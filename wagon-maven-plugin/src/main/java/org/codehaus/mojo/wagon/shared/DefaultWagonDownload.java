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

import static com.google.common.collect.Maps.newTreeMap;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.util.Collections.sort;
import static org.apache.commons.io.FileUtils.touch;
import static org.codehaus.plexus.util.StringUtils.isBlank;
import static org.kuali.common.util.FormatUtils.getCount;
import static org.kuali.common.util.FormatUtils.getRate;
import static org.kuali.common.util.FormatUtils.getSize;
import static org.kuali.common.util.FormatUtils.getTime;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.WagonException;
import org.kuali.common.util.file.CanonicalFile;

import com.google.common.collect.Lists;

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

		SortedMap<String, CanonicalFile> skipped = newTreeMap();
		SortedMap<String, CanonicalFile> downloads = newTreeMap();
		for (String remoteFile : fileList) {
			CanonicalFile destination = new CanonicalFile(remoteFileSet.getDownloadDirectory() + "/" + remoteFile);
			if (!isBlank(remoteFileSet.getDirectory())) {
				remoteFile = remoteFileSet.getDirectory() + "/" + remoteFile;
			}
			if (skipExisting && destination.exists()) {
				skipped.put(remoteFile, destination);
			} else {
				downloads.put(remoteFile, destination);
			}
		}

		long start = currentTimeMillis();
		for (String remoteFile : downloads.keySet()) {
			CanonicalFile destination = downloads.get(remoteFile);
			touchQuietly(destination);
			wagon.get(remoteFile, destination);
		}
		long elapsed = currentTimeMillis() - start;
		if (skipped.size() > 0) {
			logger.info(format("Skipped %s resources that already exist on the local file system", skipped.size()));
		}
		List<File> files = Lists.<File> newArrayList(downloads.values());
		sort(files);
		long bytes = getBytes(files);
		Object[] args = { getCount(files.size()), getSize(bytes), getRate(elapsed, bytes), getTime(elapsed) };
		logger.info(format("count: %s  size: %s  rate: %s  elapsed: %s", args));
	}

	protected long getBytes(List<File> files) {
		long bytes = 0;
		for (File file : files) {
			bytes += file.length();
		}
		return bytes;
	}

	protected void touchQuietly(File file) {
		try {
			touch(file);
		} catch (IOException e) {
			throw illegalState(e);
		}
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
