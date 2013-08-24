package org.kuali.common.util.xml.jaxb;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;

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
	@XmlJavaTypeAdapter(OmitOptionalStringAdapter.class)
	private final Optional<String> ethnicity;

	@XmlElement
	@XmlJavaTypeAdapter(OmitOptionalIntegerAdapter.class)
	private final Optional<Integer> iq;

	public Optional<Integer> getIq() {
		return iq;
	}

	public Optional<String> getEthnicity() {
		return ethnicity;
	}

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
		private Optional<Integer> iq = Optional.<Integer> absent();

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
			Assert.noNulls(clubs, sports, ethnicity, iq);
			this.clubs = ListUtils.newImmutableArrayList(clubs);
			this.sports = ListUtils.newImmutableArrayList(sports);
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
		this.ethnicity = builder.ethnicity;
		this.sports = builder.sports;
		this.iq = builder.iq;
	}

}
