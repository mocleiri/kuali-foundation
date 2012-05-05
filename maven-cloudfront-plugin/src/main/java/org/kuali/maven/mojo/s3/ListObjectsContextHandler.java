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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;

public class ListObjectsContextHandler implements ElementHandler<ListObjectsContext> {
    private final Logger logger = LoggerFactory.getLogger(ListObjectsContextHandler.class);
    private final Object mutex = new Object();

    List<ObjectListing> objectListings;

    @Override
    public void handleElement(ListIteratorContext<ListObjectsContext> context, int index, ListObjectsContext element) {
        S3BucketContext bucketContext = element.getBucketContext();
        AmazonS3Client client = bucketContext.getClient();
        ListObjectsRequest request = element.getRequest();
        request.getPrefix();
        logger.debug("[Thread:" + lpad(context.getId()) + ", Element:" + lpad(index) + "] " + request.getPrefix());
        ObjectListing listing = client.listObjects(request);
        synchronized (mutex) {
            if (objectListings == null) {
                objectListings = new ArrayList<ObjectListing>();
            }
            objectListings.add(listing);
        }
    }

    protected String lpad(int i) {
        return StringUtils.leftPad(i + "", 3, " ");
    }

    public List<ObjectListing> getObjectListings() {
        return objectListings;
    }

    public void setObjectListings(List<ObjectListing> objectListings) {
        this.objectListings = objectListings;
    }

}
