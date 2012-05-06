/**
 * Copyright 2004-2012 The Kuali Foundation
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
