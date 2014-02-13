package org.kuali.common.util.bind.breakfast.immutable;

import static org.apache.commons.io.FileUtils.write;
import static org.kuali.common.util.ReflectionUtils.newInstance;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.Builder;
import org.junit.Test;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.test.AnnotatedFieldAssembler;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

import com.google.common.collect.ImmutableMap;

public class DataBinderTest {

	@Test
	public void test() {
		try {
			List<Node<Field>> nodes = AnnotatedFieldAssembler.create(Bowl.class, Bind.class).assemble();
			String html = Trees.html(Bowl.class.getSimpleName(), nodes, new FieldNameFunction());
			write(new File("/tmp/bowl.htm"), html);
			Milk milk = build(Milk.Builder.class, ImmutableMap.of("type", "lowfat", "price", "2.29"));
			Bowl bowl = build(Bowl.Builder.class, ImmutableMap.of("milk", milk));
			System.out.println("milk.type=" + bowl.getMilk().getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static <T> T build(Class<? extends Builder<T>> type, Map<?, ?> map) {
		MutablePropertyValues values = new MutablePropertyValues(map);
		Builder<T> builder = newInstance(type);
		DataBinder binder = new DataBinder(builder);
		binder.bind(values);
		return builder.build();
	}

}
