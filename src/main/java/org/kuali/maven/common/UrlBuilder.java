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
     * Return the fully qualified url for publishing the web site
     */
    public String getPublishUrl(MavenProject project, SiteContext context) {
        return getSiteUrl(project, context, context.getPublishBase());
    }

    /**
     * Return the fully qualified url for public access to the web site
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
     * Return a fully qualified url
     */
    protected String getSiteUrl(MavenProject project, SiteContext context, String urlBase) {
        StringBuilder sb = new StringBuilder();

        // append the base we've been given
        sb.append(urlBase);

        // Add a slash, if necessary
        if (!context.getPublishBase().endsWith("/")) {
            sb.append("/");
        }

        // Append the path for this project
        sb.append(getSitePath(project, context.getOrgPoms(), context.getOrganizationGroupId()));

        // Return the fully qualified url
        return sb.toString();
    }

    /**
     * Return the portion of the url to the right of the hostname
     */
    public String getSitePath(MavenProject project, List<MavenProject> orgPoms, String orgGroupId) {
        // Convert the project hierarchy into appropriate url tokens
        List<String> tokens = getUrlTokens(project, orgPoms, orgGroupId);

        StringBuilder sb = new StringBuilder();
        // Append the tokens
        for (String token : tokens) {
            sb.append(token);
            sb.append("/");
        }
        return sb.toString();
    }

    /**
     * Return a list of tokens representing url paths for this project
     */
    protected List<String> getUrlTokens(MavenProject project, List<MavenProject> orgPoms, String orgGroupId) {
        // Determine if the project they passed in is a top level pom
        boolean orgPom = isMatch(project, orgPoms);
        if (orgPom) {
            // If so, just return top level tokens
            return getTopLevelTokens(project, orgGroupId);
        } else {
            // Otherwise, examine the full project structure
            List<MavenProject> projectPath = getProjectPath(project);

            // Convert the project structure into url tokens
            return getUrlTokens(projectPath, orgPoms, orgGroupId);
        }
    }

    /**
     * Return appropriate url tokens for the list of projects representing the path of projects from the highest level
     * pom to our current project
     */
    protected List<String> getUrlTokens(List<MavenProject> projects, List<MavenProject> orgPoms, String orgGroupId) {
        List<String> tokens = new ArrayList<String>();
        for (MavenProject project : projects) {
            // If it is an org pom, skip it, since they are not needed
            // for calculating the url used for the site path
            boolean orgPom = isMatch(project, orgPoms);
            if (!orgPom) {
                // Add tokens appropriate for this project
                addProjectTokens(project, orgPoms, orgGroupId, tokens);
            }
        }
        // return our list of tokens
        return tokens;
    }

    /**
     * Add appropriate values to the list of tokens
     */
    protected void addProjectTokens(MavenProject project, List<MavenProject> orgPoms, String orgGroupId,
            List<String> tokens) {
        // Is this a top level project?
        boolean topLevelProject = isTopLevelProject(project, orgPoms);
        if (topLevelProject) {
            // If so, add the top level tokens (groupId, artifactId, version)
            tokens.addAll(getTopLevelTokens(project, orgGroupId));
        } else {
            // Otherwise just add the artifactId
            tokens.add(project.getArtifactId());
        }
    }

    /**
     * Return the appropriate tokens for this top level project
     */
    protected List<String> getTopLevelTokens(MavenProject project, String orgGroupId) {
        List<String> tokens = new ArrayList<String>();

        // Trim off the redundant portion of the group id
        String trimmedGroupId = getTrimmedGroupId(project, orgGroupId);

        // Convert dots to slashes, and add to our list of tokens
        if (trimmedGroupId.length() > 0) {
            tokens.add(getGroupIdPath(trimmedGroupId));
        }

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

    /**
     * Return true if the string contains "${", false otherwise.
     */
    public boolean containsUnresolvedProperty(String s) {
        return s.contains("${");
    }

    /**
     * Return true if the string is, null, empty, or contains an unresolved property
     */
    public boolean isUnresolved(String s) {
        if (StringUtils.isEmpty(s)) {
            return true;
        } else {
            return containsUnresolvedProperty(s);
        }
    }

    /**
     * Return true if the 2 urls are exactly the same or if the only thing different about them is a trailing slash
     */
    public boolean isUrlMatch(String url1, String url2) {
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

    /**
     * Given a list of GAV's in the form [groupId]:[artifactId]:[version] return MavenProject objects with groupId,
     * artifactId, and version set
     */
    public List<MavenProject> getMavenProjects(List<String> gavs) {
        List<MavenProject> projects = new ArrayList<MavenProject>();
        if (gavs == null) {
            return projects;
        }
        for (String gav : gavs) {
            MavenProject project = getMavenProject(gav);
            projects.add(project);
        }
        return projects;
    }

    /**
     * Given a GAV in the form [groupId]:[artifactId]:[version] return a MavenProject object that has groupId,
     * artifactId, and version set
     */
    public MavenProject getMavenProject(String gav) {
        // Split the string into tokens
        String[] tokens = StringUtils.splitByWholeSeparator(gav.trim(), ":");

        // Setup some local storage
        String groupId = null;
        String artifactId = null;
        String version = null;

        // Extract information from the tokens
        if (tokens.length > 0) {
            groupId = tokens[0].trim();
        }
        if (tokens.length > 1) {
            artifactId = tokens[1].trim();
        }
        if (tokens.length > 2) {
            version = tokens[2].trim();
        }

        // Store info into a MavenProject
        MavenProject project = new MavenProject();
        project.setGroupId(groupId);
        project.setArtifactId(artifactId);
        project.setVersion(version);
        return project;
    }

}
