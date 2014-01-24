package org.kuali.common.util.bind.test;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.Bind;

import com.google.common.collect.Lists;

public class Fields {

	public static DefaultMutableTreeNode assemble(Class<?> type) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(type.getSimpleName());
		return assemble(root, type);
	}

	public static DefaultMutableTreeNode assemble(Field field) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(field.getName());
		return assemble(node, field.getType());
	}

	protected static DefaultMutableTreeNode assemble(DefaultMutableTreeNode node, Class<?> type) {
		List<Field> fields = getFields(type);
		for (Field field : fields) {
			node.add(getChild(field));
		}
		return node;
	}

	protected static List<Field> getFields(Class<?> type) {
		List<Field> fields = Lists.newArrayList(ReflectionUtils.getAllFields(type));
		Collections.sort(fields, FieldNameComparator.INSTANCE);
		return fields;
	}

	protected static DefaultMutableTreeNode getChild(Field field) {
		if (field.isAnnotationPresent(Bind.class)) {
			return assemble(field);
		} else {
			return new DefaultMutableTreeNode(field.getName());
		}
	}

}
