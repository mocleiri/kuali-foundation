package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

public interface ScmService {

	void add(List<File> files);

	void delete(List<File> files);

	void commit(List<File> paths);

}
