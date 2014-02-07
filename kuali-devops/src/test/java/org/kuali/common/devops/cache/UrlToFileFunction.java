package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;

import org.kuali.common.util.file.CanonicalFile;

import com.google.common.base.Function;

public class UrlToFileFunction implements Function<String, File> {

	private final File basedir = new CanonicalFile("./target");
	private final String protocol = "http";

	@Override
	public File apply(String url) {
		checkNotBlank(url, "url");
		String token = protocol + "://";
		checkState(url.startsWith(token));
		String fragment = url.substring(token.length());
		return new CanonicalFile(basedir, protocol + "/cache/" + fragment);
	}

}
