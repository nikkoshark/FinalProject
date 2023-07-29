package Visual;


import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import Dashboards.GenderInfo;
import logic.Appoinment;
import logic.Clinic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MSecretaryPanel extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private GenderInfo gender;
	private JButton btnEdit;
	private static JDateChooser dateChooser;
	private Appoinment selAppoinment;
	private JButton btnDelete;

	/**
	 * Create the panel.
	 */
	public MSecretaryPanel() {
		setBackground(SystemColor.activeCaption);
		setSize(1340, 648);
		setLayout(null);
		setVisible(false);
		
		gender = new GenderInfo();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1340, 648);
		panel.setBackground(SystemColor.activeCaption);
		add(panel);
		panel.setLayout(null);
		
		JPanel tableP = new JPanel();
		tableP.setBounds(10, 49, 757, 548);
		panel.add(tableP);
		tableP.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		tableP.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if(index>=0) {
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
					selAppoinment = Clinic.getInstance().getMyAppoinments().get(index);
				}
			}
		});
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		String[] headers = {"Cédula", "Nombre Paciente", "Hora", "Doctor", "Status"}; //HEADERS FOR THE LIST
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		
		JPanel dashboardP = new JPanel();
		dashboardP.setBackground(SystemColor.activeCaption);
		dashboardP.setBounds(803, 49, 528, 433);
		panel.add(dashboardP);
		dashboardP.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DASHBOARD (SECRETARY POV)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(80, 11, 292, 33);
		dashboardP.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Personas en espera (Status = waiting)", "Sexo\u00BF"}));
		comboBox.setBounds(90, 43, 292, 20);
		dashboardP.add(comboBox);
		
		JPanel dash = new JPanel();
		dash.setBounds(54, 74, 350, 350);
		dashboardP.add(dash);
		
		JButton btnNewButton = new JButton("NUEVA CITA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAppointment createApp = new CreateAppointment(null);
				createApp.setModal(true);
				createApp.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 30));
		btnNewButton.setBounds(803, 514, 528, 83);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("LISTA DE ESPERA");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(10, 23, 225, 27);
		panel.add(lblNewLabel_1);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(650, 23, 117, 20);
		panel.add(dateChooser);


		Date date = new Date();
		dateChooser.setDate(date);
		LocalDateTime local = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		
	
		ZonedDateTime zone = local.atZone(ZoneId.systemDefault());
		Date dateoutput = Date.from(zone.toInstant());
		
		
		dash.add(gender);
		
		btnEdit = new JButton("EDITAR CITA");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAppointment regApp = new CreateAppointment(selAppoinment);
				regApp.setModal(true);
				regApp.setVisible(true);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBounds(803, 480, 154, 23);
		panel.add(btnEdit);
		
		btnDelete = new JButton("ELIMINAR CITA");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selAppoinment != null) {
					int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el usuario: " + selAppoinment.getCode() + "?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						Clinic.getInstance().removeAppoinment(selAppoinment);
						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);
						loadAppointments();
					}
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(981, 480, 154, 23);
		panel.add(btnDelete);
		
		
		loadAppointments();

	}
	
	public static void loadAppointments() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		
		for(Appoinment appointment : Clinic.getInstance().getMyAppoinments()) {
			row[0] = " " + appointment.getSsn();
			row[1] = " " + appointment.getName();
			row[2] = " " + appointment.getDate().format(dateTimeFormatter);
			row[3] = " " + appointment.getMedic().getName(); 
			row[4] = " " + appointment.getStatus();
			
			model.addRow(row);
		}
	}
}
