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

import com.amazonaws.services.s3.model.ObjectListing;

public class S3PrefixContext {
    boolean isRoot;
    String defaultObjectKey;
    ObjectListing objectListing;
    String prefix;
    String html;
    S3BucketContext bucketContext;
    String browseHtmlKey;

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(final boolean isRoot) {
        this.isRoot = isRoot;
    }

    public String getDefaultObjectKey() {
        return defaultObjectKey;
    }

    public void setDefaultObjectKey(final String defaultObjectKey) {
        this.defaultObjectKey = defaultObjectKey;
    }

    public ObjectListing getObjectListing() {
        return objectListing;
    }

    public void setObjectListing(final ObjectListing objectListing) {
        this.objectListing = objectListing;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(final String html) {
        this.html = html;
    }

    public S3BucketContext getBucketContext() {
        return bucketContext;
    }

    public void setBucketContext(final S3BucketContext context) {
        this.bucketContext = context;
    }

    public String getBrowseHtmlKey() {
        return browseHtmlKey;
    }

    public void setBrowseHtmlKey(final String browseHtmlKey) {
        this.browseHtmlKey = browseHtmlKey;
    }
}
