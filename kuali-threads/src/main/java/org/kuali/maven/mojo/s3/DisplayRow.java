package org.kuali.maven.mojo.s3;

/**
 * Pojo that represents one row in the directory listing of the contents of a directory in an S3 bucket
 */
public class DisplayRow implements Comparable<DisplayRow> {
	String show;
	String image;
	String ahref;
	String lastModified;
	String size;

	@Override
	public int compareTo(DisplayRow other) {
		if (show == null && other.getShow() == null) {
			return 0;
		}
		if (show == null) {
			return -1;
		}
		if (other.getShow() == null) {
			return -1;
		}
		return show.compareTo(other.getShow());
	}

	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

	public String getAhref() {
		return ahref;
	}

	public void setAhref(final String ahref) {
		this.ahref = ahref;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(final String date) {
		this.lastModified = date;
	}

	public String getSize() {
		return size;
	}

	public void setSize(final String size) {
		this.size = size;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

}
