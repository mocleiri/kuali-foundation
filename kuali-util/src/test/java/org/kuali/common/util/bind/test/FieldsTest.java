package org.kuali.common.util.bind.test;

import java.io.File;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.system.SystemProperties;

public class FieldsTest {

	@Test
	public void test() {
		try {
			Class<?> type = SystemProperties.class;
			AnnotatedFieldAssembler assembler = AnnotatedFieldAssembler.make(type, Bind.class);
			List<DefaultMutableTreeNode> nodes = assembler.assemble();
			String html = Trees.html(type.getSimpleName(), nodes);
			FileUtils.write(new File("/tmp/fields.htm"), html);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
