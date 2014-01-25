package org.kuali.common.util.bind.test;

import java.io.File;
import java.util.List;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.function.FieldNameFunction;
import org.kuali.common.util.bind.function.UserObjectFunction;
import org.kuali.common.util.system.SystemProperties;
import org.kuali.common.util.tree.Trees;

public class FieldsTest {

	@Test
	public void test() {
		try {
			Class<?> type = SystemProperties.class;
			AnnotatedFieldAssembler fieldAssembler = AnnotatedFieldAssembler.make(type, Bind.class);
			List<DefaultMutableTreeNode> fields = fieldAssembler.assemble();
			KeyAssembler keyAssembler = KeyAssembler.make(type, fields);
			Set<String> keys = keyAssembler.assemble();
			for (String key : keys) {
				System.out.println(key);
			}
			String html = Trees.html(type.getSimpleName(), fields, UserObjectFunction.make(new FieldNameFunction()));
			FileUtils.write(new File("/tmp/fields.htm"), html);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
