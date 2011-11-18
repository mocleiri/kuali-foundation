package org.kuali.maven.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for working with URL's related to Maven web site generation
 */
public class UrlBuilder {
    private static final Logger logger = LoggerFactory.getLogger(UrlBuilder.class);

    /**
     * Return the fully qualified url where this MavenProject will be published.
     */
    public String getPublishUrl(MavenProject project, SiteContext context) {
        return getSiteUrl(project, context, context.getPublishBase());
    }

    /**
     * Return the fully qualified url where this MavenProject will be published.
     */
    public String getPublicUrl(MavenProject project, SiteContext context) {
        return getSiteUrl(project, context, context.getPublicBase());
    }

    /**
     * Return true if the groupId of the project positively matches the groupId passed in. False otherwise.
     */
    protected boolean isTargetGroupId(MavenProject project, String groupId) {
        // Return false if we can't do a meaningful comparison
        if (project == null || groupId == null) {
            return false;
        }

        // Check that it matches our target groupId
        return groupId.equals(project.getGroupId());
    }

    /**
     * Return the non-redundant portion of the group id. Given a project with a groupId of "org.kuali.maven.plugins" and
     * an organizationGroupId of "org.kuali" return "maven.plugins"
     */
    protected String getTrimmedGroupId(MavenProject project, String organizationGroupId) {
        String groupId = project.getGroupId();

        // Return the original group id if no organization group id is provided
        if (StringUtils.isEmpty(organizationGroupId)) {
            logger.warn("No organization group id to compare with");
            return groupId;
        }

        // Return the original group id if it is not a Kuali project
        if (!groupId.startsWith(organizationGroupId)) {
            logger.warn("Group Id: '" + groupId + "' does not start with '" + organizationGroupId + "'");
            return groupId;
        } else {
            // Remove the redundant portion of the group id
            String trimmed = StringUtils.replace(groupId, organizationGroupId, "");

            // Remove the leading dot, if necessary
            if (trimmed.startsWith(".")) {
                trimmed = trimmed.substring(1);
            }

            // Return the trimmed group id
            return trimmed;
        }
    }

    /**
     * Replace any dots with backslashes for the groupId passed in.
     */
    protected String getGroupIdPath(String groupId) {
        return groupId.replace(".", "/");
    }

    /**
     * Return true if the project has no parent, OR is one of the official org POM's. False otherwise.
     */
    protected boolean isTopLevelProject(MavenProject project, List<MavenProject> orgPoms) {
        MavenProject parent = project.getParent();
        if (parent == null) {
            return true;
        } else {
            return isMatch(parent, orgPoms);
        }
    }

