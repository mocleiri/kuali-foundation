package org.kuali.common.jdbc.convert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.SimpleScanner;

public class DatabaseConverter {

	public void convert(DatabaseContext context) {
		String includes = "*.sql";
		String excludes = context.getArtifactId() + "*.sql";
		SimpleScanner scanner = new SimpleScanner(context.getDirectory(), includes, excludes);

		List<File> oldFiles = scanner.getFiles();
		List<File> newFiles = getNewFiles(context.getDirectory(), oldFiles);
		convert(context, oldFiles, newFiles);
	}

	protected List<ConversionResult> convert(DatabaseContext context, List<File> oldFiles, List<File> newFiles) {
		SqlConverter converter = context.getConverter();
		List<ConversionResult> results = new ArrayList<ConversionResult>();
		for (int i = 0; i < oldFiles.size(); i++) {
			File oldFile = oldFiles.get(i);
			File newFile = newFiles.get(i);
			ConversionContext cc = new ConversionContext();
			cc.setNewFile(newFile);
			cc.setOldFile(oldFile);
			ConversionResult result = converter.convert(cc);
			results.add(result);
		}
		return results;
	}

	protected List<File> getNewFiles(File baseDir, List<File> oldFiles) {
		List<File> newFiles = new ArrayList<File>();
		for (File oldFile : oldFiles) {
			File newFile = new File(oldFile.getAbsolutePath() + ".combined");
			newFiles.add(newFile);
		}
		return newFiles;
	}
}
