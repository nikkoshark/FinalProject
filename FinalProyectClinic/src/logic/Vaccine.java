package logic;

import java.io.Serializable;

public class Vaccine implements Serializable{

	private static final long serialVersionUID = 2455143234880788531L;
	private String code;
	private String name;
	private String description;
	
	public Vaccine(String code, String name, String description) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
