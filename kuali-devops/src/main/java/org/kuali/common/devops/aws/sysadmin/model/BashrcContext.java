package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class BashrcContext {

	private final List<String> additionalLines;
	private final String javaHome;
	private final String catalinaHome;
	private final Heap heap;
	private final List<String> catalinaOpts;

	public static class Builder {

		// Required
		private final String javaHome;
		private final String catalinaHome;
		private final Heap heap;

		// Optional
		private List<String> additionalLines = ImmutableList.of();
		private List<String> catalinaOpts = ImmutableList.of();

		public Builder(String javaHome, String catalinaHome, Heap heap) {
			this.javaHome = javaHome;
			this.catalinaHome = catalinaHome;
			this.heap = heap;
		}

		public Builder additionalLines(List<String> additionalLines) {
			this.additionalLines = additionalLines;
			return this;
		}

		public Builder catalinaOpts(List<String> catalinaOpts) {
			this.catalinaOpts = catalinaOpts;
			return this;
		}

		public BashrcContext build() {
			Assert.noBlanks(javaHome, catalinaHome);
			Assert.noNulls(heap, additionalLines, catalinaOpts);
			this.additionalLines = ImmutableList.copyOf(additionalLines);
			this.catalinaOpts = ImmutableList.copyOf(catalinaOpts);
			return new BashrcContext(this);
		}
	}

	private BashrcContext(Builder builder) {
		this.additionalLines = builder.additionalLines;
		this.javaHome = builder.javaHome;
		this.catalinaHome = builder.catalinaHome;
		this.heap = builder.heap;
		this.catalinaOpts = builder.catalinaOpts;
	}

	public List<String> getAdditionalLines() {
		return additionalLines;
	}

	public String getJavaHome() {
		return javaHome;
	}

	public String getCatalinaHome() {
		return catalinaHome;
	}

	public Heap getHeap() {
		return heap;
	}

	public List<String> getCatalinaOpts() {
		return catalinaOpts;
	}

}
