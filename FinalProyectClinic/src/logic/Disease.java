package logic;

public class Disease {

	private String code;
	private String name;
	private String description;
	private boolean watched;
	
	public Disease(String code, String name, String description, boolean watched) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.watched = watched;
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
	public boolean isWatched() {
		return watched;
	}
	public void setWatched(boolean watched) {
		this.watched = watched;
	}
	
	
	
	

}
