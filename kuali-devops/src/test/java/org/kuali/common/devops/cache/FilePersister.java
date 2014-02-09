package org.kuali.common.devops.cache;

import static java.lang.String.format;
import static org.apache.commons.io.FileUtils.forceDelete;
import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.FileUtils.touch;
import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.copyLarge;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public final class FilePersister<K, V> implements CachePersister<K, Optional<V>> {

	public static final String GLOBAL_MAGIC_ABSENT_SUFFIX = ".absent";
	private static final Logger logger = Loggers.make();

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
				logger.info(format("creating -> %s", file));
				copy(file, reference, inputStreamFunction);
			} else {
				File absentFile = new CanonicalFile(file.getCanonicalFile() + GLOBAL_MAGIC_ABSENT_SUFFIX);
				logger.info(format("creating -> %s", absentFile));
				touch(absentFile);
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
