package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.xml.jaxb.adapter.ImmutableListAdapter;
import org.kuali.common.util.xml.jaxb.adapter.OmitFalseAdapter;

import com.google.common.collect.ImmutableList;

@XmlRootElement
@XmlBind(classes = { Team.class })
public final class Sport extends Activity {

	@SuppressWarnings("unused")
	private Sport() {
		this(NullUtils.NONE);
	}

	public Sport(String name) {
		this(name, UNKNOWN_FEE, false, ImmutableList.<Team> of());
	}

	public Sport(String name, double fee, boolean contact, Team team) {
		this(name, fee, contact, ImmutableList.of(team));
	}

	public Sport(String name, double fee, boolean contact, Team... teams) {
		this(name, fee, contact, ImmutableList.copyOf(teams));
	}

	public Sport(String name, double fee, boolean contact, List<Team> teams) {
		super(fee);
		Assert.noBlanks(name);
		this.name = name;
		this.contact = contact;
		this.teams = teams;
	}

	@XmlAttribute
	private final String name;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitFalseAdapter.class)
	private final Boolean contact;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<Team> teams;

	public String getName() {
		return name;
	}

	public Boolean getContact() {
		return contact;
	}

}
