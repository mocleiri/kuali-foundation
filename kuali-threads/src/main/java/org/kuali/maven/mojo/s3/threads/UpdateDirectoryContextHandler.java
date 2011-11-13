package org.kuali.maven.mojo.s3.threads;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
import org.kuali.maven.mojo.s3.S3PrefixContext;
import org.kuali.maven.mojo.s3.UpdateDirectoryContext;
import org.kuali.maven.mojo.s3.UpdateOriginBucketMojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.AmazonServiceException;

public class UpdateDirectoryContextHandler implements ElementHandler<UpdateDirectoryContext> {
    private final Logger logger = LoggerFactory.getLogger(ListObjectsContextHandler.class);

    UpdateOriginBucketMojo mojo;

    public UpdateDirectoryContextHandler() {
        this(null);
    }

    public UpdateDirectoryContextHandler(UpdateOriginBucketMojo mojo) {
        super();
        this.mojo = mojo;
    }

    @Override
    public void handleElement(ListIteratorContext<UpdateDirectoryContext> context, int index,
            UpdateDirectoryContext element) {
        logger.debug("[Thread:" + lpad(context.getId()) + ", Element:" + lpad(index) + "] " + element.getCopyToKey());

        try {
            S3PrefixContext prefixContext = element.getContext();
            if (prefixContext.isRoot()) {
                mojo.updateRoot(element);
            } else {
                mojo.updateDirectory(element);
            }
        } catch (IOException e) {
            throw new AmazonServiceException("Unexpected error:", e);
        }
    }

    protected String lpad(int i) {
        return StringUtils.leftPad(i + "", 3, " ");
    }

    public UpdateOriginBucketMojo getMojo() {
        return mojo;
    }

    public void setMojo(UpdateOriginBucketMojo mojo) {
        this.mojo = mojo;
    }
}
