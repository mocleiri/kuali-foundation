/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlExecutionContext;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.ProgressListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * @deprecated
 */
@Deprecated
public class SqlConfigUtils {

    private static final Logger logger = LoggerFactory.getLogger(SqlConfigUtils.class);

	public static final String SQL_ORDER_KEY = "sql.execution.order";
	public static final String RESOURCES_SUFFIX = ".resources";

    public static final String SQL_NAMESPACE_TOKEN = "sql";

    protected static final String SKIP_PROPERTY_KEY_SUFFIX = ".skip";

    protected static final String SKIP_EXECUTABLE_PROPERTY_KEY_SUFFIX = ".executable.skip";

    protected static final String TRACK_PROGRESS_KEY_SUFFIX = ".trackProgressByUpdateCount";

    public static JdbcExecutable getJdbcExecutable(SqlConfigContext scc) {
		String skipKey = scc.getContext().getGroup() + SKIP_EXECUTABLE_PROPERTY_KEY_SUFFIX;

		JdbcContext context = getJdbcContext(scc);
		context.setListener(getSqlListener(scc.getContext().getMode()));

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(scc.getEnv(), skipKey, false));
		exec.setService(scc.getCommonConfig().jdbcService());
		exec.setContext(context);
		return exec;
	}

	public static SqlListener getSqlListener(SqlMode mode) {
		switch (mode) {
		case CONCURRENT:
			return new LogSqlListener();
		case SEQUENTIAL:
			List<SqlListener> listeners = new ArrayList<SqlListener>();
			listeners.add(new LogSqlListener());
			listeners.add(new ProgressListener());
			return new NotifyingListener(listeners);
		default:
			throw new IllegalArgumentException("mode [" + mode.name() + "] is unknown");
		}
	}

	public static JdbcContext getJdbcContext(SqlConfigContext scc) {
		SqlExecutionContext ctx = scc.getContext();
		SqlMode mode = ctx.getMode();
		switch (mode) {
		case CONCURRENT:
			return getConcurrentJdbcContext(scc);
		case SEQUENTIAL:
			return getSequentialJdbcContext(scc);
		default:
			throw new IllegalArgumentException("mode [" + mode.name() + "] is unknown");
		}
	}

	public static List<SqlExecutionContext> getSqlExecutionContexts(Environment env) {
		// Extract the CSV value for "sql.execution.order" from the environment
		String csv = SpringUtils.getProperty(env, SQL_ORDER_KEY);

		// NONE or NULL means there is no sql to execute
		if (NullUtils.isNullOrNone(csv)) {
			csv = Str.EMPTY_STRING;
		}

		// Convert the CSV to a list of property keys
		List<String> propertyKeys = CollectionUtils.getTrimmedListFromCSV(csv);

		// Validate that the property keys referenced by sql.execution.order actually exist
		// Validate that all of the resources referenced by any of the properties also exist
		validateSqlExecutionOrderValues(env, propertyKeys);

		// Convert the text values into pojo's
		return getSqlExecutionContexts(propertyKeys);
	}

	public static void validateSqlExecutionOrderValues(Environment env, List<String> propertykeys) {

		// Go through the list of keys and check each one
		for (String propertyKey : propertykeys) {

			// This is a CSV list of actual resources (or .resources files) to load
			String csv = SpringUtils.getProperty(env, propertyKey);

			// Extract the CSV list of resources referenced by this property key
			List<String> resources = CollectionUtils.getTrimmedListFromCSV(csv);

			// Validate that all of the resources exist and that all of the locations referenced by any .resources files also exist
			validateResources(resources);
		}
	}

	public static void validateResources(List<String> resources) {
		// Validate that every resource exists
		for (String resource : resources) {

			// Make sure the .resources file exists
			LocationUtils.validateLocation(resource);

			// Make sure each resource inside the .resources file exists
			if (StringUtils.endsWithIgnoreCase(resource, RESOURCES_SUFFIX)) {
				LocationUtils.validateLocationListing(resource);
			}
		}
	}

	public static List<SqlExecutionContext> getSqlExecutionContexts(List<String> propertyKeys) {

		// Setup some storage for the contexts
		List<SqlExecutionContext> contexts = new ArrayList<SqlExecutionContext>();

		// Each property key will be something like "sql.schema.concurrent" where the last 2 tokens represent the "group" and the "execution mode"
		for (String propertyKey : propertyKeys) {

			// properties are assumed to have the following structure:
            // "sql." + any user string + "." + valid SqlMode
            // ex.  "sql.foo.bar.concurrent"
			String[] tokens = StringUtils.split(propertyKey, Str.DOT);

			// Must have at least 3 tokens
			Assert.isTrue(tokens.length >= 3, "tokens.length < 3");

            // These are the indexes corresponding to the tokens that indicate sql prefix and mode
            int sqlTokenIndex = 0;
			int modeIndex = tokens.length - 1;

            Assert.isTrue(tokens[sqlTokenIndex].equals(SQL_NAMESPACE_TOKEN), "sql execution properties must start with a 'sql.' namespace");

            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for(int i = 0; i < tokens.length; i++) {

                if (i != modeIndex) {
                    // append a dot, but only if at least one token has been appended
                    if(first) {
                        first = false;
                    }
                    else {
                        sb.append(Str.DOT);
                    }

                    // append the token
                    sb.append(tokens[i]);
                }
            }

			// Extract the group. This can be any arbitrary text that indicates some kind of SQL grouping, eg "schema", "data", "other"
            String group = sb.toString();

			// Extract the mode and convert to upper case
			// Only values allowed here are "sequential" and "concurrent"
			String modeString = StringUtils.trim(tokens[modeIndex].toUpperCase());

			// Convert the mode string to a strongly typed object
			SqlMode mode = SqlMode.valueOf(modeString);

			// Create a new context and add it to the list
			contexts.add(new SqlExecutionContext(propertyKey, group, mode));
		}

		// Return the list we created
		return contexts;
	}

	public static JdbcContext getConcurrentJdbcContext(SqlConfigContext rcc) {
		String threads = SpringUtils.getProperty(rcc.getEnv(), "sql.threads");
		JdbcContext ctx = getBaseJdbcContext(rcc);
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		return ctx;
	}

	public static JdbcContext getSequentialJdbcContext(SqlConfigContext rcc) {
		JdbcContext ctx = getBaseJdbcContext(rcc);
		ctx.setMultithreaded(false);
		ctx.setThreads(1);
		return ctx;
	}

	protected static JdbcContext getBaseJdbcContext(SqlConfigContext scc) {
		SqlExecutionContext sec = scc.getContext();
		// dba, schema, data, constraints, other
		String group = sec.getGroup();
		// sql.dba.concurrent, sql.dba.sequential, sql.schema.concurrent, sql.schema.sequential, etc
		String propertyKey = scc.getContext().getKey();
        String skipKey = group + SKIP_PROPERTY_KEY_SUFFIX;
        String trackProgressKey = propertyKey + TRACK_PROGRESS_KEY_SUFFIX;
		String message = "[" + sec.getGroup() + ":" + sec.getMode().name().toLowerCase() + "]";


        boolean skip = SpringUtils.getBoolean(scc.getEnv(), skipKey, false);
		boolean trackProgressByUpdateCount = SpringUtils.getBoolean(scc.getEnv(), trackProgressKey, false);
		logger.debug("{}={}", trackProgressKey, trackProgressByUpdateCount);
		List<SqlSupplier> suppliers = scc.getCommonConfig().getSqlSuppliers(propertyKey);
		DataSource dataSource = scc.getDataSourceConfig().jdbcDataSource();

		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(message);
		ctx.setSkip(skip);
		ctx.setDataSource(dataSource);
		ctx.setTrackProgressByUpdateCount(trackProgressByUpdateCount);
		ctx.setSuppliers(suppliers);
		return ctx;
	}

}
