package logic;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import login.User;


public class Clinic implements Serializable{

	private static final long serialVersionUID = 739782741399225301L;
	private ArrayList<Person> myPersons = null;
	private ArrayList<CheckUp> myCheckUps = null;
	private ArrayList<Appoinment> myAppoinments = null;
	private ArrayList<Vaccine> myVaccines = null;
	private ArrayList<Disease> myDiseases = null;
	private ArrayList<User> myUsers = null;
	private static Clinic clinic = null;
	private static User loginUser = null;
	public static int codePerson = 1;
	public static int codeCheckUp = 1;
	public static int codeAppoinment = 1;
	public static int codeVaccine = 1;
	public static int codeDisease = 1;
	
	private Clinic() {
		this.myPersons = new ArrayList<Person>();
		this.myCheckUps = new ArrayList<CheckUp>();
		this.myAppoinments = new ArrayList<Appoinment>();
		this.myVaccines = new ArrayList<Vaccine>();
		this.myDiseases = new ArrayList<Disease>();
		this.myUsers = new ArrayList<User>();
	}
	
	public static Clinic getInstance() {
		if (clinic == null) {
			clinic = new Clinic();
		}
		return clinic;
	}

	public ArrayList<Person> getMyPersons() {
		return myPersons;
	}

	public void setMyPersons(ArrayList<Person> myPersons) {
		this.myPersons = myPersons;
	}

	public ArrayList<CheckUp> getMyCheckUps() {
		return myCheckUps;
	}

	public void setMyCheckUps(ArrayList<CheckUp> myCheckUps) {
		this.myCheckUps = myCheckUps;
	}

	public ArrayList<Appoinment> getMyAppoinments() {
		return myAppoinments;
	}

	public void setMyAppoinments(ArrayList<Appoinment> myAppoinments) {
		this.myAppoinments = myAppoinments;
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
	
	public static Clinic getClinic() {
		return clinic;
	}

	public static void setClinic(Clinic clinic) {
		Clinic.clinic = clinic;
	}

	public void setMyDiseases(ArrayList<Disease> myDiseases) {
		this.myDiseases = myDiseases;
	}
	
	public ArrayList<User> getMyUsers() {
		return myUsers;
	}

	public void setMyUsers(ArrayList<User> myUsers) {
		this.myUsers = myUsers;
	}

	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Clinic.loginUser = loginUser;
	}
	
	//searching algorithms
	
	//searching object by code
	public Person searchPerson(String ssn) {
		for (Person per : myPersons) {
			if (per.getSsn().equalsIgnoreCase(ssn)) {
				return per;
			}
		}
		return null;
	}
	
	public CheckUp searchCheckUp(String code) {
		for (CheckUp che : myCheckUps) {
			if (che.getCode().equalsIgnoreCase(code)) {
				return che;
			}
		}
		return null;
	}
	
	public Appoinment searchAppoinment(String code) {
		for (Appoinment app : myAppoinments) {
			if (app.getCode().equalsIgnoreCase(code)) {
				return app;
			}
		}
		return null;
	}
	
	public Vaccine searchVaccine(String code) {
		for (Vaccine vac : myVaccines) {
			if (vac.getCode().equalsIgnoreCase(code)) {
				return vac;
			}
		}
		return null;
	}
	
	public Disease searchDisease(String code) {
		for (Disease dis : myDiseases) {
			if (dis.getCode().equalsIgnoreCase(code)) {
				return dis;
			}
		}
		return null;
	}
	
