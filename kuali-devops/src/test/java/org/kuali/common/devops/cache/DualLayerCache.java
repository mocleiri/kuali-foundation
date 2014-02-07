package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.codehaus.plexus.util.FileUtils.forceDelete;
import static org.kuali.common.util.Encodings.UTF8;

import java.io.File;

import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.validate.IdiotProofImmutable;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public abstract class DualLayerCache<T> extends CacheLoader<T, Optional<String>> implements CacheWriter<T, Optional<String>> {

	private final FileLoader fileLoader = new FileLoader(UTF8);
	private final CacheLoader<T, Optional<String>> loader = null;

	protected abstract File getFile(T key);

	private static final Logger logger = Loggers.make();

	@Override
	public Optional<String> load(T key) throws Exception {
		checkNotNull(key);
		File file = getFile(key);
		Optional<String> data = fileLoader.load(file);
		if (data.isPresent()) {
			return data;
		}
		data = loader.load(key);
		if (data.isPresent()) {
			logger.info(String.format("creating -> %s", file));
			writeStringToFile(file, data.get(), UTF8);
		} else {
			if (file.exists()) {
				forceDelete(file);
			}
		}
		return data;
	}

}
