package org.kuali.common.util.bind.test;

import java.lang.reflect.Field;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

import org.kuali.common.util.ReflectionUtils;

public class Fields {

	public static DefaultMutableTreeNode assemble(Class<?> type) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		for (Field field : fields) {
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(field);
			root.add(child);
		}
		return root;
	}

}
