package org.kuali.common.util.identifier;

import org.springframework.util.Assert;

public class BasicIdentifiable implements Identifiable {

	private final ImmutableIdentifier identifier;

	public BasicIdentifiable(ImmutableIdentifier identifier) {
		Assert.notNull(identifier, "identifier is null");
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		return identifier.toString();
	}

	@Override
	public int hashCode() {
		return identifier.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof BasicIdentifiable) {
			BasicIdentifiable other = (BasicIdentifiable) object;
			return identifier.equals(other.identifier);
		} else {
			return false;
		}
	}

	@Override
	public String getIdentity() {
		return identifier.getIdentity();
	}
}
