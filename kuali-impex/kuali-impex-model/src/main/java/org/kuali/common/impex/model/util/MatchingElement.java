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

import org.kuali.common.impex.model.NamedElement;

public class MatchingElement<T extends NamedElement> {

    T set1Element;

    T set2Element;

    public MatchingElement(T set1Element, T set2Element) {
        this.set1Element = set1Element;
        this.set2Element = set2Element;
    }

    public T getSet1Element() {
        return set1Element;
    }

    public void setSet1Element(T set1Element) {
        this.set1Element = set1Element;
    }

    public T getSet2Element() {
        return set2Element;
    }

    public void setSet2Element(T set2Element) {
        this.set2Element = set2Element;
    }
}
