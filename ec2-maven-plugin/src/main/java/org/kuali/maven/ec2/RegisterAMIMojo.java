package org.kuali.maven.ec2;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal registerami
 */
public class RegisterAMIMojo extends AbstractEC2Mojo {

    /**
     * The volume to take a snapshot of
     *
     * @parameter expression="${ec2.snapshotId}"
     * @required
     */
    private String snapshotId;

    /**
     * The description of the snapshot (if any)
     *
     * @parameter expression="${ec2.description}"
     */
    private String description;

    /**
     * If true, the build will wait until EC2 reports that the instance has reached the state of "terminated"
     *
     * @parameter expression="${ec2.wait}" default-value="false"
     */
    private boolean wait;

    /**
     * The number of seconds to wait for the instance to terminate before timing out and failing the build
     *
     * @parameter expression="${ec2.waitTimeout}" default-value="300"
     */
    private int waitTimeout;

    /**
     * The state the plugin will wait for the snapshot to be in before continuing
     *
     * @parameter expression="${ec2.state}" default-value="completed"
     */
    private String state;

    @Override
    public void execute(EC2Utils ec2Utils) throws MojoExecutionException {
    }
}
