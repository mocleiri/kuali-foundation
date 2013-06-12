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
import org.springframework.beans.factory.annotation.Autowired;

public class ExportDataExecutable implements Executable {

    ExportDataContext context;

    ExportDataService service;

    Boolean skip;

    public static final Boolean DEFAULT_SKIP_EXECUTION = false;

    public ExportDataExecutable() {
        this(DEFAULT_SKIP_EXECUTION);
    }

    public ExportDataExecutable(Boolean b) {
        this.skip = b;
    }

    @Override
    public void execute() {
        if(!skip) {
            service.exportTables(context);
        }
    }

    public ExportDataContext getContext() {
        return context;
    }

    public void setContext(ExportDataContext context) {
        this.context = context;
    }

    public ExportDataService getService() {
        return service;
    }

    public void setService(ExportDataService service) {
        this.service = service;
    }
}
