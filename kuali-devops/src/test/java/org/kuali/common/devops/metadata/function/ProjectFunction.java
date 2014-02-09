package org.kuali.common.devops.metadata.function;

import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;

import com.google.common.base.Function;

public class ProjectFunction implements Function<String, Project> {

	@Override
	public Project apply(String content) {
		checkNotNull(content, "content");
		Properties properties = getProperties(content);
		return ProjectUtils.getProject(properties);
	}

	protected Properties getProperties(String content) {
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
