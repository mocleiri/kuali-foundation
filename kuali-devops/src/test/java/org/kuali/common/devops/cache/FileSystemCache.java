package org.kuali.common.devops.cache;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.File;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.core.build.ViolationsBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public final class FileSystemCache<K, V> extends CacheLoader<K, V> {

	private final PersistentCache<File, V> fileCache;
	private final CacheLoader<K, V> loader;
	private final Function<K, File> keyConverter;
	private final boolean ignoreFileSystem;

	@Override
	public V load(K key) throws Exception {
		checkNotNull(key, "key");

		// If we are ignoring the file system, just query the main cache
		if (ignoreFileSystem) {
			return loader.load(key);
		}

		// Otherwise check the file system first
		// Convert the key into a filename
		File file = keyConverter.apply(key);

		// If the file exists, load the value from there
		if (file.exists()) {
			return fileCache.load(file);
		}

		// Query the main cache
		V value = loader.load(key);

		// Store the value to the file system
		fileCache.store(file, value);

		// Return the value
		return value;
	}

	public PersistentCache<File, V> getFileCache() {
		return fileCache;
	}

	public Function<K, File> getKeyConverter() {
		return keyConverter;
	}

	public boolean isIgnoreFileSystem() {
		return ignoreFileSystem;
	}

	private FileSystemCache(Builder<K, V> builder) {
		this.fileCache = builder.fileCache;
		this.loader = builder.loader;
		this.keyConverter = builder.keyConverter;
		this.ignoreFileSystem = builder.ignoreFileSystem;
	}

	public static <K, V> Builder<K, V> builder() {
		return new Builder<K, V>();
	}

	public static class Builder<K, V> extends ViolationsBuilder<FileSystemCache<K, V>> {

		private PersistentCache<File, V> fileCache;
		private CacheLoader<K, V> loader;
		private Function<K, File> keyConverter;
		private boolean ignoreFileSystem;

		private FileSystemCache<K, V> make() {
			return new FileSystemCache<K, V>(this);
		}

		@Override
		public Set<ConstraintViolation<FileSystemCache<K, V>>> violations() {
			return violations(make());
		}

		@Override
		public FileSystemCache<K, V> build() {
			return validate(make());
		}

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

		public Builder<K, V> ignoreFileSystem(boolean ignoreFileSystem) {
			this.ignoreFileSystem = ignoreFileSystem;
			return this;
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

		public boolean isIgnoreFileSystem() {
			return ignoreFileSystem;
		}

		public void setIgnoreFileSystem(boolean ignoreFileSystem) {
			this.ignoreFileSystem = ignoreFileSystem;
		}

	}

	public CacheLoader<K, V> getLoader() {
		return loader;
	}

}
