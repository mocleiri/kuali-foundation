package org.kuali.common.util.xml.jaxb;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.nullify.NullUtils;

@XmlRootElement
public final class Student {

	@XmlAttribute
	private final String name;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListWrapperAdapter.class)
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

		private Builder finish() {
			Assert.noBlanks(name);
			Assert.noNulls(clubs);
			this.clubs = Collections.unmodifiableList(clubs);
			return this;
		}

		public Student build() {
			finish();
			return new Student(this);
		}
	}

	private Student() {
		this(new Builder(NullUtils.NONE).finish());
	}

	private Student(Builder builder) {
		this.name = builder.name;
		this.clubs = builder.clubs;
	}

}
