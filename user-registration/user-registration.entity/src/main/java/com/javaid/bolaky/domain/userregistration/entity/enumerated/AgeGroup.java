package com.javaid.bolaky.domain.userregistration.entity.enumerated;

public enum AgeGroup {

	LESS_OR_EQUAL_FIFTHTEEN(15), SIXTEEN_TO_SEVENTEEN(17), EIGHTTEEN_TO_TWENTY(
			20), TWENTY_ONE_TO_TWENTYFIVE(25), TWENTYSIX_TO_THIRTY(30), THIRTY_ONE_TO_THIRTYFIVE(
			35), THIRTSIX_TO_FOURTY(40), FOURTY_ONE_AND_ABOVE(45);

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
