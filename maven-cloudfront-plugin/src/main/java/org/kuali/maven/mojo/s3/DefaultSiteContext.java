/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.maven.mojo.s3;

import java.util.List;

import org.apache.maven.project.MavenProject;
import org.kuali.maven.common.SiteContext;

public class DefaultSiteContext implements SiteContext {

	String latestToken;
	boolean latest;
	String snapshotSnippet;
	String organizationGroupId;
	String downloadBase;
	String downloadSnapshotPath;
	String downloadReleasePath;
	String downloadExternalPath;
	String publicBase;
	String publishBase;
	List<MavenProject> orgPoms;

	@Override
	public String getLatestToken() {
		return latestToken;
	}

	public void setLatestToken(String latestToken) {
		this.latestToken = latestToken;
	}

	@Override
	public boolean isLatest() {
		return latest;
	}

	public void setLatest(boolean latest) {
		this.latest = latest;
	}

	@Override
	public String getSnapshotSnippet() {
		return snapshotSnippet;
	}

	public void setSnapshotSnippet(String snapshotSnippet) {
		this.snapshotSnippet = snapshotSnippet;
	}

	@Override
	public String getOrganizationGroupId() {
		return organizationGroupId;
	}

	public void setOrganizationGroupId(String organizationGroupId) {
		this.organizationGroupId = organizationGroupId;
	}

	@Override
	public String getDownloadBase() {
		return downloadBase;
	}

	public void setDownloadBase(String downloadBase) {
		this.downloadBase = downloadBase;
	}

	@Override
	public String getDownloadSnapshotPath() {
		return downloadSnapshotPath;
	}

	public void setDownloadSnapshotPath(String downloadSnapshotPath) {
		this.downloadSnapshotPath = downloadSnapshotPath;
	}

	@Override
	public String getDownloadReleasePath() {
		return downloadReleasePath;
	}

	public void setDownloadReleasePath(String downloadReleasePath) {
		this.downloadReleasePath = downloadReleasePath;
	}

	@Override
	public String getDownloadExternalPath() {
		return downloadExternalPath;
	}

	public void setDownloadExternalPath(String downloadExternalPath) {
		this.downloadExternalPath = downloadExternalPath;
	}

	@Override
	public String getPublicBase() {
		return publicBase;
	}

	public void setPublicBase(String publicBase) {
		this.publicBase = publicBase;
	}

	@Override
	public String getPublishBase() {
		return publishBase;
	}

	public void setPublishBase(String publishBase) {
		this.publishBase = publishBase;
	}

	@Override
	public List<MavenProject> getOrgPoms() {
		return orgPoms;
	}

	public void setOrgPoms(List<MavenProject> orgPoms) {
		this.orgPoms = orgPoms;
	}
}
