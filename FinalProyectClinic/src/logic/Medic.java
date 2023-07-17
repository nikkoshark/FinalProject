package logic;

import java.io.Serializable;
import java.util.Date;

public class Medic extends Person implements Serializable{

	private static final long serialVersionUID = 2108777428989665770L;
	private String speciality;
	private boolean available;

	public Medic(String code, String ssn, String name, String lastName, String phoneNumber, String address,
			Date birthdate, char sex, String speciality, boolean available) {
		super(code, ssn, name, lastName, phoneNumber, address, birthdate, sex);
		this.speciality = speciality;
		this.available = available;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	
}
