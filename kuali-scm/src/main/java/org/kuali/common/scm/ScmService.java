package org.kuali.common.scm;

import java.io.File;
import java.util.List;

public interface ScmService {

	void add(List<File> files, String message);

	void update(List<File> files, String message);

	void delete(List<File> files, String message);

}
