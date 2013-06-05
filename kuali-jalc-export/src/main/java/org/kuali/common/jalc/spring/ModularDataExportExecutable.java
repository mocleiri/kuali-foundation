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

package org.kuali.common.jalc.spring;

import org.kuali.common.jalc.data.ExportDataContext;
import org.kuali.common.jalc.data.ExportDataService;
import org.kuali.common.util.execute.Executable;

public class ModularDataExportExecutable implements Executable {

    protected ExportDataService service;

    protected ExportDataContext context;

    protected Boolean execute = Boolean.TRUE;

    public static final Boolean DEFAULT_EXECUTE_ENABLED = true;

    public ModularDataExportExecutable(ExportDataContext context, ExportDataService service) {
        this.context = context;
        this.service = service;
    }

    @Override
    public void execute() {
        if (!execute) {
            return;
        }

        service.exportTables(context);
    }

    public Boolean getExecute() {
        return execute;
    }

    public void setExecute(Boolean execute) {
        this.execute = execute;
    }
}
