package org.kuali.common.util.bind.test;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Test;
import org.kuali.common.util.system.SystemProperties;

public class FieldsTest {

	@Test
	public void test() {
		try {
			DefaultMutableTreeNode tree = Fields.assemble(SystemProperties.class);
			List<DefaultMutableTreeNode> nodes = Trees.children(tree);
			System.out.println(nodes.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
