package org.kuali.common.util.bind.breakfast.immutable;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newTreeSet;
import static java.lang.String.format;
import static org.kuali.common.util.ReflectionUtils.copyProperty;
import static org.kuali.common.util.ReflectionUtils.newInstance;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.Builder;
import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.test.AnnotatedFieldAssemblerFunction;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.spring.convert.Conversion;
import org.kuali.common.util.system.JVM;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;
import org.slf4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.DataBinder;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class DataBinderTest {

	private static final Logger logger = newLogger();

	private static final ConversionService conversion = getConversionService();

	private static ConversionService getConversionService() {
		return Conversion.getDefaultConversionService();
	}

	@Test
	public void test() {
		try {
			// Class<Bowl> type = Bowl.class;
			// Map<String, String> values = ImmutableMap.of("bowl.milk.type", "lowfat", "bowl.milk.price", "2.29");
			// Bowl bowl = getInstance(type, values);
			// logger.info(format("bowl.milk.price=%s", bowl.getMilk().getPrice()));

			// show(System.getProperties());

			JVM jvm = getInstance(JVM.class, getSystemProperties());
			logger.info(jvm.getFileSeparator());
			logger.info(jvm.getUser().getName());
			logger.info("classpath entries: " + jvm.getJava().getClasspath().size());
			logger.info("   extension dirs: " + jvm.getJava().getExtensionDirectories().size());
			logger.info("    library paths: " + jvm.getJava().getLibraryPaths().size());

			logger.info(jvm.getUser().getDir() + "");
			logger.info(jvm.getUser().getTimeZone().isPresent() + "");
			logger.info(jvm.getUser().getLanguage().isPresent() + "");
			logger.info(jvm.getUser().getCountry().isPresent() + "");
			String version = jvm.getJava().getRuntimeEnvironment().getVersion();
			String name = jvm.getJava().getVirtualMachine().getName();
			logger.info(name + "::" + version);
			logger.info(jvm.getJava().getHome() + "");

			logger.info(format("sys props: %s", jvm.getSystem().size()));
			logger.info(format("env props: %s", jvm.getEnvironment().size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Properties getSystemProperties() {
		String system = conversion.convert(System.getProperties(), String.class);
		String environment = conversion.convert(PropertyUtils.convert(System.getenv()), String.class);

		Properties props = new Properties();
		props.putAll(System.getProperties());
		props.put("system.properties", system);
		props.put("system.environment", environment);

		Set<String> exceptions = ImmutableSet.of("line.separator");
		removeAllBlanks(props, exceptions);

		return ImmutableProperties.copyOf(props);
	}

	public static <T> T getInstance(Class<T> type, Map<String, ?> values) {
		List<Node<Field>> nodes = AnnotatedFieldAssemblerFunction.create(Bind.class).apply(type);
		BindKeysFunction function = new BindKeysFunction(type);
		List<Node<BindDescriptor>> descriptors = buildDescriptorNodes(nodes, function, values);
		bindLeavesToParents(descriptors);
		buildInstances(descriptors);
		// TODO Remove this
		String html = Trees.html(type.getSimpleName(), descriptors, new BindDescriptorFunction());
		write("/tmp/bds.htm", html);
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
		binder.setConversionService(conversion);
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
			copyProperty(bean, name, value);
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
				binder.setConversionService(conversion);
				binder.bind(mpvs);
			}
		}
	}

	protected static class BindDescriptorFunction implements Function<Node<BindDescriptor>, String> {

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
		String display = value == null ? null : value.replace("<", "&lt;").replace(":", "<br>").replace(",", "<br>").replace("http<br>", "http:").replace("\n", "<br>");
		return "<tr valign=top><td align=right>" + label + "&nbsp;</td><td>" + display + "</td></tr>";
	}

	protected static List<Node<BindDescriptor>> buildDescriptorNodes(List<Node<Field>> nodes, BindKeysFunction function, Map<String, ?> values) {
		return convert(getDescriptors(nodes, function, values));
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

	protected static List<MutableNode<BindDescriptor>> getDescriptors(List<Node<Field>> nodes, BindKeysFunction function, Map<String, ?> values) {
		// Create some storage space for descriptor nodes
		List<MutableNode<BindDescriptor>> descriptorNodes = newArrayList();

		// Convert each node containing a field into a node containing a descriptor
		for (Node<Field> node : nodes) {

			// Create a new descriptor based on this node
			BindDescriptor descriptor = new BindDescriptor(node);

			// Hook the node into the tree
			MutableNode<BindDescriptor> descriptorNode = new MutableNode<BindDescriptor>(descriptor);
			descriptorNodes.add(descriptorNode);

			if (node.isLeaf()) {
				// If it's a leaf, extract a value from the map and store it in the descriptor
				updateLeafDescriptor(node, descriptor, function, values);
			} else {
				// If it's not a leaf create an empty Builder instance and store it in the descriptor
				Builder<?> builder = createBuilder(descriptorNode);
				descriptor.setInstanceBuilder(builder);
			}

			// Recurse
			List<MutableNode<BindDescriptor>> children = getDescriptors(node.getChildren(), function, values);
			descriptorNode.add(children);
		}

		// Return the descriptor nodes
		return descriptorNodes;
	}

	protected static void updateLeafDescriptor(Node<Field> node, BindDescriptor descriptor, BindKeysFunction function, Map<String, ?> values) {
		List<String> bindKeys = function.apply(node.getElementPath());
		Optional<?> value = getValue(bindKeys, values);

		descriptor.setBindKeys(bindKeys);
		descriptor.setBindValue(value);
	}

	protected static Optional<?> getValue(List<String> keys, Map<String, ?> values) {
		for (String key : keys) {
			if (values.containsKey(key)) {
				return Optional.of(values.get(key));
			}
		}
		return absent();
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

	public static <T> T getInstance(Class<T> type, Properties props) {
		Map<String, String> map = PropertyUtils.convert(ImmutableProperties.copyOf(props));
		return getInstance(type, map);
	}

	protected static void removeAllBlanks(Properties props, String... exceptions) {
		Set<String> keys = exceptions == null ? ImmutableSet.<String> of() : ImmutableSet.copyOf(exceptions);
		removeAllBlanks(props, keys);
	}

	protected static void removeAllBlanks(Properties props, Set<String> exceptions) {
		for (String key : newTreeSet(props.stringPropertyNames())) {
			String value = props.getProperty(key);
			boolean remove = StringUtils.isBlank(value) && !exceptions.contains(key);
			if (remove) {
				logger.info(String.format("ignoring [%s] because it is blank", key));
				props.remove(key);
			}
		}
	}

	protected static void write(String path, String content) {
		try {
			FileUtils.write(new File(path), content);
		} catch (IOException e) {
			throw illegalState(e);
		}
	}

	protected static void show(Properties props) {
		SortedSet<String> keys = newTreeSet(props.stringPropertyNames());
		for (String key : keys) {
			String value = props.getProperty(key);
			System.out.println(key + "=[" + Str.flatten(value) + "]");
		}
	}

}
