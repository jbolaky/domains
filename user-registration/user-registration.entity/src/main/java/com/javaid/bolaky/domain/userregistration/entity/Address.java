package com.javaid.bolaky.domain.userregistration.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.TypeDef;

import com.javaid.bolaky.domain.jpa.entity.AbstractTimestampUsernameEntity;
import com.javaid.bolaky.domain.userregistration.hibernate.group.MandatoryDataRules;

@Entity
@Table(name = "ADDRESS")
@TypeDef(name = "hibernate_persistentDateTime", typeClass = org.joda.time.contrib.hibernate.PersistentDateTime.class)
public class Address extends AbstractTimestampUsernameEntity {

	private static final long serialVersionUID = 2516961564567192851L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRESS_ID")
	private Long addressId;

	@Column(name = "ADDRESS_LINE_1")
	private String addressLine1;

	@Column(name = "ADDRESS_LINE_2")
	private String addressLine2;

	@Column(name = "ADDRESS_LINE_3")
	private String addressLine3;

	@Column(name = "POST_CODE")
	private String postCode;

	@NotNull(groups=MandatoryDataRules.class,message="P24")
	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@NotNull(groups=MandatoryDataRules.class,message="P25")
	@Column(name = "TOWN_CODE")
	private String townCode;

	@Column(name = "PROVINCE_CODE")
	private String provinceCode;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSON_ID")
	private Person person;

	/**
	 * for hibernate
	 */
	protected Address() {
	}

	public Address(String addressLine1, String countryCode, String postCode,
			String townCode, String provinceCode) {
		super();
		this.addressLine1 = addressLine1;
		this.postCode = postCode;
		this.countryCode = countryCode;
		this.townCode = townCode;
		this.provinceCode = provinceCode;
	}

	public Address(String addressLine1, String addressLine2,
			String addressLine3, String postCode, String countryCode,
			String townCode, String provinceCode) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.postCode = postCode;
		this.countryCode = countryCode;
		this.townCode = townCode;
		this.provinceCode = provinceCode;
	}

	public Long getAddressId() {
		return addressId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getTownCode() {
		return townCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public Person getPerson() {
		return person;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public void setPerson(Person person) {
		this.person = person;
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

		Address rhs = (Address) obj;

		return new EqualsBuilder().appendSuper(super.equals(obj))
				.append(addressId, rhs.addressId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(11, 11).append(addressId).toHashCode();

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
