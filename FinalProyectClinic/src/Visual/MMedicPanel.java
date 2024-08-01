package Visual;


import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import Dashboards.AppointmentInfo;
import Dashboards.GenderInfo;
import logic.Appoinment;
import logic.Clinic;
import logic.SqlConnection;

import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;

public class MMedicPanel extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private JButton btnConsultar;
	private JButton btnEdit;
	private JButton btnDelete;
	private String selAppoinment = null;
	private GenderInfo ginfo;
	private AppointmentInfo appinfo;
	private JComboBox cbDash;
	private static JDateChooser dateChooser;

	/**
	 * Create the panel.
	 */
	public MMedicPanel() {
		setOpaque(false);
		setSize(1340, 648);
		setLayout(null);
		setVisible(false);
		
		ginfo = new GenderInfo();
		appinfo = new AppointmentInfo();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1340, 648);
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
				
				if (index >= 0) {
					btnConsultar.setEnabled(true);
					btnDelete.setEnabled(true);
					btnEdit.setEnabled(true);
					selAppoinment = (String) table.getModel().getValueAt(index, 0);
					//String code = (String) table.getModel().getValueAt(index, 0);
					//selAppoinment = Clinic.getInstance().searchAppoinment(code);
				}
			}
		});
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		String[] headers = {"Codigo", "Cédula", "Nombre Paciente", "Fecha", "Status"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		
		JPanel dashboardP = new JPanel();
		dashboardP.setLayout(null);
		dashboardP.setBackground(SystemColor.activeCaption);
		dashboardP.setBounds(803, 49, 528, 398);
		panel.add(dashboardP);
		
		
		
		JButton button = new JButton("NUEVA CITA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAppointment createApp = new CreateAppointment(null);
				createApp.setModal(true);
				createApp.setVisible(true);
			}
		});
		button.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		button.setBounds(803, 556, 510, 41);
		panel.add(button);
		
		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setEnabled(false);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				if (selAppoinment.getStatus().equalsIgnoreCase("Visto")) {
					JOptionPane.showMessageDialog(null, "Esta consulta ya fue realizada.", "Consulta hecha", JOptionPane.INFORMATION_MESSAGE);
				}*/ //HABIA UN ELSE AQUI
				CreateCheckup createCheck = new CreateCheckup(selAppoinment, null);
				createCheck.setModal(true);
				createCheck.setVisible(true);
				btnConsultar.setEnabled(false);
				btnDelete.setEnabled(false);
				btnEdit.setEnabled(false);	
				
			}
		});
		btnConsultar.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		btnConsultar.setBounds(803, 458, 510, 41);
		panel.add(btnConsultar);
		
		JLabel label = new JLabel("LISTA DE ESPERA");
		label.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		label.setBounds(10, 23, 225, 27);
		panel.add(label);
		
		dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				loadSQLApp();
			}
		});
		dateChooser.setBounds(650, 23, 117, 20);
		panel.add(dateChooser);

		Date date = new Date();
		dateChooser.setDate(date);
		
		JPanel search = new JPanel();
		search.setBounds(202, 23, 122, 27);
		panel.add(search);
		search.addMouseListener(new PanelButtonMouseAdapter(search));
		
		JLabel lblNewLabel = new JLabel("Buscar Paciente");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		search.add(lblNewLabel);
		
		cbDash = new JComboBox();
		cbDash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = cbDash.getSelectedIndex();
				if(select == 0) {
					menuclicked(appinfo);
				} else {
					menuclicked(ginfo);
				}
			}
		});
		cbDash.setModel(new DefaultComboBoxModel(new String[] {"Status de Personas", "Sexo"}));
		cbDash.setSelectedIndex(0);
		cbDash.setBounds(133, 11, 292, 20);
		dashboardP.add(cbDash);

		JPanel dash = new JPanel();
		dash.setBounds(95, 40, 350, 350);
		dashboardP.add(dash);
		dash.setLayout(null);
		
		dash.add(ginfo);
		dash.add(appinfo);
		menuclicked(appinfo);
		
		btnEdit = new JButton("EDITAR CITA");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if (selAppoinment.getStatus().equalsIgnoreCase("Visto")) {
					JOptionPane.showMessageDialog(null, "Esta consulta ya fue realizada.", "Consulta hecha", JOptionPane.INFORMATION_MESSAGE);
				}*/
				CreateAppointment regApp = new CreateAppointment(selAppoinment);
				regApp.setModal(true);
				regApp.setVisible(true);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				
			}
		});
		btnEdit.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		btnEdit.setEnabled(false);
		btnEdit.setBounds(803, 510, 248, 41);
		panel.add(btnEdit);
		
		btnDelete = new JButton("ELIMINAR CITA");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selAppoinment != null) {
					/*if (selAppoinment.getStatus().equalsIgnoreCase("Visto")) {
						JOptionPane.showMessageDialog(null, "Esta consulta ya fue realizada.", "Consulta hecha", JOptionPane.INFORMATION_MESSAGE);
					}*/
						int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la cita de: " + selAppoinment + "?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {

							try { 
								Connection con = SqlConnection.getConnection();
								PreparedStatement ps;
								ps = con.prepareStatement("DELETE FROM appointment WHERE id=? ");
								ps.setString(1, selAppoinment);
								//EL ÓRDEN DE CÓMO SE VA A INSERTAR ES EN BASE AL QUERY
								
								ps.executeUpdate();
								
								JOptionPane.showMessageDialog(null, "SE BORRÓ NMMS QUE FELIZ!");
								//clean();
								
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "error dentro de ELIMINAR. sadge. " + e1.toString());
								e1.printStackTrace();
							}
							
						/*Clinic.getInstance().removeAppoinment(selAppoinment);
						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);*/
						loadSQLApp();
					}
				}
			}
		});
		btnDelete.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		btnDelete.setEnabled(false);
		btnDelete.setBounds(1065, 510, 248, 41);
		panel.add(btnDelete);

		loadSQLApp();
	}
	

	private void menuclicked(JPanel panel) {
		ginfo.setVisible(false);
		appinfo.setVisible(false);
		panel.setVisible(true);
		
	}

	public static void loadSQLApp() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];
		PreparedStatement ps;
		ResultSet rs;
		ResultSetMetaData rsmd;
		int columns;
		
		try {
			Connection con = SqlConnection.getConnection();
			ps = con.prepareStatement("SELECT a.id, a.ssn_patient, a.name_patient, "
					+ "a.time_a, a.status FROM appointment a ");
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			columns = rsmd.getColumnCount();
			
			
			while (rs.next()) {
				Object[] fila = new Object[columns];
				for(int indice=0; indice<columns; indice++) {
					fila[indice] = rs.getObject(indice+1);
				}
				model.addRow(fila);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error dentro de AQUIII MMedicPanel, loadsqlapp. " +e.toString());
			e.printStackTrace();
		}
	}
	
	
	/*
	public static void loadAppointments() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");

		for (Appoinment appointment : Clinic.getInstance().getMyAppoinments()) {
			if (appointment.getDate().format(formatter).equals(dayFormat.format(dateChooser.getDate()))) {
				row[0] = appointment.getCode();
				row[1] = " " + appointment.getSsn();
				row[2] = " " + appointment.getName();
				row[3] = " " + appointment.getDate().format(timeFormatter);
				row[4] = " " + appointment.getStatus();
				model.addRow(row);

			}
		}
	}*/


	private class PanelButtonMouseAdapter extends MouseAdapter{
			
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(192, 192, 192));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(240, 240, 240));
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(192, 192, 192));
			SearchPatient sPatient = new SearchPatient();
			sPatient.setModal(true);
			sPatient.setVisible(true);
		}
	}


}
