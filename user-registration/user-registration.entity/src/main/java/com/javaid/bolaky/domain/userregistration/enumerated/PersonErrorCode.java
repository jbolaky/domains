package com.javaid.bolaky.domain.userregistration.enumerated;

public enum PersonErrorCode {

	PERSON_AGE_CONSTRAINT("P10","person having a valid license should be at least 18 years old"),
	PERSON_USERNAME_NULL("P11","username cannot be null"),
	PERSON_USERNAME_SIZE("P12","username size should be of minimum 8 lenght"),
	PERSON_PASSWORD_NULL("P13","password cannot be null"),
	PERSON_PASSWORD_SIZE("P14","password size should be of minimum 8 lenght"),
	PERSON_FIRSTNAME_NULL("P15","firstname cannot be null"),
	PERSON_LASTNAME_NULL("P16","lastname cannot be null"),
	PERSON_AGEGROUP_NULL("P17","age group cannot be null"),
	PERSON_GENDER_NULL("P18","gender cannot be null"),
	PERSON_VALID_LICENSE_NULL("P19","valid license cannot be null"),
	PERSON_EMAIL_INVALID("P21","email not in correct format"),
	PERSON_EMAIL_NULL("P20","email address cannot be null"),
	PERSON_LIST_OF_ADDRESS_NULL("P22","list of addresses cannot be null"),
	PERSON_LIST_OF_ADDRESS_ZERO("P23","should contain at least one address"),
	PERSON_COUNTRY_CODE_NULL("P24","country code cannot be null"),
	PERSON_TOWN_CODE_NULL("P25","town code cannot be null");

	private String code;
	private String descripion;

	private PersonErrorCode(String code,String descripion) {
		this.code = code;
		this.descripion=descripion;
	}

	public String getCode() {
		return code;
	}

	public String getDescripion() {
		return descripion;
	}

	public static PersonErrorCode convertFrom(String code) {

		if (code != null) {

			for (PersonErrorCode personErrorCode : values()) {

				if (code.equalsIgnoreCase(personErrorCode.getCode())) {

					return personErrorCode;
				}
			}
		}

		return null;
	}
}
