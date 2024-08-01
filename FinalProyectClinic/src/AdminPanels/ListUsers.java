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
import Dashboards.UserInfo;
import logic.Clinic;
import logic.SqlConnection;
import login.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class ListUsers extends JPanel {
	
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private int seleUser = (-1);
	private JButton btnDelete;
	private JButton btnEdit;
	private UserInfo usin;

	/**
	 * Create the panel.
	 */
	public ListUsers() {
		setBackground(SystemColor.activeCaption);
		setSize(1320, 551);
		setLayout(null);
		setVisible(false);
		
		usin = new UserInfo();
		usin.setLocation(38, 38);
		
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
		
		table = new JTable() {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				
				if (index >= 0) {
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
					seleUser = (int) table.getModel().getValueAt(index, 0); //guardar informacion del medico que se ha encontrado aqui

				}
			}
		});
		
		
		model = new DefaultTableModel();
		String[] headers = {"Id","Usuario", "Posición"}; //HEADERS FOR THE LIST
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
				if(seleUser != (-1)) {
					try { 
						Connection con = SqlConnection.getConnection();
						PreparedStatement ps;
						ps = con.prepareStatement("DELETE FROM [user] WHERE id=? ");
						ps.setInt(1, seleUser);
						
						ps.executeUpdate();

						JOptionPane.showMessageDialog(null, "¡Se ha borrado!");
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "error dentro de ELIMINAR USUARIO." + e1.toString());
						e1.printStackTrace();
					}
						loadSQLUsers();
					
				}
			}
		});
		btnDelete.setBounds(436, 490, 89, 23);
		add(btnDelete);
		
		JPanel dash = new JPanel();
		dash.setBackground(SystemColor.activeCaption);
		dash.setBounds(811, 64, 431, 415);
		add(dash);
		dash.setLayout(null);
		
		dash.add(usin);
		loadSQLUsers();
	}
	

	public static void loadSQLUsers() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];
		PreparedStatement ps;
		ResultSet rs;
		ResultSetMetaData rsmd;
		int columns;
		
		try {
			Connection con = SqlConnection.getConnection();
			ps = con.prepareStatement("SELECT [user].id, [user].username, user_position.name FROM [user]"
					+ "JOIN user_position ON user_position.id = [user].id_user_position");
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
			JOptionPane.showMessageDialog(null, "error dentro de loadsql list " +e.toString());
			e.printStackTrace();
		}
		
		
	}
	
	
}