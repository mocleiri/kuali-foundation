package org.kuali.common.util.property;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.Assert;

public final class ImmutableProperties extends Properties {

	private static final long serialVersionUID = -3964884087103719367L;
	private static final String UOE_MSG = "Immutable properties cannot be changed";

	public ImmutableProperties(Properties original) {

		// original can't be null
		Assert.notNull(original, "original is null");

		// Make sure they don't change original on us while we are setting things up
		synchronized (original) {

			// Make sure original contains only string values
			Assert.isTrue(original.stringPropertyNames().size() == original.size(), "Immutable properties only support string property values");

			// Copy original
			super.putAll(original);

		}
	}

	@Override
	public void load(Reader reader) throws IOException {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void load(InputStream inStream) throws IOException {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public Object put(Object key, Object value) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public Object remove(Object key) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void putAll(Map<? extends Object, ? extends Object> t) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException(UOE_MSG);
	}

}
