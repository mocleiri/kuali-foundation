/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectUtils;

public class ExtractProjectContext extends DefaultProjectContext {

    public ExtractProjectContext() {
        super(Constants.ARTIFACT_ID, getLocations());
    }

    protected static List<String> getLocations() {
        String prefix = ProjectUtils.getCommonClassPathPrefix(Constants.ARTIFACT_ID);
        List<String> locations = new ArrayList<String>();
        locations.add(prefix + "/extract.properties");
        return locations;
    }

}
