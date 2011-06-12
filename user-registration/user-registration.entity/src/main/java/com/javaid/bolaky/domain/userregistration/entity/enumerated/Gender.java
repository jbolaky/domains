package com.javaid.bolaky.domain.userregistration.entity.enumerated;

public enum Gender {

	MALE("M"), FEMALE("F");

	private String code;

	private Gender(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public static Gender convertCode(String genderCode) {

		if (genderCode != null) {

			for (Gender gender : values()) {

				if (genderCode.equalsIgnoreCase(gender.getCode())) {

					return gender;
				}
			}
		}

		return null;
	}
}
