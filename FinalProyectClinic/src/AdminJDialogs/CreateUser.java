package AdminJDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.bcel.internal.generic.NEW;

import AdminPanels.ListUsers;
import Dashboards.UserInfo;
import logic.Clinic;
import logic.SqlConnection;
import logic.Vaccine;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ActionEvent;

public class CreateUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField txtPsw;
	private static JComboBox comboBox;
	private int userSQL;

	/**
	 * Create the dialog.
	 */
	public CreateUser(int seleUser) {
		userSQL = seleUser;
		
		setTitle("Modificar");
		if (userSQL == (-1)) {
			setTitle("Registrar");
		}
		setBounds(100, 100, 518, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblTitle = new JLabel("NUEVO USUARIO");
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTitle.setBounds(159, 44, 188, 25);
			contentPanel.add(lblTitle);

			JLabel lblHorarioEntrada = new JLabel("Usuario:");
			lblHorarioEntrada.setBounds(122, 114, 120, 14);
			contentPanel.add(lblHorarioEntrada);

			txtUser = new JTextField();
			txtUser.setColumns(10);
			txtUser.setBounds(122, 139, 110, 20);
			contentPanel.add(txtUser);

			JLabel lblHorarioSalida = new JLabel("Contrase\u00F1a:");
			lblHorarioSalida.setBounds(122, 173, 120, 14);
			contentPanel.add(lblHorarioSalida);
	
			txtPsw = new JPasswordField();
			txtPsw.setBounds(122, 198, 110, 20);
			contentPanel.add(txtPsw);
			
			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(262, 114, 46, 14);
			contentPanel.add(lblTipo);

			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<SELECCIONAR>", "Admin", "Medic", "Secretary"}));
			comboBox.setBounds(262, 139, 127, 20);
			contentPanel.add(comboBox);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("MODIFICAR");
				if (userSQL == (-1)) {
					btnSave.setText("SALVAR");
				}
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (Clinic.getInstance().isUniqueUserSQL(userSQL,txtUser.getText()) || userSQL != (-1))  {
							if (!txtUser.getText().isEmpty() && String.valueOf(txtPsw.getPassword()).length() > 0 && comboBox.getSelectedIndex() > 0) {
								if (userSQL == (-1)) {									
									
									try {
										Connection con = SqlConnection.getConnection();
										PreparedStatement ps;
										ps = con.prepareStatement("INSERT INTO [user](username, password, id_user_position)"
												+ "VALUES (?,?,?)");
										ps.setString(1, txtUser.getText());
										ps.setString(2, String.valueOf(txtPsw.getPassword()) );
										ps.setInt(3, Integer.valueOf(comboBox.getSelectedIndex()));
										ps.executeUpdate();
										
										
										JOptionPane.showMessageDialog(null, "se ha guardado! wooo!");
										ListUsers.loadSQLUsers();
										clean();
										
										
									} catch (Exception e2) {
										JOptionPane.showMessageDialog(null, "error dentro de guardar info nueva user. " + e2.toString());
										e2.printStackTrace();
									}
									
									/*
									User insUser = new User(String.valueOf(comboBox.getSelectedItem()), txtUser.getText(), String.valueOf(txtPsw.getPassword()));
									Clinic.getInstance().insertUser(insUser);
									JOptionPane.showMessageDialog(null, "Registro hecho.", "Registro", JOptionPane.INFORMATION_MESSAGE);
									clean();
									*/
								}
								else {
									
									try {
										Connection con = SqlConnection.getConnection();
										PreparedStatement ps = con.prepareStatement("UPDATE [user] SET username=?,"
												+ "password=?, id_user_position=? WHERE id=?");
										ps.setString(1, txtUser.getText());
										ps.setString(2, String.valueOf(txtPsw.getPassword()) );
										ps.setInt(3, Integer.valueOf(comboBox.getSelectedIndex()));
										ps.setInt(4, userSQL);
										ps.executeUpdate();
										
										JOptionPane.showMessageDialog(null, "SE HA MODIFICADO LESSGOOO!");
										
										
										
									} catch (Exception e2) {
										JOptionPane.showMessageDialog(null, "error dentro de update user. " + e2.toString());
										e2.printStackTrace();
									}
									
									/*
									userSQL.setName(txtUser.getText());
									userSQL.setPassword(String.valueOf(txtPsw.getPassword()));
									userSQL.setType(String.valueOf(comboBox.getSelectedItem()));
									
									Clinic.getInstance().modifiedUser(user);*/
									
									ListUsers.loadSQLUsers();
									dispose();
									UserInfo.refreshUser();
								}
								
								
							}
							else {
								JOptionPane.showMessageDialog(null, "¡Parámetro(s) sin completar!\nPor favor completar los campos.", "Información Vacía", JOptionPane.ERROR_MESSAGE);								
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Este usuario ya ha sido registrado.", "Usuario Inválido", JOptionPane.ERROR_MESSAGE);
						}
					}

				});
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
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
		loadSQLUser();
	}
	
	private void clean() {
		txtUser.setText("");
		txtPsw.setText("");
		comboBox.setSelectedIndex(0);
						
	}
	
	
	private void loadSQLUser() {
		if (userSQL != (-1)) {
			try {
				PreparedStatement ps;
				ResultSet rs;
				Connection con = SqlConnection.getConnection();
				
				ps = con.prepareStatement("SELECT username, password,"
						+ "id_user_position FROM [user]"
						+ "WHERE id = ?");
				ps.setInt(1, userSQL); //ESTE ES EL ID, PRIMERA POSICION
				rs = ps.executeQuery();
				
				while (rs.next()) {
					txtUser.setText(rs.getString("username"));
					txtPsw.setText(rs.getString("password"));
					comboBox.setSelectedIndex(rs.getInt("id_user_position"));
				}
				//ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "ALM SE ESTA VIENDO");
				
				
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "error dentro del loadsqluser create. " +e.toString());
				e.printStackTrace();
			}
		}
	}
	
	
	
	private void loadInCBUsers() {
		comboBox.removeAllItems();
		comboBox.insertItemAt(new String("<SELECCIONAR>"), 0);
		
		try {
			Connection con = SqlConnection.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			
			ps = con.prepareStatement("SELECT COUNT(*) FROM [user]");
			rs = ps.executeQuery();
			ResultSetMetaData rsmd= rs.getMetaData();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		for(Vaccine aux : Clinic.getInstance().getMyVaccines()) {
			/*
			if (patient.getMyVaccines().size() > 0) {				
				for (Vaccine vaccine : patient.getMyVaccines()) {
					if (vaccine.getName() == aux.getName()) {
						
					}
					else {
						String name = new String(aux.getName());
						cbVaccine.addItem(name);
					}
				}
			}
			
			
			String name = new String(aux.getName());
			cbVaccine.addItem(name);
			
			*/
		}
		
		comboBox.setSelectedIndex(0);
		
		
		
		
	}
	
	
	
	/*
	private void loadUser() {
		if (user != null) {
			txtUser.setText(user.getName());
			txtPsw.setText(user.getPassword());
			
			if (user.getType().equalsIgnoreCase("Secretaria")) {
				comboBox.setSelectedIndex(1);
			}
			else if (user.getType().equalsIgnoreCase("Medico")) {
				comboBox.setSelectedIndex(2);
			}
			else {
				comboBox.setSelectedIndex(3);
			}
		}
	}*/
	
	
}

