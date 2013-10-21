package org.kuali.common.util.pojo;

import java.util.Map;

public class Instance implements Comparable<Instance> {

	int startIndex;
	int stopIndex;
	String id;
	String name = "-";
	String size = "-";
	String state;
	Map<String, String> tags;
	double monthlyCost;

	@Override
	public int compareTo(Instance other) {
		String compare1 = state + ":" + name;
		String compare2 = other.getState() + ":" + other.getName();
		return compare1.compareTo(compare2);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Map<String, String> getTags() {
		return tags;
	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getStopIndex() {
		return stopIndex;
	}

	public void setStopIndex(int stopIndex) {
		this.stopIndex = stopIndex;
	}

	public double getMonthlyCost() {
		return monthlyCost;
	}

	public void setMonthlyCost(double monthlyCost) {
		this.monthlyCost = monthlyCost;
	}

}
