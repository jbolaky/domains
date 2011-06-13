package com.javaid.bolaky.domain.userregistration.hibernate.constraint;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import com.javaid.bolaky.domain.userregistration.entity.Person;
import com.javaid.bolaky.domain.userregistration.enumerated.PersonErrorCode;

public class TestValidations {

	@Test
	public void testGettingErrorFromAgeWithLicenseCheck() {

		Person person = new Person();
		person.setValidLicense(true);

		Set<PersonErrorCode> personErrorCodes = person.validate();

		boolean hasFound = false;
		for (PersonErrorCode personErrorCode : personErrorCodes) {

			if (personErrorCode
					.equals(PersonErrorCode.TOO_YOUNG_TO_HAVE_LICENSE)) {
				hasFound = true;
			}
		}

		assertThat(hasFound, is(hasFound));
	}

}
