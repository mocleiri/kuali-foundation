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

public class FieldsTest {

	@Test
	public void test() {
		try {
			Class<?> type = SystemProperties.class;
			AnnotatedFieldAssembler assembler = AnnotatedFieldAssembler.make(type, Bind.class);
			List<DefaultMutableTreeNode> nodes = assembler.assemble();
			KeyAssembler ass = KeyAssembler.make(type, nodes);
			Set<String> keys = ass.assemble();
			for (String key : keys) {
				System.out.println(key);
			}
			String html = Trees.html(type.getSimpleName(), nodes, UserObjectFunction.make(new FieldNameFunction()));
			FileUtils.write(new File("/tmp/fields.htm"), html);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
