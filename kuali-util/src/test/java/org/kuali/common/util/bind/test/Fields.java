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
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(type.getCanonicalName());
		List<Field> fields = getFields(type);
		for (Field field : fields) {
			parent.add(getChild(field));
		}
		return parent;
	}

	public static DefaultMutableTreeNode assemble(Field field) {
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(field.getName());
		List<Field> fields = getFields(field.getType());
		for (Field childField : fields) {
			parent.add(getChild(childField));
		}
		return parent;
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
