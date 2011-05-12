package org.kuali.rice.ksb.api.registry;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.kuali.rice.core.api.CoreConstants;
import org.kuali.rice.core.api.mo.ModelBuilder;
import org.kuali.rice.core.api.mo.ModelObjectComplete;
import org.w3c.dom.Element;

@XmlRootElement(name = ServiceEndpoint.Constants.ROOT_ELEMENT_NAME)
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = ServiceEndpoint.Constants.TYPE_NAME, propOrder = {
		ServiceEndpoint.Elements.ID,
		ServiceEndpoint.Elements.HEADER,
		ServiceEndpoint.Elements.DESCRIPTOR,
		CoreConstants.CommonElements.FUTURE_ELEMENTS
})
public final class ServiceEndpoint implements ModelObjectComplete, ServiceEndpointContract {

	@XmlElement(name = Elements.ID, required = false)
    private final Long id;
    
	@XmlElement(name = Elements.HEADER, required = false)
    private final ServiceHeader header;
	
    @XmlElement(name = Elements.DESCRIPTOR, required = false)
    private final ServiceDescriptor descriptor;
    
    @SuppressWarnings("unused")
    @XmlAnyElement
    private final Collection<Element> _futureElements = null;

    /**
     * Private constructor used only by JAXB.
     */
    private ServiceEndpoint() {
    	this.id = null;
        this.header = null;
    	this.descriptor = null;
    }
    
    private ServiceEndpoint(Builder builder) {
    	this.id = builder.getId();
        this.header = builder.getHeader().build();
        this.descriptor = builder.getDescriptor().build();
    }
    
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public ServiceHeader getHeader() {
        return this.header;
    }
    
    @Override
    public ServiceDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, Constants.HASH_CODE_EQUALS_EXCLUDE);
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(object, this, Constants.HASH_CODE_EQUALS_EXCLUDE);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * A builder which can be used to construct {@link ServiceEndpoint} instances.  Enforces the constraints of the {@link ServiceEndpointContract}.
     */
    public final static class Builder implements Serializable, ModelBuilder, ServiceEndpointContract {

    	private Long id;
        private ServiceHeader.Builder header;
        private ServiceDescriptor.Builder descriptor;

        private Builder() {
            // TODO modify this constructor as needed to pass any required values and invoke the appropriate 'setter' methods
        }

        public static Builder create() {
            // TODO modify as needed to pass any required values and add them to the signature of the 'create' method
            return new Builder();
        }

        public static Builder create(ServiceEndpointContract contract) {
            if (contract == null) {
                throw new IllegalArgumentException("contract was null");
            }
            // TODO if create() is modified to accept required parameters, this will need to be modified
            Builder builder = create();
            builder.setId(contract.getId());
            builder.setHeader(ServiceHeader.Builder.create(contract.getHeader()));
            builder.setDescriptor(ServiceDescriptor.Builder.create(contract.getDescriptor()));
            return builder;
        }

        public ServiceEndpoint build() {
            return new ServiceEndpoint(this);
        }

        @Override
        public Long getId() {
            return this.id;
        }

        @Override
        public ServiceHeader.Builder getHeader() {
            return this.header;
        }
        
        @Override
        public ServiceDescriptor.Builder getDescriptor() {
            return this.descriptor;
        }
        
        public void setId(Long id) {
            // TODO add validation of input value if required and throw IllegalArgumentException if needed
            this.id = id;
        }

        public void setHeader(ServiceHeader.Builder header) {
            // TODO add validation of input value if required and throw IllegalArgumentException if needed
            this.header = header;
        }

        public void setDescriptor(ServiceDescriptor.Builder descriptor) {
            // TODO add validation of input value if required and throw IllegalArgumentException if needed
            this.descriptor = descriptor;
        }
        
    }


    /**
     * Defines some internal constants used on this class.
     */
    static class Constants {

        final static String ROOT_ELEMENT_NAME = "serviceEndpoint";
        final static String TYPE_NAME = "ServiceEndpointType";
        final static String[] HASH_CODE_EQUALS_EXCLUDE = new String[] {CoreConstants.CommonElements.FUTURE_ELEMENTS };

    }


    /**
     * A private class which exposes constants which define the XML element names to use when this object is marshalled to XML.
     */
    static class Elements {

        final static String ID = "id";
        final static String HEADER = "header";
        final static String DESCRIPTOR = "descriptor";

    }
    
}
