package AdminPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import AdminJDialogs.CreateUser;
import logic.Clinic;
import login.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class ListUsers extends JPanel {
	
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private User seleUser = null;
	private JButton btnDelete;
	private JButton btnEdit;

	/**
	 * Create the panel.
	 */
	public ListUsers() {
		setBackground(SystemColor.activeCaption);
		setSize(1320, 551);
		setLayout(null);
		setVisible(false);
		
		JLabel lblNewLabel = new JLabel("LISTADO USUARIOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(20, 13, 285, 42);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 64, 612, 415);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setLocation(20, 0);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				
				if (index >= 0) {
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
					seleUser = Clinic.getInstance().getMyUsers().get(index);
				}
			}
		});
		
		
		model = new DefaultTableModel();
		String[] headers = {"Usuario", "Posicion"}; //HEADERS FOR THE LIST
		model.setColumnIdentifiers(headers);
		
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btnEdit = new JButton("EDITAR");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUser regUser = new CreateUser(seleUser);
				regUser.setModal(true);
				regUser.setVisible(true);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
			}
		});
		btnEdit.setBounds(543, 490, 89, 23);
		add(btnEdit);
		
		btnDelete = new JButton("ELIMINAR");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(seleUser != null) {
					int option = JOptionPane.showConfirmDialog(null, "Desea eliminar el usuario: " + seleUser.getName() + "?", "Confirmacion", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						Clinic.getInstance().removeUser(seleUser);
						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);
						loadUsers();
					}
				}
			}
		});
		btnDelete.setBounds(436, 490, 89, 23);
		add(btnDelete);
		loadUsers();
	}
	
	public static void loadUsers() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];
		
		for (User user : Clinic.getInstance().getMyUsers()) {
			row[0] = " " + user.getName();
			row[1] = " " + user.getType();
			model.addRow(row);
		}
		
	}
	
}



















