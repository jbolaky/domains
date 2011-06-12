package com.javaid.bolaky.domain.hibernate.interceptor.impl;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.PropertyAccessException;
import org.hibernate.type.Type;
import org.joda.time.DateTime;

import com.javaid.bolaky.domain.hibernate.interceptor.api.TimestampEntity;

public class TimestampInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 2089934963550220631L;
	private static final String CREATION_DATE_TIME_FIELD = "creationDateTime";
	private static final String LASTMODIFIED_DATETIME_FIELD = "lastModifiedDateTime";

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		if (entity instanceof TimestampEntity) {
			setTimestamps((TimestampEntity) entity, new String[] {
					CREATION_DATE_TIME_FIELD, LASTMODIFIED_DATETIME_FIELD },
					state, propertyNames);
		}

		return true;
	}

	private void setTimestamps(Object entity, String[] fields, Object[] state,
			String[] propertyNames) {
		DateTime currentDateTime = new DateTime();

		for (String nextField : fields) {
			try {
				for (int propertyNamesIdx = 0; propertyNamesIdx < propertyNames.length; ++propertyNamesIdx) {

					if (CREATION_DATE_TIME_FIELD
							.equals(propertyNames[propertyNamesIdx])
							&& state[propertyNamesIdx] != null) {
						continue;
					}

					if (nextField.equals(propertyNames[propertyNamesIdx])) {
						state[propertyNamesIdx] = currentDateTime;
					}
				}
			} catch (IllegalArgumentException e) {
				throw new PropertyAccessException(e, "Could not access field: "
						+ e.getMessage(), false, entity.getClass(), nextField);
			} catch (SecurityException e) {
				throw new PropertyAccessException(e, "Could not access field: "
						+ e.getMessage(), false, entity.getClass(), nextField);
			}
		}
	}
}
