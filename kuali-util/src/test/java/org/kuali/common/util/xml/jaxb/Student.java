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
			Assert.noNulls(clubs, sports);
			this.clubs = ListUtils.newArrayList(clubs);
			this.sports = ListUtils.newArrayList(sports);
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
	}

}
