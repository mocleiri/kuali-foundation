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

package org.kuali.common.impex.model;

/**
 * Bean that contains size information of a column data type
 */
public class TypeSize {
    protected Integer size;

    protected Integer scale;

    protected Boolean scaleSet;

    public TypeSize(Integer size) {
        this.size = size;
        this.scale = null;
        scaleSet = false;
    }

    public TypeSize(Integer size, Integer scale) {
        this.size = size;
        this.scale = scale;
        scaleSet = true;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getScale() {
        return scale;
    }

    public boolean hasScale() {
        return scaleSet;
    }
}
