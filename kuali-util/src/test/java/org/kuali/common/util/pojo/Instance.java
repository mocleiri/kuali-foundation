package org.kuali.common.util.pojo;

import java.util.Map;

public class Instance {

	Reservation reservation;
	Map<String, String> properties;

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}
