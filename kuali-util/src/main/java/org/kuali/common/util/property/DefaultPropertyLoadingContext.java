package org.kuali.common.util.property;

import java.util.List;

public class DefaultPropertyLoadingContext extends DefaultPropertyHandlingContext implements PropertyLoadingContext {

	List<String> locations;
	boolean ignoreMissingLocations;
	boolean decrypt;
	PropertyMatcher encryptedPropertyMatcher = new EndsWithPropertyMatcher();

	@Override
	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	@Override
	public boolean isIgnoreMissingLocations() {
		return ignoreMissingLocations;
	}

	public void setIgnoreMissingLocations(boolean ignoreMissingLocations) {
		this.ignoreMissingLocations = ignoreMissingLocations;
	}

	@Override
    public boolean isDecrypt() {
		return decrypt;
	}

	public void setDecrypt(boolean decrypt) {
		this.decrypt = decrypt;
	}

	@Override
    public PropertyMatcher getEncryptedPropertyMatcher() {
		return encryptedPropertyMatcher;
	}

	public void setEncryptedPropertyMatcher(PropertyMatcher encryptedPropertyMatcher) {
		this.encryptedPropertyMatcher = encryptedPropertyMatcher;
	}
}
