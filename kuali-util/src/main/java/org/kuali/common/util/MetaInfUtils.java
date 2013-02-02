package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaInfUtils {

	private static final Logger logger = LoggerFactory.getLogger(MetaInfUtils.class);

	public static void scanAndCreateFiles(List<MetaInfContext> contexts) throws IOException {
		for (MetaInfContext context : contexts) {
			Assert.notNull(context.getBaseDir(), "baseDir is null");
			Assert.notNull(context.getOutputFile(), "outputFile is null");
			List<String> includes = context.getIncludes();
			List<String> excludes = context.getExcludes();
			logger.info("Examining - " + context.getBaseDir().getCanonicalPath());
			logger.info("Include - " + CollectionUtils.getSpaceSeparatedString(includes));
			logger.info("Exclude - " + CollectionUtils.getSpaceSeparatedString(excludes));
			SimpleScanner scanner = new SimpleScanner(context.getBaseDir(), includes, excludes);
			List<File> files = scanner.getFiles();
			logger.info("Located " + files.size() + " files");
			List<String> locations = getLocations(context.getBaseDir(), files, context.getPrefix());
			if (context.isSort()) {
				Collections.sort(locations);
			}
			logger.info("Creating " + context.getOutputFile().getCanonicalPath());
			FileUtils.writeLines(context.getOutputFile(), locations);
		}
	}

	public static void scanAndCreateFile(MetaInfContext context) throws IOException {
		scanAndCreateFiles(Arrays.asList(context));
	}

	public static List<String> getLocations(File baseDir, List<File> files, String prefix) throws IOException {
		List<String> locations = new ArrayList<String>();
		for (int i = 0; i < files.size(); i++) {
			String location = getLocation(baseDir, files.get(i), prefix);
			locations.add(location);
		}
		return locations;
	}

	public static String getLocation(File baseDir, File file, String prefix) throws IOException {
		String dir = baseDir.getCanonicalPath();
		String path = file.getCanonicalPath();
		int pos = dir.length() + 1;
		return prefix + StringUtils.substring(path, pos);
	}

}
