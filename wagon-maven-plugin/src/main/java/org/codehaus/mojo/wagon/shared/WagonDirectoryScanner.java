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

import static org.codehaus.plexus.util.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.WagonException;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.inform.PercentCompleteInformer;

public class WagonDirectoryScanner {

	private final static String[] NOT_DIRECTORIES = new String[] { ".jar", ".zip", ".md5", ".sha1", ".pom", ".xml", ".war" };

	/**
	 * Patterns which should be excluded by default.
	 * 
	 * @see #addDefaultExcludes()
	 */
	public static final String[] DEFAULTEXCLUDES = org.codehaus.plexus.util.DirectoryScanner.DEFAULTEXCLUDES;

	/**
	 * The wagon
	 */
	private Wagon wagon;

	/**
	 * Relative to wagon url
	 */
	private String directory;

	/**
	 * Print a dot to the console each time we scan 10 directories
	 */
	private PercentCompleteInformer informer = new PercentCompleteInformer(100);

	/** The patterns for the wagon files to be included. */
	private String[] includes;

	/** The patterns for the wagon files to be excluded. */
	private String[] excludes;

	/**
	 * Whether or not the file system should be treated as a case sensitive one.
	 */
	private boolean isCaseSensitive = true;

	/**
	 * The files which matched at least one include and at least one exclude and relative to directory
	 */
	private List<String> filesIncluded = new ArrayList<String>();

	private Log logger;

	/**
	 * Sets the list of include patterns to use. All '/' and '\' characters are replaced by <code>File.separatorChar</code>, so the separator used need not match
	 * <code>File.separatorChar</code>.
	 * <p>
	 * When a pattern ends with a '/' or '\', "**" is appended.
	 * 
	 * @param includes
	 *            A list of include patterns. May be <code>null</code>, indicating that all files should be included. If a non-<code>null</code> list is given, all elements must be
	 *            non-<code>null</code>.
	 */
	public void setIncludes(String[] includes) {
		if (includes == null) {
			this.includes = null;
		} else {
			this.includes = new String[includes.length];
			for (int i = 0; i < includes.length; i++) {
				String pattern = includes[i].trim();

				if (pattern.endsWith("/")) {
					pattern += "**";
				}
				this.includes[i] = pattern;
			}
		}
	}

	/**
	 * Sets the list of exclude patterns to use. All '\' characters are replaced by '/'
	 * <p>
	 * When a pattern ends with a '/' or '\', "**" is appended.
	 * 
	 * @param excludes
	 *            A list of exclude patterns. May be <code>null</code>, indicating that no files should be excluded. If a non-<code>null</code> list is given, all elements must be
	 *            non-<code>null</code>.
	 */
	public void setExcludes(String[] excludes) {
		if (excludes == null) {
			this.excludes = null;
		} else {
			this.excludes = new String[excludes.length];
			for (int i = 0; i < excludes.length; i++) {
				String pattern = excludes[i].trim();

				if (pattern.endsWith("/")) {
					pattern += "**";
				}
				this.excludes[i] = pattern;
			}
		}
	}

