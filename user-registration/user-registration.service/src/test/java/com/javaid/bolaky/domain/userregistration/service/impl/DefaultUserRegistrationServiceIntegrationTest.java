package com.javaid.bolaky.domain.userregistration.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javaid.bolaky.domain.userregistration.entity.Address;
import com.javaid.bolaky.domain.userregistration.entity.Person;
import com.javaid.bolaky.domain.userregistration.entity.enumerated.AgeGroup;
import com.javaid.bolaky.domain.userregistration.entity.enumerated.Gender;
import com.javaid.bolaky.domain.userregistration.service.api.UserRegistrationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/default-user-registration-service-context.xml",
		"classpath:/default-user-registration-datasource-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional(propagation = Propagation.REQUIRED)
public class DefaultUserRegistrationServiceIntegrationTest {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@Before
	public void populateUsername() {
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				"Javaid", "Jav");
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Test
	public void saveAndRetrievePerson() {
		Person person = createPerson("Javaid", "password", "javaid", "javaid",
				2, AgeGroup.THIRTY_ONE_TO_THIRTYFIVE, true, false, true, false,
				Gender.MALE, true, "javaid.bolaky@tnt.com", "2307768487");

		Address address = new Address("ADDRESS_LINE_1", "MRU", "23000", "PL",
				"PL");
		person.getContactDetails().addAddress(address);
		Address address2 = new Address("ADDRESS_LINE_3", "MU", "23000", "BB",
				"BB");
		person.getContactDetails().addAddress(address2);

		person = userRegistrationService.savePerson(person);
		assertThat(person.getCreationUsername(), is("Javaid"));
		assertThat(person.getPersonId(), is(notNullValue()));

		Person person2 = userRegistrationService.retrievePerson(person
				.getPersonId());
		assertPerson(person2, "Javaid", "password", "javaid", "javaid", 2,
				AgeGroup.THIRTY_ONE_TO_THIRTYFIVE, true, false, true, false,
				Gender.MALE, true, "javaid.bolaky@tnt.com", "2307768487");

		Iterator<Address> iterator = person2.getContactDetails().getAddresses()
				.iterator();

		assertAddress(iterator.next(), "ADDRESS_LINE_1", "MRU", "23000", "PL",
				"PL");

		assertAddress(iterator.next(), "ADDRESS_LINE_3", "MU", "23000", "BB",
				"BB");
	}

	private Person createPerson(String username, String password,
			String firstname, String lastname, Integer age, AgeGroup ageGroup,
			Boolean validLicense, Boolean shareCost, Boolean shareDriving,
			Boolean vehicleOwner, Gender gender, Boolean allowToReceiveUpdates,
			String emailAddress, String phoneNumber) {

		Person person = new Person();
		person.setFirstname(firstname);
		person.setLastname(lastname);
		person.setShareCost(shareCost);
		person.setShareDriving(shareDriving);
		person.setAge(age);
		person.setVehicleOwner(vehicleOwner);
		person.setValidLicense(validLicense);
		person.setAgeGroup(ageGroup);
		person.setUsername(username);
		person.setPassword(password);
		person.setGender(gender);
		person.getUserPreferences().setAllowToReceiveUpdates(
				allowToReceiveUpdates);
		person.getContactDetails().setPhoneNumber(phoneNumber);
		person.getContactDetails().setEmailAddress(emailAddress);

		return person;
	}

	private void assertPerson(Person person, String username, String password,
			String firstname, String lastname, Integer age, AgeGroup ageGroup,
			Boolean validLicense, Boolean shareCost, Boolean shareDriving,
			Boolean vehicleOwner, Gender gender, Boolean allowToReceiveUpdates,
			String emailAddress, String phoneNumber) {

		assertThat(person.getUsername(), is(username));
		assertThat(person.getPassword(), is(password));
		assertThat(person.getFirstname(), is(firstname));
		assertThat(person.getLastname(), is(lastname));
		assertThat(person.getAge(), is(age));
		assertThat(person.getAgeGroup(), is(ageGroup));
		assertThat(person.hasValidLicense(), is(validLicense));
		assertThat(person.canShareCost(), is(shareCost));
		assertThat(person.canShareDriving(), is(shareDriving));
		assertThat(person.isAVehicleOwner(), is(vehicleOwner));
		assertThat(person.getGender(), is(gender));
		assertThat(person.getUserPreferences().isAllowToReceiveUpdates(),
				is(allowToReceiveUpdates));
		assertThat(person.getContactDetails().getEmailAddresse(),
				is(emailAddress));
		assertThat(person.getContactDetails().getPhoneNumber(), is(phoneNumber));
	}

	private void assertAddress(Address address, String addressLine1,
			String countryCode, String postCode, String townCode,
			String provinceCode) {

		assertThat(address.getAddressLine1(), is(addressLine1));
		assertThat(address.getCountryName(), is(countryCode));
		assertThat(address.getPostCode(), is(postCode));
		assertThat(address.getTownName(), is(townCode));
		assertThat(address.getProvinceName(), is(provinceCode));
	}
}
