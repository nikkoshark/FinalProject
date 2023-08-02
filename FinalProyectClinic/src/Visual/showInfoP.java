package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

import logic.CheckUp;
import logic.Disease;

import java.util.ArrayList;
import java.util.Date;
import java.awt.SystemColor;
import java.time.ZoneId;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class showInfoP extends JDialog {
	private JTable tableAv;
	private JTable tablePat;
	private static DefaultTableModel model;
	private static DefaultTableModel modelPatient;
	private static Object[] row;
	private static ArrayList<Disease> patientsDiseases = new ArrayList<>();
	private static ArrayList<Disease> avaibleDiseases = new ArrayList<>();
	private static JCheckBox chbxRecord;


	/**
	 * Create the dialog.
	 */
	public showInfoP(CheckUp showCheckUp) {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		setBounds(100, 100, 631, 461);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 389, 615, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton btnCancel = new JButton("VOLVER");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		
		JLabel label = new JLabel("Fecha de la cita");
		label.setBounds(10, 11, 130, 14);
		getContentPane().add(label);
		

        ZoneId zoneId = ZoneId.systemDefault();
		Date dateTime = Date.from(showCheckUp.getDate().atZone(zoneId).toInstant());
		
		JDateChooser dateChooser = new JDateChooser(dateTime);
		dateChooser.setEnabled(false);
		dateChooser.setBounds(10, 28, 87, 20);
		getContentPane().add(dateChooser);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 56, 592, 118);
		getContentPane().add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 17, 269, 94);
		panel_1.add(scrollPane);
		
		tableAv = new JTable();
		scrollPane.setViewportView(tableAv);

		model = new DefaultTableModel();
		String[] headers = {"Disponibles"};
		model.setColumnIdentifiers(headers);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(313, 17, 269, 94);
		panel_1.add(scrollPane_1);
		
		tablePat = new JTable();
		scrollPane_1.setViewportView(tablePat);
		modelPatient = new DefaultTableModel();
		String[] headersPatieStrings = {"Paciente"};
		modelPatient.setColumnIdentifiers(headersPatieStrings);
		

		tablePat.setModel(modelPatient);
		tableAv.setModel(model);
		
		
		
		
		JLabel label_2 = new JLabel("Enfermedades");
		label_2.setBounds(10, 0, 87, 14);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("S\u00EDntomas:");
		label_3.setBounds(10, 195, 130, 14);
		getContentPane().add(label_3);
		
		JTextArea txtSin = new JTextArea();
		txtSin.setEditable(false);
		txtSin.setText(showCheckUp.getSymptoms());
		txtSin.setLineWrap(true);
		txtSin.setBounds(10, 218, 594, 48);
		getContentPane().add(txtSin);
		
		JLabel label_4 = new JLabel("Diagnosis:");
		label_4.setBounds(10, 277, 130, 14);
		getContentPane().add(label_4);
		
		JTextArea txtDiag = new JTextArea();
		txtDiag.setEditable(false);
		txtDiag.setText(showCheckUp.getDiagnosis());
		txtDiag.setLineWrap(true);
		txtDiag.setBounds(10, 300, 594, 48);
		getContentPane().add(txtDiag);
		
		chbxRecord = new JCheckBox("Guardar como r\u00E9cord m\u00E9dico");
		chbxRecord.setBackground(SystemColor.inactiveCaption);
		chbxRecord.setSelected(showCheckUp.isMedicalRecord());
		chbxRecord.setBounds(408, 359, 196, 23);
		getContentPane().add(chbxRecord);
		
		
		loadDiseaseAv();
		loadDiseasePat();
	}
	

	private void loadDiseaseAv() {
		model.setRowCount(0);
		row = new Object[tableAv.getColumnCount()];
		
		for(Disease disease: avaibleDiseases) {				
			row[0] = disease.getName();
			model.addRow(row);			
		}
	}
	
	private void loadDiseasePat() {
		modelPatient.setRowCount(0);
		row = new Object[tablePat.getColumnCount()];
		for(Disease disease: patientsDiseases) {
			row[0] = disease.getName();
			modelPatient.addRow(row);
			
			if(disease.isWatched()) {
				chbxRecord.setSelected(true);	
			}
		}
	}
}
