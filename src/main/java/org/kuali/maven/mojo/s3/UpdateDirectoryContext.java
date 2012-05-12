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
