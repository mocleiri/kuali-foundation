package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.kuali.common.util.Encodings.UTF8;

import java.io.File;

import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public abstract class FileCache<T> extends CacheLoader<T, Optional<String>> {

	public FileCache(CacheLoader<T, Optional<String>> loader, Function<T, File> function) {
		this.loader = checkNotNull(loader);
		this.function = checkNotNull(function);
	}

	private final CacheLoader<File, Optional<String>> fileLoader = new FileLoader(UTF8);
	private final CachePersister<File, Optional<String>> filePersister = new FilePersister(UTF8);

	private final CacheLoader<T, Optional<String>> loader;
	private final Function<T, File> function;

	@Override
	public Optional<String> load(T key) throws Exception {
		checkNotNull(key);
		File file = function.apply(key);
		Optional<String> data = fileLoader.load(file);
		if (!data.isPresent()) {
			data = loader.load(key);
			filePersister.persist(file, data);
		}
		return data;
	}

}
