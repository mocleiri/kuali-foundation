package org.apache.torque.engine.platform;

public class DatabaseObjectInformation implements Comparable<DatabaseObjectInformation> {

	protected String name;
	protected String type;
	protected String comment;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int compareTo(DatabaseObjectInformation other) {
		return name.compareTo(other.name);
	}
}
