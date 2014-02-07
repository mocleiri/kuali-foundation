package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.io.FileUtils.forceDelete;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public final class FilePersister<K, V> implements CachePersister<K, Optional<V>> {

	public FilePersister(Function<K, File> file, Function<V, InputStream> in) {
		this.file = checkNotNull(file, "file");
		this.in = checkNotNull(in, "in");
	}

	private final Function<K, File> file;
	private final Function<V, InputStream> in;

	@Override
	public void persist(K key, Optional<V> reference) throws IOException {
		checkNotNull(key);
		checkNotNull(reference);
		File file = this.file.apply(key);
		if (reference.isPresent()) {
			copy(file, reference);
		} else {
			forceDeleteIfExists(file);
		}
	}

	protected void copy(File file, Optional<V> reference) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = this.in.apply(reference.get());
			out = FileUtils.openOutputStream(file);
			IOUtils.copyLarge(in, out);
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}

	protected void forceDeleteIfExists(File file) throws IOException {
		if (file.exists()) {
			forceDelete(file);
		}
	}

}
