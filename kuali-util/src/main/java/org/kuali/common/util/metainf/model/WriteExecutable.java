package org.kuali.common.util.metainf.model;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;

public class WriteExecutable implements Executable {

	private final List<WriteLines> lines;
	private final List<WriteProperties> properties;

	public WriteExecutable(List<WriteLines> lines, List<WriteProperties> properties) {
		super();
		this.lines = lines;
		this.properties = properties;
	}

	@Override
	public void execute() {
		for (WriteLines element : CollectionUtils.toEmptyList(lines)) {
			write(element);
		}
		for (WriteProperties element : CollectionUtils.toEmptyList(properties)) {
			PropertyUtils.store(element.getProperties(), element.getOutputFile(), element.getEncoding());
		}

	}

	protected void write(WriteLines lines) {
		try {
			FileUtils.writeLines(lines.getOutputFile(), lines.getLines(), lines.getEncoding());
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO error", e);
		}
	}

}
