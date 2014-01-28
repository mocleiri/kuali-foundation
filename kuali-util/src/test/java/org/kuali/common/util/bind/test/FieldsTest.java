package org.kuali.common.util.bind.test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.function.FieldNameFunction;
import org.kuali.common.util.system.SystemProperties;
import org.kuali.common.util.tree.Node;
import org.kuali.common.util.tree.NodeStringFunction;
import org.kuali.common.util.tree.Trees;

import com.google.common.collect.Lists;

public class FieldsTest {

	@Test
	public void test() {
		try {
			Class<?> type = SystemProperties.class;
			AnnotatedFieldAssembler assembler = AnnotatedFieldAssembler.create(type, Bind.class);
			List<Node<Field>> fields = assembler.assemble();
			String html = Trees.html(type.getSimpleName(), fields, NodeStringFunction.create(new FieldNameFunction()));
			File file = new CanonicalFile(System.getProperty("java.io.tmpdir"), "fields.htm");
			FileUtils.write(file, html);
			System.out.println(file);
			List<Node<Field>> nodes = Lists.newArrayList();
			for (Node<Field> field : fields) {
				nodes.addAll(Trees.breadthFirst(field));
			}
			for (Node<Field> node : nodes) {
				System.out.println(node.getClass().getCanonicalName());
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
