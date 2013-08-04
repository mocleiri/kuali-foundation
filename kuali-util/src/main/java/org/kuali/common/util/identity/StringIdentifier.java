package org.kuali.common.util.identity;

import org.kuali.common.util.Assert;

public final class StringIdentifier implements Identifiable {

	private final String identifier;
	private final int hashCode;

	public StringIdentifier(String identifier) {

		// Can't be null, and can't be blank
		Assert.noBlanks("identifier is blank", identifier);

		// Store the string based identifier
		this.identifier = identifier;

		// Cache the hash code of the identifier string
		this.hashCode = identifier.hashCode();
	}

	@Override
	public String getIdentity() {
		return identifier;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object object) {

		// They are the exact same physical object
		if (this == object) {
			return true;
		}

		// Make sure other isn't null AND is the exact same runtime type
		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		// Cast to an StringIdentifier
		StringIdentifier other = (StringIdentifier) object;

		// The hash code's being equal AND the identifier strings being equal, constitutes equality
		return hashCode == other.hashCode && identifier.equals(other.identifier);
	}

	@Override
	public String toString() {
		return identifier;
	}

}
