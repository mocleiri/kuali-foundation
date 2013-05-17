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

package org.kuali.common.impex.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.kuali.common.impex.model.ModelProvider;
import org.kuali.common.impex.service.schema.SchemaSqlProducer;
import org.kuali.common.impex.spring.LiquibaseModelProviderConfig;
import org.kuali.common.impex.spring.SchemaSqlProducerConfig;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ LiquibaseModelProviderConfig.class, SchemaSqlProducerConfig.class })
public class DumpExecutable implements Executable {

    private static Logger log = Logger.getLogger(DumpExecutable.class.getSimpleName());

	private static final String LF = "\n";

	@Autowired
	Environment env;

	@Autowired
	ModelProvider modelProvider;

	@Autowired
	SchemaSqlProducer schemaProducer;

	/**
	 * This property key points to the user defined folder to output sql files A property with the key is required to build the executable
	 */
	final static String DUMP_FOLDER_KEY = "impex.schemadump.folder";

	/**
	 * This property key points to the user defined prefix for dumped file names
	 */
	final static String FILENAME_PREFIX_KEY = "impex.schemadump.prefix";

	final static String DEFAULT_FILENAME_PREFIX = "dump-";

	final static String TABLE_FILE = "tables.sql";
	final static String FOREIGN_KEY_FILE = "foreignKeys.sql";
	final static String SEQUENCE_FILE = "sequences.sql";
	final static String VIEW_FILE = "views.sql";

	@Override
	public void execute() {

		Map<String, List<String>> fileNamesToSqls = new HashMap<String, List<String>>();

        log.info("Loading table data");
		fileNamesToSqls.put(getFileName(TABLE_FILE), schemaProducer.getTablesSql(modelProvider.getTables()));
        log.info("Loading foreign key data");
		fileNamesToSqls.put(getFileName(FOREIGN_KEY_FILE), schemaProducer.getForeignKeySql(modelProvider.getForeignKeys()));
        log.info("Loading sequence data");
		fileNamesToSqls.put(getFileName(SEQUENCE_FILE), schemaProducer.getSequencesSql(modelProvider.getSequences()));
        log.info("Loading view data");
		fileNamesToSqls.put(getFileName(VIEW_FILE), schemaProducer.getViewsSql(modelProvider.getViews()));

		dumpFiles(fileNamesToSqls);

	}

	protected String getFileName(String fileNameSuffix) {
		StringBuilder sb = new StringBuilder();

		String dumpFolder = SpringUtils.getProperty(env, DUMP_FOLDER_KEY);
		String fileNamePrefix = SpringUtils.getProperty(env, FILENAME_PREFIX_KEY, DEFAULT_FILENAME_PREFIX);

		sb.append(dumpFolder).append(fileNamePrefix).append(fileNameSuffix);

		return sb.toString();
	}

	protected static void dumpFiles(Map<String, List<String>> fileNamesToSqls) {

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

    @Bean(initMethod = "execute")
    public DumpExecutable executableInstance() {
        return new DumpExecutable();
    }

}
