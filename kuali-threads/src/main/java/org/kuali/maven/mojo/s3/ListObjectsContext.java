package org.kuali.maven.mojo.s3;

import com.amazonaws.services.s3.model.ListObjectsRequest;

public class ListObjectsContext {
    S3BucketContext bucketContext;
    ListObjectsRequest request;

    public ListObjectsContext() {
        this(null, null);
    }

    public ListObjectsContext(S3BucketContext bucketContext, ListObjectsRequest request) {
        super();
        this.bucketContext = bucketContext;
        this.request = request;
    }

    public S3BucketContext getBucketContext() {
        return bucketContext;
    }

    public void setBucketContext(S3BucketContext bucketContext) {
        this.bucketContext = bucketContext;
    }

    public ListObjectsRequest getRequest() {
        return request;
    }

    public void setRequest(ListObjectsRequest request) {
        this.request = request;
    }

}
