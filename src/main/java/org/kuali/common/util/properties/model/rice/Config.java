package org.kuali.common.util.properties.model.rice;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {

	@SuppressWarnings("unused")
	private Config() {
		this(ImmutableList.<Param> of());
	}

	public Config(List<Param> params) {
		Assert.noNulls(params);
		this.params = ImmutableList.copyOf(params);
	}

	@XmlElement(name = "param")
	private final List<Param> params;

	public List<Param> getParams() {
		return params;
	}

}
