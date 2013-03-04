package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

public interface ScmService {

	void add(List<File> paths);

	void delete(List<File> paths);

	void commit(List<File> paths, String message);

}
