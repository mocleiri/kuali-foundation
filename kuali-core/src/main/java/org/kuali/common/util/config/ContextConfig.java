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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.CollectionUtils;

/**
 * @deprecated
 */
@Deprecated
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ContextConfig {

	String id;
	List<Location> locations = new ArrayList<Location>();
	List<ContextConfig> contexts = new ArrayList<ContextConfig>();

	public ContextConfig(ContextConfig config) {
		super();
		this.id = config.getId();
		for (Location location : CollectionUtils.toEmptyList(config.getLocations())) {
			this.locations.add(new Location(location));
		}
		for (ContextConfig child : CollectionUtils.toEmptyList(config.getContexts())) {
			this.contexts.add(new ContextConfig(child));
		}
	}

	public ContextConfig() {
		this((String) null);
	}

	public ContextConfig(String name) {
		this(name, null);
	}

	public ContextConfig(String name, List<Location> locations) {
		super();
		this.id = name;
		this.locations = locations;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	@XmlElement(name = "location")
	public List<Location> getLocations() {
		return locations;
	}

	@XmlElement(name = "context")
	public List<ContextConfig> getContexts() {
		return contexts;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void setContexts(List<ContextConfig> children) {
		this.contexts = children;
	}

	public void setId(String name) {
		this.id = name;
	}

}
