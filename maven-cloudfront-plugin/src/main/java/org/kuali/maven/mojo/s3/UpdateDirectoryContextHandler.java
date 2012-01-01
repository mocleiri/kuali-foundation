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

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
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
