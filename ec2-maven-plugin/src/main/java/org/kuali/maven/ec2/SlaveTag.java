package org.kuali.maven.ec2;
import com.amazonaws.services.ec2.model.Tag;


public class SlaveTag implements Comparable<SlaveTag> {
	String label;
	String date;
	int sequence;
	String imageId;
	Tag tag;
	String snapshot;

	public int compareTo(SlaveTag other) {
		Integer one = this.getSequence();
		Integer two = other.getSequence();
		return one.compareTo(two);
	}

	public String getLabel() {
		return label;
	}
	
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getSnapshot() {
		return snapshot;
	}
	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}
}