	/**
	 * Tests whether or not a name matches against at least one include pattern.
	 * 
	 * @param name
	 *            The name to match. Must not be <code>null</code>.
	 * @return <code>true</code> when the name matches against at least one include pattern, or <code>false</code> otherwise.
	 */
	private boolean isIncluded(String name) {
		logger.debug("includes.length=" + includes.length);
		for (int i = 0; i < includes.length; i++) {
			logger.debug("includes[" + i + "]=" + includes[i]);
			if (matchPath(includes[i], name, isCaseSensitive)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests whether or not a name matches against at least one exclude pattern.
	 * 
	 * @param name
	 *            The name to match. Must not be <code>null</code>.
	 * @return <code>true</code> when the name matches against at least one exclude pattern, or <code>false</code> otherwise.
	 */
	protected boolean isExcluded(String name) {
		for (int i = 0; i < excludes.length; i++) {
			if (matchPath(excludes[i], name, isCaseSensitive)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests whether or not a name matches the start of at least one include pattern.
	 * 
	 * @param name
	 *            The name to match. Must not be <code>null</code>.
	 * @return <code>true</code> when the name matches against the start of at least one include pattern, or <code>false</code> otherwise.
	 */
	protected boolean couldHoldIncluded(String name) {
		for (int i = 0; i < includes.length; i++) {
			if (matchPatternStart(includes[i], name, isCaseSensitive)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests whether or not a given path matches the start of a given pattern up to the first "**".
	 * <p>
	 * This is not a general purpose test and should only be used if you can live with false positives. For example, <code>pattern=**\a</code> and <code>str=b</code> will yield
	 * <code>true</code>.
	 * 
	 * @param pattern
	 *            The pattern to match against. Must not be <code>null</code>.
	 * @param str
	 *            The path to match, as a String. Must not be <code>null</code>.
	 * @param isCaseSensitive
	 *            Whether or not matching should be performed case sensitively.
	 * 
	 * @return whether or not a given path matches the start of a given pattern up to the first "**".
	 */
	protected static boolean matchPatternStart(String pattern, String str, boolean isCaseSensitive) {
		return SelectorUtils.matchPatternStart(pattern, str, isCaseSensitive);
	}

	/**
	 * Tests whether or not a given path matches a given pattern.
	 * 
	 * @param pattern
	 *            The pattern to match against. Must not be <code>null</code>.
	 * @param str
	 *            The path to match, as a String. Must not be <code>null</code>.
	 * @param isCaseSensitive
	 *            Whether or not matching should be performed case sensitively.
	 * 
	 * @return <code>true</code> if the pattern matches against the string, or <code>false</code> otherwise.
	 */
	private static boolean matchPath(String pattern, String str, boolean isCaseSensitive) {
		return SelectorUtils.matchPath(pattern, str, isCaseSensitive);
	}

	public void scan() throws WagonException {
		if (wagon == null) {
			throw new IllegalStateException("No wagon set");
		}

		if (StringUtils.isBlank(directory)) {
			directory = "";
		}

		// logger.info("directory=" + directory);

		if (includes == null) {
			// No includes supplied, so set it to 'matches all'
			includes = new String[1];
			includes[0] = "**";
		}

		if (excludes == null) {
			excludes = new String[0];
		}

		filesIncluded = new ArrayList<String>();

		scandir(directory);

		Collections.sort(filesIncluded);

	}

	/**
	 * Adds default exclusions to the current exclusions set.
	 */
	public void addDefaultExcludes() {
		int excludesLength = excludes == null ? 0 : excludes.length;
		String[] newExcludes;
		newExcludes = new String[excludesLength + DEFAULTEXCLUDES.length];
		if (excludesLength > 0) {
			System.arraycopy(excludes, 0, newExcludes, 0, excludesLength);
		}
		for (int i = 0; i < DEFAULTEXCLUDES.length; i++) {
			newExcludes[i + excludesLength] = DEFAULTEXCLUDES[i];
		}
		excludes = newExcludes;
	}

	/**
	 * Jenkins, if nothing else, will return pathnames with * characters in them that lead to infinite recursion down here. Given the impoverished API to the wagons, some ad-hoc
	 * filtration is called for. The filters in here are just culled from strange stuff we see from Jenkins.
	 * 
	 * @param fileName
	 *            supposed file name
	 * @return true if it seems like a bad idea.
	 */
	private boolean isRidiculousFile(String fileName) {
		return fileName.endsWith(".") || fileName.contains("*") || fileName.startsWith("?") || fileName.startsWith("#");
	}

	/**
	 * Scans the given directory for files and directories. Files are placed in a collection, based on the matching of includes, excludes, and the selectors. When a directory is
	 * found, it is scanned recursively.
	 * 
	 * @throws WagonException
	 * 
	 * @see #filesIncluded
	 */
	protected void scandir(String dir) throws WagonException {
		if (this.informer != null) {
			informer.incrementProgress();
		}
		if (isBlank(dir)) {
			logger.debug("Scanning '" + dir + "'");
		} else {
			logger.debug("Scanning " + dir);
		}
		List<?> files = wagon.getFileList(dir);

		for (Iterator<?> itr = files.iterator(); itr.hasNext();) {
			String fileName = (String) itr.next();
			if (isRidiculousFile(fileName)) {
				continue;
			}

			boolean directory = isDirectory(fileName);
			boolean included = isIncluded(fileName);
			boolean excluded = isExcluded(fileName);
			boolean chi = directory && couldHoldIncluded(fileName);
			boolean include = included && !excluded || chi && !excluded;

			if (!include) {
				logger.debug("Skipping " + fileName);
				logger.debug("fileName=" + fileName + " included=" + included + " excluded=" + excluded + " chi=" + chi);
				continue;
			}

			if (directory) {
				scandir(fileName);
			} else {
				filesIncluded.add(fileName);
			}
		}
	}

	private boolean isDirectory(String existingRemotePath) throws WagonException {
		for (int x = 0; x < NOT_DIRECTORIES.length; x++) {
			if (existingRemotePath.endsWith(NOT_DIRECTORIES[x])) {
				return false;
			}
		}
		return existingRemotePath.endsWith("/");
	}

	public List<String> getFilesIncluded() {
		return filesIncluded;
	}

	public void setWagon(Wagon wagon) {
		this.wagon = wagon;
	}

	public void setCaseSensitive(boolean isCaseSensitive) {
		this.isCaseSensitive = isCaseSensitive;
	}

	public void setDirectory(String basePath) {
		this.directory = basePath;
	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	public PercentCompleteInformer getInformer() {
		return informer;
	}

	public void setInformer(PercentCompleteInformer informer) {
		this.informer = informer;
	}

}
