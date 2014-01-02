package org.kuali.common.util.properties.model.rice;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.ImmutableList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {

	@XmlElement(name = "param")
	private List<Param> params;

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = ImmutableList.copyOf(params);
	}

}
