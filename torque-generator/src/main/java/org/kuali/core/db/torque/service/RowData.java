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

package org.kuali.core.db.torque.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrewlubbers
 */
public class RowData {

    private List<DataBean> dataBeans;

    public void setDataBeans(List<DataBean> dataBeans) {
        this.dataBeans = dataBeans;
    }

    public List<DataBean> getDataBeans() {
        return dataBeans;
    }

    public List<String> findColumnNames() {
        if(dataBeans == null) {
            return null;
        }

        List<String> results = new ArrayList<String>(dataBeans.size());
        for(DataBean d : dataBeans) {
            results.add(d.getColumn().getName());
        }
        return results;
    }

    public List<DataBean> findPrimaryKeyBeans() {
        if(dataBeans == null) {
            return null;
        }

        List<DataBean> results = new ArrayList<DataBean>();
        for(DataBean d : dataBeans) {
            if(d.getColumn().isPrimaryKey()) {
                results.add(d);
            }
        }

        return results;
    }
}
