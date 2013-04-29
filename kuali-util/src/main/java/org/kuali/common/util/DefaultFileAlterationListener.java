package org.kuali.common.util;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultFileAlterationListener implements FileAlterationListener {

	private static final Logger logger = LoggerFactory.getLogger(DefaultFileAlterationListener.class);

	@Override
	public void onStart(FileAlterationObserver observer) {
	}

	@Override
	public void onDirectoryCreate(File directory) {
	}

	@Override
	public void onDirectoryChange(File directory) {
	}

	@Override
	public void onDirectoryDelete(File directory) {
	}

	@Override
	public void onFileCreate(File file) {
		logger.info("[filesystem:created] - {}", LocationUtils.getCanonicalPath(file));
	}

	@Override
	public void onFileChange(File file) {
		logger.info("[filesystem:changed] - {}", LocationUtils.getCanonicalPath(file));
	}

	@Override
	public void onFileDelete(File file) {
	}

	@Override
	public void onStop(FileAlterationObserver observer) {
	}

}
