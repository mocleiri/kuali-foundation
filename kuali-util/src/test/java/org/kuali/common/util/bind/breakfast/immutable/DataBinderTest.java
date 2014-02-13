package org.kuali.common.util.bind.breakfast.immutable;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static java.lang.String.format;
import static org.apache.commons.io.FileUtils.write;
import static org.kuali.common.util.ReflectionUtils.newInstance;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.bind.breakfast.immutable.BindKeyFunction.newBindKeyFunction;
import static org.kuali.common.util.log.Loggers.newLogger;

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
import org.slf4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class DataBinderTest {

	private static final Logger logger = newLogger();

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
			createInstances(list);
			bindLeavesToParents(list);
			buildInstances(list);

			Map<String, Object> bowlMap = newHashMap();
			for (Node<BindDescriptor> node : list) {
				BindDescriptor bd = node.getElement();
				String key = bd.getInstancePropertyName();
				Object value = bd.getInstance();
				bowlMap.put(key, value);
			}

			Bowl.Builder builder = new Bowl.Builder();
			MutablePropertyValues mpvs = new MutablePropertyValues(bowlMap);
			DataBinder binder = new DataBinder(builder);
			binder.bind(mpvs);
			Bowl bowl = builder.build();
			logger.info(format("bowl.milk.price=%s", bowl.getMilk().getPrice()));
			String html2 = Trees.html(Bowl.class.getSimpleName(), list, new BindDescriptorFunction());
			write(new File("/tmp/bds.htm"), html2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void buildInstances(List<Node<BindDescriptor>> nodes) {
		for (Node<BindDescriptor> node : nodes) {
			List<Node<BindDescriptor>> children = node.getChildren();
			for (Node<BindDescriptor> child : children) {
				BindDescriptor bd = child.getElement();
				if (!child.isLeaf()) {
					Builder<?> builder = bd.getBuilder();
					Object instance = builder.build();
					bd.setInstance(instance);
				}
			}
			BindDescriptor bd = node.getElement();
			Builder<?> builder = bd.getBuilder();
			Object instance = builder.build();
			bd.setInstance(instance);
		}
	}

	private void bindLeavesToParents(List<Node<BindDescriptor>> nodes) {
		for (Node<BindDescriptor> node : nodes) {
			List<Node<BindDescriptor>> children = node.getChildren();
			Map<String, Object> values = newHashMap();
			List<Node<BindDescriptor>> subNodes = newArrayList();
			for (Node<BindDescriptor> child : children) {
				BindDescriptor bd = child.getElement();
				if (child.isLeaf()) {
					values.put(bd.getInstancePropertyName(), bd.getBindValue());
				} else {
					subNodes.add(child);
				}
			}
			MutablePropertyValues mpvs = new MutablePropertyValues(values);
			Builder<?> builder = node.getElement().getBuilder();
			DataBinder binder = new DataBinder(builder);
			binder.bind(mpvs);
			bindLeavesToParents(subNodes);
		}
	}

	private void createInstances(List<Node<BindDescriptor>> nodes) {
		List<Node<BindDescriptor>> leaves = Trees.getLeaves(nodes);
		for (Node<BindDescriptor> leaf : leaves) {
			List<Node<BindDescriptor>> path = leaf.getPath();
			for (int i = 0; i < path.size() - 1; i++) {
				Node<BindDescriptor> ancestor = path.get(i);
				BindDescriptor bd = ancestor.getElement();
				if (bd.getBuilder() == null) {
					Builder<?> builder = createBuilder(ancestor);
					bd.setBuilder(builder);
				}
			}
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
			BindDescriptor bd = node.getElement();
			StringBuilder sb = new StringBuilder();
			sb.append(bd.getBindKey() + "<br>");
			sb.append(bd.getBindValue() + "<br>");
			sb.append(bd.getInstancePropertyName() + "<br>");
			sb.append(bd.getBuilder() + "<br>");
			sb.append(bd.getInstance() + "<br>");
			return sb.toString();
		}
	}

	protected static List<MutableNode<BindDescriptor>> getDescriptors(Class<?> type, List<Node<Field>> nodes, Function<List<Field>, String> function) {
		List<MutableNode<BindDescriptor>> newNodes = newArrayList();
		for (Node<Field> node : nodes) {
			Field field = node.getElement();
			BindDescriptor bd = new BindDescriptor();
			bd.setNode(node);
			if (node.isLeaf()) {
				List<Field> fields = node.getElementPath();
				String bindKey = function.apply(fields);
				bd.setBindKey(bindKey);
			}
			bd.setInstancePropertyName(field.getName());
			MutableNode<BindDescriptor> newNode = new MutableNode<BindDescriptor>(bd);
			List<MutableNode<BindDescriptor>> children = getDescriptors(field.getType(), node.getChildren(), function);
			newNode.add(children);
			newNodes.add(newNode);
		}
		return newNodes;
	}

	protected static Builder<?> createBuilder(Node<BindDescriptor> node) {
		BindDescriptor bd = node.getElement();
		Class<?> type = bd.getNode().getElement().getType();
		List<Class<?>> declaredClasses = ImmutableList.copyOf(type.getDeclaredClasses());
		for (Class<?> declaredClass : declaredClasses) {
			if (Builder.class.isAssignableFrom(declaredClass)) {
				return (Builder<?>) newInstance(declaredClass);
			}
		}
		throw illegalState("could not locate a builder for [%s]", type.getCanonicalName());
	}

}
