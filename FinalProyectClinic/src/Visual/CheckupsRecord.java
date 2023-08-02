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
import java.time.format.DateTimeFormatter;

public class CheckupsRecord extends JPanel {
	private static JTable tableCheckup;
	private static JTable tableMedicalR;
	private static DefaultTableModel modelCheckUp;
	private static DefaultTableModel modelRecord;
	private static Object[] row;
	private static Person searchpatient = null;
	private CheckUp selCheckUp = null;

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
		
		tableCheckup = new JTable(){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tableCheckup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = tableCheckup.getSelectedRow();
					if(index >= 0) {
						selCheckUp = Clinic.getInstance().getMyCheckUps().get(index);
						showInfoP show = new showInfoP(selCheckUp);
						show.setVisible(true);
						show.setModal(true);
					}
				}
			}
		});
		scrollCheckup.setViewportView(tableCheckup);
		modelCheckUp = new DefaultTableModel();
		String[] headerC = {"Código", "Fecha", "Diagnosis"};
		modelCheckUp.setColumnIdentifiers(headerC);
		tableCheckup.setModel(modelCheckUp);
		scrollCheckup.setViewportView(tableCheckup);
		
		JScrollPane scrollMedicalR = new JScrollPane();
		scrollMedicalR.setBounds(318, 52, 286, 340);
		add(scrollMedicalR);
		
		tableMedicalR = new JTable(){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tableMedicalR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = tableCheckup.getSelectedRow();
					if(index >= 0) {
						selCheckUp = Clinic.getInstance().getMyCheckUps().get(index);
						showInfoP show = new showInfoP(selCheckUp);
						show.setVisible(true);
						show.setModal(true);
					}
				}
			}
		});
		scrollMedicalR.setViewportView(tableMedicalR);
		modelRecord = new DefaultTableModel();
		String[] headerM = {"Código", "Fecha", "Diagnosis"};
		modelRecord.setColumnIdentifiers(headerM);
		tableMedicalR.setModel(modelRecord);
		scrollMedicalR.setViewportView(tableMedicalR);
		
		loadCheckup();
		loadMedicalR();

	}
	
	public static void loadCheckup() {
		modelCheckUp.setRowCount(0);
		row = new Object[tableCheckup.getColumnCount()];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
		
		for(CheckUp check : ((Patient)searchpatient).getMyCheckUpsRecord()) {
			row[0] = check.getCode();
			row[1] = check.getDate().format(formatter);
			row[2] = check.getDiagnosis();
			modelCheckUp.addRow(row);
		}
	}

	public static void loadMedicalR() {
		modelRecord.setRowCount(0);
		row = new Object[tableCheckup.getColumnCount()];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
		
		for(CheckUp check : ((Patient)searchpatient).getMyMedicalRecord()) {
			row[0] = check.getCode();
			row[1] = check.getDate().format(formatter);
			row[2] = check.getDiagnosis();
			modelRecord.addRow(row);
		}
	}
}
