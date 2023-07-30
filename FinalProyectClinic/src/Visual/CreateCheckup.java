package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import CheckupPanels.CheckupsRecord;
import CheckupPanels.DiseasePanel;
import CheckupPanels.InfoPanel;
import CheckupPanels.MedicalRecordPanel;
import logic.Appoinment;
import logic.Clinic;
import logic.Patient;
import logic.Person;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;

public class CreateCheckup extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtLastName;
	private InfoPanel infoPanel;
	private DiseasePanel diseasePanel;
	private MedicalRecordPanel medicalRecordPanel;
	private CheckupsRecord checkupsRecord;
	private JTextField txtAge;
	private JTextField txtCode;
	private JTextField txtEmail;
	private JDateChooser dateChooser;
	private JTextArea txtaAddress;
	private JFormattedTextField ftxtPhone;
	private JFormattedTextField ftxtSSN;
	private JComboBox cbSex;
	private JComboBox cbBloodType;
	private JButton btnEdit;
	private Person patient = null;
	private Appoinment appinfo = null;
	private int patientVer = 0;
	private char ch;


	/**
	 * Create the dialog.
	 */
	public CreateCheckup(Appoinment app) { //sends AN APPOINTMENT, if ssn exists, autofill. else, save ssn, name and phone
		appinfo = app;
		patient = Clinic.getInstance().searchPerson(appinfo.getSsn());
		
		if(patient == null) {
			patient = new Patient(null, appinfo.getSsn(), appinfo.getName(), null, appinfo.getPhoneNumber(), null, null, ch, null, null);
			//changeEditable(true, 1, "Establecer Paciente");
		}
		
		setBounds(100, 100, 915, 546);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setResizable(false);
		setLocationRelativeTo(null);
		
		infoPanel = new InfoPanel();
		diseasePanel = new DiseasePanel();
		medicalRecordPanel = new MedicalRecordPanel();
		checkupsRecord = new CheckupsRecord();
		
		contentPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("REGISTRAR CONSULTA");
		lblTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 28));
		lblTitle.setBounds(10, 11, 290, 33);
		contentPanel.add(lblTitle);
		
		{
			JPanel patientP = new JPanel();
			patientP.setBounds(10, 50, 265, 423);
			contentPanel.add(patientP);
			patientP.setLayout(null);
			{
				JLabel lblCode = new JLabel("C\u00F3digo");
				lblCode.setBounds(10, 24, 46, 14);
				patientP.add(lblCode);

				txtCode = new JTextField();
				txtCode.setEditable(false);
				txtCode.setColumns(10);
				txtCode.setBounds(10, 43, 120, 20);
				patientP.add(txtCode);
				
				JLabel lblCdula = new JLabel("C\u00E9dula");
				lblCdula.setBounds(140, 24, 46, 14);
				patientP.add(lblCdula);
				
				ftxtSSN = new JFormattedTextField();
				ftxtSSN.setEditable(false);
				ftxtSSN.setBounds(140, 43, 120, 20);
				patientP.add(ftxtSSN);

				MaskFormatter maskssn;
				try {
					maskssn = new MaskFormatter("###-#######-#");
					maskssn.install(ftxtSSN);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				
				JLabel lblName = new JLabel("Nombre");
				lblName.setBounds(10, 69, 46, 14);
				patientP.add(lblName);
				
				txtName = new JTextField();
				txtName.setEditable(false);
				txtName.setBounds(10, 88, 120, 20);
				patientP.add(txtName);
				txtName.setColumns(10);
				
				
				JLabel lblLastName = new JLabel("Apellido");
				lblLastName.setBounds(140, 69, 46, 14);
				patientP.add(lblLastName);
				
				txtLastName = new JTextField();
				txtLastName.setEditable(false);
				txtLastName.setColumns(10);
				txtLastName.setBounds(140, 88, 120, 20);
				patientP.add(txtLastName);
				
				
				JLabel lblGmail = new JLabel("Correo Electr\u00F3nico");
				lblGmail.setBounds(140, 119, 120, 14);
				patientP.add(lblGmail);
			
				txtEmail = new JTextField();
				txtEmail.setEditable(false);
				txtEmail.setColumns(10);
				txtEmail.setBounds(140, 138, 120, 20);
				patientP.add(txtEmail);
					
				
				JLabel label = new JLabel("Tel\u00E9fono");
				label.setBounds(10, 119, 120, 14);
				patientP.add(label);
				
				ftxtPhone = new JFormattedTextField();
				ftxtPhone.setEditable(false);
				ftxtPhone.setBounds(10, 138, 120, 20);
				patientP.add(ftxtPhone);
				
				MaskFormatter maskphone;
				try {
					maskphone = new MaskFormatter("###-###-####");
					maskphone.install(ftxtPhone);					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			
				JLabel lblAge = new JLabel("Edad");
				lblAge.setBounds(140, 169, 46, 14);
				patientP.add(lblAge);
				
				txtAge = new JTextField();
				txtAge.setEditable(false);
				txtAge.setBounds(140, 188, 120, 20);
				patientP.add(txtAge);
			
				JLabel lblBirthdate = new JLabel("Fecha de Nacimiento");
				lblBirthdate.setBounds(10, 169, 120, 14);
				patientP.add(lblBirthdate);
				
				dateChooser = new JDateChooser();
				dateChooser.setBounds(10, 188, 120, 20);
				patientP.add(dateChooser);
				dateChooser.setEnabled(false);
			
				JLabel lblAddress = new JLabel("Direcci\u00F3n");
				lblAddress.setBounds(11, 265, 249, 14);
				patientP.add(lblAddress);
				
				txtaAddress = new JTextArea();
				txtaAddress.setLineWrap(true);
				txtaAddress.setEditable(false);
				txtaAddress.setBounds(10, 285, 250, 93);
				patientP.add(txtaAddress);
			
				JLabel lblSex = new JLabel("Sexo");
				lblSex.setBounds(10, 219, 120, 14);
				patientP.add(lblSex);
				
				cbSex = new JComboBox();
				cbSex.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Femenino", "Masculino"}));
				cbSex.setBounds(10, 234, 120, 20);
				patientP.add(cbSex);
				
				JLabel lblBloodType = new JLabel("Tipo de Sangre");
				lblBloodType.setBounds(140, 219, 120, 14);
				patientP.add(lblBloodType);
				
				cbBloodType = new JComboBox();
				cbBloodType.setModel(new DefaultComboBoxModel(new String[] {"<No Verificado>", "A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"}));
				cbBloodType.setBounds(140, 234, 120, 20);
				patientP.add(cbBloodType);
			}
			btnEdit = new JButton("Editar Paciente");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(patientVer == 0) {
						changeEditable(true, 1, "Establecer Paciente");
					} else {
						changeEditable(false, 0, "Editar Paciente");
					}
				}
			});
			btnEdit.setBounds(10, 389, 250, 23);
			patientP.add(btnEdit);	
		}
		
		
		JPanel infoP = new JPanel();
		infoP.setBackground(SystemColor.inactiveCaption);
		infoP.setBounds(285, 70, 614, 403);
		contentPanel.add(infoP);
		infoP.setLayout(null);
			
			
		infoP.add(infoPanel);
		infoP.add(diseasePanel);
		infoP.add(medicalRecordPanel);
		infoP.add(checkupsRecord);
			
		menuclicked(infoPanel);
			
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnPatient = new JButton("*Salvar Paciente");
			btnPatient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String code = txtCode.getText();
					String ssn =  ftxtSSN.getText();
					String name = txtName.getText();
					String lastName = txtLastName.getText();
					String phone = ftxtPhone.getText();
					String email = txtEmail.getText();
					String address = txtaAddress.getText();
					Date date = dateChooser.getDate();
					char sex= String.valueOf(cbSex.getSelectedItem()).charAt(0);
					String bloodType = String.valueOf(cbBloodType.getSelectedItem());
					if(patient == null) {
						Patient insPatient = new Patient(code, ssn, name, lastName, phone, address, date, sex, bloodType, email);
						Clinic.getInstance().insertPerson(insPatient);
						JOptionPane.showMessageDialog(null, "Registro hecho.", "Registro", JOptionPane.INFORMATION_MESSAGE);
					} else {
						patient.setCode(code);
						patient.setSsn(ssn);
						patient.setName(name);
						patient.setLastName(lastName);
						patient.setPhoneNumber(phone);
						patient.setAddress(address);
						patient.setBirthdate(date);
						((Patient)patient).setBloodType(bloodType);
						((Patient)patient).setEmail(email);
						
						Clinic.getInstance().modifiedPerson(patient);
						dispose();
					}
				}
			});
			buttonPane.add(btnPatient);
			{
				JButton btnRegister = new JButton("REGISTRAR");
				buttonPane.add(btnRegister);
				getRootPane().setDefaultButton(btnRegister);
			
				JButton btnClose = new JButton("CANCELAR");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir? Perderá todo su proceso.", "Verificación", JOptionPane.OK_CANCEL_OPTION);
						if(option == JOptionPane.OK_OPTION) {
							dispose();
						}
					}
				});
				buttonPane.add(btnClose);
			}
		}
		
		JPanel infP = new JPanel();
		infP.setBounds(285, 40, 110, 33);
		contentPanel.add(infP);
		infP.addMouseListener(new PanelButtonMouseAdapter(infP, 1));
		infP.setLayout(null);
		
		JLabel lblInf = new JLabel("INFORMACI\u00D3N");
		lblInf.setBounds(8, 5, 93, 19);
		lblInf.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		infP.add(lblInf);
		
		JPanel hisP = new JPanel();
		hisP.setBounds(396, 40, 110, 33);
		contentPanel.add(hisP);
		hisP.addMouseListener(new PanelButtonMouseAdapter(hisP, 2));
		hisP.setLayout(null);
		
		JLabel lblHis = new JLabel("HISTORIAL");
		lblHis.setBounds(23, 5, 64, 19);
		lblHis.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		hisP.add(lblHis);
		
		loadperson();
	}

	private void loadperson() {
		ftxtSSN.setText(patient.getSsn());	
		txtName.setText(patient.getName());
		txtLastName.setText(patient.getLastName());
		txtaAddress.setText(patient.getAddress());
		txtEmail.setText(((Patient)patient).getEmail());
		dateChooser.setDate(patient.getBirthdate());
		ftxtPhone.setText(patient.getPhoneNumber());
		cbBloodType.setSelectedItem(((Patient)patient).getBloodType());
		cbSex.setSelectedItem(patient.getSex());		
		
	}

	private void changeEditable(boolean bool, int ver, String text) {
		ftxtSSN.setEditable(bool);
		txtName.setEditable(bool);
		txtLastName.setEditable(bool);
		txtEmail.setEditable(bool);
		dateChooser.setEnabled(bool);
		txtaAddress.setEditable(bool);
		ftxtPhone.setEditable(bool);
		patientVer = ver;
		btnEdit.setText(text);
	}

	private void menuclicked(JPanel panel) {
		infoPanel.setVisible(false);
		diseasePanel.setVisible(false);
		medicalRecordPanel.setVisible(false);
		checkupsRecord.setVisible(false);
		
		panel.setVisible(true);
		
	}
	
	private class PanelButtonMouseAdapter extends MouseAdapter{
			
		JPanel panel;
		private int typo;
		public PanelButtonMouseAdapter(JPanel panel, int typo) {
			this.panel = panel;
			this.typo = typo;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(119, 136, 153));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(240, 240, 240));
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(192, 192, 192));
			if(typo == 1) {
				menuclicked(infoPanel);
			} else {
				menuclicked(medicalRecordPanel);
			}
		}
	}
}
