package org.kuali.common.util.bind.breakfast.immutable;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.io.FileUtils.write;
import static org.kuali.common.util.ReflectionUtils.newInstance;
import static org.kuali.common.util.bind.breakfast.immutable.BindKeyFunction.newBindKeyFunction;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.Builder;
import org.junit.Test;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.test.AnnotatedFieldAssembler;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class DataBinderTest {

	@Test
	public void test() {
		try {
			Class<?> type = Bowl.class;
			List<Node<Field>> nodes = AnnotatedFieldAssembler.create(type, Bind.class).assemble();
			Function<List<Field>, String> function = newBindKeyFunction(type);
			List<MutableNode<BindDescriptor>> bds = getDescriptors(type, nodes, function);
			List<Node<BindDescriptor>> list = getDescriptors(bds);
			Map<String, String> map = ImmutableMap.of("bowl.milk.type", "lowfat", "bowl.milk.price", "2.29");
			bindValuesToLeaves(list, map);
			String html2 = Trees.html(Bowl.class.getSimpleName(), list, new BindDescriptorFunction());
			write(new File("/tmp/bds.htm"), html2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void bindValuesToLeaves(List<Node<BindDescriptor>> nodes, Map<String, ?> values) {
		List<Node<BindDescriptor>> leaves = Trees.getLeaves(nodes);
		for (Node<BindDescriptor> leaf : leaves) {
			BindDescriptor bd = leaf.getElement();
			String bindKey = bd.getBindKey();
			Optional<?> value = fromNullable(values.get(bindKey));
			if (value.isPresent()) {
				bd.setBindValue(value.get());
			}
		}
	}

	private List<Node<BindDescriptor>> getDescriptors(List<MutableNode<BindDescriptor>> bds) {
		List<Node<BindDescriptor>> list = Lists.newArrayList();
		for (MutableNode<BindDescriptor> bd : bds) {
			list.add(bd);
		}
		return list;
	}

	private static class BindDescriptorFunction implements Function<Node<BindDescriptor>, String> {

		@Override
		public String apply(Node<BindDescriptor> node) {
			boolean newInstance = false;
			for (Node<BindDescriptor> child : node.getChildren()) {
				newInstance = child.getElement().getBindValue() != null;
			}
			BindDescriptor bd = node.getElement();

			StringBuilder sb = new StringBuilder();
			sb.append(bd.getBindKey() + "<br>");
			sb.append(bd.getBindValue() + "<br>");
			sb.append(bd.getInstancePropertyName() + "<br>");
			sb.append("newInstance=" + newInstance + "<br>");
			return sb.toString();
		}
	}

	protected static List<MutableNode<BindDescriptor>> getDescriptors(Class<?> type, List<Node<Field>> nodes, Function<List<Field>, String> function) {
		List<MutableNode<BindDescriptor>> newNodes = newArrayList();
		for (Node<Field> node : nodes) {
			Field field = node.getElement();
			BindDescriptor bd = new BindDescriptor();
			bd.setNode(node);
			// bd.setInstance(newInstance(field.getType()));
			if (node.isLeaf()) {
				List<Field> fields = node.getElementPath();
				String bindKey = function.apply(fields);
				bd.setBindKey(bindKey);
				bd.setInstancePropertyName(field.getName());
			}
			MutableNode<BindDescriptor> newNode = new MutableNode<BindDescriptor>(bd);
			List<MutableNode<BindDescriptor>> children = getDescriptors(field.getType(), node.getChildren(), function);
			newNode.add(children);
			newNodes.add(newNode);
		}
		return newNodes;
	}

	protected static <T> T build(Class<? extends Builder<T>> type, Map<?, ?> map) {
		MutablePropertyValues values = new MutablePropertyValues(map);
		Builder<T> builder = newInstance(type);
		DataBinder binder = new DataBinder(builder);
		binder.bind(values);
		return builder.build();
	}

}
