package org.kuali.common.devops.cache;

import java.io.File;
import java.io.InputStream;

import org.kuali.common.devops.cache.function.NoopFunction;
import org.kuali.common.devops.cache.function.ReadFileToStringFunction;
import org.kuali.common.devops.cache.function.StringInputStreamFunction;
import org.kuali.common.devops.cache.function.UrlToFileFunction;
import org.kuali.common.http.model.HttpContext;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

public class FileCacheFactory {

	public static <T, V> FileCache<String, String> createHttpUrlCacher() {
		return createHttpUrlCacher(HttpContext.create());
	}

	public static <T, V> FileCache<String, String> createHttpUrlCacher(HttpContext context) {
		return createHttpUrlCacher(context, UrlToFileFunction.create().getBasedir(), context.getEncoding());
	}

	public static <T, V> FileCache<String, String> createHttpUrlCacher(HttpContext context, File basedir) {
		return createHttpUrlCacher(context, basedir, context.getEncoding());
	}

	public static <T, V> FileCache<String, String> createHttpUrlCacher(HttpContext context, File basedir, String encoding) {
		CacheLoader<String, Optional<String>> loader = HttpLoader.create(context);
		CacheLoader<File, Optional<String>> fileLoader = new FileLoader<String>(new ReadFileToStringFunction(encoding));
		Function<String, File> fileFunction = UrlToFileFunction.create(basedir);

		Function<String, InputStream> inputStreamFunction = new StringInputStreamFunction(encoding);
		CachePersister<File, Optional<String>> filePersister = new FilePersister<File, String>(new NoopFunction<File>(), inputStreamFunction);

		FileCache.Builder<String, String> builder = new FileCache.Builder<String, String>();
		builder.fileFunction(fileFunction);
		builder.loader(loader);
		builder.fileLoader(fileLoader);
		builder.filePersister(filePersister);
		return builder.build();
	}

}
