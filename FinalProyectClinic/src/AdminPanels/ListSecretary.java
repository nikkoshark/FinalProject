package AdminPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class ListSecretary extends JPanel {
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;

	/**
	 * Create the panel.
	 */
	public ListSecretary() {
		setBackground(SystemColor.activeCaption);
		setSize(1320, 551);
		setLayout(null);
		setVisible(false);
		
		JLabel lblNewLabel = new JLabel("LIST SECRETARY");
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
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		String[] headers = {"Código", "Nombre", "Descripción"}; //HEADERS FOR THE LIST
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		
		JButton btnEdit = new JButton("EDITAR");
		btnEdit.setBounds(543, 490, 89, 23);
		add(btnEdit);

	}

}
