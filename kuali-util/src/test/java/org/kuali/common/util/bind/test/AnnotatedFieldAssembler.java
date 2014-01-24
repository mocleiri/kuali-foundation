package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public final class AnnotatedFieldAssembler {

	private final Class<? extends Annotation> annotation;
	private final Class<?> type;
	private final Comparator<Field> comparator;

	public ImmutableList<DefaultMutableTreeNode> assemble() {
		DefaultMutableTreeNode root = assemble(Optional.<Field> absent(), type);
		List<DefaultMutableTreeNode> children = Trees.children(root);
		return ImmutableList.copyOf(children);
	}

	protected DefaultMutableTreeNode assemble(Optional<Field> field, Class<?> type) {
		DefaultMutableTreeNode node = getNode(field);
		List<Field> children = getSortedFields(type);
		for (Field child : children) {
			node.add(getChild(child));
		}
		return node;
	}

	protected DefaultMutableTreeNode getNode(Optional<Field> field) {
		if (field.isPresent()) {
			return new DefaultMutableTreeNode(field.get());
		} else {
			return new DefaultMutableTreeNode();
		}
	}

	protected DefaultMutableTreeNode assemble(Field field) {
		return assemble(Optional.of(field), field.getType());
	}

	protected List<Field> getSortedFields(Class<?> type) {
		List<Field> fields = Lists.newArrayList(ReflectionUtils.getAllFields(type));
		Collections.sort(fields, comparator);
		return fields;
	}

	protected DefaultMutableTreeNode getChild(Field field) {
		if (field.isAnnotationPresent(annotation)) {
			return assemble(field);
		} else {
			return getNode(Optional.of(field));
		}
	}

	private AnnotatedFieldAssembler(Builder builder) {
		this.annotation = builder.annotation;
		this.type = builder.type;
		this.comparator = builder.comparator;
	}

	public static AnnotatedFieldAssembler make(Class<?> type, Class<? extends Annotation> annotation) {
		return builder(type, annotation).build();
	}

	public static Builder builder(Class<?> type, Class<? extends Annotation> annotation) {
		return new Builder(type, annotation);
	}

	public static class Builder implements org.kuali.common.util.build.Builder<AnnotatedFieldAssembler> {

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
			AnnotatedFieldAssembler instance = new AnnotatedFieldAssembler(this);
			validate(instance);
			return instance;
		}

		private static void validate(AnnotatedFieldAssembler instance) {
			checkNotNull(instance.annotation, "'annotation' cannot be null");
			checkNotNull(instance.type, "'type' cannot be null");
			checkNotNull(instance.comparator, "'comparator' cannot be null");
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
