package org.kuali.core.db.torque.service;

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.springframework.beans.BeanUtils;

public class ImpexUtils {

	private static final String FS = File.separator;

	public static ImpexContext clone(ImpexContext source) {
		ImpexContext target = new ImpexContext();
		BeanUtils.copyProperties(source, target);
		return target;
	}

	public static ImpexContext cloneAndInitialize(ImpexContext source, String include, String artifactId) {
		ImpexContext clone = clone(source);
		clone.setTableIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setViewIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setSequenceIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setArtifactId(artifactId);
		clone.setWorkingDir(new File(clone.getWorkingDir() + FS + artifactId));
		clone.setSchemaXmlFile(new File(clone.getWorkingDir() + FS + "schema.xml"));
		initialize(clone);
		return clone;
	}

	/**
	 * Working dir must be set before invoking this method
	 */
	public static void initialize(ImpexContext context) {
		context.setContextProperties(new File(context.getWorkingDir(), "/reports/context.datadtd.properties"));
		context.setReportFile("../reports/report." + context.getArtifactId() + ".datadtd.generation");
		prepareFileSystem(context);
	}

	protected static void prepareFileSystem(ImpexContext context) {
		Properties properties = new Properties();
		properties.setProperty("project", "impex");
		properties.setProperty("version", "2.0");
		PropertyUtils.store(properties, context.getContextProperties());
	}

}
