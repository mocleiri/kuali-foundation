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

package org.kuali.common.impex.model.util;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.model.NamedElement;

public class MissingElements<T extends NamedElement> {

    List<T> set1Only;

    List<T> set2Only;

    List<MatchingElement<T>> both;

    public MissingElements() {
        both = new ArrayList<MatchingElement<T>>();
        set1Only = new ArrayList<T>();
        set2Only = new ArrayList<T>();
    }

    public List<MatchingElement<T>> getBoth() {
        return both;
    }

    public void setBoth(List<MatchingElement<T>> both) {
        this.both = both;
    }

    public List<T> getSet1Only() {
        return set1Only;
    }

    public void setSet1Only(List<T> set1Only) {
        this.set1Only = set1Only;
    }

    public List<T> getSet2Only() {
        return set2Only;
    }

    public void setSet2Only(List<T> set2Only) {
        this.set2Only = set2Only;
    }
}
