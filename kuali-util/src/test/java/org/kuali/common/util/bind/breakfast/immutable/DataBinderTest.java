package org.kuali.common.util.bind.breakfast.immutable;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.kuali.common.util.ReflectionUtils.newInstance;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.bind.breakfast.immutable.BindKeyFunction.newBindKeyFunction;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.Builder;
import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.base.Exceptions;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.test.AnnotatedFieldAssemblerFunction;
import org.kuali.common.util.system.SystemProperties;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;
import org.slf4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

public class DataBinderTest {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		try {
			// Class<Bowl> type = Bowl.class;
			// Map<String, String> values = ImmutableMap.of("bowl.milk.type", "lowfat", "bowl.milk.price", "2.29");
			// Bowl bowl = getInstance(type, values);
			// logger.info(format("bowl.milk.price=%s", bowl.getMilk().getPrice()));

			show(System.getProperties());
			SystemProperties vm = getInstance(SystemProperties.class, System.getProperties());
			logger.info(vm.getFileSeparator());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void show(Properties props) {
		SortedSet<String> keys = Sets.newTreeSet(props.stringPropertyNames());
		for (String key : keys) {
			String value = props.getProperty(key);
			System.out.println(key + "=" + value);
		}
	}

	public static <T> T getInstance(Class<T> type, Map<String, ?> values) {
		List<Node<Field>> nodes = AnnotatedFieldAssemblerFunction.create(Bind.class).apply(type);
		List<Node<BindDescriptor>> descriptors = buildDescriptors(type, nodes, newBindKeyFunction(type));
		bindValuesToLeaves(descriptors, values);
		createBuilderInstances(descriptors);
		bindLeavesToParents(descriptors);
		buildInstances(descriptors);
		Map<String, Object> map = newHashMap();
		for (Node<BindDescriptor> node : descriptors) {
			BindDescriptor bd = node.getElement();
			String key = bd.getInstancePropertyName();
			Object value = bd.getInstance() != null ? bd.getInstance() : bd.getBindValue();
			map.put(key, value);
		}

		Builder<T> builder = createBuilder(type);
		MutablePropertyValues mpvs = new MutablePropertyValues(map);
		DataBinder binder = new DataBinder(builder);
		binder.bind(mpvs);
		return builder.build();
	}

	protected static void buildInstances(List<Node<BindDescriptor>> nodes) {
		for (Node<BindDescriptor> node : nodes) {
			buildInstances(node.getChildren());
			if (node.isLeaf()) {
				continue;
			}
			BindDescriptor bd = node.getElement();
			Builder<?> builder = bd.getInstanceBuilder();
			if (builder == null) {
				continue;
			}
			Object instance = builder.build();
			bd.setInstance(instance);
			Optional<Node<BindDescriptor>> parent = node.getParent();
			if (parent.isPresent()) {
				updateParentBuilder(parent.get().getElement(), bd);
			}
		}
	}

	protected static void updateParentBuilder(BindDescriptor parent, BindDescriptor child) {
		Builder<?> parentBuilder = parent.getInstanceBuilder();
		Object bean = parentBuilder;
		String name = child.getInstancePropertyName();
		Object value = child.getInstance();
		if (value != null) {
			ReflectionUtils.copyProperty(bean, name, value);
		}
	}

	protected static void bindLeavesToParents(List<Node<BindDescriptor>> nodes) {
		for (Node<BindDescriptor> node : nodes) {
			BindDescriptor descriptor = node.getElement();
			List<Node<BindDescriptor>> children = node.getChildren();
			Map<String, Object> values = newHashMap();
			List<Node<BindDescriptor>> subNodes = newArrayList();
			for (Node<BindDescriptor> child : children) {
				BindDescriptor bd = child.getElement();
				if (child.isLeaf()) {
					if (bd.getBindValue() != null) {
						values.put(bd.getInstancePropertyName(), bd.getBindValue());
					}
				} else {
					subNodes.add(child);
				}
			}
			bindLeavesToParents(subNodes);
			if (!children.isEmpty()) {
				MutablePropertyValues mpvs = new MutablePropertyValues(values);
				Builder<?> builder = descriptor.getInstanceBuilder();
				DataBinder binder = new DataBinder(builder);
				binder.bind(mpvs);
			}
		}
	}

	protected static void createBuilderInstances(List<Node<BindDescriptor>> nodes) {
		List<Node<BindDescriptor>> leaves = Trees.getLeaves(nodes);
		for (Node<BindDescriptor> leaf : leaves) {
			List<Node<BindDescriptor>> path = leaf.getPath();
			for (int i = 0; i < path.size() - 1; i++) {
				Node<BindDescriptor> ancestor = path.get(i);
				BindDescriptor bd = ancestor.getElement();
				if (bd.getInstanceBuilder() == null) {
					Builder<?> builder = createBuilder(ancestor);
					bd.setInstanceBuilder(builder);
				}
			}
		}
	}

