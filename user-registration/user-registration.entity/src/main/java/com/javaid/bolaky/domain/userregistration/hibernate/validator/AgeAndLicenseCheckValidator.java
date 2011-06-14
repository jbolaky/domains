package com.javaid.bolaky.domain.userregistration.hibernate.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.javaid.bolaky.domain.userregistration.entity.Person;
import com.javaid.bolaky.domain.userregistration.entity.enumerated.AgeGroup;
import com.javaid.bolaky.domain.userregistration.hibernate.constraint.AgeAndLicenseCheck;

public class AgeAndLicenseCheckValidator implements
		ConstraintValidator<AgeAndLicenseCheck, Person> {

	public void initialize(AgeAndLicenseCheck constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	public boolean isValid(Person person, ConstraintValidatorContext context) {

		boolean isValid = false;

		if (person != null && person.hasValidLicense() != null
				&& person.hasValidLicense()) {

			if ((person.getAge() != null && person.getAge() >= 18)
					|| (person.getAgeGroup() != null && (person.getAgeGroup()
							.equals(AgeGroup.LESS_OR_EQUAL_FIFTHTEEN) || person
							.getAgeGroup()
							.equals(AgeGroup.SIXTEEN_TO_SEVENTEEN)))) {

				isValid = true;
			}
		}

		return isValid;
	}

}