package org.kuali.common.devops.json.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Basket {

	@JsonCreator
	public Basket(@JsonProperty("material") String material, @JsonProperty("apples") List<Apple> apples) {
		this.material = material;
		this.apples = apples;
	}

	String material;
	List<Apple> apples;

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public List<Apple> getApples() {
		return apples;
	}

	public void setApples(List<Apple> apples) {
		this.apples = apples;
	}

}
