package Visual;


import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MSecretaryPanel extends JPanel {
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;

	/**
	 * Create the panel.
	 */
	public MSecretaryPanel() {
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
		dashboardP.setBackground(SystemColor.activeCaption);
		dashboardP.setBounds(803, 49, 528, 433);
		panel.add(dashboardP);
		dashboardP.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DASHBOARD (SECRETARY POV)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(107, 31, 292, 64);
		dashboardP.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Personas en espera (Status = waiting)", "Sexo\u00BF"}));
		comboBox.setBounds(107, 117, 292, 20);
		dashboardP.add(comboBox);
		
		JButton btnNewButton = new JButton("NUEVA CITA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAppointment createApp = new CreateAppointment();
				createApp.setModal(true);
				createApp.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(803, 514, 528, 83);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("LISTA DE ESPERA");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(10, 23, 225, 27);
		panel.add(lblNewLabel_1);

	}
}
