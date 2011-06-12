package com.javaid.bolaky.domain.userregistration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javaid.bolaky.domain.userregistration.entity.Person;
import com.javaid.bolaky.domain.userregistration.repository.api.UserRegistrationRepository;
import com.javaid.bolaky.domain.userregistration.service.api.UserRegistrationService;

public class DefaultUserRegistrationService implements UserRegistrationService {

	@Autowired
	private UserRegistrationRepository userRegistrationRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public Person savePerson(Person person) {
		return userRegistrationRepository.save(person);
	}

	@Transactional(readOnly = true)
	public Person retrievePerson(Long personId) {
		return userRegistrationRepository.findOne(personId);
	}

}
