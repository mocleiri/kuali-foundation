/*
 * Copyright 2006-2011 The Kuali Foundation
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

package org.kuali.rice.core.api.parameter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.kuali.rice.core.api.CoreConstants;
import org.w3c.dom.Element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Collection;

/**
*  This class represents the 4-part key which uniquely identifies a parameter.
*  
*  @see ParameterContract
*  @see Parameter
*/
@XmlRootElement(name = ParameterKey.Constants.ROOT_ELEMENT_NAME)
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = ParameterKey.Constants.TYPE_NAME, propOrder = {
    ParameterKey.Elements.APPLICATION_CODE,
    ParameterKey.Elements.NAMESPACE_CODE,
    ParameterKey.Elements.COMPONENT_CODE,
    ParameterKey.Elements.NAME,
    CoreConstants.CommonElements.FUTURE_ELEMENTS
})
public final class ParameterKey implements Serializable {

	private static final long serialVersionUID = -4405355319548951283L;

	@XmlElement(name = Elements.APPLICATION_CODE, required=true)
	private final String applicationCode;

	@XmlElement(name = Elements.NAMESPACE_CODE, required=true)
	private final String namespaceCode;

	@XmlElement(name = Elements.COMPONENT_CODE, required=true)
	private final String componentCode;
	
    @XmlElement(name = Elements.NAME, required=true)
    private final String name;

    @SuppressWarnings("unused")
	@XmlAnyElement
    private final Collection<Element> _futureElements = null;

    /** 
     * This constructor should never be called except during JAXB unmarshalling. 
     */
    private ParameterKey() {
    	this.applicationCode = null;
    	this.namespaceCode = null;
    	this.componentCode = null;
    	this.name = null;
    }

	/**
	 * Constructs a ParameterKey from the given values.
	 */
    private ParameterKey(String applicationCode, String namespaceCode, String componentCode, String name) {
        if (StringUtils.isBlank(applicationCode)) {
            throw new IllegalArgumentException("applicationCode is blank");
        }
        if (StringUtils.isBlank(namespaceCode)) {
            throw new IllegalArgumentException("namespaceCode is blank");
        }
        if (StringUtils.isBlank(componentCode)) {
            throw new IllegalArgumentException("componentCode is blank");
        }
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("name is blank");
		}
        this.applicationCode = applicationCode;
        this.namespaceCode = namespaceCode;
        this.componentCode = componentCode;
		this.name = name;
    }

    /**
     * Creates a ParameterKey from the given required values.
     * 
     * @param applicationCode the application code, cannot be null or blank
     * @param namespaceCode the namespace code, cannot be null or blank
     * @param componentCode the component code, cannot be null or blank
	 * @param name the parameter name, cannot be null or blank
     * @return the fully-constructed ParameterKey
     * @throws IllegalArgumentException if any arguments are null or blank
     */
    public static ParameterKey create(String applicationCode, String namespaceCode, String componentCode, String name) {
        return new ParameterKey(applicationCode, namespaceCode, componentCode, name);
    }
	
    public String getApplicationCode() {
		return applicationCode;
	}

	public String getNamespaceCode() {
		return namespaceCode;
	}

	public String getComponentCode() {
		return componentCode;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, Constants.HASH_CODE_EQUALS_EXCLUDE);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(obj, this, Constants.HASH_CODE_EQUALS_EXCLUDE);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
    
	/**
	 * Defines some internal constants used on this class.
	 */
	static class Constants {
		final static String ROOT_ELEMENT_NAME = "parameterKey";
		final static String TYPE_NAME = "ParameterKeyType";
		final static String[] HASH_CODE_EQUALS_EXCLUDE = { CoreConstants.CommonElements.FUTURE_ELEMENTS};
	}
  
	/**
	 * A private class which exposes constants which define the XML element names to use
	 * when this object is marshalled to XML.
	 */
	static class Elements {
		final static String APPLICATION_CODE = "applicationCode";
		final static String NAMESPACE_CODE = "namespaceCode";
		final static String COMPONENT_CODE = "componentCode";
		final static String NAME = "name";
	}
  
}