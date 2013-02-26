package org.kuali.common.svn;

import java.io.File;
import java.util.List;

public interface ScmService {

	void add(List<File> files);

	void update(List<File> files);

	void delete(List<File> files);

}
