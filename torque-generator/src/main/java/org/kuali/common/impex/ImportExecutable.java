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

import org.kuali.common.impex.service.DefaultImpexService;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexService;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author andrewlubbers
 */
public class ImportExecutable implements Executable {

    private static final Logger logger = LoggerFactory.getLogger(ImportExecutable.class);

    ImpexService service;
    ImpexContext sourceContext;
    ExecutionContext sqlExecutionContext;

    @Override
    public void execute() {
        Assert.notNull(sourceContext);
        Assert.notNull(service);
        Assert.notNull(sqlExecutionContext);

        logger.info("Starting Import");
        ImpexUtils.log(sourceContext);

        // import the data from the generated mpx files
        service.importData(sourceContext, sqlExecutionContext);
        logger.info("Import Complete");
    }

    public ImpexService getService() {
        return service;
    }

    public void setService(ImpexService service) {
        this.service = service;
    }

    public ImpexContext getSourceContext() {
        return sourceContext;
    }

    public void setSourceContext(ImpexContext sourceContext) {
        this.sourceContext = sourceContext;
    }

    public ExecutionContext getSqlExecutionContext() {
        return sqlExecutionContext;
    }

    public void setSqlExecutionContext(ExecutionContext sqlExecutionContext) {
        this.sqlExecutionContext = sqlExecutionContext;
    }
}
