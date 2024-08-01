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
import logic.SqlConnection;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListMedic extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private static DefaultTableModel modelSQL;
	private static Object[] row;
	private String selMedic = null;
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
		
		JLabel lblNewLabel = new JLabel("LISTAR MÉDICOS");
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
				
				//try {
					
					/*
					
					PreparedStatement ps;
					ResultSet rs;
					ResultSetMetaData rsmd;

					Connection con = SqlConnection.getConnection();
					ps = con.prepareStatement("SELECT nombre, especialidad FROM ejemplo_noid WHERE id=?");
					ps.setString(1, code);
									
					rs = ps.executeQuery();
					
					while(rs.next()) {
						
					}
					
					
					rsmd = rs.getMetaData();
					columns = rsmd.getColumnCount();
					*/
							
							
							
					/*
					if(index>=0) {
						btnEdit.setEnabled(true);
						btnDelete.setEnabled(true);
						String code = (String) table.getModel().getValueAt(index, 0);
						selMedic = Clinic.getInstance().searchPersonByCode(code);
					}
						*/	
							
				//}catch(Exception err) {
				//	JOptionPane.showMessageDialog(null, err.toString());
				//}
			
			
			
			
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				

				int index = table.getSelectedRow();
				//System.out.println(index);
				if(index >=0) {
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
					selMedic = (String) table.getModel().getValueAt(index, 0); //guardar informacion del medico que se ha encontrado aqui

					//System.out.println(selMedic);
				}
				
				
			}
		});
		scrollPane.setViewportView(table);
		

		modelSQL = new DefaultTableModel();
		String[] headers = {"Id", "Nombre", "Especialidad"}; //HEADERS FOR THE LIST
		modelSQL.setColumnIdentifiers(headers);
		table.setModel(modelSQL);
		
		
		
		
		btnEdit = new JButton("EDITAR");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				CreateMedic regMedic = new CreateMedic(selMedic);
				regMedic.setModal(true);
				regMedic.setVisible(true);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				selMedic = null; //RECENTLY ADDED.
				
			}
		});
		btnEdit.setBounds(543, 488, 89, 23);
		add(btnEdit);
		
		btnDelete = new JButton("ELIMINAR");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selMedic != null) {
					/*
					String code = txtCode.getText();
					*/
					 try { 
							Connection con = SqlConnection.getConnection();
							PreparedStatement ps;
							ps = con.prepareStatement("DELETE FROM medic WHERE id_person=? "
									+ "DELETE FROM person WHERE id=?");
							ps.setString(1, selMedic);
							ps.setString(2, selMedic);
							//EL ÓRDEN DE CÓMO SE VA A INSERTAR ES EN BASE AL QUERY
							
							ps.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "SE BORRÓ NMMS QUE FELIZ!");
							//clean();
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "error dentro de ELIMINAR. sadge. " + e1.toString());
							e1.printStackTrace();
						}
					
					
					//int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el médico: " + selMedic.getName() + "?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
					//if (option == JOptionPane.OK_OPTION) {
						/*Clinic.getInstance().removePerson(selMedic); //ERROR MAKER
						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);*/
						loadSQLMedic();
					//}
				
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(444, 488, 89, 23);
		add(btnDelete);
		
		
		loadSQLMedic();

	}
	

	public static void loadSQLMedic() {
		modelSQL.setRowCount(0);
		row = new Object[table.getColumnCount()];
		PreparedStatement ps;
		ResultSet rs;
		ResultSetMetaData rsmd;
		int columns;
		
		
		try {
			Connection con = SqlConnection.getConnection();
			ps = con.prepareStatement("SELECT id_person, person.name, speciality.name FROM medic "
					+ "JOIN person ON medic.id_person = person.id	"
					+ "JOIN speciality ON speciality.id = medic.id_speciality");
			
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			columns = rsmd.getColumnCount();
			
			while(rs.next()) {
				Object[] fila = new Object[columns];
				for(int indice=0; indice<columns; indice++) {
					fila[indice] = rs.getObject(indice+1);
				}
				modelSQL.addRow(fila);
						
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error dentro de loadsql list " +e.toString());
		}
	}
	
	/*
	public static void loadMedic() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];
		
		for (Person med : Clinic.getInstance().getMyPersons()) {
			if(med instanceof Medic) {
				row[0] = med.getCode();
				row[1] = med.getName();
				row[2] = ((Medic) med).getSpeciality();
				model.addRow(row);
			}
		}
	}*/

}
