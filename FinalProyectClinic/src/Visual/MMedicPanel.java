package Visual;


import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import Dashboards.AppointmentInfo;
import Dashboards.GenderInfo;
import logic.Appoinment;
import logic.Clinic;
import logic.Person;

public class MMedicPanel extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private JButton btnConsultar;
	private Appoinment selPatient = null;
	private GenderInfo ginfo;
	private AppointmentInfo appinfo;
	private JComboBox cbDash;

	/**
	 * Create the panel.
	 */
	public MMedicPanel() {
		setBackground(SystemColor.activeCaption);
		setSize(1340, 648);
		setLayout(null);
		setVisible(false);
		
		ginfo = new GenderInfo();
		appinfo = new AppointmentInfo();
		
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
				
				if (index >= 0) {
					btnConsultar.setEnabled(true);
					String ssn = (String) table.getModel().getValueAt(index, 0);
					selPatient = Clinic.getInstance().searchAppoinment(ssn);
				}
			}
		});
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		String[] headers = {"Codigo", "Cédula", "Nombre Paciente", "Fecha", "Status"}; //HEADERS FOR THE LIST
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		
		JPanel dashboardP = new JPanel();
		dashboardP.setLayout(null);
		dashboardP.setBackground(SystemColor.activeCaption);
		dashboardP.setBounds(803, 49, 528, 426);
		panel.add(dashboardP);
		
		
		
		JButton button = new JButton("NUEVA CITA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAppointment createApp = new CreateAppointment(null);
				createApp.setModal(true);
				createApp.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(803, 548, 528, 49);
		panel.add(button);
		
		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setEnabled(false);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateCheckup createCheck = new CreateCheckup(selPatient);
				createCheck.setModal(true);
				createCheck.setVisible(true);
				btnConsultar.setEnabled(false);
			}
		});
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConsultar.setBounds(803, 486, 528, 49);
		panel.add(btnConsultar);
		
		JLabel label = new JLabel("LISTA DE ESPERA");
		label.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		label.setBounds(10, 23, 225, 27);
		panel.add(label);
		
		JDateChooser dateChooser = new JDateChooser();
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
		cbDash.setBounds(133, 11, 292, 20);
		dashboardP.add(cbDash);

		JPanel dash = new JPanel();
		dash.setBounds(102, 50, 350, 350);
		dashboardP.add(dash);
		dash.setLayout(null);
		
		dash.add(ginfo);
		dash.add(appinfo);
		menuclicked(appinfo);

		loadAppointments();
	}
	

	private void menuclicked(JPanel panel) {
		ginfo.setVisible(false);
		appinfo.setVisible(false);
		panel.setVisible(true);
		
	}

	public static void loadAppointments() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];

		for(Appoinment appointment : Clinic.getInstance().getMyAppoinments()) {
			if (true) {				
				row[0] = " " + appointment.getCode(); 
				row[1] = " " + appointment.getSsn();
				row[2] = " " + appointment.getName();
				row[3] = " " + appointment.getDate(); 
				row[4] = " " + appointment.getStatus();
				
				model.addRow(row);
			}
		}
	}


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
