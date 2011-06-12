package com.javaid.bolaky.domain.userregistration.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaid.bolaky.domain.userregistration.entity.Person;

public interface UserRegistrationRepository extends JpaRepository<Person, Long> {

	
}
