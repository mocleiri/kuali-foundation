package org.kuali.core.db.torque.service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class ImpexUtils {

	private static final Logger logger = LoggerFactory.getLogger(ImpexUtils.class);

	private static final String FS = File.separator;

	public static ImpexContext clone(ImpexContext source) {
		ImpexContext target = new ImpexContext();
		BeanUtils.copyProperties(source, target);
		return target;
	}

	public static ImpexContext cloneAndInitialize(ImpexContext source, String include, String artifactId) throws IOException {
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
	public static void initialize(ImpexContext context) throws IOException {
		initReportFile(context);
		initContextPropertiesFile(context);
	}

	protected static void initReportFile(ImpexContext context) throws IOException {
		String relativePath = "../reports/" + context.getArtifactId() + "/context.datadtd.generation";
		String absolutePath = context.getWorkingDir() + "/" + relativePath;
		File file = new File(absolutePath);
		String canonicalPath = LocationUtils.getCanonicalPath(file);
		File canonicalFile = new File(canonicalPath);
		logger.debug("Touching report file [{}]", canonicalPath);
		FileUtils.touch(canonicalFile);
		context.setReportFile(relativePath);
	}

	protected static void initContextPropertiesFile(ImpexContext context) {
		String path = context.getWorkingDir() + "/../reports/" + context.getArtifactId() + "/context.datadtd.properties";
		String canonicalPath = LocationUtils.getCanonicalPath(new File(path));
		File file = new File(canonicalPath);
		context.setContextProperties(file);

		Properties properties = new Properties();
		properties.setProperty("project", "impex");
		properties.setProperty("version", "2.0");
		PropertyUtils.store(properties, context.getContextProperties());
	}

}
