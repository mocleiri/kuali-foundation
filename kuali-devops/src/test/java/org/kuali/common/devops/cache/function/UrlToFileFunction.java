package org.kuali.common.devops.cache.function;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;

@IdiotProofImmutable
public final class UrlToFileFunction implements Function<String, File> {

	public UrlToFileFunction() {
		this(new CanonicalFile("./target/cache"));
	}

	public UrlToFileFunction(File basedir) {
		this.basedir = basedir;
	}

	private final File basedir;

	@Override
	public File apply(String url) {
		checkNotBlank(url, "url");
		String path = StringUtils.removeEnd(url.replace(":", ""), "/");
		return new CanonicalFile(basedir, path);
	}

	public File getBasedir() {
		return basedir;
	}

}
