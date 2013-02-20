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

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexUtils;
import org.springframework.beans.factory.FactoryBean;

public class ImpexContextCloningFactoryBean implements FactoryBean<ImpexContext> {

	ImpexContext sourceContext;
	String include;
	String artifactId;

	@Override
	public ImpexContext getObject() throws Exception {
		return ImpexUtils.clone(sourceContext, include, artifactId);
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
}
