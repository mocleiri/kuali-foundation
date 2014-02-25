package org.kuali.common.devops.cache;

import static org.kuali.common.devops.cache.UrlLoader.newUrlLoader;
import static org.kuali.common.devops.cache.function.UrlToFileFunction2.newUrlToFileFunction2;
import static org.kuali.common.http.model.HttpContext.newHttpContext;

import java.io.File;

import org.kuali.common.devops.cache.function.UrlToFileFunction;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpWaitResult;

import com.google.common.base.Function;
import com.google.common.cache.CacheLoader;

public class Caches {

	public static <T, V> FileSystemCache<String, HttpWaitResult> buildUrlCache() {
		return buildUrlCache(newHttpContext());
	}

	public static <T, V> FileSystemCache<String, HttpWaitResult> buildUrlCache(HttpContext context) {
		return buildUrlCache(context, UrlToFileFunction.create().getBasedir(), context.getEncoding());
	}

	public static <T, V> FileSystemCache<String, HttpWaitResult> buildUrlCache(HttpContext context, File basedir) {
		return buildUrlCache(context, basedir, context.getEncoding());
	}

	public static <T, V> FileSystemCache<String, HttpWaitResult> buildUrlCache(HttpContext context, File basedir, String encoding) {
		CacheLoader<String, HttpWaitResult> loader = newUrlLoader(context);
		PersistentCache<File, HttpWaitResult> fileCache = new UrlFileCache();
		Function<String, File> keyConverter = newUrlToFileFunction2(basedir);

		FileSystemCache.Builder<String, HttpWaitResult> builder = FileSystemCache.builder();
		builder.keyConverter(keyConverter);
		builder.loader(loader);
		builder.fileCache(fileCache);
		builder.ignoreFileSystem(Boolean.getBoolean("cache.ignoreFileSystem"));
		return builder.build();
	}

}
