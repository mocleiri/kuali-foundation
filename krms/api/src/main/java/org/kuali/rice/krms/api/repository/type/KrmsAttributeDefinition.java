package org.kuali.rice.krms.api.repository.type;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.kuali.rice.core.api.CoreConstants;
import org.kuali.rice.core.api.mo.ModelBuilder;
import org.kuali.rice.core.api.mo.ModelObjectComplete;

/**
 * Concrete model object implementation of KRMS KrmsAttributeDefinition. 
 * immutable. 
 * Instances of KrmsAttributeDefinition can be (un)marshalled to and from XML.
 *
 * @see KrmsAttributeDefinitionContract
 */
@XmlRootElement(name = KrmsAttributeDefinition.Constants.ROOT_ELEMENT_NAME)
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = KrmsAttributeDefinition.Constants.TYPE_NAME, propOrder = {
		KrmsAttributeDefinition.Elements.ID,
		KrmsAttributeDefinition.Elements.NAME,
		KrmsAttributeDefinition.Elements.NAMESPACE,
		KrmsAttributeDefinition.Elements.LABEL,
		KrmsAttributeDefinition.Elements.ACTIVE,
		KrmsAttributeDefinition.Elements.COMPONENT_NAME,
		CoreConstants.CommonElements.FUTURE_ELEMENTS
})
public final class KrmsAttributeDefinition implements KrmsAttributeDefinitionContract, ModelObjectComplete{
	private static final long serialVersionUID = -6356968810972165031L;
	
	@XmlElement(name = Elements.ID, required=true)
	private String id;
	@XmlElement(name = Elements.NAME, required=true)
	private String name;
	@XmlElement(name = Elements.NAMESPACE, required=true)
	private String namespace;
	@XmlElement(name = Elements.LABEL, required=false)
	private String label;
	@XmlElement(name = Elements.ACTIVE, required=false)
	private boolean active;
	@XmlElement(name = Elements.COMPONENT_NAME, required=false)
	private String componentName;
	
	@SuppressWarnings("unused")
    @XmlAnyElement
    private final Collection<org.w3c.dom.Element> _futureElements = null;
	
	 /** 
     * This constructor should never be called.  It is only present for use during JAXB unmarshalling. 
     */
    private KrmsAttributeDefinition() {
    	this.id = null;
    	this.name = null;
    	this.namespace = null;
    	this.label = null;
    	this.active = false;
    	this.componentName = null;
    }
    
    /**
	 * Constructs a KrmsAttributeDefinition from the given builder.  This constructor is private and should only
	 * ever be invoked from the builder.
	 * 
	 * @param builder the Builder from which to construct the KrmsAttributeDefinition
	 */
    private KrmsAttributeDefinition(Builder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.namespace = builder.getNamespace();
        this.label = builder.getLabel();
        this.active = builder.isActive();
        this.componentName = builder.getComponentName();
    }
    
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public String getNamespace() {
		return this.namespace;
	}

	public String getLabel() {
		return this.label;
	}
	
	public boolean isActive() {
		return this.active; 
	}

	public String getComponentName() {
		return this.componentName;
	}
	
	/**
     * This builder is used to construct instances of KrmsAttributeDefinition.  It enforces the constraints of the {@link KrmsAttributeDefinitionContract}.
     */
    public static class Builder implements KrmsAttributeDefinitionContract, ModelBuilder, Serializable {		
		private static final long serialVersionUID = -2110564370088779631L;
		
		private String id;
        private String name;
        private String namespace;
        private String label;
        private boolean active;
        private String componentName;

		/**
		 * Private constructor for creating a builder with all of it's required attributes.
		 */
        private Builder(String id, String name, String namespace) {
            setId(id);
            setName(name);
            setNamespace(namespace);
			setActive(true);
        }

        public Builder label(String label){
        	setLabel(label);
        	return this;
        }
        public Builder componentName(String componentName){
        	setComponentName(componentName);
        	return this;
        }
        /**
         * Creates a builder from the given parameters.
         * 
         * @param id the KrmsAttributeDefinition id
         * @param name the KrmsAttributeDefinition name
         * @param namespace the KrmsAttributeDefinition namespace
         * @return an instance of the builder with the fields already populated
         * @throws IllegalArgumentException if the either the id, name or namespace is null or blank
         */
        public static Builder create(String id, String name, String namespace) {
            return new Builder(id, name, namespace);
        }

        /**
         * Creates a builder by populating it with data from the given {@link KrmsAttributeDefinitionContract}.
         * 
         * @param contract the contract from which to populate this builder
         * @return an instance of the builder populated with data from the contract
         */
        public static Builder create(KrmsAttributeDefinitionContract contract) {
        	if (contract == null) {
                throw new IllegalArgumentException("contract is null");
            }
            Builder builder =  new Builder(contract.getId(), contract.getName(), contract.getNamespace());
            builder.setActive(contract.isActive());
            builder.setLabel(contract.getLabel());
            builder.setComponentName(contract.getComponentName());
            return builder;
        }

		/**
		 * Sets the value of the id on this builder to the given value.
		 * 
		 * @param id the id value to set, must not be null or blank
		 * @throws IllegalArgumentException if the id is null or blank
		 */
        public void setId(String id) {
            if (StringUtils.isBlank(id)) {
                throw new IllegalArgumentException("id is blank");
            }
            this.id = id;
        }

		public void setName(String name) {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("name is blank");
            }
			this.name = name;
		}

		public void setNamespace(String namespace) {
            if (StringUtils.isBlank(namespace)) {
                throw new IllegalArgumentException("namespace is blank");
            }
			this.namespace = namespace;
		}
		
		public void setLabel(String label) {
			this.label = label;
		}
		
		public void setComponentName(String componentName) {
			this.componentName = componentName;
		}
		
		public void setActive(boolean active) {
			this.active = active;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getNamespace() {
			return namespace;
		}

		public String getComponentName() {
			return componentName;
		}

		public String getLabel() {
			return label;
		}

		public boolean isActive() {
			return active;
		}

		/**
		 * Builds an instance of a CampusType based on the current state of the builder.
		 * 
		 * @return the fully-constructed CampusType
		 */
        @Override
        public KrmsAttributeDefinition build() {
            return new KrmsAttributeDefinition(this);
        }
		
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
		final static String ROOT_ELEMENT_NAME = "KrmsAttributeDefinition";
		final static String TYPE_NAME = "KrmsAttributionDefinitionType";
		final static String[] HASH_CODE_EQUALS_EXCLUDE = { CoreConstants.CommonElements.FUTURE_ELEMENTS };
	}
	
	/**
	 * A private class which exposes constants which define the XML element names to use
	 * when this object is marshalled to XML.
	 */
	public static class Elements {
		final static String ID = "id";
		final static String NAME = "name";
		final static String NAMESPACE = "namespace";
		final static String LABEL = "label";
		final static String COMPONENT_NAME = "componentName";
		final static String ACTIVE = "active";
	}
}
