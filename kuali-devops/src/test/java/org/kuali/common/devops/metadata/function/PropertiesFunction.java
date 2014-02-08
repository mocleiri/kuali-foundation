package org.kuali.common.devops.metadata.function;

import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import com.google.common.base.Function;

public class PropertiesFunction implements Function<String, Properties> {

	@Override
	public Properties apply(String content) {
		checkNotNull(content, "content");
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes(UTF8));
			Properties props = new Properties();
			props.load(in);
			return props;
		} catch (IOException e) {
			throw illegalState(e, "unexpected io error loading properties -> \n\n%s\n\n", content);
		}
	}

}
