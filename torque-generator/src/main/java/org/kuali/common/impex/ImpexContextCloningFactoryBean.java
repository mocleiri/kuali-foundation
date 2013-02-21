/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.impex;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexUtils;
import org.springframework.beans.factory.FactoryBean;

public class ImpexContextCloningFactoryBean implements FactoryBean<ImpexContext> {

	ImpexContext sourceContext;
	String include;
	String artifactId;
	File finalDirectory;
	boolean copyDataFiles;

	@Override
	public ImpexContext getObject() throws Exception {
		ImpexContext context = ImpexUtils.clone(sourceContext, include, artifactId);
		copyProperties(context, this);
		return context;
	}

	protected void copyProperties(ImpexContext dest, ImpexContextCloningFactoryBean orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public Class<ImpexContext> getObjectType() {
		return ImpexContext.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public ImpexContext getSourceContext() {
		return sourceContext;
	}

	public void setSourceContext(ImpexContext sourceContext) {
		this.sourceContext = sourceContext;
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public File getFinalDirectory() {
		return finalDirectory;
	}

	public void setFinalDirectory(File scmBaseDirectory) {
		this.finalDirectory = scmBaseDirectory;
	}
}
