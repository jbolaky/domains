package com.javaid.bolaky.domain.userregistration.enumerated;

public enum PersonErrorCode {

	TOO_YOUNG_TO_HAVE_LICENSE("P10");

	private String code;

	private PersonErrorCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
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
