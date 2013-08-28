/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.property;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ImmutableProperties extends Properties {

	private static final long serialVersionUID = -3964884087103719367L;
	private static final String UOE_MSG = "Immutable properties cannot be changed";

	public ImmutableProperties(Properties original) {

		// original can't be null
		if (original == null) {
			throw new NullPointerException("original is null");
		}

		// Prevent anything from changing original until we are done
		synchronized (original) {

			// Extract only those keys where both the key and its corresponding value are strings
			Set<String> keys = original.stringPropertyNames();

			// If the sizes are different, original contains at least one key or value that is not a string
			if (keys.size() != original.size()) {
				throw new IllegalArgumentException("Immutable properties only support strings");
			}

			// Copy every key/value pair from original - can't use putAll() since it calls put() which is now disabled
			for (String key : keys) {
				super.put(key, original.getProperty(key));
			}
		}
	}

	@Override
	public Object setProperty(String key, String value) {
		throw new UnsupportedOperationException(UOE_MSG);
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

	@Override
	public Set<Object> keySet() {
		return Collections.unmodifiableSet(super.keySet());
	}

	@Override
	public Set<java.util.Map.Entry<Object, Object>> entrySet() {
		return Collections.unmodifiableSet(super.entrySet());
	}

	@Override
	public Collection<Object> values() {
		return Collections.unmodifiableCollection(super.values());
	}

}
