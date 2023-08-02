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
import logic.Medic;
import logic.Person;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
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
	private Person medic;
	private ArrayList<String> optionString;
	private Date dateCompare = null;


	/**
	 * Create the dialog.
	 */
	public CreateMedic(Person med) {
		medic = med;
		
		setTitle("Modificar");
		if(medic == null) {
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
			txtCode.setEditable(false);
			txtCode.setText(getCodePerson(Clinic.getInstance().getCodePerson()));
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
			cbSpeciality.setModel(new DefaultComboBoxModel(new String[] {"<SELECCIONAR>", "Psic\u00F3logo", "Ginec\u00F3logo", "[AGREGAR]"}));
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
				if(medic == null) {
					btnAdd = new JButton("SALVAR");
				}
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!ftxtSSN.getText().isEmpty() && !txtName.getText().isEmpty() && !txtLastName.getText().isEmpty() && cbSpeciality.getSelectedIndex() > 0 && !ftxtPhone.getText().isEmpty() && dateChooserBirthday.getDate().getYear() < dateCompare.getYear() - 20) {
							if(Clinic.getInstance().isUniqueSsnNumber(ftxtSSN.getText()) || medic != null) {
								String code = txtCode.getText();
								String ssn =  ftxtSSN.getText();
								String name = txtName.getText();
								String lastName = txtLastName.getText();
								String phone = ftxtPhone.getText();
								String address = txtAAddress.getText();
								Date date = dateChooserBirthday.getDate();
								char sex = String.valueOf(cbSex.getSelectedItem()).charAt(0);
								String speciality = String.valueOf(cbSpeciality.getSelectedItem());
								boolean available = chbxAvailable.isSelected();
								if(medic == null) {
									Medic insmedic = new Medic(code, ssn, name, lastName, phone, address, date, sex, speciality, available);
									Clinic.getInstance().insertPerson(insmedic);
									JOptionPane.showMessageDialog(null, "Registro hecho.", "Registro", JOptionPane.INFORMATION_MESSAGE);
									clean();
								} else {
									medic.setCode(code);
									medic.setSsn(ssn);
									medic.setName(name);
									medic.setLastName(lastName);
									medic.setPhoneNumber(phone);
									medic.setAddress(address);
									medic.setBirthdate(date);
									medic.setSex(sex);
									((Medic)medic).setAvailable(available);
									((Medic)medic).setSpeciality(speciality);
									
									Clinic.getInstance().modifiedPerson(medic);
									dispose();
								}
								ListMedic.loadMedic();
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
		loadMedic();
	}
	

	private static String getCodePerson(int codePerson) {
		int total = codePerson / 10;
		String code = null;
		
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
	
	private void loadMedic() {
		if(medic != null) {
			
			txtAAddress.setText(medic.getAddress());
			txtCode.setText(medic.getCode());
			txtLastName.setText(medic.getLastName());
			txtName.setText(medic.getName());
			ftxtPhone.setText(medic.getPhoneNumber());
			cbSpeciality.setSelectedItem(((Medic)medic).getSpeciality());
			ftxtSSN.setText(medic.getSsn());
			dateChooserBirthday.setDate(medic.getBirthdate());
			if(medic.getSex() == 'F') {
				cbSex.setSelectedIndex(1);
			} else {
				cbSex.setSelectedIndex(2);
			}
			chbxAvailable.setSelected(((Medic)medic).isAvailable());
		}
	}
}
