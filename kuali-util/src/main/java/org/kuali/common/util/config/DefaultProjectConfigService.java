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
package org.kuali.common.util.config;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.nullify.Nullifier;
import org.springframework.util.Assert;

public class DefaultProjectConfigService implements ProjectConfigService {

	protected static final String RESOURCES_DIR = "src/main/resources";
	protected static final String PREFIX = "META-INF";
	protected static final String XML_FILE = "metadata.xml";
	protected static final String PROPS_FILE = "metadata.properties";

	@Override
	public ProjectConfig loadMetadata(String groupId, String artifactId) {
		return null;
	}

	@Override
	public void store(File file, ProjectConfig config) {

		Assert.notNull(file, "file is null");
		Assert.notNull(config, "config is null");

		ProjectConfig clone = new ProjectConfig(config);

		Nullifier nullifier = new ProjectConfigNullifier(clone);
		nullifier.nullify();

		Writer writer = null;
		try {
			writer = LocationUtils.openWriter(file);
			JAXBContext context = JAXBContext.newInstance(ProjectConfig.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(clone, writer);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}
}
