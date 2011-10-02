package org.kuali.maven.plugins;

import java.util.List;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * @author Jeff Caddel
 * @goal deployjars
 */
public class DeployJarsMojo extends AbstractMojo {
    /**
     * The Maven project this plugin runs in.
     * 
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        List<ArtifactRepository> repos = project.getRemoteArtifactRepositories();
        for (ArtifactRepository repo : repos) {
            getLog().info(repo.getId());
        }
    }

}
