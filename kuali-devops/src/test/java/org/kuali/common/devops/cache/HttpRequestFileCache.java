package org.kuali.common.devops.cache;

import java.io.File;

import org.kuali.common.http.model.HttpRequestResult;

public class HttpRequestFileCache extends PersistentCache<File, HttpRequestResult> {

	@Override
	public void persist(File key, HttpRequestResult value) {
	}

	@Override
	public HttpRequestResult load(File key) throws Exception {
		return null;
	}

}
