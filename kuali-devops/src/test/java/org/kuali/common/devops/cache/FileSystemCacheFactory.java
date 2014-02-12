package org.kuali.common.devops.cache;

import java.io.File;

import org.kuali.common.devops.cache.function.UrlToFileFunction;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpRequestResult;

import com.google.common.base.Function;
import com.google.common.cache.CacheLoader;

public class FileSystemCacheFactory {

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
		PersistentCache<File, HttpRequestResult> fileCache = new UrlFileCache();
		Function<String, File> keyConverter = UrlToFileFunction.create(basedir);

		FileSystemCache.Builder<String, HttpRequestResult> builder = new FileSystemCache.Builder<String, HttpRequestResult>();
		builder.keyConverter(keyConverter);
		builder.loader(loader);
		builder.fileCache(fileCache);
		return builder.build();
	}

}
