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
	LocalDateTime date = null;
	private Medic medic = null;
	private Patient patient = null;
	private ArrayList<Disease> myDiseases = null;
	private boolean medicalRecord;
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
