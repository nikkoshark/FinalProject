package Visual;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.CheckUp;
import logic.Clinic;
import logic.Patient;
import logic.Person;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckupsRecord extends JPanel {
	private static JTable tableCheckup;
	private static JTable tableMedicalR;
	private static DefaultTableModel model;
	private static Object[] row;
	private static Person searchpatient = null;

	/**
	 * Create the panel.
	 */
	public CheckupsRecord(Person patient) {
		searchpatient = patient;
		setBackground(SystemColor.inactiveCaption);
		setSize(614,403);
		setLayout(null);
		setVisible(false);
		
		JLabel lblCheckup = new JLabel("CHECKUPS");
		lblCheckup.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblCheckup.setBounds(100, 21, 144, 25);
		add(lblCheckup);
		
		JLabel lblMedicalRecord = new JLabel("MEDICAL RECORD");
		lblMedicalRecord.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblMedicalRecord.setBounds(373, 21, 178, 25);
		add(lblMedicalRecord);
		
		JScrollPane scrollCheckup = new JScrollPane();
		scrollCheckup.setBounds(10, 52, 286, 340);
		add(scrollCheckup);
		
		tableCheckup = new JTable();
		tableCheckup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableCheckup.getSelectedRow();
				if(index>=0) {
					
				}
			}
		});
		scrollCheckup.setViewportView(tableCheckup);
		model = new DefaultTableModel();
		String[] headerC = {"Código", "Fecha", "Diagnosis"};
		model.setColumnIdentifiers(headerC);
		tableCheckup.setModel(model);
		scrollCheckup.setViewportView(tableCheckup);
		
		JScrollPane scrollMedicalR = new JScrollPane();
		scrollMedicalR.setBounds(318, 52, 286, 340);
		add(scrollMedicalR);
		
		tableMedicalR = new JTable();
		tableMedicalR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableMedicalR.getSelectedRow();
				if(index>=0) {
					
				}
			}
		});
		scrollMedicalR.setViewportView(tableMedicalR);
		model = new DefaultTableModel();
		String[] headerM = {"Código", "Fecha", "Diagnosis"};
		model.setColumnIdentifiers(headerM);
		tableMedicalR.setModel(model);
		scrollMedicalR.setViewportView(tableMedicalR);
		
		loadCheckup();
		loadMedicalR();

	}
	
	public static void loadCheckup() {
		model.setRowCount(0);
		row = new Object[tableCheckup.getColumnCount()];
		for(CheckUp check : ((Patient)searchpatient).getMyCheckUpsRecord()) {
			row[0] = check.getCode();
			row[1] = check.getDate();
			row[2] = check.getDiagnosis();
			model.addRow(row);
		}
	}

	public static void loadMedicalR() {
		model.setRowCount(0);
		row = new Object[tableCheckup.getColumnCount()];
		for(CheckUp check : ((Patient)searchpatient).getMyMedicalRecord()) {
			row[0] = check.getCode();
			row[1] = check.getDate();
			row[2] = check.getDiagnosis();
			model.addRow(row);
		}
	}
}
