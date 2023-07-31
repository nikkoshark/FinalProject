package CheckupPanels;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class InfoPanel extends JPanel {
	private JTextArea txtaDiagnosis;
	private JTable tableAv;
	private JTable tablePat;

	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		setBackground(SystemColor.inactiveCaption);
		setSize(614,403);
		setLayout(null);
		setVisible(false);
		
		JLabel lblDate = new JLabel("Fecha de la cita");
		lblDate.setBounds(10, 11, 130, 14);
		add(lblDate);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 28, 87, 20);
		add(dateChooser);
		dateChooser.setEnabled(false);
		
		txtaDiagnosis = new JTextArea();
		txtaDiagnosis.setText((String) null);
		txtaDiagnosis.setLineWrap(true);
		txtaDiagnosis.setBounds(10, 300, 594, 48);
		add(txtaDiagnosis);
		
		JTextArea txtaSymptoms = new JTextArea();
		txtaSymptoms.setText((String) null);
		txtaSymptoms.setLineWrap(true);
		txtaSymptoms.setBounds(10, 218, 594, 48);
		add(txtaSymptoms);
		
		JLabel lblSymptoms = new JLabel("S\u00EDntomas");
		lblSymptoms.setBounds(10, 195, 130, 14);
		add(lblSymptoms);
		
		JLabel lblDiagnosis = new JLabel("Diagnosis");
		lblDiagnosis.setBounds(10, 277, 130, 14);
		add(lblDiagnosis);
		
		JPanel diseasePanel = new JPanel();
		diseasePanel.setBackground(SystemColor.inactiveCaption);
		diseasePanel.setBounds(10, 56, 291, 118);
		add(diseasePanel);
		diseasePanel.setLayout(null);
		
		JLabel lblDis = new JLabel("Enfermedades");
		lblDis.setBounds(107, 0, 87, 14);
		diseasePanel.add(lblDis);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"imagine it being dynamic"}));
		comboBox.setBounds(64, 25, 184, 20);
		diseasePanel.add(comboBox);
		
		JPanel vaccinePanel = new JPanel();
		vaccinePanel.setBackground(SystemColor.inactiveCaption);
		vaccinePanel.setBounds(311, 56, 291, 118);
		add(vaccinePanel);
		vaccinePanel.setLayout(null);
		
		JLabel lblVac = new JLabel("Vacunas");
		lblVac.setBounds(126, 0, 56, 14);
		vaccinePanel.add(lblVac);
		
		JScrollPane scrollVaccineAv = new JScrollPane();
		scrollVaccineAv.setBounds(10, 17, 126, 94);
		vaccinePanel.add(scrollVaccineAv);
		
		tableAv = new JTable();
		scrollVaccineAv.setViewportView(tableAv);
		
		JScrollPane scrollVaccinesPat = new JScrollPane();
		scrollVaccinesPat.setBounds(155, 17, 126, 94);
		vaccinePanel.add(scrollVaccinesPat);
		
		tablePat = new JTable();
		scrollVaccinesPat.setViewportView(tablePat);
		
		JCheckBox chbxMedicalR = new JCheckBox("Guardar como r\u00E9cord m\u00E9dico");
		chbxMedicalR.setBackground(SystemColor.inactiveCaption);
		chbxMedicalR.setBounds(441, 359, 163, 23);
		add(chbxMedicalR);
	}
}
