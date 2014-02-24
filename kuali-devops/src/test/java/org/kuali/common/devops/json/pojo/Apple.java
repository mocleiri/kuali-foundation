package org.kuali.common.devops.json.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Apple {

	@JsonCreator
	public Apple(@JsonProperty("color") String color) {
		this.color = color;
	}

	String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