	protected static void bindValuesToLeaves(List<Node<BindDescriptor>> nodes, Map<String, ?> values) {
		for (Node<BindDescriptor> leaf : Trees.getLeaves(nodes)) {
			bindValuesToLeaf(leaf, values);
		}
	}

	protected static void bindValuesToLeaf(Node<BindDescriptor> leaf, Map<String, ?> values) {
		for (String bindKey : leaf.getElement().getBindKeys()) {
			// Ordering of the bindKeys is significant here.
			// We must return the first value that matches
			if (values.containsKey(bindKey)) {
				Object value = values.get(bindKey);
				leaf.getElement().setBindValue(value);
				break;
			}
		}
	}

	private static class BindDescriptorFunction implements Function<Node<BindDescriptor>, String> {

		@Override
		public String apply(Node<BindDescriptor> node) {
			BindDescriptor bd = node.getElement();
			StringBuilder sb = new StringBuilder();
			sb.append("<table border=0>");
			if (bd.getBindKeys() != null) {
				sb.append(tr("bind keys", Joiner.on(", ").join(bd.getBindKeys())));
			} else {
				sb.append(tr("bind keys", null));
			}
			sb.append(tr("bind value", bd.getBindValue() + ""));
			sb.append(tr("instance property name", bd.getInstancePropertyName() + ""));
			String display = null;
			if (bd.getInstanceBuilder() != null) {
				String simple = bd.getInstanceBuilder().getClass().getDeclaringClass().getSimpleName();
				display = simple + ".Builder<" + simple + ">";
			}
			sb.append(tr("instance builder", display));
			display = null;
			if (bd.getInstance() != null) {
				display = bd.getInstance().getClass().getSimpleName() + "@" + Integer.toHexString(bd.getInstance().hashCode());
			}
			sb.append(tr("instance", display));
			sb.append("</table>");
			return sb.toString();
		}
	}

	protected static String tr(String label, String value) {
		String display = value == null ? null : value.replace(":", "<br>").replace(",", "<br>").replace("http<br>", "http:");
		return "<tr valign=top><td align=right>" + label + "&nbsp;</td><td>" + display + "</td></tr>";
	}

	protected static List<Node<BindDescriptor>> buildDescriptors(Class<?> type, List<Node<Field>> nodes, Function<List<Field>, List<String>> function) {
		return convert(getDescriptors(type, nodes, function));
	}

	protected static List<MutableNode<BindDescriptor>> transform(List<Node<Field>> nodes) {
		List<MutableNode<BindDescriptor>> newNodes = newArrayList();
		for (Node<Field> node : nodes) {
			MutableNode<BindDescriptor> newNode = new MutableNode<BindDescriptor>(new BindDescriptor(node));
			newNode.add(transform(node.getChildren()));
			newNodes.add(newNode);
		}
		return newNodes;
	}

	protected static List<MutableNode<BindDescriptor>> getDescriptors(Class<?> type, List<Node<Field>> nodes, Function<List<Field>, List<String>> function) {
		List<MutableNode<BindDescriptor>> newNodes = newArrayList();
		for (Node<Field> node : nodes) {
			Field field = node.getElement();
			BindDescriptor bd = new BindDescriptor(node);
			bd.setInstancePropertyName(field.getName());
			if (node.isLeaf()) {
				List<Field> fields = node.getElementPath();
				List<String> bindKeys = function.apply(fields);
				bd.setBindKeys(bindKeys);
			}
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
		return createBuilder(type);
	}

	/**
	 * Suppressing this warning means that we are assuming any class implementing the Builder interface declared inside of another class produces instances of the class in which it
	 * is declared.
	 */
	@SuppressWarnings("unchecked")
	protected static <T> Builder<T> createBuilder(Class<T> type) {
		List<Class<?>> declaredClasses = ImmutableList.copyOf(type.getDeclaredClasses());
		for (Class<?> declaredClass : declaredClasses) {
			if (Builder.class.isAssignableFrom(declaredClass)) {
				return (Builder<T>) newInstance(declaredClass);
			}
		}
		throw illegalState("could not locate a [%s] declared in [%s]", Builder.class.getCanonicalName(), type.getCanonicalName());
	}

	protected static List<Node<BindDescriptor>> convert(List<MutableNode<BindDescriptor>> bds) {
		List<Node<BindDescriptor>> list = newArrayList();
		for (MutableNode<BindDescriptor> bd : bds) {
			list.add(bd);
		}
		return list;
	}

	public static <T> T getInstance(Class<T> type, Properties properties) {
		return getInstance(type, PropertyUtils.convert(properties));
	}

	private static void write(String path, String content) {
		try {
			FileUtils.write(new File(path), content);
		} catch (IOException e) {
			throw Exceptions.illegalState(e);
		}
	}

}
