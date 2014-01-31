package org.kuali.common.util.bind.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.model.BoundTypeDescriptor;
import org.kuali.common.util.bind.test.Prefixes;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class BoundTypes {

	public static BoundTypeDescriptor describe(Class<?> type) {
		Optional<String> prefix = Prefixes.get(type);
		return null;
	}

	protected static List<Node<Field>> getFields(Class<?> type) {
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		List<Node<Field>> list = Lists.newArrayList();
		for (Field field : fields) {
			Node<Field> node = getNode(field);
			list.add(node);
		}
		return list;
	}

	protected static MutableNode<Field> getNode(Field field) {
		MutableNode<Field> node = MutableNode.of(field);
		Set<Field> fields = ReflectionUtils.getAllFields(field.getType());
		for (Field element : fields) {
			if (element.isAnnotationPresent(Bind.class)) {
				MutableNode<Field> child = getNode(field);
				node.add(child);
			}
		}
		return node;
	}

}
