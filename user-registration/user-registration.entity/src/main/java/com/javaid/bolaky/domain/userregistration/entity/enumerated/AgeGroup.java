package com.javaid.bolaky.domain.userregistration.entity.enumerated;

public enum AgeGroup {

	LESS_THAN_FIFTHTEEN(15), FIFTNTEEN_TO_TWENTY(20), TWENTY_TO_TWENTYFIVE(25), TWENTYFIVE_TO_THIRTY(
			30), THIRTY_TO_THIRTYFIVE(35), THIRTFIVE_TO_FOURTY(40), FOURTY_AND_ABOVE(
			45);

	private Integer code;

	private AgeGroup(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	
	
	public static AgeGroup convertCode(Integer age) {

		if (age != null) {

			for (AgeGroup ageGroup : values()) {

				if (age < ageGroup.getCode()) {
					return ageGroup;
				}
			}
		}

		return null;
	}
}
