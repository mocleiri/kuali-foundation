/**
 * Copyright 2004-2012 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.project.MavenProject;

public class DefaultSiteContext implements SiteContext {

    @Override
    public String getOrganizationGroupId() {
        return "org.kuali";
    }

    @Override
    public String getDownloadBase() {
        return "http://s3browse.springsource.com/browse/maven.kuali.org";
    }

    @Override
    public String getDownloadSnapshotPath() {
        return "snapshot";
    }

    @Override
    public String getDownloadReleasePath() {
        return "release";
    }

    @Override
    public String getDownloadExternalPath() {
        return "external";
    }

    @Override
    public String getPublicBase() {
        return "http://site.kuali.org";
    }

    @Override
    public String getPublishBase() {
        return "s3://site.origin.kuali.org";
    }

    @Override
    public List<MavenProject> getOrgPoms() {
        List<MavenProject> orgPoms = new ArrayList<MavenProject>();

        MavenProject kuali = new MavenProject();
        kuali.setGroupId("org.kuali.pom");
        kuali.setArtifactId("kuali");
        kuali.setVersion("29-SNAPSHOT");

        MavenProject kualiCommon = new MavenProject();
        kualiCommon.setGroupId("org.kuali.pom");
        kualiCommon.setArtifactId("kuali-common");
        kualiCommon.setVersion("110-SNAPSHOT");

        orgPoms.add(kuali);
        orgPoms.add(kualiCommon);
        return orgPoms;
    }

    @Override
    public String getSnapshotSnippet() {
        return "SNAPSHOT";
    }

}
