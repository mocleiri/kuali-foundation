package org.kuali.common.util.build;

import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.util.create.Creation;
import org.kuali.common.util.create.Creator;

public abstract class AwesomeBuilder<T> implements InstanceBuilder<T> {

	protected Creator creator = Creation.getDefaultCreator();

	@Override
	public final T build() {
		checkNotNull(creator, "'creator' cannot be null'");
		return creator.create(this);
	}

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

}
