package AdminJDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import logic.Clinic;
import logic.SqlConnection;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import AdminPanels.ListMedic;

import javax.swing.JFormattedTextField;

public class CreateMedic extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JFormattedTextField ftxtSSN;
	private JTextField txtLastName;
	private JTextField txtName;
	private JFormattedTextField ftxtPhone;
	private JTextArea txtAAddress;
	private JComboBox cbSex;
	private JComboBox cbSpeciality;
	private JDateChooser dateChooserBirthday;
	private JCheckBox chbxAvailable;
	private String medicSQL = null;
	private ArrayList<String> optionString;
	private Date dateCompare = null;


	/**
	 * Create the dialog.
	 */
	public CreateMedic(String code) {
		medicSQL = code;
		
		setTitle("Modificar");
		if(medicSQL == null) {
			setTitle("Registrar");
		}
		setBounds(100, 100, 519, 399);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lblNuevoMdico = new JLabel("REGISTRO M\u00C9DICO");
			lblNuevoMdico.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
			lblNuevoMdico.setBounds(161, 27, 174, 25);
			contentPanel.add(lblNuevoMdico);


			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(39, 60, 46, 14);
			contentPanel.add(lblCode);

			txtCode = new JTextField();
			txtCode.setEditable(true); //CAMBIADO PARA EL ESPERIMENTO XD
			txtCode.setText((Clinic.getInstance().getSQLCodePerson()).toString());
			txtCode.setColumns(10);
			txtCode.setBounds(39, 77, 85, 20);
			contentPanel.add(txtCode);

			JLabel lblCedula = new JLabel("C\u00E9dula");
			lblCedula.setBounds(134, 63, 109, 14);
			contentPanel.add(lblCedula);

			ftxtSSN = new JFormattedTextField();
			ftxtSSN.setBounds(134, 77, 99, 20);
			contentPanel.add(ftxtSSN);
			
			MaskFormatter maskssn;
			try {
				maskssn = new MaskFormatter("###-#######-#");
				maskssn.install(ftxtSSN);					
				
			} catch (ParseException e) {
				e.printStackTrace();
			}

			JLabel lblApellido = new JLabel("Apellido[s]");
			lblApellido.setBounds(248, 115, 126, 14);
			contentPanel.add(lblApellido);

			txtLastName = new JTextField();
			txtLastName.setColumns(10);
			txtLastName.setBounds(247, 130, 212, 20);
			contentPanel.add(txtLastName);

			JLabel lblNombre = new JLabel("Nombre[s]");
			lblNombre.setBounds(39, 115, 85, 14);
			contentPanel.add(lblNombre);
			
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(38, 130, 199, 20);
			contentPanel.add(txtName);

			JLabel lblEspecialidad = new JLabel("Especialidad");
			lblEspecialidad.setBounds(174, 171, 110, 14);
			contentPanel.add(lblEspecialidad);
			
			cbSpeciality = new JComboBox();
			cbSpeciality.setModel(new DefaultComboBoxModel(new String[] {"<SELECCIONAR>", "Psic\u00F3logo", "Ginec\u00F3logo", "Cirujano", "Pediatra", "Cardiólogo", "Oftalmólogo","Dermatólogo"}));
			cbSpeciality.setBounds(174, 183, 161, 20);
			contentPanel.add(cbSpeciality);

			JLabel lblDir = new JLabel("Direcci\u00F3n");
			lblDir.setBounds(39, 223, 84, 14);
			contentPanel.add(lblDir);

			txtAAddress = new JTextArea();
			txtAAddress.setLineWrap(true);
			txtAAddress.setBounds(38, 238, 421, 68);
			contentPanel.add(txtAAddress);

			JLabel lblTel = new JLabel("Tel\u00E9fono");
			lblTel.setBounds(346, 171, 113, 14);
			contentPanel.add(lblTel);
			
			ftxtPhone = new JFormattedTextField();
			ftxtPhone.setBounds(346, 183, 113, 20);
			contentPanel.add(ftxtPhone);

			MaskFormatter maskphone;
			try {
				maskphone = new MaskFormatter("###-###-####");
				maskphone.install(ftxtPhone);					
				
			} catch (ParseException e) {
				e.printStackTrace();
			}

			JLabel lblSexo = new JLabel("Sexo");
			lblSexo.setBounds(243, 63, 99, 14);
			contentPanel.add(lblSexo);
			
			cbSex = new JComboBox();
			cbSex.setModel(new DefaultComboBoxModel(new String[] {"<SELECCIONAR>", "Femenino", "Masculino"}));
			cbSex.setBounds(243, 77, 120, 20);
			contentPanel.add(cbSex);
			
			JLabel lblDate = new JLabel("Fecha de Nacimiento");
			lblDate.setBounds(39, 171, 143, 14);
			contentPanel.add(lblDate);
			
			dateCompare = new Date();
			dateChooserBirthday = new JDateChooser();
			dateChooserBirthday.setDate(dateCompare);
			dateChooserBirthday.setBounds(39, 183, 128, 20);
			contentPanel.add(dateChooserBirthday);
			
			chbxAvailable = new JCheckBox("Disponible");
			chbxAvailable.setBackground(SystemColor.activeCaption);
			chbxAvailable.setBounds(369, 76, 99, 23);
			contentPanel.add(chbxAvailable);
		}
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAdd = new JButton("MODIFICAR");
				if(medicSQL == null) {
					btnAdd = new JButton("SALVAR");
				}
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!ftxtSSN.getText().isEmpty() && !txtName.getText().isEmpty() &&
							!txtLastName.getText().isEmpty() && cbSpeciality.getSelectedIndex() > 0 &&
							!ftxtPhone.getText().isEmpty() && dateChooserBirthday.getDate().getYear() < dateCompare.getYear() - 20) {
							//VERIFICA QUE CADA UNA DE LOS TXT NO ESTÉ VACÍO
							
							if(Clinic.getInstance().isUniqueSSNSQL(ftxtSSN.getText()) || medicSQL != null) {
								
								String code = txtCode.getText();
								String ssn =  ftxtSSN.getText();
								String name = txtName.getText();
								String lastName = txtLastName.getText();
								String phone = ftxtPhone.getText();
								String address = txtAAddress.getText();
								Date date = dateChooserBirthday.getDate();
								char sex = String.valueOf(cbSex.getSelectedItem()).charAt(0);
								int speciality = cbSpeciality.getSelectedIndex();
								boolean available = chbxAvailable.isSelected();
								String medic_user = txtCode.getText();
								
								
								if(medicSQL == null) {
								//PARA GUARDAR LA INFORMACIÓN <<NUEVA>>. SE GUARDA LO QUE ESTÉ EN LOS TXT DENTRO DE VARIABLES
								//PARA LUEGO PASARLO A LA CONEXIÓN DE SQL E INSERTARLO
								
									try { 
										Connection con = SqlConnection.getConnection();
										PreparedStatement ps;
										ps = con.prepareStatement("INSERT INTO person(id, ssn, name, last_name, phone,"
												+ "direction, date_birth, sex) VALUES(?,?,?,?,?,?,?,?);"
												+ "INSERT INTO medic(id_person, id_speciality, is_avaible, id_user) VALUES(?,?,?,?)");
										ps.setString(1, code);
										ps.setString(2, ssn);
										ps.setString(3, name);
										ps.setString(4, lastName);
										ps.setString(5, phone);
										ps.setString(6, address);
										ps.setObject(7, date, java.sql.Types.DATE);
										ps.setString(8, Character.toString(sex));
										ps.setString(9, code);
										
										ps.setInt(10, speciality);
										if(available == true) {
											ps.setInt(11, 1);
										} else if(available == false){
											ps.setInt(11, 0);
										}
										ps.setString(12, medic_user);
										
										ps.executeUpdate();
										JOptionPane.showMessageDialog(null, "¡Se ha guardado!");
										
										
										ListMedic.loadSQLMedic(); //PARA QUE SE CARGUE AUTOMÁTICO LA LISTA YA QUE ESTÁ EN OTRA SECCIÓN
										
										clean();
										
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "error dentro de guardar info nueva. " + e1.toString());
										e1.printStackTrace();
									}

									
								} else {
									
									try {
										Connection con = SqlConnection.getConnection();
										PreparedStatement ps;
										ps = con.prepareStatement("UPDATE person SET ssn=?, "
												+ "name=?, last_name=?, phone=?, direction=?, date_birth=?, sex=? WHERE id=? "
												+ "UPDATE medic SET id_speciality=?, is_avaible=? WHERE id_person=? ");
										ps.setString(1, ssn);
										ps.setString(2, name);
										ps.setString(3, lastName);
										ps.setString(4, phone);
										ps.setString(5, address);
										ps.setObject(6, date, java.sql.Types.DATE);
										ps.setString(7, Character.toString(sex));
										ps.setString(8, code);
										
										ps.setInt(9, speciality);
										
										
										if(available == true) {
											ps.setInt(10, 1);
										} else if(available == false){
											ps.setInt(10, 0);
										}
										ps.setString(11, code);
											
										ps.executeUpdate();
										JOptionPane.showMessageDialog(null, "¡Se ha modificado!");
											
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "error dentro de guardar info nueva INSIDE. " + e1.toString());
										e1.printStackTrace();
									}
									
									ListMedic.loadSQLMedic();
									dispose();
								}
								
							} else {
								JOptionPane.showMessageDialog(null, "Este médico ya ha sido registrado.", "Médico Inválido", JOptionPane.ERROR_MESSAGE);
							}

						}else {
							if (dateChooserBirthday.getDate().getYear() < dateCompare.getYear() - 20) {
								JOptionPane.showMessageDialog(null, "¡Parámetro(s) sin completar!\nPor favor completar los campos.", "Información Vacía", JOptionPane.ERROR_MESSAGE);
							}
							else {								
								JOptionPane.showMessageDialog(null, "El medico no cumple la edad requeridad por la organizacion (20 años minimo).", "Información Invalida", JOptionPane.ERROR_MESSAGE);
							}
						}
						
					}
				});
				buttonPane.add(btnAdd);
				getRootPane().setDefaultButton(btnAdd);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		loadSQLMedic();
	}
	

	
	//WILL NEED TO BE REMOVED. I THINK.
	//OR FIXED, SO THAT IT GOES THROUGH SQL INSTEAD OF HERE.
	private static String getCodePerson(int codePerson) {
		//int total = codePerson / 10;
		String code = null;
		/*
		code = "P-0000" + codePerson;
		
		if (total >= 1 && total < 10) {
			code = "P-000" + codePerson;
		}
		else if (total >= 10 && total < 100) {
			code = "P-00" + codePerson;
		}
		else if (total >= 100 && total < 1000) {
			code = "P-0" + codePerson;
		}
		else if (total >= 1000) {
			code = "P-" + codePerson;
		}
		*/
		return code;
	}


	private void clean(){
		txtAAddress.setText("");
		txtCode.setText(getCodePerson(Clinic.getInstance().getCodePerson()));
		txtLastName.setText("");
		txtName.setText("");
		ftxtPhone.setText("");
		cbSex.setSelectedIndex(0);
		cbSpeciality.setSelectedIndex(0);
		ftxtSSN.setText("");
		chbxAvailable.setSelected(false);
		dateChooserBirthday.setDate(null);
	}
	
	
	private void loadSQLMedic() {

		if(medicSQL != null) {
			try {
				PreparedStatement ps;
				ResultSet rs;
				Connection con = SqlConnection.getConnection();
				ps = con.prepareStatement("SELECT m.id_speciality, m.is_avaible, p.id, p.ssn, "
				        + "p.name, p.last_name, p.phone, p.direction, p.date_birth, p.sex "
				        + "FROM medic m "
				        + "FULL JOIN person p ON p.id = m.id_person "
				        + "WHERE m.id_person = ?");
				ps.setString(1, medicSQL);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					txtCode.setText(rs.getString("id"));
					ftxtSSN.setText(rs.getString("ssn"));
					txtName.setText(rs.getString("name"));
					txtLastName.setText(rs.getString("last_name"));
					ftxtPhone.setText(rs.getString("phone"));
					txtAAddress.setText(rs.getString("direction"));
					dateChooserBirthday.setDate(rs.getDate("date_birth"));
					cbSex.setSelectedItem(rs.getString("sex"));
					cbSpeciality.setSelectedIndex(rs.getInt("id_speciality"));
					if(rs.getInt("is_avaible") == 1) 
						chbxAvailable.setSelected(true);
					else
						chbxAvailable.setSelected(false);
				}
				
			} catch (SQLException esql) {
				JOptionPane.showMessageDialog(null, "error dentro del createmdeic loadsqlmedic. " +esql.toString());
				esql.printStackTrace();
			}
		}
		
	} //ENDLOAD
	
	
	
	
	
} //DND
