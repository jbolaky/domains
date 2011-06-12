package com.javaid.bolaky.domain.userregistration.service.api;

import com.javaid.bolaky.domain.userregistration.entity.Person;

public interface UserRegistrationService {

	Person savePerson(Person person);
	
	Person retrievePerson(Long personId);
}
