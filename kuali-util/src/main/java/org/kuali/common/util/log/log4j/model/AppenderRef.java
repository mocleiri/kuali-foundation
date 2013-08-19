package org.kuali.common.util.log.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

public final class AppenderRef {

	@XmlAttribute
	private final String ref;

	@SuppressWarnings("unused")
	private AppenderRef() {
		this(NullUtils.NONE);
	}

	public AppenderRef(String ref) {
		Assert.noBlanks(ref);
		this.ref = ref;
	}

	public String getRef() {
		return ref;
	}

}
