/**
 * Copyright 2004-2011 The Kuali Foundation
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
package org.kuali.maven.common;

import java.util.List;

import org.apache.maven.project.MavenProject;

/**
 *
 */
public interface SiteContext {

    // If a version contains this text it is assumed to be a snapshot version of an artifact
    // This is usually just "SNAPSHOT"
    public String getSnapshotSnippet();

    // The group id for the organization ie "org.kuali"
    public String getOrganizationGroupId();

    // The base url for downloading ie "http://s3browse.springsource.com/browse/maven.kuali.org/"
    public String getDownloadBase();

    // The path to append to downloadBase for snapshot artifacts ie "snapshot"
    public String getDownloadSnapshotPath();

    // The path to append to downloadBase for release artifacts ie "release"
    public String getDownloadReleasePath();

    // The path to append to downloadBase for non-Kuali artifacts ie "external"
    public String getDownloadExternalPath();

    // The base url for the public web site ie "http://site.kuali.org"
    public String getPublicBase();

    // The base url for publishing the public web site ie "s3://site.origin.kuali.org"
    public String getPublishBase();

    // POM's for the organization
    public List<MavenProject> getOrgPoms();
}
