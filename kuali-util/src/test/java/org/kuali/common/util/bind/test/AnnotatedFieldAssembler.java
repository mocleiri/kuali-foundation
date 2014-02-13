package org.kuali.common.util.bind.test;

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.validate.Validation.checkValidation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.tree.ImmutableNode;
import org.kuali.common.util.tree.MutableNode;
import org.kuali.common.util.tree.Node;

import com.google.common.collect.ImmutableList;

public final class AnnotatedFieldAssembler implements Assembler<List<Node<Field>>> {

	private final Class<? extends Annotation> annotation;
	private final Class<?> type;
	private final Comparator<Field> comparator;

	@Override
	public List<Node<Field>> assemble() {
		List<MutableNode<Field>> assembled = assemble(type);
		List<Node<Field>> list = newArrayList();
		for (Node<Field> element : assembled) {
			list.add(ImmutableNode.copyOf(element));
		}
		return ImmutableList.copyOf(list);
	}

	protected List<MutableNode<Field>> assemble(Class<?> type) {
		List<Field> fields = getSortedFields(type);
		List<MutableNode<Field>> list = newArrayList();
		for (Field field : fields) {
			list.add(getNode(field));
		}
		return list;
	}

	protected MutableNode<Field> getNode(Field field) {
		MutableNode<Field> node = new MutableNode<Field>(field);
		if (field.isAnnotationPresent(annotation)) {
			node.add(assemble(field.getType()));
		}
		return node;
	}

	protected List<Field> getSortedFields(Class<?> type) {
		List<Field> fields = newArrayList(ReflectionUtils.getAllFields(type));
		Collections.sort(fields, comparator);
		return fields;
	}

	private AnnotatedFieldAssembler(Builder builder) {
		this.annotation = builder.annotation;
		this.type = builder.type;
		this.comparator = builder.comparator;
	}

	public static AnnotatedFieldAssembler create(Class<?> type, Class<? extends Annotation> annotation) {
		return builder(type, annotation).build();
	}

	public static Builder builder(Class<?> type, Class<? extends Annotation> annotation) {
		return new Builder(type, annotation);
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<AnnotatedFieldAssembler> {

		// Required
		private final Class<?> type;
		private final Class<? extends Annotation> annotation;

		// Optional
		private Comparator<Field> comparator = FieldNameComparator.INSTANCE;

		public Builder(Class<?> type, Class<? extends Annotation> annotation) {
			this.type = type;
			this.annotation = annotation;
		}

		public Builder comparator(Comparator<Field> comparator) {
			this.comparator = comparator;
			return this;
		}

		@Override
		public AnnotatedFieldAssembler build() {
			return checkValidation(new AnnotatedFieldAssembler(this));
		}

		public Comparator<Field> getComparator() {
			return comparator;
		}

		public void setComparator(Comparator<Field> comparator) {
			this.comparator = comparator;
		}

		public Class<? extends Annotation> getAnnotation() {
			return annotation;
		}

		public Class<?> getType() {
			return type;
		}
	}

	public Class<? extends Annotation> getAnnotation() {
		return annotation;
	}

	public Class<?> getType() {
		return type;
	}

	public Comparator<Field> getComparator() {
		return comparator;
	}

}
