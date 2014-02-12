package org.kuali.common.devops.cache;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.File;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public class FileSystemCache<K, V> extends CacheLoader<K, V> {

	private final PersistentCache<File, V> fileCache;
	private final CacheLoader<K, V> loader;
	private final Function<K, File> keyConverter;
	private final boolean useFileSystemCache;

	@Override
	public V load(K key) throws Exception {
		checkNotNull(key, "key");
		if (useFileSystemCache) {
			File file = keyConverter.apply(key);
			if (file.exists()) {
				return fileCache.load(file);
			}
			V data = loader.load(key);
			fileCache.persist(file, data);
			return data;
		} else {
			return loader.load(key);
		}
	}

	public PersistentCache<File, V> getFileCache() {
		return fileCache;
	}

	public Function<K, File> getKeyConverter() {
		return keyConverter;
	}

	public boolean isUseFileSystemCache() {
		return useFileSystemCache;
	}

	private FileSystemCache(Builder<K, V> builder) {
		this.fileCache = builder.fileCache;
		this.loader = builder.loader;
		this.keyConverter = builder.keyConverter;
		this.useFileSystemCache = builder.useFileSystemCache;
	}

	public static <K, V> Builder<K, V> builder() {
		return new Builder<K, V>();
	}

	public static class Builder<K, V> extends ValidatingBuilder<FileSystemCache<K, V>> {

		private PersistentCache<File, V> fileCache;
		private CacheLoader<K, V> loader;
		private Function<K, File> keyConverter;
		private boolean useFileSystemCache;

		public Builder<K, V> fileCache(PersistentCache<File, V> fileCache) {
			this.fileCache = fileCache;
			return this;
		}

		public Builder<K, V> loader(CacheLoader<K, V> loader) {
			this.loader = loader;
			return this;
		}

		public Builder<K, V> keyConverter(Function<K, File> keyConverter) {
			this.keyConverter = keyConverter;
			return this;
		}

		public Builder<K, V> useFileSystemCache(boolean useFileSystemCache) {
			this.useFileSystemCache = useFileSystemCache;
			return this;
		}

		@Override
		public FileSystemCache<K, V> getInstance() {
			return new FileSystemCache<K, V>(this);
		}

		public PersistentCache<File, V> getFileCache() {
			return fileCache;
		}

		public void setFileCache(PersistentCache<File, V> fileCache) {
			this.fileCache = fileCache;
		}

		public CacheLoader<K, V> getLoader() {
			return loader;
		}

		public void setLoader(CacheLoader<K, V> loader) {
			this.loader = loader;
		}

		public Function<K, File> getKeyConverter() {
			return keyConverter;
		}

		public void setKeyConverter(Function<K, File> keyConverter) {
			this.keyConverter = keyConverter;
		}

		public boolean isUseFileSystemCache() {
			return useFileSystemCache;
		}

		public void setUseFileSystemCache(boolean useFileSystemCache) {
			this.useFileSystemCache = useFileSystemCache;
		}

	}

	public CacheLoader<K, V> getLoader() {
		return loader;
	}

}
