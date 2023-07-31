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

import Dashboards.AppointmentInfo;
import Dashboards.GenderInfo;
import logic.Appoinment;
import logic.Clinic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MSecretaryPanel extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private GenderInfo gender;
	private AppointmentInfo appinfo;
	private JButton btnEdit;
	private static JDateChooser dateChooser;
	private Appoinment selAppoinment = null;
	private JButton btnDelete;
	private JComboBox cbDash;

	/**
	 * Create the panel.
	 */
	public MSecretaryPanel() {
		setOpaque(false);
		setSize(1340, 648);
		setLayout(null);
		setVisible(false);
		
		gender = new GenderInfo();
		appinfo = new AppointmentInfo();
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 1340, 648);
		panel.setOpaque(false);
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
		String[] headers = {"Cédula", "Nombre Paciente", "Hora", "Doctor", "Status"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		
		JPanel dashboardP = new JPanel();
		dashboardP.setBackground(SystemColor.activeCaption);
		dashboardP.setBounds(803, 49, 528, 433);
		panel.add(dashboardP);
		dashboardP.setLayout(null);
		
		cbDash = new JComboBox();
		cbDash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = cbDash.getSelectedIndex();
				if(select == 0) {
					menuclicked(appinfo);
				} else {
					menuclicked(gender);
				}
			}
		});
		cbDash.setModel(new DefaultComboBoxModel(new String[] {"Status de Personas", "Sexo\u00BF"}));
		cbDash.setBounds(126, 11, 292, 20);
		dashboardP.add(cbDash);
		
		
		JButton btnNewApp = new JButton("NUEVA CITA");
		btnNewApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAppointment createApp = new CreateAppointment(null);
				createApp.setModal(true);
				createApp.setVisible(true);
			}
		});
		btnNewApp.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 30));
		btnNewApp.setBounds(803, 549, 510, 48);
		panel.add(btnNewApp);
		
		JLabel lblWaiting = new JLabel("LISTA DE ESPERA");
		lblWaiting.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblWaiting.setBounds(10, 23, 225, 27);
		panel.add(lblWaiting);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(650, 23, 117, 20);
		panel.add(dateChooser);

		Date date = new Date();
		dateChooser.setDate(date);
		LocalDateTime local = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		ZonedDateTime zone = local.atZone(ZoneId.systemDefault());
		Date dateoutput = Date.from(zone.toInstant());
		
		btnEdit = new JButton("EDITAR CITA");
		btnEdit.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
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
		btnEdit.setBounds(803, 493, 248, 48);
		panel.add(btnEdit);
		
		btnDelete = new JButton("ELIMINAR CITA");
		btnDelete.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selAppoinment != null) {
					int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la cita de: " + selAppoinment.getName() + "?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
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
		btnDelete.setBounds(1065, 493, 248, 48);
		panel.add(btnDelete);
		
		
		JPanel dash = new JPanel();
		dash.setBounds(95, 40, 350, 350);
		dashboardP.add(dash);
		dash.setLayout(null);
		
		dash.add(gender);
		dash.add(appinfo);
		menuclicked(appinfo);
		
		loadAppointments();

	}
	
	private void menuclicked(JPanel panel) {
		gender.setVisible(false);
		appinfo.setVisible(false);
		panel.setVisible(true);
	}
	
	public static void loadAppointments() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		
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
