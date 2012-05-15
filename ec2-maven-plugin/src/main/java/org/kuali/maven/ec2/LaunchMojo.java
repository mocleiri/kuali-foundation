package org.kuali.maven.ec2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.common.ResourceUtils;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;

/**
 * Connect to EC2 and launch a a single instance configured according to user preferences. By default, the plugin waits
 * until the instance reaches the state of "running" before allowing the build to continue. Once an EC2 instance is
 * "running" Amazon has assigned it a public dns name. The publi dns name, the instance id, and the value of the tag
 * "Name" (if that tag is supplied) are stored as the project properties <code>ec2.instance.dns</code>,
 * <code>ec2.instance.id</code>, <code>ec2.instance.name</code>, respectively.
 *
 * If <code>wait</code> is false, the <code>ec2.instance.dns</code> property will not be set since the instance will not
 * have a public dns name by the time the plugin execution completes.
 *
 * @goal launch
 */
public class LaunchMojo extends AbstractEC2Mojo {

    /**
     * The Maven project object
     *
     * @parameter expression="${project}"
     * @readonly
     */
    private MavenProject project;

    /**
     * The AMI to launch
     *
     * @parameter expression="${ec2.ami}"
     * @required
     */
    private String ami;

    /**
     * The name of the key to use
     *
     * @parameter expression="${ec2.key}"
     * @required
     */
    private String key;

    /**
     * The type of instance to launch
     *
     * @parameter expression="${ec2.type}" default-value="c1.medium";
     * @required
     */
    private String type;

    /**
     * The security groups into which the instance will be launched
     *
     * @parameter
     */
    private List<String> securityGroups;

    /**
     * Optional user data for the instance
     *
     * @parameter expression="${ec2.userData}"
     */
    private String userData;

    /**
     * If supplied, the contents of the file are supplied to the instance as userData. This can be a file on the file
     * system, or any url Spring resource loading can understand eg "<code>classpath:user-data.txt</code>"
     *
     * @parameter expression="${ec2.userDataFile}"
     */
    private String userDataFile;

    /**
     * If true, userData is filtered with current project, environment, and system properties before being supplied to
     * the instance.
     *
     * @parameter expression="${ec2.filterUserData}
     */
    private boolean filterUserData;

    /**
     * The encoding of the userDataFile
     *
     * @parameter expression="${ec2.encoding}" default-value="${project.build.sourceEncoding}"
     */
    private String encoding;

    /**
     * List of tags to associate with the instance. Tags are key value pairs and can be supplied in the plugin
     * configuration like this:<br>
     *
     * <pre>
     *   &lt;tags&gt;
     *     &lt;tag&gt;
     *       &lt;key&gt;Name&lt;/key&gt;
     *       &lt;value&gt;production&lt;/value&gt;
     *     &lt;/tag&gt;
     *     &lt;tag&gt;
     *       &lt;key&gt;Category&lt;/key&gt;
     *       &lt;value&gt;networking&lt;/value&gt;
     *     &lt;/tag&gt;
     *   &lt;/tags&gt;
     * </pre>
     *
     * @parameter
     */
    private List<Tag> tags;

    /**
     * If true, the build will wait until EC2 reports that the instance has reached the state of "running"
     *
     * @parameter expression="${ec2.wait}" default-value="true"
     */
    private boolean wait;

    /**
     * The number of seconds to wait for the instance to start before timing out and failing the build
     *
     * @parameter expression="${ec2.waitTimeout}" default-value="300"
     */
    private int waitTimeout;

    /**
     * The state the instance needs to be in before the plugin considers it to be started.
     *
     * @parameter expression="${ec2.state}" default-value="running"
     */
    private String state;

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        RunInstancesRequest request = getRequest();
        Instance i = getInstance(client, request);
        handleTags(client, i, tags);
        wait(client, i);
        Instance running = getInstance(client, i.getInstanceId());
        Properties props = project.getProperties();
        props.setProperty("ec2.instance.id", running.getInstanceId());
        props.setProperty("ec2.instance.name", getTagValue(running, "Name"));
    }

    protected RunInstancesRequest getRequest() throws MojoExecutionException {
        RunInstancesRequest request = new RunInstancesRequest();
        request.setMaxCount(1);
        request.setMinCount(1);
        request.setImageId(ami);
        request.setKeyName(key);
        request.setInstanceType(InstanceType.fromValue(type));
        request.setSecurityGroups(securityGroups);
        String data = getUserData(userData, userDataFile, encoding);
        request.setUserData(data);
        return request;
    }

    protected Instance getInstance(AmazonEC2 client, RunInstancesRequest request) {
        RunInstancesResult result = client.runInstances(request);
        Reservation r = result.getReservation();
        List<Instance> instances = r.getInstances();
        return instances.get(0);
    }

    protected void wait(AmazonEC2 client, Instance i) throws MojoExecutionException {
        if (wait) {
            getLog().info("Waiting up to " + waitTimeout + " seconds for " + i.getInstanceId() + " to start");
            waitForState(client, i.getInstanceId(), state, waitTimeout);
            Instance running = getInstance(client, i.getInstanceId());
            String id = i.getInstanceId();
            String dns = running.getPublicDnsName();
            getLog().info("EC2 Instance: " + getTagValue(running, "Name") + " (" + id + ") " + dns);
            Properties props = project.getProperties();
            props.setProperty("ec2.instance.dns", running.getPublicDnsName());
        } else {
            getLog().info("Launched " + i.getInstanceId());
        }
    }

    protected void handleTags(AmazonEC2 client, Instance instance, List<Tag> tags) {
        if (isEmpty(tags)) {
            return;
        }
        CreateTagsRequest request = new CreateTagsRequest();
        request.setResources(Collections.singletonList(instance.getInstanceId()));
        request.setTags(tags);
        client.createTags(request);
    }

    protected String getUserData(String data, String location, String encoding) throws MojoExecutionException {
        String s = data;
        if (!StringUtils.isBlank(location)) {
            try {
                s = getString(location, encoding);
            } catch (IOException e) {
                throw new MojoExecutionException("Error reading from " + location, e);
            }
        }
        if (StringUtils.isBlank(s)) {
            return null;
        }
        if (filterUserData) {
            PropertiesUtils pu = new PropertiesUtils();
            Properties properties = pu.getMavenProperties(project);
            s = pu.getResolvedValue(s, properties);
        }
        byte[] bytes = Base64.encodeBase64(s.getBytes());
        return new String(bytes);
    }

    protected String getString(String location, String encoding) throws IOException {
        InputStream in = null;
        try {
            ResourceUtils ru = new ResourceUtils();
            in = ru.getInputStream(location);
            return IOUtils.toString(in, encoding);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public String getAmi() {
        return ami;
    }

    public void setAmi(String ami) {
        this.ami = ami;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(List<String> securityGroups) {
        this.securityGroups = securityGroups;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public boolean isFilterUserData() {
        return filterUserData;
    }

    public void setFilterUserData(boolean filterUserData) {
        this.filterUserData = filterUserData;
    }

    public MavenProject getProject() {
        return project;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    public int getWaitTimeout() {
        return waitTimeout;
    }

    public void setWaitTimeout(int waitTimeout) {
        this.waitTimeout = waitTimeout;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserDataFile() {
        return userDataFile;
    }

    public void setUserDataFile(String userDataFile) {
        this.userDataFile = userDataFile;
    }
}
