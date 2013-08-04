package org.kuali.common.util.identify;

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

		// These are the exact same two physical objects
		if (this == object) {
			return true;
		}

		// Make sure object isn't null and is a BasicIdentifiable
		if (object == null || !(object instanceof BasicIdentifiable)) {
			return false;
		}

		// Cast to a BasicIdentifiable
		BasicIdentifiable other = (BasicIdentifiable) object;

		// The hash codes being the same AND the equals method returning true constitutes equality
		return identifier.hashCode() == other.identifier.hashCode() && identifier.equals(other.identifier);
	}

	@Override
	public String getIdentifier() {
		return identifier.getIdentifier();
	}
}
