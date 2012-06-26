package org.kuali.maven.ec2.state;

import org.apache.commons.lang.StringUtils;
import org.kuali.maven.ec2.EC2Utils;

public class SnapshotStateRetriever implements StateRetriever {

    EC2Utils ec2Utils;
    String snapshotId;

    public SnapshotStateRetriever() {
        this(null, null);
    }

    public SnapshotStateRetriever(EC2Utils ec2Utils, String snapshotId) {
        super();
        this.ec2Utils = ec2Utils;
        this.snapshotId = snapshotId;
    }

    @Override
    public String getState() {
        return null;
    }

    protected void validate() {
        if (ec2Utils == null || StringUtils.isBlank(snapshotId)) {
            throw new IllegalStateException("snapshot id must not be blank, and ec2Utils must be non-null");
        }
    }

    public EC2Utils getEc2Utils() {
        return ec2Utils;
    }

    public void setEc2Utils(EC2Utils ec2Utils) {
        this.ec2Utils = ec2Utils;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

}
