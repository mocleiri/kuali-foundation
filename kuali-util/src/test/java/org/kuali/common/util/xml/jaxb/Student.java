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

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@XmlRootElement
@XmlBind(classes = { Club.class, Sport.class })
public final class Student {

	@XmlAttribute
	private final String name;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<Club> clubs;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<Sport> sports;

	@XmlElement
	@XmlJavaTypeAdapter(OmitOptionalAdapter.class)
	private final Optional<String> ethnicity;

	public String getName() {
		return name;
	}

	public List<Club> getClubs() {
		return clubs;
	}

	public List<Sport> getSports() {
		return sports;
	}

	public static class Builder {

		// Required
		private final String name;

		// Optional
		private List<Club> clubs = Collections.<Club> emptyList();
		private List<Sport> sports = Collections.<Sport> emptyList();
		private Optional<String> ethnicity = Optional.<String> absent();

		public Builder(String name) {
			this.name = name;
		}

		public Builder ethnicity(String ethnicity) {
			Assert.noBlanks(ethnicity);
			this.ethnicity = Optional.of(ethnicity);
			return this;
		}

		public Builder club(Club club) {
			this.clubs = CollectionUtils.singletonList(club);
			return this;
		}

		public Builder clubs(List<Club> clubs) {
			this.clubs = clubs;
			return this;
		}

		public Builder sport(Sport sport) {
			this.sports = CollectionUtils.singletonList(sport);
			return this;
		}

		public Builder sports(List<Sport> sports) {
			this.sports = sports;
			return this;
		}

		private Builder finish() {
			Assert.noBlanks(name);
			Assert.noNulls(clubs, sports, ethnicity);
			this.clubs = ImmutableList.<Club> copyOf(clubs);
			this.sports = ImmutableList.<Sport> copyOf(sports);
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
		this.sports = builder.sports;
		this.ethnicity = builder.ethnicity;
	}

}
