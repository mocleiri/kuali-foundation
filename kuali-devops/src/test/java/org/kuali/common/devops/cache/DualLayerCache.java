package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.kuali.common.util.Encodings.UTF8;

import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public final class DualLayerCache<T> extends CacheLoader<T, Optional<String>> {

	private final FileLoader fileLoader = new FileLoader(UTF8);

	@Override
	public Optional<String> load(T key) {
		checkNotNull(key);
		return null;
	}

}
