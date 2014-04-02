package org.kuali.common.devops.metadata.function;

import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.kuali.common.util.properties.rice.RiceLoader;

import com.google.common.base.Function;

public class RicePropertiesFunction implements Function<String, Properties> {

	@Override
	public Properties apply(String content) {
		return getProperties(checkNotNull(content, "content"));
	}

	protected Properties getProperties(String content) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes(UTF8));
			return RiceLoader.load(in);
		} catch (Exception e) {
			// TODO Don't do this
			e.printStackTrace();
			return new Properties();
		}
	}

}
