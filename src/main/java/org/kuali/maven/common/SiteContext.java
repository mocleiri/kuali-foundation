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
