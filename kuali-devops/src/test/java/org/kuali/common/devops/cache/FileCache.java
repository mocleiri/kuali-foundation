package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.InputStream;

import org.kuali.common.http.model.HttpContext;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public final class FileCache<T, V> extends CacheLoader<T, Optional<V>> {

	private final CacheLoader<File, Optional<V>> fileLoader;
	private final CachePersister<File, Optional<V>> filePersister;
	private final CacheLoader<T, Optional<V>> loader;
	private final Function<T, File> function;

	@Override
	public Optional<V> load(T key) throws Exception {
		checkNotNull(key);
		File file = function.apply(key);
		Optional<V> data = fileLoader.load(file);
		if (!data.isPresent()) {
			data = loader.load(key);
			filePersister.persist(file, data);
		}
		return data;
	}

	private FileCache(Builder<T, V> builder) {
		this.fileLoader = builder.fileLoader;
		this.filePersister = builder.filePersister;
		this.loader = builder.loader;
		this.function = builder.function;
	}

	public static <T, V> FileCache<String, String> createHttpUrlCacher() {
		return createHttpUrlCacher(HttpContext.create());
	}

	public static <T, V> FileCache<String, String> createHttpUrlCacher(HttpContext context) {
		return createHttpUrlCacher(context, Encodings.UTF8, new CanonicalFile("./target/cache"));
	}

	public static <T, V> FileCache<String, String> createHttpUrlCacher(HttpContext context, String encoding, File basedir) {
		CacheLoader<String, Optional<String>> loader = HttpLoader.create(context);
		CacheLoader<File, Optional<String>> fileLoader = new FileLoader<String>(new ReadFileToStringFunction(encoding));
		Function<String, File> function = UrlToFileFunction.builder().basedir(basedir).build();

		Function<String, InputStream> inputStreamFunction = new StringInputStreamFunction(encoding);
		CachePersister<File, Optional<String>> filePersister = new FilePersister<File, String>(new NoopFunction<File>(), inputStreamFunction);

		Builder<String, String> builder = new Builder<String, String>();
		builder.function(function);
		builder.loader(loader);
		builder.fileLoader(fileLoader);
		builder.filePersister(filePersister);
		return builder.build();
	}

	public static <T, V> Builder<T, V> builder() {
		return new Builder<T, V>();
	}

	public static class Builder<T, V> extends ValidatingBuilder<FileCache<T, V>> {

		private CacheLoader<File, Optional<V>> fileLoader;
		private CachePersister<File, Optional<V>> filePersister;
		private CacheLoader<T, Optional<V>> loader;
		private Function<T, File> function;

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

		public Builder<T, V> function(Function<T, File> function) {
			this.function = function;
			return this;
		}

		@Override
		public FileCache<T, V> getInstance() {
			return new FileCache<T, V>(this);
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

		public Function<T, File> getFunction() {
			return function;
		}

		public void setFunction(Function<T, File> function) {
			this.function = function;
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

	public Function<T, File> getFunction() {
		return function;
	}

}
