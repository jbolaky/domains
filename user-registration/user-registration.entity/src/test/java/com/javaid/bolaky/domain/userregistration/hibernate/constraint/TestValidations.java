package com.javaid.bolaky.domain.userregistration.hibernate.constraint;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import com.javaid.bolaky.domain.userregistration.entity.Address;
import com.javaid.bolaky.domain.userregistration.entity.Person;
import com.javaid.bolaky.domain.userregistration.enumerated.PersonErrorCode;
import com.javaid.bolaky.domain.userregistration.hibernate.group.MandatoryDataRules;

public class TestValidations {

	@Test
	public void testGettingErrorFromAgeWithLicenseCheck() {

		Person person = new Person();
		person.setValidLicense(true);

		Set<PersonErrorCode> personErrorCodes = person
				.validate(MandatoryDataRules.class);

		boolean hasFound = false;
		for (PersonErrorCode personErrorCode : personErrorCodes) {

			if (personErrorCode.equals(PersonErrorCode.PERSON_AGE_CONSTRAINT)) {
				hasFound = true;
			}
		}

		assertThat(hasFound, is(true));
	}

	@Test
	public void testGettingConstraintsWhenPersonIsEmpty() {

		Person person = new Person();

		Set<PersonErrorCode> personErrorCodes = person
				.validate(MandatoryDataRules.class);

		assertThat(personErrorCodes.size(), is(10));
	}

	@Test
	public void testInvalidEmailConstraint() {

		Person person = new Person();
		person.getContactDetails().setEmailAddress("a");

		Set<PersonErrorCode> personErrorCodes = person
				.validate(MandatoryDataRules.class);

		boolean hasFound = false;
		for (PersonErrorCode personErrorCode : personErrorCodes) {

			if (personErrorCode.equals(PersonErrorCode.PERSON_EMAIL_INVALID)) {
				hasFound = true;
			}
		}

		assertThat(hasFound, is(true));
	}

	@Test
	public void testValidEmailConstraint() {

		Person person = new Person();
		person.getContactDetails().setEmailAddress("javaid@gmail.com");

		Set<PersonErrorCode> personErrorCodes = person
				.validate(MandatoryDataRules.class);

		boolean hasFound = false;
		for (PersonErrorCode personErrorCode : personErrorCodes) {

			if (personErrorCode.equals(PersonErrorCode.PERSON_EMAIL_INVALID)) {
				hasFound = true;
			}
		}

		assertThat(hasFound, is(false));
	}

	@Test
	public void testProvidedInvalidAddressConstraint() {

		Person person = new Person();
		Address address = new Address(null, null, null, null, null);
		person.getContactDetails().setEmailAddress("javaid@gmail.com");
		person.getContactDetails().addAddress(address);

		Set<PersonErrorCode> personErrorCodes = person
				.validate(MandatoryDataRules.class);

		boolean hasFound = false;
		for (PersonErrorCode personErrorCode : personErrorCodes) {

			if (personErrorCode.equals(PersonErrorCode.PERSON_EMAIL_INVALID)) {
				hasFound = true;
			}
		}

		assertThat(hasFound, is(false));

		hasFound = false;
		for (PersonErrorCode personErrorCode : personErrorCodes) {

			if (personErrorCode
					.equals(PersonErrorCode.PERSON_LIST_OF_ADDRESS_ZERO)) {
				hasFound = true;
			}
		}

		assertThat(hasFound, is(false));

		hasFound = false;
		for (PersonErrorCode personErrorCode : personErrorCodes) {

			if (personErrorCode
					.equals(PersonErrorCode.PERSON_COUNTRY_CODE_NULL)) {
				hasFound = true;
			}
		}

		assertThat(hasFound, is(true));
	}
}
