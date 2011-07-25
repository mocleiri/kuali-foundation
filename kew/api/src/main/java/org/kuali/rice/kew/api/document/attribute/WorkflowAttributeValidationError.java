/*
 * Copyright 2005-2009, 2011 The Kuali Foundation
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
package org.kuali.rice.kew.api.document.attribute;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.CoreConstants;
import org.kuali.rice.core.api.mo.AbstractDataTransferObject;
import org.w3c.dom.Element;

/**
 * TODO...
 */
@XmlRootElement(name = WorkflowAttributeValidationError.Constants.ROOT_ELEMENT_NAME)
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = WorkflowAttributeValidationError.Constants.TYPE_NAME, propOrder = {
        WorkflowAttributeValidationError.Elements.KEY,
        WorkflowAttributeValidationError.Elements.MESSAGE,
        CoreConstants.CommonElements.FUTURE_ELEMENTS
})
public final class WorkflowAttributeValidationError extends AbstractDataTransferObject {

    private static final long serialVersionUID = 3323649177455266977L;

    @XmlElement(name = Elements.KEY, required = true)
    private final String key;
    
    @XmlElement(name = Elements.MESSAGE, required = true)
    private final String message;
    
    @SuppressWarnings("unused")
    @XmlAnyElement
    private final Collection<Element> _futureElements = null;

    /**
     * Private constructor used only by JAXB.
     */
    public WorkflowAttributeValidationError() {
    	this.key = null;
    	this.message = null;
    }
    
    private WorkflowAttributeValidationError(String key, String message) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("key was null or blank");
        }
        if (StringUtils.isBlank(message)) {
            throw new IllegalArgumentException("message was null or blank");
        }
        this.key = key;
        this.message = message;
    }
    
    public static WorkflowAttributeValidationError create(String key, String message) {
        return new WorkflowAttributeValidationError(key, message);
    }
    
    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Defines some internal constants used on this class.
     */
    static class Constants {
        final static String ROOT_ELEMENT_NAME = "workflowAttributeValidationError";
        final static String TYPE_NAME = "WorkflowAttributeValidationErrorType";
    }

    /**
     * A private class which exposes constants which define the XML element names to use when this
     * object is marshalled to XML.
     */
    static class Elements {
        final static String KEY = "key";
        final static String MESSAGE = "message";
    }

    
}
