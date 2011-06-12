package com.javaid.bolaky.domain.hibernate.interceptor.impl;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.PropertyAccessException;
import org.hibernate.type.Type;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.javaid.bolaky.domain.jpa.entity.AbstractTimestampUsernameEntity;

public class UsernameInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 2089934963550220631L;
	private static final String CREATION_USERNAME_FIELD_NAME = "creationUsername";
	private static final String LAST_MODIFIED_BY_FIELD_NAME = "lastModifiedUsername";

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		if (entity instanceof AbstractTimestampUsernameEntity) {
			setTimestamps(entity, new String[] { CREATION_USERNAME_FIELD_NAME,
					LAST_MODIFIED_BY_FIELD_NAME }, state, propertyNames);
		}

		return true;
	}

	private void setTimestamps(Object entity, String[] fields, Object[] state,
			String[] propertyNames) {

		final Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();

		if (authentication == null) {
			throw new AuthenticationCredentialsNotFoundException(
					"No user was populated in SecurityContextHolder");
		}

		final String username = authentication.getName();

		if (username == null || username.length() == 0) {
			throw new AuthenticationCredentialsNotFoundException(
					"UserName was null for Authentication");
		}

		for (String nextField : fields) {
			try {

				for (int propertyNamesIdx = 0; propertyNamesIdx < propertyNames.length; ++propertyNamesIdx) {

					if (nextField.equals(propertyNames[propertyNamesIdx])) {
						state[propertyNamesIdx] = username;
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
