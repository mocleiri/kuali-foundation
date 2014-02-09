package org.kuali.common.devops.logic;

import static com.google.common.base.Optional.fromNullable;

import java.util.Properties;

import org.kuali.common.devops.model.Scm;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public class Applications extends Examiner {

	private static final Logger logger = Loggers.make();

	public static Optional<Scm> getScm(Properties properties) {
		Optional<String> url = fromNullable(properties.getProperty(SCM_URL_KEY));
		Optional<String> revision = fromNullable(properties.getProperty(SCM_REVISION_KEY));
		if (url.isPresent() && revision.isPresent()) {
			return Optional.of(Scm.create(url.get(), revision.get()));
		} else {
			return Optional.absent();
		}
	}

}
