package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.kuali.common.util.Encodings.UTF8;

import java.io.File;

import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public abstract class DualLayerCache<T> extends CacheLoader<T, Optional<String>> {

	public DualLayerCache(CacheLoader<T, Optional<String>> loader) {
		this.loader = checkNotNull(loader);
	}

	private final FileLoader fileLoader = new FileLoader(UTF8);
	private final FileWriter fileWriter = new FileWriter(UTF8);
	private final CacheLoader<T, Optional<String>> loader;

	protected abstract File getFile(T key);

	@Override
	public Optional<String> load(T key) throws Exception {
		checkNotNull(key);
		File file = getFile(key);
		Optional<String> data = fileLoader.load(file);
		if (!data.isPresent()) {
			data = loader.load(key);
			fileWriter.write(file, data);
		}
		return data;
	}

}
