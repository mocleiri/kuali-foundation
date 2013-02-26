/**
 * Copyright 2011-2013 The Kuali Foundation
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
package org.kuali.common.svn;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNPropertyValue;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.SVNExternal;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNCopyClient;
import org.tmatesoft.svn.core.wc.SVNCopySource;
import org.tmatesoft.svn.core.wc.SVNInfo;
import org.tmatesoft.svn.core.wc.SVNPropertyData;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(SVNUtils.class);
	private static final String EMPTY_STRING = "";
	private static final String EXTERNALS_PROPERTY_NAME = "svn:externals";
	private static final String EXTERNALS_COMMENT = "#";
	private static final String DELETE_EXTERNALS_COMMIT_MESSAGE = "Delete externals";
	private static final String CREATE_EXTERNALS_COMMIT_MESSAGE = "Create externals";
	private static final String LINEFEED = "\n";
	private static final String SPACE = " ";
	private static final String SEMICOLON = ":";

	protected static SVNUtils instance;

	protected SVNUtils() {
		super();
		initialize();
	}

	protected void initialize() {
		SVNRepositoryFactoryImpl.setup();
		DAVRepositoryFactory.setup();
		FSRepositoryFactory.setup();
	}

	public synchronized static SVNUtils getInstance() {
		if (instance == null) {
			instance = new SVNUtils();
		}
		return instance;
	}

	public void markForDeletion(List<File> files) {
		SVNWCClient client = getSVNWCClient();
		try {
			for (File file : files) {
				client.doDelete(file, true, false);
			}
		} catch (SVNException e) {
			throw new IllegalStateException(e);
		}
	}

	protected SVNURL[] getSvnUrlArray(List<File> files) {
		String singleSlashPrefix = "file:/";
		String doubleSlashPrefix = "file://";
		SVNURL[] array = new SVNURL[files.size()];
		for (int i = 0; i < files.size(); i++) {
			File file = files.get(i);
			String fileUrlString = file.toURI().toString();
			boolean singleSlash = StringUtils.startsWith(fileUrlString, singleSlashPrefix);
			boolean doubleSlash = StringUtils.startsWith(fileUrlString, doubleSlashPrefix);
			if (singleSlash && !doubleSlash) {
				fileUrlString = StringUtils.replace(fileUrlString, singleSlashPrefix, doubleSlashPrefix);
			}
			SVNURL fileUrl = getSvnUrl(fileUrlString);
			array[i] = fileUrl;
		}
		return array;
	}

	public void markForDeletion(File file) {
		markForDeletion(Collections.singletonList(file));
	}

	/**
	 * Return the Subversion url that corresponds with the local working copy
	 */
	public String getUrl(File workingCopyPath) {
		SVNInfo info = getInfo(workingCopyPath);
		return info.getURL().toDecodedString();
	}

	public long checkout(String url, File dstPath, String username, String password) {
		try {
			SVNClientManager manager = SVNClientManager.newInstance(null, username, password);
			SVNUpdateClient client = manager.getUpdateClient();
			client.setIgnoreExternals(false);
			SVNURL svnUrl = getSvnUrl(url);
			return client.doCheckout(svnUrl, dstPath, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY, false);
		} catch (SVNException e) {
			throw new IllegalStateException(e);
		}
	}

	public SVNCommitInfo commit(List<File> workingCopyPaths, String message, String username, String password) {
		File[] fileArray = workingCopyPaths.toArray(new File[workingCopyPaths.size()]);
		return commit(fileArray, message, username, password);
	}

	public SVNCommitInfo commit(File[] workingCopyPaths, String message, String username, String password) {
		try {
			SVNClientManager manager = SVNClientManager.newInstance(null, username, password);
			SVNCommitClient client = manager.getCommitClient();
			client.setIgnoreExternals(false);
			return client.doCommit(workingCopyPaths, true, message, null, null, true, false, SVNDepth.INFINITY);
		} catch (SVNException e) {
			throw new IllegalStateException(e);
		}
	}

	public SVNCommitInfo commit(File workingCopyPath, String message, String username, String password) {
		return commit(new File[] { workingCopyPath }, message, username, password);
	}

	/**
	 * Return the revision of the last commit for the working copy.
	 */
	public long getLastRevision(File workingCopyPath) {
		SVNInfo info = getInfo(workingCopyPath);
		return info.getCommittedRevision().getNumber();
	}

	/**
	 * Return the revision of the last commit for the given url
	 */
	public long getLastRevision(String url) {
		SVNRepository repository = getRepository(url);
		return getLastRevision(repository);
	}

	protected boolean isUrl(String s) {
		return s.contains(SEMICOLON);
	}

	protected String getUrl(String value1, String value2) {
		if (isUrl(value1)) {
			return value1;
		} else {
			return value2;
		}
	}

	protected String getPath(String value1, String value2) {
		if (isUrl(value1)) {
			return value2;
		} else {
			return value1;
		}
	}

	protected File getExternalWorkingCopyPath(File workingCopyPath, String path) {
		if (workingCopyPath == null) {
			return null;
		} else {
			return new File(workingCopyPath.getAbsolutePath() + File.separator + path);
		}
	}

	protected SVNWCClient getSVNWCClient() {
		ISVNAuthenticationManager authMgr = SVNWCUtil.createDefaultAuthenticationManager();
		return new SVNWCClient(authMgr, null);
	}

	protected SVNInfo getInfo(File workingCopyPath) {
		try {
			SVNWCClient client = getSVNWCClient();
			SVNRevision revision = SVNRevision.create(-1);
			return client.doInfo(workingCopyPath, revision);
		} catch (SVNException e) {
			throw new IllegalStateException(e);
		}
	}

	protected long getLastRevision(SVNRepository repository) {
		try {
			SVNDirEntry entry = repository.info(EMPTY_STRING, -1);
			return entry.getRevision();
		} catch (SVNException e) {
			throw new IllegalStateException(e);
		}
	}

	protected SVNRepository getRepository(String url) {
		return getRepository(url, null, null);
	}

	protected SVNRepository getRepository(String url, String username, String password) {
		try {
			SVNURL svnUrl = getSvnUrl(url);
			SVNRepository repository = SVNRepositoryFactory.create(svnUrl, null);
			if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
				ISVNAuthenticationManager authManager = SVNWCUtil
						.createDefaultAuthenticationManager(username, password);
				repository.setAuthenticationManager(authManager);
			}
			return repository;
		} catch (SVNException e) {
			throw new IllegalStateException(e);
		}
	}

	@SuppressWarnings("deprecation")
	protected SVNURL getSvnUrl(String url) {
		try {
			return SVNURL.parseURIDecoded(url);
		} catch (SVNException e) {
			throw new IllegalStateException(e);
		}
	}
}
