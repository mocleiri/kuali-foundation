package org.kuali.common.devops.cache;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

public final class FileLoader<V> extends CacheLoader<File, Optional<V>> {

	public FileLoader(Function<File, Optional<V>> function) {
		this.function = checkNotNull(function, "function");
	}

	private final Function<File, Optional<V>> function;

	@Override
	public Optional<V> load(File file) throws IOException {
		checkNotNull(file);
		if (file.exists()) {
			Optional<V> reference = function.apply(file);
			return reference;
		} else {
			return absent();
		}
	}

	public Function<File, Optional<V>> getFunction() {
		return function;
	}

}
