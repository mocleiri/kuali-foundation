package org.kuali.common.util.xml.jaxb.wrapper;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyElement;

import org.kuali.common.util.Assert;

public class MapWrapper<K, V> {

	@XmlAnyElement(lax = true)
	private final Map<K, V> map;

	@SuppressWarnings("unused")
	private MapWrapper() {
		this(new HashMap<K, V>());
	}

	public MapWrapper(Map<K, V> map) {
		Assert.noNulls(map);
		this.map = map;
	}

	public Map<K, V> getMap() {
		return map;
	}

}
