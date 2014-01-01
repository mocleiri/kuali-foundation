/**
 * Copyright 2010-2014 The Kuali Foundation
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

import java.io.File;

public class DirectoryContext {

	File directory;
	SqlConverter converter;
	String include;
	String exclude;
    private PostConversionProcessor postProcessor;

    public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	public SqlConverter getConverter() {
		return converter;
	}

	public void setConverter(SqlConverter converter) {
		this.converter = converter;
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getExclude() {
		return exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
	}

    public PostConversionProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(PostConversionProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }
}