    /**
     * Return true if groupId + artifactId of "project" matches a groupId + artifactId from "targetProjects"
     */
    protected boolean isMatch(MavenProject project, List<MavenProject> targetProjects) {
        for (MavenProject targetProject : targetProjects) {
            if (isMatch(project, targetProject)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if the groupId's and artifactId's match
     */
    protected boolean isMatch(MavenProject project1, MavenProject project2) {
        // Pull out the groupIds
        String groupId1 = project1.getGroupId();
        String groupId2 = project2.getGroupId();

        // If the groupIds don't match, we are done
        if (!groupId1.equals(groupId2)) {
            return false;
        }

        // If we get here the groupId's match
        String artifactId1 = project1.getArtifactId();
        String artifactId2 = project2.getArtifactId();

        // Return true only if the artifactIds also match
        return artifactId1.equals(artifactId2);
    }

    /**
     * Return true if the artifact id should be appended to the url for site publication.
     *
     * This method returns true, unless we have a multi-module project where the top level project's artifact id matches
     * the final portion of the groupId.
     *
     * For example, the Kuali Student top level pom has the groupId "org.kuali.student" and the artifactId "student". We
     * want to translate that groupId + artifactId into "site.kuali.org/student" NOT "site.kuali.org/student/student"
     */
    protected boolean isAppendArtifactId(MavenProject project, String trimmedGroupId) {
        // Always append the artifactId for single module projects
        if (isEmpty(project.getModules())) {
            return true;
        }

        // Always append the artifactId if it is different than the final
        // portion of the groupId
        if (!trimmedGroupId.endsWith(project.getArtifactId())) {
            return true;
        }

        /**
         * We have a multi-module build where the artifactId for the top level project is the same as the final portion
         * of the groupId.<br>
         * eg org.kuali.rice:rice Return false here so the public url is:<br>
         * http://site.kuali.org/rice/1.0.3<br>
         * instead of<br>
         * http://site.kuali.org/rice/rice/1.0.3<br>
         */
        return false;
    }

    /**
     * Returns a fully qualified url
     */
    protected String getSiteUrl(MavenProject project, SiteContext context, String urlBase) {
        StringBuilder sb = new StringBuilder();

        // append the base we've been given
        sb.append(urlBase);

        // Add a slash, if necessary
        if (!context.getPublishBase().endsWith("/")) {
            sb.append("/");
        }

        // Convert the project hierarchy into appropriate url tokens
        List<String> tokens = getUrlTokens(project, context);

        // Append the tokens
        for (String token : tokens) {
            sb.append(token);
            sb.append("/");
        }

        // Return the fully qualified url
        return sb.toString();
    }

    /**
     * Return a list of tokens representing url paths for this project
     */
    protected List<String> getUrlTokens(MavenProject project, SiteContext context) {
        // Determine if this is a top level org pom
        boolean orgPom = isMatch(project, context.getOrgPoms());
        if (orgPom) {
            // If so, just return top level tokens
            return getTopLevelTokens(project, context);
        } else {
            // Otherwise, examine the full project structure
            List<MavenProject> projectPath = getProjectPath(project);

            // Convert the project structure into url tokens
            return getUrlTokens(projectPath, context);
        }
    }

    /**
     * Return appropriate url tokens for the list of projects representing the path of projects from the highest level
     * pom to our current project
     */
    protected List<String> getUrlTokens(List<MavenProject> projects, SiteContext context) {
        List<String> tokens = new ArrayList<String>();
        for (MavenProject project : projects) {
            // If it is an org pom, just skip it
            boolean orgPom = isMatch(project, context.getOrgPoms());
            if (orgPom) {
                continue;
            } else {
                // Add tokens appropriate for this project
                addProjectTokens(project, context, tokens);
            }
        }
        // return our list of tokens
        return tokens;
    }

    /**
     * Add appropriate values to the list of tokens
     */
    protected void addProjectTokens(MavenProject project, SiteContext context, List<String> tokens) {
        // Is this a top level project?
        boolean topLevelProject = isTopLevelProject(project, context.getOrgPoms());
        if (topLevelProject) {
            // If so, add the top level tokens (groupId, artifactId, version)
            tokens.addAll(getTopLevelTokens(project, context));
        } else {
            // Otherwise just add the artifactId
            tokens.add(project.getArtifactId());
        }
    }

    /**
     * Return the appropriate tokens for this top level project
     */
    protected List<String> getTopLevelTokens(MavenProject project, SiteContext context) {
        List<String> tokens = new ArrayList<String>();

        // Trim off the redundant portion of the group id
        String trimmedGroupId = getTrimmedGroupId(project, context.getOrganizationGroupId());

        // Convert dots to slashes, and add to our list of tokens
        tokens.add(getGroupIdPath(trimmedGroupId));

        // Only time we don't append the artifact id is when it matches the final portion of the group id
        boolean appendArtifactId = isAppendArtifactId(project, trimmedGroupId);
        if (appendArtifactId) {
            tokens.add(project.getArtifactId());
        }

        // Always add the version
        tokens.add(project.getVersion());

        // Return what we've found
        return tokens;
    }

    /**
     * Return a List representing the complete hierarchy for this project.
     *
     * The list is ordered where the top level pom is first, and the current project is last
     */
    protected List<MavenProject> getProjectPath(MavenProject project) {
        List<MavenProject> projects = new ArrayList<MavenProject>();
        buildPath(project, projects);
        Collections.reverse(projects);
        return projects;
    }

    /**
     * Traverse the hierarchy of projects and flatten it into a List
     */
    protected void buildPath(MavenProject project, List<MavenProject> projects) {
        projects.add(project);
        MavenProject parent = project.getParent();
        if (parent == null) {
            return;
        } else {
            buildPath(parent, projects);
        }
    }

    /**
     * Return the fully qualified URL for downloading a Kuali artifact from Kuali's Maven repo.
     */
    public String getDownloadUrl(MavenProject project, SiteContext context) {
        String baseUrl = context.getDownloadBase();
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl);
        if (!baseUrl.endsWith("/")) {
            sb.append("/");
        }

        // "snapshot", "release", or "external"
        sb.append(getDownloadPath(project, context));
        sb.append("/");

        // Convert dots to slashes
        sb.append(getGroupIdPath(project.getGroupId()));
        sb.append("/");

        sb.append(project.getArtifactId());
        sb.append("/");

        sb.append(project.getVersion());
        sb.append("/");
        return sb.toString();
    }

    /**
     * Decide between "snapshot", "release", and "external"
     */
    protected String getDownloadPath(MavenProject project, SiteContext context) {
        // All snapshots (Kuali and non-Kuali) go into the "snapshot" folder
        if (isSnapshot(project.getVersion(), context.getSnapshotSnippet())) {
            return context.getDownloadSnapshotPath();
        }

        // If we get here we are dealing with a non-snapshot artifact

        // It would be better to positively identify something as a release artifact
        // instead of assuming "non-snapshot" == "release"

        if (isOrganizationProject(project, context.getOrganizationGroupId())) {
            // For Kuali projects, this is "release"
            return context.getDownloadReleasePath();
        } else {
            // For non-Kuali projects, this is "external"
            return context.getDownloadExternalPath();
        }
    }

    /**
     * Return true if the group id of the project matches organization group id, false otherwise.
     */
    protected boolean isOrganizationProject(MavenProject project, String organizationGroupId) {
        return project.getGroupId().startsWith(organizationGroupId);
    }

    /**
     * Return true if version contains snapshotSnippet (case insensitive)
     */
    protected boolean isSnapshot(String version, String snapshotSnippet) {
        return version.toUpperCase().contains(snapshotSnippet);
    }

    /**
     * Return true if the Collection passed in is null or size zero, false otherwise
     */
    protected boolean isEmpty(Collection<?> c) {
        if (c == null || c.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
