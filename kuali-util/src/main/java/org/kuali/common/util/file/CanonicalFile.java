package org.kuali.common.util.file;

import java.io.File;
import java.io.IOException;

/**
 * A <code>CanonicalFile</code> is always both absolute and unique.
 */
public final class CanonicalFile extends File {

	private static final long serialVersionUID = -8366640724070158688L;

	public CanonicalFile(String path) {
		this(new File(path));
	}

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
