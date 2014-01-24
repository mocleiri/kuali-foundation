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
			DefaultMutableTreeNode child = getChild(field);
			parent.add(child);
		}
		return parent;
	}

	protected static DefaultMutableTreeNode getChild(Field field) {
		if (field.isAnnotationPresent(Bind.class)) {
			return assemble(field);
		} else {
			return new DefaultMutableTreeNode(field.getName());
		}
	}

	public static DefaultMutableTreeNode assemble(Field field) {
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(field.getName());
		Set<Field> fields = ReflectionUtils.getAllFields(field.getType());
		for (Field childField : fields) {
			DefaultMutableTreeNode child = getChild(childField);
			parent.add(child);
		}
		return parent;
	}

}
