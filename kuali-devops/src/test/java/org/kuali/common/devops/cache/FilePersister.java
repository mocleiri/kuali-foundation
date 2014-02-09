package org.kuali.common.devops.cache;

import static org.apache.commons.io.FileUtils.forceDelete;
import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.copyLarge;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public final class FilePersister<K, V> implements CachePersister<K, Optional<V>> {

	public static final String GLOBAL_MAGIC_ABSENT_STRING = "##--absent--##";

	public FilePersister(Function<K, File> fileFunction, Function<V, InputStream> inputStreamFunction) {
		this.fileFunction = checkNotNull(fileFunction, "fileFunction");
		this.inputStreamFunction = checkNotNull(inputStreamFunction, "inputStreamFunction");
	}

	private final Function<K, File> fileFunction;
	private final Function<V, InputStream> inputStreamFunction;

	@Override
	public void persist(K key, Optional<V> reference) {
		checkNotNull(key, "key");
		checkNotNull(reference, "reference");
		File file = this.fileFunction.apply(key);
		syncFileSystem(reference, file);
	}

	protected void syncFileSystem(Optional<V> reference, File file) {
		try {
			if (reference.isPresent()) {
				copy(file, reference, inputStreamFunction);
			} else {
				writeStringToFile(file, GLOBAL_MAGIC_ABSENT_STRING);
			}
		} catch (IOException e) {
			throw illegalState(e, "unexpected io error -> [%s]", file);
		}
	}

	protected void copy(File file, Optional<V> reference, Function<V, InputStream> inputStreamFunction) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = inputStreamFunction.apply(reference.get());
			out = openOutputStream(file);
			copyLarge(in, out);
		} finally {
			closeQuietly(in);
			closeQuietly(out);
		}
	}

	protected void forceDeleteIfExists(File file) throws IOException {
		if (file.exists()) {
			forceDelete(file);
		}
	}

	public Function<K, File> getFileFunction() {
		return fileFunction;
	}

	public Function<V, InputStream> getInputStreamFunction() {
		return inputStreamFunction;
	}

}
