package logic;

import java.io.Serializable;
import java.util.Date;

public abstract class Person implements Serializable{

	private static final long serialVersionUID = -3652014729598442801L;
	protected String code;
	protected String ssn;
	protected String name;
	protected String lastName;
	protected String phoneNumber;
	protected String address;
	protected Date birthdate;
	protected char sex;
	
	public Person(String code, String ssn, String name, String lastName, String phoneNumber, String address,
			Date birthdate, char sex) {
		super();
		this.code = code;
		this.ssn = ssn;
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.birthdate = birthdate;
		this.sex = sex;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}
	
	
	
}
