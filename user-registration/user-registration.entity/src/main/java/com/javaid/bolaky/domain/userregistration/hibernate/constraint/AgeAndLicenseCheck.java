package com.javaid.bolaky.domain.userregistration.hibernate.constraint;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.javaid.bolaky.domain.userregistration.hibernate.validator.AgeAndLicenseCheckValidator;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = AgeAndLicenseCheckValidator.class)
@Documented
public @interface AgeAndLicenseCheck {

	String message() default "P10";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
