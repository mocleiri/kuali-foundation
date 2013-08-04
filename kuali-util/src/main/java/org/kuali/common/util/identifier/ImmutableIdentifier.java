package org.kuali.common.util.identifier;

import org.kuali.common.util.Assert;

public final class ImmutableIdentifier implements Identifiable {

	private final String identifier;
	private final int hashCode;

	public ImmutableIdentifier(String identifier) {
		Assert.notNull(identifier, "identifier is null");
		this.identifier = identifier;
		this.hashCode = identifier.hashCode();
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		ImmutableIdentifier other = (ImmutableIdentifier) object;

		return hashCode == other.hashCode && identifier.equals(other.identifier);
	}

	@Override
	public String toString() {
		return identifier;
	}

}
