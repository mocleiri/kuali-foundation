package org.kuali.common.util.bind.test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.function.FieldNameFunction;
import org.kuali.common.util.bind.function.UserObjectFunction;
import org.kuali.common.util.system.SystemProperties;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.Trees;

public class FieldsTest {

	@Test
	public void test() {
		try {
			Class<?> type = SystemProperties.class;
			AnnotatedFieldAssembler fieldAssembler = AnnotatedFieldAssembler.of(type, Bind.class);
			List<? extends Node<Field>> fields = fieldAssembler.assemble();
			String html = Trees.html(type.getSimpleName(), fields, UserObjectFunction.of(FieldNameFunction.of()));
			FileUtils.write(new File("/tmp/fields.htm"), html);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
