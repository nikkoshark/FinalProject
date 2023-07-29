package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Patient extends Person implements Serializable{

	private static final long serialVersionUID = -1364712702441777544L;
	private String bloodType;
	private String email;
	private ArrayList<CheckUp> myCheckUpsRecord = null;
	private ArrayList<CheckUp> myMedicalRecord = null;
	private ArrayList<Vaccine> myVaccines = null;
	private ArrayList<Disease> myDiseases = null;

	public Patient(String code, String ssn, String name, String lastName, String phoneNumber, String address,
			Date birthdate, char sex, String bloodType, String email) {
		super(code, ssn, name, lastName, phoneNumber, address, birthdate, sex);
		this.bloodType = bloodType;
		this.email = email;
		this.myCheckUpsRecord = new ArrayList<>();
		this.myMedicalRecord = new ArrayList<>();
		this.myVaccines = new ArrayList<>();
		this.myDiseases = new ArrayList<>();
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<CheckUp> getMyCheckUpsRecord() {
		return myCheckUpsRecord;
	}

	public void setMyCheckUpsRecord(ArrayList<CheckUp> myCheckUpsRecord) {
		this.myCheckUpsRecord = myCheckUpsRecord;
	}

	public ArrayList<CheckUp> getMyMedicalRecord() {
		return myMedicalRecord;
	}

	public void setMyMedicalRecord(ArrayList<CheckUp> myMedicalRecord) {
		this.myMedicalRecord = myMedicalRecord;
	}

	public ArrayList<Vaccine> getMyVaccines() {
		return myVaccines;
	}

	public void setMyVaccines(ArrayList<Vaccine> myVaccines) {
		this.myVaccines = myVaccines;
	}

	public ArrayList<Disease> getMyDiseases() {
		return myDiseases;
	}

	public void setMyDiseases(ArrayList<Disease> myDiseases) {
		this.myDiseases = myDiseases;
	}

	
	
}
