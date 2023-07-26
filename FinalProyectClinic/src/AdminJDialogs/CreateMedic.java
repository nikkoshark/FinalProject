package AdminJDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import com.toedter.calendar.JDateChooser;

import AdminPanels.ListMedic;
import Visual.CreateAppointment;
import javax.swing.JFormattedTextField;

public class CreateMedic extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JFormattedTextField ftxtSSN;
	private JTextField txtLastName;
	private JTextField txtName;
	private JTextField txtSpeciality;
	private JFormattedTextField ftxtPhone;
	private JTextField txtUser;
	private JTextField txtPsw;
	private JTextArea txtAAddress;
	private JComboBox cbSex;
	private JSpinner spnEntry;
	private JSpinner spnExit;
	private JDateChooser dateChooser;
	private Person medic;


	/**
	 * Create the dialog.
	 */
	public CreateMedic(Person med) {
		medic = med;
		
		setTitle("Modificar");
		if(medic == null) {
			setTitle("Registrar");
		}
		setLocationRelativeTo(null);
		setBounds(100, 100, 519, 399);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNuevoMdico = new JLabel("NUEVO M\u00C9DICO");
			lblNuevoMdico.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNuevoMdico.setBounds(174, 16, 174, 25);
			contentPanel.add(lblNuevoMdico);

			JLabel lblCode = new JLabel("C\u00F3digo");
			lblCode.setBounds(39, 52, 46, 14);
			contentPanel.add(lblCode);

			txtCode = new JTextField();
			txtCode.setEditable(false);
			txtCode.setColumns(10);
			txtCode.setBounds(39, 77, 128, 20);
			contentPanel.add(txtCode);

			JLabel lblCedula = new JLabel("C\u00E9dula");
			lblCedula.setBounds(177, 52, 46, 14);
			contentPanel.add(lblCedula);

			ftxtSSN = new JFormattedTextField();
			ftxtSSN.setBounds(177, 77, 142, 20);
			contentPanel.add(ftxtSSN);
			
			MaskFormatter maskssn;
			try {
				maskssn = new MaskFormatter("###-#######-#");
				maskssn.install(ftxtSSN);					
				
			} catch (ParseException e) {
				e.printStackTrace();
			}

			JLabel lblApellido = new JLabel("Apellido[s]");
			lblApellido.setBounds(247, 107, 126, 14);
			contentPanel.add(lblApellido);

			txtLastName = new JTextField();
			txtLastName.setColumns(10);
			txtLastName.setBounds(247, 128, 212, 20);
			contentPanel.add(txtLastName);

			JLabel lblNombre = new JLabel("Nombre[s]");
			lblNombre.setBounds(38, 105, 85, 14);
			contentPanel.add(lblNombre);
			
			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(38, 130, 199, 20);
			contentPanel.add(txtName);

			JLabel lblEspecialidad = new JLabel("Especialidad");
			lblEspecialidad.setBounds(177, 161, 110, 14);
			contentPanel.add(lblEspecialidad);

			txtSpeciality = new JTextField();
			txtSpeciality.setColumns(10);
			txtSpeciality.setBounds(177, 183, 159, 20);
			contentPanel.add(txtSpeciality);

			JLabel lblDir = new JLabel("Direcci\u00F3n");
			lblDir.setBounds(39, 213, 84, 14);
			contentPanel.add(lblDir);

			txtAAddress = new JTextArea();
			txtAAddress.setLineWrap(true);
			txtAAddress.setEditable(false);
			txtAAddress.setBounds(38, 233, 182, 73);
			contentPanel.add(txtAAddress);

			JLabel lblTel = new JLabel("Tel\u00E9fono");
			lblTel.setBounds(346, 159, 46, 14);
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
			lblSexo.setBounds(329, 52, 46, 14);
			contentPanel.add(lblSexo);
			
			cbSex = new JComboBox();
			cbSex.setModel(new DefaultComboBoxModel(new String[] {"<SELECCIONAR>", "Femenino", "Masculino"}));
			cbSex.setBounds(329, 77, 127, 20);
			contentPanel.add(cbSex);

			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setBounds(228, 213, 120, 14);
			contentPanel.add(lblUsuario);

			txtUser = new JTextField();
			txtUser.setColumns(10);
			txtUser.setBounds(228, 238, 110, 20);
			contentPanel.add(txtUser);

			JLabel lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setBounds(228, 261, 120, 14);
			contentPanel.add(lblContrasea);
			
			txtPsw = new JTextField();
			txtPsw.setColumns(10);
			txtPsw.setBounds(228, 286, 110, 20);
			contentPanel.add(txtPsw);
			
			JLabel lblHorarioEntrada = new JLabel("Horario Entrada");
			lblHorarioEntrada.setBounds(346, 213, 120, 14);
			contentPanel.add(lblHorarioEntrada);

			spnEntry = new JSpinner();
			spnEntry.setModel(new SpinnerListModel(new String[] {"0800", "0830", "0900", "0930", "1000", "1030", "1100", "1130", "1200", "1230", "1300", "1330", "1400", "1430", "1500", "1530", "1600", "1630", "1700", "1730", "1800"}));
			spnEntry.setBounds(346, 238, 110, 20);
			contentPanel.add(spnEntry);

			JLabel lblHorarioSalida = new JLabel("Horario Salida");
			lblHorarioSalida.setBounds(346, 261, 120, 14);
			contentPanel.add(lblHorarioSalida);
			
			spnExit = new JSpinner();
			spnExit.setBounds(346, 286, 110, 20);
			contentPanel.add(spnExit);
			
			JLabel lblDate = new JLabel("Fecha de Nacimiento");
			lblDate.setBounds(38, 159, 112, 14);
			contentPanel.add(lblDate);
			
			dateChooser = new JDateChooser();
			dateChooser.setBounds(36, 183, 131, 20);
			contentPanel.add(dateChooser);
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
						if(Clinic.getInstance().isUniqueSsnNumber(ftxtSSN.getText())) {
							String code = txtCode.getText();
							String ssn =  ftxtSSN.getText();
							String name = txtName.getText();
							String lastName = txtLastName.getText();
							String phone = ftxtPhone.getText();
							String address = txtAAddress.getText();
							Date date = dateChooser.getDate();
							char sex= String.valueOf(cbSex.getSelectedItem()).charAt(0);
							String speciality = txtSpeciality.getText();
							boolean available = true;
							if(medic == null) {
								Medic insmedic = new Medic(code, ssn, name, lastName, phone, address, new Date()/*txtDate.getText()*/, sex, speciality, available);
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
								medic.setBirthdate(date); //not functional (also DATE, not LocalDateTime)
								((Medic)medic).setAvailable(available); // not in the visual yet
								medic.setSex(sex);
								((Medic)medic).setSpeciality(speciality);
								
								Clinic.getInstance().modifiedPerson(medic);
								dispose();
							}
							ListMedic.loadMedic();
						} else {
							JOptionPane.showMessageDialog(null, "Este médico ya ha sido registrado.", "Médico Inválido", JOptionPane.ERROR_MESSAGE);
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
	

	private void clean(){
		txtAAddress.setText("");
		txtCode.setText("");
		txtLastName.setText("");
		txtName.setText("");
		ftxtPhone.setText("");
		txtPsw.setText("");
		txtSpeciality.setText("");
		ftxtSSN.setText("");
		txtUser.setText("");
	}
	private void loadMedic() {
		if(medic != null) {
			txtAAddress.setText(medic.getAddress());
			txtCode.setText(medic.getCode());
			txtLastName.setText(medic.getLastName());
			txtName.setText(medic.getName());
			ftxtPhone.setText(medic.getPhoneNumber());
			//txtPsw.setText();
			txtSpeciality.setText(((Medic)medic).getSpeciality());
			ftxtSSN.setText(medic.getSsn());
			//txtUser.setText("");
		}
		
		
	}
}
