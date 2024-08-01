package AdminJDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AdminPanels.ListDisease;
import AdminPanels.ListVaccine;
import logic.SqlConnection;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class CreateDiseaseVaccine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode = new JTextField();
	private JTextField txtName;
	private JLabel lblTitle;
	private JTextArea txtDesc;
	private String disease = null;
	private String vaccine = null;
	private String type = null;
	private JCheckBox chckbxIsWatched = new JCheckBox("En Vigilancia");


	/**
	 * Create the dialog.
	 */
	public CreateDiseaseVaccine(String typeToRegister, String dis, String vac) {
		
		disease = dis;
		vaccine = vac;
		type = typeToRegister;
		
		setTitle("Modificar");
		if(disease == null && vaccine == null) {
			setTitle("Registrar");
		}
		
		if(type.equalsIgnoreCase("disease")) {
			lblTitle = new JLabel("REGISTRO ENFERMEDAD");
			chckbxIsWatched.setVisible(true);
			//txtCode.setText(getCodeDisease(Clinic.getInstance().getCodeDisease()));
		}else {
			lblTitle = new JLabel("REGISTRO VACUNA");
			chckbxIsWatched.setVisible(false);
			//txtCode.setText(getCodeVaccine(Clinic.getInstance().getCodeVaccine()));
		}
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTitle.setBounds(200, 20, 230, 25);
			contentPanel.add(lblTitle);
		}
		{
			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(32, 9, 46, 14);
			contentPanel.add(lblCode);
			txtCode.setColumns(10);
			txtCode.setBounds(32, 32, 120, 20);
			contentPanel.add(txtCode);

			JLabel lblAddress = new JLabel("Descripci\u00F3n");
			lblAddress.setBounds(32, 113, 120, 14);
			contentPanel.add(lblAddress);

			txtDesc = new JTextArea();
			txtDesc.setLineWrap(true);
			txtDesc.setBounds(32, 136, 376, 73);
			contentPanel.add(txtDesc);

			JLabel lblName = new JLabel("Nombre");
			lblName.setBounds(32, 61, 46, 14);
			contentPanel.add(lblName);
			
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(32, 84, 185, 20);
			contentPanel.add(txtName);
			
			chckbxIsWatched.setBackground(SystemColor.activeCaption);
			chckbxIsWatched.setBounds(240, 82, 113, 25);
			contentPanel.add(chckbxIsWatched);

			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCreate = new JButton("MODIFICAR");
				if(disease == null && vaccine == null) {
					btnCreate.setText("REGISTRAR");
				}
				btnCreate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (!txtCode.getText().isEmpty() && !txtName.getText().isEmpty() && !txtDesc.getText().isEmpty()) {
							if(vaccine == null && disease == null) {
								if(type.equalsIgnoreCase("disease")) {
									

									try {
										Connection con = SqlConnection.getConnection();
										PreparedStatement ps;
										ps = con.prepareStatement("INSERT INTO disease(id, name, description, in_observation)"
												+ "VALUES (?,?,?,?)");
										ps.setString(1, txtCode.getText());
										ps.setString(2, txtName.getText());
										ps.setString(3, txtDesc.getText());
										if(chckbxIsWatched.isSelected() == true) {
											ps.setInt(4, 1);
										} else {
											ps.setInt(4, 0);
										}
										ps.executeUpdate();

										JOptionPane.showMessageDialog(null, "¡Se ha guardado!");
										ListDisease.loadSQLDisease();
										clear();
										
										
									} catch (Exception e2) {
										JOptionPane.showMessageDialog(null, "error dentro de guardar info nueva disease. " + e2.toString());
										e2.printStackTrace();
									}
								} else {
									

									try {
										Connection con = SqlConnection.getConnection();
										PreparedStatement ps;
										ps = con.prepareStatement("INSERT INTO vaccine(id, name, description)"
												+ "VALUES (?,?,?)");
										ps.setString(1, txtCode.getText());
										ps.setString(2, txtName.getText());
										ps.setString(3, txtDesc.getText());
										ps.executeUpdate();
										

										JOptionPane.showMessageDialog(null, "¡Se ha guardado!");
										ListVaccine.loadSQLVaccine();
										clear();
										
										
									} catch (Exception e2) {
										JOptionPane.showMessageDialog(null, "error dentro de guardar info nueva vaccine. " + e2.toString());
										e2.printStackTrace();
									}
									
									clear();
								}
							} else  {
								if (type.equalsIgnoreCase("disease")){
									
									try {
										Connection con = SqlConnection.getConnection();
										PreparedStatement ps = con.prepareStatement("UPDATE disease SET name=?,"
												+ "description=?, in_observation=? WHERE id=?");
										ps.setString(1, txtName.getText());
										ps.setString(2, txtDesc.getText());
										if(chckbxIsWatched.isSelected() == true) {
											ps.setInt(3, 1);
										} else {
											ps.setInt(3, 0);
										}
										ps.setString(4, disease);
										
										ps.executeUpdate();

										JOptionPane.showMessageDialog(null, "¡Se ha modificado!");
										
										
									} catch (Exception e2) {
										JOptionPane.showMessageDialog(null, "error dentro de update disease. " + e2.toString());
										e2.printStackTrace();
									}
									
									ListDisease.loadSQLDisease();
									dispose();
									
									
								} else {
									
									try {
										Connection con = SqlConnection.getConnection();
										PreparedStatement ps = con.prepareStatement("UPDATE vaccine SET name=?,"
												+ "description=? WHERE id=?");
										ps.setString(1, txtName.getText());
										ps.setString(2, txtDesc.getText());
										ps.setString(3, vaccine);
										
										ps.executeUpdate();
										
										JOptionPane.showMessageDialog(null, "¡Se ha modificado!");
										
										
										
									} catch (Exception e2) {
										JOptionPane.showMessageDialog(null, "error dentro de update vaccine. " + e2.toString());
										e2.printStackTrace();
									}
									
									ListVaccine.loadSQLVaccine();
									dispose();
								}
							}
							ListDisease.loadSQLDisease();
							ListVaccine.loadSQLVaccine();
								
						} else {
							JOptionPane.showMessageDialog(null, "¡Parámetro(s) sin completar!\nPor favor completar los campos.", "Información Vacía", JOptionPane.ERROR_MESSAGE);
						}
						
						
					}

				});
				buttonPane.add(btnCreate);
				getRootPane().setDefaultButton(btnCreate);
			}
			{
				JButton btnCancel = new JButton("CANCEL");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnCancel);
			}
		}
		loadSQLVD();
	}

	private void clear() {
		
		txtName.setText("");
		txtDesc.setText("");	
		
		if (type.equalsIgnoreCase("disease")) {
			chckbxIsWatched.setSelected(false);
			txtCode.setText("");
			//txtCode.setText(getCodeDisease(Clinic.getInstance().getCodeDisease()));
		}
		else {
			txtCode.setText("");
			//txtCode.setText(getCodeVaccine(Clinic.getInstance().getCodeVaccine()));			
		}
	}
	
	private void loadSQLVD() {
		if (disease != null) {
			try {
				PreparedStatement ps;
				ResultSet rs;
				Connection con = SqlConnection.getConnection();
				
				ps = con.prepareStatement("SELECT id, name, description, in_observation FROM disease "
						+ "WHERE id = ?");
				ps.setString(1, disease);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					txtCode.setText(rs.getString("id"));
					txtName.setText(rs.getString("name"));
					txtDesc.setText(rs.getString("description"));
					if(rs.getInt("in_observation") == 1) 
						chckbxIsWatched.setSelected(true);
					else 
						chckbxIsWatched.setSelected(false);
					
				}
				JOptionPane.showMessageDialog(null, "ALM SE ESTA VIENDO");
				
				
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "error dentro del loaddisease . " +e.toString());
				e.printStackTrace();
			}
		} else if (vaccine != null) {
			try {
				PreparedStatement ps;
				ResultSet rs;
				Connection con = SqlConnection.getConnection();
				
				ps = con.prepareStatement("SELECT id, name, description FROM vaccine "
						+ "WHERE id=?");
				ps.setString(1, vaccine); 
				rs = ps.executeQuery();
				
				while (rs.next()) {
					txtCode.setText(rs.getString("id"));
					txtName.setText(rs.getString("name"));
					txtDesc.setText(rs.getString("description"));
				}
				
				
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "error dentro de THIS loadvaccine. " +e.toString());
				e.printStackTrace();
			}
		}
	}
	
	/*
	private static String getCodeVaccine(int codeVaccine) {
		int total = codeVaccine / 10;
		String code = null;
		
		code = "V-0000" + codeVaccine;
		
		if (total >= 1 && total < 10) {
			code = "V-000" + codeVaccine;
		}
		else if (total >= 10 && total < 100) {
			code = "V-00" + codeVaccine;
		}
		else if (total >= 100 && total < 1000) {
			code = "V-0" + codeVaccine;
		}
		else if (total >= 1000) {
			code = "V-" + codeVaccine;
		}
		
		return code;
	}*/

	/*
	private static String getCodeDisease(int codeDisease) {
		int total = codeDisease / 10;
		String code = null;
		
		code = "D-0000" + codeDisease;
		
		if (total >= 1 && total < 10) {
			code = "D-000" + codeDisease;
		}
		else if (total >= 10 && total < 100) {
			code = "D-00" + codeDisease;
		}
		else if (total >= 100 && total < 1000) {
			code = "D-0" + codeDisease;
		}
		else if (total >= 1000) {
			code = "D-" + codeDisease;
		}
		
		return code;
	}*/
}
