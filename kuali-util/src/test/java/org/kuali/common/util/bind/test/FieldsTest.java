package org.kuali.common.util.bind.test;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.system.SystemProperties;
import org.kuali.common.util.tree.Node;

public class FieldsTest {

	@Test
	public void test() {
		try {
			Class<?> type = SystemProperties.class;
			AnnotatedFieldAssembler fieldAssembler = AnnotatedFieldAssembler.of(type, Bind.class);
			List<? extends Node<Field>> fields = fieldAssembler.assemble();
			// String html = Trees.html(type.getSimpleName(), fields, NodeElementFunction.of(new FieldNameFunction()));
			// FileUtils.write(new File("/tmp/fields.htm"), html);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
