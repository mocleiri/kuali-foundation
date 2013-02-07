package org.kuali.core.db.torque.pojo;

import java.io.File;
import java.util.Arrays;

import org.kuali.core.db.torque.service.ImpexContext;
import org.springframework.beans.BeanUtils;

public class ImpexUtils {

	public static ImpexContext clone(ImpexContext source) {
		ImpexContext target = new ImpexContext();
		BeanUtils.copyProperties(source, target);
		return target;
	}

	public static ImpexContext clone(ImpexContext source, String include, String artifactId) {
		ImpexContext clone = clone(source);
		clone.setTableIncludes(Arrays.asList(include));
		clone.setViewIncludes(Arrays.asList(include));
		clone.setSequenceIncludes(Arrays.asList(include));
		clone.setArtifactId(artifactId);
		clone.setSchemaXmlFile(new File(clone.getWorkingDir() + "/" + artifactId + ".xml"));
		return clone;
	}

}
