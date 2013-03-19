package org.kuali.common.jdbc.supplier;

/**
 * @author andrewlubbers
 */
public interface LocationExtensionSupplierBuilder {

    String getExtension();

    LocationSupplier buildSupplier(String location);

}
