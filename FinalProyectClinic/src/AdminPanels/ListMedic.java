package AdminPanels;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import AdminJDialogs.CreateMedic;
import logic.Clinic;
import logic.Medic;
import logic.Person;
import login.User;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListMedic extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Person selMedic = null;
	private JButton btnEdit;
	private JButton btnDelete;

	/**
	 * Create the panel.
	 */
	public ListMedic() {
		setBackground(SystemColor.activeCaption);
		setSize(1320, 551);
		setLayout(null);
		setVisible(false);
		
		JLabel lblNewLabel = new JLabel("LIST MEDICS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 285, 42);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 64, 612, 415);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int index = table.getSelectedRow();
				
				if(index>=0) {
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
					selMedic = Clinic.getInstance().getMyPersons().get(index);
				}
			}
		});
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		String[] headers = {"Código", "Nombre", "Especialidad"/*, "Horario Entrada", "Horario Salida"*/}; //HEADERS FOR THE LIST
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		
		btnEdit = new JButton("EDITAR");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateMedic regMedic = new CreateMedic(selMedic);
				regMedic.setModal(true);
				regMedic.setVisible(true);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
			}
		});
		btnEdit.setBounds(543, 488, 89, 23);
		add(btnEdit);
		
		btnDelete = new JButton("ELIMINAR");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selMedic != null) {
					int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el médico: " + selMedic.getName() + "?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						Clinic.getInstance().removePerson(selMedic); //ERROR MAKER
						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);
						loadMedic();
					}
				
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(444, 488, 89, 23);
		add(btnDelete);
		
		
		loadMedic();

	}
	public static void loadMedic() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];
		
		for (Person med : Clinic.getInstance().getMyPersons()) {
			if(med instanceof Medic) {
				row[0] = med.getCode();
				row[1] = med.getName();
				row[2] = ((Medic) med).getSpeciality();
				//horarios not yet implemented.
				model.addRow(row);
			}
		}
	}

}
