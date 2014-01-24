package org.kuali.common.util.bind.test;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.system.SystemProperties;

public class FieldsTest {

	@Test
	public void test() {
		try {
			AnnotatedFieldAssembler assembler = AnnotatedFieldAssembler.make(SystemProperties.class, Bind.class);
			DefaultMutableTreeNode tree = assembler.assemble();
			String html = Trees.html(tree);
			FileUtils.write(new File("/tmp/fields.htm"), html);
			System.out.println(Trees.breadthFirst(tree).size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
