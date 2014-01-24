package org.kuali.common.util.bind.test;

import java.lang.reflect.Field;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.Bind;

public class Fields {

	public static DefaultMutableTreeNode assemble(Class<?> type) {
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(type.getSimpleName());
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		for (Field field : fields) {
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(field.getName());
			if (field.isAnnotationPresent(Bind.class)) {
				child.add(assemble(field.getType()));
			}
			parent.add(child);
		}
		return parent;
	}

}
