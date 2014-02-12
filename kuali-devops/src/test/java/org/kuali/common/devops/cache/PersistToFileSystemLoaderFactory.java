package org.kuali.common.devops.cache;

import java.io.File;

import org.kuali.common.devops.cache.function.UrlToFileFunction;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpRequestResult;

import com.google.common.base.Function;
import com.google.common.cache.CacheLoader;

public class PersistToFileSystemLoaderFactory {

	public static <T, V> FileSystemCache<String, HttpRequestResult> createHttpUrlCacher() {
		return createHttpUrlCacher(HttpContext.create());
	}

	public static <T, V> FileSystemCache<String, HttpRequestResult> createHttpUrlCacher(HttpContext context) {
		return createHttpUrlCacher(context, UrlToFileFunction.create().getBasedir(), context.getEncoding());
	}

	public static <T, V> FileSystemCache<String, HttpRequestResult> createHttpUrlCacher(HttpContext context, File basedir) {
		return createHttpUrlCacher(context, basedir, context.getEncoding());
	}

	public static <T, V> FileSystemCache<String, HttpRequestResult> createHttpUrlCacher(HttpContext context, File basedir, String encoding) {
		CacheLoader<String, HttpRequestResult> loader = UrlLoader.create(context);
		CacheLoader<File, HttpRequestResult> fileSystemLoader = null;// new FileLoader<String>(new ReadFileToStringFunction(encoding));
		Function<String, File> convertKeyToFileFunction = UrlToFileFunction.create(basedir);

		CachePersister<File, HttpRequestResult> fileSystemPersister = null; // new FilePersister<File, String>(new NoopFunction<File>(), inputStreamFunction);

		FileSystemCache.Builder<String, HttpRequestResult> builder = new FileSystemCache.Builder<String, HttpRequestResult>();
		builder.convertKeyToFileFunction(convertKeyToFileFunction);
		builder.loader(loader);
		builder.fileSystemLoader(fileSystemLoader);
		builder.fileSystemPersister(fileSystemPersister);
		return builder.build();
	}

}
