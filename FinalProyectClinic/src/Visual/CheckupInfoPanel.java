package Visual;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import logic.CheckUp;
import logic.Clinic;
import logic.Disease;
import logic.Medic;
import logic.Patient;
import logic.Person;
import logic.Vaccine;

import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckupInfoPanel extends JPanel {
	private static JDateChooser dateChooser;
	private static JTextArea txtaDiagnosis;
	private static JTextArea txtaSymptoms;
	private static JCheckBox chbxMedicalR;
	private static JTable tableAv;
	private static JTable tablePat;
	private static DefaultTableModel model;
	private static Object[] row;
	private static LocalDateTime localDateTime;
	private static JComboBox cbVaccine;

	/**
	 * Create the panel.
	 */
	public CheckupInfoPanel(LocalDateTime date, Person patient) {
		localDateTime = date;
		setBackground(SystemColor.inactiveCaption);
		setSize(614,403);
		setLayout(null);
		setVisible(false);
		
		JLabel lblDate = new JLabel("Fecha de la cita");
		lblDate.setBounds(10, 11, 130, 14);
		add(lblDate);
		
		
        ZoneId zoneId = ZoneId.systemDefault();
		Date dateTime = Date.from(date.atZone(zoneId).toInstant());
		
		dateChooser = new JDateChooser(dateTime);
		dateChooser.setBounds(10, 28, 87, 20);
		add(dateChooser);
		dateChooser.setEnabled(false);
		
		JLabel lblDiagnosis = new JLabel("Diagnosis");
		lblDiagnosis.setBounds(10, 277, 130, 14);
		add(lblDiagnosis);
		
		txtaDiagnosis = new JTextArea();
		txtaDiagnosis.setLineWrap(true);
		txtaDiagnosis.setBounds(10, 300, 594, 48);
		add(txtaDiagnosis);
		
		JLabel lblSymptoms = new JLabel("S\u00EDntomas");
		lblSymptoms.setBounds(10, 195, 130, 14);
		add(lblSymptoms);
		
		txtaSymptoms = new JTextArea();
		txtaSymptoms.setText((String) null);
		txtaSymptoms.setLineWrap(true);
		txtaSymptoms.setBounds(10, 218, 594, 48);
		add(txtaSymptoms);
		
		JPanel diseasePanel = new JPanel();
		diseasePanel.setBackground(SystemColor.inactiveCaption);
		diseasePanel.setBounds(10, 56, 291, 118);
		add(diseasePanel);
		diseasePanel.setLayout(null);
		
		cbVaccine = new JComboBox();
		cbVaccine.setModel(new DefaultComboBoxModel(new String[] {"<SELECCIONAR>"}));
		cbVaccine.setBounds(65, 36, 184, 20);
		diseasePanel.add(cbVaccine);
		
		JLabel lblVac = new JLabel("Vacunas");
		lblVac.setBounds(117, 11, 56, 14);
		diseasePanel.add(lblVac);
		
		JPanel vaccinePanel = new JPanel();
		vaccinePanel.setBackground(SystemColor.inactiveCaption);
		vaccinePanel.setBounds(311, 56, 291, 118);
		add(vaccinePanel);
		vaccinePanel.setLayout(null);
		
		JScrollPane scrollDiseaseAv = new JScrollPane();
		scrollDiseaseAv.setBounds(10, 17, 126, 94);
		vaccinePanel.add(scrollDiseaseAv);
		
		tableAv = new JTable(){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tableAv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					
				}
			}
		});
		scrollDiseaseAv.setViewportView(tableAv);
		
		JScrollPane scrollDiseasePat = new JScrollPane();
		scrollDiseasePat.setBounds(155, 17, 126, 94);
		vaccinePanel.add(scrollDiseasePat);
		
		
		
		
		tablePat = new JTable(){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tablePat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					
				}
			}
		});
		scrollDiseasePat.setViewportView(tablePat);
		
		JLabel lblDis = new JLabel("Enfermedades");
		lblDis.setBounds(107, 0, 87, 14);
		vaccinePanel.add(lblDis);
		
		chbxMedicalR = new JCheckBox("Guardar como r\u00E9cord m\u00E9dico");
		chbxMedicalR.setBackground(SystemColor.inactiveCaption);
		chbxMedicalR.setBounds(408, 359, 196, 23);
		add(chbxMedicalR);
		
		loadVaccines();
	}
	
	private void loadVaccines() {
		cbVaccine.removeAllItems();
		for(Vaccine aux : Clinic.getInstance().getMyVaccines()) {
			String name = new String(aux.getName());
			cbVaccine.addItem(name);
		}
		cbVaccine.insertItemAt(new String("<SELECCIONAR>"), 0);
		cbVaccine.setSelectedIndex(0);

	}
	
	private void loadDiseaseAv() {
		model.setRowCount(0);
		row = new Object[tableAv.getColumnCount()];
		for(Disease disease: Clinic.getInstance().getMyDiseases()) {
			row[0] = disease.getName();
			model.addRow(row);
		}
	}
	
	private void loadDiseasePat() {
		model.setRowCount(0);
		row = new Object[tablePat.getColumnCount()];
		for(Disease disease: Clinic.getInstance().getMyDiseases()) {
			row[0] = disease.getName();
			model.addRow(row);
			if(disease.isWatched()) {
				chbxMedicalR.setSelected(true);
			}
		}
	}
	private static String getCodeCheckup(int codeCheck) {
		int total = codeCheck / 10;
		String code = null;
		
		code = "C-0000" + codeCheck;
		
		if (total >= 1 && total < 10) {
			code = "C-000" + codeCheck;
		}
		else if (total >= 10 && total < 100) {
			code = "C-00" + codeCheck;
		}
		else if (total >= 100 && total < 1000) {
			code = "C-0" + codeCheck;
		}
		else if (total >= 1000) {
			code = "C-" + codeCheck;
		}
		
		return code;
	}
	
	public static CheckUp sendCheckUp(Person aPatient, Medic aMedic) {
		String diagnosis = txtaDiagnosis.getText();
		String symptoms = txtaSymptoms.getText();
		
		CheckUp checkUp = new CheckUp(getCodeCheckup(Clinic.getInstance().getCodeCheckUp()), diagnosis, symptoms, localDateTime, aMedic , (Patient)aPatient, null/*myDiseases*/, chbxMedicalR.isSelected());
		Clinic.getInstance().insertCheckUp(checkUp);
		return checkUp;
	}
	
	public static Vaccine sendVaccine() {
		return Clinic.getInstance().getMyVaccines().get(cbVaccine.getSelectedIndex());
	}

	public static ArrayList<Disease> sendDisease() {
		ArrayList<Disease> diz = new ArrayList<>();
		for(Disease disease : Clinic.getInstance().getMyDiseases()) { // the ones added to the checkup
			diz.add(disease);
		}
		return diz;
	}
	
	
}
