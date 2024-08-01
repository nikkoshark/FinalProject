package logic;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	private Integer codePerson = null;
	private Integer codeSQLPerson = null;
	private Integer codeCheckUp = null;
	private Integer codeAppoinment = null;
	private Integer codeVaccine = null;
	private Integer codeDisease = null;
	private static Connection con = SqlConnection.getConnection();
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static ResultSetMetaData rsmd;
	
	private Clinic() {
		this.myPersons = new ArrayList<Person>();
		this.myCheckUps = new ArrayList<CheckUp>();
		this.myAppoinments = new ArrayList<Appoinment>();
		this.myVaccines = new ArrayList<Vaccine>();
		this.myDiseases = new ArrayList<Disease>();
		this.myUsers = new ArrayList<User>();
		
		this.codePerson = new Integer(1);
		this.codeSQLPerson = new Integer(1);
		this.codeCheckUp = new Integer(1);
		this.codeAppoinment = new Integer(1);
		this.codeVaccine = new Integer(1);
		this.codeDisease = new Integer(1);
		
		
	}
	
	public static Clinic getInstance() {
		if (clinic == null) {
			clinic = new Clinic();
		}
		return clinic;
	}
	
	public String getSQLCodePerson() {
		//con = sql
		try {
			 con = SqlConnection.getConnection(); // Asumiendo que SqlConnection es tu clase de conexión
		        ps = con.prepareStatement("SELECT MAX(CAST(id AS INT)) FROM person");
		        rs = ps.executeQuery();

		        if (rs.next()) {
		            int maxId = rs.getInt(1); // Obtiene el valor máximo del id
		            return String.valueOf(maxId + 1); // Retorna el próximo id incrementado en 1
		        } else {
		            // Si no hay registros, el primer ID será 1
		            return "1";
		        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR en Clinic.ISUNIQUEUSERSQL. " + e.toString());
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Integer getCodePerson() {
		return codeSQLPerson;
	}
	
	public Integer getCodeCheckUp() {
		return codeCheckUp;
	}
	
	public Integer getCodeAppoinment() {
		return codeAppoinment;
	}
	
	public Integer getCodeVaccine() {
		return codeVaccine;
	}
	
	public Integer getCodeDisease() {
		return codeDisease;
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
	
	//QUERYS -------------------------------------------------------------------------------------------

	
	
	
	public boolean isUniqueSSNSQL(String textfromtxt) {

		
		try {
			ps = con.prepareStatement("SELECT ssn FROM person WHERE id=?");
			ps.setString(1, textfromtxt); //ESTE ES EL ID, PRIMERA POSICION
			rs = ps.executeQuery();
			
			if(rs.next()) 
				return false;
			
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error en Clinic.ISUNIQUESSNSQL. " + e2.toString());
			e2.printStackTrace();
		}
		return true;
	}
	
	public boolean isUniqueUserSQL(String textfromtxt) {

		try {
			ps = con.prepareStatement("SELECT username FROM [user] WHERE username=?");
			ps.setString(1, textfromtxt); //ESTE ES EL ID, PRIMERA POSICION
			rs = ps.executeQuery();
			
			if(rs.next()) 
				return false;
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR en Clinic.ISUNIQUEUSERSQL. " + e.toString());
			e.printStackTrace();
		}
		return true;
	}
	
/*
	public boolean isUniqueUserName(String name) {
		
		for (User use : myUsers) {
			if (use.getName().equalsIgnoreCase(name)) {
				return false;
			}
		}
		
		return true;
	}
	*/
	
	//searching object by code
	

    public String searchSQLPerson(String ssn) {
        String foundPersonSsn = null;

        // SQL query to find a person by their SSN
        String sql = "SELECT ssn FROM person WHERE ssn = ?";

        try {
            Connection con = SqlConnection.getConnection(); // Assuming SqlConnection is your connection utility class
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ssn);
            ResultSet rs = ps.executeQuery();

            // Check if the person exists
            if (rs.next()) {
                foundPersonSsn = rs.getString("ssn");
            }

            // Close resources
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }

        return foundPersonSsn; // Return the found SSN or null if not found
    }
	
	
	public Person searchPerson(String ssn) {
		for (Person per : myPersons) {
			if (per.getSsn().equalsIgnoreCase(ssn)) {
				return per;
			}
		}
		return null;
	}
	
	public Person searchPersonByCode(String code) {
		for (Person per : myPersons) {
			if (per.getCode().equalsIgnoreCase(code)) {
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
	
	//INSERT -------------------------------------------
	
	
	//TBD
/*
	public void insertSQLPerson(int person) {
		//myPersons.add(person);
		try {
			ps = con.prepareStatement("SELECT COUNT(id) FROM person");
			ps.executeQuery();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR en Clinic.ISUNIQUEUSERSQL. " + e.toString());
			e.printStackTrace();
		}
		codeSQLPerson++;
	}*/
	
	public void insertUser(User user) {
		myUsers.add(user);
	}
	/*
	
	public void insertPerson(Person person) {
		myPersons.add(person);
		codePerson++;
	}
	
	public void insertCheckUp(CheckUp checkUp) {
		myCheckUps.add(checkUp);
		codeCheckUp++;
	}
	
	public void insertAppoinment(Appoinment appoinment) {
		myAppoinments.add(appoinment);
		codeAppoinment++;
	}
	
	public void insertVaccine(Vaccine vaccine) {
		myVaccines.add(vaccine);
		codeVaccine++;
	}
	
	public void insertDisease(Disease disease) {
		myDiseases.add(disease);
		codeDisease++;
	}*/
	
	//MODIFY ----------------------------------------------------
	
	/*
	public void modifiedUser(User user) {
		myUsers.set(getIndexUser(user.getName()), user);
	}

	public void modifiedVaccine(Vaccine vaccine) {
		myVaccines.set(getIndexvaccine(vaccine.getCode()), vaccine);
	}

	public void modifiedDisease(Disease disease) {
		myDiseases.set(getIndexDisease(disease.getCode()), disease);
	}

	public void modifiedAppoinment(Appoinment app) {
		myAppoinments.set(getIndexAppoinment(app.getCode()), app);
	}
	
	public void modifiedPerson(Person person) {
		myPersons.set(getIndexPerson(person.getCode()), person);
	}*/
	
	//REMOVE --------------------------------------------------------
	
	/*
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
	*/
	
	
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
	
	public int totalPatientsWithDisease(String code) {
		int total = 0;
		
		for (Person per : myPersons) {
			if (per instanceof Patient) {
				Patient pat = (Patient) per;
				for (int i = 0; i < pat.getMyDiseases().size(); i++) {
					if (pat.getMyDiseases().get(i).getCode().equalsIgnoreCase(code)) {
						total++;
					}
				}
			}
		}
		
		return total;
	}
	
	public int totalPatientsWithVaccine(String code) {
		int total = 0;
		
		for (Person per : myPersons) {
			if (per instanceof Patient) {
				Patient pat = (Patient) per;
				for (int i = 0; i < pat.getMyVaccines().size(); i++) {
					if (pat.getMyVaccines().get(i).getCode().equalsIgnoreCase(code)) {
						total++;
					}
				}
			}
		}
		
		return total;
	}

	public int totalUsers(String type) {
		int total = 0;
		for(User user: myUsers) {
			if(user.getType().equalsIgnoreCase(type)){
				total++;
			}
		}		
		return total;
	}
	
	public int totalSQLPatientsStatus(String status) {
	    int total = 0;
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = SqlConnection.getConnection(); // Assuming SqlConnection is your connection utility class

	        // Prepare the SQL query to count patients with the given status
	        ps = con.prepareStatement("SELECT COUNT(*) FROM appointment WHERE status = ?");
	        ps.setString(1, status);
	        rs = ps.executeQuery();

	        // Retrieve the count result
	        if (rs.next()) {
	            total = rs.getInt(1); // Get the count from the first column
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error in totalPatientsStatus: " + e.toString());
	        e.printStackTrace();
	    } finally {
	        // Close resources
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return total;
	}

	
	/*
	public int totalPatientsStatus(String status) {
		int total = 0;
		
		for (Appoinment app : myAppoinments) {
			if (app.getStatus().equalsIgnoreCase(status)) {
				total++;
			}
		}
		
		return total;
	}*/
	
	
	/*
	public int totalPatientsCheck() {
		return myAppoinments.size();
	}*/

	
	
	//miscellaneous methods
	/*
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
	}*/
	
	/*
	public boolean validSQLUser(String username, String psw) {
		try {
			//con = SqlConnection.getConnection();
			ps = con.prepareStatement("SELECT username, password FROM [user]"
					+ "WHERE username=?");
			ps.setString(1, username);
			
			while (rs.next()) {
				if(rs.getString("username").equals(username) && rs.getString("password").equals(psw)) {
					loginUser = username;
					return true;
				}
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR en Clinic.validSQLUser. " + e.toString());
			e.printStackTrace();
		}
		return false;
	}
	*/
	
	
	
	public boolean validUser(String username, String psw) {
		for(User user: myUsers) {
			if(user.getName().equals(username) && user.getPassword().equals(psw)) {
				loginUser = user;
				return true; 
			}
		}
		
		return false;
	}
	/*
	public boolean isUniquePhoneNumber(String number) {
		
		for (Person per : myPersons) {
			if (per.getPhoneNumber().equalsIgnoreCase(number)) {
				return false;
			}
		}
		
		return true;
	}*/
	/*
	public boolean isUniqueSsnNumber(String number) {
		
		for (Person per : myPersons) {
			if (per.getSsn().equalsIgnoreCase(number)) {
				return false;
			}
		}
		
		return true;
	}*/
	
	
}
