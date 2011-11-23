package org.kuali.maven.mojo.s3;

public class UpdateDirectoryContext {
    S3PrefixContext context;
    boolean isCopyIfExists;
    String copyToKey;

    public S3PrefixContext getContext() {
        return context;
    }

    public void setContext(S3PrefixContext context) {
        this.context = context;
    }

    public boolean isCopyIfExists() {
        return isCopyIfExists;
    }

    public void setCopyIfExists(boolean isCopyIfExists) {
        this.isCopyIfExists = isCopyIfExists;
    }

    public String getCopyToKey() {
        return copyToKey;
    }

    public void setCopyToKey(String copyToKey) {
        this.copyToKey = copyToKey;
    }

}
