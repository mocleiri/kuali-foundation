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
package org.kuali.common.jdbc.convert;

import org.kuali.common.jdbc.convert.DirectoryContext;
import org.kuali.common.jdbc.convert.DirectoryConverter;
import org.kuali.common.jdbc.convert.PostConversionProcessor;
import org.kuali.common.jdbc.convert.SqlConverter;
import org.kuali.common.util.execute.Executable;

import java.io.File;

/**
 * This is a helper class for launching a DirectoryConverter from a spring context
 *
 * @author andrewlubbers
 */
public class DirectoryConverterExecutable implements Executable {

    private String inputSqlFolder;

    private String includedFilePattern;

    private String excludedFilePattern;

    private PostConversionProcessor postConversionProcessor;

    private SqlConverter sqlConverter;

    public void execute() {
        File directory = new File(inputSqlFolder);

        DirectoryContext context = new DirectoryContext();
        context.setConverter(sqlConverter);
        context.setDirectory(directory);
        context.setInclude(includedFilePattern);
        context.setExclude(excludedFilePattern);
        context.setPostProcessor(postConversionProcessor);
        DirectoryConverter dc = new DirectoryConverter();
        dc.convert(context);
    }

    public String getExcludedFilePattern() {
        return excludedFilePattern;
    }

    public void setExcludedFilePattern(String excludedFilePattern) {
        this.excludedFilePattern = excludedFilePattern;
    }

    public String getIncludedFilePattern() {
        return includedFilePattern;
    }

    public void setIncludedFilePattern(String includedFilePattern) {
        this.includedFilePattern = includedFilePattern;
    }

    public String getInputSqlFolder() {
        return inputSqlFolder;
    }

    public void setInputSqlFolder(String inputSqlFolder) {
        this.inputSqlFolder = inputSqlFolder;
    }

    public PostConversionProcessor getPostConversionProcessor() {
        return postConversionProcessor;
    }

    public void setPostConversionProcessor(PostConversionProcessor postConversionProcessor) {
        this.postConversionProcessor = postConversionProcessor;
    }

    public SqlConverter getSqlConverter() {
        return sqlConverter;
    }

    public void setSqlConverter(SqlConverter sqlConverter) {
        this.sqlConverter = sqlConverter;
    }
}
