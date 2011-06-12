package com.javaid.bolaky.domain.hibernate.interceptor.api;

import java.io.Serializable;

import org.joda.time.DateTime;

public interface TimestampEntity extends Serializable {

	public DateTime getCreationDateTime();

	public DateTime getLastModifiedDateTime();
}