	//searching index by code
	public int getIndexUser(String name) {
		int i = 0;
		for (User user : myUsers) {
			if (user.getName().equalsIgnoreCase(name)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public int getIndexPerson(String codigo) {
		int i = 0;
		for (Person per : myPersons) {
			if (per.getCode().equalsIgnoreCase(codigo)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public int getIndexCheckUp(String codigo) {
		int i = 0;
		for (CheckUp che : myCheckUps) {
			if (che.getCode().equalsIgnoreCase(codigo)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public int getIndexAppoinment(String codigo) {
		int i = 0;
		for (Appoinment app : myAppoinments) {
			if (app.getCode().equalsIgnoreCase(codigo)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public int getIndexvaccine(String codigo) {
		int i = 0;
		for (Vaccine vac : myVaccines) {
			if (vac.getCode().equalsIgnoreCase(codigo)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public int getIndexDisease(String codigo) {
		int i = 0;
		for (Disease dis : myDiseases) {
			if (dis.getCode().equalsIgnoreCase(codigo)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	//insertion methods
	public void insertUser(User user) {
		myUsers.add(user);
	}
	
	public void insertPerson(Person person) {
		myPersons.add(person);
	}
	
	public void insertCheckUp(CheckUp checkUp) {
		myCheckUps.add(checkUp);
	}
	
	public void insertAppoinment(Appoinment appoinment) {
		myAppoinments.add(appoinment);
	}
	
	public void insertVaccine(Vaccine vaccine) {
		myVaccines.add(vaccine);
	}
	
	public void insertDisease(Disease disease) {
		myDiseases.add(disease);
	}
	
	//modified methods
	public void modifiedUser(User user) {
		myUsers.set(getIndexUser(user.getName()), user);
	}

	public void modifiedVaccine(Vaccine vaccine) {
		myVaccines.set(getIndexvaccine(vaccine.getName()), vaccine);
	}

	public void modifiedDisease(Disease disease) {
		myDiseases.set(getIndexDisease(disease.getName()), disease);
	}
	
	//remove methods
	public void removeUser(User user) {
		myUsers.remove(user);
	}
	
	public void removePerson(Person person) {
		myPersons.remove(person);
	}
	
	public void removeCheckUp(CheckUp checkUp) {
		myCheckUps.remove(checkUp);
	}
	
	public void removeAppoinment(Appoinment appoinment) {
		myAppoinments.remove(appoinment);
	}
	
	public void removeVaccine(Vaccine vaccine) {
		myVaccines.remove(vaccine);
	}
	
	public void removeDisease(Disease disease) {
		myDiseases.remove(disease);
	}
	
	
	
	//analytics methods
	public int totalPacienteGenero(char sex) {
		int total = 0;
		
		for (Person per : myPersons) {
			if (per instanceof Patient && per.getSex() == sex) {
				total++;
			}
		}
		
		return total;
	}
	
	public int totalPatientDiseaseWith(String code) {
		int total = 0;
		
		for (Person per : myPersons) {
			if (per instanceof Patient) {
				Patient pat = (Patient) per;
				for (int i = 0; i < pat.getMyDiseases().size(); i++) {
					if (pat.getMyDiseases().get(i).getCode().equalsIgnoreCase(code) && pat.getMyDiseases().get(i).isWatched()) {
						total++;
					}
				}
			}
		}
		
		return total;
	}
	
	public int totalPatientsCheck() {
		return myAppoinments.size();
	}

	
	
	//miscellaneous methods
	
	public ArrayList<Appoinment> getAppoinmentsOfMedic(String codeMedic) {
		ArrayList<Appoinment> appoinments = new ArrayList<>();
		
		for (Appoinment appoinment : myAppoinments) {
			if (appoinment.getStatus().equalsIgnoreCase("Espera")) {
				if (appoinment.getMedic().getCode().equalsIgnoreCase(codeMedic)) {
					appoinments.add(appoinment);
				}
			}
		}
		
		return appoinments;
	}
	
	public boolean validUser(String username, String psw) {
		for(User user: myUsers) {
			if(user.getName().equals(username) && user.getPassword().equals(psw)) {
				loginUser = user;
				return true; 
			}
		}
		
		return false;
	}
	
	public boolean isUniquePhoneNumber(String number) {
		
		for (Person per : myPersons) {
			if (per.getPhoneNumber().equalsIgnoreCase(number)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isUniqueSsnNumber(String number) {
		
		for (Person per : myPersons) {
			if (per.getSsn().equalsIgnoreCase(number)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isUniqueUserName(String name) {
		
		for (User use : myUsers) {
			if (use.getName().equalsIgnoreCase(name)) {
				return false;
			}
		}
		
		return true;
	}
	
}
