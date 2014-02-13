package org.kuali.common.util.bind.breakfast.immutable;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.io.FileUtils.write;
import static org.kuali.common.util.ReflectionUtils.newInstance;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.Builder;
import org.junit.Test;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.test.AnnotatedFieldAssembler;
import org.kuali.common.util.function.FieldNameFunction;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class DataBinderTest {

	@Test
	public void test() {
		try {
			Class<?> type = Bowl.class;
			List<Node<Field>> nodes = AnnotatedFieldAssembler.create(type, Bind.class).assemble();
			String html = Trees.html(Bowl.class.getSimpleName(), nodes, new org.kuali.common.util.bind.breakfast.immutable.FieldNameFunction());
			write(new File("/tmp/bowl.htm"), html);
			Milk milk = build(Milk.Builder.class, ImmutableMap.of("type", "lowfat", "price", "2.29"));
			Bowl bowl = build(Bowl.Builder.class, ImmutableMap.of("milk", milk));
			System.out.println("milk.type=" + bowl.getMilk().getType());
			List<String> keys = getKeys(type, nodes);
			for (String key : keys) {
				System.out.println(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static List<String> getKeys(Class<?> type, List<Node<Field>> nodes) {
		String prefix = StringUtils.uncapitalize(type.getSimpleName());
		char separator = '.';
		Joiner joiner = Joiner.on(separator);
		List<Node<Field>> leaves = Trees.getLeaves(nodes);
		List<String> keys = newArrayList();
		for (Node<Field> leaf : leaves) {
			List<Field> fields = leaf.getElementPath();
			List<String> names = Lists.transform(fields, new FieldNameFunction());
			keys.add(prefix + separator + joiner.join(names));
		}
		return keys;
	}

	protected static <T> T build(Class<? extends Builder<T>> type, Map<?, ?> map) {
		MutablePropertyValues values = new MutablePropertyValues(map);
		Builder<T> builder = newInstance(type);
		DataBinder binder = new DataBinder(builder);
		binder.bind(values);
		return builder.build();
	}

}
