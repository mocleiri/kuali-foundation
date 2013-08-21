package org.kuali.common.util.log.log4j.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

public final class Param {

	public static final List<Param> EMPTY = Collections.<Param> emptyList();

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final String value;

	@SuppressWarnings("unused")
	private Param() {
		this(NullUtils.NONE, NullUtils.NONE);
	}

	public Param(String name, String value) {
		Assert.noBlanks(name, value);
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}
