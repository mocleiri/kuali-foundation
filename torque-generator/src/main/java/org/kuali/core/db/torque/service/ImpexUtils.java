package org.kuali.core.db.torque.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

public class ImpexUtils {

	private static final Logger logger = LoggerFactory.getLogger(ImpexUtils.class);

	private static final String FS = File.separator;

	public static ImpexContext clone(ImpexContext source) {
		ImpexContext target = new ImpexContext();
		BeanUtils.copyProperties(source, target);
		return target;
	}

	public static ImpexContext clone(ImpexContext source, String include, String artifactId) throws IOException {
		ImpexContext clone = clone(source);
		clone.setTableIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setViewIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setSequenceIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setArtifactId(artifactId);
		clone.setWorkingDir(new File(clone.getWorkingDir() + FS + artifactId));
		clone.setSchemaXmlFile(new File(clone.getWorkingDir() + FS + "schema.xml"));
		return clone;
	}

	public static void prepareFileSystem(List<ImpexContext> contexts) {
		try {
			for (ImpexContext context : contexts) {
				prepareFileSystem(context);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error");
		}
	}

	/**
	 * Working dir must be set before invoking this method
	 */
	public static void prepareFileSystem(ImpexContext context) throws IOException {
		Assert.notNull(context.getWorkingDir(), "workingDir is null");
		// The Texen ant task requires these 2 files to be present or the parsing of schema.xml will fail
		createReportFile(context);
		createContextPropertiesFile(context);
	}

	protected static void createReportFile(ImpexContext context) throws IOException {
		String relativePath = "../reports/" + context.getArtifactId() + "/context.datadtd.generation";
		String absolutePath = context.getWorkingDir() + FS + relativePath;
		File file = new File(absolutePath);
		String canonicalPath = LocationUtils.getCanonicalPath(file);
		File canonicalFile = new File(canonicalPath);
		logger.debug("Create file  [{}]", canonicalPath);
		FileUtils.touch(canonicalFile);
		context.setReportFile(relativePath);
	}

	protected static void createContextPropertiesFile(ImpexContext context) {
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
