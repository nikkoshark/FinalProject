package AdminPanels;

import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import AdminJDialogs.CreateDiseaseVaccine;
import Dashboards.DiseaseInfo;
import logic.SqlConnection;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.ScrollPaneConstants;

public class ListDisease extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private static Object[] row; 
	private JButton btnDelete;
	private JButton btnEdit;
	private String selDisease = null;
	private DiseaseInfo dinfo;

	/**
	 * Create the panel.
	 */
	public ListDisease() {
		setBackground(SystemColor.activeCaption);
		setSize(1320, 551);
		setLayout(null);
		setVisible(false);
		
		dinfo = new DiseaseInfo();
		
		JLabel lblTitle = new JLabel("LISTAR ENFERMEDADES");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(10, 11, 285, 42);
		add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 64, 612, 415);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, BorderLayout.CENTER);
		
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
					selDisease = (String) table.getModel().getValueAt(index, 0);
					//DiseaseInfo.refreshSQLChart(selDisease);
				}
			}
		});
		model = new DefaultTableModel();
		String[] headers = {"Código", "Nombre", "Descripción", "Estatus"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btnDelete = new JButton("ELIMINAR");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(selDisease != null) {
					int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la enfermedad: " + selDisease + "?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						try { 
							Connection con = SqlConnection.getConnection();
							PreparedStatement ps;
							ps = con.prepareStatement("DELETE FROM disease WHERE id=? ");
							ps.setString(1, selDisease);
							//EL ÓRDEN DE CÓMO SE VA A INSERTAR ES EN BASE AL QUERY
							
							ps.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "SE BORRÓ NMMS QUE FELIZ!");
							//clean();
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "error dentro de ELIMINAR. sadge. " + e1.toString());
							e1.printStackTrace();
						}
						
						
						/*Clinic.getInstance().removeDisease(selDisease);
						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);*/
						loadSQLDisease();
					}
				} 				
			}
		});
		btnDelete.setBounds(436, 490, 89, 23);
		add(btnDelete);
		
		btnEdit = new JButton("EDITAR");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDiseaseVaccine regDisease = new CreateDiseaseVaccine("disease", selDisease, null);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				regDisease.setModal(true);
				regDisease.setVisible(true);
			}
		});
		btnEdit.setBounds(543, 490, 89, 23);
		add(btnEdit);
		
		JPanel dash = new JPanel();
		dash.setLayout(null);
		dash.setBackground(SystemColor.activeCaption);
		dash.setBounds(743, 80, 431, 415);
		add(dash);
		
		dash.add(dinfo);

		loadSQLDisease();
	}
	

	public static void loadSQLDisease() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];
		PreparedStatement ps;
		ResultSet rs;
		ResultSetMetaData rsmd;
		int columns;
		
		try {
			Connection con = SqlConnection.getConnection();
			ps = con.prepareStatement("SELECT id, name, description, in_observation  FROM disease");
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			columns = rsmd.getColumnCount();
			
			
			while (rs.next()) {
				Object[] fila = new Object[columns];
				for(int indice=0; indice<columns; indice++) {
					fila[indice] = rs.getObject(indice+1);
					if (indice == 3) {
						fila[indice] = "Neutro";
						if(rs.getInt("in_observation") == 1) {
							fila[indice] = "Vigilado";
						}
					}
				}
				model.addRow(fila);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error dentro listdisease load " +e.toString());
			e.printStackTrace();
		}
		
	}
	
	
	/*
	public static void loadDisease() {
		model.setRowCount(0);
		row = new Object[table.getColumnCount()];

		for(Disease disease : Clinic.getInstance().getMyDiseases()) {
			row[0] = disease.getCode();
			row[1] = disease.getName();
			row[2] = disease.getDescription();
			row[3] = " Neutro";
			if (disease.isWatched()) {					
				row[3] = " Vigilado";
			}
			model.addRow(row);
			
		}
	}*/
}
