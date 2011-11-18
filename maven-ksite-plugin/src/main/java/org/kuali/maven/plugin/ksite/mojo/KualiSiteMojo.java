package org.kuali.maven.plugin.ksite.mojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.model.DistributionManagement;
import org.apache.maven.model.Site;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.common.SiteContext;
import org.kuali.maven.common.UrlBuilder;

/**
 * This plugin organizes/standardizes the maven site publication process for the Kuali organization
 *
 * @goal set
 * @phase pre-site
 */
public class KualiSiteMojo extends AbstractMojo implements SiteContext {

    /**
     * The path into the bucket when downloading a snapshot version
     *
     * @parameter expression="${ksite.downloadSnapshotPath}" default-value="snapshot"
     */
    private String downloadSnapshotPath;

    /**
     * The path into the bucket when downloading a release version
     *
     * @parameter expression="${ksite.downloadReleasePath}" default-value="release"
     */
    private String downloadReleasePath;

    /**
     * The path into the bucket when downloading a release version
     *
     * @parameter expression="${ksite.downloadExternalPath}" default-value="external"
     */
    private String downloadExternalPath;

    /**
     * The base url for publishing Maven web sites
     *
     * @parameter expression="${ksite.publishUrlBase}" default-value="s3://site.origin.kuali.org"
     */
    private String publishBase;

    /**
     * The base url for where the public accesses the Maven web sites
     *
     * @parameter expression="${ksite.publicUrlBase}" default-value="http://site.kuali.org"
     */
    private String publicBase;

    /**
     * The prefix for the location that artifacts can be downloaded from
     *
     * @parameter expression="${ksite.downloadBase}"
     *            default-value="http://s3browse.springsource.com/browse/maven.kuali.org/"
     */
    private String downloadBase;

    /**
     * The groupId for the organization
     *
     * @parameter expression="${ksite.organizationGroupId}" default-value="org.kuali"
     */
    private String organizationGroupId;

    /**
     * If the version number for a pom contains this string it is assumed to be a SNAPSHOT artifact
     *
     * @parameter expression="${ksite.snapshotSnippet}" default-value="SNAPSHOT"
     */
    private String snapshotSnippet;

    /**
     * The Maven project this plugin runs in.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * GAV strings representing organizational poms eg "org.kuali.pom:kuali" and "org.kuali.pom:kuali-common"
     *
     * @parameter
     */
    private List<String> orgPomGavs;

    @Override
    public List<MavenProject> getOrgPoms() {
        List<MavenProject> projects = new ArrayList<MavenProject>();
        return projects;
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        UrlBuilder builder = new UrlBuilder();

        // Generate our urls
        String publicUrl = builder.getPublicUrl(getProject(), this);
        String downloadUrl = builder.getDownloadUrl(getProject(), this);
        String publishUrl = builder.getPublishUrl(getProject(), this);

        // Get a reference to the relevant model objects
        MavenProject project = getProject();
        DistributionManagement dm = project.getDistributionManagement();
        Site site = dm.getSite();

        // Update the model with our generated urls as needed
        handlePublicUrl(publicUrl, project);
        handlePublishUrl(publishUrl, site);
        handleDownloadUrl(downloadUrl, dm);

    }

    protected boolean containsUnresolvedProperty(String s) {
        return s.contains("${");
    }

    /**
     * Return true if "s" is empty or is an unresolved property
     */
    protected boolean isUnresolved(String s) {
        if (StringUtils.isEmpty(s)) {
            return true;
        } else {
            return containsUnresolvedProperty(s);
        }
    }

    protected void warn(String pomString, String calculatedString, String propertyDescription) {
        getLog().warn("****************************************");
        getLog().warn(propertyDescription + " mismatch");
        getLog().warn("  Supplied Value: " + pomString);
        getLog().warn("Calculated value: " + calculatedString);
        getLog().warn("****************************************");
    }

    /**
     * Return true if the 2 urls are exactly the same or if the only thing different about them is a trailing slash
     */
    protected boolean isUrlMatch(String url1, String url2) {
        if (url1.equals(url2)) {
            return true;
        }
        if ((url1 + "/").equals(url2)) {
            return true;
        }
        if (url1.equals(url2 + "/")) {
            return true;
        }
        return false;
    }

    protected void handleDownloadUrl(String downloadUrl, DistributionManagement dm) {
        if (isUnresolved(dm.getDownloadUrl())) {
            getLog().info("Setting download url to " + downloadUrl);
            dm.setDownloadUrl(downloadUrl);
            return;
        }
        if (!isUrlMatch(downloadUrl, dm.getDownloadUrl())) {
            warn(dm.getDownloadUrl(), downloadUrl, "Download url");
        }
        getLog().info("Using download url from the POM " + dm.getDownloadUrl());
    }

    protected void handlePublishUrl(String publishUrl, Site site) {
        if (isUnresolved(site.getUrl())) {
            getLog().info("Setting site publication url to " + publishUrl);
            site.setUrl(publishUrl);
            return;
        }
        if (!isUrlMatch(publishUrl, site.getUrl())) {
            warn(site.getUrl(), publishUrl, "Site publication url");
        }
        getLog().info("Using site publication url - " + site.getUrl());
    }

    protected void handlePublicUrl(String publicUrl, MavenProject project) {
        if (isUnresolved(project.getUrl())) {
            getLog().info("Setting public url to " + publicUrl);
            project.setUrl(publicUrl);
            return;
        }
        if (!isUrlMatch(publicUrl, project.getUrl())) {
            warn(project.getUrl(), publicUrl, "Public url");
        }
        getLog().info("Using public url from the POM " + project.getUrl());
    }

    /**
     * @return the project
     */
    public MavenProject getProject() {
        return project;
    }

    /**
     * @param project
     *            the project to set
     */
    public void setProject(final MavenProject project) {
        this.project = project;
    }

    /**
     * @return the parentGroupId
     */
    @Override
    public String getOrganizationGroupId() {
        return organizationGroupId;
    }

    /**
     * @param parentGroupId
     *            the parentGroupId to set
     */
    public void setOrganizationGroupId(final String parentGroupId) {
        this.organizationGroupId = parentGroupId;
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
    public String getDownloadBase() {
        return downloadBase;
    }

    public void setDownloadBase(String downloadBase) {
        this.downloadBase = downloadBase;
    }

    public List<String> getOrgPomGavs() {
        return orgPomGavs;
    }

    public void setOrgPomGavs(List<String> orgPomGavs) {
        this.orgPomGavs = orgPomGavs;
    }

    @Override
    public String getSnapshotSnippet() {
        return snapshotSnippet;
    }

    public void setSnapshotSnippet(String snapshotSnippet) {
        this.snapshotSnippet = snapshotSnippet;
    }

    @Override
    public String getPublishBase() {
        return publishBase;
    }

    public void setPublishBase(String publishBase) {
        this.publishBase = publishBase;
    }

    @Override
    public String getPublicBase() {
        return publicBase;
    }

    public void setPublicBase(String publicBase) {
        this.publicBase = publicBase;
    }

}
