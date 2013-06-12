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

package org.kuali.common.impex.spring;

import org.kuali.common.impex.data.ExportDataContext;
import org.kuali.common.impex.data.ExportDataService;
import org.kuali.common.util.execute.Executable;

public class ModularDataExportExecutable implements Executable {

    protected ExportDataService service;

    protected ExportDataContext context;

    protected Boolean skip = DEFAULT_EXECUTION_SKIP;

    public static final Boolean DEFAULT_EXECUTION_SKIP = false;

    public ModularDataExportExecutable(ExportDataContext context, ExportDataService service) {
        this.context = context;
        this.service = service;
    }

    @Override
    public void execute() {
        if (skip) {
            return;
        }

        service.exportTables(context);
    }

    public Boolean getSkip() {
        return skip;
    }

    public void setSkip(Boolean skip) {
        this.skip = skip;
    }
}
