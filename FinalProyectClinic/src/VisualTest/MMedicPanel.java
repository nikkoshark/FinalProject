package VisualTest;


import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MMedicPanel extends JPanel {
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;

	/**
	 * Create the panel.
	 */
	public MMedicPanel() {
		setBackground(SystemColor.activeCaption);
		setSize(1340, 648);
		setLayout(null);
		setVisible(false);
		
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
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		String[] headers = {"Cédula", "Nombre Paciente", "Fecha", "Doctor", "Status"}; //HEADERS FOR THE LIST
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		
		JPanel dashboardP = new JPanel();
		dashboardP.setLayout(null);
		dashboardP.setBackground(SystemColor.activeCaption);
		dashboardP.setBounds(803, 49, 528, 426);
		panel.add(dashboardP);
		
		JLabel lblDashboardMedicPov = new JLabel("DASHBOARD (MEDIC POV)");
		lblDashboardMedicPov.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDashboardMedicPov.setBounds(118, 11, 292, 64);
		dashboardP.add(lblDashboardMedicPov);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Personas en espera (Status = waiting)", "Sexo\u00BF"}));
		comboBox.setBounds(118, 107, 292, 20);
		dashboardP.add(comboBox);
		
		JButton button = new JButton("NUEVA CITA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAppointment createApp = new CreateAppointment();
				createApp.setModal(true);
				createApp.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBounds(803, 548, 528, 49);
		panel.add(button);
		
		JButton btnConsultar = new JButton("CONSULTAR");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateCheckup createCheck = new CreateCheckup();
				createCheck.setModal(true);
				createCheck.setVisible(true);
			}
		});
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConsultar.setBounds(803, 486, 528, 49);
		panel.add(btnConsultar);
		
		JLabel label = new JLabel("LISTA DE ESPERA");
		label.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		label.setBounds(10, 23, 225, 27);
		panel.add(label);
	}
}
