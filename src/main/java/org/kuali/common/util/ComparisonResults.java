/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andy
 * Date: 2/22/13
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ComparisonResults {

    private List<String> added;
    private List<String> same;
    private List<String> deleted;

    public List<String> getAdded() {
        return added;
    }

    public void setAdded(List<String> added) {
        this.added = added;
    }

    public List<String> getDeleted() {
        return deleted;
    }

    public void setDeleted(List<String> deleted) {
        this.deleted = deleted;
    }

    public List<String> getSame() {
        return same;
    }

    public void setSame(List<String> same) {
        this.same = same;
    }
}
