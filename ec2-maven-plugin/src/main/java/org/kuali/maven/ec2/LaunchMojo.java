package org.kuali.maven.ec2;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.PropertiesUtils;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;

/**
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
     * If supplied, the contents of the file are supplied to the instance as userData
     *
     * @parameter expression="${ec2.userDataFile}"
     */
    private File userDataFile;

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
     * List of tags to associate with the instance
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

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        RunInstancesRequest request = new RunInstancesRequest();
        request.setMaxCount(1);
        request.setMinCount(1);
        request.setImageId(ami);
        request.setKeyName(key);
        request.setInstanceType(InstanceType.fromValue(type));
        request.setSecurityGroups(securityGroups);
        setUserData(request);
        RunInstancesResult result = client.runInstances(request);
        Reservation r = result.getReservation();
        List<Instance> instances = r.getInstances();
        Instance i = instances.get(0);
        handleTags(client, i, tags);
        getLog().info(i.getInstanceId());
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

    protected boolean isEmpty(Collection<?> c) {
        return c == null || c.size() == 0;
    }

    protected void setUserData(RunInstancesRequest request) throws MojoExecutionException {
        try {
            String data = getUserData(userData, userDataFile, encoding);
            if (StringUtils.isBlank(data)) {
                return;
            }
            if (filterUserData) {
                PropertiesUtils pu = new PropertiesUtils();
                Properties properties = pu.getMavenProperties(project);
                String filteredUserData = pu.getResolvedValue(data, properties);
                request.setUserData(filteredUserData);
            } else {
                request.setUserData(data);
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Error handling user data", e);
        }
    }

    protected String getUserData(String data, File f, String encoding) throws IOException {
        if (f == null) {
            return data;
        } else {
            return FileUtils.readFileToString(f, encoding);
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

    public File getUserDataFile() {
        return userDataFile;
    }

    public void setUserDataFile(File userDataFile) {
        this.userDataFile = userDataFile;
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
}
