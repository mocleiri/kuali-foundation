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
import java.util.List;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class DefaultScmService implements ScmService {

	protected SVNWCClient getSVNWCClient() {
		ISVNAuthenticationManager authMgr = SVNWCUtil.createDefaultAuthenticationManager();
		return new SVNWCClient(authMgr, null);
	}

	@Override
	public void delete(List<File> files) {
		doDelete(files);
		commit(files, "Automated delete", null, null);
	}

	@Override
	public void add(List<File> files) {
		doAdd(files);
		commit(files, "Automated add", null, null);
	}

	@Override
	public void update(List<File> files) {
		doAdd(files);
		commit(files, "Automated update", null, null);
	}

	protected void doAdd(List<File> files) {
		SVNWCClient client = getSVNWCClient();
		try {
			for (File file : files) {
				client.doAdd(file, true, true, true, SVNDepth.INFINITY, false, true);
			}
		} catch (SVNException e) {
			throw new IllegalStateException(e);
		}
	}

	protected void doDelete(List<File> files) {
		SVNWCClient client = getSVNWCClient();
		try {
			for (File file : files) {
				client.doDelete(file, true, false);
			}
		} catch (SVNException e) {
			throw new IllegalStateException(e);
		}
	}

	public SVNCommitInfo commit(List<File> workingCopyPaths, String message, String username, String password) {
		File[] fileArray = workingCopyPaths.toArray(new File[workingCopyPaths.size()]);
		return commit(fileArray, message, username, password);
	}

	protected SVNCommitInfo commit(File[] workingCopyPaths, String message, String username, String password) {
		try {
			SVNClientManager manager = SVNClientManager.newInstance(null, username, password);
			SVNCommitClient client = manager.getCommitClient();
			return client.doCommit(workingCopyPaths, true, message, null, null, true, false, SVNDepth.INFINITY);
		} catch (SVNException e) {
			throw new IllegalStateException(e);
		}
	}

	public SVNCommitInfo commit(File workingCopyPath, String message, String username, String password) {
		return commit(new File[] { workingCopyPath }, message, username, password);
	}
}
