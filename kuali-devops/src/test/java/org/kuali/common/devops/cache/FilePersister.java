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

public final class FilePersister<K, V> implements CachePersister<K, Optional<V>, File> {

	public FilePersister(Function<K, File> fileFunction, Function<V, InputStream> inputStreamFunction) {
		this.fileFunction = checkNotNull(fileFunction, "fileFunction");
		this.inputStreamFunction = checkNotNull(inputStreamFunction, "inputStreamFunction");
	}

	private final Function<K, File> fileFunction;
	private final Function<V, InputStream> inputStreamFunction;

	@Override
	public File persist(K key, Optional<V> reference) throws IOException {
		checkNotNull(key);
		checkNotNull(reference);
		File file = fileFunction.apply(key);
		if (reference.isPresent()) {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = inputStreamFunction.apply(reference.get());
				out = FileUtils.openOutputStream(file);
				IOUtils.copy(in, out);
			} finally {
				IOUtils.closeQuietly(in);
				IOUtils.closeQuietly(out);
			}
		} else {
			if (file.exists()) {
				forceDelete(file);
			}
		}
		return file;
	}

}
