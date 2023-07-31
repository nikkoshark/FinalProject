package logic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CheckUp implements Serializable{

	private static final long serialVersionUID = -909052395961762239L;
	private String code;
	private String diagnosis;
	private String weight;
	private String height;
	private LocalDateTime date = null;
	private Medic medic = null;
	private Patient patient = null;
	private ArrayList<Disease> myDiseases = new ArrayList<>();
	private boolean medicalRecord;
	
	public CheckUp(String code, String diagnosis, String weight, String height, LocalDateTime date, Medic medic,
			Patient patient, ArrayList<Disease> myDiseases, boolean medicalRecord) {
		super();
		this.code = code;
		this.diagnosis = diagnosis;
		this.weight = weight;
		this.height = height;
		this.date = date;
		this.medic = medic;
		this.patient = patient;
		this.myDiseases = myDiseases;
		this.medicalRecord = medicalRecord;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Medic getMedic() {
		return medic;
	}

	public void setMedic(Medic medic) {
		this.medic = medic;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ArrayList<Disease> getMyDiseases() {
		return myDiseases;
	}

	public void setMyDiseases(ArrayList<Disease> myDiseases) {
		this.myDiseases = myDiseases;
	}

	public boolean isMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(boolean medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
