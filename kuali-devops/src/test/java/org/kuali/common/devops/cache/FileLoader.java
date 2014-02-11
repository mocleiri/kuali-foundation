package org.kuali.common.devops.cache;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

public final class FileLoader<V> extends CacheLoader<File, Optional<V>> {

	public FileLoader(Function<File, V> function) {
		this.function = checkNotNull(function, "function");
	}

	private final Function<File, V> function;

	@Override
	public Optional<V> load(File file) throws IOException {
		checkNotNull(file, "file");
		if (file.exists()) {
			V reference = function.apply(file);
			return Optional.of(reference);
		} else {
			return absent();
		}
	}

	public Function<File, V> getFunction() {
		return function;
	}

}
