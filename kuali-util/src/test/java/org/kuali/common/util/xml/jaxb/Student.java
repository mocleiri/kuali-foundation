package org.kuali.common.util.xml.jaxb;

import java.util.Collections;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

public final class Student {

	private final String name;
	private final List<Club> clubs;

	public String getName() {
		return name;
	}

	public List<Club> getClubs() {
		return clubs;
	}

	public static class Builder {

		private final String name;
		private List<Club> clubs = Collections.<Club> emptyList();

		public Builder(String name) {
			this.name = name;
		}

		public Builder club(Club club) {
			this.clubs = CollectionUtils.singletonList(club);
			return this;
		}

		public Builder clubs(List<Club> clubs) {
			this.clubs = clubs;
			return this;
		}

		public Student build() {
			Assert.noBlanks(name);
			return new Student(this);
		}
	}

	private Student(Builder builder) {
		this.name = builder.name;
		this.clubs = builder.clubs;
	}

}
