package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.lang.reflect.Field;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.common.base.Function;

public class UserObjectFieldNameFunction implements Function<DefaultMutableTreeNode, String> {

	@Override
	public String apply(DefaultMutableTreeNode node) {
		checkNotNull(node, "'node' cannot be null'");
		Object userObject = node.getUserObject();
		checkNotNull(userObject, "'userObject' cannot be null'");
		checkState(Field.class == userObject.getClass(), "'node' must contain a [%s] object", Field.class.getCanonicalName());
		Field field = (Field) userObject;
		return field.getName();
	}

}
