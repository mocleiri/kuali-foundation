package org.kuali.common.util.xml.jaxb.adapter;

import java.util.Collections;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.xml.jaxb.wrapper.MapWrapper;

public class ImmutableMapAdapter<K, V> extends XmlAdapter<MapWrapper<K, V>, Map<K, V>> {

	private final Map<K, V> EMPTY_MAP = Collections.emptyMap();
	private final MapWrapper<K, V> EMPTY_WRAPPER = new MapWrapper<K, V>(EMPTY_MAP);

	@Override
	public MapWrapper<K, V> marshal(Map<K, V> map) {
		if (isEmpty(map)) {
			return EMPTY_WRAPPER;
		} else {
			return new MapWrapper<K, V>(map);
		}
	}

	@Override
	public Map<K, V> unmarshal(MapWrapper<K, V> wrapper) {
		if (isEmpty(wrapper.getMap())) {
			return EMPTY_MAP;
		} else {
			return Collections.unmodifiableMap(wrapper.getMap());
		}
	}

	protected static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.size() == 0;
	}

}
