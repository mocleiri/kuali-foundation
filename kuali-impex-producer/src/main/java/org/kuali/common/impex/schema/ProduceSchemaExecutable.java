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

package org.kuali.common.impex.schema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.kuali.common.impex.spring.SchemaSqlProducerConfig;
import org.kuali.common.impex.spring.XmlModelProviderConfig;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ XmlModelProviderConfig.class, SchemaSqlProducerConfig.class })
public class ProduceSchemaExecutable implements Executable {

    private static final Logger log = LoggerFactory.getLogger(ProduceSchemaExecutable.class.getSimpleName());

	protected static final String LF = "\n";

    protected static final boolean DEFAULT_SKIP_EXECUTION = false;

    Map<String, List<String>> fileNamesToSqls;

    boolean skip = DEFAULT_SKIP_EXECUTION;

    @Override
    public void execute() {
        if(skip) {
            return;
        }

        for (String fileName : fileNamesToSqls.keySet()) {
            List<String> sqls = fileNamesToSqls.get(fileName);
            long start = System.currentTimeMillis();

            log.info("Writing " + sqls.size() + " sql statments to file " + fileName);

            Writer writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(fileName));

                for (String s : sqls) {
                    writer.write(s);
                    writer.write(LF);
                    writer.write(LF);
                }

            } catch (IOException e) {
                throw new IllegalStateException("Could not write to file " + fileName + ", IOException was thrown: " + e.getMessage(), e);
            } finally {
                IOUtils.closeQuietly(writer);
            }

            log.info("File output complete, took: " + (System.currentTimeMillis() - start)/1000l + " seconds");
        }
    }

    public Map<String, List<String>> getFileNamesToSqls() {
        return fileNamesToSqls;
    }

    public void setFileNamesToSqls(Map<String, List<String>> fileNamesToSqls) {
        this.fileNamesToSqls = fileNamesToSqls;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
}
