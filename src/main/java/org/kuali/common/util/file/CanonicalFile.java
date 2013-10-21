package org.kuali.common.util.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * A <code>CanonicalFile</code> is always both absolute and unique.
 */
public class CanonicalFile extends File {

	private static final long serialVersionUID = -8366640724070158688L;

	/**
	 * A <code>CanonicalFile</code> is always both absolute and unique.
	 */
	public CanonicalFile(File parent, String child) {
		this(new File(parent, child));
	}

	/**
	 * A <code>CanonicalFile</code> is always both absolute and unique.
	 */
	public CanonicalFile(String parent, String child) {
		this(new File(parent, child));
	}

	/**
	 * A <code>CanonicalFile</code> is always both absolute and unique.
	 */
	public CanonicalFile(URI uri) {
		this(new File(uri));
	}

	/**
	 * A <code>CanonicalFile</code> is always both absolute and unique.
	 */
	public CanonicalFile(String path) {
		this(new File(path));
	}

	/**
	 * A <code>CanonicalFile</code> is always both absolute and unique.
	 */
	public CanonicalFile(File file) {
		super(getCanonicalPath(file));
	}

	protected static String getCanonicalPath(File file) {
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

}
