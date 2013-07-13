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
package org.kuali.common.util.spring.metainf;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.MetaInfExecutable;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MetaInfConfig {

    protected static final String EXECUTABLE_SKIP_KEY = "util.metainf.skip";

    protected static final String METAINF_CONTEXTS_KEY = "util.metainf.contexts";

    protected static final String ADD_LINE_COUNT_KEY = ".linecount";

    protected static final boolean DEFAULT_ADD_LINE_COUNT = false;

    protected static final String ADD_PROPERTIES_FILE_KEY = ".propertiesfile";

    protected static final boolean DEFAULT_ADD_PROPERTIES_FILE = false;

    protected static final String SORT_KEY = ".sort";

    protected static final boolean DEFAULT_SORT = MetaInfContext.DEFAULT_SORT;

    protected static final String BASE_DIR_KEY = ".basedir";

    protected static final String OUTPUT_FILE_KEY = ".output";

    protected static final String INCLUDES_KEY = ".includes";

    protected static final String EXCLUDES_KEY = ".excludes";

    protected static final String DEFAULT_EXCLUDES = Constants.NONE;

    protected static final String PREFIX_KEY = ".prefix";

    protected static final String DEFAULT_PREFIX = MetaInfContext.DEFAULT_PREFIX;

    @Autowired
    Environment env;

    @Bean
    public Executable metaInfExecutable() {

        String prefixes = SpringUtils.getProperty(env, METAINF_CONTEXTS_KEY);

        List<String> contextKeys = CollectionUtils.getTrimmedListFromCSV(prefixes);

        List<MetaInfContext> contexts = new ArrayList<MetaInfContext>();

        for (String contextKey : contextKeys) {
            MetaInfContext context = new MetaInfContext();

            context.setAddLineCount(getDefaultedBoolean(contextKey, ADD_LINE_COUNT_KEY, DEFAULT_ADD_LINE_COUNT));
            context.setAddPropertiesFile(getDefaultedBoolean(contextKey, ADD_PROPERTIES_FILE_KEY, DEFAULT_ADD_PROPERTIES_FILE));
            context.setSort(getDefaultedBoolean(contextKey, SORT_KEY, DEFAULT_SORT));

            context.setBaseDir(SpringUtils.getFile(env, buildContextKey(contextKey, BASE_DIR_KEY)));
            context.setOutputFile(SpringUtils.getFile(env, buildContextKey(contextKey, OUTPUT_FILE_KEY)));

            context.setIncludes(SpringUtils.getIncludes(env, buildContextKey(contextKey, INCLUDES_KEY)));
            context.setExcludes(SpringUtils.getExcludes(env, buildContextKey(contextKey, EXCLUDES_KEY), DEFAULT_EXCLUDES));

            context.setPrefix(SpringUtils.getProperty(env, buildContextKey(contextKey, PREFIX_KEY), DEFAULT_PREFIX));

            contexts.add(context);
        }

        MetaInfExecutable mie = new MetaInfExecutable();
        mie.setSkip(SpringUtils.getBoolean(env, EXECUTABLE_SKIP_KEY, MetaInfExecutable.DEFAULT_EXECUTION_SKIP));
        mie.setContexts(contexts);
        return mie;
    }

    private boolean getDefaultedBoolean(String contextKey, String keySuffix, boolean defaultVal) {
        String key = buildContextKey(contextKey, keySuffix);
        return SpringUtils.getBoolean(env, key, defaultVal);
    }

    private String buildContextKey(String contextKey, String keySuffix) {
        return contextKey + keySuffix;
    }

}
