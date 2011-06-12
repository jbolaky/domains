package com.javaid.bolaky.domain.userregistration.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.javaid.bolaky.domain.jpa.entity.AbstractTimestampUsernameEntity;
import com.javaid.bolaky.domain.userregistration.entity.enumerated.AgeGroup;
import com.javaid.bolaky.domain.userregistration.entity.enumerated.Gender;

@Entity
@Table(name = "PERSON")
@TypeDefs({
		@TypeDef(name = "hibernate_persistentDateTime", typeClass = org.joda.time.contrib.hibernate.PersistentDateTime.class),
		@TypeDef(name = "gender_user_types", typeClass = com.javaid.bolaky.domain.hibernate.jpa.enumeration.GenericEnumUserType.class, parameters = @Parameter(name = "type", value = "com.javaid.bolaky.domain.userregistration.entity.enumerated.Gender")),
		@TypeDef(name = "age_user_types", typeClass = com.javaid.bolaky.domain.hibernate.jpa.enumeration.GenericEnumUserType.class, parameters = @Parameter(name = "type", value = "com.javaid.bolaky.domain.userregistration.entity.enumerated.AgeGroup")) })
public class Person extends AbstractTimestampUsernameEntity {

	private static final long serialVersionUID = -687567688155349393L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_ID", nullable = false)
	private Long personId;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "FIRSTNAME")
	private String firstname;

	@Column(name = "LASTNAME")
	private String lastname;

	@Column(name = "AGE")
	private Integer age;

	@Column(name = "AGE_GROUP")
	@Type(type = "age_user_types")
	private AgeGroup ageGroup;

	@Column(name = "GENDER")
	@Type(type = "gender_user_types")
	private Gender gender;

	@Column(name = "VALID_LICENSE_IND")
	@Type(type = "yes_no")
	private Boolean validLicense;

	@Column(name = "VEHICLE_OWNER")
	@Type(type = "yes_no")
	private Boolean vehicleOwner;

	@Column(name = "SHARE_COST_IND")
	@Type(type = "yes_no")
	private Boolean shareCost;

	@Column(name = "SHARE_DRIVING_IND")
	@Type(type = "yes_no")
	private Boolean shareDriving;

	@Embedded
	private UserPreferences userPreferences = new UserPreferences();

	@Embedded
	private ContactDetails contactDetails = new ContactDetails(this);

	public Long getPersonId() {
		return personId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Integer getAge() {
		return age;
	}

	public AgeGroup getAgeGroup() {
		return ageGroup;
	}

	public Gender getGender() {
		return gender;
	}

	public Boolean hasValidLicense() {
		return validLicense;
	}

	public Boolean isAVehicleOwner() {
		return vehicleOwner;
	}

	public Boolean canShareCost() {
		return shareCost;
	}

	public Boolean canShareDriving() {
		return shareDriving;
	}

	public UserPreferences getUserPreferences() {
		return userPreferences;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setAgeGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setValidLicense(Boolean validLicense) {
		this.validLicense = validLicense;
	}

	public void setVehicleOwner(Boolean vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}

	public void setShareCost(Boolean shareCost) {
		this.shareCost = shareCost;
	}

	public void setShareDriving(Boolean shareDriving) {
		this.shareDriving = shareDriving;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		Person rhs = (Person) obj;

		return new EqualsBuilder().appendSuper(super.equals(obj))
				.append(personId, rhs.personId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(9, 11).append(personId).toHashCode();

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
