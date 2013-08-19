package org.kuali.common.util.log.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

import org.kuali.common.util.Assert;

public final class AppenderRef {

	private final String ref;

	public AppenderRef(String ref) {
		Assert.noBlanks(ref);
		this.ref = ref;
	}

	@XmlAttribute
	public String getRef() {
		return ref;
	}

}
