package org.kuali.common.util.log.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

public final class AppenderRef {

	public static final String DEFAULT_REF = NullUtils.NONE;

	@XmlAttribute
	private final String ref;

	@SuppressWarnings("unused")
	private AppenderRef() {
		this(DEFAULT_REF);
	}

	public AppenderRef(String ref) {
		Assert.noBlanks(ref);
		this.ref = ref;
	}

	public String getRef() {
		return ref;
	}

}
