package org.kuali.maven.common;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.maven.project.MavenProject;
import org.junit.Test;

public class UrlBuilderTest {
    UrlBuilder builder = new UrlBuilder();
    SiteContext context = new DefaultSiteContext();

    @Test
    public void testGetProjectPath() {
        MavenProjectTest riceModule = getTopLevelRice().getChildren().get(0);
        List<MavenProject> projects = builder.getProjectPath(riceModule);
        Assert.assertEquals(4, projects.size());
        MavenProject riceCore = projects.get(3);
        MavenProject kuali = projects.get(0);
        Assert.assertEquals("kuali", kuali.getArtifactId());
        Assert.assertEquals("rice-core", riceCore.getArtifactId());
    }

    @Test
    public void testGetPublishUrl1() {
        MavenProjectTest riceModule = getTopLevelRice().getChildren().get(0);
        String url = builder.getPublishUrl(riceModule, context);
        String expected = "s3://site.origin.kuali.org/rice/2.0.0-b2-SNAPSHOT/rice-core/";
        Assert.assertEquals(expected, url);
    }

    @Test
    public void testGetPublishUrl2() {
        MavenProjectTest kuali = getKuali();
        String url = builder.getPublishUrl(kuali, context);
        String expected = "s3://site.origin.kuali.org/pom/kuali/29-SNAPSHOT/";
        Assert.assertEquals(expected, url);
    }

    @Test
    public void testGetPublishUrl3() {
        MavenProjectTest kualiCommon = getKualiCommon();
        String url = builder.getPublishUrl(kualiCommon, context);
        String expected = "s3://site.origin.kuali.org/pom/kuali-common/110-SNAPSHOT/";
        Assert.assertEquals(expected, url);
    }

    @Test
    public void testGetPublicUrl1() {
        MavenProjectTest riceModule = getTopLevelRice().getChildren().get(0);
        String url = builder.getPublicUrl(riceModule, context);
        String expected = "http://site.kuali.org/rice/2.0.0-b2-SNAPSHOT/rice-core/";
        Assert.assertEquals(expected, url);
    }

    @Test
    public void testGetPublicUrl2() {
        MavenProjectTest kuali = getKuali();
        String url = builder.getPublicUrl(kuali, context);
        String expected = "http://site.kuali.org/pom/kuali/29-SNAPSHOT/";
        Assert.assertEquals(expected, url);
    }

    @Test
    public void testGetPublicUrl3() {
        MavenProjectTest kualiCommon = getKualiCommon();
        String url = builder.getPublicUrl(kualiCommon, context);
        String expected = "http://site.kuali.org/pom/kuali-common/110-SNAPSHOT/";
        Assert.assertEquals(expected, url);
    }

    @Test
    public void test1() {
        MavenProject project = getTopLevelRice();
        // show(project);
    }

    @Test
    public void test2() {
        MavenProject project = getMavenProject(getKualiParentGAV());
        // show(project);
    }

    @Test
    public void test3() {
        MavenProjectTest project = getTopLevelRice();
        List<MavenProjectTest> children = project.getChildren();
        for (MavenProjectTest child : children) {
            // show(child);
        }

    }

    protected void show(MavenProject project) {
        System.out.println(builder.getDownloadUrl(project, context));
        System.out.println(builder.getPublicUrl(project, context));
        System.out.println(builder.getPublishUrl(project, context));
        System.out.println();
    }

    protected GAV getKualiParentGAV() {
        return new GAV("org.kuali.pom", "kuali", "29-SNAPSHOT");
    }

    protected GAV getKualiCommonGAV() {
        return new GAV("org.kuali.pom", "kuali-common", "110-SNAPSHOT");
    }

    protected GAV getRiceGAV() {
        return new GAV("org.kuali.rice", "rice", "2.0.0-b2-SNAPSHOT");
    }

    protected List<GAV> getRiceModuleGAVs() {
        List<GAV> gavs = new ArrayList<GAV>();
        gavs.add(new GAV("org.kuali.rice", "rice-core", "2.0.0-b2-SNAPSHOT"));
        gavs.add(new GAV("org.kuali.rice", "rice-db", "2.0.0-b2-SNAPSHOT"));
        return gavs;
    }

    protected List<MavenProjectTest> getMavenProjects(List<GAV> gavs) {
        List<MavenProjectTest> projects = new ArrayList<MavenProjectTest>();
        for (GAV gav : gavs) {
            projects.add(getMavenProject(gav));
        }
        return projects;
    }

    protected MavenProject getRiceModule() {
        MavenProjectTest project = getMavenProject(getRiceGAV());
        project.setParent(getMavenProject(getKualiCommonGAV()));
        project.setModules(getModules(getRiceModuleGAVs()));
        return project;
    }

    protected MavenProjectTest getKuali() {
        return getMavenProject(getKualiParentGAV());
    }

    protected MavenProjectTest getKualiCommon() {
        MavenProject kuali = getKuali();
        MavenProjectTest kualiCommon = getMavenProject(getKualiCommonGAV());
        kualiCommon.setParent(kuali);
        return kualiCommon;
    }

    protected MavenProjectTest getTopLevelRice() {
        MavenProjectTest rice = getMavenProject(getRiceGAV());

        rice.setParent(getKualiCommon());

        List<MavenProjectTest> children = getMavenProjects(getRiceModuleGAVs());
        for (MavenProject child : children) {
            child.setParent(rice);
        }
        rice.setChildren(children);
        rice.setModules(getModules(getRiceModuleGAVs()));
        return rice;
    }

    protected List<String> getModules(List<GAV> gavs) {
        List<String> modules = new ArrayList<String>();
        for (GAV gav : gavs) {
            modules.add(gav.getArtifactId());
        }
        return modules;
    }

    protected MavenProjectTest getMavenProject(String groupId, String artifactId, String version) {
        MavenProjectTest project = new MavenProjectTest();
        project.setGroupId(groupId);
        project.setArtifactId(artifactId);
        project.setVersion(version);
        return project;
    }

    protected MavenProjectTest getMavenProject(GAV gav) {
        MavenProjectTest project = new MavenProjectTest();
        project.setGroupId(gav.getGroupId());
        project.setArtifactId(gav.getArtifactId());
        project.setVersion(gav.getVersion());
        return project;
    }
}
