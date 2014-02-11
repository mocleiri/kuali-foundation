package org.kuali.common.devops.cache;

import static org.kuali.common.devops.cache.FilePersister.GLOBAL_MAGIC_ABSENT_SUFFIX;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.File;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public final class PersistToFileSystemLoader<T, V> extends CacheLoader<T, Optional<V>> {

	private final CacheLoader<File, Optional<V>> fileLoader;
	private final CachePersister<File, Optional<V>> filePersister;
	private final CacheLoader<T, Optional<V>> loader;
	private final Function<T, File> fileFunction;

	@Override
	public Optional<V> load(T key) throws Exception {
		checkNotNull(key, "key");
		File file = fileFunction.apply(key);
		File absentFile = new CanonicalFile(file + GLOBAL_MAGIC_ABSENT_SUFFIX);
		if (absentFile.exists()) {
			return Optional.<V> absent();
		}
		Optional<V> data = fileLoader.load(file);
		if (!data.isPresent()) {
			data = loader.load(key);
			filePersister.persist(file, data);
		}
		return data;
	}

	private PersistToFileSystemLoader(Builder<T, V> builder) {
		this.fileLoader = builder.fileLoader;
		this.filePersister = builder.filePersister;
		this.loader = builder.loader;
		this.fileFunction = builder.fileFunction;
	}

	public static <T, V> Builder<T, V> builder() {
		return new Builder<T, V>();
	}

	public static class Builder<T, V> extends ValidatingBuilder<PersistToFileSystemLoader<T, V>> {

		private CacheLoader<File, Optional<V>> fileLoader;
		private CachePersister<File, Optional<V>> filePersister;
		private CacheLoader<T, Optional<V>> loader;
		private Function<T, File> fileFunction;

		public Builder<T, V> fileLoader(CacheLoader<File, Optional<V>> fileLoader) {
			this.fileLoader = fileLoader;
			return this;
		}

		public Builder<T, V> filePersister(CachePersister<File, Optional<V>> filePersister) {
			this.filePersister = filePersister;
			return this;
		}

		public Builder<T, V> loader(CacheLoader<T, Optional<V>> loader) {
			this.loader = loader;
			return this;
		}

		public Builder<T, V> fileFunction(Function<T, File> fileFunction) {
			this.fileFunction = fileFunction;
			return this;
		}

		@Override
		public PersistToFileSystemLoader<T, V> getInstance() {
			return new PersistToFileSystemLoader<T, V>(this);
		}

		public CacheLoader<File, Optional<V>> getFileLoader() {
			return fileLoader;
		}

		public void setFileLoader(CacheLoader<File, Optional<V>> fileLoader) {
			this.fileLoader = fileLoader;
		}

		public CachePersister<File, Optional<V>> getFilePersister() {
			return filePersister;
		}

		public void setFilePersister(CachePersister<File, Optional<V>> filePersister) {
			this.filePersister = filePersister;
		}

		public CacheLoader<T, Optional<V>> getLoader() {
			return loader;
		}

		public void setLoader(CacheLoader<T, Optional<V>> loader) {
			this.loader = loader;
		}

		public Function<T, File> getFileFunction() {
			return fileFunction;
		}

		public void setFileFunction(Function<T, File> function) {
			this.fileFunction = function;
		}

	}

	public CacheLoader<File, Optional<V>> getFileLoader() {
		return fileLoader;
	}

	public CachePersister<File, Optional<V>> getFilePersister() {
		return filePersister;
	}

	public CacheLoader<T, Optional<V>> getLoader() {
		return loader;
	}

	public Function<T, File> getFileFunction() {
		return fileFunction;
	}

}
