package logic;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Appoinment implements Serializable{

	private static final long serialVersionUID = 8538556223435197685L;
	private String code;
	private String name;
	private String ssn;
	private String phoneNumber;
	private String description;
	private LocalDateTime date = null;
	private Medic medic = null;
	private String status;
	
	public Appoinment(String code, String name, String ssn, String phoneNumber, String description, LocalDateTime date ,Medic medic, String status) {
		super();
		this.code = code;
		this.name = name;
		this.ssn = ssn;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.date = date;
		this.medic = medic;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
