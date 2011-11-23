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
