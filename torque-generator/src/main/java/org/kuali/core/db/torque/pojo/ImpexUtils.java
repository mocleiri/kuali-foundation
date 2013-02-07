package org.kuali.core.db.torque.pojo;

import java.io.File;

import org.kuali.common.util.CollectionUtils;
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
		clone.setTableIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setViewIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setSequenceIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setArtifactId(artifactId);
		clone.setSchemaXmlFile(new File(clone.getWorkingDir() + "/" + artifactId + ".xml"));
		return clone;
	}

}
