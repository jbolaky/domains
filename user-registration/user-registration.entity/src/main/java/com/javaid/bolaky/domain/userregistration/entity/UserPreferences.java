package com.javaid.bolaky.domain.userregistration.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

@Embeddable
public class UserPreferences {

	@Column(name = "RECEIVE_UPDATES")
	@Type(type = "yes_no")
	private Boolean allowToReceiveUpdates;

	public Boolean isAllowToReceiveUpdates() {
		return allowToReceiveUpdates;
	}

	public void setAllowToReceiveUpdates(Boolean allowToReceiveUpdates) {
		this.allowToReceiveUpdates = allowToReceiveUpdates;
	}

}
