package org.kuali.common.util.bind.test;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.util.system.SystemProperties;

public class FieldsTest {

	@Test
	public void test() {
		try {
			DefaultMutableTreeNode tree = Fields.assemble(SystemProperties.class);
			List<DefaultMutableTreeNode> nodes = Trees.depthFirst(tree);
			for (DefaultMutableTreeNode node : nodes) {
				int level = node.getLevel();
				String prefix = StringUtils.repeat(" ", level);
				Object object = node.getUserObject();
				System.out.println(prefix + object.toString());
			}
			System.out.println(nodes.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
